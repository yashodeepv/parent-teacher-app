package com.ysol.ptapp.parentteacherservices.course;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChildHomeworkStatusRepository extends JpaRepository<ChildHomeworkStatus, Long> {

    Optional<ChildHomeworkStatus> findByCourseBatchDetilsId(Long courseBatchDetailsId);

}
