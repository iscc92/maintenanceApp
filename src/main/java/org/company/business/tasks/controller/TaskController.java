package org.company.business.tasks.controller;

import org.company.business.tasks.service.TaskService;
import org.company.business.employees.entities.Employee;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.Size;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tasks")
@ApplicationScoped
@Tag(name = "Tasks")
public class TaskController {

    @Inject
    TaskService taskService;

    @GET
    @Path("/getById")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get task entry")
    @PermitAll
    public Response readTask (@QueryParam("user") String user,
                              @QueryParam("user role") Employee.Role role) {
        return Response.ok(taskService.readListOfTasks(user, role)).build();
    }

    @GET
    @Path("/getAllTasksOfEmployee")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all task entries from specific user")
    @RolesAllowed({"MANAGER"})
    public Response seeAllTasksOfUser (@QueryParam("user") String userName) {
        return Response.ok(taskService.seeAllTasksOfUser(userName)).build();
    }

    @PUT
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create task entry with user and summary")
    @RolesAllowed({"TECHNICIAN"})
    public Response createTask (@QueryParam("user") @Size(min=1, max= 50) String user,
                                @QueryParam("summary") @Size(min=1, max= 2500) String summary) {
        return Response.ok(taskService.createTask(user, summary)).build();

    }

    @POST
    @Path("/updateTaskUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Update user of task entry")
    @RolesAllowed({"TECHNICIAN"})
    public Response updateTaskUser(@QueryParam("user") @Size(min=1, max= 50) String user,
                                   @QueryParam("summary") @Size(min=1, max= 2500) String summary,
                                   @QueryParam("new User") @Size(min=1, max= 2500) String newUser) {
        return Response.ok(taskService.updateTaskUser(user, summary, newUser)).build();

    }

    @POST
    @Path("/updateTaskSummary")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Update summary of task entry")
    @RolesAllowed({"TECHNICIAN"})
    public Response updateTaskSummary(@QueryParam("user") String user,
                                          @QueryParam("summary") @Size(min = 1, max = 250) String summary,
                                          @QueryParam("new summary") @Size(min = 1, max = 250) String newSummary) {
        return Response.ok(taskService.updateTaskSummary(user, summary, newSummary)).build();

    }

    @DELETE
    @Path("/deleteTaskById")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete task entry")
    @RolesAllowed({"MANAGER"})
    public Response deleteTask(@QueryParam("user") String user,
                               @QueryParam("summary") @Size(min = 1, max = 250) String summary) {
        taskService.deleteTask(user, summary);
        return Response.ok(200).build();

    }

}
