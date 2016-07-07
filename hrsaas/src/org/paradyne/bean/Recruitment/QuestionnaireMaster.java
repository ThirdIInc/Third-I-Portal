/**
 * 
 */
package org.paradyne.bean.Recruitment;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * 
 * @author prakashs
 *
 */
public class QuestionnaireMaster extends BeanBase {
	private String modeLength="false";
	private String totalRecords="";
	private String quesCode;
	private String quesName;
	private String quesType;
	private String quesAttr;
	private String quesValue;
	private String Attribute;
	private String Value;
	private String subCode;
	private String subcode;
	private String srNo;
	private String myPage;
	private ArrayList list;
	private String hiddenEdit;
	private String hiddenCode;
	private String hdeleteOp;
	private String tableLength="0";
	private String confChkop="";
	private ArrayList questionlist;
	public ArrayList getQuestionlist() {
		return questionlist;
	}
	public void setQuestionlist(ArrayList questionlist) {
		this.questionlist = questionlist;
	}
	public String getQuesCode() {
		return quesCode;
	}
	public void setQuesCode(String quesCode) {
		this.quesCode = quesCode;
	}
	public String getQuesName() {
		return quesName;
	}
	public void setQuesName(String quesName) {
		this.quesName = quesName;
	}
	public String getQuesType() {
		return quesType;
	}
	public void setQuesType(String quesType) {
		this.quesType = quesType;
	}
	public String getAttribute() {
		return Attribute;
	}
	public void setAttribute(String attribute) {
		Attribute = attribute;
	}
	public String getValue() {
		return Value;
	}
	public void setValue(String value) {
		Value = value;
	}
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
	}
	public String getSubCode() {
		return subCode;
	}
	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}
	public String getSubcode() {
		return subcode;
	}
	public void setSubcode(String subcode) {
		this.subcode = subcode;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getQuesAttr() {
		return quesAttr;
	}
	public void setQuesAttr(String quesAttr) {
		this.quesAttr = quesAttr;
	}
	public String getQuesValue() {
		return quesValue;
	}
	public void setQuesValue(String quesValue) {
		this.quesValue = quesValue;
	}
	public String getTableLength() {
		return tableLength;
	}
	public void setTableLength(String tableLength) {
		this.tableLength = tableLength;
	}
	public String getHiddenEdit() {
		return hiddenEdit;
	}
	public void setHiddenEdit(String hiddenEdit) {
		this.hiddenEdit = hiddenEdit;
	}
	public String getHdeleteOp() {
		return hdeleteOp;
	}
	public void setHdeleteOp(String hdeleteOp) {
		this.hdeleteOp = hdeleteOp;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getHiddenCode() {
		return hiddenCode;
	}
	public void setHiddenCode(String hiddenCode) {
		this.hiddenCode = hiddenCode;
	}
	public String getConfChkop() {
		return confChkop;
	}
	public void setConfChkop(String confChkop) {
		this.confChkop = confChkop;
	}
	public String getModeLength() {
		return modeLength;
	}
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

}
