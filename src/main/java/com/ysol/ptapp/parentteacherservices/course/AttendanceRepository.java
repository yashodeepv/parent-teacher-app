package com.ysol.ptapp.parentteacherservices.course;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<AttendanceEntry, Long> {
    Optional<List<AttendanceEntry>> findByCourseBatchId(Long courseBatchId);
}
