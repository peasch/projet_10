package com.peasch.service;

import com.peasch.model.dto.Categories.CategoryDto;

import java.util.List;

public interface CategoryService {
   List<CategoryDto> getCategories();

    CategoryDto findById(Integer id);

    CategoryDto save(CategoryDto categoryDto);
}
