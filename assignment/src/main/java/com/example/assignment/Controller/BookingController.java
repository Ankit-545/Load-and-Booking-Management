package com.example.assignment.Controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment.Entity.Booking;
import com.example.assignment.Services.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@Valid @RequestBody Booking booking) {
        try {
            Booking createdBooking = bookingService.createBooking(booking);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



    @GetMapping
    public ResponseEntity<List<Booking>> getBookings(
        @RequestParam(required = false) String shipperId,
        @RequestParam(required = false) String transporterId
    ) {
         List<Booking> bookings = bookingService.getFilteredBookings(shipperId, transporterId);
        return ResponseEntity.ok(bookings);
    }


    @GetMapping("/{bookingId}")
    public ResponseEntity<?> getBookingById(@PathVariable UUID bookingId) {
      try {
        Booking booking = bookingService.getBookingById(bookingId);
        return ResponseEntity.ok(booking);
      } catch (NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found with id: " + bookingId);
      }
    }


    @PutMapping("/{bookingId}")
    public ResponseEntity<?> updateBooking(@PathVariable UUID bookingId, @Valid @RequestBody Booking updatedBooking) {
      try {
        Booking booking = bookingService.updateBooking(bookingId, updatedBooking);
        return ResponseEntity.ok(booking);
       } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
      }
    }


    @DeleteMapping("/{bookingId}")
    public ResponseEntity<?> deleteBooking(@PathVariable UUID bookingId) {
        try {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.ok("Booking deleted and load marked as CANCELLED");
     } catch (Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
     }
    }

}

