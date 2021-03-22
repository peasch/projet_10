package com.peasch.model.dto.copies;

import com.peasch.model.dto.Book.BookDto;

public class CopyWithBookDTO {
    private BookDto book;

    public CopyWithBookDTO() {
    }

    public BookDto getBook() {
        return book;
    }

    public void setBook(BookDto book) {
        this.book = book;
    }
}
