/**
 * 
 */
package edu.zhku.pojo;

/**  
* <p>Title: SearchFCRVO.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年7月8日  
*/
public class SearchFCRVO extends SearchVO{
	private Integer day;
	private Integer beginJie;
	private Integer endJie;
	
	/**
	 * 
	 */
	public SearchFCRVO() {
		// TODO Auto-generated constructor stub
	}


	public Integer getDay() {
		return day;
	}

	public Integer getBeginJie() {
		return beginJie;
	}

	public Integer getEndJie() {
		return endJie;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public void setBeginJie(Integer beginJie) {
		this.beginJie = beginJie;
	}

	public void setEndJie(Integer endJie) {
		this.endJie = endJie;
	}
	
	
	
}
