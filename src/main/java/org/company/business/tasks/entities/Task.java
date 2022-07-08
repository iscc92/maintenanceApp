package org.company.business.tasks.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "Tasks")
public class Task extends PanacheEntityBase {

    private String userName;

    @Id
    @Column(length=2500)
    @Size(min = 1, max = 2500)
    private String summary;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;


    public Task() {
    }

    public Task(String userName, String summary) {
        this.userName = userName;
        this.summary = summary;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String owner) {
        this.userName = owner;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
}
