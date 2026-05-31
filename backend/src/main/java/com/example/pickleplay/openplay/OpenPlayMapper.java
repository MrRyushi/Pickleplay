package com.example.pickleplay.openplay;

import com.example.pickleplay.court.Court;
import com.example.pickleplay.court.CourtRepository;
import com.example.pickleplay.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class OpenPlayMapper {

    private final CourtRepository courtRepository;

    public OpenPlayMapper(CourtRepository courtRepository) {
        this.courtRepository = courtRepository;
    }

    public OpenPlay toOpenPlay(OpenPlayDto dto){
        var openPlay = new OpenPlay();
        Court court = courtRepository.findById(dto.court_id())
                .orElseThrow(() -> new RuntimeException("Court not found with id " + dto.court_id()));
        openPlay.setCourt(court);
        openPlay.setDateTime(dto.dateTime());
        openPlay.setHost_id(dto.host_id());
        openPlay.setMaxPlayers(dto.maxPlayers());
        openPlay.setPricePerPlayer(dto.pricePerPlayer());
        openPlay.setSkillLevel(dto.skillLevel());

        return openPlay;
    }

    public OpenPlayResponseDto toOpenPlayResponseDto(OpenPlay openPlay){
        return new OpenPlayResponseDto(
                openPlay.getCourt().getId(),
                openPlay.getHost_id(),
                openPlay.getDateTime(),
                openPlay.getDuration(),
                openPlay.getMaxPlayers(),
                openPlay.getSkillLevel(),
                openPlay.getPricePerPlayer()
        );
    }
}
