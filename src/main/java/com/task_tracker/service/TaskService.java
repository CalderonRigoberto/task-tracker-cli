package com.task_tracker.service;

import com.task_tracker.enums.TaskStatus;
import com.task_tracker.model.Task;

import java.io.IOException;
import java.util.List;

public interface TaskService {

    void add(Task task) throws IllegalArgumentException, IOException;

    void update(Task task) throws IllegalArgumentException, IOException;

    void delete(Integer id) throws IOException;

    List<Task> getAllTasks() throws IOException;

    void getAllTasksByStatus(TaskStatus status) throws IllegalArgumentException, IOException;

    void changeStatus(Integer id, TaskStatus status) throws IOException;
}
