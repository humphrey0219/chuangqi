package com.chuangqi.service.impl;

import com.chuangqi.dao.BaseDao;
import com.chuangqi.dao.ZoneDao;
import com.chuangqi.service.ZoneService;
import com.chuangqi.vo.ZoneVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * 区域地址管理服务
 * @author jellyra
 */
@Service
public class ZoneServiceImpl extends BaseServiceImpl<ZoneVo> implements ZoneService {

    @Resource
    private ZoneDao zoneDao ;

    @Override
    public BaseDao<ZoneVo> getBaseDao() {
        return zoneDao ;
    }
}
