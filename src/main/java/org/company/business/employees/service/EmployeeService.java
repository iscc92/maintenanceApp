package org.company.business.employees.service;

import org.company.business.employees.entities.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class EmployeeService {
    @Inject
   EmployeeRepository employeeRepository;

    @Transactional
    public Employee createEmployee(String name, Employee.Role role) {
        return employeeRepository.createUser(name,role);
    }

}
