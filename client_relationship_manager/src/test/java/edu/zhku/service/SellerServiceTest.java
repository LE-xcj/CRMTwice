/**
 * 
 */
package edu.zhku.service;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.zhku.pojo.Bill;
import edu.zhku.pojo.BillCondition;
import edu.zhku.pojo.BillVO;
import edu.zhku.pojo.Client;
import edu.zhku.pojo.ClientVO;
import edu.zhku.pojo.EVA;
import edu.zhku.pojo.EVAVO;
import junit.framework.TestCase;

/**  
* <p>Title: SellerServiceTest.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年6月19日  
*/
@RunWith(SpringJUnit4ClassRunner.class)    
@ContextConfiguration(locations={"classpath:spring/springDao-config.xml","classpath:spring/springService-config.xml","classpath:mybatis/mybatis-config.xml"}) 
public class SellerServiceTest extends AbstractJUnit4SpringContextTests  {
	
	@Autowired
	SellerService s;
	/**
	 * Test method for {@link edu.zhku.service.impl.SellerServiceImpl#getModelName()}.
	 * @throws Exception 
	 */
	@Test
	public void test() throws Exception {
		
		if(null != s) {
			//s.createBill("201521314109", "", 123, null);
//			BillCondition condition = new BillCondition();
//			
//			condition.setSid("201521314109");
//			condition.setProgressing(true);
//			condition.setComplete(true);
//			
//			List<Bill> list = s.getPrgBill("201521314109");
//			
//			for(Bill b : list) {
//				System.out.println(b.getStatue());
//			}
			
			//s.updateBill("20152131410920180620151458", 865907, -2);
			//Map<Integer, List<EVAVO>> temp = s.getSatisfaction("201521314109");
			//Map<String, List<Float>> t = s.getPersonlExploitForYear("201521314109", 2018);
//			int [] a = s.getTimeDistance("201521314109");
//			System.out.println(a[0] + " " + a[1]);
			 //List<ClientVO> c = s.getValueClient(0, 4, "201521314109");
			//List<Client> c = s.getProClient(0, 5);
			//int c = s.countProClient();
			//List<Client> cs = s.showClients(1, 5);
			//System.out.println(c);
			String sid = "201521314109";
			int begin = 1;
			int size = 5;
			List<BillVO> bvos = s.getCompleteBill(sid, begin, size, 2018);
			System.out.println("ok");
		}else {
			System.out.println("not ok");
		}
	}
}
