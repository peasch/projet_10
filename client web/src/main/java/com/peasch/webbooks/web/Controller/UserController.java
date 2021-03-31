package com.peasch.webbooks.web.Controller;

import com.peasch.webbooks.Beans.BorrowingBean;
import com.peasch.webbooks.Beans.UserBean;
import com.peasch.webbooks.Beans.WaitListBean;
import com.peasch.webbooks.web.proxies.MicroserviceUserProxy;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Controller
public class UserController {

    @Autowired
    private MicroserviceUserProxy mUserProxy;

    private String secretKey1 = "spongeBob";

    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    private SigningKeyResolver signingKeyResolver;

    @GetMapping("/users")
    public String users(ModelMap model, HttpSession session) {
        String token = (String) session.getAttribute("token");
        List<UserBean> users = mUserProxy.getUsers(token);
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/register")
    public String register(ModelMap model) {
        UserBean userBean = new UserBean();
        model.addAttribute("userBean", userBean);

        return "register";
    }

    @PostMapping("/register")
    public String registered(@Valid UserBean userBean, ModelMap model) {
        model.addAttribute("registered", true);
        mUserProxy.addUser(userBean);
        return "index";
    }

    @GetMapping("/login")
    public String login(ModelMap model) {
        UserBean user = new UserBean();
        model.addAttribute("userBean", user);
        return "login";
    }

    @PostMapping("/login")
    public String LoggedIn(@ModelAttribute("userBean") UserBean user, ModelMap model, HttpSession session) {
        String token = mUserProxy.login(user);
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey1).parseClaimsJws(token);
        String userName = claims.getBody().getSubject();
        token = "bearer " + token;
        UserBean userSession = mUserProxy.getUserByUserName(userName, token);
        session.setAttribute("user", userSession);
        session.setAttribute("token", token);
        model.addAttribute("borrowings", userSession.getBorrowings());
        model.addAttribute("localDate", LocalDate.now());
        return "index";
    }

    @GetMapping("/profile")
    public String profileDisplay(ModelMap model, HttpSession session) {
        UserBean userBean = (UserBean) session.getAttribute("user");
        String token = (String) session.getAttribute("token");
        UserBean userSession = mUserProxy.getUserByUserName(userBean.getUserName(), token);
        List<WaitListBean> waitlists = mUserProxy.getAllWLofUser(token);
        int userId = userSession.getId();
        Set<BorrowingBean> unreturned = mUserProxy.findUnReturnedBorrowingsByUserId(userId, token);
        Set<BorrowingBean> returned = mUserProxy.findReturnedBorrowingsByUserId(userId, token);
        model.addAttribute("user", userSession);
        model.addAttribute("borrowings", userSession.getBorrowings());
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("returned", returned);
        model.addAttribute("unreturned", unreturned);
        model.addAttribute("waitLists", waitlists);
        return "profile";
    }

    @GetMapping("/profile/deleteWaitList/{id}")
    public String deleteWLOnProfileDisplay(@PathVariable(name = "id") Integer id, ModelMap model, HttpSession session) {
        UserBean userBean = (UserBean) session.getAttribute("user");
        String token = (String) session.getAttribute("token");
        UserBean userSession = mUserProxy.getUserByUserName(userBean.getUserName(), token);
        int userId = userSession.getId();
        mUserProxy.deleteWaitList(id, token);
        List<WaitListBean> waitlists = mUserProxy.getAllWLofUser(token);
        Set<BorrowingBean> unreturned = mUserProxy.findUnReturnedBorrowingsByUserId(userId, token);
        Set<BorrowingBean> returned = mUserProxy.findReturnedBorrowingsByUserId(userId, token);
        model.addAttribute("user", userSession);
        model.addAttribute("borrowings", userSession.getBorrowings());
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("returned", returned);
        model.addAttribute("unreturned", unreturned);
        model.addAttribute("waitLists", waitlists);
        return "profile";
    }


    @GetMapping("/logout")
    public String logout(ModelMap model, HttpSession session) {
        model.addAttribute("localDate", LocalDate.now());
        session.invalidate();

        return "index";
    }
}
