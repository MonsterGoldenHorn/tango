package com.priva.tango.test;

import java.math.BigDecimal;
import java.text.ParseException;


public class Test {
	static int i = 2;
	public static void main(String[] args) throws ParseException {
		double a = 1.2d;
		double b = 0.2d;
		System.out.println(a/b);

		BigDecimal c = new BigDecimal(1.2);
		BigDecimal d = new BigDecimal(0.2);
		System.out.println(c.divide(d, 0, BigDecimal.ROUND_UP));
	}
	
	static void add(Integer i) {
		i=i+1;
	}
	
	void ifelse() {
		if(i==1) {
			
		}else if(i==2) {
			System.out.println(111);
		}
	}
	
	void ifelse2() {
		if(i==1) {
			
		}
		else 
		if(i==2) {
			System.out.println(111);
		}
	}
	
	void ifelse3() {
		if(i==1) {
			
		}
		else 
		if(i==2) 
			System.out.println(111);
	}
}


class F {
	private String id;
	private String plain;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlain() {
		return plain;
	}
	public void setPlain(String plain) {
		this.plain = plain;
	}
}

class D extends F{
	private String id;
	private String name;
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
}