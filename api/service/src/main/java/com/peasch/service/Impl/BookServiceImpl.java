package com.peasch.service.Impl;

import com.googlecode.jmapper.JMapper;
import com.peasch.model.dto.Book.BookDto;
import com.peasch.model.dto.Book.BookWithoutCopiesDTO;
import com.peasch.model.entities.Book;
import com.peasch.model.entities.Research;
import com.peasch.repository.dao.BookDao;
import com.peasch.service.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private JMapper<BookDto, Book> bookJMapper;
    @Autowired
    private CopyService copyService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private JMapper<BookWithoutCopiesDTO, Book> bookWithoutCopiesDTOBookJMapper;


    @Autowired
    private WaitListService waitListService;

    public List<BookDto> getBooks() {
        List<Book> books = bookDao.findAll();
        return books.stream().map(x -> bookJMapper.getDestination(x)).collect(Collectors.toList());
    }

    public List<BookWithoutCopiesDTO> getBooksAvailable() throws NotFoundException {
        List<Book> books = bookDao.findAll();
        List<BookWithoutCopiesDTO> booksWoCopies = new ArrayList<>();
        for (Book book : books) {
            if (this.checkBookAvailable(book.getId())) {
                booksWoCopies.add(bookWithoutCopiesDTOBookJMapper.getDestination(book));
            }
        }
        return booksWoCopies;
    }

    public BookWithoutCopiesDTO findById(Integer id) throws NotFoundException {
        try {
            Book book = bookDao.findById(id).get();
            BookWithoutCopiesDTO bookDto = bookWithoutCopiesDTOBookJMapper.getDestination(book);
            bookDto.setAuthor(authorService.findById(book.getAuthor().getId()));
            bookDto.setCategory(categoryService.findById(book.getCategory().getId()));
            return bookDto;
        }catch (NoSuchElementException e){
            throw new NotFoundException("not such book");
        }
    }
    /*public BookWithAllDTO findByIdWithAll(Integer id) {
        Book book = bookDao.findById(id).get();
        BookWithAllDTO bookDto = bookToDTOwithAllMapper.getDestination(book);
        bookDto.setAuthor(authorService.findById(book.getAuthor().getId()));
        bookDto.setCategory(categoryService.findById(book.getCategory().getId()));
        bookDto.setCopiesOfBook(copyService.findCopiesByBook_Id(book.getId()));
        return bookDto;
    }*/


    public Book save(Book book) {
        return bookDao.save(book);
    }


    public List<BookWithoutCopiesDTO> findBooksByResearch(Research research) throws NotFoundException {
        List<BookWithoutCopiesDTO> booksDtoResearched = new ArrayList<>();
        List<Book> bookSearched = bookDao.findBooksByTitleLikeAndAuthor_NameLikeAndCategory_NameLike("%" + research.getResearchTitle() + "%", "%" + research.getResearchAuthor()
                + "%", "%" + research.getResearchCategory() + "%");
        for (Book book : bookSearched) {
            booksDtoResearched.add(this.findById(book.getId()));
        }
        return booksDtoResearched;
    }

    public Book checkAvailabilityOfBook(Book book) {
        if (copyService.findNumberOfCopiesAvailable(book.getId()) > 0) {
            book.setAvailable(true);

        } else {
            book.setAvailable(false);
        }

        return book;
    }

    public Boolean checkBookAvailable(int bookId) throws NotFoundException {
        BookDto book = this.findById(bookId);
        return book.getAvailable();

    }

    public List<BookWithoutCopiesDTO> getBooksAvailableAndWaitListed() {
        List<Book> books = bookDao.findAll();
        List<BookWithoutCopiesDTO> booksWoCopies = new ArrayList<>();
        for (Book book : books) {
            book = this.checkAvailabilityOfBook(book);
            if (waitListService.isWaitListed(book.getId()) && book.getAvailable()) {
                booksWoCopies.add(bookWithoutCopiesDTOBookJMapper.getDestination(book));
            }
        }
        return booksWoCopies;
    }
}
