package com.peasch.model.dto.Categories;


import com.googlecode.jmapper.annotations.JGlobalMap;
import com.peasch.model.dto.Book.BookDto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@JGlobalMap
public class CategoryDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String description;


    public CategoryDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
