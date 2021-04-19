package com.peasch.webbooks.web.proxies;

import com.peasch.webbooks.Beans.*;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    /*---------------------- LIBRARY -------------------------*/

    /*@GetMapping("/libraries")
    List<LibraryBean> getLibraries(@RequestHeader(name = "Authorization") String token);

    @GetMapping("/libraries/{id}")
    LibraryBean getLibraryById(@PathVariable(value = "id")Integer id,@RequestHeader(name = "Authorization") String token);*/


    /*---------------------- BOOKS -----------------------*/

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

    @GetMapping("/copies/quantities/available/{id}")
    int getNumberOfCopiesAvailable(@PathVariable(value="id")Integer id, @RequestHeader(name = "Authorization") String token);

    @GetMapping("/copies/quantities/{id}")
    int getNumberOfCopies(@PathVariable(value="id")Integer id, @RequestHeader(name = "Authorization") String token);
//--------------------- BORROWINGS -----------------------------

    @PostMapping("/borrowings/extend/{id}")
    BorrowingBean extendBorrowing(@PathVariable(value="id")Integer id,@RequestHeader(name = "Authorization") String token);

    @GetMapping("/borrowings/rentable/{id}")
    Boolean rentableBook (@PathVariable(value="id")Integer bookId,@RequestHeader(name = "Authorization") String token);

    @GetMapping("/borrowings/rent/{id}")
    LocalDate findFirstReturnDateOfBook(@PathVariable(value = "id")Integer id, @RequestHeader(name = "Authorization") String token);

    @GetMapping("/borrowings/unreturned/{id}")
    Set<BorrowingBean> findUnReturnedBorrowingsByUserId(@PathVariable(value = "id")Integer id, @RequestHeader(name = "Authorization") String token);


    @GetMapping("/borrowings/returned/{id}")
    Set<BorrowingBean> findReturnedBorrowingsByUserId(@PathVariable(value = "id")Integer id, @RequestHeader(name = "Authorization") String token);

    //-------------------------- WaitList -------------------------------------

    @GetMapping("/waitList/add/{id}")
    void addUserToWaitList(@PathVariable(value = "id") Integer id, @RequestHeader(name = "Authorization") String token);

    @GetMapping("/waitList/showList/{id}")
    List<WaitListBean> getWaitListsOfBook(@PathVariable(value = "id") Integer id, @RequestHeader(name = "Authorization") String token);

    @GetMapping("/waitList/isWaitListable/{id}")
    Boolean isWaitListable(@PathVariable(value = "id") Integer id, @RequestHeader(name = "Authorization") String token);


    @GetMapping("/waitList/exist/{id}")
    Boolean existingWaitList(@PathVariable(value = "id") Integer id, @RequestHeader(name = "Authorization") String token);

    @GetMapping("/waitList/getWaitList/{id}")
    WaitListBean getWaitListOfUserThisBook(@PathVariable(value = "id") Integer id, @RequestHeader(name = "Authorization") String token);

    @GetMapping("/waitList/delete/{id}")
    void deleteWaitList(@PathVariable(value = "id") Integer id, @RequestHeader(name = "Authorization") String token);

    @GetMapping("/waitList/user/")
    List<WaitListBean> getAllWLofUser( @RequestHeader(name = "Authorization") String token);
}
