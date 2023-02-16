package com.mingrisoft.dao.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

//主要用于存储数据
public class TbSellMain implements Serializable{
    private String sellId;
    private String pzs;
    private String je;
    private String ysjl;
    private String khname;
    private String xsdate;
    private String czy;
    private String jsr;
    private String jsfs;
    private Set tbSellDetails = new HashSet(0);
    public TbSellMain(){}//缺省构造方法
    public TbSellMain(String sellId, String pzs, String je, String ysjl, String khname, String xsdate, String czy,
                      String jsr, String jsfs) {// �������캯��
        this.sellId = sellId;
        this.pzs = pzs;
        this.je = je;
        this.ysjl = ysjl;
        this.khname = khname;
        this.xsdate = xsdate;
        this.czy = czy;
        this.jsr = jsr;
        this.jsfs = jsfs;
        this.tbSellDetails = tbSellDetails;
    }
    public String getJsr() {
        return jsr;
    }
    public void setJsr(String jsr) {
        this.jsr = jsr;
    }
    public String getSellId() {
        return sellId;
    }
    public void setSellId(String SellID) {
        this.sellId = SellID;
    }
    public String getPzs() {
        return pzs;
    }
    public void setPzs(String pzs) {
        this.pzs = pzs;
    }
    public String getJe() {
        return je;
    }
    public void setJe(String je) {
        this.je = je;
    }
    public String getYsjl() {
        return ysjl;
    }
    public void setYsjl(String ysjl) {
        this.ysjl = ysjl;
    }
    public String getKhname() {
        return khname;
    }
    public void setKhname(String khname) {
        this.khname = khname;
    }
    public String getXsdate() {
        return xsdate;
    }
    public void setXsdate(String xsdate) {
        this.xsdate = xsdate;
    }
    public String getCzy() {
        return czy;
    }
    public void setCzy(String czy) {
        this.czy = czy;
    }
    public String getJsfs() {
        return jsfs;
    }
    public void setJsfs(String jsfs) {
        this.jsfs = jsfs;
    }

    public Set getTbSellDetails() {
        return tbSellDetails;
    }

    public void setTbSellDetails(Set tbSellDetails) {
        this.tbSellDetails = tbSellDetails;
    }
}
