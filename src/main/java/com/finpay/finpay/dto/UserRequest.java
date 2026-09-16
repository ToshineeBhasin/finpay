package com.finpay.finpay.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserRequest(

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Email is also required")
        @Email(message = "Invalid Email Format")
        String email,

        @NotBlank(message = "Phone number is required")
        @Pattern(regexp = "^[0-9]{10}$" , message = "Phone number must contain 10 digit.")
        String phone
) {
}
