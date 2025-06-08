package com.devsteve.hotel_manage_system.infra.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "oauth2_providers")
public class Oauth2Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "oauth2_providers_id_gen")
    @SequenceGenerator(name = "oauth2_providers_id_gen", sequenceName = "oauth2_providers_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Size(max = 50)
    @NotNull
    @Column(name = "provider", nullable = false, length = 50)
    private String provider;

    @Size(max = 255)
    @NotNull
    @Column(name = "provider_user_id", nullable = false)
    private String providerUserId;

}