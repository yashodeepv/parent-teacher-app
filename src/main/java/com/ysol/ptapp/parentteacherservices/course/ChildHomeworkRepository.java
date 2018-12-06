package com.ysol.ptapp.parentteacherservices.course;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChildHomeworkRepository extends JpaRepository<Homework, Long> {
    Optional<List<Homework>> findByCourseBatchId(Long courseBatchId);
}
