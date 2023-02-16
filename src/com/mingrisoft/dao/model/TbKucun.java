package com.mingrisoft.dao.model;
//主要用于存储数据
public class TbKucun implements java.io.Serializable{
    private String id;
    private String spname;
    private String jc;
    private String cd;
    private String gg;
    private String bz;
    private String dw;
    private Double dj;
    private Integer kcsl;
    public TbKucun(){}//缺省构造方法
    public TbKucun(String id) {
        this.id = id;
    }
    public TbKucun(String id, String spname, String jc, String cd, String gg, String bz, String dw, Double dj,
                   Integer kcsl) {
        this.id = id;
        this.spname = spname;
        this.jc = jc;
        this.cd = cd;
        this.gg = gg;
        this.bz = bz;
        this.dw = dw;
        this.dj = dj;
        this.kcsl = kcsl;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSpname() {
        return spname;
    }
    public void setSpname(String spname) {
        this.spname = spname;
    }
    public String getJc() {
        return jc;
    }
    public void setJc(String jc) {
        this.jc = jc;
    }
    public String getCd() {
        return cd;
    }
    public void setCd(String cd) {
        this.cd = cd;
    }
    public String getGg() {
        return gg;
    }
    public void setGg(String gg) {
        this.gg = gg;
    }
    public String getBz() {
        return bz;
    }
    public void setBz(String bz) {
        this.bz = bz;
    }
    public String getDw() {
        return dw;
    }
    public void setDw(String dw) {
        this.dw = dw;
    }
    public Double getDj() {
        return dj;
    }
    public void setDj(Double dj) {
        this.dj = dj;
    }
    public Integer getKcsl() {
        return kcsl;
    }
    public void setKcsl(Integer kcsl) {
        this.kcsl = kcsl;
    }
    public String toString() {
        return getSpname();
    }
}
