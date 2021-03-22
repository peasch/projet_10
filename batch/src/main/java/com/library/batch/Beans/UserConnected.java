package com.library.batch.Beans;

public class UserConnected {
    private UserBean user;
    private String token;

    public UserConnected() {
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
