/**
 * 
 */
package edu.zhku.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.zhku.util.CodeMaker;
import edu.zhku.util.EMailMaker;
import edu.zhku.util.RandomUtil;

/**  
* <p>Title: VertifyCodeController.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年6月15日  
*/

@Controller
@RequestMapping("/vertifyCode")
public class VertifyCodeController {
	
	@RequestMapping("/setNormalVC")
	public void setNormalVC(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, BufferedImage> code = CodeMaker.getCode();
		
		for(String tCode : code.keySet()) {
			System.out.println("the code is " + tCode);
			HttpSession session = request.getSession();
			session.setAttribute("normalVC", tCode);
			BufferedImage img = code.get(tCode);
			ImageIO.write(img, "jpg", response.getOutputStream());
		}
	}
	
	@RequestMapping("/setEMailVC")
	public void setEMailVC(HttpServletRequest request, HttpServletResponse response, String cid) throws Exception {
		
		String tCode = RandomUtil.generateNumber(5);
		HttpSession session = request.getSession();
		session.setAttribute("eMailCode", tCode);
		
		List<String> context = new ArrayList<>();
		context.add("code.txt");
		context.add(tCode);
		EMailMaker.sendEMail(cid, "注册验证码", context);
	}
	
}
