package com.yao.controller;

import com.yao.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(String username, String password, HttpServletRequest request){
        if (managerService.doLogin(username,password)){
            request.getSession().setAttribute("manager",username);
//            model.addAttribute("manager",username);
            return "login_success";
        }
        return "redirect:user/userLogin.jsp";
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request){
//        request.removeAttribute("manager");
//        model.addAttribute("manager",null);
        request.getSession().setAttribute("manager",null);
        return "redirect:user/userLogin.jsp";
    }

}
