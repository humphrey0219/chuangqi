/**
 * 
 */
package com.chuangqi.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 病例信息
 * @author wmk
 *
 */
@SuppressWarnings("serial")
@Getter
@Setter
public class CaseInfoVo extends BaseVo{
	private Long id;//主键 [id, bigint, 19]
	private Long cateId;//分类ID [cateId, bigint, 19]
	private Integer proCate;//矫正产品：10舌侧，20隐形矫治器 [proCate, tinyint, 3]
	private String caseSpecial;//病例特点 [caseSpecial, varchar, 128]
	private String doctorName;//主治医生 [doctorName, varchar, 64]
	private String hospital;//治疗机构 [hospital, varchar, 255]
	private Integer status;
	private String beforeImg;//整形前图片 [beforeImg, varchar, 255]
	private String afterImg;//整形后图片 [afterImg, varchar, 255]
	private String experience;//矫治体会 [experience, text, 65535]
	private String problem;//问题列表 [problem, text, 65535]
	private String target;//治疗目标 [target, text, 65535]
	private String plan;//治疗计划 [plan, varchar, 255]
	
	private String cateName;
	private String showbeforeImg;//整形前图片
	private String showAfterImg;//整形后图片
}
