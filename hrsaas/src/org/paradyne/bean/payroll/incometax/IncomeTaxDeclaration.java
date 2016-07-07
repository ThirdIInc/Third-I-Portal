/**
 * 
 */
package org.paradyne.bean.payroll.incometax;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0623
 * 
 */
public class IncomeTaxDeclaration extends BeanBase {

	/**
	 * 
	 */
	public IncomeTaxDeclaration() {
		// TODO Auto-generated constructor stub
	}

	private String taxCode = "";
	private String empID = "";
	private String empName = "";
	private String empTokenNo = "";
	private String department = "";
	private String center = "";
	private String fromYear = "";
	private String toYear = "";

	/**
	 * @return the taxCode
	 */
	public String getTaxCode() {
		return taxCode;
	}

	/**
	 * @param taxCode
	 *            the taxCode to set
	 */
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	/**
	 * @return the empID
	 */
	public String getEmpID() {
		return empID;
	}

	/**
	 * @param empID
	 *            the empID to set
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
	 * @param empName
	 *            the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return the empTokenNo
	 */
	public String getEmpTokenNo() {
		return empTokenNo;
	}

	/**
	 * @param empTokenNo
	 *            the empTokenNo to set
	 */
	public void setEmpTokenNo(String empTokenNo) {
		this.empTokenNo = empTokenNo;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return the center
	 */
	public String getCenter() {
		return center;
	}

	/**
	 * @param center
	 *            the center to set
	 */
	public void setCenter(String center) {
		this.center = center;
	}

	/**
	 * @return the fromYear
	 */
	public String getFromYear() {
		return fromYear;
	}

	/**
	 * @param fromYear
	 *            the fromYear to set
	 */
	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}

	/**
	 * @return the toYear
	 */
	public String getToYear() {
		return toYear;
	}

	/**
	 * @param toYear
	 *            the toYear to set
	 */
	public void setToYear(String toYear) {
		this.toYear = toYear;
	}

}
