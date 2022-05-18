package com.example.rentalcarflowmanagerwebapp.repository;

import com.example.rentalcarflowmanagerwebapp.model.User;
import com.example.rentalcarflowmanagerwebapp.utility.PasswordEncryption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    @BeforeEach
    public void setup(){
        User user = new User();
    }

    @Test
    void getUserFromLogInNameAndPassword() {

    User user = new User();
    UserRepository ur = new UserRepository();
    PasswordEncryption pwe = new PasswordEncryption();

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
    }

    @Test
    void updatePassword() {
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