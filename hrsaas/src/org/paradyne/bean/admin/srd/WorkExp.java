package org.paradyne.bean.admin.srd;
import org.paradyne.lib.BeanBase;


public class WorkExp extends BeanBase{
	String empId="";
	String empName="";
	String repType="";
	public WorkExp(){
		
	}
	public WorkExp(String empId, String empName, String repType) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.repType = repType;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getRepType() {
		return repType;
	}
	public void setRepType(String repType) {
		this.repType = repType;
	}

	
		
	
}
