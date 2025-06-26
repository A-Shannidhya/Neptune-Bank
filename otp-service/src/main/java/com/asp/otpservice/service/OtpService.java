package com.asp.otpservice.service;

/*
 * Copyright (c) 2025 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Neptune Bank
 * Author: Ayshi Shannidhya Panda
 * Created on: 24-06-2025
 */

import com.asp.otpservice.entity.Otp;
import com.asp.otpservice.otp.OtpGenerator;
import com.asp.otpservice.repositories.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OtpService {

    private final JavaMailSender mailSender;
    private final OtpRepository otpRepository;

    @Value("bd83eaff-0466-41ed-8ec8-a3234b09f36e")
    private String apiKey;

    @Value("68594bd7a5fdde60955c7fdc")
    private String deviceId;

    private static final long OTP_EXPIRY_SECONDS = 300;

    @Autowired
    public OtpService(JavaMailSender mailSender, OtpRepository otpRepository) {
        this.mailSender = mailSender;
        this.otpRepository = otpRepository;
    }


    public ResponseEntity<String> sendEmailOtp(String toEmail) {
        String otp = OtpGenerator.generateOtp(6);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your Neptune Bank OTP");
        message.setText("Dear user,\n\nYour OTP is: " + otp + "\nIt will expire in 5 minutes.\n\nRegards,\nNeptune Bank");

        try {
            mailSender.send(message);
            otpRepository.deleteByPhoneNumber(toEmail); // Cleanup in case email = phone style key
            otpRepository.save(new Otp(toEmail, otp, Instant.now().plusSeconds(OTP_EXPIRY_SECONDS)));
            return ResponseEntity.ok("OTP sent to " + toEmail);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send OTP email.");
        }
    }


    public ResponseEntity<String> sendSmsOtp(String phoneNumber) {
        String otp = OtpGenerator.generateOtp(6);
        String message = "Dear Customer, your Neptune Bank OTP is: " + otp + ". Please do not share it with anyone. - Neptune Bank";

        String url = "https://api.textbee.dev/api/v1/gateway/devices/" + deviceId + "/send-sms";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = Map.of(
                "recipients", List.of(phoneNumber),
                "message", message
        );

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url, HttpMethod.POST, requestEntity, String.class
            );

            otpRepository.deleteByPhoneNumber(phoneNumber); // Clean up old OTPs
            otpRepository.save(new Otp(phoneNumber, otp, Instant.now().plusSeconds(OTP_EXPIRY_SECONDS)));

            return ResponseEntity.ok("OTP sent successfully.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send OTP.");
        }
    }


    public ResponseEntity<String> verifyOtp(String identifier, String inputOtp) {
        Optional<Otp> otpOptional = otpRepository.findByPhoneNumber(identifier);

        if (otpOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No OTP sent to this identifier.");
        }

        Otp otpEntity = otpOptional.get();

        if (Instant.now().isAfter(otpEntity.getExpiryTime())) {
            otpRepository.delete(otpEntity);
            return ResponseEntity.status(HttpStatus.GONE).body("OTP has expired.");
        }

        if (!otpEntity.getOtp().equals(inputOtp)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP.");
        }

        otpRepository.delete(otpEntity);
        return ResponseEntity.ok("OTP verified successfully.");
    }
}
