package com.example.pickleplay.booking;

import com.example.pickleplay.Status;
import com.example.pickleplay.court.Court;
import com.example.pickleplay.user.User;

import java.time.LocalDateTime;

public record BookingDto(
        LocalDateTime startTime,
        LocalDateTime endTime,
        Status status,
        Integer courtId,
        Integer userId
) {
    @Override
    public LocalDateTime startTime() {
        return startTime;
    }

    @Override
    public LocalDateTime endTime() {
        return endTime;
    }

    @Override
    public Status status() {
        return status;
    }

    @Override
    public Integer courtId() {
        return courtId;
    }

    @Override
    public Integer userId() {
        return userId;
    }
}
