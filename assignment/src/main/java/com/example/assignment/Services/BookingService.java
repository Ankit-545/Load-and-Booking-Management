package com.example.assignment.Services;

import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.assignment.Entity.Booking;
import com.example.assignment.Entity.Load;
import com.example.assignment.Entity.Booking.Status;
import com.example.assignment.Repository.BookingRepository;
import com.example.assignment.Repository.LoadRepository;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final LoadRepository loadRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, LoadRepository loadRepository) {
        this.bookingRepository = bookingRepository;
        this.loadRepository = loadRepository;
    }

    public Booking createBooking(Booking booking) {
        UUID loadId = booking.getLoad().getId();

        Load load = loadRepository.findById(loadId)
                .orElseThrow(() -> new NoSuchElementException("Load not found with id: " + loadId));

        if (load.getStatus() == Load.Status.CANCELLED) {
            throw new IllegalStateException("Cannot create booking: Load is CANCELLED");
        }

        if (booking.getRequestedAt() == null) {
            booking.setRequestedAt(new Timestamp(System.currentTimeMillis()));
        }

        booking.setStatus(Status.PENDING);
        load.setStatus(Load.Status.BOOKED);
        loadRepository.save(load);
        booking.setLoad(load);
        Booking savedBooking = bookingRepository.save(booking);

        

        return savedBooking;
    }


    public List<Booking> getFilteredBookings(String shipperId, String transporterId) {
        if (shipperId != null && transporterId != null) {
            return bookingRepository.findByLoad_ShipperIdAndTransporterId(shipperId, transporterId);
        } else if (shipperId != null) {
            return bookingRepository.findByLoad_ShipperId(shipperId);
        } else if (transporterId != null) {
            return bookingRepository.findByTransporterId(transporterId);
        } else {
            return bookingRepository.findAll();
        }
    }
    

    public Booking getBookingById(UUID bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new NoSuchElementException("Booking not found with id: " + bookingId));
    }
    

    public Booking updateBooking(UUID bookingId, Booking updatedBooking) {
        Booking existingBooking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new NoSuchElementException("Booking not found with id: " + bookingId));
    
       
       
        if (updatedBooking.getStatus() == Booking.Status.ACCEPTED) {
            existingBooking.setStatus(Booking.Status.ACCEPTED);
        }
    
        return bookingRepository.save(existingBooking);
    }
    

    public void deleteBooking(UUID bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new NoSuchElementException("Booking not found with id: " + bookingId));
    
        Load load = booking.getLoad();
    
        // Delete the booking
        bookingRepository.deleteById(bookingId);
    
        // Update Load status to CANCELLED
        load.setStatus(Load.Status.CANCELLED);
        loadRepository.save(load);
    }
    
}
 