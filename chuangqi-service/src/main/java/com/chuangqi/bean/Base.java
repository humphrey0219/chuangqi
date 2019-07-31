package com.chuangqi.bean;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@SuppressWarnings("serial")
public class Base implements Serializable{
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
