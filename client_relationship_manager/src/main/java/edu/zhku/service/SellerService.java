/**
 * 
 */
package edu.zhku.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import edu.zhku.pojo.Bill;
import edu.zhku.pojo.BillCondition;
import edu.zhku.pojo.BillVO;
import edu.zhku.pojo.Client;
import edu.zhku.pojo.ClientVO;
import edu.zhku.pojo.EVA;
import edu.zhku.pojo.EVAVO;
import edu.zhku.pojo.Seller;
import edu.zhku.pojo.TimeDistance;

/**  
* <p>Title: SellerService.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年6月12日  
*/
public interface SellerService {
	
	Seller findSeller(Seller seller) throws Exception;
	
	void regist(Seller seller) throws Exception;
	
	boolean canRegist(Seller seller) throws Exception;
	
	boolean login(Seller seller) throws Exception;
	
	String[] getModelName() throws Exception;
	
	String getBillModelHtml(String docName) throws Exception;
	
	void createBill(String sid, String htm, String hb, float money, String cid) throws Exception;
	
	List<Bill> getBill(BillCondition condition) throws Exception;
	
	List<Bill> getPrgBill(String sid) throws Exception;
	
	List<Bill> getCompleteBill(String sid) throws Exception;
	
	void updateBill(String htm, String bh, String fileName) throws Exception;
	
	void updateBill(String bid, float money, int statue) throws Exception;
	
	Map<Integer, List<EVAVO>> getSatisfaction(String sid) throws Exception;
	
	Map<String, List<Float>> getPersonlExploitForYear(String sid, int year) throws Exception;
	
	int countClient() throws Exception;
	
	int[] petchImportClientImfor(MultipartFile fileModel) throws Exception;
	
	List<Client> showClients(int begin, int size) throws Exception;
	
	List<Client> searchClientByName(String cname) throws Exception;
	
	int[] getTimeDistance(String sid) throws Exception;
	
	int countValueClient(String sid) throws Exception;
	
	int countProClient() throws Exception;
	
	List<ClientVO> getValueClient(int begin, int size, String sid) throws Exception;
	
	List<Client> getProClient(int begin, int size) throws Exception;
	
	List<BillVO> getCompleteBill(String sid, int begin, int size, int year) throws Exception;
	
}
