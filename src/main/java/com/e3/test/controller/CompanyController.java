package com.e3.test.controller;

import com.e3.test.model.Company;
import com.e3.test.model.mapper.CompanyMapper;
import com.e3.test.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    public CompanyController(CompanyService companyService, CompanyMapper companyMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }

    @GetMapping("/{companyId}")
    ResponseEntity<?> getCompany(@PathVariable Long companyId) {
        Company company = companyService.getCompany(companyId);
        return ResponseEntity.ok(companyMapper.valueOf(company));
    }

    @DeleteMapping("{companyId}")
    public ResponseEntity<?> deleteCompany(@PathVariable("companyId") final Long companyId) {
        companyService.removeCompany(companyId);
        return ResponseEntity.ok("Company deleted successfully.");
    }

}
