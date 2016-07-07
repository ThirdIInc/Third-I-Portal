package org.paradyne.bean.ApplicationStudio;

import java.util.ArrayList;
import java.util.HashMap;

import org.paradyne.lib.BeanBase;

public class EmailTemplate extends BeanBase {
	private String noqueryparameter = "";
	private String queryparameter = "";
	private String ittrnoqueryparameter = "";
	private String ittrqueryparameter = "";
	private String htmlcode = "";
	private String tempCode = "";
	private String tempContent = "";
	private String field = "";
	private String TemplateId = "";
	private String tempName = "";
	private String querytype="";
	private String field1="";
	private String qName = "";
	private String qtype="";
	private String qtype1="";
	private String qId = "";	
	private String queryName="";
	private String hiddenEdit="";
	private String srNo="";
	private ArrayList <EmailTemplate>list;
	private ArrayList <EmailTemplate>queryList;
	private String subcode="";
	private String tableLength="0";
	private String hiddenMode="";
	private String hdeleteOp="";
	private String fromMailId="";
	private String toMailId="";
	private String subject="";
	ArrayList iterate = null;
	ArrayList ittTable=null;
	ArrayList fromiterate = null;
	ArrayList toiterate = null;

	
	
	
	HashMap hashmapTable;
	HashMap hashmapTableSel;
	
	private String availTable="";
	private String selTable="";
	private String srNoOther="";
	
	private String moduleCode="";
	private String moduleName="";
	
	ArrayList listTable=null;
	private String listfield="";
	private String queryNo="";
	/**
	 * @return the htmlcode
	 */
	public String getHtmlcode() {
		return htmlcode;
	}
	/**
	 * @param htmlcode the htmlcode to set
	 */
	public void setHtmlcode(String htmlcode) {
		this.htmlcode = htmlcode;
	}
	/**
	 * @return the tempCode
	 */
	public String getTempCode() {
		return tempCode;
	}
	/**
	 * @param tempCode the tempCode to set
	 */
	public void setTempCode(String tempCode) {
		this.tempCode = tempCode;
	}
	/**
	 * @return the tempContent
	 */
	public String getTempContent() {
		return tempContent;
	}
	/**
	 * @param tempContent the tempContent to set
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
	 * @param field the field to set
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
	 * @param templateId the templateId to set
	 */
	public void setTemplateId(String templateId) {
		TemplateId = templateId;
	}
	/**
	 * @return the tempName
	 */
	public String getTempName() {
		return tempName;
	}
	/**
	 * @param tempName the tempName to set
	 */
	public void setTempName(String tempName) {
		this.tempName = tempName;
	}
	public String getQName() {
		return qName;
	}
	public void setQName(String name) {
		qName = name;
	}
	public String getQId() {
		return qId;
	}
	public void setQId(String id) {
		qId = id;
	}
	public String getQueryName() {
		return queryName;
	}
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
	public String getHiddenEdit() {
		return hiddenEdit;
	}
	public void setHiddenEdit(String hiddenEdit) {
		this.hiddenEdit = hiddenEdit;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public ArrayList <EmailTemplate>getList() {
		return list;
	}
	public void setList(ArrayList <EmailTemplate>list) {
		this.list = list;
	}
	public ArrayList getQueryList() {
		return queryList;
	}
	public void setQueryList(ArrayList queryList) {
		this.queryList = queryList;
	}
	public String getSubcode() {
		return subcode;
	}
	public void setSubcode(String subcode) {
		this.subcode = subcode;
	}
	public String getTableLength() {
		return tableLength;
	}
	public void setTableLength(String tableLength) {
		this.tableLength = tableLength;
	}
	public String getHiddenMode() {
		return hiddenMode;
	}
	public void setHiddenMode(String hiddenMode) {
		this.hiddenMode = hiddenMode;
	}
	public String getHdeleteOp() {
		return hdeleteOp;
	}
	public void setHdeleteOp(String hdeleteOp) {
		this.hdeleteOp = hdeleteOp;
	}
	public ArrayList getIterate() {
		return iterate;
	}
	public void setIterate(ArrayList iterate) {
		this.iterate = iterate;
	}
	public String getFromMailId() {
		return fromMailId;
	}
	public void setFromMailId(String fromMailId) {
		this.fromMailId = fromMailId;
	}
	public String getToMailId() {
		return toMailId;
	}
	public void setToMailId(String toMailId) {
		this.toMailId = toMailId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getQuerytype() {
		return querytype;
	}
	public void setQuerytype(String querytype) {
		this.querytype = querytype;
	}
	public String getField1() {
		return field1;
	}
	public void setField1(String field1) {
		this.field1 = field1;
	}
	public String getQtype() {
		return qtype;
	}
	public void setQtype(String qtype) {
		this.qtype = qtype;
	}
	public ArrayList getFromiterate() {
		return fromiterate;
	}
	public void setFromiterate(ArrayList fromiterate) {
		this.fromiterate = fromiterate;
	}
	public ArrayList getToiterate() {
		return toiterate;
	}
	public void setToiterate(ArrayList toiterate) {
		this.toiterate = toiterate;
	}
	public String getQtype1() {
		return qtype1;
	}
	public void setQtype1(String qtype1) {
		this.qtype1 = qtype1;
	}
	public HashMap getHashmapTable() {
		return hashmapTable;
	}
	public void setHashmapTable(HashMap hashmapTable) {
		this.hashmapTable = hashmapTable;
	}
	public HashMap getHashmapTableSel() {
		return hashmapTableSel;
	}
	public void setHashmapTableSel(HashMap hashmapTableSel) {
		this.hashmapTableSel = hashmapTableSel;
	}
	public String getAvailTable() {
		return availTable;
	}
	public void setAvailTable(String availTable) {
		this.availTable = availTable;
	}
	public String getSelTable() {
		return selTable;
	}
	public void setSelTable(String selTable) {
		this.selTable = selTable;
	}
	public ArrayList getIttTable() {
		return ittTable;
	}
	public void setIttTable(ArrayList ittTable) {
		this.ittTable = ittTable;
	}
	public String getSrNoOther() {
		return srNoOther;
	}
	public void setSrNoOther(String srNoOther) {
		this.srNoOther = srNoOther;
	}
	public String getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public ArrayList getListTable() {
		return listTable;
	}
	public void setListTable(ArrayList listTable) {
		this.listTable = listTable;
	}
	public String getListfield() {
		return listfield;
	}
	public void setListfield(String listfield) {
		this.listfield = listfield;
	}
	public String getQueryNo() {
		return queryNo;
	}
	public void setQueryNo(String queryNo) {
		this.queryNo = queryNo;
	}
	public String getNoqueryparameter() {
		return noqueryparameter;
	}
	public void setNoqueryparameter(String noqueryparameter) {
		this.noqueryparameter = noqueryparameter;
	}
	public String getQueryparameter() {
		return queryparameter;
	}
	public void setQueryparameter(String queryparameter) {
		this.queryparameter = queryparameter;
	}
	public String getIttrnoqueryparameter() {
		return ittrnoqueryparameter;
	}
	public void setIttrnoqueryparameter(String ittrnoqueryparameter) {
		this.ittrnoqueryparameter = ittrnoqueryparameter;
	}
	public String getIttrqueryparameter() {
		return ittrqueryparameter;
	}
	public void setIttrqueryparameter(String ittrqueryparameter) {
		this.ittrqueryparameter = ittrqueryparameter;
	}
}