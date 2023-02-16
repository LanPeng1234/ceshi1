package com.mingrisoft.iframe;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.*;

import com.mingrisoft.*;
import com.mingrisoft.dao.Dao;
import com.mingrisoft.dao.model.*;

public class JinHuoDan_IFrame extends JInternalFrame {// 进货单窗体

    private JPanel jContentPane = null;
    private JPanel topPanel = null;
    private JLabel idLabel = null;
    private JTextField idField = null;
    private JLabel gysLabel = null;
    private JLabel lxrLabel = null;
    private JTextField lxrField = null;
    private JLabel jsfsLabel = null;
    private JComboBox jsfsComboBox = null;
    private JLabel jhsjLabel = null;
    private JTextField jhsjField = null;
    private JLabel jsrLabel = null;
    private JTextField czyField = null;
    private JPanel bottomPanel = null;
    private JLabel pzslLabel = null;
    private JTextField pzslField = null;
    private JLabel hpzsLabel = null;
    private JTextField hpzsField = null;
    private JLabel hjjeLabel = null;
    private JTextField hjjeField = null;
    private JLabel ysjlLabel = null;
    private JTextField ysjlField = null;
    private JLabel czyLabel = null;
    private JButton tjButton = null;
    private JButton rukuButton = null;
    private JScrollPane tablePane = null;
    private JTable table = null;
    private JComboBox spComboBox = null;
    private JComboBox gysComboBox = null;
    private JComboBox jsrComboBox = null;
    private Date jhsjDate = new Date();
    public JinHuoDan_IFrame() {
        super();
        initialize();// 初始化进货单窗体
    }
    private void initialize() {
        this.setSize(600, 320);// 设置进货单窗体的宽高
        this.setIconifiable(true);// 设置进货单窗体可以图标化
        this.setResizable(true);// 可以调整进货单窗体的大小
        this.setMaximizable(true);// 设置进货单窗体可以最大化
        this.setTitle("进货单");// 设置进货单窗体的标题
        this.setClosable(true);// 设置进货单窗体可以关闭
        this.setContentPane(getJContentPane());// 加载内容面板
    }
    //内容面板
    private JPanel getJContentPane() {
        if (jContentPane == null) {// 内容面板不存在
            jContentPane = new JPanel();// 创建内容面板
            jContentPane.setLayout(new BorderLayout());// 创建边界布局
            jContentPane.add(getTopPanel(), BorderLayout.NORTH);// 把顶部面板置于内容面板的北部
            jContentPane.add(getBottomPanel(), BorderLayout.SOUTH);// 把底部面板置于内容面板的南部
            jContentPane.add(getTablePane(), BorderLayout.CENTER);// 把表格面板置于内容面板的中间
        }
        return jContentPane;
    }
    //顶部面板
    private JPanel getTopPanel() {
        //初始化
        if (topPanel == null) {
            GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
            gridBagConstraints21.fill = GridBagConstraints.BOTH;
            gridBagConstraints21.gridy = 1;
            gridBagConstraints21.weightx = 1.0;
            gridBagConstraints21.gridx = 9;
            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.fill = GridBagConstraints.BOTH;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridx = 5;
            GridBagConstraints gridBagConstraints101 = new GridBagConstraints();
            gridBagConstraints101.gridx = 8;
            gridBagConstraints101.gridy = 1;
            jsrLabel = new JLabel();
            jsrLabel.setText("\u7ecf\u624b\u4eba\uff1a");
            GridBagConstraints gridBagConstraints91 = new GridBagConstraints();
            gridBagConstraints91.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints91.gridy = 1;
            gridBagConstraints91.weightx = 1.0;
            gridBagConstraints91.gridx = 5;
            GridBagConstraints gridBagConstraints81 = new GridBagConstraints();
            gridBagConstraints81.gridx = 4;
            gridBagConstraints81.gridy = 1;
            //进货时间
            jhsjLabel = new JLabel();
            jhsjLabel.setText("进货时间：");
            GridBagConstraints gridBagConstraints71 = new GridBagConstraints();
            gridBagConstraints71.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints71.gridy = 1;
            gridBagConstraints71.weightx = 1.0;
            gridBagConstraints71.gridx = 1;
            GridBagConstraints gridBagConstraints61 = new GridBagConstraints();
            gridBagConstraints61.gridx = 0;
            gridBagConstraints61.gridy = 1;
            //结算方式
            jsfsLabel = new JLabel();
            jsfsLabel.setText("\u7ed3\u7b97\u65b9\u5f0f\uff1a");
            GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
            gridBagConstraints51.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints51.gridy = 0;
            gridBagConstraints51.weightx = 1.0;
            gridBagConstraints51.gridx = 9;
            GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
            gridBagConstraints41.fill = GridBagConstraints.NONE;// 组件不扩大
            gridBagConstraints41.gridy = 0;
            gridBagConstraints41.gridx = 8;
            //联系人
            lxrLabel = new JLabel();
            lxrLabel.setText("\u8054\u7cfb\u4eba\uff1a");
            GridBagConstraints gridBagConstraints25 = new GridBagConstraints();
            gridBagConstraints25.gridx = 4;
            gridBagConstraints25.gridy = 0;
            //供应商
            gysLabel = new JLabel();
            gysLabel.setText("\u4f9b\u5e94\u5546\uff1a");
            GridBagConstraints gridBagConstraints110 = new GridBagConstraints();
            gridBagConstraints110.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints110.gridx = 1;
            gridBagConstraints110.gridy = 0;
            gridBagConstraints110.weightx = 1.0;
            gridBagConstraints110.insets = new Insets(0, 0, 0, 1);
            GridBagConstraints gridBagConstraints24 = new GridBagConstraints();
            gridBagConstraints24.gridx = 0;
            gridBagConstraints24.gridy = 0;
            //进货票号
            idLabel = new JLabel();
            idLabel.setText("进货票号：");
            topPanel = new JPanel();// 创建顶部面板
            topPanel.setLayout(new GridBagLayout());
            topPanel.add(idLabel, gridBagConstraints24);
            topPanel.add(getIdField(), gridBagConstraints110);
            topPanel.add(gysLabel, gridBagConstraints25);
            topPanel.add(getGysComboBox(), gridBagConstraints);
            topPanel.add(lxrLabel, gridBagConstraints41);
            topPanel.add(getLxrField(), gridBagConstraints51);
            topPanel.add(jsfsLabel, gridBagConstraints61);
            topPanel.add(getJsfsComboBox(), gridBagConstraints71);
            topPanel.add(jhsjLabel, gridBagConstraints81);
            topPanel.add(getJhsjField(), gridBagConstraints91);
            topPanel.add(jsrLabel, gridBagConstraints101);
            topPanel.add(getJsrComboBox(), gridBagConstraints21);
        }
        return topPanel;// 返回顶部面板
    }
    //进货票号
    private JTextField getIdField() {
        if (idField == null) {
            idField = new JTextField();
            idField.setEditable(false);// 设置“进货票号”文本框不可编辑
        }
        return idField;
    }
    //联系人文本框
    private JTextField getLxrField() {
        if (lxrField == null) {
            lxrField = new JTextField();
        }
        return lxrField;
    }
    //结算方式
    private JComboBox getJsfsComboBox() {
        if (jsfsComboBox == null) {
            jsfsComboBox = new JComboBox();
            jsfsComboBox.addItem("现金结款");
            jsfsComboBox.addItem("支票结款");
        }
        return jsfsComboBox;
    }
    //进货时间文本框
    private JTextField getJhsjField() {
        if (jhsjField == null) {
            jhsjField = new JTextField();
        }
        return jhsjField;
    }
    //操作员文本框
    private JTextField getCzyField() {
        if (czyField == null) {
            czyField = new JTextField();
            czyField.setEditable(false);// 设置“操作员”文本框不可编辑
            czyField.setText(MainFrame.getCzyStateLabel().getText());
        }
        return czyField;
    }
    //底部面板
    private JPanel getBottomPanel() {
        //底部面板初始化
        if (bottomPanel == null) {
            GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
            gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints2.gridy = 1;
            gridBagConstraints2.weightx = 1.0;
            gridBagConstraints2.gridx = 3;
            GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
            gridBagConstraints1.fill = GridBagConstraints.BOTH;
            gridBagConstraints1.gridy = 1;
            gridBagConstraints1.weightx = 1.0;
            gridBagConstraints1.gridx = 9;
            GridBagConstraints gridBagConstraints231 = new GridBagConstraints();
            gridBagConstraints231.fill = GridBagConstraints.NONE;// 组件不扩大
            gridBagConstraints231.gridy = 1;
            gridBagConstraints231.weightx = 0.3;
            gridBagConstraints231.gridx = 6;
            GridBagConstraints gridBagConstraints221 = new GridBagConstraints();
            gridBagConstraints221.gridx = 5;
            gridBagConstraints221.weightx = 0.3;
            gridBagConstraints221.gridy = 1;
            GridBagConstraints gridBagConstraints201 = new GridBagConstraints();
            gridBagConstraints201.gridx = 2;
            gridBagConstraints201.gridy = 1;
            //操作员
            czyLabel = new JLabel();
            czyLabel.setText("\u64cd\u4f5c\u5458\uff1a");
            GridBagConstraints gridBagConstraints191 = new GridBagConstraints();
            gridBagConstraints191.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints191.gridy = 1;
            gridBagConstraints191.weightx = 1.0;
            gridBagConstraints191.gridx = 1;
            GridBagConstraints gridBagConstraints181 = new GridBagConstraints();
            gridBagConstraints181.gridx = 0;
            gridBagConstraints181.gridy = 1;
            //验收结论
            ysjlLabel = new JLabel();
            ysjlLabel.setText("\u9a8c\u6536\u7ed3\u8bba\uff1a");
            GridBagConstraints gridBagConstraints171 = new GridBagConstraints();
            gridBagConstraints171.anchor = GridBagConstraints.WEST;// 组件在其显示区域的左中
            gridBagConstraints171.gridwidth = 2;
            gridBagConstraints171.gridx = 5;
            gridBagConstraints171.gridy = 0;
            gridBagConstraints171.weightx = 0.6;
            gridBagConstraints171.fill = GridBagConstraints.HORIZONTAL;
            GridBagConstraints gridBagConstraints161 = new GridBagConstraints();
            gridBagConstraints161.gridx = 4;
            gridBagConstraints161.gridy = 0;
            //合计金额
            hjjeLabel = new JLabel();
            hjjeLabel.setText("\u5408\u8ba1\u91d1\u989d\uff1a");
            GridBagConstraints gridBagConstraints151 = new GridBagConstraints();
            gridBagConstraints151.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints151.gridy = 0;
            gridBagConstraints151.weightx = 1.0;
            gridBagConstraints151.gridx = 3;
            GridBagConstraints gridBagConstraints141 = new GridBagConstraints();
            gridBagConstraints141.gridx = 2;
            gridBagConstraints141.gridy = 0;
            //货品总数
            hpzsLabel = new JLabel();
            hpzsLabel.setText("\u8d27\u54c1\u603b\u6570\uff1a");
            GridBagConstraints gridBagConstraints131 = new GridBagConstraints();
            gridBagConstraints131.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints131.gridy = 0;
            gridBagConstraints131.weightx = 1.0;
            gridBagConstraints131.gridx = 1;
            GridBagConstraints gridBagConstraints121 = new GridBagConstraints();
            gridBagConstraints121.anchor = GridBagConstraints.WEST;
            gridBagConstraints121.gridy = 0;
            gridBagConstraints121.gridx = 0;
            //品种数量
            pzslLabel = new JLabel();
            pzslLabel.setText("\u54c1\u79cd\u6570\u91cf\uff1a");
            bottomPanel = new JPanel();// 创建底部面板
            bottomPanel.setLayout(new GridBagLayout());
            bottomPanel.add(pzslLabel, gridBagConstraints121);
            bottomPanel.add(getPzslField(), gridBagConstraints131);
            bottomPanel.add(hpzsLabel, gridBagConstraints141);
            bottomPanel.add(getHpzsField(), gridBagConstraints151);
            bottomPanel.add(hjjeLabel, gridBagConstraints161);
            bottomPanel.add(getHjjeField(), gridBagConstraints171);
            bottomPanel.add(ysjlLabel, gridBagConstraints181);
            bottomPanel.add(getYsjlField(), gridBagConstraints191);
            bottomPanel.add(czyLabel, gridBagConstraints201);
            bottomPanel.add(getCzyField(), gridBagConstraints2);
            bottomPanel.add(getTjButton(), gridBagConstraints221);
            bottomPanel.add(getRukuButton(), gridBagConstraints231);
        }
        return bottomPanel;// 返回底部面板
    }
    //品种数量文本框
    private JTextField getPzslField() {
        if (pzslField == null) {
            pzslField = new JTextField();
            pzslField.setEditable(false);
        }
        return pzslField;
    }
    //货品总数文本框
    private JTextField getHpzsField() {
        if (hpzsField == null) {
            hpzsField = new JTextField();
            hpzsField.setEditable(false);
        }
        return hpzsField;
    }
    private JTextField getHjjeField() {
        if (hjjeField == null) {
            hjjeField = new JTextField();
            hjjeField.setEditable(false);
        }
        return hjjeField;
    }
    private JTextField getYsjlField() {
        if (ysjlField == null) {
            ysjlField = new JTextField();
        }
        return ysjlField;
    }
    private JButton getTjButton() {
        if (tjButton == null) {
            tjButton = new JButton();
            tjButton.setText("添加");
            tjButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    java.sql.Date date = new java.sql.Date(jhsjDate.getTime());// 创建日期对象
                    jhsjField.setText(date.toString());// 设置“进货时间”文本框中的文本内容
                    String maxId = Dao.getRuKuMainMaxId(date);// 获取最大的“进货票号”
                    idField.setText(maxId);// 设置“进货票号”文本框中的文本内容
                    // 结束表格中没有编写的单元
                    stopTableCellEditing();
                    // 如果表格中不包含空行，就添加新行
                    for (int i = 0; i <= table.getRowCount() - 1; i++) {
                        if (table.getValueAt(i, 0) == null)
                            return;
                    }
                    DefaultTableModel model = (DefaultTableModel) table.getModel();// 创建表格对象
                    model.addRow(new Vector());// 向表格添加空行
                }
            });
        }
        return tjButton;
    }
    private JButton getRukuButton() {
        if (rukuButton == null) {
            rukuButton = new JButton();
            rukuButton.setText("入库");
            rukuButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // 停止表格单元的编辑
                    stopTableCellEditing();
                    // 清除空行
                    clearEmptyRow();
                    String pzsStr = pzslField.getText();
                    String jeStr = hjjeField.getText();
                    String jsfsStr = jsfsComboBox.getSelectedItem().toString();
                    String jsrStr = jsrComboBox.getSelectedItem() + "";
                    String czyStr = jsrComboBox.getSelectedItem() + "";
                    String rkDate = jhsjField.getText();
                    String ysjlStr = ysjlField.getText().trim();
                    String id = idField.getText();
                    String gysName = gysComboBox.getSelectedItem() + "";
                    if (jsrStr == null || jsrStr.isEmpty()) {
                        JOptionPane.showMessageDialog(JinHuoDan_IFrame.this, "请填写经手人");
                        return;
                    }
                    if (ysjlStr == null || ysjlStr.isEmpty()) {
                        JOptionPane.showMessageDialog(JinHuoDan_IFrame.this, "填写验收结论");
                        return;
                    }
                    if (table.getRowCount() <= 0) {
                        JOptionPane.showMessageDialog(JinHuoDan_IFrame.this, "填加入库商品");
                        return;
                    }
                    TbRukuMain ruMain = new TbRukuMain(id, pzsStr, jeStr, ysjlStr, gysName, rkDate, czyStr, jsrStr,
                            jsfsStr);// 入库主表
                    Set<TbRukuDetail> set = ruMain.getTabRukuDetails();// 入库明细
                    int rows = table.getRowCount();// 获得表格模型中的行数
                    for (int i = 0; i < rows; i++) {
                        TbSpinfo spinfo = (TbSpinfo) table.getValueAt(i, 0);// 商品信息
                        if (spinfo == null || spinfo.getId() == null || spinfo.getId().isEmpty())
                            continue;// 跳过本次循环，执行下一次循环
                        String djStr = (String) table.getValueAt(i, 6);
                        String slStr = (String) table.getValueAt(i, 7);
                        Double dj = Double.valueOf(djStr);
                        Integer sl = Integer.valueOf(slStr);
                        TbRukuDetail detail = new TbRukuDetail();// 入库明细
                        detail.setTbSpinfo(spinfo.getId());// 商品信息
                        detail.setTbRukuMain(ruMain.getRkId());// 入库主表(入库编号)
                        detail.setDj(dj);
                        detail.setSl(sl);
                        set.add(detail);// 添加入库明细
                    }
                    boolean rs = Dao.insertRukuInfo(ruMain);// 是否成功添加入库信息
                    if (rs) {// 成功添加入库信息
                        JOptionPane.showMessageDialog(JinHuoDan_IFrame.this, "入库完成");// 弹出提示框
                        DefaultTableModel dftm = new DefaultTableModel();// 创建表格默认模型对象
                        table.setModel(dftm);// 将表格的数据模型设置为dftm
                        pzslField.setText("0");
                        hpzsField.setText("0");
                        hjjeField.setText("0");
                    }
                }
            });
        }
        return rukuButton;
    }
    private JScrollPane getTablePane() {
        if (tablePane == null) {
            tablePane = new JScrollPane();
            tablePane.setViewportView(getTable());
        }
        return tablePane;
    }
    private JTable getTable() {
        if (table == null) {
            String[] columnNames = { "商品名称", "商品编号", "产地", "单位", "规格", "包装", "单价", "数量", "批号", "批准文号" };// 表头
            table = new JTable();
            table.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));// 设置表格模型的边框样式
            table.setShowGrid(true);// 绘制网格线
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// 不自动调整列的宽度，使用滚动条
            // 添加事件完成品种数量、货品总数、合计金额的计算
            table.addContainerListener(new computeInfo());
            ((DefaultTableModel) table.getModel()).setColumnIdentifiers(columnNames);// 创建表格模型对象并向其中添加表头
            TableColumn column = table.getColumnModel().getColumn(0);// 以表格模型中索引为0的列作参照
            final DefaultCellEditor editor = new DefaultCellEditor(getSpComboBox());// 创建单元格编辑器
            column.setCellEditor(editor);// 编辑参照列中单元格时所用的编辑器
            table.addPropertyChangeListener(new PropertyChangeListener() {
                public void propertyChange(java.beans.PropertyChangeEvent e) {// 为表格添加更改属性的监听事件
                    if ((e.getPropertyName().equals("tableCellEditor"))) {
                        new computeInfo();// 事件处理器，该处理器用于计算货品总数、合计金额等信息
                    }
                }
            });
        }
        return table;
    }
    private JComboBox getSpComboBox() {
        if (spComboBox == null) {
            spComboBox = new JComboBox();
            spComboBox.addItem(new TbSpinfo());
            spComboBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ResultSet set = Dao.query("select * from tb_spinfo where gysName='" + getGysComboBox().getSelectedItem() + "'");// 获得供应商信息的集合
                    updateSpComboBox(set);// 更新商品下拉列表
                }
            });
            spComboBox.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    TbSpinfo info = (TbSpinfo) spComboBox.getSelectedItem();// 获得“商品”下拉列表中被选中的商品信息
                    // 如果选择有效就更新表格
                    if (info != null && info.getId() != null) {
                        updateTable();// 更新表格当前行的内容
                    }
                }
            });
        }
        return spComboBox;
    }
    private void updateSpComboBox(ResultSet set) {
        try {
            while (set.next()) {// 移动后的记录指针指向一条有效的记录
                TbSpinfo spinfo = new TbSpinfo();// 商品信息
                spinfo.setId(set.getString("id").trim());
                spinfo.setSpname(set.getString("spname").trim());
                spinfo.setCd(set.getString("cd").trim());
                spinfo.setJc(set.getString("jc").trim());
                spinfo.setDw(set.getString("dw").trim());
                spinfo.setGg(set.getString("gg").trim());
                spinfo.setBz(set.getString("bz").trim());
                spinfo.setPh(set.getString("ph").trim());
                spinfo.setPzwh(set.getString("pzwh").trim());
                spinfo.setMemo(set.getString("memo").trim());
                spinfo.setGysname(set.getString("gysname").trim());
                DefaultComboBoxModel model = (DefaultComboBoxModel) spComboBox.getModel();// “商品”下拉列表的默认模型
                if (model.getIndexOf(spinfo) < 0)// “商品”下拉列表不包含该商品
                    spComboBox.addItem(spinfo);// 则添加选项
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
    private synchronized void updateTable() {
        TbSpinfo spinfo = (TbSpinfo) spComboBox.getSelectedItem();// 获得“商品”下拉列表中被选中的选项
        int row = table.getSelectedRow();// 获得表格模型中被选中的行
        if (row >= 0 && spinfo != null) {// 表格模型中被选中的行大于等于0且“商品”下拉列表中被选中的选项不为空
            // 设置表模型中单元格的值
            table.setValueAt(spinfo.getId(), row, 1);
            table.setValueAt(spinfo.getCd(), row, 2);
            table.setValueAt(spinfo.getDw(), row, 3);
            table.setValueAt(spinfo.getGg(), row, 4);
            table.setValueAt(spinfo.getBz(), row, 5);
            table.setValueAt("0", row, 6);
            table.setValueAt("0", row, 7);
            table.setValueAt(spinfo.getPh(), row, 8);
            table.setValueAt(spinfo.getPzwh(), row, 9);
            table.editCellAt(row, 6);// 单价可编辑
        }
    }
    private JComboBox getGysComboBox() {
        if (gysComboBox == null) {
            gysComboBox = new JComboBox();
            List gysInfos = Dao.getGysInfos();// 获得供应商信息的集合
            for (Iterator iter = gysInfos.iterator(); iter.hasNext();) {
                List list = (List) iter.next();
                Item item = new Item();
                item.setId(list.get(0).toString().trim());
                item.setName(list.get(1).toString().trim());
                gysComboBox.addItem(item);
            }
            Item item = (Item) gysComboBox.getSelectedItem();
            TbGysinfo gysInfo = Dao.getGysInfo(item);
            getLxrField().setText(gysInfo.getLian());
        }
        return gysComboBox;
    }
    private void stopTableCellEditing() {
        TableCellEditor cellEditor = table.getCellEditor();
        if (cellEditor != null)// 表格单元编辑器存在
            cellEditor.stopCellEditing();// 停止表格单元的编辑
    }
    private JComboBox getJsrComboBox() {
        if (jsrComboBox == null) {
            jsrComboBox = new JComboBox();
            List<List> czyList = Dao.getJsrs();
            for (List<String> list : czyList) {
                String id = list.get(0);
                String name = list.get(1);
                Item item = new Item(id, name);
                item.setId(id + "");
                item.setName(name);
                jsrComboBox.addItem(item);
            }
        }
        return jsrComboBox;
    }
    private final class computeInfo implements ContainerListener {
        @Override
        public void componentRemoved(ContainerEvent e) {
            // 清除空行
            clearEmptyRow();
            int rows = table.getRowCount();// 获得表格模型中的行数
            int count = 0;// “货品总数”
            double money = 0.0;// “合计金额”
            TbSpinfo column = null;// “商品信息”的实例
            if (rows > 0)// 表格模型中的行数大于0
                column = (TbSpinfo) table.getValueAt(rows - 1, 0);// 为“商品信息”的实例赋值
            if (rows > 0 && (column == null || column.getId().isEmpty()))// 表格模型中的行数大于0且“商品信息”的实例不存在或商品编号为空
                rows--;// 表格模型中的行数减一
            // 计算货品总数和合计金额
            for (int i = 0; i < rows; i++) {
                String column7 = (String) table.getValueAt(i, 7);
                String column6 = (String) table.getValueAt(i, 6);
                int c7 = (column7 == null || column7.isEmpty()) ? 0 : Integer.parseInt(column7);
                float c6 = (column6 == null || column6.isEmpty()) ? 0 : Float.parseFloat(column6);
                count += c7;
                money += c6 * c7;
            }
            pzslField.setText(rows + "");
            hpzsField.setText(count + "");
            hjjeField.setText(money + "");
        }
        @Override
        public void componentAdded(ContainerEvent e) {
        }

    }
    // 清除空行
    private synchronized void clearEmptyRow() {
        DefaultTableModel dftm = (DefaultTableModel) table.getModel();// 获得默认的表格模型
        for (int i = 0; i < table.getRowCount(); i++) {
            TbSpinfo spinfo = (TbSpinfo) table.getValueAt(i, 0);// 获得商品信息
            if (spinfo == null || spinfo.getId() == null || spinfo.getId().isEmpty()) {// 商品信息为空
                dftm.removeRow(i);// 移除默认的表格模型中的该行
            }
        }
    }
}
