/**
 * 
 */
package edu.zhku.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import edu.zhku.mapper.ClientMapper;
import edu.zhku.pojo.Client;
import edu.zhku.pojo.EVA;
import edu.zhku.service.ClientService;
import edu.zhku.util.IDGenerator;

/**  
* <p>Title: ClientServiceImpl.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年6月15日  
*/
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	private ClientMapper clientMapper;

	/* (non-Javadoc)
	 * @see edu.zhku.service.ClientService#findClient(edu.zhku.pojo.Client)
	 */
	@Override
	public Client findClient(Client client) throws Exception {
		// TODO Auto-generated method stub
		
		return clientMapper.findClient(client);
	}

	/* (non-Javadoc)
	 * @see edu.zhku.service.ClientService#canLogin(edu.zhku.pojo.Client)
	 */
	@Override
	public boolean canLogin(Client client) throws Exception {
		// TODO Auto-generated method stub
		Client c = clientMapper.findClient(client);
		if(null == c) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see edu.zhku.service.ClientService#canRegist(edu.zhku.pojo.Client)
	 */
	@Override
	public boolean canRegist(Client client) throws Exception {
		// TODO Auto-generated method stub
		Client c = clientMapper.findClient(client);
		if(null == c) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.zhku.service.ClientService#regist(edu.zhku.pojo.Client)
	 */
	@Override
	public void regist(Client client) throws Exception {
		// TODO Auto-generated method stub
		//自动绑定邮箱地址
		client.seteMail(client.getCid());
		
		clientMapper.regist(client);
	}

	/* (non-Javadoc)
	 * @see edu.zhku.service.ClientService#evaluate(java.lang.String, java.lang.String, int)
	 */
	@Override
	public void evaluate(String sid, String evaluation, String cid, int level) throws Exception {
		
		EVA eva = new EVA();
		eva.setSid(sid);
		eva.setLevel(level);
		eva.setEvaluation(evaluation);
		eva.setCid(cid);
		eva.setEtime(IDGenerator.getDate());
		//向数据库插入评价记录
		clientMapper.evaluate(eva);
		
	}

	/* (non-Javadoc)
	 * @see edu.zhku.service.ClientService#updateClient(edu.zhku.pojo.Client)
	 */
	@Override
	public void updateClient(Client client) throws Exception {
		
		clientMapper.update(client);
		
	}
	
	
	
}
