/**
 * 
 */
package edu.zhku.util;

/**  
* <p>Title: DayUtil.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年7月8日  
*/
public class DayUtil {
	private static final String MON = "一";
	private static final String TUE = "二";
	private static final String WEB = "三";
	private static final String THU = "四";
	private static final String FRI = "五";
	private static final String SAT = "六";
	private static final String SUN = "日";
	public static Integer getDay(String sDay) {
		Integer day = null;
		switch (sDay) {
			case MON :
				day = 1;
			break;
			case TUE :
				day = 2;
			break;
			case WEB :
				day = 3;
			break;
			case THU :
				day = 4;
			break;
			case FRI :
				day = 5;
			break;
			case SAT :
				day = 6;
			break;
			case SUN :
				day = 7;
			break;
			
		}
		
		return day;
	}
	
	public static int getBeginjie(String day) {
		int index = day.lastIndexOf("节");
		String segment = day.substring(2, index);
		
		String[] temp = segment.split("-");
		
		int begin = Integer.valueOf(temp[0]);
		
		return begin;
	}
	
	public static int getEndJie(String day) {
		
		int index = day.lastIndexOf("节");
		String segment = day.substring(2, index);
		
		String[] temp = segment.split("-");
		
		int end = Integer.valueOf(temp[1]);
		
		return end;
	}
}
