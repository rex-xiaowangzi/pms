package com.ujiuye.info.controller;

import com.ujiuye.info.bean.Email;
import com.ujiuye.info.service.EmailService;
import com.ujiuye.jobs.EmailJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping(value = "/email")
public class EmailController {
    @Autowired
    private JavaMailSenderImpl javaMailSender;
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)

    public String saveInfo(Email email) throws Exception{
        System.out.println(111);
        //创建JobDetail对象，指定对象的任务名称、组名
        JobDetail job = JobBuilder.newJob(EmailJob.class).build();
        JobDataMap jobDataMap = job.getJobDataMap();
        jobDataMap.put("email",email);
        jobDataMap.put("javaMailSenderImpl",javaMailSender);


        //创建SimpleTrigger对象，指定对象名称、组名  设置任务重复执行间隔时间，重复执行次数 启动时间
        SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().startAt(new Date()).build();
        //创建任务管理器Scheduler对象
        Scheduler sched= StdSchedulerFactory.getDefaultScheduler();
        jobDataMap.put("Scheduler",sched);
        //为Scheduler对象新增JOB以及对应的SimpleTrigger
        sched.scheduleJob(job,trigger);
        //启动定时任务管理器
        sched.start();
        return "redirect:/message.jsp";
    }
}
