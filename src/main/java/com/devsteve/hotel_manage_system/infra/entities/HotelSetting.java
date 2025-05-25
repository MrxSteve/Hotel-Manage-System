package com.devsteve.hotel_manage_system.infra.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "hotel_settings")
public class HotelSetting {
    @Id
    @ColumnDefault("nextval('hotel_settings_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @Column(name = "nombre_hotel", length = 100)
    private String nombreHotel;

    @Size(max = 20)
    @Column(name = "telefono_contacto", length = 20)
    private String telefonoContacto;

    @Size(max = 100)
    @Column(name = "email_contacto", length = 100)
    private String emailContacto;

    @Column(name = "direccion", length = Integer.MAX_VALUE)
    private String direccion;

    @Column(name = "logo_url", length = Integer.MAX_VALUE)
    private String logoUrl;

    @Column(name = "config_json")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> configJson;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at")
    private Instant updatedAt;

}