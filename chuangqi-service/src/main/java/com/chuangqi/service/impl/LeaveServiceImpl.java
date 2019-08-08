/**
 * 
 */
package com.chuangqi.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chuangqi.dao.BaseDao;
import com.chuangqi.dao.LeaveDao;
import com.chuangqi.service.LeaveService;
import com.chuangqi.vo.LeaveVo;

/**
 * 留言业务接口实现
 * @author wmk
 *
 */
@Service
public class LeaveServiceImpl extends BaseServiceImpl<LeaveVo> implements LeaveService{
	@Resource
	private LeaveDao leaveDao;
	@Override
	public BaseDao<LeaveVo> getBaseDao() {
		return leaveDao;
	}

}
