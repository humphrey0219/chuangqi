/**
 * 
 */
package com.chuangqi.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chuangqi.dao.BaseDao;
import com.chuangqi.dao.SmileTestDao;
import com.chuangqi.service.SmileTestService;
import com.chuangqi.vo.SmileTestVo;

/**
 * 微笑测试业务接口实现类
 * @author wmk
 *
 */
@Service
public class SmileTestServiceImpl extends BaseServiceImpl<SmileTestVo> implements SmileTestService {

	@Resource
	private SmileTestDao smileTestDao;
	/* (non-Javadoc)
	 * @see com.chuangqi.service.impl.BaseServiceImpl#getBaseDao()
	 */
	@Override
	public BaseDao<SmileTestVo> getBaseDao() {
		return smileTestDao;
	}

}
