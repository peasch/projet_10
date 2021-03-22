package com.peasch.model.dto.Borrowings;

import com.peasch.model.dto.User.UserDto;

public class BorrowingLateDTO extends BorrowingDto {

    private UserDto user;

    public BorrowingLateDTO() {
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
