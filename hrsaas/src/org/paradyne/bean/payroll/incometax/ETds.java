/**
 * 
 */
package org.paradyne.bean.payroll.incometax;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0517
 *
 */
public class ETds extends BeanBase {
	
	private String uploadFileName="";
	private String uploadChallanFileName = "";

	public String getUploadChallanFileName() {
		return uploadChallanFileName;
	}

	public void setUploadChallanFileName(String uploadChallanFileName) {
		this.uploadChallanFileName = uploadChallanFileName;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

}
