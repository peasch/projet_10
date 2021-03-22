package com.peasch.model.dto.User;

import com.peasch.model.dto.Borrowings.BorrowingDto;

import com.peasch.model.dto.Borrowings.BorrowingWithAllDTO;
import com.peasch.model.dto.Role.RoleDto;

import java.util.HashSet;
import java.util.Set;

public class UserWithAllDTO extends UserDto{
    private Set<BorrowingWithAllDTO> borrowings = new HashSet<>();
    private Set<RoleDto> roles;

    public UserWithAllDTO() {
    }

    public Set<BorrowingWithAllDTO> getBorrowings() {
        return borrowings;
    }

    public void setBorrowings(Set<BorrowingWithAllDTO> borrowings) {
        this.borrowings = borrowings;
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }
}
