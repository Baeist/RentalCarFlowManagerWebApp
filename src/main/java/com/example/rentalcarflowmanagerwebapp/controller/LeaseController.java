package com.example.rentalcarflowmanagerwebapp.controller;

import com.example.rentalcarflowmanagerwebapp.model.CarModel;
import com.example.rentalcarflowmanagerwebapp.service.LeaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
public class LeaseController {


@RequestMapping(value="/bilstatistik", method= RequestMethod.GET)
public String bilStatistik(Model model){
  ArrayList<CarModel> biler = LeaseService.testMetodetilBilID();
  model.addAttribute("biler", biler);
  return "/bilstatistik";
}


}
