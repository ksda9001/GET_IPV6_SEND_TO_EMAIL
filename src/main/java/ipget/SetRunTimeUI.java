package ipget;

import javax.swing.*;
import java.awt.*;

class Dialog2 extends JDialog {

    Dialog2(SetRunTimeUI frame) {
        //实例化一个JDialog类对象，指定对话框的父窗体、窗体标题和类型
        super(frame, "消息", true);
        Container container = getContentPane();                    //创建一个容器
        container.add(new JLabel("操作成功！请不要关闭本程序，将本程序主窗口最小化！"));           //在容器中添加标签
        setBounds(420, 320, 500, 100);          //设置对话框窗体大小
        setResizable(false);
    }
}

public class SetRunTimeUI extends JFrame {
    JPanel root;
    JLabel setTimeLabel;
    JTextField setTimeField;
    JButton okButton;

    public SetRunTimeUI() {
        root = new JPanel();      //定义面板容器
        setContentPane(root);
        setLayout(null);         //设置面板为绝对布局

        setTimeLabel = new JLabel("设置运行时间（小时）：");
        setTimeLabel.setBounds(30, 33, 140, 15);
        root.add(setTimeLabel);

        setTimeField = new JTextField(5);
        setTimeField.setBounds(180, 30, 40, 21);
        root.add(setTimeField);

        okButton = new JButton("确认");
        okButton.setBounds(64, 80, 80, 23);
        okButton.addActionListener(e -> {
            new SetTime().timer1(Integer.parseInt(setTimeField.getText()));
            new Dialog2(SetRunTimeUI.this).setVisible(true);
        });
        root.add(okButton);

        //设置窗口风格
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(400, 300, 250, 180);
        setTitle("发送邮件");
        setVisible(true);
        setResizable(false);
    }
}
