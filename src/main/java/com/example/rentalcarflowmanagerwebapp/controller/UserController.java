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

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(HttpSession session){
        boolean isLoggedIn = false;
        System.out.println("hej");
        if(session.getAttribute("isLoggedIn") == null) {
            session.setAttribute("isLoggedIn", false);
            }
        else{
            isLoggedIn = (boolean)session.getAttribute("isLoggedIn");
        }

        if(isLoggedIn){
            return "redirect:/administrator"; // skal fixes til rigtig url
        }
        return "index";
    }

    @PostMapping("/")
    public String logIn(HttpSession session, @RequestParam("log_in_name") String logInName, @RequestParam("employee_password") String employeePassword){

        UserModel user = userService.getUserFromLogInNameAndPassword(logInName, employeePassword);


        // mangler check for log in og set session emplyee type

        session.setAttribute("logInName", logInName);
        session.setAttribute("isLoggedIn", true);



        return "redirect:/administrator"; // redirect to type of employee that logged in
    }

    @GetMapping("/administrator")
    public String administrator(){

        return "administrator";
    }

}
