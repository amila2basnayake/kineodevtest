package com.e3.test.controller;

import com.e3.test.model.Employee;
import com.e3.test.model.mapper.EmployeeMapper;
import com.e3.test.service.EmployeeService;
import com.e3.test.service.dto.ApiError;
import com.e3.test.service.dto.EmployeeDto;
import com.e3.test.service.dto.EmployeeSearch;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping("/{employeeId}")
    ResponseEntity<?> getEmployee(@PathVariable Long employeeId) {

        Employee employee = employeeService.getEmployee(employeeId);
        return ResponseEntity.ok(employeeMapper.valueOf(employee));
    }

    @DeleteMapping("{employeeId}")
    public ResponseEntity<?> deleteEmployeeDetails(@PathVariable("employeeId") final Long employeeId) {
        employeeService.removeEmployee(employeeId);
        return new ResponseEntity<>("Employee with ID " + employeeId + " has been deleted successfully.", HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchEmployees(@RequestBody EmployeeSearch employeeSearch) {

        List<Employee> employees = employeeService.searchEmployees(employeeSearch);
        List<EmployeeDto> employeeDtos = employees.stream().map(employee -> employeeMapper.valueOf(employee)).collect(Collectors.toList());
        return ResponseEntity.ok(employees);
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        Employee createdEmployee = employeeService.createEmployee(employeeDto);
        if (createdEmployee != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Employee created successfully ..! ");
        } else {
            return new ResponseEntity(new ApiError(HttpStatus.NOT_IMPLEMENTED, "Employee not created"), HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto employeeDto) {
        Employee updatedEmployee = employeeService.updateEmployee(employeeId, employeeDto);
        return ResponseEntity.ok(updatedEmployee);
    }
}
