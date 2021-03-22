package com.peasch.model.dto.User;

import com.googlecode.jmapper.annotations.JMap;
import com.peasch.model.dto.Role.RoleDto;
import com.peasch.model.entities.Role;

import java.util.HashSet;
import java.util.Set;

public class UserWithRoleDTO extends UserDto {


    private Set<RoleDto> roles;

    public UserWithRoleDTO() {
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }
}
