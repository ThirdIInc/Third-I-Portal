/**
 * 
 */
package org.paradyne.bean.ApplicationStudio;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0563
 *
 */
public class MenuRelease extends BeanBase {
	int  selMenuCode;
	String check = "";
	String profileId = "";
	private String isTotal="false";
	/**
	 * @return the selMenuCode
	 */
	public int getSelMenuCode() {
		return selMenuCode;
	}

	/**
	 * @param selMenuCode the selMenuCode to set
	 */
	public void setSelMenuCode(int selMenuCode) {
		this.selMenuCode = selMenuCode;
	}

	/**
	 * @return the check
	 */
	public String getCheck() {
		return check;
	}

	/**
	 * @param check the check to set
	 */
	public void setCheck(String check) {
		this.check = check;
	}

	/**
	 * @return the profileId
	 */
	public String getProfileId() {
		return profileId;
	}

	/**
	 * @param profileId the profileId to set
	 */
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public String getIsTotal() {
		return isTotal;
	}

	public void setIsTotal(String isTotal) {
		this.isTotal = isTotal;
	}

}
