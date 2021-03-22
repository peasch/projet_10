package com.peasch.service;

import com.peasch.model.dto.Book.BookDto;
import com.peasch.model.dto.Book.BookWithoutCopiesDTO;
import com.peasch.model.entities.Book;
import com.peasch.model.entities.Research;

import java.util.List;

public interface BookService {

    List<BookDto> getBooks();

    BookWithoutCopiesDTO findById(Integer id);

    Book save(Book book);

    /*List<BookDto> findBooksByAuthor_Name(String author);
    List<BookDto> findBooksByTitle(String title);*/
    List<BookWithoutCopiesDTO> findBooksByResearch(Research research);

}
