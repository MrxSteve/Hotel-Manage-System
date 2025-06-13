package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.specification;

import com.devsteve.hotel_manage_system.infra.entities.Reservation;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public class ReservationSpecification {
    public static Specification<Reservation> hasUserId(UUID userId) {
        return (root, query, cb) -> userId == null ? null : cb.equal(root.get("user").get("id"), userId);
    }

    public static Specification<Reservation> hasRoomId(UUID roomId) {
        return (root, query, cb) -> roomId == null ? null : cb.equal(root.get("room").get("id"), roomId);
    }

    public static Specification<Reservation> hasStatusId(Integer statusId) {
        return (root, query, cb) -> statusId == null ? null : cb.equal(root.get("status").get("id"), statusId);
    }

    public static Specification<Reservation> hasFechaInicioDesde(LocalDate desde) {
        return (root, query, cb) -> desde == null ? null : cb.greaterThanOrEqualTo(root.get("fechaInicio"), desde);
    }

    public static Specification<Reservation> hasFechaFinHasta(LocalDate hasta) {
        return (root, query, cb) -> hasta == null ? null : cb.lessThanOrEqualTo(root.get("fechaFin"), hasta);
    }

    public static Specification<Reservation> hasTotalPagoDesde(BigDecimal desde) {
        return (root, query, cb) -> desde == null ? null : cb.greaterThanOrEqualTo(root.get("totalPago"), desde);
    }

    public static Specification<Reservation> hasTotalPagoHasta(BigDecimal hasta) {
        return (root, query, cb) -> hasta == null ? null : cb.lessThanOrEqualTo(root.get("totalPago"), hasta);
    }

    public static Specification<Reservation> hasCreatedAtDesde(Instant desde) {
        return (root, query, cb) -> desde == null ? null : cb.greaterThanOrEqualTo(root.get("createdAt"), desde);
    }

    public static Specification<Reservation> hasCreatedAtHasta(Instant hasta) {
        return (root, query, cb) -> hasta == null ? null : cb.lessThanOrEqualTo(root.get("createdAt"), hasta);
    }
}
