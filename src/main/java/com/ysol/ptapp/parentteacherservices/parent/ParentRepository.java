package com.ysol.ptapp.parentteacherservices.parent;

import com.ysol.ptapp.parentteacherservices.parent.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParentRepository extends JpaRepository<Parent, Long> {
    Optional<Parent> findByUserId(Long userId);
}
