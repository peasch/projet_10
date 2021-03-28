package com.peasch.model.dto.WaitList;

import com.peasch.model.dto.Book.BookDto;
import com.peasch.model.dto.User.UserDto;

import java.util.HashSet;
import java.util.Set;

public class WaitListWithAllDto extends WaitListDto {
    private Set<UserDto> users = new HashSet<>();
    private Set<BookDto> books = new HashSet<>();

    public WaitListWithAllDto() {
    }

    public Set<UserDto> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDto> users) {
        this.users = users;
    }

    public Set<BookDto> getBooks() {
        return books;
    }

    public void setBooks(Set<BookDto> books) {
        this.books = books;
    }
}
