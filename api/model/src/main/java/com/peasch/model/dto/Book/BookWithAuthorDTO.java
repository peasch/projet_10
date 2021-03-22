package com.peasch.model.dto.Book;

import com.peasch.model.dto.Author.AuthorDto;

public class BookWithAuthorDTO extends BookDto {
    private AuthorDto author;

    public BookWithAuthorDTO() {
    }


    public AuthorDto getAuthor() {
        return author;
    }


    public void setAuthor(AuthorDto author) {
        this.author = author;
    }
}
