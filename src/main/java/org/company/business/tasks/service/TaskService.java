package org.company.business.tasks.service;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.company.business.tasks.entities.Task;
import org.company.business.employees.entities.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TaskService {

    @Inject
    TaskRepository taskRepository;

    @Transactional
    public PanacheQuery<Task> readListOfTasks(String userName, Employee.Role role) {

        if (role == Employee.Role.MANAGER) {
            return taskRepository.findAll();
        } else if (role == Employee.Role.TECHNICIAN) {
            return taskRepository.find("userName = ?1", userName);
        }
        return null;
    }

    @Transactional
    public Optional<Task> readUniqueTask (String userName, String summary) {
        return Optional.ofNullable(taskRepository.find("userName = ?1 AND summary = ?2", userName, summary)
                .firstResult());
    }

    @Transactional
    public List<Task> seeAllTasksOfUser(String userName) {
        return taskRepository.findByUser(userName);
    }

    @Transactional
    public Task createTask(String userName, String summary) {
        Task task = new Task();
        task.setCreatedDate(LocalDateTime.now());
        task.setUserName(userName);
        task.setSummary(summary);
        return taskRepository.createTask(task);
    }

    @Transactional
    public Task updateTaskUser(String userName, String summary, String newUserName) {
        Task task = taskRepository.findByUserNameAndSummary(userName, summary);
        task.setUserName(newUserName);
        task.setUpdatedDate(LocalDateTime.now());
        return taskRepository.updateTask(task);
    }

    @Transactional
    public Task updateTaskSummary(String username, String summary, String newSummary) {
        Task task = taskRepository.findByUserNameAndSummary(username, summary);
        task.setSummary(newSummary);
        task.setUpdatedDate(LocalDateTime.now());
        return taskRepository.updateTask(task);
    }

    @Transactional
    public void deleteTask(String userName, String summary) {
        taskRepository.deleteTask(userName,summary);

    }

}
