/**
 * 
 */
package com.chuangqi.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 留言vo
 * @author wmk
 *
 */
@SuppressWarnings("serial")
@Getter
@Setter
public class LeaveVo extends BaseVo{
	private Long id;//主键 [id, bigint, 19]
	private String phone;//手机号码 [phone, varchar, 20]
	private String email;//电子邮件 [email, varchar, 128]
	private String content;//留言内容 [content, varchar, 255]
	private Date leaveTime;//留言时间 [leaveTime, datetime, 0]
	private Integer status;//状态0留言，1已反馈，2作废 [status, tinyint, 3]
	private String feedback;//反馈内容 [feedback, varchar, 255]
	private Date feedbackTime;//反馈时间 [feedbackTime, datetime, 0]
	private Date createTime;//创建时间 [createTime, datetime, 0]
	private Date updateTime;//更新时间 [updateTime, timestamp, 0]
}
