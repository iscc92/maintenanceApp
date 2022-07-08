package org.company.business.employees;

import io.quarkus.test.junit.QuarkusTest;
import org.company.business.employees.entities.Employee;
import org.company.business.employees.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
public class EmployeeServiceTest {

    @Inject
    EmployeeService employeeService;

    @Test
    public void testCreatingUser() {
        //given
        String name = "Tech 73";
        Employee.Role role = Employee.Role.TECHNICIAN;

        Employee expected = new Employee("Tech 73", Employee.Role.TECHNICIAN);

        //when
        Employee actual = employeeService.createEmployee(name, role);

        //then
        Assertions.assertEquals(actual.getEmployeeName(), expected.getEmployeeName());
        Assertions.assertEquals(actual.getEmployeeRole(), expected.getEmployeeRole());

    }
}
