package com.asp.otpservice.controller;

import com.asp.otpservice.dto.OtpMultiChannelRequestDTO;
import com.asp.otpservice.dto.OtpVerifyRequestDTO;
import com.asp.otpservice.service.OtpService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/otp")
public class OtpController {

    private final OtpService otpService;

    @Autowired
    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }

    // 🔹 Send OTP to both email and phone with separate codes
    @PostMapping("/send")
    public ResponseEntity<String> sendOtpToBoth(@Valid @RequestBody OtpMultiChannelRequestDTO request) {
        StringBuilder status = new StringBuilder();

        if (request.getPhone() != null && !request.getPhone().isBlank()) {
            var smsResponse = otpService.sendSmsOtp(request.getPhone());
            status.append("SMS: ").append(smsResponse.getBody()).append("\n");
        }

        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            var emailResponse = otpService.sendEmailOtp(request.getEmail());
            status.append("Email: ").append(emailResponse.getBody()).append("\n");
        }

        if (status.length() == 0) {
            return ResponseEntity.badRequest().body("At least one of phone or email is required.");
        }

        return ResponseEntity.ok(status.toString().trim());
    }

    // 🔹 Unified OTP verification
    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@Valid @RequestBody OtpVerifyRequestDTO request) {
        return otpService.verifyOtp(request.getIdentifier(), request.getOtp());
    }
}
