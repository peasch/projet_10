package com.peasch.webbooks.web.proxies;

import com.peasch.webbooks.Beans.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name="MICROSERVICE-LIBRARY-MODEL", url = "localhost:8181")
public interface MicroserviceUserProxy {

/*    --------USERS------------------*/

    @GetMapping(value="/users")
    List<UserBean> getUsers(@RequestHeader(name = "Authorization") String token);

    @GetMapping(value="/user/{id}")
    UserBean getUserById(@PathVariable(value = "id") Integer id);

    @PostMapping("/api/auth/register")
    void addUser(@RequestBody UserBean userBean);

    @PostMapping("/api/auth/login")
    String login(@RequestBody UserBean user);

    @GetMapping("/user/username/{userName}")
    UserBean getUserByUserName(@PathVariable(value = "userName")String userName,@RequestHeader(name = "Authorization") String token);

    /*----------------------LIBRARY-------------------------*/

    @GetMapping("/libraries")
    List<LibraryBean> getLibraries(@RequestHeader(name = "Authorization") String token);

    @GetMapping("/libraries/{id}")
    LibraryBean getLibraryById(@PathVariable(value = "id")Integer id,@RequestHeader(name = "Authorization") String token);


    /*----------------------BOOKS-----------------------*/

    @GetMapping("/books")
    List<BookBean> getBooks(@RequestHeader(name = "Authorization") String token);

    @GetMapping("/authors")
    List<AuthorBean> getAuthors(@RequestHeader(name = "Authorization") String token);

    @GetMapping("/categories")
    List<CategoryBean> getCategories(@RequestHeader(name = "Authorization") String token);

    @PostMapping("/books/search")
    List<BookBean> findBooksByAuthor(@RequestBody Research research,@RequestHeader(name = "Authorization") String token);

    @GetMapping("/books/{id}")
    BookBean getBookById(@PathVariable(value = "id")Integer id,@RequestHeader(name = "Authorization") String token);

    @GetMapping("/copies/book/{id}")
    Map<Integer,Integer> getCopiesofBookInLibraries(@PathVariable(value="id")Integer id,@RequestHeader(name = "Authorization") String token);

//---------------------BORROWINGS-----------------------------

    @PostMapping("/borrowings/extend/{id}")
    BorrowingBean extendBorrowing(@PathVariable(value="id")Integer id,@RequestHeader(name = "Authorization") String token);

}
