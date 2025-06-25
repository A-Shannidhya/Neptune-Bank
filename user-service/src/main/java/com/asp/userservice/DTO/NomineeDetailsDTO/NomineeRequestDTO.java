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

package com.asp.userservice.DTO.NomineeDetailsDTO;


import com.asp.userservice.enumeration.Relationship;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NomineeRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Nominee name is required.")
    @Size(min = 8, message = "Nominee name is too short.")
    private String nomineeName;

    @NotNull(message = "Relationship is required.")
    private Relationship nomineeRelationship;

    @NotNull(message = "Date of birth is required.")
    private LocalDate nomineeDateOfBirth;

    @NotBlank(message = "Mobile number is required.")
    private String NomineeMobileNumber;

    @NotBlank(message = "Email is required.")
    private String NomineeEmail;

    @NotBlank(message = "Aadhar number is required.")
//    @Pattern(regexp = "^[2-9]{4}[0-9]{4}[0-9]{4}[0-9]{4}$", message = "Aadhar number is invalid.")
    @Pattern(regexp = "^[2-9]{1}[0-9]{11}$", message = "Aadhar number is invalid.")
    @Size(min = 12, message = "Aadhar number must be 12 digits.")
    private String nomineeAadhar;

    @NotBlank(message = "Aadhar number is required.")
    @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$", message = "Pan number is invalid.")
    @Size(min = 10, message = "Aadhar number must be 12 digits.")
    private String nomineePan;

    @NotBlank(message = "Nominee address is required.")
    private String nomineeAddress;
}
