package com.ysol.ptapp.parentteacherservices.jpa;

import com.ysol.ptapp.parentteacherservices.jpa.domain.Child;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChildRepository extends JpaRepository<Child, Long> {
    Optional<List<Child>> findByParentId(Long id);
}
