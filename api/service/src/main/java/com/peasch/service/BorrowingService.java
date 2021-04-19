package com.peasch.service;

import com.peasch.model.dto.Borrowings.BorrowingDto;
import com.peasch.model.dto.Borrowings.BorrowingLateDTO;
import com.peasch.model.dto.Borrowings.BorrowingWithAllDTO;
import com.peasch.model.dto.User.UserDto;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BorrowingService {
    List<BorrowingDto> getBorrowings();


    BorrowingDto findById(Integer id);

    BorrowingWithAllDTO save(BorrowingWithAllDTO borrowingWithAllDTO);
    BorrowingWithAllDTO findByIdWithAll(Integer id) throws NotFoundException;

    Set<BorrowingWithAllDTO> findReturnedBorrowingsByUserId(Integer id) throws NotFoundException;
    Set<BorrowingWithAllDTO> findUnReturnedBorrowingsByUserId(Integer id) throws NotFoundException;
    ResponseEntity extendByIdWithAll(Integer id);
    Set<BorrowingLateDTO> findAllTooLateBorrowings();
    ResponseEntity returnBorrowing(Integer id, UserDto employee);
    BorrowingWithAllDTO addBorrowing(BorrowingWithAllDTO borrowingWithAllDTO);
    ResponseEntity deleteBorrowing (BorrowingWithAllDTO borrowingWithAllDTO);
    Boolean bookRentable(Integer userId, Integer bookId) throws NotFoundException;
    LocalDate findBorrowingsByBookId (Integer bookId);
}
