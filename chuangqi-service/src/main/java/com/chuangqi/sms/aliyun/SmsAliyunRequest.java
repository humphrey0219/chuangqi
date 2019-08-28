/**
 * 
 */
package com.chuangqi.sms.aliyun;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.IAcsClient;
import com.chuangqi.configs.sms.SmsTemplateConfigProperties;

/**
 * @author wmk
 *
 */
@Slf4j
@Component
public class SmsAliyunRequest {
	@Autowired
	private CommonRequest commonRequest;
	@Autowired
	private IAcsClient iAcsClient;
	@Autowired
	private SmsTemplateConfigProperties smsTemplateConfigProperties;
	@Autowired
	private ConfigSmsAliyunProperties configSmsAliyunProperties;
	
	/**
	 * 短信验证码，默认模板
	 * @param phoneNumbers
	 * @param code
	 * @return
	 */
	public boolean sendSmsCodeDefaultTemplate(String phoneNumbers,String code) {
		return sendSmsCode(phoneNumbers, smsTemplateConfigProperties.getCode(), code);
	}
	
	/**
	 * 微笑测试结果短信通知，默认模板
	 * @param phoneNumbers
	 * @param code
	 * @return
	 */
	public boolean sendSmsSmileResultDefaultTemplate(String phoneNumbers,String code) {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("code", code);
		return sendSms(phoneNumbers, smsTemplateConfigProperties.getSmileResult(), JSONObject.toJSONString(paramMap));
	}
	
	public boolean sendSmsCode(String phoneNumbers,String templateCode,String code) {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("code", code);
		return sendSms(phoneNumbers, templateCode, JSONObject.toJSONString(paramMap));
	}
	
	public boolean sendSms(String phoneNumbers,String templateCode,String templateParam) {
		try{
			commonRequest.setAction("SendSms");
			commonRequest.putQueryParameter("PhoneNumbers", phoneNumbers);
			commonRequest.putQueryParameter("SignName", configSmsAliyunProperties.getSignName());//签名
			commonRequest.putQueryParameter("TemplateCode", templateCode);//短信模板
			commonRequest.putQueryParameter("TemplateParam", templateParam);//短信内容参数
			CommonResponse commonResponse= iAcsClient.getCommonResponse(commonRequest);
			String json=commonResponse.getData();
			JSONObject jsonObject=JSONObject.parseObject(json);
			if("OK".equals(jsonObject.getString("Code"))){
				log.info("发送短信成功,模板ID={},手机号码={},验证码={}",templateCode,phoneNumbers,templateParam);
				return true;
			}else{
				log.info("发送短信错误,模板ID={},手机号码={},验证码={},返回错误信息={}",templateCode,phoneNumbers,templateParam,json);
			}
		}catch (Throwable e) {
			throw new RuntimeException(e);
		}
		return false;
	}
	
}
