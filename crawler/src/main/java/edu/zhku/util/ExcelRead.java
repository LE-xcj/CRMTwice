/**
 * 
 */
package edu.zhku.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * <p>
 * Title: ExcelRead.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author xcj
 * @date 2018年6月23日
 */
public class ExcelRead {
	
	public static void read() throws IOException {
		File file = new File("excel", "校区：白云校区  楼房：杨钊杨勋楼  教室：(白)杨钊杨勋楼207.xls");
		
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis, "gb2312");
		BufferedReader bfr = new BufferedReader(isr);
		
		String line = null;
		while( (line = bfr.readLine()) != null ) {
			String[] array = line.split("\t");
			
		}
	}
	
	public static List<String> getDate(String fileName){
		File file = new File(fileName);
		List<String> data = new ArrayList<>();
		if (file.exists()) {
			FileInputStream fis;
			try {
				fis = new FileInputStream(file);
				InputStreamReader isr = new InputStreamReader(fis, "gb2312");
				BufferedReader bfr = new BufferedReader(isr);
				
				String line = null;
				while( (line = bfr.readLine()) != null ) {
					data.add(line);
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return data;
		
	}

	
	public static void main(String[] args) throws IOException {
		read();
	}
}
