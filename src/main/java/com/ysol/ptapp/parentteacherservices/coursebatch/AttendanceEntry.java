package com.ysol.ptapp.parentteacherservices.coursebatch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

}
