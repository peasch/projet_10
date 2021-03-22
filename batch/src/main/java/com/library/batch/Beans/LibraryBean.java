package com.library.batch.Beans;

import java.util.HashSet;
import java.util.Set;

public class LibraryBean {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String adress;
    private String phone;
    private String email;
    private Set<UserBean> Users = new HashSet<>();

    public LibraryBean() {
    }

    public LibraryBean(int id, String name, String adress, String phone, String email, Set<UserBean> users) {
        this.id = id;
        this.name = name;
        this.adress = adress;
        this.phone = phone;
        this.email = email;
        Users = users;
    }

    @Override
    public String toString() {
        return "LibraryBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", Users=" + Users +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<UserBean> getUsers() {
        return Users;
    }

    public void setUsers(Set<UserBean> users) {
        Users = users;
    }
}
