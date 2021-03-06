package com.bytesnapper.zaman.util;

import java.util.Calendar;
import java.util.Date;

import com.bytesnapper.zaman.common.Interval;
import com.bytesnapper.zaman.exceptions.NegativeIntervalException;
/**
 * 
 * @author Ahmed Hosni
 *
 */
public class ZamanUtil {

	private static Calendar calendar = Calendar.getInstance();

	/**
	 * 
	 * @param date
	 *            input date to add days to
	 * @param numberOfDays
	 *            if number of days is less than 0,the number of days will be
	 *            removed from the input date
	 * @return date after adding the number of days
	 */
	public static Date addDaysWithoutResttingTime(Date date, int numberOfDays) {
		calendar.setTime(date);
		calendar.add(Calendar.DATE, numberOfDays);
		return calendar.getTime();
	}
	
	
//		

	/**
	 * 
	 * @param date
	 *            input date to add days to
	 * @param numberOfDays
	 *            if number of days is less than 0,the number of days will be
	 *            subtracted from the input date
	 * @return date after adding the number of days
	 */
	public static Date addDays(Date date, int numberOfDays) {
		Date resetDate = resetTime(date);
		calendar.setTime(resetDate);
		calendar.add(Calendar.DATE, numberOfDays);
		return calendar.getTime();
	}
	/**
	 * 
	 * @param date  input date to add months to
	 * @param months if number of months is less than 0,the number of days will be
	 *            subtracted from the input date
	 * @return date after adding months
	 */
	public static Date addMonths(Date date, int months) {
		Date inputDate = resetTime(date);
		calendar.setTime(inputDate);
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}
	/**
	 * 
	 * @param date  input date to add months to
	 * @param years if number of years is less than 0,the number of days will be
	 *            subtracted from the input date
	 * @return date after adding years
	 */
	public static Date addYears(Date date, int years) {
		Date inputDate = resetTime(date);
		calendar.setTime(inputDate);
		calendar.add(Calendar.YEAR, years);
		return calendar.getTime();
		
	}

	/**
	 * 
	 * @param fromDate date to start with
	 * @param toDate   date to end with
	 * @return an interval 
	 */
	public static Interval subtractDate(Date fromDate, Date toDate) {
		if(fromDate.after(toDate)){
			throw new NegativeIntervalException("Interval cannot be negative.");
		}
		calendar.setTime(fromDate);
		int fromYears=calendar.get(Calendar.YEAR);
		int fromMonths=calendar.get(Calendar.MONTH);
		int fromDays=calendar.get(Calendar.DAY_OF_MONTH);
		int fromHours=calendar.get(Calendar.HOUR_OF_DAY);
		int fromMinutes=calendar.get(Calendar.MINUTE);
		int fromSeconds=calendar.get(Calendar.SECOND);
		
		calendar.setTime(toDate);
		int toYears=calendar.get(Calendar.YEAR);
		int toMonths=calendar.get(Calendar.MONTH);
		int toDays=calendar.get(Calendar.DAY_OF_MONTH);
		int toHours=calendar.get(Calendar.HOUR_OF_DAY);
		int toMinutes=calendar.get(Calendar.MINUTE);
		int toSeconds=calendar.get(Calendar.SECOND);
		
		return subtractor(fromYears, fromMonths, fromDays, fromHours, fromMinutes, fromSeconds, toYears, toMonths, toDays, toHours, toMinutes, toSeconds);
	}

	
	/**
	 * 
	 * @param first interval
	 * @param second interval
	 * @return interval contains adding result
	 */
	public static Interval addIntervals(Interval first, Interval second) {
		Interval result=adder(first.getYears(), first.getMonths(), first.getDays(),first.getHours(), first.getMinutes(), first.getSeconds(),
				 second.getYears(), second.getMonths(), second.getDays(),second.getHours(), second.getMinutes(), second.getSeconds());
		return result;
}

	/**
	 * 
	 * @param first interval
	 * @param second interval
	 * @return interval contains subtraction result
	 */
	public static Interval subtractIntervals(Interval first, Interval second) {
		 if(first.greaterThan(second)){
				throw new NegativeIntervalException("Interval cannot be negative.");
		 }
		Interval result=subtractor(first.getYears(), first.getMonths(), first.getDays(),first.getHours(), first.getMinutes(), first.getSeconds(),
						 second.getYears(), second.getMonths(), second.getDays(),second.getHours(), second.getMinutes(), second.getSeconds());
		return result;
	}

	/**
	 * 
	 * @return today Date after resetting time
	 */
	public static Date getToday() {
		return resetTime(new Date());
	}

	/**
	 * 
	 * @param date
	 *            input date
	 * @return rests hours,minutes and seconds to the beginning of the day to be
	 *         like Sat Oct 24 00:00:00 EET 2015
	 */
	public static Date resetTime(Date date) {
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
		Date outputDate = new Date(calendar.getTimeInMillis());
		return outputDate;
	}
	
	/**Subtracts members of the interval  
	 * 
	 * @param fromYears
	 * @param fromMonths
	 * @param fromDays
	 * @param fromHours
	 * @param fromMinutes
	 * @param fromSeconds
	 * @param toYears
	 * @param toMonths
	 * @param toDays
	 * @param toHours
	 * @param toMinutes
	 * @param toSeconds
	 * @return
	 */
	private static Interval subtractor(int fromYears,int fromMonths,int fromDays,int fromHours,int fromMinutes,int fromSeconds,int toYears,int toMonths,int toDays,int toHours,int toMinutes,int toSeconds){
	
		if(toSeconds<fromSeconds){
			toMinutes--;
			toSeconds+=60;
		}
		
		int outputSeconds=toSeconds-fromSeconds;
		
		if(toMinutes<fromMinutes){
			toHours--;
			toMinutes+=60;
		}
		
		int outputMinutes=toMinutes-fromMinutes;
		
		if(toHours<fromHours){
			toDays--;
			toHours+=24;
		}
		
		int outputHours=toHours-fromHours;
		

		if(toDays<fromDays){
			toMonths--;
			toDays+=30;
		}
		
		int outputDays=toDays-fromDays;
		
		if(toMonths<fromMonths){
			toYears--;
			toMonths+=12;
		}
		
		int outputMonths=toMonths-fromMonths;
		
		
			int outputYears=toYears-fromYears;
			Interval interval = new Interval();
			interval.setSeconds(outputSeconds);
			interval.setMinutes(outputMinutes);
			interval.setHours(outputHours);
			interval.setDays(outputDays);
			interval.setMonths(outputMonths);
			interval.setYears(outputYears);


			return interval;
	
	}
	
	
	private static Interval adder(int fromYears,int fromMonths,int fromDays,int fromHours,int fromMinutes,int fromSeconds,int toYears,int toMonths,int toDays,int toHours,int toMinutes,int toSeconds){
		
		int outputSeconds=toSeconds+fromSeconds;

		if(outputSeconds>59){
			toMinutes++;
			outputSeconds-=60;
		}
		
		int outputMinutes=toMinutes+fromMinutes;

		
		if(outputMinutes>59){
			toHours++;
			outputMinutes-=60;
		}
		
		int outputHours=toHours+fromHours;

		if(outputHours>23){
			toDays++;
			outputHours-=24;
		}
		
		int outputDays=toDays+fromDays;


		if(outputDays>29){
			toMonths++;
			outputDays-=30;
		}
		
		int outputMonths=toMonths+fromMonths;

		if(outputMonths>11){
			toYears++;
			outputMonths-=12;
		}
		
		int outputYears=toYears+fromYears;
		Interval interval = new Interval();
		interval.setSeconds(outputSeconds);
		interval.setMinutes(outputMinutes);
		interval.setHours(outputHours);
		interval.setDays(outputDays);
		interval.setMonths(outputMonths);
		interval.setYears(outputYears);
		return interval;
		
		
	}
	
	
}
