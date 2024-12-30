package com.Bank.userms.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "UserMS", uniqueConstraints = @UniqueConstraint(columnNames = {"username", "email","phone_number"}))
@Getter
@Setter
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;
    @NotNull
    private String username;
    @NotNull
    private String password_hash;
    @NotNull
    private String email;
    private String phone_number;
    private boolean two_factor_enabled;
    private String kyc_status;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        this.createdAt = currentDateTime;
        this.updatedAt = currentDateTime;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
