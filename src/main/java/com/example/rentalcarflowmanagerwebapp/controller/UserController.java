package com.example.rentalcarflowmanagerwebapp.controller;

import com.example.rentalcarflowmanagerwebapp.model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.rentalcarflowmanagerwebapp.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(HttpSession session){
        boolean isLoggedIn;

        if(session.getAttribute("isLoggedIn") == null) {
            session.setAttribute("isLoggedIn", false);
            isLoggedIn = false;
            }
        else{
            isLoggedIn = (boolean)session.getAttribute("isLoggedIn");
        }

        if(isLoggedIn) {
            if (session.getAttribute("employeeType").equals("admin")) {
                return "redirect:/administrator/" + session.getAttribute("logInName"); // mangler else if for resten af medarbejder typerne
            }
        }
        return "index";
    }

    @PostMapping("/")
    public String logIn(HttpSession session, @RequestParam("log_in_name") String logInName, @RequestParam("employee_password") String employeePassword){

        UserModel user = userService.getUserFromLogInNameAndPassword(logInName, employeePassword);

        if(user != null){

            session.setAttribute("employeeFullName", user.getFirstName() + " " + user.getLastName());
            session.setAttribute("employeeType", user.getEmployeeType());
            session.setAttribute("logInName", logInName);
            session.setAttribute("isLoggedIn", true);
            session.setAttribute("password", employeePassword);

            return "redirect:/administrator/" + session.getAttribute("logInName"); // redirect to type of employee that logged in, mangler for alle typer medarbejdere
        }

        return "index";
    }

    @GetMapping("/administrator/{logInName}")
    public String administrator(@PathVariable("logInName") String logInName, HttpSession session, Model model){

        // bør nok være i de fleste side kald, tjekker man ikke bare hopper ind gennem url uden log in
        if(session.getAttribute("isLoggedIn") == null || !((boolean) session.getAttribute("isLoggedIn"))){
            return "index";
        }

        model.addAttribute("logInName", logInName);
        model.addAttribute("fullName", session.getAttribute("employeeFullName"));
        model.addAttribute("user", userService.getAllEmployees());
        return "administrator";
    }

    @GetMapping("logout")
    public String logOut(HttpSession session){

        session.invalidate();

        return "index";
    }

    @GetMapping("change_password")
    public String changePassword(HttpSession session, Model model){

        if(session.getAttribute("isLoggedIn") == null || !((boolean) session.getAttribute("isLoggedIn"))){
            return "index";
        }

        model.addAttribute("logInName", session.getAttribute("logInName"));

        return "/setpassword";
    }

    @PostMapping("/setpassword")
    public String passwordChanged(HttpSession session, @RequestParam("old_password") String oldPassword,
                                  @RequestParam("first_new_password") String firstNewPassword,
                                  @RequestParam("second_new_password") String secondNewPassword){

        if(session.getAttribute("isLoggedIn") == null || !((boolean) session.getAttribute("isLoggedIn"))){
            return "index";
        }

        if(oldPassword.equals(session.getAttribute("password")) && firstNewPassword.equals(secondNewPassword)){

            userService.updatePassword((String)session.getAttribute("logInName"), firstNewPassword);

            session.setAttribute("password", firstNewPassword);

            return "redirect:/administrator/" + session.getAttribute("logInName");
        }

        return "/setpassword";
    }
}
