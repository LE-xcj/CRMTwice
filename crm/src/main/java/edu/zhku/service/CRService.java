/**
 * 
 */
package edu.zhku.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import edu.zhku.pojo.Record;
import edu.zhku.pojo.SearchFCRVO;
import edu.zhku.pojo.SearchFSVO;
import edu.zhku.pojo.SearchResultVO;
import edu.zhku.pojo.SearchVO;


/**  
* <p>Title: CRService.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年7月7日  
*/
public interface CRService {

	//获取某校区、某教学楼、某课室、某学期、某一周，课室的使用情况
	List<List<SearchResultVO>> getCRUseState(SearchVO svo) throws Exception;
	
	//获取学期
	List<String> getTerms() throws Exception;
	
	//获取某学期的周数
	String getWeeks(String term) throws Exception;
	
	//获取某栋教学楼的课室
	List<String> getRooms(String xq, String jxl) throws Exception;
	
	//获取某个校区的教学楼
	Set<String> getJxl(String xq) throws Exception;
	
	List<Set<Integer>> getFreeCR(SearchFCRVO frVO) throws Exception;
	
	//获取某个课室在某个学期，从第n周导第m周，从星期k到星期l之间的所有空闲时间段
	List<List<List<String>>> getCRFreeSegment(SearchFSVO fsvo) throws Exception;
	
	List<Record> petchInsertRecord(MultipartFile fileModel) throws Exception;
}
