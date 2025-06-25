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


import com.asp.userservice.DTO.UsersDTO.UsersRequestDTO;
import com.asp.userservice.models.Users;
import org.springframework.stereotype.Component;

@Component
public class UsersMapper {
    public static Users toEntity(UsersRequestDTO dto) {
        return Users.builder()
                .firstName(dto.getFirstName())
                .middleName(dto.getMiddleName())
                .lastName(dto.getLastName())
                .dateOfBirth(dto.getDateOfBirth())
                .gender(dto.getGender())
                .fatherName(dto.getFatherName())
                .motherName(dto.getMotherName())
                .maritalStatus(dto.getMaritalStatus())
                .spouseName(dto.getSpouseName())
                .occupation(dto.getOccupation())
                .salary(dto.getSalary())
                .citizen(dto.getCitizen())
                .category(dto.getCategory())
                .religion(dto.getReligion())
                .contactDetails(ContactDetailsMapper.toEntity(dto.getContactDetails()))
                .nominee(NomineeMapper.toEntity(dto.getNominee()))
                .kycId(KycMapper.toEntity(dto.getKyc()))
                .build();
    }
}
