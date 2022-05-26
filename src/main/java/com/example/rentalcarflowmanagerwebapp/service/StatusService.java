package com.example.rentalcarflowmanagerwebapp.service;

import com.example.rentalcarflowmanagerwebapp.model.Status;
import com.example.rentalcarflowmanagerwebapp.repository.StatusRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

@Service
public class StatusService {

    StatusRepository statusRepository;
    CarService carService;

    public StatusService(StatusRepository statusRepository){
        this.statusRepository = statusRepository;
    }

    public ArrayList<Status> getStatusAndDaysLeft(ArrayList<Integer> allCarID){

        ArrayList<Status> descDaysList = new ArrayList<>();
        Status smallStatus;

        for(int i = 0; i < allCarID.size(); i++){

            Status status = statusRepository.getStatusFromCarID(allCarID.get(i));

            if(status != null){
            status.setDaysLeft(calculateDaysLeft(status.getEndDate()));

            smallStatus = new Status(allCarID.get(i), status.getStatusDescription(), status.getDaysLeft());

            descDaysList.add(smallStatus);
            }
            else{
                smallStatus = new Status(allCarID.get(i), "ingen", -1);
                descDaysList.add(smallStatus);
            }
        }

        return descDaysList;
    }

    public int calculateDaysLeft(LocalDate endDate){

        if(endDate != null) {
            long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), endDate);
            return (int)daysLeft;
        }

        return 0;
    }

    public void newStatus(int carID, String statusDescription, String startDate, int daysLeft){

        String[] date = startDate.split("-", 3);
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[2]);

        LocalDate startingDate = LocalDate.of(year, month, day);

        LocalDate endDate = startingDate.plusDays(daysLeft);

        statusRepository.newStatus(carID, statusDescription, startingDate, endDate);
    }
}
