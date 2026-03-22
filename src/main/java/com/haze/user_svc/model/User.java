package com.haze.user_svc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Persistable;

import java.util.UUID;

@Entity
@Table(name = "profile")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Persistable<UUID> {
    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;

    @Override
    public @Nullable UUID getId() {
        return this.id;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
