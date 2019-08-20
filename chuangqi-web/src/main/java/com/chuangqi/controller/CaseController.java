/**
 * 
 */
package com.chuangqi.controller;

import com.chuangqi.bean.Paginer;
import com.chuangqi.bean.RequestBean;
import com.chuangqi.bean.ResultCode;
import com.chuangqi.common.constant.Constant;
import com.chuangqi.service.CaseCateService;
import com.chuangqi.service.CaseInfoService;
import com.chuangqi.vo.CaseCateVo;
import com.chuangqi.vo.CaseInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 留言控制类
 * @author wmk
 *
 */

@Slf4j
@RestController
@RequestMapping("/case")
public class CaseController extends BaseController{
	@Autowired
	private CaseInfoService caseInfoService;
	@Autowired
	private CaseCateService caseCateService;
	
	@RequestMapping("/caseInfolistUI")
	public ModelAndView CaseInfolistUI(){
		modelAndView("cases/caseInfolistUI");
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/caseInfolist")
	public void caseInfolist(){
		try{
			CaseInfoVo vo= new CaseInfoVo();
			vo.setWhereSql(getSearchRules());
			vo.setOrderBySql(" order by id desc ");
			Paginer<CaseInfoVo> paginer=getPaginer();
			paginer.setObj(vo);
			paginer=caseInfoService.getPaginer(paginer);
			List<CaseInfoVo> caseInfoVos=paginer.getEntityList();
			if(caseInfoVos!=null&&!caseInfoVos.isEmpty()){
				for (CaseInfoVo caseInfoVo:caseInfoVos) {
					caseInfoVo.setShowAfterImg(showResourceDomainUrl(caseInfoVo.getAfterImg()));
					caseInfoVo.setShowbeforeImg(showResourceDomainUrl(caseInfoVo.getBeforeImg()));
				}
			}
			outPage(paginer);
		}catch (Throwable e) {
			e.printStackTrace();
			log.error("查询列表错误", e);
		}
	}

}
