package com.chuangqi.service.impl;

import com.chuangqi.dao.BaseDao;
import com.chuangqi.dao.PageImageDao;
import com.chuangqi.service.PageImageService;
import com.chuangqi.vo.PageImageVo;

import javax.annotation.Resource;

public class PageImageServiceImpl extends BaseServiceImpl<PageImageVo> implements PageImageService {

    @Resource
    PageImageDao  pageImageDao ;

    @Override
    public BaseDao<PageImageVo> getBaseDao() {
        return pageImageDao ;
    }
}
