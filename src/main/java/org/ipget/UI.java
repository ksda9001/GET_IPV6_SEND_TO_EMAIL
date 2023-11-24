package org.ipget;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UI extends JFrame {
    JPanel root;
    JLabel userNameLabel,passWordLabel;
    JTextField userTextField;
    JButton getIpButton,setBootButton,sendButton,setTimeButton;
    JPasswordField passWordTextField;

    public UI() {
        root = new JPanel();      //定义面板容器
        setContentPane(root);
        setLayout(null);         //设置面板为绝对布局

        //获取ip地址
        getIpButton = new JButton("获取ip地址");          //定义按钮对象
        getIpButton.setBounds(10, 20, 150, 23);
        // 添加监听
        getIpButton.addActionListener(
                e -> {
                    try {
                        new getIpUI("获取ip");
                    } catch (UnknownHostException | SocketException ex) {
                        throw new RuntimeException(ex);
                    }
                });
        root.add(getIpButton);

        //添加开机启动
        setBootButton = new JButton("添加开机启动");
        setBootButton.setBounds(180, 20, 150, 23);
        root.add(setBootButton);

        //发送邮件
        sendButton = new JButton("发送邮件");
        sendButton.setBounds(10, 53, 150, 23);
        root.add(sendButton);

        //设置运行时间
        setTimeButton = new JButton("设置运行时间");
        setTimeButton.setBounds(180,53,150,23);
        root.add(setTimeButton);

        //设置窗口风格
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(400, 300, 360, 130);
        setTitle("IP获取器");
        setResizable(false);
        setVisible(true);
    }

    //main方法
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
        new UI();
    }
}
