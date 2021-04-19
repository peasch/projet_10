package com.peasch.controller;

import com.peasch.model.dto.Book.BookDto;
import com.peasch.model.dto.Book.BookWithoutCopiesDTO;
import com.peasch.model.entities.Research;
import com.peasch.service.BookService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping
    public List<BookDto> getBooks( @RequestHeader(name = "Authorization") String token) throws NotFoundException {
        List<BookWithoutCopiesDTO> books = service.getBooksAvailable();
        return service.getBooks();
    }

    @GetMapping("{id}")
    public ResponseEntity getBookById(@PathVariable(value = "id")Integer id, @RequestHeader(name = "Authorization") String token) throws NotFoundException {
        try{
            return new ResponseEntity( service.findById(id), HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("add")
    public BookDto addBook (@RequestBody BookDto book, @RequestHeader(name = "Authorization") String token){
        return service.save(book);
    }
    @GetMapping("delete/{id}")
    public void deleteBook(@PathVariable(value="id")Integer id, @RequestHeader(name = "Authorization") String token) throws NotFoundException {
        service.delete(id);
    }

    @PostMapping("search")
    public List<BookWithoutCopiesDTO> findBooksByAuthor(@RequestBody Research research, @RequestHeader(name = "Authorization") String token) throws NotFoundException { ;
        return service.findBooksByResearch(research);

    }

    @GetMapping("availables")
    public List<BookWithoutCopiesDTO> checkAvailableBooks( @RequestHeader(name = "Authorization") String token) throws NotFoundException {

        return service.getBooksAvailable();
    }

    @GetMapping("availablesAndWaitList")
    public List<BookWithoutCopiesDTO> checkAvailableBooksAndWaitList( @RequestHeader(name = "Authorization") String token){

        return service.getBooksAvailableAndWaitListed();
    }

}
