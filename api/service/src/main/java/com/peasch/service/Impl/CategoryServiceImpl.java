package com.peasch.service.Impl;

import com.googlecode.jmapper.JMapper;
import com.peasch.model.dto.Categories.CategoryDto;
import com.peasch.model.entities.Category;
import com.peasch.repository.dao.CategoryDao;
import com.peasch.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private JMapper<CategoryDto, Category> categoryToDTOMapper;

    @Autowired
    private JMapper<Category, CategoryDto>  dtoToCategoryMapper;


    public List<CategoryDto> getCategories(){

        List<Category> categories = categoryDao.findAll();
        return categories.stream().map(x->categoryToDTOMapper.getDestination(x)).collect(Collectors.toList());
    }

    public CategoryDto findById(Integer id){
        return categoryToDTOMapper.getDestination(categoryDao.findById(id).get());

    }

    public CategoryDto save(CategoryDto categoryDto){
        return categoryToDTOMapper.getDestination(categoryDao.save(dtoToCategoryMapper.getDestination(categoryDto)));
    }
}
