package org.ipget;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class SendEmailUI extends JFrame {
    JPanel root;
    JLabel sendEmailLabel,getEmailLabel,smtpAddressLabel,smtpPortLabel,authNameLabel,authPassLabel;
    JTextField sendEmailField,getEmailTextField,smtpAddressField,smtpPortField,authNameField,authPassField;
    JCheckBox ifSSLCheckBox,ifNeedAuthCheckBox;
    JButton enterButton,closeButton;

    SendEmailUI() {
        ArrayList<Object> list = new ArrayList<>();
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
        root.add(sendEmailField);
        list.add(sendEmailField.getText());

        //接收邮件地址标签
        getEmailLabel = new JLabel("接收邮件地址：");
        getEmailLabel.setBounds(30, 74, 100, 15);
        root.add(getEmailLabel);

        //接收邮件地址文本框
        getEmailTextField = new JTextField(30);
        getEmailTextField.setBounds(116, 71, 200, 21);
        root.add(getEmailTextField);
        list.add(getEmailTextField.getText());

        //smtp服务器
        smtpAddressLabel = new JLabel("smtp服务器：");
        smtpAddressLabel.setBounds(30, 115, 100, 15);
        root.add(smtpAddressLabel);

        //smtp服务器文本框
        smtpAddressField = new JTextField(30);
        smtpAddressField.setBounds(116, 112, 200, 21);
        root.add(smtpAddressField);
        list.add(smtpAddressField.getText());

        //smtp端口号
        smtpPortLabel = new JLabel("smtp端口号：");
        smtpPortLabel.setBounds(30,156,100,15);
        root.add(smtpPortLabel);

        //smtp端口号文本框
        smtpPortField = new JTextField(5);
        smtpPortField.setBounds(116, 153, 60, 21);
        root.add(smtpPortField);
        list.add(smtpPortField.getText());

        //ssl
        ifSSLCheckBox = new JCheckBox("是否使用SSL");
        ifSSLCheckBox.setBounds(25,197,120,15);
        root.add(ifSSLCheckBox);
        list.add(ifSSLCheckBox.isSelected());

        //是否需要认证
        ifNeedAuthCheckBox = new JCheckBox("是否需要认证");
        ifNeedAuthCheckBox.setBounds(25,238,120,15);
        ifNeedAuthCheckBox.addActionListener(e -> {
            if (ifNeedAuthCheckBox.isSelected()){
                list.add(ifNeedAuthCheckBox.isSelected());

                authNameLabel = new JLabel("认证用户名：");
                authNameLabel.setBounds(30, 279, 100, 15);
                root.add(authNameLabel);

                authNameField = new JTextField(30);
                authNameField.setBounds(116,276,200,21);
                root.add(authNameField);

                authPassLabel = new JLabel("密码：");
                authPassLabel.setBounds(30, 320, 100, 15);
                root.add(authPassLabel);

                authPassField = new JTextField(30);
                authPassField.setBounds(116, 317, 200, 21);
                root.add(authPassField);

                root.updateUI();
            }else {
                list.set(5,false);

                root.remove(authNameLabel);
                root.remove(authNameField);
                root.remove(authPassLabel);
                root.remove(authPassField);
                root.updateUI();
            }
        });
        root.add(ifNeedAuthCheckBox);


        //登录按钮
//        enterButton = new JButton("登录");          //定义按钮对象
//        enterButton.setBounds(64, 116, 69, 23);
//        root.add(enterButton);
//
//        //退出按钮
//        closeButton = new JButton("退出");
//        closeButton.setBounds(174, 116, 69, 23);
//        root.add(closeButton);

        //设置窗口风格
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(400, 300, 340, 500);
        setVisible(true);
    }

    //main方法
    public static void main(String[] args) {
        new SendEmailUI();
    }
}