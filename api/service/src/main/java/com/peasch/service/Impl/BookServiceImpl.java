package com.peasch.service.Impl;

import com.googlecode.jmapper.JMapper;
import com.peasch.model.dto.Author.AuthorDto;
import com.peasch.model.dto.Book.BookDto;
import com.peasch.model.dto.Book.BookWithAllDTO;
import com.peasch.model.dto.Book.BookWithoutCopiesDTO;
import com.peasch.model.dto.Categories.CategoryDto;
import com.peasch.model.dto.copies.CopyDto;
import com.peasch.model.entities.*;
import com.peasch.repository.dao.BookDao;
import com.peasch.service.AuthorService;
import com.peasch.service.BookService;
import com.peasch.service.CategoryService;
import com.peasch.service.CopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private JMapper<BookDto,Book> bookJMapper;
    @Autowired
    private JMapper<AuthorDto, Author> authorJMapper;
    @Autowired
    private CopyService copyService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private JMapper<BookWithoutCopiesDTO, Book>  bookWithoutCopiesDTOBookJMapper;
    @Autowired
    private JMapper<CategoryDto, Category> categoryToDTOMapper;


    public List<BookDto> getBooks() {
        List<Book> books = bookDao.findAll();
        return books.stream().map(x-> bookJMapper.getDestination(x)).collect(Collectors.toList());
    }

    public BookWithoutCopiesDTO findById(Integer id) {
        Book book = bookDao.findById(id).get();
        BookWithoutCopiesDTO bookDto =  bookWithoutCopiesDTOBookJMapper.getDestination(book);
        bookDto.setAuthor(authorService.findById(book.getAuthor().getId()));
        bookDto.setCategory(categoryService.findById(book.getCategory().getId()));
        return bookDto;
    }



    public Book save(Book book) {
        return bookDao.save(book);
    }

    /*public List<BookDto> findBooksByAuthor_Name(String authorName) {
        List<BookDto> bookDtoSearched = new ArrayList<>();
        List<Book> bookSearched = bookDao.findBooksByAuthor_NameLike("%" + authorName + "%");
        for (Book book : bookSearched) {
            bookDtoSearched.add(mapper.fromBookToDto(book));
        }
        return bookDtoSearched;
    }*/

    /*public List<BookDto> findBooksByTitle(String title) {
        List<BookDto> booksDtoSearched = new ArrayList<>();
        List<Book> bookSearched = bookDao.findBooksByTitleLike("%" + title + "%");
        for (Book book : bookSearched) {
            booksDtoSearched.add(mapper.fromBookToDto(book));
        }
        return booksDtoSearched;
    }*/

    public List<BookWithoutCopiesDTO> findBooksByResearch(Research research) {
        List<BookWithoutCopiesDTO> booksDtoResearched = new ArrayList<>();
        List<Book> bookSearched = bookDao.findBooksByTitleLikeAndAuthor_NameLikeAndCategory_NameLike("%" + research.getResearchTitle() + "%", "%" + research.getResearchAuthor()
                + "%", "%" + research.getResearchCategory() + "%");
        for (Book book : bookSearched) {
            booksDtoResearched.add(this.findById(book.getId()));
        }
        return booksDtoResearched;
    }

}
