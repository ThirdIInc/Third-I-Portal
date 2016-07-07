package org.paradyne.bean.common;
import java.util.*;
import org.paradyne.lib.BeanBase;
/*
 * author:Pradeep S
 * Date:30-01-2008
 */

public class PerformanceAppr extends BeanBase {
	
	private TreeMap empMap=null;
	
	private String apprCode;
	private String apprId;
	
	private String empName;
	private String empId;
	
	public String getApprCode() {
		return apprCode;
	}
	public void setApprCode(String apprCode) {
		this.apprCode = apprCode;
	}
	public String getApprId() {
		return apprId;
	}
	public void setApprId(String apprId) {
		this.apprId = apprId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public TreeMap getEmpMap() {
		return empMap;
	}
	public void setEmpMap(TreeMap empMap) {
		this.empMap = empMap;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	

}
