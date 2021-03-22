package com.peasch.model.dto.Author;

import com.peasch.model.dto.Book.BookDto;

import java.util.HashSet;
import java.util.Set;

public class AuthorWithBooksDTO extends AuthorDto {
    private Set<BookDto> books = new HashSet<>();

    public AuthorWithBooksDTO() {
    }

    public Set<BookDto> getBooks() {
        return books;
    }

    public void setBooks(Set<BookDto> books) {
        this.books = books;
    }
}
