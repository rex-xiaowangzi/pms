package com.ujiuye.info.service;

import com.ujiuye.info.bean.Email;
import com.ujiuye.info.mapper.EmailMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EmailService {
    @Resource
    private EmailMapper emailMapper;

}
