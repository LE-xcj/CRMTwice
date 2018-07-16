/**
 * 
 */
package edu.zhku.util;

/**  
* <p>Title: StatueUtil.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年6月21日  
*/
public class StatueUtil {
	public static String getStatuteTitle(int level) {
		String title = null;
		switch(level) {
			case 1:
				title = "差评";
			break;
			case 2:
				title = "比较差";
			break;
			case 3:
				title = "还可以接受";
			break;
			case 4:
				title = "比较满意";
			break;
			case 5:
				title = "满意";
			break;
		}
		return title;
	}

	public static String getBillStatueTitle(int statue) {
		String title = null;
		switch(statue) {
			case -2:
				title = "取消";
			break;
			case -1:
				title = "拒绝";
			break;
			case 0:
				title = "未处理";
			break;
			case 1:
				title = "进行中";
			break;
			case 2:
				title = "完成";
			break;
		}
		return title;
	}
}
