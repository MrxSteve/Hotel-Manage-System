package com.devsteve.hotel_manage_system.shared.dto.req.reservation;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class AssignServiceRequest {
    @NotNull(message = "Service ID is required")
    private Integer serviceId;

    @NotNull(message = "Cantidad is required")
    @Min(1)
    private Integer cantidad;
}
