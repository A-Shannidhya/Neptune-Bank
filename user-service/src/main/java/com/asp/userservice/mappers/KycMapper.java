/*
 * Copyright (c) 2025 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Neptune Bank
 * Author: Ayshi Shannidhya Panda
 * Created on: 20-06-2025
 */
package com.asp.userservice.mappers;

import com.asp.userservice.DTO.KycDTO.KycRequestDTO;
import com.asp.userservice.models.Kyc;
import org.springframework.stereotype.Component;

@Component
public class KycMapper {

    public static Kyc toEntity(KycRequestDTO dto) {
        return Kyc.builder()
                .aadharNumber(dto.getAadharNumber())
                .panNumber(dto.getPanNumber())
                .voterId(dto.getVoterId())
                .passportNumber(dto.getPassportNumber())
                .drivingLicenseNumber(dto.getDrivingLicenseNumber())
                .build();
    }
}
