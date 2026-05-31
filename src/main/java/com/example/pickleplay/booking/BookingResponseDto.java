package com.example.pickleplay.booking;

import com.example.pickleplay.Status;
import com.example.pickleplay.court.Court;
import com.example.pickleplay.user.User;

import java.time.LocalDateTime;

public record BookingResponseDto(
        Integer id,
        LocalDateTime startTime,
        LocalDateTime endTime,
        Status status,
        Integer courtId,
        Integer userId
) {
}
