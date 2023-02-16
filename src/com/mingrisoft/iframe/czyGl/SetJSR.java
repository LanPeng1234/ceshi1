package com.mingrisoft.iframe.czyGl;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.mingrisoft.dao.Dao;
import com.mingrisoft.iframe.JsrGL;

public class SetJSR extends JPanel {// 设置经手人面板

    private JTextField sexField;
    private JTextField ageField;
    private JTextField nameField;
    private JTable table;
    private DefaultTableModel dftm;
    private String[] columnNames;

    public SetJSR() {// 设置经手人面板
        super();// 调用父类JPanel的构造器
        setBounds(0, 0, 491, 200);// 设置“设置经手人面板”的位置和宽高
        setLayout(new GridBagLayout());// 设置“设置经手人面板”的布局为网格布局
        final JScrollPane scrollPane = new JScrollPane();// 滚动面板
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();// 创建网格限制对象
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, 20, 0);
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 35;
        gridBagConstraints.ipady = -195;
        add(scrollPane, gridBagConstraints);// 向设置经手人面板中添加滚动面板
        table = new JTable();// 表格模型
        dftm = (DefaultTableModel) table.getModel();// 创建表格对象
        columnNames = new String[] { "编号", "姓名", "性别", "年龄", "联系电话", "禁用" };// 存储列标题的数组
        dftm.setColumnIdentifiers(columnNames);// 用列标题数组中的元素替换表格对象中的列标识符
        table.addMouseListener(new MouseAdapter() {// 为表格模型添加鼠标点击事件
            public void mouseClicked(final MouseEvent e) {
                String nameStr, ageStr, sexStr;// 声明“姓名”、“性别”、“年龄”
                int selRow = table.getSelectedRow();// 选定行的索引
                nameStr = table.getValueAt(selRow, 1).toString().trim();
                ageStr = table.getValueAt(selRow, 3).toString().trim();
                sexStr = table.getValueAt(selRow, 2).toString().trim();
                nameField.setText(nameStr);
                ageField.setText(ageStr);
                sexField.setText(sexStr);
            }
        });
        scrollPane.setViewportView(table);// 把表格模型置于滚动面板中
        final JLabel label = new JLabel();// “姓名”标签
        final GridBagConstraints gridBagConstraints_3 = new GridBagConstraints();// 创建网格限制对象
        gridBagConstraints_3.gridy = 2;
        gridBagConstraints_3.gridx = 0;
        //姓名
        add(label, gridBagConstraints_3);
        label.setText("姓名：");
        nameField = new JTextField();
        nameField.setEditable(false);// 设置“姓名”文本框不可编辑
        final GridBagConstraints gridBagConstraints_4 = new GridBagConstraints();// 创建网格限制对象
        gridBagConstraints_4.insets = new Insets(0, 0, 0, 10);// 组件彼此的间距
        gridBagConstraints_4.fill = GridBagConstraints.HORIZONTAL;// 组件水平扩大以占据空白区域
        gridBagConstraints_4.weightx = 1.0;
        gridBagConstraints_4.gridy = 2;
        gridBagConstraints_4.gridx = 3;
        add(nameField, gridBagConstraints_4);
        //性别
        final JLabel label_2 = new JLabel();
        label_2.setText("性别：");
        final GridBagConstraints gridBagConstraints_7 = new GridBagConstraints();// 创建网格限制对象
        gridBagConstraints_7.gridy = 2;
        gridBagConstraints_7.gridx = 4;
        add(label_2, gridBagConstraints_7);
        sexField = new JTextField();
        sexField.setEditable(false);// 设置“性别”文本框不可编辑
        final GridBagConstraints gridBagConstraints_8 = new GridBagConstraints();// 创建网格限制对象
        gridBagConstraints_8.weightx = 1.0;
        gridBagConstraints_8.insets = new Insets(0, 0, 0, 10);
        gridBagConstraints_8.fill = GridBagConstraints.HORIZONTAL;// 组件水平扩大以占据空白区域
        gridBagConstraints_8.gridy = 2;
        gridBagConstraints_8.gridx = 5;
        add(sexField, gridBagConstraints_8);
        //年龄
        final JLabel label_1 = new JLabel();
        final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
        gridBagConstraints_6.gridy = 2;
        gridBagConstraints_6.gridx = 6;
        add(label_1, gridBagConstraints_6);
        label_1.setText("年龄：");
        ageField = new JTextField();
        final GridBagConstraints gridBagConstraints_5 = new GridBagConstraints();
        gridBagConstraints_5.insets = new Insets(0, 0, 0, 10);
        gridBagConstraints_5.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints_5.weightx = 1.0;
        gridBagConstraints_5.gridy = 2;
        gridBagConstraints_5.gridx = 7;
        add(ageField, gridBagConstraints_5);
        ageField.setEditable(false);
        //删除
        final JButton button = new JButton("删除");
        final GridBagConstraints gridBagConstraints_1 = new GridBagConstraints();
        gridBagConstraints_1.insets = new Insets(5, 0, 5, 0);
        gridBagConstraints_1.gridy = 7;
        gridBagConstraints_1.gridx = 4;
        add(button, gridBagConstraints_1);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                int op = JOptionPane.showConfirmDialog(SetJSR.this, "确认要删除该操作员？");
                if (op == JOptionPane.OK_OPTION) {// 点击“确认”
                    int selRow = table.getSelectedRow();// 选定行的索引
                    if (selRow < 0)// 选定行的索引小于0
                        return;// 退出应用程序
                    String id = table.getValueAt(selRow, 0).toString().trim();// 获得经手人编号
                    Dao.delete("delete from tb_jsr where id='" + id + "'");// 执行SQL删除语句
                    sexField.setText("");
                    ageField.setText("");
                    nameField.setText("");
                    initTable();// 初始化表格模型
                }
            }
        });
        //禁用/启用
        final JButton enableButton = new JButton("禁用/启用");
        final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        gridBagConstraints4.insets = new Insets(5, 0, 5, 0);
        gridBagConstraints4.gridy = 7;
        gridBagConstraints4.gridx = 3;
        add(enableButton, gridBagConstraints4);
        enableButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                int selRow = table.getSelectedRow();// 选定行的索引
                if (selRow < 0)// 选定行的索引小于0
                    return;// 退出应用程序
                int op = JOptionPane.showConfirmDialog(SetJSR.this, "确认要修改该操作员状态？");
                if (op == JOptionPane.OK_OPTION) {
                    if (selRow < 0)
                        return;
                    String id = table.getValueAt(selRow, 0).toString().trim();
                    Dao.update("update tb_jsr set enable=enable-1 where id='" + id + "'");
                    initTable();
                }
            }
        });
        //关闭
        final JButton button_1 = new JButton("关闭");
        final GridBagConstraints gridBagConstraints_2 = new GridBagConstraints();
        gridBagConstraints_2.insets = new Insets(5, 0, 5, 0);
        gridBagConstraints_2.gridy = 7;
        gridBagConstraints_2.gridx = 6;
        add(button_1, gridBagConstraints_2);
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                JsrGL parent = (JsrGL) SetJSR.this.getRootPane().getParent();
                parent.doDefaultCloseAction();// 关闭经手人设置窗体
            }
        });
    }
    public void initTable() {// 初始化表格模型
        List ul = Dao.getJsrs();// 获得被启用的经手人集合
        Iterator it = ul.iterator();// 获取经手人集合的迭代器
        String[] data = new String[6];
        dftm.setDataVector(null, columnNames);// 设置表格模型对象的列名
        while (it.hasNext()) {// 经手人的集合中的元素是否被遍历完
            List userlist = (List) it.next();// 获得经手人的集合中的下一个元素
            // 把集合中元素存放在字符串数组中
            data[0] = (String) userlist.get(0);
            data[1] = (String) userlist.get(1);
            data[2] = (String) userlist.get(2);
            data[3] = (String) userlist.get(3);
            data[4] = (String) userlist.get(4);
            data[5] = ((String) userlist.get(5)).equals("1") ? "启用" : "禁用";
            dftm.addRow(data);// 向表格对象中添加行数据
        }
        setVisible(true);// 使表格模型可见
    }
}
