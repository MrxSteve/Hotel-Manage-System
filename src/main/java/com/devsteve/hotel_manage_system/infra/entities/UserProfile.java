package com.devsteve.hotel_manage_system.infra.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Size(max = 150)
    @Column(name = "nombre_completo", length = 150)
    private String nombreCompleto;

    @Size(max = 20)
    @Column(name = "dui", length = 20)
    private String dui;

    @Size(max = 20)
    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "direccion", columnDefinition = "TEXT")
    private String direccion;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Size(max = 10)
    @Column(name = "genero", length = 10)
    private String genero;

    @Size(max = 50)
    @Column(name = "nacionalidad", length = 50)
    private String nacionalidad;
}
