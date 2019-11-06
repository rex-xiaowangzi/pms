package com.ujiuye.usual.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.ujiuye.emp.bean.Employee;
import com.ujiuye.usual.bean.Baoxiao;
import com.ujiuye.usual.service.BaoXiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping(value = "/bx")
public class BaoXiaoController {

    @Autowired
    private BaoXiaoService baoXiaoService;

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(HttpSession session, Baoxiao baoxiao){
        Employee loginUser = (Employee)session.getAttribute("loginUser");
        baoxiao.setEmpFk(loginUser.getEid());
        baoXiaoService.saveInfo(baoxiao);
        return "redirect:/bx/myList";
    }
    //查询列表
    @RequestMapping(value = "/myList",method = RequestMethod.GET)
    public ModelAndView getMyBaoXiaoList(HttpServletRequest request,HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        Map<String, Object> parametmap = WebUtils.getParametersStartingWith(request, "search_");

        Employee loginUser = (Employee)session.getAttribute("loginUser");
        Integer eid = loginUser.getEid();
        PageInfo<Baoxiao> page=baoXiaoService.getMyBaoXiaoList(eid,pageNum,parametmap);
        ModelAndView mv = new ModelAndView("mybaoxiao-base");
        mv.addObject("page",page);
        return mv;
    }
}
