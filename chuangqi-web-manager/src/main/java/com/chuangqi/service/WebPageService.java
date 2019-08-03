package com.chuangqi.service;

import com.chuangqi.vo.WebPageVo;

public interface WebPageService extends BaseService<WebPageVo> {
    Long insert(WebPageVo webPageVo);
}
