package com.example.rentalcarflowmanagerwebapp.service;

import com.example.rentalcarflowmanagerwebapp.model.CarModel;
import com.example.rentalcarflowmanagerwebapp.repository.LeaseRepository;

import java.util.ArrayList;

import static org.springframework.http.HttpHeaders.FROM;

public class LeaseService {


    public static ArrayList<CarModel> seUdlejedeBiler(){

        return LeaseRepository.seUdlejedeBiler();


    }

    public static ArrayList<CarModel> seLedigeBiler(){
        return LeaseRepository.seLedigeBiler();
    }

}
