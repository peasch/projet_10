package com.peasch.model.dto.WaitList;

import com.googlecode.jmapper.annotations.JGlobalMap;
import com.peasch.model.entities.Book;
import com.peasch.model.entities.User;

import javax.persistence.*;
@JGlobalMap
public class WaitListDto {
    private static final long serialVersionUID = 1L;
    private int id;
    private String waitListDate;
    private User user;
    private Book book;

    public WaitListDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWaitListDate() {
        return waitListDate;
    }

    public void setWaitListDate(String waitListDate) {
        this.waitListDate = waitListDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
