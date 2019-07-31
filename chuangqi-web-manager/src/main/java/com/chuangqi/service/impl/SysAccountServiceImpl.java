/**
 * 
 */
package com.chuangqi.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chuangqi.dao.BaseDao;
import com.chuangqi.dao.SysAccountDao;
import com.chuangqi.service.SysAccountService;
import com.chuangqi.vo.SysAccountVo;

/**
 * 系统账号业务
 * @author weiminke
 *
 */
@Service
public class SysAccountServiceImpl extends BaseServiceImpl<SysAccountVo> implements SysAccountService{

	@Resource
	private SysAccountDao sysAccountDao;
	
	@Override
	public BaseDao<SysAccountVo> getBaseDao() {
		return sysAccountDao;
	}

}
