package org.company.business.employees.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.company.business.tasks.entities.Task;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Employees")
public class Employee extends PanacheEntityBase {

    @Id
    private String employeeName;

    @Enumerated(EnumType.STRING)
    private Role employeeRole;

    @OneToMany
    private Set<Task> tasks;


    public enum Role {
        MANAGER,
        TECHNICIAN
    }

    public Employee(String employeeName, Role employeeRole) {
        this.employeeName = employeeName;
        this.employeeRole = employeeRole;
    }

    public Employee() {
    }


    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Role getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(Role employeeRole) {
        this.employeeRole = employeeRole;
    }
}