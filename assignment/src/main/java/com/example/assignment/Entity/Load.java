package com.example.assignment.Entity;


import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data

@AllArgsConstructor
public class Load {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Shipper ID is required")
    private String shipperId;

    @Embedded
    @Valid
    private Facility facility;

    @NotBlank(message = "Product type is required")
    private String productType;

    @NotBlank(message = "Truck type is required")
    private String truckType;

    @Min(value = 1, message = "At least one truck is required")
    private int noOfTrucks;

    @Positive(message = "Weight must be positive")
    private double weight;

    @Size(max = 255, message = "Comment must be under 255 characters")
    private String comment;

    @NotNull(message = "Date posted cannot be null")
    private Timestamp datePosted;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(255) DEFAULT 'POSTED'")
    private Status status ;

    public enum Status {
        POSTED, BOOKED, CANCELLED
    }

    public Load(){
        this.status = Status.POSTED;
    }

    
}
