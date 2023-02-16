package com.mingrisoft;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.lang.reflect.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import com.mingrisoft.iframe.*;
//编写各种功能的初始化 确定menuBar有什么功能，每个功能包括哪些功能
public class MenuBar extends JMenuBar {
    //JMenu菜单，JMenuItem菜单中的实际项目
    private JMenu jinhuo_Menu = null;
    private JMenu xiaoshou_Menu = null;
    private JMenu kucun_Menu = null;
    private JMenu xinxi_chaxunMenu = null;
    private JMenu jiben_ziliaoMenu = null;
    private JMenu xitong_weihuMenu = null;
    private JMenu chuang_kouMenu = null;
    private JMenu bang_zhuMenu = null;
    private JMenuItem jinhuoItem = null;
    private JMenuItem jinhuo_tuihuoItem = null;
    private JMenuItem guanyu_Item = null;
    private JMenuItem bugItem = null;
    private JMenuItem fangwen_wangzhanItem = null;
    private JMenuItem xiaoshou_danItem = null;
    private JMenuItem xiaoshou_tuihuoItem = null;
    private JMenuItem kucun_pandianItem = null;
    private JMenuItem jiage_tiaozhengItem = null;
    private JMenuItem xiaoshou_chaxunItem = null;
    private JMenuItem shangpin_chaxunItem = null;
    private JMenuItem xiaoshou_paihangItem = null;
    private JMenuItem shangpin_guanliItem = null;
    private JMenuItem kehu_guanliItem = null;
    private JMenuItem gys_guanliItem = null;
    private JMenuItem jsr_guanliItem = null;
    private JMenuItem mima_xiugaiItem = null;
    private JMenuItem shuju_beifenItem = null;
    private JMenuItem exitItem = null;
    private JMenuItem pingpuItem = null;
    private JMenuItem closeAllItem = null;
    private JMenuItem allIconItem = null;
    private JMenuItem allResumeItem = null;
    private JLabel stateLabel = null;//标签 利用标签(JLabel)可以在图形用户界面上显示一个字符串或一幅图
    private JDesktopPane desktopPanel = null;//多文档容器
    private Map<JMenuItem, JInternalFrame> iFrames = null;
    private int nextFrameX, nextFrameY;
    private MenuBar() {
    }
    public MenuBar(JDesktopPane desktopPanel, JLabel label) {//初始化
        super();
        iFrames = new HashMap<JMenuItem, JInternalFrame>();
        this.desktopPanel = desktopPanel;
        this.stateLabel = label;
        initialize();
    }
    private void initialize() {//确定功能
        this.setSize(new Dimension(600, 24));
        add(getJinhuo_Menu());
        add(getXiaoshou_Menu());
        add(getKucun_Menu());
        add(getXinxi_chaxunMenu());
        add(getJiben_ziliaoMenu());
        add(getXitong_weihuMenu());
        add(getChuang_kouMenu());
        add(getBang_zhuMenu());
    }
    public JMenu getJinhuo_Menu() {
        if (jinhuo_Menu == null) {
            jinhuo_Menu = new JMenu();
            jinhuo_Menu.setText("进货管理");//名称
            jinhuo_Menu.setMnemonic(KeyEvent.VK_J);//设置键位，大小写的j都行
            //进货管理分为进货单和进货退货
            jinhuo_Menu.add(getJinhuoItem());
            jinhuo_Menu.add(getJinhuo_tuihuoItem());
        }
        return jinhuo_Menu;
    }
    public JMenuItem getJinhuoItem() {
        if (jinhuoItem == null) {
            jinhuoItem = new JMenuItem();
            jinhuoItem.setText("进货单");
            jinhuoItem.setIcon(new ImageIcon(getClass().getResource("/res/icon/jinhuodan.png")));
            jinhuoItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createIFrame(jinhuoItem, JinHuoDan_IFrame.class);
                }
            });
        }
        return jinhuoItem;
    }
    public JMenuItem getJinhuo_tuihuoItem() {
        if (jinhuo_tuihuoItem == null) {
            jinhuo_tuihuoItem = new JMenuItem();
            jinhuo_tuihuoItem.setText("进货-退货");
            jinhuo_tuihuoItem.setIcon(new ImageIcon(getClass().getResource("/res/icon/jinhuo_tuihuo.png")));
            jinhuo_tuihuoItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createIFrame(jinhuo_tuihuoItem, JinHuoTuiHuo.class);
                }
            });
        }
        return jinhuo_tuihuoItem;
    }
    public JMenu getXiaoshou_Menu() {
        if (xiaoshou_Menu == null) {
            xiaoshou_Menu = new JMenu();
            xiaoshou_Menu.setText("销售管理");
            xiaoshou_Menu.setMnemonic(KeyEvent.VK_X);
            xiaoshou_Menu.add(getXiaoshou_danItem());
            xiaoshou_Menu.add(getXiaoshou_tuihuoItem());
        }
        return xiaoshou_Menu;
    }
    public JMenu getKucun_Menu() {
        if (kucun_Menu == null) {
            kucun_Menu = new JMenu();
            kucun_Menu.setText("库存盘点");
            kucun_Menu.setMnemonic(KeyEvent.VK_K);
            kucun_Menu.add(getKucun_pandianItem());
            kucun_Menu.add(getJiage_tiaozhengItem());
        }
        return kucun_Menu;
    }
    public JMenu getXinxi_chaxunMenu() {
        if (xinxi_chaxunMenu == null) {
            xinxi_chaxunMenu = new JMenu();
            xinxi_chaxunMenu.setText("信息查询");
            xinxi_chaxunMenu.setMnemonic(KeyEvent.VK_C);
            xinxi_chaxunMenu.add(getXiaoshou_chaxunItem());
            xinxi_chaxunMenu.add(getShangpin_chaxunItem());
            xinxi_chaxunMenu.addSeparator();
            xinxi_chaxunMenu.add(getXiaoshou_paihangItem());
        }
        return xinxi_chaxunMenu;
    }
    public JMenu getJiben_ziliaoMenu() {
        if (jiben_ziliaoMenu == null) {
            jiben_ziliaoMenu = new JMenu();
            jiben_ziliaoMenu.setText("基本资料");
            jiben_ziliaoMenu.setMnemonic(KeyEvent.VK_B);
            jiben_ziliaoMenu.add(getShangpin_guanliItem());
            jiben_ziliaoMenu.add(getKehu_guanliItem());
            jiben_ziliaoMenu.add(getGys_guanliItem());
            jiben_ziliaoMenu.addSeparator();
            jiben_ziliaoMenu.add(getJsr_guanliItem());
        }
        return jiben_ziliaoMenu;
    }
    public JMenu getXitong_weihuMenu() {
        if (xitong_weihuMenu == null) {
            xitong_weihuMenu = new JMenu();
            xitong_weihuMenu.setText("系统维护");
            xitong_weihuMenu.setMnemonic(KeyEvent.VK_S);
            xitong_weihuMenu.add(getShuju_beifenItem());
            xitong_weihuMenu.addSeparator();//分割线
            xitong_weihuMenu.add(getMima_xiugaiItem());
            xitong_weihuMenu.addSeparator();
            xitong_weihuMenu.add(getExitItem());
        }
        return xitong_weihuMenu;
    }
    public JMenu getChuang_kouMenu() {
        if (chuang_kouMenu == null) {
            chuang_kouMenu = new JMenu();
            chuang_kouMenu.setText("窗口");
            chuang_kouMenu.setMnemonic(KeyEvent.VK_W);
            //添加监听
            chuang_kouMenu.addMenuListener(new MenuListener() {
                public void menuSelected(MenuEvent e) {
                    chuang_kouMenu.removeAll();
                    chuang_kouMenu.add(getPingpuItem());
                    chuang_kouMenu.add(getClassAllItem());
                    chuang_kouMenu.add(getAllIconItem());
                    chuang_kouMenu.add(getAllResumeItem());
                    chuang_kouMenu.addSeparator();
                    int count = 0;
                    //获得桌面面板中拥有内部窗体的数量
                    JInternalFrame[] allFrames = desktopPanel.getAllFrames();
                    //每一个窗口都循环一次
                    //窗口可以选择已打开的所有窗口
                    for (final JInternalFrame frame : allFrames) {
                        String frameTitle = frame.getTitle();
                        count++;//记录窗口数
                        final JMenuItem item = new JMenuItem(count + "  " + frameTitle);//命令式菜单
                        chuang_kouMenu.add(item);
                        item.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    frame.setIcon(false);
                                    frame.setSelected(true);
                                } catch (PropertyVetoException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        });
                    }
                }
                //关闭窗口
                public void menuDeselected(MenuEvent e) {
                }
                //取消窗口
                public void menuCanceled(MenuEvent e) {
                }
            });
        }
        return chuang_kouMenu;
    }
    public JMenu getBang_zhuMenu() {
        if (bang_zhuMenu == null) {
            bang_zhuMenu = new JMenu();
            bang_zhuMenu.setText("帮助");
            bang_zhuMenu.setMnemonic(KeyEvent.VK_H);
            bang_zhuMenu.add(getGuanyu_Item());
            bang_zhuMenu.add(getBugItem());
            bang_zhuMenu.add(getFangwen_wangzhanItem());
        }
        return bang_zhuMenu;
    }
    //帮助中的关于功能
    private JMenuItem getGuanyu_Item() {
        if (guanyu_Item == null) {
            guanyu_Item = new JMenuItem();
            guanyu_Item.setText("关于");
            guanyu_Item.setIcon(new ImageIcon(getClass().getResource("/res/icon/guanyu.png")));//加载关于图标
            //界面背景
            URL url = DesktopPanel.class.getResource("/res/about.jpg");
            ImageIcon aboutImage = new ImageIcon(url);
            final JLabel imgLabel = new JLabel(aboutImage);
            imgLabel.setVisible(false);//隐藏
            desktopPanel.add(imgLabel);
            desktopPanel.setLayer(imgLabel, Integer.MAX_VALUE);
            guanyu_Item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int dw = desktopPanel.getWidth();
                    int dh = desktopPanel.getHeight();
                    imgLabel.setBounds((dw - 500) / 2, (dh - 350) / 2, 500, 350);
                    imgLabel.setVisible(true);
                }
            });
            imgLabel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    imgLabel.setVisible(false);
                }
            });
        }
        return guanyu_Item;
    }
    //帮助中的技术支持功能
    public JMenuItem getBugItem() {
        if (bugItem == null) {
            bugItem = new JMenuItem();
            bugItem.setText("技术支持");
            bugItem.setIcon(new ImageIcon(getClass().getResource("/res/icon/jishu_zhichi.png")));
            bugItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (Desktop.isDesktopSupported()) {
                        Desktop desktop = Desktop.getDesktop();
                        try {
                            URI uri = new URI("mailto:tmoonbook@sina.com");
                            desktop.mail(uri);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            });
        }
        return bugItem;
    }
    //帮助中的访问网站功能
    public JMenuItem getFangwen_wangzhanItem() {
        if (fangwen_wangzhanItem == null) {
            fangwen_wangzhanItem = new JMenuItem();
            fangwen_wangzhanItem.setText("访问网站");
            fangwen_wangzhanItem.setIcon(new ImageIcon(getClass().getResource("/res/icon/jishu_wangzhan.png")));
            fangwen_wangzhanItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (Desktop.isDesktopSupported()) {
                        Desktop desktop = Desktop.getDesktop();
                        try {
                            URL url = new URL("http://www.mrbccd.com");
                            desktop.browse(url.toURI());
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            });
        }
        return fangwen_wangzhanItem;
    }
    //销售管理的销售单功能
    public JMenuItem getXiaoshou_danItem() {
        if (xiaoshou_danItem == null) {
            xiaoshou_danItem = new JMenuItem();
            xiaoshou_danItem.setText("销售单");
            xiaoshou_danItem.setIcon(new ImageIcon(getClass().getResource("/res/icon/xiaoshoudan.png")));
            xiaoshou_danItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createIFrame(xiaoshou_danItem, XiaoShouDan.class);
                }
            });
        }
        return xiaoshou_danItem;
    }
    //销售管理的销售退货功能
    public JMenuItem getXiaoshou_tuihuoItem() {
        if (xiaoshou_tuihuoItem == null) {
            xiaoshou_tuihuoItem = new JMenuItem();
            xiaoshou_tuihuoItem.setText("销售-退货");
            xiaoshou_tuihuoItem.setIcon(new ImageIcon(getClass().getResource("/res/icon/xiaoshou_tuihuo.png")));
            xiaoshou_tuihuoItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createIFrame(xiaoshou_tuihuoItem, XiaoShouTuiHuo.class);
                }
            });
        }
        return xiaoshou_tuihuoItem;
    }
    //库存管理的库存盘点功能
    public JMenuItem getKucun_pandianItem() {
        if (kucun_pandianItem == null) {
            kucun_pandianItem = new JMenuItem();
            kucun_pandianItem.setText("库存盘点");
            kucun_pandianItem.setIcon(new ImageIcon(getClass().getResource("/res/icon/kucun_pandian.png")));
            kucun_pandianItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createIFrame(kucun_pandianItem, KuCunPanDian.class);
                }
            });
        }
        return kucun_pandianItem;
    }
    //库存盘点的价格调整功能
    public JMenuItem getJiage_tiaozhengItem() {
        if (jiage_tiaozhengItem == null) {
            jiage_tiaozhengItem = new JMenuItem();
            jiage_tiaozhengItem.setText("价格调整");
            jiage_tiaozhengItem.setIcon(new ImageIcon(getClass().getResource("/res/icon/jiage_tiaozheng.png")));
            jiage_tiaozhengItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createIFrame(jiage_tiaozhengItem, JiaGeTiaoZheng.class);
                }
            });
        }
        return jiage_tiaozhengItem;
    }
    //信息查询的销售查询
    public JMenuItem getXiaoshou_chaxunItem() {
        if (xiaoshou_chaxunItem == null) {
            xiaoshou_chaxunItem = new JMenuItem();
            xiaoshou_chaxunItem.setText("销售查询");
            xiaoshou_chaxunItem.setIcon(new ImageIcon(getClass().getResource("/res/icon/xiaoshou_chaxun.png")));
            xiaoshou_chaxunItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createIFrame(xiaoshou_chaxunItem, XiaoShouChaXun.class);
                }
            });
        }
        return xiaoshou_chaxunItem;
    }
    //信息查询的商品查询
    public JMenuItem getShangpin_chaxunItem() {
        if (shangpin_chaxunItem == null) {
            shangpin_chaxunItem = new JMenuItem();
            shangpin_chaxunItem.setText("商品查询");
            shangpin_chaxunItem.setIcon(new ImageIcon(getClass().getResource("/res/icon/shangpin_chaxun.png")));
            shangpin_chaxunItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createIFrame(shangpin_chaxunItem, ShangPinChaXun.class);
                }
            });
        }
        return shangpin_chaxunItem;
    }
    //信息查询的销售排行
    public JMenuItem getXiaoshou_paihangItem() {
        if (xiaoshou_paihangItem == null) {
            xiaoshou_paihangItem = new JMenuItem();
            xiaoshou_paihangItem.setText("销售排行");
            xiaoshou_paihangItem.setIcon(new ImageIcon(getClass().getResource("/res/icon/xiaoshou_paihang.png")));
            xiaoshou_paihangItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createIFrame(xiaoshou_paihangItem, XiaoShouPaiHang.class);
                }
            });
        }
        return xiaoshou_paihangItem;
    }
    //基本信息的商品管理
    public JMenuItem getShangpin_guanliItem() {
        if (shangpin_guanliItem == null) {
            shangpin_guanliItem = new JMenuItem();
            shangpin_guanliItem.setText("商品管理");
            shangpin_guanliItem.setIcon(new ImageIcon(getClass().getResource("/res/icon/shangpin_guanli.png")));
            shangpin_guanliItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createIFrame(shangpin_guanliItem, ShangPinGuanLi.class);
                }
            });
        }
        return shangpin_guanliItem;
    }
    //基本信息的客户管理
    public JMenuItem getKehu_guanliItem() {
        if (kehu_guanliItem == null) {
            kehu_guanliItem = new JMenuItem();
            kehu_guanliItem.setText("客户管理");
            kehu_guanliItem.setIcon(new ImageIcon(getClass().getResource("/res/icon/kehu_guanli.png")));
            kehu_guanliItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createIFrame(kehu_guanliItem, KeHuGuanLi.class);
                }
            });
        }
        return kehu_guanliItem;
    }
    //基本信息的供应商管理
    public JMenuItem getGys_guanliItem() {
        if (gys_guanliItem == null) {
            gys_guanliItem = new JMenuItem();
            gys_guanliItem.setText("供应商管理");
            gys_guanliItem.setIcon(new ImageIcon(getClass().getResource("/res/icon/gys_guanli.png")));
            gys_guanliItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createIFrame(gys_guanliItem, GysGuanLi.class);
                }
            });
        }
        return gys_guanliItem;
    }
    //基本信息的经手人管理
    public JMenuItem getJsr_guanliItem() {
        if (jsr_guanliItem == null) {
            jsr_guanliItem = new JMenuItem();
            jsr_guanliItem.setText("经手人管理");
            jsr_guanliItem.setIcon(new ImageIcon(getClass().getResource("/res/icon/jsr_shezhi.png")));
            jsr_guanliItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createIFrame(jsr_guanliItem, JsrGL.class);
                }
            });
        }
        return jsr_guanliItem;
    }
    //系统维护的密码管理
    public JMenuItem getMima_xiugaiItem() {
        if (mima_xiugaiItem == null) {
            mima_xiugaiItem = new JMenuItem();
            mima_xiugaiItem.setText("密码管理");
            mima_xiugaiItem.setIcon(new ImageIcon(getClass().getResource("/res/icon/mima_xiugai.png")));
            mima_xiugaiItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createIFrame(mima_xiugaiItem, GengGaiMiMa.class);
                }
            });
        }
        return mima_xiugaiItem;
    }
    //系统维护的数据备份
    public JMenuItem getShuju_beifenItem() {
        if (shuju_beifenItem == null) {
            shuju_beifenItem = new JMenuItem();
            shuju_beifenItem.setText("数据备份");
            shuju_beifenItem.setIcon(new ImageIcon(getClass().getResource("/res/icon/shujuku_beifen_huifu.png")));
            shuju_beifenItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createIFrame(shuju_beifenItem, BackupAndRestore.class);
                }
            });
        }
        return shuju_beifenItem;
    }
    //系统维护的退出系统
    public JMenuItem getExitItem() {
        if (exitItem == null) {
            exitItem = new JMenuItem();
            exitItem.setText("退出系统");
            exitItem.setIcon(new ImageIcon(getClass().getResource("/res/icon/tuichu_xitong.png")));
            exitItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
        }
        return exitItem;
    }
    //窗口的平铺
    public JMenuItem getPingpuItem() {
        if (pingpuItem == null) {
            pingpuItem = new JMenuItem();
            pingpuItem.setText("平铺");
            pingpuItem.setIcon(new ImageIcon(getClass().getResource("/res/icon/chuangkou_pingpu.png")));
            pingpuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JInternalFrame[] allFrames = desktopPanel.getAllFrames();
                    int x = 0, y = 0;
                    for (JInternalFrame iFrame : allFrames) {
                        iFrame.setLocation(x, y);
                        try {
                            iFrame.setSelected(true);
                        } catch (PropertyVetoException e1) {
                            e1.printStackTrace();
                        }
                        int frameH = iFrame.getPreferredSize().height;//容器欧的首选高度
                        int panelH = iFrame.getContentPane().getPreferredSize().height;//返回此内部框架的内容窗格首选高度。
                        int fSpacing = frameH - panelH;//高度差
                        x += fSpacing;
                        y += fSpacing;
                        //当x/y>屏幕，下一次就换行/换列放窗口
                        if (x + getWidth() / 2 > desktopPanel.getWidth())
                            x = 0;
                        if (y + getHeight() / 2 > desktopPanel.getHeight())
                            y = 0;
                    }
                }
            });
        }
        return pingpuItem;
    }
    //创建窗口功能
    private JInternalFrame createIFrame(JMenuItem item, Class clazz) {
        Constructor constructor = clazz.getConstructors()[0];//反射-获取Constructor对象
        JInternalFrame iFrame = iFrames.get(item);//跟JFrame一样,屏幕
        try {
            if (iFrame == null || iFrame.isClosed()) {//屏幕不存在或关闭时
                iFrame = (JInternalFrame) constructor.newInstance(new Object[] {});//通过对应的方法创建窗口
                iFrames.put(item, iFrame);//调用put使输出iFrame
                iFrame.setFrameIcon(item.getIcon());//设置窗体标题栏显示的图标
                iFrame.setLocation(nextFrameX, nextFrameY);//设置窗体位置
                int frameH = iFrame.getPreferredSize().height;
                int panelH = iFrame.getContentPane().getPreferredSize().height;
                int fSpacing = frameH - panelH;
                nextFrameX += fSpacing;
                nextFrameY += fSpacing;
                if (nextFrameX + iFrame.getWidth() > desktopPanel.getWidth())
                    nextFrameX = 0;
                if (nextFrameY + iFrame.getHeight() > desktopPanel.getHeight())
                    nextFrameY = 0;
                desktopPanel.add(iFrame);//添加文档
                iFrame.setResizable(false);//不可以变大变小
                iFrame.setMaximizable(false);//不可以最大化
                iFrame.setVisible(true);//显示屏幕
            }
            iFrame.setSelected(true);
            stateLabel.setText(iFrame.getTitle());
            iFrame.addInternalFrameListener(new InternalFrameAdapter() {
                public void internalFrameActivated(InternalFrameEvent e) {
                    super.internalFrameActivated(e);
                    JInternalFrame frame = e.getInternalFrame();
                    stateLabel.setText(frame.getTitle());
                }

                public void internalFrameDeactivated(InternalFrameEvent e) {
                    stateLabel.setText("AAAA");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iFrame;
    }
    //窗口的全部关闭
    private JMenuItem getClassAllItem() {
        if (closeAllItem == null) {
            closeAllItem = new JMenuItem();
            closeAllItem.setText("全部关闭");
            closeAllItem.setIcon(new ImageIcon(getClass().getResource("/res/icon/quanbu_guanbi.png")));
            closeAllItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JInternalFrame[] allFrames = desktopPanel.getAllFrames();
                    for (JInternalFrame frame : allFrames) {//循环所有打开的窗口
                        frame.doDefaultCloseAction();//关闭窗口
                    }
                }
            });
        }
        return closeAllItem;
    }
    //窗口的全部最小化
    private JMenuItem getAllIconItem() {
        if (allIconItem == null) {
            allIconItem = new JMenuItem();
            allIconItem.setText("全部最小化");
            allIconItem.setIcon(new ImageIcon(getClass().getResource("/res/icon/quanbu_zuixiaohua.png")));
            allIconItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JInternalFrame[] allFrames = desktopPanel.getAllFrames();
                    for (JInternalFrame frame : allFrames) {
                        try {
                            frame.setIcon(true);//窗口图标化
                        } catch (PropertyVetoException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            });
        }
        return allIconItem;
    }
    //窗口的全部还原
    private JMenuItem getAllResumeItem() {
        if (allResumeItem == null) {
            allResumeItem = new JMenuItem();
            allResumeItem.setText("全部还原");
            allResumeItem.setIcon(new ImageIcon(getClass().getResource("/res/icon/quanbu_huanyuan.png")));
            allResumeItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JInternalFrame[] allFrames = desktopPanel.getAllFrames();
                    for (JInternalFrame frame : allFrames) {
                        try {
                            frame.setIcon(false);//取消窗口图标化
                        } catch (PropertyVetoException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            });
        }
        return allResumeItem;
    }
}
