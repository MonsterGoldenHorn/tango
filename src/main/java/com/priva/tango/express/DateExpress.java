package com.wisesoft.commons.form.core.util;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 日期表达式
 */
public class DateExpress {
    private static String regx1 = "^[a-z]+$";
    private static String regx2 = "^[a-z]+.[a-z]+$";
    private static String regx3 = "^[a-z]+.[a-z]+\\+\\d+$";
    private static String regx4 = "^[a-z]+.[a-z]+-\\d+$";
    private Calendar c ;
    private Date now = new Date();
    private Date report = null;

    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(1625500800000l));
//        c.setTimeInMillis(1625500800000l);
        c.add(Calendar.MONTH, -1);
        System.out.println(c.get(Calendar.MONTH)+1);
    }

    public DateExpress(Date now, Date report) {
        this.now = now;
        this.report = report;
        c = Calendar.getInstance();
    }

    public String parse(String express){
        if(Pattern.matches(regx1, express)){
            return parseRedirect(express);
        }else if(Pattern.matches(regx2, express)){
            return parseProperty(express);
        }else if (Pattern.matches(regx3, express)){
            return parseRule(express, "+");
        }else if (Pattern.matches(regx4, express)){
            return parseRule(express, "-");
        }else{
            return express;
        }
    }

    /**
     * 单表达式直接解析
     * @param express
     * @return
     */
    private String parseRedirect(String express){
        if("now".equals(express)){
            c.setTime(now);
        }else if("publish".equals(express)){
            c.setTime(report);
        }
        return c.get(Calendar.YEAR)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.DATE);
    }

    /**
     * 单表达式解析属性
     * @param express
     * @return
     */
    private String parseProperty(String express){
        String[] split = express.split("\\.");
        if("now".equals(split[0])){
            c.setTime(now);
        }else if("publish".equals(split[0])){
            c.setTime(report);
        }
        return String.valueOf(getProperty(split[1], c));
    }

    /**
     * 表达式计算
     * @param express 变量
     * @param p 加减符号
     * @param num 加减数量
     * @return
     */
    private String parseProperty(String express, String p, int num){
        String[] split = express.split("\\.");
        //设置基准时间
        if("now".equals(split[0])){
            c.setTime(now);
        }else if("publish".equals(split[0])){
            c.setTime(report);
        }
        //设置时间加减
        int amount = Integer.valueOf(p+num);
        if(split.length == 1){
            c.add(Calendar.DATE, amount);
            return c.get(Calendar.YEAR)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.DATE);
        }else{
            this.addDate(split[1], c, amount);
            return String.valueOf(this.getProperty(split[1], c));
        }

    }

    /**
     * 加减规则
     * @param express
     * @return
     */
    private String parseRule(String express, String format){
        int i = express.indexOf(format);
        String var = express.substring(0, i);
        String consta = express.substring(i+1);
        int num = Integer.valueOf(consta);
        return this.parseProperty(var, format, num);
    }

    private int getProperty(String p, Calendar c){
        switch (p){
            case "day":
                return c.get(Calendar.DATE);
            case "month":
                return c.get(Calendar.MONTH) + 1;
            case "year":
                return c.get(Calendar.YEAR);
            case "week":
                return c.get(Calendar.WEEK_OF_YEAR);
            default:
                return 0;
        }
    }

    private void addDate(String property, Calendar c, int amount){
        switch (property){
            case "day":
                c.add(Calendar.DATE, amount);
            case "month":
                c.add(Calendar.MONTH, amount);
            case "year":
                c.add(Calendar.YEAR, amount);
            case "week":
                c.add(Calendar.WEEK_OF_YEAR, amount);
            default:
        }
    }

    public static class ExpressStack{
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

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }
}
