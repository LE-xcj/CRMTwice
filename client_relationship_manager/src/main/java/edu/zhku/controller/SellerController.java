/**
 * 
 */
package edu.zhku.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.RequestPartServletServerHttpRequest;
import org.springframework.web.servlet.ModelAndView;

import edu.zhku.mapper.SellerMapper;
import edu.zhku.pojo.Bill;
import edu.zhku.pojo.BillCondition;
import edu.zhku.pojo.BillVO;
import edu.zhku.pojo.Client;
import edu.zhku.pojo.ClientVO;
import edu.zhku.pojo.EVA;
import edu.zhku.pojo.EVAStatue;
import edu.zhku.pojo.EVAVO;
import edu.zhku.pojo.Seller;
import edu.zhku.pojo.State;
import edu.zhku.service.SellerService;
import edu.zhku.util.ExcelRead;
import edu.zhku.util.ExcelUtil;
import edu.zhku.util.PropertyUtil;
import edu.zhku.util.StatueUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * <p>
 * Title: SellerController.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author xcj
 * @date 2018年6月12日
 */
@Controller
@RequestMapping("/seller")
public class SellerController {

	@Autowired
	private SellerService sellerService;

	@RequestMapping("/canLogin")
	public void canLogin(HttpServletResponse response, Seller seller) throws Exception {

		boolean canLogin = sellerService.login(seller);

		JSONObject jo = new JSONObject();
		jo.put("action", "login");
		if (canLogin) {
			jo.put("flag", 1);
		} else {
			jo.put("flag", 0);
		}

		response.setContentType("text/json;charset=utf-8");
		response.getWriter().print(jo.toString());

	}

	@RequestMapping("/login")
	public String login(HttpSession session, Seller seller, Boolean remember) throws Exception {

		// 设置session
		session.setAttribute("sid", seller.getSid());

		System.out.println("this is remember" + remember);

		return "redirect:sellerBackground.action";
	}

	@RequestMapping("/regist")
	public String regist(HttpSession session, Seller seller) throws Exception {

		sellerService.regist(seller);

		// 设置session
		session.setAttribute("sid", seller.getSid());

		// 跳转到后台
		// response.sendRedirect("seller_background");

		return "redirect:sellerBackground.action";

	}

	@RequestMapping("/canRegist")
	public void canRegist(HttpServletResponse response, Seller seller) throws Exception {

		boolean canRegist = sellerService.canRegist(seller);

		JSONObject jo = new JSONObject();
		jo.put("action", "regist");

		// 不能注册，该销售人员id已经注册过
		if (!canRegist) {
			jo.put("flag", 1);
		} else {
			jo.put("flag", 0);
		}

		response.setContentType("text/json;charset=utf-8");
		response.getWriter().print(jo.toString());
	}

	@RequestMapping("/sellerBackground")
	public ModelAndView sellerBackground() {

		ModelAndView modelAndView = new ModelAndView();

		// 这样就可以不用设置jsp文件的全路径
		modelAndView.setViewName("seller/index");

		return modelAndView;
	}

	@RequestMapping("/fileModelUpload")
	@ResponseBody
	public State fileModelUpload(MultipartFile fileModel) throws IllegalStateException, IOException {
		State state = new State();
		state.setAction("upload");

		// 上传文件的原来名称（包括扩展名）
		String originalFileName = fileModel.getOriginalFilename();

		System.out.println("name :" + originalFileName);

		// 上传图片
		if (null != fileModel && null != originalFileName && originalFileName.length() > 0) {

			// 上传文件的存储路径
			String path = PropertyUtil.getProperty("DOC_MODEL_PARENT_PATH");

			System.out.println("path is " + path);

			String newFileName = originalFileName;

			// 文件
			File file = new File(path + newFileName);

			// 将上传文件存储到磁盘中
			fileModel.transferTo(file);

			state.setFlag(1);
		} else {
			state.setFlag(-1);
		}

		return state;
	}

	@RequestMapping("/billManage")
	public ModelAndView billManage(HttpSession session) throws Exception {

		String sid = (String) session.getAttribute("sid");

		ModelAndView modelAndView = new ModelAndView();

		// 获取模板的文件名
		String[] modelNames = sellerService.getModelName();

		// 从数据库中获取当前销售人员的所属的进行中订单
		List<Bill> pList = sellerService.getPrgBill(sid);

		// 完成的订单、拒绝的订单、 取消的订单
		List<Bill> cList = sellerService.getCompleteBill(sid);

		// 将数据设置到视图中
		modelAndView.addObject("modelNames", modelNames);
		modelAndView.addObject("pList", pList);
		modelAndView.addObject("cList", cList);

		modelAndView.setViewName("seller/bm");

		return modelAndView;
	}

	@RequestMapping("/createBill")
	public void createBill(HttpServletResponse response, String docName) throws Exception {

		// docName = new String(docName.getBytes("iso-8859-1"), "utf-8");

		String html = sellerService.getBillModelHtml(docName);

		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(html);
	}

	@RequestMapping("/generateBill")
	public void generateBill(HttpServletRequest request, HttpServletResponse response, String htm, String bh,
			float money, String cid) throws Exception {

		String sid = (String) request.getSession().getAttribute("sid");

		sellerService.createBill(sid, htm, bh, money, cid);

		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print("开单成功!");
	}

	@RequestMapping("/updateBillWord")
	public void updateBillWord(HttpServletResponse response, String htm, String bh, String fileName) throws Exception {

		// 更新订单，不是数据库
		sellerService.updateBill(htm, bh, fileName);

		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print("更新成功!");
	}

	@RequestMapping("/updateBillDB")
	public void updateBillDB(HttpServletResponse response, String bid, float money, int statue) throws Exception {

		sellerService.updateBill(bid, money, statue);

		response.sendRedirect("billManage.action");

	}

	@RequestMapping("/showExploit")
	public String showExploit() {

		return "forward:/jsp/seller/pexploit.jsp";
	}

	@RequestMapping("/getEVAStatue")
	@ResponseBody
	public List<EVAStatue> getEVAStatue(HttpSession session) throws Exception {

		String sid = (String) session.getAttribute("sid");
		Map<Integer, List<EVAVO>> map = sellerService.getSatisfaction(sid);

		List<EVAStatue> list = new ArrayList<>();
		List<EVAVO> evaluations = new ArrayList<>();

		// 遍历
		for (Integer level : map.keySet()) {

			// 获取每一个级别中的评价实体集合
			List<EVAVO> l = map.get(level);

			// 获取评价实体总的评价信息
			for (EVAVO eva : l) {
				evaluations.add(eva);
			}
			// 将评价的数量按照评价级别进行分类统计
			EVAStatue e = new EVAStatue(level, l.size());
			String title = StatueUtil.getStatuteTitle(level);
			e.setTitle(title);

			list.add(e);
		}

		session.setAttribute("evaluations", evaluations);
		return list;

	}

	@RequestMapping("/showEVAStatue")
	public ModelAndView showEVAStatue() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("seller/csf");
		return modelAndView;
	}

	@RequestMapping("/getSellStatue")
	public void getSellStatue(HttpSession session, HttpServletResponse response) throws Exception {
		BillCondition condition = new BillCondition();
		String sid = (String) session.getAttribute("sid");
		condition.setSid(sid);
		condition.setComplete(true);
		condition.setProgressing(true);
		condition.setUndeal(true);
		condition.setCancel(true);
		condition.setReject(true);
		List<Bill> bs = sellerService.getBill(condition);

		Map<String, Integer> count = new HashMap<>();

		for (Bill b : bs) {
			String statue = StatueUtil.getBillStatueTitle(b.getStatue());

			Integer num = count.get(statue);
			if (num == null) {
				num = new Integer(1);
			} else {
				num += 1;
			}
			count.put(statue, num);
		}

		JSONArray ts = new JSONArray();
		JSONArray nums = new JSONArray();

		for (String t : count.keySet()) {
			ts.add(t);
			nums.add(count.get(t));
		}

		JSONObject jo = new JSONObject();

		jo.put("name", ts);
		jo.put("value", nums);

		response.setContentType("text/json;charset=utf-8");
		response.getWriter().print(jo.toString());
	}

	@RequestMapping("/getPersonlExploitForYear")
	public void getPersonlExploitForYear(HttpServletResponse response, HttpSession session, int year) throws Exception {

		String sid = (String) session.getAttribute("sid");

		Map<String, List<Float>> m = sellerService.getPersonlExploitForYear(sid, year);

		JSONArray moneyPerMon = new JSONArray();
		JSONArray amountPerMon = new JSONArray();

		for (String month : m.keySet()) {

			JSONArray jaMon = new JSONArray();
			JSONArray jaAmount = new JSONArray();

			List<Float> l = m.get(month);

			jaMon.add(month);
			jaMon.add(l.get(0));

			jaAmount.add(month);
			jaAmount.add(l.get(1));

			moneyPerMon.add(jaMon);
			amountPerMon.add(jaAmount);
		}

		JSONObject jo = new JSONObject();
		jo.put("moneyPerMon", moneyPerMon);
		jo.put("amountPerMon", amountPerMon);

		response.setContentType("text/json;charset=utf-8");
		response.getWriter().print(jo.toString());
	}

	@RequestMapping("/ciEntry")
	public void ciEntry(HttpServletResponse response, MultipartFile fileModel) throws Exception {
		
		JSONObject jo = new JSONObject();
		// 判断文件是否为空
		if (fileModel == null) {
			jo.put("stuate", "empty");
		}else {
			String name = fileModel.getOriginalFilename();
			long size = fileModel.getSize();
			if (name == null || ExcelUtil.EMPTY.equals(name) && size == 0) {
				jo.put("statue", "fail");
			}else {
				int[] imfor = sellerService.petchImportClientImfor(fileModel);
				jo.put("statue", "success");
				jo.put("all", imfor[0]);
				jo.put("add", imfor[1]);
			}
			
		}

		response.setContentType("text/json;charset=utf-8");
		response.getWriter().print(jo.toString());
	}
	
	@RequestMapping("/showCS")
	public String showCS() {
		return "forward:/jsp/seller/clientList.jsp";
	}
	
	@RequestMapping("/showClientsByPage")
	@ResponseBody
	public List<Client> showClientsByPage(HttpSession session, int begin, int size) throws Exception {
		
		
		List<Client> cs = sellerService.showClients((begin-1)*size, size);
		
		//session.setAttribute("clientList", cs);
		
		return cs;
		
	}
	
	@RequestMapping("/searchClientByName")
	@ResponseBody
	public List<Client> searchClientByName(HttpSession session, String cname) throws Exception{
		
		List<Client> l = sellerService.searchClientByName(cname);
		
		return l;
		
	}
	
	@RequestMapping("/getTimeDistance")
	public void getTimeDistance(HttpServletResponse response, HttpSession session) throws Exception {
		
		String sid = (String) session.getAttribute("sid");
		
		int[] td = sellerService.getTimeDistance(sid);
		
		JSONObject jo = new JSONObject();
		
		jo.put("from", td[0]);
		jo.put("to", td[1]);
		
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().print(jo.toString());
	}
	
	@RequestMapping("clientCharaterAnalysis")
	public ModelAndView clientCharaterAnalysis(HttpSession session) throws Exception {
		
		String sid = (String) session.getAttribute("sid");
		
		ModelAndView mv = new ModelAndView();
		
		//查询价值客户
		int svn = sellerService.countValueClient(sid);
		
		//潜在客户的数量
		int pron = sellerService.countProClient();
		
		//获取价值客户
		List<ClientVO> vcs = sellerService.getValueClient(0, 5, sid);
		
		//获取潜在客户
		List<Client> pros = sellerService.getProClient(0, 5);
		
		mv.addObject("svn", svn);
		mv.addObject("pron", pron);
		mv.addObject("vcs",vcs);
		mv.addObject("pros", pros);
		
		mv.setViewName("seller/cca");
		
		return mv;
	}
	
	@RequestMapping("/getValueClients")
	@ResponseBody
	public List<ClientVO> getValueClients(HttpSession session,int begin, int size) throws Exception {
		
		String sid = (String) session.getAttribute("sid");
		
		List<ClientVO> vcs = sellerService.getValueClient((begin-1)*size, size, sid);
		
		return vcs;
	}
	
	@RequestMapping("/getProClients")
	@ResponseBody
	public List<Client> getProClients(int begin, int size) throws Exception {
		
		List<Client> pvs = sellerService.getProClient((begin-1)*size, size);
		
		return pvs;
	}
	
	@RequestMapping("importExcel")
	public ModelAndView importExcel(HttpSession session, String action, int begin, int size) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		List<? extends Client> list = null;
		
		int num = 0;
		
		if ("vc".equals(action)) {
			
			String sid = (String) session.getAttribute("sid");
			
			//查询价值客户
			num = sellerService.countValueClient(sid);
			
			list = sellerService.getValueClient((begin-1)*size, size, sid);
		}else {
			
			//潜在客户的数量
			num = sellerService.countProClient();
			
			list = sellerService.getProClient((begin-1)*size, size);
		}
		
		mv.addObject("action", action);
		mv.addObject("num", num);
		mv.addObject("importCS", list);
		mv.setViewName("seller/importExcel");
		return mv;
	}
	
	@RequestMapping("/getCompleteBill")
	@ResponseBody
	public List<BillVO> getCompleteBill(HttpSession session , int begin, int size, int year) throws Exception {
		
		String sid = (String) session.getAttribute("sid");
		
		List<BillVO> bvos = sellerService.getCompleteBill(sid, begin, size, year);
		
		return bvos;
	}
	
}
