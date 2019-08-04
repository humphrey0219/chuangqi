package com.chuangqi.dao;

import com.chuangqi.vo.WebPageVo;

/**
 *  网站网页
 * @author jellyra
 */
public interface WebPageDao extends BaseDao<WebPageVo>  {

    Long insert(WebPageVo webPageVo);
}
