package com.example.spring_boot.repository;

import com.example.spring_boot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, String> {
    User findByName(String name);

    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.admin = true")
    boolean existAdmin();

}
