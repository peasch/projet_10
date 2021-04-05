package com.peasch.service;

import com.peasch.model.dto.Book.BookDto;
import com.peasch.model.dto.Book.BookWithAllDTO;
import com.peasch.model.dto.Book.BookWithoutCopiesDTO;
import com.peasch.model.entities.Book;
import com.peasch.model.entities.Research;

import java.util.List;

public interface BookService {

    List<BookDto> getBooks();

    BookWithoutCopiesDTO findById(Integer id);

    Book save(Book book);
    List<BookWithoutCopiesDTO> findBooksByResearch(Research research);
    Book checkAvailabilityOfBook(Book book);
    List<BookWithoutCopiesDTO> getBooksAvailable();
    List<BookWithoutCopiesDTO> getBooksAvailableAndWaitListed();
    /*BookWithAllDTO findByIdWithAll(Integer id);*/
    Boolean checkBookAvailable(int bookId);

}
