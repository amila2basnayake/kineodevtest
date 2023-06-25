package com.e3.test.service.dto;

import javax.validation.constraints.Pattern;

public class EmployeeDto {
    private Long id;
    @Pattern(regexp = "[A-Za-z '-]+", message = "Invalid first name chars")
    private String firstName;
    @Pattern(regexp = "[A-Za-z '-]+", message = "Invalid last name chars")
    private String lastName;
    private CompanyDto companyDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public CompanyDto getCompanyDto() {
        return companyDto;
    }

    public void setCompanyDto(CompanyDto companyDto) {
        this.companyDto = companyDto;
    }
}
