package com.mingrisoft.iframe.keHuGuanLi;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

import keyListener.InputKeyListener;

import com.mingrisoft.dao.Dao;
import com.mingrisoft.dao.model.TbKhinfo;

public class KeHuTianJiaPanel extends JPanel {// 客户添加面板
    private JTextField keHuQuanCheng;
    private JTextField yinHangZhangHao;
    private JTextField kaiHuYinHang;
    private JTextField EMail;
    private JTextField lianXiDianHua;
    private JTextField lianXiRen;
    private JTextField chuanZhen;
    private JTextField dianHua;
    private JTextField youZhengBianMa;
    private JTextField diZhi;
    private JTextField keHuJianCheng;
    private JButton resetButton;
    public KeHuTianJiaPanel() {// 客户添加面板
        super();
        setBounds(10, 10, 460, 300);// 设置客户添加面板的位置与宽高
        setLayout(new GridBagLayout());// 设置客户添加面板的布局为网格布局
        setVisible(true);// 使客户添加面板可见
        // 客户全称
        final JLabel khName = new JLabel();
        khName.setText("客户全称：");
        setupComponet(khName, 0, 0, 1, 0, false);
        keHuQuanCheng = new JTextField();
        setupComponet(keHuQuanCheng, 1, 0, 3, 350, true);
        //客户地址
        final JLabel addressLabel = new JLabel("客户地址：");
        setupComponet(addressLabel, 0, 1, 1, 0, false);
        diZhi = new JTextField();
        setupComponet(diZhi, 1, 1, 3, 0, true);
        //客户简称
        final JLabel jc = new JLabel();
        jc.setText("客户简称：");
        setupComponet(jc, 0, 2, 1, 0, false);
        keHuJianCheng = new JTextField();
        setupComponet(keHuJianCheng, 1, 2, 1, 100, true);
        //邮政编码
        setupComponet(new JLabel("邮政编码："), 2, 2, 1, 0, false);
        youZhengBianMa = new JTextField();
        setupComponet(youZhengBianMa, 3, 2, 1, 100, true);
        youZhengBianMa.addKeyListener(new InputKeyListener());
        //电话
        setupComponet(new JLabel("电话："), 0, 3, 1, 0, false);
        dianHua = new JTextField();
        setupComponet(dianHua, 1, 3, 1, 100, true);
        dianHua.addKeyListener(new InputKeyListener());// 为“电话”文本框添加键盘输入事件的监听
        //传真
        setupComponet(new JLabel("传真："), 2, 3, 1, 0, false);
        chuanZhen = new JTextField();
        chuanZhen.addKeyListener(new InputKeyListener());
        setupComponet(chuanZhen, 3, 3, 1, 100, true);
        //联系人
        setupComponet(new JLabel("联系人："), 0, 4, 1, 0, false);
        lianXiRen = new JTextField();
        setupComponet(lianXiRen, 1, 4, 1, 100, true);
        //联系电话
        setupComponet(new JLabel("联系电话："), 2, 4, 1, 0, false);
        lianXiDianHua = new JTextField();
        setupComponet(lianXiDianHua, 3, 4, 1, 100, true);
        lianXiDianHua.addKeyListener(new InputKeyListener());// 为“联系电话”文本框添加键盘输入事件的监听
        //E-Mail
        setupComponet(new JLabel("E-Mail："), 0, 5, 1, 0, false);
        EMail = new JTextField();
        setupComponet(EMail, 1, 5, 3, 350, true);
        //开户银行
        setupComponet(new JLabel("开户银行："), 0, 6, 1, 0, false);
        kaiHuYinHang = new JTextField();
        setupComponet(kaiHuYinHang, 1, 6, 1, 100, true);
        //银行账号
        setupComponet(new JLabel("银行账号："), 2, 6, 1, 0, false);
        yinHangZhangHao = new JTextField();
        setupComponet(yinHangZhangHao, 3, 6, 1, 100, true);
        //保存按钮
        final JButton saveButton = new JButton("保存");
        setupComponet(saveButton, 1, 7, 1, 0, false);
        saveButton.addActionListener(new SaveButtonActionListener());// 为“保存”按钮添加动作事件的监听
        // 重置按钮
        resetButton = new JButton("重置");
        setupComponet(resetButton, 3, 7, 1, 0, false);
        resetButton.addActionListener(new ChongZheButtonActionListener());// 为“重置”按钮添加动作事件的监听
    }
    // 设置组件位置并添加到容器中
    private void setupComponet(JComponent component, int gridx, int gridy, int gridwidth, int ipadx, boolean fill) {
        final GridBagConstraints gridBagConstrains = new GridBagConstraints();// 创建网格限制对象
        gridBagConstrains.gridx = gridx;
        gridBagConstrains.gridy = gridy;
        gridBagConstrains.insets = new Insets(5, 1, 3, 1);
        if (gridwidth > 1)// 组件横跨网格数大于1
            gridBagConstrains.gridwidth = gridwidth;
        if (ipadx > 0)// 组件横向填充的大小大于0
            gridBagConstrains.ipadx = ipadx;// 设置组件横向填充的大小
        if (fill)// 组件占据空白区域
            gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;// 组件水平扩大以占据空白区域
        add(component, gridBagConstrains);// 添加组件
    }
    private final class SaveButtonActionListener implements ActionListener {// “保存”按钮动作事件的监听
        public void actionPerformed(final ActionEvent e) {
            // 文本框为空时，弹出提示框
            if (diZhi.getText().equals("") || youZhengBianMa.getText().equals("") || chuanZhen.getText().equals("")
                    || yinHangZhangHao.getText().equals("") || keHuJianCheng.getText().equals("")
                    || keHuQuanCheng.getText().equals("") || lianXiRen.getText().equals("")
                    || lianXiDianHua.getText().equals("") || EMail.getText().equals("") || dianHua.getText().equals("")
                    || kaiHuYinHang.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "请填写全部信息");
                return;// 退出应用程序
            }
            // 执行SQL查询语句是否存在同名客户
            ResultSet haveUser=Dao.query("select * from tb_khinfo where khname='"+keHuQuanCheng.getText().trim()+"'");
            try {
                if (haveUser.next()) {// 结果集haveUser中有超过一条的记录
                    System.out.println("error");// 控制台输出error
                    JOptionPane.showMessageDialog(KeHuTianJiaPanel.this, "客户信息添加失败，存在同名客户",
                            "客户添加信息",
                            JOptionPane.INFORMATION_MESSAGE);// 弹出提示框
                    return;
                }
            } catch (Exception er) {
                er.printStackTrace();
            }
            ResultSet set = Dao.query("select max(id) from tb_khinfo");// 执行SQL查询语句获得的结果集
            String id = null;// 声明客户编号
            try {
                if (set != null && set.next()) {// 结果集set不为空且结果集set中有超过一条的记录
                    String sid = set.getString(1);// 获得结果集set中的第一列数据值
                    if (sid == null)// 第一列数据值为空
                        id = "kh1001";// 为产品编号赋值
                    else {
                        String str = sid.substring(2);// 从索引为2处开始截取字符串
                        id = "kh" + (Integer.parseInt(str) + 1);// 重新拼接字符串获得产品编号
                    }
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            TbKhinfo khinfo = new TbKhinfo();// 客户信息
            khinfo.setId(id);// 设置商品编号
            khinfo.setAddress(diZhi.getText().trim());// 设置客户地址
            khinfo.setBianma(youZhengBianMa.getText().trim());// 设置邮编
            khinfo.setFax(chuanZhen.getText().trim());// 设置传真
            khinfo.setHao(yinHangZhangHao.getText().trim());// 设置银行账号
            khinfo.setJian(keHuJianCheng.getText().trim());// 设置客户简称
            khinfo.setKhname(keHuQuanCheng.getText().trim());// 设置客户名称
            khinfo.setLian(lianXiRen.getText().trim());// 设置联系人
            khinfo.setLtel(lianXiDianHua.getText().trim());// 设置联系电话
            khinfo.setMail(EMail.getText().trim());// 设置电子邮箱
            khinfo.setTel(dianHua.getText().trim());// 设置电话
            khinfo.setXinhang(kaiHuYinHang.getText());// 设置开户银行
            Dao.addKeHu(khinfo);// 添加客户信息
            JOptionPane.showMessageDialog(KeHuTianJiaPanel.this, "已成功添加客户", "客户添加信息",
                    JOptionPane.INFORMATION_MESSAGE);// 弹出提示框
            //自动重置
            resetButton.doClick();
        }
    }
    // “重置”按钮动作事件的监听
    private class ChongZheButtonActionListener implements ActionListener {
        public void actionPerformed(final ActionEvent e) {
            // 设置文本框的内容为空
            keHuQuanCheng.setText("");
            yinHangZhangHao.setText("");
            kaiHuYinHang.setText("");
            EMail.setText("");
            lianXiDianHua.setText("");
            lianXiRen.setText("");
            chuanZhen.setText("");
            dianHua.setText("");
            youZhengBianMa.setText("");
            diZhi.setText("");
            keHuJianCheng.setText("");
        }
    }
}