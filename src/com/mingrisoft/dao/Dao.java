package com.mingrisoft.dao;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import com.mingrisoft.Item;
import com.mingrisoft.dao.model.TbGysinfo;
import com.mingrisoft.dao.model.TbJsr;
import com.mingrisoft.dao.model.TbKhinfo;
import com.mingrisoft.dao.model.TbKucun;
import com.mingrisoft.dao.model.TbRkthDetail;
import com.mingrisoft.dao.model.TbRkthMain;
import com.mingrisoft.dao.model.TbRukuDetail;
import com.mingrisoft.dao.model.TbRukuMain;
import com.mingrisoft.dao.model.TbSellDetail;
import com.mingrisoft.dao.model.TbSellMain;
import com.mingrisoft.dao.model.TbSpinfo;
import com.mingrisoft.dao.model.TbXsthDetail;
import com.mingrisoft.dao.model.TbXsthMain;
public class Dao {
    //数据库驱动名称
    protected static String dbClassName="com.mysql.jdbc.Driver";
    //访问数据库路径
    protected static String dbUrl="jdbc:mysql://localhost:3306/db_jxcms";
    protected static String dbUser="root";
    protected static String dbPwd="781205";
    protected static String second = null;//
    public static Connection conn = null;
    //登录数据库
    static {
        try {
            if (conn == null) {
                Class.forName(dbClassName);
                conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"请将Mysql的JDBC包复制到lib中");
            System.exit(-1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private Dao(){//封闭构造方法禁止创建Dao类实例
    }
    //查询、插入、更新、删除
    public static ResultSet findForResultSet(String sql) {
        if (conn == null)
            return null;
        long time = System.currentTimeMillis();
        ResultSet rs = null;
        try {
            Statement stmt = null;
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //ResultSet.TYPE_SCROLL_INSENSITIVE返回的结果集对数据库中的的数据变动是不敏感的。
            //ResultSet.CONCUR_READ_ONLY不能用结果集更新数据库中的表。
            rs = stmt.executeQuery(sql);
            second = ((System.currentTimeMillis() - time) / 1000d) + "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    public static boolean insert(String s) {
        boolean result = false;
        try {
            result=conn.createStatement().execute(s);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
    public static int update(String s) {
        int result=0;
        try {
            result=conn.createStatement().executeUpdate(s);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
    public static int delete(String sql) {
        return update(sql);
    }
    //查询之后封装数据便于使用
    //获取客户信息
    public static TbKhinfo getKhInfo(Item item){
        //如果没有id就通过名字查询客户
        String where="khname='"+item.getName()+"'";
        if(item.getId()!=null){//获取对象的id属性
            where="id='"+item.getId()+"'";
        }
        TbKhinfo info=new TbKhinfo();//创建客户信息数据模型
        ResultSet set=findForResultSet("select * from tb_khinfo where "+where);
        try{//封装数据到数据模型中
            if(set.next()){
                info.setId(set.getString("id").trim());
                info.setKhname(set.getString("khname").trim());
                info.setJian(set.getString("jian").trim());
                info.setAddress(set.getString("address").trim());
                info.setBianma(set.getString("bianma").trim());
                info.setFax(set.getString("fax").trim());
                info.setHao(set.getString("hao").trim());
                info.setLian(set.getString("lian").trim());
                info.setLtel(set.getString("ltel").trim());
                info.setMail(set.getString("mail").trim());
                info.setTel(set.getString("tel").trim());
                info.setXinhang(set.getString("xinhang").trim());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return info;
    }
    //获取供应商信息
    public static TbGysinfo getGysInfo(Item item){
        String where="name='"+item.getName()+"'";
        if(item.getId()!=null){
            where="id='"+item.getId()+"'";
        }
        ResultSet set= findForResultSet("select * from tb_gysinfo where "+where);
        TbGysinfo info=new TbGysinfo();
        try {
            if(set.next()){
                info.setId(set.getString("id").trim());
                info.setAddress(set.getString("address").trim());
                info.setBianma(set.getString("bianma").trim());
                info.setFax(set.getString("fax").trim());
                info.setJc(set.getString("jc").trim());
                info.setLian(set.getString("lian").trim());
                info.setLtel(set.getString("ltel").trim());
                info.setMail(set.getString("mail").trim());
                info.setName(set.getString("name").trim());
                info.setTel(set.getString("tel").trim());
                info.setYh(set.getString("yh").trim());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  info;
    }
    //获取经手人信息
    public static TbJsr getJsr(String name, String password) {
        TbJsr user = new TbJsr();
        ResultSet rs = findForResultSet("select * from tb_jsr where name='"
                + name + "'");
        try {
            if (rs.next()) {
                user.setSex(name);
                user.setAge(rs.getString("pass"));
                if (user.getAge().equals(password)) {
                    user.setName(rs.getString("name"));
                    user.setTel(rs.getString("quan"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    //获取商品信息
    public static TbSpinfo getSpInfo(Item item){
        String where="spname='"+item.getName()+"'";
        if(item.getId()!=null){
            where="id='"+item.getId()+"'";
        }
        ResultSet rs=findForResultSet("select * from tb_spinfo where "+where);
        TbSpinfo spInfo=new TbSpinfo();
        try {
            if(rs.next()){
                spInfo.setId(rs.getString("id").trim());
                spInfo.setBz(rs.getString("bz").trim());
                spInfo.setCd(rs.getString("cd").trim());
                spInfo.setDw(rs.getString("dw").trim());
                spInfo.setGg(rs.getString("gg").trim());
                spInfo.setGysname(rs.getString("gysname").trim());
                spInfo.setJc(rs.getString("jc").trim());
                spInfo.setMemo(rs.getString("memo").trim());
                spInfo.setPh(rs.getString("ph").trim());
                spInfo.setPzwh(rs.getString("pzwh").trim());
                spInfo.setSpname(rs.getString("spname").trim());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return spInfo;
    }
    //获取库存信息
    public static TbKucun getKucun(Item item) {
        String where = "spname='" + item.getName() + "'";
        if (item.getId() != null)
            where = "id='" + item.getId() + "'";
        ResultSet rs = findForResultSet("select * from tb_kucun where " + where);
        TbKucun kucun = new TbKucun();
        try {
            if (rs.next()) {
                kucun.setId(rs.getString("id"));
                kucun.setSpname(rs.getString("spname"));
                kucun.setJc(rs.getString("jc"));
                kucun.setBz(rs.getString("bz"));
                kucun.setCd(rs.getString("cd"));
                kucun.setDj(rs.getDouble("dj"));
                kucun.setDw(rs.getString("dw"));
                kucun.setGg(rs.getString("gg"));
                kucun.setKcsl(rs.getInt("kcsl"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kucun;
    }
    //检测登录
    public static boolean checkLogin(String userStr,String passStr)throws  SQLException{
        ResultSet rs=findForResultSet("select * from tb_userlist where name='"+userStr+"' and pass='"+passStr+"'");
        if(rs==null){
            return false;
        }
        return rs.next();
    }
    //先插入main表，再插入detail表，最后更新库存
    //在事务中添加销售信息
    public static boolean inserSellInfo(TbSellMain sellMain){
        try {
            boolean autoCommit=conn.getAutoCommit();
            conn.setAutoCommit(false);//设置手动提交
            //添加销售主表记录
            insert("insert into tb_sell_main values("+sellMain.getSellId()+","+sellMain.getPzs()+","+
                    sellMain.getJe()+","+sellMain.getYsjl() +","+sellMain.getKhname()+","+sellMain.getXsdate()+","
                    +sellMain.getJsr()+","+sellMain.getJsfs()+")");
            //同时关联selldetail表
            Set<TbSellDetail> rkDetails=sellMain.getTbSellDetails();
            for (Iterator<TbSellDetail> iter=rkDetails.iterator();iter.hasNext();){
                TbSellDetail details=iter.next();
                //添加销售记录
                insert("insert into tb_sell_detail values("+sellMain.getSellId()+","+details.getSpid()+","+
                        details.getDj()+","+details.getSl()+")");
                //修改库存表记录
                Item item=new Item();
                item.setId(details.getSpid());
                TbSpinfo spInfo=getSpInfo(item);
                if (spInfo.getId()!=null&&!spInfo.getId().isEmpty()){//值和栈都不为空，即有这个商品
                    TbKucun kucun=getKucun(item);
                    if (kucun.getId()!=null&&!kucun.getId().isEmpty()){
                        int sl=kucun.getKcsl()-details.getSl();
                        update("update tb_kucun set kcsl="+sl+"where id="+kucun.getId()+"");
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //插入销售退货信息
    public static boolean insertXsthInfo(TbXsthMain xsthMain) {
        try {
            boolean autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            insert("insert into tb_xsth_main values('" + xsthMain.getXsthId()
                    + "','" + xsthMain.getPzs() + "'," + xsthMain.getJe()
                    + ",'" + xsthMain.getYsjl() + "','" + xsthMain.getKhname()
                    + "','" + xsthMain.getThdate() + "','" + xsthMain.getCzy()
                    + "','" + xsthMain.getJsr() + "','" + xsthMain.getJsfs()
                    + "')");
            Set<TbXsthDetail> xsthDetails = xsthMain.getTbXsthDetails();
            for (Iterator<TbXsthDetail> iter = xsthDetails.iterator(); iter.hasNext();) {
                TbXsthDetail details = iter.next();
                insert("insert into tb_xsth_detail values('"
                        + xsthMain.getXsthId() + "','" + details.getSpid()
                        + "'," + details.getDj() + "," + details.getSl() + ")");
                Item item = new Item();
                item.setId(details.getSpid());
                TbSpinfo spInfo = getSpInfo(item);
                if (spInfo.getId() != null && !spInfo.getId().isEmpty()) {
                    TbKucun kucun = getKucun(item);
                    if (kucun.getId() != null && !kucun.getId().isEmpty()) {
                        int sl = kucun.getKcsl() + details.getSl();
                        update("update tb_kucun set kcsl=" + sl + " where id='"
                                + kucun.getId() + "'");
                    }
                }
            }
            conn.commit();
            conn.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    //插入入库信息
    public static boolean insertRukuInfo(TbRukuMain ruMain) {
        try {
            boolean autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            insert("insert into tb_ruku_main values('" + ruMain.getRkId()
                    + "','" + ruMain.getPzs() + "'," + ruMain.getJe() + ",'"
                    + ruMain.getYsjl() + "','" + ruMain.getGysname() + "','"
                    + ruMain.getRkdate() + "','" + ruMain.getCzy() + "','"
                    + ruMain.getJsr() + "','" + ruMain.getJsfs() + "')");
            Set<TbRukuDetail> rkDetails = ruMain.getTabRukuDetails();
            for (Iterator<TbRukuDetail> iter = rkDetails.iterator(); iter
                    .hasNext();) {
                TbRukuDetail details = iter.next();
                insert("insert into tb_ruku_detail values('" + ruMain.getRkId()
                        + "','" + details.getTbSpinfo() + "',"
                        + details.getDj() + "," + details.getSl() + ")");
                Item item = new Item();
                item.setId(details.getTbSpinfo());
                TbSpinfo spInfo = getSpInfo(item);
                if (spInfo.getId() != null && !spInfo.getId().isEmpty()) {
                    TbKucun kucun = getKucun(item);
                    if (kucun.getId() == null || kucun.getId().isEmpty()) {
                        insert("insert into tb_kucun values('" + spInfo.getId()
                                + "','" + spInfo.getSpname() + "','"
                                + spInfo.getJc() + "','" + spInfo.getCd()
                                + "','" + spInfo.getGg() + "','"
                                + spInfo.getBz() + "','" + spInfo.getDw()
                                + "'," + details.getDj() + ","
                                + details.getSl() + ")");
                    } else {
                        int sl = kucun.getKcsl() + details.getSl();
                        update("update tb_kucun set kcsl=" + sl + ",dj="
                                + details.getDj() + " where id='"
                                + kucun.getId() + "'");
                    }
                }
            }
            conn.commit();
            conn.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //插入入库退货信息
    public static boolean insertRkthInfo(TbRkthMain rkthMain) {
        try {
            boolean autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            insert("insert into tb_rkth_main values('" + rkthMain.getRkthId()
                    + "','" + rkthMain.getPzs() + "'," + rkthMain.getJe()
                    + ",'" + rkthMain.getYsjl() + "','" + rkthMain.getGysname()
                    + "','" + rkthMain.getRtdate() + "','" + rkthMain.getCzy()
                    + "','" + rkthMain.getJsr() + "','" + rkthMain.getJsfs()
                    + "')");
            Set<TbRkthDetail> rkDetails = rkthMain.getTbRkthDetails();
            for (Iterator<TbRkthDetail> iter = rkDetails.iterator(); iter
                    .hasNext();) {
                TbRkthDetail details = iter.next();
                insert("insert into tb_rkth_detail values('"
                        + rkthMain.getRkthId() + "','" + details.getSpid()
                        + "'," + details.getDj() + "," + details.getSl() + ")");
                Item item = new Item();
                item.setId(details.getSpid());
                TbSpinfo spInfo = getSpInfo(item);
                if (spInfo.getId() != null && !spInfo.getId().isEmpty()) {
                    TbKucun kucun = getKucun(item);
                    if (kucun.getId() != null && !kucun.getId().isEmpty()) {
                        int sl = kucun.getKcsl() - details.getSl();
                        update("update tb_kucun set kcsl=" + sl + " where id='"
                                + kucun.getId() + "'");
                    }
                }
            }
            conn.commit();
            conn.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //插入销售信息
    public static boolean insertSellInfo(TbSellMain sellMain) {
        try {
            boolean autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            insert("insert into tb_sell_main values('" + sellMain.getSellId()
                    + "','" + sellMain.getPzs() + "'," + sellMain.getJe()
                    + ",'" + sellMain.getYsjl() + "','" + sellMain.getKhname()
                    + "','" + sellMain.getXsdate() + "','" + sellMain.getCzy()
                    + "','" + sellMain.getJsr() + "','" + sellMain.getJsfs()
                    + "')");
            Set<TbSellDetail> rkDetails = sellMain.getTbSellDetails();
            for (Iterator<TbSellDetail> iter = rkDetails.iterator(); iter
                    .hasNext();) {
                TbSellDetail details = iter.next();
                insert("insert into tb_sell_detail values('"
                        + sellMain.getSellId() + "','" + details.getSpid()
                        + "'," + details.getDj() + "," + details.getSl() + ")");
                Item item = new Item();
                item.setId(details.getSpid());
                TbSpinfo spInfo = getSpInfo(item);
                if (spInfo.getId() != null && !spInfo.getId().isEmpty()) {
                    TbKucun kucun = getKucun(item);
                    if (kucun.getId() != null && !kucun.getId().isEmpty()) {
                        int sl = kucun.getKcsl() - details.getSl();
                        update("update tb_kucun set kcsl=" + sl + " where id='"
                                + kucun.getId() + "'");
                    }
                }
            }
            conn.commit();
            conn.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //从数据库获取所有sql语句
    //备份数据库
    public static String backup() throws SQLException{
        LinkedList<String> sqls=new LinkedList<String>();//备份文件中所有sql
        String tables[]={"tb_gysinfo","tb_jsr","tb_khinfo","tb_kucun","tb_rkth_detail","tb_rkth_main","tb_ruku_detail",
                "tb_ruku_main","tb_sell_detail","tb_sell_main","tb_spinfo",
        "tb_userlist","tb_xsth_detail","tb_xsth_main"};
        ArrayList<Tables> tablelist=new ArrayList<Tables>();//创建保存所有对象的合集
        for (int i=0;i<tables.length;i++){
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("desc"+tables[i]);//查询表结构
            ArrayList<Columns>columns=new ArrayList<Columns>();
            while (rs.next()){
                Columns c=new Columns();
                c.setName(rs.getString("Field"));
                c.setType(rs.getString("Type"));
                String isnull=rs.getString("Null");
                if("Yes".equals(isnull)){
                    c.setNull(true);
                }
                String key=rs.getString("Key");//读取主键
                if("PRI".equals(key)){
                    c.setKey(true);
                    String increment=rs.getString("Extra");//读取特殊属性
                    if("auto_increment".equals(increment)){//主键是否自增
                        c.setIncrement(true);
                    }
                }
                columns.add(c);
            }
            Tables table=new Tables(tables[i],columns);
            tablelist.add(table);
            rs.close();//关闭结果集
            stmt.close();//关闭sql语句接口
        }
        for (int i=0;i<tablelist.size();i++){
            Tables table=tablelist.get(i);
            String dropsql="DROP TABLE IF EXISTS"+table.getName()+";";
            sqls.add(dropsql);
            StringBuilder createsql=new StringBuilder();
            createsql.append("CREATE TANLE "+table.getName()+"(");//创建语句句头
            ArrayList<Columns> columns=table.getColumns();
            for (int k=0;k<columns.size();k++){
                Columns c=columns.get(k);
                createsql.append(c.getName()+" "+c.getType());
                if(!c.isNull()){
                    createsql.append("not Null");
                }
                if(c.isKey()){
                    createsql.append(" primary key");
                    if(c.isIncrement()){
                        createsql.append(" AUTO_INCREMENT");
                    }
                }
                //判段是不是句尾
                if(k<columns.size()-1){
                    createsql.append(",");
                }else {
                    createsql.append(");");
                }
            }
            sqls.add(createsql.toString());
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("select * from  "+table.getName());
            while (rs.next()){
                StringBuilder insertsql=new StringBuilder();
                insertsql.append("INSERT INTO "+table.getName()+"VALUES(");
                for (int j=0;j<columns.size();j++){
                    Columns c=columns.get(j);
                    String type=c.getType();
                    if(type.startsWith("varchar")||type.startsWith("char")||type.startsWith("datetime")){
                        insertsql.append(""+rs.getString(c.getName())+"");//获取本列两端加逗号
                    }else {
                        insertsql.append(rs.getString(c.getName()));
                    }
                    if (j<columns.size()-1){
                        insertsql.append(",");
                    }
                    else {
                        insertsql.append(");");
                    }
                }
                sqls.add(insertsql.toString());
            }
            rs.close();
            stmt.close();
        }
        sqls.add("DROP VIEW IF EXISTS v_rukuView;");
        sqls.add("CREATE VIEW v_rukuView AS SELECT tb_ruku_main.rkID,tb_ruku_detail.spid,tb_spinfo.spname,tb_spinfo.gg,tb_ruku_detail.dj," +
                "tb_ruku_detail.sl,tb_ruku_detail.dj*tb_ruku_detail.sl AS je,tb_spinfo.gysname,tb_ruku_main.rkdate,tb_ruku_main.czy," +
                "tb_ruku_main.jsr,tb_ruku_main.jsfs FROM tb_ruku_detail INSERT JOIN tb_ruku_main ON tb_ruku_detail.rkID=tb_ruku_main.rkID " +
                "INNER JOIN tb_spinfo ON tb_ruku_detail.spid=tb_spinfo.id");
        sqls.add("DROP VIEW IF EXISTS v_sellView");
        sqls.add("CREATE VIEW v_sellView AS SELECT tb_sell_main.sellID,tb_spinfo.spname,tb_spinfo_spid,tb_spinfo.gg,tb_ruku_detail.dj," +
                "tb_sell_detail.sl,tb_sell_detail.sl*tb_sell_detail.dj AS je,tb_sell_main.khname,tb_sell_main.xsdate,tb_sell_main.czy," +
                "tb_sell_main.jsr,tb_sell_main.jsfs FROM tb_sell_detail INNER JOIN tb_sell_main ON tb_sell_detail.sellID=tb_sell_main.sellID " +
                "INNER JOIN tb_spinfo ON tb_sell_detail.spid=tb_spinfo.id");
        java.util.Date date=new java.util.Date();//获取当前时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_HHmmss");//设置时间输出格式
        String backupTime=sdf.format(date);
        String filePath="backup\\"+backupTime+".sql";//存放路径
        File sqlFile=new File(filePath);//创建备份文件
        //写入sql语句
        FileOutputStream fos=null;//文件字节输出流
        OutputStreamWriter osw=null;//字节流转为字符流
        BufferedWriter rw=null;//缓冲字符流
        try{
            fos=new FileOutputStream(sqlFile);
            osw=new OutputStreamWriter(fos);
            rw=new BufferedWriter(osw);
            for(String tmp:sqls){//便利所有备份sql语句
                rw.write(tmp);//写入sql语句
                rw.newLine();//换行
                rw.flush();//字符流刷新
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if(rw!=null){
                try{
                    rw.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(osw!=null){
                try{
                    osw.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(fos!=null){
                try{
                    fos.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return filePath;
    }
    //读取备份数据库，恢复数据库
    public static void restore(String filePath){
        File sqlFile=new File(filePath);
        Statement stmt=null;//sql语句接口
        FileInputStream fis=null;//文件输入字节流
        InputStreamReader isr=null;//字节流转为字符流
        BufferedReader br=null;
        try {
            fis=new FileInputStream(sqlFile);
            isr=new InputStreamReader(fis);
            br=new BufferedReader(isr);
            String readStr=null;
            while ((readStr=br.readLine())!=null){
                if(!"".equals(readStr.trim())){
                    stmt=conn.createStatement();//执行接口
                    int count=stmt.executeUpdate(readStr);
                    stmt.close();
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if(br!=null){
                try{
                    br.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(isr!=null){
                try{
                    isr.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(fis!=null){
                try{
                    fis.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    //修改密码
    public static int modifyPassword(String oldPass, String pass) {
        return update("update tb_userlist set pass='" + pass + "' where pass='"
                + oldPass + "'");
    }
    //获取用户
    public static TbJsr getUser(Item item) {
        String where = "username='" + item.getName() + "'";
        if (item.getId() != null)
            where = "name='" + item.getId() + "'";
        ResultSet rs = findForResultSet("select * from tb_userlist where "
                + where);
        TbJsr user = new TbJsr();
        try {
            if (rs.next()) {
                user.setName(rs.getString("name").trim());
                user.setSex(rs.getString("username").trim());
                user.setAge(rs.getString("pass").trim());
                user.setTel(rs.getString("quan").trim());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    //获取信息列表
    public static List getGysInfos() {
        List list = findForList("select id,name from tb_gysinfo");
        return list;
    }
    public static List getKhInfos() {
        List list = findForList("select id,khname from tb_khinfo");
        return list;
    }
    public static List getKucunInfos() {
        List list = findForList("select id,spname,dj,kcsl from tb_kucun");
        return list;
    }
    public static List getJsrs() {
        List list = findForList("select * from tb_jsr where enable=1");
        return list;
    }
    public static List getSpInfos() {
        List list = findForList("select * from tb_spinfo");
        return list;
    }
    //获取列表
    //用String来逐个获取查询出的信息，用List<String>整合一列，最后List<List>获取所有列
    public static List findForList(String sql) {
        List<List> list = new ArrayList<List>();
        ResultSet rs = findForResultSet(sql);//查询sql
        try {
            ResultSetMetaData metaData = rs.getMetaData();//获取数据库表信息
            int colCount = metaData.getColumnCount();

            while (rs.next()) {
                List<String> row = new ArrayList<String>();
                for (int i = 1; i <= colCount; i++) {
                    String str = rs.getString(i);
                    if (str != null && !str.isEmpty())
                        str = str.trim();
                    row.add(str);
                }
                list.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    //添加经手人
    public static int addJsr(TbJsr jsr) {
        String sql = "insert into tb_jsr(name, sex, age, tel, enable) values('" + jsr.getName() + "','"
                + jsr.getSex() + "','" + jsr.getAge() + "','" + jsr.getTel()
                + "',1)";
        System.out.println(sql);
        return update(sql);
    }
    //添加客户
    public static boolean addKeHu(TbKhinfo khinfo) {
        if (khinfo == null)
            return false;
        return insert("insert tb_khinfo values('" + khinfo.getId() + "','"
                + khinfo.getKhname() + "','" + khinfo.getJian() + "','"
                + khinfo.getAddress() + "','" + khinfo.getBianma() + "','"
                + khinfo.getTel() + "','" + khinfo.getFax() + "','"
                + khinfo.getLian() + "','" + khinfo.getLtel() + "','"
                + khinfo.getMail() + "','" + khinfo.getXinhang() + "','"
                + khinfo.getHao() + "')");
    }
    //客户信息更新
    public static int updateKeHu(TbKhinfo khinfo) {
        return update("update tb_khinfo set jian='" + khinfo.getJian()
                + "',address='" + khinfo.getAddress() + "',bianma='"
                + khinfo.getBianma() + "',tel='" + khinfo.getTel() + "',fax='"
                + khinfo.getFax() + "',lian='" + khinfo.getLian() + "',ltel='"
                + khinfo.getLtel() + "',mail='" + khinfo.getMail()
                + "',xinhang='" + khinfo.getXinhang() + "',hao='"
                + khinfo.getHao() + "' where id='" + khinfo.getId() + "'");
    }
    //更新库存单价
    public static int updateKucunDj(TbKucun kcInfo) {
        return update("update tb_kucun set dj=" + kcInfo.getDj()
                + " where id='" + kcInfo.getId() + "'");
    }
    //添加供应商
    public static boolean addGys(TbGysinfo gysInfo) {
        if (gysInfo == null)
            return false;
        return insert("insert tb_gysinfo values('" + gysInfo.getId() + "','"
                + gysInfo.getName() + "','" + gysInfo.getJc() + "','"
                + gysInfo.getAddress() + "','" + gysInfo.getBianma() + "','"
                + gysInfo.getTel() + "','" + gysInfo.getFax() + "','"
                + gysInfo.getLian() + "','" + gysInfo.getLtel() + "','"
                + gysInfo.getMail() + "','" + gysInfo.getYh() + "')");
    }
    //更新供应商信息
    public static int updateGys(TbGysinfo gysInfo) {
        return update("update tb_gysinfo set jc='" + gysInfo.getJc()
                + "',address='" + gysInfo.getAddress() + "',bianma='"
                + gysInfo.getBianma() + "',tel='" + gysInfo.getTel()
                + "',fax='" + gysInfo.getFax() + "',lian='" + gysInfo.getLian()
                + "',ltel='" + gysInfo.getLtel() + "',mail='"
                + gysInfo.getMail() + "',yh='" + gysInfo.getYh()
                + "' where id='" + gysInfo.getId() + "'");
    }
    //添加商品信息
    public static boolean addSp(TbSpinfo spInfo) {
        if (spInfo == null)
            return false;
        return insert("insert tb_spinfo values('" + spInfo.getId() + "','"
                + spInfo.getSpname() + "','" + spInfo.getJc() + "','"
                + spInfo.getCd() + "','" + spInfo.getDw() + "','"
                + spInfo.getGg() + "','" + spInfo.getBz() + "','"
                + spInfo.getPh() + "','" + spInfo.getPzwh() + "','"
                + spInfo.getMemo() + "','" + spInfo.getGysname() + "')");
    }
    //更新商品信息
    public static int updateSp(TbSpinfo spInfo) {
        return update("update tb_spinfo set jc='" + spInfo.getJc() + "',cd='"
                + spInfo.getCd() + "',dw='" + spInfo.getDw() + "',gg='"
                + spInfo.getGg() + "',bz='" + spInfo.getBz() + "',ph='"
                + spInfo.getPh() + "',pzwh='" + spInfo.getPzwh() + "',memo='"
                + spInfo.getMemo() + "',gysname='" + spInfo.getGysname()
                + "' where id='" + spInfo.getId() + "'");
    }
    //中转站
    public static String getXsthMainMaxId(Date date) {
        return getMainTypeTableMaxId(date, "tb_xsth_main", "XT", "xsthID");
    }
    public static ResultSet query(String QueryStr) {
        ResultSet set = findForResultSet(QueryStr);
        return set;
    }
    // 获取更类主表最大ID
    private static String getMainTypeTableMaxId(Date date, String table,String idChar, String idName) {
        String dateStr = date.toString().replace("-", "");
        String id = idChar + dateStr;
        String sql = "select max(" + idName + ") from " + table + " where "
                + idName + " like '" + id + "%'";
        ResultSet set = query(sql);
        String baseId = null;
        try {
            if (set.next())
                baseId = set.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        baseId = baseId == null ? "000" : baseId.substring(baseId.length() - 3);
        int idNum = Integer.parseInt(baseId) + 1;
        id += String.format("%03d", idNum);
        return id;
    }
    public static String getRuKuMainMaxId(Date date) {
        return getMainTypeTableMaxId(date, "tb_ruku_main", "RK", "rkid");
    }
    public static String getRkthMainMaxId(Date date) {
        return getMainTypeTableMaxId(date, "tb_rkth_main", "RT", "rkthId");
    }
    public static String getSellMainMaxId(Date date) {
        return getMainTypeTableMaxId(date, "tb_sell_main", "XS", "sellID");
    }
}
