package dao;

import model.Project;
import utility.DBConnection;

import java.sql.*;

public class ProjectDAOImpl implements ProjectDAO {

    @Override
    public void addProject(Project project) {
        String query = "INSERT INTO projects (user_id, project_name, description, start_date, end_date) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, project.getUserId());
            stmt.setString(2, project.getProjectName());
            stmt.setString(3, project.getDescription());
            stmt.setTimestamp(4, project.getStartDate());
            stmt.setTimestamp(5, project.getEndDate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Project getProjectById(int projectId) {
        String query = "SELECT * FROM projects WHERE project_id = ?";
        Project project = null;
        
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, projectId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                project = new Project(rs.getInt("project_id"), rs.getInt("user_id"), rs.getString("project_name"),
                        rs.getString("description"), rs.getTimestamp("start_date"), rs.getTimestamp("end_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    @Override
    public void deleteProject(int projectId) {
        String query = "DELETE FROM projects WHERE project_id = ?";
        
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, projectId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
