package com.example.pickleplay.court;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courts")
public class CourtController {
    private final CourtService courtService;

    public CourtController(CourtService courtService) {
        this.courtService = courtService;
    }

    @PostMapping
    public CourtResponseDto saveCourt(
            @Valid @RequestBody CourtDto courtDto
    ) {
        return this.courtService.saveCourt(courtDto);
    }

    @GetMapping
    public List<CourtResponseDto> getCourts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String location
    ){
        return this.courtService.getCourts(name, location);
    }

    @GetMapping("/{id}")
    public CourtResponseDto getCourtById(
            @PathVariable Integer id
    ){
        return this.courtService.getCourtById(id);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK
    )
    public void delete(
            @PathVariable Integer id
    ) {
        this.courtService.delete(id);
    }
}
