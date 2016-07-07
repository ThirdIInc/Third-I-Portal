/*Author: Anantha lakshmi */

package org.paradyne.bean.DataMigration;

import org.paradyne.lib.BeanBase;

public class CandidateDetailsUpload extends BeanBase {
	private String dataPath;
	private String status = "Fail";
	private String note = "";
	private String uploadName;
	private String uploadFileName;
	
	
	private String uploadType;
	private String downloadType;
	private String invCode="";
	private boolean dataExists = false;
	private boolean fileUploaded = false;
	
	
	
	/**
	 * @return the dataPath
	 */
	public String getDataPath() {
		return dataPath;
	}
	/**
	 * @param dataPath the dataPath to set
	 */
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @return the uploadName
	 */
	public String getUploadName() {
		return uploadName;
	}
	/**
	 * @param uploadName the uploadName to set
	 */
	public void setUploadName(String uploadName) {
		this.uploadName = uploadName;
	}
	/**
	 * @return the uploadFileName
	 */
	public String getUploadFileName() {
		return uploadFileName;
	}
	/**
	 * @param uploadFileName the uploadFileName to set
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	/**
	 * @return the uploadType
	 */
	public String getUploadType() {
		return uploadType;
	}
	/**
	 * @param uploadType the uploadType to set
	 */
	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}
	/**
	 * @return the downloadType
	 */
	public String getDownloadType() {
		return downloadType;
	}
	/**
	 * @param downloadType the downloadType to set
	 */
	public void setDownloadType(String downloadType) {
		this.downloadType = downloadType;
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
	 * @return the fileUploaded
	 */
	public boolean isFileUploaded() {
		return fileUploaded;
	}
	/**
	 * @param fileUploaded the fileUploaded to set
	 */
	public void setFileUploaded(boolean fileUploaded) {
		this.fileUploaded = fileUploaded;
	}
	public String getInvCode() {
		return invCode;
	}
	public void setInvCode(String invCode) {
		this.invCode = invCode;
	}
}