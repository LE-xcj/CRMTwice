/**
 * 
 */
package edu.zhku.util;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**  
* <p>Title: HttpClientPoolUtil.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年7月5日  
*/
public class HttpClientPoolUtil {
	private static PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
	
	static {
		cm.setMaxTotal(200);
		
		cm.setDefaultMaxPerRoute(20);
		
	}
	
	public static CloseableHttpClient getHC() {
		CloseableHttpClient hc = HttpClients.custom().setConnectionManager(cm).build();
		
		return hc;
		
	}
}
