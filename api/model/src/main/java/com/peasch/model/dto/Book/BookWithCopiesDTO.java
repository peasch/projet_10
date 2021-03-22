package com.peasch.model.dto.Book;

import com.peasch.model.dto.copies.CopyDto;

import java.util.HashSet;
import java.util.Set;

public class BookWithCopiesDTO extends BookDto {

    private Set<CopyDto> copiesOfBook = new HashSet<>();

    public BookWithCopiesDTO() {
    }


    public Set<CopyDto> getCopiesOfBook() {
        return copiesOfBook;
    }


    public void setCopiesOfBook(Set<CopyDto> copiesOfBook) {
        this.copiesOfBook = copiesOfBook;
    }
}
