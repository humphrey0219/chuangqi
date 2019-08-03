/**
 * 
 */
package com.chuangqi.dao;

import java.util.List;
import java.util.Map;

/**
 * 基础Dao
 * @author minkeWei
 *
 */
public interface BaseDao<T> {
	List<T> getList(T t);
	Long getCount(T t) ;
	Long add(T t);
	Long updateByUqKey(T t);
	T get(T t) ;
	int del(T t);
    List<Map<String, Object>> getMapList(T t);
}
