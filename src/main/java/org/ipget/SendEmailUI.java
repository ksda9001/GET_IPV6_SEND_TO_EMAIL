package org.ipget;

import jakarta.mail.MessagingException;

import javax.swing.*;
import java.awt.*;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

class Dialog extends JDialog {

    Dialog(SendEmailUI frame) {
        //实例化一个JDialog类对象，指定对话框的父窗体、窗体标题和类型
        super(frame, "消息", true);
        Container container = getContentPane();                    //创建一个容器
        container.add(new JLabel("操作成功！"));           //在容器中添加标签
        setBounds(420, 320, 150, 100);          //设置对话框窗体大小
    }
}

public class SendEmailUI extends JFrame {
    JPanel root;
    JLabel sendEmailLabel, getEmailLabel, smtpAddressLabel, smtpPortLabel, authNameLabel, authPassLabel;
    JTextField sendEmailField, getEmailTextField, smtpAddressField, smtpPortField, authNameField, authPassField;
    JCheckBox ifSSLCheckBox, ifNeedAuthCheckBox;
    JButton saveButton, submitButton;
    GWProperties gwProperties;

    Map<String, String> smap;

    public SendEmailUI() {
        smap = new HashMap<>();
        gwProperties = new GWProperties();

        root = new JPanel();      //定义面板容器
        setContentPane(root);
        setLayout(null);         //设置面板为绝对布局

        //发送邮箱地址标签
        sendEmailLabel = new JLabel("发送邮箱地址：");
        sendEmailLabel.setBounds(30, 33, 100, 15);
        root.add(sendEmailLabel);

        //发送邮箱地址文本框
        sendEmailField = new JTextField(30);
        sendEmailField.setBounds(116, 30, 200, 21);
        sendEmailField.setText(gwProperties.getPropertiesReader().get("sendEmailAddress"));
        root.add(sendEmailField);

        //接收邮件地址标签
        getEmailLabel = new JLabel("接收邮件地址：");
        getEmailLabel.setBounds(30, 74, 100, 15);
        root.add(getEmailLabel);

        //接收邮件地址文本框
        getEmailTextField = new JTextField(30);
        getEmailTextField.setBounds(116, 71, 200, 21);
        getEmailTextField.setText(gwProperties.getPropertiesReader().get("getEmailAddress"));
        root.add(getEmailTextField);

        //smtp服务器
        smtpAddressLabel = new JLabel("smtp服务器：");
        smtpAddressLabel.setBounds(30, 115, 100, 15);
        root.add(smtpAddressLabel);

        //smtp服务器文本框
        smtpAddressField = new JTextField(30);
        smtpAddressField.setBounds(116, 112, 200, 21);
        smtpAddressField.setText(gwProperties.getPropertiesReader().get("smtpServer"));
        root.add(smtpAddressField);

        //smtp端口号
        smtpPortLabel = new JLabel("smtp端口号：");
        smtpPortLabel.setBounds(30, 156, 100, 15);
        root.add(smtpPortLabel);

        //smtp端口号文本框
        smtpPortField = new JTextField(5);
        smtpPortField.setBounds(116, 153, 60, 21);
        smtpPortField.setText(gwProperties.getPropertiesReader().get("smtpPort"));
        root.add(smtpPortField);

        //ssl
        ifSSLCheckBox = new JCheckBox("是否使用SSL");
        ifSSLCheckBox.setBounds(25, 197, 120, 15);
        ifSSLCheckBox.setSelected(Boolean.parseBoolean(gwProperties.getPropertiesReader().get("ssl")));
        root.add(ifSSLCheckBox);

        //是否需要认证
        ifNeedAuthCheckBox = new JCheckBox("是否需要认证");
        ifNeedAuthCheckBox.setBounds(25, 238, 120, 15);
        ifNeedAuthCheckBox.setSelected(Boolean.parseBoolean(gwProperties.getPropertiesReader().get("auth")));
        if (ifNeedAuthCheckBox.isSelected()) {
            authNameLabel = new JLabel("认证用户名：");
            authNameLabel.setBounds(30, 279, 100, 15);
            root.add(authNameLabel);

            authNameField = new JTextField(30);
            authNameField.setBounds(116, 276, 200, 21);
            authNameField.setText(gwProperties.getPropertiesReader().get("authName"));
            root.add(authNameField);

            authPassLabel = new JLabel("密码：");
            authPassLabel.setBounds(30, 320, 100, 15);
            root.add(authPassLabel);

            authPassField = new JTextField(30);
            authPassField.setBounds(116, 317, 200, 21);
            authPassField.setText(gwProperties.getPropertiesReader().get("authPass"));
            root.add(authPassField);
        }

        ifNeedAuthCheckBox.addActionListener(e -> {
            if (ifNeedAuthCheckBox.isSelected()) {
                authNameLabel = new JLabel("认证用户名：");
                authNameLabel.setBounds(30, 279, 100, 15);
                root.add(authNameLabel);

                authNameField = new JTextField(30);
                authNameField.setBounds(116, 276, 200, 21);
                authNameField.setText(gwProperties.getPropertiesReader().get("authName"));
                root.add(authNameField);

                authPassLabel = new JLabel("密码：");
                authPassLabel.setBounds(30, 320, 100, 15);
                root.add(authPassLabel);

                authPassField = new JTextField(30);
                authPassField.setBounds(116, 317, 200, 21);
                authPassField.setText(gwProperties.getPropertiesReader().get("authPass"));
                root.add(authPassField);

                root.updateUI();
            } else {
                root.remove(authNameLabel);
                root.remove(authNameField);
                root.remove(authPassLabel);
                root.remove(authPassField);
                root.updateUI();
            }
        });
        root.add(ifNeedAuthCheckBox);


        // 确认按钮
        saveButton = new JButton("保存配置");          //定义按钮对象
        saveButton.setBounds(64, 358, 100, 23);
        saveButton.addActionListener(e -> {
            if (sendEmailField != null) {
                smap.put("sendEmailAddress", sendEmailField.getText());
            } else {
                smap.put("sendEmailAddress", "");
            }

            if (getEmailTextField != null) {
                smap.put("getEmailAddress", getEmailTextField.getText());
            } else {
                smap.put("getEmailAddress", "");
            }

            if (smtpAddressField != null) {
                smap.put("smtpServer", smtpAddressField.getText());
            } else {
                smap.put("smtpServer", "");
            }

            if (smtpPortField != null) {
                smap.put("smtpPort", smtpPortField.getText());
            } else {
                smap.put("smtpPort", "");
            }

            smap.put("ssl", String.valueOf(ifSSLCheckBox.isSelected()));

            smap.put("auth", String.valueOf(ifNeedAuthCheckBox.isSelected()));

            if (authNameField != null) {
                smap.put("authName", authNameField.getText());
            } else {
                smap.put("authName", "");
            }

            if (authPassField != null) {
                smap.put("authPass", authPassField.getText());
            } else {
                smap.put("authPass", "");
            }

            gwProperties.writeProperties(smap.get("sendEmailAddress"), smap.get("getEmailAddress"),
                    smap.get("smtpServer"), smap.get("smtpPort"), smap.get("ssl"),
                    smap.get("auth"), smap.get("authName"), smap.get("authPass"));

            new Dialog(SendEmailUI.this).setVisible(true);
        });
        root.add(saveButton);

        //提交按钮
        submitButton = new JButton("发送邮件");
        submitButton.setBounds(180, 358, 100, 23);
        submitButton.addActionListener(e -> {
            try {
                new SendEmail().sendEmail();
            } catch (UnknownHostException | SocketException | MessagingException ex) {
                throw new RuntimeException(ex);
            }

            new Dialog(SendEmailUI.this).setVisible(true);
        });
        root.add(submitButton);

        //设置窗口风格
        setBounds(400, 300, 360, 500);
        setTitle("发送邮件");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}