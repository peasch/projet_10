package com.peasch.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Lob
    @Column(name = "summary")
    private String summary;
    @Column(name="cover")
    private String cover;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_category")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_author")
    private Author author;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private Set<Copy> copiesOfBook = new HashSet<>();

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private Set<WaitListDemand> demands = new HashSet<>();

    public Book() {
    }

    public Set<WaitListDemand> getDemands() {
        return demands;
    }

    public void setDemands(Set<WaitListDemand> demands) {
        this.demands = demands;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<Copy> getCopiesOfBook() {
        return copiesOfBook;
    }

    public void setCopiesOfBook(Set<Copy> copiesOfBook) {
        this.copiesOfBook = copiesOfBook;
    }
}
