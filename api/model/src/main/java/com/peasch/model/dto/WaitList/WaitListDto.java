package com.peasch.model.dto.WaitList;

import com.googlecode.jmapper.annotations.JGlobalMap;


import javax.persistence.*;
import java.time.LocalDate;

@JGlobalMap
public class WaitListDto {
    private static final long serialVersionUID = 1L;
    private int id;
    private LocalDate waitListDate;
    private LocalDate firstReturnDate;
    private LocalDate contactDate;

    public WaitListDto() {
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
}
