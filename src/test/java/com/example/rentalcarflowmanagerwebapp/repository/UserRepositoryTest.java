package com.example.rentalcarflowmanagerwebapp.repository;

import com.example.rentalcarflowmanagerwebapp.model.User;
import com.example.rentalcarflowmanagerwebapp.utility.PasswordEncryption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    @Test
    void getUserFromLogInNameAndPassword() {

    User user;
    UserRepository ur = new UserRepository();

    String logInName = "jen";
    String password = "1";

    String userExpectedFirstName = "Jen";
    String lastNameUserExpected = "Hansen";

    user = ur.getUserFromLogInNameAndPassword(logInName, password);

    String userFirstNameFound = user.getFirstName();
    String userLastNameFound = user.getLastName();

    assertEquals(userExpectedFirstName, userFirstNameFound);
    assertEquals(lastNameUserExpected, userLastNameFound);

    }

    @Test
    void getAllActiveEmployees() {

        List<User> activeUsers = new ArrayList<>();
        UserRepository ur = new UserRepository();

        int expectedListsize = 4;
        activeUsers = ur.getAllActiveEmployees();

        int actualListSize = activeUsers.size();

        assertEquals(expectedListsize, actualListSize);
    }

    @Test
    void updatePassword() {

        User userAfterUpdate;
        UserRepository ur = new UserRepository();

        String logInName = "jen";
        String newPassword = "2";

        String userFirstNameExpected = "Jen";
        String userLastNameExpected = "Hansen";

        ur.updatePassword(logInName, newPassword);

        userAfterUpdate = ur.getUserFromLogInNameAndPassword(logInName, newPassword);

        String firstNameFound = userAfterUpdate.getFirstName();
        String lastNameFound = userAfterUpdate.getLastName();

        assertEquals(userFirstNameExpected, firstNameFound);
        assertEquals(userLastNameExpected, lastNameFound);
    }

    @Test
    void updateIsUserActiveFalse() {
    }

    @Test
    void getUserFromLogInName() {
    }

    @Test
    void updateUserInfo() {
    }

    @Test
    void createNewUser() {
    }
}