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
@Table(name = "admin_actions_log")
public class AdminActionsLog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_actions_log_id_gen")
    @SequenceGenerator(name = "admin_actions_log_id_gen", sequenceName = "admin_actions_log_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "actor_id", nullable = false)
    private User actor;

    @Size(max = 100)
    @NotNull
    @Column(name = "target_entity", nullable = false, length = 100)
    private String targetEntity;

    @Size(max = 100)
    @Column(name = "target_id", length = 100)
    private String targetId;

    @Size(max = 50)
    @NotNull
    @Column(name = "action", nullable = false, length = 50)
    private String action;

    @Column(name = "details", length = Integer.MAX_VALUE)
    private String details;

    @Size(max = 50)
    @Column(name = "ip_address", length = 50)
    private String ipAddress;

    @Column(name = "user_agent", length = Integer.MAX_VALUE)
    private String userAgent;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

}