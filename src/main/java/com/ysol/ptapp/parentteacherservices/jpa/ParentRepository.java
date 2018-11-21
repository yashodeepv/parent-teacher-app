package com.ysol.ptapp.parentteacherservices.jpa;

import com.ysol.ptapp.parentteacherservices.jpa.domain.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParentRepository extends JpaRepository<Parent, Long> {
    Optional<Parent> findByUserId(Long userId);
}
