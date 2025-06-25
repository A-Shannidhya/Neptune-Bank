package com.asp.otpservice.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpMultiChannelRequestDTO {

    @Email(message = "Invalid email format")
    private String email;

    private String phone;
}
