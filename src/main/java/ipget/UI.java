package ipget;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class mainDialog extends JDialog {

    mainDialog(UI frame,String msg) {
        //实例化一个JDialog类对象，指定对话框的父窗体、窗体标题和类型
        super(frame, "消息", true);
        Container container = getContentPane();                    //创建一个容器
        container.add(new JLabel(msg));           //在容器中添加标签
        setBounds(420, 320, 200, 100);          //设置对话框窗体大小
    }
}

public class UI extends JFrame {
    JPanel root;
    JLabel userNameLabel, passWordLabel;
    JTextField userTextField;
    JButton getIpButton, setBootButton, sendButton, setTimeButton;
    JPasswordField passWordTextField;
    DeleteLNK deleteLNK;

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
                        new GetIpUI("获取ip");
                    } catch (UnknownHostException | SocketException ex) {
                        throw new RuntimeException(ex);
                    }
                });
        root.add(getIpButton);

        //添加开机启动
        setBootButton = new JButton("添加开机启动");
        setBootButton.setBounds(180, 20, 150, 23);
        setBootButton.addActionListener(e -> {
            deleteLNK = new DeleteLNK();
            try {
                if (!deleteLNK.checkFile()){
                    Path target = Paths.get("GET_IPV6_SEND_TO_EMAIL.exe");
                    Path link = Paths.get("C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\IPGET.link");
                    try {
                        Files.createSymbolicLink(link, target);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    new mainDialog(UI.this,"已创建开机启动！").setVisible(true);
                }else{
                    deleteLNK.deleteFile();
                    new mainDialog(UI.this,"已删除开机启动！").setVisible(true);
                }

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }



        });
        root.add(setBootButton);

        //发送邮件
        sendButton = new JButton("发送邮件");
        sendButton.setBounds(10, 53, 150, 23);
        sendButton.addActionListener(
                e -> {
                    new SendEmailUI();
                });
        root.add(sendButton);

        //设置运行时间
        setTimeButton = new JButton("设置运行时间");
        setTimeButton.setBounds(180, 53, 150, 23);
        setTimeButton.addActionListener(e -> {
            new SetRunTimeUI();
        });
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
        System.setProperty("java.home", ".");
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
        new UI();
    }
}
