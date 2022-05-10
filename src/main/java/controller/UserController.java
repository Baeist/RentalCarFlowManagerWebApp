package controller;

import model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    UserService US;

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
    public String logIn(HttpSession session, @RequestParam("log_in_name") String logInName, @RequestParam("employee_password") String employeePassword){

        UserModel user = US.getUserFromLogInNameAndPassword(logInName, employeePassword);

        // mangler check for log in og set session emplyee type

        session.setAttribute("logInName", logInName);
        session.setAttribute("isLoggedIn", true);



        return "redirect:/administrator"; // redirect to type of employee that logged in
    }


}
