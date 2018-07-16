/**
 * 
 */
package edu.zhku.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**  
* <p>Title: DateUtil.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年6月22日  
*/
public class DateUtil {
    private static Pattern p=Pattern.compile("(\\d{4})-(\\d{1,2})-(\\d{1,2})");  
    private static SimpleDateFormat tdf = new SimpleDateFormat("yyyy/MM/dd");
    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    public static Date getDate(String date) {
    	try {
    		Date d = tdf.parse(date);
			return d;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
    public static int getNowYear() {
    	
    	String now = df.format(new Date());
    	
    	
    	
    	int year = getYear(now);
    	
    	return year;
    	
    }
    
    public static String getMonth(String date) {
    	Matcher m=p.matcher(date);
    	String month = "";
        if(m.find()){  
        	month = m.group(2);
//            System.out.println("日期:"+m.group());  
//            System.out.println("年:"+m.group(1));  
//            System.out.println("月:"+m.group(2));  
//            System.out.println("日:"+m.group(3));  
        }  
        return month;
    }
    
    public static int getYear(String year) {
    	Matcher m=p.matcher(year);
    	int y = 0;
    	if(m.find()) {
    		String temp = m.group(1);
    		y = Integer.valueOf(temp);
    	}
    	return y;
    }
    
    public static int getAge(int now, String birthday) {
    	
    	if(birthday == null) {
    		return -1;
    	}
    
    	
    	int age = now - getYear(birthday);
    	
    	return age;
    	
    }
    
    public static void main(String[] args) {
		//System.out.println(getMonth("2018-01-15"));
    	
    	int now = getNowYear();
    	System.out.println(now);
    	System.out.println(getAge(now, "1997-01-15"));
    	
    	System.out.println(getDate("1997/01/15"));
	}

}
