package com.task_tracker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.task_tracker.enums.TaskStatus;

import java.time.LocalDate;

// Record class to mapping objects from JSON file
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record Task(
        Integer id,
        String description,
        TaskStatus status,
        LocalDate createdAt,
        LocalDate updatedAt
) {
    public Task {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
    }

    public Task() {
        this(null, null, null, null, null);
    }

    public Task(Integer id, String description) {
        this(id, description, null, null, null);
    }

    public Task(String description) {
        this(null,
                description,
                TaskStatus.todo,
                LocalDate.now(),
                LocalDate.now());
    }


    public Task withStatus(TaskStatus newStatus) {
        return new Task(
                this.id,
                this.description,
                newStatus,
                this.createdAt,
                LocalDate.now()
        );
    }


    public Task withIdAndDescription(Integer id, String description) {
        return new Task(
                id,
                description,
                this.status,
                this.createdAt,
                LocalDate.now()
        );
    }
}
