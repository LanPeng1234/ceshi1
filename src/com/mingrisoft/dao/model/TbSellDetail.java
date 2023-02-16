package com.mingrisoft.dao.model;
//主要用于存储数据
public class TbSellDetail implements java.io.Serializable{
    private Integer id;
    private String tbSellMain;
    private String spid;
    private Double dj;
    private int sl;
    public TbSellDetail(){}//缺省构造方法
    public TbSellDetail(String tbSellMain, String spid, Double dj, Integer sl) {// �������캯��
        this.tbSellMain = tbSellMain;
        this.spid = spid;
        this.dj = dj;
        this.sl = sl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTbSellMain() {
        return tbSellMain;
    }

    public void setTbSellMain(String tbSellMain) {
        this.tbSellMain = tbSellMain;
    }

    public String getSpid() {
        return spid;
    }

    public void setSpid(String spid) {
        this.spid = spid;
    }

    public Double getDj() {
        return dj;
    }

    public void setDj(Double dj) {
        this.dj = dj;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }
}
