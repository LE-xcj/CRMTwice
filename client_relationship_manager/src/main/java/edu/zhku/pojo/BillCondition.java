/**
 * 
 */
package edu.zhku.pojo;

/**  
* <p>Title: BillCondition.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年6月20日  
*/
public class BillCondition extends Bill{
	
	//完成
	private boolean complete;
	
	//进行中
	private boolean progressing;
	
	//未处理
	private boolean undeal;
	
	//取消
	private boolean cancel;
	
	//拒绝
	private boolean reject;

	public boolean isComplete() {
		return complete;
	}

	public boolean isProgressing() {
		return progressing;
	}

	public boolean isUndeal() {
		return undeal;
	}

	public boolean isCancel() {
		return cancel;
	}

	public boolean isReject() {
		return reject;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public void setProgressing(boolean progressing) {
		this.progressing = progressing;
	}

	public void setUndeal(boolean undeal) {
		this.undeal = undeal;
	}

	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}

	public void setReject(boolean reject) {
		this.reject = reject;
	}

	
}
