package edu.zhku.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.zhku.pojo.Bill;
import edu.zhku.pojo.BillCondition;
import edu.zhku.pojo.BillVO;
import edu.zhku.pojo.Client;
import edu.zhku.pojo.ClientVO;
import edu.zhku.pojo.EVA;
import edu.zhku.pojo.Seller;
import edu.zhku.pojo.TimeDistance;

public interface SellerMapper {
   
	Seller findSeller(Seller seller) throws Exception;
	
	void regist(Seller seller) throws Exception;
	
	void createBill(Bill bill) throws Exception;
	
	List<Bill> getBill(BillCondition condition) throws Exception;
	
	void updateBill(Bill bill) throws Exception;
	
	BillVO getBillDetailByBid(String bid) throws Exception;
	
	List<Bill> getPersonlBillForYear(@Param("sid")String sid,@Param("bYear")String bYear, @Param("eYear")String eYear) throws Exception;
	
	
	int countClient() throws Exception;
	
	void petchImportClientImfor(List<Client> cs) throws Exception;
	
	List<Client> showClientsForPage(@Param("begin")int begin, @Param("size")int size) throws Exception;
	
	List<Client> searchClientByName(String cname) throws Exception;
	
	TimeDistance getTimeDistance(String sid) throws Exception;
	
	
	int countValueClient(String sid) throws Exception;
	
	int countProClient() throws Exception;
	
	List<ClientVO> getValueClient(@Param("begin")int begin, @Param("size")int size, @Param("sid")String sid) throws Exception;
	
	List<Client> getProClient(@Param("begin")int begin, @Param("size")int size) throws Exception;
	
	
	List<BillVO> getCompleteBill(@Param("sid") String sid, @Param("begin")int begin, @Param("size")int size, @Param("year")int year) throws Exception;
}