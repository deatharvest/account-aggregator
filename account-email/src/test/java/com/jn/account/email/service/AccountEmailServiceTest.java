package com.jn.account.email.service;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.mail.Message;

import static org.junit.Assert.assertEquals;

public class AccountEmailServiceTest {
    private GreenMail greenMail; // 使用GreenMail作为测试服务器

    @Before
    // Before注解表示会先于测试方法@Test执行
    public void startMailServer() throws Exception {
        greenMail = new GreenMail(ServerSetup.SMTP); // 基于SMTP协议初始化GreenMail
        greenMail.setUser("zachary@mymail.com", "123456"); // 创建一个邮件账户
        greenMail.start(); // 启动邮件服务
    }

    @Test
    public void testSendMail() throws Exception {
        // 根据account-email.xml的配置创建ApplicationContext
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "account-email.xml");
        // 从ctx中获取要测试的id为accountEmailService的bean，并转换成AccountEmailService接口
        AccountEmailService accountEmailService = (AccountEmailService) ctx
                .getBean("accountEmailService");

        String subject = "Test Subject"; // 设置邮件主题
        String htmlText = "<h3>Test</h3>"; // 设置邮件内容
        accountEmailService.sendMail("test@mymail.com", subject, htmlText); // 发送邮件

        greenMail.waitForIncomingEmail(2000, 1); // 接收一封邮件最多等待2秒

        Message[] msgs = greenMail.getReceivedMessages(); // 读取收到的邮件
        assertEquals(1, msgs.length); // 检查邮件的数目
        // //第一封邮件的主题及内容
        assertEquals(subject, msgs[0].getSubject());
        assertEquals(htmlText, GreenMailUtil.getBody(msgs[0]).trim());
    }

    @After
    public void stopMailServer() throws Exception {
        greenMail.stop();			//停止邮件服务
    }
}
