/**
 * 
 */
package edu.zhku.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**  
* <p>Title: IDGenerator.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年6月19日  
*/
public class IDGenerator {
	
	private static SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss"); 
	private static SimpleDateFormat tdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static String generateBID(String sid) {
		String bID = sid + df.format(new Date());
		
		return bID;
	}
	
	public static String getDate() {
		return tdf.format(new Date());
	}
	
	public static Date getDate(String sDate) {
		try {
			return tdf.parse(sDate);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(generateBID("123"));
	}
	
}
