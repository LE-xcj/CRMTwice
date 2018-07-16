/**
 * 
 */
package edu.zhku.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import edu.zhku.pojo.Address;
import edu.zhku.pojo.Arrange;
import edu.zhku.pojo.Time;

/**  
* <p>Title: AllMapper.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年7月6日  
*/
public interface AllMapper {
	void insertKe(@Param("kes")Map<String, String> kes);
	
	void insertAddress(List<Address> adds);
	
	void insertTime(List<Time> times);
	
	void insertArrange(List<Arrange> arranges);
	
	
}
