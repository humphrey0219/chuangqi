/**
 * 
 */
package com.chuangqi.sms.aliyun;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * 阿里云短信
 * @author wmk
 */

@Component
@ConfigurationProperties(prefix = "sms.aliyun")
public class ConfigSmsAliyunProperties {
	private String accessKeyId;
	private String secret;
	private String domain="dysmsapi.aliyuncs.com";
	private String version="2017-05-25";
	private String signName;
	/**
	 * @return the accessKeyId
	 */
	public String getAccessKeyId() {
		return accessKeyId;
	}
	/**
	 * @return the secret
	 */
	public String getSecret() {
		return secret;
	}
	/**
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param accessKeyId the accessKeyId to set
	 */
	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}
	/**
	 * @param secret the secret to set
	 */
	public void setSecret(String secret) {
		this.secret = secret;
	}
	/**
	 * @param domain the domain to set
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * @return the signName
	 */
	public String getSignName() {
		return signName;
	}
	/**
	 * @param signName the signName to set
	 */
	public void setSignName(String signName) {
		this.signName = signName;
	}
}
