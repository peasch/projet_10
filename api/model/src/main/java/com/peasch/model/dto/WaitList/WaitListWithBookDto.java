package com.peasch.model.dto.WaitList;

import com.peasch.model.dto.Book.BookDto;

import java.util.HashSet;
import java.util.Set;

public class WaitListWithBookDto extends WaitListDto  {
    private Set<BookDto> books = new HashSet<>();

    public WaitListWithBookDto() {
    }

    public Set<BookDto> getBooks() {
        return books;
    }

    public void setBooks(Set<BookDto> books) {
        this.books = books;
    }
}
