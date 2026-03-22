package com.portfolio.controller;

import com.portfolio.dao.SkillDao;
import com.portfolio.model.Skill;
import com.portfolio.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/skill")
public class SkillController extends HttpServlet {
    private SkillDao skillDao;

    @Override
    public void init() {
        skillDao = new SkillDao();
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
        String action = request.getParameter("action");

        Skill skill = new Skill();
        skill.setUserId(userId);
        if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
            skill.setId(Integer.parseInt(request.getParameter("id")));
        }
        
        if ("delete".equals(action)) {
            skillDao.deleteSkill(skill.getId(), userId);
        } else {
            skill.setSkillName(request.getParameter("skillName"));
            skill.setSkillLevel(request.getParameter("skillLevel"));

            if ("add".equals(action)) {
                skillDao.addSkill(skill);
            } else if ("update".equals(action)) {
                skillDao.updateSkill(skill);
            }
        }
        response.sendRedirect("dashboard?success=skill_updated");
    }
}
