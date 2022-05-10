package service;

import model.UserModel;
import org.springframework.stereotype.Service;
import repository.UserRepository;

@Service
public class UserService {

    UserRepository UR;

    public UserModel getUserFromLogInNameAndPassword(String logInName, String employeePassword){

        return UR.getUserFromLogInNameAndPassword(logInName, employeePassword);
    }

}
