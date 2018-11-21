package com.ysol.ptapp.parentteacherservices.jpa;

import com.ysol.ptapp.parentteacherservices.jpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<List<User>> findByUsernameAndPassword(String userName, String password);

    Optional<List<User>> findByUsername(String username);
}
