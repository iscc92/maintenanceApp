package org.company.business.employees.controller;

import org.company.business.employees.entities.Employee;
import org.company.business.employees.service.EmployeeService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
@ApplicationScoped
@Tag(name = "Employee")
public class EmployeeController {

    @Inject
    EmployeeService employeeService;

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create user")
    @RolesAllowed({"MANAGER"})
    @Transactional
    public Response createUser(@QueryParam("user") String name, @QueryParam ("role") Employee.Role role) {
        return Response.ok(employeeService.createEmployee(name,role)).build();
    }
}
