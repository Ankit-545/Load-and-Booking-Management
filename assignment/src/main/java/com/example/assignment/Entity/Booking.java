package com.example.assignment.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "load_id", nullable = false)
    @NotNull(message = "Load reference is required")
    private Load load;

    @NotBlank(message = "Transporter ID is required")
    private String transporterId;

    @Positive(message = "Proposed rate must be positive")
    private double proposedRate;

    private String comment;

    @PastOrPresent(message = "Requested time cannot be in the future")
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp requestedAt;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    public enum Status {
        PENDING, ACCEPTED, REJECTED
    }
}
