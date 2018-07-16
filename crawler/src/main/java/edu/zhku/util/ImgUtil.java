/**
 * 
 */
package edu.zhku.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * <p>
 * Title: ImgUtil.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author xcj
 * @date 2018年7月5日
 */
public class ImgUtil {

	// 验证码的请求地址
	private static final String IMGURL = "http://jw.zhku.edu.cn/sys/ValidateCode.aspx";

	// 引用页地址
	private static final String REFERER = "http://jw.zhku.edu.cn/ZNPK/KBFB_RoomSel.aspx";

	public static void saveImag(Header[] headers, CloseableHttpClient hc) {
		String imgName = "code/img.jpg";

		HttpGet get = new HttpGet(IMGURL);

		String first = headers[0].getValue();
		String second = headers[1].getValue();

		String sessionID = first.substring(0, first.indexOf(";"));
		String pre = second.substring(0, second.indexOf(";") + 1);
		String cookie = pre + sessionID;
		System.out.println(cookie);
		
		
		get.setHeader("Referer", REFERER);
		get.setHeader("Cookie", cookie);

		FileOutputStream fos = null;
		CloseableHttpResponse response = null;
		try {

			response = hc.execute(get);
			if (200 == response.getStatusLine().getStatusCode()) {
				byte[] buf = EntityUtils.toByteArray(response.getEntity());

				File file = new File(imgName);
				fos = new FileOutputStream(file);
				fos.write(buf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * 获取src指向的地址值，然后下载验证码到本地
	 */
	public static void saveImag(Header[] headers) {
		String imgName = "code/img.jpg";

		File file = new File(imgName);
		InputStream is = null;
		FileOutputStream fos = null;

		String first = headers[0].getValue();
		String second = headers[1].getValue();

		String sessionID = first.substring(0, first.indexOf(";"));
		String pre = second.substring(0, second.indexOf(";") + 1);
		String cookie = pre + sessionID;
		System.out.println(cookie);
		try {
			URL url = new URL(IMGURL);
			HttpURLConnection c = (HttpURLConnection) url.openConnection();

			c.setRequestProperty("Referer", "http://jw.zhku.edu.cn/ZNPK/KBFB_RoomSel.aspx");
			c.setRequestProperty("Cookie", cookie);

			c.connect();
			is = c.getInputStream();
			// is = url.openStream();
			fos = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int length = 0;

			while ((length = is.read(buffer)) != -1) {
				fos.write(buffer, 0, length);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (null != fos) {
					fos.flush();
					fos.close();
				}
				if (null != is)
					is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	/*
	 * 获取src指向的地址值，然后下载验证码到本地
	 */
	public static void saveImag(String cookie, String imgName) {

		File file = new File(imgName);
		InputStream is = null;
		FileOutputStream fos = null;

		System.out.println(cookie);
		try {
			URL url = new URL(IMGURL);
			HttpURLConnection c = (HttpURLConnection) url.openConnection();

			c.setRequestProperty("Referer", REFERER);
			c.setRequestProperty("Cookie", cookie);

			c.connect();
			is = c.getInputStream();
			fos = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int length = 0;

			while ((length = is.read(buffer)) != -1) {
				fos.write(buffer, 0, length);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		close(fos, is);
	}

	/**
	 * @param fos
	 * @param is
	 */
	private static void close(FileOutputStream fos, InputStream is) {
		try {
			if (null != fos) {
				fos.flush();
				fos.close();
			}
			if (null != is)
				is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
