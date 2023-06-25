package com.e3.test.model.mapper;

import com.e3.test.model.Company;
import com.e3.test.service.dto.CompanyDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
    private final ObjectMapper objectMapper;

    public CompanyMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Company valueOf(CompanyDto companyDto) {
        return objectMapper.convertValue(companyDto, Company.class);
    }

    public CompanyDto valueOf(Company company) {
        return objectMapper.convertValue(company, CompanyDto.class);
    }
}
