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

package com.asp.employeeservice.mappers;

import com.asp.employeeservice.dto.EmployeeDTO.EmployeeRequestDTO;
import com.asp.employeeservice.dto.EmployeeDTO.EmployeeResponseDTO;
import com.asp.employeeservice.models.Employee;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee toEntity(EmployeeRequestDTO dto);

    EmployeeResponseDTO toDto(Employee employee);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(EmployeeRequestDTO dto, @MappingTarget Employee employee);
}
