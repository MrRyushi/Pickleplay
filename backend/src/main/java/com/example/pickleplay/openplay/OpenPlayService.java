package com.example.pickleplay.openplay;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OpenPlayService {
    public final OpenPlayRepository repository;
    public final OpenPlayMapper openPlayMapper;

    public OpenPlayService(OpenPlayRepository repository, OpenPlayMapper openPlayMapper) {
        this.repository = repository;
        this.openPlayMapper = openPlayMapper;
    }

    public OpenPlayResponseDto saveOpenPlay(OpenPlayDto dto){
        var openPlay = openPlayMapper.toOpenPlay(dto);
        var savedOpenPlay = repository.save(openPlay);
        return openPlayMapper.toOpenPlayResponseDto(savedOpenPlay);
    }

    public List<OpenPlayResponseDto> getOpenPlays(Integer courtId, LocalDate date){
        // get by courts and date
        if (courtId != null && date != null) {
            LocalDateTime startOfDay = date.atStartOfDay();
            LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

            return repository.findByCourtIdAndDateTimeBetween(
                    courtId,
                    startOfDay,
                    endOfDay
                    )
                    .stream()
                    .map(openPlayMapper::toOpenPlayResponseDto)
                    .collect(Collectors.toList());
        }

        // get by courts
        if (courtId != null) {
            return repository.findByCourtId(courtId)
                    .stream()
                    .map(openPlayMapper::toOpenPlayResponseDto)
                    .collect(Collectors.toList());
        }

        // get by the whole day
        if (date != null) {
            LocalDateTime startOfDay = date.atStartOfDay();
            LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

            return repository.findByDateTimeBetween(
                    startOfDay,
                    endOfDay
                    )
                    .stream()
                    .map(openPlayMapper::toOpenPlayResponseDto)
                    .collect(Collectors.toList());
        }

        // get all
        return repository.findAll()
                .stream()
                .map(openPlayMapper::toOpenPlayResponseDto)
                .collect(Collectors.toList());
    }

    public void deleteOpenPlay(Integer id){
        repository.deleteById(id);
    }
}
