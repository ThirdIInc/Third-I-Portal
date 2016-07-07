/**
 * 
 */
package org.paradyne.bean.openCloseOffice;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author Reeba_Joseph
 *
 */
public class OpenCloseOfficeConfig extends BeanBase {
	
	private String branchId = "";
	private String branchName ="";
	private String empCode = "";
	private String empToken = "";
	private String ename = "";
	
	private String empIdList ="";
	private String empTokenList ="";
	private String enameList = "";
	private String delEmpCode = "";
	
	private boolean listNotnull = false;
	
	private ArrayList<Object> empList;
	
	
	
	
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getEmpIdList() {
		return empIdList;
	}
	public void setEmpIdList(String empIdList) {
		this.empIdList = empIdList;
	}
	public String getEmpTokenList() {
		return empTokenList;
	}
	public void setEmpTokenList(String empTokenList) {
		this.empTokenList = empTokenList;
	}
	public String getEnameList() {
		return enameList;
	}
	public void setEnameList(String enameList) {
		this.enameList = enameList;
	}
	public ArrayList<Object> getEmpList() {
		return empList;
	}
	public void setEmpList(ArrayList<Object> empList) {
		this.empList = empList;
	}
	public boolean isListNotnull() {
		return listNotnull;
	}
	public void setListNotnull(boolean listNotnull) {
		this.listNotnull = listNotnull;
	}
	public String getDelEmpCode() {
		return delEmpCode;
	}
	public void setDelEmpCode(String delEmpCode) {
		this.delEmpCode = delEmpCode;
	}

}
