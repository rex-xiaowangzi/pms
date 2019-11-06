package com.ujiuye.pro.controller;

import com.ujiuye.cus.bean.Customer;
import com.ujiuye.cus.service.CustomerService;
import com.ujiuye.emp.bean.Employee;
import com.ujiuye.emp.service.EmployeeService;
import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pro")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private EmployeeService employeeService;

    //添加项目
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Project project){
        projectService.saveInfo(project);
        return "redirect:/pro/list";
    }

    //查询所有项目
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView getProjectList(){
        List<Project> list=projectService.getProjectList();
        ModelAndView mv= new ModelAndView("project-base");
        mv.addObject("list",list);
        return mv;
    }


    //批量删除
    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> batchDelete(@RequestParam("ids[]") Integer[] ids){
        boolean result=  projectService.batchDelete(ids);
        Map<String,Object> map=new HashMap<String, Object>();
        if(result){
            map.put("statusCode",200);
            map.put("message","删除成功");
        }else{
            map.put("message","有关联数据，无法删除");
        }
        return map;
    }
    //查看详细信息
    @RequestMapping(value = "/detail/{id}",method =RequestMethod.GET )
    public String detail(@PathVariable("id") Integer pid, Map<String,Object> map){

        Project project=projectService.detail(pid);
        Customer customer= customerService.detail(project.getComname());
        Employee employee= employeeService.detail(project.getEmpFk());
        System.out.println(project);
        map.put("project",project);
        map.put("customer",customer);
        map.put("employee",employee);
        return "project-base-look";
    }

    //修改(先查
    @RequestMapping(value = "/update/{id}",method =RequestMethod.GET )
    public String update(@PathVariable("id") Integer pid, Map<String,Object> map){
        Project project =projectService.updateById(pid);
        Customer customer= customerService.detail(project.getComname());
       Employee employee= employeeService.detail(project.getEmpFk());
        map.put("project",project);
        map.put("customer",customer);
        map.put("employee",employee);
        return "project-base-edit";
    }
    //后修改数据
    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    public String editById(Project project){

        projectService.editById(project);
        return "redirect:/pro/list";
    }

    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
    public List<Project> getProjectJsonList(){
        List<Project> list = projectService.getProjectJsonList();
        return list;
    }

    //模糊查询
    @RequestMapping(value = "/searchList",method = RequestMethod.GET)
    public ModelAndView getProjectList(Integer pid,String keyword,Integer orderby){
        ModelAndView mv=new ModelAndView("project-base");
       List<Project> list= projectService.getProjectList(pid,keyword,orderby);
        mv.addObject("list",list);
        return mv;
    }
}
