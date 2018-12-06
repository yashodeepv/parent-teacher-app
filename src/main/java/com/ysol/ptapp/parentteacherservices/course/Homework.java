package com.ysol.ptapp.parentteacherservices.course;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Homework {

    @GeneratedValue
    @Id
    private Long id;

    private String homeworkDetails;

    @OneToOne
    @JoinColumn(name="course_id")
    private Course course;

}
