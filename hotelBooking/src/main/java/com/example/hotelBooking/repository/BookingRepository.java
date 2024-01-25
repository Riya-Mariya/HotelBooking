package com.example.hotelBooking.repository;

import com.example.hotelBooking.model.Booking;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BookingRepository extends ReactiveCrudRepository<Booking, Long> {
}
