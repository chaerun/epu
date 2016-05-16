package com.kemenkes.epu.common.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class Constraint {

	private String whereClause;
	private Map<String, Object> parameters;
	
	public Constraint() {
		whereClause = null;
		parameters = new HashMap<String, Object>();
	}
	
	public String getWhereClause() {
		return whereClause;
	}
	public void setWhereClause(String whereClause) {
		this.whereClause = whereClause;
	}
	
	public Map<String, Object> getParameters() {
		return parameters;
	}
	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}
	
	public void appendAnd(String whereClause){
		if(StringUtils.isNotBlank(this.getWhereClause())) {
			this.setWhereClause(this.getWhereClause() + " and " + whereClause);
		} else {
			this.setWhereClause(whereClause);
		}
	}
	
	public static Constraint precheckConstraint(Constraint constraint) {

		if (constraint == null) {
			constraint = new Constraint();
		}
		
		if(StringUtils.isNotBlank(constraint.getWhereClause())) {
			constraint.setWhereClause(constraint.getWhereClause() + " and ");
		} else {
			constraint.setWhereClause("");
		}
		
		return constraint;
	}
	
}
