/**
 * 
 */
package com.chuangqi.common.utils;

import java.text.SimpleDateFormat;

/**
 * 时间工具类
 * @author wmk
 *
 */
public class DateUtils {
	public static final String YYYY_MM="yyyyMM";
	/**
	 * 获取 现在时间日期 格式 
	 * 
	 * @param formart
	 *            时间格式
	 * 
	 * @return String 现在时间日期 格式
	 */
	public static String getFormatNowDate(String formart) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(formart);
		return dateFormat.format(System.currentTimeMillis());
	}
}
