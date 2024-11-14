package controller;

import model.Task;
import service.UserService;
import service.TaskService;
import service.ProjectService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class CombinedController {
    private TaskService taskService;
    private Scanner scanner;

    public CombinedController() {
        new UserService(); 
        this.taskService = new TaskService();
        new ProjectService(); 
        this.scanner = new Scanner(System.in);
    }

    public void startTaskManagement() {
        System.out.println("Welcome to the Task Management System!");
        
        boolean exit = false;
        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Task");
            System.out.println("2. Update Task");
            System.out.println("3. Delete Task");
            System.out.println("4. Set Task Deadline");
            System.out.println("5. Mark Task as Complete");
            System.out.println("6. View Current Tasks");
            System.out.println("7. Exit");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    updateTask();
                    break;
                case 3:
                    deleteTask();
                    break;
                case 4:
                    setTaskDeadline();
                    break;
                case 5:
                    markTaskAsComplete();
                    break;
                case 6:
                    viewCurrentTasks();
                    break;
                case 7:
                    exit = true;
                    System.out.println("Exiting Task Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addTask() {
        System.out.print("Enter Task Name: ");
        String taskName = scanner.nextLine();
        
        System.out.print("Enter Task Description: ");
        String description = scanner.nextLine();
        
        System.out.print("Enter Task Priority (Low, Medium, High): ");
        String priority = scanner.nextLine();

        System.out.print("Enter User ID for the task: ");
        int userId = scanner.nextInt();
        
        System.out.print("Enter Project ID for the task (or 0 if none): ");
        int projectId = scanner.nextInt();
        scanner.nextLine(); 
        
        Task task = new Task(0, userId, projectId, taskName, description, "Pending", null, priority, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        
        taskService.addTask(task);
        System.out.println("Task added successfully!");
    }

    private void updateTask() {
        System.out.print("Enter Task ID to update: ");
        int taskId = scanner.nextInt();
        scanner.nextLine(); 

        Task task = taskService.getTaskById(taskId);
        if (task == null) {
            System.out.println("Task not found.");
            return;
        }

        System.out.print("Enter new Task Name (leave blank to keep current): ");
        String taskName = scanner.nextLine();
        if (!taskName.isEmpty()) {
            task.setTaskName(taskName);
        }

        System.out.print("Enter new Task Description (leave blank to keep current): ");
        String description = scanner.nextLine();
        if (!description.isEmpty()) {
            task.setDescription(description);
        }

        System.out.print("Enter new Task Priority (Low, Medium, High, leave blank to keep current): ");
        String priority = scanner.nextLine();
        if (!priority.isEmpty()) {
            task.setPriority(priority);
        }

        task.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        taskService.updateTask(task);
        System.out.println("Task updated successfully!");
    }

    private void deleteTask() {
        System.out.print("Enter Task ID to delete: ");
        int taskId = scanner.nextInt();
        
        taskService.deleteTask(taskId);
        System.out.println("Task deleted successfully!");
    }

    private void setTaskDeadline() {
        System.out.print("Enter Task ID to set deadline: ");
        int taskId = scanner.nextInt();
        scanner.nextLine(); 

        Task task = taskService.getTaskById(taskId);
        if (task == null) {
            System.out.println("Task not found.");
            return;
        }

        System.out.print("Enter deadline (yyyy-MM-dd HH:mm:ss): ");
        String deadlineStr = scanner.nextLine();
        
        try {
            Timestamp deadline = Timestamp.valueOf(deadlineStr);
            task.setDeadline(deadline);
            task.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            taskService.updateTask(task);
            System.out.println("Task deadline set successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd HH:mm:ss.");
        }
    }

    private void markTaskAsComplete() {
        System.out.print("Enter Task ID to mark as complete: ");
        int taskId = scanner.nextInt();
        scanner.nextLine(); 

        Task task = taskService.getTaskById(taskId);
        if (task == null) {
            System.out.println("Task not found.");
            return;
        }

        task.setStatus("Completed");
        task.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        taskService.updateTask(task);
        System.out.println("Task marked as complete!");
    }
    
    public void viewCurrentTasks() {
        System.out.println("Viewing all current tasks:");
        List<Task> tasks = taskService.getAllTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }
}
