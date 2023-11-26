package org.ipget;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

public class Email {
    public Email(String from, String to, String body, String server, String port, String ssl, String auth, String user, String pass) throws MessagingException {
        EmailEntity emailEntity = new EmailEntity();
        emailEntity.setFrom(from);
        emailEntity.setTo(new String[]{to});
        LocalDateTime dateTime = LocalDateTime.now(); // get the current date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        emailEntity.setSubject(dateTime.format(formatter) + " IP地址");
        emailEntity.setFormat(EmailEntity.EmailFormat.TEXT);
        emailEntity.setBody(body);

        // smtp配置，可保存到properties文件，读取
        Properties props = new Properties();
        props.put("mail.smtp.host", server);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.ssl", ssl);
        // 需要认证
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.user", user);
        props.put("mail.smtp.pass", pass);
        // 使用ssl
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        //props.put("mail.smtp.socketFactory.fallback", false);
        //props.put("mail.smtp.socketFactory.port", mailConfig.getPort());

        // 创建会话
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                if (Boolean.valueOf(props.getProperty("mail.smtp.auth"))) {
                    // 需要认证
                    PasswordAuthentication auth = new PasswordAuthentication(props.getProperty("mail.smtp.user"), props.getProperty("mail.smtp.pass"));
                    return auth;
                }
                return super.getPasswordAuthentication();
            }
        });

        // 构建邮件消息
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new

                InternetAddress(emailEntity.getFrom()));

        InternetAddress[] address = new InternetAddress[emailEntity.getTo().length];
        for (
                int i = 0, j = emailEntity.getTo().length;
                i < j; i++) {
            address[i] = new InternetAddress(emailEntity.getTo()[i]);
        }
// 可以用msg.setRecipients方法增加多个接收人，指定接收人类型
// Message.RecipientType.CC 抄送
// Message.RecipientType.BCC 密送
        msg.setRecipients(Message.RecipientType.TO, address);

        msg.setSubject(emailEntity.getSubject());
        if (EmailEntity.EmailFormat.HTML.equals(emailEntity.getFormat())) {
            // html格式
            msg.setContent(emailEntity.getBody(), "text/html;charset=utf-8");
        } else {
            msg.setText(emailEntity.getBody());
        }
        msg.setSentDate(new Date());

// 发送邮件
        Transport.send(msg);
    }
}
