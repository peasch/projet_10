package com.peasch.model.dto.Book;

import com.peasch.model.dto.Author.AuthorDto;
import com.peasch.model.dto.Categories.CategoryDto;
import com.peasch.model.dto.copies.CopyDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookWithAllDTO extends BookDto {
    private AuthorDto author;
    private CategoryDto category;
    private List<CopyDto> copiesOfBook = new ArrayList<>();

    public BookWithAllDTO() {
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public List<CopyDto> getCopiesOfBook() {
        return copiesOfBook;
    }

    public void setCopiesOfBook(List<CopyDto> copiesOfBook) {
        this.copiesOfBook = copiesOfBook;
    }
}
