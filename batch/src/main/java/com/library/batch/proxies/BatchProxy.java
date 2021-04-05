package com.library.batch.proxies;

import com.library.batch.Beans.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@FeignClient(name="MICROSERVICE-LIBRARY-MODEL", url = "localhost:8181")
public interface BatchProxy {

    @PostMapping("/api/auth/login")
    String login(@RequestBody UserBean user);

    @GetMapping(value="/users")
    List<UserBean> getUsers();

//====================== Borrowings ===============================

    @GetMapping("/borrowings/late")
    Set<BorrowingBean> findAllTooLateBorrowings(@RequestHeader(name = "Authorization") String token);

//====================== Books ====================================

    @GetMapping("/books/availables")
    List<BookBean> checkAvailableBooks(@RequestHeader(name = "Authorization") String token);

    @GetMapping("/books/availablesAndWaitList")
   List<BookBean> checkAvailableBooksAndWaitList( @RequestHeader(name = "Authorization") String token);

    @GetMapping("/waitList/checkWaitList/{id}")
    WaitListBean checkWaitListOfBook(@PathVariable(value = "id") Integer id, @RequestHeader(name = "Authorization") String token);

//====================== WaitList =================================
    @GetMapping("/waitList/waitListed/{id}")
     boolean isWaitListed(@PathVariable(value = "id") Integer id, @RequestHeader(name = "Authorization") String token);


}
