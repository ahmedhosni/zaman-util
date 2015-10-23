package com.bytesnapper.test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.bytesnapper.zaman.util.DateUtil;

public class AddMonths {

	@Test
	public void test() {
		Calendar calendar = Calendar.getInstance();
		int months = 10;
		int inMonth = calendar.get(Calendar.MONTH);
		int inYear = calendar.get(Calendar.YEAR);

		Date outputDate = DateUtil.addMonths(calendar.getTime(), months);
		calendar.setTime(outputDate);
		int outMonth = calendar.get(Calendar.MONTH);
		int outYear = calendar.get(Calendar.YEAR);

		if (months > 0 && inMonth > outMonth && !(outYear > inYear)) {
			fail("Months param  greater than 0 and output month is not greater than input month");
		}
		/*
		System.out.println("Input Date: "+ inMonth+" " + outYear);
		System.out.println("Output Date: "+outMonth + " " +outYear);
		*/
		months=-2;
		inMonth = calendar.get(Calendar.MONTH);
		inYear = calendar.get(Calendar.YEAR);
		outputDate = DateUtil.addMonths(calendar.getTime(), months);
		calendar.setTime(outputDate);
		outMonth = calendar.get(Calendar.MONTH);
		outYear = calendar.get(Calendar.YEAR);
		
	/*	System.out.println("Input Date: "+ inMonth+" " + outYear);
		System.out.println("Output Date: "+outMonth + " " +outYear);*/

		
		if (months < 0 && inMonth < outMonth && !(outYear < inYear)) {
			fail("Months param is less than 0 and output month is not less than input month");
		}
		
		months=0;
		inMonth = calendar.get(Calendar.MONTH);
		inYear = calendar.get(Calendar.YEAR);
		
		outputDate = DateUtil.addMonths(calendar.getTime(), months);
		calendar.setTime(outputDate);
		outMonth = calendar.get(Calendar.MONTH);
		outYear = calendar.get(Calendar.YEAR);
		
/*		System.out.println("Input Date: "+ inMonth+" " + outYear);
		System.out.println("Output Date: "+outMonth + " " +outYear);
*/
		if (months == 0 && inMonth != outMonth && outYear != inYear) {
			fail("Months param is equal to 0 and output month is not greater than input month");
		}

		/*System.out.println("Input Date: "+ inMonth+" " + outYear);
		System.out.println("Output Date: "+outMonth + " " +outYear);*/

	}

}
