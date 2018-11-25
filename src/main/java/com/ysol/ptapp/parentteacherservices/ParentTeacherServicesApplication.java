package com.ysol.ptapp.parentteacherservices;

import com.ysol.ptapp.parentteacherservices.entity.UserType;
import com.ysol.ptapp.parentteacherservices.child.ChildRepository;
import com.ysol.ptapp.parentteacherservices.parent.ParentRepository;
import com.ysol.ptapp.parentteacherservices.user.UserRepository;
import com.ysol.ptapp.parentteacherservices.child.Child;
import com.ysol.ptapp.parentteacherservices.parent.Parent;
import com.ysol.ptapp.parentteacherservices.user.User;
import com.ysol.ptapp.parentteacherservices.parent.ParentService;
import com.ysol.ptapp.parentteacherservices.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
@EnableWebMvc
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


        };
    }

}

