package org.company.business.employees.service;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.company.business.employees.entities.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class EmployeeRepository implements PanacheRepository<Employee> {

    @Transactional
    public Employee createUser(String userName, Employee.Role role) {
        Employee employee = new Employee();
        employee.setEmployeeName(userName);
        employee.setEmployeeRole(role);
        employee.persistAndFlush();
        return employee;
    }

    @Transactional
    public void deleteEmployee(String userName) {
        delete("userName", userName);

    }

}
