package com.ysol.ptapp.parentteacherservices.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private String password;
    private String userName;
    private String emailid;
    private UserType userType;

}
