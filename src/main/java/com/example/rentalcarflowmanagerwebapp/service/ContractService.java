package com.example.rentalcarflowmanagerwebapp.service;


import com.example.rentalcarflowmanagerwebapp.model.Contract;
import com.example.rentalcarflowmanagerwebapp.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ContractService {

    private ContractRepository contractRepository;

    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public ArrayList<Contract> GetAllContracts(){
        return contractRepository.getAllContract();
    }


    public ArrayList<Contract> GetAllContractsWithCars(){
        return contractRepository.getAllContractWithCars();
    }


    public boolean deleteContract(int contractID) {
        int totalLease = contractRepository.totalLeasesForContract(contractID);

        // checks if there exist leases under contract before deleting
        if(totalLease == 0){
            contractRepository.deleteContract(contractID);
            return true;
        }

        return false;
    }

    public boolean createContract(Contract contract) {
        return contractRepository.saveContract(contract);
    }





}
