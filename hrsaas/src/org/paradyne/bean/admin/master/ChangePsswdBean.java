/**
 * 
 */
package org.paradyne.bean.admin.master;

import org.paradyne.lib.BeanBase;

/**
 * @author MuzaffarS
 *
 */
public class ChangePsswdBean extends BeanBase
{
	private String pssword;
	private String emp_id;
	private String empnm;
	private String oldpsswd;
	private String newpsswd1;
	private String newpsswd2;
	private String userName;
	private String oldPass;
	//private String newPass;
	//private String confPass;
	//private String mailPassword;
	
	/*public String getMailPassword() {
		return mailPassword;
	}
	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}*/
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOldPass() {
		return oldPass;
	}
	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}
	/*public String getNewPass() {
		return newPass;
	}
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	public String getConfPass() {
		return confPass;
	}
	public void setConfPass(String confPass) {
		this.confPass = confPass;
	}*/
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmpnm() {
		return empnm;
	}
	public void setEmpnm(String empnm) {
		this.empnm = empnm;
	}
	public String getNewpsswd1() {
		return newpsswd1;
	}
	public void setNewpsswd1(String newpsswd1) {
		this.newpsswd1 = newpsswd1;
	}
	public String getNewpsswd2() {
		return newpsswd2;
	}
	public void setNewpsswd2(String newpsswd2) {
		this.newpsswd2 = newpsswd2;
	}
	public String getOldpsswd() {
		return oldpsswd;
	}
	public void setOldpsswd(String oldpsswd) {
		this.oldpsswd = oldpsswd;
	}
	public String getPssword() {
		return pssword;
	}
	public void setPssword(String pssword) {
		this.pssword = pssword;
	}
}
