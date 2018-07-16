/**
 * 
 */
package edu.zhku.util;

import org.apache.commons.lang.RandomStringUtils;

/**  
* <p>Title: RandomUtil.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年6月15日  
*/
public class RandomUtil {
	public static String generateNumber(int length, boolean letter, boolean number) {
		String random = RandomStringUtils.random(length, letter, number);
		return random;
	}
	
	public static String  generateNumber(int length) {
		String random = RandomStringUtils.random(length, false, true);
		return random;
	}
	
	public static void main(String[] args) {
		System.out.println(generateNumber(5));
	}
}
