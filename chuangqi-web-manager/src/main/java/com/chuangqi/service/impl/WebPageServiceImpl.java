package com.chuangqi.service.impl;

import javax.annotation.Resource;

import com.chuangqi.dao.WebPageDao;
import com.chuangqi.service.WebPageService;
import com.chuangqi.vo.WebPageVo;
import org.springframework.stereotype.Service;

import com.chuangqi.dao.BaseDao;


/**
 * 页面管理业务
 * @author jellyra
 */
@Service
public class WebPageServiceImpl extends BaseServiceImpl<WebPageVo> implements WebPageService {

    @Resource
    private WebPageDao webPageDao;


    @Override
    public BaseDao<WebPageVo> getBaseDao() {
        return webPageDao;
    }

    @Override
    public Long insert(WebPageVo webPageVo) {
        return  webPageDao.insert(webPageVo);
    }
}
