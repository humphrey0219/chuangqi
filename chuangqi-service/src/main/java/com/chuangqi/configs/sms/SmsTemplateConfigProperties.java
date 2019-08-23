/**
 * 
 */
package com.chuangqi.configs.sms;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 短信模板
 * @author wmk
 *
 */
@Component
@ConfigurationProperties(prefix = "sms.template")
public class SmsTemplateConfigProperties {
	private String code="SMS_172735618";//短信验证码
	private String smileResult="";//微笑测试结果
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @return the smileResult
	 */
	public String getSmileResult() {
		return smileResult;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @param smileResult the smileResult to set
	 */
	public void setSmileResult(String smileResult) {
		this.smileResult = smileResult;
	}
}
