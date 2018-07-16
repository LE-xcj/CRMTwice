/**
 * 
 */
package edu.zhku.mapper;

import java.util.List;

import edu.zhku.pojo.EVAVO;

/**  
* <p>Title: EVAMapper.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年6月21日  
*/
public interface EVAMapper {
	List<EVAVO> getSatisfaction(String sid) throws Exception;
}
