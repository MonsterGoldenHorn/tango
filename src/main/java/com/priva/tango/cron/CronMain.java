package com.priva.tango.cron;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.redisson.executor.CronExpression;

/**
 * CronMain.java
 *
 * @author tangjc
 * @date 2021年4月20日
 */
public class CronMain {
	static String dayReport="0 0 0 * * ?";//日报 每天
	static String weekReport="0 0 0 ? * WED";//周报 每周三
	static String monthReport="0 0 0 1 * ?";//月报
	static String halfMonthReport="0 0 0 1,15 * ?";//半月报
	static String yearReport="0 0 0 1 1 ?";//年报
	static String halfYearReport="0 0 0 1 1,7 ?";//半年报
	static String quartReport="0 0 0 1 1,4,7,10 ?";//季报
	static String tendaysReport="0 0 0 1,11,21 * ?";//旬报
	
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, 1);
		System.out.println(c.getActualMaximum(Calendar.DAY_OF_MONTH));
		c.set(Calendar.DATE, 31);
		System.out.println(c.getActualMaximum(Calendar.DAY_OF_MONTH));
	}
	
//	public static void main(String[] args) {
//		String format = "yyyy-MM-dd";
//		CronExpression cron = new CronExpression(tendaysReport);
//		Date next = cron.getNextValidTimeAfter(new Date());
//		Date next2 = cron.getNextValidTimeAfter(next);
//		System.out.println("下一次"+DateFormatUtils.format(next, format));
//		System.out.println("下下一次"+DateFormatUtils.format(next2, format));
//		Date finalFireTime = cron.getFinalFireTime();
//		System.out.println(finalFireTime);
//		System.out.println("本次"+DateFormatUtils.format(finalFireTime, format));
//	}
}
