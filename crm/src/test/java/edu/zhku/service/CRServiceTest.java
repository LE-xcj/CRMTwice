/**
 * 
 */
package edu.zhku.service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.zhku.config.MyApplication;
import edu.zhku.pojo.ClassRoom;
import edu.zhku.pojo.SearchFCRVO;
import edu.zhku.pojo.SearchFSVO;
import edu.zhku.pojo.SearchVO;

/**  
* <p>Title: CRServiceTest.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年7月7日  
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MyApplication.class)
@WebAppConfiguration
public class CRServiceTest {

	@Autowired
	private CRService crService;
	/**
	 * Test method for {@link edu.zhku.service.CRService#getWeeks(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public void testGetWeeks() throws Exception {
		if(null != crService) {

			//			String s = crService.getWeeks("20171");
//			Set<String> j = crService.getJxl("白云校区");
//			List<String> r = crService.getRooms("白云校区", "杨钊杨勋楼");
//			List<String> t = crService.getTerms();
//			SearchVO svo = new SearchVO();
//			svo.setTerm("20171");
//			svo.setWeeks("1");
//			ClassRoom cr = new ClassRoom();
//			cr.setXq("白云校区");
//			cr.setJxl("杨钊杨勋楼");
//			cr.setKs("104");
//			svo.setCr(cr);
			
//			SearchFCRVO frVO = new SearchFCRVO();
//			ClassRoom cr = new ClassRoom();
//			cr.setXq("白云校区");
//			cr.setJxl("杨钊杨勋楼");
//			frVO.setCr(cr);
//			
//			frVO.setTerm("20171");
//			frVO.setWeeks("12");
//			frVO.setDay(3);
//			frVO.setBeginJie(10);
//			frVO.setEndJie(12);
//			
//			List<Set<Integer>> fs = crService.getFreeCR(frVO);
//			for(Set<Integer> floor : fs) {
//				for(Integer room : floor) {
//					System.out.print(room + "\t");
//				}
//				System.out.println();
//			}
//			for(int i=1; i<=5; ++i) {
//				for(Integer floor : fs) {
//					int bai = floor/100;
//					if(i == bai) {
//						System.out.print(floor + "\t");
//					}
//				}
//				System.out.println();
//			}
			
//			System.out.println(s);
			
			SearchFSVO fsvo = new SearchFSVO();
			
			fsvo.setTerm("20171");
			fsvo.setXq("白云校区");
			fsvo.setJxl("杨钊杨勋楼");
			fsvo.setKs("104");
			
			fsvo.setBeginWeek(1);
			fsvo.setEndWeek(7);
			fsvo.setBeginDay(1);
			fsvo.setEndDay(7);
			
			List<List<List<String>>> data = crService.getCRFreeSegment(fsvo);
			
			//System.out.println(data);
			
			int i = 1;
			//星期
			for(List<List<String>> days : data) {
				System.out.print("星期" + i + "\t");
				//周次
				for(List<String> weeks : days) {
					
					//节次
					for(String jies : weeks) {
						System.out.print(jies + "\t");
					}
					System.out.print("|");
					
				}
				
				++i;
				System.out.println();
			}
			
		}
	}

}
