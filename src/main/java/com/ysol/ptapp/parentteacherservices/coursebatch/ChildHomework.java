package com.ysol.ptapp.parentteacherservices.coursebatch;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChildHomework {

    @GeneratedValue
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_batch_id", nullable = false)
    private CourseBatch batch;

    @OneToMany
    private List<ChildHomeworkStatus> homeworkStatuses;

}
