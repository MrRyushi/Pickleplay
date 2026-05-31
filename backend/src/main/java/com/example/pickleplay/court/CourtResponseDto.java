package com.example.pickleplay.court;

public record CourtResponseDto(
        Integer id,
        String name,
        String location,
        int hourly_rate
) {
}
