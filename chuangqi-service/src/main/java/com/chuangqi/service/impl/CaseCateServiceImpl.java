/**
 * 
 */
package com.chuangqi.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chuangqi.dao.BaseDao;
import com.chuangqi.dao.CaseCateDao;
import com.chuangqi.service.CaseCateService;
import com.chuangqi.vo.CaseCateVo;

/**
 * 病例分类业务接口实现类
 * @author wmk
 *
 */
@Service
public class CaseCateServiceImpl extends BaseServiceImpl<CaseCateVo> implements CaseCateService {

	@Resource
	private CaseCateDao caseCateDao;
	/* (non-Javadoc)
	 * @see com.chuangqi.service.impl.BaseServiceImpl#getBaseDao()
	 */
	@Override
	public BaseDao<CaseCateVo> getBaseDao() {
		return caseCateDao;
	}

}
