package com.mzx.webcocket.controller;

import com.mzx.webcocket.vo.Result;
import com.mzx.webcocket.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author ZhenXinMa
 * @date 2020/7/24 11:53
 */
@Controller
public class UserController {

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, HttpSession session) {


        User user = new User();
        user.setUserName(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        Result result = new Result();
        if (user != null && "123".equals(user.getPassword())) {

            result.setFlag(true);
            session.setAttribute("user", user.getUserName());

            return "main";
        } else {

            result.setFlag(false);
            result.setMessage("登录失败");

            return "login";
        }

    }


    @RequestMapping(value = "/getUserName")
    @ResponseBody
    public String getUsername(HttpSession session) {
        return session.getAttribute("user").toString();
    }


    @RequestMapping(value = "/")
    public String toLogin() {
        return "login";
    }

    @RequestMapping(value = "/admin")
    public String toAdmin(){
        return "admin";
    }


}
