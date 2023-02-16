package com.mingrisoft;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
//便捷工具栏的功能确定和点击按钮之后运用MenuBar的初始化调用各种功能
public class ToolBar extends JToolBar {

    private MenuBar menuBar;
    private ToolBar() {
    }
    public ToolBar(MenuBar frameMenuBar) {
        super();
        this.menuBar = frameMenuBar;
        initialize();
    }
    //初始化
    private void initialize() {
        setSize(new Dimension(600, 24));//宽和高
        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));// 具有“浮雕化”外观效果的边框(效果为凹陷)
        add(createToolButton(menuBar.getJinhuoItem()));
        add(createToolButton(menuBar.getXiaoshou_danItem()));
        add(createToolButton(menuBar.getKucun_pandianItem()));
        add(createToolButton(menuBar.getJiage_tiaozhengItem()));
        add(createToolButton(menuBar.getShangpin_chaxunItem()));
        add(createToolButton(menuBar.getShangpin_guanliItem()));
        add(createToolButton(menuBar.getKehu_guanliItem()));
        add(createToolButton(menuBar.getGys_guanliItem()));
        add(createToolButton(menuBar.getExitItem()));
    }
    private JButton createToolButton(final JMenuItem item) {
        JButton button = new JButton();
        button.setText(item.getText());//返回的Button上面的文字到外面的TextView
        button.setToolTipText(item.getText());//光标放在图标上会有提示信息（重点）
        button.setIcon(item.getIcon());// SetIcon图标将会被自动地放到按钮的上面，item.getIcon()目标图片
        button.setFocusable(false);// 按钮不是焦点状态
        button.addActionListener(new java.awt.event.ActionListener() {// 监听
            public void actionPerformed(java.awt.event.ActionEvent e) {
                item.doClick();//点击按钮事件
            }
        });
        return button;
    }
    public void setMenuBar(MenuBar menuBar) {
        this.menuBar = menuBar;
    }
}
