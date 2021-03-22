package com.peasch.model.dto.Borrowings;

import com.peasch.model.dto.User.UserDto;
import com.peasch.model.dto.copies.CopyDto;
import com.peasch.model.dto.copies.CopyWithALLDTO;

public class BorrowingWithAllDTO extends BorrowingDto {
    private UserDto user;
    private CopyWithALLDTO copy;
    private Integer daysToGo;
    private UserDto returningEmployee;

    public BorrowingWithAllDTO() {
    }

    public CopyWithALLDTO getCopy() {
        return copy;
    }

    public void setCopy(CopyWithALLDTO copy) {
        this.copy = copy;
    }

    public Integer getDaysToGo() {
        return daysToGo;
    }

    public void setDaysToGo(Integer daysToGo) {
        this.daysToGo = daysToGo;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public UserDto getReturningEmployee() {
        return returningEmployee;
    }

    public void setReturningEmployee(UserDto returningEmployee) {
        this.returningEmployee = returningEmployee;
    }
}
