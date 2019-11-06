package com.ujiuye.cus.service;

import com.ujiuye.cus.bean.CustomerExample;
import com.ujiuye.cus.mapper.CustomerMapper;
import org.springframework.stereotype.Service;
import com.ujiuye.cus.bean.Customer;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class CustomerService {
    @Resource
    private CustomerMapper customerMapper;

    public void saveInfo(Customer customer) {
        customer.setAddtime(new Date());
        customerMapper.insert(customer);

    }

    public List<Customer> getCustomerList() {
        CustomerExample customerExample = new CustomerExample();
        List<Customer> customers = customerMapper.selectByExample(customerExample);
        return customers;
    }

    public Customer detail(Integer cid) {
        Customer customer = customerMapper.selectByPrimaryKey(cid);
        return customer;
    }

    public Customer updateById(Integer cid) {
        Customer customer = customerMapper.selectByPrimaryKey(cid);
        return customer;
    }

    public void editById(Customer customer) {

        customerMapper.updateByPrimaryKeySelective(customer);
    }

    public boolean batchDelete(Integer[] ids) {
        List<Integer> idList = Arrays.asList(ids);
        CustomerExample example = new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(idList);
        int i = customerMapper.deleteByExample(example);
        return ids.length==i;

    }

    public List<Customer> search(Integer cid, String keyword, Integer orderby) {
        CustomerExample example=new CustomerExample();
        CustomerExample.Criteria criteria=example.createCriteria();
        if(cid==0){
            criteria.andComnameLike("%"+keyword+"%");
            CustomerExample.Criteria criteria1=example.createCriteria();
            criteria1.andCompanypersonLike("%"+keyword+"%");
            example.or(criteria1);
        }else if(cid ==1){
            criteria.andComnameLike("%"+keyword+"%");
        }else {
            criteria.andCompanypersonLike("%"+keyword+"%");
        }if (orderby==1){
            example.setOrderByClause("id desc");
        }
        List<Customer> customers=customerMapper.selectByExample(example);
        return customers;
    }
}