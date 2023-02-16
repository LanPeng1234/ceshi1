package com.mingrisoft;

import static java.awt.BorderLayout.*;
import static javax.swing.border.BevelBorder.*;
import java.awt.*;
import java.util.Date;
import javax.swing.*;
import com.mingrisoft.login.LoginDialog;
//初始化各种有用的功能，设计主页面，安排各种组件
public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;//流式标签
    private JPanel frameContentPane = null;
    private MenuBar frameMenuBar = null;
    private ToolBar toolBar = null;
    private DesktopPanel desktopPane = null;
    private JPanel statePanel = null;
    private JLabel stateLabel = null;
    private JLabel nameLabel = null;
    private JLabel nowDateLabel = null;
    private JSeparator jSeparator1 = null;
    private static JLabel czyStateLabel = null;
    private JSeparator jSeparator2 = null;
    public static void main(String[] args) {
        SplashScreen splashScreen = SplashScreen.getSplashScreen();//闪屏
        JFrame login = new LoginDialog();
        if (splashScreen != null) {//没有闪屏图片时
            try {
                login.setDefaultCloseOperation(EXIT_ON_CLOSE);//设置用户在此窗体上发起 "close" 时默认执行的操作。
                Thread.sleep(3000);//反应时间3s
            } catch (InterruptedException e) {

            }
        }
        login.setVisible(true);//显示屏幕
    }
    //初始化ToolBar
    private ToolBar getJJToolBar() {
        if (toolBar == null) {
            toolBar = new ToolBar(getFrameMenuBar());
            toolBar.setCursor(new Cursor(Cursor.HAND_CURSOR));//cursor的构造函数的参数是curser的类型
        }
        return toolBar;
    }
    //初始化成员变量frameMenuBar
    protected MenuBar getFrameMenuBar() {
        if (frameMenuBar == null) {// ˵Ϊ
            frameMenuBar = new MenuBar(getDesktopPane(), getStateLabel());//其他变量的初始化方法的返回值
        }
        return frameMenuBar;
    }
    //初始化DesktopPanel
    private DesktopPanel getDesktopPane() {
        if (desktopPane == null) {
            desktopPane = new DesktopPanel();
        }
        return desktopPane;
    }
    //初始化StatePanel
    private JPanel getStatePanel() {
        if (statePanel == null) {
            GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
            gridBagConstraints6.gridx = 2;//每个组件占据格子里左上角的x
            gridBagConstraints6.fill = GridBagConstraints.VERTICAL;//加高组件，使它在垂直方向上填满其显示区域，但是不改变宽度
            gridBagConstraints6.insets = new Insets(0, 5, 0, 5);
            gridBagConstraints6.gridy = 0;//每个组件占据格子里左上角的y
            GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
            gridBagConstraints4.gridx = 3;
            gridBagConstraints4.gridy = 0;
            GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
            gridBagConstraints3.gridx = 6;
            gridBagConstraints3.fill = GridBagConstraints.VERTICAL;//加高组件，使它在垂直方向上填满其显示区域，但是不改变宽度
            gridBagConstraints3.insets = new Insets(0, 5, 0, 5);
            gridBagConstraints3.gridy = 0;
            GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
            gridBagConstraints11.gridx = 5;
            gridBagConstraints11.insets = new Insets(0, 5, 0, 5);
            gridBagConstraints11.gridy = 0;
            nowDateLabel = new JLabel();
            Date now = new Date();
            nowDateLabel.setText(String.format("%tF", now));//Label对象的文字
            GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
            gridBagConstraints2.gridx = 7;
            gridBagConstraints2.weightx = 0.0;
            gridBagConstraints2.fill = GridBagConstraints.NONE;//不调整
            gridBagConstraints2.gridy = 0;
            nameLabel = new JLabel("ABCD");
            GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
            gridBagConstraints1.gridx = 4;
            gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
            gridBagConstraints1.weighty = 1.0;
            gridBagConstraints1.insets = new Insets(0, 5, 0, 5);
            gridBagConstraints1.gridy = 0;
            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;//加宽组件，使它在水平方向上填满其显示区域，但是不改变高度；
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridy = 0;
            statePanel = new JPanel();
            statePanel.setLayout(new GridBagLayout());//设置窗体布局
            statePanel.setBorder(BorderFactory.createBevelBorder(RAISED));//设置边界凸起
            statePanel.add(getStateLabel(), gridBagConstraints);
            statePanel.add(getJSeparator(), gridBagConstraints1);
            statePanel.add(nameLabel, gridBagConstraints2);
            statePanel.add(getJSeparator1(), gridBagConstraints3);
            statePanel.add(nowDateLabel, gridBagConstraints11);
            statePanel.add(getCzyStateLabel(), gridBagConstraints4);
            statePanel.add(getJSeparator2(), gridBagConstraints6);
        }
        return statePanel;
    }
    public static JLabel getCzyStateLabel() {
        if (czyStateLabel == null) {
            czyStateLabel = new JLabel("操作员：");
        }
        return czyStateLabel;
    }
    public JLabel getStateLabel() {
        if (stateLabel == null) {
            stateLabel = new JLabel();
            stateLabel.setText("当前没有选定窗口");
        }
        return stateLabel;
    }
    //竖直分割线
    private JSeparator getJSeparator() {
        JSeparator jSeparator = new JSeparator();
        jSeparator.setOrientation(JSeparator.VERTICAL);
        return jSeparator;
    }
    //垂直分割线
    private JSeparator getJSeparator1() {
        if (jSeparator1 == null) {
            jSeparator1 = new JSeparator();
            jSeparator1.setOrientation(SwingConstants.VERTICAL);
        }
        return jSeparator1;
    }
    private JSeparator getJSeparator2() {
        if (jSeparator2 == null) {
            jSeparator2 = new JSeparator();
            jSeparator2.setOrientation(SwingConstants.VERTICAL);
        }
        return jSeparator2;
    }
    public MainFrame() {
        super();// 继承JFrame
        initialize();
    }
    private void initialize() {// ʼķ
        this.setSize(800, 600);
        this.setJMenuBar(getFrameMenuBar());
        this.setContentPane(getFrameContentPane());
        this.setTitle("企业进销存系统");
    }
    private JPanel getFrameContentPane() {
        if (frameContentPane == null) {
            frameContentPane = new JPanel();
            frameContentPane.setLayout(new BorderLayout());
            //添加组件位置
            frameContentPane.add(getStatePanel(), SOUTH);
            frameContentPane.add(getJJToolBar(), NORTH);
            frameContentPane.add(getDesktopPane(), CENTER);
        }
        return frameContentPane;
    }
}