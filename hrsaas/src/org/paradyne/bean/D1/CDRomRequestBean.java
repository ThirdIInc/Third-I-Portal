package org.paradyne.bean.D1;

import java.util.List;

import org.paradyne.lib.BeanBase;

/**
 * @author aa1439/aa1389.
 *
 */
public class CDRomRequestBean extends BeanBase {
	/**
	 * Draft Flag.
	 */
	private boolean draftFlag;
	/**
	 * Authorized Token.
	 */
	private String authorizedToken;
	/**
	 * Initiator. 
	 */
	private String initiator = "";
	/**
	 * Application Complete Date. 
	 */
	private String completedDate = "";
	/**
	 * Completed by name.
	 */
	private String completedBy = "";
	/**
	 * Completed by hidden field.
	 */
	private String completedByCode = "";
	
	
	//For approval
	/**
	 * Relation Address.
	 */
	private String relationAddress;
	/**
	 * Approver Comments List.
	 */
	private List<CDRomRequestBean> approverCommentList;
	/**
	 * Approver Name.
	 */
	private String apprName;
	/**
	 * Approver Comments.
	 */
	private String apprComments;
	/**
	 * Approved Date.
	 */
	private String apprDate;
	/**
	 * Approval Status.
	 */
	private String apprStatus;
	
	//end approval
	
	
	//Upload file feild
	/**
	 * Upload File Field.
	 */
	private List<CDRomRequestBean> uploadFileList;
	//Hidden fields
	/**
	 * CDROM hidden id.
	 */
	private String cdRomID;
	/**
	 * Hidden code.
	 */
	private String hiddenCode;
	/**
	 * Level.
	 */
	private String level = "";
	/**
	 * Status.
	 */
	private String status = "";
	/**
	 * Upload File SR No.
	 */
	private String uploadFileSrNo = "";
	/**
	 * Data Path hidden field.
	 */
	private String dataPath = "";
	/**
	 * Data Path related hidden field.
	 */
	private String dataPath1 = "";   
	
	/**
	 * added by nilesh for addressInfoFile1 & addressInfoFile2 hidden data path fields.
	 */
	/**
	 * Data Path related hidden field.
	 */
	private String dataPath2 = "";
	/**
	 * Application Status.
	 */
	private String applnStatus = "";
	/**
	 * List Type.
	 */
	private String listType = "";
	/**
	 * Data Path Addressing.
	 */
	private String dataPathAddressing = "";
	/**
	 * Data Path Additional.
	 */
	private String dataPathAdditional = "";
	
	/**
	 * Employee Details Info.
	 */
	
	
	/**
	 * Employee Name.
	 */
	private String employeeName;
	/**
	 * Employee Code.
	 */
	private String employeeCode;
	/**
	 * Employee Token.
	 */
	private String employeeToken;
	/**
	 * Department Name.
	 */
	private String deptName;
	/**
	 * Department No.
	 */
	private String deptNo;
	/**
	 * Office Location Name.
	 */
	private String officeLocation;
	/**
	 * Phone No.
	 */
	private String phNo;
	
	/**added by Nilesh*/ 
	
	/**
	 * Department ID Hidden.
	 */
	private String deptId = "";
	/**
	 * Attachment Related Hidden Field.
	 */
	private String requestDetailFile = "";
	/**
	 * Attachment Related Hidden Field.
	 */
	private String addressInfoFile1 = "";
	/**
	 * Attachment Related Hidden Field.
	 */
	private String addressInfoFile2 = "";
	
	/**
	 * Approver Details - Change My Manager Fields. 
	 */
	
	/**
	 * Approver Token.
	 */
	private String approverToken;
	/**
	 * Approver Name.
	 */
	private String approverName;
	/**
	 * Approver Code hidden field.
	 */
	private String approverCode;
	
	/**
	 * Default Approver Name Fields(Gen/Admin). 
	 */
	
	/**
	 * First Approver Code hidden.
	 */
	private String firstApproverCode;
	/**
	 * First Approver Token.
	 */
	private String firstApproverToken;
	/**
	 * First Approver Name.
	 */
	private String firstApproverName;
	
	/**
	 * Second Approver Code hidden field.
	 */
	private String secondApproverCode;
	/**
	 * Second Approver Token.
	 */
	private String secondApproverToken;
	/**
	 * Second Approver Name.
	 */
	private String secondApproverName;
	
	/**
	 * Flag details.
	 */
	
	/**
	 * Flag to display first approver or second it depends on flag value.
	 */
	private boolean secondAppFlag;
	/**
	 * For Approval Table flag.
	 */
	private boolean forApproval;
	/**
	 * for radio button.
	 */
	private boolean readOnlyDetails;
	
	/**
	 * Request Details form fields.
	 */
	/**
	 * Request Name.
	 */
	private String requestName;
	/**
	 * Number of masters.
	 */
	private String noMaster;
	/**
	 * No of copy needed.
	 */
	private String noCopy;
	/**
	 * Package name. 
	 */
	private String pakName;
	/**
	 * Attachment Request Description Text area field.
	 */
	private String attachementRequestDesc;
	/**
	 * Source Name.
	 */
	private String sourceName;
	/**
	 * Upload file name.
	 */
	private String uploadFileName;
	/**
	 * Upload file name flag.
	 */	
	private boolean uploadFileNameFlag;
	/**
	 * Status Draft Back Flag.
	 */
	private boolean statusDraftBack;
	
	
	/**
	 * Delivery Information form fields.
	 */
	
	/**
	 * Delivery Date.
	 */
	private String deliveryDate;
	/**
	 * Deliver Via.
	 */
	private String deliveryVia;
	
	/**
	 * Addressing Information form fields.
	 */
	
	/**
	 * Source Address.
	 */
	private String sourceAddress;
	/**
	 * Upload file Address.
	 */
	private String uploadFileAddress;
	/**
	 * Upload File Address Flag.
	 */
	private boolean uploadFileAddressFlag;
	/**
	 * Address Documents.
	 */
	private String addressDocument;
	/**
	 * Proof (Yes/No drop down).
	 */
	private String proof;
	/**
	 * Additional Info.
	 */
	private String additionalInfoDoc;
	/**
	 * upload File Additional doc.
	 */
	private String uploadFileAdditionalInfoDoc;
	/**
	 * Upload File Additional Info Document Flag.
	 */
	private boolean uploadFileAdditionalInfoDocFlag;
	/**
	 * Approval Comments Flag.
	 */
	private boolean approvalCommentsFlag;    
	/**
	 *added by nilesh.
	 */
	/**
	 * Draft Data Display Flag.
	 */
	private boolean draftDataDisplyFlag;
	
	/**
	 * Approver Comments Details.
	 */
	
	/**
	 * Comment - not used.
	 */
	private String comment = "";
	/**
	 * App Actual Status - not used.
	 */
	private String applnActualStatus = "";

	/**
	 * Approver Comments field.
	 */
	private String approverComments;
	
	
	/**
	 * List Variable.
	 */
	
	/**
	 * Emp Token - not used.
	 */
	private String empToken;
	/**
	 * Emp Name.
	 */
	private String empName;
	/**
	 * Application Date.
	 */
	private String applicationDate;
	
	/**
	 * Draft List.
	 */
	private List<CDRomRequestBean> draftList;
	/**
	 * Draft List Length.
	 */
	private boolean draftListLength;
	/**
	 * Application List.
	 */
	private List<CDRomRequestBean> applicationList;
	/**
	 * In Process List Length.
	 */
	private boolean inProcessListLength;
	/**
	 * Send Back List.
	 */
	private List<CDRomRequestBean> sendBackList;
	/**
	 * Send Back list Length.
	 */
	private boolean sendBackLength;
	/**
	 * Approved List.
	 */
	private List<CDRomRequestBean> approvedList;
	/**
	 * Approved List Length.
	 */
	private boolean approvedListLength;
	/**
	 * Approved Cancel List.
	 */
	private List<CDRomRequestBean> approvedCancelList;
	/**
	 * Approved Cancel List Length.
	 */
	private boolean approvedCancelListLength;
	/**
	 * Rejected List.
	 */
	private List<CDRomRequestBean> rejectList;
	/**
	 *  Rejected List Length.
	 */
	private boolean rejectListLength;
	/**
	 *  Rejected Cancel List.
	 */
	private List<CDRomRequestBean> rejectCancelList;
	/**
	 * Rejected Cancel List Length.
	 */
	private boolean rejectCancelListLength;
	
	/**
	 * Page related Variable.
	 */
	
	/**
	 * My Page for - Draft.
	 */
	private String myPage = "";
	/**
	 * Page for - Pending.
	 */
	private String myPageInProcess = "";
	/**
	 * Page for - Send Back.
	 */
	private String myPageSentBack = "";
	/**
	 * Page for - Approved.
	 */
	private String myPageApproved = "";
	/**
	 * Page for - Approved Cancel.
	 */
	private String myPageApprovedCancel = "";
	/**
	 * Page for - Rejected. 
	 */
	private String myPageRejected = "";
	/**
	 * Page for - Cancel Rejected.
	 */
	private String myPageCancelRejected = "";
	
	/**
	 * @return the myPageInProcess
	 */
	public String getMyPageInProcess() {
		return this.myPageInProcess;
	}
	/**
	 * @param myPageInProcess the myPageInProcess to set
	 */
	public void setMyPageInProcess(final String myPageInProcess) {
		this.myPageInProcess = myPageInProcess;
	}
	/**
	 * @return the myPageSentBack
	 */
	public String getMyPageSentBack() {
		return this.myPageSentBack;
	}
	/**
	 * @param myPageSentBack the myPageSentBack to set
	 */
	public void setMyPageSentBack(final String myPageSentBack) {
		this.myPageSentBack = myPageSentBack;
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
	 * @return the deliveryDate
	 */
	public String getDeliveryDate() {
		return this.deliveryDate;
	}
	/**
	 * @param deliveryDate the deliveryDate to set
	 */
	public void setDeliveryDate(final String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	/**
	 * @return the deliveryVia
	 */
	public String getDeliveryVia() {
		return this.deliveryVia;
	}
	/**
	 * @param deliveryVia the deliveryVia to set
	 */
	public void setDeliveryVia(final String deliveryVia) {
		this.deliveryVia = deliveryVia;
	}
	/**
	 * @return the secondAppFlag
	 */
	public boolean isSecondAppFlag() {
		return this.secondAppFlag;
	}
	/**
	 * @param secondAppFlag the secondAppFlag to set
	 */
	public void setSecondAppFlag(final boolean secondAppFlag) {
		this.secondAppFlag = secondAppFlag;
	}
	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return this.employeeName;
	}
	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(final String employeeName) {
		this.employeeName = employeeName;
	}
	/**
	 * @return the employeeCode
	 */
	public String getEmployeeCode() {
		return this.employeeCode;
	}
	/**
	 * @param employeeCode the employeeCode to set
	 */
	public void setEmployeeCode(final String employeeCode) {
		this.employeeCode = employeeCode;
	}
	/**
	 * @return the employeeToken
	 */
	public String getEmployeeToken() {
		return this.employeeToken;
	}
	/**
	 * @param employeeToken the employeeToken to set
	 */
	public void setEmployeeToken(final String employeeToken) {
		this.employeeToken = employeeToken;
	}
	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return this.deptName;
	}
	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(final String deptName) {
		this.deptName = deptName;
	}
	/**
	 * @return the deptNo
	 */
	public String getDeptNo() {
		return this.deptNo;
	}
	/**
	 * @param deptNo the deptNo to set
	 */
	public void setDeptNo(final String deptNo) {
		this.deptNo = deptNo;
	}
	/**
	 * @return the officeLocation
	 */
	public String getOfficeLocation() {
		return this.officeLocation;
	}
	/**
	 * @param officeLocation the officeLocation to set
	 */
	public void setOfficeLocation(final String officeLocation) {
		this.officeLocation = officeLocation;
	}
	/**
	 * @return the phNo
	 */
	public String getPhNo() {
		return this.phNo;
	}
	/**
	 * @param phNo the phNo to set
	 */
	public void setPhNo(final String phNo) {
		this.phNo = phNo;
	}
	/**
	 * @return the approverToken
	 */
	public String getApproverToken() {
		return this.approverToken;
	}
	/**
	 * @param approverToken the approverToken to set
	 */
	public void setApproverToken(final String approverToken) {
		this.approverToken = approverToken;
	}
	/**
	 * @return the approverName
	 */
	public String getApproverName() {
		return this.approverName;
	}
	/**
	 * @param approverName the approverName to set
	 */
	public void setApproverName(final String approverName) {
		this.approverName = approverName;
	}
	/**
	 * @return the approverCode
	 */
	public String getApproverCode() {
		return this.approverCode;
	}
	/**
	 * @param approverCode the approverCode to set
	 */
	public void setApproverCode(final String approverCode) {
		this.approverCode = approverCode;
	}
	/**
	 * @return the secondApproverCode
	 */
	public String getSecondApproverCode() {
		return this.secondApproverCode;
	}
	/**
	 * @param secondApproverCode the secondApproverCode to set
	 */
	public void setSecondApproverCode(final String secondApproverCode) {
		this.secondApproverCode = secondApproverCode;
	}
	/**
	 * @return the secondApproverToken
	 */
	public String getSecondApproverToken() {
		return this.secondApproverToken;
	}
	/**
	 * @param secondApproverToken the secondApproverToken to set
	 */
	public void setSecondApproverToken(final String secondApproverToken) {
		this.secondApproverToken = secondApproverToken;
	}
	/**
	 * @return the secondApproverName
	 */
	public String getSecondApproverName() {
		return this.secondApproverName;
	}
	/**
	 * @param secondApproverName the secondApproverName to set
	 */
	public void setSecondApproverName(final String secondApproverName) {
		this.secondApproverName = secondApproverName;
	}
	/**
	 * @return the firstApproverCode
	 */
	public String getFirstApproverCode() {
		return this.firstApproverCode;
	}
	/**
	 * @param firstApproverCode the firstApproverCode to set
	 */
	public void setFirstApproverCode(final String firstApproverCode) {
		this.firstApproverCode = firstApproverCode;
	}
	/**
	 * @return the firstApproverToken
	 */
	public String getFirstApproverToken() {
		return this.firstApproverToken;
	}
	/**
	 * @param firstApproverToken the firstApproverToken to set
	 */
	public void setFirstApproverToken(final String firstApproverToken) {
		this.firstApproverToken = firstApproverToken;
	}
	/**
	 * @return the firstApproverName
	 */
	public String getFirstApproverName() {
		return this.firstApproverName;
	}
	/**
	 * @param firstApproverName the firstApproverName to set
	 */
	public void setFirstApproverName(final String firstApproverName) {
		this.firstApproverName = firstApproverName;
	}
	/**
	 * @return the requestName
	 */
	public String getRequestName() {
		return this.requestName;
	}
	/**
	 * @param requestName the requestName to set
	 */
	public void setRequestName(final String requestName) {
		this.requestName = requestName;
	}
	/**
	 * @return the noMaster
	 */
	public String getNoMaster() {
		return this.noMaster;
	}
	/**
	 * @param noMaster the noMaster to set
	 */
	public void setNoMaster(final String noMaster) {
		this.noMaster = noMaster;
	}
	/**
	 * @return the noCopy
	 */
	public String getNoCopy() {
		return this.noCopy;
	}
	/**
	 * @param noCopy the noCopy to set
	 */
	public void setNoCopy(final String noCopy) {
		this.noCopy = noCopy;
	}
	/**
	 * @return the pakName
	 */
	public String getPakName() {
		return this.pakName;
	}
	/**
	 * @param pakName the pakName to set
	 */
	public void setPakName(final String pakName) {
		this.pakName = pakName;
	}
	/**
	 * @return the attachementRequestDesc
	 */
	public String getAttachementRequestDesc() {
		return this.attachementRequestDesc;
	}
	/**
	 * @param attachementRequestDesc the attachementRequestDesc to set
	 */
	public void setAttachementRequestDesc(final String attachementRequestDesc) {
		this.attachementRequestDesc = attachementRequestDesc;
	}
	/**
	 * @return the sourceName
	 */
	public String getSourceName() {
		return this.sourceName;
	}
	/**
	 * @param sourceName the sourceName to set
	 */
	public void setSourceName(final String sourceName) {
		this.sourceName = sourceName;
	}
	/**
	 * @return the uploadFileName
	 */
	public String getUploadFileName() {
		return this.uploadFileName;
	}
	/**
	 * @param uploadFileName the uploadFileName to set
	 */
	public void setUploadFileName(final String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	/**
	 * @return the sourceAddress
	 */
	public String getSourceAddress() {
		return this.sourceAddress;
	}
	/**
	 * @param sourceAddress the sourceAddress to set
	 */
	public void setSourceAddress(final String sourceAddress) {
		this.sourceAddress = sourceAddress;
	}
	/**
	 * @return the uploadFileAddress
	 */
	public String getUploadFileAddress() {
		return this.uploadFileAddress;
	}
	/**
	 * @param uploadFileAddress the uploadFileAddress to set
	 */
	public void setUploadFileAddress(final String uploadFileAddress) {
		this.uploadFileAddress = uploadFileAddress;
	}
	/**
	 * @return the addressDocument
	 */
	public String getAddressDocument() {
		return this.addressDocument;
	}
	/**
	 * @param addressDocument the addressDocument to set
	 */
	public void setAddressDocument(final String addressDocument) {
		this.addressDocument = addressDocument;
	}
	/**
	 * @return the proof
	 */
	public String getProof() {
		return this.proof;
	}
	/**
	 * @param proof the proof to set
	 */
	public void setProof(final String proof) {
		this.proof = proof;
	}
	/**
	 * @return the additionalInfoDoc
	 */
	public String getAdditionalInfoDoc() {
		return this.additionalInfoDoc;
	}
	/**
	 * @param additionalInfoDoc the additionalInfoDoc to set
	 */
	public void setAdditionalInfoDoc(final String additionalInfoDoc) {
		this.additionalInfoDoc = additionalInfoDoc;
	}
	/**
	 * @return the uploadFileAdditionalInfoDoc
	 */
	public String getUploadFileAdditionalInfoDoc() {
		return this.uploadFileAdditionalInfoDoc;
	}
	/**
	 * @param uploadFileAdditionalInfoDoc the uploadFileAdditionalInfoDoc to set
	 */
	public void setUploadFileAdditionalInfoDoc(final String uploadFileAdditionalInfoDoc) {
		this.uploadFileAdditionalInfoDoc = uploadFileAdditionalInfoDoc;
	}
	/**
	 * @return the cdRomID
	 */
	public String getCdRomID() {
		return this.cdRomID;
	}
	/**
	 * @param cdRomID the cdRomID to set
	 */
	public void setCdRomID(final String cdRomID) {
		this.cdRomID = cdRomID;
	}
	/**
	 * @return the hiddenCode
	 */
	public String getHiddenCode() {
		return this.hiddenCode;
	}
	/**
	 * @param hiddenCode the hiddenCode to set
	 */
	public void setHiddenCode(final String hiddenCode) {
		this.hiddenCode = hiddenCode;
	}
	/**
	 * @return the level
	 */
	public String getLevel() {
		return this.level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(final String level) {
		this.level = level;
	}
	/**
	 * @return the forApproval
	 */
	public boolean isForApproval() {
		return this.forApproval;
	}
	/**
	 * @param forApproval the forApproval to set
	 */
	public void setForApproval(final boolean forApproval) {
		this.forApproval = forApproval;
	}
	/**
	 * @return the readOnlyDetails
	 */
	public boolean isReadOnlyDetails() {
		return this.readOnlyDetails;
	}
	/**
	 * @param readOnlyDetails the readOnlyDetails to set
	 */
	public void setReadOnlyDetails(final boolean readOnlyDetails) {
		this.readOnlyDetails = readOnlyDetails;
	}
	/**
	 * @return the comment
	 */
	public String getComment() {
		return this.comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(final String comment) {
		this.comment = comment;
	}
	/**
	 * @return the applnActualStatus
	 */
	public String getApplnActualStatus() {
		return this.applnActualStatus;
	}
	/**
	 * @param applnActualStatus the applnActualStatus to set
	 */
	public void setApplnActualStatus(final String applnActualStatus) {
		this.applnActualStatus = applnActualStatus;
	}
	/**
	 * @return the approverComments
	 */
	public String getApproverComments() {
		return this.approverComments;
	}
	/**
	 * @param approverComments the approverComments to set
	 */
	public void setApproverComments(final String approverComments) {
		this.approverComments = approverComments;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return this.status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(final String status) {
		this.status = status;
	}
	/**
	 * @return the uploadFileSrNo
	 */
	public String getUploadFileSrNo() {
		return this.uploadFileSrNo;
	}
	/**
	 * @param uploadFileSrNo the uploadFileSrNo to set
	 */
	public void setUploadFileSrNo(final String uploadFileSrNo) {
		this.uploadFileSrNo = uploadFileSrNo;
	}
	/**
	 * @return the dataPath
	 */
	public String getDataPath() {
		return this.dataPath;
	}
	/**
	 * @param dataPath the dataPath to set
	 */
	public void setDataPath(final String dataPath) {
		this.dataPath = dataPath;
	}
	/**
	 * @return the empToken
	 */
	public String getEmpToken() {
		return this.empToken;
	}
	/**
	 * @param empToken the empToken to set
	 */
	public void setEmpToken(final String empToken) {
		this.empToken = empToken;
	}
	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return this.empName;
	}
	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(final String empName) {
		this.empName = empName;
	}
	/**
	 * @return the applicationDate
	 */
	public String getApplicationDate() {
		return this.applicationDate;
	}
	/**
	 * @param applicationDate the applicationDate to set
	 */
	public void setApplicationDate(final String applicationDate) {
		this.applicationDate = applicationDate;
	}
	/**
	 * @return the draftList
	 */
	public List<CDRomRequestBean> getDraftList() {
		return this.draftList;
	}
	/**
	 * @param draftList the draftList to set
	 */
	public void setDraftList(final List<CDRomRequestBean> draftList) {
		this.draftList = draftList;
	}
	/**
	 * @return the draftListLength
	 */
	public boolean isDraftListLength() {
		return this.draftListLength;
	}
	/**
	 * @param draftListLength the draftListLength to set
	 */
	public void setDraftListLength(final boolean draftListLength) {
		this.draftListLength = draftListLength;
	}
	/**
	 * @return the applnStatus
	 */
	public String getApplnStatus() {
		return this.applnStatus;
	}
	/**
	 * @param applnStatus the applnStatus to set
	 */
	public void setApplnStatus(final String applnStatus) {
		this.applnStatus = applnStatus;
	}
	/**
	 * @return the listType
	 */
	public String getListType() {
		return this.listType;
	}
	/**
	 * @param listType the listType to set
	 */
	public void setListType(final String listType) {
		this.listType = listType;
	}
	/**
	 * @return the uploadFileList
	 */
	public List<CDRomRequestBean> getUploadFileList() {
		return this.uploadFileList;
	}
	/**
	 * @param uploadFileList the uploadFileList to set
	 */
	public void setUploadFileList(final List<CDRomRequestBean> uploadFileList) {
		this.uploadFileList = uploadFileList;
	}
	/**
	 * @return the dataPathAddressing
	 */
	public String getDataPathAddressing() {
		return this.dataPathAddressing;
	}
	/**
	 * @param dataPathAddressing the dataPathAddressing to set
	 */
	public void setDataPathAddressing(final String dataPathAddressing) {
		this.dataPathAddressing = dataPathAddressing;
	}
	/**
	 * @return the dataPathAdditional
	 */
	public String getDataPathAdditional() {
		return this.dataPathAdditional;
	}
	/**
	 * @param dataPathAdditional the dataPathAdditional to set
	 */
	public void setDataPathAdditional(final String dataPathAdditional) {
		this.dataPathAdditional = dataPathAdditional;
	}
	
	
	/**
	 * @return the applicationList
	 */
	public List<CDRomRequestBean> getApplicationList() {
		return this.applicationList;
	}
	/**
	 * @param applicationList the applicationList to set
	 */
	public void setApplicationList(final List<CDRomRequestBean> applicationList) {
		this.applicationList = applicationList;
	}
	/**
	 * @return the inProcessListLength
	 */
	public boolean isInProcessListLength() {
		return this.inProcessListLength;
	}
	/**
	 * @param inProcessListLength the inProcessListLength to set
	 */
	public void setInProcessListLength(final boolean inProcessListLength) {
		this.inProcessListLength = inProcessListLength;
	}
	/**
	 * @return the sendBackList
	 */
	public List<CDRomRequestBean> getSendBackList() {
		return this.sendBackList;
	}
	/**
	 * @param sendBackList the sendBackList to set
	 */
	public void setSendBackList(final List<CDRomRequestBean> sendBackList) {
		this.sendBackList = sendBackList;
	}
	/**
	 * @return the sendBackLength
	 */
	public boolean isSendBackLength() {
		return this.sendBackLength;
	}
	/**
	 * @param sendBackLength the sendBackLength to set
	 */
	public void setSendBackLength(final boolean sendBackLength) {
		this.sendBackLength = sendBackLength;
	}
	/**
	 * @return the approvedList
	 */
	public List<CDRomRequestBean> getApprovedList() {
		return this.approvedList;
	}
	/**
	 * @param approvedList the approvedList to set
	 */
	public void setApprovedList(final List<CDRomRequestBean> approvedList) {
		this.approvedList = approvedList;
	}
	/**
	 * @return the approvedListLength
	 */
	public boolean isApprovedListLength() {
		return this.approvedListLength;
	}
	/**
	 * @param approvedListLength the approvedListLength to set
	 */
	public void setApprovedListLength(final boolean approvedListLength) {
		this.approvedListLength = approvedListLength;
	}
	/**
	 * @return the myPageApproved
	 */
	public String getMyPageApproved() {
		return this.myPageApproved;
	}
	/**
	 * @param myPageApproved the myPageApproved to set
	 */
	public void setMyPageApproved(final String myPageApproved) {
		this.myPageApproved = myPageApproved;
	}
	/**
	 * @return the myPageApprovedCancel
	 */
	public String getMyPageApprovedCancel() {
		return this.myPageApprovedCancel;
	}
	/**
	 * @param myPageApprovedCancel the myPageApprovedCancel to set
	 */
	public void setMyPageApprovedCancel(final String myPageApprovedCancel) {
		this.myPageApprovedCancel = myPageApprovedCancel;
	}
	/**
	 * @return the approvedCancelList
	 */
	public List<CDRomRequestBean> getApprovedCancelList() {
		return this.approvedCancelList;
	}
	/**
	 * @param approvedCancelList the approvedCancelList to set
	 */
	public void setApprovedCancelList(final List<CDRomRequestBean> approvedCancelList) {
		this.approvedCancelList = approvedCancelList;
	}
	/**
	 * @return the approvedCancelListLength
	 */
	public boolean isApprovedCancelListLength() {
		return this.approvedCancelListLength;
	}
	/**
	 * @param approvedCancelListLength the approvedCancelListLength to set
	 */
	public void setApprovedCancelListLength(final boolean approvedCancelListLength) {
		this.approvedCancelListLength = approvedCancelListLength;
	}
	/**
	 * @return the rejectList
	 */
	public List<CDRomRequestBean> getRejectList() {
		return this.rejectList;
	}
	/**
	 * @param rejectList the rejectList to set
	 */
	public void setRejectList(final List<CDRomRequestBean> rejectList) {
		this.rejectList = rejectList;
	}
	/**
	 * @return the rejectListLength
	 */
	public boolean isRejectListLength() {
		return this.rejectListLength;
	}
	/**
	 * @param rejectListLength the rejectListLength to set
	 */
	public void setRejectListLength(final boolean rejectListLength) {
		this.rejectListLength = rejectListLength;
	}
	/**
	 * @return the rejectCancelList
	 */
	public List<CDRomRequestBean> getRejectCancelList() {
		return this.rejectCancelList;
	}
	/**
	 * @param rejectCancelList the rejectCancelList to set
	 */
	public void setRejectCancelList(final List<CDRomRequestBean> rejectCancelList) {
		this.rejectCancelList = rejectCancelList;
	}
	/**
	 * @return the rejectCancelListLength
	 */
	public boolean isRejectCancelListLength() {
		return this.rejectCancelListLength;
	}
	/**
	 * @param rejectCancelListLength the rejectCancelListLength to set
	 */
	public void setRejectCancelListLength(final boolean rejectCancelListLength) {
		this.rejectCancelListLength = rejectCancelListLength;
	}
	/**
	 * @return the myPageRejected
	 */
	public String getMyPageRejected() {
		return this.myPageRejected;
	}
	/**
	 * @param myPageRejected the myPageRejected to set
	 */
	public void setMyPageRejected(final String myPageRejected) {
		this.myPageRejected = myPageRejected;
	}
	/**
	 * @return the myPageCancelRejected
	 */
	public String getMyPageCancelRejected() {
		return this.myPageCancelRejected;
	}
	/**
	 * @param myPageCancelRejected the myPageCancelRejected to set
	 */
	public void setMyPageCancelRejected(final String myPageCancelRejected) {
		this.myPageCancelRejected = myPageCancelRejected;
	}
	/**
	 * @return the uploadFileNameFlag
	 */
	public boolean isUploadFileNameFlag() {
		return this.uploadFileNameFlag;
	}
	/**
	 * @param uploadFileNameFlag the uploadFileNameFlag to set
	 */
	public void setUploadFileNameFlag(final boolean uploadFileNameFlag) {
		this.uploadFileNameFlag = uploadFileNameFlag;
	}
	/**
	 * @return the uploadFileAddressFlag
	 */
	public boolean isUploadFileAddressFlag() {
		return this.uploadFileAddressFlag;
	}
	/**
	 * @param uploadFileAddressFlag the uploadFileAddressFlag to set
	 */
	public void setUploadFileAddressFlag(final boolean uploadFileAddressFlag) {
		this.uploadFileAddressFlag = uploadFileAddressFlag;
	}
	/**
	 * @return the uploadFileAdditionalInfoDocFlag
	 */
	public boolean isUploadFileAdditionalInfoDocFlag() {
		return this.uploadFileAdditionalInfoDocFlag;
	}
	/**
	 * @param uploadFileAdditionalInfoDocFlag the uploadFileAdditionalInfoDocFlag to set
	 */
	public void setUploadFileAdditionalInfoDocFlag(final boolean uploadFileAdditionalInfoDocFlag) {
		this.uploadFileAdditionalInfoDocFlag = uploadFileAdditionalInfoDocFlag;
	}
	/**
	 * @return the relationAddress
	 */
	public String getRelationAddress() {
		return this.relationAddress;
	}
	/**
	 * @param relationAddress the relationAddress to set
	 */
	public void setRelationAddress(final String relationAddress) {
		this.relationAddress = relationAddress;
	}
	/**
	 * @return the approverCommentList
	 */
	public List<CDRomRequestBean> getApproverCommentList() {
		return this.approverCommentList;
	}
	/**
	 * @param approverCommentList the approverCommentList to set
	 */
	public void setApproverCommentList(final List<CDRomRequestBean> approverCommentList) {
		this.approverCommentList = approverCommentList;
	}
	/**
	 * @return the apprName
	 */
	public String getApprName() {
		return this.apprName;
	}
	/**
	 * @param apprName the apprName to set
	 */
	public void setApprName(final String apprName) {
		this.apprName = apprName;
	}
	/**
	 * @return the apprComments
	 */
	public String getApprComments() {
		return this.apprComments;
	}
	/**
	 * @param apprComments the apprComments to set
	 */
	public void setApprComments(final String apprComments) {
		this.apprComments = apprComments;
	}
	/**
	 * @return the apprDate
	 */
	public String getApprDate() {
		return this.apprDate;
	}
	/**
	 * @param apprDate the apprDate to set
	 */
	public void setApprDate(final String apprDate) {
		this.apprDate = apprDate;
	}
	/**
	 * @return the apprStatus
	 */
	public String getApprStatus() {
		return this.apprStatus;
	}
	/**
	 * @param apprStatus the apprStatus to set
	 */
	public void setApprStatus(final String apprStatus) {
		this.apprStatus = apprStatus;
	}
	/**
	 * @return the authorizedToken
	 */
	public String getAuthorizedToken() {
		return this.authorizedToken;
	}
	/**
	 * @param authorizedToken the authorizedToken to set
	 */
	public void setAuthorizedToken(final String authorizedToken) {
		this.authorizedToken = authorizedToken;
	}
	/**
	 * @return the initiator
	 */
	public String getInitiator() {
		return this.initiator;
	}
	/**
	 * @param initiator the initiator to set
	 */
	public void setInitiator(final String initiator) {
		this.initiator = initiator;
	}
	/**
	 * @return the completedDate
	 */
	public String getCompletedDate() {
		return this.completedDate;
	}
	/**
	 * @param completedDate the completedDate to set
	 */
	public void setCompletedDate(final String completedDate) {
		this.completedDate = completedDate;
	}
	/**
	 * @return the completedBy
	 */
	public String getCompletedBy() {
		return this.completedBy;
	}
	/**
	 * @param completedBy the completedBy to set
	 */
	public void setCompletedBy(final String completedBy) {
		this.completedBy = completedBy;
	}
	/**
	 * @return the completedByCode
	 */
	public String getCompletedByCode() {
		return this.completedByCode;
	}
	/**
	 * @param completedByCode the completedByCode to set
	 */
	public void setCompletedByCode(final String completedByCode) {
		this.completedByCode = completedByCode;
	}
	/**
	 * @return the draftFlag
	 */
	public boolean isDraftFlag() {
		return this.draftFlag;
	}
	/**
	 * @param draftFlag the draftFlag to set
	 */
	public void setDraftFlag(final boolean draftFlag) {
		this.draftFlag = draftFlag;
	}
	/**
	 * @return the statusDraftBack
	 */
	public boolean isStatusDraftBack() {
		return this.statusDraftBack;
	}
	/**
	 * @param statusDraftBack the statusDraftBack to set
	 */
	public void setStatusDraftBack(final boolean statusDraftBack) {
		this.statusDraftBack = statusDraftBack;
	}
	/**
	 * @return the deptId
	 */
	public String getDeptId() {
		return this.deptId;
	}
	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(final String deptId) {
		this.deptId = deptId;
	}
	/**
	 * @return the requestDetailFile
	 */
	public String getRequestDetailFile() {
		return this.requestDetailFile;
	}
	/**
	 * @param requestDetailFile the requestDetailFile to set
	 */
	public void setRequestDetailFile(final String requestDetailFile) {
		this.requestDetailFile = requestDetailFile;
	}
	/**
	 * @return the addressInfoFile1
	 */
	public String getAddressInfoFile1() {
		return this.addressInfoFile1;
	}
	/**
	 * @param addressInfoFile1 the addressInfoFile1 to set
	 */
	public void setAddressInfoFile1(final String addressInfoFile1) {
		this.addressInfoFile1 = addressInfoFile1;
	}
	/**
	 * @return the addressInfoFile2
	 */
	public String getAddressInfoFile2() {
		return this.addressInfoFile2;
	}
	/**
	 * @param addressInfoFile2 the addressInfoFile2 to set
	 */
	public void setAddressInfoFile2(final String addressInfoFile2) {
		this.addressInfoFile2 = addressInfoFile2;
	}
	/**
	 * @return the dataPath1
	 */
	public String getDataPath1() {
		return this.dataPath1;
	}
	/**
	 * @param dataPath1 the dataPath1 to set
	 */
	public void setDataPath1(final String dataPath1) {
		this.dataPath1 = dataPath1;
	}
	/**
	 * @return the dataPath2
	 */
	public String getDataPath2() {
		return this.dataPath2;
	}
	/**
	 * @param dataPath2 the dataPath2 to set
	 */
	public void setDataPath2(final String dataPath2) {
		this.dataPath2 = dataPath2;
	}
	/**
	 * @return the approvalCommentsFlag
	 */
	public boolean isApprovalCommentsFlag() {
		return this.approvalCommentsFlag;
	}
	/**
	 * @param approvalCommentsFlag the approvalCommentsFlag to set
	 */
	public void setApprovalCommentsFlag(final boolean approvalCommentsFlag) {
		this.approvalCommentsFlag = approvalCommentsFlag;
	}
	/**
	 * @return the draftDataDisplyFlag
	 */
	public boolean isDraftDataDisplyFlag() {
		return this.draftDataDisplyFlag;
	}
	/**
	 * @param draftDataDisplyFlag the draftDataDisplyFlag to set
	 */
	public void setDraftDataDisplyFlag(final boolean draftDataDisplyFlag) {
		this.draftDataDisplyFlag = draftDataDisplyFlag;
	}

}
