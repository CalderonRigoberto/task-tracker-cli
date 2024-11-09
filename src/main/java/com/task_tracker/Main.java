package com.task_tracker;

import com.task_tracker.enums.TaskCommands;
import com.task_tracker.enums.TaskStatus;
import com.task_tracker.model.Task;
import com.task_tracker.service.impl.TaskServiceImpl;
import com.task_tracker.utils.PrintHelpUsageCLI;

import java.io.IOException;


public class Main {

    public static void main(String[] args) {

        try {
            TaskServiceImpl taskServiceImpl = new TaskServiceImpl();

            if (args.length == 0) {
                PrintHelpUsageCLI.message();
                return;
            }

            String argCommand = args[0].toLowerCase();
            TaskCommands command = TaskCommands.valueOf(argCommand);

            switch (command) {
                case add:
                    if (args.length < 2) {
                        System.out.println("It need a description to add");
                        break;
                    }

                    taskServiceImpl.add(
                            new Task(args[1])
                    );
                    break;
                case update:
                    if (args.length < 3) {
                        System.out.println("It need the description and an id to update a task");
                        break;
                    }

                    taskServiceImpl.update(
                            new Task(Integer.parseInt(args[1]), args[2])
                    );
                    break;
                case delete:
                    taskServiceImpl.delete(Integer.parseInt(args[1]));
                    break;
                case list:
                    if (args.length >= 2) {
                        var status = TaskStatus.valueOf(args[1]);
                        taskServiceImpl.getAllTasksByStatus(status);
                        break;
                    }

                    var tasks = taskServiceImpl.getAllTasks();
                    if (tasks.isEmpty()) {
                        System.out.println("No task has been added yet");
                        return;
                    }
                    tasks.forEach(System.out::println);
                    break;
                case mark_done:
                    if (args.length < 2) {
                        System.out.println("Not enough args, id it's missing");
                        break;
                    }

                    taskServiceImpl.changeStatus(Integer.parseInt(args[1]), TaskStatus.done);
                    break;
                case mark_in_progress:
                    if (args.length < 2) {
                        System.out.println("Not enough args, id it's missing");
                        break;
                    }

                    taskServiceImpl.changeStatus(Integer.parseInt(args[1]), TaskStatus.in_progress);
                    break;
                default:
                    System.out.println("This command doesn't exist");
                    break;
            }


        } catch (IOException e) {
            System.out.println("Something went wrong! Try again");
        } catch (IllegalAccessException ie) {
            System.out.println("An unknown error has happened");
        }
    }

}