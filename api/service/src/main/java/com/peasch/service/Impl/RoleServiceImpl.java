package com.peasch.service.Impl;

import com.googlecode.jmapper.JMapper;
import com.peasch.model.dto.Role.RoleDto;
import com.peasch.model.dto.User.UserDto;
import com.peasch.model.entities.Role;
import com.peasch.model.entities.User;
import com.peasch.repository.dao.RoleDao;
import com.peasch.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private JMapper<RoleDto, Role> roleToDTOMapper;
    @Autowired
    private RoleDao roleDao;

    @Override
    public RoleDto findByRole(String role) {
        Role rolee = roleDao.findByRole(role);
        RoleDto roleDto = new RoleDto();
        roleDto.setId(rolee.getId());
        roleDto.setRole(rolee.getRole());
        return roleDto;
    }

    @Override
    public List<RoleDto> getRoles() {
        List<Role> roles = roleDao.findAll();
        return roles.stream().map(x -> roleToDTOMapper.getDestination(x)).collect(Collectors.toList());
    }
}
