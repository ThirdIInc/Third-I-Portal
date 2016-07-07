package org.paradyne.bean.admin.srd;
import org.paradyne.lib.BeanBase;
import java.util.*;
/*
 * author:Pradeep Kumar Sahoo
 */

public class OfficerReport extends BeanBase{
	String empId="";
	String empName="";
	String repType="";
	String token="";
	String center="";
	String rank="";
	ArrayList offList;
	String paraId="";
	


	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
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

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getRepType() {
		return repType;
	}

	public void setRepType(String repType) {
		this.repType = repType;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public ArrayList getOffList() {
		return offList;
	}

	public void setOffList(ArrayList offList) {
		this.offList = offList;
	}

	public String getParaId() {
		return paraId;
	}

	public void setParaId(String paraId) {
		this.paraId = paraId;
	}
		
		
	
}
