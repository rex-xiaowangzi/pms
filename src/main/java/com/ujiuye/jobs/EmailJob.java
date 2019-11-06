package com.ujiuye.jobs;

import com.ujiuye.info.bean.Email;
import org.quartz.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.io.File;


public class EmailJob implements Job{
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException{
        JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
        Email email =(Email) dataMap.get("email");

        JavaMailSenderImpl javaMailSender=(JavaMailSenderImpl) dataMap.get("javaMailSenderImpl");
        Scheduler scheduler = (Scheduler)dataMap.get("Scheduler");
        try{
            //邮件对象
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");

            helper.setFrom("twz1781748566@163.com");
            System.out.println(email.getEname());
            System.out.println(email.getTitle());
            System.out.println(email.getContent());
            helper.setTo(email.getEname());
            helper.setSubject(email.getTitle());
            helper.setText(email.getContent(),true);
            //作为附件下载
            //FileSystemResource file = new FileSystemResource(new File(""));
            //helper.addAttachment("CollImage.jpg",file);

            //图片作为内置资源
           // FileSystemResource file1 = new FileSystemResource(new File(""));
           // helper.addInline("",file1);
            //发送邮件
            javaMailSender.send(mimeMessage);

            System.out.println("EMAIL PASS");
           // scheduler.shutdown(true);
        }catch(Exception ex){
            System.out.println(2333);
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

    }
}
