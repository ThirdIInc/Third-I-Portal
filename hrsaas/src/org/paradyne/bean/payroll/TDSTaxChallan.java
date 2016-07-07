package org.paradyne.bean.payroll;
import org.paradyne.lib.BeanBase;
/*
 * author:Pradeep 
 * Date:17-09-2008
 */

public class TDSTaxChallan extends BeanBase {
	
	private String fromMonth="";
	private String fromYear="";
	private String divId="";
	private String divName="";
	private String brnId="";
	private String brnName="";
	private String deptId="";
	private String deptName="";
	private String typeId="";
	private String typeName="";
	
		
	public String getFromMonth() {
		return fromMonth;
	}
	public void setFromMonth(String fromMonth) {
		this.fromMonth = fromMonth;
	}
	public String getFromYear() {
		return fromYear;
	}
	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}
	
	public String getDivId() {
		return divId;
	}
	public void setDivId(String divId) {
		this.divId = divId;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getBrnId() {
		return brnId;
	}
	public void setBrnId(String brnId) {
		this.brnId = brnId;
	}
	public String getBrnName() {
		return brnName;
	}
	public void setBrnName(String brnName) {
		this.brnName = brnName;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}
