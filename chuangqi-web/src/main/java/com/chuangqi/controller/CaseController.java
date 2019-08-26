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
public class CaseController extends BaseController{
	@Autowired
	private CaseInfoService caseInfoService;
	@Autowired
	private CaseCateService caseCateService;
	


}
