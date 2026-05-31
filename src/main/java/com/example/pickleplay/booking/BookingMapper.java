package com.example.pickleplay.booking;

import com.example.pickleplay.court.Court;
import com.example.pickleplay.court.CourtRepository;
import com.example.pickleplay.user.User;
import com.example.pickleplay.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingMapper {

    private final UserRepository userRepository;
    private final CourtRepository courtRepository;

    public BookingMapper(UserRepository userRepository, CourtRepository courtRepository) {
        this.userRepository = userRepository;
        this.courtRepository = courtRepository;
    }

    public Booking toBooking(BookingDto dto) {
        var booking = new Booking();
        booking.setStartTime(dto.startTime());
        booking.setEndTime(dto.endTime());
        booking.setStatus(dto.status());

        Court court = courtRepository.findById(dto.courtId())
                .orElseThrow(() -> new RuntimeException("Court not found with id " + dto.courtId()));
        booking.setCourt(court);

        return booking;
    }

    public BookingResponseDto toBookingResponseDto(Booking booking) {
        return new BookingResponseDto(
                booking.getId(),
                booking.getStartTime(),
                booking.getEndTime(),
                booking.getStatus(),
                booking.getCourt().getId(),
                booking.getUser().getId()
        );
    }
}
