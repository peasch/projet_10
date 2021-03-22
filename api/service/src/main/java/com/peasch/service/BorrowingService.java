package com.peasch.service;

import com.peasch.model.dto.Borrowings.BorrowingDto;
import com.peasch.model.dto.Borrowings.BorrowingLateDTO;
import com.peasch.model.dto.Borrowings.BorrowingWithAllDTO;
import com.peasch.model.dto.User.UserDto;
import com.peasch.model.dto.copies.CopyWithALLDTO;
import com.peasch.model.entities.Borrowing;

import java.util.List;
import java.util.Set;

public interface BorrowingService {
    List<BorrowingDto> getBorrowings();


    BorrowingDto findById(Integer id);

    BorrowingWithAllDTO save(BorrowingWithAllDTO borrowingWithAllDTO);
    BorrowingWithAllDTO findByIdWithAll(Integer id);
    Set<BorrowingWithAllDTO> findBorrowingsByUserId(Integer id);
    BorrowingWithAllDTO extendByIdWithAll(Integer id);
    Set<BorrowingLateDTO> findAllTooLateBorrowings();
    BorrowingWithAllDTO returnBorrowing(Integer id, UserDto employee);
    BorrowingWithAllDTO addBorrowing(UserDto user, CopyWithALLDTO copy);
}
