package com.peasch.model.dto.WaitList;

import com.peasch.model.dto.Book.BookDto;
import com.peasch.model.dto.User.UserDto;
import com.peasch.model.entities.User;

import java.util.HashSet;
import java.util.Set;

public class WAitListWithUserDto extends WaitListDto  {
    private UserDto user;

    public WAitListWithUserDto() {
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
