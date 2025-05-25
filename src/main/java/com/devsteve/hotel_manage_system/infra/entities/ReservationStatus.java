package com.devsteve.hotel_manage_system.infra.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "reservation_statuses")
public class ReservationStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_statuses_id_gen")
    @SequenceGenerator(name = "reservation_statuses_id_gen", sequenceName = "reservation_statuses_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "name", nullable = false, length = 50)
    private String name;

}