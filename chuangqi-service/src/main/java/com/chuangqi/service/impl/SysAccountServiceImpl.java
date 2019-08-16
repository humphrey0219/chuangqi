/**
 * 
 */
package com.chuangqi.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
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

	/* (non-Javadoc)
	 * @see com.chuangqi.service.impl.BaseServiceImpl#updateByUqKey(java.lang.Object)
	 */
	@Override
	public Long updateByUqKey(SysAccountVo t) {
		if(StringUtils.isBlank(t.getPwd())){
			t.setPwd(null);
		}
		return super.updateByUqKey(t);
	}
}
