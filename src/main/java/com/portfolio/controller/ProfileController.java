package com.portfolio.controller;

import com.portfolio.dao.ProfileDao;
import com.portfolio.model.Profile;
import com.portfolio.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet("/profile")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class ProfileController extends HttpServlet {
    private ProfileDao profileDao;
    private static final String UPLOAD_DIR = "uploads";

    @Override
    public void init() {
        profileDao = new ProfileDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("auth?action=login");
            return;
        }

        User user = (User) session.getAttribute("user");
        int userId = user.getId();

        Profile profile = new Profile();
        profile.setUserId(userId);
        profile.setFullName(request.getParameter("fullName"));
        profile.setTitle(request.getParameter("title"));
        profile.setAbout(request.getParameter("about"));
        profile.setPhone(request.getParameter("phone"));
        profile.setLinkedinUrl(request.getParameter("linkedinUrl"));
        profile.setGithubUrl(request.getParameter("githubUrl"));
        profile.setSelectedTemplate(request.getParameter("selectedTemplate"));

        // Handle File Upload
        Part filePart = request.getPart("profileImage");
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String applicationPath = request.getServletContext().getRealPath("");
            String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;

            File uploadFolder = new File(uploadFilePath);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }

            String savePath = uploadFilePath + File.separator + userId + "_" + fileName;
            filePart.write(savePath);
            profile.setProfileImagePath(UPLOAD_DIR + "/" + userId + "_" + fileName);
        } else {
            Profile existing = profileDao.getProfileByUserId(userId);
            if (existing != null) {
                profile.setProfileImagePath(existing.getProfileImagePath());
            }
        }

        profileDao.saveOrUpdateProfile(profile);
        response.sendRedirect("dashboard?success=profile_updated");
    }
}
