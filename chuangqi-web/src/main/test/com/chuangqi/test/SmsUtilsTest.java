/**
 * 
 */
package com.chuangqi.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.chuangqi.WebApplication;
import com.chuangqi.service.SysAccountService;
import com.chuangqi.sms.aliyun.SmsAliyunRequest;
import com.chuangqi.vo.SysAccountVo;

/**
 * 短信
 * @author wmk
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
@WebAppConfiguration
public class SmsUtilsTest {
	
	@Autowired
	private SmsAliyunRequest smsAliyunRequest;
	
	/*@Autowired
	private SysAccountService accountService;*/
	
	@Test
	public void sendSms(){
		try {
			//accountService.getCount(new SysAccountVo());
			//smsAliyunRequest.sendSmsCode("18816824093","SMS_172735618", "fsdf");
			smsAliyunRequest.sendSmsCodeDefaultTemplate("18816824093","4555");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		    DefaultProfile profile = DefaultProfile.getProfile("default", "LTAIy7bJhCsjfx4K", "I5bLl0eVtXKukIxWmCnpzYsrat5FEY");
	        IAcsClient client = new DefaultAcsClient(profile);

	        CommonRequest request = new CommonRequest();
	        request.setMethod(MethodType.POST);
	        request.setDomain("dysmsapi.aliyuncs.com");
	        request.setVersion("2017-05-25");
	        request.setAction("SendSms");
	        request.putQueryParameter("RegionId", "default");
	        request.putQueryParameter("PhoneNumbers", "18816824093");
	        request.putQueryParameter("SignName", "广州创启医疗");//签名
	        request.putQueryParameter("TemplateCode", "SMS_172735618");//短信模板
	        request.putQueryParameter("TemplateParam", "{\"code\":\"43523\"}");//短信内容参数
	        try {
	            CommonResponse response = client.getCommonResponse(request);
	            System.out.println(response.getData());
	        } catch (ServerException e) {
	            e.printStackTrace();
	        } catch (ClientException e) {
	            e.printStackTrace();
	        }
	}
}
