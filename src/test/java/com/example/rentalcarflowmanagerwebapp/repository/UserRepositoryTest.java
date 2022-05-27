package com.example.rentalcarflowmanagerwebapp.repository;

import com.example.rentalcarflowmanagerwebapp.model.User;
import com.example.rentalcarflowmanagerwebapp.utility.PasswordEncryption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Lars
class UserRepositoryTest {

    // needs a reset of the test database data before running

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

        String logInName = "pil";
        String newPassword = "5";

        String userFirstNameExpected = "Pil";
        String userLastNameExpected = "Tisen";

        ur.updatePassword(logInName, newPassword);

        userAfterUpdate = ur.getUserFromLogInNameAndPassword(logInName, newPassword);

        String firstNameFound = userAfterUpdate.getFirstName();
        String lastNameFound = userAfterUpdate.getLastName();

        assertEquals(userFirstNameExpected, firstNameFound);
        assertEquals(userLastNameExpected, lastNameFound);
    }

    @Test
    void updateIsUserActiveFalse() {

        List<User> activeUsers = new ArrayList<>();
        UserRepository ur = new UserRepository();

        String logInName = "tim";

        ur.updateIsUserActiveFalse(logInName);

        int expectedListsizeAfter = 3;
        activeUsers = ur.getAllActiveEmployees();

        int actualListSizeAfter = activeUsers.size();

        assertEquals(expectedListsizeAfter, actualListSizeAfter);
    }

    @Test
    void getUserFromLogInName() {

        UserRepository ur = new UserRepository();
        User user;
        String logInName = "dan";

        String userExpectedFirstName = "Dan";
        String lastNameUserExpected = "Jensen";

        user = ur.getUserFromLogInName(logInName);

        String userFirstNameFound = user.getFirstName();
        String userLastNameFound = user.getLastName();

        assertEquals(userExpectedFirstName, userFirstNameFound);
        assertEquals(lastNameUserExpected, userLastNameFound);

    }

    @Test
    void updateUserInfo() {

        UserRepository ur = new UserRepository();

        String oldLogInName = "jen";
        User oldInfoUser = ur.getUserFromLogInName(oldLogInName);
        int oldInfoUserEmployeeID = oldInfoUser.getEmployeeID();

        String newFirstName = "Don";
        String newLastName = "Mathisen";
        String newLogInName = "don";
        String newEmployeeType = "skade- og mangler";
        int expectedEmployeeID = 1;

        ur.updateUserInfo(oldInfoUserEmployeeID, newFirstName, newLastName, newLogInName, newEmployeeType);

        User newUserInfo = ur.getUserFromLogInName("don");

        int newInfoEmployeeID = newUserInfo.getEmployeeID();
        String foundEmployeeType = newUserInfo.getEmployeeType();

        assertEquals(expectedEmployeeID, newInfoEmployeeID);
        assertEquals(newEmployeeType, foundEmployeeType);

    }

    @Test
    void createNewUser() {

        UserRepository ur = new UserRepository();

        String newFirstName = "Tammy";
        String newLastName = "Toryr";
        String newLogInName = "tam";
        String newEmployeeType = "admin";
        String newPassword = "6";
        int expectedEmployeeID = 6;
        String expectedLastName = "Toryr";

        ur.createNewUser(newFirstName, newLastName, newLogInName, newEmployeeType, newPassword);

        User numberSix = ur.getUserFromLogInName("tam");

        int actualID = numberSix.getEmployeeID();
        String actualLastName = numberSix.getLastName();

        assertEquals(expectedEmployeeID, actualID);
        assertEquals(expectedLastName, actualLastName);

        ur.updateIsUserActiveFalse(numberSix.getLogInName());
    }
}