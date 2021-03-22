package com.peasch.service;

import com.peasch.model.dto.Role.RoleDto;

import java.util.List;

public interface RoleService {

    RoleDto findByRole(String role);
    List<RoleDto> getRoles();
}
