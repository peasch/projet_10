package com.peasch.controller;

import com.peasch.controller.security.config.JwtTokenProvider;
import com.peasch.model.dto.Borrowings.BorrowingDto;
import com.peasch.model.dto.Borrowings.BorrowingLateDTO;
import com.peasch.model.dto.Borrowings.BorrowingWithAllDTO;
import com.peasch.model.dto.User.UserDto;
import com.peasch.service.BorrowingService;
import com.peasch.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/borrowings")
public class BorrowingController {

   @Autowired
    private BorrowingService service;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserService userService;


    @GetMapping
    public List<BorrowingDto> getBorrowings( @RequestHeader(name = "Authorization") String token){
        return service.getBorrowings();
    }

    @GetMapping("{id}")
    public BorrowingWithAllDTO getBorrowingById(@PathVariable(value = "id")Integer id, @RequestHeader(name = "Authorization") String token) throws NotFoundException {
        return service.findByIdWithAll(id);
    }

    @PostMapping("add")
    public BorrowingWithAllDTO addBorrowing (@RequestBody BorrowingWithAllDTO borrowingWithAllDTO, @RequestHeader(name = "Authorization") String token){
        return service.addBorrowing(borrowingWithAllDTO);
    }

    @GetMapping("delete")
    public ResponseEntity deleteBorrowing (@RequestBody BorrowingWithAllDTO borrowingWithAllDTO, @RequestHeader(name = "Authorization") String token){
        return service.deleteBorrowing(borrowingWithAllDTO);
    }
    @PostMapping("extend/{id}")
    public ResponseEntity extendBorrowing(@PathVariable(value="id")Integer id, @RequestHeader(name = "Authorization") String token){
     return service.extendByIdWithAll(id);
    }

    @GetMapping("late")
    public Set<BorrowingLateDTO> findAllTooLateBorrowings( @RequestHeader(name = "Authorization") String token){
        return  service.findAllTooLateBorrowings();
    }

    @PostMapping("return/{id}")
    public ResponseEntity returnBorrowing (@PathVariable(value="id")Integer id, @RequestHeader(name = "Authorization") String token){
        UserDto employee = userService.findUserByUserName(jwtTokenProvider.getUsername(token.substring(7)));
        return service.returnBorrowing(id,employee);
    }

    @GetMapping("rentable/{id}")
    public Boolean rentableBook (@PathVariable(value="id")Integer bookId,@RequestHeader(name = "Authorization") String token) throws NotFoundException {
        UserDto user = userService.findUserByUserName(jwtTokenProvider.getUsername(token.substring(7)));
        return service.bookRentable(user.getId(),bookId);
    }
    @GetMapping("rent/{id}")
    public LocalDate findFirstReturnDateOfBook(@PathVariable(value = "id")Integer id, @RequestHeader(name = "Authorization") String token) {
        return service.findBorrowingsByBookId(id);
    }

    @GetMapping("unreturned/{id}")
    public Set<BorrowingWithAllDTO> findUnReturnedBorrowingsByUserId(@PathVariable(value = "id")Integer id, @RequestHeader(name = "Authorization") String token) throws NotFoundException {
        return service.findUnReturnedBorrowingsByUserId(id);
    }

    @GetMapping("returned/{id}")
    public Set<BorrowingWithAllDTO> findReturnedBorrowingsByUserId(@PathVariable(value = "id")Integer id, @RequestHeader(name = "Authorization") String token) throws NotFoundException {
        return service.findReturnedBorrowingsByUserId(id);
    }


}
