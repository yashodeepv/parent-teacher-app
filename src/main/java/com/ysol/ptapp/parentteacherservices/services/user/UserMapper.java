package com.ysol.ptapp.parentteacherservices.services.user;

import com.ysol.ptapp.parentteacherservices.entity.UserDTO;
import com.ysol.ptapp.parentteacherservices.jpa.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO mapUser(User user) {
        System.out.println(user);
        if(user == null) {
            return null;
        }
        return UserDTO.builder()
                .userName(user.getUsername())
                .password(user.getPassword())
                .emailid(user.getEmailid())
                .userType(user.getUserType())
                .build();
    }

}
