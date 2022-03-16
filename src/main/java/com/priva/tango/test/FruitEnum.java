package com.priva.tango.test;

import org.apache.tomcat.websocket.WsIOException;

public enum FruitEnum {
	Apple("酸", 50, "红色"), Melon("甜", 550, "黄色"), Water_Melon("甜", 5500, "绿色");
	
	private String taste;
	private int wight;
	private String color;
	private FruitEnum(String taste, int wight, String color){
		this.taste = taste;
		this.wight = wight;
		this.color = color;
	}
	public String getTaste() {
		return taste;
	}
	public void setTaste(String taste) {
		this.taste = taste;
	}
	public int getWight() {
		return wight;
	}
	public void setWight(int wight) {
		this.wight = wight;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public static FruitEnum getFruitByMoney(int num) {
		switch (num) {
		case 100:
			return Apple;
		case 500:
			return Water_Melon;
		default:
			return Melon;
		}
	}
}
