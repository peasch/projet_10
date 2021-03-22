package com.peasch.model.dto.Borrowings;

import com.peasch.model.dto.User.UserDto;
import com.peasch.model.dto.copies.CopyDto;
import com.peasch.model.dto.copies.CopyWithALLDTO;

public class BorrowingWithCopyAndUserDto {
    private UserDto user;
    private CopyDto copy;

    public BorrowingWithCopyAndUserDto() {
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public CopyDto getCopy() {
        return copy;
    }

    public void setCopy(CopyDto copy) {
        this.copy = copy;
    }
}
