package ipget;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class GWProperties {
    Map<String,String> map = new HashMap<>();
    public Map<String,String> getPropertiesReader() {
        Properties properties = new Properties();//获取Properties实例
        try {
            InputStream inStream = new FileInputStream("config.properties");
            properties.load(inStream);//载入输入流
            map.put("sendEmailAddress",properties.getProperty("sendEmailAddress"));
            map.put("getEmailAddress",properties.getProperty("getEmailAddress"));
            map.put("smtpServer", properties.getProperty("smtpServer"));
            map.put("smtpPort",properties.getProperty("smtpPort"));
            map.put("ssl",properties.getProperty("ssl"));
            map.put("auth",properties.getProperty("auth"));
            map.put("authName",properties.getProperty("authName"));
            map.put("authPass",properties.getProperty("authPass"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public void writeProperties(String sendEmailAddress, String getEmailAddress,
                                String smptServer, String smtpPort,
                                String ssl, String auth, String authName, String authPass) {
        Properties properties = new Properties();
        try {
            OutputStream outputStream = new FileOutputStream("config.properties");
            properties.setProperty("sendEmailAddress", sendEmailAddress);
            properties.setProperty("getEmailAddress", getEmailAddress);
            properties.setProperty("smtpServer", smptServer);
            properties.setProperty("smtpPort", smtpPort);
            properties.setProperty("ssl", ssl);
            properties.setProperty("auth", auth);
            properties.setProperty("authName", authName);
            properties.setProperty("authPass", authPass);
            properties.store(outputStream, "Setting Properties");
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
