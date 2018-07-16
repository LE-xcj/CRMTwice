package edu.zhku.mapper;

import edu.zhku.pojo.Client;
import edu.zhku.pojo.EVA;

public interface ClientMapper {
	
	Client findClient(Client client) throws Exception;
	
	void regist(Client client) throws Exception;
	
	void update(Client client) throws Exception;
	
	void evaluate(EVA eva) throws Exception;
	
	
}