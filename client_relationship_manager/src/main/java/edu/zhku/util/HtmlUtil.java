/**
 * 
 */
package edu.zhku.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.ResourceUtils;

/**  
* <p>Title: HtmlParser.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年6月17日  
*/
public class HtmlUtil {
	private static final String MODEL_HTML_PATH;
	private static final String BILL_HTML_PARENT_PATH;
	
	private static String css = "";
	private static String js = "";
	
	static {
		MODEL_HTML_PATH = PropertyUtil.getProperty("MODEL_HTML_PATH");
		
		BILL_HTML_PARENT_PATH = PropertyUtil.getProperty("BILL_HTML_PARENT_PATH");
		
		String cssPath = PropertyUtil.getProperty("css_filePath");
		String jsPath = PropertyUtil.getProperty("bill_jsFilePath");
		css = getContext(cssPath);
		js = getContext(jsPath);
	}
	
	
	public static String getContext(String path) {
		File file = null;
		try {
			file = ResourceUtils.getFile(path);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		StringBuilder builder = new StringBuilder("");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			int length = 0;
			byte[] buf = new byte[1024];
			while((length = fis.read(buf)) != -1) {
				builder.append(new String(buf, 0, length, "utf-8"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return builder.toString();
		
		
	}
	
	/**
	 * 对 /model/html/ 目录下的html文件中的内容进行加工
	 * @param htmlName	html的文件名，后缀名一定要为html
	 * @param action
	 * @param isModel
	 * @return	返回html形式，可以在网页上编辑的word文档
	 */
	public static String processHtml(String htmlName, String action, String href, boolean isModel) {
		String path = null;
		if (isModel) {
			path = MODEL_HTML_PATH + htmlName;
		}else {
			path = BILL_HTML_PARENT_PATH + htmlName;
		}
		File file = new File(path);
		String html = "";
		if(file.exists()) {
			try {
				//解析文件
				Document doc = Jsoup.parse(file, "UTF-8");
				
				//创建节点
				Element htm = doc.selectFirst("html");
				Element head = doc.selectFirst("head");
				Element body = doc.selectFirst("body");
				Element main = doc.createElement("div");
				
				//设置节点的属性
				main.attr("id", "div_bh");
				htm.attr("id", "main");
				
				//获取所有div标签
				Elements es = doc.getElementsByTag("div");
				
				//将原本的div追加到新的div中
				for(Element e : es) {
					
					//将所有div标签添加到main节点中
					e.appendTo(main);
				}
				
				Element form = getFormElement(doc, action, href);
				
				setHead(doc, head);
				main.appendTo(body);
				form.appendTo(body);
				
				//将文档转为字符串的html
				html = doc.html();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		return html;
	}
	
	
	
	/**
	 * 设置头部信息
	 * @param head
	 * @param head2 
	 */
	public static void setHead(Document doc, Element head) {
		// TODO Auto-generated method stub
		Element meta = doc.createElement("meta");
		
		//设置编码
		meta.attr("charset", "UTF-8");
		
		//添加meta标签
		meta.appendTo(head);
		
		//以html的形式添加css样式
		head.append(css);
		
		//添加js
		head.append(js);
		
	}


	/**
	 * 
	 * 创建表单
	 * @param doc
	 * @param action	form表单中aciton值
	 * @return		返回一个表单代码
	 * 
	 */
	public static Element getFormElement(Document doc, String action, String href) {
		
		//表单一些列标签
		Element form = doc.createElement("form");
		Element ta = doc.createElement("textarea");
		Element ta2 = doc.createElement("textarea");
		Element money = doc.createElement("input");
		Element cid = doc.createElement("input");
		Element input = doc.createElement("input");
		Element close = doc.createElement("a");
		Element downLoad = doc.createElement("a");
		
		//设置属性值
		form.attr("action", action);
		form.attr("method", "post");
		form.attr("onsubmit", "return getHtml();");
		
		ta.attr("id", "ta_bh");
		ta.attr("name", "bh");
		ta.attr("style", "display: none;");
		
		ta2.attr("id", "ta_htm");
		ta2.attr("name", "htm");
		ta2.attr("style", "display: none;");
		
		money.attr("type", "text");
		money.attr("placeholder", "金额");
		money.attr("name", "money");
		
		cid.attr("type", "text");
		cid.attr("placeholder", "客户id");
		cid.attr("name", "cid");
		
		input.attr("value", "提交");
		input.attr("type", "submit");
		
		close.attr("href", "javascript:window.opener=null;window.open('','_self');window.close();");
		close.text("关闭");
		
		downLoad.attr("href", href);
		downLoad.text("下载");
		
		//添加到form表单中
		ta.appendTo(form);
		ta2.appendTo(form);
		money.appendTo(form);
		cid.appendTo(form);
		input.appendTo(form);
		close.appendTo(form);
		downLoad.appendTo(form);
		
		//返回一个form表单节点
		return form;
	}
	
	/**
	 * 
	 * @param tags	没有html标签的html字符串，
	 * 	
	 * @return	正常的html
	 * 
	 */
	public static String addHtmlTag(String tags) {
		String model = "<html id='main'>%s</html>";
		
		String html = String.format(model, tags);
		
		return html;
	}
	
	/**
	 * 修改提交的订单的html文件
	 * @param sHtml	: 从更新界面传来的html，但是没有html标签
	 * @param	fileName : 没有后缀名
	 * 	form表单的action需要改变
	 * 	下载a标签的href需要修改成当前html的映射目录  虚拟目录 + 文件名 + 后缀名
	 * 	提交按钮的文字改为“修改”
	 * 
	 * @return
	 */
	public static String getUpdateHtml(String sHtml, String fileName) {
		
		String html = addHtmlTag(sHtml);
		
		Document doc = Jsoup.parse(html);
		
		Element form = doc.selectFirst("form");
		
		//获取第一个类型为submit的input标签
		Element submit = doc.selectFirst("input[type='submit']");
		
		//标签选择器，获取所有a标签
		Elements temp = doc.select("a");
		
		//移除2个input
		doc.select("input[name='money']").remove();
		doc.select("input[name='cid']").remove();
		
		//添加一个input，用于保存更新html的文件名
		Element inputFileName = doc.createElement("input");
		inputFileName.attr("value", fileName);
		inputFileName.attr("name", "fileName");
		inputFileName.attr("type", "text");
		inputFileName.attr("style","display: none;");
		inputFileName.appendTo(form);

		Element downLoad = null;
		String pSuf = "";
		for(Element e : temp) {
			if("下载".equals(e.text())) {
				downLoad = e ;
				String pHref = e.attr("href");
				pSuf = pHref.substring(pHref.indexOf("."));
			}
		}
		pSuf =".doc";
		String action = PropertyUtil.getProperty("UPDATE_BILL_WORD_ACTION");
		String href = PropertyUtil.getProperty("WEB_FOR_BILL_DOC") + fileName + pSuf;
		
		form.attr("action", action);
		submit.attr("value", "修改");
		downLoad.attr("href", href);
		
		return doc.html();
	}
	
	
	
	/**
	 * 
	 * @param fileName	不需要包含扩展名
	 * @param html
	 */
	public static void saveHtml(String fileName, String html) {
		String path = PropertyUtil.getProperty("BILL_HTML_PARENT_PATH") + fileName + ".html";
		
		File file = new File(path);
		
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream(file);
			
			fos.write(html.getBytes("utf-8"));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(null != fos) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	/**
	 * 
	 * @param fileName	不要扩展名
	 * @return
	 */
	public static String readHtml(String fileName) {
		
		String path = PropertyUtil.getProperty("BILL_HTML_PARENT_PATH") + fileName + ".html";
		
		StringBuilder builder = new StringBuilder("");
		
		File file = new File(path);
		
		if(file.exists()) {
			FileInputStream fis = null;
			
			try {
				fis = new FileInputStream(file);
				
				int length = 0;
				byte[] buf = new byte[1024];
				
				while((length = fis.read(buf)) != -1) {
					builder.append(new String(buf, 0, length, "utf-8"));
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return builder.toString();
	}
	
	public static String getSuf() {
		return ".html";
	}
	
	/**
	 * 
	 * @param fileName	这里的名称用订单名，没有后缀名
	 * @param sid
	 */
	public static void generateEvaHtml(String bid, String sid, String cid) {
		
		//先后去填充sid的的模板评价html
		String html = getEvaHtml(bid, sid, cid);
		
		//保存到zhku目录下的evalution
		saveEvaHtml(bid, html);
		
	}
	
	private static void saveEvaHtml(String fileName, String html) {
		
		//评价html的保存目录
		String path = PropertyUtil.getProperty("EVA_HTML_PATH") + fileName + getSuf();
		
		File file = new File(path);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			
			fos.write(html.getBytes("utf-8"));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @return
	 */
	private static String getEvaHtml(String bid, String sid, String cid) {
		// TODO Auto-generated method stub
		
		String path = PropertyUtil.getProperty("EVA_MODELFILE_PATH");
		
		File file = new File(path);
		Document doc = null;
		try {
			doc = Jsoup.parse(file, "UTF-8");
			
			Element binput = doc.getElementById("bid");
			binput.attr("value", bid);
			
			Element sinput = doc.getElementById("sid");
			sinput.attr("value", sid);
			
			Element cinput = doc.getElementById("cid");
			cinput.attr("value", cid);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return doc.html();
	}
	
	/**
	 * 
	 * @param parent
	 * @param fileName	无需后缀名，记得这是订单号作为html的文件名
	 */
	public static void removeFileHtml(String parent, String fileName) {
		fileName += getSuf();
		File file = new File(parent, fileName);
		
		if(file.exists()) {
			file.delete();
		}
	}

	public static void main(String[] args) {
		String s = processHtml("附件1 仲恺农业工程学院假期留校住宿申请表.html", "#", "#", false);
		System.out.println(s);
	}
	
	
}
