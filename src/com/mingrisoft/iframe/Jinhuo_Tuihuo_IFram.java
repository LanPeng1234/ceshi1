package com.mingrisoft.iframe;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.table.*;
import javax.swing.JComboBox;

public class Jinhuo_Tuihuo_IFram extends JInternalFrame {// 进货退货内部窗体

    private JPanel jContentPane = null;
    private JPanel topPanel = null;
    private JPanel bottomPanel = null;
    private JScrollPane tablePane = null;
    private JTable table = null;
    private JLabel idLabel = null;
    private JTextField idField = null;// “退货票号”文本框
    private JLabel gysLabel = null;// “供应商”标签
    private JTextField gysField = null;// “供应商”文本框
    private JLabel lxrLabel = null;// “联系人”标签
    private JTextField lxrField = null;// “联系人”文本框
    private JLabel jsfsLabel = null;// “结算方式”标签
    private JComboBox jsfsComboBox = null;// “结算方式”下拉列表
    private JLabel thsjLabel = null;// “退货时间”标签
    private JTextField thsjField = null;// “退货时间”文本框
    private JLabel jsrLabel = null;// “经手人”标签
    private JTextField jsrField = null;// “经手人”文本框
    private JLabel pzslLabel = null;// “品种数量”标签
    private JTextField pzslField = null;// “品种数量”文本框
    private JLabel hpzsLabel = null;// “货品总数”标签
    private JTextField hpzsField = null;// “货品总数”文本框
    private JLabel hjjeLabel = null;// “合计金额”标签
    private JTextField hjjeField = null;// “合计金额”文本框
    private JLabel ysjlLabel = null;// “验收结论”标签
    private JTextField ysjlField = null;// “验收结论”文本框
    private JLabel czyLabel = null;// “操作员”标签
    private JTextField czyField = null;// “操作员”文本框
    private JButton tjButton = null;// “添加”按钮
    private JButton thButton = null;// “退货”按钮
    private JComboBox spComboBox = null;// “商品”下拉列表

    public Jinhuo_Tuihuo_IFram() {// 进货-退货窗体的构造方法
        super();// 调用父类JInternalFrame的构造方法
        initialize();// 初始化进货-退货窗体
    }

    private void initialize() {// 初始化进货-退货窗体
        this.setSize(600, 320);// 设置进货-退货窗体的宽高
        this.setResizable(true);// 可以调整进货-退货窗体的大小
        this.setIconifiable(true);// 设置进货-退货窗体可以图标化
        this.setMaximizable(true);// 设置进货-退货窗体可以最大化
        this.setTitle("进货退货");// 设置进货-退货窗体的标题
        this.setClosable(true);// 设置进货-退货窗体可以关闭
        this.setContentPane(getJContentPane());// 加载内容面板
    }

    private JPanel getJContentPane() {// 获得内容面板
        if (jContentPane == null) {// 内容面板不存在
            BorderLayout borderLayout = new BorderLayout();// 创建边界布局
            jContentPane = new JPanel();// 创建内容面板
            jContentPane.setLayout(borderLayout);// 设置内容面板的布局为边界布局
            jContentPane.add(getTopPanel(), BorderLayout.NORTH);// 把顶部面板置于内容面板的北部
            jContentPane.add(getBottomPanel(), BorderLayout.SOUTH);// 把底部面板置于内容面板的南部
            jContentPane.add(getTablePane(), BorderLayout.CENTER);// 把表格面板置于内容面板的中间
        }
        return jContentPane;// 返回内容面板
    }

    private JPanel getTopPanel() {// 获得顶部面板
        if (topPanel == null) {
            GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
            gridBagConstraints11.gridx = 9;
            gridBagConstraints11.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints11.weightx = 1.0;
            gridBagConstraints11.gridy = 1;
            GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
            gridBagConstraints10.gridy = 1;
            gridBagConstraints10.gridx = 8;
            GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
            gridBagConstraints9.gridx = 5;
            gridBagConstraints9.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints9.weightx = 1.0;
            gridBagConstraints9.gridy = 1;
            GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
            gridBagConstraints8.gridy = 1;
            gridBagConstraints8.gridx = 4;
            GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
            gridBagConstraints7.gridx = 1;
            gridBagConstraints7.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints7.weightx = 1.0;
            gridBagConstraints7.gridy = 1;
            GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
            gridBagConstraints6.gridy = 1;
            gridBagConstraints6.gridx = 0;
            GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
            gridBagConstraints5.gridx = 9;
            gridBagConstraints5.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints5.weightx = 1.0;
            gridBagConstraints5.gridy = 0;
            GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
            gridBagConstraints4.gridy = 0;
            gridBagConstraints4.fill = GridBagConstraints.NONE;// 组件不扩大
            gridBagConstraints4.gridx = 8;
            GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
            gridBagConstraints3.gridx = 5;
            gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints3.weightx = 1.0;
            gridBagConstraints3.gridy = 0;
            GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
            gridBagConstraints2.gridy = 0;
            gridBagConstraints2.gridx = 4;
            GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
            gridBagConstraints1.gridx = 1;
            gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints1.weightx = 1.0;
            gridBagConstraints1.insets = new Insets(0, 0, 0, 1);// 组件彼此的间距
            gridBagConstraints1.gridy = 0;
            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridx = 0;
            jsrLabel = new JLabel();// “经手人”标签
            jsrLabel.setText("经手人：");// 设置“经手人”标签里的文本内容
            thsjLabel = new JLabel();// “退货时间”标签
            thsjLabel.setText("退货时间：");// 设置“退货时间”标签里的文本内容
            jsfsLabel = new JLabel();// “结算方式”标签
            jsfsLabel.setText("结算方式：");// 设置“结算方式”标签里的文本内容
            lxrLabel = new JLabel();// “联系人”标签
            lxrLabel.setText("联系人：");// 设置“联系人”标签里的文本内容
            gysLabel = new JLabel();// “供应商”标签
            gysLabel.setText("供应商：");// 设置“供应商”标签里的文本内容
            idLabel = new JLabel();// “退货票号”标签
            idLabel.setText("退货票号：");// 设置“退货票号”标签里的文本内容
            topPanel = new JPanel();// 创建顶部面板
            topPanel.setLayout(new GridBagLayout());// 设置顶部面板的布局为网格布局
            topPanel.add(idLabel, gridBagConstraints);// 把“退货票号”标签置于网格布局中相应的网格限制对象中
            topPanel.add(getIdField(), gridBagConstraints1);// 把“退货票号”文本框置于网格布局中相应的网格限制对象中
            topPanel.add(gysLabel, gridBagConstraints2);// 把“供应商”标签置于网格布局中相应的网格限制对象中
            topPanel.add(getGysField(), gridBagConstraints3);// 把“供应商”文本框置于网格布局中相应的网格限制对象中
            topPanel.add(lxrLabel, gridBagConstraints4);// 把“联系人”标签置于网格布局中相应的网格限制对象中
            topPanel.add(getLxrField(), gridBagConstraints5);// 把“联系人”文本框置于网格布局中相应的网格限制对象中
            topPanel.add(jsfsLabel, gridBagConstraints6);// 把“结算方式”标签置于网格布局中相应的网格限制对象中
            topPanel.add(getJsfsComboBox(), gridBagConstraints7);// 把“结算方式”下拉列表置于网格布局中相应的网格限制对象中
            topPanel.add(thsjLabel, gridBagConstraints8);// 把“退货时间”标签置于网格布局中相应的网格限制对象中
            topPanel.add(getThsjField(), gridBagConstraints9);// 把“退货时间”文本框置于网格布局中相应的网格限制对象中
            topPanel.add(jsrLabel, gridBagConstraints10);// 把“经手人”标签置于网格布局中相应的网格限制对象中
            topPanel.add(getJsrField(), gridBagConstraints11);// 把“经手人”文本框置于网格布局中相应的网格限制对象中
        }
        return topPanel;// 返回顶部面板
    }

    private JPanel getBottomPanel() {// 获得底部面板
        if (bottomPanel == null) {// 如果底部面板不存在
            GridBagConstraints gridBagConstraints23 = new GridBagConstraints();
            gridBagConstraints23.gridx = 6;
            gridBagConstraints23.fill = GridBagConstraints.NONE;// 组件不扩大
            gridBagConstraints23.gridy = 1;
            GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
            gridBagConstraints22.gridx = 5;
            gridBagConstraints22.gridy = 1;
            GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
            gridBagConstraints21.gridy = 1;
            gridBagConstraints21.weightx = 1.0;
            gridBagConstraints21.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints21.gridx = 3;
            GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
            gridBagConstraints20.gridx = 2;
            gridBagConstraints20.gridy = 1;
            GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
            gridBagConstraints19.gridy = 1;
            gridBagConstraints19.weightx = 1.0;
            gridBagConstraints19.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints19.gridx = 1;
            GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
            gridBagConstraints18.gridx = 0;
            gridBagConstraints18.gridy = 1;
            GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
            gridBagConstraints17.gridy = 0;
            gridBagConstraints17.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints17.weightx = 1.0;
            gridBagConstraints17.anchor = GridBagConstraints.WEST;
            gridBagConstraints17.gridwidth = 2;
            gridBagConstraints17.gridx = 5;
            GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
            gridBagConstraints16.gridx = 4;
            gridBagConstraints16.gridy = 0;
            GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
            gridBagConstraints15.gridy = 0;
            gridBagConstraints15.weightx = 1.0;
            gridBagConstraints15.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints15.gridx = 3;
            GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
            gridBagConstraints14.gridx = 2;
            gridBagConstraints14.gridy = 0;
            GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
            gridBagConstraints13.gridy = 0;
            gridBagConstraints13.weightx = 1.0;
            gridBagConstraints13.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints13.gridx = 1;
            GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
            gridBagConstraints12.gridx = 0;
            gridBagConstraints12.anchor = GridBagConstraints.WEST;
            gridBagConstraints12.gridy = 0;
            czyLabel = new JLabel();// “操作员”标签
            czyLabel.setText("操作员：");
            ysjlLabel = new JLabel();
            ysjlLabel.setText("验收结论：");
            hjjeLabel = new JLabel();
            hjjeLabel.setText("合计金额：");
            hpzsLabel = new JLabel();
            hpzsLabel.setText("货品总数：");
            pzslLabel = new JLabel();
            pzslLabel.setText("品种数量：");
            bottomPanel = new JPanel();
            bottomPanel.setLayout(new GridBagLayout());
            bottomPanel.add(pzslLabel, gridBagConstraints12);
            bottomPanel.add(getPzslField(), gridBagConstraints13);
            bottomPanel.add(hpzsLabel, gridBagConstraints14);
            bottomPanel.add(getHpzsField(), gridBagConstraints15);
            bottomPanel.add(hjjeLabel, gridBagConstraints16);
            bottomPanel.add(getHjjeField(), gridBagConstraints17);
            bottomPanel.add(ysjlLabel, gridBagConstraints18);
            bottomPanel.add(getYsjlField(), gridBagConstraints19);
            bottomPanel.add(czyLabel, gridBagConstraints20);
            bottomPanel.add(getCzyField(), gridBagConstraints21);
            bottomPanel.add(getTjButton(), gridBagConstraints22);
            bottomPanel.add(getThButton(), gridBagConstraints23);
        }
        return bottomPanel;// 返回底部面板
    }

    private JScrollPane getTablePane() {// 获得表格面板
        if (tablePane == null) {// 如果表格面板不存在
            tablePane = new JScrollPane();// 创建滚动面板
            tablePane.setViewportView(getTable());// 创建表格模型窗口
        }
        return tablePane;// 返回表格面板
    }

    private JTable getTable() {// 获得表格模型
        if (table == null) {
            table = new JTable();// 创建表格模型
            table.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));// 设置表格模型的边框样式
            table.setShowGrid(true);// 绘制网格线
            String[] columnNames = { "商品名称", "商品编号", "产地", "单位", "规格", "包装", "单价", "数量" };// 表头
            ((DefaultTableModel) table.getModel()).setColumnIdentifiers(columnNames);// 创建表格模型对象并向其中添加表头
            TableColumn column = table.getColumnModel().getColumn(0);// 以表格模型中索引为0的列作参照
            final DefaultCellEditor editor = new DefaultCellEditor(getSpComboBox());// 创建单元格编辑器
            editor.setClickCountToStart(2);// 开始编辑单元格所需的单击次数为2次
            column.setCellEditor(editor);// 编辑参照列中单元格时所用的编辑器
        }
        return table;// 返回表格模型
    }

    private JTextField getIdField() {// 获得“退货票号”文本框
        if (idField == null) {// 如果“退货票号”文本框不存在
            idField = new JTextField();// 创建“退货票号”文本框
        }
        return idField;// 返回“退货票号”文本框
    }

    private JTextField getGysField() {// 获得“供应商”文本框
        if (gysField == null) {// 如果“供应商”文本框不存在
            gysField = new JTextField();// 创建“供应商”文本框
        }
        return gysField;// 返回“供应商”文本框
    }

    private JTextField getLxrField() {// 获得“联系人”文本框
        if (lxrField == null) {// 如果“联系人”文本框不存在
            lxrField = new JTextField();// 创建“联系人”文本框
        }
        return lxrField;// 返回“联系人”文本框
    }

    private JComboBox getJsfsComboBox() {// 获得“结算方式”下拉列表
        if (jsfsComboBox == null) {// 如果“结算方式”下拉列表不存在
            jsfsComboBox = new JComboBox();// 创建“结算方式”下拉列表
        }
        return jsfsComboBox;// 返回“结算方式”下拉列表
    }

    private JTextField getThsjField() {// 获得“退货时间”文本框
        if (thsjField == null) {// 如果“退货时间”文本框不存在
            thsjField = new JTextField();// 创建“退货时间”文本框
        }
        return thsjField;// 返回“退货时间”文本框
    }

    private JTextField getJsrField() {// 获得“经手人”文本框
        if (jsrField == null) {// 如果“经手人”文本框不存在
            jsrField = new JTextField();// 创建“经手人”文本框
        }
        return jsrField;// 返回“经手人”文本框
    }

    private JTextField getPzslField() {// 获得“品种数量”文本框
        if (pzslField == null) {// 如果“品种数量”文本框不存在
            pzslField = new JTextField();// 创建“品种数量”文本框
        }
        return pzslField;// 返回“品种数量”文本框
    }

    private JTextField getHpzsField() {// 获得“货品总数”文本框
        if (hpzsField == null) {// 如果“货品总数”文本框不存在
            hpzsField = new JTextField();// 创建“货品总数”文本框
        }
        return hpzsField;// 返回“货品总数”文本框
    }

    private JTextField getHjjeField() {// 获得“合计金额”文本框
        if (hjjeField == null) {// 如果“合计金额”文本框不存在
            hjjeField = new JTextField();// 创建“合计金额”文本框
        }
        return hjjeField;// 返回“合计金额”文本框
    }

    private JTextField getYsjlField() {// 获得“验收结论”文本框
        if (ysjlField == null) {// 如果“验收结论”文本框不存在
            ysjlField = new JTextField();// 创建“验收结论”文本框
        }
        return ysjlField;// 返回“验收结论”文本框
    }

    private JTextField getCzyField() {// “操作员”文本框
        if (czyField == null) {// 如果“操作员”文本框不存在
            czyField = new JTextField();// 创建“操作员”文本框
        }
        return czyField;// 返回“操作员”文本框
    }

    private JButton getTjButton() {// 获得“添加”按钮
        if (tjButton == null) {// 如果“添加”按钮不存在
            tjButton = new JButton();// 创建“添加”按钮
            tjButton.setText("添加");// 设置“添加”按钮的文本内容
        }
        return tjButton;// 返回“添加”按钮
    }

    private JButton getThButton() {// 获得“退货”按钮
        if (thButton == null) {// 如果“退货”按钮不存在
            thButton = new JButton();// 创建“退货”按钮
            thButton.setText("退货");// 设置“退货”按钮的文本内容
        }
        return thButton;// 返回“退货按钮”
    }

    private JComboBox getSpComboBox() {// 获得“商品”下拉列表
        if (spComboBox == null) {// 如果“商品”下拉列表不存在
            spComboBox = new JComboBox();// 创建“商品”下拉列表
        }
        return spComboBox;// 返回“商品”下拉列表
    }

}
