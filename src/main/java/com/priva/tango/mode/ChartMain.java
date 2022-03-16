package com.priva.tango.mode;

import java.util.HashMap;

public class ChartMain {
	public static void main(String[] args) {
		String jsonString = "{title:{text:'堆叠区域图'},tooltip:{trigger:'axis',axisPointer:{type:'cross',label:{backgroundColor:'#6a7985'}}},legend:{data:[]},toolbox:{feature:{saveAsImage:{}}},grid:{left:'3%',right:'4%',bottom:'3%',containLabel:true},xAxis:[{type:'category',boundaryGap:false,data:[]}],yAxis:[{type:'value'}],series:[{name:'邮件营销',type:'line',stack:'总量',areaStyle:{},data:[]},{name:'联盟广告',type:'line',stack:'总量',areaStyle:{},data:[220,182,191,234,290,330,310]},{name:'视频广告',type:'line',stack:'总量',areaStyle:{},data:[150,232,201,154,190,330,410]},{name:'直接访问',type:'line',stack:'总量',areaStyle:{},data:[320,332,301,334,390,330,320]},{name:'搜索引擎',type:'line',stack:'总量',label:{normal:{show:true,position:'top'}},areaStyle:{},data:[820,932,901,934,1290,1330,1320]}]}";
		ChartBuilder c = new ChartBuilder(jsonString);
		c.title("1");
		System.out.println(c.title("1"));
	}
}
