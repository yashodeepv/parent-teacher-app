package com.ysol.ptapp.parentteacherservices.services.user;

import com.ysol.ptapp.parentteacherservices.entity.UserDTO;
import com.ysol.ptapp.parentteacherservices.jpa.UserRepository;
import com.ysol.ptapp.parentteacherservices.jpa.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public boolean validateCredentials(String userName, String password) {
        List<User> users = userRepository.findByUsernameAndPassword(userName, password).orElse(Collections.emptyList());
        return users.size() > 0;
    }

    public UserDTO getUserDetails(String userName, String password) {
        List<User> users = userRepository
                .findByUsernameAndPassword(userName, password)
                .orElse(Collections.emptyList());

        return userMapper.mapUser(users.size() > 0 ? users.get(0) : null);
    }

    public User addUser(UserDTO userDTO) {
        return userRepository.saveAndFlush(
                User.builder()
                        .userType(userDTO.getUserType())
                        .emailid(userDTO.getEmailid())
                        .password(userDTO.getPassword())
                        .username(userDTO.getUserName())
                        .build());
    }
}
