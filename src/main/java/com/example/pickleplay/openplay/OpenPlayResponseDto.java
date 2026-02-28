package com.example.pickleplay.openplay;

import com.example.pickleplay.SkillLevel;

import java.time.LocalDateTime;

public record OpenPlayResponseDto(
        Integer court_id,
        Integer host_id,
        LocalDateTime dateTime,
        int duration,
        int maxPlayers,
        SkillLevel skillLevel,
        int pricePerPlayer
) {
}
