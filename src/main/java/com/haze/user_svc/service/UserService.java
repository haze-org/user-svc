package com.haze.user_svc.service;

import com.haze.user_svc.dto.UserDto;
import com.haze.user_svc.model.User;
import com.haze.user_svc.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void createUserProfile(UserDto userDto) {

        Optional<User> userProfile = userRepository.findById(userDto.userId());

        if (userProfile.isEmpty()){
            User createUser = User.builder()
                    .id(userDto.userId())
                    .username(userDto.username())
                    .firstName(userDto.firstName())
                    .lastName(userDto.lastName())
                    .email(userDto.email())
                    .build();
            userRepository.save(createUser);
            log.debug("user profile created for userId: {}", userDto.userId());
        }else{
            log.debug("user profile already exists for userId: {}", userDto.userId());
        }
    }

    public void followRequest(String followerUsername, String followedUsername) {

        Optional<UUID> followerId = userRepository.findIdByUsername(followerUsername);
        Optional<UUID> followedId = userRepository.findIdByUsername(followedUsername);

        if(followedId.isPresent() && followerId.isPresent()) {
            log.info("follower and followed ID are present {}, {}", followerId.get(), followedId.get());
            userRepository.followRequest(followerId.get(), followedId.get());
            log.debug("User: {} has followed user {}", followerId.get(), followedId.get());
        }
        else{
            log.debug("follower or followed ID is not present");
        }
    }

    public UserDto getProfile(String username) {

        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()){
            User userProfile = user.get();
            return UserDto.builder()
                    .userId(userProfile.getId())
                    .email(userProfile.getEmail())
                    .firstName(userProfile.getFirstName())
                    .lastName(userProfile.getLastName())
                    .username(userProfile.getUsername())
                    .profilePictureUrl(userProfile.getProfilePictureUrl())
                    .build();
        }else{
            log.debug("User not found with username {}", username);
            return null;
        }
    }
}
