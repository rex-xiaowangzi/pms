package com.ujiuye.cus.controller;
import com.ujiuye.cus.bean.Customer;
import com.ujiuye.cus.bean.CustomerExample;
import com.ujiuye.cus.service.CustomerService;
import com.ujiuye.emp.bean.Employee;
import com.ujiuye.emp.bean.EmployeeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/cust")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    //添加用户
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Customer customer){
        customerService.saveInfo(customer);
        return "redirect:/cust/list";
    }
    //查询所有用户
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView getCustomerList(){
       List<Customer>  list=customerService.getCustomerList();
      ModelAndView mv= new ModelAndView("customer");
      mv.addObject("list",list);
      return mv;
    }
    //查看详细信息
    @RequestMapping(value = "/detail/{id}",method =RequestMethod.GET )
    public String detail(@PathVariable("id") Integer cid, Map<String,Object> map){
        Customer customer=customerService.detail(cid);
        map.put("customer",customer);
        return "customer-look";
    }
    //修改(先查
    @RequestMapping(value = "/update/{id}",method =RequestMethod.GET )
    public String update(@PathVariable("id") Integer cid, Map<String,Object> map){
        Customer customer=customerService.updateById(cid);
        map.put("customer",customer);
        return "customer-edit";
    }
    //后修改数据
    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    public String editById(Customer customer){
        System.out.println(customer.getId());
        customerService.editById(customer);
        return "redirect:/cust/list";
    }

    //删除
    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> batchDelete(@RequestParam("ids[]") Integer[] ids){
        System.out.println(ids);
      boolean result=  customerService.batchDelete(ids);
      Map<String,Object> map=new HashMap<String, Object>();
      if(result){
        map.put("statusCode",200);
        map.put("message","删除成功");
      }else{
          map.put("message","有关联数据，无法删除");
      }
        return map;
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public ModelAndView search(Integer cid,String keyword,Integer orderby){
        List<Customer>  list=customerService.search(cid,keyword,orderby);
        ModelAndView mv= new ModelAndView("customer");
        mv.addObject("list",list);
        return mv;
    }

    //查询所有项目
    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> getCustomerJsonList(){
        List<Customer>  list=customerService.getCustomerList();
        return list;
    }
    //根据客户公司查询负责人
    @RequestMapping(value = "/info/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Customer selectInfo(@PathVariable("id") Integer cid ){
        System.out.println(cid);
        Customer customer=customerService.detail(cid);
        return customer;
    }



}
