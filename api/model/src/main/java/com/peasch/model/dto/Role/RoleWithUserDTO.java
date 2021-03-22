package com.peasch.model.dto.Role;

import com.peasch.model.dto.User.UserDto;
import com.peasch.model.entities.User;

public class RoleWithUserDTO extends RoleDto {

    private UserDto user;

    public RoleWithUserDTO() {
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
