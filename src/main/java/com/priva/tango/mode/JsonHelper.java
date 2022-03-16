package com.priva.tango.mode;

import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.alibaba.fastjson.JSONObject;

public class JsonHelper {
	private static ThreadLocal<Node> node = new ThreadLocal<Node>();
	public void set(String key, String value) {
	}
	
	static void parse(String jsonString){
		char[] ch = jsonString.toCharArray();
		String name = null;
		String value = null;
		int head = 0;
		int tail = ch.length-1;
		
		
		
	}
	
}

class Node{
	Node parent;
	Node[] childs;
	
	private String name;
	private String value;
}