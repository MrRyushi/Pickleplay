package com.example.pickleplay.court;

import org.springframework.stereotype.Service;

@Service
public class CourtMapper {
    public Court toCourt(CourtDto dto){
        var court = new Court();
        court.setId(dto.id());
        court.setName(dto.name());
        court.setLocation(dto.location());
        court.setHourly_rate(dto.hourly_rate());

        return court;
    }

    public CourtResponseDto toCourtResponseDto(Court court){
        return new CourtResponseDto(
                court.getId(),
                court.getName(),
                court.getLocation(),
                court.getHourly_rate()
        );
    }
}
