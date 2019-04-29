package com.car.commons.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	/**
	 * 日
	 */
	public final static int INTERVAL_DAY = 1;
	/**
	 * 周
	 */
	public final static int INTERVAL_WEEK = 2;
	/**
	 * 月
	 */
	public final static int INTERVAL_MONTH = 3;
	
	/**
	 * 季度
	 */
	public final static int INTERVAL_SEASON=8;
	
	/**
	 * 半年
	 */
	public final static int INTERVAL_HALFYEAR=9;
	
	/**
	 * 年
	 */
	public final static int INTERVAL_YEAR = 4;
	/**
	 * 小时
	 */
	public final static int INTERVAL_HOUR = 5;
	/**
	 * 分钟
	 */
	public final static int INTERVAL_MINUTE = 6;
	/**
	 * 秒
	 */
	public final static int INTERVAL_SECOND = 7;
	
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

	private final static SimpleDateFormat sdfDay = new SimpleDateFormat(
			"yyyy-MM-dd");

	private final static SimpleDateFormat sdfDays = new SimpleDateFormat(
			"yyyyMMdd");

	private final static SimpleDateFormat sdfTime = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private final static SimpleDateFormat stringTime = new SimpleDateFormat(
			"yyyyMMddHHmmss");
	private final static SimpleDateFormat sdfMonth = new SimpleDateFormat(
			"yyyyMM");
	private final static SimpleDateFormat sdfMonths = new SimpleDateFormat(
			"yyyy-MM");
	private final static SimpleDateFormat hourMinute=new SimpleDateFormat(
			"HH:mm");
	private final static SimpleDateFormat hourMinuteSecond=new SimpleDateFormat(
			"HH:mm:ss");
	
	private final static SimpleDateFormat Month=new SimpleDateFormat(
			"MM");
	
	private final static SimpleDateFormat Day=new SimpleDateFormat(
			"dd"); 

	/**
	 * 获取YYYY格式
	 * 
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}
	/**
	 * 获取YYYY格式
	 * 
	 * @return
	 */
	public static String getYear(Date date) {
		return sdfYear.format(date);
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * 
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}
	
	public static String getDay(Date date){
		return sdfDay.format(date);
	}
	
	public static Date getToday(){
		return DateUtil.fomatDate(getDay(), sdfDay);
	}

	
	
	public static String getoneDay(Date date){
		return Day.format(date);
	}
	
	
	/**
	 * 获取YYYYMMDD格式
	 * 
	 * @return
	 */
	public static String getDays() {
		return sdfDays.format(new Date());
	}
	
	public static String getDays(Date date){
		return sdfDays.format(date);
	}
	
	public static String getDays(Date date, DateFormat fmt){
		return fmt.format(date);
	}
	/**
	 * 获取HH:mm格式
	 * @author YanB
	 *2016-6-29
	 *@param date
	 *@return
	 *TODO
	 */
	public static String getHourAndMinute(Date date){
		return hourMinute.format(date);
	}
	/**
	 * 获取HH:mm:ss格式
	 * @author YanB
	 *2017-3-15
	 *@param date
	 *@return
	 *TODO
	 */
	public static String getHourAndMinuteAndSecond(Date date){
		return hourMinuteSecond.format(date);
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * 
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}
	
	public static String getTime(Date date){
		return sdfTime.format(date);
	}

	/**
	 * 获取YYYYMMDD格式
	 * 
	 * @return
	 */
	public static String getStringtime() {
		return stringTime.format(new Date());
	}
	public static String getStringtime(Date date) {
		return sdfTime.format(date);
	}
	
	public static String getYearMonthTime(){
		return sdfMonth.format(new Date());
	}
	/**
	 * 获取格式为yyyy-MM的是年月
	 * @author YanB
	 *2016-11-21
	 *@return
	 *TODO
	 */
	public static String getYearMonthTimes(Date date){
		return sdfMonths.format(date);
	}
	
	public static String getYearMonthTime(Date date){
		return sdfMonth.format(date);
	}
	
	/**
	 * 获取YYYYMM格式
	 * @return
	 */
	public static String getYearMonth(Date date){
		return sdfMonth.format(null!=date?date:new Date());
	}
	
	public static String getYearMonth(){
		return getYearMonth(null);
	}

	/**
	* @Description:(日期比较，如果s<e 返回true 否则返回false)
	* @Parameters:
	* @Return:
	* @Author:LiuY
	* @Create:2016-4-28 下午7:38:25
	* @Version:V1.00
	 */
	public static boolean compareDate(Date s, Date e) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date dateTime1 = dateFormat
				.parse(dateFormat.format(s));
		Date dateTime2 = dateFormat.parse(dateFormat.format(e));
		int i = dateTime1.compareTo(dateTime2);
		if(i<0)
		{
			return true;
		}
		return false;
	}
	
	
	
	/**
	 * @Title: compareDate
	 * @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	 * @param s
	 * @param e
	 * @return boolean
	 * @throws
	 * @author luguosui
	 */
	public static boolean compareDate(String s, String e) {
		if (fomatDate(s) == null || fomatDate(e) == null) {
			return false;
		}
		return fomatDate(s).getTime() >= fomatDate(e).getTime();
	}

	/**
	 * 格式化日期
	 * 
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date fomatDate(String date, DateFormat fmt){
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date fomatYearAndMonth(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Date fomatYearAndMonthAndDayAndHour(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 校验日期是否合法
	 * 
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}

	public static int getDiffYear(String startTime, String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			long aa = 0;
			int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(
					startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}

	/**
	 * <li>功能描述：时间相减得到天数
	 * 
	 * @param beginDateStr
	 * @param endDateStr
	 * @return long
	 * @author Administrator
	 */
	public static long getDaySub(String beginDateStr, String endDateStr) {
		//long day = 0;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		java.util.Date beginDate = null;
		java.util.Date endDate = null;

		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
		// System.out.println("相隔的天数="+day);

		return getDaySub(beginDate,endDate);
	}

	public static long getDaySub(Date beginDate, Date endDate){
		long day = 0;
		day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}

	 /**
     * <li>功能描述：时间相减得到分钟
     * @param beginDateStr
     * @param endDateStr
     * @return
     * long
     * @author Administrator
     */
    public static long getMinuteSub(String beginDateStr, String endDateStr){
    	long minute=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date beginDate = null;
        java.util.Date endDate = null;
        
            try {
				beginDate = format.parse(beginDateStr);
				endDate= format.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            minute=(endDate.getTime()-beginDate.getTime())/(1000*60);
      
        return minute;
    }
    
    public static long getMinuteSub(Date beginDate, Date endDate){
    	long minute=0;
        minute=(endDate.getTime()-beginDate.getTime())/(1000*60);
        return minute;
    }

	/**
	 * 得到n天之后的日期
	 * 
	 * @param days
	 * @return
	 */
	public static String getAfterDayDate(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdfd.format(date);

		return dateStr;
	}
	
	
	
	/**
	 * 
	* @Title: 
	* @Description: 得到n天之前的日期
	* @return     
	* @throws Exception
	* @author Yuyanxia
	* @date 2018-4-17 下午4:41:05
	 */
	public static String getBeforDayDate(String days){
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, -daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdfd.format(date);

		return dateStr;
	}
	

	/**
	 * 得到n天之后是周几
	 * 
	 * @param days
	 * @return
	 */
	public static String getAfterDayWeek(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("E");
		String dateStr = sdf.format(date);

		return dateStr;
	}
	
	/**
	 * 
	 * @title: parseDate 
	 * @description: 把日期时间转换成格式为'yyyy-MM-dd'的字符串 
	 * @param date
	 * @return
	 * @return String
	 * @author liyunhua
	 */
	public static String parseDate(Date date){
		String datestr=sdfDay.format(date);
		return datestr;
	}
	
	/**
	 * 
	 * @title: parseDateTime 
	 * @description: 把日期时间转换成格式为'yyyy-MM-dd HH:mm:ss'的字符串  
	 * @param date
	 * @return
	 * @return String
	 * @author liyunhua
	 */
	public static String parseDateTime(Date date){
		String datetimestr=sdfTime.format(date);
		return datetimestr;
	}

	
	/**
	 * formatMonth格式化月份
	 * @param args
	 */
	public static String parseMonth(String month){
		//判断参数为null或空字符串
		if(month==null || month == "")
		{
					return "01";
		}
		int mon = Integer.parseInt(month);
		String afterMon = (mon<10?"0"+mon:String.valueOf(mon));
		return afterMon;

	}
	
	/**
	 * 将“yyyy-MM-dd HH:mm:ss”格式转化yyyyMM
	 * @param time
	 * @return 
	 */
	public static String parseYearmonth(String time){
		String substr = time.substring(0, time.indexOf(" "));
		String[] strdate = substr.split("-");
		return strdate[0]+strdate[1];
	}
	public static String getMonth(Date date){
		String month=Month.format(date);
		return month;
	}
	
	/**
	 * 获取当前日期的上个月1号的日期
	 * @param args
	 */
	public static String beforMonth(){
		 //获得当月时间上月1号

	       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(new Date());
	        cal.add(Calendar.MONTH, -1);
	        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return sdf.format(cal.getTime());
	}
	
	/**
	 * 获取当前日期的1号的日期
	 * @param args
	 */
	public static String nowMonth(){
		 //获得当月时间上月1号

	       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(new Date());
	        cal.add(Calendar.MONTH,0);
	        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return sdf.format(cal.getTime());
	}
	
	
	/**
	 * 
	* @Title: 
	* @Description:取下个月一号的日期
	* @return     
	* @throws Exception
	* @author Yuyanxia
	* @date 2018-7-19 上午9:55:59
	 */
	public static String nextMonth(Date date){
		 //获得当月时间上月1号

	       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.MONTH,1);
	        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));
       return sdf.format(cal.getTime());
	}
	
	
	//将时间字符串转为long型
	public static long fromDateStringToLong(String inVal) { //此方法计算时间毫秒
		  Date date = null;   //定义时间类型
		  SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-mm-dd hh:ss");
		  try {
		  date = inputFormat.parse(inVal); //将字符型转换成日期型
		  } catch (Exception e) {
		  e.printStackTrace();
		  }
		  return date.getTime();   //返回毫秒数

    }
	
	/**
	 * 
	 * @Title:
	 * @Description: 获取几天几小时几分钟
	 * @param start
	 * @param end
	 * @return
	 * @return Object
	 * @author Yuyanxia
	 * @date 2017-3-20
	 */
	public static String getTimeDifference(long start, long end){
		  long diff=(start-end); //共计秒数
		  long days = diff / (1000 * 60 * 60 * 24);
		  long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
		  long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60); 
		  return (days+"天"+hours+"小时 "+minutes+"分钟");	  
	}
	
	/**
	 * 
	 * @title: getLastDay 
	 * @description: 获取某月最大天数 
	 * @param year
	 * @param month
	 * @return int
	 * @author liyunhua
	 */
	public static int getLastDay(int year, int month) {
		 int day = 1;
		 Calendar cal = Calendar.getInstance();
		 cal.set(year,month - 1,day);
		 int last = cal.getActualMaximum(Calendar.DATE);
		 return last;
	}
	/**
	 * 得到某日期的前一天日期的字符串
	 * @author YanB
	 *2016-11-21
	 *@param date
	 *@return
	 *TODO
	 */
	public static String getYesterDay(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE)-1);
		String newDate=sdfDay.format(cal.getTime());
		return newDate;
	}
	
	/**
	 * 得到指定月的天数
	 * */
	public static int getMonthDays(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}
	
	/**
	 * 取得当月天数
	 * */
	public static int getMonthDays() {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}
	
	/**
	 * 增加时间
	 * 
	 * @param interval
	 *            [INTERVAL_DAY,INTERVAL_WEEK,INTERVAL_MONTH,INTERVAL_YEAR,
	 *            INTERVAL_HOUR,INTERVAL_MINUTE]
	 * @param date
	 * @param n
	 *            可以为负数
	 * @return
	 */
	public static Date dateAdd(int interval, Date date, int n) {
		long time = (date.getTime() / 1000); // 单位秒
		switch (interval) {
		case INTERVAL_DAY:
			time = time + n * 86400;// 60 * 60 * 24;
			break;
		case INTERVAL_WEEK:
			time = time + n * 604800;// 60 * 60 * 24 * 7;
			break;
		case INTERVAL_MONTH:
			if(n<1)return date;
			for(int i=0;i<n;i++){
				int maxDays=DateUtil.getMonthDays(date.getYear(), date.getMonth()+(i+1));
				time = time + 60*60*24*maxDays;// 60 * 60 * 24 * 31;
			}
			break;
		case INTERVAL_SEASON:
			int todayDays=DateUtil.getMonthDays(date.getYear(), date.getMonth());
			int tomorrowDays=DateUtil.getMonthDays(date.getYear(), date.getMonth()+1);
			int afterDays=DateUtil.getMonthDays(date.getYear(), date.getMonth()+2);
			time = time + n * 60*60*24*(todayDays+tomorrowDays+afterDays);
			break;
		case INTERVAL_YEAR:
			time = time + n * 31536000;// 60 * 60 * 24 * 365;
			break;
		case INTERVAL_HOUR:
			time = time + n * 3600;// 60 * 60 ;
			break;
		case INTERVAL_MINUTE:
			time = time + n * 60;
			break;
		case INTERVAL_SECOND:
			time = time + n;
			break;
		default:
		}

		Date result = new Date();
		result.setTime(time * 1000);
		return result;
	}
	
	/**
	 * 日期合法性验证 格式为：YYYY-MM-DD HH:MM:SS
	 * ^\\d{4}(\\-|\\/|\\.)\\d{1,2}\\1\\d{1,2}$正则如2014
	 * /09/01或2014-09-01或2014.09.01
	 */
	public static boolean isValidDateTime(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}
	
	public static boolean isValidDate(String s, DateFormat fmt){
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}
	
	
	

	/**
	 * 
	 * @Title:
	 * @Description:时间加1秒
	 * @param crtTime
	 * @return
	 * @return Object
	 * @author Yuyanxia
	 * @date 2017-3-31
	 */
	public static Date addOneSecond(Date crtTime) {
		  Calendar calendar = Calendar.getInstance();
		  calendar.setTime(crtTime);  
		  calendar.add(Calendar.SECOND, 1);
		  return calendar.getTime();  
	}
	
	
	
	
	/**
	 * 
	 * @Title:
	 * @Description:时间加1天
	 * @param crtTime
	 * @return
	 * @return Object
	 * @author Yuyanxia
	 * @date 2017-3-31
	 */
	public static Date addOneDay(Date crtTime) {
		  Calendar calendar = Calendar.getInstance();
		  calendar.setTime(crtTime);  
		  calendar.add(Calendar.DATE, 1);
		  return calendar.getTime();  
	}
	
	
	
	public static Date addOneMonth(Date crtTime) {
		  Calendar calendar = Calendar.getInstance();
		  calendar.setTime(crtTime);  
		  calendar.add(Calendar.MONTH, 1);
		  return calendar.getTime();  
	}
	
	
	/**
	 *获取本周的星期一日期
	 *@author YanB
	 *2017-2-27
	 *@return
	 *TODO
	 */
	public static String getMondayOfThisWeek(){
		  Calendar c = Calendar.getInstance();
		  int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		  if (day_of_week == 0)
		  day_of_week = 7;
		  c.add(Calendar.DATE, -day_of_week + 1);
		  return sdfDay.format(c.getTime());
	}
	
	public static void main(String[] args) {
		Date time=DateUtil.dateAdd(DateUtil.INTERVAL_YEAR,new Date(),1);
		System.out.println(DateUtil.getTime(time));
	}
	
	/**
	 * 
	 * @Title:
	 * @Description:获取一段时间后的时间
	 * @param time
	 * @param produceTimes
	 * @return : Date
	 * @author YuYX
	 * @date 2017-10-26
	 */
	public static Date getAfterDayDates(String time, Integer produceTimes) {
		  Calendar nowTime = Calendar.getInstance();
		  nowTime.setTime(DateUtil.fomatDate(time));
		  nowTime.add(INTERVAL_MINUTE, produceTimes);
		  return nowTime.getTime();
	}
	/**
	 * 
	 * @Title:
	 * @Description:获取几分钟后的时间
	 * @param time
	 * @param produceTimes
	 * @return : Date
	 * @author LiuY
	 * @date 2017-11-3
	 */
	public static Date getAfterMinuteDates(String time, Integer produceTimes) {
		  Calendar nowTime = Calendar.getInstance();
		  nowTime.setTime(DateUtil.fomatYearAndMonthAndDayAndHour(time));
		  nowTime.add(Calendar.MINUTE, produceTimes);
		  return nowTime.getTime();
	}
	
	/**
	 * 
	* @Title: 
	* @Description: 获取本月的上个月1日
	* @return     
	* @throws Exception
	* @author Yuyanxia
	* @date 2018-8-14 下午3:10:30
	 */
	public static String getBeforMonthDayDate(Date date) {
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		System.out.println("上个月第一天："+format.format(calendar.getTime()));
		return format.format(calendar.getTime());
	}
	
	
	public static int compareDates(Date dt1, Date dt2) {
		  if (dt1.getTime() > dt2.getTime()) {
              System.out.println("dt1 在dt2前");
              return 1;
          } else if (dt1.getTime() < dt2.getTime()) {
              System.out.println("dt1在dt2后");
              return -1;
          } else {//相等
              return 0;
          }
	}
	
	
	public static String stampToDateStr(long timeStamp, String pattern) {
		DateFormat sdf = new SimpleDateFormat(pattern);
		Date date = new Date(timeStamp);
		return sdf.format(date);
	}

	public static String dateToStr(Date date) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = sdf.format(date);
		return format;
	}


	public static Date strToDate(String date) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date parse = null;
		try {
			parse = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parse;
	}

	/**
	 * 时间相减
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date dateSub(Date date,Integer day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH,day);
		return calendar.getTime();
	}

	public static Date dateMonthOperator(Date date,Integer month) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, month);
		return calendar.getTime();
	}
}
