/**
 * 
 */
package edu.zhku.pojo;

/**  
* <p>Title: Term.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年7月7日  
*/
public class Term {
	private Integer id;
	private String term;
	private String begin;
	private String end;
	private String weeks;
	
	
	/**
	 * 
	 */
	public Term() {
		// TODO Auto-generated constructor stub
	}

	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTerm() {
		return term;
	}

	public String getBegin() {
		return begin;
	}

	public String getEnd() {
		return end;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getWeeks() {
		return weeks;
	}

	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}
	
	
}
