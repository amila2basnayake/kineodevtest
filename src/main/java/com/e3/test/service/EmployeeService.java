package com.e3.test.service;


import com.e3.test.exception.NotFoundException;
import com.e3.test.model.Company;
import com.e3.test.model.Employee;
import com.e3.test.model.specification.EmployeeSpecifications;
import com.e3.test.repository.EmployeeRepository;
import com.e3.test.service.dto.EmployeeDto;
import com.e3.test.service.dto.EmployeeSearch;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final CompanyService companyService;

    public EmployeeService(EmployeeRepository employeeRepository,
                           CompanyService companyService) {
        this.employeeRepository = employeeRepository;
        this.companyService = companyService;
    }

    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee not found with ID: " + id));
    }

    public void removeEmployee(Long employeeId) {
        Employee employee = getEmployee(employeeId);
        employeeRepository.delete(employee);
    }

    public List<Employee> searchEmployees(EmployeeSearch employeeSearch) {

        Specification<Employee> specification = EmployeeSpecifications.searchEmployees(employeeSearch);
        Sort sort = new Sort(Sort.Direction.ASC, "firstName");
        List<Employee> employees = employeeRepository.findAll(specification, sort);
        return employeeRepository.findAll(specification);
    }

    public Employee createEmployee(EmployeeDto employeeDto) {
        Company company = companyService.getCompany(employeeDto.getCompanyDto().getId());
        Employee employee = new Employee();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setCompany(company);
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long employeeId, EmployeeDto employeeDto) {
        Employee employee = getEmployee(employeeId);
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        return employeeRepository.save(employee);
    }

}
