package com.peasch.controller;

import com.peasch.controller.security.config.JwtTokenProvider;
import com.peasch.model.dto.Borrowings.BorrowingDto;
import com.peasch.model.dto.Borrowings.BorrowingLateDTO;
import com.peasch.model.dto.Borrowings.BorrowingWithAllDTO;
import com.peasch.model.dto.User.UserDto;
import com.peasch.model.entities.Borrowing;
import com.peasch.service.BorrowingService;
import com.peasch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public BorrowingWithAllDTO getBorrowingById(@PathVariable(value = "id")Integer id, @RequestHeader(name = "Authorization") String token){
        return service.findByIdWithAll(id);
    }

    @PostMapping("add")
    public void addBorrowing (@RequestBody BorrowingWithAllDTO borrowingWithAllDTO, @RequestHeader(name = "Authorization") String token){
        service.addBorrowing(borrowingWithAllDTO.getUser(),borrowingWithAllDTO.getCopy());
    }

    @PostMapping("extend/{id}")
    public BorrowingWithAllDTO extendBorrowing(@PathVariable(value="id")Integer id, @RequestHeader(name = "Authorization") String token){
     return service.extendByIdWithAll(id);
    }

    @GetMapping("late")
    public Set<BorrowingLateDTO> findAllTooLateBorrowings( @RequestHeader(name = "Authorization") String token){
        return  service.findAllTooLateBorrowings();
    }

    @PostMapping("return/{id}")
    public BorrowingWithAllDTO returnBorrowing (@PathVariable(value="id")Integer id, @RequestHeader(name = "Authorization") String token){

        UserDto employee = userService.findUserByUserName(jwtTokenProvider.getUsername(token.substring(7)));
        return service.returnBorrowing(id,employee);
    }
}
