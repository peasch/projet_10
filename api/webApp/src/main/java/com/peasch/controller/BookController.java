package com.peasch.controller;

import com.peasch.model.dto.Book.BookDto;
import com.peasch.model.dto.Book.BookWithoutCopiesDTO;
import com.peasch.model.entities.Book;
import com.peasch.model.entities.Research;
import com.peasch.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;


    @GetMapping
    public List<BookDto> getBooks( @RequestHeader(name = "Authorization") String token){
        return service.getBooks();
    }

    @GetMapping("{id}")
    public BookDto getBookById(@PathVariable(value = "id")Integer id, @RequestHeader(name = "Authorization") String token){
        return service.findById(id);
    }

    @PostMapping("add")
    public void addBook (@RequestBody Book book, @RequestHeader(name = "Authorization") String token){
        service.save(book);
    }

    @PostMapping("search")
    public List<BookWithoutCopiesDTO> findBooksByAuthor(@RequestBody Research research, @RequestHeader(name = "Authorization") String token){ ;
        return service.findBooksByResearch(research);

    }

}
