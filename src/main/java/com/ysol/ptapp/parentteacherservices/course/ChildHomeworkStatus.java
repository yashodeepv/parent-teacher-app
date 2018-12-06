package com.ysol.ptapp.parentteacherservices.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"homework_id", "course_batch_detils_id"})})
public class ChildHomeworkStatus {

    @GeneratedValue
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "homework_id")
    private Homework homework;

    private HomeworkStatus homeworkStatus;

    @OneToOne
    @JoinColumn(name="course_batch_detils_id")
    private CourseBatchDetails courseBatchDetails;

}
