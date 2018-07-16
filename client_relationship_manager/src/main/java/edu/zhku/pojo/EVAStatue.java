/**
 * 
 */
package edu.zhku.pojo;

/**  
* <p>Title: EVAStatue.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年6月21日  
*/
public class EVAStatue {
	private String title;
	private int level;
	private int amount;
	
	/**
	 * 
	 */
	public EVAStatue() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public EVAStatue(int level, int amount) {
		super();
		this.level = level;
		this.amount = amount;
	}


	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public int getLevel() {
		return level;
	}
	public int getAmount() {
		return amount;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
