package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@RestController
public class Controller {

    @RequestMapping(value = "/login")
    public String test(HttpServletRequest httpServletRequest){

        HttpSession session = httpServletRequest.getSession();
        String name = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("passworld");
        System.out.println(name + password);
        if(name.equals("root") && password.equals("root")){
            User user = new User();
            user.setUsername(name);
            session.setAttribute("name", name);
            ServletRequest request=httpServletRequest;
//            reques
            return "Login Success";
        }else{
            return "Login Error";
        }
    }

}
