package com.example.hotelBooking.controller;

import com.example.hotelBooking.model.Booking;
import com.example.hotelBooking.service.BookingService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(BookingController.class)
public class BookingControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private BookingService bookingService;
    private static Booking bookingTestData1;
    private static Booking bookingTestData2;
    private static Booking bookingTestData3;


    @BeforeAll
    public static void setUp() {
        bookingTestData1 = Booking.builder().id(1L).name("Jan").checkInDate(LocalDate.parse("2024-03-05")).checkOutDate(LocalDate.parse("2024-02-05"))
                .email("jan@gmail.com").telephone("1312425").isPaid(true).numberOfGuests(2).roomType("Deluxe").build();
        bookingTestData2 = Booking.builder().id(2L).name("Jeni").checkInDate(LocalDate.parse("2024-03-07")).checkOutDate(LocalDate.parse("2024-02-08"))
                .email("jeni@gmail.com").telephone("13129925").isPaid(true).numberOfGuests(1).roomType("Deluxe").build();
        bookingTestData3 = Booking.builder().id(2L).name("Jeni").checkInDate(LocalDate.parse("2024-03-07")).checkOutDate(LocalDate.parse("2024-02-08"))
                .email("jeni@gmail.com").isPaid(true).numberOfGuests(1).roomType("Deluxe").build();

    }

    @Test
    public void testCreateBooking() {
        when(bookingService.createBooking(any(Booking.class))).thenReturn(Mono.just(bookingTestData1));

        webTestClient.post()
                .uri("/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(bookingTestData1)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Booking.class)
                .isEqualTo(bookingTestData1);
    }

    @Test
    public void testValidCreateBooking() {
        when(bookingService.createBooking(any(Booking.class))).thenReturn(Mono.just(bookingTestData3));

        webTestClient.post()
                .uri("/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(bookingTestData3)
                .exchange()
                .expectStatus().isBadRequest();
    }


    @Test
    public void testGetAllBookings() {
        when(bookingService.getAllBookings()).thenReturn(Flux.just(bookingTestData1, bookingTestData2));

        webTestClient.get()
                .uri("/bookings")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Booking.class)
                .contains(bookingTestData1, bookingTestData2);
    }

    @Test
    public void testUpdateBooking() {
        Booking updateFields = bookingTestData1;
        updateFields.setName("Test");
        Booking updatedBooking = bookingTestData1.updateFields(updateFields,1L);

        when(bookingService.updateBooking(1L, updatedBooking)).thenReturn(Mono.just(updatedBooking));

        webTestClient.put()
                .uri("/bookings/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updatedBooking)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Booking.class)
                .isEqualTo(updatedBooking);
    }

    @Test
    public void testDeleteBooking() {
        when(bookingService.deleteBooking(1L)).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/bookings/{id}", 1L)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("Booking with id " + 1L + " successfully deleted");
    }

    @Test
    public void testGetBookingById() {
        Booking booking = Booking.builder().id(1L).name("TestBooking").build();

        when(bookingService.getBookingById(1L)).thenReturn(Mono.just(booking));

        webTestClient.get()
                .uri("/bookings/{id}", 1L)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Booking.class)
                .isEqualTo(booking);
    }
}
