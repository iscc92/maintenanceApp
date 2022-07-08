package org.company.business.tasks.service;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.company.business.tasks.entities.Task;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class TaskRepository implements PanacheRepository<Task> {

    public List<Task> findByUser(String employeeName) {
        return list("userName", employeeName);
    }

    public void deleteTask(String userName, String summary) {
        delete("username = ?1 AND summary = ?2", userName, summary);
    }

    public Task updateTask(Task task) {
        task.persistAndFlush();
        return task;
    }

    public Task createTask (Task task) {
        task.flush();
        return task;
    }

    public Task findByUserNameAndSummary(String userName, String summary) {
        return find("username = ?1 AND summary = ?2", userName, summary).firstResult();
    }

}
