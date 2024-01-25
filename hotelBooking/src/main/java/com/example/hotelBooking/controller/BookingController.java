package com.example.hotelBooking.controller;

import com.example.hotelBooking.model.Booking;
import com.example.hotelBooking.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController

@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public Mono<Booking> createBooking(@Valid @RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @GetMapping
    public Flux<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public Mono<Booking> getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @PutMapping("/{id}")
    public Mono<Booking> updateBooking(@PathVariable Long id, @Valid @RequestBody Booking updatedBooking) {
        return bookingService.updateBooking(id, updatedBooking);
    }


    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<String>> deleteBooking(@PathVariable Long id) {
        return bookingService.deleteBooking(id)
                .then(Mono.just(new ResponseEntity<>("Booking with id " + id + " successfully deleted", HttpStatus.OK)));
    }
}
