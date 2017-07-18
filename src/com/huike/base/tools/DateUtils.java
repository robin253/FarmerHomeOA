package com.huike.base.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.smartframework.common.utils.ExDateUtils;

public class DateUtils {
	public static String season_1_begin = "01-01";
	public static String season_1_end = "03-31";
	public static String season_2_begin = "04-01";
	public static String season_2_end = "06-30";
	public static String season_3_begin = "07-01";
	public static String season_3_end = "09-30";
	public static String season_4_begin = "10-01";
	public static String season_4_end = "12-31";
	
	public static Map<String, String> getYearSeasonRange(String year, String season){
		Map<String, String> result = new HashMap<String, String>();
		switch (season){
		case "1" : 
			result.put("start", year +"-"+season_1_begin);
			result.put("end", year +"-"+season_1_end);
		break;
		case "2" : 
			result.put("start", year +"-"+season_2_begin);
			result.put("end", year +"-"+season_2_end);
		break;
		case "3" : 
			result.put("start", year +"-"+season_3_begin);
			result.put("end", year +"-"+season_3_end);
		break;
		case "4" : 
			result.put("start", year +"-"+season_4_begin);
			result.put("end", year +"-"+season_4_end);
		break;
		default:
			result.put("start", year +"-"+season_1_begin);
			result.put("end", year +"-"+season_4_end);
		}
		return result;
	}
	 /** 
     * 获取date的月份的时间范围 
     * @param date 
     * @return 
     */  
    public static DateRange getMonthRange(Date date) {  
        Calendar startCalendar = Calendar.getInstance();  
        startCalendar.setTime(date);  
        startCalendar.set(Calendar.DAY_OF_MONTH, 1);  
        setMaxTime(startCalendar);  
          
        Calendar endCalendar = Calendar.getInstance();  
        endCalendar.setTime(date);  
        endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
        setMaxTime(endCalendar);  
          
        return new DateRange(startCalendar.getTime(), endCalendar.getTime());  
    }  
    /** 
     * 获取当前季度的时间范围 
     * @return current quarter 
     */  
    public static DateRange getThisQuarter() {  
        Calendar startCalendar = Calendar.getInstance();  
        startCalendar.set(Calendar.MONTH, ((int) startCalendar.get(Calendar.MONTH) / 3) * 3);  
        startCalendar.set(Calendar.DAY_OF_MONTH, 1);  
        setMinTime(startCalendar);  
          
        Calendar endCalendar = Calendar.getInstance();  
        endCalendar.set(Calendar.MONTH, ((int) startCalendar.get(Calendar.MONTH) / 3) * 3 + 2);  
        endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
        setMaxTime(endCalendar);  
          
        return new DateRange(startCalendar.getTime(), endCalendar.getTime());  
    }  
      
    /** 
     * 获取昨天的时间范围 
     * @return 
     */  
    public static DateRange getYesterdayRange() {  
         Calendar startCalendar = Calendar.getInstance();  
         startCalendar.add(Calendar.DAY_OF_MONTH, -1);  
         setMinTime(startCalendar);  
           
         Calendar endCalendar = Calendar.getInstance();  
         endCalendar.add(Calendar.DAY_OF_MONTH, -1);  
         setMaxTime(endCalendar);  
           
         return new DateRange(startCalendar.getTime(), endCalendar.getTime());  
    }  
      
    /** 
     * 获取当前月份的时间范围 
     * @return 
     */  
    public static DateRange getThisMonth(){  
        Calendar startCalendar = Calendar.getInstance();  
        startCalendar.set(Calendar.DAY_OF_MONTH, 1);  
        setMinTime(startCalendar);  
          
        Calendar endCalendar = Calendar.getInstance();  
        endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
        setMaxTime(endCalendar);  
          
        return new DateRange(startCalendar.getTime(), endCalendar.getTime());  
    }  
      
    /** 
     * 获取上个月的时间范围 
     * @return 
     */  
    public static DateRange getLastMonth(){  
        Calendar startCalendar = Calendar.getInstance();  
        startCalendar.add(Calendar.MONTH, -1);  
        startCalendar.set(Calendar.DAY_OF_MONTH, 1);  
        setMinTime(startCalendar);  
          
        Calendar endCalendar = Calendar.getInstance();  
        endCalendar.add(Calendar.MONTH, -1);  
        endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
        setMaxTime(endCalendar);  
          
        return new DateRange(startCalendar.getTime(), endCalendar.getTime());  
    }  
      
    /** 
     * 获取上个季度的时间范围 
     * @return 
     */  
    public static DateRange getLastQuarter() {  
        Calendar startCalendar = Calendar.getInstance();  
        startCalendar.set(Calendar.MONTH, ((int) startCalendar.get(Calendar.MONTH) / 3 - 1) * 3);  
        startCalendar.set(Calendar.DAY_OF_MONTH, 1);  
        setMinTime(startCalendar);  
          
        Calendar endCalendar = Calendar.getInstance();  
        endCalendar.set(Calendar.MONTH, ((int) endCalendar.get(Calendar.MONTH) / 3 - 1) * 3 + 2);  
        endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
        setMaxTime(endCalendar);  
          
        return new DateRange(startCalendar.getTime(), endCalendar.getTime());  
    }  
      
    private static void setMinTime(Calendar calendar){  
        calendar.set(Calendar.HOUR_OF_DAY, 0);  
        calendar.set(Calendar.MINUTE, 0);  
        calendar.set(Calendar.SECOND, 0);  
        calendar.set(Calendar.MILLISECOND, 0);  
    }  
      
    private static void setMaxTime(Calendar calendar){  
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));  
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));  
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));  
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));  
    }  
      
      
    public final static String DEFAULT_PATTERN = "MM/dd/yyyy HH:mm:ss";  
    public static String format(Date date){  
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN);  
        return sdf.format(date);  
    }  
      
    
    public static String getWorkDay(Date startDate, int workDay) {
    	Calendar c1 = Calendar.getInstance();
    	c1.setTime(startDate);
    	for (int i = 0; i < workDay; i++) {
    	c1.set(Calendar.DATE, c1.get(Calendar.DATE) -1);
    	if (Calendar.SATURDAY == c1.get(Calendar.SATURDAY) || Calendar.SUNDAY == c1.get(Calendar.SUNDAY)) {
    	workDay = workDay + 1;
    	c1.set(Calendar.DATE, c1.get(Calendar.DATE) - 1);
    	continue;
    	}
    	}
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	c1.set(Calendar.DATE, c1.get(Calendar.DATE) +1);
//    		System.out.println(df.format(c1.getTime()) + " " + getWeekOfDate(c1.getTime()));
    	return df.format(c1.getTime());
    	}
    
    public static void main(String[] args) {  
        
    	System.out.println(getWorkDay(ExDateUtils.getTodayDate(), 7));
    	
        DateRange currentQuarter = getThisQuarter();  
        System.out.println("当前季度的时间范围： "+ExDateUtils.formatDate(currentQuarter.getStart())+" - "+ExDateUtils.formatDate(currentQuarter.getEnd()));  
          
        DateRange yesterdayRange = getYesterdayRange();  
        System.out.println("昨天的时间范围: "+ExDateUtils.formatDate(yesterdayRange.getStart())+" - "+ExDateUtils.formatDate(yesterdayRange.getEnd()));  
          
        DateRange thisMonth = getThisMonth();  
        System.out.println("当前月份的时间范围: "+ExDateUtils.formatDate(thisMonth.getStart())+" - "+ExDateUtils.formatDate(thisMonth.getEnd()));  
          
        DateRange lastMonth = getLastMonth();  
        System.out.println("上个月的时间范围: "+ExDateUtils.formatDate(lastMonth.getStart())+" - "+ExDateUtils.formatDate(lastMonth.getEnd()));  
          
        DateRange lastQuarter = getLastQuarter();  
        System.out.println("上个季度的时间范围: "+ExDateUtils.formatDate(lastQuarter.getStart())+" - "+ExDateUtils.formatDate(lastQuarter.getEnd()));  
          
    }  
}
