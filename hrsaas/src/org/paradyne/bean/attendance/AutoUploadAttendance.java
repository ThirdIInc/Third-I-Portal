/* Bhushan Dasare Feb 25, 2010 */

package org.paradyne.bean.attendance;

import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

public class AutoUploadAttendance extends BeanBase {
	private String uploadId;
	private String driver;
	private String server;
	private String userName;
	private String password;
	private String database;
	private String portNo;
	private String tableName;
	private boolean dataExists = false;
	
	private String data_exit;
	
	private String empFlag;
	private String dateFlag;
	private String wrkHoursFlag;
	private String shiftFlag;
	private String inTimeFlag;
	private String outTimeFlag;
	private String timeFlag;
	private String ioFlag;	
	
	private String empField;
	private String dateField;
	private String wrkHoursField;
	private String shiftField;
	private String inTimeField;
	private String outTimeField;
	private String timeField;
	private String ioField;
	private String autoUploadID;
	private String radioFlag;	
	
	private ArrayList<AutoUploadAttendance> autoUploadList = new ArrayList<AutoUploadAttendance>();
	private String totalRecords;
	
	private boolean jobScheduled = false;
	private String jobDuration;
	private String jobDayOfWeek;
	private String jobDayOfMonth;
	private String jobStartTime;
	
	
	/**
	 * @return the uploadId
	 */
	public String getUploadId() {
		return uploadId;
	}
	/**
	 * @param uploadId the uploadId to set
	 */
	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}
	/**
	 * @return the driver
	 */
	public String getDriver() {
		return driver;
	}
	/**
	 * @param driver the driver to set
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}
	/**
	 * @return the server
	 */
	public String getServer() {
		return server;
	}
	/**
	 * @param server the server to set
	 */
	public void setServer(String server) {
		this.server = server;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the database
	 */
	public String getDatabase() {
		return database;
	}
	/**
	 * @param database the database to set
	 */
	public void setDatabase(String database) {
		this.database = database;
	}
	/**
	 * @return the portNo
	 */
	public String getPortNo() {
		return portNo;
	}
	/**
	 * @param portNo the portNo to set
	 */
	public void setPortNo(String portNo) {
		this.portNo = portNo;
	}
	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	/**
	 * @return the dataExists
	 */
	public boolean isDataExists() {
		return dataExists;
	}
	/**
	 * @param dataExists the dataExists to set
	 */
	public void setDataExists(boolean dataExists) {
		this.dataExists = dataExists;
	}
	/**
	 * @return the data_exit
	 */
	public String getData_exit() {
		return data_exit;
	}
	/**
	 * @param data_exit the data_exit to set
	 */
	public void setData_exit(String data_exit) {
		this.data_exit = data_exit;
	}
	/**
	 * @return the empFlag
	 */
	public String getEmpFlag() {
		return empFlag;
	}
	/**
	 * @param empFlag the empFlag to set
	 */
	public void setEmpFlag(String empFlag) {
		this.empFlag = empFlag;
	}
	/**
	 * @return the dateFlag
	 */
	public String getDateFlag() {
		return dateFlag;
	}
	/**
	 * @param dateFlag the dateFlag to set
	 */
	public void setDateFlag(String dateFlag) {
		this.dateFlag = dateFlag;
	}
	/**
	 * @return the wrkHoursFlag
	 */
	public String getWrkHoursFlag() {
		return wrkHoursFlag;
	}
	/**
	 * @param wrkHoursFlag the wrkHoursFlag to set
	 */
	public void setWrkHoursFlag(String wrkHoursFlag) {
		this.wrkHoursFlag = wrkHoursFlag;
	}
	/**
	 * @return the shiftFlag
	 */
	public String getShiftFlag() {
		return shiftFlag;
	}
	/**
	 * @param shiftFlag the shiftFlag to set
	 */
	public void setShiftFlag(String shiftFlag) {
		this.shiftFlag = shiftFlag;
	}
	/**
	 * @return the inTimeFlag
	 */
	public String getInTimeFlag() {
		return inTimeFlag;
	}
	/**
	 * @param inTimeFlag the inTimeFlag to set
	 */
	public void setInTimeFlag(String inTimeFlag) {
		this.inTimeFlag = inTimeFlag;
	}
	/**
	 * @return the outTimeFlag
	 */
	public String getOutTimeFlag() {
		return outTimeFlag;
	}
	/**
	 * @param outTimeFlag the outTimeFlag to set
	 */
	public void setOutTimeFlag(String outTimeFlag) {
		this.outTimeFlag = outTimeFlag;
	}
	/**
	 * @return the timeFlag
	 */
	public String getTimeFlag() {
		return timeFlag;
	}
	/**
	 * @param timeFlag the timeFlag to set
	 */
	public void setTimeFlag(String timeFlag) {
		this.timeFlag = timeFlag;
	}
	/**
	 * @return the ioFlag
	 */
	public String getIoFlag() {
		return ioFlag;
	}
	/**
	 * @param ioFlag the ioFlag to set
	 */
	public void setIoFlag(String ioFlag) {
		this.ioFlag = ioFlag;
	}
	/**
	 * @return the empField
	 */
	public String getEmpField() {
		return empField;
	}
	/**
	 * @param empField the empField to set
	 */
	public void setEmpField(String empField) {
		this.empField = empField;
	}
	/**
	 * @return the dateField
	 */
	public String getDateField() {
		return dateField;
	}
	/**
	 * @param dateField the dateField to set
	 */
	public void setDateField(String dateField) {
		this.dateField = dateField;
	}
	/**
	 * @return the wrkHoursField
	 */
	public String getWrkHoursField() {
		return wrkHoursField;
	}
	/**
	 * @param wrkHoursField the wrkHoursField to set
	 */
	public void setWrkHoursField(String wrkHoursField) {
		this.wrkHoursField = wrkHoursField;
	}
	/**
	 * @return the shiftField
	 */
	public String getShiftField() {
		return shiftField;
	}
	/**
	 * @param shiftField the shiftField to set
	 */
	public void setShiftField(String shiftField) {
		this.shiftField = shiftField;
	}
	/**
	 * @return the inTimeField
	 */
	public String getInTimeField() {
		return inTimeField;
	}
	/**
	 * @param inTimeField the inTimeField to set
	 */
	public void setInTimeField(String inTimeField) {
		this.inTimeField = inTimeField;
	}
	/**
	 * @return the outTimeField
	 */
	public String getOutTimeField() {
		return outTimeField;
	}
	/**
	 * @param outTimeField the outTimeField to set
	 */
	public void setOutTimeField(String outTimeField) {
		this.outTimeField = outTimeField;
	}
	/**
	 * @return the timeField
	 */
	public String getTimeField() {
		return timeField;
	}
	/**
	 * @param timeField the timeField to set
	 */
	public void setTimeField(String timeField) {
		this.timeField = timeField;
	}
	/**
	 * @return the ioField
	 */
	public String getIoField() {
		return ioField;
	}
	/**
	 * @param ioField the ioField to set
	 */
	public void setIoField(String ioField) {
		this.ioField = ioField;
	}
	/**
	 * @return the autoUploadID
	 */
	public String getAutoUploadID() {
		return autoUploadID;
	}
	/**
	 * @param autoUploadID the autoUploadID to set
	 */
	public void setAutoUploadID(String autoUploadID) {
		this.autoUploadID = autoUploadID;
	}
	/**
	 * @return the radioFlag
	 */
	public String getRadioFlag() {
		return radioFlag;
	}
	/**
	 * @param radioFlag the radioFlag to set
	 */
	public void setRadioFlag(String radioFlag) {
		this.radioFlag = radioFlag;
	}
	/**
	 * @return the autoUploadList
	 */
	public ArrayList<AutoUploadAttendance> getAutoUploadList() {
		return autoUploadList;
	}
	/**
	 * @param autoUploadList the autoUploadList to set
	 */
	public void setAutoUploadList(ArrayList<AutoUploadAttendance> autoUploadList) {
		this.autoUploadList = autoUploadList;
	}
	/**
	 * @return the totalRecords
	 */
	public String getTotalRecords() {
		return totalRecords;
	}
	/**
	 * @param totalRecords the totalRecords to set
	 */
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	/**
	 * @return the jobScheduled
	 */
	public boolean isJobScheduled() {
		return jobScheduled;
	}
	/**
	 * @param jobScheduled the jobScheduled to set
	 */
	public void setJobScheduled(boolean jobScheduled) {
		this.jobScheduled = jobScheduled;
	}
	/**
	 * @return the jobDuration
	 */
	public String getJobDuration() {
		return jobDuration;
	}
	/**
	 * @param jobDuration the jobDuration to set
	 */
	public void setJobDuration(String jobDuration) {
		this.jobDuration = jobDuration;
	}
	/**
	 * @return the jobDayOfWeek
	 */
	public String getJobDayOfWeek() {
		return jobDayOfWeek;
	}
	/**
	 * @param jobDayOfWeek the jobDayOfWeek to set
	 */
	public void setJobDayOfWeek(String jobDayOfWeek) {
		this.jobDayOfWeek = jobDayOfWeek;
	}
	/**
	 * @return the jobDayOfMonth
	 */
	public String getJobDayOfMonth() {
		return jobDayOfMonth;
	}
	/**
	 * @param jobDayOfMonth the jobDayOfMonth to set
	 */
	public void setJobDayOfMonth(String jobDayOfMonth) {
		this.jobDayOfMonth = jobDayOfMonth;
	}
	/**
	 * @return the jobStartTime
	 */
	public String getJobStartTime() {
		return jobStartTime;
	}
	/**
	 * @param jobStartTime the jobStartTime to set
	 */
	public void setJobStartTime(String jobStartTime) {
		this.jobStartTime = jobStartTime;
	}	
}