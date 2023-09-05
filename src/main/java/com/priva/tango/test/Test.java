package com.priva.tango.test;

import java.math.BigDecimal;
import java.sql.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Test {
	static int i = 2;
	public static void main(String[] args) throws ParseException {
		Calendar instance = Calendar.getInstance();
		instance.setTime(new Date());
		int weeksInWeekYear = instance.getWeeksInWeekYear();
		System.out.println(weeksInWeekYear);

		List<String> l1 = new ArrayList<String>();
		l1.add("1");
		l1.add("2");

		List<String> l2 = new ArrayList<String>();
		l2.add("1");

		System.out.println(l1.containsAll(l2));

	}
}

