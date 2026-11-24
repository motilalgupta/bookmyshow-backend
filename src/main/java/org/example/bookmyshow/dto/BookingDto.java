package org.example.bookmyshow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bookmyshow.model.ShowSeat;
import org.example.bookmyshow.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    private Long id;
    private String bookingNumber;
    private LocalDateTime bookingTime;

    private UserDto users;
    private ShowDto show;
    private String status;
    private Double totalAmount;

    private List<ShowSeatDto> seats;
    private PaymentDto payment;

}
