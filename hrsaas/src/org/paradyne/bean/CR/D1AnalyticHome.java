package org.paradyne.bean.CR;
import org.paradyne.lib.BeanBase;

/**@purpose : Create Home page for Analytics Module
 * @author AA1711
 * @Date : 17-Jan-2013
 */
public class D1AnalyticHome extends BeanBase{

	
	/** string. * */
	private String customerOldPassword = "";
	/** string. * */
	private String customerNewPassword = "";
	/** string. * */
	private String customerConfirmPass = "";
	
	private String accountNo="";
	private String dashBoardID="";
	private String accountID="";
	private String accountName="";
	
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getDashBoardID() {
		return dashBoardID;
	}
	public void setDashBoardID(String dashBoardID) {
		this.dashBoardID = dashBoardID;
	}
	public String getAccountID() {
		return accountID;
	}
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getCustomerOldPassword() {
		return customerOldPassword;
	}
	public void setCustomerOldPassword(String customerOldPassword) {
		this.customerOldPassword = customerOldPassword;
	}
	public String getCustomerNewPassword() {
		return customerNewPassword;
	}
	public void setCustomerNewPassword(String customerNewPassword) {
		this.customerNewPassword = customerNewPassword;
	}
	public String getCustomerConfirmPass() {
		return customerConfirmPass;
	}
	public void setCustomerConfirmPass(String customerConfirmPass) {
		this.customerConfirmPass = customerConfirmPass;
	}
	
	
}
