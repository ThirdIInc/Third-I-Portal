package org.paradyne.bean.payroll;
/*
 * Date: 30/11/2010
 * Ganesh
 */
import org.paradyne.lib.BeanBase;

public class LTCConfigBean extends BeanBase {
	
	private String creditCode = "";
	private String creditName = "";
	private String attnDays = "";
	private String leaveDays = "";
	private String trvlDays = "";
	
	
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public String getCreditName() {
		return creditName;
	}

	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}

	public String getAttnDays() {
		return attnDays;
	}


	public void setAttnDays(String attnDays) {
		this.attnDays = attnDays;
	}

	public String getLeaveDays() {
		return leaveDays;
	}


	public void setLeaveDays(String leaveDays) {
		this.leaveDays = leaveDays;
	}

	public String getTrvlDays() {
		return trvlDays;
	}

	public void setTrvlDays(String trvlDays) {
		this.trvlDays = trvlDays;
	}
	

}
