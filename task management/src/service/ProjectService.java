package service;

import dao.ProjectDAO;
import dao.ProjectDAOImpl;
import model.Project;

public class ProjectService {

    private ProjectDAO projectDAO;

    public ProjectService() {
        this.projectDAO = new ProjectDAOImpl();
    }

    public void addProject(Project project) {
        projectDAO.addProject(project);
    }

    public Project getProjectById(int projectId) {
        return projectDAO.getProjectById(projectId);
    }

    public void deleteProject(int projectId) {
        projectDAO.deleteProject(projectId);
    }
}
