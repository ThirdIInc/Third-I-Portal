/**
 * 
 */
package org.paradyne.bean.Recruitment;

import org.paradyne.lib.BeanBase;

/**
 * @author aa0517
 *
 */
public class CandChangePassword extends BeanBase {

	private String candName = "";
	private String oldPassword = "";
	private String newPassword = "";
	private String confirmPass = "";
	
	public String getCandName() {
		return candName;
	}
	public void setCandName(String candName) {
		this.candName = candName;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPass() {
		return confirmPass;
	}
	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}
}
