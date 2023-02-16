package com.mingrisoft.dao.model;
//主要用于存储数据
public class TbRukuDetail implements java.io.Serializable{
    private Integer id;
    private String tbSpinfo;
    private String tbRukuMain;
    private Double dj;
    private int sl;
    public TbRukuDetail(){}//缺省构造方法
    public TbRukuDetail(String tbSpinfo, String tbRukuMain, Double dj, Integer sl) {// �������캯��
        this.tbSpinfo = tbSpinfo;
        this.tbRukuMain = tbRukuMain;
        this.dj = dj;
        this.sl = sl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTbSpinfo() {
        return tbSpinfo;
    }

    public void setTbSpinfo(String tbSpinfo) {
        this.tbSpinfo = tbSpinfo;
    }

    public String getTbRukuMain() {
        return tbRukuMain;
    }

    public void setTbRukuMain(String tbRukuMain) {
        this.tbRukuMain = tbRukuMain;
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
