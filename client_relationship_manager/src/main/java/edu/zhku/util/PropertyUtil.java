/**
 * 
 */
package edu.zhku.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.util.ResourceUtils;

/**
 * <p>
 * Title: PropertyUtil.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author xcj
 * @date 2018年6月17日
 */
public class PropertyUtil {
	private static Properties props = null;
	private static final String PATH = "classpath:fpath.properties";
	
	static {
		readProperties();
	}
	private static void readProperties() {
		try {
			props = new Properties();
			File file = ResourceUtils.getFile(PATH);
			InputStream fis = new FileInputStream(file);
			props.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取某个属性
	 */
	public static String getProperty(String key) {
		return props.getProperty(key);
	}
	
	public static void main(String[] args) {
		System.out.println(getProperty("vc_message_path"));
	}
}
