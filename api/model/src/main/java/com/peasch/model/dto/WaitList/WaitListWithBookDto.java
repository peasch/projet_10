package com.peasch.model.dto.WaitList;

import com.peasch.model.dto.Book.BookDto;

import java.util.HashSet;
import java.util.Set;

public class WaitListWithBookDto extends WaitListDto  {
    private BookDto book;

    public WaitListWithBookDto() {
    }

    public BookDto getBook() {
        return book;
    }

    public void setBook(BookDto book) {
        this.book = book;
    }
}
