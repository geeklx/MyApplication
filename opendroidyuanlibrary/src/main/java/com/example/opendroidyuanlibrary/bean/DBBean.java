package com.example.opendroidyuanlibrary.bean;

import java.util.ArrayList;

public class DBBean {
	private int version;	// 数据库版本
	private String name;	// 数据库名
	private ArrayList<String> sqls = new ArrayList<String>(); // 建表语句

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addSql(String sql) {
		this.sqls.add(sql);
	}
	
	public ArrayList<String> getSqls() {
		return this.sqls;
	}
}
