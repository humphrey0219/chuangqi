/**
 * 
 */
package com.chuangqi.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 微笑测试Vo
 * @author wmk
 *
 */
@SuppressWarnings("serial")
@Getter
@Setter
public class SmileTestVo extends BaseVo{
	private Long id;// [id, bigint, 19]
	private String phone;// [phone, varchar, 20]
	private String smileTestImg;// [smileTestImg, varchar, 255]
	private Date applyTime;//申请时间 [applyTime, datetime, 0]
	private Integer status;//状态（0申请中，1测试中，2已反馈，3作废） [status, tinyint, 3]
	private Date backTime;//反馈时间 [backTime, datetime, 0]
	private String testReport;//测试报告 [testReport, text, 65535]
	private String remark;//备注 [remark, varchar, 255]

}
