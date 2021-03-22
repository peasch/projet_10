package com.peasch.service;

import com.peasch.model.dto.User.UserDto;
import com.peasch.model.dto.User.UserWithAllDTO;
import com.peasch.model.dto.User.UserWithRoleDTO;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();
    UserDto findById(Integer id);
    UserDto save(UserDto userDto);
    UserWithAllDTO findUserByUserName(String userName);
    UserWithRoleDTO findUserByUserNameWithRole(String userName);
    UserDto findIfExistsUsername(String userName);
    UserWithRoleDTO saveWithRole(UserWithRoleDTO userDto);

}
