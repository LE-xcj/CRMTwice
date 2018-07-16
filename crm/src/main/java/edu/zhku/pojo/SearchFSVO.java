/**
 * 
 */
package edu.zhku.pojo;

/**  
* <p>Title: SearchFSVO.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年7月9日  
*/
public class SearchFSVO extends ClassRoom{
	private String term;
	private int beginWeek;
	private int endWeek;
	private int beginDay;
	private int endDay;
	
	/**
	 * 
	 */
	public SearchFSVO() {
		// TODO Auto-generated constructor stub
	}

	public String getTerm() {
		return term;
	}

	public int getBeginWeek() {
		return beginWeek;
	}

	public int getEndWeek() {
		return endWeek;
	}

	public int getBeginDay() {
		return beginDay;
	}

	public int getEndDay() {
		return endDay;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public void setBeginWeek(int beginWeek) {
		this.beginWeek = beginWeek;
	}

	public void setEndWeek(int endWeek) {
		this.endWeek = endWeek;
	}

	public void setBeginDay(int beginDay) {
		this.beginDay = beginDay;
	}

	public void setEndDay(int endDay) {
		this.endDay = endDay;
	}
	
	
	
}
