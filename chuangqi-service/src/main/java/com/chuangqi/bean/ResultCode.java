package com.chuangqi.bean;

//结果代码
@SuppressWarnings("serial")
public class ResultCode extends Base{
	//成功代码
	public static final String CODE_0000="0000";
	//成功代码
	public static final String MSG_0000="操作成功";
	//错误代码
	public static final String CODE_9999="9999";
	//成功代码
	public static final String MSG_9999="操作失败";
	
	
	private String code;
	private String msg;
	
	public ResultCode() {
		
	}
	public ResultCode(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	//成功代码
	public static ResultCode newSuccess(){
		ResultCode resultCode=new ResultCode(CODE_0000, MSG_0000);
		return resultCode;
	}
	//成功代码
	public void setSuccess(String msg){
		this.code=CODE_0000;
		this.msg=msg;
	}
	//失败代码
	public void setFail(String msg){
		this.code=CODE_9999;
		this.msg=msg;
	}
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
