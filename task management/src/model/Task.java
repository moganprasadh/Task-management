package model;

import java.sql.Timestamp;

public class Task {
    private int taskId;
    private int userId;
    private int projectId;
    private String taskName;
    private String description;
    private String status;
    private Timestamp deadline;
    private String priority;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Task() {}

    public Task(int taskId, int userId, int projectId, String taskName, String description,
                String status, Timestamp deadline, String priority, Timestamp createdAt, Timestamp updatedAt) {
        this.taskId = taskId;
        this.userId = userId;
        this.projectId = projectId;
        this.taskName = taskName;
        this.description = description;
        this.status = status;
        this.deadline = deadline;
        this.priority = priority;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    @Override
    public String toString() {
        return "Task ID: " + taskId +
               ", User ID: " + userId +
               ", Project ID: " + projectId +
               ", Name: " + taskName +
               ", Description: " + description +
               ", Status: " + status +
               ", Deadline: " + (deadline != null ? deadline.toString() : "N/A") +
               ", Priority: " + priority +
               ", Created At: " + (createdAt != null ? createdAt.toString() : "N/A") +
               ", Updated At: " + (updatedAt != null ? updatedAt.toString() : "N/A");
    }

}
