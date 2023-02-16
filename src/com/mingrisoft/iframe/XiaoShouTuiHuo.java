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
public class XiaoShouTuiHuo extends JInternalFrame {
    private final JTable table;
    private final JTextField thsj = new JTextField();
    private JComboBox jsr = null;
    private final JComboBox jsfs = new JComboBox();
    private final JTextField lian = new JTextField();
    private final JComboBox kehu = new JComboBox();
    private final JTextField piaoHao = new JTextField();
    private final JTextField pzs = new JTextField("0");//初始为零
    private final JTextField hpzs = new JTextField("0");
    private final JTextField hjje = new JTextField("0");
    private final JTextField ysjl = new JTextField();
    private final JTextField czy = new JTextField();
    private Date thsjDate;
    private JComboBox sp;
    public XiaoShouTuiHuo() {
        super();
        setMaximizable(true);//可以最大化
        setIconifiable(true);//可以图标化
        setClosable(true);//可关闭
        getContentPane().setLayout(new GridBagLayout());//将当前的内容面板的布局改为GridBagLayout。
        setTitle("销售退货");//
        setBounds(50, 50, 700, 400);// 前两个int 是矩形组件左上角那个点在容器中的坐标。后两个int 是矩形组件的宽度和高度。
        //初始化 设计窗口内容
        setupComponet(new JLabel("票号"), 0, 0, 1, 0, false);
        piaoHao.setFocusable(false);//不为焦点
        setupComponet(piaoHao, 1, 0, 1, 140, true);

        setupComponet(new JLabel("客户"), 2, 0, 1, 0, false);
        kehu.setPreferredSize(new Dimension(160, 21));
        kehu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doKhSelectAction();//选择客户
            }
        });
        setupComponet(kehu, 3, 0, 1, 1, true);

        setupComponet(new JLabel("联系人"), 4, 0, 1, 0, false);
        lian.setFocusable(false);
        lian.setPreferredSize(new Dimension(80, 21));
        setupComponet(lian, 5, 0, 1, 0, true);

        setupComponet(new JLabel("结算方式"), 0, 1, 1, 0, false);
        jsfs.addItem("现金");
        jsfs.addItem("微信");
        jsfs.setEditable(true);//可编辑状态
        setupComponet(jsfs, 1, 1, 1, 1, true);

        setupComponet(new JLabel("退货时间"), 2, 1, 1, 0, false);
        thsj.setFocusable(false);
        setupComponet(thsj, 3, 1, 1, 1, true);

        setupComponet(new JLabel("商品"), 4, 1, 1, 0, false);
        setupComponet(getJsrComboBox(), 5, 1, 1, 1, true);
        sp = new JComboBox();
        sp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TbSpinfo info = (TbSpinfo) sp.getSelectedItem();//组合框显示商品对象
                if (info != null && info.getId() != null) {
                    updateTable();// 更新表格
                }
            }
        });
        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// 关闭表格列的自动调整功能
        initTable();
        table.addContainerListener(new computeInfo());
        JScrollPane scrollPanel = new JScrollPane(table);
        scrollPanel.setPreferredSize(new Dimension(380, 200));
        setupComponet(scrollPanel, 0, 2, 6, 1, true);

        setupComponet(new JLabel("品种数量"), 0, 3, 1, 0, false);
        pzs.setFocusable(false);
        setupComponet(pzs, 1, 3, 1, 1, true);

        setupComponet(new JLabel("货品总数"), 2, 3, 1, 0, false);
        hpzs.setFocusable(false);
        setupComponet(hpzs, 3, 3, 1, 1, true);

        setupComponet(new JLabel("合计金额"), 4, 3, 1, 0, false);
        hjje.setFocusable(false);
        setupComponet(hjje, 5, 3, 1, 1, true);

        setupComponet(new JLabel("验收结论"), 0, 4, 1, 0, false);
        setupComponet(ysjl, 1, 4, 1, 1, true);

        setupComponet(new JLabel("操作员"), 2, 4, 1, 0, false);
        czy.setFocusable(false);
        czy.setText(MainFrame.getCzyStateLabel().getText());
        setupComponet(czy, 3, 4, 1, 1, true);

        JButton tjButton = new JButton("添加");
        tjButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                initPiaoHao();
                stopTableCellEditing();
                for (int i = 0; i < table.getRowCount(); i++) {
                    TbSpinfo info = (TbSpinfo) table.getValueAt(i, 0);
                    if (info == null || info.getId().isEmpty())
                        return;
                }
                DefaultTableModel model = (DefaultTableModel) table.getModel();//删除鼠标选中行
                model.addRow(new Vector());//添加信息
                initSpBox();
            }
        });
        setupComponet(tjButton, 4, 4, 1, 1, false);

        JButton rkButton = new JButton("入库");
        rkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stopTableCellEditing();
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
                String gysName = kehu.getSelectedItem().toString();
                if (jsrStr == null || jsrStr.isEmpty()) {
                    JOptionPane.showMessageDialog(XiaoShouTuiHuo.this, "缺少经手人");
                    return;
                }
                if (ysjlStr == null || ysjlStr.isEmpty()) {
                    JOptionPane.showMessageDialog(XiaoShouTuiHuo.this, "缺少验收结论");
                    return;
                }
                if (table.getRowCount() <= 0) {//able.getRowCount() 获取table数据
                    JOptionPane.showMessageDialog(XiaoShouTuiHuo.this, "无数据");
                    return;
                }
                TbXsthMain xsthMain = new TbXsthMain(id, pzsStr, jeStr, ysjlStr, gysName, rkDate, czyStr, jsrStr, jsfsStr);
                Set<TbXsthDetail> set = xsthMain.getTbXsthDetails();
                int rows = table.getRowCount();
                for (int i = 0; i < rows; i++) {
                    TbSpinfo spinfo = (TbSpinfo) table.getValueAt(i, 0);//第i行第1个数据
                    String djStr = (String) table.getValueAt(i, 6);
                    String slStr = (String) table.getValueAt(i, 7);
                    Double dj = Double.valueOf(djStr);
                    Integer sl = Integer.valueOf(slStr);
                    TbXsthDetail detail = new TbXsthDetail();
                    detail.setSpid(spinfo.getId());
                    detail.setTbXsthMain(xsthMain.getXsthId());
                    detail.setDj(dj);
                    detail.setSl(sl);
                    set.add(detail);
                }
                boolean rs = Dao.insertXsthInfo(xsthMain);//插入销售退货信息
                if (rs) {
                    JOptionPane.showMessageDialog(XiaoShouTuiHuo.this, "销售退货");
                    DefaultTableModel dftm = new DefaultTableModel();//要显示JTable组件需要用到TableModel接口的DefaultTableModel类
                    table.setModel(dftm);
                    initTable();
                    pzs.setText("0");
                    hpzs.setText("0");
                    hjje.setText("0");
                }
            }
        });
        setupComponet(rkButton, 5, 4, 1, 1, false);
        addInternalFrameListener(new initTasks());//监听窗口事件
    }
    //经手人选择框
    private JComboBox getJsrComboBox() {
        if (jsr == null) {
            jsr = new JComboBox();//JComboBox选择框
            List<List> czyList = Dao.getJsrs();
            //为选择框添加所有经手人的id+姓名
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

    private void initTable() {
        String[] columnNames = { "商品名称", "商品编号", "供应商", "产地", "单位", "规格", "单价", "数量", "包装", "批号", "批准文号" };//表头
        ((DefaultTableModel) table.getModel()).setColumnIdentifiers(columnNames);// 创建表格模型对象并向其中添加表头
        TableColumn column = table.getColumnModel().getColumn(0);// 以表格模型中索引为0的列作参照
        final DefaultCellEditor editor = new DefaultCellEditor(sp);// 创建单元格编辑器
        editor.setClickCountToStart(2);// 开始编辑单元格所需的单击次数为2次
        column.setCellEditor(editor);// 编辑参照列中单元格时所用的编辑器
    }
    // 初始化商品下拉框
    private void initSpBox() {
        List list = new ArrayList();//商品信息集合
        ResultSet set = Dao.query("select * from tb_spinfo where id in (select id from tb_kucun)");// 获得有库存的商品信息的结果集
        sp.removeAllItems(); //清空下拉框
        sp.addItem(new TbSpinfo());
        for (int i = 0; table != null && i < table.getRowCount(); i++) {
            TbSpinfo tmpInfo = (TbSpinfo) table.getValueAt(i, 0);//商品的信息
            if (tmpInfo != null && tmpInfo.getId() != null)//商品信息和商品编号都不为空
                list.add(tmpInfo.getId());
        }
        try {
            while (set.next()) {
                TbSpinfo spinfo = new TbSpinfo();
                spinfo.setId(set.getString("id").trim());
                //表格中已存在这个商品，下拉框不再添加
                if (list.contains(spinfo.getId()))
                    continue;
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
                sp.addItem(spinfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 设置组件位置并添加到容器中
    private void setupComponet(JComponent component, int gridx, int gridy, int gridwidth, int ipadx, boolean fill) {
        final GridBagConstraints gridBagConstrains = new GridBagConstraints();
        gridBagConstrains.gridx = gridx;
        gridBagConstrains.gridy = gridy;
        gridBagConstrains.insets = new Insets(5, 1, 3, 1);
        if (gridwidth > 1)// 组件横跨网格数大于1
            gridBagConstrains.gridwidth = gridwidth;
        if (ipadx > 0)// 组件横向填充的大小大于0
            gridBagConstrains.ipadx = ipadx;
        if (fill)
            gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;// 组件水平扩大以占据空白区域
        getContentPane().add(component, gridBagConstrains);
    }
    // 客户选择时更新联系人字段
    private void doKhSelectAction() {
        Item item = (Item) kehu.getSelectedItem();
        TbKhinfo khInfo = Dao.getKhInfo(item);// 读取指定客户信息
        lian.setText(khInfo.getLian());// 设置“联系人”文本框中的文本内容
    }
    // 计算品种数量、货品总数、合计金额
    private final class computeInfo implements ContainerListener {
        public void componentRemoved(ContainerEvent e) {
            clearEmptyRow();// 清除空行
            int rows = table.getRowCount();// 获得表格模型中的行数
            int count = 0;
            double money = 0.0;
            TbSpinfo column = null;// 商品信息的实例
            if (rows > 0)
                column = (TbSpinfo) table.getValueAt(rows - 1, 0);// 为“商品信息”的实例赋值
            if (rows > 0 && (column == null || column.getId().isEmpty()))//无商品信息的行
                rows--;
            // 计算货品总数和金额
            for (int i = 0; i < rows; i++) {
                String column7 = (String) table.getValueAt(i, 7);// 获得表格中“数量”
                String column6 = (String) table.getValueAt(i, 6);// 获得表格中“单价”
                int c7 = (column7 == null || column7.isEmpty()) ? 0 : Integer.parseInt(column7);// 将String类型的“数量”转换为int型
                Double c6 = (column6 == null || column6.isEmpty()) ? 0 : Double.valueOf(column6);// 将String类型的“单价”转换为Double型
                count += c7;// 计算货品总数
                money += c6 * c7;// 计算合计金额
            }
            //编写信息
            pzs.setText(rows + "");
            hpzs.setText(count + "");
            hjje.setText(money + "");
        }
        public void componentAdded(ContainerEvent e) {
        }
    }
    // 窗体的初始化任务
    private final class initTasks extends InternalFrameAdapter {
        public void internalFrameActivated(InternalFrameEvent e) {
            super.internalFrameActivated(e);
            initTimeField();// 启动退货时间线程
            initKehuField();// 初始化客户字段
            initPiaoHao();// 初始化“销售票号”
            initSpBox();// 初始化商品下拉列表
        }
        private void initKehuField() {// 初始化客户字段
            List keHuInfo = Dao.getKhInfos();
            for (Iterator iter = keHuInfo.iterator(); iter.hasNext();) {
                List list = (List) iter.next();// 获得迭代的下一个集合
                Item item = new Item();// 数据表公共类
                item.setId(list.get(0).toString().trim());// 编号属性
                item.setName(list.get(1).toString().trim());// 名称信息
                kehu.addItem(item);// 向“客户”下拉列表中添加选项
            }
            doKhSelectAction();
        }
        // 启动退货时间线程
        private void initTimeField() {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        while (true) {
                            thsjDate = new Date();// 创建日期对象
                            thsj.setText(thsjDate.toLocaleString());// 设置“退货时间”文本框中的文本内容
                            Thread.sleep(1000);// 线程休眠1s
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
    // 初始化销退票号
    private void initPiaoHao() {
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());// 使用系统时间值构造一个日期对象
        String maxId = Dao.getXsthMainMaxId(date);// 获取最大的销售退货编号
        piaoHao.setText(maxId);// 设置“销售票号”文本框中的文本内容
    }
    // 根据商品下拉框的选择，更新表格当前行的内容
    private synchronized void updateTable() {
        TbSpinfo spinfo = (TbSpinfo) sp.getSelectedItem();// 获得“商品”下拉列表中被选中的选项
        Item item = new Item();// 数据表公共类
        item.setId(spinfo.getId());// 编号属性
        TbKucun kucun = Dao.getKucun(item);// 获取库存商品信息
        int row = table.getSelectedRow();// 获得表格模型中被选中的行
        if (row >= 0 && spinfo != null) {
            table.setValueAt(spinfo.getId(), row, 1);
            table.setValueAt(spinfo.getGysname(), row, 2);
            table.setValueAt(spinfo.getCd(), row, 3);
            table.setValueAt(spinfo.getDw(), row, 4);
            table.setValueAt(spinfo.getGg(), row, 5);
            table.setValueAt(kucun.getDj() + "", row, 6);
            table.setValueAt(kucun.getKcsl() + "", row, 7);
            table.setValueAt(spinfo.getBz(), row, 8);
            table.setValueAt(spinfo.getPh(), row, 9);
            table.setValueAt(spinfo.getPzwh(), row, 10);
            table.editCellAt(row, 7);// 库存数量可编辑
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
    // 停止表格单元的编辑
    private void stopTableCellEditing() {
        TableCellEditor cellEditor = table.getCellEditor();// 创建表格单元编辑器
        if (cellEditor != null)// 表格单元编辑器存在
            cellEditor.stopCellEditing();// 停止表格单元的编辑
    }
}

