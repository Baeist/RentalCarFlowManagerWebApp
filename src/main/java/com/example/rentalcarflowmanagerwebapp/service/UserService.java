package com.example.rentalcarflowmanagerwebapp.service;

import com.example.rentalcarflowmanagerwebapp.model.UserModel;
import org.springframework.stereotype.Service;
import com.example.rentalcarflowmanagerwebapp.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel getUserFromLogInNameAndPassword(String logInName, String employeePassword){

        return userRepository.getUserFromLogInNameAndPassword(logInName, employeePassword);
    }

    public List<UserModel> getAllActiveEmployees(){
        return userRepository.getAllActiveEmployees();
    }

    public void updatePassword(String logInName, String firstNewPassword){
        userRepository.updatePassword(logInName, firstNewPassword);
    }
    public UserModel getUserFromLogInName(String logInName){

        return userRepository.getUserFromLogInName(logInName);
    }
    public void updateIsUserActiveFalse(String logInName){
        userRepository.updateIsUserActiveFalse(logInName);
    }

    public void updateUserInfo(int employeeID, String firstName, String lastName, String logInName, String employeeType){
     userRepository.updateUserInfo(employeeID, firstName, lastName, logInName, employeeType);
    }

    public void createNewUser(String firstName, String lastName, String logInName, String employeeType, String employeePassword){
        userRepository.createNewUser(firstName, lastName, logInName, employeeType, employeePassword);
    }
}
