package com.example.pickleplay.openplay;

import com.example.pickleplay.SkillLevel;

import java.time.LocalDateTime;

public record OpenPlayDto(
        Integer court_id,
        Integer host_id,
        LocalDateTime dateTime,
        int duration,
        int maxPlayers,
        SkillLevel skillLevel,
        int pricePerPlayer
) {
    @Override
    public Integer court_id() {
        return court_id;
    }

    @Override
    public Integer host_id() {
        return host_id;
    }

    @Override
    public LocalDateTime dateTime() {
        return dateTime;
    }

    @Override
    public int duration() {
        return duration;
    }

    @Override
    public int maxPlayers() {
        return maxPlayers;
    }

    @Override
    public SkillLevel skillLevel() {
        return skillLevel;
    }

    @Override
    public int pricePerPlayer() {
        return pricePerPlayer;
    }
}
