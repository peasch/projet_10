package com.peasch.model.dto.User;

public class UserConnectedDto {
    private UserDto user;
    private String token;

    public UserConnectedDto() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
