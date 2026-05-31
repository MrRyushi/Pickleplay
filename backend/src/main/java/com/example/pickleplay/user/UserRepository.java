package com.example.pickleplay.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByNameContainingIgnoreCase(String name);
    Optional<User> findByEmail(String email);
}
