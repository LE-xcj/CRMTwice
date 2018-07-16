/**
 * 
 */
package edu.zhku.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.XWPFConverterException;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * <p>
 * Title: POIUtil.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author xcj
 * @date 2018年6月17日
 */
public class POIUtil {

	private static final String DOC_MODEL_PARENT_PATH;
	private static final String TARGET_HTML_PARENT_PATH;
	private static final String IMG_PARENT_PATH;

	private static final String BILL_DOC_PARENT_PATH;
	private static final String BILL_HTML_PARENT_PATH;
	private static final String BILL_IMG_PATH;

	private static final String MODE_HTML_FILE;

	//Word和html文件的存储路径
	static {
		DOC_MODEL_PARENT_PATH = PropertyUtil.getProperty("DOC_MODEL_PARENT_PATH");
		TARGET_HTML_PARENT_PATH = PropertyUtil.getProperty("TARGET_HTML_PARENT_PATH");
		IMG_PARENT_PATH = PropertyUtil.getProperty("IMG_PARENT_PATH");

		BILL_DOC_PARENT_PATH = PropertyUtil.getProperty("BILL_DOC_PARENT_PATH");
		BILL_HTML_PARENT_PATH = PropertyUtil.getProperty("BILL_HTML_PARENT_PATH");
		BILL_IMG_PATH = PropertyUtil.getProperty("BILL_IMG_PATH");

		MODE_HTML_FILE = PropertyUtil.getProperty("MODE_HTML_FILE");

	}

	/**
	 * doc类型的Word转为html文件
	 * @param fileName	包括扩展名
	 * @param isModel	是否模型文件
	 */
	public static void docToHtml(String fileName, boolean isModel) {
		String docPath = null;
		String htmlPath = null;
		String imagePath;

		String htmlName = getSuf(fileName, ".html");
		
		//设置文件路径
		if (isModel) {
			docPath = DOC_MODEL_PARENT_PATH + fileName;
			htmlPath = TARGET_HTML_PARENT_PATH + htmlName;
			imagePath = IMG_PARENT_PATH;
		} else {
			docPath = BILL_DOC_PARENT_PATH + fileName;
			htmlPath = BILL_HTML_PARENT_PATH + htmlName;
			imagePath = BILL_IMG_PATH;
		}

		HWPFDocument wordDocument = null;
		Document document = null;

		try {

			// 将word文档以一种数据结构进行存储在程序中
			wordDocument = new HWPFDocument(new FileInputStream(docPath));

			// 创建文档，用于建立word对应的html文件
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

		} catch (ParserConfigurationException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println("is not a doc");
			e.printStackTrace();
		}

		// word -> html 转换器, document作为载体
		WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(document);

		// 保存图片，并返回图片的相对路径
		wordToHtmlConverter.setPicturesManager((content, pictureType, name, width, height) -> {
			try (FileOutputStream out = new FileOutputStream(imagePath + name)) {
				out.write(content);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "image/" + name;
		});

		// 将word转为html文档
		wordToHtmlConverter.processDocument(wordDocument);

		// 获取文档
		Document htmlDocument = wordToHtmlConverter.getDocument();

		DOMSource domSource = new DOMSource(htmlDocument);
		StreamResult streamResult = new StreamResult(new File(htmlPath));
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer serializer;
		try {
			serializer = tf.newTransformer();
			serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
			serializer.setOutputProperty(OutputKeys.INDENT, "yes");
			serializer.setOutputProperty(OutputKeys.METHOD, "html");
			serializer.transform(domSource, streamResult);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/***
	 * docx类型的Word转为html
	 * @param fileName	文件名，包括扩展名
	 * @param isModel
	 */
	public static void docxToHtml(String fileName, boolean isModel) {
		String docPath = null;
		String htmlPath = null;
		String imagePath;

		String htmlName = getSuf(fileName, ".html");

		if (isModel) {
			docPath = DOC_MODEL_PARENT_PATH + fileName;
			htmlPath = TARGET_HTML_PARENT_PATH + htmlName;
			imagePath = IMG_PARENT_PATH;
		} else {
			docPath = BILL_DOC_PARENT_PATH + fileName;
			htmlPath = BILL_HTML_PARENT_PATH + htmlName;
			imagePath = BILL_IMG_PATH;
		}

		OutputStreamWriter outputStreamWriter = null;
		try {

			//以特定的数据结构对读取的内容进行存储
			XWPFDocument document = new XWPFDocument(new FileInputStream(docPath));
			XHTMLOptions options = XHTMLOptions.create();

			// 存放图片的文件夹
			options.setExtractor(new FileImageExtractor(new File(imagePath)));

			// html中图片的路径
			options.URIResolver(new BasicURIResolver("image"));

			outputStreamWriter = new OutputStreamWriter(new FileOutputStream(htmlPath), "utf-8");

			XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();

			//通过docx的转换器将docx类型的Word转为html文件
			xhtmlConverter.convert(document, outputStreamWriter, options);
		} catch (XWPFConverterException | IOException e) {
			
		} finally {
			if (outputStreamWriter != null) {
				try {
					outputStreamWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public static String getSuf(String fileName, String suf) {
		String newName = "";
		if (fileName != null) {
			int index = fileName.lastIndexOf(".");

			newName = fileName.substring(0, index) + suf;
		}

		return newName;
	}

	public static String getSuf() {
		return ".doc";
	}

	/**
	 * 
	 * @param fileName
	 *            没有后缀
	 * @param div
	 */
	public static void htmlToWord(String fileName, String div) {
		String path = BILL_DOC_PARENT_PATH + fileName + getSuf();

		try {

			InputStream modelIs = new FileInputStream(MODE_HTML_FILE);

			String html = getContent(modelIs);

			html = String.format(html, div);

			System.out.println(html);

			InputStream is = new ByteArrayInputStream(html.getBytes("UTF-8"));
			OutputStream os = new FileOutputStream(path);
			inputStreamToWord(is, os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 把is写入到对应的word输出流os中 不考虑异常的捕获，直接抛出
	 * 
	 * @param is
	 * @param os
	 * @throws IOException
	 */
	private static void inputStreamToWord(InputStream is, OutputStream os) throws IOException {

		// poi的文件系统
		POIFSFileSystem fs = new POIFSFileSystem();
		// 对应于org.apache.poi.hdf.extractor.WordDocument

		fs.createDocument(is, "WordDocument");
		fs.writeFilesystem(os);
		os.close();
		is.close();
	}

	/**
	 * 把输入流里面的内容以UTF-8编码当文本取出。 不考虑异常，直接抛出
	 * 
	 * @param ises
	 * @return
	 * @throws IOException
	 */
	public static String getContent(InputStream... ises) throws IOException {
		if (ises != null) {
			StringBuilder result = new StringBuilder();
			BufferedReader br;
			String line;
			for (InputStream is : ises) {
				br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				while ((line = br.readLine()) != null) {
					result.append(line);
				}
			}
			return result.toString();
		}
		return null;
	}

	/**
	 * html文件转为word，目前只能针对doc类型
	 * @param fileName
	 */
	public static void htmlToWord(String fileName) {
		String path = BILL_DOC_PARENT_PATH + fileName + getSuf();
		String hpath = "D:\\eclipse workplaces\\zhku\\model\\html\\" + fileName + ".html";
		try {

			InputStream modelIs = new FileInputStream(hpath);

			String html = getContent(modelIs);

			System.out.println(html);

			InputStream is = new ByteArrayInputStream(html.getBytes("UTF-8"));
			OutputStream os = new FileOutputStream(path);
			inputStreamToWord(is, os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		docxToHtml("仲恺农业工程学院假期留校住宿申请表.docx", true);
		//htmlToWord("2015");
		//docToHtml("2015.doc", false);
	}

}
