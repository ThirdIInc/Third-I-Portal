package org.paradyne.bean.admin.srd;

import org.paradyne.lib.BeanBase;

public class ServiceBook extends BeanBase {
	private String empId = "";
	private String empName = "";
	private String repType = "";
	private String token = "";
	private String center = "";
	private String rank = "";

	public ServiceBook() {}

	public ServiceBook(String empId, String empName, String repType, String token, String center, String rank) {
		super();

		this.empId = empId;
		this.empName = empName;
		this.repType = repType;
		this.token = token;
		this.center = center;
		this.rank = rank;
	}

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
}