package com.ysol.ptapp.parentteacherservices.coursebatch;

import com.ysol.ptapp.parentteacherservices.child.Child;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"child_id", "course_batch_id"})})
public class ChildCourseBatch {

    @GeneratedValue
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "child_id")
    private Child child;

    @OneToOne
    @JoinColumn(name = "course_batch_id")
    private CourseBatch courseBatch;

}
