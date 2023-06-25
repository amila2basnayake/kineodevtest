package com.e3;

import com.e3.test.controller.EmployeeController;
import com.e3.test.model.Employee;
import com.e3.test.model.mapper.EmployeeMapper;
import com.e3.test.service.EmployeeService;
import com.e3.test.service.dto.EmployeeDto;
import com.e3.test.service.dto.EmployeeSearch;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
    @MockBean
    private EmployeeService employeeService;
    @MockBean
    private EmployeeMapper employeeMapper;
    @Autowired
    private MockMvc mockMvc;

    private static String asJsonString(Object obj) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    @DisplayName("Delete employee should return a valid status")
    public void testDeleteEmployee() throws Exception {
        ResultActions result = mockMvc.perform(delete("/employees/1"));
        result.andExpect(status().isOk());
    }

    @Test
    @DisplayName("Search employees should return a valid employee")
    public void testSearchEmployees() throws Exception {
        EmployeeSearch employeeSearch = new EmployeeSearch();
        employeeSearch.setFirstName("John");

        List<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee();
        employee1.setId(1L);
        employee1.setFirstName("John");
        employee1.setLastName("Doe");
        employees.add(employee1);
        when(employeeService.searchEmployees(any(EmployeeSearch.class))).thenReturn(employees);
        ResultActions result = mockMvc.perform(post("/employees/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(employeeSearch)));
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(employee1.getId()))
                .andExpect(jsonPath("$[0].firstName").value(employee1.getFirstName()))
                .andExpect(jsonPath("$[0].lastName").value(employee1.getLastName()));
    }

    //todo : need to continue

    @Test
    @DisplayName("Create Employee should return valid status")
    public void testCreateEmployee() throws Exception {
        // Create an example EmployeeDto
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("John");
        employeeDto.setLastName("Doe");

        // Create a mock Employee object
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());

        when(employeeService.createEmployee(any(EmployeeDto.class))).thenReturn(employee);

        ResultActions result = mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(employeeDto)));
        result.andExpect(status().isCreated());
    }
}
