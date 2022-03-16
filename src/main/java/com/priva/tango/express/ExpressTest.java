package com.priva.tango.express;

import com.wisesoft.commons.form.core.util.DateExpress;
import org.apache.commons.jexl3.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressTest {

    public static void main(String[] args) {

//        String s = "文本内容{{now}}{{now.year+11}}{{now.month}}{{now.week}}{{now.day+30}}{{now-10}}";
        String s = "文本内容 {{now.month-1}}";
        s = s.replaceAll("\\s","");
        String regexp = "\\{\\{[a-z]+[.\\w\\\\+\\-\\d]*\\}\\}";
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(s );
        StringBuffer ret = new StringBuffer(s);
        List<ExpressStack> list = new ArrayList<>();
        while (matcher.find()) {
            String group = matcher.group();
            int start = matcher.start();
            int end = matcher.end();
            group = group.replaceAll("\\{\\{","");
            group = group.replaceAll("\\}\\}","");
            DateExpress express = new DateExpress(new Date(1625500800000l), new Date(1625500800000l));
            String parse = express.parse(group);
            list.add(new ExpressStack(start, end, parse));
        }

        Collections.reverse(list);
        list.forEach(item ->{
            ret.replace(item.start, item.end, item.getValue());
        });

        System.out.println(ret);
    }


    private static String count(String express){
        express = express.replaceAll("\\{\\{","");
        express = express.replaceAll("\\}\\}","");
        express = express.replaceAll("\\s","");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        if(express.contains("now")){

        }

        // 初始化Jexl构造器
        JexlBuilder jexlBuilder = new JexlBuilder();
        // 创建Jexl表达式引擎
        JexlEngine jexlEngine = jexlBuilder.create();
        JexlExpression expression = jexlEngine.createExpression(express);

        JexlContext jexlContext = new MapContext();
        jexlContext.set("now", 15);
        jexlContext.set("now.year", 12);
        jexlContext.set("now.month", 13);
        jexlContext.set("now.week", 11);
        jexlContext.set("now.day", 5);
        Object evaluate = expression.evaluate(jexlContext);
        System.out.println(evaluate);
//        String regexp = "[a-z]+[\\w.]*";
//        Pattern pattern = Pattern.compile(regexp);
//        Matcher matcher = pattern.matcher(express);
//        while (matcher.find()) {
//            String group = matcher.group();
//            System.out.println(group);
//        }
        return "";
    }

    private static List<DateExpress> parse(String express){
//        char[] chars = express.toCharArray();
//        int idx = 0;
//        while (idx < chars.length){
//
//        }
        String regx1 = "^[a-z]+$";
        String regx2 = "^[a-z]+.[a-z]+$";
        String regx3 = "^[a-z]+.[a-z]+\\\\+\\d+$";
        String regx4 = "^[a-z]+.[a-z]+-\\d+$";
        return null;
    }

}
class ExpressStack{
    int start;
    int end;
    String value;
    String express;

    public ExpressStack(int start, int end, String value) {
        this.start = start;
        this.end = end;
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}