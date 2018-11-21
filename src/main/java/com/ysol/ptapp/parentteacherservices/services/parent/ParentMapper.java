package com.ysol.ptapp.parentteacherservices.services.parent;

import com.ysol.ptapp.parentteacherservices.entity.ParentDTO;
import com.ysol.ptapp.parentteacherservices.jpa.domain.Parent;
import com.ysol.ptapp.parentteacherservices.services.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParentMapper {

    private final UserMapper userMapper;

    @Autowired
    public ParentMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    public ParentDTO map(Parent parent) {
        return ParentDTO.builder()
                .user(userMapper.mapUser(parent.getUser()))
                .build();
    }

}
