package com.mingrisoft.login;
import java.awt.*;
import java.net.URL;
import javax.swing.*;
public class LoginPanel extends JPanel {//面板容器，定义页面
    public int width, height;//定义宽和高
    private Image img;//图片
    public LoginPanel() {
        super();// super关键字可以在子类的构造方法中显示地调用父类的构造方法，super()必须为子类构造函数中的第一行。
        URL url = getClass().getResource("/res/login.jpg");//图片位置
        img = new ImageIcon(url).getImage();//图片
    }
    protected void paintComponent(Graphics g) {//背景图
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this);// drawImage(图片, 0, 0,（左上角的位置） this（包含了宽和高，指用到这个函数的整个容器）);
    }

}