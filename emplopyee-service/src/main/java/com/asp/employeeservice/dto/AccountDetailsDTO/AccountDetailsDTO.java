/*
 * Copyright (c) 2025 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Neptune Bank
 * Author: Ayshi Shannidhya Panda
 * Created on: 21-06-2025
 */

package com.asp.employeeservice.dto.AccountDetailsDTO;


import com.asp.employeeservice.dto.BranchDTO.BranchDTO;
import com.asp.employeeservice.enumeration.AccountType;
import com.asp.employeeservice.enumeration.ModeOfOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDetailsDTO {
    private Long accountId;
    private String accountNumber;
    private BigDecimal balance;
    private AccountType accountType;
    private ModeOfOperation modeOfOperation;

    private BranchDTO branch;
}
