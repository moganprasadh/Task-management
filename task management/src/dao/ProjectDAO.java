package dao;

import model.Project;

public interface ProjectDAO {
    void addProject(Project project);
    Project getProjectById(int projectId);
    void deleteProject(int projectId);
}
