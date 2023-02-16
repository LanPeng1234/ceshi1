package com.mingrisoft.dao.model;
//主要用于存储数据
public class TbKhinfo implements java.io.Serializable{
    private String id;
    private String khname;
    private String jian;
    private String address;
    private String bianma;
    private String tel;
    private String fax;
    private String lian;
    private String ltel;
    private String mail;
    private String xinhang;
    private String hao;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getKhname() {
        return khname;
    }
    public void setKhname(String khname) {
        this.khname = khname;
    }
    public String getJian() {
        return jian;
    }
    public void setJian(String jian) {
        this.jian = jian;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getBianma() {
        return bianma;
    }
    public void setBianma(String bianma) {
        this.bianma = bianma;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getFax() {
        return fax;
    }
    public void setFax(String fax) {
        this.fax = fax;
    }
    public String getLian() {
        return lian;
    }
    public void setLian(String lian) {
        this.lian = lian;
    }
    public String getLtel() {
        return ltel;
    }
    public void setLtel(String ltel) {
        this.ltel = ltel;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getXinhang() {
        return xinhang;
    }
    public void setXinhang(String xinhang) {
        this.xinhang = xinhang;
    }
    public String getHao() {
        return hao;
    }
    public void setHao(String hao) {
        this.hao = hao;
    }
    public TbKhinfo(){}//缺省构造方法
    public TbKhinfo(String id){this.id=id;}
    public TbKhinfo(String id, String khname, String jian, String address, String bianma, String tel, String fax,
                    String lian, String ltel, String mail, String xinhang, String hao) {
        this.id = id;
        this.khname = khname;
        this.jian = jian;
        this.address = address;
        this.bianma = bianma;
        this.tel = tel;
        this.fax = fax;
        this.lian = lian;
        this.ltel = ltel;
        this.mail = mail;
        this.xinhang = xinhang;
        this.hao = hao;
    }
    @Override
    public int hashCode(){
        final  int PRIME=31;
        int result=1;
        result=PRIME*result+((fax==null)?0:fax.hashCode());
        result=PRIME*result+((address==null)?0:address.hashCode());
        result=PRIME*result+((bianma==null)?0:bianma.hashCode());
        result=PRIME*result+((tel==null)?0:tel.hashCode());
        result=PRIME*result+((xinhang==null)?0:xinhang.hashCode());
        result=PRIME*result+((id==null)?0:id.hashCode());
        result=PRIME*result+((jian==null)?0:jian.hashCode());
        result=PRIME*result+((mail==null)?0:mail.hashCode());
        result=PRIME*result+((lian==null)?0:lian.hashCode());
        result=PRIME*result+((ltel==null)?0:ltel.hashCode());
        result=PRIME*result+((khname==null)?0:khname.hashCode());
        result=PRIME*result+((hao==null)?0:hao.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj){
        if(this==obj){
            return true;
        }
        if (obj==null){
            return false;
        }
        if (getClass()!=obj.getClass()){
            return false;
        }
        final  TbKhinfo other=(TbKhinfo) obj;
        if(fax==null){
            if (other.fax!=null){
                return false;
            }
        }
        else if(!fax.equals(other.fax)){
            return false;
        }
        if(address==null){
            if (other.address!=null){
                return false;
            }
        }
        else if(!address.equals(other.address)){
            return false;
        }
        if(bianma==null){
            if (other.bianma!=null){
                return false;
            }
        }
        else if(!bianma.equals(other.bianma)){
            return false;
        }
        if(tel==null){
            if (other.tel!=null){
                return false;
            }
        }
        else if(!tel.equals(other.tel)){
            return false;
        }
        if(xinhang==null){
            if (other.xinhang!=null){
                return false;
            }
        }
        else if(!xinhang.equals(other.xinhang)){
            return false;
        }
        if(id==null){
            if (other.id!=null){
                return false;
            }
        }
        else if(!id.equals(other.id)){
            return false;
        }
        if(jian==null){
            if (other.jian!=null){
                return false;
            }
        }
        else if(!jian.equals(other.jian)){
            return false;
        }
        if(mail==null){
            if (other.mail!=null){
                return false;
            }
        }
        else if(!mail.equals(other.mail)){
            return false;
        }
        if(lian==null){
            if (other.lian!=null){
                return false;
            }
        }
        else if(!lian.equals(other.lian)){
            return false;
        }
        if(ltel==null){
            if (other.ltel!=null){
                return false;
            }
        }
        else if(!ltel.equals(other.ltel)){
            return false;
        }
        if(khname==null){
            if (other.khname!=null){
                return false;
            }
        }
        else if(!khname.equals(other.khname)){
            return false;
        }
        if(hao==null){
            if (other.hao!=null){
                return false;
            }
        }
        else if(!hao.equals(other.hao)){
            return false;
        }
        return true;
    }
}
