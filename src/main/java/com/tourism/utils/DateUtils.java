package com.tourism.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



/**日期工具类**/
public class DateUtils {
	

	private static SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat format2 = new SimpleDateFormat("HHmmss");
	
	private static SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd");
	
	private static SimpleDateFormat format4 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	private static SimpleDateFormat format5 = new SimpleDateFormat("yyyy/MM/dd");
	
	private static SimpleDateFormat format6 = new SimpleDateFormat("MMdd");
	
	public static void main(String[] args){
//		System.out.println(getReturnYYYYMMDDDate(new Date()).toString());
		
		boolean f = daysBetween("2015-11-29",6);
		System.out.println(f);
	}
	
	/**以yyyyMMdd格式--返回当前日期字符
	 * **/
	public static String yyyyMMdd(){
		return yyyyMMdd(new Date());
	}
	
	/**以MMdd格式--返回指定时间字符
	 * @param date 日期对象
	 * **/
	public static String MMdd(Date date)
	{
		return format6.format(date);
	}
	

	/**以HHmmss格式--返回指定时间字符
	 * @param date 日期对象
	 * **/
	public static String yyyyMMdd(Date date)
	{
		return format1.format(date);
	}
	
	
	/**以HHmmss格式--返回当前时间字符
	 * @param date
	 * **/
	public static String HHmmss()
	{
		return HHmmss(new Date());
	}
	
	/**以HHmmss格式--返回指定日期字符
	 * @param date 时间对象
	 * **/
	public static String HHmmss(Date date)
	{
		return format2.format(date);
	}
	
	
	public static String yyyyMMddHHmmss(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	

	public static String yyyyMMddHHmmssExtend(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date);
	}
	
	public static String getyyyyMMdd(int addYear){
		Calendar curr = Calendar.getInstance();
		curr.set(Calendar.YEAR,curr.get(Calendar.YEAR)+addYear);
		Date date=curr.getTime();
		return format1.format(date);
	}
	
	/**以HHmmss格式--返回指定时间字符
	 * @param date 日期对象
	 * **/
	public static String yyyyMMddStr(Date date)
	{
		if(date==null){
			return "null";
		}else{
			return format3.format(date);
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public static String getDay(int addDay){
		Date date= new Date();
		date.setDate(date.getDate()+addDay);
		return format3.format(date);
	}
	
	/**以HHmmss格式--返回指定时间字符
	 * @param date 日期对象
	 * **/
	public static Date getTime(String date)
	{
		SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date returnDate =null;
		if(date==null || "".equals(date)){
			return returnDate;
		}
		try {
			returnDate = formatTime.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return returnDate;
		
	}
	
	/**
	 * 判断有效期是否在符合办证条件内
	 * @param yxqz
	 * @return
	 */
	public static boolean daysBetween(String yxqz,int m)  
    {  
		
		boolean flag = true;
		String twoStr ="";//有效期后两位
		if(yxqz==null || "".equals(yxqz)){
			return flag = false;
		}else{
			/**判断是否为数字 不是为长期**/
			boolean isNum = yxqz.matches("[0-9]+");
			if(!isNum){
				return flag = false;
			}
			/**获取有效期后两位数字**/
			if(yxqz.length()>2){
				twoStr = yxqz.substring(yxqz.length()-2, yxqz.length());
			}
			
		}
		SimpleDateFormat matter = new SimpleDateFormat("yyyyMM");
		Calendar calendar = Calendar.getInstance();
		//将calendar装换为Date类型
		Date date = getReturnTime(yxqz);
		//将date类型转换为BigDecimal类型（该类型对应oracle中的number类型）
		calendar.setTime(date);
		//获取到期时间的前6个月
		calendar.add(Calendar.MONTH, -m);
		Date date02 = calendar.getTime();
		BigDecimal time02 = new BigDecimal(matter.format(date02));
		//获取到期时间的前6个月+有效期后两位
		String dqTime = time02.toString()+twoStr;

		String currentTime = format1.format(new Date());
		Date cTime =null;
		Date yTime =null;
		try {
			 cTime = format1.parse(currentTime);
			 yTime = format1.parse(dqTime);
			 if(cTime.getTime() < yTime.getTime()){
				 flag= false;
			 }
		} catch (ParseException e) {
			e.printStackTrace();
		}
       return flag;         
    }
	
	
	
	/**
	 * 根据出生日期获取办证的有效期限
	 * @param csrq
	 * @return
	 */
	public static String getSlxxByYxqz(Date csrq){
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		String yxqz ="";
		try {
			if(csrq!=null){
			  String csrqTime = format2.format(csrq);
			  Date date = new Date();
			  String newTime = format2.format(date);
			  long l = format2.parse(newTime).getTime()-format2.parse(csrqTime).getTime();
			  long day=l/(24*60*60*1000);
			  long year = day/365;
			  if(year<16){
				  yxqz = DateUtils.getyyyyMMdd(5);
			  }
			  if(year>=16 && year<26){
				  yxqz = DateUtils.getyyyyMMdd(10);
			  }
			  if(year>=26 && year<46){
				  yxqz = DateUtils.getyyyyMMdd(20);
			  }
			  if(year>=46){
				  yxqz = "长期";
			  }
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return yxqz;
	}
	
	/**
	 * 根据出生日期获取办证的有效期限
	 * @param csrq
	 * @return
	 */
	public static String getYear(String csrq){
		SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd");
		String yxqz ="";
		try {
			if(csrq!=null){
			  Date csrqTime = format2.parse(csrq);
			  Date date = new Date();
			  String newTime = format2.format(date);
			  long l = format2.parse(newTime).getTime()-csrqTime.getTime();
			  long day=l/(24*60*60*1000);
			  long year = day/365;
			  if(year<16){
				  yxqz = "5年";
			  }
			  if(year>=16 && year<26){
				  yxqz = "10年";
			  }
			  if(year>=26 && year<46){
				  yxqz = "20年";
			  }
			  if(year>=46){
				  yxqz = "长期";
			  }
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return yxqz;
	}
	
	/**
	 * 获取截至时间
	 * @param date
	 * @param addDay
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getEndDate(Date date,int addDay){
		date.setDate(date.getDate()+addDay);
		return format3.format(date);
	}
	
	
	
	/**以yyyyMMdd格式--返回指定时间字符
	 * @param date 日期对象
	 * **/
	public static Date getReturnTime(String date)
	{
		Date returnDate =null;
		if(date==null || "".equals(date)){
			return returnDate;
		}
		try {
//			format1 = new SimpleDateFormat("yyyyMMdd");
			returnDate = new SimpleDateFormat("yyyyMMdd").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return returnDate;
	}
	
	/**以HHmmss格式--返回指定时间字符
	 * @param date 日期对象
	 * **/
	public static String yyyyMMddHHmm(Date date)
	{
		if(date==null){
			return "null";
		}else{
			return format4.format(date);
		}
		
	}
	
	
	/**以HHmmss格式--返回指定时间字符
	 * @param date 日期对象
	 * **/
	public static Date getReturnDate(Date date)
	{
		Date returnDate =null;
		if(date==null || "".equals(date)){
			return returnDate;
		}
		try {
			returnDate = format4.parse(format4.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return returnDate;
	}
	
	/**以yyyy/mm/dd格式--返回指定时间字符
	 * @param date 日期对象
	 * **/
	public static Date getReturnYYYYMMDDDate(Date date)
	{
		Date returnDate =null;
		if(date==null || "".equals(date)){
			return returnDate;
		}
		try {
			returnDate = format5.parse(format5.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return returnDate;
	}
	
	
	/**
	 * 获取本月最后一天
	 * @return
	 */
	public static String getMaxMonthDate(){
		Calendar calender =  Calendar.getInstance();
		calender.set(Calendar.DATE, calender.getActualMaximum(Calendar.DATE));
		String  maxMonthStr = format3.format(calender.getTime());
		return maxMonthStr;
	}
	
	/**
	 * 获取本月第一天
	 * @return
	 */
	public static String getMinMonthDate(){
		Calendar calender1 =  Calendar.getInstance();
		calender1.set(Calendar.DATE, 1);
		String  minMonthStr = format3.format(calender1.getTime());
		return minMonthStr;
	}
	
	/**
	 * 获取某月第一天
	 * @return
	 */
	public static String getFirstMonthDate(int month){
		Calendar calender1 =  Calendar.getInstance();
		
		//设置月份
		calender1.set(Calendar.MONTH, month-1);
		
		calender1.set(Calendar.DATE, 1);
		String  minMonthStr = format3.format(calender1.getTime());
		return minMonthStr;
	}
	
	/**
	 * 获取某月的最后一天
	 * @Title:getLastDayOfMonth
	 * @Description:
	 * @param:@param year
	 * @param:@param month
	 * @param:@return
	 * @return:String
	 * @throws
	 */
	public static String getLastDayOfMonth(int year,int month)
	{
		Calendar cal = Calendar.getInstance();
		//设置年份
		cal.set(Calendar.YEAR,year);
		//设置月份
		cal.set(Calendar.MONTH, month-1);
		//获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		//格式化日期
		String lastDayOfMonth = format3.format(cal.getTime());
		
		return lastDayOfMonth;
	}
	
	/**
	 * 判断当前日期是否最后一天
	 * @return
	 */
	public static boolean getIsMaxMonthDate(){
		boolean flag = false;
		String  maxMonthStr = getMaxMonthDate();
		String  currentStr = format3.format(new Date());
		if(maxMonthStr.equals(currentStr))
			flag = true;
		
		return flag;
	}
	
	/**
	 * 判断当前日期是否周六日
	 * @return
	 */
	public static boolean getWeek(Date date){
		boolean flag = false;
		String bDate = format3.format(new Date()); 
		Date bdate=null;
		try {
			bdate = format3.parse(bDate);
			Calendar cal = Calendar.getInstance();
		    cal.setTime(bdate);
		    if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)
		    {
		    	flag = true;
		    }
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return flag;   
	}
	
	/***
	 * 把字符串转成时间格式
	 * @param time
	 * @return
	 */
	public static Date getYyyyMMddHHmmss(String time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	// 获取前后日期 i为正数 向后推迟i天，负数时向前提前i天
		public static String afterNDay(int n) {
			Calendar c = Calendar.getInstance();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			c.setTime(new Date());
			c.add(Calendar.DATE, n);
			Date d2 = c.getTime();
			String s = df.format(d2);
			return s;
		}
	
	/**
	 * @MethodName: string转Date</br>
	 * @Description: 将String类型的日期转换为指定格式的Date类型</br>
	 * @Parameters： @param pattern 转换格式例如 yyyyMMdd</br>
	 * @Parameters： @param date String 类型的日期</br>
	 * @Parameters： @return </br>
	 * @ReturnType: Date </br>
	 * @CreateDate: 2016-6-20 </br>
	 * @Author: WangBin wangb@ezhuoteng.com </br>
	 * @Exception:
	 */
	public static Date stringToDate(String pattern, String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);                
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}   
	}
}
