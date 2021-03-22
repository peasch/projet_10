package com.peasch.model.dto.Book;

import com.peasch.model.dto.Author.AuthorDto;
import com.peasch.model.dto.Categories.CategoryDto;

public class BookWithoutCopiesDTO extends BookDto {
    private AuthorDto author;
    private CategoryDto category;

    public BookWithoutCopiesDTO() {
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
}
