package com.chuangqi.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 分页工具类
 * 
 * @author minkeWei
 */
public class Paginer<T> {

	/*** 页面Id pageId **/
	private int pageId = 1;

	/*** 每页显示条数 pageSize **/
	private int pageSize = 10;

	/**
	 * 分页开始行数
	 */
	private int pageStartRow = 0;

	/**
	 * 分页结束行数
	 */
	private int pageEndRow = 0;

	/** 共分多少页 **/
	private long pageTotal = 0;

	/** 共记录数 **/
	private long count = 0;

	/**
	 * 是否获取总记录数
	 */
	private boolean isGetCount = true;

	/**
	 * 是否 只获取 总记录数
	 */
	private boolean onlyGetCount = false;

	/**
	 * 是否 只获取 列表
	 */
	private boolean onlyGetList = false;

	/** 存放所有 实体 对象 **/
	private List<T> entityList = new ArrayList<T>();

	/**
	 * 实体，用于查询条件
	 */
	private T obj;

	/**
	 * <默认构造函数>
	 */
	public Paginer() {
	}

	public Paginer(int pageId, int pageSize) {
		this.pageId = pageId;
		this.pageSize = pageSize;
	}

	public Paginer(int pageId, int pageSize, int count) {
		this.pageId = pageId;
		this.pageSize = pageSize;
		this.count = count;
	}

	/**
	 * @return 开始行
	 */
	public int getPageStartRow() {
		if (pageId > 0 && pageSize > 0) {
			pageStartRow = (pageId - 1) * pageSize;
		}
		return pageStartRow;
	}

	/**
	 * 设置开始行
	 * 
	 * @param pageStartRow
	 */
	public void setPageStartRow(int pageStartRow) {
		this.pageStartRow = pageStartRow;
	}

	/**
	 * @return 返回 pageId
	 */
	public int getPageId() {
		return pageId;
	}

	/**
	 * @return 返回 pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @return 返回 pageTotal
	 */
	public long getPageTotal() {
		if (count > 0) {
			if (pageSize > 0 && count > pageSize) {
				if ((count % pageSize) == 0) {
					pageTotal = count / pageSize;
				} else {
					pageTotal = count / pageSize + 1;
				}
			} else {
				pageTotal = 1;
			}
		}
		return pageTotal;
	}

	/**
	 * @param 对
	 *            pageId 进行赋值
	 */
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	/**
	 * @param 对
	 *            pageSize 进行赋值
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @param 对
	 *            pageTotal 进行赋值
	 */
	public void setPageTotal(long pageTotal) {
		this.pageTotal = pageTotal;
	}

	/**
	 * @return 返回 count
	 */
	public long getCount() {
		return count;
	}

	/**
	 * @param 对
	 *            count 进行赋值
	 */
	public void setCount(long count) {
		this.count = count;
	}

	/**
	 * @return the onlyGetCount
	 */
	public boolean isOnlyGetCount() {
		return onlyGetCount;
	}

	/**
	 * @param onlyGetCount
	 *            the onlyGetCount to set
	 */
	public void setOnlyGetCount(boolean onlyGetCount) {
		this.onlyGetCount = onlyGetCount;
	}

	/**
	 * @return 返回 entityList
	 */
	public List<T> getEntityList() {
		return entityList;
	}

	/**
	 * @param 对
	 *            entityList 进行赋值
	 */

	public void setEntityList(List<T> entityList) {
		this.entityList = entityList;
	}

	/**
	 * @return the onlyGetList
	 */
	public boolean isOnlyGetList() {
		return onlyGetList;
	}

	/**
	 * @param onlyGetList
	 *            the onlyGetList to set
	 */
	public void setOnlyGetList(boolean onlyGetList) {
		this.onlyGetList = onlyGetList;
	}

	/**
	 * default true
	 * 
	 * @return the isGetCount
	 */
	public boolean isGetCount() {
		return isGetCount;
	}

	/**
	 * default true
	 * 
	 * @param isGetCount
	 *            the isGetCount to set
	 */
	public void setGetCount(boolean isGetCount) {
		this.isGetCount = isGetCount;
	}

	/**
	 * 如何没有设置结束的行,则根据 pageStartRow+pageSize
	 * 
	 * @return the pageEndRow
	 */
	public int getPageEndRow() {
		if (pageEndRow == 0) {
			pageEndRow = pageStartRow + pageSize;
		}
		return pageEndRow;
	}

	/**
	 * @param pageEndRow
	 *            the pageEndRow to set
	 */
	public void setPageEndRow(int pageEndRow) {
		this.pageEndRow = pageEndRow;
	}

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
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
}
