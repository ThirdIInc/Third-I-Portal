package org.paradyne.bean.Recruitment;

import java.util.List;

import org.paradyne.lib.BeanBase;

/**
 * @author Nilesh Dhandare
 * @modified by @author Prajakta.Bhandare
 * @date 05 march 2013
 */

public class InterviewAssessMaster extends BeanBase {
	/** paraName. *  */
	private String paraName = "";
	/** paraDes. *  */
	private String paraDes = "";
	/** isActive. *  */
	private String isActive = "";
	/** recassessCode. *  */
	private String recassessCode = "";
	/** modeLength. *  */
	private String modeLength = "";
	/** totalNoOfRecords. *  */
	private String totalNoOfRecords = "";
	/** myPage. *  */
	private String myPage = "";
	/** parameterCode. *  */
	private String parameterCode = "";
	/** hdeleteCode. *  */
	private String hdeleteCode = "";
	/** assesmentList. *  */
	private List<InterviewAssessMaster> assesmentList;
	/** interviewRecord. *  */
	private String interviewRecord;
	
	
	/** groupList. *  */
	private List<InterviewAssessMaster> groupList;
	/** groupCode. *  */
	private String groupCode = "";
	/** groupNameItr. *  */
	private String groupNameItr = "";
	/** groupDescriptionItr. *  */
	private String groupDescriptionItr = "";
	
	private String groupIsActiveItr = "";
	/** listLength. *  */
	private boolean listLength;
	
	/** groupName. *  */
	private String groupName = "";
	/** groupAbbreviation. *  */
	private String groupAbbreviation = "";
	/** groupDescription. *  */
	private String groupDescription = "";
	/** isGroupActive. *  */
	private String isGroupActive = "";
	
	/** parameterNameItr. *  */
	private String parameterNameItr = "";
	/** parameterDescriptionItr. *  */
	private String parameterDescriptionItr = "";
	/** parameterIsActiveItr. *  */
	private String parameterIsActiveItr = "";
	/** parameterIsActiveItr. *  */
	private List<InterviewAssessMaster> parameterList;
	/** parameterIsActiveItr. *  */
	private boolean dataPresent;
	/** parameterCodeItr. *  */
	private String parameterCodeItr = "";
	
	//Added By Prajakta B
	private String parameterListLength="";
	private String paraCode="";
	private String paraIsActive="";
	
	
	/**
	 * @return paraIsActive
	 */
	public String getParaIsActive() {
		return paraIsActive;
	}
	/**
	 * @param paraIsActive
	 * the paraIsActive to set
	 */
	public void setParaIsActive(String paraIsActive) {
		this.paraIsActive = paraIsActive;
	}
	/**
	 * @return paraCode
	 */
	public String getParaCode() {
		return paraCode;
	}
	/**
	 * @param paraCode
	 * the paraCode to set
	 */
	public void setParaCode(String paraCode) {
		this.paraCode = paraCode;
	}
	/**
	 * @return parameterListLength
	 */
	public String getParameterListLength() {
		return parameterListLength;
	}
	/**
	 * @param parameterListLength
	 * the parameterListLength to set
	 */
	public void setParameterListLength(String parameterListLength) {
		this.parameterListLength = parameterListLength;
	}
	/**
	 * @return the paraName
	 */
	public String getParaName() {
		return this.paraName;
	}
	/**
	 * @param paraName the paraName to set
	 */
	public void setParaName(final String paraName) {
		this.paraName = paraName;
	}
	/**
	 * @return the paraDes
	 */
	public String getParaDes() {
		return this.paraDes;
	}
	/**
	 * @param paraDes the paraDes to set
	 */
	public void setParaDes(final String paraDes) {
		this.paraDes = paraDes;
	}
	/**
	 * @return the isActive
	 */
	public String getIsActive() {
		return this.isActive;
	}
	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(final String isActive) {
		this.isActive = isActive;
	}
	/**
	 * @return the recassessCode
	 */
	public String getRecassessCode() {
		return this.recassessCode;
	}
	/**
	 * @param recassessCode the recassessCode to set
	 */
	public void setRecassessCode(final String recassessCode) {
		this.recassessCode = recassessCode;
	}
	/**
	 * @return the modeLength
	 */
	public String getModeLength() {
		return this.modeLength;
	}
	/**
	 * @param modeLength the modeLength to set
	 */
	public void setModeLength(final String modeLength) {
		this.modeLength = modeLength;
	}
	/**
	 * @return the totalNoOfRecords
	 */
	public String getTotalNoOfRecords() {
		return this.totalNoOfRecords;
	}
	/**
	 * @param totalNoOfRecords the totalNoOfRecords to set
	 */
	public void setTotalNoOfRecords(final String totalNoOfRecords) {
		this.totalNoOfRecords = totalNoOfRecords;
	}
	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return this.myPage;
	}
	/**
	 * @param myPage the myPage to set
	 */
	public void setMyPage(final String myPage) {
		this.myPage = myPage;
	}
	/**
	 * @return the parameterCode
	 */
	public String getParameterCode() {
		return this.parameterCode;
	}
	/**
	 * @param parameterCode the parameterCode to set
	 */
	public void setParameterCode(final String parameterCode) {
		this.parameterCode = parameterCode;
	}
	/**
	 * @return the hdeleteCode
	 */
	public String getHdeleteCode() {
		return this.hdeleteCode;
	}
	/**
	 * @param hdeleteCode the hdeleteCode to set
	 */
	public void setHdeleteCode(final String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	/**
	 * @return the assesmentList
	 */
	public List getAssesmentList() {
		return this.assesmentList;
	}
	/**
	 * @param assesmentList the assesmentList to set
	 */
	public void setAssesmentList(final List<InterviewAssessMaster> assesmentList) {
		this.assesmentList = assesmentList;
	}
	/**
	 * @return the interviewRecord
	 */
	public String getInterviewRecord() {
		return this.interviewRecord;
	}
	/**
	 * @param interviewRecord the interviewRecord to set
	 */
	public void setInterviewRecord(final String interviewRecord) {
		this.interviewRecord = interviewRecord;
	}
	 
	/**
	 * @return the groupList
	 */
	public List<InterviewAssessMaster> getGroupList() {
		return this.groupList;
	}
	/**
	 * @param groupList the groupList to set
	 */
	public  void setGroupList(final List<InterviewAssessMaster> groupList) {
		this.groupList = groupList;
	}
	/**
	 * @return the groupCode
	 */
	public String getGroupCode() {
		return this.groupCode;
	}
	/**
	 * @param groupCode the groupCode to set
	 */
	public void setGroupCode(final String groupCode) {
		this.groupCode = groupCode;
	}
	/**
	 * @return the groupNameItr
	 */
	public String getGroupNameItr() {
		return this.groupNameItr;
	}
	/**
	 * @param groupNameItr the groupNameItr to set
	 */
	public void setGroupNameItr(final String groupNameItr) {
		this.groupNameItr = groupNameItr;
	}
	/**
	 * @return the groupDescriptionItr
	 */
	public final String getGroupDescriptionItr() {
		return this.groupDescriptionItr;
	}
	/**
	 * @param groupDescriptionItr the groupDescriptionItr to set
	 */
	public void setGroupDescriptionItr(final String groupDescriptionItr) {
		this.groupDescriptionItr = groupDescriptionItr;
	}
	/**
	 * @return the listLength
	 */
	public boolean isListLength() {
		return this.listLength;
	}
	/**
	 * @param listLength the listLength to set
	 */
	public void setListLength(final boolean listLength) {
		this.listLength = listLength;
	}
	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return this.groupName;
	}
	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	/**
	 * @return the groupAbbreviation
	 */
	public String getGroupAbbreviation() {
		return this.groupAbbreviation;
	}
	/**
	 * @param groupAbbreviation the groupAbbreviation to set
	 */
	public void setGroupAbbreviation(String groupAbbreviation) {
		this.groupAbbreviation = groupAbbreviation;
	}
	/**
	 * @return the groupDescription
	 */
	public String getGroupDescription() {
		return this.groupDescription;
	}
	/**
	 * @param groupDescription the groupDescription to set
	 */
	public void setGroupDescription(final String groupDescription) {
		this.groupDescription = groupDescription;
	}
	/**
	 * @return the isGroupActive
	 */
	public final String getIsGroupActive() {
		return this.isGroupActive;
	}
	/**
	 * @param isGroupActive the isGroupActive to set
	 */
	public void setIsGroupActive(final String isGroupActive) {
		this.isGroupActive = isGroupActive;
	}
	/**
	 * @return the parameterNameItr
	 */
	public final String getParameterNameItr() {
		return this.parameterNameItr;
	}
	/**
	 * @param parameterNameItr the parameterNameItr to set
	 */
	public void setParameterNameItr(final String parameterNameItr) {
		this.parameterNameItr = parameterNameItr;
	}
	/**
	 * @return the parameterDescriptionItr
	 */
	public String getParameterDescriptionItr() {
		return this.parameterDescriptionItr;
	}
	/**
	 * @param parameterDescriptionItr the parameterDescriptionItr to set
	 */
	public void setParameterDescriptionItr(final String parameterDescriptionItr) {
		this.parameterDescriptionItr = parameterDescriptionItr;
	}
	/**
	 * @return the parameterIsActiveItr
	 */
	public String getParameterIsActiveItr() {
		return this.parameterIsActiveItr;
	}
	/**
	 * @param parameterIsActiveItr the parameterIsActiveItr to set
	 */
	public void setParameterIsActiveItr(final String parameterIsActiveItr) {
		this.parameterIsActiveItr = parameterIsActiveItr;
	}
	/**
	 * @return the parameterList
	 */
	public final List<InterviewAssessMaster> getParameterList() {
		return this.parameterList;
	}
	/**
	 * @param parameterList the parameterList to set
	 */
	public final void setParameterList(List<InterviewAssessMaster> parameterList) {
		this.parameterList = parameterList;
	}
	/**
	 * @return the dataPresent
	 */
	public final boolean isDataPresent() {
		return this.dataPresent;
	}
	/**
	 * @param dataPresent the dataPresent to set
	 */
	public final void setDataPresent(boolean dataPresent) {
		this.dataPresent = dataPresent;
	}
	/**
	 * @return the parameterCodeItr
	 */
	public final String getParameterCodeItr() {
		return this.parameterCodeItr;
	}
	/**
	 * @param parameterCodeItr the parameterCodeItr to set
	 */
	public final void setParameterCodeItr(String parameterCodeItr) {
		this.parameterCodeItr = parameterCodeItr;
	}
	public String getGroupIsActiveItr() {
		return groupIsActiveItr;
	}
	public void setGroupIsActiveItr(String groupIsActiveItr) {
		this.groupIsActiveItr = groupIsActiveItr;
	}
	 
}
