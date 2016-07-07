package org.paradyne.bean.admin.transfer;
import org.paradyne.lib.BeanBase;
import java.util.*;

/**
 * 
 * @author Pradeep Kumar
 *Date:20-06-2008
 */



public class TransferApplication  extends BeanBase {
	
	private String source ="";
	String empToken="";
	String trfId="";
	String empId="";
	String empName="";
	String empDept="";
	String trdNo="";
	String empDesg="";
	String type="";
	String curDept="";
	String newDept="";
	String curCent="";
	String newCent="";
	String curDock="";
	String newDock="";
	String reason="";
	String forward="";
	String frdCode="";
	String deptId="";
	String dockId="";
	String desgId="";
	String curDeptId="";
	String propId="";
	String relReq="";
	String relId="";
	String appDt="";
	String relDt="";
	String propName="";
	String relName="";
	String isForApprove	="false" ;
	String date="";
	String remark="";
	String status="";
	ArrayList trnAppList=null;
	String aprName="";
	String aprId="";
	String curCentId="";
	String newCentId="";
	String apprStatus="";
	String isRecommend="false";
	String result="";
	String apprDate="";
	ArrayList trnRepList=null;
	String appName="";
	String transUnit="";
	String rank="";
	String flag="true";
	String propToken="";	
	String relToken="";
	String showFlag="";
	String leaveType;
	String closingBal;
	String OpeningBal;
	String dicsplinaryAction;
	String effectiveDate;
	String period;
	ArrayList discList=null;
	ArrayList leaveList=null;
	String leaveFlag="false";
	String discFlag="false";
	String discLength="";
	String res="";
	String leaveRes="";
	String centFlag="true";
	String relFlag="true";
	String level="";
	String newDeptId="";
	String curDivId="";
	String curDiv="";
	String newDivId="";
	String newDiv="";
	String genEmpFlag="true";
	String genLoginFlag="true";
	String divFlag="true";
	String deptFlag="true";
	String dateFlag="true";
	String emp="";
	String relReqFlag="false";
	private String trfStatus="";
	
	


	public String getTrfStatus() {
		return trfStatus;
	}

	public void setTrfStatus(String trfStatus) {
		this.trfStatus = trfStatus;
	}

	public String getEmp() {
		return emp;
	}

	public void setEmp(String emp) {
		this.emp = emp;
	}

	public String getCurDivId() {
		return curDivId;
	}

	public void setCurDivId(String curDivId) {
		this.curDivId = curDivId;
	}

	public String getNewDivId() {
		return newDivId;
	}

	public void setNewDivId(String newDivId) {
		this.newDivId = newDivId;
	}

	public String getNewDiv() {
		return newDiv;
	}

	public void setNewDiv(String newDiv) {
		this.newDiv = newDiv;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(String isRecommend) {
		this.isRecommend = isRecommend;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public TransferApplication() {
		
		//No argument constructor
	}

	public TransferApplication(String result,String sttus,String date,String remark,String status,String relName,String propName,String propId,String relReq,String relId,String appDt,String relDt,String trfId, String empId, String empName, String empDept, String trdNo, String empDesg, String type, String curDept, String newDept, String curCent, String newCent, String curDock, String newDock, String reason, String forward,String frdCode,String deptId,String dockId,String desgId,String curDeptId) {
		super();
		
		
		this.apprStatus=sttus;
		this.date=date;
		this.status=status;
		this.remark=remark;
		this.relName=relName;
		this.propName=propName;
		this.propId=propId;
		this.relReq=relReq;
		this.relId=relId;
		this.appDt=appDt;
		this.relDt=relDt;
		this.trfId = trfId;
		this.empId = empId;
		this.empName = empName;
		this.empDept = empDept;
		this.trdNo = trdNo;
		this.empDesg = empDesg;
		this.type = type;
		this.curDept = curDept;
		this.newDept = newDept;
		this.curCent = curCent;
		this.newCent = newCent;
		this.curDock = curDock;
		this.newDock = newDock;
		this.reason = reason;
		this.forward = forward;
		this.frdCode=frdCode;
		this.deptId=deptId;
		this.dockId=dockId;
		this.desgId=desgId;
		this.curDeptId=curDeptId;
		this.result=result;
		
		
	}

	/**
	 * @return the curCent
	 */
	public String getCurCent() {
		return curCent;
	}

	/**
	 * @param curCent the curCent to set
	 */
	public void setCurCent(String curCent) {
		this.curCent = curCent;
	}

	/**
	 * @return the curDept
	 */
	public String getCurDept() {
		return curDept;
	}

	/**
	 * @param curDept the curDept to set
	 */
	public void setCurDept(String curDept) {
		this.curDept = curDept;
	}

	/**
	 * @return the curDock
	 */
	public String getCurDock() {
		return curDock;
	}

	/**
	 * @param curDock the curDock to set
	 */
	public void setCurDock(String curDock) {
		this.curDock = curDock;
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
	 * @return the empDesg
	 */
	public String getEmpDesg() {
		return empDesg;
	}

	/**
	 * @param empDesg the empDesg to set
	 */
	public void setEmpDesg(String empDesg) {
		this.empDesg = empDesg;
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
	 * @return the forward
	 */
	public String getForward() {
		return forward;
	}

	/**
	 * @param forward the forward to set
	 */
	public void setForward(String forward) {
		this.forward = forward;
	}

	/**
	 * @return the newCent
	 */
	public String getNewCent() {
		return newCent;
	}

	/**
	 * @param newCent the newCent to set
	 */
	public void setNewCent(String newCent) {
		this.newCent = newCent;
	}

	/**
	 * @return the newDept
	 */
	public String getNewDept() {
		return newDept;
	}

	/**
	 * @param newDept the newDept to set
	 */
	public void setNewDept(String newDept) {
		this.newDept = newDept;
	}

	/**
	 * @return the newDock
	 */
	public String getNewDock() {
		return newDock;
	}

	/**
	 * @param newDock the newDock to set
	 */
	public void setNewDock(String newDock) {
		this.newDock = newDock;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the trdNo
	 */
	public String getTrdNo() {
		return trdNo;
	}

	/**
	 * @param trdNo the trdNo to set
	 */
	public void setTrdNo(String trdNo) {
		this.trdNo = trdNo;
	}

	/**
	 * @return the trfId
	 */
	public String getTrfId() {
		return trfId;
	}

	/**
	 * @param trfId the trfId to set
	 */
	public void setTrfId(String trfId) {
		this.trfId = trfId;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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

	/**
	 * @return the frdCode
	 */
	public String getFrdCode() {
		return frdCode;
	}

	/**
	 * @param frdCode the frdCode to set
	 */
	public void setFrdCode(String frdCode) {
		this.frdCode = frdCode;
	}

	/**
	 * @return the dockId
	 */
	public String getDockId() {
		return dockId;
	}

	/**
	 * @param dockId the dockId to set
	 */
	public void setDockId(String dockId) {
		this.dockId = dockId;
	}

	/**
	 * @return the desgId
	 */
	public String getDesgId() {
		return desgId;
	}

	/**
	 * @param desgId the desgId to set
	 */
	public void setDesgId(String desgId) {
		this.desgId = desgId;
	}

	/**
	 * @return the curDeptId
	 */
	public String getCurDeptId() {
		return curDeptId;
	}

	/**
	 * @param curDeptId the curDeptId to set
	 */
	public void setCurDeptId(String curDeptId) {
		this.curDeptId = curDeptId;
	}

	/**
	 * @return the appDt
	 */
	public String getAppDt() {
		return appDt;
	}

	/**
	 * @param appDt the appDt to set
	 */
	public void setAppDt(String appDt) {
		this.appDt = appDt;
	}

	/**
	 * @return the propId
	 */
	public String getPropId() {
		return propId;
	}

	/**
	 * @param propId the propId to set
	 */
	public void setPropId(String propId) {
		this.propId = propId;
	}

	/**
	 * @return the relDt
	 */
	public String getRelDt() {
		return relDt;
	}

	/**
	 * @param relDt the relDt to set
	 */
	public void setRelDt(String relDt) {
		this.relDt = relDt;
	}

	/**
	 * @return the relId
	 */
	public String getRelId() {
		return relId;
	}

	/**
	 * @param relId the relId to set
	 */
	public void setRelId(String relId) {
		this.relId = relId;
	}

	/**
	 * @return the relReq
	 */
	public String getRelReq() {
		return relReq;
	}

	/**
	 * @param relReq the relReq to set
	 */
	public void setRelReq(String relReq) {
		this.relReq = relReq;
	}

	/**
	 * @return the propName
	 */
	public String getPropName() {
		return propName;
	}

	/**
	 * @param propName the propName to set
	 */
	public void setPropName(String propName) {
		this.propName = propName;
	}

	/**
	 * @return the relName
	 */
	public String getRelName() {
		return relName;
	}

	/**
	 * @param relName the relName to set
	 */
	public void setRelName(String relName) {
		this.relName = relName;
	}
	
	/**
	 * @return the isForApprove
	 */
	public String getIsForApprove() {
		return isForApprove;
	}

	/**
	 * @param isForApprove the isForApprove to set
	 */
	public void setIsForApprove(String isForApprove) {
		this.isForApprove = isForApprove;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the trnAppList
	 */
	public ArrayList getTrnAppList() {
		return trnAppList;
	}

	/**
	 * @param trnAppList the trnAppList to set
	 */
	public void setTrnAppList(ArrayList trnAppList) {
		this.trnAppList = trnAppList;
	}

	public String getAprName() {
		return aprName;
	}

	public void setAprName(String aprName) {
		this.aprName = aprName;
	}

	public String getAprId() {
		return aprId;
	}

	public void setAprId(String aprId) {
		this.aprId = aprId;
	}

	public String getCurCentId() {
		return curCentId;
	}

	public void setCurCentId(String curCentId) {
		this.curCentId = curCentId;
	}

	public String getNewCentId() {
		return newCentId;
	}

	public void setNewCentId(String newCentId) {
		this.newCentId = newCentId;
	}

	public String getApprStatus() {
		return apprStatus;
	}

	public void setApprStatus(String apprStatus) {
		this.apprStatus = apprStatus;
	}

	public ArrayList getTrnRepList() {
		return trnRepList;
	}

	public void setTrnRepList(ArrayList trnRepList) {
		this.trnRepList = trnRepList;
	}

	public String getApprDate() {
		return apprDate;
	}

	public void setApprDate(String apprDate) {
		this.apprDate = apprDate;
	}

	public String getEmpToken() {
		return empToken;
	}

	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getTransUnit() {
		return transUnit;
	}

	public void setTransUnit(String transUnit) {
		this.transUnit = transUnit;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getPropToken() {
		return propToken;
	}

	public void setPropToken(String propToken) {
		this.propToken = propToken;
	}

	public String getRelToken() {
		return relToken;
	}

	public void setRelToken(String relToken) {
		this.relToken = relToken;
	}

	public String getShowFlag() {
		return showFlag;
	}

	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}

	public String getClosingBal() {
		return closingBal;
	}

	public void setClosingBal(String closingBal) {
		this.closingBal = closingBal;
	}

	public String getDicsplinaryAction() {
		return dicsplinaryAction;
	}

	public void setDicsplinaryAction(String dicsplinaryAction) {
		this.dicsplinaryAction = dicsplinaryAction;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getOpeningBal() {
		return OpeningBal;
	}

	public void setOpeningBal(String openingBal) {
		OpeningBal = openingBal;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public ArrayList getDiscList() {
		return discList;
	}

	public void setDiscList(ArrayList discList) {
		this.discList = discList;
	}

	public ArrayList getLeaveList() {
		return leaveList;
	}

	public void setLeaveList(ArrayList leaveList) {
		this.leaveList = leaveList;
	}

	public String getLeaveFlag() {
		return leaveFlag;
	}

	public void setLeaveFlag(String leaveFlag) {
		this.leaveFlag = leaveFlag;
	}

	public String getDiscFlag() {
		return discFlag;
	}

	public void setDiscFlag(String discFlag) {
		this.discFlag = discFlag;
	}

	public String getDiscLength() {
		return discLength;
	}

	public void setDiscLength(String discLength) {
		this.discLength = discLength;
	}

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	public String getLeaveRes() {
		return leaveRes;
	}

	public void setLeaveRes(String leaveRes) {
		this.leaveRes = leaveRes;
	}

	public String getCentFlag() {
		return centFlag;
	}

	public void setCentFlag(String centFlag) {
		this.centFlag = centFlag;
	}

	public String getRelFlag() {
		return relFlag;
	}

	public void setRelFlag(String relFlag) {
		this.relFlag = relFlag;
	}

	public String getNewDeptId() {
		return newDeptId;
	}

	public void setNewDeptId(String newDeptId) {
		this.newDeptId = newDeptId;
	}

	public String getCurDiv() {
		return curDiv;
	}

	public void setCurDiv(String curDiv) {
		this.curDiv = curDiv;
	}

	public String getGenEmpFlag() {
		return genEmpFlag;
	}

	public void setGenEmpFlag(String genEmpFlag) {
		this.genEmpFlag = genEmpFlag;
	}

	public String getGenLoginFlag() {
		return genLoginFlag;
	}

	public void setGenLoginFlag(String genLoginFlag) {
		this.genLoginFlag = genLoginFlag;
	}

	public String getDivFlag() {
		return divFlag;
	}

	public void setDivFlag(String divFlag) {
		this.divFlag = divFlag;
	}

	public String getDeptFlag() {
		return deptFlag;
	}

	public void setDeptFlag(String deptFlag) {
		this.deptFlag = deptFlag;
	}

	public String getDateFlag() {
		return dateFlag;
	}

	public void setDateFlag(String dateFlag) {
		this.dateFlag = dateFlag;
	}

	public String getRelReqFlag() {
		return relReqFlag;
	}

	public void setRelReqFlag(String relReqFlag) {
		this.relReqFlag = relReqFlag;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	
	

	

	
		
	

}
