package com.peasch.model.dto.WaitList;

import com.peasch.model.dto.Book.BookWithoutCopiesDTO;
import com.peasch.model.dto.User.UserDto;

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
