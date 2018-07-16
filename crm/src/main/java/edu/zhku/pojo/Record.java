/**
 * 
 */
package edu.zhku.pojo;

/**  
* <p>Title: Record.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年7月10日  
*/
public class Record extends SearchResultVO{
	private String term;
	private String xq;
	private String jxl;
	private String ks;
	
	/**
	 * 
	 */
	public Record() {
		// TODO Auto-generated constructor stub
	}

	public String getTerm() {
		return term;
	}

	public String getXq() {
		return xq;
	}

	public String getJxl() {
		return jxl;
	}

	public String getKs() {
		return ks;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public void setJxl(String jxl) {
		this.jxl = jxl;
	}

	public void setKs(String ks) {
		this.ks = ks;
	}
	
	
}
