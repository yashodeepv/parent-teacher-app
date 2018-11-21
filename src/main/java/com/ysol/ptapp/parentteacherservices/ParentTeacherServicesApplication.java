package com.ysol.ptapp.parentteacherservices;

import com.ysol.ptapp.parentteacherservices.entity.ChildDTO;
import com.ysol.ptapp.parentteacherservices.entity.ParentDTO;
import com.ysol.ptapp.parentteacherservices.entity.UserDTO;
import com.ysol.ptapp.parentteacherservices.entity.UserType;
import com.ysol.ptapp.parentteacherservices.jpa.ChildRepository;
import com.ysol.ptapp.parentteacherservices.jpa.ParentRepository;
import com.ysol.ptapp.parentteacherservices.jpa.UserRepository;
import com.ysol.ptapp.parentteacherservices.jpa.domain.Child;
import com.ysol.ptapp.parentteacherservices.jpa.domain.Parent;
import com.ysol.ptapp.parentteacherservices.jpa.domain.User;
import com.ysol.ptapp.parentteacherservices.services.parent.ParentService;
import com.ysol.ptapp.parentteacherservices.services.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class ParentTeacherServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParentTeacherServicesApplication.class, args);
    }

    @Bean
    public CommandLineRunner cml(UserRepository userRepository, ParentRepository parentRepository, ChildRepository childRepository, ParentService parentService, UserService userService) {
        return b -> {
            Stream.of("yash:123", "rash:123")
                    .map(a -> a.split(":"))
                    .map(a -> User.builder()
                            .username(a[0])
                            .password(a[1])
                            .userType(UserType.PARENT)
                            .build())
                    .forEach(a -> {
                        userRepository.saveAndFlush(a);
                        Parent parent = parentRepository.saveAndFlush(Parent.builder()
                                .user(a)
                                .build());
                        childRepository.saveAndFlush(Child.builder()
                                .firstName("yash12")
                                .lastName("vaid")
                                .parent(parent)
                                .build());
                        childRepository.saveAndFlush(Child.builder()
                                .firstName("rash12")
                                .lastName("vaid")
                                .parent(parent)
                                .build());
                    });

            UserDTO yash = userService.getUserDetails("yash", "123");

            List<ChildDTO> childDTOS = parentService.addChild(ChildDTO.builder()
                    .parent(ParentDTO.builder()
                            .user(yash)
                            .build())
                    .firstName("Yash123")
                    .lastName("Vaidya")
                    .grade("9th")
                    .build());

            System.out.println(childDTOS);
        };
    }

}

