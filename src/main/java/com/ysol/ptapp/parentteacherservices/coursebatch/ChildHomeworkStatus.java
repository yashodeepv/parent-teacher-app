package com.ysol.ptapp.parentteacherservices.coursebatch;

import com.ysol.ptapp.parentteacherservices.child.Child;
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
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"child_id", "course_batch_id"})})
public class ChildHomeworkStatus {

    @GeneratedValue
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "child_id", nullable = false)
    private Child child;

    @OneToOne
    @JoinColumn(name = "course_batch_id", nullable = false)
    private CourseBatch courseBatch;

    private HomeworkStatus homeworkStatus;

}
