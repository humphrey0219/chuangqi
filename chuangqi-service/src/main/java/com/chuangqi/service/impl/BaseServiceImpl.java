package com.chuangqi.service.impl;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;

import com.chuangqi.bean.Paginer;
import com.chuangqi.dao.BaseDao;
import com.chuangqi.service.BaseService;

public abstract class BaseServiceImpl<T> implements BaseService<T>{

	protected  final org.slf4j.Logger log=LoggerFactory.getLogger(getClass());
	
	public Paginer<T> getPaginer(Paginer<T> paginer) {
		if(paginer.isOnlyGetCount()){
			long count=getCount(paginer.getObj());
			paginer.setCount(count);
		}else if(paginer.isOnlyGetList()){
			T obj=paginer.getObj();
			if(obj!=null){
				setObjectFieldVal(obj, paginer);
			}
			paginer.setEntityList(getList(obj));
		}else{
			long count=getCount(paginer.getObj());
			paginer.setCount(count);
			if(count>0){
				T obj=paginer.getObj();
				if(obj!=null){
					setObjectFieldVal(obj, paginer);
				}
				paginer.setEntityList(getList(obj));
			}
		}
		return paginer;
	}
	
	protected void setObjectFieldVal(Object obj,@SuppressWarnings("rawtypes") Paginer paginer){
		try {
			Class<?> class1=obj.getClass();
			Method fieldSetMet = class1.getMethod("setPageStartRow", Integer.class);
			fieldSetMet.invoke(obj, paginer.getPageStartRow());
			Method fieldSetMetEnd = class1.getMethod("setPageEndRow", Integer.class);
			fieldSetMetEnd.invoke(obj, paginer.getPageSize());
		} catch (Exception e) {
			throw new RuntimeException("分页反射设置分页字段pageStartRow,pageEndRow值错误", e);
		} 
	}

	
	public  List<T> getList(T t) {
		
		return getBaseDao().getList(t);
	}

	
	public Long getCount(T t) {
		
		return getBaseDao().getCount(t);
	}
	
	@Override
	public Long add(T t) {
		
		return getBaseDao().add(t);
	}
	
	/* (non-Javadoc)
	 * @see com.wc.base.service.BaseService#get(java.lang.Object)
	 */
	@Override
	public T get(T t) {
		
		return getBaseDao().get(t);
	}
	
	@Override
	public Long updateByUqKey(T t) {
		
		return getBaseDao().updateByUqKey(t);
	}
	
	/* (non-Javadoc)
	 * @see com.ruiton.service.BaseService#getMapList(com.ruiton.vo.OrderVo)
	 */
	@Override
	public List<Map<String, Object>> getMapList(T t) {
		
		return getBaseDao().getMapList(t);
	}
	
	public int del(T t) {
		return getBaseDao().del(t);
	};
	
	public abstract BaseDao<T> getBaseDao();
}
