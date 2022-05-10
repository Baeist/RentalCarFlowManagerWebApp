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
        boolean isLoggedIn = false;

        if(session.getAttribute("isLoggedIn") == null) {
            session.setAttribute("isLoggedIn", false);
            }
        else{
            isLoggedIn = (boolean)session.getAttribute("isLoggedIn");
        }

        if(isLoggedIn){
            return "redirect:/administrator/" + session.getAttribute("logInName"); // skal fixes til rigtig url efter hvilken type medarbejder vi har med at gøre
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

            return "redirect:/administrator/" + session.getAttribute("logInName"); // redirect to type of employee that logged in
        }


        return "index";
    }

    @GetMapping("/administrator/{logInName}")
    public String administrator(@PathVariable("logInName") String logInName, HttpSession session, Model model){

        // bør nok være i de fleste side kald, tjekker man ikke bare hopper ind gennem url uden log in
        if(!((boolean) session.getAttribute("isLoggedIn"))){
            return "index";
        }


        model.addAttribute("logInName", logInName);
        model.addAttribute("fullName", session.getAttribute("employeeFullName"));

        return "administrator";
    }

}
