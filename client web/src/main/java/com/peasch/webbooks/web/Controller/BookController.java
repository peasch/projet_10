package com.peasch.webbooks.web.Controller;

import com.peasch.webbooks.Beans.*;
import com.peasch.webbooks.web.proxies.MicroserviceUserProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {
    @Autowired
    MicroserviceUserProxy mUserProxy;

    @GetMapping("/books")
    public String books (ModelMap model,HttpSession session ){
        Research research = new Research();
        String token = (String) session.getAttribute("token");
        List<AuthorBean> authors = mUserProxy.getAuthors(token);
        List<CategoryBean> categories = mUserProxy.getCategories(token);
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("authors",authors);
        model.addAttribute("categories",categories);
        model.addAttribute("research",research);
        return "bookSearch";
    }

    @PostMapping("/books")
    public String bookSearch(@ModelAttribute ("research") Research research, ModelMap model,HttpSession session){
        String token = (String) session.getAttribute("token");
        List<BookBean> books = mUserProxy.findBooksByAuthor(research, token);
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("books",books);
        model.addAttribute("research",research);
        return "booksByAuthor";
    }

    @GetMapping("/collection")
    public String bookCollection(ModelMap model,HttpSession session) {
        String token = (String) session.getAttribute("token");

        List<BookBean> books = mUserProxy.getBooks(token);
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("books",books);
        for (BookBean book:books){
            System.out.println(book.getCover());
        }
        return "bookList";

    }

    @GetMapping("/book/describe/{id}")
    public String bookDescribe(@PathVariable(name="id")Integer id, ModelMap model, HttpSession session){
        String token = (String) session.getAttribute("token");
        List<LibraryBean> libraries = mUserProxy.getLibraries(token);
        BookBean book= mUserProxy.getBookById(id,token);
        Map<Integer,Integer> bookMap = mUserProxy.getCopiesofBookInLibraries(id,token);
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("book",book);
        model.addAttribute("bookMap",bookMap);
        model.addAttribute("libraries",libraries);

        return "bookDescribe";
    }


}
