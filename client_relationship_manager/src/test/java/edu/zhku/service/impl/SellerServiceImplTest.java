/**
 * 
 */
package edu.zhku.service.impl;

import junit.framework.TestCase;

/**  
* <p>Title: SellerServiceImplTest.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年6月18日  
*/
public class SellerServiceImplTest extends TestCase {

	/**
	 * Test method for {@link edu.zhku.service.impl.SellerServiceImpl#getModelName()}.
	 */
	public void testGetModelName() {
		SellerServiceImpl s = new SellerServiceImpl();
		
		try {
			s.getModelName();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
