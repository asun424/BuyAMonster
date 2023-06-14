package com.ecommerceredux.userservice.controller;

import com.ecommerceredux.userservice.dto.*;
import com.ecommerceredux.userservice.model.User;
import com.ecommerceredux.userservice.service.UserService;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization){
        return userService.getUser(authorization);
    }

    @PostMapping("/verify/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/verify/authentication")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(userService.authenticate(request));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@RequestBody UserRequest updateRequest, @PathVariable("id") Long id){
        User updatedUser = userService.updateUser(updateRequest, id);
        return updatedUser;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserRequest userRequest){
        userService.createUser(userRequest);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }
}
