package com.jn.account.email.service;

import com.jn.account.email.entity.Email_Authenticator;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class Test_Email_N {
    public static void  main(String args[]){
        try {
            send_email();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void send_email() throws IOException, AddressException, MessagingException{

        String to = "1026218167@qq.com";
        String subject = "hello";
        String content = "qinhao";
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.qq.com");
        properties.put("mail.smtp.port", "587");//qq邮箱的smtp规定端口号为587，否则无法发送
        properties.put("mail.smtp.auth", "true");
        Authenticator authenticator = new Email_Authenticator("595509710@qq.com", "waww0212");
        javax.mail.Session sendMailSession = javax.mail.Session.getDefaultInstance(properties, authenticator);
        MimeMessage mailMessage = new MimeMessage(sendMailSession);
        mailMessage.setFrom(new InternetAddress("595509710@qq.com"));
        // Message.RecipientType.TO属性表示接收者的类型为TO
        mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        mailMessage.setSubject(subject, "UTF-8");
        mailMessage.setSentDate(new Date());
        // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
        Multipart mainPart = new MimeMultipart();
        // 创建一个包含HTML内容的MimeBodyPart
        BodyPart html = new MimeBodyPart();
        html.setContent(content.trim(), "text/html; charset=utf-8");
        mainPart.addBodyPart(html);
        mailMessage.setContent(mainPart);
        Transport.send(mailMessage);
    }
}