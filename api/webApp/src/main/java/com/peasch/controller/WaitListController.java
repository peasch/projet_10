package com.peasch.controller;

import com.peasch.controller.security.config.JwtTokenProvider;
import com.peasch.model.dto.User.UserDto;
import com.peasch.model.dto.WaitList.WaitListWithAllDto;
import com.peasch.service.BookService;
import com.peasch.service.UserService;
import com.peasch.service.WaitListService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/waitList")
public class WaitListController {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    WaitListService service;
    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;

    @GetMapping("/add/{id}")
    public ResponseEntity addUserToWaitList(@PathVariable(value = "id") Integer id, @RequestHeader(name = "Authorization") String token) throws NotFoundException {
        UserDto user = userService.findUserByUserName(jwtTokenProvider.getUsername(token.substring(7)));
        if (service.ExistingWaitList(id,user)){
            return new ResponseEntity("il existe déjà une demande de réservation pour cet abonné", HttpStatus.FORBIDDEN);
        }else{
            return new ResponseEntity(service.save(user, id),HttpStatus.OK);
        }
    }

    @GetMapping("/showList/{id}")
    public List<WaitListWithAllDto> getWaitListsOfBook(@PathVariable(value = "id") Integer id, @RequestHeader(name = "Authorization") String token) {
        return service.waitListByBookId(id);
    }

    @GetMapping("/user")
    public List<WaitListWithAllDto> getAllWLofUser( @RequestHeader(name = "Authorization") String token){
        UserDto user = userService.findUserByUserName(jwtTokenProvider.getUsername(token.substring(7)));
        return service.getAllWaitListofuser(user);
    }

    @GetMapping("/isWaitListable/{id}")
    public Boolean isWaitListable(@PathVariable(value = "id") Integer id, @RequestHeader(name = "Authorization") String token) {
        return service.isWaitListable(id);
    }


    @GetMapping("/exist/{id}")
    public Boolean existingWaitList(@PathVariable(value = "id") Integer id, @RequestHeader(name = "Authorization") String token){
        UserDto user = userService.findUserByUserName(jwtTokenProvider.getUsername(token.substring(7)));
        if (service.ExistingWaitList(id, user)) {
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/getWaitList/{id}")
    public WaitListWithAllDto getWaitListOfUserThisBook(@PathVariable(value = "id") Integer id, @RequestHeader(name = "Authorization") String token){
        UserDto user = userService.findUserByUserName(jwtTokenProvider.getUsername(token.substring(7)));
        return service.getWaitListOfUser(id,user);
    }

    @GetMapping("/delete/{id}")
    public void deleteWaitList(@PathVariable(value = "id") Integer id, @RequestHeader(name = "Authorization") String token) throws NotFoundException {
        service.deleteWaitlistDemand(id);
    }
    @GetMapping("/waitListed/{id}")
    public boolean isWaitListed(@PathVariable(value = "id") Integer id, @RequestHeader(name = "Authorization") String token){
        return service.isWaitListed(id);
    }

    @GetMapping("/checkWaitList/{id}")
    public WaitListWithAllDto checkWaitListOfBook(@PathVariable(value = "id") Integer id, @RequestHeader(name = "Authorization") String token)  {
       return service.availableBookofWaitLists(id);
    }
}
