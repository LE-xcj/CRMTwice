/**
 * 
 */
package edu.zhku.pojo;

/**  
* <p>Title: ClientVO.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年6月25日  
*/
public class ClientVO extends Client{
	
	//创造价值
	private float money;
	
	//单量
	private int num;
	
	/**
	 * 
	 */
	public ClientVO() {
		// TODO Auto-generated constructor stub
	}

	public float getMoney() {
		return money;
	}

	public int getNum() {
		return num;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	
	
}
