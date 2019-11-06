package com.ujiuye.emp.service;

import com.ujiuye.cus.bean.Customer;
import com.ujiuye.emp.bean.Employee;
import com.ujiuye.emp.bean.EmployeeExample;
import com.ujiuye.emp.mapper.EmployeeMapper;
import com.ujiuye.pro.bean.Project;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    public List<Employee> getManagers() {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andPFkEqualTo(4);
        List<Employee> employees = employeeMapper.selectByExample(example);
        return employees;
    }


    public Employee detail(Integer empFk) {

        Employee employee = employeeMapper.selectByPrimaryKey(empFk);
        return employee;
    }

    public Employee login(Employee employee) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        //用户名密码比较
        criteria.andUsernameEqualTo(employee.getUsername());
        criteria.andPasswordEqualTo(employee.getPassword());
        List<Employee> employees = employeeMapper.selectByExample(example);

        if (employees!=null && employees.size()>0){
           employee = employees.get(0);
            return employee;
        }else {
            return null;
        }

    }

    public List<Employee> others(Integer eid) {

      return employeeMapper.others(eid);
    }
}
