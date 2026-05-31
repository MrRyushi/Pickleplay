package com.example.pickleplay.openplay;

import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OpenPlayRepository extends JpaRepository<OpenPlay, Integer> {
    List<OpenPlay> findByCourtIdAndDateTimeBetween(
            Integer courtId,
            LocalDateTime start,
            LocalDateTime end
    );

    List<OpenPlay> findByCourtId(
            Integer courtId
    );

    List<OpenPlay> findByDateTimeBetween(
            LocalDateTime start,
            LocalDateTime end
    );

}
