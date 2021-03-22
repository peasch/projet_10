package com.peasch.model.dto.Categories;

import com.peasch.model.dto.Book.BookDto;

import java.util.HashSet;
import java.util.Set;

public class CategorywithBooksDTO extends CategoryDto {
    private Set<BookDto> booksOfCategory = new HashSet<>();

    public CategorywithBooksDTO() {
    }

    public Set<BookDto> getBooksOfCategory() {
        return booksOfCategory;
    }

    public void setBooksOfCategory(Set<BookDto> booksOfCategory) {
        this.booksOfCategory = booksOfCategory;
    }
}
