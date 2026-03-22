package com.portfolio.dao;

import com.portfolio.model.Education;
import com.portfolio.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EducationDao {

    public List<Education> getAllEducationByUserId(int userId) {
        List<Education> list = new ArrayList<>();
        String sql = "SELECT * FROM education WHERE user_id = ? ORDER BY passing_year DESC";
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

    public boolean addEducation(Education education) {
        String sql = "INSERT INTO education (user_id, degree, institution, passing_year, description) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, education.getUserId());
            stmt.setString(2, education.getDegree());
            stmt.setString(3, education.getInstitution());
            stmt.setInt(4, education.getPassingYear());
            stmt.setString(5, education.getDescription());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateEducation(Education education) {
        String sql = "UPDATE education SET degree=?, institution=?, passing_year=?, description=? WHERE id=? AND user_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, education.getDegree());
            stmt.setString(2, education.getInstitution());
            stmt.setInt(3, education.getPassingYear());
            stmt.setString(4, education.getDescription());
            stmt.setInt(5, education.getId());
            stmt.setInt(6, education.getUserId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteEducation(int id, int userId) {
        String sql = "DELETE FROM education WHERE id=? AND user_id=?";
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

    public Education getEducationById(int id, int userId) {
        String sql = "SELECT * FROM education WHERE id=? AND user_id=?";
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

    private Education extractFromResultSet(ResultSet rs) throws SQLException {
        Education edu = new Education();
        edu.setId(rs.getInt("id"));
        edu.setUserId(rs.getInt("user_id"));
        edu.setDegree(rs.getString("degree"));
        edu.setInstitution(rs.getString("institution"));
        edu.setPassingYear(rs.getInt("passing_year"));
        edu.setDescription(rs.getString("description"));
        return edu;
    }
}
