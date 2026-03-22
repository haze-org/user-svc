package com.haze.user_svc;

import com.haze.user_svc.dto.UserDto;
import com.haze.user_svc.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMqListener {

    private final UserService userService;

    @RabbitListener(queues = "profile-creation")
    public void listen(UserDto userDto){
        try{
            userService.createUserProfile(userDto);
        }catch (Exception e){
            log.error("user profile creation failed for userId: {}", userDto.userId());
            throw e;
        }
    }
}
