package org.paradyne.bean.common;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * 
 * @author KRISHNA
 * 
 * Date:06-11-2008
 * 
 */
public class TemplateMaster extends BeanBase {

	String htmlcode = "";
	String tempId = "";
	String tempContent = "";
	String field = "";
	String TemplateId = "";
	String EmpName = "";
	String EmpId = "";
	String QueryContent = "";
	String qId = "";
	String qName = "";
	String tempName = "";
	String queryId = "";
	// for generating dynamic labels
	private String paraName;
	private String paraValue;
	private ArrayList paraList;
	ArrayList iterate = null;

	/**
	 * @return the html code
	 */
	public String getHtmlcode() {
		return htmlcode;
	}

	/**
	 * @param htmlcode
	 *            the htmlcode to set
	 */
	public void setHtmlcode(String htmlcode) {
		this.htmlcode = htmlcode;
	}

	/**
	 * @return the tempId
	 */
	public String getTempId() {
		return tempId;
	}

	/**
	 * @param tempId
	 *            the tempId to set
	 */
	public void setTempId(String tempId) {
		this.tempId = tempId;
	}

	/**
	 * @return the tempContent
	 */
	public String getTempContent() {
		return tempContent;
	}

	/**
	 * @param tempContent
	 *            the tempContent to set
	 */
	public void setTempContent(String tempContent) {
		this.tempContent = tempContent;
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field
	 *            the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * @return the templateId
	 */
	public String getTemplateId() {
		return TemplateId;
	}

	/**
	 * @param templateId
	 *            the templateId to set
	 */
	public void setTemplateId(String templateId) {
		TemplateId = templateId;
	}

	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return EmpName;
	}

	/**
	 * @param empName
	 *            the empName to set
	 */
	public void setEmpName(String empName) {
		EmpName = empName;
	}

	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return EmpId;
	}

	/**
	 * @param empId
	 *            the empId to set
	 */
	public void setEmpId(String empId) {
		EmpId = empId;
	}

	/**
	 * @return the queryContent
	 */
	public String getQueryContent() {
		return QueryContent;
	}

	/**
	 * @param queryContent
	 *            the queryContent to set
	 */
	public void setQueryContent(String queryContent) {
		QueryContent = queryContent;
	}

	/**
	 * @return the qId
	 */
	public String getQId() {
		return qId;
	}

	/**
	 * @param id
	 *            the qId to set
	 */
	public void setQId(String id) {
		qId = id;
	}

	/**
	 * @return the qName
	 */
	public String getQName() {
		return qName;
	}

	/**
	 * @param name
	 *            the qName to set
	 */
	public void setQName(String name) {
		qName = name;
	}

	/**
	 * @return the iterate
	 */
	public ArrayList getIterate() {
		return iterate;
	}

	/**
	 * @param iterate
	 *            the iterate to set
	 */
	public void setIterate(ArrayList iterate) {
		this.iterate = iterate;
	}

	/**
	 * @return the tempName
	 */
	public String getTempName() {
		return tempName;
	}

	/**
	 * @param tempName
	 *            the tempName to set
	 */
	public void setTempName(String tempName) {
		this.tempName = tempName;
	}

	/**
	 * @return the queryId
	 */
	public String getQueryId() {
		return queryId;
	}

	/**
	 * @param queryId
	 *            the queryId to set
	 */
	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	/**
	 * @return the paraName
	 */
	public String getParaName() {
		return paraName;
	}

	/**
	 * @param paraName
	 *            the paraName to set
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
	 * @param paraValue
	 *            the paraValue to set
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
	 * @param paraList
	 *            the paraList to set
	 */
	public void setParaList(ArrayList paraList) {
		this.paraList = paraList;
	}

}
