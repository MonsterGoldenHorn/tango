package com.priva.tango.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.util.StopWatch;

public class MapReduceMain {
	/**
	 * 100W快3倍
	 * 10W快10多倍
	 * @param args
	 */
	public static void main(String[] args) {
		Goods good = new Goods(1, 2.5d);
//		List<Goods> list = Arrays.asList(new Goods(1, 2.0d),new Goods(2, 3.0d),good,new Goods(1, 1.0d));
		List<Goods> list = new ArrayList<Goods>();
		for (int i = 0; i < 100000; i++) {
			list.add(new Goods(i, 2.5d));
		}
		StopWatch st = new StopWatch("good");
		st.start("good lambdaloop parallel");
		Double double1 = list.parallelStream().parallel().map(goods->goods.getNum()*goods.getPrice()).reduce((sum,price)->sum+price).get();
		System.out.println(double1);
		st.stop();
		
		st.start("good iterator loop");
		Double double2 = 0d;
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Goods goods2 = (Goods) iterator.next();
			double2 = double2+goods2.getNum()*goods2.getPrice();
		}
		System.out.println(double2);
		st.stop();
		
		st.start("good for loop");
		Double double3 = 0d;
		for (Goods goods2 : list) {
			double3 = double3+goods2.getNum()*goods2.getPrice();
		}
		System.out.println(double3);
		st.stop();
		System.out.println(st.prettyPrint());
	}
}

class Goods{
	Integer num;
	Double price;
	public Goods(Integer num, Double price) {
		this.num = num;
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
}