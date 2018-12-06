package com.ysol.ptapp.parentteacherservices.course;

import com.ysol.ptapp.parentteacherservices.child.Child;
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
public class CourseBatchDetails {

    @GeneratedValue
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "child_id")
    private Child child;

    @OneToMany
    private List<AttendanceEntry> attendanceEntries;

    @OneToMany
    private List<ChildHomeworkStatus> homeworkStatuses;

    @OneToOne
    @JoinColumn(name="course_id")
    private Course course;
}
