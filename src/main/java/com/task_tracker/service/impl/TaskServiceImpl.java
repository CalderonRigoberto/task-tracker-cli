package com.task_tracker.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.task_tracker.enums.TaskStatus;
import com.task_tracker.model.Task;
import com.task_tracker.service.TaskService;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class TaskServiceImpl implements TaskService {
    private final static String fileName = "task_tracker.json";
    private final static String desktopLocation = System.getProperty("user.home") + File.separator;
    private final static String fileLocation = desktopLocation + fileName;
    private final ObjectMapper objectMapper;
    private final File file;


    public TaskServiceImpl() throws IOException, IllegalAccessException {
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        this.file = new File(fileLocation);
        createFilStoreage();

    }


    @Override
    public void add(Task task) throws IllegalArgumentException, IOException {
        var allTasks = getAllTasks();
        var lastTask = allTasks.isEmpty() ? null : allTasks.stream().max(Comparator.comparing(Task::id)).get();


        var id = lastTask != null ? lastTask.id() : 0;
        task = new Task(id + 1, task.description(), task.status(), task.createdAt(), null);
        allTasks.add(task);

        writeValueAtTasks(allTasks);
    }

    @Override
    public void update(Task taskById) throws IllegalArgumentException, IOException {
        var allTasks = getAllTasks();
        var updatedTasks = allTasks.stream()
                .map(task -> task.id().intValue() == taskById.id().intValue() ? task.withIdAndDescription(taskById.id(), taskById.description()) : task)
                .collect(Collectors.toList());

        writeValueAtTasks(updatedTasks);
    }

    @Override
    public void delete(Integer id) throws IOException {
        boolean found = false;
        var allTasks = getAllTasks();
        for (Iterator<Task> iterator = allTasks.iterator(); iterator.hasNext(); ) {
            Integer removableById = iterator.next().id();
            if (removableById.intValue() != id.intValue()) {
                continue;
            }

            iterator.remove();
            found = true;
        }

        if(found) {
            System.out.println("All coincidences has been removed");
        }

        writeValueAtTasks(allTasks);
    }

    @Override
    public List<Task> getAllTasks() throws IOException {
        var tasksMap = objectMapper.readValue(file, new TypeReference<Map<String, Object>>() {
        });
        if (!tasksMap.containsKey("tasks")) return null;

        return objectMapper.readValue((String) tasksMap.get("tasks"), new TypeReference<List<Task>>() {
        });
    }

    @Override
    public void getAllTasksByStatus(TaskStatus status) throws IllegalArgumentException, IOException {
       var filteredTasks =  getAllTasks()
                .stream()
                .filter(t -> t.status().equals(status))
                .toList();

       if(filteredTasks.isEmpty()){
           System.out.println("No task has been found with " +status+ " status");
           return;
       }
       filteredTasks.forEach(System.out::println);
    }

    @Override
    public void changeStatus(Integer id, TaskStatus status) throws IOException {
        var allTasks = getAllTasks();

        var updatedTasks = allTasks.stream()
                .map(task -> task.id().intValue() == id.intValue() ? task.withStatus(status): task)
                .collect(Collectors.toList());

        writeValueAtTasks(updatedTasks);
    }

    private void writeValueAtTasks(List<Task> alltTasks) throws IOException {
        var node = getNode();
        node.put("tasks", objectMapper.writeValueAsString(alltTasks));
        objectMapper.writeValue(file, node);
    }

    private ObjectNode getNode() throws IOException {
        return (ObjectNode) objectMapper.readTree(file);
    }

    private boolean validMainNode(File file) throws IOException {
        return objectMapper.readTree(file).findValue("tasks") != null;
    }

    private void createMainNode(File file) throws IOException {
        ObjectNode node = objectMapper.createObjectNode();
        node.put("tasks", "[]");
        objectMapper.writeValue(file, node);
    }

    private void createFilStoreage() throws IOException, IllegalAccessException {
        try {
            if (fileLocation.contains("null")) {
                throw new IllegalArgumentException("File location contains null, it won't be possible detect if exists or create the file");
            }

            if (!file.exists()) file.createNewFile();


            if (!file.canRead() || !file.canWrite()) {
                throw new IllegalAccessException("You do not have permissions to read or write this file!");
            }

            if (validMainNode(file)) {
                return;
            }

            createMainNode(file);
        } catch (IOException ioException) {
            throw new IOException();
        }

    }
}
