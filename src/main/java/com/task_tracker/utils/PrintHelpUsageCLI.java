package com.task_tracker.utils;

public class PrintHelpUsageCLI {
    public static void message() {
        System.out.println("""
            Task Tracker - A simple CLI task management tool
            
            Usage:
              task-tracker <command> [arguments]
            
            Commands:
              add <description>  Add a new task
              list              List all tasks
              list status       List all tasks by status
              mark-done <id>    Mark a task as completed
              mark-in-progress <id> Mark a task as in progress
              delete <id>       Remove a task
              help             Show this help message
            
            Examples:
              task-tracker add "Buy groceries"
              task-tracker list
              task-tracker mark-done 1
              task-tracker delete 2
            """);
    }
}
