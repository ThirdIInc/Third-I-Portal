package org.paradyne.bean.PAS;

import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

/**
 * @author REEBA_JOSEPH
 * 22 OCTOBER 2010
 *
 */

public class MonthlyRating extends BeanBase {
	private String month = "";
	private String year = "";
	private String empId = "";
	private String empToken = "";
	private String empName = "";
	ArrayList empList;
	private boolean recordsAvailable = false;
	private String rating = "";
	private String viewEmp = "false";
	private String dataPath;
	private String status = "Fail";
	private String uploadFileName;
	private String note = "";
	private String uploadName="";
	public String getUploadName() {
		return uploadName;
	}
	public void setUploadName(String uploadName) {
		this.uploadName = uploadName;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getDataPath() {
		return dataPath;
	}
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getViewEmp() {
		return viewEmp;
	}
	public void setViewEmp(String viewEmp) {
		this.viewEmp = viewEmp;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public boolean isRecordsAvailable() {
		return recordsAvailable;
	}
	public void setRecordsAvailable(boolean recordsAvailable) {
		this.recordsAvailable = recordsAvailable;
	}
	public ArrayList getEmpList() {
		return empList;
	}
	public void setEmpList(ArrayList empList) {
		this.empList = empList;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
}
