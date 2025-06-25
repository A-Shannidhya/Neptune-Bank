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

package com.asp.employeeservice.models;


import com.asp.employeeservice.dto.BranchDTO.BranchDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(updatable = false)
    private Long branchId;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<AccountDetails> accounts = new ArrayList<>();


    @NotBlank(message = "Branch Name cannot be blank")
    @Column(nullable = false)
    private String branchName;

    @NotBlank(message = "Branch Address cannot be blank")
    @Column(nullable = false)
    private String branchAddress;

    @NotBlank(message = "Branch City cannot be blank")
    @Column(nullable = false)
    private String branchCity;

    @NotBlank(message = "Branch State cannot be blank")
    @Column(nullable = false)
    private String branchState;

    @NotBlank(message = "Branch Zip cannot be blank")
    @Column(nullable = false)
    private String branchZip;

    @Column(updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static Branch fromDTO(BranchDTO branchDTO) {
        return Branch.builder()
                .branchId(branchDTO.getBranchId())
                .branchName(branchDTO.getBranchName())
                .branchAddress(branchDTO.getBranchAddress())
                .branchCity(branchDTO.getBranchCity())
                .branchState(branchDTO.getBranchState())
                .branchZip(branchDTO.getBranchZip())
                .build();
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

    public BranchDTO toDTO() {
        return BranchDTO.builder()
                .branchName(this.branchName)
                .branchAddress(this.branchAddress)
                .branchCity(this.branchCity)
                .branchState(this.branchState)
                .branchZip(this.branchZip)
                .build();
    }

}
