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
import com.chuangqi.service.KeyValService;
import com.chuangqi.vo.KeyValVo;

/**
 * 
 * @author wmk
 *
 */
@Slf4j
@RestController
@RequestMapping("/keyVal")
public class KeyValController extends BaseController{
	@Autowired
	private KeyValService keyValService;
	
	@RequestMapping("/keyVallistUI")
	public ModelAndView keyVallistUI(){
		modelAndView("keyVal/keyVallistUI");
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/keyVallist")
	public void keyvallist(){
		try{
			KeyValVo vo= new KeyValVo();
			vo.setWhereSql(getSearchRules());
			vo.setOrderBySql(" order by id desc ");
			Paginer<KeyValVo> paginer=getPaginer();
			paginer.setObj(vo);
			paginer=keyValService.getPaginer(paginer);
			outPage(paginer);
		}catch (Throwable e) {
			e.printStackTrace();
			log.error("查询列表错误", e);
		}
	}
	
	//添加数据页面
	@RequestMapping("/addkeyValUI")
	public ModelAndView addkeyValUI(){
		return modelAndView("keyVal/addkeyValUI");
	}
	
	//添加数据
	@RequestMapping("/addkeyVal")
	@ResponseBody
	public Object addkeyVal(KeyValVo vo){
		ResultCode resultCode=ResultCode.newSuccess();
		try{
			 long rt=keyValService.add(vo);
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
	@RequestMapping("/updkeyValUI")
	public ModelAndView updkeyValUI(KeyValVo vo){
			vo=keyValService.get(vo);
			modelAndView("keyVal/updkeyValUI");
			modelAndView.addObject("vo", vo);
			return modelAndView;
	}
		
	//修改数据
	@RequestMapping("/updkeyVal")
	public void updCaseCate(KeyValVo vo){
			ResultCode resultCode=ResultCode.newSuccess();
			try{
				Long rt=keyValService.updateByUqKey(vo);
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
	@RequestMapping("/delkeyVal")
	public void delkeyVal(RequestBean requestBean){
		ResultCode resultCode=ResultCode.newSuccess();
		try{
			String ids=requestBean.getIds();
			KeyValVo t=new KeyValVo();
			t.setWhereSql(" id in("+ids+")");
			int rt=keyValService.del(t);
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
