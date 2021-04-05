package com.library.batch.Beans;

import java.time.LocalDate;

public class WaitListBean {
    private static final long serialVersionUID = 1L;
    private int id;
    private LocalDate waitListDate;
    private LocalDate firstReturnDate;
    private LocalDate contactDate;
    private UserBean user;
    private BookBean book;

    public WaitListBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getWaitListDate() {
        return waitListDate;
    }

    public void setWaitListDate(LocalDate waitListDate) {
        this.waitListDate = waitListDate;
    }

    public LocalDate getFirstReturnDate() {
        return firstReturnDate;
    }

    public void setFirstReturnDate(LocalDate firstReturnDate) {
        this.firstReturnDate = firstReturnDate;
    }

    public LocalDate getContactDate() {
        return contactDate;
    }

    public void setContactDate(LocalDate contactDate) {
        this.contactDate = contactDate;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public BookBean getBook() {
        return book;
    }

    public void setBook(BookBean book) {
        this.book = book;
    }
}
