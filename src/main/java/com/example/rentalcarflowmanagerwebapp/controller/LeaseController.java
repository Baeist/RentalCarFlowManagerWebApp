package com.example.rentalcarflowmanagerwebapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LeaseController {

    @GetMapping("/dashboard/lease")
    public String dashboardLease(){

        return "lease_dashboard";
    }



}
