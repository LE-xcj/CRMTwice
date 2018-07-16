/**
 * 
 */
package edu.zhku.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**  
* <p>Title: ClientServiceTest.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年6月21日  
*/
@RunWith(SpringJUnit4ClassRunner.class)    
@ContextConfiguration(locations={"classpath:spring/springDao-config.xml","classpath:spring/springService-config.xml","classpath:mybatis/mybatis-config.xml"}) 
public class ClientServiceTest extends AbstractJUnit4SpringContextTests  {

	@Autowired
	public ClientService cs;
	/**
	 * Test method for {@link edu.zhku.service.ClientService#evaluate(java.lang.String, java.lang.String, int)}.
	 * @throws Exception 
	 */
	@Test
	public void testEvaluate() throws Exception {
		if(cs != null) {
			
			cs.evaluate("201521314109", "不错", "865263907@qq.com", 5);
			
			System.out.println(" ok");
		}else {
			System.out.println("not ok");
		}
	}

}
