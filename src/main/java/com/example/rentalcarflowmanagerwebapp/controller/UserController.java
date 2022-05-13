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
    public String index(HttpSession session) {
        boolean isLoggedIn;

        if (session.getAttribute("isLoggedIn") == null) {
            session.setAttribute("isLoggedIn", false);
            isLoggedIn = false;
        } else {
            isLoggedIn = (boolean) session.getAttribute("isLoggedIn");
        }

        if (isLoggedIn) {
            if (session.getAttribute("employeeType").equals("admin")) {
                return "redirect:/administrator/" + session.getAttribute("logInName"); // mangler else if for resten af medarbejder typerne
            }
        }
        return "index";
    }

    @PostMapping("/")
    public String logIn(HttpSession session, @RequestParam("log_in_name") String logInName, @RequestParam("employee_password") String employeePassword) {

        UserModel user = userService.getUserFromLogInNameAndPassword(logInName, employeePassword);

        if (user != null && user.isUserActive()) {

            session.setAttribute("employeeFullName", user.getFirstName() + " " + user.getLastName());
            session.setAttribute("employeeType", user.getEmployeeType());
            session.setAttribute("logInName", logInName);
            session.setAttribute("isLoggedIn", true);
            session.setAttribute("password", employeePassword);
            session.setAttribute("isTypeDamage", false);
            session.setAttribute("isTypeBusiness", false);
            session.setAttribute("isTypeRegistering", false);
            session.setAttribute("isTypeAdmin", false);

            if (user.getEmployeeType().equals("admin"))
                session.setAttribute("isTypeAdmin", true);
            if (user.getEmployeeType().equals("dataregistrering"))
                session.setAttribute("isTypeRegistering", true);
            if (user.getEmployeeType().equals("forretningsudvikler"))
                session.setAttribute("isTypeBusiness", true);
            if (user.getEmployeeType().equals("skade og -mangler"))
                session.setAttribute("isTypeDamage", true);

            return "redirect:/administrator/" + session.getAttribute("logInName"); // redirect to type of employee that logged in, mangler for alle typer medarbejdere
        }

        return "index";
    }

    @GetMapping("/administrator/{logInName}")
    public String administrator(@PathVariable("logInName") String logInName, HttpSession session, Model model) {

        // TODO bør nok være i de fleste side kald, tjekker man ikke bare hopper ind gennem url uden log in
        if (session.getAttribute("isLoggedIn") == null || !((boolean) session.getAttribute("isLoggedIn"))) {
            return "index";
        }

        model.addAttribute("userToBeDeleted", userService.getUserFromLogInName((String) session.getAttribute("deleteUser")));

        model.addAttribute("logInName", logInName);
        model.addAttribute("fullName", session.getAttribute("employeeFullName"));
        model.addAttribute("user", userService.getAllActiveEmployees());
        return "administrator";
    }

    @GetMapping("logout")
    public String logOut(HttpSession session) {

        session.invalidate();

        return "index";
    }

    @GetMapping("change_password")
    public String changePassword(HttpSession session, Model model) {

        if (session.getAttribute("isLoggedIn") == null || !((boolean) session.getAttribute("isLoggedIn"))) {
            return "index";
        }

        model.addAttribute("logInName", session.getAttribute("logInName"));

        return "/setpassword";
    }

    @PostMapping("/setpassword")
    public String passwordChanged(HttpSession session, @RequestParam("old_password") String oldPassword,
                                  @RequestParam("first_new_password") String firstNewPassword,
                                  @RequestParam("second_new_password") String secondNewPassword) {

        if (session.getAttribute("isLoggedIn") == null || !((boolean) session.getAttribute("isLoggedIn"))) {
            return "index";
        }

        if (oldPassword.equals(session.getAttribute("password")) && firstNewPassword.equals(secondNewPassword)) {

            userService.updatePassword((String) session.getAttribute("logInName"), firstNewPassword);

            session.setAttribute("password", firstNewPassword);

            return "redirect:/administrator/" + session.getAttribute("logInName");
        }

        return "/setpassword";
    }

    @GetMapping("/update_user/{logInName}")
    public String updateUser(@PathVariable("logInName") String logInName, HttpSession session, Model model) {

        model.addAttribute("user", userService.getUserFromLogInName(logInName));

        return "/update_user_information";
    }

    @PostMapping("/update_user_information")
    public String changeUserInfo(HttpSession session, @RequestParam("user_id") int employeeID, @RequestParam("first_name") String firstName, @RequestParam("last_name") String lastName,
                                 @RequestParam("log_in_name") String logInName, @RequestParam("user_type") String employeeType){

        userService.updateUserInfo(employeeID, firstName, lastName, logInName, employeeType);

        return "redirect:/administrator/" + session.getAttribute("logInName");
    }

    @PostMapping("/delete_user/{logInName}")
    public String deleteUser(@PathVariable("logInName") String logInName, HttpSession session, @RequestParam("delete") String delete) {

        session.setAttribute("delete", delete);
        session.setAttribute("deleteUser", logInName);

        return "redirect:/administrator/" + session.getAttribute("logInName");
    }

    @GetMapping("/final_delete_user/{logInName}")
    public String finalDeleteUser(@PathVariable("logInName") String logInName, HttpSession session) {

        userService.updateIsUserActiveFalse(logInName);

        session.removeAttribute("delete");
        session.removeAttribute("deleteUser");

        return "redirect:/administrator/" + session.getAttribute("logInName");
    }

    @GetMapping("/regret_delete_user/{logInName}")
    public String regretDeleteUser(@PathVariable("logInName") String logInName, HttpSession session) {

        session.removeAttribute("delete");
        session.removeAttribute("deleteUser");

        return "redirect:/administrator/" + session.getAttribute("logInName");
    }
    @GetMapping("/create_user")
    public String createUser(HttpSession session){

        session.setAttribute("create", true);

        return "redirect:/administrator/" + session.getAttribute("logInName");
    }
    @PostMapping("/user_created")
    public String confirmUserCreation(HttpSession session, @RequestParam("first_name") String firstName, @RequestParam("last_name") String lastName,
                                      @RequestParam("log_in_name") String logInName, @RequestParam("user_type") String employeeType,
                                      @RequestParam("user_password") String employeePassword){

        session.removeAttribute("create");

        userService.createNewUser(firstName, lastName, logInName, employeeType, employeePassword);

        return "redirect:/administrator/" + session.getAttribute("logInName");
    }

    @GetMapping("/cancel_create_user")
    public String cancelCreate(HttpSession session){
        session.removeAttribute("create");

        return "redirect:/administrator/" + session.getAttribute("logInName");
    }
}


