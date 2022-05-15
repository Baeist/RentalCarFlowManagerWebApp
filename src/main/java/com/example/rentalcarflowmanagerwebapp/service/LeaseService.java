package com.example.rentalcarflowmanagerwebapp.service;


import com.example.rentalcarflowmanagerwebapp.model.Lease;
import com.example.rentalcarflowmanagerwebapp.repository.LeaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

@Service
public class LeaseService {



    private LeaseRepository leaseRepository;

    public LeaseService(LeaseRepository leaseRepository){
        this.leaseRepository = leaseRepository;
    }


    public ArrayList<Lease> getAllLeases() {
        return leaseRepository.getAllLease();
    }

    public void deleteLease(int leaseID) {
        leaseRepository.deleteLease(leaseID);
    }


    public void saveLease(Lease lease) {
        int leaseID = lease.getLeaseID();

        if (leaseRepository.leaseExist(leaseID)){
            leaseRepository.updateLease(lease);
        } else {
            leaseRepository.saveLease(lease);
        }
    }

    public LocalDate convertStringtoLocalDate(String startDate) {
        // String format = 1999-22-03
        Scanner scanner = new Scanner(startDate);
        scanner.useDelimiter("-");

        int year = scanner.nextInt();
        int month = scanner.nextInt();
        int day = scanner.nextInt();

        LocalDate localDate = LocalDate.of(year, month, day);

        return localDate;
    }
}
