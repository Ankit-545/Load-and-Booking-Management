package com.example.assignment.Entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

import java.sql.Timestamp;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Facility {

    @NotBlank(message = "Loading point is required")
    private String loadingPoint;
    
    @NotBlank(message = "Unloading point is required")
    private String unloadingPoint;

    @NotNull(message = "loading date is required")
    private Timestamp loadingDate;

    @NotNull(message = "Unloading date is required")
    private Timestamp unloadingDate;

 
}
