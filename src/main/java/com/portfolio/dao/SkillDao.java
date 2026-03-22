package com.portfolio.dao;

import com.portfolio.model.Skill;
import com.portfolio.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SkillDao {

    public List<Skill> getAllSkillsByUserId(int userId) {
        List<Skill> list = new ArrayList<>();
        String sql = "SELECT * FROM skills WHERE user_id = ?";
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

    public boolean addSkill(Skill skill) {
        String sql = "INSERT INTO skills (user_id, skill_name, skill_level) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, skill.getUserId());
            stmt.setString(2, skill.getSkillName());
            stmt.setString(3, skill.getSkillLevel());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateSkill(Skill skill) {
        String sql = "UPDATE skills SET skill_name=?, skill_level=? WHERE id=? AND user_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, skill.getSkillName());
            stmt.setString(2, skill.getSkillLevel());
            stmt.setInt(3, skill.getId());
            stmt.setInt(4, skill.getUserId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteSkill(int id, int userId) {
        String sql = "DELETE FROM skills WHERE id=? AND user_id=?";
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

    public Skill getSkillById(int id, int userId) {
        String sql = "SELECT * FROM skills WHERE id=? AND user_id=?";
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

    private Skill extractFromResultSet(ResultSet rs) throws SQLException {
        Skill skill = new Skill();
        skill.setId(rs.getInt("id"));
        skill.setUserId(rs.getInt("user_id"));
        skill.setSkillName(rs.getString("skill_name"));
        skill.setSkillLevel(rs.getString("skill_level"));
        return skill;
    }
}
