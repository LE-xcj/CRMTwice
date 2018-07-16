/**
 * 
 */
package edu.zhku.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * <p>
 * Title: Crawler.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author xcj
 * @date 2018年7月6日
 */
public class Crawler {

	//学期
	private static final String TERM = "Sel_XNXQ";
	
	//格式
	private static final String TYPE = "rad_gs";
	
	//验证码
	private static final String CODE = "txt_yzm";
	
	//校区
	private static final String LOCATION = "Sel_XQ";
	
	//教学楼
	private static final String PLACE = "Sel_JXL";
	
	//课室
	private static final String ROOM = "Sel_ROOM";

	private static String selTerm = "20171";
	
	private static String selType = "2";
	
	private static String selLocation = "3";
	
	private static String selPlace = "301";

	private static final String REQUESTURL = "http://jw.zhku.edu.cn/ZNPK/KBFB_RoomSel_rpt.aspx";

	public static String getData(String cookie, String code, String selRoom) {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader bfr = null;

		StringBuffer buffer = null;
		HttpURLConnection c = null;

		try {
			URL url = new URL(REQUESTURL);
			c = (HttpURLConnection) url.openConnection();
			c.setConnectTimeout(10000);
			c.setDoInput(true);
			c.setDoOutput(true);
			c.setRequestMethod("POST");

			c.setRequestProperty("Referer", "http://jw.zhku.edu.cn/ZNPK/KBFB_RoomSel.aspx");
			c.setRequestProperty("Cookie", cookie);

			System.out.println(code);
			String data = TERM + "=" + selTerm + "&" + 
						  TYPE + "=" + selType + "&" + 
						  CODE + "=" + code + "&" + 
						  LOCATION + "=" + selLocation + "&" + 
						  PLACE + "=" + selPlace + "&" + 
						  ROOM + "=" + selPlace + selRoom;
			System.out.println(data);
			c.getOutputStream().write(data.getBytes());

			c.connect();

			is = c.getInputStream();
			isr = new InputStreamReader(is, "gb2312");
			bfr = new BufferedReader(isr);

			String line = "";
			buffer = new StringBuffer();
			while ((line = bfr.readLine()) != null) {
				buffer.append(line);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close(bfr, isr, is, c);

		return buffer.toString();
	}

	/**
	 * @param bfr
	 * @param isr
	 * @param is
	 * @param c
	 */
	private static void close(BufferedReader bfr, InputStreamReader isr, InputStream is, HttpURLConnection c) {
		try {
			if (null != bfr) {
				bfr.close();
			}

			if (null != isr) {
				isr.close();
			}

			if (null != is) {
				is.close();
			}

			if (null != c) {
				c.disconnect();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
