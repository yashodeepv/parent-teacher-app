package com.ysol.ptapp.parentteacherservices.child;

import com.ysol.ptapp.parentteacherservices.child.Child;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChildRepository extends JpaRepository<Child, Long> {
    Optional<List<Child>> findByParentId(Long id);

    Optional<List<Child>> findByParentIdAndFirstNameAndLastName(Long id, String firstName, String lastName);

}
