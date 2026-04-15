package com.apricity.user_svc.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserDto(
        UUID userId,
        String username,
        String firstName,
        String lastName,
        String email,
        String profilePictureUrl
) {
}
