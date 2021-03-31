package com.peasch.model.entities;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "wait_list")

public class WaitListDemand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="wait_list_date")
    private LocalDate waitListDate;
    @Column(name="first_return_date")
    private LocalDate firstReturnDate;
    @Column(name="contact_date")
    private LocalDate contactDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_book")
    private Book book;

    public WaitListDemand() {
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
}
