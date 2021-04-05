package com.peasch.model.dto.WaitList;

import com.peasch.model.dto.Book.BookDto;
import com.peasch.model.dto.Book.BookWithAllDTO;
import com.peasch.model.dto.Book.BookWithAuthorDTO;
import com.peasch.model.dto.Book.BookWithoutCopiesDTO;
import com.peasch.model.dto.User.UserDto;

import java.util.HashSet;
import java.util.Set;

public class WaitListWithAllDto extends WaitListDto {
    private UserDto user;
    private BookWithoutCopiesDTO book;

    public WaitListWithAllDto() {
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public BookWithoutCopiesDTO getBook() {
        return book;
    }

    public void setBook(BookWithoutCopiesDTO book) {
        this.book = book;
    }
}
