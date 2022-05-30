package com.example.rentalcarflowmanagerwebapp.repository;

import com.example.rentalcarflowmanagerwebapp.model.Lease;
import com.example.rentalcarflowmanagerwebapp.utility.ConnectionManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// Tobias
class LeaseRepositoryTest {

    LeaseRepository leaseRepository = new LeaseRepository();

    @Test
    void getAllLease() {
        final int TOTAL_LENGTH_LEASES_TEST_DATA = 8;

        ArrayList<Lease> allData = leaseRepository.getAllLease();

        for (int i = 0; i < allData.size(); i++) {
            assertNotNull(allData.get(i));
            assertNotNull(allData.get(i).getStartDate());
        }

        assertEquals(TOTAL_LENGTH_LEASES_TEST_DATA, allData.size());
    }

    @Test
    void saveLease() {
        LocalDate localDate = LocalDate.of(2022, 5, 17);

        Lease lease = new Lease(5000, localDate, 1, 120, 12);
        Lease leaseNoContract = new Lease(0, localDate, -5, 120, -5);
        Lease leaseNoExistingCarID = new Lease(0, localDate, 1, 120, -5);

        // should be able to record a lease in database.
        // NOTE: Running the test multiple times will result in failure. check that leaseID is uniqe on lease.
        assertTrue(leaseRepository.saveLease(lease));
        assertFalse(leaseRepository.saveLease(leaseNoContract));
        assertFalse(leaseRepository.saveLease(leaseNoExistingCarID));
    }

    @Test
    void updateLease() {
        LocalDate newDate = LocalDate.of(9999, 9, 9);

        Lease oldLease1 = leaseRepository.getLease(2);

        Lease updatedLease = new Lease(2, newDate, 2, 999, 14);

        // checks if methods returns true
        assertTrue(leaseRepository.updateLease(updatedLease));

        // checks if both objects have same lease ID
        assertEquals(oldLease1.getLeaseID(), updatedLease.getLeaseID());

        //checks that leasePeriod has changed
        assertNotEquals(oldLease1.getLeasePeriodDays(), updatedLease.getLeasePeriodDays());
    }

    @Test
    void getLease() {
        Lease lease = leaseRepository.getLease(2);

        assertNotNull(lease);

        assertNotNull(lease.getStartDate());
    }


    @Test
    void deleteLease() {
        ArrayList<Lease> oldList = leaseRepository.getAllLease();

        leaseRepository.deleteLease(9);
        ArrayList<Lease> newList= leaseRepository.getAllLease();

        // Old list should be bigger than new list
        assertTrue(oldList.size() > newList.size());
    }




    @Test
    void leaseExist() {
        assertTrue(leaseRepository.leaseExist(1));
        assertFalse(leaseRepository.leaseExist(1000));
    }
}