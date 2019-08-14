/**
 * 
 */
package com.chuangqi.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chuangqi.dao.BaseDao;
import com.chuangqi.dao.KeyValDao;
import com.chuangqi.service.KeyValService;
import com.chuangqi.vo.KeyValVo;

/**
 * @author wmk
 *
 */
@Service
public class KeyValServiceImpl extends BaseServiceImpl<KeyValVo> implements KeyValService{

	@Resource
	private KeyValDao keyValDao;
	/* (non-Javadoc)
	 * @see com.chuangqi.service.impl.BaseServiceImpl#getBaseDao()
	 */
	@Override
	public BaseDao<KeyValVo> getBaseDao() {
		return keyValDao;
	}

}
