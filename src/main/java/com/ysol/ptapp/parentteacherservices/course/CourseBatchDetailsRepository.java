package com.ysol.ptapp.parentteacherservices.course;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseBatchDetailsRepository extends JpaRepository<CourseBatchDetails, Long> {

    Optional<List<CourseBatchDetails>> findByChildIdAndCourseId(Long childId, Long courseBatchId);

    Optional<List<CourseBatchDetails>> findByCourseId(Long courseBatchId);

    Optional<List<CourseBatchDetails>> findByChildId(Long childId);

}
