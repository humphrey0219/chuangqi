package com.chuangqi.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 基础VO
 * @author minkeWei
 *
 */
@SuppressWarnings("serial")
public class BaseVo implements Serializable{
	/**
	 * 分页开始行数
	 */
	private Integer pageStartRow;

	/**
	 * 分页结束行数
	 */
	private Integer pageEndRow;
	
	/**
	 * 排序sql
	 */
	private String orderBySql;
	
	//直接查询sql,以and 或者or 条件开头
	private String whereSql;
	
	private Date createTime;
	private Date updateTime;
	private String remark;//备注

	public Integer getPageStartRow() {
		return pageStartRow;
	}

	public void setPageStartRow(Integer pageStartRow) {
		this.pageStartRow = pageStartRow;
	}

	public Integer getPageEndRow() {
		return pageEndRow;
	}

	public void setPageEndRow(Integer pageEndRow) {
		this.pageEndRow = pageEndRow;
	}
	
	public String getOrderBySql() {
		return orderBySql;
	}

	public void setOrderBySql(String orderBySql) {
		this.orderBySql = orderBySql;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
	            ToStringStyle.MULTI_LINE_STYLE);
	}
	
	public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
	public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the whereSql
	 */
	public String getWhereSql() {
		return whereSql;
	}

	/**
	 * @param whereSql the whereSql to set
	 */
	public void setWhereSql(String whereSql) {
		this.whereSql = whereSql;
	}
	

}
