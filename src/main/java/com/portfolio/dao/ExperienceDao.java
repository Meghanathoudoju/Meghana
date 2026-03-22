package com.portfolio.dao;

import com.portfolio.model.Experience;
import com.portfolio.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExperienceDao {

    public List<Experience> getAllExperienceByUserId(int userId) {
        List<Experience> list = new ArrayList<>();
        String sql = "SELECT * FROM experience WHERE user_id = ?";
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

    public boolean addExperience(Experience experience) {
        String sql = "INSERT INTO experience (user_id, company, role, duration, description) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, experience.getUserId());
            stmt.setString(2, experience.getCompany());
            stmt.setString(3, experience.getRole());
            stmt.setString(4, experience.getDuration());
            stmt.setString(5, experience.getDescription());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateExperience(Experience experience) {
        String sql = "UPDATE experience SET company=?, role=?, duration=?, description=? WHERE id=? AND user_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, experience.getCompany());
            stmt.setString(2, experience.getRole());
            stmt.setString(3, experience.getDuration());
            stmt.setString(4, experience.getDescription());
            stmt.setInt(5, experience.getId());
            stmt.setInt(6, experience.getUserId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteExperience(int id, int userId) {
        String sql = "DELETE FROM experience WHERE id=? AND user_id=?";
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

    public Experience getExperienceById(int id, int userId) {
        String sql = "SELECT * FROM experience WHERE id=? AND user_id=?";
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

    private Experience extractFromResultSet(ResultSet rs) throws SQLException {
        Experience exp = new Experience();
        exp.setId(rs.getInt("id"));
        exp.setUserId(rs.getInt("user_id"));
        exp.setCompany(rs.getString("company"));
        exp.setRole(rs.getString("role"));
        exp.setDuration(rs.getString("duration"));
        exp.setDescription(rs.getString("description"));
        return exp;
    }
}
