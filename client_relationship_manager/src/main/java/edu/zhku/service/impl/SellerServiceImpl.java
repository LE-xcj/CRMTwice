/**
 * 
 */
package edu.zhku.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.util.HSSFColor.TAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.sun.mail.util.PropUtil;

import edu.zhku.mapper.EVAMapper;
import edu.zhku.mapper.SellerMapper;
import edu.zhku.pojo.Bill;
import edu.zhku.pojo.BillCondition;
import edu.zhku.pojo.BillVO;
import edu.zhku.pojo.Client;
import edu.zhku.pojo.ClientVO;
import edu.zhku.pojo.EVA;
import edu.zhku.pojo.EVAVO;
import edu.zhku.pojo.Seller;
import edu.zhku.pojo.TimeDistance;
import edu.zhku.service.SellerService;
import edu.zhku.util.BillStatue;
import edu.zhku.util.DateUtil;
import edu.zhku.util.EMailMaker;
import edu.zhku.util.ExcelRead;
import edu.zhku.util.HtmlUtil;
import edu.zhku.util.IDGenerator;
import edu.zhku.util.POIUtil;
import edu.zhku.util.PropertyUtil;

/**  
* <p>Title: SellerImpl.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年6月12日  
*/

public class SellerServiceImpl implements SellerService{

	@Autowired
	private SellerMapper sellerMapper;
	
	@Autowired
	private EVAMapper evaMapper;
	


	/* (non-Javadoc)
	 * @see edu.zhku.service.SellerService#regist()
	 */
	@Override
	public void regist(Seller seller) throws Exception {
		// TODO Auto-generated method stub
		sellerMapper.regist(seller);
		
	}
	
	/* (non-Javadoc)
	 * @see edu.zhku.service.SellerService#canRegist(java.lang.String)
	 */
	@Override
	public boolean canRegist(Seller seller) throws Exception {
		// TODO Auto-generated method stub
		
		Seller s = findSeller(seller);
		//该销售人员已经存在
		if(null != s) {
			return false;
		}
		return true;
	}



	/* (non-Javadoc)
	 * @see edu.zhku.service.SellerService#findSeller(java.lang.String)
	 */
	@Override
	public Seller findSeller(Seller seller) throws Exception {
		// TODO Auto-generated method stub
		return sellerMapper.findSeller(seller);
	}



	/* (non-Javadoc)
	 * @see edu.zhku.service.SellerService#login(java.lang.String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public boolean login(Seller seller) throws Exception {
		// TODO Auto-generated method stub
		
		Seller s = sellerMapper.findSeller(seller);
		
		//id和密码不匹配
		if(null == s) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see edu.zhku.service.SellerService#getModelName()
	 */
	@Override
	public String[] getModelName() throws Exception {
		// TODO Auto-generated method stub
		
		String docPath = PropertyUtil.getProperty("DOC_MODEL_PARENT_PATH");
		
		File dir = new File(docPath);
		
		String[] names = dir.list();
		
		return names;
	}

	/* (non-Javadoc)
	 * @see edu.zhku.service.SellerService#getBillModelHtml(java.lang.String)
	 */
	@Override
	public String getBillModelHtml(String docName) throws Exception {
		// TODO Auto-generated method stub
		
		String type = docName.substring(docName.indexOf("."));
		String html = null;
		
		//将word文档转为html文件
		if(".doc".equals(type)) {
			POIUtil.docToHtml(docName, true);
		}else {
			POIUtil.docxToHtml(docName, true);
		}
		
		//然后读取生成的html文件，并加工得到可以编辑的html形式的word
		String htmlName = docName.substring(0, docName.indexOf(".")) + ".html";
		
		//配置表单提交的地址
		String action = PropertyUtil.getProperty("GENERATE_BILL_ACTION");
		
		//下载按钮指向的地址，已经配置了虚拟目录
		String ref = PropertyUtil.getProperty("VR_MODEL_DOC") + docName;
		
		//处理后的html字符串形式的 word文档
		html = HtmlUtil.processHtml(htmlName, action, ref, true);
		
		return html;
	}

	/* (non-Javadoc)
	 * @see edu.zhku.service.SellerService#createBill(java.lang.String, float, java.lang.String)
	 */
	@Override
	public void createBill(String sid, String htm, String bh, float money, String cid) throws Exception {

		String fileName = IDGenerator.generateBID(sid);
		
		//生成订单的word文档,不是在model目录
		POIUtil.htmlToWord(fileName, bh);
		
		//这里再生成一份html,放在bill目录下的html文件夹中，用于客户再次查看和修改
		String html = HtmlUtil.getUpdateHtml(htm, fileName);
		HtmlUtil.saveHtml(fileName, html);
		
		//创建bill对象
		Bill bill = new Bill();
		bill.setBid(fileName);
		bill.setSid(sid);
		bill.setCid(cid);
		bill.setMoney(money);
		bill.setStatue(1);
		bill.setCreatetime(IDGenerator.getDate());
		
		//向数据库进行操作
		sellerMapper.createBill(bill);
	}

	/* (non-Javadoc)
	 * @see edu.zhku.service.SellerService#getBill(edu.zhku.pojo.BillCondition)
	 */
	@Override
	public List<Bill> getBill(BillCondition condition) throws Exception {
		// TODO Auto-generated method stub
		
		List<Bill> bill = sellerMapper.getBill(condition);
		return bill;
	}

	/* (non-Javadoc)
	 * @see edu.zhku.service.SellerService#getPrgBill()
	 */
	@Override
	public List<Bill> getPrgBill(String sid) throws Exception {
		BillCondition condition = new BillCondition();
		condition.setSid(sid);
		condition.setProgressing(true);
		return sellerMapper.getBill(condition);
	}

	/* (non-Javadoc)
	 * @see edu.zhku.service.SellerService#getCompleteBill()
	 */
	@Override
	public List<Bill> getCompleteBill(String sid) throws Exception {
		// TODO Auto-generated method stub
		BillCondition condition = new BillCondition();
		condition.setSid(sid);
		condition.setCancel(true);
		condition.setReject(true);
		condition.setComplete(true);
		return sellerMapper.getBill(condition);
	}

	/* (non-Javadoc)
	 * @see edu.zhku.service.SellerService#updateBill(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void updateBill(String htm, String bh, String fileName) throws Exception {
		
		
		//更新订单的word文档,不是在model目录
		POIUtil.htmlToWord(fileName, bh);
		
		/**
		 * 更新html,放在bill目录下的html文件夹中，用于客户再次查看和修改
		 * 不过这里的html是更新使用的html，所以无需作“更新的处理”，直接添加html标签即可
		 * */
		String html = HtmlUtil.addHtmlTag(htm);
		HtmlUtil.saveHtml(fileName, html);
		
	}

	/* (non-Javadoc)
	 * @see edu.zhku.service.SellerService#updateBill(java.lang.String, float, int)
	 */
	@Override
	public void updateBill(String bid, float money, int statue) throws Exception {
		// TODO Auto-generated method stub
		
		Bill bill = new Bill();
		bill.setBid(bid);
		bill.setMoney(money);
		bill.setStatue(statue);
		
		//这里订单的状态发生改变，那么就需要发送e-mail通知客户
		if(statue == BillStatue.COMPLETE) {
			
			//首先从订单号，寻找到关联的客户id和销售人员，并返回一个billvo回来
			BillVO bvo = sellerMapper.getBillDetailByBid(bid);
			
			
			
			//这里要对客户进行判断，因为一个订单可能用户id是没有。那么就需要判断是否存在该客户，并且邮箱不能为空
			if(canSendEMail(bvo)) {
				
				//这里要生成一个用于评价的html
				HtmlUtil.generateEvaHtml(bid, bvo.getSid(), bvo.getCid());
			
				//向用户发送e-mail
				EMailMaker.sendEMail(bvo, statue);
			}
			
			
		}else if(statue == BillStatue.CANCEL) {
			
			BillVO bvo = sellerMapper.getBillDetailByBid(bid);
			
			if(canSendEMail(bvo)) {
				EMailMaker.sendEMail(bvo, statue);
			}
		}else {
			
		}
		
		//向数据库对订单进行更新
		sellerMapper.updateBill(bill);
		
		
	}
	
	private static boolean canSendEMail(BillVO bvo) {
		Client client = bvo.getClient();
		//这里要对客户进行判断，因为一个订单可能用户id是没有。那么就需要判断是否存在该客户，并且邮箱不能为空
		if(client != null && (client.geteMail() != null && !"".equals(client.geteMail()))) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.zhku.service.SellerService#getSatisfaction(java.lang.String)
	 */
	@Override
	public Map<Integer, List<EVAVO>> getSatisfaction(String sid) throws Exception {
		// TODO Auto-generated method stub
		List<EVAVO> list = evaMapper.getSatisfaction(sid);
		
		Map<Integer, List<EVAVO>> evas = new HashMap<>();
		
		for(EVAVO eva : list) {
			int level = eva.getLevel();
			List<EVAVO> t = evas.get(level);
			if(t == null) {
				t = new ArrayList<>();
				evas.put(level, t);
			}
			t.add(eva);
		}
		return evas;
	}

	/* (non-Javadoc)
	 * 
	 * @see edu.zhku.service.SellerService#getPersonlExploitForYear(java.lang.String)
	 *	这里key作为月份
	 *	value作为数值数据，用一个list集合来装
	 *	0 ： 当月的销售利润
	 *	1 ： 当月的销售量
	 */
	@Override
	public Map<String, List<Float>> getPersonlExploitForYear(String sid, int year) throws Exception {
		// TODO Auto-generated method stub
		int eYear = year + 1;
		List<Bill> bs = sellerMapper.getPersonlBillForYear(sid, year+"", eYear+"");
		
		 Map<String, List<Float>> map = new HashMap<>();
		for(Bill b : bs) {
			String month = DateUtil.getMonth(b.getCreatetime());
			
			List<Float> nums = map.get(month);
			if(nums == null) {
				nums = new ArrayList<>();
				
				nums.add(b.getMoney());
				nums.add(1F);
			}else {
				//当月的销售利润
				Float money = nums.get(0);
				//当月的单量
				Float amount = nums.get(1);
				
				money += b.getMoney();
				amount += 1F;
				
				nums.set(0, money);
				nums.set(1, amount);
			}
			map.put(month, nums);
		}
		
		return map;
	}

	/* (non-Javadoc)
	 * @see edu.zhku.service.SellerService#countClient()
	 */
	@Override
	public int countClient() throws Exception {
		// TODO Auto-generated method stub
		return sellerMapper.countClient();
	}

	/* (non-Javadoc)
	 * @see edu.zhku.service.SellerService#petchImportClientImfor(org.springframework.web.multipart.MultipartFile)
	 */
	@Override
	public int[] petchImportClientImfor(MultipartFile fileModel) throws Exception {
		// TODO Auto-generated method stub
		
		int affectRow = 0;
		int pnum = sellerMapper.countClient();
		
		// 读取Excel数据到List中
		List<ArrayList<String>> list = new ExcelRead().readExcel(fileModel);

		// list中存的就是excel中的数据，可以根据excel中每一列的值转换成你所需要的值（从0开始）
		Client client = null;
		List<Client> clients = new ArrayList<Client>();
		
		//遍历Excel表中的每一行数据， arr：代表一行， list代表所有行
		for (ArrayList<String> arr : list) {
			client = new Client();
			
			// 每一行的第一个单元格
			client.setCid(arr.get(0));	//账号
			client.setCname(arr.get(1));
			client.setPsw(arr.get(2));
			client.setSex(arr.get(3));
			client.setBirthday(arr.get(4));
			client.setJob(arr.get(5));
			client.setPhone(arr.get(6));
			client.setAddress(arr.get(7));
			client.seteMail(arr.get(8));
			
			//添加到clients集合中
			clients.add(client);
		}
		
		//批量插入或更新，有则更新，无则插入
		sellerMapper.petchImportClientImfor(clients);
		
		int nnum = sellerMapper.countClient();
		
		int[] r = new int[2];
		r[0] = clients.size();	//导入的客户数量
		r[1] = nnum - pnum;		//插入的客户数量
		//返回插入的记录数
		return r;
	}

	/* (non-Javadoc)
	 * @see edu.zhku.service.SellerService#showClients(int, int)
	 */
	@Override
	public List<Client> showClients(int begin, int size) throws Exception {
		// TODO Auto-generated method stub
		
		List<Client> cs = sellerMapper.showClientsForPage(begin, size);
		
		transformBirthday(cs);
				
		return cs;
	}

	/* (non-Javadoc)
	 * @see edu.zhku.service.SellerService#searchClientByName(java.lang.String)
	 */
	@Override
	public List<Client> searchClientByName(String cname) throws Exception {
		// TODO Auto-generated method stub
		return sellerMapper.searchClientByName(cname);
	}

	/* (non-Javadoc)
	 * @see edu.zhku.service.SellerService#getTimeDistance(java.lang.String)
	 */
	@Override
	public int[] getTimeDistance(String sid) throws Exception {
		// TODO Auto-generated method stub
		
		TimeDistance td = sellerMapper.getTimeDistance(sid);
		
		int previous = DateUtil.getYear(td.getMinT());
		int last = DateUtil.getYear(td.getMaxT());
		
		int[] t = new int[2];
		t[0] = previous;
		t[1] = last;
		
		return t;
	}

	/* (non-Javadoc)
	 * @see edu.zhku.service.SellerService#countValueClient(java.lang.String)
	 */
	@Override
	public int countValueClient(String sid) throws Exception {
		// TODO Auto-generated method stub
		return sellerMapper.countValueClient(sid);
	}

	/* (non-Javadoc)
	 * @see edu.zhku.service.SellerService#getValueClient(int, int, java.lang.String)
	 */
	@Override
	public List<ClientVO> getValueClient(int begin, int size, String sid) throws Exception {
		// TODO Auto-generated method stub
		List<ClientVO> cs = sellerMapper.getValueClient(begin, size, sid);
		transformBirthday(cs);
		return cs;
	}

	/* (non-Javadoc)
	 * @see edu.zhku.service.SellerService#getProClient(int, int)
	 */
	@Override
	public List<Client> getProClient(int begin, int size) throws Exception {
		// TODO Auto-generated method stub
		List<Client> cs = sellerMapper.getProClient(begin, size);
		transformBirthday(cs);
		return cs;
	}

	/* (non-Javadoc)
	 * @see edu.zhku.service.SellerService#countProClient()
	 */
	@Override
	public int countProClient() throws Exception {
		// TODO Auto-generated method stub
		return sellerMapper.countProClient();
	}

	
	private void transformBirthday(List<? extends Client> cs) {
		int now = DateUtil.getNowYear();
		
		for(Client c : cs) {
			
			int age = DateUtil.getAge(now, c.getBirthday());
			
			c.setBirthday(age+"");
		}
	}

	/* (non-Javadoc)
	 * @see edu.zhku.service.SellerService#getCompleteBill(java.lang.String, int, int)
	 */
	@Override
	public List<BillVO> getCompleteBill(String sid, int begin, int size, int year) throws Exception {
		// TODO Auto-generated method stub
		
		begin = (begin - 1 )*size;
		
		List<BillVO> bvos = sellerMapper.getCompleteBill(sid, begin, size, year);
		
		return bvos;
	}




}
