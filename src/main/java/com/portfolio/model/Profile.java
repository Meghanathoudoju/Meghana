package com.portfolio.model;

public class Profile {
    private int id;
    private int userId;
    private String fullName;
    private String title;
    private String about;
    private String phone;
    private String linkedinUrl;
    private String githubUrl;
    private String profileImagePath;
    private String selectedTemplate;

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAbout() { return about; }
    public void setAbout(String about) { this.about = about; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getLinkedinUrl() { return linkedinUrl; }
    public void setLinkedinUrl(String linkedinUrl) { this.linkedinUrl = linkedinUrl; }
    public String getGithubUrl() { return githubUrl; }
    public void setGithubUrl(String githubUrl) { this.githubUrl = githubUrl; }
    public String getProfileImagePath() { return profileImagePath; }
    public void setProfileImagePath(String profileImagePath) { this.profileImagePath = profileImagePath; }
    public String getSelectedTemplate() { return selectedTemplate == null ? "minimal" : selectedTemplate; }
    public void setSelectedTemplate(String selectedTemplate) { this.selectedTemplate = selectedTemplate; }
}
