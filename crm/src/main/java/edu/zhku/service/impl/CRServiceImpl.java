/**
 * 
 */
package edu.zhku.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.zhku.mapper.CRMapper;
import edu.zhku.pojo.Arrange;
import edu.zhku.pojo.CRState;
import edu.zhku.pojo.ClassRoom;
import edu.zhku.pojo.Record;
import edu.zhku.pojo.Search;
import edu.zhku.pojo.SearchFCRVO;
import edu.zhku.pojo.SearchFSVO;
import edu.zhku.pojo.SearchResultVO;
import edu.zhku.pojo.SearchVO;
import edu.zhku.pojo.Term;
import edu.zhku.service.CRService;
import edu.zhku.util.DayUtil;
import edu.zhku.util.ExcelRead;

/**  
* <p>Title: CRServiceImpl.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年7月7日  
*/
@Service
public class CRServiceImpl implements CRService{

	@Autowired
	private CRMapper crMapper;
	
	/* 
	 * 获取所有学期
	 */
	@Override
	public List<String> getTerms() throws Exception {
		
		List<Term> terms = crMapper.getTerm();
		
		List<String> ts = new ArrayList<>();
		
		for(Term t : terms) {
			ts.add(t.getTerm());
		}
		
		return ts;
	}
	
	
	/**
	 * 获取某个学期的周数
	 */
	@Override
	public String getWeeks(String term) throws Exception {
		// TODO Auto-generated method stub
		
		List<Term> terms = crMapper.getTerm();
		String weeks = null;
		for(Term t : terms) {
			if(t.getTerm().equals(term)) {
				weeks = t.getWeeks();
				break;
			}
		}
		
		return weeks;
	}

	/* 
	 * 获取某个校区、某栋教学楼的所有课室
	 */
	@Override
	public List<String> getRooms(String xq, String jxl) throws Exception {
		
		List<ClassRoom> cs = crMapper.getClassRoom(xq);
		
		List<String> rooms = new ArrayList<>();
		
		for(ClassRoom c : cs) {
			if(c.getJxl().equals(jxl)) {
				rooms.add(c.getKs());
			}
		}
		
		return rooms;
	}

	/* 
	 * 获取某个校区下所有的教学楼
	 */
	@Override
	public Set<String> getJxl(String xq) throws Exception {
		// TODO Auto-generated method stub
		
		List<ClassRoom> cs = crMapper.getClassRoom(xq);
		
		Set<String> jxl = new HashSet<>();
		
		for(ClassRoom c : cs) {
			jxl.add(c.getJxl());
		}
		
		return jxl;
	}

	/* 
	 * 查询某间课室在哪个学期、哪个周次的使用情况
	 * 显示星期1~7、1~12节的使用情况
	 * 
	 * 这里返回的数据结构解释：
	 * List<SearchResultVO> ： 某一节次下所有星期的安排情况
	 * List<List<>>		： 	所有节次
	 * 
	 * 因为显示是以：节次作为行，星期作为列来显示的。
	 */
	@Override
	public List<List<SearchResultVO>> getCRUseState(SearchVO svo) throws Exception {
		
		Map<Integer, Map<Integer, SearchResultVO>> map = new HashMap<>();
		
		//从数据库获取原始数据
		List<SearchResultVO> result = crMapper.getCRUseState(svo);
		
		//获取使用者查询的周次
		String weeks = svo.getWeeks();
		
		//遍历从数据库返回的结果集
		for(SearchResultVO r : result) {
			
			//获取对象的周次，这里的周次是未经过处理的，如：1~13周，15周的形式
			String rweek = r.getWeeks();
			
			//获取对象的日次，这里日次也是没有经过处理，如：一[1-2节]的形式
			String day = r.getDay();
			
			//这里是判断该对象是否在使用者筛选的范围里
			if(isInRweek(weeks, rweek, day)) {
				add(map, r);
			}
		}
		
		
		//将map转成list数据结构
		List<List<SearchResultVO>> data = dealResultMap(map);
		
		return data;
	}

	/**
	 * 
	 * 将map数据转为list数据结构
	 * 
	 * 因为存在某一天，有些节次是没有安排课程的，
	 * 所以就需要对这些没有被安排的节次进行初始化，但是大小为0
	 * 
	 * 同样某一天也可能没有被安排课程，所以也需要对某一天进行初始化，但大小同样为0
	 * @param map
	 * @return
	 */
	private List<List<SearchResultVO>> dealResultMap(Map<Integer, Map<Integer, SearchResultVO>> map) {
		// TODO Auto-generated method stub
		List<List<SearchResultVO>> row = new ArrayList<>();
		
		//遍历所有节次
		for(Integer jie=1; jie<=12; ++jie) {
			
			//获取某一节次下，所有星期的安排情况
			Map<Integer, SearchResultVO> jies = map.get(jie);
			List<SearchResultVO> col = new ArrayList<>();
			
			//遍历所有星期
			for(Integer day=1; day<=7; ++day) {
				
				/**
				 * 这里要考虑
				 * 1、某一节所有星期都没哟课
				 * 2、某一天没有课
				 * 
				 */
				if(jies != null && null != jies.get(day)) {
					col.add(jies.get(day));
				}else {
					
					//添加空的课程
					col.add(new SearchResultVO());
				}
				
			}
			
			//添加星期
			row.add(col);
		}
		
		return row;
	}



	/**
	 * 
	 * 用于某一节次，课室所有星期下的安排情况
	 * 
	 * @param map
	 * @param r
	 */
	private void add(Map<Integer, Map<Integer, SearchResultVO>> map, SearchResultVO r) {
		// TODO Auto-generated method stub
		String rday = r.getDay();
		
		//获取星期的数字形式
		Integer day = DayUtil.getDay(rday.substring(0,1));	//星期
		
		//获取节次范围：如：1-2
		String jie = rday.substring(2, rday.lastIndexOf("节"));	//节次
		
		
		//处理节次（1-2），变成数组
		String[] distance = jie.split("-");		//节次范围
		
		
		int begin = Integer.valueOf(distance[0]);
		int end = Integer.valueOf(distance[1]);
		
		
		/**
		 * 
		 * 从开始的节次到结束节次遍历
		 * 这里保存的是某一星期下，课室的节次安排情况。
		 */
		for(int i=begin; i<=end; ++i) {
			Integer k_jie = i;
			
			//获取某一节次，课室所有星期的安排情况
			Map<Integer, SearchResultVO> m = map.get(k_jie);
			
			//空则代表之前没有安排
			if(m == null) {
				m = new HashMap<>();
				map.put(k_jie, m);
			}
			
			//设置这一星期有课程安排
			m.put(day, r);
		}
		
		
	}

	/**
	 * 
	 * 用于判断某个课程是否在指定的周里
	 * 
	 * 基本流程：
	 * 	1、先判断是否在周次里
	 * 	2、再判断是否单双周，但是前提是1要成立
	 * @param weeks	用户指定的某一周
	 * @param rweek	课室的记录周
	 * @param day	因为有些是有个单字
	 * @return
	 */
	private boolean isInRweek(String weeks, String rweek, String day) {
		// TODO Auto-generated method stub
		boolean flag = false;
		
		//分离带有，的周次范围
		String[] segment = rweek.split(",");
		
		//目标周次
		int i_weeks = Integer.valueOf(weeks);
		
		//判断是否在这周
		if(isInRweek(segment, i_weeks)) {
			
			//再做进一步判断，因为符合了周次范围，但是还需要对是否为单双周进行判断
			flag = isInRweek(day, i_weeks);
		}
		
		
		return flag;
	}

	/**
	 * 前提条件：已经是在周次范围内
	 * 判断是否符合单双周
	 * 三种情况：单、双、无
	 * @param day
	 * @return
	 */
	private final static String SINGLE =  "单";
	private final static String DOUBLE = "双";
	private boolean isInRweek(String day, int i_weeks) {
		// TODO Auto-generated method stub
		
		boolean flag = true;
		
		//单周
		if(day.contains(SINGLE)) {
			
			//如果查询的周不是单周，则为false
			if(1 != i_weeks%2) {
				flag = false;
			}
			
		}else if(day.contains(DOUBLE)){		//双周
			if(0 != i_weeks%2) {
				flag = false;
			}
		}
		
		//如果不是单双周，直接是true
		return flag;
	}

	/**
	 * 判断查询的周次是否在结果的范围内
	 * 
	 * @param segment
	 * @param i_weeks
	 * @return
	 */
	private boolean isInRweek(String[] segment, int i_weeks) {
		// TODO Auto-generated method stub
		boolean flag = false;
		
		///遍历所有周次范围
		for(String s : segment) {
			
			String[] distance = s.split("~");
			
			int begin  = Integer.valueOf(distance[0]);
			
			//判断是否周次范围，因为有些周次是特定一个周
			if(distance.length != 1) {
				int end = Integer.valueOf(distance[1]);
				
				//判断是否在范围内
				if(i_weeks >= begin && i_weeks <= end) {
					flag = true;
					break;
				}
			}else {
				
				//具体某一周
				if(i_weeks == begin) {
					flag = true;
					break;
				}
			}
			
		}
		return flag;
	}

	/* 
	 * 空课室查询
	 * 在指定的校区、教学楼、学期、周次范围、日次、节次范围，查找该教学楼所有在指定时间段下空闲的课室
	 * 这里返回的数据结构代表的意思是：某一栋教学楼下，每一层的空闲课室
	 */
	@Override
	public List<Set<Integer>> getFreeCR(SearchFCRVO frVO) throws Exception {
		// TODO Auto-generated method stub
		
		//获取所有课室的使用情况
		List<CRState> result = crMapper.getCRState(frVO);
		
		/**
		 * 记录每一间课室的使用情况，key：课室， value：使用情况
		 * 
		 * 归并每一间课室的使用情况
		 */
		Map<String, List<CRState>> data = mergeCR(result);
		
		Set<Integer> freeRoom = new HashSet<>();
		
		//遍历每一间课室
		for(String ks : data.keySet()) {
			
			//记录集
			List<CRState> record = data.get(ks);
			
			boolean isFree = true;
			
			//遍历课室所有的安排记录
			for(CRState schedul : record) {

				//判断该课室在这个时间段是否空闲
				if(isNotFreeThisMoment(frVO, schedul)) {
					isFree = false;
					break;
				}
			}
			
			if(isFree) {
				freeRoom.add(Integer.valueOf(ks));
			}
			
		}
		
		
		List<Set<Integer>> r = dealFreeRoom(freeRoom);
		
		return r;
	}

	/**
	 * @param freeRoom
	 * @return
	 */
	private List<Set<Integer>> dealFreeRoom(Set<Integer> freeRoom) {
		// TODO Auto-generated method stub
		freeRoom = new TreeSet<>(freeRoom);
		int pre  = 0;
		List<Set<Integer>> data = new ArrayList<>();
		Set<Integer> floors = new TreeSet<>();
		data.add(floors);
		
		for(Integer room : freeRoom) {
			int floor = room/100;
			
			if(pre == (floor)) {
				floors.add(room);
			}else {
				pre = floor;
				floors = new TreeSet<>();
				floors.add(room);
				data.add(floors);
				
			}
		}
		return data;
	}

	/**
	 * 
	 * 判断是否在指定的时间段里不空闲
	 * @param frVO
	 * @param record
	 * @return
	 */
	private boolean isNotFreeThisMoment(SearchFCRVO frVO, CRState schedul) {
		// TODO Auto-generated method stub
		
		//获取指定周次范围
		String targetWeek = frVO.getWeeks();
		
		//指定星期
		Integer targetDay = frVO.getDay();
		
		//指定节次范围
		Integer beginJie = frVO.getBeginJie();
		Integer endJie = frVO.getEndJie();
		
		
		String weeks = schedul.getWeeks();
		String day = schedul.getDay();
		
		boolean isNotFree = false;
		
		//是否在这一天和这一节段
		if(isInThisDayAndJie(targetDay, beginJie, endJie, day)) {
			//判断是否在这一周
			if(isInRweek(targetWeek, weeks, day)) {
				isNotFree = true;
			}
		}
		
		
		return isNotFree;
	}

	/**
	 * @param targetDay
	 * @param beginJie
	 * @param endJie
	 * @param day
	 * @return
	 */
	private boolean isInThisDayAndJie(Integer targetDay, Integer beginJie, Integer endJie, String day) {
		// TODO Auto-generated method stub
		
		int rday = DayUtil.getDay(day.substring(0, 1));
		
		boolean flag = false;
		
		//判断是否在这一天
		if(rday == (targetDay)) {
			
			String jie = day.substring(2, day.lastIndexOf("节"));
			//
			if(inThisJie(beginJie, endJie, jie)) {
				flag = true;
			}
		}
		
		
		return flag;
	}

	/**
	 * 一共有四种情况：
	 * 	1、左交叉
	 * 	2、右交叉
	 * 	3、包含
	 * 	4、被包含
	 * @param beginJie
	 * @param endJie
	 * @param jie
	 * @return
	 */
	private boolean inThisJie(Integer beginJie, Integer endJie, String jie) {
		// TODO Auto-generated method stub
		String[] temp = jie.split("-");
		
		int begin = Integer.valueOf(temp[0]);
		int end = Integer.valueOf(temp[1]);
		
		boolean flag = false;
		
		//左交叉
		if(begin <= beginJie && beginJie <= end) {
			flag = true;
		}
		
		//右交叉
		if(begin <= endJie && endJie <= end) {
			flag = true;
		}
		
		
		//包含
		if(beginJie <= begin && end <= endJie) {
			flag = true;
		}
		
		//被包含
		if(begin <= beginJie && endJie <= end) {
			flag = true;
		}
		
		return flag;
	}

	/**
	 * @param data
	 */
	private Map<String, List<CRState>> mergeCR(List<CRState> data) {
		Map<String, List<CRState>> map = new HashMap<>();
		for(CRState record : data) {
			String ks = record.getKs();
			List<CRState> sechedul = map.get(ks);
			if(null == sechedul) {
				sechedul = new ArrayList<>();
				map.put(ks, sechedul);
			}
			sechedul.add(record);
		}
		
		return map;
		
	}


	
	///////////////////////////////////////////////////////////////////
	//////////////////////////课室空闲段查询////////////////////////////////
	///////////////////////////////////////////////////////////////////
	@Override
	public List<List<List<String>>> getCRFreeSegment(SearchFSVO fsvo) throws Exception {
		// TODO Auto-generated method stub
		
		//获取源生数据，经过xq、jxl、ks、term的筛选
		List<Arrange> result = crMapper.getCRArrange(fsvo);
		
		//排序，根据星期升序排序，如果星期相同，那就按照节次的首节升序排序
		Collections.sort(result);
		
		//记录该课室在指定范围的日次和周次的所有使用情况
		/*
		 * Boolean 是以星期作为横坐标、节次作为纵坐标的布尔数组, 代表该课室在某一星期的第几节的使用情况，使用则是true
		 * 
		 * 层次：星期、周次、使用记录
		 */
		Map<Integer, Map<Integer, Boolean[][]>> records = new HashMap<>();
		
		//搜索的日次范围
		int beginDay = fsvo.getBeginDay();
		int endDay = fsvo.getEndDay();
		
		//遍历该课室的所有安排记录
		for(Arrange arrange : result) {
			
			//获取记录中的日次安排
			String day = arrange.getDay();
			
			//筛选从beginDay - > endDay 之间 所有的使用情况
			for(int i=beginDay; i<=endDay; ++i) {
				
				//如果是在这一天
				if(inThisDay(day, i)) {
					
					//获取记录中周次的安排
					String rweek = arrange.getWeeks();
					
					//获取搜索的周范围
					int beginWeek = fsvo.getBeginWeek();
					int endWeek = fsvo.getEndWeek();
					
					//获取第i天的 所有周 使用情况
					Map<Integer, Boolean[][]> weeksRecord = records.get(i);
					
					if(null == weeksRecord) {
						weeksRecord = new HashMap<>();
						records.put(i, weeksRecord);
					}
					
					
					//进行第二步处理，判断周次
					foreachWeek(weeksRecord, beginWeek, endWeek, rweek, day);
					
					
				}
				
			}
			
			
		}//end 遍历数据库返回的记录
		
		
		//这里需要对record 变量做最后一步处理
		//对使用情况进行取反 , 以及改变它的存储结构，转换成返回的类型 List<list < list<String> > >
		
		int beginWeek = fsvo.getBeginWeek();
		int endWeek = fsvo.getEndWeek();
		List<List<List<String>>> data = lastProcess(records, beginDay, endDay, beginWeek, endWeek);
		
		return data;
	}

	/**
	 * 对加工处理后的数据，进行最后一步处理
	 * 1、对使用情况进行取反
	 * 2、改变records的存储结构
	 * 
	 * 一定要记住存储结构是：星期、周次、节次情况
	 * @param records
	 * @return
	 */
	private List<List<List<String>>> lastProcess(
			Map<Integer, Map<Integer, Boolean[][]>> records,
			int beginDay, int endDay, int beginWeek, int endWeek) {
		// TODO Auto-generated method stub
		
		//日次
		List<List<List<String>>> days = new ArrayList<>();
		
		//遍历星期
		for(Integer d=beginDay; d<=endDay; ++d) {
			
			//周次
			List<List<String>> weeks = new ArrayList<>();
			
			//获取该星期所有周次
			Map<Integer, Boolean[][]> rweeks = records.get(d);
			
			if(rweeks == null) {
				rweeks = new HashMap<>();
			}
			
			//遍历所有周
			for(Integer w=beginWeek; w<=endWeek; ++w){
				
				//获取使用记录
				Boolean[][] rjies = rweeks.get(w);
				
				if(rjies == null) {
					rjies = initUseState();
				}
				
				//做取反处理
				List<String> jies = getFreeSegment(rjies, d);
				
				//存储所有经过取反的jie
				weeks.add(jies);
			}
			
			//存储该星期的所有周次空闲段
			days.add(weeks);
		}
		
		return days;
	}

	/**
	 * 对使用记录进行取反处理
	 * @param rjies		某星期、某一周的具体使用情况
	 * @param day	某星期确定行
	 * @return
	 */
	private List<String> getFreeSegment(Boolean[][] rjies, int day) {
		// TODO Auto-generated method stub
		
		List<Integer> data = new ArrayList<>();
		List<String> result = new ArrayList<>();
		//确定行
		for(int j=1; j<13; ++j) {
			//day星期 在 该周的被使用
			if(j ==5 || rjies[day][j]) {
				continue;
			}else {
				//添加可使用的节次
				data.add(j);
			}
		}
		
		int length = data.size();
		if(length != 0) {
			int begin = data.get(0);
			int pre = begin;
			
			for(int i=1; i<length; ++i){
				
				int current = data.get(i);
				
				if(current - pre != 1) {
					String freeJie = begin + "-" + pre + "节";
					begin = current;
					pre = current;
					result.add(freeJie);
				}else {
					pre = current;
				}
				
			}
			
			//最后一组
			result.add((begin + "-" + pre + "节"));
		}
		
			
		
		return result;
	}

	/**
	 * 用于遍历搜索周次范围，记录该课室该星期所有周的使用记录
	 * @param weeksRecord	某一个星期下所有周次的使用情况
	 * @param beginWeek		搜索的起始周次
	 * @param endWeek		搜索的终止周次
	 * @param rweek			数据库提取出来的记录周次
	 * @param day			该课室的日次记录，数据库提取的结果
	 */
	private void foreachWeek(Map<Integer, Boolean[][]> weeksRecord, int beginWeek, int endWeek, String rweek,
			String day) {
		// TODO Auto-generated method stub
		
		//遍历搜索周次范围，因为不在范围无需关心
		for(int i=beginWeek; i<=endWeek; ++i) {
			
			//如果该记录在搜索周次范围，就进行下一步的处理，提取日次和节次，并进行记录
			if(isInRweek(i+"", rweek, day)) {
				Boolean[][] useState = weeksRecord.get(i);
				if(null == useState) {
					
					//对useState二维数组进行初始化
					useState = initUseState();
					
					//添加
					weeksRecord.put(i, useState);
				}
				
				//开始对这一周，的某个星期进行 “记录使用情况”
				recordUse(useState, day);
			}else {
				continue;
			}
			
		}
	}

	/**
	 * 一共有7天、12节，因为考虑遍历方便，所以就设置8行、13列
	 * 
	 * 初始全部为false，代表没有使用
	 * @param useState
	 * 
	 */
	private Boolean[][] initUseState() {
		// TODO Auto-generated method stub
		Boolean[][] useState = new Boolean[8][13];
		
		for(int i=0; i<8; ++i) {
			for(int j=0; j<13; ++j) {
				useState[i][j] = new Boolean(false);
			}
		}
		return useState;
	}

	/**
	 * 
	 * 记录某个星期， 在哪段节次的使用情况
	 * 以星期作为横坐标、 节次作为纵坐标
	 * @param useState
	 * @param day	数据库中的形式
	 */
	private void recordUse(Boolean[][] useState, String day) {
		
		//获取该记录是哪一天的
		int d = DayUtil.getDay(day.substring(0, 1));
		
		//获取该记录的节次范围
		int beginJie = DayUtil.getBeginjie(day);
		int endJie = DayUtil.getEndJie(day);
		
		//遍历该记录节的范围
		for(int i=beginJie; i<=endJie; ++i) {
			
			//记录使用情况
			useState[d][i] = true;
		}
	}

	/**
	 * 
	 * 判断arrange的 day是否在检索范围中
	 * @param day
	 * @param i
	 * @return
	 */
	private boolean inThisDay(String day, int tday) {
		// TODO Auto-generated method stub
		day = day.substring(0, 1);
		int d = DayUtil.getDay(day);
		if(d == tday) {
			return true;
		}
		return false;
	}

	
	
	////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////课室使用申请/////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	@Override
	public List<Record> petchInsertRecord(MultipartFile fileModel) throws Exception {
		// TODO Auto-generated method stub
		
		//读取Excel数据到List中
		List<ArrayList<String>> list = new ExcelRead().readExcel(fileModel);
		
		List<Record> canInsertRS = new ArrayList<>();
		List<Record> canNotInsert = new ArrayList<>();
		
		String title = fileModel.getOriginalFilename();
		String[] temp = title.split("-");
		String term = temp[0];
		String xq = temp[1];
		String jxl = temp[2];
		String ks = temp[3];
		
		list.remove(0);
		
		//遍历Excel表中的每一行数据， arr：代表一行， list代表所有行
		for(ArrayList<String> row : list) {
			
			Record r = new Record();
			r.setTerm(term);
			r.setXq(xq);
			r.setJxl(jxl);
			r.setKs(ks);
			
			r.setCid(row.get(0));
			r.setTname(row.get(2));
			r.setConsist(row.get(3));
			r.setNum(row.get(4));
			
			String weeks = row.get(5);
			String day = row.get(6);
			
			r.setWeeks(weeks);
			r.setDay(day);
			
			//判断这条纪录是否可以加入到“插入列表”
			Search search = new Search();
			search.setTerm(term);
			search.setXq(xq);
			search.setJxl(jxl);
			search.setKs(ks);
			
			if(canInsert(r, search)) {
				canInsertRS.add(r);
			}else {
				canNotInsert.add(r);
			}
			
		}
		
		
		//插入满足条件的记录
		//crMapper.petchInsertRecord(canInsertRS);
		
		//返回不能插入的记录
		return canNotInsert;
	}

	/**
	 * @param r
	 * @param search
	 * @return
	 * @throws Exception 
	 */
	private boolean canInsert(Record r, Search search) throws Exception {
		// TODO Auto-generated method stub
		
		boolean canInsert = true;
		//获取使用情况
		List<CRState> result = crMapper.getCRStateBySearch(search);
		
		//遍历所有记录
		for(CRState dr : result) {
			//判断是否存在周次冲突
			
			//节次冲突
			
			//日次冲突
		}
		
		//isInRweek(weeks, rweek, day)
		
		return canInsert;
	}


	
}
