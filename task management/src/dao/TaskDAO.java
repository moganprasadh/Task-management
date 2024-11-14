package dao;

import model.Task;
import java.util.List;

public interface TaskDAO {
    void addTask(Task task);
    Task getTaskById(int taskId);
    List<Task> getAllTasks();
    void updateTask(Task task);
    void deleteTask(int taskId);
}
