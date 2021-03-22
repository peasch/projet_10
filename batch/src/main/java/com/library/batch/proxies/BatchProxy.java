package com.library.batch.proxies;

import com.library.batch.Beans.BorrowingBean;
import com.library.batch.Beans.UserBean;
import com.library.batch.Beans.UserConnected;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Set;

@FeignClient(name="MICROSERVICE-LIBRARY-MODEL", url = "localhost:8181")
public interface BatchProxy {

    @PostMapping("/api/auth/login")
    String login(@RequestBody UserBean user);

    @GetMapping(value="/users")
    List<UserBean> getUsers();

    @GetMapping("/borrowings/late")
    Set<BorrowingBean> findAllTooLateBorrowings(@RequestHeader(name = "Authorization") String token);


}
