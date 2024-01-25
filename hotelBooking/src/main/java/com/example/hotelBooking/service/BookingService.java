package com.example.hotelBooking.service;

import com.example.hotelBooking.model.Booking;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookingService {
    Mono<Booking> createBooking(Booking booking);

    Flux<Booking> getAllBookings();

    Mono<Booking> getBookingById(Long id);

    Mono<Booking> updateBooking(Long id, Booking updatedBooking);

    Mono<Void> deleteBooking(Long id);
}
