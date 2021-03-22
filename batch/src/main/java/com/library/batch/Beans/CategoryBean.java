package com.library.batch.Beans;

import java.util.HashSet;
import java.util.Set;

public class CategoryBean {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String description;
    private Set<BookBean> booksOfCategory = new HashSet<>();

    public CategoryBean() {
    }

    @Override
    public String toString() {
        return "CategoryBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", booksOfCategory=" + booksOfCategory +
                '}';
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

    public Set<BookBean> getBooksOfCategory() {
        return booksOfCategory;
    }

    public void setBooksOfCategory(Set<BookBean> booksOfCategory) {
        this.booksOfCategory = booksOfCategory;
    }
}
