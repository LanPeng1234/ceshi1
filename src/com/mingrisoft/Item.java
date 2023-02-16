package com.mingrisoft;
//通过id获取name并输出name
public class Item {
    private String id;
    private String name;
    public Item() {
    }
    public Item(String id,String name){
        this.id=id;
        this.name=name;
    }
    //使用Getter and Setters方法将数据表公共类的私有属性封装起来
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String toString(){
        return getName();
    }
}
