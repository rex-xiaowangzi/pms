package com.ujiuye.emp.controller;

import com.ujiuye.emp.bean.Employee;

import com.ujiuye.emp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
@RequestMapping(value = "/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    //查询所有项目经理
    @RequestMapping(value = "/managers", method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> getManagers() {
      return employeeService.getManagers();
    }


    //登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(Employee employee,String code,HttpSession session){
        String validateCode = (String) session.getAttribute("validateCode");
        boolean b = code.equalsIgnoreCase(validateCode);
        if(b){
         employee=  employeeService.login(employee);
          session.removeAttribute("validateCode");
          if (employee!=null){
              session.setAttribute("loginUser",employee);
              return "redirect:/index.jsp";
          }else {
              return "redirect:/login.jsp";
          }
        }
        return "redirect:/login.jsp";
    }

    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.removeAttribute("validateCode");
        return "redirect:/login.jsp";
    }
    //发邮件
    @RequestMapping(value = "/others",method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> others(HttpSession session){
        Employee loginUser = (Employee) session.getAttribute("loginUser");
        Integer eid = loginUser.getEid();
       List<Employee> emp= employeeService.others(eid);
       return emp;
    }

}