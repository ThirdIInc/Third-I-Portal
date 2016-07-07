package org.paradyne.bean.Training;

import java.io.File;
import java.util.ArrayList;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

public class CourseTypeMaster extends BeanBase{

	private String courseCode="";
	private String courseName="";
	private String courseType="";
	private String courseAddDate="";
	private String courseAddedBy="";
	private String courseIsActive="";
	private String isCertified="";
	private String skillAdv="";
	private String isACtive="";
	private String uploadFileName ="";
	private String hiddencode;
	private String level="";
	
	/* Variables for Course List */
	
	private ArrayList<CourseTypeMaster> courseTypeList;
	private String modeLength="false";
	private String totalPage="";
	private String pageNo="";
	private String myPage="";
	private String totalRecords="";

	
	private ArrayList<CourseTypeMaster> courseSubTypeList;
	private String submodeLength="false";
	private String courseSubType;
	private String courseSubCode;
	private String courseSubDesc;
	private String subSrNo;
	private String subCreatedBy;
	private String subCreatedOn;
	private String courseSubTypeIt;
	private String courseSubCodeIt;
	private String courseSubDescIt;
	private String subSrNoIt;
	private String subCreatedByIt;
	private String subCreatedOnIt;
	

	
	/*For Attached Files*/
	private ArrayList<CourseTypeMaster> attchFilesList;
	private String isAttFile ="false";
	private String attFileName="";	
	private String attFileSrNo="";
	private String removeUpload="";
	private String setFilesFlag	= "false";
	private String dataPath="";
	
	private TreeMap map;

	private File Uploadfile;

	private String UploadfileFileName;

	private String UploadfileContentType;
	
	public File getUploadfile() {
		return Uploadfile;
	}
	public void setUploadfile(File uploadfile) {
		Uploadfile = uploadfile;
	}
	public String getUploadfileFileName() {
		return UploadfileFileName;
	}
	public void setUploadfileFileName(String uploadfileFileName) {
		UploadfileFileName = uploadfileFileName;
	}
	public String getUploadfileContentType() {
		return UploadfileContentType;
	}
	public void setUploadfileContentType(String uploadfileContentType) {
		UploadfileContentType = uploadfileContentType;
	}
	public ArrayList<CourseTypeMaster> getCourseSubTypeList() {
		return courseSubTypeList;
	}
	public void setCourseSubTypeList(ArrayList<CourseTypeMaster> courseSubTypeList) {
		this.courseSubTypeList = courseSubTypeList;
	}
	public String getSubmodeLength() {
		return submodeLength;
	}
	public void setSubmodeLength(String submodeLength) {
		this.submodeLength = submodeLength;
	}
	public String getCourseSubType() {
		return courseSubType;
	}
	public void setCourseSubType(String courseSubType) {
		this.courseSubType = courseSubType;
	}
	public String getCourseSubCode() {
		return courseSubCode;
	}
	public void setCourseSubCode(String courseSubCode) {
		this.courseSubCode = courseSubCode;
	}
	public String getCourseSubDesc() {
		return courseSubDesc;
	}
	public void setCourseSubDesc(String courseSubDesc) {
		this.courseSubDesc = courseSubDesc;
	}
	public String getSubSrNo() {
		return subSrNo;
	}
	public void setSubSrNo(String subSrNo) {
		this.subSrNo = subSrNo;
	}
	public String getSubCreatedBy() {
		return subCreatedBy;
	}
	public void setSubCreatedBy(String subCreatedBy) {
		this.subCreatedBy = subCreatedBy;
	}
	public String getSubCreatedOn() {
		return subCreatedOn;
	}
	public void setSubCreatedOn(String subCreatedOn) {
		this.subCreatedOn = subCreatedOn;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	public String getCourseAddDate() {
		return courseAddDate;
	}
	public void setCourseAddDate(String courseAddDate) {
		this.courseAddDate = courseAddDate;
	}
	public String getCourseAddedBy() {
		return courseAddedBy;
	}
	public void setCourseAddedBy(String courseAddedBy) {
		this.courseAddedBy = courseAddedBy;
	}
	public String getCourseIsActive() {
		return courseIsActive;
	}
	public void setCourseIsActive(String courseIsActive) {
		this.courseIsActive = courseIsActive;
	}
	public ArrayList<CourseTypeMaster> getCourseTypeList() {
		return courseTypeList;
	}
	public void setCourseTypeList(ArrayList<CourseTypeMaster> courseTypeList) {
		this.courseTypeList = courseTypeList;
	}
	public String getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	public String getModeLength() {
		return modeLength;
	}
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}	
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getIsCertified() {
		return isCertified;
	}
	public void setIsCertified(String isCertified) {
		this.isCertified = isCertified;
	}
	public String getSkillAdv() {
		return skillAdv;
	}
	public void setSkillAdv(String skillAdv) {
		this.skillAdv = skillAdv;
	}
	public String getIsACtive() {
		return isACtive;
	}
	public void setIsACtive(String isACtive) {
		this.isACtive = isACtive;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getHiddencode() {
		return hiddencode;
	}
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}	
	public ArrayList<CourseTypeMaster> getAttchFilesList() {
		return attchFilesList;
	}
	public void setAttchFilesList(ArrayList<CourseTypeMaster> attchFilesList) {
		this.attchFilesList = attchFilesList;
	}
	public String getIsAttFile() {
		return isAttFile;
	}
	public void setIsAttFile(String isAttFile) {
		this.isAttFile = isAttFile;
	}	
	public String getAttFileName() {
		return attFileName;
	}
	public void setAttFileName(String attFileName) {
		this.attFileName = attFileName;
	}

	public String getCourseSubTypeIt() {
		return courseSubTypeIt;
	}
	public void setCourseSubTypeIt(String courseSubTypeIt) {
		this.courseSubTypeIt = courseSubTypeIt;
	}
	public String getCourseSubCodeIt() {
		return courseSubCodeIt;
	}
	public void setCourseSubCodeIt(String courseSubCodeIt) {
		this.courseSubCodeIt = courseSubCodeIt;
	}
	public String getCourseSubDescIt() {
		return courseSubDescIt;
	}
	public void setCourseSubDescIt(String courseSubDescIt) {
		this.courseSubDescIt = courseSubDescIt;
	}
	public String getSubSrNoIt() {
		return subSrNoIt;
	}
	public void setSubSrNoIt(String subSrNoIt) {
		this.subSrNoIt = subSrNoIt;
	}
	public String getSubCreatedByIt() {
		return subCreatedByIt;
	}
	public void setSubCreatedByIt(String subCreatedByIt) {
		this.subCreatedByIt = subCreatedByIt;
	}
	public String getSubCreatedOnIt() {
		return subCreatedOnIt;
	}
	public void setSubCreatedOnIt(String subCreatedOnIt) {
		this.subCreatedOnIt = subCreatedOnIt;
	}

	public String getAttFileSrNo() {
		return attFileSrNo;
	}
	public void setAttFileSrNo(String attFileSrNo) {
		this.attFileSrNo = attFileSrNo;
	}
	public String getRemoveUpload() {
		return removeUpload;
	}
	public void setRemoveUpload(String removeUpload) {
		this.removeUpload = removeUpload;
	}
	public String getSetFilesFlag() {
		return setFilesFlag;
	}
	public void setSetFilesFlag(String setFilesFlag) {
		this.setFilesFlag = setFilesFlag;
	}
	public String getDataPath() {
		return dataPath;
	}
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public TreeMap getMap() {
		return map;
	}
	public void setMap(TreeMap map) {
		this.map = map;
	}	

}
