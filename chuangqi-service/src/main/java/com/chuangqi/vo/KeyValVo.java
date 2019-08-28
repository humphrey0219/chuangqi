/**
 * 
 */
package com.chuangqi.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 键值配置信息
 * @author wmk
 *
 */
@SuppressWarnings("serial")
@Getter
@Setter
public class KeyValVo extends BaseVo{
	private Long id;//'主键',
	private String key;// '键',
	private String keyDesc;//'主键描述说明',
	private String val;//值',
	private String txtType;
}
