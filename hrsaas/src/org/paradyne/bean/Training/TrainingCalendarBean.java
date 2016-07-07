/**
 * 
 */
package org.paradyne.bean.Training;

import java.util.ArrayList;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

/**
 * @author ankita.wankhade
 *
 */
public class TrainingCalendarBean extends BeanBase {

	/**
	 * @param args
	 */
	private String courseName;
	private String desc;
	private String trainingID;
	private String isCertification;
	private String facultyType;
	private String trainingType;
	private String isActive;
	private String division;
	private String branch;
	private String scheduleDate;
	private String scheduleStartDate;
	private String scheduleEndDate;
	private String cutOffDate;
	ArrayList courseList;
	private String myPage;
	TreeMap map;
	private String totalRecords="";
	private String modeLength="false";
    private String report="";
	
	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getIsCertification() {
		return isCertification;
	}
	public void setIsCertification(String isCertification) {
		this.isCertification = isCertification;
	}
	public String getFacultyType() {
		return facultyType;
	}
	public void setFacultyType(String facultyType) {
		this.facultyType = facultyType;
	}
	public String getTrainingType() {
		return trainingType;
	}
	public void setTrainingType(String trainingType) {
		this.trainingType = trainingType;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	public String getCutOffDate() {
		return cutOffDate;
	}
	public void setCutOffDate(String cutOffDate) {
		this.cutOffDate = cutOffDate;
	}
	public String getModeLength() {
		return modeLength;
	}
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
	public ArrayList getCourseList() {
		return courseList;
	}
	public void setCourseList(ArrayList courseList) {
		this.courseList = courseList;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getTrainingID() {
		return trainingID;
	}

	public void setTrainingID(String trainingID) {
		this.trainingID = trainingID;
	}

	public String getScheduleStartDate() {
		return scheduleStartDate;
	}

	public void setScheduleStartDate(String scheduleStartDate) {
		this.scheduleStartDate = scheduleStartDate;
	}

	public String getScheduleEndDate() {
		return scheduleEndDate;
	}

	public void setScheduleEndDate(String scheduleEndDate) {
		this.scheduleEndDate = scheduleEndDate;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public TreeMap getMap() {
		return map;
	}

	public void setMap(TreeMap map) {
		this.map = map;
	}

}
