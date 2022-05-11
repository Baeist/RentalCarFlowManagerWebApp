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
}
