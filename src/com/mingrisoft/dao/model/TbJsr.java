package com.mingrisoft.dao.model;
//主要用于存储数据
public class TbJsr implements java.io.Serializable{
    private String name;
    private String sex;
    private String age;
    private String tel;
    public TbJsr(){}//缺省构造方法
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
}
