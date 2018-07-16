/**
 * 
 */
package edu.zhku.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.zhku.pojo.Course;

/**
 * <p>
 * Title: HtmlUtil.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author xcj
 * @date 2018年7月6日
 */
public class HtmlUtil {
	
	
	public static void parser() throws IOException {
		
		
		File file = new File("file/1.html");
		Document doc = Jsoup.parse(file, "gb2312");

		Elements es = doc.select("table[name='theExportData']");

		//获取第一栏table
		Element address = es.get(0);
		
		String fileName = address.selectFirst("tr").selectFirst("td").text();
		
		//table
		Element table = es.get(1);
		
		//标题
		Element title = table.selectFirst("tr");
		
		//所有tr，包括标题
		Elements trs = table.select("tr");
		
		List<Course> courses = new ArrayList<>();
		
		//遍历所有的tr
		for(Element tr : trs) {
			if(tr == title) {
				continue;
			}
			
			//每一行的td
			Elements tds = tr.select("td");
			
			addCourse(tds, courses);
			
		}
		
		exportExcel(courses, fileName);
	}
	


	/**
	 * @param courses
	 * @throws IOException 
	 */
	private static void exportExcel(List<Course> courses, String fileName) throws IOException {
		
		File file=new File("excel", fileName + ".xls");
		
		FileOutputStream fos = new FileOutputStream(file);
		OutputStreamWriter osw = new OutputStreamWriter(fos , "gb2312");
		
		BufferedWriter bw=new BufferedWriter(osw);
		
		int row= courses.size();
		
		if(0 == row){
			bw.close();		//关闭写入文件操作
			return;
		}

		int index = fileName.lastIndexOf("楼");
		String room = fileName.substring(index+1);
		for(int i=0; i<row; ++i){
			
			Course c = courses.get(i);
			bw.write("白云校区"); bw.write("\t");
			bw.write("杨钊杨勋楼"); bw.write("\t");
			bw.write(room); bw.write("\t");
			bw.write(c.getCname()); bw.write("\t");
			bw.write(c.getTname()); bw.write("\t");
			bw.write(c.getNum()); bw.write("\t");
			bw.write(c.getCs()); bw.write("\t");
			bw.write(c.getWeek()); bw.write("\t");
			bw.write(c.getDay());
			
			bw.newLine();
		}
		bw.flush();
		bw.close();
		
	}


	/**
	 * @param tds
	 * @param courses 
	 */
	private static void addCourse(Elements tds, List<Course> courses) {
		
		
		//课程
		Course c = new Course();
		
		String cname = tds.get(0).text();
		String tname = tds.get(7).text();
		String num = tds.get(9).text();
		String cs = tds.get(10).text();
		
		c.setCname(cname);		//课程名
		c.setTname(tname);		//老师
		c.setNum(num);			//上课人数
		c.setCs(cs);			//上课班级
		
		String week = tds.get(11).text();
		week = week.replaceAll("-", "~");
		c.setWeek(week);	//周次
		c.setDay(tds.get(12).text());	//日次
		
		if(isEmpty(cname)) {
			
			//获取上一个课程
			Course pre = courses.get(courses.size()-1);
			c.setCname(pre.getCname());
			
			if(isEmpty(tname)) {
				c.setTname(pre.getTname());
			}
			
			if(isEmpty(num)) {
				c.setNum(pre.getNum());
			}
			
			if(isEmpty(cs)) {
				c.setCs(pre.getCs());
			}
			
		}
		
		courses.add(c);

	}
	
	public static void saveToExcel(String html) throws IOException {
		Document doc = Jsoup.parse(html);
		Elements es = doc.select("table[name='theExportData']");

		//获取第一栏table
		Element address = es.get(0);
		
		String fileName = address.selectFirst("tr").selectFirst("td").text();
		
		//table
		Element table = es.get(1);
		
		//标题
		Element title = table.selectFirst("tr");
		
		//所有tr，包括标题
		Elements trs = table.select("tr");
		
		List<Course> courses = new ArrayList<>();
		
		//遍历所有的tr
		for(Element tr : trs) {
			if(tr == title) {
				continue;
			}
			
			//每一行的td
			Elements tds = tr.select("td");
			
			addCourse(tds, courses);
			
		}
		
		exportExcel(courses, fileName);
	}

	/**
	 * @param cname
	 * @return
	 */
	private static boolean isEmpty(String str) {
		// TODO Auto-generated method stub
		if(null == str || "".equals(str)) {
			return true;
		}
		return false;
	}



	public static void main(String[] args) throws IOException {
		
		parser();
	}
}
