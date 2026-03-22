package com.portfolio.dao;

import com.portfolio.model.Project;
import com.portfolio.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDao {

    public List<Project> getAllProjectsByUserId(int userId) {
        List<Project> list = new ArrayList<>();
        String sql = "SELECT * FROM projects WHERE user_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(extractFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addProject(Project project) {
        String sql = "INSERT INTO projects (user_id, title, description, technologies, github_link) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, project.getUserId());
            stmt.setString(2, project.getTitle());
            stmt.setString(3, project.getDescription());
            stmt.setString(4, project.getTechnologies());
            stmt.setString(5, project.getGithubLink());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateProject(Project project) {
        String sql = "UPDATE projects SET title=?, description=?, technologies=?, github_link=? WHERE id=? AND user_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, project.getTitle());
            stmt.setString(2, project.getDescription());
            stmt.setString(3, project.getTechnologies());
            stmt.setString(4, project.getGithubLink());
            stmt.setInt(5, project.getId());
            stmt.setInt(6, project.getUserId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteProject(int id, int userId) {
        String sql = "DELETE FROM projects WHERE id=? AND user_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setInt(2, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Project getProjectById(int id, int userId) {
        String sql = "SELECT * FROM projects WHERE id=? AND user_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setInt(2, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Project extractFromResultSet(ResultSet rs) throws SQLException {
        Project proj = new Project();
        proj.setId(rs.getInt("id"));
        proj.setUserId(rs.getInt("user_id"));
        proj.setTitle(rs.getString("title"));
        proj.setDescription(rs.getString("description"));
        proj.setTechnologies(rs.getString("technologies"));
        proj.setGithubLink(rs.getString("github_link"));
        return proj;
    }
}
