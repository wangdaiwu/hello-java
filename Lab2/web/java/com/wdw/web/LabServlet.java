package com.wdw.web;

import com.google.gson.Gson;
import com.wdw.pojo.Person;
import com.wdw.pojo.User;
import com.wdw.service.LabService;
import com.wdw.utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LabServlet extends BaseServlet {
    private LabService labService = new LabService();
    private Gson gson = new Gson();

    protected void hasUsername(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Object> map = new HashMap<>();
        try {
            boolean hasUsername = labService.hasUsername(req.getParameter("username"));
            map.put("hasUsername", hasUsername);
            resp.getWriter().write(gson.toJson(map));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void hasName(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Object> map = new HashMap<>();
        try {
            boolean hasName = labService.hasName(req.getParameter("name"));
            map.put("hasName", hasName);
            resp.getWriter().write(gson.toJson(map));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void addUser(HttpServletRequest req, HttpServletResponse resp) {
        try {
            User user = WebUtils.copyParamToBean(new User(), req.getParameterMap());
            labService.addUser(user);
            resp.sendRedirect(req.getContextPath() + "/labServlet?action=queryAll");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void addPerson(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Person person = WebUtils.copyParamToBean(new Person(), req.getParameterMap());
            labService.addPerson(person);
            resp.sendRedirect(req.getContextPath() + "/labServlet?action=queryAll");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void deleteUser(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String username = req.getParameter("username");
            labService.deleteUserByUsername(username);
            labService.deletePersonByUsername(username);
            resp.sendRedirect(req.getContextPath() + "/labServlet?action=queryAll");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void deletePerson(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String username = req.getParameter("username");
            labService.deletePersonByUsername(username);
            resp.sendRedirect(req.getContextPath() + "/labServlet?action=queryAll");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void queryAll(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<User> userList = labService.queryUserList();
            req.setAttribute("userList", userList);
            List<Person> personList = labService.queryPersonList();
            req.setAttribute("personlist", personList);
            req.getRequestDispatcher("/pages/show.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
