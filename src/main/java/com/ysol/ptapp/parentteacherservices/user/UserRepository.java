package com.ysol.ptapp.parentteacherservices.user;

import com.ysol.ptapp.parentteacherservices.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<List<User>> findByUsernameAndPassword(String userName, String password);

    Optional<List<User>> findByUsername(String username);
}
