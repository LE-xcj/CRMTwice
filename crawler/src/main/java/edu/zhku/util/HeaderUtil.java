/**
 * 
 */
package edu.zhku.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * <p>
 * Title: HeaderUtil.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author xcj
 * @date 2018年7月5日
 */
public class HeaderUtil {
    private static final String ORIGINURL = "http://jw.zhku.edu.cn/ZNPK/KBFB_RoomSel.aspx";
    
    public static Header[] getCookie(CloseableHttpClient hc) {
    	Header[] headers = null;
		
		HttpGet get = new HttpGet(ORIGINURL);
		CloseableHttpResponse response = null;
		try {
			response = hc.execute(get);
			if(200 == response.getStatusLine().getStatusCode()) {
				headers = response.getHeaders("Set-Cookie");
				
				for(Header h : headers) {
					System.out.println(h);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return headers;
    }
    
    /**
     * 获取Cookie
     * @param hc
     * @return
     */
    public static String getCookieValue(CloseableHttpClient hc) {
		String cookie = null;
		HttpGet get = new HttpGet(ORIGINURL);
		CloseableHttpResponse response = null;
		try {
			response = hc.execute(get);
			if(200 == response.getStatusLine().getStatusCode()) {
				Header[] headers = response.getHeaders("Set-Cookie");
				cookie = getCookie(headers);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return cookie;
    }

	/**
	 * 
	 * 处理headers中的Cookie值
	 * @param headers
	 * @return
	 */
	private static String getCookie(Header[] headers) {
		String first = headers[0].getValue();
		String second = headers[1].getValue();

		String sessionID = first.substring(0, first.indexOf(";"));
		String pre = second.substring(0, second.indexOf(";") + 1);
		String cookie = pre + sessionID;
		System.out.println(cookie);
		return cookie;
	}
    

}
