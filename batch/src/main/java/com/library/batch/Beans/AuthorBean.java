package com.library.batch.Beans;

import java.util.HashSet;
import java.util.Set;

public class AuthorBean {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String firstName;
    private String birthDate;
    private String deathDate;
    private Set<BookBean> books = new HashSet<>();

    public AuthorBean() {
    }

    @Override
    public String toString() {
        return "AuthorBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", deathDate='" + deathDate + '\'' +
                ", books=" + books +
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(String deathDate) {
        this.deathDate = deathDate;
    }

    public Set<BookBean> getBooks() {
        return books;
    }

    public void setBooks(Set<BookBean> books) {
        this.books = books;
    }
}
