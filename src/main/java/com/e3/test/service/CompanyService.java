package com.e3.test.service;

import com.e3.test.exception.NotFoundException;
import com.e3.test.exception.RequestCanNotCompleteException;
import com.e3.test.model.Company;
import com.e3.test.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void removeCompany(Long companyId) {
        Company company = getCompany(companyId);
        if (!company.getEmployees().isEmpty()) {
            throw new RequestCanNotCompleteException("Company cannot be deleted as it still has employees.");
        }
        companyRepository.delete(company);
    }

    public Company getCompany(Long companyId) {
        return companyRepository.findById(companyId).orElseThrow(() -> new NotFoundException("Company not found with ID: " + companyId));

    }

}
