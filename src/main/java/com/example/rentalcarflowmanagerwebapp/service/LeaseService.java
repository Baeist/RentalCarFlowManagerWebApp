package com.example.rentalcarflowmanagerwebapp.service;


import com.example.rentalcarflowmanagerwebapp.model.Lease;
import com.example.rentalcarflowmanagerwebapp.repository.LeaseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
}
