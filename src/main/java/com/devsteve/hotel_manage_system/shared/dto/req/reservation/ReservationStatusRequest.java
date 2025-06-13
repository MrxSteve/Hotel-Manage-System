package com.devsteve.hotel_manage_system.shared.dto.req.reservation;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class ReservationStatusRequest {
    @NotBlank(message = "Name is required")
    private String name;
}
