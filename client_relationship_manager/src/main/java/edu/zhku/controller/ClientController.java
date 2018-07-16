/**
 * 
 */
package edu.zhku.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.zhku.pojo.Client;
import edu.zhku.pojo.State;
import edu.zhku.service.ClientService;
import edu.zhku.util.HtmlUtil;
import edu.zhku.util.PropertyUtil;

/**  
* <p>Title: ClientController.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年6月15日  
*/

@Controller
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@RequestMapping("/login")
	@ResponseBody
	public State login(HttpSession session, Client client, String vertifyCode) throws Exception {
		
		String tCode = (String) session.getAttribute("normalVC");
		System.out.println(tCode + "   " + vertifyCode);
		
		State state = new State();
		state.setAction("login");
		
		//先判断验证码是否正确
		if(null == vertifyCode || !vertifyCode.equals(tCode)) {
			state.setFlag(-1);
		}else {
			
			//再判断是否可以登录
			boolean canLogin = clientService.canLogin(client);

			if(canLogin) {
				//设置session
				session.setAttribute("cid", client.getCid());
				state.setFlag(1);
			}else {
				state.setFlag(0);
			}
		}
		return state;
	}
	
	@RequestMapping("/regist")
	@ResponseBody
	public State regist(HttpSession session, Client client, String vertifyCode) throws Exception {
		
		String eMailCode = (String) session.getAttribute("eMailCode");
		System.out.println("this eMailCode" + eMailCode);
		
		State state = new State();
		state.setAction("regist");
		
		//验证码不正确
		if(null == vertifyCode || !vertifyCode.equals(eMailCode)) {
			state.setFlag(-1);
		}else {
			
			//再判断是否存在该客户
			boolean canRegist = clientService.canRegist(client);
			if(canRegist) {
				//设置session
				session.setAttribute("cid", client.getCid());
				clientService.regist(client);
				state.setFlag(1);
			}else {
				state.setFlag(0);
			}
		}
		return state;
	}
	
	@RequestMapping("/clientLR")
	public ModelAndView clientLR() {
		ModelAndView modelAndView = new ModelAndView();
		
        //这样就可以不用设置jsp文件的全路径
        modelAndView.setViewName("client/CLR");
        
		return modelAndView;
	}
	
	@RequestMapping("/clientBackground")
	public ModelAndView sellerBackground() {
		
		ModelAndView modelAndView = new ModelAndView();
		
        //这样就可以不用设置jsp文件的全路径
        modelAndView.setViewName("client/client_background");
        
		return modelAndView;
	}
	
	@RequestMapping("/evaluate")
	public void evaluate(HttpServletResponse response, String sid, String bid, String evaluation, String cid, int level) throws Exception {
		
		//评价，向数据库插入评价数据
		clientService.evaluate(sid, evaluation, cid, level);
		
		//移除该订单的评价html，也就是每一个订单只有一次的提交评价机会
		String parent = PropertyUtil.getProperty("EVA_HTML_PATH");
		HtmlUtil.removeFileHtml(parent, bid);
		
		response.setContentType("text/html;charset=utf-8");
    	response.getWriter().print("感谢您的评价!");
	}
	
	@RequestMapping("/updateImfor")
	public void updateImfor(HttpServletResponse response, HttpSession session, Client client, String qu) throws Exception {
		
		String cid = (String) session.getAttribute("cid");
		client.setCid(cid);
		
		String address = client.getAddress() + "-" + qu;
		client.setAddress(address);
		
		clientService.updateClient(client);
		
		session.setAttribute("hasUpdate", "yes");
		
		response.sendRedirect("showClient.action");
		
	}
	
	@RequestMapping("/showClient")
	public ModelAndView showClient(HttpSession session) throws Exception {
		
		String cid = (String) session.getAttribute("cid");
		Client client = new Client();
		client.setCid(cid);
		
		Client c = clientService.findClient(client);
	
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("client", c);
		
		
		mav.setViewName("client/cis");
		return mav;
		
	}
	
}
