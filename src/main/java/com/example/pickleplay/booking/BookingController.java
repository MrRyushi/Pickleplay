package com.example.pickleplay.booking;

import com.example.pickleplay.court.Court;
import com.example.pickleplay.user.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public BookingResponseDto saveBooking(
            @RequestBody BookingDto bookingDto,
            Authentication authentication
    ){
        return bookingService.saveBooking(bookingDto, authentication);
    }

    @GetMapping
    public List<BookingResponseDto> getBookings(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) Integer courtId
    ){
        return bookingService.getBookings(date, userId, courtId);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Integer id
    ){
        this.bookingService.delete(id);
    }


}
