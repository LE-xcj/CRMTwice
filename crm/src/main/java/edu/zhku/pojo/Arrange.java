/**
 * 
 */
package edu.zhku.pojo;

import edu.zhku.util.DayUtil;

/**  
* <p>Title: Arrange.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年7月9日  
*/
public class Arrange extends CRState implements Comparable<Arrange>{

	@Override
	public int compareTo(Arrange o) {
		// TODO Auto-generated method stub
		String sDay = this.getDay();
		String osDay = o.getDay();
		
		int day = DayUtil.getDay(sDay.substring(0, 1));
		int oday = DayUtil.getDay(osDay.substring(0, 1));
		
		//先按星期进行排序
		if(day > oday) {
			return 1;
		}else if(day < oday) {
			return -1;
		}else {
			//再按节次进行排序
			int beginJie = DayUtil.getBeginjie(sDay);
			int obeginJie = DayUtil.getBeginjie(osDay);
			
			if(beginJie > obeginJie) {
				return 1;
			}else if(beginJie < obeginJie) {
				return -1;
			}else {
				return 0;
			}
		}
	}

}
