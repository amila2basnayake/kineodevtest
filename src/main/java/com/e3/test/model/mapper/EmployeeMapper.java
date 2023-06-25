package com.e3.test.model.mapper;

import com.e3.test.model.Employee;
import com.e3.test.service.dto.EmployeeDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    private final ObjectMapper objectMapper;

    public EmployeeMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Employee valueOf(EmployeeDto employeeDto) {
        return objectMapper.convertValue(employeeDto, Employee.class);
    }

    public EmployeeDto valueOf(Employee employee) {
        return objectMapper.convertValue(employee, EmployeeDto.class);
    }
}
