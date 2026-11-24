package org.example.bookmyshow.controller;

import jakarta.validation.Valid;
import org.example.bookmyshow.dto.BookingDto;
import org.example.bookmyshow.dto.BookingRequestDto;
import org.example.bookmyshow.model.Booking;
import org.example.bookmyshow.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PostExchange;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@Valid @RequestBody BookingRequestDto bookingRequest){
        return new ResponseEntity<>(bookingService.creatBooking(bookingRequest), HttpStatus.CREATED);
    }
}
