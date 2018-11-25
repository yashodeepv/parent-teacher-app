package com.ysol.ptapp.parentteacherservices.user;

import com.ysol.ptapp.parentteacherservices.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public boolean validateCredentials(String userName, String password) {
        List<User> users = userRepository.findByUsernameAndPassword(userName, password).orElse(Collections.emptyList());
        return users.size() > 0;
    }

    public User getUserDetails(String userName, String password) {
        List<User> users = userRepository
                .findByUsernameAndPassword(userName, password)
                .orElseThrow(UserNotFoundException::new);

        return (users.size() > 0 ? users.get(0) : null);
    }

    public User addUser(User userDTO) {
        return userRepository.saveAndFlush(
                User.builder()
                        .userType(userDTO.getUserType())
                        .emailid(userDTO.getEmailid())
                        .password(userDTO.getPassword())
                        .username(userDTO.getUsername())
                        .build());
    }

}
