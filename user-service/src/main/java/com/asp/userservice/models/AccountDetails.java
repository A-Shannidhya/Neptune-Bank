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
package com.asp.userservice.models;

import com.asp.userservice.DTO.AccountDetailsDTO.AccountDetailsDTO;
import com.asp.userservice.enumeration.AccountType;
import com.asp.userservice.enumeration.ModeOfOperation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AccountDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long accountId;


    @NotBlank(message = "Account Number cannot be blank")
    @Column(nullable = false, unique = true)
    private String accountNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @Builder.Default
    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    @NotNull(message = "Account Type cannot be blank")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType accountType;

    @NotNull(message = "Account Mode Of Operation cannot be blank")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ModeOfOperation modeOfOperation;


    @Column(updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static AccountDetails fromDTO(AccountDetailsDTO accountDetails) {
        AccountDetails accountDetails1 = AccountDetails.builder()
                .accountId(accountDetails.getAccountId())
                .accountNumber(accountDetails.getAccountNumber())
                .balance(accountDetails.getBalance())
                .accountType(accountDetails.getAccountType())
                .modeOfOperation(accountDetails.getModeOfOperation())
                .branch(Branch.fromDTO(accountDetails.getBranch()))
                .build();

        if (accountDetails.getBranch() != null) {
            accountDetails1.setBranch(Branch.fromDTO(accountDetails.getBranch()));
        }

        return accountDetails1;

    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public AccountDetailsDTO toDTO() {
        return AccountDetailsDTO.builder()
                .accountNumber(this.accountNumber)
                .balance(this.balance)
                .accountType(this.accountType)
                .modeOfOperation(this.modeOfOperation)
                .branch(this.branch != null ? this.branch.toDTO() : null)
                .build();
    }


}

