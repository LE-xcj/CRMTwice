/**
 * 
 */
package edu.zhku.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.zhku.mapper.AllMapper;
import edu.zhku.pojo.Address;
import edu.zhku.pojo.Time;
import edu.zhku.service.AllService;
import edu.zhku.view.MainTest;

/**  
* <p>Title: Main.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年7月6日  
*/
public class Main {
	public static void main(String[] args) {
		//new MainTest("img.jpg", 1);
		//test2();
		//test4();
		
		test1();
	}
	
	/**
	 * 
	 */
	private static void test4() {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/springDao-config.xml");
		AllMapper allMapper =  (AllMapper) context.getBean("allMapper");
		
		List<Time> times = new ArrayList<>();
		Time t = new Time();
		
		t.setTerm("20170");
		t.setWeek("1~8,10~12");
		t.setDay("二[3-4节]");
		t.setCid("10555");
		t.setConsist("投资171 投资172 投资173");
		t.setNum("200");
		t.setTname("骆少明");
		
		Time t2 = new Time();
		
		t2.setTerm("20170");
		t2.setWeek("1~8,10~12");
		t2.setDay("二[3-4节]");
		t2.setCid("10555");
		t2.setConsist("投资171 投资172 投资173");
		t2.setNum("200");
		t2.setTname("骆少明");
		
		times.add(t2);
		times.add(t);
		
		allMapper.insertTime(times);
		
	}

	/**
	 * 
	 */
	private static void test3() {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/springDao-config.xml");
		AllMapper allMapper =  (AllMapper) context.getBean("allMapper");
		
		Address a = new Address();
		List<Address> adds = new ArrayList<>();
		
		a.setXq("白云校区");
		a.setJxl("杨钊杨勋楼");
		a.setKs("103");
		a.setCid("123");
		a.setTerm("20170");
		
		Address a2 = new Address();
		
		a2.setXq("白云校区");
		a2.setJxl("杨钊杨勋楼");
		a2.setKs("103");
		a2.setCid("123");
		a2.setTerm("20170");
		
		adds.add(a);
		adds.add(a2);
		
		allMapper.insertAddress(adds);
	}

	/**
	 * 
	 */
	private static void test2() {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/springDao-config.xml");
		AllMapper allMapper =  (AllMapper) context.getBean("allMapper");
		
		Map<String, String> map = new HashMap<>();
		map.put("123", "java");
		map.put("124", "c++");
		
		allMapper.insertKe(map);
	}

	public static void test1() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/springDao-config.xml");
		AllService alls = (AllService) context.getBean("allService");
		
		alls.readExcel("excel");
	}
	
}
