package com.example.rentalcarflowmanagerwebapp.service;

import com.example.rentalcarflowmanagerwebapp.repository.StatusRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class StatusService {

    StatusRepository statusRepository;
    CarService carService;

    public StatusService(StatusRepository statusRepository){
        this.statusRepository = statusRepository;
    }

    public int calculateDaysLeft(LocalDate endDate){

        long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), endDate);

        return (int)daysLeft;
    }
}
