/**
 * 
 */
package com.chuangqi.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.chuangqi.bean.Paginer;
import com.chuangqi.bean.RequestBean;
import com.chuangqi.bean.ResultCode;
import com.chuangqi.service.CaseCateService;
import com.chuangqi.vo.CaseCateVo;

/**
 * 留言控制类
 * @author wmk
 *
 */

@Slf4j
@RestController
@RequestMapping("/case")
public class CaseCateController extends BaseController{
	@Autowired
	private CaseCateService caseCateService;
	
	@RequestMapping("/caseCatelistUI")
	public ModelAndView caseCatelistUI(){
		modelAndView("cases/caseCatelistUI");
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/caseCatelist")
	public void caseCatelist(){
		try{
			CaseCateVo vo= new CaseCateVo();
			vo.setWhereSql(getSearchRules());
			vo.setOrderBySql(" order by id desc ");
			Paginer<CaseCateVo> paginer=getPaginer();
			paginer.setObj(vo);
			paginer=caseCateService.getPaginer(paginer);
			outPage(paginer);
		}catch (Throwable e) {
			e.printStackTrace();
			log.error("查询列表错误", e);
		}
	}
	
	//添加数据页面
	@RequestMapping("/addCaseCateUI")
	public ModelAndView addCaseCateUI(){
		return modelAndView("cases/addCaseCateUI");
	}
	
	//添加数据
	@RequestMapping("/addCaseCate")
	@ResponseBody
	public Object addCaseCate(CaseCateVo vo){
		ResultCode resultCode=ResultCode.newSuccess();
		try{
			CaseCateVo qCaseCateVo=new CaseCateVo();
			qCaseCateVo.setName(vo.getName());
			long rt=caseCateService.getCount(qCaseCateVo);
			if(rt>=1){
				resultCode.setFail("分类名称已存在");
				return resultCode;
			}
			 rt=caseCateService.add(vo);
			if(rt<=0){
				resultCode.setFail("添加失败,请联系技术人员");
			}
		}catch (Throwable e) {
			e.printStackTrace();
			log.error("添加异常,data={},异常信息={}",vo, e);
			resultCode.setFail("添加异常,请联系技术人员");
		}
		return resultCode;
	}
	
	//修改数据页面
	@RequestMapping("/updCaseCateUI")
	public ModelAndView updcaseCateUI(CaseCateVo vo){
			vo=caseCateService.get(vo);
			modelAndView("cases/updCaseCateUI");
			modelAndView.addObject("vo", vo);
			return modelAndView;
	}
		
	//修改数据
	@RequestMapping("/updCaseCate")
	public void updCaseCate(CaseCateVo vo){
			ResultCode resultCode=ResultCode.newSuccess();
			try{
				Long rt=caseCateService.updateByUqKey(vo);
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
	@RequestMapping("/delCaseCate")
	public void delCaseCate(RequestBean requestBean){
		ResultCode resultCode=ResultCode.newSuccess();
		try{
			String ids=requestBean.getIds();
			CaseCateVo t=new CaseCateVo();
			t.setWhereSql(" id in("+ids+")");
			int rt=caseCateService.del(t);
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
