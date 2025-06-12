package com.devsteve.hotel_manage_system.infra.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "room_images")
public class RoomImage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_images_id_gen")
    @SequenceGenerator(name = "room_images_id_gen", sequenceName = "room_images_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @NotNull
    @Column(name = "url_imagen", nullable = false, length = Integer.MAX_VALUE)
    private String urlImagen;

}