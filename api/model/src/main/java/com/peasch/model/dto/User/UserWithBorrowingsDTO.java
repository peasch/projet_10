package com.peasch.model.dto.User;

import com.peasch.model.dto.Borrowings.BorrowingDto;

import java.util.HashSet;
import java.util.Set;

public class UserWithBorrowingsDTO extends UserDto {

    private Set<BorrowingDto> borrowings = new HashSet<>();


    public UserWithBorrowingsDTO() {
    }

    public Set<BorrowingDto> getBorrowings() {
        return borrowings;
    }

    public void setBorrowings(Set<BorrowingDto> borrowings) {
        this.borrowings = borrowings;
    }


}
