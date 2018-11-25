package com.ysol.ptapp.parentteacherservices.teacher;

import com.ysol.ptapp.parentteacherservices.teacher.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
