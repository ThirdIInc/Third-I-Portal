package org.paradyne.bean.payroll.incometax;
import org.paradyne.lib.BeanBase;
import java.util.ArrayList;
/*
 * author:Pradeep Kumar Sahoo
 * Date:26.03.2008
 */
public class HouseRent extends BeanBase {
	
	private String rentCode="";
	private String empid="";
	private String empName="";
	private String fromYear="";
	private String toYear="";
	private String monthNo="";
	private String month="";
	private String amount="";
	private String empTokenNo="";
	private String center="";
	private String designation="";
	private String rentFlag="false";
	private ArrayList rentList=null;
	private String totAmt="";
	private String joinFlag="false";
	private String dataPath="";
	private String owner1Agreement="";
	private String owner2Agreement="";
	private String proofAttach="";
	public ArrayList getRentList() {
		return rentList;
	}
	public void setRentList(ArrayList rentList) {
		this.rentList = rentList;
	}
	public HouseRent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HouseRent(String rentCode, String empid, String empName,
			String fromYear, String toYear, String monthNo, String month,
			String amount, String empTokenNo, String center, String designation) {
		super();
		this.rentCode = rentCode;
		this.empid = empid;
		this.empName = empName;
		this.fromYear = fromYear;
		this.toYear = toYear;
		this.monthNo = monthNo;
		this.month = month;
		this.amount = amount;
		this.empTokenNo = empTokenNo;
		this.center = center;
		this.designation = designation;
	}
	public String getRentCode() {
		return rentCode;
	}
	public void setRentCode(String rentCode) {
		this.rentCode = rentCode;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getFromYear() {
		return fromYear;
	}
	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}
	public String getToYear() {
		return toYear;
	}
	public void setToYear(String toYear) {
		this.toYear = toYear;
	}
	public String getMonthNo() {
		return monthNo;
	}
	public void setMonthNo(String monthNo) {
		this.monthNo = monthNo;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getEmpTokenNo() {
		return empTokenNo;
	}
	public void setEmpTokenNo(String empTokenNo) {
		this.empTokenNo = empTokenNo;
	}
	public String getCenter() {
		return center;
	}
	public void setCenter(String center) {
		this.center = center;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getRentFlag() {
		return rentFlag;
	}
	public void setRentFlag(String rentFlag) {
		this.rentFlag = rentFlag;
	}
	public String getTotAmt() {
		return totAmt;
	}
	public void setTotAmt(String totAmt) {
		this.totAmt = totAmt;
	}
	public String getJoinFlag() {
		return joinFlag;
	}
	public void setJoinFlag(String joinFlag) {
		this.joinFlag = joinFlag;
	}
	public String getDataPath() {
		return dataPath;
	}
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	public String getOwner1Agreement() {
		return owner1Agreement;
	}
	public void setOwner1Agreement(String owner1Agreement) {
		this.owner1Agreement = owner1Agreement;
	}
	public String getOwner2Agreement() {
		return owner2Agreement;
	}
	public void setOwner2Agreement(String owner2Agreement) {
		this.owner2Agreement = owner2Agreement;
	}
	public String getProofAttach() {
		return proofAttach;
	}
	public void setProofAttach(String proofAttach) {
		this.proofAttach = proofAttach;
	}
	

}
