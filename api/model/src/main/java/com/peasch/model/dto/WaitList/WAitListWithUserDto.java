package com.peasch.model.dto.WaitList;

import com.peasch.model.dto.Book.BookDto;
import com.peasch.model.dto.User.UserDto;
import com.peasch.model.entities.User;

import java.util.HashSet;
import java.util.Set;

public class WAitListWithUserDto extends WaitListDto  {
    private Set<UserDto> users = new HashSet<>();

    public WAitListWithUserDto() {
    }

    public Set<UserDto> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDto> users) {
        this.users = users;
    }
}
