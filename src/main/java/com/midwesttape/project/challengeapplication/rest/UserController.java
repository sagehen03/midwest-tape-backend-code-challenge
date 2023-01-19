package com.midwesttape.project.challengeapplication.rest;

import com.midwesttape.project.challengeapplication.model.User;
import com.midwesttape.project.challengeapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/v1/users/{userId}")
    public User user(@PathVariable final Long userId) {
        return userService.user(userId);
    }

    @PatchMapping("/v1/users/{userId}")
    public User updateUserAttribute(@PathVariable Long userId, @RequestParam String attribute,
                                    @RequestParam String newVal){
        return userService.updateUserAttribute(userId, attribute, newVal);
    }

    @PutMapping("/v1/users")
    public User updateUser(@RequestBody User updatedUser){
        return userService.updateUser(updatedUser);
    }

}
