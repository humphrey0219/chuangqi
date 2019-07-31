package com.chuangqi.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

//搜索规则值
@SuppressWarnings("serial")
public class SearchRules extends Base{
	
	List<Rules> rules=new ArrayList<>();
	String op;
	
	public String getRulesSql(){
		if(rules!=null&&!rules.isEmpty()){
			StringBuilder sql=new StringBuilder();
			String opF=null;
			for (Rules rule:rules) {
				if(StringUtils.isBlank(rule.getValue())){
					continue;
				}
				opF=rule.getOp();
				switch (opF) {
				case "equal":
					sql.append(" "+op+" ");//追加and 条件
					sql.append(rule.getField() + "='" + rule.getValue() + "'");
					break;
				case "notequal":
					sql.append(" "+op+" ");//追加and 条件
					sql.append(rule.getField() + "<>'" + rule.getValue() + "'");
					break;
				case "startwith":
					sql.append(" "+op+" ");//追加and 条件
					sql.append(rule.getField() + " like '" + rule.getValue() + "%'");
					break;
				case "like":
					sql.append(" "+op+" ");//追加and 条件
					sql.append(rule.getField() + " like '%" + rule.getValue() + "%'");
					break;
				case "greaterorequal":
					sql.append(" "+op+" ");//追加and 条件
					sql.append(rule.getField() + " >= '" + rule.getValue() + "'");
					break;
				case "less":
					sql.append(" "+op+" ");//追加and 条件
					sql.append(rule.getField() + " < '" + rule.getValue() + "'");
					break;
				case "lessorequal":
					sql.append(" "+op+" ");//追加and 条件
					sql.append(rule.getField() + " <= '" + rule.getValue() + "'");
					break;
				case "in":
					sql.append(" "+op+" ");//追加and 条件
					sql.append(rule.getField() + " in ('" + rule.getValue() + "')");
					break;
				case "notin":
					sql.append(" "+op+" ");//追加and 条件
					sql.append(rule.getField() + " not in ('" + rule.getValue() + "')");
					break;
				default:
					break;
				}
			}
			return sql.toString();
		}
		return "";
	}
	
	/**
	 * @return the rules
	 */
	public List<Rules> getRules() {
		return rules;
	}
	/**
	 * @param rules the rules to set
	 */
	public void setRules(List<Rules> rules) {
		this.rules = rules;
	}
	/**
	 * @return the op
	 */
	public String getOp() {
		return op;
	}
	/**
	 * @param op the op to set
	 */
	public void setOp(String op) {
		this.op = op;
	}
	
}
