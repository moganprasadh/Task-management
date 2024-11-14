package service;

import dao.TaskDAO;
import dao.TaskDAOImpl;
import model.Task;

import java.sql.Timestamp;
import java.util.List;

public class TaskService {

    private TaskDAO taskDAO;

    public TaskService() {
        this.taskDAO = new TaskDAOImpl();
    }

    public void addTask(Task task) {
        taskDAO.addTask(task);
    }

    public Task getTaskById(int taskId) {
        return taskDAO.getTaskById(taskId);
    }

    public List<Task> getAllTasks() {
        return taskDAO.getAllTasks();
    }

    public void updateTask(Task task) {
        taskDAO.updateTask(task);
    }

    public void deleteTask(int taskId) {
        taskDAO.deleteTask(taskId);
    }

    public void setDeadline(int taskId, Timestamp deadline) {
        Task task = taskDAO.getTaskById(taskId);
        if (task != null) {
            task.setDeadline(deadline);
            taskDAO.updateTask(task);
        }
    }

    public void markTaskAsComplete(int taskId) {
        Task task = taskDAO.getTaskById(taskId);
        if (task != null) {
            task.setStatus("Completed");
            taskDAO.updateTask(task);
        }
    }
}
