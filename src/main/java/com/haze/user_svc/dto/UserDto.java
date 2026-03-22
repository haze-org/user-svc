package com.haze.user_svc.dto;

import java.util.UUID;

public record UserDto(
        UUID userId,
        String username,
        String firstName,
        String lastName,
        String email
) {
}
