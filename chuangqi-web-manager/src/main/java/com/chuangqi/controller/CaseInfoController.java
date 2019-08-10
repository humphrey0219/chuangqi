/**
 * 
 */
package com.chuangqi.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.chuangqi.bean.Paginer;
import com.chuangqi.bean.RequestBean;
import com.chuangqi.bean.ResultCode;
import com.chuangqi.common.constant.Constant;
import com.chuangqi.service.CaseCateService;
import com.chuangqi.service.CaseInfoService;
import com.chuangqi.vo.CaseCateVo;
import com.chuangqi.vo.CaseInfoVo;

/**
 * 留言控制类
 * @author wmk
 *
 */

@Slf4j
@RestController
@RequestMapping("/case")
public class CaseInfoController extends BaseController{
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
	
	//添加数据页面
	@RequestMapping("/addCaseInfoUI")
	public ModelAndView addCaseInfoUI(){
		CaseCateVo caseCateVo=new CaseCateVo();
		caseCateVo.setStatus(Constant.STATUS_1);
		List<CaseCateVo> caseCateVos=caseCateService.getList(caseCateVo);
		return modelAndView("cases/addCaseInfoUI").addObject("caseCateVos", caseCateVos);
	}
	
	//添加数据
	@RequestMapping("/addCaseInfo")
	public void addCaseInfo(CaseInfoVo vo){
		ResultCode resultCode=ResultCode.newSuccess();
		try{
			Long rt=caseInfoService.add(vo);
			if(rt==null||rt<=0){
				resultCode.setFail("添加失败,请联系技术人员");
			}
		}catch (Throwable e) {
			e.printStackTrace();
			log.error("添加异常,data={},异常信息={}",vo, e);
			resultCode.setFail("添加异常,请联系技术人员");
		}
		resWriteObjectJson(resultCode);
	}
	
	//修改数据页面
	@RequestMapping("/updCaseInfoUI")
	public ModelAndView updCaseInfoUI(CaseInfoVo vo){
			vo=caseInfoService.get(vo);
			vo.setShowAfterImg(showResourceDomainUrl(vo.getAfterImg()));
			vo.setShowbeforeImg(showResourceDomainUrl(vo.getBeforeImg()));
			modelAndView("cases/updCaseInfoUI");
			modelAndView.addObject("vo", vo);
			
			CaseCateVo caseCateVo=new CaseCateVo();
			caseCateVo.setStatus(Constant.STATUS_1);
			List<CaseCateVo> caseCateVos=caseCateService.getList(caseCateVo);
			
			modelAndView.addObject("caseCateVos", caseCateVos);
			return modelAndView;
	}
		
	//修改数据
	@RequestMapping("/updCaseInfo")
	public void updCaseInfo(CaseInfoVo vo){
			ResultCode resultCode=ResultCode.newSuccess();
			try{
				Long rt=caseInfoService.updateByUqKey(vo);
				if(rt==null||rt<=0){
					resultCode.setFail("修改失败,请联系技术人员");
				}
			}catch (Throwable e) {
				e.printStackTrace();
				log.error("修改异常,data={},异常信息={}",vo, e);
				resultCode.setFail("修改异常,请联系技术人员");
			}
			resWriteObjectJson(resultCode);
	}
	
	//删除数据
	@RequestMapping("/delCaseInfo")
	public void delCaseInfo(RequestBean requestBean){
		ResultCode resultCode=ResultCode.newSuccess();
		try{
			String ids=requestBean.getIds();
			CaseInfoVo t=new CaseInfoVo();
			t.setWhereSql(" id in("+ids+")");
			int rt=caseInfoService.del(t);
			if(rt<=0){
				resultCode.setFail("删除失败,请联系技术人员");
			}
		}catch (Throwable e) {
			e.printStackTrace();
			log.error("删除异常,data={},异常信息={}",requestBean, e);
			resultCode.setFail("删除异常,请联系技术人员");
		}
		resWriteObjectJson(resultCode);
	}
}
