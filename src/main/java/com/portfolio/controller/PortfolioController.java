package com.portfolio.controller;

import com.portfolio.dao.*;
import com.portfolio.model.Profile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/view/*")
public class PortfolioController extends HttpServlet {
    private ProfileDao profileDao;
    private EducationDao educationDao;
    private ProjectDao projectDao;
    private SkillDao skillDao;
    private ExperienceDao experienceDao;
    private UserDao userDao;

    @Override
    public void init() {
        profileDao = new ProfileDao();
        educationDao = new EducationDao();
        projectDao = new ProjectDao();
        skillDao = new SkillDao();
        experienceDao = new ExperienceDao();
        userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.length() <= 1) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing User ID in URL.");
            return;
        }

        String userIdStr = pathInfo.substring(1);
        int userId;
        try {
            userId = Integer.parseInt(userIdStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid User ID.");
            return;
        }

        if (userDao.getUserById(userId) == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found.");
            return;
        }

        Profile profile = profileDao.getProfileByUserId(userId);
        if (profile == null) {
            profile = new Profile();
            profile.setSelectedTemplate("minimal");
            profile.setFullName("User " + userId);
        }

        request.setAttribute("profile", profile);
        request.setAttribute("educationList", educationDao.getAllEducationByUserId(userId));
        request.setAttribute("projectList", projectDao.getAllProjectsByUserId(userId));
        request.setAttribute("skillList", skillDao.getAllSkillsByUserId(userId));
        request.setAttribute("experienceList", experienceDao.getAllExperienceByUserId(userId));
        request.setAttribute("userId", userId);

        String template = profile.getSelectedTemplate();
        if (template == null || template.isEmpty()) {
            template = "minimal";
        }
        
        request.getRequestDispatcher("/templates/" + template + ".jsp").forward(request, response);
    }
}
