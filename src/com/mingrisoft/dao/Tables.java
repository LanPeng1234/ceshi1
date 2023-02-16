package com.mingrisoft.dao;
import java.util.ArrayList;
public class Tables {
    private String name;
    private ArrayList<Columns> columns = new ArrayList<Columns>();
    public Tables() {
        super();
    }
    public Tables(String name, ArrayList<Columns> columns) {
        super();
        this.name = name;
        this.columns = columns;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Columns> getColumns() {
        return columns;
    }
    public void setColumns(ArrayList<Columns> columns) {
        this.columns = columns;
    }
}
