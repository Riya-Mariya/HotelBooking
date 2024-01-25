package com.example.hotelBooking.ServiceImpl;

import com.example.hotelBooking.Exception.BookingNotFoundException;
import com.example.hotelBooking.model.Booking;
import com.example.hotelBooking.repository.BookingRepository;
import com.example.hotelBooking.service.impl.BookingServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataR2dbcTest
public class BookingServiceImplTest {
    private final BookingRepository bookingRepository;
    private final BookingServiceImpl bookingService;

    @Autowired
    public BookingServiceImplTest(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
        this.bookingService = new BookingServiceImpl(bookingRepository);
    }

    public void setup() {
        bookingRepository.deleteAll().block(); // Assuming deleteAll returns a Mono<Void>
    }

    @Test
    public void getAllBookingsTest() {
        Flux<Booking> result = bookingService.getAllBookings();

        StepVerifier.create(result)
                .expectComplete();
    }

    @Test
    public void getBookingsByIdTest() {
        Mono<Booking> result = bookingService.getBookingById(2L);

        StepVerifier.create(result)
                .consumeNextWith(bookingDetails -> {
                    assertEquals("Test", bookingDetails.getName());
                }).verifyComplete();
    }

    @Test
    public void deleteBookingsByIdTest() {
        Mono<Void> result = bookingService.deleteBooking(1L);

        StepVerifier.create(result).verifyComplete();
    }

    @Test
    public void createBookingTest() {
        Booking bookingTestData1 = Booking.builder() // Use a unique ID for each test
                .name("Juan").checkInDate(LocalDate.parse("2024-03-05")).checkOutDate(LocalDate.parse("2024-02-05"))
                .email("jan@gmail.com").telephone("1312425").isPaid(true).numberOfGuests(2).roomType("Deluxe").build();

        Mono<Booking> result = bookingService.createBooking(bookingTestData1);

        StepVerifier.create(result)
                .assertNext(booking -> {
                    assertEquals("Juan", booking.getName());
                    // Add more assertions if needed
                })
                .expectComplete()
                .verify();
    }

    @Test
    public void updateBookingTest() {

        Booking bookingTestData1 = Booking.builder() // Use a unique ID for each test
                .name("Juan").checkInDate(LocalDate.parse("2024-03-05")).checkOutDate(LocalDate.parse("2024-02-05"))
                .email("jan@gmail.com").telephone("1312425").isPaid(true).numberOfGuests(2).roomType("Deluxe").build();
        bookingTestData1.setName("Juvaniya");
        Booking updatedBooking = bookingTestData1.updateFields(bookingTestData1,1L);

        Mono<Booking> result = bookingService.updateBooking(1L, updatedBooking);

        StepVerifier.create(result)
                .assertNext(booking -> {
                    assertEquals("Juvaniya", booking.getName());
                    // Add more assertions if needed
                })
                .expectComplete()
                .verify();
    }

    @Test
    public void updateBookingNotFoundTest() {

        Booking bookingTestData1 = Booking.builder()
                .name("Juan").checkInDate(LocalDate.parse("2024-03-05")).checkOutDate(LocalDate.parse("2024-02-05"))
                .email("jan@gmail.com").telephone("1312425").isPaid(true).numberOfGuests(2).roomType("Deluxe").build();
        bookingTestData1.setName("Juvaniya");
        Booking updatedBooking = bookingTestData1.updateFields(bookingTestData1,1L);

        Mono<Booking> result = bookingService.updateBooking(7L, updatedBooking);

        StepVerifier.create(result)
                .expectError(BookingNotFoundException.class)
                .verify();
    }


}
