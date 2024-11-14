package dao;

import model.Task;
import utility.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAOImpl implements TaskDAO {

    @Override
    public void addTask(Task task) {
        String query = "INSERT INTO tasks (user_id, project_id, task_name, description, status, deadline, priority, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, task.getUserId());
            stmt.setInt(2, task.getProjectId());
            stmt.setString(3, task.getTaskName());
            stmt.setString(4, task.getDescription());
            stmt.setString(5, task.getStatus());
            stmt.setTimestamp(6, task.getDeadline());
            stmt.setString(7, task.getPriority());
            stmt.setTimestamp(8, task.getCreatedAt());
            stmt.setTimestamp(9, task.getUpdatedAt());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Task getTaskById(int taskId) {
        String query = "SELECT * FROM tasks WHERE task_id = ?";
        Task task = null;
        
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, taskId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                task = new Task(rs.getInt("task_id"), rs.getInt("user_id"), rs.getInt("project_id"),
                        rs.getString("task_name"), rs.getString("description"), rs.getString("status"),
                        rs.getTimestamp("deadline"), rs.getString("priority"), rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks";
        
        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Task task = new Task(rs.getInt("task_id"), rs.getInt("user_id"), rs.getInt("project_id"),
                        rs.getString("task_name"), rs.getString("description"), rs.getString("status"),
                        rs.getTimestamp("deadline"), rs.getString("priority"), rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at"));
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    @Override
    public void updateTask(Task task) {
        String query = "UPDATE tasks SET user_id = ?, project_id = ?, task_name = ?, description = ?, status = ?, deadline = ?, priority = ?, updated_at = ? WHERE task_id = ?";
        
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, task.getUserId());
            stmt.setInt(2, task.getProjectId());
            stmt.setString(3, task.getTaskName());
            stmt.setString(4, task.getDescription());
            stmt.setString(5, task.getStatus());
            stmt.setTimestamp(6, task.getDeadline());
            stmt.setString(7, task.getPriority());
            stmt.setTimestamp(8, task.getUpdatedAt());
            stmt.setInt(9, task.getTaskId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTask(int taskId) {
        String query = "DELETE FROM tasks WHERE task_id = ?";
        
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, taskId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
