package com.asp.employeeservice.service;

/*
 * Copyright (c) 2025 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Neptune Bank
 * Author: Ayshi Shannidhya Panda
 * Created on: 22-06-2025
 */

import com.asp.employeeservice.dto.EmployeeDTO.EmployeeRequestDTO;
import com.asp.employeeservice.dto.EmployeeDTO.EmployeeResponseDTO;
import com.asp.employeeservice.mappers.EmployeeMapper;
import com.asp.employeeservice.models.Branch;
import com.asp.employeeservice.models.Employee;
import com.asp.employeeservice.repositories.BranchRepository;
import com.asp.employeeservice.repositories.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto) {
        Branch branch = getBranchById(dto.getBranchId());
        Employee employee = employeeMapper.toEntity(dto);
        employee.setBranch(branch);
        Employee saved = employeeRepository.save(employee);
        return employeeMapper.toDto(saved);
    }

    public EmployeeResponseDTO getEmployeeById(Long id) {
        Employee employee = getEmployeeEntityById(id);
        return employeeMapper.toDto(employee);
    }

    public List<EmployeeResponseDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO updatedDTO) {
        Employee existing = getEmployeeEntityById(id);
        Branch branch = getBranchById(updatedDTO.getBranchId());

        // Update only modifiable fields
        employeeMapper.updateEntityFromDto(updatedDTO, existing);
        existing.setBranch(branch);

        Employee saved = employeeRepository.save(existing);
        return employeeMapper.toDto(saved);
    }

    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }

    private Branch getBranchById(Long branchId) {
        return branchRepository.findById(branchId)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found with ID: " + branchId));
    }

    private Employee getEmployeeEntityById(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with ID: " + employeeId));
    }
}
