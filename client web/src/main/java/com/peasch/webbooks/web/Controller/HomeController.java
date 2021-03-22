package com.peasch.webbooks.web.Controller;

import com.peasch.webbooks.web.proxies.MicroserviceUserProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;


@Controller
public class HomeController {

    @Autowired
    MicroserviceUserProxy mUserProxy;

    @GetMapping("/home")
    public String accueil (ModelMap model, HttpSession session){
        model.addAttribute("localDate", LocalDate.now());
        return "index";
    }
}
