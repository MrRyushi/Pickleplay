package com.example.pickleplay.booking;

import com.example.pickleplay.user.User;
import com.example.pickleplay.user.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {
    public final BookingRepository bookingRepository;
    public final BookingMapper bookingMapper;
    public final UserRepository userRepository;

    public BookingService(BookingRepository repository, BookingMapper bookingMapper, UserRepository userRepository) {
        this.bookingRepository = repository;
        this.bookingMapper = bookingMapper;
        this.userRepository = userRepository;
    }

    public BookingResponseDto saveBooking(BookingDto dto, Authentication authentication){
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        var booking = bookingMapper.toBooking(dto);
        booking.setUser(user);

        var savedBooking = bookingRepository.save(booking);
        return bookingMapper.toBookingResponseDto(savedBooking);
    }

    public List<BookingResponseDto> getBookings(LocalDate date, Integer userId, Integer courtId){
        if (date != null) {
            LocalDateTime startOfDay = date.atStartOfDay();
            LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

            return bookingRepository.findBookingsOverlappingDate(startOfDay, endOfDay)
                    .stream()
                    .map(bookingMapper::toBookingResponseDto)
                    .collect(Collectors.toList());
        }

        if(userId != null) {
            return bookingRepository.findByUserId(userId)
                    .stream()
                    .map(bookingMapper::toBookingResponseDto)
                    .collect(Collectors.toList());
        }

        if(courtId != null) {
            return bookingRepository.findByCourtId(courtId)
                    .stream()
                    .map(bookingMapper::toBookingResponseDto)
                    .collect(Collectors.toList());
        }

        return bookingRepository.findAll()
                .stream()
                .map(bookingMapper::toBookingResponseDto)
                .collect(Collectors.toList());
    }

    public void delete(Integer id){
        bookingRepository.deleteById(id);
    }
}
