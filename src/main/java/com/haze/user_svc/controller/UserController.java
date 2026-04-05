package com.haze.user_svc.controller;

import com.haze.user_svc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/follow-request")
    public void followRequest(@AuthenticationPrincipal Jwt jwt, String followedUsername){
        String loggedInUserName = jwt.getClaimAsString("preferred_username");
        userService.followRequest(loggedInUserName, followedUsername);
    }
}
