package com.car.commons.util;

import org.apache.commons.lang.StringUtils;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * <p>Title:ModelArithUtil</p>
 * <p>Description:取模算法，对于数字(包括字符串的数字)直接取模，对于字符串取hash值后取模</p>
 * <p>Company:yuntai</p>
 * @author liyunhua
 * @date 2016-7-16 上午9:19:19
 */
public class ModelArithUtil {
	
	/**
	 * 
	 * @title: delivery 
	 * @description: 根据多个整数值，累加后，取模 
	 * @param len
	 * @param ids
	 * @return int
	 * @author liyunhua
	 */
	public static int delivery(int len,int ... ids){
		/**
		 * 1、遍历参数，累加到结果值
		 */
		int result=0;
		for(int id:ids){
			result+=id;
		}
		
		/**
		 * 2、把结果值取模第一个参数，如果计算值为0，则返回len，否则返回计算值
		 */
		int modeResult=result%len;
		if(modeResult==0)modeResult=len;
		return modeResult;
	}
	
	/**
	 * 
	 * @title: delivery 
	 * @description: 根据多个字符串，hash后，累加后，取模 
	 * @param len
	 * @return int
	 * @author liyunhua
	 */
	public static int delivery(int len,String... str){
		/**
		 * 1、遍历参数，如果是字符串，则判断是否是数字，如果不是数字，转成hash值，然后累加到结果值
		 */
		int result=0;
		for(String s:str){
			int intParam=0;
			if(!isDigit(s)){
				intParam=str.hashCode();
				BigInteger b=new BigInteger(String.valueOf(intParam));
				b=b.abs();
				intParam=b.intValue();
			}else{
				intParam=Integer.parseInt(s);
			}
			result+=intParam;
		}
		
		/**
		 * 2、把结果值取模第一个参数，如果计算值为0，则返回len，否则返回计算值
		 */
		int modeResult=result%len;
		if(modeResult==0)modeResult=len;
		
		return modeResult;
	}
	
	public static int delivery(int len, String str, int id){
		return delivery(len,str,String.valueOf(id));
	}
	public static int delivery256(int id){
		return delivery(256,id);
	}
	
	public static int delivery256(String str){
		return delivery(256,str);
	}
	
	public static int delivery256(int ... ids){
		return delivery(256,ids);
	}
	
	public static int delivery256(String... strs){
		return delivery(256,strs);
	}
	
	public static int delivery256(String str, int id){
		return delivery(256,id);
	}
	
	public static int delivery128(int id){
		return delivery(128,id);
	}
	
	public static int delivery128(String str){
		return delivery(128,str);
	}
	
	public static int delivery128(int ... ids){
		return delivery(128,ids);
	}
	
	public static int delivery128(String... strs){
		return delivery(128,strs);
	}
	
	public static int delivery128(String str, int id){
		return delivery(128,id);
	}
	
	public static int delivery16(int id){
		return delivery(16,id);
	}
	
	public static int delivery16(String str){
		return delivery(16,str);
	}
	
	public static int delivery16(int ... id){
		return delivery(16,id);
	}
	
	public static int delivery16(String... strs){
		return delivery(16,strs);
	}
	
	public static int delivery16(String str, int id){
		return delivery(16,id);
	}
	
	public static int delivery32(int id){
		return delivery(32,id);
	}
	
	public static int delivery32(String str){
		return delivery(32,str);
	}
	
	public static int delivery32(int ... ids){
		return delivery(32,ids);
	}
	
	public static int delivery32(String... strs){
		return delivery(32,strs);
	}
	
	public static int delivery32(String str, int id){
		return delivery(32,id);
	}
	
	public static int delivery64(int id){
		return delivery(64,id);
	}
	
	public static int delivery64(String str){
		return delivery(64,str);
	}
	
	public static int delivery64(int ... ids){
		return delivery(64,ids);
	}
	
	public static int delivery64(String... strs){
		return delivery(64,strs);
	}
	
	public static int delivery64(String str, int id){
		return delivery(64,id);
	}
	
	public static int delivery8(int id){
		return delivery(8,id);
	}
	
	public static int delivery8(String str){
		return delivery(8,str);
	}
	
	public static int delivery8(int ... ids){
		return delivery(8,ids);
	}
	
	public static int delivery8(String... strs){
		return delivery(8,strs);
	}
	
	public static int delivery8(String str, int id){
		return delivery(8,id);
	}
	
	public static int delivery4(int id){
		return delivery(4,id);
	}
	
	public static int delivery4(String str){
		return delivery(4,str);
	}
	
	public static int delivery4(int ... ids){
		return delivery(4,ids);
	}
	
	public static int delivery4(String... strs){
		return delivery(4,strs);
	}
	
	public static int delivery4(String str, int id){
		return delivery(4,id);
	}
	
	/**
	 * 
	 * @param len 分模的数量
	 * @param str 日期，格式如"2016-11-20"
	 * @return
	 */
	public static int deliveryYearMonth(int len,String str){
		if(StringUtils.isNotEmpty(str)){
			Date date=DateUtil.fomatDate(str);
			return deliveryYearMonth(len,date);
		}
		return 0;
	}
	
	/**
	 * 
	 * @param len 分模的数量
	 * @param date 日期
	 * @return
	 */
	public static int deliveryYearMonth(int len,Date date){
		Calendar cld = Calendar.getInstance();
		int year=0;
		int month=0;
		if(null!=date){
			cld.setTime(date);
			year=cld.get(Calendar.YEAR);
			month=cld.get(Calendar.MONTH)+1;
		}
		return deliveryYearMonth(len,year,month);
	}
	
	
	
	/**
	 * 
	 * @Title:
	 * @Description: 取一段时间内模
	 * @param len
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ParseException
	 * @return Object
	 * @author Yuyanxia
	 * @date 2017-3-28
	 */
	public static List<Integer> deliveryTeayMonthList(int len, String startDate, String endDate) throws ParseException {
		Set<Integer> result = new HashSet<Integer>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式化为年月
		
		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();
		if(null == startDate || ("").equals(startDate)){
			min.setTime(new Date());
		}else{
			min.setTime(sdf.parse(startDate));
		}
		if(null == endDate || ("").equals(endDate)){
			max.setTime(new Date());
		}else{
			max.setTime(sdf.parse(endDate));
		}
		
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
		max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

		Calendar curr = min;
		while (curr.before(max)) {
			 int num = deliveryYearMonth(len,sdf.format(curr.getTime()));
			 result.add(num);
			 curr.add(Calendar.MONTH, 1);
		}
		List<Integer> list2 = new ArrayList<Integer>();
		list2.addAll(result); 
		return list2;
	}
	
	
	
	/**
	 * 
	 * @param len	分模的数量
	 * @param year	年 2016 
	 * @param month 月 9
	 * @return
	 */
	public static int deliveryYearMonth(int len,int year,int month){
		int temp=year*12+month;
		return delivery(len,temp);
	}
	
	
	private static boolean isDigit(String str){
		String reg="[0-9]+";
		return str.matches(reg);
	}

	/**
	 * 生成指定长度的集合
	 * @param length
	 * @return
	 */
	public static List<Integer> generateList(int length) {
		List<Integer> list = new ArrayList<>();
		for(int i = 1; i <= length; i++) {
			list.add(i);
		}
		return list;
	}
	
	public static void main(String[] args) {
		//System.out.println(ModelArithUtil.delivery8("5"));
		//System.out.println(ModelArithUtil.delivery(32, MD5.md52("5")));
		System.out.println(ModelArithUtil.deliveryYearMonth(8,"2018-10-24"));
		//System.out.println(ModelArithUtil.delivery(32,31));
	}
}
