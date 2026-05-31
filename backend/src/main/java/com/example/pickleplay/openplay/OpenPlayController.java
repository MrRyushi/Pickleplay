package com.example.pickleplay.openplay;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/openplays")
public class OpenPlayController {
    public final OpenPlayService openPlayService;

    public OpenPlayController(OpenPlayService openPlayService) {
        this.openPlayService = openPlayService;
    }

    @PostMapping
    public OpenPlayResponseDto saveOpenPlay(
            @RequestBody OpenPlayDto openPlayDto
    ){
        return openPlayService.saveOpenPlay(openPlayDto);
    }

    @GetMapping
    public List<OpenPlayResponseDto> getOpenPlays(
            @RequestParam(required = false) Integer courtId,
            @RequestParam(required = false) LocalDate date
    ){
        return openPlayService.getOpenPlays(courtId, date);
    }

    @DeleteMapping
    public void deleteOpenPlay(Integer id){
        this.openPlayService.deleteOpenPlay(id);
    }
}
