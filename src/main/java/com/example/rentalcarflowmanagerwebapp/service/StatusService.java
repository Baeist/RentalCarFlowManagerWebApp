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

            smallStatus = new Status(status.getStatusID(), allCarID.get(i), status.getStatusDescription(), status.getDaysLeft());

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

        LocalDate startingDate = fromStringToDate(startDate);

        LocalDate endDate = startingDate.plusDays(daysLeft);

        statusRepository.newStatus(carID, statusDescription, startingDate, endDate);
    }

    public Status getStatusFromCarID(int carID){
       return statusRepository.getStatusFromCarID(carID);
    }
    public void editStatus(int statusID, int carID, String statusDescription, String startDate, int daysLeft){

        LocalDate startingDate = fromStringToDate(startDate);

        LocalDate endDate = startingDate.plusDays(daysLeft);

        statusRepository.editStatus(statusID, carID, statusDescription, startingDate, endDate);
    }
    public LocalDate fromStringToDate(String input){

        String[] date = input.split("-", 3);
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[2]);

        LocalDate returnDate = LocalDate.of(year, month, day);

        return returnDate;
    }
}
