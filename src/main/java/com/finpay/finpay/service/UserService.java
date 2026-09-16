package com.finpay.finpay.service;

import com.finpay.finpay.dto.UserRequest;
import com.finpay.finpay.dto.UserResponse;
import com.finpay.finpay.entity.User;
import com.finpay.finpay.exception.DuplicateEmailException;
import com.finpay.finpay.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //UserRequest
    //    ↓
    //duplicate email check
    //    ↓
    //Builder Pattern
    //    ↓
    //User Entity
    //    ↓
    //repository.save()
    //    ↓
    //Hibernate INSERT
    //    ↓
    //UserResponse
    public UserResponse createUser(UserRequest userRequest) {
        //business validation
        if (userRepository.existsByEmail(userRequest.email())) {
            throw new DuplicateEmailException("Email already Registered.");
        }


        //DTO -> Entity
        User user = User.builder()
                .name(userRequest.name())
                .email(userRequest.email())
                .phone(userRequest.phone())
                .build();

        //Insert into DB
        User savedUser = userRepository.save(user);

        //Entity -> Response DTO
        return new UserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getPhone(),
                savedUser.getEmail(),
                savedUser.getCreatedAt()
        );

    }
}
