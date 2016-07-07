package org.paradyne.bean.probation;

import java.util.List;

import org.paradyne.bean.D1.BusinessUnitBean;
import org.paradyne.lib.BeanBase;

public class ProbationEvaluationParameterBean extends BeanBase {

	/**
	 * Form Fields
	 */

	/**
	 * Probation Code hidden.
	 */
	private String probationCode = "";
	/**
	 * Question Name.
	 */
	private String questionName = "";
	/**
	 * Question Attribute.
	 */
	private String questionAttr = "";
	/**
	 * Attribute Value.
	 */
	private String attributeValue = "";

	/**
	 * List Related Fields.
	 */

	private String editDataId = ""; //For edit button id.

	/**
	 * Unit Hdelete Code hidden field.*/

	private String hdeleteCode = "";
	/**
	 *Paging field.*/

	private String myPage = "";
	/**
	 * Mode Length Paging purpose.*/

	private String modeLength = "";
	/**
	 * Total No of Records.*/

	private String totalNoOfRecords = "";
	/**
	 * Displaying List.*/

	private List<Object> probEvaluationList;
	private List<Object> probevalList;
	
	private String probationItemCode = ""; 
	private String modifyId = "";
	
	
	//Unused fields.
	private String srNo = ""; 
	private String Attribute = "";
	private String Value = "";
	
	private String hiddenEdit ="";
	
	
	
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
	 * @return the probationCode
	 */
	public String getProbationCode() {
		return probationCode;
	}
	/**
	 * @param probationCode the probationCode to set
	 */
	public void setProbationCode(String probationCode) {
		this.probationCode = probationCode;
	}
	/**
	 * @return the questionName
	 */
	public String getQuestionName() {
		return questionName;
	}
	/**
	 * @param questionName the questionName to set
	 */
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	/**
	 * @return the questionAttr
	 */
	public String getQuestionAttr() {
		return questionAttr;
	}
	/**
	 * @param questionAttr the questionAttr to set
	 */
	public void setQuestionAttr(String questionAttr) {
		this.questionAttr = questionAttr;
	}
	/**
	 * @return the attributeValue
	 */
	public String getAttributeValue() {
		return attributeValue;
	}
	/**
	 * @param attributeValue the attributeValue to set
	 */
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
	/**
	 * @return the editDataId
	 */
	public String getEditDataId() {
		return editDataId;
	}
	/**
	 * @param editDataId the editDataId to set
	 */
	public void setEditDataId(String editDataId) {
		this.editDataId = editDataId;
	}
	/**
	 * @return the hdeleteCode
	 */
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	/**
	 * @param hdeleteCode the hdeleteCode to set
	 */
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return myPage;
	}
	/**
	 * @param myPage the myPage to set
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	/**
	 * @return the modeLength
	 */
	public String getModeLength() {
		return modeLength;
	}
	/**
	 * @param modeLength the modeLength to set
	 */
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
	/**
	 * @return the totalNoOfRecords
	 */
	public String getTotalNoOfRecords() {
		return totalNoOfRecords;
	}
	/**
	 * @param totalNoOfRecords the totalNoOfRecords to set
	 */
	public void setTotalNoOfRecords(String totalNoOfRecords) {
		this.totalNoOfRecords = totalNoOfRecords;
	}
	/**
	 * @return the probEvaluationList
	 */
	public List<Object> getProbEvaluationList() {
		return probEvaluationList;
	}
	/**
	 * @param probEvaluationList the probEvaluationList to set
	 */
	public void setProbEvaluationList(
			List<Object> probEvaluationList) {
		this.probEvaluationList = probEvaluationList;
	}
	/**
	 * @return the probevalList
	 */
	public List<Object> getProbevalList() {
		return probevalList;
	}
	/**
	 * @param probevalList the probevalList to set
	 */
	public void setProbevalList(List<Object> probevalList) {
		this.probevalList = probevalList;
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
	 * @return the attribute
	 */
	public String getAttribute() {
		return Attribute;
	}
	/**
	 * @param attribute the attribute to set
	 */
	public void setAttribute(String attribute) {
		Attribute = attribute;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return Value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		Value = value;
	}
	/**
	 * @return the probationItemCode
	 */
	public String getProbationItemCode() {
		return probationItemCode;
	}
	/**
	 * @param probationItemCode the probationItemCode to set
	 */
	public void setProbationItemCode(String probationItemCode) {
		this.probationItemCode = probationItemCode;
	}
	
	/**
	 * @return the modifyId
	 */
	public String getModifyId() {
		return modifyId;
	}
	/**
	 * @param modifyId the modifyId to set
	 */
	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}
	

}
