package com.wdw.web;

import com.google.gson.Gson;
import com.wdw.pojo.Person;
import com.wdw.pojo.User;
import com.wdw.service.LabService;
import com.wdw.utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class LabServlet extends BaseServlet {
    private LabService labService = new LabService();
    private Gson gson = new Gson();

    protected void signUp(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Object> map = new HashMap<>();

        User user = WebUtils.paramsToBean(new User(), req.getParameterMap());
        Person person = WebUtils.paramsToBean(new Person(), req.getParameterMap());

        if (labService.hasUsername(user.getUsername())) {
            map.put("result", "username already exists");
        } else if (labService.hasName(person.getName())) {
            map.put("result", "name already exists");
        } else {
            labService.addUser(user);
            labService.addPerson(person);
            map.put("result", "success");
        }

        try {
            resp.getWriter().write(gson.toJson(map));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void signIn(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Object> map = new HashMap<>();

        if (labService.hasUser(WebUtils.paramsToBean(new User(), req.getParameterMap()))) {
            map.put("result", "success");
        } else {
            map.put("result", "unknown username or bad password");
        }

        try {
            resp.getWriter().write(gson.toJson(map));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void welcome(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Object> map = new HashMap<>();
        Person person = labService.queryPersonByUsername(req.getParameter("username"));

        try {
            resp.getWriter().write(gson.toJson(person));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
