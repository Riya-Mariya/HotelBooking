package com.example.hotelBooking.model;


import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("bookings")

public class Booking {
    @Id
    @Column("id")
    private Long id;
    @NotBlank(message = "Name cannot be blank")
    @Column("name")
    private String name;
    @NotNull(message = "Check-in date cannot be blank")
    @FutureOrPresent(message = "Check-in date must be in the future or present")
    @Column("checkInDate")
    private LocalDate checkInDate;
    @NotNull(message = "Check-out date cannot be blank")
    @FutureOrPresent(message = "Check-out date must be in the future or present")
    @Column("checkOutDate")
    private LocalDate checkOutDate;
    @Column("email")
    private String email;
    @NotBlank(message = "Telephone number cannot be blank")
    @Column("telephone")
    private String telephone;
    @Column("numberOfGuests")
    private int numberOfGuests;
    @NotBlank(message = "Room type cannot be blank")
    @Column("roomType")
    private String roomType;
    @JsonProperty("isPaid")
    @Column("isPaid")
    private boolean isPaid;

    public Booking updateFields(Booking updatedBooking,Long id) {
        return Booking.builder()
                .id(id)
                .name(updatedBooking.getName() != null ? updatedBooking.getName() : this.getName())
                .checkInDate(updatedBooking.getCheckInDate() != null ? updatedBooking.getCheckInDate() : this.checkInDate)
                .checkOutDate(updatedBooking.getCheckOutDate() != null ? updatedBooking.getCheckOutDate() : this.checkOutDate)
                .email(updatedBooking.getEmail() != null ? updatedBooking.getEmail() : this.getEmail())
                .telephone(updatedBooking.getTelephone() != null ? updatedBooking.getTelephone() : this.getTelephone())
                .numberOfGuests(updatedBooking.getNumberOfGuests() != 0 ? updatedBooking.getNumberOfGuests() : this.numberOfGuests)
                .roomType(updatedBooking.getRoomType() != null ? updatedBooking.getRoomType() : this.roomType)
                .isPaid(updatedBooking.isPaid)
                .build();
    }


}
