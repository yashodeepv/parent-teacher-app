package com.ysol.ptapp.parentteacherservices.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChildDTO {
    private String firstName;
    private String lastName;
    private String grade;
    private ParentDTO parent;
}
