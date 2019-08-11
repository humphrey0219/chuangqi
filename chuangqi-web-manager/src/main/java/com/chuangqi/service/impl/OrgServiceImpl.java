package com.chuangqi.service.impl;

import com.chuangqi.dao.BaseDao;
import com.chuangqi.dao.OrgDao;
import com.chuangqi.service.OrgService;
import com.chuangqi.vo.OrgVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrgServiceImpl extends BaseServiceImpl<OrgVo> implements OrgService {

    @Resource
    private OrgDao orgDao ;

    @Override
    public BaseDao<OrgVo> getBaseDao() {
        return orgDao;
    }
}
