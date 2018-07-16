/**
 * 
 */
package edu.zhku.pojo;

/**  
* <p>Title: EVA.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年6月21日  
*/
public class EVA {
	private Integer id;
	private String cid;
	private String sid;
	private String evaluation;
	private int level;
	private String etime;
	
	/**
	 * 
	 */
	public EVA() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getSid() {
		return sid;
	}
	public String getEvaluation() {
		return evaluation;
	}
	public int getLevel() {
		return level;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}
	public void setLevel(int level) {
		this.level = level;
	}



	public String getCid() {
		return cid;
	}



	public void setCid(String cid) {
		this.cid = cid;
	}



	public String getEtime() {
		return etime;
	}



	public void setEtime(String etime) {
		this.etime = etime;
	}
	
	
	
}
