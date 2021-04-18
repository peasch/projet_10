package com.peasch.webbooks.web.Controller;

import com.peasch.webbooks.Beans.*;
import com.peasch.webbooks.web.proxies.MicroserviceUserProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {
    @Autowired
    MicroserviceUserProxy mUserProxy;

    @GetMapping("/books")
    public String books(ModelMap model, HttpSession session) {
        Research research = new Research();
        String token = (String) session.getAttribute("token");
        List<AuthorBean> authors = mUserProxy.getAuthors(token);
        List<CategoryBean> categories = mUserProxy.getCategories(token);
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("authors", authors);
        model.addAttribute("categories", categories);
        model.addAttribute("research", research);
        return "bookSearch";
    }

    @PostMapping("/books")
    public String bookSearch(@ModelAttribute("research") Research research, ModelMap model, HttpSession session) {
        String token = (String) session.getAttribute("token");
        List<BookBean> books = mUserProxy.findBooksByAuthor(research, token);
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("books", books);
        model.addAttribute("research", research);
        return "booksByAuthor";
    }

    @GetMapping("/collection")
    public String bookCollection(ModelMap model, HttpSession session) {
        String token = (String) session.getAttribute("token");

        List<BookBean> books = mUserProxy.getBooks(token);
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("books", books);
        return "bookList";

    }


    @GetMapping("/book/describe/{id}")
    public String bookDescribe(@PathVariable(name = "id") Integer id, ModelMap model, HttpSession session) {
        String token = (String) session.getAttribute("token");
        BookBean book = mUserProxy.getBookById(id, token);
        Boolean alreadyDemanded = mUserProxy.existingWaitList(id, token);

        Map<Integer, Integer> bookMap = mUserProxy.getCopiesofBookInLibraries(id, token);
        int numberOfCopiesAvailable = mUserProxy.getNumberOfCopiesAvailable(id, token);
        int numberOfCopiesTotal = mUserProxy.getNumberOfCopies(id, token);

        if (numberOfCopiesAvailable == 0) {
            LocalDate firstDateOfReturn = mUserProxy.findFirstReturnDateOfBook(id, token);
            model.addAttribute("firstDate", firstDateOfReturn);
        }
        if (alreadyDemanded) {
            WaitListBean WL = mUserProxy.getWaitListOfUserThisBook(id, token);
            model.addAttribute("WL", WL);
        }
        Boolean rentable = mUserProxy.rentableBook(id, token);
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("book", book);
        model.addAttribute("bookMap", bookMap);

        model.addAttribute("numberOfCopiesAvailable", numberOfCopiesAvailable);
        model.addAttribute("numberOfCopies", numberOfCopiesTotal);
        model.addAttribute("rentable", rentable);
        model.addAttribute("alreadydemanded", alreadyDemanded);

        return "bookDescribe";
    }

    @GetMapping("/book/waitList/add/{id}")
    public String addWaitListDemand(@PathVariable(name = "id") Integer id, ModelMap model, HttpSession session) {
        String token = (String) session.getAttribute("token");
        mUserProxy.addUserToWaitList(id, token);
        Boolean rentable = mUserProxy.rentableBook(id, token);
        Boolean alreadyDemanded = mUserProxy.existingWaitList(id, token);
        Boolean waitListable = mUserProxy.isWaitListable(id, token);

        BookBean book = mUserProxy.getBookById(id, token);
        Map<Integer, Integer> bookMap = mUserProxy.getCopiesofBookInLibraries(id, token);
        int numberOfCopiesAvailable = mUserProxy.getNumberOfCopiesAvailable(id, token);
        int numberOfCopiesTotal = mUserProxy.getNumberOfCopies(id, token);
        if (numberOfCopiesAvailable == 0) {
            LocalDate firstDateOfReturn = mUserProxy.findFirstReturnDateOfBook(id, token);
            model.addAttribute("firstDate", firstDateOfReturn);
        }

        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("book", book);
        model.addAttribute("bookMap", bookMap);

        model.addAttribute("numberOfCopiesAvailable", numberOfCopiesAvailable);
        model.addAttribute("numberOfCopies", numberOfCopiesTotal);
        model.addAttribute("rentable", rentable);
        model.addAttribute("alreadydemanded", alreadyDemanded);
        model.addAttribute("waitListable", waitListable);
        return "bookDescribe";
    }


    @GetMapping("/book/waitList/delete/{id}")
    public String deleteWaitListDemand(@PathVariable(name = "id") Integer id, ModelMap model, HttpSession session) {
        String token = (String) session.getAttribute("token");
        Boolean rentable = mUserProxy.rentableBook(id, token);
        Boolean alreadyDemanded = mUserProxy.existingWaitList(id, token);
        Boolean waitListable = mUserProxy.isWaitListable(id, token);

        BookBean book = mUserProxy.getBookById(id, token);
        Map<Integer, Integer> bookMap = mUserProxy.getCopiesofBookInLibraries(id, token);
        int numberOfCopiesAvailable = mUserProxy.getNumberOfCopiesAvailable(id, token);
        int numberOfCopiesTotal = mUserProxy.getNumberOfCopies(id, token);
        if (numberOfCopiesAvailable == 0) {
            LocalDate firstDateOfReturn = mUserProxy.findFirstReturnDateOfBook(id, token);
            model.addAttribute("firstDate", firstDateOfReturn);
        }
        WaitListBean WL = mUserProxy.getWaitListOfUserThisBook(id, token);
        mUserProxy.deleteWaitList(WL.getId(), token);
        alreadyDemanded= mUserProxy.existingWaitList(id, token);
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("book", book);
        model.addAttribute("bookMap", bookMap);

        model.addAttribute("numberOfCopiesAvailable", numberOfCopiesAvailable);
        model.addAttribute("numberOfCopies", numberOfCopiesTotal);
        model.addAttribute("rentable", rentable);
        model.addAttribute("alreadydemanded", alreadyDemanded);
        model.addAttribute("waitListable", waitListable);

        return "bookDescribe";
    }

}
