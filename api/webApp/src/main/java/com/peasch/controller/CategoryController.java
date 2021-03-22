package com.peasch.controller;

import com.peasch.model.dto.Categories.CategoryDto;
import com.peasch.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;


    @GetMapping
    public List<CategoryDto> getCategories(){
        return service.getCategories();
    }

    @GetMapping("{id}")
    public CategoryDto getCategoryById(@PathVariable(value = "id")Integer id){
        return service.findById(id);
    }

    @PostMapping("add")
    public void addCategory (@RequestBody CategoryDto categoryDto){
        service.save(categoryDto);
    }


}
