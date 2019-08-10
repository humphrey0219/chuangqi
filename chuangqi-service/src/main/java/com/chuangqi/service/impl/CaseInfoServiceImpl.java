/**
 * 
 */
package com.chuangqi.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chuangqi.dao.BaseDao;
import com.chuangqi.dao.CaseInfoDao;
import com.chuangqi.service.CaseInfoService;
import com.chuangqi.vo.CaseInfoVo;

/**
 * 病例案例
 * @author wmk
 *
 */
@Service
public class CaseInfoServiceImpl extends BaseServiceImpl<CaseInfoVo> implements CaseInfoService{
	@Resource
	private CaseInfoDao caseInfoDao;
	/* (non-Javadoc)
	 * @see com.chuangqi.service.impl.BaseServiceImpl#getBaseDao()
	 */
	@Override
	public BaseDao<CaseInfoVo> getBaseDao() {
		return caseInfoDao;
	}
}
