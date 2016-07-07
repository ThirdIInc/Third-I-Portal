package org.paradyne.bean.common;


import org.paradyne.lib.BeanBase;


/**
 * Name	  : F9Bean.java
 * Author : Sumit Kasundra
 * Date   : 
 * Purpose:
*/

public class F9Bean extends BeanBase {

	private int query = 0;
	private String search = "";
	private String fieldName = "";
	private String codeField = "";
	private String nameField = "";
	private String param = "";
	private String param1 ="";
	private String recordCount = "0";
	private String submitFlag = "false" ;
	private String f9query = "";
	
	/* for final f9*/
	private String headers = "";
	private String indexes="";
	
	
	private boolean flag = false;
	
/*	public void setRecordCount(String recordCount) {
		this.recordCount= "Total Records :"+recordCount;
	}
	public String getRecordCount() {
		return recordCount;
	}*/

	public void setFlag(boolean flag) {
		this.flag= flag;
	}
	public boolean getFlag() {
		return flag;
	}

	public void setFieldName(String fieldName) {
		this.fieldName= fieldName;
	}
	public String getFieldName() {
		return fieldName;
	}

	public void setCodeField(String fieldName) {
		this.codeField= fieldName;
	}
	public String getCodeField() {
		return codeField;
	}

	public void setNameField(String fieldName) {
		this.nameField= fieldName;
	}
	public String getNameField() {
		return nameField;
	}

	public void setParam(String paramField) {
		this.param= paramField;
	}
	public String getParam() {
		return param;
	}
	
	public void setParam1(String paramField1) {
		this.param1= paramField1;
	}
	public String getParam1() {
		return param1;
	}

	public void setQuery(int query) {
		this.query= query;
	}
	public int getQuery() {
		return query;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	public String getSearch() {
		return search;
	}

	public void setHeaders(String heads) {
		this.headers= heads;
	}
	public String getHeaders() {
		return headers;
	}

	public void setIndexes(String indexes) {
		this.indexes = indexes;
	}
	public String getIndexes() {
		return indexes;
	}
	
	public void setSubmitFlag(String submitFlag) {
		this.submitFlag = submitFlag;
	}
	public String getSubmitFlag() {
		return submitFlag;
	}
	public String getF9query() {
		return f9query;
	}
	public void setF9query(String f9query) {
		this.f9query = f9query;
	}
	
}