package org.ipget;

import jakarta.mail.MessagingException;

import java.net.SocketException;
import java.net.UnknownHostException;

public class SendEmail {
    GWProperties gwProperties;
    Email email;
    String info;
    public void sendEmail() throws UnknownHostException, SocketException, MessagingException {
        gwProperties = new GWProperties();

        info = "系统平台：" + System.getProperty("os.name") + "\n"
                + "主机名称：" + IP.getHostName() + "\n"
                + "系统首选IP：" + IP.getLocalIP() + "\n"
                + "系统所有IP：" + "\n" + String.join("\n", IP.getLocalIPs());

        email = new Email(gwProperties.getPropertiesReader().get("sendEmailAddress"),
                gwProperties.getPropertiesReader().get("getEmailAddress"),
                info, gwProperties.getPropertiesReader().get("smtpServer"),
                gwProperties.getPropertiesReader().get("smtpPort"),
                gwProperties.getPropertiesReader().get("ssl"),
                gwProperties.getPropertiesReader().get("auth"),
                gwProperties.getPropertiesReader().get("authName"),
                gwProperties.getPropertiesReader().get("authPass"));
    }
}
