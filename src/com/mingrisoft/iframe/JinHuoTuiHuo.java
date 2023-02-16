package com.mingrisoft.iframe;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import com.mingrisoft.*;
import com.mingrisoft.dao.Dao;
import com.mingrisoft.dao.model.*;

public class JinHuoTuiHuo extends JInternalFrame {// 进货-退货内部窗体
    private final JTable table;
    private final JTextField thsj = new JTextField();
    private JComboBox jsr = null;
    private final JComboBox jsfs = new JComboBox();
    private final JTextField lian = new JTextField();
    private final JComboBox gys = new JComboBox();
    private final JTextField piaoHao = new JTextField();
    private final JTextField pzs = new JTextField("0");
    private final JTextField hpzs = new JTextField("0");
    private final JTextField hjje = new JTextField("0");
    private final JTextField ysjl = new JTextField();
    private final JTextField czy = new JTextField();
    private Date thsjDate;
    private JComboBox sp;
    public JinHuoTuiHuo() {// 进货-退货内部窗体的构造方法
        super();// 调用父类JInternalFrame的构造器
        setMaximizable(true);// 可以最大化
        setIconifiable(true);// 可以图标化
        setClosable(true);// 进货-退货内部窗体可以关闭
        getContentPane().setLayout(new GridBagLayout());
        setTitle("进货-退货");
        setBounds(50, 50, 700, 400);
        //退货票号
        setupComponet(new JLabel("退货票号："), 0, 0, 1, 0, false);
        piaoHao.setFocusable(false);
        setupComponet(piaoHao, 1, 0, 1, 140, true);
        //供应商
        setupComponet(new JLabel("供应商："), 2, 0, 1, 0, false);
        gys.setPreferredSize(new Dimension(160, 21));
        gys.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doGysSelectAction();// “供应商”下拉列表的选择事件
            }
        });
        setupComponet(gys, 3, 0, 1, 1, true);
        //联系人
        setupComponet(new JLabel("联系人："), 4, 0, 1, 0, false);
        lian.setFocusable(false);
        lian.setPreferredSize(new Dimension(80, 21));
        setupComponet(lian, 5, 0, 1, 0, true);
        //结算方式
        setupComponet(new JLabel("结算方式："), 0, 1, 1, 0, false);
        jsfs.addItem("现金");
        jsfs.addItem("支票");
        jsfs.setEditable(true);
        setupComponet(jsfs, 1, 1, 1, 1, true);
        //退货时间
        setupComponet(new JLabel("退货时间："), 2, 1, 1, 0, false);
        thsj.setFocusable(false);
        setupComponet(thsj, 3, 1, 1, 1, true);
        //经手人
        setupComponet(new JLabel("经手人："), 4, 1, 1, 0, false);
        setupComponet(getJsrComboBox(), 5, 1, 1, 1, true);
        //商品
        sp = new JComboBox();
        sp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TbKucun info = (TbKucun) sp.getSelectedItem();
                if (info != null && info.getId() != null) {// 如果选择有效就更新表格
                    updateTable();
                }
            }
        });
        // 表格模型
        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// 不自动调整列的宽度，使用滚动条
        initTable();
        table.addContainerListener(new computeInfo());
        JScrollPane scrollPanel = new JScrollPane(table);
        scrollPanel.setPreferredSize(new Dimension(380, 200));
        setupComponet(scrollPanel, 0, 2, 6, 1, true);
        //品种数量
        setupComponet(new JLabel("品种数量："), 0, 3, 1, 0, false);
        pzs.setFocusable(false);
        setupComponet(pzs, 1, 3, 1, 1, true);
        //货品总数
        setupComponet(new JLabel("货品总数："), 2, 3, 1, 0, false);
        hpzs.setFocusable(false);
        setupComponet(hpzs, 3, 3, 1, 1, true);
        //合计金额
        setupComponet(new JLabel("合计金额："), 4, 3, 1, 0, false);
        hjje.setFocusable(false);
        setupComponet(hjje, 5, 3, 1, 1, true);
        //退货原因
        setupComponet(new JLabel("退货原因："), 0, 4, 1, 0, false);
        setupComponet(ysjl, 1, 4, 1, 1, true);
        //操作人员
        setupComponet(new JLabel("操作人员："), 2, 4, 1, 0, false);
        czy.setFocusable(false);
        czy.setText(MainFrame.getCzyStateLabel().getText());
        setupComponet(czy, 3, 4, 1, 1, true);
        //添加
        JButton tjButton = new JButton("添加");
        tjButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                initPiaoHao();
                // 停止表格单元的编辑
                stopTableCellEditing();
                // 如果表格中还包含空行，就不再添加新行
                for (int i = 0; i < table.getRowCount(); i++) {
                    TbKucun info = (TbKucun) table.getValueAt(i, 0);
                    if (info == null || info.getId().isEmpty())
                        return;
                }
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.addRow(new Vector());
                initSpBox();
            }
        });
        setupComponet(tjButton, 4, 4, 1, 1, false);
        //退货
        JButton rkButton = new JButton("退货");
        rkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 停止表格单元的编辑
                stopTableCellEditing();
                // 清除空行
                clearEmptyRow();
                String hpzsStr = hpzs.getText();
                String pzsStr = pzs.getText();
                String jeStr = hjje.getText();
                String jsfsStr = jsfs.getSelectedItem().toString();
                String jsrStr = jsr.getSelectedItem() + "";
                String czyStr = czy.getText();
                String rkDate = thsjDate.toLocaleString();
                String ysjlStr = ysjl.getText().trim();
                String id = piaoHao.getText();
                String gysName = gys.getSelectedItem().toString();
                if (jsrStr == null || jsrStr.isEmpty()) {
                    JOptionPane.showMessageDialog(JinHuoTuiHuo.this, "请填写经手人");
                    return;
                }
                if (ysjlStr == null || ysjlStr.isEmpty()) {
                    JOptionPane.showMessageDialog(JinHuoTuiHuo.this, "填写退货原因");
                    return;
                }
                if (table.getRowCount() <= 0) {
                    JOptionPane.showMessageDialog(JinHuoTuiHuo.this, "填加退货商品");
                    return;
                }
                TbRkthMain rkthMain = new TbRkthMain(id, pzsStr, jeStr, ysjlStr, gysName, rkDate, czyStr, jsrStr,
                        jsfsStr);// 进货退货主表
                Set<TbRkthDetail> set = rkthMain.getTbRkthDetails();// 存储进货退货详细信息的集合
                int rows = table.getRowCount();// 获得表格模型中的行数
                for (int i = 0; i < rows; i++) {
                    TbKucun kucun = (TbKucun) table.getValueAt(i, 0);
                    String djStr = (String) table.getValueAt(i, 6);
                    String slStr = (String) table.getValueAt(i, 7);
                    Double dj = Double.valueOf(djStr);
                    Integer sl = Integer.valueOf(slStr);
                    TbRkthDetail detail = new TbRkthDetail();
                    detail.setSpid(kucun.getId());
                    detail.setTbRkthMain(rkthMain.getRkthId());// 进货退货主表
                    detail.setDj(dj);
                    detail.setSl(sl);
                    set.add(detail);
                }
                boolean rs = Dao.insertRkthInfo(rkthMain);
                if (rs) {
                    JOptionPane.showMessageDialog(JinHuoTuiHuo.this, "退货完成");
                    DefaultTableModel dftm = new DefaultTableModel();
                    table.setModel(dftm);// 将表格的数据模型设置为dftm
                    initTable();// 初始化表格
                    pzs.setText("0");
                    hpzs.setText("0");
                    hjje.setText("0");
                }
            }
        });
        setupComponet(rkButton, 5, 4, 1, 1, false);
        addInternalFrameListener(new initTasks());
    }
    //经手人下拉列表
    private JComboBox getJsrComboBox() {
        if (jsr == null) {//初始化经手人下拉列表
            jsr = new JComboBox();
            List<List> czyList = Dao.getJsrs();
            for (List<String> list : czyList) {
                String id = list.get(0);
                String name = list.get(1);
                Item item = new Item(id, name);
                item.setId(id + "");
                item.setName(name);
                jsr.addItem(item);
            }
        }
        return jsr;
    }
    private void initTable() {// 初始化表格
        String[] columnNames = { "商品名称", "商品编号", "产地", "单位", "规格", "包装", "单价", "数量" };
        ((DefaultTableModel) table.getModel()).setColumnIdentifiers(columnNames);
        TableColumn column = table.getColumnModel().getColumn(0);
        final DefaultCellEditor editor = new DefaultCellEditor(sp);// 创建单元格编辑器
        editor.setClickCountToStart(2);// 开始编辑单元格所需的单击次数为2次
        column.setCellEditor(editor);// 编辑参照列中单元格时所用的编辑器
    }
    private void initSpBox() {// 初始化“商品”下拉列表
        List list = new ArrayList();// 创建存储库存信息的集合
        ResultSet set = Dao.query("select * from tb_kucun where id in(select id from tb_spinfo where gysName='"
                + gys.getSelectedItem() + "')");// 获得商品信息的结果集
        sp.removeAllItems();// 移除“商品”下拉列表中的选项
        sp.addItem(new TbKucun());// 向“商品”下拉列表中添加选项
        for (int i = 0; table != null && i < table.getRowCount(); i++) {
            TbKucun tmpInfo = (TbKucun) table.getValueAt(i, 0);// 获得库存信息
            if (tmpInfo != null && tmpInfo.getId() != null)// 如果库存信息和商品编号都不为空
                list.add(tmpInfo.getId());// 向库存信息的集合中添加商品编号
        }
        try {
            while (set.next()) {
                TbKucun kucun = new TbKucun();// 库存信息
                kucun.setId(set.getString("id").trim());// 商品编号
                if (list.contains(kucun.getId()))// 如果表格中已存在同样商品，商品下拉框中就不再包含该商品
                    continue;
                kucun.setSpname(set.getString("spname").trim());// 商品名称
                kucun.setCd(set.getString("cd").trim());// 产地
                kucun.setJc(set.getString("jc").trim());// 商品简称
                kucun.setDw(set.getString("dw").trim());// 商品计量单位
                kucun.setGg(set.getString("gg").trim());// 商品规格
                kucun.setBz(set.getString("bz").trim());// 包装
                kucun.setDj(Double.valueOf(set.getString("dj").trim()));// 单价
                kucun.setKcsl(Integer.valueOf(set.getString("kcsl").trim()));// 库存数量
                sp.addItem(kucun);// 向“商品”下拉列表中添加库存信息
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void setupComponet(JComponent component, int gridx, int gridy, int gridwidth, int ipadx, boolean fill) {
        final GridBagConstraints gridBagConstrains = new GridBagConstraints();// 创建网格限制对象
        gridBagConstrains.gridx = gridx;
        gridBagConstrains.gridy = gridy;
        gridBagConstrains.insets = new Insets(5, 1, 3, 1);// 组件彼此的间距
        if (gridwidth > 1)// 组件横跨网格数大于1
            gridBagConstrains.gridwidth = gridwidth;
        if (ipadx > 0)// 组件横向填充的大小大于0
            gridBagConstrains.ipadx = ipadx;// 设置组件横向填充的大小
        if (fill)// 组件占据空白区域
            gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;// 组件水平扩大以占据空白区域
        getContentPane().add(component, gridBagConstrains);
    }

    private void doGysSelectAction() {// “供应商”下拉列表的选择事件
        Item item = (Item) gys.getSelectedItem();// 获得被选中的供应商
        TbGysinfo gysInfo = Dao.getGysInfo(item);// 读取指定供应商信息
        lian.setText(gysInfo.getLian());// 设置“联系人”文本框中的文本内容
    }

    private final class computeInfo implements ContainerListener {// 在事件中计算品种数量、货品总数、合计金额
        public void componentRemoved(ContainerEvent e) {
            // 清除空行
            clearEmptyRow();
            int rows = table.getRowCount();// 获得表格模型中的行数
            int count = 0;// “货品总数”
            double money = 0.0;// “合计金额”
            TbKucun column = null;// “库存信息”的实例
            if (rows > 0)// 表格模型中的行数大于0
                column = (TbKucun) table.getValueAt(rows - 1, 0);// 为“库存信息”的实例赋值
            if (rows > 0 && (column == null || column.getId().isEmpty()))
                rows--;// 表格模型中的行数减一
            // 计算货品总数和金额
            for (int i = 0; i < rows; i++) {
                String column7 = (String) table.getValueAt(i, 7);
                String column6 = (String) table.getValueAt(i, 6);
                int c7 = (column7 == null || column7.isEmpty()) ? 0 : Integer.parseInt(column7);
                Double c6 = (column6 == null || column6.isEmpty()) ? 0 : Double.valueOf(column6);
                count += c7;
                money += c6 * c7;
            }
            pzs.setText(rows + "");
            hpzs.setText(count + "");
            hjje.setText(money + "");
        }

        public void componentAdded(ContainerEvent e) {
        }
    }

    private final class initTasks extends InternalFrameAdapter {// 窗体的初始化任务
        public void internalFrameActivated(InternalFrameEvent e) {
            super.internalFrameActivated(e);
            initTimeField();// 启动退货时间线程
            initGysField();// 初始化供应商字段
            initPiaoHao();// 初始化“退货票号”
            initSpBox();// 初始化“商品”下拉列表
        }
        private void initGysField() {// 初始化供应商字段
            List gysInfos = Dao.getGysInfos();// 获得所有供应商信息的集合
            for (Iterator iter = gysInfos.iterator(); iter.hasNext();) {// 遍历所有供应商信息的集合
                List list = (List) iter.next();// 获得迭代的下一个集合
                Item item = new Item();// 数据表公共类
                item.setId(list.get(0).toString().trim());
                item.setName(list.get(1).toString().trim());
                gys.addItem(item);// 向“供应商”下拉列表中添加选项
            }
            doGysSelectAction();
        }
        private void initTimeField() {// 启动退货时间线程
            new Thread(new Runnable() {
                public void run() {
                    try {
                        while (true) {
                            thsjDate = new Date();// 创建日期对象
                            thsj.setText(thsjDate.toLocaleString());// 设置“退货时间”文本框中的文本内容
                            Thread.sleep(1000);// 线程休眠1秒
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();// 启动线程
        }
    }
    private void initPiaoHao() {// 初始化“退货票号”
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());// 使用系统时间值构造一个日期对象
        String maxId = Dao.getRkthMainMaxId(date);// 获取退货最大ID
        piaoHao.setText(maxId);// 设置“退货票号”文本框中的文本内容
    }
    private synchronized void updateTable() {// 根据商品下拉框的选择，更新表格当前行的内容
        TbKucun kucun = (TbKucun) sp.getSelectedItem();// 获得“商品”下拉列表中被选中的选项
        int row = table.getSelectedRow();// 获得表格模型中被选中的行
        if (row >= 0 && kucun != null) {// 表格模型中被选中的行大于等于0且“商品”下拉列表中被选中的选项不为空
            table.setValueAt(kucun.getId(), row, 1);
            table.setValueAt(kucun.getCd(), row, 2);
            table.setValueAt(kucun.getDw(), row, 3);
            table.setValueAt(kucun.getGg(), row, 4);
            table.setValueAt(kucun.getBz(), row, 5);
            table.setValueAt(kucun.getDj().toString(), row, 6);
            table.setValueAt(kucun.getKcsl().toString(), row, 7);
            table.editCellAt(row, 7);// 库存数量可编辑
        }
    }

    private synchronized void clearEmptyRow() {// 清除空行
        DefaultTableModel dftm = (DefaultTableModel) table.getModel();// 获得默认的表格模型
        for (int i = 0; i < table.getRowCount(); i++) {
            TbKucun kucun = (TbKucun) table.getValueAt(i, 0);// 获得库存信息
            if (kucun == null || kucun.getId() == null || kucun.getId().isEmpty()) {
                dftm.removeRow(i);// 移除默认的表格模型中的该行
            }
        }
    }
    private void stopTableCellEditing() {
        TableCellEditor cellEditor = table.getCellEditor();// 创建表格单元编辑器
        if (cellEditor != null)// 表格单元编辑器存在
            cellEditor.stopCellEditing();// 停止表格单元的编辑
    }
}
