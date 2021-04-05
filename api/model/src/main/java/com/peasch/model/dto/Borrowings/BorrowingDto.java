package com.peasch.model.dto.Borrowings;

import com.googlecode.jmapper.annotations.JGlobalMap;


import java.io.Serializable;
import java.time.LocalDate;

@JGlobalMap
public class BorrowingDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private LocalDate borrowingDate;
    private LocalDate returnDate;
    private boolean extended;
    private Boolean returned;

    public BorrowingDto() {
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
}
