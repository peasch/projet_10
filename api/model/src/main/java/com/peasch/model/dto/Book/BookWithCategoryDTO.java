package com.peasch.model.dto.Book;

import com.peasch.model.dto.Categories.CategoryDto;

public class BookWithCategoryDTO {
    private CategoryDto category;

    public BookWithCategoryDTO() {
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }
}
