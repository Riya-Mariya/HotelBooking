package com.example.hotelBooking.service.impl;

import com.example.hotelBooking.Exception.BookingCreationException;
import com.example.hotelBooking.Exception.BookingDeletionException;
import com.example.hotelBooking.Exception.BookingNotFoundException;
import com.example.hotelBooking.Exception.BookingUpdateException;
import com.example.hotelBooking.model.Booking;
import com.example.hotelBooking.repository.BookingRepository;
import com.example.hotelBooking.service.BookingService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service

public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Mono<Booking> createBooking(Booking booking) {
        return bookingRepository.save(booking)
                .onErrorMap(e -> new BookingCreationException("Error creating booking: " + e.getMessage()));

    }

    @Override
    public Flux<Booking> getAllBookings() {
        return bookingRepository.findAll()
                .switchIfEmpty(Mono.error(new BookingNotFoundException("No bookings found")));
    }

    @Override
    public Mono<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id)
                .switchIfEmpty(Mono.error(new BookingNotFoundException("Booking not found with id: " + id)));
    }

    @Override
    public Mono<Booking> updateBooking(Long id, Booking updatedBooking) {
        return bookingRepository.findById(id)
                .switchIfEmpty(Mono.error(new BookingNotFoundException("Booking not found with id: " + id)))
                .flatMap(existingBooking -> {
                    Booking updateDetails = existingBooking.updateFields(updatedBooking,id);
                    return bookingRepository.save(updateDetails)
                            .onErrorMap(e -> new BookingUpdateException("Error updating booking: " + e.getMessage()));
                });
    }

    @Override
    public Mono<Void> deleteBooking(Long id) {
        return bookingRepository.findById(id)
                .switchIfEmpty(Mono.error(new BookingNotFoundException("Booking not found with id: " + id)))
                .flatMap(existingBooking ->
                        bookingRepository.deleteById(id)
                                .then(Mono.empty()) // Return an empty Mono upon successful deletion
                )
                .onErrorMap(e -> new BookingDeletionException("Error deleting booking: " + e.getMessage())).then();
    }

}
