package com.peasch.repository.dao;

import com.peasch.model.entities.Role;
import com.peasch.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role,Integer> {
    Role findByRole(String role);

}
