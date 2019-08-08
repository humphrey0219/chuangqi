/**
 * 
 */
package com.chuangqi.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.chuangqi.bean.Paginer;
import com.chuangqi.bean.RequestBean;
import com.chuangqi.bean.ResultCode;
import com.chuangqi.service.LeaveService;
import com.chuangqi.vo.LeaveVo;

/**
 * 留言控制类
 * @author wmk
 *
 */

@Slf4j
@RestController
@RequestMapping("/leave")
public class LeaveController extends BaseController{
	@Autowired
	private LeaveService leaveService;
	
	@RequestMapping("/leavelistUI")
	public ModelAndView leavelistUI(){
		modelAndView("leave/leaveListUI");
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/leavelist")
	public void leavelist(){
		try{
			LeaveVo vo= new LeaveVo();
			vo.setWhereSql(getSearchRules());
			vo.setOrderBySql(" order by id desc ");
			Paginer<LeaveVo> paginer=getPaginer();
			paginer.setObj(vo);
			paginer=leaveService.getPaginer(paginer);
			outPage(paginer);
		}catch (Throwable e) {
			e.printStackTrace();
			log.error("查询留言列表错误", e);
		}
	}
	
	//添加数据页面
	@RequestMapping("/addleaveUI")
	public ModelAndView addleaveUI(){
		return modelAndView("leave/addleaveUI");
	}
	
	//添加数据
	@RequestMapping("/addleave")
	public void addleave(LeaveVo vo){
		ResultCode resultCode=ResultCode.newSuccess();
		try{
			Long rt=leaveService.add(vo);
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
	@RequestMapping("/updleaveUI")
	public ModelAndView updleaveUI(LeaveVo vo){
			vo=leaveService.get(vo);
			modelAndView("leave/updleaveUI");
			modelAndView.addObject("vo", vo);
			return modelAndView;
	}
		
	//修改数据
	@RequestMapping("/updleave")
	public void updleave(LeaveVo vo){
			ResultCode resultCode=ResultCode.newSuccess();
			try{
				Long rt=leaveService.updateByUqKey(vo);
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
	@RequestMapping("/delleave")
	public void delleave(RequestBean requestBean){
		ResultCode resultCode=ResultCode.newSuccess();
		try{
			String ids=requestBean.getIds();
			LeaveVo t=new LeaveVo();
			t.setWhereSql(" id in("+ids+")");
			int rt=leaveService.del(t);
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
