/**
 * 
 */
package edu.zhku.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.zhku.mapper.AllMapper;
import edu.zhku.pojo.Address;
import edu.zhku.pojo.Arrange;
import edu.zhku.pojo.Ke;
import edu.zhku.pojo.Time;
import edu.zhku.util.ExcelRead;

/**  
* <p>Title: AllService.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年7月6日  
*/

@Service("allService")
public class AllService {
	
	@Autowired
	private AllMapper allMapper;
	
	
	public void readExcel(String path) {
		File dir = new File(path);
		if(dir.exists()) {
			File[] fs = dir.listFiles();
			for(File f : fs) {
				if(f.isFile()) {
					String fileName = f.getAbsolutePath();
					List<String> data = ExcelRead.getDate(fileName);
					
					saveArrange(data);
					//saveToDB(data);
				}else {
					String p = f.getPath();
					readExcel(p);
				}
			}
		}
	}
	
	public void saveArrange(List<String> data) {
		
		List<Arrange> arranges = new ArrayList<>();
		String xq = "白云校区";
		String jxl = "杨钊杨勋楼";
		String room = null;
		String term = "20171";
		
		for(String line : data) {
			String[] array = line.split("\t");
			
			String cid_cname = array[3];
			int index = cid_cname.indexOf("]");
			
			String cid = cid_cname.substring(1, index);
			room = array[2];
			
			
			Arrange t = new Arrange();
			t.setTerm(term);
			t.setWeek(array[7]);
			t.setDay(array[8]);
			t.setCid(cid);
			t.setConsist(array[6]);
			t.setNum(array[5]);
			t.setTname(array[4]);
			
			t.setXq(xq);
			t.setJxl(jxl);
			t.setKs(room);
			arranges.add(t);
		}
		
		allMapper.insertArrange(arranges);
	}
	
	public void saveToDB(List<String> data) {
		
		Map<String, String> kes = new HashMap<>();
		List<Address> addresses= new ArrayList<>();
		List<Time> times = new ArrayList<>();
		
		String xq = "白云校区";
		String jxl = "杨钊杨勋楼";
		String room = null;
		String term = "20171";
		
		for(String line : data) {
			String[] array = line.split("\t");
			
			String cid_cname = array[3];
			int index = cid_cname.indexOf("]");
			
			String cid = cid_cname.substring(1, index);
			String cname = cid_cname.substring(index+1);
			kes.put(cid, cname);
			
			room = array[2];
			
			
			Time t = new Time();
			t.setTerm(term);
			t.setWeek(array[7]);
			t.setDay(array[8]);
			t.setCid(cid);
			t.setConsist(array[6]);
			t.setNum(array[5]);
			t.setTname(array[4]);
			times.add(t);
		}
		
		for(String cid : kes.keySet()) {
			Address addr = new Address();
			addr.setXq(xq);
			addr.setJxl(jxl);
			addr.setKs(room);
			addr.setCid(cid);
			addr.setTerm(term);
			addresses.add(addr);
		}
		
		//插入课程 kes
		allMapper.insertKe(kes);
		
		//插入地址安排 addresses
		allMapper.insertAddress(addresses);
		
		//插入时间安排 times
		allMapper.insertTime(times);
		
		
	}

	public AllMapper getAllMapper() {
		return allMapper;
	}

	public void setAllMapper(AllMapper allMapper) {
		this.allMapper = allMapper;
	}
	

}
