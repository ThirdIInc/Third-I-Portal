/**
 * 
 */
package org.paradyne.bean.ApplicationStudio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0623
 *
 */
public class LetterTemplate extends BeanBase {
	
	private String htmlcode = "";
	private String tempCode = "";
	private String tempContent = "";
	private String field = "";
	private String TemplateId = ""; 
	private String tempName = "";
	private String dupTempName = "";
	private String qName = "";
	private String qId = "";
	private String qType="";
	private String queryName="";
	private String queryType="";
	private String hiddenEdit="";
	private String srNo="";
	private ArrayList list;
	private ArrayList queryList;
	private String subcode="";
	private String tableLength="0";
	private String hiddenMode="";
	private String hdeleteOp="";
	private String chk="";
	private String chkFlag="false";
	ArrayList<Object> iterate = null;
	ArrayList<Object> mainList = null;
	private String tempType="";
	private String templateType="";
	
	private HashMap statMap;

	private String templateOpt="";
	private TreeMap templateOptMap=null;
	private String templateOptFlag="false";
	
	private String templateOptSalary="";
	private TreeMap templateOptSalaryMap=null;
	private String templateOptSalaryFlag="false";
	
	private String noOfColumnItt="";
	private String noOfRowsItt="";
	private String colsWidthItt="";
	private String noOfColumn="";
	private String noOfRows="";
	private String cols_width="";
	ArrayList<Object> salaryItt = null;
	private String queryNameItt="";
	private String queryNameTab="";
	private String showOnLoadFlag="";
	private TreeMap tempTypeMap=null;
	
	//added by prashant on 6/09/2010
	private TreeMap letterTempMap = null;
	
	private String variableName;
	private String variableValue;
	private String variablePriority ;
	private String letterTempVar ;
	
	
	public TreeMap getTempTypeMap() {
		return tempTypeMap;
	}

	public void setTempTypeMap(TreeMap tempTypeMap) {
		this.tempTypeMap = tempTypeMap;
	}

	public String getShowOnLoadFlag() {
		return showOnLoadFlag;
	}

	public void setShowOnLoadFlag(String showOnLoadFlag) {
		this.showOnLoadFlag = showOnLoadFlag;
	}

	public String getQueryNameTab() {
		return queryNameTab;
	}

	public void setQueryNameTab(String queryNameTab) {
		this.queryNameTab = queryNameTab;
	}

	public String getQueryNameItt() {
		return queryNameItt;
	}

	public void setQueryNameItt(String queryNameItt) {
		this.queryNameItt = queryNameItt;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public String getTempType() {
		return tempType;
	}

	public void setTempType(String tempType) {
		this.tempType = tempType;
	}

	/**
	 * @return the iterate
	 */
	public ArrayList getIterate() {
		return iterate;
	}

	/**
	 * @param iterate the iterate to set
	 */
	

	/**
	 * @return the list
	 */
	public ArrayList getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(ArrayList list) {
		this.list = list;
	}

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

	
	public String getDupTempName() {
		return dupTempName;
	}

	public void setDupTempName(String dupTempName) {
		this.dupTempName = dupTempName;
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

	/**
	 * @return the qName
	 */
	public String getQName() {
		return qName;
	}

	/**
	 * @param name the qName to set
	 */
	public void setQName(String name) {
		qName = name;
	}

	/**
	 * @return the qId
	 */
	public String getQId() {
		return qId;
	}

	/**
	 * @param id the qId to set
	 */
	public void setQId(String id) {
		qId = id;
	}

	/**
	 * @return the srNo
	 */
	public String getSrNo() {
		return srNo;
	}

	/**
	 * @param srNo the srNo to set
	 */
	public void setSrNo(String srNo) {
		this.srNo = srNo;
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
	 * @return the hiddenEdit
	 */
	public String getHiddenEdit() {
		return hiddenEdit;
	}

	/**
	 * @param hiddenEdit the hiddenEdit to set
	 */
	public void setHiddenEdit(String hiddenEdit) {
		this.hiddenEdit = hiddenEdit;
	}

	/**
	 * @return the queryList
	 */
	public ArrayList getQueryList() {
		return queryList;
	}

	/**
	 * @param queryList the queryList to set
	 */
	public void setQueryList(ArrayList queryList) {
		this.queryList = queryList;
	}

	/**
	 * @return the subcode
	 */
	public String getSubcode() {
		return subcode;
	}

	/**
	 * @param subcode the subcode to set
	 */
	public void setSubcode(String subcode) {
		this.subcode = subcode;
	}

	/**
	 * @return the tableLength
	 */
	public String getTableLength() {
		return tableLength;
	}

	/**
	 * @param tableLength the tableLength to set
	 */
	public void setTableLength(String tableLength) {
		this.tableLength = tableLength;
	}

	/**
	 * @return the hiddenMode
	 */
	public String getHiddenMode() {
		return hiddenMode;
	}

	/**
	 * @param hiddenMode the hiddenMode to set
	 */
	public void setHiddenMode(String hiddenMode) {
		this.hiddenMode = hiddenMode;
	}

	/**
	 * @return the hdeleteOp
	 */
	public String getHdeleteOp() {
		return hdeleteOp;
	}

	/**
	 * @param hdeleteOp the hdeleteOp to set
	 */
	public void setHdeleteOp(String hdeleteOp) {
		this.hdeleteOp = hdeleteOp;
	}

	public String getQType() {
		return qType;
	}

	public void setQType(String type) {
		qType = type;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public ArrayList getMainList() {
		return mainList;
	}

	

	public String getChk() {
		return chk;
	}

	public void setChk(String chk) {
		this.chk = chk;
	}

	public void setIterate(ArrayList<Object> iterate) {
		this.iterate = iterate;
	}

	public void setMainList(ArrayList<Object> mainList) {
		this.mainList = mainList;
	}

	public String getChkFlag() {
		return chkFlag;
	}

	public void setChkFlag(String chkFlag) {
		this.chkFlag = chkFlag;
	}

	public HashMap getStatMap() {
		return statMap;
	}

	public void setStatMap(HashMap statMap) {
		this.statMap = statMap;
	}

	public String getTemplateOpt() {
		return templateOpt;
	}

	public void setTemplateOpt(String templateOpt) {
		this.templateOpt = templateOpt;
	}

	public TreeMap getTemplateOptMap() {
		return templateOptMap;
	}

	public void setTemplateOptMap(TreeMap templateOptMap) {
		this.templateOptMap = templateOptMap;
	}

	public String getTemplateOptFlag() {
		return templateOptFlag;
	}

	public void setTemplateOptFlag(String templateOptFlag) {
		this.templateOptFlag = templateOptFlag;
	}

	public String getTemplateOptSalary() {
		return templateOptSalary;
	}

	public void setTemplateOptSalary(String templateOptSalary) {
		this.templateOptSalary = templateOptSalary;
	}

	public TreeMap getTemplateOptSalaryMap() {
		return templateOptSalaryMap;
	}

	public void setTemplateOptSalaryMap(TreeMap templateOptSalaryMap) {
		this.templateOptSalaryMap = templateOptSalaryMap;
	}

	public String getTemplateOptSalaryFlag() {
		return templateOptSalaryFlag;
	}

	public void setTemplateOptSalaryFlag(String templateOptSalaryFlag) {
		this.templateOptSalaryFlag = templateOptSalaryFlag;
	}

	public String getNoOfColumnItt() {
		return noOfColumnItt;
	}

	public void setNoOfColumnItt(String noOfColumnItt) {
		this.noOfColumnItt = noOfColumnItt;
	}

	public String getNoOfRowsItt() {
		return noOfRowsItt;
	}

	public void setNoOfRowsItt(String noOfRowsItt) {
		this.noOfRowsItt = noOfRowsItt;
	}

	public String getNoOfColumn() {
		return noOfColumn;
	}

	public void setNoOfColumn(String noOfColumn) {
		this.noOfColumn = noOfColumn;
	}

	public String getNoOfRows() {
		return noOfRows;
	}

	public void setNoOfRows(String noOfRows) {
		this.noOfRows = noOfRows;
	}

	public ArrayList<Object> getSalaryItt() {
		return salaryItt;
	}

	public void setSalaryItt(ArrayList<Object> salaryItt) {
		this.salaryItt = salaryItt;
	}

	public TreeMap getLetterTempMap() {
		return letterTempMap;
	}

	public void setLetterTempMap(TreeMap letterTempMap) {
		this.letterTempMap = letterTempMap;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public String getVariableValue() {
		return variableValue;
	}

	public void setVariableValue(String variableValue) {
		this.variableValue = variableValue;
	}

	public String getVariablePriority() {
		return variablePriority;
	}

	public void setVariablePriority(String variablePriority) {
		this.variablePriority = variablePriority;
	}

	public String getLetterTempVar() {
		return letterTempVar;
	}

	public void setLetterTempVar(String letterTempVar) {
		this.letterTempVar = letterTempVar;
	}

	public String getCols_width() {
		return cols_width;
	}

	public void setCols_width(String cols_width) {
		this.cols_width = cols_width;
	}

	public String getColsWidthItt() {
		return colsWidthItt;
	}

	public void setColsWidthItt(String colsWidthItt) {
		this.colsWidthItt = colsWidthItt;
	}

	

}
