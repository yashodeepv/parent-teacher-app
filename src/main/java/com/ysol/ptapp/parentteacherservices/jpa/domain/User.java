package com.ysol.ptapp.parentteacherservices.jpa.domain;

import com.ysol.ptapp.parentteacherservices.entity.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @GeneratedValue
    @Id
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String emailid;

    private UserType userType;
    private String firstName;
    private String lastName;

}
