package ipget;

import javax.swing.*;
import java.awt.*;
import java.net.SocketException;
import java.net.UnknownHostException;

public class GetIpUI extends JFrame {
    JPanel root;
    JTextArea textArea;

    public GetIpUI(String title) throws UnknownHostException, SocketException {
        super(title);

        root = new JPanel();      //定义面板容器
        setContentPane(root);
        setLayout(null);         //设置面板为绝对布局

        textArea = new JTextArea();              //定义文本域组件
        textArea.setLineWrap(true);              //设置文本域自动换行
        textArea.setBounds(5, 5, 500, 400);
        textArea.setText("系统平台：" + System.getProperty("os.name") + "\n"
                + "主机名称：" + IP.getHostName() + "\n"
                + "系统首选IP：" + IP.getLocalIP() + "\n"
                + "系统所有IP：" + "\n" + String.join("\n", IP.getLocalIPs()));
        textArea.setEditable(false); //不可编辑
        textArea.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
        root.add(textArea);                      //将文本域添加到面板

        //设置窗口风格
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(400, 300, 525, 450);
        setVisible(true);
        setResizable(false);
    }
}
