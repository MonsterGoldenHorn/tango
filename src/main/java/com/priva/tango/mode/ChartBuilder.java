package com.priva.tango.mode;

import java.util.Set;
import java.util.Iterator;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import io.netty.handler.codec.json.JsonObjectDecoder;

public class ChartBuilder {
	static final String title = "title.text";
	static final String barSeries = "series.data";
	private JSONObject json;
	public ChartBuilder(String jsonString) {
		this.json = JSONObject.parseObject(jsonString);
	}
	@Override
	public String toString() {
		return JSONObject.toJSONString(json);
	}
	public void explainPath(String path){
		this.path = path.split("\\.");
	}
	
	public ChartBuilder title(String value) {
		explainPath(title);
		JSONObject v = json;
		for (int i = 0; i < path.length-1; i++) {
			v = (JSONObject) v.get(path[i]);
		}
		if(v.containsKey(path[path.length-1])) {
			v.put(path[path.length-1], value);
		}
		return this;
	}
	
	public ChartBuilder series(String value) {
		explainPath(barSeries);
		JSONObject v = json;
		for (int i = 0; i < path.length-1; i++) {
			v = (JSONObject) v.get(path[i]);
		}
		if(v.containsKey(path[path.length-1])) {
			v.put(path[path.length-1], value);
		}
		return this;
	}
	public static Object findObject(String key, String json) {
		Object ret = null; 
		if(JSONObject.isValidArray(json)) {
			ret = JSONObject.parseArray(json);
		}else if(JSONObject.isValidObject(json)) {
			Set<Entry<String, Object>> entrySet = JSONObject.parseObject(json).entrySet();
			for (Iterator iterator = entrySet.iterator(); iterator.hasNext();) {
				Entry<String, Object> entry = (Entry<String, Object>) iterator.next();
				if(key.equals(entry.getKey())) {
					ret = entry.getValue();
				}
			}
		}
		return ret;
	}
	
	public ChartBuilder legend(String value) {
		return this;
	}
	
	private String[] path;
	static final String legend = "legend.data";
	static final String xAxis = "xAxis.data";
	
	static final String pieSeries = "series.data";

}



enum DataItem{
	MapItem{
		void setValue(String value){
			
		}
	},ArrayItem{
		void setValue(String value){
			
		}
	};
	private String path;

	DataItem() {
		
	}
	
	private void setPath(String path) {
		this.path = path;
	}
}