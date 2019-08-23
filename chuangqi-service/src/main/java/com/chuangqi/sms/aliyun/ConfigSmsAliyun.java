/**
 * 
 */
package com.chuangqi.sms.aliyun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 阿里云配置对象
 * @author wmk
 *
 */
@Configuration
public class ConfigSmsAliyun {
	@Autowired
	private ConfigSmsAliyunProperties configSmsAliyunProperties;
	
	@Bean
	public IAcsClient getIAcsClient(){
		 DefaultProfile profile = DefaultProfile.getProfile("default", configSmsAliyunProperties.getAccessKeyId(), configSmsAliyunProperties.getSecret());
	     IAcsClient client = new DefaultAcsClient(profile);
	     return client;
	}
	
	@Bean
	public CommonRequest getCommonRequest(IAcsClient iAcsClient){
		  CommonRequest request = new CommonRequest();
	      request.setMethod(MethodType.POST);
	      request.setDomain(configSmsAliyunProperties.getDomain());
	      request.setVersion(configSmsAliyunProperties.getVersion());
	      request.putQueryParameter("RegionId", "default");
	      request.putQueryParameter("SignName", configSmsAliyunProperties.getSignName());//签名
	      return request;
	}
	
}
