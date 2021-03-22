package com.peasch.webbooks.web.Controller;

import com.peasch.webbooks.Beans.UserBean;
import com.peasch.webbooks.web.proxies.MicroserviceUserProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@Controller
public class BorrowingController {

    @Autowired
    MicroserviceUserProxy mUserProxy;

    @GetMapping("/borrowings/extend/{id}")
    public String prolongation(@PathVariable(name="id")Integer id, ModelMap model, HttpSession session){
        model.addAttribute("localDate", LocalDate.now());
        String token =(String) session.getAttribute("token");
        mUserProxy.extendBorrowing(id, token);

        return "index";
    }
}
