package com.devsteve.hotel_manage_system.infra.adapters.output.jpa.specification;

import com.devsteve.hotel_manage_system.infra.entities.Room;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RoomSpecification {
    public static Specification<Room> hasNumeroHabitacion(Integer numero) {
        return (root, query, cb) ->
                numero == null ? null : cb.equal(root.get("numeroHabitacion"), numero);
    }

    public static Specification<Room> hasCapacidad(Integer capacidad) {
        return (root, query, cb) ->
                capacidad == null ? null : cb.equal(root.get("capacidad"), capacidad);
    }

    public static Specification<Room> hasStatusId(Integer statusId) {
        return (root, query, cb) ->
                statusId == null ? null : cb.equal(root.get("status").get("id"), statusId);
    }

    public static Specification<Room> hasRoomTypeId(Integer typeId) {
        return (root, query, cb) ->
                typeId == null ? null : cb.equal(root.get("roomType").get("id"), typeId);
    }

    public static Specification<Room> hasPrecioMenorOIgual(BigDecimal maxPrecio, LocalDate hoy) {
        return (root, query, cb) -> {
            if (maxPrecio == null || hoy == null) return null;

            Join<Object, Object> roomTypeJoin = root.join("roomType").join("precios");
            return cb.and(
                    cb.lessThanOrEqualTo(roomTypeJoin.get("precioPorNoche"), maxPrecio),
                    cb.lessThanOrEqualTo(roomTypeJoin.get("vigenteDesde"), hoy),
                    cb.or(
                            cb.isNull(roomTypeJoin.get("vigenteHasta")),
                            cb.greaterThanOrEqualTo(roomTypeJoin.get("vigenteHasta"), hoy)
                    )
            );
        };
    }
}
