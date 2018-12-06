package com.ysol.ptapp.parentteacherservices.teacher;

import com.ysol.ptapp.parentteacherservices.course.Course;
import com.ysol.ptapp.parentteacherservices.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Teacher {
    @GeneratedValue
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany
    private List<Course> courses;
}
