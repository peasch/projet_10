package com.peasch.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="category")

public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="description")
    private String description;


    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Book> booksOfCategory = new HashSet<>();

    public Category() {
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

    public Set<Book> getBooksOfCategory() {
        return booksOfCategory;
    }

    public void setBooksOfCategory(Set<Book> booksOfCategory) {
        this.booksOfCategory = booksOfCategory;
    }
}
