package com.peasch.webbooks.Beans;

import java.time.LocalDate;

public class BorrowingBean {
    private static final long serialVersionUID = 1L;
    private int id;
    private LocalDate borrowingDate;
    private LocalDate returnDate;
    private boolean extended;
    private UserBean user;
    private CopyBean copy;
    private Boolean returned;
    private Integer daysToGo;

    public BorrowingBean() {
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

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public CopyBean getCopy() {
        return copy;
    }

    public void setCopy(CopyBean copy) {
        this.copy = copy;
    }

    public Boolean getReturned() {
        return returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }

    public Integer getDaysToGo() {
        return daysToGo;
    }

    public void setDaysToGo(Integer daysToGo) {
        this.daysToGo = daysToGo;
    }
}
