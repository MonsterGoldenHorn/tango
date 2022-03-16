package com.priva.tango.ezexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EasyExcelMain {

    public static void main(String[] args) {
        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
        // {} 代表普通变量 {.} 代表是list的变量 {前缀.} 前缀可以区分不同的list
        String templateFileName = "D:\\composite.xlsx";

        ExcelWriter excelWriter = EasyExcel.write("D:\\1.xlsx").withTemplate(templateFileName).build();
        WriteSheet writeSheet = EasyExcel.writerSheet(0).build();

        List list2 = new ArrayList(2);
        Map map3 = new HashMap();
        map3.put("name", "fff");
        map3.put("age", "18");
        list2.add(map3);

        Map map4 = new HashMap();
        map4.put("name", "xxxx");
        map4.put("age", "99");
        list2.add(map4);

        excelWriter.fill(list2, writeSheet);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("date", "2021-10");
        map.put("total", 1000);
        excelWriter.fill(map, writeSheet);


        // 别忘记关闭流
        excelWriter.finish();
    }
}
