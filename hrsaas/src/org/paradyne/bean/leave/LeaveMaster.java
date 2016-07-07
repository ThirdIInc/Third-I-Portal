/**
 * 
 */
package org.paradyne.bean.leave;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * 	@author sunil
 *	purpose : 	To provide an implementation of Leave information Bean.
 *				To be used in Leave Master of admin Section
 */
public class LeaveMaster extends BeanBase implements Serializable {
	
	String leavCode = "";
	String leavName = "";
	String leavDesc = "";
	ArrayList testList = null;
	
	public LeaveMaster() {
		
	}
	/**
	 * 
	 * @param leavCode
	 * @param leavName
	 * @param leavDesc
	 */
	public LeaveMaster(String leavCode, String leavName, String leavDesc) {
		this.leavCode = leavCode;
		this.leavName = leavName;
		this.leavDesc = leavDesc;
	}
	
	public ArrayList getTestList() {
		return testList;
	}

	public void setTestList(ArrayList testList) {
		this.testList = testList;
	}
	
	/**
	 * @return the leavCode
	 */
	public String getLeavCode() {
		return leavCode;
	}
	/**
	 * @param leavCode the leavCode to set
	 */
	public void setLeavCode(String leavCode) {
		this.leavCode = leavCode;
	}
	/**
	 * @return the leavDesc
	 */
	public String getLeavDesc() {
		return leavDesc;
	}
	/**
	 * @param leavDesc the leavDesc to set
	 */
	public void setLeavDesc(String leavDesc) {
		this.leavDesc = leavDesc;
	}
	/**
	 * @return the leavName
	 */
	public String getLeavName() {
		return leavName;
	}
	/**
	 * @param leavName the leavName to set
	 */
	public void setLeavName(String leavName) {
		this.leavName = leavName;
	}
}