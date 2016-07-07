package org.paradyne.bean.admin.srd;
/*
 * author:Pradeep Kumar Sahoo
 */

import org.paradyne.lib.BeanBase;

public class InitialAppointment extends BeanBase {
	
	
	String employeeToken;
	String empRank;
	String empId;
	String empName;
	String initialDesg;
	String empDept;
	String empCent;
	String initialJnDate;
	String dtOfJnServ;
	String dtOfJnDockyard;
	String payScale;
	String dceList;
	String dtOfExm;
	String doctName;
	String hspName;
	String remarks;
	String superAnnDt;
	String desgCode;
	String payCode;
	String deptId;
	String frdCode;
	String frdName;
	String initTradeCode;
	String initTradeName;
	String initCentCode;
	String initCentName;
	String initRankCode;
	String initRankName; 
	String dceDate;
	String rtType;
	
	
	public InitialAppointment() {  }
	
	public InitialAppointment(String empId,String empName,String initialDesg,String empDept,
		String	initialJnDate,String dtOfJnServ,String dtOfJnDockyard,String payScale,String dceList,String dtOfExm,
		String doctName,String deptId,String hspName,String remarks,String superAnnDt,String empCent,String desgCode,String payCode,
		String initTradeCode,String initTradeName,String initCentCode,String initCentName,String initRankCode,String initRankName,String dceDate,String rtType,
		String empRank,String employeeToken) {
		this.empId=empId;
		this.empName=empName;
		this.initialDesg=initialDesg;
		this.empDept=empDept;
		this.initialJnDate=initialJnDate;
		this.dtOfJnServ=dtOfJnServ;
		this.dtOfJnDockyard=dtOfJnDockyard;
		this.payScale=payScale;
		this.dceList=dceList;
		this.dtOfExm=dtOfExm;
		this.doctName=doctName;
		this.hspName=hspName;
		this.remarks=remarks;
		this.superAnnDt=superAnnDt;
		this.empCent=empCent;
		this.deptId=deptId;
		this.desgCode=desgCode;
		this.payCode=payCode;
		this.initCentCode=initCentCode;
		this.initCentName=initCentName;
		this.initTradeCode=initTradeCode;
		this.initTradeName=initTradeName;
		this.initRankCode=initRankCode;
		this.initRankName=initRankName;
		this.dceDate=dceDate;
		this.rtType=rtType;
		this.empRank=empRank;
		this.employeeToken=employeeToken;
		
	
		}

	/**
	 * @return the dceList
	 */
	public String getDceList() {
		return dceList;
	}

	/**
	 * @param dceList the dceList to set
	 */
	public void setDceList(String dceList) {
		this.dceList = dceList;
	}

	/**
	 * @return the doctName
	 */
	public String getDoctName() {
		return doctName;
	}

	/**
	 * @param doctName the doctName to set
	 */
	public void setDoctName(String doctName) {
		this.doctName = doctName;
	}

	/**
	 * @return the dtOfExm
	 */
	public String getDtOfExm() {
		return dtOfExm;
	}

	/**
	 * @param dtOfExm the dtOfExm to set
	 */
	public void setDtOfExm(String dtOfExm) {
		this.dtOfExm = dtOfExm;
	}

	/**
	 * @return the dtOfJnDockyard
	 */
	public String getDtOfJnDockyard() {
		return dtOfJnDockyard;
	}

	/**
	 * @param dtOfJnDockyard the dtOfJnDockyard to set
	 */
	public void setDtOfJnDockyard(String dtOfJnDockyard) {
		this.dtOfJnDockyard = dtOfJnDockyard;
	}

	/**
	 * @return the dtOfJnServ
	 */
	public String getDtOfJnServ() {
		return dtOfJnServ;
	}

	/**
	 * @param dtOfJnServ the dtOfJnServ to set
	 */
	public void setDtOfJnServ(String dtOfJnServ) {
		this.dtOfJnServ = dtOfJnServ;
	}

	/**
	 * @return the empDept
	 */
	public String getEmpDept() {
		return empDept;
	}

	/**
	 * @param empDept the empDept to set
	 */
	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}

	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}

	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
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
	 * @return the hspName
	 */
	public String getHspName() {
		return hspName;
	}

	/**
	 * @param hspName the hspName to set
	 */
	public void setHspName(String hspName) {
		this.hspName = hspName;
	}

	/**
	 * @return the initialDesg
	 */
	public String getInitialDesg() {
		return initialDesg;
	}

	/**
	 * @param initialDesg the initialDesg to set
	 */
	public void setInitialDesg(String initialDesg) {
		this.initialDesg = initialDesg;
	}

	/**
	 * @return the initialJnDate
	 */
	public String getInitialJnDate() {
		return initialJnDate;
	}

	/**
	 * @param initialJnDate the initialJnDate to set
	 */
	public void setInitialJnDate(String initialJnDate) {
		this.initialJnDate = initialJnDate;
	}

	/**
	 * @return the payScale
	 */
	public String getPayScale() {
		return payScale;
	}

	/**
	 * @param payScale the payScale to set
	 */
	public void setPayScale(String payScale) {
		this.payScale = payScale;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the superAnnDt
	 */
	public String getSuperAnnDt() {
		return superAnnDt;
	}

	/**
	 * @param superAnnDt the superAnnDt to set
	 */
	public void setSuperAnnDt(String superAnnDt) {
		this.superAnnDt = superAnnDt;
	}
	
	
	public String getEmpCent() {
		return empCent;
	}
	
	public void setEmpCent(String empCent){
		
		this.empCent=empCent;
	}

	/**
	 * @return the desgCode
	 */
	public String getDesgCode() {
		return desgCode;
	}

	/**
	 * @param desgCode the desgCode to set
	 */
	public void setDesgCode(String desgCode) {
		this.desgCode = desgCode;
	}

	/**
	 * @return the payCode
	 */
	public String getPayCode() {
		return payCode;
	}

	/**
	 * @param payCode the payCode to set
	 */
	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	/**
	 * @return the deptId
	 */
	public String getDeptId() {
		return deptId;
	}

	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDceDate() {
		return dceDate;
	}

	public void setDceDate(String dceDate) {
		this.dceDate = dceDate;
	}

	public String getInitCentCode() {
		return initCentCode;
	}

	public void setInitCentCode(String initCentCode) {
		this.initCentCode = initCentCode;
	}

	public String getInitCentName() {
		return initCentName;
	}

	public void setInitCentName(String initCentName) {
		this.initCentName = initCentName;
	}

	public String getInitRankCode() {
		return initRankCode;
	}

	public void setInitRankCode(String initRankCode) {
		this.initRankCode = initRankCode;
	}

	public String getInitRankName() {
		return initRankName;
	}

	public void setInitRankName(String initRankName) {
		this.initRankName = initRankName;
	}

	public String getInitTradeCode() {
		return initTradeCode;
	}

	public void setInitTradeCode(String initTradeCode) {
		this.initTradeCode = initTradeCode;
	}

	public String getInitTradeName() {
		return initTradeName;
	}

	public void setInitTradeName(String initTradeName) {
		this.initTradeName = initTradeName;
	}

	public String getRtType() {
		return rtType;
	}

	public void setRtType(String rtType) {
		this.rtType = rtType;
	}

	public String getEmployeeToken() {
		return employeeToken;
	}

	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}

	public String getEmpRank() {
		return empRank;
	}

	public void setEmpRank(String empRank) {
		this.empRank = empRank;
	}

	
	}
