package com.e3.test.model.specification;

import com.e3.test.model.Company_;
import com.e3.test.model.Employee;
import com.e3.test.model.Employee_;
import com.e3.test.service.dto.EmployeeSearch;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public class EmployeeSpecifications {

    public static Specification<Employee> searchEmployees(EmployeeSearch employeeSearch) {
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);
            root.join(Employee_.COMPANY);
            Predicate predicate = criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get(Employee_.FIRST_NAME)), "%" + employeeSearch.getFirstName() + "%"),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get(Employee_.LAST_NAME)), "%" + employeeSearch.getLastName() + "%"),
                    criteriaBuilder.equal(criteriaBuilder.lower(root.get(Employee_.ID)), employeeSearch.getId())
            );

            if (employeeSearch.getCompanyId() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get(Employee_.COMPANY).get("id"), employeeSearch.getCompanyId()));
            }

            if (employeeSearch.getCompanyName() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get(Employee_.COMPANY).get(Company_.NAME)), "%" + employeeSearch.getCompanyId() + "%"));
            }
            return predicate;
        };
    }

}
