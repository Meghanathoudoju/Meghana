package com.portfolio.dao;

import com.portfolio.model.Profile;
import com.portfolio.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileDao {
    public Profile getProfileByUserId(int userId) {
        String sql = "SELECT * FROM profile WHERE user_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractProfileFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean saveOrUpdateProfile(Profile profile) {
        Profile existingInfo = getProfileByUserId(profile.getUserId());
        String sql;
        if (existingInfo == null) {
            sql = "INSERT INTO profile (full_name, title, about, phone, linkedin_url, github_url, profile_image_path, selected_template, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        } else {
            sql = "UPDATE profile SET full_name=?, title=?, about=?, phone=?, linkedin_url=?, github_url=?, profile_image_path=?, selected_template=? WHERE user_id=?";
        }

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, profile.getFullName());
            stmt.setString(2, profile.getTitle());
            stmt.setString(3, profile.getAbout());
            stmt.setString(4, profile.getPhone());
            stmt.setString(5, profile.getLinkedinUrl());
            stmt.setString(6, profile.getGithubUrl());
            stmt.setString(7, profile.getProfileImagePath());
            stmt.setString(8, profile.getSelectedTemplate());
            stmt.setInt(9, profile.getUserId());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Profile extractProfileFromResultSet(ResultSet rs) throws SQLException {
        Profile profile = new Profile();
        profile.setId(rs.getInt("id"));
        profile.setUserId(rs.getInt("user_id"));
        profile.setFullName(rs.getString("full_name"));
        profile.setTitle(rs.getString("title"));
        profile.setAbout(rs.getString("about"));
        profile.setPhone(rs.getString("phone"));
        profile.setLinkedinUrl(rs.getString("linkedin_url"));
        profile.setGithubUrl(rs.getString("github_url"));
        profile.setProfileImagePath(rs.getString("profile_image_path"));
        profile.setSelectedTemplate(rs.getString("selected_template"));
        return profile;
    }
}
