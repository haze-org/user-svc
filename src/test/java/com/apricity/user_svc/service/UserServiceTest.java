package com.apricity.user_svc.service;


import com.apricity.user_svc.model.User;
import com.apricity.user_svc.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    private static final String username = "mike";
    private static final String email = "mike@example.com";
    private static final UUID id = UUID.randomUUID();

    @Test
    void shouldGetUserProfileIfExists(){

        Optional<User> user = Optional.ofNullable(User.builder()
                .id(id)
                .email(email)
                .username(username)
                .build());

        when(userRepository.findByUsername(username)).thenReturn(user);
        assertEquals(userService.getProfile(username).userId(), id);
        assertEquals(userService.getProfile(username).email(), email);
        assertEquals(userService.getProfile(username).username(), username);
    }

    @Test
    void shouldReturnNullIfProfileNotExist(){

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
        assertNull(userService.getProfile(username));
    }
}
