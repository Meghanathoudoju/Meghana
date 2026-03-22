<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- PROFILE SECTION -->
<section id="profile-section" class="content-section">
    <div class="card">
        <div class="card-header">
            <h3>Personal Profile</h3>
        </div>
        <form action="profile" method="POST" enctype="multipart/form-data">
            <div class="form-group">
                <label>Full Name</label>
                <input type="text" name="fullName" value="<%= profile != null && profile.getFullName() != null ? profile.getFullName() : "" %>" required>
            </div>
            <div class="form-group">
                <label>Professional Title</label>
                <input type="text" name="title" value="<%= profile != null && profile.getTitle() != null ? profile.getTitle() : "" %>">
            </div>
            <div class="form-group">
                <label>About You</label>
                <textarea name="about" rows="4"><%= profile != null && profile.getAbout() != null ? profile.getAbout() : "" %></textarea>
            </div>
            <div style="display:flex; gap:15px;">
                <div class="form-group" style="flex:1;">
                    <label>Phone</label>
                    <input type="text" name="phone" value="<%= profile != null && profile.getPhone() != null ? profile.getPhone() : "" %>">
                </div>
                <div class="form-group" style="flex:1;">
                    <label>Portfolio Template</label>
                    <select name="selectedTemplate">
                        <option value="minimal" <%= profile != null && "minimal".equals(profile.getSelectedTemplate()) ? "selected" : "" %>>Minimal</option>
                        <option value="modern" <%= profile != null && "modern".equals(profile.getSelectedTemplate()) ? "selected" : "" %>>Modern</option>
                        <option value="professional" <%= profile != null && "professional".equals(profile.getSelectedTemplate()) ? "selected" : "" %>>Professional</option>
                    </select>
                </div>
            </div>
            <div style="display:flex; gap:15px;">
                <div class="form-group" style="flex:1;">
                    <label>LinkedIn URL</label>
                    <input type="text" name="linkedinUrl" value="<%= profile != null && profile.getLinkedinUrl() != null ? profile.getLinkedinUrl() : "" %>">
                </div>
                <div class="form-group" style="flex:1;">
                    <label>GitHub URL</label>
                    <input type="text" name="githubUrl" value="<%= profile != null && profile.getGithubUrl() != null ? profile.getGithubUrl() : "" %>">
                </div>
            </div>
            <div class="form-group">
                <label>Profile Image</label>
                <% if (profile != null && profile.getProfileImagePath() != null) { %>
                    <p style="font-size:12px; margin-bottom:5px;">Current image: <%= profile.getProfileImagePath() %></p>
                <% } %>
                <input type="file" name="profileImage" accept="image/png, image/jpeg">
            </div>
            <button type="submit" class="btn btn-primary">Save Profile</button>
        </form>
    </div>
</section>
