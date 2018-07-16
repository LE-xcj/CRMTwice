/**
 * 
 */
package edu.zhku.mapper;

import java.util.List;

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

/**  
* <p>Title: CRMapper.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年7月7日  
*/
public interface CRMapper {
	
	List<Term> getTerm() throws Exception;
	
	List<ClassRoom> getClassRoom(String xq) throws Exception;
	
	List<SearchResultVO> getCRUseState(SearchVO svo) throws Exception;
	
	List<CRState> getCRState(SearchFCRVO frVO) throws Exception;
	
	List<Arrange> getCRArrange(SearchFSVO fsvo) throws Exception;
	
	void petchInsertRecord(List<Record> rds) throws Exception;
	
	List<CRState> getCRStateBySearch(Search search) throws Exception;
}
