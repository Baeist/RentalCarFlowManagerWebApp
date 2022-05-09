package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @GetMapping("/")
    public String index(HttpSession session){
        boolean isLoggedIn = false;

        if(session.getAttribute("isLoggedIn") == null) {
            session.setAttribute("isLoggedIn", false);
            }
        else{
            isLoggedIn = (boolean)session.getAttribute("isLoggedIn");
        }

        if(isLoggedIn){
            return "redirect:/administrator"; // skal fixes til rigtig url
        }
        else
        return "index";
    }

    @PostMapping("/")
    public String logIn(HttpSession session){

        // mangler check for log in

        session.setAttribute("isLoggedIn", true); // mangler sætte session attributes for user



        return "redirect:/administrator";
    }


}
