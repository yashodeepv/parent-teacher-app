package com.ysol.ptapp.parentteacherservices.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceEntry {

    @GeneratedValue
    @Id
    private Long id;

    private Date date;
    private boolean present;

    @OneToOne
    @JoinColumn(name="course_batch_details_id")
    private CourseBatchDetails courseBatchDetails;

}
