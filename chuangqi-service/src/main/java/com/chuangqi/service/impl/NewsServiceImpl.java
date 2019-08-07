/**
 * 
 */
package com.chuangqi.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chuangqi.dao.BaseDao;
import com.chuangqi.dao.NewsDao;
import com.chuangqi.service.NewsService;
import com.chuangqi.vo.NewsVo;

/**
 * 新闻业务接口
 * @author wmk
 *
 */
@Service
public class NewsServiceImpl extends BaseServiceImpl<NewsVo> implements NewsService{

	@Resource
	private NewsDao newsDao;
	/* (non-Javadoc)
	 * @see com.chuangqi.service.impl.BaseServiceImpl#getBaseDao()
	 */
	@Override
	public BaseDao<NewsVo> getBaseDao() {
		return newsDao;
	}

}
