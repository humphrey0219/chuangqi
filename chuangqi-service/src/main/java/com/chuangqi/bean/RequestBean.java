package com.chuangqi.bean;

/**
 * 请求参数
 */
@SuppressWarnings("serial")
public class RequestBean extends Base{
	
	private String ids;//以头号分开

	/**
	 * @return the ids
	 */
	public String getIds() {
		return ids;
	}

	/**
	 * @param ids the ids to set
	 */
	public void setIds(String ids) {
		this.ids = ids;
	}
	
}
