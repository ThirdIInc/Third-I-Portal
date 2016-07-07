package org.paradyne.bean.Recruitment;
import org.paradyne.bean.common.LoginBean;
import org.paradyne.lib.BeanBase;
/**
 * 
 * @author Pradeep Kumar Sahoo
 * Date:05-05-2009
 *
 */

public class CandidateReg extends CandidateLoginBean {
	
	private String firstName="";
	private String lastname="";
	private String emailId="";
	private String userName="";
	private String password="";
	private String reEnterPassword="";
	private String candidateCode="";
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getReEnterPassword() {
		return reEnterPassword;
	}
	public void setReEnterPassword(String reEnterPassword) {
		this.reEnterPassword = reEnterPassword;
	}
	public String getCandidateCode() {
		return candidateCode;
	}
	public void setCandidateCode(String candidateCode) {
		this.candidateCode = candidateCode;
	}
}
