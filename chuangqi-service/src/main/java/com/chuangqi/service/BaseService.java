package com.chuangqi.service;

import java.util.List;
import java.util.Map;

import com.chuangqi.bean.Paginer;

/**
 * 基础业务接口
 * 
 * @author wmk
 *
 * @param <T>
 */
public interface BaseService<T> {
	Paginer<T> getPaginer(Paginer<T> paginer);
	List<T> getList(T t);
	Long getCount(T t);
	Long add(T t);
	Long updateByUqKey(T t);
	T get(T t);
	int del(T t);
	List<Map<String, Object>> getMapList(T t);
}
