package com.mingrisoft.dao.model;
//主要用于存储数据
public class TbXsthDetail implements java.io.Serializable{
    private Integer id;
    private String spid;
    private String tbXsthMain;
    private Double dj;
    private int sl;
    public TbXsthDetail(){}//缺省构造方法
    public TbXsthDetail(String tbXsthMain, String spid, Double dj, Integer sl) {// �������캯��
        this.tbXsthMain = tbXsthMain;
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

    public String getSpid() {
        return spid;
    }

    public void setSpid(String spid) {
        this.spid = spid;
    }

    public String getTbXsthMain() {
        return tbXsthMain;
    }

    public void setTbXsthMain(String tbXsthMain) {
        this.tbXsthMain = tbXsthMain;
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
