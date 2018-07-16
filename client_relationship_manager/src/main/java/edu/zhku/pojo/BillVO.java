/**
 * 
 */
package edu.zhku.pojo;

/**  
* <p>Title: BillVO.java</p>  
* <p>Description:
* 	显示订单的详情，如：订单负责人和客户的名称等信息，单号
* </p>  
* 
* @author xcj
* @date 2018年6月20日  
*/
public class BillVO extends Bill{
	
	private Client client;
	
	private Seller seller;
	
	/**
	 * 
	 */
	public BillVO() {
		// TODO Auto-generated constructor stub
	}

	public Client getClient() {
		return client;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	
	
	
}
