/**
 * 
 */
package edu.zhku.service;

import edu.zhku.pojo.Client;

/**  
* <p>Title: ClientService.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年6月15日  
*/
public interface ClientService {
	
	Client findClient(Client client) throws Exception;
	
	boolean canLogin(Client client) throws Exception;
	
	boolean canRegist(Client client) throws Exception;
	
	void regist(Client client) throws Exception;
	
	void evaluate(String sid, String evaluation, String cid, int level) throws Exception;
	
	
	void updateClient(Client client) throws Exception;
}
