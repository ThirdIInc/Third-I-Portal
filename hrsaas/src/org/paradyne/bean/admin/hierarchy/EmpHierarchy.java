/**
 * 
 */
package org.paradyne.bean.admin.hierarchy;

import org.paradyne.lib.BeanBase;

/**
 * @author lakkichand
 *
 */
public class EmpHierarchy extends BeanBase {

	private String empID; 
	private String empName;
	private String empToken;
	private String hiddensub;
	private String flag = "true";
	private String empFlag = "false";
	/**
	 * @return the empID
	 */
	public String getEmpID() {
		return empID;
	}
	/**
	 * @param empID the empID to set
	 */
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}
	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	/**
	 * @return the empToken
	 */
	public String getEmpToken() {
		return empToken;
	}
	/**
	 * @param empToken the empToken to set
	 */
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
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
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getHiddensub() {
		return hiddensub;
	}
	public void setHiddensub(String hiddensub) {
		this.hiddensub = hiddensub;
	}
	
}
