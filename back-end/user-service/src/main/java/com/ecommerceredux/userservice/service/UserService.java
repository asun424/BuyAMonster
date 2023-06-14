package com.ecommerceredux.userservice.service;

import com.ecommerceredux.userservice.config.JwtService;
import com.ecommerceredux.userservice.controller.UserController;
import com.ecommerceredux.userservice.dto.*;
import com.ecommerceredux.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ecommerceredux.userservice.model.User;

import java.util.HashMap;
import java.util.Map;

import static com.ecommerceredux.userservice.specification.UserSpecification.*;

@Service
@RequiredArgsConstructor
@Slf4j
@ComponentScan(basePackageClasses = UserController.class)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JwtService jwtService;

    public AuthenticationResponse register(RegisterRequest request){
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .build();

        userRepository.save(user);

        log.info("User {} has been saved", user.getUsername());

        Map<String, Object> extraClaims = new HashMap<String, Object>();
        extraClaims.put("username", user.getUsername());

        String jwtToken = jwtService.generateToken(extraClaims, user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){

        Specification<User> specification = matchUsername(request.getUsername());

        User user = userRepository.findOne(specification).orElse(null);

        if(user == null){
            throw new RuntimeException("User does not exist!");
        }

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
           throw new RuntimeException("You do not have access!");
        }

        Map<String, Object> extraClaims = new HashMap<String, Object>();
        extraClaims.put("username", user.getUsername());

        String jwtToken = jwtService.generateToken(extraClaims, user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public UserResponse getUser(String authorization){
        Long userId = Long.parseLong(jwtService.extractId(authorization.substring(7)));

        User foundUser = userRepository.findById(userId).orElse(null);

        UserResponse userResponse = UserResponse.builder()
                .id(foundUser.getId())
                .username(foundUser.getUsername())
                .email(foundUser.getEmail())
                .build();
        return userResponse;
    }
    public void createUser(UserRequest userRequest){
        User user = User.builder()
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .email(userRequest.getEmail())
                .build();

        userRepository.save(user);
        log.info("User {} has been saved", user.getUsername());
    }

    public User updateUser(UserRequest updateRequest, Long id){
         User userToUpdate = userRepository.findById(id).orElse(null);
         userToUpdate.setUsername(updateRequest.getUsername());
         userToUpdate.setPassword(updateRequest.getPassword());
         userToUpdate.setEmail(updateRequest.getEmail());
         userRepository.save(userToUpdate);
         log.info("info updated");
         return userToUpdate;
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
        log.info("User has been deleted");
    }

}
