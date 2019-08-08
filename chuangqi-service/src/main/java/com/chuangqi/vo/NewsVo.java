/**
 * 
 */
package com.chuangqi.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 新闻
 * @author wmk
 *
 */
@SuppressWarnings("serial")
@Getter
@Setter
public class NewsVo extends BaseVo{
	private Long id;//主键 [id, bigint, 19]
	private Integer serviceType;//业务类型：10公司新闻，20行业新闻 [serviceType, tinyint, 3]
	private String title;//新闻标题 [title, varchar, 255]
	private String resource;//新闻来源 [resource, varchar, 128]
	private String resourceUrl;//来源URL [resourceUrl, varchar, 255]
	private String keyword;//关键词 [keyword, varchar, 255]
	private String description;//关键字描述 [description, varchar, 1024]
	private String imgUrl;//新闻图片 [imgUrl, varchar, 255]
	private String newDesc;//新闻简介 [newDesc, varchar, 1024]
	private Integer status;//状态（0：草稿，1上架，2下架） [status, tinyint, 3]
	private Date pubTime;//发布时间 [pubTime, datetime, 0]
	private String details;//新闻详情 [details, text, 65535]
	
	private String showImgUrl;//显示图片地址
}
