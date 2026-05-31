package com.example.pickleplay.court;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourtService {
    public final CourtRepository repository;
    public final CourtMapper courtMapper;

    public CourtService(CourtRepository repository, CourtMapper courtMapper) {
        this.repository = repository;
        this.courtMapper = courtMapper;
    }

    public CourtResponseDto saveCourt(CourtDto dto){
        var court = courtMapper.toCourt(dto);
        var savedCourt = repository.save(court);
        return courtMapper.toCourtResponseDto(savedCourt);
    }

    public CourtResponseDto getCourtById(Integer id){
        return repository.findById(id)
                .map(courtMapper::toCourtResponseDto)
                .orElse(null);
    }

    public List<CourtResponseDto> getCourts(String name, String location) {

        if (name != null && location != null) {
            return repository
                    .findByNameContainingIgnoreCaseAndLocationContainingIgnoreCase(name, location)
                    .stream()
                    .map(courtMapper::toCourtResponseDto)
                    .toList();
        }

        if (name != null) {
            return repository
                    .findByNameContainingIgnoreCase(name)
                    .stream()
                    .map(courtMapper::toCourtResponseDto)
                    .toList();
        }

        if (location != null) {
            return repository
                    .findByLocationContainingIgnoreCase(location)
                    .stream()
                    .map(courtMapper::toCourtResponseDto)
                    .toList();
        }

        return repository.findAll()
                .stream()
                .map(courtMapper::toCourtResponseDto)
                .toList();
    }

    public void delete(Integer id){
        repository.deleteById(id);
    }
}
