/**
 * 
 */
package edu.zhku.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.util.ResourceUtils;

import com.sun.mail.util.MailSSLSocketFactory;

import edu.zhku.pojo.BillVO;
import edu.zhku.pojo.Client;

/**
 * <p>
 * Title: EMailMaker.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author xcj
 * @date 2018年6月15日
 */
public class EMailMaker {
	//发件人地址
    public static String senderAddress = "3191758809@qq.com";
    
    //收件人地址
//    public static String recipientAddress = "865263907@qq.com";
    
    //发件人账户名
    public static String senderAccount = "3191758809";
    
    //发件人账户密码,这里我是使用qq，所以这里是授权码
    public static String senderPassword = "cdnqzylnomuodega";
    
    //1、连接邮件服务器的参数配置
    private static Properties props = null;
    
    static {
    	props = new Properties();
    	
        //设置用户的认证方式
        props.setProperty("mail.smtp.auth", "true");
        //设置传输协议
        props.setProperty("mail.transport.protocol", "smtp");
        //设置发件人的SMTP服务器地址
        props.setProperty("mail.smtp.host", "smtp.qq.com");
        
        //开启ssl加密， 如果没有会报错
        MailSSLSocketFactory sf = null;;
		try {
			sf = new MailSSLSocketFactory();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);
    }
    
    
    /**
     * 
     * @param recipientAddress	接收邮件的邮箱地址
     * @param title		邮件的标题
     * @param context	邮件的内容，
     * 					第一个值是msg模板的名称，包含文件扩展名
     * 					剩下的就是填充的信息参数
     * @throws Exception
     */
    public static void sendEMail(String recipientAddress, String title, List<String> context) throws Exception {
        
        //2、创建定义整个应用程序所需的环境信息的 Session 对象
        Session session = Session.getInstance(props);
        
        //设置调试信息在控制台打印出来
        session.setDebug(true);
        
        //3、创建邮件的实例对象
        Message msg = getMimeMessage(session, recipientAddress, title, context);
        
        //4、根据session对象获取邮件传输对象Transport
        Transport transport = session.getTransport();
        
        //设置发件人的账户名和密码
        transport.connect(senderAccount, senderPassword);
        
        //发送邮件，并发送到所有收件人地址，message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(msg,msg.getAllRecipients());
         
        //如果只想发送给指定的人，可以如下写法
        //transport.sendMessage(msg, new Address[]{new InternetAddress("xxx@qq.com")});
         
        //5、关闭邮件连接
        transport.close();
    }
     
    /**
     * 获得创建一封邮件的实例对象
     * @param session
     * @return
     * @throws MessagingException
     * @throws AddressException
     */
    public static MimeMessage getMimeMessage(Session session, String recipientAddress, String title, List<String> para) throws Exception{
    	
        //创建一封邮件的实例对象
        MimeMessage msg = new MimeMessage(session);
        
        //设置发件人地址
        msg.setFrom(new InternetAddress(senderAddress));
        
        /**
         * 设置收件人地址（可以增加多个收件人、抄送、密送），即下面这一行代码书写多行
         * MimeMessage.RecipientType.TO:发送
         * MimeMessage.RecipientType.CC：抄送
         * MimeMessage.RecipientType.BCC：密送
         */
        msg.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(recipientAddress));
        
        //设置邮件主题
        msg.setSubject(title,"UTF-8");
        
        //填充msg模板
        String context = fillContext(para);
        
        //设置邮件正文
        msg.setContent(context, "text/html;charset=UTF-8");
        
        //设置邮件的发送时间,默认立即发送
        msg.setSentDate(new Date());
         
        return msg;
    }
	
    /**
	 * @param para
	 * @return
	 */
	public static String fillContext(List<String> para) {
		// TODO Auto-generated method stub
		String context = "";
		if(null != para) {
			String path = para.remove(0);
			String model = getModel(path);
			String[] t = para.toArray(new String[para.size()]);
			context = String.format(model, t);
		}
		
		System.out.println(context);
		return context;
	}
	
	
	//模板路径vc vertify code
	private static String vcMsgPath = PropertyUtil.getProperty("vc_message_path");
	
	//读取模板
	public static String getModel(String path) {
		
		path = vcMsgPath + path;
		File file = null;
		FileInputStream fis = null;
		StringBuilder builder = new StringBuilder("");
		try {
			file = new File(path);
			fis = new FileInputStream(file);
			int length = 0;
			byte[] buf = new byte[1024];
			while((length = fis.read(buf)) != -1) {
				builder.append(new String(buf, 0, length));
			}
		} catch (FileNotFoundException e1) {
			
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

	public static void main(String[] args) throws Exception {
		
		List<String> list = new ArrayList<>();
		list.add("code.txt");
		list.add("1234656");
		sendEMail("865263907@qq.com", "我的第一个javamail", list);

    }
	
	public static void sendEMail(BillVO bvo, int billStatue) {
		
		//获取客户的邮箱地址
		String eMail = bvo.getClient().geteMail();
		String title = null;
		List<String> context = new ArrayList<>();
		
		
		//先获取邮件的模板
		if (billStatue == BillStatue.COMPLETE) {
			title = "完成订单";
			//模板名称
			context.add(PropertyUtil.getProperty("COMPLETE_MODEL_NAME"));
			context.add(bvo.getClient().getCname());
			context.add(bvo.getBid());
			context.add(bvo.getSeller().getSname());
			String href = PropertyUtil.getProperty("EVALUATION_HTML_HREF") + bvo.getBid() + ".html";
			context.add(href);
		} else if (billStatue == BillStatue.CANCEL) {
			title = "订单取消";
			context.add(PropertyUtil.getProperty("CANCEL_MODEL_NAME"));
			context.add(bvo.getClient().getCname());
			context.add(bvo.getBid());
			context.add(bvo.getSeller().getSname());
			context.add(bvo.getSeller().getPhone());
			context.add(bvo.getSeller().geteMail());
		} else {
			
		}
		try {
			sendEMail(eMail, title, context);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
