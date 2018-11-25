package com.ysol.ptapp.parentteacherservices.coursebatch;

import com.ysol.ptapp.parentteacherservices.teacher.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseBatch {

    @GeneratedValue
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @OneToMany
    private List<ChildCourseBatch> childrenCourseBatch;

    @OneToMany
    private List<ChildAttendance> childAttendances;

    @OneToMany
    private List<ChildHomework> childHomework;
}
