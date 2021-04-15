package com.peasch.controller;

import com.peasch.controller.security.config.JwtTokenProvider;
import com.peasch.controller.security.service.CustomUserDetailsService;
import com.peasch.model.dto.Role.RoleDto;
import com.peasch.model.dto.User.AuthBody;
import com.peasch.model.dto.User.UserDto;
import com.peasch.model.dto.User.UserWithRoleDTO;
import com.peasch.service.RoleService;
import com.peasch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthBody data) throws AuthenticationException {
        try {
            String userName = data.getUserName();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, data.getPassword()));

       return new ResponseEntity( jwtTokenProvider.createToken(userName, userService.findUserByUserNameWithRole(userName).getRoles()), HttpStatus.OK);

        }catch (BadCredentialsException e) {
            /*throw new AuthenticationException("Invalid Username/password");*/
return new ResponseEntity("Invalid Username/password",HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserWithRoleDTO user) {
        Set<RoleDto> roles = new HashSet<>();
        RoleDto role = new RoleDto();
        role = roleService.findByRole("USER");
        roles.add(role);
        user.setRoles(roles);
        UserDto userExists = userService.findIfExistsUsername(user.getEmail());
        if (userExists != null) {
            throw new BadCredentialsException("User with username: " + user.getEmail() + " already exists");
        }
        customUserDetailsService.saveUser(user);
        Map<Object, Object> model = new HashMap<>();
        model.put("message", "User registered successfully");
        return ok(model);
    }
}
