package com.peasch.model.dto.Borrowings;

import com.googlecode.jmapper.annotations.JGlobalMap;
import com.peasch.model.dto.User.UserDto;
import com.peasch.model.dto.copies.CopyDto;

import java.io.Serializable;

@JGlobalMap
public class BorrowingDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String borrowingDate;
    private String returnDate;
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

    public String getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(String borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
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
