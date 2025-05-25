package com.devsteve.hotel_manage_system.infra.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class ReservationServiceId implements Serializable {
    private static final long serialVersionUID = -2264997771835529292L;
    @NotNull
    @Column(name = "reservation_id", nullable = false)
    private UUID reservationId;

    @NotNull
    @Column(name = "service_id", nullable = false)
    private Integer serviceId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ReservationServiceId entity = (ReservationServiceId) o;
        return Objects.equals(this.reservationId, entity.reservationId) &&
                Objects.equals(this.serviceId, entity.serviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId, serviceId);
    }

}