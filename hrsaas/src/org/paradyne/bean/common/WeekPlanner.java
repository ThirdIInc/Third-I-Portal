/**
 * 
 */
package org.paradyne.bean.common;

import java.util.ArrayList;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

/**
 * @author shashikant
 * 
 */
public class WeekPlanner extends BeanBase {
	private String uploadLocFileName="";
	private String uploadFWFileName="";
	private ArrayList attachMultDocList;
	private String searchProject="";
	private String dataPath="";
	private String editFlag="false";
	private String fwHisName="";
	private String fwHisdate="";
	private String fwHisStatus="";
	private String fwHisComment="";
	private String fwHisAttchFile="";
	private ArrayList fwHisList;
	private String fwempToken="";
	private String fwempCode="";
	private String fwempName="";
	private String fwComment="";
	private String fwStatus="";
	private String otherTaskProject="";
	private String otherTaskType="";
	private String isEdit ="";
	private String priority="";
	String newtask = "";
	String taskStartTime = "";
	String taskEndTime = "";
	String newtaskDate = "";
	String hiddentask = "";

	String subject = "";
	String newStartDate = "";
	String startTimeHr = "";
	String startTimeMin = "";
	String newEndDate = "";
	String endTimeHr = "";
	String endTimeMin = "";

	String taskType = "";
	String empToken = "";
	String empCode = "";
	String taskTitle = "";
	String taskTitleNew = "";
	String taskDesc = "";
	String status = "";
	String empName = "";
	String endDate = "";
	String title = "";
	String startDate = "";
	String assigned = "";
	private ArrayList showList;
	String taskId = "";
	String hiddentaskId = "";
	private String type = "";
	private String project = "";
	
	private String searchDate = "";
	private String toDate = "";
	private String searchTaskTitle = "";
	private String searchStatus = "";
	private String searchEmp="";
	private String islist = "";
	private String hiddenChk = "";
	private String saveFlag = "true";
	private String searchEmpCode = "";
	private String iteratorstatus = "";
	private String taskInitiatedBy="";
	private String forwardFlag="";
	private String statusFlag="";
	private String endSearchDateStart="";
	private String endSearchDateEnd="";
	TreeMap tmap;
	TreeMap tmap1;
	TreeMap tmap2;
	
	/**
	 *  Task Status List which is displayed
	 */
	private ArrayList taskStatusList;
	private String projectName = "";
	private String modeLength="false";
	private String totalRecords="";
	private String myPageInProcess="";
	private String myPage="";
	private String statusComment="";
	private String statusHrs="";
	/**
	 *  Forward History 
	 */
	private String statusPercentage="";
	/**
	 *  Forward History Hrs
	 */
	private String forwardHrs="";
	/**
	 * Forward History Percentage
	 */
	private String forwardPercentage="";
	/**
	 * Task Status Employee Name
	 */
	private String statusEmpName="";
	/**
	 * Task Status Date
	 */
	private String statusDate="";
	/**
	 * Task Status Comments
	 */
	private String statusTaskComment="";
	/**
	 * Task Status Completed Hrs
	 */
	private String statusTaskHrs="";
	/**
	 *  Task Status Completed Percentage
	 */
	private String statusTaskPercentage="";
	/**
	 *  This flag is set true when the task is closed
	 */
	private String closedFlag="";
	
	private String docFlag="";


	public String getDocFlag() {
		return docFlag;
	}

	public void setDocFlag(String docFlag) {
		this.docFlag = docFlag;
	}

	public String getStatusEmpName() {
		return statusEmpName;
	}

	public void setStatusEmpName(String statusEmpName) {
		this.statusEmpName = statusEmpName;
	}

	public String getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(String statusDate) {
		this.statusDate = statusDate;
	}

	public String getStatusTaskComment() {
		return statusTaskComment;
	}

	public void setStatusTaskComment(String statusTaskComment) {
		this.statusTaskComment = statusTaskComment;
	}

	public String getStatusTaskHrs() {
		return statusTaskHrs;
	}

	public void setStatusTaskHrs(String statusTaskHrs) {
		this.statusTaskHrs = statusTaskHrs;
	}

	public String getStatusTaskPercentage() {
		return statusTaskPercentage;
	}

	public void setStatusTaskPercentage(String statusTaskPercentage) {
		this.statusTaskPercentage = statusTaskPercentage;
	}

	public String getStatusComment() {
		return statusComment;
	}

	public void setStatusComment(String statusComment) {
		this.statusComment = statusComment;
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

	public String getMyPageInProcess() {
		return myPageInProcess;
	}

	public void setMyPageInProcess(String myPageInProcess) {
		this.myPageInProcess = myPageInProcess;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	/** added by vivek wadhwani
	 * @return project name for List
	 */
	public String getProjectName() {
		return projectName;
	}

	/**  added by vivek wadhwani
	 * @param projectName for List
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public TreeMap getTmap2() {
		return tmap2;
	}

	public void setTmap2(TreeMap tmap2) {
		this.tmap2 = tmap2;
	}

	public TreeMap getTmap() {
		return tmap;
	}

	public void setTmap(TreeMap tmap) {
		this.tmap = tmap;
	}

	public TreeMap getTmap1() {
		return tmap1;
	}

	public void setTmap1(TreeMap tmap1) {
		this.tmap1 = tmap1;
	}

	public String getIslist() {
		return islist;
	}

	public void setIslist(String islist) {
		this.islist = islist;
	}

	public String getHiddenChk() {
		return hiddenChk;
	}

	public void setHiddenChk(String hiddenChk) {
		this.hiddenChk = hiddenChk;
	}

	public String getHiddentask() {
		return hiddentask;
	}

	public void setHiddentask(String hiddentask) {
		this.hiddentask = hiddentask;
	}

	public String getNewtask() {
		return newtask;
	}

	public void setNewtask(String newtask) {
		this.newtask = newtask;
	}

	public String getNewtaskDate() {
		return newtaskDate;
	}

	public void setNewtaskDate(String newtaskDate) {
		this.newtaskDate = newtaskDate;
	}

	public String getTaskStartTime() {
		return taskStartTime;
	}

	public void setTaskStartTime(String taskStartTime) {
		this.taskStartTime = taskStartTime;
	}

	public String getTaskEndTime() {
		return taskEndTime;
	}

	public void setTaskEndTime(String taskEndTime) {
		this.taskEndTime = taskEndTime;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getNewStartDate() {
		return newStartDate;
	}

	public void setNewStartDate(String newStartDate) {
		this.newStartDate = newStartDate;
	}

	public String getStartTimeHr() {
		return startTimeHr;
	}

	public void setStartTimeHr(String startTimeHr) {
		this.startTimeHr = startTimeHr;
	}

	public String getStartTimeMin() {
		return startTimeMin;
	}

	public void setStartTimeMin(String startTimeMin) {
		this.startTimeMin = startTimeMin;
	}

	public String getNewEndDate() {
		return newEndDate;
	}

	public void setNewEndDate(String newEndDate) {
		this.newEndDate = newEndDate;
	}

	public String getEndTimeHr() {
		return endTimeHr;
	}

	public void setEndTimeHr(String endTimeHr) {
		this.endTimeHr = endTimeHr;
	}

	public String getEndTimeMin() {
		return endTimeMin;
	}

	public void setEndTimeMin(String endTimeMin) {
		this.endTimeMin = endTimeMin;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getEmpToken() {
		return empToken;
	}

	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getAssigned() {
		return assigned;
	}

	public void setAssigned(String assigned) {
		this.assigned = assigned;
	}

	public ArrayList getShowList() {
		return showList;
	}

	public void setShowList(ArrayList showList) {
		this.showList = showList;
	}

	public String getTaskTitleNew() {
		return taskTitleNew;
	}

	public void setTaskTitleNew(String taskTitleNew) {
		this.taskTitleNew = taskTitleNew;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getHiddentaskId() {
		return hiddentaskId;
	}

	public void setHiddentaskId(String hiddentaskId) {
		this.hiddentaskId = hiddentaskId;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}

	public String getSearchTaskTitle() {
		return searchTaskTitle;
	}

	public void setSearchTaskTitle(String searchTaskTitle) {
		this.searchTaskTitle = searchTaskTitle;
	}

	public String getSearchStatus() {
		return searchStatus;
	}

	public void setSearchStatus(String searchStatus) {
		this.searchStatus = searchStatus;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getSaveFlag() {
		return saveFlag;
	}

	public void setSaveFlag(String saveFlag) {
		this.saveFlag = saveFlag;
	}

	public String getIteratorstatus() {
		return iteratorstatus;
	}

	public void setIteratorstatus(String iteratorstatus) {
		this.iteratorstatus = iteratorstatus;
	}

	public String getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
	}

	/**
	 * @return the otherTaskProject
	 */
	public String getOtherTaskProject() {
		return otherTaskProject;
	}

	/**
	 * @param otherTaskProject the otherTaskProject to set
	 */
	public void setOtherTaskProject(String otherTaskProject) {
		this.otherTaskProject = otherTaskProject;
	}

	/**
	 * @return the otherTaskType
	 */
	public String getOtherTaskType() {
		return otherTaskType;
	}

	/**
	 * @param otherTaskType the otherTaskType to set
	 */
	public void setOtherTaskType(String otherTaskType) {
		this.otherTaskType = otherTaskType;
	}

	public String getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}

	public String getFwempToken() {
		return fwempToken;
	}

	public void setFwempToken(String fwempToken) {
		this.fwempToken = fwempToken;
	}

	public String getFwempCode() {
		return fwempCode;
	}

	public void setFwempCode(String fwempCode) {
		this.fwempCode = fwempCode;
	}

	public String getFwempName() {
		return fwempName;
	}

	public void setFwempName(String fwempName) {
		this.fwempName = fwempName;
	}

	public String getFwComment() {
		return fwComment;
	}

	public void setFwComment(String fwComment) {
		this.fwComment = fwComment;
	}

	public String getFwStatus() {
		return fwStatus;
	}

	public void setFwStatus(String fwStatus) {
		this.fwStatus = fwStatus;
	}

	public String getFwHisName() {
		return fwHisName;
	}

	public void setFwHisName(String fwHisName) {
		this.fwHisName = fwHisName;
	}

	public String getFwHisdate() {
		return fwHisdate;
	}

	public void setFwHisdate(String fwHisdate) {
		this.fwHisdate = fwHisdate;
	}

	public String getFwHisStatus() {
		return fwHisStatus;
	}

	public void setFwHisStatus(String fwHisStatus) {
		this.fwHisStatus = fwHisStatus;
	}

	public String getFwHisComment() {
		return fwHisComment;
	}

	public void setFwHisComment(String fwHisComment) {
		this.fwHisComment = fwHisComment;
	}

	public ArrayList getFwHisList() {
		return fwHisList;
	}

	public void setFwHisList(ArrayList fwHisList) {
		this.fwHisList = fwHisList;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getUploadLocFileName() {
		return uploadLocFileName;
	}

	public void setUploadLocFileName(String uploadLocFileName) {
		this.uploadLocFileName = uploadLocFileName;
	}

	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}

	public ArrayList getAttachMultDocList() {
		return attachMultDocList;
	}

	public void setAttachMultDocList(ArrayList attachMultDocList) {
		this.attachMultDocList = attachMultDocList;
	}

	public String getUploadFWFileName() {
		return uploadFWFileName;
	}

	public void setUploadFWFileName(String uploadFWFileName) {
		this.uploadFWFileName = uploadFWFileName;
	}

	public String getFwHisAttchFile() {
		return fwHisAttchFile;
	}

	public void setFwHisAttchFile(String fwHisAttchFile) {
		this.fwHisAttchFile = fwHisAttchFile;
	}

	public String getSearchProject() {
		return searchProject;
	}

	public void setSearchProject(String searchProject) {
		this.searchProject = searchProject;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getSearchEmp() {
		return searchEmp;
	}

	public void setSearchEmp(String searchEmp) {
		this.searchEmp = searchEmp;
	}

	public String getSearchEmpCode() {
		return searchEmpCode;
	}

	public void setSearchEmpCode(String searchEmpCode) {
		this.searchEmpCode = searchEmpCode;
	}

	public String getTaskInitiatedBy() {
		return taskInitiatedBy;
	}

	public void setTaskInitiatedBy(String taskInitiatedBy) {
		this.taskInitiatedBy = taskInitiatedBy;
	}

	public String getForwardFlag() {
		return forwardFlag;
	}

	public void setForwardFlag(String forwardFlag) {
		this.forwardFlag = forwardFlag;
	}

	public String getEndSearchDateStart() {
		return endSearchDateStart;
	}

	public void setEndSearchDateStart(String endSearchDateStart) {
		this.endSearchDateStart = endSearchDateStart;
	}

	public String getEndSearchDateEnd() {
		return endSearchDateEnd;
	}

	public void setEndSearchDateEnd(String endSearchDateEnd) {
		this.endSearchDateEnd = endSearchDateEnd;
	}

	public String getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}

	public String getStatusHrs() {
		return statusHrs;
	}

	public void setStatusHrs(String statusHrs) {
		this.statusHrs = statusHrs;
	}

	public String getStatusPercentage() {
		return statusPercentage;
	}

	public void setStatusPercentage(String statusPercentage) {
		this.statusPercentage = statusPercentage;
	}

	public String getForwardHrs() {
		return forwardHrs;
	}

	public void setForwardHrs(String forwardHrs) {
		this.forwardHrs = forwardHrs;
	}

	public String getForwardPercentage() {
		return forwardPercentage;
	}

	public void setForwardPercentage(String forwardPercentage) {
		this.forwardPercentage = forwardPercentage;
	}

	public ArrayList getTaskStatusList() {
		return taskStatusList;
	}

	public void setTaskStatusList(ArrayList taskStatusList) {
		this.taskStatusList = taskStatusList;
	}

	public String getClosedFlag() {
		return closedFlag;
	}

	public void setClosedFlag(String closedFlag) {
		this.closedFlag = closedFlag;
	}

}
