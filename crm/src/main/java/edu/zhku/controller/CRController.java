/**
 * 
 */
package edu.zhku.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.zhku.pojo.SearchFCRVO;
import edu.zhku.pojo.SearchFSVO;
import edu.zhku.pojo.SearchResultVO;
import edu.zhku.pojo.SearchVO;
import edu.zhku.service.CRService;

/**  
* <p>Title: CRController.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年7月7日  
*/
@RestController
@RequestMapping("/classroom")
public class CRController {
	
	@Autowired
	private CRService crService;
	
	@RequestMapping("/showCRUseState")
	public ModelAndView showCRUseState() throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		//学期
		List<String> terms = crService.getTerms();
		
		mv.addObject("terms", terms);
		mv.setViewName("crUseState");
		return mv;
	}
	
	@RequestMapping("/getJxl")
	public Set<String> getJxl(String xq) throws Exception{
		
		Set<String> jxl = crService.getJxl(xq);
		
		return jxl;
	}
	
	@RequestMapping("/getKs")
	public List<String> getKs(String xq, String jxl) throws Exception{
		
		List<String> ks = crService.getRooms(xq, jxl);
		
		return ks;
	}
	
	@RequestMapping("/getWeeks")
	public String getWeeks(String term) throws Exception {
		
		String weeks = crService.getWeeks(term);
		
		return weeks;
	}
	
	@RequestMapping("/getCRUseState")
	public List<List<SearchResultVO>> getCRUseState(SearchVO svo) throws Exception{
		
		List<List<SearchResultVO>> data = crService.getCRUseState(svo);
		
		return data;
	}
	
	@RequestMapping("/showFrc")
	public ModelAndView showFrc() throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		//学期
		List<String> terms = crService.getTerms();
		
		mv.addObject("terms", terms);
		mv.setViewName("fcrSearch");
		return mv;
	}
	
	@RequestMapping("/searchFCR")
	public List<Set<Integer>> searchFCR(SearchFCRVO frVO) throws Exception {
		
		List<Set<Integer>> data = crService.getFreeCR(frVO);
		
		return data;
	}
	
	@RequestMapping("/showCRFreeSegment")
	public ModelAndView showCRFreeSegment() throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		//学期
		List<String> terms = crService.getTerms();
		
		mv.addObject("terms", terms);
		mv.setViewName("fsegmentSearch");
		return mv;
	}
	
	@RequestMapping("/seachFreeSegment")
	public List<List<List<String>>> seachFreeSegment(SearchFSVO fsvo) throws Exception {
		
		List<List<List<String>>> data = crService.getCRFreeSegment(fsvo);
		
		return data;
	}
	
}
