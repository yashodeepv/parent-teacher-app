package com.ysol.ptapp.parentteacherservices.coursebatch;

import com.ysol.ptapp.parentteacherservices.coursebatch.ChildAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildAttendanceRepository extends JpaRepository<ChildAttendance, Long> {
}
