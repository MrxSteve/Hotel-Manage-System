package com.devsteve.hotel_manage_system.infra.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "login_audit")
public class LoginAudit {
    @Id
    @ColumnDefault("nextval('login_audit_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @Column(name = "user_agent", nullable = false, length = Integer.MAX_VALUE)
    private String userAgent;

    @Size(max = 50)
    @Column(name = "device", length = 50)
    private String device;

    @Size(max = 50)
    @Column(name = "browser", length = 50)
    private String browser;

    @Size(max = 10)
    @Column(name = "country", length = 10)
    private String country;

    @Size(max = 10)
    @Column(name = "region", length = 10)
    private String region;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

}