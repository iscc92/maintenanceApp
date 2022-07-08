package org.company.business.tasks;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.company.business.tasks.entities.Task;
import org.company.business.tasks.service.TaskRepository;
import org.company.business.tasks.service.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;

@QuarkusTest
public class TaskServiceTest {

    @Inject
    TaskService taskService;

    @InjectMock
    TaskRepository taskRepository;


    @Test
    public void testCreatingTask() {
        //given
        String userName = "Test User TECH create";
        String summary = "This is a test task for user Test User TECH create";

        Task expected = new Task(userName, summary);
        Mockito.when(taskRepository.createTask(any(Task.class))).thenReturn(expected);

        //when
        Task actual = taskService.createTask(userName, summary);

        //then
        Assertions.assertEquals(actual.getUserName(), expected.getUserName());
        Assertions.assertEquals(actual.getSummary(), expected.getSummary());
    }


    @Test
    public void testUpdateTaskUser() {
        //given
        String userName = "Test User TECH";
        String userNameToUpdate = "Test User UPDATE";
        String summary = "This is a test task for user Test User TECH";

        Task expected = new Task(userNameToUpdate, summary);
        Task taskToUpdated = new Task(userName, summary);
        Mockito.when(taskRepository.updateTask(any(Task.class))).thenReturn(expected);
        Mockito.when(taskRepository.findByUserNameAndSummary(eq(userName), eq(summary))).thenReturn(taskToUpdated);

        //when
        Task actual = taskService.updateTaskUser(userName, summary, userNameToUpdate);

        //then
        Assertions.assertEquals(actual.getUserName(), expected.getUserName());
        Assertions.assertEquals(actual.getSummary(), expected.getSummary());

    }

    @Test
    public void testUpdateTaskSummary() {
        //given
        String userName = "Test User TECH";
        String summary = "This is a test task for user Test User TECH";
        String summaryToUpdate = "This is a test UPDATED task for user Test User TECH";

        Task expected = new Task(userName, summaryToUpdate);
        Task taskToUpdated = new Task(userName, summary);
        Mockito.when(taskRepository.updateTask(any(Task.class))).thenReturn(expected);
        Mockito.when(taskRepository.findByUserNameAndSummary(eq(userName), eq(summary))).thenReturn(taskToUpdated);

        //when
        Task actual = taskService.updateTaskSummary(userName, summary, summaryToUpdate);

        //then
        Assertions.assertEquals(actual.getUserName(), expected.getUserName());
        Assertions.assertEquals(actual.getSummary(), expected.getSummary());

    }

    @Test
    public void testDeleteTask() {
        //given
        String userName = "Test User TECH";
        String summary = "This is a DELETE test task for user Test User TECH";

        //when
        taskService.deleteTask(userName, summary);

        //then
        Mockito.verify(taskRepository, times(1)).deleteTask(userName, summary);

    }

}
