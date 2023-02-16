package com.mingrisoft;
import java.awt.*;
import java.net.URL;
import javax.swing.*;
public class DesktopPanel extends JDesktopPane {//和LoginPanel一样，负责界面设计
    private static final long serialVersionUID = 1L;
    private final Image backImage;
    public DesktopPanel() {
        super();//继承JDesktopPane
        URL url = DesktopPanel.class.getResource("/res/back.jpg");// 背景图片路径
        backImage = new ImageIcon(url).getImage();//
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = this.getHeight();
        g.drawImage(backImage, 0, 0, width, height, this);
    }
}
