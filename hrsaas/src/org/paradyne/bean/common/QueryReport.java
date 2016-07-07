/**
 * 
 */
package org.paradyne.bean.common;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author lakkichand
 *
 */
public class QueryReport extends BeanBase {

	private String queryId;
	private String queryName;
	private String queryString;
	private String paraId;
	private String paraName;
	private String paraValue;
	private String headerName;
	private String valueOfHeader;
	private ArrayList paraList;
	private ArrayList headerList;
	private ArrayList valueList;
	/**
	 * @return the queryId
	 */
	public String getQueryId() {
		return queryId;
	}
	/**
	 * @param queryId the queryId to set
	 */
	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}
	/**
	 * @return the queryName
	 */
	public String getQueryName() {
		return queryName;
	}
	/**
	 * @param queryName the queryName to set
	 */
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
	/**
	 * @return the queryString
	 */
	public String getQueryString() {
		return queryString;
	}
	/**
	 * @param queryString the queryString to set
	 */
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	/**
	 * @return the paraId
	 */
	public String getParaId() {
		return paraId;
	}
	/**
	 * @param paraId the paraId to set
	 */
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}
	/**
	 * @return the paraName
	 */
	public String getParaName() {
		return paraName;
	}
	/**
	 * @param paraName the paraName to set
	 */
	public void setParaName(String paraName) {
		this.paraName = paraName;
	}
	/**
	 * @return the paraValue
	 */
	public String getParaValue() {
		return paraValue;
	}
	/**
	 * @param paraValue the paraValue to set
	 */
	public void setParaValue(String paraValue) {
		this.paraValue = paraValue;
	}
	/**
	 * @return the paraList
	 */
	public ArrayList getParaList() {
		return paraList;
	}
	/**
	 * @param paraList the paraList to set
	 */
	public void setParaList(ArrayList paraList) {
		this.paraList = paraList;
	}
	/**
	 * @return the headerList
	 */
	public ArrayList getHeaderList() {
		return headerList;
	}
	/**
	 * @param headerList the headerList to set
	 */
	public void setHeaderList(ArrayList headerList) {
		this.headerList = headerList;
	}
	/**
	 * @return the valueList
	 */
	public ArrayList getValueList() {
		return valueList;
	}
	/**
	 * @param valueList the valueList to set
	 */
	public void setValueList(ArrayList valueList) {
		this.valueList = valueList;
	}
	/**
	 * @return the headerName
	 */
	public String getHeaderName() {
		return headerName;
	}
	/**
	 * @param headerName the headerName to set
	 */
	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}
	/**
	 * @return the valueOfHeader
	 */
	public String getValueOfHeader() {
		return valueOfHeader;
	}
	/**
	 * @param valueOfHeader the valueOfHeader to set
	 */
	public void setValueOfHeader(String valueOfHeader) {
		this.valueOfHeader = valueOfHeader;
	}
	

	
}
