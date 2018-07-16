/**
 * 
 */
package edu.zhku.classCrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import edu.zhku.util.HeaderUtil;
import edu.zhku.util.HttpClientPoolUtil;

/**  
* <p>Title: ClassCrawler.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年7月5日  
*/
public class ClassCrawler {
	
	private static final String REFERER = "http://jw.zhku.edu.cn/ZNPK/KBFB_RoomSel.aspx";
	
    //异步请求获取课室使用情况的地址
    private static final String REQUESTURL = "http://jw.zhku.edu.cn/ZNPK/KBFB_RoomSel_rpt.aspx";
    
    //学期
    private static final String TERM = "Sel_XNXQ";
    
    //格式，使用格式2
    private static final String TYPE = "rad_gs";
    
    //验证码
    private static final String CORE =  "txt_yzm";
    
    //校区
    private static final String LOCATION = "Sel_XQ";
    
    //教学楼
    private static final String PLACE ="Sel_JX";
    
    //课室
    private static final String ROOM = "Sel_ROOM";
    
    public static String getData(CloseableHttpClient hc, String code, Header[] headers){
    	String data = null;
    	
    	HttpPost post = new HttpPost(REQUESTURL);
    	
    	
    	String first = headers[0].getValue();
		String second = headers[1].getValue();
		
		String sessionID = first.substring(0, first.indexOf(";"));
		String pre = second.substring(0, second.indexOf(";")+1);
		String cookie = pre+sessionID;
		System.out.println("c:" + cookie);
		
		post.setHeader("Referer", REFERER);
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		post.setHeader("Cookie", cookie);
		post.setHeader("User-Agen", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0");
		
    	CloseableHttpResponse response = null;
    	
    	try {
    		
    		setParams(post, code);
			response = hc.execute(post);
			
			if(200 == response.getStatusLine().getStatusCode()) {
				data = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	System.out.println(data);
    	
    	
        return data;
    }


	/**
	 * @param post
	 * @throws UnsupportedEncodingException 
	 */
	private static void setParams(HttpPost post, String code) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair(TERM, "20171"));
		params.add(new BasicNameValuePair(TYPE, "2"));
		params.add(new BasicNameValuePair(CORE, code));
		params.add(new BasicNameValuePair(LOCATION, "3"));
		params.add(new BasicNameValuePair(PLACE, "301"));
		params.add(new BasicNameValuePair(ROOM, "301210"));
	
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params);
		
		post.setEntity(formEntity);
	}
    
}
