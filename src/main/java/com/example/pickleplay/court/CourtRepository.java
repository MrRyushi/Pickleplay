package com.example.pickleplay.court;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourtRepository extends JpaRepository<Court, Integer> {
    List<Court> findByNameContainingIgnoreCaseAndLocationContainingIgnoreCase(String name, String location);
    List<Court> findByNameContainingIgnoreCase(String name);
    List<Court> findByLocationContainingIgnoreCase(String location);
}
