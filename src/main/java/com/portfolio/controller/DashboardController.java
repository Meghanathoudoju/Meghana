package com.portfolio.controller;

import com.portfolio.dao.*;
import com.portfolio.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {
    private ProfileDao profileDao;
    private EducationDao educationDao;
    private ProjectDao projectDao;
    private SkillDao skillDao;
    private ExperienceDao experienceDao;

    @Override
    public void init() {
        profileDao = new ProfileDao();
        educationDao = new EducationDao();
        projectDao = new ProjectDao();
        skillDao = new SkillDao();
        experienceDao = new ExperienceDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("auth?action=login");
            return;
        }

        User user = (User) session.getAttribute("user");
        int userId = user.getId();

        request.setAttribute("profile", profileDao.getProfileByUserId(userId));
        request.setAttribute("educationList", educationDao.getAllEducationByUserId(userId));
        request.setAttribute("projectList", projectDao.getAllProjectsByUserId(userId));
        request.setAttribute("skillList", skillDao.getAllSkillsByUserId(userId));
        request.setAttribute("experienceList", experienceDao.getAllExperienceByUserId(userId));

        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
}
