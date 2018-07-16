/**
 * 
 */
package edu.zhku.activity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * <p>
 * Title: ActivityCrawler.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author xcj
 * @date 2018年7月5日
 */
public class ActivityCrawler {
	private static String url = "http://jw.zhku.edu.cn/ZNPK/KBFB_LSHD_rpt.aspx";

	public static String getData() {
		String data = null;

		HttpPost post = new HttpPost(url);


		CloseableHttpResponse response = null;

		try {

			setParams(post);
			CloseableHttpClient hc = HttpClients.createDefault();
			response = hc.execute(post);

			if (200 == response.getStatusLine().getStatusCode()) {
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
	private static void setParams(HttpPost post) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("btnsea", "%BC%EC%CB%F7"));
		params.add(new BasicNameValuePair("input_hdmc", ""));
		params.add(new BasicNameValuePair("sel_hdlx", ""));
		params.add(new BasicNameValuePair("sel_jxl", "301"));
		params.add(new BasicNameValuePair("sel_room", "Nothing"));
		params.add(new BasicNameValuePair("sel_xnxq", "20180"));
		params.add(new BasicNameValuePair("sel_xq", "3"));
		params.add(new BasicNameValuePair("sel_yx", "Nothing"));
		params.add(new BasicNameValuePair("txtTimeBEG", ""));
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "UTF-8");

		post.setEntity(formEntity);
	}
	public static void main(String[] args) {
		getData();
	}
}
