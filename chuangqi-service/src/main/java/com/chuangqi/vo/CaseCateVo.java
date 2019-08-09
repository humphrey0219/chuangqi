/**
 * 
 */
package com.chuangqi.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 病例分类
 * @author wmk
 *
 */
@SuppressWarnings("serial")
@Getter
@Setter
public class CaseCateVo extends BaseVo{
	private Long id;//主键 [id, bigint, 19]
	private String name;//分类名称 [name, varchar, 64]
	private Integer status;//状态：1启用，0禁用 [status, tinyint, 3]
}
