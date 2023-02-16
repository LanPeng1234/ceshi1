package com.mingrisoft.iframe.czyGl;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.mingrisoft.dao.Dao;
import com.mingrisoft.dao.model.TbJsr;

public class TJJingShouRen extends JPanel {// 添加经手人面板
    private JTextField jsrName;
    private JTextField tel;
    private JTextField age;
    private JComboBox sex;
    private void clear() {// 清除文本框内容的方法
        // 设置文本框的内容为空
        jsrName.setText(null);
        tel.setText(null);
        age.setText(null);
    }

    public TJJingShouRen() {// 添加经手人面板
        super();
        setLayout(new GridBagLayout());
        setBounds(0, 0, 280, 236);
        //姓名
        final JLabel label_4 = new JLabel();
        label_4.setFont(new Font("", Font.PLAIN, 14));// 设置“姓名”标签内字体的样式和大小
        label_4.setText("姓名：");
        final GridBagConstraints gridBagConstraints_10 = new GridBagConstraints();
        gridBagConstraints_10.gridx = 0;
        gridBagConstraints_10.gridy = 0;
        add(label_4, gridBagConstraints_10);
        jsrName = new JTextField();
        final GridBagConstraints gridBagConstraints_11 = new GridBagConstraints();
        gridBagConstraints_11.weighty = 1.0;
        gridBagConstraints_11.insets = new Insets(0, 0, 0, 10);
        gridBagConstraints_11.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints_11.gridwidth = 2;
        gridBagConstraints_11.gridy = 0;
        gridBagConstraints_11.gridx = 1;
        add(jsrName, gridBagConstraints_11);
        //性别
        final JLabel label = new JLabel();
        label.setFont(new Font("", Font.PLAIN, 14));
        label.setText("性别：");
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        add(label, gridBagConstraints);
        sex = new JComboBox();// “性别”下拉列表
        sex.addItem("男");
        sex.addItem("女");
        final GridBagConstraints gridBagConstraints_1 = new GridBagConstraints();
        gridBagConstraints_1.weightx = 1.0;
        gridBagConstraints_1.weighty = 1.0;
        gridBagConstraints_1.ipadx = -250;
        gridBagConstraints_1.insets = new Insets(0, 0, 0, 10);
        gridBagConstraints_1.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints_1.gridwidth = 2;
        gridBagConstraints_1.gridy = 1;
        gridBagConstraints_1.gridx = 1;
        add(sex, gridBagConstraints_1);
        //年龄
        final JLabel label_2 = new JLabel();
        label_2.setFont(new Font("", Font.PLAIN, 14));
        label_2.setText("年龄：");
        final GridBagConstraints gridBagConstraints_4 = new GridBagConstraints();
        gridBagConstraints_4.gridy = 2;
        gridBagConstraints_4.gridx = 0;
        add(label_2, gridBagConstraints_4);
        age = new JTextField();
        final GridBagConstraints gridBagConstraints_5 = new GridBagConstraints();
        gridBagConstraints_5.weighty = 1.0;
        gridBagConstraints_5.insets = new Insets(0, 0, 0, 10);
        gridBagConstraints_5.fill = GridBagConstraints.HORIZONTAL;// 组件水平扩大以占据空白区域
        gridBagConstraints_5.gridwidth = 2;
        gridBagConstraints_5.gridy = 2;
        gridBagConstraints_5.gridx = 1;
        add(age, gridBagConstraints_5);
        //电话
        final JLabel label_3 = new JLabel();
        label_3.setFont(new Font("", Font.PLAIN, 14));
        label_3.setText("电话：");
        final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
        gridBagConstraints_6.gridy = 3;
        gridBagConstraints_6.gridx = 0;
        add(label_3, gridBagConstraints_6);
        tel = new JTextField();
        final GridBagConstraints gridBagConstraints_7 = new GridBagConstraints();
        gridBagConstraints_7.weighty = 1.0;
        gridBagConstraints_7.insets = new Insets(0, 0, 0, 10);
        gridBagConstraints_7.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints_7.gridwidth = 2;
        gridBagConstraints_7.gridy = 3;
        gridBagConstraints_7.gridx = 1;
        add(tel, gridBagConstraints_7);
        //添加
        final JButton button = new JButton("添加");
        button.addActionListener(new ActionListener() {// “添加”按钮动作事件的监听
            public void actionPerformed(final ActionEvent e) {
                String nameStr = jsrName.getText();// 获得“姓名”文本框中的文本内容
                if (nameStr == null || nameStr.isEmpty())
                    return;// 退出应用程序
                String ageStr = new String(age.getText());// 获得“年龄”文本框中的文本内容
                TbJsr user = Dao.getJsr(nameStr, ageStr);// 经手人信息
                if (user.getSex() != null && !user.getSex().isEmpty()) {// 经手人的性别不为空且经手人表示性别的字符串的长度不为0
                    JOptionPane.showMessageDialog(TJJingShouRen.this, "经手人(" + user.getName() +
                            ")已经存在");
                    sex.setFocusable(true);// // “性别”下拉列表获得焦点
                    return;// 退出应用程序
                }
                String sexStr = sex.getSelectedItem() + "";
                TbJsr jsr = new TbJsr();
                jsr.setTel(tel.getText());
                jsr.setAge(age.getText());
                jsr.setName(nameStr);
                jsr.setSex(sexStr);
                int i = Dao.addJsr(jsr);
                if (i > 0)
                    JOptionPane.showMessageDialog(TJJingShouRen.this, "添加成功");
                clear();// 清除文本框的内容
            }
        });
        final GridBagConstraints gridBagConstraints_8 = new GridBagConstraints();
        gridBagConstraints_8.weighty = 1.0;
        gridBagConstraints_8.anchor = GridBagConstraints.EAST;
        gridBagConstraints_8.gridy = 5;
        gridBagConstraints_8.gridx = 1;
        add(button, gridBagConstraints_8);
        //重置
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                clear();// 清除文本框的内容
            }
        });
        button_1.setText("重置");
        final GridBagConstraints gridBagConstraints_9 = new GridBagConstraints();
        gridBagConstraints_9.weighty = 1.0;
        gridBagConstraints_9.gridy = 5;
        gridBagConstraints_9.gridx = 2;
        add(button_1, gridBagConstraints_9);
    }
}
