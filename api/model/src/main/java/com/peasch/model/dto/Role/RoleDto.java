package com.peasch.model.dto.Role;

import com.googlecode.jmapper.annotations.JGlobalMap;
import com.peasch.model.dto.User.UserDto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
@JGlobalMap
public class RoleDto implements Serializable {

    private Integer id;
    private String role;


    public RoleDto() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
