package com.example.pickleplay.booking;

import com.example.pickleplay.court.Court;
import com.example.pickleplay.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
   @Query("""
   SELECT b FROM Booking b
   WHERE b.startTime < :endOfDay
   AND b.endTime > :startOfDay
   """)
   List<Booking> findBookingsOverlappingDate(
           @Param("startOfDay") LocalDateTime startOfDay,
           @Param("endOfDay") LocalDateTime endOfDay
   );

   List<Booking> findByUserId(Integer userId);
   List<Booking> findByCourtId(Integer courtId);
}
