import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

public class EmailTest {

    @Test
    public void test01()throws Exception{

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-email.xml");
        JavaMailSenderImpl bean = context.getBean(JavaMailSenderImpl.class);

        //邮件对象
        MimeMessage mimeMessage = bean.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        helper.setFrom("twz1781748566@163.com");
        helper.setTo("wj2424588930@163.com");
        helper.setSubject("这是小王紫的邮件测试");
        helper.setText("大傻逼");

        //发送邮件
        bean.send(mimeMessage);

        System.out.println("EMAIL PASS");
    }



    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-quartz.xml");

    }
}