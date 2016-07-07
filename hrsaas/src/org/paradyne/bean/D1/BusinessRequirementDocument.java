package org.paradyne.bean.D1;

import java.util.List;
import java.util.TreeMap;
import org.paradyne.lib.BeanBase;

/**
 * @modified By AA1711
 * @purpose : Change Request  
 */

public class BusinessRequirementDocument extends BeanBase {

	private static final String FALSE = "false";
	private String brdSuperUserFlag = "";
	private String priority = "";
	private boolean projectCommentsFlag = false;
	private String cancelProjectComments = "";
	private String listType = "";
	private List<BusinessRequirementDocument> uploadDocumentIterator;
	private String uploadFileNameItr = "";
	private List<BusinessRequirementDocument> initiatorUploadDocIterator;
	private String initiatorUploadDocNameItr = "";
	private List<BusinessRequirementDocument> forwardedApproverUploadDocActivityLogIterator;
	private String forwardedApprActivityLogUploadDocItr = "";
	private String brdNumber = "";
	private String projectName = "";
	private String startDate = "";
	private String expectedEndDate = "";
	private String allocatedBudget = "";
	private String businessObjective = "";
	private String projectClosureCriteria = "";
	private String constraints = "";
	private String assuptions = "";
	private String serialNo = "";
	private String serialNoStake = "";
	private String employeeId = "";
	private String employeeToken = "";
	private String employeeName = "";
	private String empid = "";
	private String stakeholderEmpTokenItt = "";
	private String stakeholderEmpNameItt = "";
	private List<BusinessRequirementDocument> stakeHoldersList;
	private String checkRemove = "";
	private TreeMap<String, String> mapState;
	private TreeMap<String, String> mapDoc;
	private TreeMap<String, String> mapRole;
	private String docType = "";
	private String uploadFileName = "";
	private String selectRole = "";
	private String forwardEmpToken = "";
	private String forwardEmpName = "";
	private String fwdempCode = "";
	private String completedByID = "";
	private String completedBy = "";
	private String completedDate = "";
	private String modeLength = "";
	private String totalNoOfRecords = "";
	private String forMyActionPage = "";
	private String myongoingProjectPage = "";
	private String myCloseProjectPage = "";
	private String mySendBackProjectPage = "";
	private boolean formyActionListLength;
	private boolean formyOngoingListLength;
	private boolean formyClosedListLength;
	private boolean formySendbackListLength;
	private boolean myCancelListLength;
	private List<BusinessRequirementDocument> myCancelList;
	private String myCancelProjectPage = "";
	private List<BusinessRequirementDocument> forMyActionList;
	private List<BusinessRequirementDocument> forMyOngoingList;
	private List<BusinessRequirementDocument> forMyClosedList;
	private List<BusinessRequirementDocument> forMySendbackList;
	private String brdCode = "";
	private String attachFile = "";
	private String dataPath = "";
	private String status = "";
	private String hiddenStatus = "";
	private String displayFlag = "false";
	private String empFlag = "false";
	private String currentStage = "";
	private String closedDate = "";
	private String closedBy = "";
	private String comments = "";
	private String uploadDocName = "";
	private String documentAttached = "";
	private String currentState = "";
	private String empRole = "";
	private String brdAppCodeItt = "";
	private String concernedPerson = "";
	private String applDate = "";
	private String logState = "";
	private String empComments = "";
	private String applnStatus = "";
	private String commentFlag = "false";
	private List<BusinessRequirementDocument> logList;
	private boolean activityList;
	private String roleFlag = "false";
	private String stageFlag = "false";
	private String docFlag = "false";
	private String viewCode = "";
	private String appCode = "";
	private String buttonFlag = "false";
	private String initiatorName = "";
	private String fileName = "";
	private String logFileName = "";
	private List<BusinessRequirementDocument> initiatorList;
	private String activityOwnerName = "";
	private boolean activityOwnerPresentFlag = false;
	private String empCode = "";
	private String empName = "";
	private String empToken = "";
	private String serialNum = "";
	private String empIttId = "";
	private String stakeholderAppEmpNameItt = "";
	private String checkAppRemove = "";
	private String stakeHolderFlag = "false";
	private String businessFlag = "false";
	private String closureFlag = "false";
	private String constraintsFlag = "false";
	private String assumptionFlag = "false";
	private String applicantCommnetsFlag = "false";
	private String applicantEditableFlag = "";
	private String initiatorListFlag = "false";
	private String closeStatus = "";
	private String inProcessStatus = "";
	private String noFileAttachedFlag = "";
	private String noInitiatorFileAttachedFlag = "";
	private String removeButtonFlag = "false";
	private List<BusinessRequirementDocument> approverStakeHoldersList;
	private String applicantsComments = "";
	private String foreCastedDateItr = "";
	private String currentActivityStatus = "";
	private String forecastedCompletionDate = "";
	private String currentActivityStatusItr = "";
	private String myPage = "";
	private TreeMap<String, String> mapProjectType;
	private TreeMap<String, String> mapProjectClassification;
	private String projectType = "";
	private String projectClassification = "";
	private String projectAnnualFinancialBenefit = "";
	/**Fields for new Change Request*/
	private String businessUnit = "";
	private TreeMap<String, String> mapBusinessUnitID;
	private String businessUnitID = "";
	private String rank = "";
	private boolean forwardStatus = false;
	private String projectTypeOwnerFlag = "false";

	/**
	 * @return projectType
	 */
	public String getProjectType() {
		return this.projectType;
	}

	/**
	 * @param projectType to set projectType
	 */
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	/**
	 * @return projectClassification
	 */
	public String getProjectClassification() {
		return this.projectClassification;
	}

	/**
	 * @param projectClassification to set projectClassification
	 */
	public void setProjectClassification(String projectClassification) {
		this.projectClassification = projectClassification;
	}

	/**
	 * @return projectAnnualFinancialBenefit
	 */
	public String getProjectAnnualFinancialBenefit() {
		return this.projectAnnualFinancialBenefit;
	}

	/**
	 * @param projectAnnualFinancialBenefit to set projectAnnualFinancialBenefit
	 */
	public void setProjectAnnualFinancialBenefit(
			String projectAnnualFinancialBenefit) {
		this.projectAnnualFinancialBenefit = projectAnnualFinancialBenefit;
	}

	/**
	 * @return currentActivityStatus
	 */
	public String getCurrentActivityStatus() {
		return this.currentActivityStatus;
	}

	/**
	 * @param currentActivityStatus to set currentActivityStatus
	 */
	public void setCurrentActivityStatus(String currentActivityStatus) {
		this.currentActivityStatus = currentActivityStatus;
	}

	/**
	 * @return forecastedCompletionDate
	 */
	public String getForecastedCompletionDate() {
		return this.forecastedCompletionDate;
	}

	/**
	 * @param forecastedCompletionDate to set forecastedCompletionDate
	 */
	public void setForecastedCompletionDate(String forecastedCompletionDate) {
		this.forecastedCompletionDate = forecastedCompletionDate;
	}

	/**
	 * @return initiatorName
	 */
	public String getInitiatorName() {
		return this.initiatorName;
	}

	/**
	 * @param initiatorName to set initiatorName
	 */
	public void setInitiatorName(String initiatorName) {
		this.initiatorName = initiatorName;
	}

	/**
	 * @return mapState
	 */
	public TreeMap<String, String> getMapState() {
		return this.mapState;
	}

	/**
	 * @param mapState to set mapState
	 */
	public void setMapState(TreeMap<String, String> mapState) {
		this.mapState = mapState;
	}

	/**
	 * @return concernedPerson
	 */
	public String getConcernedPerson() {
		return this.concernedPerson;
	}

	/**
	 * @param concernedPerson to set concernedPerson
	 */
	public void setConcernedPerson(String concernedPerson) {
		this.concernedPerson = concernedPerson;
	}

	/**
	 * @return applDate
	 */
	public String getApplDate() {
		return this.applDate;
	}

	/**
	 * @param applDate to set applDate
	 */
	public void setApplDate(String applDate) {
		this.applDate = applDate;
	}

	/**
	 * @return logState
	 */
	public String getLogState() {
		return this.logState;
	}

	/**
	 * @param logState to set logState
	 */
	public void setLogState(String logState) {
		this.logState = logState;
	}

	/**
	 * @return empComments
	 */
	public String getEmpComments() {
		return this.empComments;
	}

	/**
	 * @param empComments to set empComments
	 */
	public void setEmpComments(String empComments) {
		this.empComments = empComments;
	}

	/**
	 * @return currentStage
	 */
	public String getCurrentStage() {
		return this.currentStage;
	}

	/**
	 * @param currentStage to set currentStage
	 */
	public void setCurrentStage(String currentStage) {
		this.currentStage = currentStage;
	}

	/**
	 * @return displayFlag
	 */
	public String getDisplayFlag() {
		return this.displayFlag;
	}

	/**
	 * @param displayFlag to set displayFlag
	 */
	public void setDisplayFlag(String displayFlag) {
		this.displayFlag = displayFlag;
	}

	/**
	 * @return hiddenStatus
	 */
	public String getHiddenStatus() {
		return this.hiddenStatus;
	}

	/**
	 * @param hiddenStatus to set hiddenStatus
	 */
	public void setHiddenStatus(String hiddenStatus) {
		this.hiddenStatus = hiddenStatus;
	}

	/**
	 * @return status
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * @param status to set status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return brdNumber
	 */
	public String getBrdNumber() {
		return this.brdNumber;
	}

	/**
	 * @param brdNumber to set brdNumber
	 */
	public void setBrdNumber(String brdNumber) {
		this.brdNumber = brdNumber;
	}

	/**
	 * @return projectName
	 */
	public String getProjectName() {
		return this.projectName;
	}

	/**
	 * @param projectName to set projectName
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return startDate
	 */
	public String getStartDate() {
		return this.startDate;
	}

	/**
	 * @param startDate to set startDate
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return expectedEndDate
	 */
	public String getExpectedEndDate() {
		return this.expectedEndDate;
	}

	/**
	 * @param expectedEndDate to set expectedEndDate
	 */
	public void setExpectedEndDate(String expectedEndDate) {
		this.expectedEndDate = expectedEndDate;
	}

	/**
	 * @return businessObjective
	 */
	public String getBusinessObjective() {
		return this.businessObjective;
	}

	/**
	 * @param businessObjective to set businessObjective
	 */
	public void setBusinessObjective(String businessObjective) {
		this.businessObjective = businessObjective;
	}

	/**
	 * @return projectClosureCriteria
	 */
	public String getProjectClosureCriteria() {
		return this.projectClosureCriteria;
	}

	/**
	 * @param projectClosureCriteria to set projectClosureCriteria
	 */
	public void setProjectClosureCriteria(String projectClosureCriteria) {
		this.projectClosureCriteria = projectClosureCriteria;
	}

	/**
	 * @return constraints
	 */
	public String getConstraints() {
		return this.constraints;
	}

	/**
	 * @param constraints to set constraints
	 */
	public void setConstraints(String constraints) {
		this.constraints = constraints;
	}

	/**
	 * @return assuptions
	 */
	public String getAssuptions() {
		return this.assuptions;
	}

	/**
	 * @param assuptions to set assuptions
	 */
	public void setAssuptions(String assuptions) {
		this.assuptions = assuptions;
	}

	/**
	 * @return docType
	 */
	public String getDocType() {
		return this.docType;
	}

	/**
	 * @param docType to set docType
	 */
	public void setDocType(String docType) {
		this.docType = docType;
	}

	/**
	 * @return uploadFileName
	 */
	public String getUploadFileName() {
		return this.uploadFileName;
	}

	/**
	 * @param uploadFileName to set uploadFileName
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/**
	 * @return selectRole
	 */
	public String getSelectRole() {
		return this.selectRole;
	}

	/**
	 * @param selectRole to set selectRole
	 */
	public void setSelectRole(String selectRole) {
		this.selectRole = selectRole;
	}

	/**
	 * @return forwardEmpToken
	 */
	public String getForwardEmpToken() {
		return this.forwardEmpToken;
	}

	/**
	 * @param forwardEmpToken to set forwardEmpToken
	 */
	public void setForwardEmpToken(String forwardEmpToken) {
		this.forwardEmpToken = forwardEmpToken;
	}

	/**
	 * @return forwardEmpName
	 */
	public String getForwardEmpName() {
		return this.forwardEmpName;
	}

	/**
	 * @param forwardEmpName to set forwardEmpName
	 */
	public void setForwardEmpName(String forwardEmpName) {
		this.forwardEmpName = forwardEmpName;
	}

	/**
	 * @return fwdempCode
	 */
	public String getFwdempCode() {
		return this.fwdempCode;
	}

	/**
	 * @param fwdempCode to set fwdempCode
	 */
	public void setFwdempCode(String fwdempCode) {
		this.fwdempCode = fwdempCode;
	}

	/**
	 * @return completedByID
	 */
	public String getCompletedByID() {
		return this.completedByID;
	}

	/**
	 * @param completedByID to set completedByID
	 */
	public void setCompletedByID(String completedByID) {
		this.completedByID = completedByID;
	}

	/**
	 * @return completedBy
	 */
	public String getCompletedBy() {
		return this.completedBy;
	}

	/**
	 * @param completedBy to set completedBy
	 */
	public void setCompletedBy(String completedBy) {
		this.completedBy = completedBy;
	}

	/**
	 * @return completedDate
	 */
	public String getCompletedDate() {
		return this.completedDate;
	}

	/**
	 * @param completedDate to set completedDate
	 */
	public void setCompletedDate(String completedDate) {
		this.completedDate = completedDate;
	}

	/**
	 * @return brdCode
	 */
	public String getBrdCode() {
		return this.brdCode;
	}

	/**
	 * @param brdCode to set brdCode
	 */
	public void setBrdCode(String brdCode) {
		this.brdCode = brdCode;
	}

	/**
	 * @return attachFile
	 */
	public String getAttachFile() {
		return this.attachFile;
	}

	/**
	 * @param attachFile to set attachFile
	 */
	public void setAttachFile(String attachFile) {
		this.attachFile = attachFile;
	}

	/**
	 * @return dataPath
	 */
	public String getDataPath() {
		return this.dataPath;
	}

	/**
	 * @param dataPath to set dataPath
	 */
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}

	/**
	 * @return modeLength
	 */
	public String getModeLength() {
		return this.modeLength;
	}

	/**
	 * @param modeLength to set modeLength
	 */
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}

	/**
	 * @return totalNoOfRecords
	 */
	public String getTotalNoOfRecords() {
		return this.totalNoOfRecords;
	}

	/**
	 * @param totalNoOfRecords to set totalNoOfRecords
	 */
	public void setTotalNoOfRecords(String totalNoOfRecords) {
		this.totalNoOfRecords = totalNoOfRecords;
	}

	/**
	 * @return forMyActionPage
	 */
	public String getForMyActionPage() {
		return this.forMyActionPage;
	}

	/**
	 * @param forMyActionPage to set forMyActionPage
	 */
	public void setForMyActionPage(String forMyActionPage) {
		this.forMyActionPage = forMyActionPage;
	}

	/**
	 * @return myongoingProjectPage
	 */
	public String getMyongoingProjectPage() {
		return this.myongoingProjectPage;
	}

	/**
	 * @param myongoingProjectPage to set myongoingProjectPage
	 */
	public void setMyongoingProjectPage(String myongoingProjectPage) {
		this.myongoingProjectPage = myongoingProjectPage;
	}

	/**
	 * @return myCloseProjectPage
	 */
	public String getMyCloseProjectPage() {
		return this.myCloseProjectPage;
	}

	/**
	 * @param myCloseProjectPage to set myCloseProjectPage
	 */
	public void setMyCloseProjectPage(String myCloseProjectPage) {
		this.myCloseProjectPage = myCloseProjectPage;
	}

	/**
	 * @return formyActionListLength
	 */
	public boolean isFormyActionListLength() {
		return this.formyActionListLength;
	}

	/**
	 * @param formyActionListLength to set formyActionListLength
	 */
	public void setFormyActionListLength(boolean formyActionListLength) {
		this.formyActionListLength = formyActionListLength;
	}

	/**
	 * @return formyOngoingListLength
	 */
	public boolean isFormyOngoingListLength() {
		return this.formyOngoingListLength;
	}

	/**
	 * @param formyOngoingListLength to set formyOngoingListLength
	 */
	public void setFormyOngoingListLength(boolean formyOngoingListLength) {
		this.formyOngoingListLength = formyOngoingListLength;
	}

	/**
	 * @return formyClosedListLength
	 */
	public boolean isFormyClosedListLength() {
		return this.formyClosedListLength;
	}

	/**
	 * @param formyClosedListLength to set formyClosedListLength
	 */
	public void setFormyClosedListLength(boolean formyClosedListLength) {
		this.formyClosedListLength = formyClosedListLength;
	}

	/**
	 * @return forMyActionList
	 */
	public List<BusinessRequirementDocument> getForMyActionList() {
		return this.forMyActionList;
	}

	/**
	 * @param forMyActionList to set forMyActionList
	 */
	public void setForMyActionList(
			List<BusinessRequirementDocument> forMyActionList) {
		this.forMyActionList = forMyActionList;
	}

	/**
	 * @return forMyOngoingList
	 */
	public List<BusinessRequirementDocument> getForMyOngoingList() {
		return this.forMyOngoingList;
	}

	/**
	 * @param forMyOngoingList to set forMyOngoingList
	 */
	public void setForMyOngoingList(
			List<BusinessRequirementDocument> forMyOngoingList) {
		this.forMyOngoingList = forMyOngoingList;
	}

	/**
	 * @return forMyClosedList
	 */
	public List<BusinessRequirementDocument> getForMyClosedList() {
		return this.forMyClosedList;
	}

	/**
	 * @param forMyClosedList to set forMyClosedList
	 */
	public void setForMyClosedList(
			List<BusinessRequirementDocument> forMyClosedList) {
		this.forMyClosedList = forMyClosedList;
	}

	/**
	 * @return allocatedBudget
	 */
	public String getAllocatedBudget() {
		return this.allocatedBudget;
	}

	/**
	 * @param allocatedBudget to set allocatedBudget
	 */
	public void setAllocatedBudget(String allocatedBudget) {
		this.allocatedBudget = allocatedBudget;
	}

	/**
	 * @return employeeId
	 */
	public String getEmployeeId() {
		return this.employeeId;
	}

	/**
	 * @param employeeId to set employeeId
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return employeeToken
	 */
	public String getEmployeeToken() {
		return this.employeeToken;
	}

	/**
	 * @param employeeToken to set employeeToken
	 */
	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}

	/**
	 * @return employeeName
	 */
	public String getEmployeeName() {
		return this.employeeName;
	}

	/**
	 * @param employeeName to set employeeName
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	/**
	 * @return empid
	 */
	public String getEmpid() {
		return this.empid;
	}

	/**
	 * @param empid to set empid
	 */
	public void setEmpid(String empid) {
		this.empid = empid;
	}

	/**
	 * @return serialNo
	 */
	public String getSerialNo() {
		return this.serialNo;
	}

	/**
	 * @param serialNo to set serialNo
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	/**
	 * @return stakeholderEmpTokenItt
	 */
	public String getStakeholderEmpTokenItt() {
		return this.stakeholderEmpTokenItt;
	}

	/**
	 * @param stakeholderEmpTokenItt to set stakeholderEmpTokenItt
	 */
	public void setStakeholderEmpTokenItt(String stakeholderEmpTokenItt) {
		this.stakeholderEmpTokenItt = stakeholderEmpTokenItt;
	}

	/**
	 * @return stakeholderEmpNameItt
	 */
	public String getStakeholderEmpNameItt() {
		return this.stakeholderEmpNameItt;
	}

	/**
	 * @param stakeholderEmpNameItt to set stakeholderEmpNameItt
	 */
	public void setStakeholderEmpNameItt(String stakeholderEmpNameItt) {
		this.stakeholderEmpNameItt = stakeholderEmpNameItt;
	}

	/**
	 * @return stakeHoldersList
	 */
	public List<BusinessRequirementDocument> getStakeHoldersList() {
		return this.stakeHoldersList;
	}

	/**
	 * @param stakeHoldersList to set stakeHoldersList
	 */
	public void setStakeHoldersList(
			List<BusinessRequirementDocument> stakeHoldersList) {
		this.stakeHoldersList = stakeHoldersList;
	}

	/**
	 * @return checkRemove
	 */
	public String getCheckRemove() {
		return this.checkRemove;
	}

	/**
	 * @param checkRemove to set checkRemove
	 */
	public void setCheckRemove(String checkRemove) {
		this.checkRemove = checkRemove;
	}

	/**
	 * @return closedDate
	 */
	public String getClosedDate() {
		return this.closedDate;
	}

	/**
	 * @param closedDate to set closedDate
	 */
	public void setClosedDate(String closedDate) {
		this.closedDate = closedDate;
	}

	/**
	 * @return comments
	 */
	public String getComments() {
		return this.comments;
	}

	/**
	 * @param comments to set comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return closedBy
	 */
	public String getClosedBy() {
		return this.closedBy;
	}

	/**
	 * @param closedBy to set closedBy
	 */
	public void setClosedBy(String closedBy) {
		this.closedBy = closedBy;
	}

	/**
	 * @return logList
	 */
	public List<BusinessRequirementDocument> getLogList() {
		return this.logList;
	}

	/**
	 * @param logList to set logList
	 */
	public void setLogList(List<BusinessRequirementDocument> logList) {
		this.logList = logList;
	}

	/**
	 * @return roleFlag
	 */ 
	public String getRoleFlag() {
		return this.roleFlag;
	}

	/**
	 * @param roleFlag to set roleFlag
	 */
	public void setRoleFlag(String roleFlag) {
		this.roleFlag = roleFlag;
	}

	/**
	 * @return stageFlag
	 */
	public String getStageFlag() {
		return this.stageFlag;
	}

	/**
	 * @param stageFlag to set stageFlag
	 */
	public void setStageFlag(String stageFlag) {
		this.stageFlag = stageFlag;
	}

	/**
	 * @return docFlag
	 */
	public String getDocFlag() {
		return this.docFlag;
	}

	/**
	 * @param docFlag to set docFlag
	 */
	public void setDocFlag(String docFlag) {
		this.docFlag = docFlag;
	}

	/**
	 * @return mapDoc
	 */
	public TreeMap<String, String> getMapDoc() {
		return this.mapDoc;
	}

	/**
	 * @param mapDoc to set mapDoc
	 */
	public void setMapDoc(TreeMap<String, String> mapDoc) {
		this.mapDoc = mapDoc;
	}

	/**
	 * @return mapRole
	 */
	public TreeMap<String, String> getMapRole() {
		return this.mapRole;
	}

	/**
	 * @param mapRole to set mapRole
	 */
	public void setMapRole(TreeMap<String, String> mapRole) {
		this.mapRole = mapRole;
	}

	/**
	 * @return uploadDocName
	 */
	public String getUploadDocName() {
		return this.uploadDocName;
	}

	/**
	 * @param uploadDocName to set uploadDocName
	 */
	public void setUploadDocName(String uploadDocName) {
		this.uploadDocName = uploadDocName;
	}

	/**
	 * @return documentAttached
	 */
	public String getDocumentAttached() {
		return this.documentAttached;
	}

	/**
	 * @param documentAttached to set documentAttached
	 */
	public void setDocumentAttached(String documentAttached) {
		this.documentAttached = documentAttached;
	}

	/**
	 * @return currentState
	 */
	public String getCurrentState() {
		return this.currentState;
	}

	/**
	 * @param currentState to set currentState
	 */
	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	/**
	 * @return empRole
	 */ 
	public String getEmpRole() {
		return this.empRole;
	}

	/**
	 * @param empRole to set empRole
	 */
	public void setEmpRole(String empRole) {
		this.empRole = empRole;
	}

	/**
	 * @return brdAppCodeItt
	 */
	public String getBrdAppCodeItt() {
		return this.brdAppCodeItt;
	}

	/**
	 * @param brdAppCodeItt to set brdAppCodeItt
	 */
	public void setBrdAppCodeItt(String brdAppCodeItt) {
		this.brdAppCodeItt = brdAppCodeItt;
	}

	/**
	 * @return applnStatus
	 */
	public String getApplnStatus() {
		return this.applnStatus;
	}

	/**
	 * @param applnStatus to set applnStatus
	 */
	public void setApplnStatus(String applnStatus) {
		this.applnStatus = applnStatus;
	}

	/** 
	 * @return mySendBackProjectPage
	 */
	public String getMySendBackProjectPage() {
		return this.mySendBackProjectPage;
	}

	/**
	 * @param mySendBackProjectPage to set mySendBackProjectPage
	 */
	public void setMySendBackProjectPage(String mySendBackProjectPage) {
		this.mySendBackProjectPage = mySendBackProjectPage;
	}

	/**
	 * @return formySendbackListLength
	 */
	public boolean isFormySendbackListLength() {
		return this.formySendbackListLength;
	}

	/**
	 * @param formySendbackListLength to set formySendbackListLength
	 */
	public void setFormySendbackListLength(boolean formySendbackListLength) {
		this.formySendbackListLength = formySendbackListLength;
	}

	/**
	 * @return forMySendbackList
	 */
	public List<BusinessRequirementDocument> getForMySendbackList() {
		return this.forMySendbackList;
	}

	/**
	 * @param forMySendbackList to set forMySendbackList
	 */
	public void setForMySendbackList(
			List<BusinessRequirementDocument> forMySendbackList) {
		this.forMySendbackList = forMySendbackList;
	}

	/**
	 * @return viewCode
	 */
	public String getViewCode() {
		return this.viewCode;
	}

	/**
	 * @param viewCode to set viewCode
	 */
	public void setViewCode(String viewCode) {
		this.viewCode = viewCode;
	}

	/**
	 * @return appCode
	 */
	public String getAppCode() {
		return this.appCode;
	}

	/**
	 * @param appCode to set appCode
	 */
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	/**
	 * @return buttonFlag
	 */
	public String getButtonFlag() {
		return this.buttonFlag;
	}

	/**
	 * @param buttonFlag to set buttonFlag
	 */
	public void setButtonFlag(String buttonFlag) {
		this.buttonFlag = buttonFlag;
	}

	/**
	 * @return initiatorList
	 */
	public List<BusinessRequirementDocument> getInitiatorList() {
		return this.initiatorList;
	}

	/**
	 * @param initiatorList to set initiatorList
	 */
	public void setInitiatorList(List<BusinessRequirementDocument> initiatorList) {
		this.initiatorList = initiatorList;
	}

	/**
	 * @return activityList
	 */
	public boolean isActivityList() {
		return this.activityList;
	}

	/**
	 * @param activityList to set activityList
	 */
	public void setActivityList(boolean activityList) {
		this.activityList = activityList;
	}

	/**
	 * @return fileName
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * @param fileName to set fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return logFileName
	 */
	public String getLogFileName() {
		return this.logFileName;
	}

	/**
	 * @param logFileName to set logFileName
	 */
	public void setLogFileName(String logFileName) {
		this.logFileName = logFileName;
	}

	/**
	 * @return empCode
	 */
	public String getEmpCode() {
		return this.empCode;
	}

	/**
	 * @param empCode to set empCode
	 */
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	/**
	 * @return empName
	 */
	public String getEmpName() {
		return this.empName;
	}

	/**
	 * @param empName to set empName
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return empToken
	 */
	public String getEmpToken() {
		return this.empToken;
	}

	/**
	 * @param empToken to set empToken
	 */
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}

	/**
	 * @return serialNum
	 */
	public String getSerialNum() {
		return this.serialNum;
	}

	/**
	 * @param serialNum to set serialNum
	 */
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	/**
	 * @return empIttId
	 */
	public String getEmpIttId() {
		return this.empIttId;
	}

	/**
	 * @param empIttId to set empIttId
	 */
	public void setEmpIttId(String empIttId) {
		this.empIttId = empIttId;
	}

	/**
	 * @return stakeholderAppEmpNameItt
	 */
	public String getStakeholderAppEmpNameItt() {
		return this.stakeholderAppEmpNameItt;
	}

	/**
	 * @param stakeholderAppEmpNameItt to set stakeholderAppEmpNameItt
	 */
	public void setStakeholderAppEmpNameItt(String stakeholderAppEmpNameItt) {
		this.stakeholderAppEmpNameItt = stakeholderAppEmpNameItt;
	}

	/**
	 * @return approverStakeHoldersList
	 */
	public List<BusinessRequirementDocument> getApproverStakeHoldersList() {
		return this.approverStakeHoldersList;
	}

	/**
	 * @param approverStakeHoldersList to set approverStakeHoldersList
	 */ 
	public void setApproverStakeHoldersList(
			List<BusinessRequirementDocument> approverStakeHoldersList) {
		this.approverStakeHoldersList = approverStakeHoldersList;
	}

	/**
	 * @return checkAppRemove
	 */
	public String getCheckAppRemove() {
		return this.checkAppRemove;
	}

	/** 
	 * @param checkAppRemove to set checkAppRemove
	 */
	public void setCheckAppRemove(String checkAppRemove) {
		this.checkAppRemove = checkAppRemove;
	}

	/**
	 * @return stakeHolderFlag
	 */
	public String getStakeHolderFlag() {
		return this.stakeHolderFlag;
	}

	/**
	 * @param stakeHolderFlag to set stakeHolderFlag
	 */
	public void setStakeHolderFlag(String stakeHolderFlag) {
		this.stakeHolderFlag = stakeHolderFlag;
	}

	/**
	 * @return empFlag
	 */
	public String getEmpFlag() {
		return this.empFlag;
	}

	/**
	 * @param empFlag to set empFlag
	 */
	public void setEmpFlag(String empFlag) {
		this.empFlag = empFlag;
	}

	/**
	 * @return businessFlag
	 */
	public String getBusinessFlag() {
		return this.businessFlag;
	}

	/**
	 * @param businessFlag to set businessFlag
	 */
	public void setBusinessFlag(String businessFlag) {
		this.businessFlag = businessFlag;
	}

	/**
	 * @return closureFlag
	 */
	public String getClosureFlag() {
		return this.closureFlag;
	}

	/**
	 * @param closureFlag to set closureFlag
	 */
	public void setClosureFlag(String closureFlag) {
		this.closureFlag = closureFlag;
	}

	/**
	 * @return constraintsFlag
	 */
	public String getConstraintsFlag() {
		return this.constraintsFlag;
	}

	/**
	 * @param constraintsFlag to set constraintsFlag
	 */
	public void setConstraintsFlag(String constraintsFlag) {
		this.constraintsFlag = constraintsFlag;
	}

	/**
	 * @return assumptionFlag
	 */
	public String getAssumptionFlag() {
		return this.assumptionFlag;
	}

	/**
	 * @param assumptionFlag to set assumptionFlag
	 */
	public void setAssumptionFlag(String assumptionFlag) {
		this.assumptionFlag = assumptionFlag;
	}

	/**
	 * @return commentFlag
	 */
	public String getCommentFlag() {
		return this.commentFlag;
	}

	/**
	 * @param commentFlag to set commentFlag
	 */
	public void setCommentFlag(String commentFlag) {
		this.commentFlag = commentFlag;
	}

	/**
	 * @return removeButtonFlag
	 */
	public String getRemoveButtonFlag() {
		return this.removeButtonFlag;
	}

	/**
	 * @param removeButtonFlag to set removeButtonFlag
	 */
	public void setRemoveButtonFlag(String removeButtonFlag) {
		this.removeButtonFlag = removeButtonFlag;
	}

	/**
	 * @return initiatorListFlag
	 */
	public String getInitiatorListFlag() {
		return this.initiatorListFlag;
	}

	/**
	 * @param initiatorListFlag to set initiatorListFlag
	 */
	public void setInitiatorListFlag(String initiatorListFlag) {
		this.initiatorListFlag = initiatorListFlag;
	}

	/**
	 * @return closeStatus
	 */
	public String getCloseStatus() {
		return this.closeStatus;
	}

	/**
	 * @param closeStatus to set closeStatus
	 */
	public void setCloseStatus(String closeStatus) {
		this.closeStatus = closeStatus;
	}

	/**
	 * @return inProcessStatus
	 */
	public String getInProcessStatus() {
		return this.inProcessStatus;
	}

	/**
	 * @param inProcessStatus to set inProcessStatus
	 */
	public void setInProcessStatus(String inProcessStatus) {
		this.inProcessStatus = inProcessStatus;
	}

	/**
	 * @return noFileAttachedFlag
	 */
	public String getNoFileAttachedFlag() {
		return this.noFileAttachedFlag;
	}

	/**
	 * @param noFileAttachedFlag to set noFileAttachedFlag
	 */
	public void setNoFileAttachedFlag(String noFileAttachedFlag) {
		this.noFileAttachedFlag = noFileAttachedFlag;
	}

	/**
	 * @return noInitiatorFileAttachedFlag
	 */
	public String getNoInitiatorFileAttachedFlag() {
		return this.noInitiatorFileAttachedFlag;
	}

	/**
	 * @param noInitiatorFileAttachedFlag to set noInitiatorFileAttachedFlag
	 */
	public void setNoInitiatorFileAttachedFlag(
			String noInitiatorFileAttachedFlag) {
		this.noInitiatorFileAttachedFlag = noInitiatorFileAttachedFlag;
	}

	/**
	 * @return applicantsComments
	 */
	public String getApplicantsComments() {
		return this.applicantsComments;
	}

	/**
	 * @param applicantsComments to set applicantsComments
	 */
	public void setApplicantsComments(String applicantsComments) {
		this.applicantsComments = applicantsComments;
	}

	/**
	 * @return applicantCommnetsFlag
	 */
	public String getApplicantCommnetsFlag() {
		return this.applicantCommnetsFlag;
	}

	/**
	 * @param applicantCommnetsFlag to set applicantCommnetsFlag
	 */
	public void setApplicantCommnetsFlag(String applicantCommnetsFlag) {
		this.applicantCommnetsFlag = applicantCommnetsFlag;
	}

	/**
	 * @return applicantEditableFlag
	 */
	public String getApplicantEditableFlag() {
		return this.applicantEditableFlag;
	}

	/**
	 * @param applicantEditableFlag to set applicantEditableFlag
	 */
	public void setApplicantEditableFlag(String applicantEditableFlag) {
		this.applicantEditableFlag = applicantEditableFlag;
	}

	/**
	 * @return uploadDocumentIterator 
	 */
	public List<BusinessRequirementDocument> getUploadDocumentIterator() {
		return this.uploadDocumentIterator;
	}

	/**
	 * @param uploadDocumentIterator to set uploadDocumentIterator
	 */
	public void setUploadDocumentIterator(
			List<BusinessRequirementDocument> uploadDocumentIterator) {
		this.uploadDocumentIterator = uploadDocumentIterator;
	}

	/**
	 * @return uploadFileNameItr
	 */
	public String getUploadFileNameItr() {
		return this.uploadFileNameItr;
	}

	/**
	 * @param uploadFileNameItr to set uploadFileNameItr
	 */
	public void setUploadFileNameItr(String uploadFileNameItr) {
		this.uploadFileNameItr = uploadFileNameItr;
	}

	/**
	 * @return initiatorUploadDocIterator
	 */
	public List<BusinessRequirementDocument> getInitiatorUploadDocIterator() {
		return this.initiatorUploadDocIterator;
	}

	/**
	 * @param initiatorUploadDocIterator to set initiatorUploadDocIterator
	 */
	public void setInitiatorUploadDocIterator(
			List<BusinessRequirementDocument> initiatorUploadDocIterator) {
		this.initiatorUploadDocIterator = initiatorUploadDocIterator;
	}

	/**
	 * @return initiatorUploadDocNameItr
	 */
	public String getInitiatorUploadDocNameItr() {
		return this.initiatorUploadDocNameItr;
	}

	/**
	 * @param initiatorUploadDocNameItr to set initiatorUploadDocNameItr
	 */
	public void setInitiatorUploadDocNameItr(String initiatorUploadDocNameItr) {
		this.initiatorUploadDocNameItr = initiatorUploadDocNameItr;
	}

	/**
	 * @return forwardedApproverUploadDocActivityLogIterator
	 */
	public List<BusinessRequirementDocument> getForwardedApproverUploadDocActivityLogIterator() {
		return this.forwardedApproverUploadDocActivityLogIterator;
	}

	/**
	 * @param forwardedApproverUploadDocActivityLogIterator to set forwardedApproverUploadDocActivityLogIterator
	 */
	public void setForwardedApproverUploadDocActivityLogIterator(
			List<BusinessRequirementDocument> forwardedApproverUploadDocActivityLogIterator) {
		this.forwardedApproverUploadDocActivityLogIterator = forwardedApproverUploadDocActivityLogIterator;
	}

	/**
	 * @return forwardedApprActivityLogUploadDocItr
	 */
	public String getForwardedApprActivityLogUploadDocItr() {
		return this.forwardedApprActivityLogUploadDocItr;
	}

	/**
	 * @param forwardedApprActivityLogUploadDocItr to set forwardedApprActivityLogUploadDocItr
	 */
	public void setForwardedApprActivityLogUploadDocItr(
			String forwardedApprActivityLogUploadDocItr) {
		this.forwardedApprActivityLogUploadDocItr = forwardedApprActivityLogUploadDocItr;
	}

	/**
	 * @return listType
	 */
	public String getListType() {
		return this.listType;
	}

	/**
	 * @param listType to set listType
	 */
	public void setListType(String listType) {
		this.listType = listType;
	}

	/**
	 * @return brdSuperUserFlag
	 */
	public String getBrdSuperUserFlag() {
		return this.brdSuperUserFlag;
	}

	/**
	 * @param brdSuperUserFlag to set brdSuperUserFlag
	 */
	public void setBrdSuperUserFlag(String brdSuperUserFlag) {
		this.brdSuperUserFlag = brdSuperUserFlag;
	}

	/**
	 * @return cancelProjectComments
	 */
	public String getCancelProjectComments() {
		return this.cancelProjectComments;
	}

	/**
	 * @param cancelProjectComments to set cancelProjectComments
	 */
	public void setCancelProjectComments(String cancelProjectComments) {
		this.cancelProjectComments = cancelProjectComments;
	}

	/**
	 * @return myCancelListLength
	 */
	public boolean isMyCancelListLength() {
		return this.myCancelListLength;
	}

	/**
	 * @param myCancelListLength to set myCancelListLength
	 */
	public void setMyCancelListLength(boolean myCancelListLength) {
		this.myCancelListLength = myCancelListLength;
	}

	/**
	 * @return myCancelList
	 */
	public List<BusinessRequirementDocument> getMyCancelList() {
		return this.myCancelList;
	}

	/**
	 * @param myCancelList to set myCancelList
	 */
	public void setMyCancelList(List<BusinessRequirementDocument> myCancelList) {
		this.myCancelList = myCancelList;
	}

	/**
	 * @return myCancelProjectPage
	 */
	public String getMyCancelProjectPage() {
		return this.myCancelProjectPage;
	}

	/**
	 * @param myCancelProjectPage to set myCancelProjectPage
	 */
	public void setMyCancelProjectPage(String myCancelProjectPage) {
		this.myCancelProjectPage = myCancelProjectPage;
	}

	/**
	 * @return priority
	 */
	public String getPriority() {
		return this.priority;
	}

	/**
	 * @param priority to set priority
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}

	/**
	 * @return projectCommentsFlag
	 */
	public boolean isProjectCommentsFlag() {
		return this.projectCommentsFlag;
	}

	/**
	 * @param projectCommentsFlag to set projectCommentsFlag
	 */
	public void setProjectCommentsFlag(boolean projectCommentsFlag) {
		this.projectCommentsFlag = projectCommentsFlag;
	}

	/**
	 * @return foreCastedDateItr
	 */
	public String getForeCastedDateItr() {
		return this.foreCastedDateItr;
	}

	/**
	 * @param foreCastedDateItr to set foreCastedDateItr
	 */
	public void setForeCastedDateItr(String foreCastedDateItr) {
		this.foreCastedDateItr = foreCastedDateItr;
	}

	/**
	 * @return currentActivityStatusItr
	 */
	public String getCurrentActivityStatusItr() {
		return this.currentActivityStatusItr;
	}

	/**
	 * @param currentActivityStatusItr to set currentActivityStatusItr
	 */
	public void setCurrentActivityStatusItr(String currentActivityStatusItr) {
		this.currentActivityStatusItr = currentActivityStatusItr;
	}

	/**
	 * @return myPage
	 */
	public String getMyPage() {
		return this.myPage;
	}

	/**
	 * @param myPage to set myPage
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	/**
	 * @return mapProjectType
	 */
	public TreeMap<String, String> getMapProjectType() {
		return this.mapProjectType;
	}

	/**
	 * @param mapProjectType to set mapProjectType
	 */
	public void setMapProjectType(TreeMap<String, String> mapProjectType) {
		this.mapProjectType = mapProjectType;
	}

	/**
	 * @return mapProjectClassification
	 */
	public TreeMap<String, String> getMapProjectClassification() {
		return this.mapProjectClassification;
	}

	/**
	 * @param mapProjectClassification to set mapProjectClassification
	 */
	public void setMapProjectClassification(
			TreeMap<String, String> mapProjectClassification) {
		this.mapProjectClassification = mapProjectClassification;
	}

	/**
	 * @return activityOwnerName
	 */
	public String getActivityOwnerName() {
		return this.activityOwnerName;
	}

	/**
	 * @param activityOwnerName to set activityOwnerName
	 */
	public void setActivityOwnerName(String activityOwnerName) {
		this.activityOwnerName = activityOwnerName;
	}

	/**
	 * @return activityOwnerPresentFlag
	 */
	public boolean isActivityOwnerPresentFlag() {
		return this.activityOwnerPresentFlag;
	}

	/**
	 * @param activityOwnerPresentFlag to set activityOwnerPresentFlag
	 */
	public void setActivityOwnerPresentFlag(boolean activityOwnerPresentFlag) {
		this.activityOwnerPresentFlag = activityOwnerPresentFlag;
	}

	/**
	 * @return businessUnit
	 */
	public String getBusinessUnit() {
		return businessUnit;
	}

	/**
	 * @param businessUnit to set businessUnit
	 */
	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	/**
	 * @return businessUnitID
	 */
	public String getBusinessUnitID() {
		return businessUnitID;
	}

	/**
	 * @param businessUnitID to set businessUnitID
	 */
	public void setBusinessUnitID(String businessUnitID) {
		this.businessUnitID = businessUnitID;
	}

	/**
	 * @return rank
	 */
	public String getRank() {
		return rank;
	}

	/**
	 * @param rank to set rank
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}

	/**
	 * @return the forwardStatus
	 */
	public boolean isForwardStatus() {
		return forwardStatus;
	}

	/**
	 * @param forwardStatus the forwardStatus to set
	 */
	public void setForwardStatus(boolean forwardStatus) {
		this.forwardStatus = forwardStatus;
	}

	/**
	 * @return the projectTypeOwnerFlag
	 */
	public String getProjectTypeOwnerFlag() {
		return projectTypeOwnerFlag;
	}

	/**
	 * @param projectTypeOwnerFlag the projectTypeOwnerFlag to set
	 */
	public void setProjectTypeOwnerFlag(String projectTypeOwnerFlag) {
		this.projectTypeOwnerFlag = projectTypeOwnerFlag;
	}

	/**
	 * @return the serialNoStake
	 */
	public String getSerialNoStake() {
		return serialNoStake;
	}

	/**
	 * @param serialNoStake the serialNoStake to set
	 */
	public void setSerialNoStake(String serialNoStake) {
		this.serialNoStake = serialNoStake;
	}	
	/**
	 * @return the mapBusinessUnitID
	 */
	public TreeMap<String, String> getMapBusinessUnitID() {
		return mapBusinessUnitID;
	}

	/**
	 * @param mapBusinessUnitID the mapBusinessUnitID to set
	 */
	public void setMapBusinessUnitID(TreeMap<String, String> mapBusinessUnitID) {
		this.mapBusinessUnitID = mapBusinessUnitID;
	}
}