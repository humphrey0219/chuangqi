/**
 * 
 */
package com.chuangqi.common.convert;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 多个日期请求转换支持
 */
public class DatePropertyEditorSupport extends PropertyEditorSupport {  

	private static final List<String> formarts = new ArrayList<String>(4);
    static{
        formarts.add("yyyy-MM");
        formarts.add("yyyy-MM-dd");
        formarts.add("yyyy-MM-dd HH:mm");
        formarts.add("yyyy-MM-dd HH:mm:ss");
    }
    
  @Override  
  public void setAsText(String text) throws IllegalArgumentException {  
      setValue(convert(text));  
  }  
  
  public Date convert(String source) {
      if (org.apache.commons.lang.StringUtils.isBlank(source)) {
          return null;
      }
      if(source.matches("^\\d{4}-\\d{1,2}$")){ 
          return parseDate(source, formarts.get(0));
      }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")){
          return parseDate(source, formarts.get(1));
      }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")){
          return parseDate(source, formarts.get(2));
      }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")){
          return parseDate(source, formarts.get(3));
      }else {
          throw new IllegalArgumentException("Invalid boolean value '" + source + "'");
      }
  }

  /**
   * 功能描述：格式化日期
   * 
   * @param dateStr
   *            String 字符型日期
   * @param format
   *            String 格式
   * @return Date 日期
   */
  public  Date parseDate(String dateStr, String format) {
      Date date=null;
      try {
          DateFormat dateFormat = new SimpleDateFormat(format);
          date = (Date) dateFormat.parse(dateStr);
      } catch (Exception e) {
      }
      return date;
  }
    
}  
