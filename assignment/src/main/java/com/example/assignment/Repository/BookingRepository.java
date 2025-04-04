package com.example.assignment.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.assignment.Entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {

    List<Booking> findByLoad_ShipperId(String shipperId);
    List<Booking> findByTransporterId(String transporterId);
    List<Booking> findByLoad_ShipperIdAndTransporterId(String shipperId, String transporterId);

}
