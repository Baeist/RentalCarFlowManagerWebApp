package com.example.rentalcarflowmanagerwebapp.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Tobias
class ContractRepositoryTest {


    ContractRepository contractRepository = new ContractRepository();

    @Test
    void saveContract() {
    }

    @Test
    void getContract() {
    }

    @Test
    void getAllContract() {
    }

    @Test
    void deleteContract() {
    }

    @Test
    void totalLeasesForContract() {
        // 4 leases with contract ID 2 on test dataset

        int total = contractRepository.totalLeasesForContract(2);

        assertEquals(4, total);











    }
}