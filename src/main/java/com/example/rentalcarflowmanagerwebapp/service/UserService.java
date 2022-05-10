package service;

import model.UserModel;
import org.springframework.stereotype.Service;
import repository.UserRepository;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel getUserFromLogInNameAndPassword(String logInName, String employeePassword){

        return userRepository.getUserFromLogInNameAndPassword(logInName, employeePassword);
    }

}
