package com.peasch.model.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="borrowing")

public class    Borrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name="borrowing_date")
    private LocalDate borrowingDate;
    @Column(name="returnDate")
    private LocalDate returnDate;
    @Column(name="extended")
    private boolean extended;
    @Column(name="returned")
    private Boolean returned;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_employee")
    private User returningEmployee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_copy")
    private Copy copy;
    @Transient
    private Integer daysToGo;

    public Borrowing() {
    }

    public Integer getDaysToGo() {
        return daysToGo;
    }

    public void setDaysToGo(Integer daysToGo) {
        this.daysToGo = daysToGo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(LocalDate borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isExtended() {
        return extended;
    }

    public void setExtended(boolean extended) {
        this.extended = extended;
    }

    public Boolean getReturned() {
        return returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Copy getCopy() {
        return copy;
    }

    public void setCopy(Copy copy) {
        this.copy = copy;
    }

    public User getReturningEmployee() {
        return returningEmployee;
    }

    public void setReturningEmployee(User returningEmployee) {
        this.returningEmployee = returningEmployee;
    }
}
