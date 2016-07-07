/**
 * 
 */
package org.paradyne.bean.TravelManagement.Master;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author aa0651
 *
 */
public class TravelAuthorities extends BeanBase {
	private String selectAllAcc="";
	private String accEmpName="";
	private String accEmpId="";
	private String hidAltAccName="";
	private String altAccName="";
	private String hidAccName="";
	private String accName="";
	private String selectAllAppr="";
	private String appEmpName="";
	private String appEmpId="";
	private String hidAltApprover="";
	private String altApprover="";
	private String hidApprover="";
	private String approverName="";
	private String selectAllSchr="";
	private String schEmpName="";
	private String schEmpId="";
	private String hidAltSchCode="";
	private String altSchName="";
	private String schName="";
	private String hidSchCode="";
	private String branchId="";	
	private String description="";
	private String status="";
	private String branchName;
	private String hidappFlag="";
	//private String appFlag;
	private String branchFlag="";
	private String travelAuth;
	private ArrayList typeList=null;
	private String confChk;
	private String hdeleteCode;
	private String myPage;
	private String show;
	private String selectname;
	private String hiddencode;
	private String hdeleteOp;
	private String confChkop;
	
	private String hidAltSchToken;
	private String hidSchToken;
	private String hidAltAccToken;
	private String hidAccToken;
	private String hidAltAppToken;
	private String hidAppToken;
	
	
	
	
	
	private ArrayList aprlist=null;
	private String hdeleteApr;
	private String confChkApr;
	private String itAprName;
	private String aprToken;
	private String itAprId=null;;
	private String altAccCode;
	private String hidDtlCode;
	
	private String altAppCode;
	
	private String itEmpId=null;;
	private String itEmpName;
	private String altSchCode;
	private String empToken;
	private ArrayList schlist;
	
	
	
	private ArrayList acclist=null;
	private String itAccId=null;
	private String  itAccName=null;
	private String  srNo3;
	private String  srNo2;
	private String  srNo1;
	private String  accToken;	
	private String  hdeleteAcc;
	private String  confChkAcc;
	private String  tableLength3;
	private String msg;
	//private String msgFlag;
	
	
	/**
	 * Flags Required  
	 */
	
	private boolean onLoadFlag=false;
	private boolean saveFlag=false;
	private boolean flag=false;
	private boolean editFlag=false;
	private boolean pageFlag=false;
	private boolean dblFlag=false;
	private boolean imgflag=false;
	private boolean msgFlag=false;
	//private boolean branchFlag=false;
	private String appFlag="";
	/**
	 * Flags For Cancel Button
	 */
	
	private boolean loadFlag;
	private boolean addFlag;
	private boolean modFlag;
	private boolean dbFlag;
	
	
	
	
	
	private String  hidBranchFlag="N";
	
	public String getSelectAllAcc() {
		return selectAllAcc;
	}
	public void setSelectAllAcc(String selectAllAcc) {
		this.selectAllAcc = selectAllAcc;
	}
	public String getAccEmpName() {
		return accEmpName;
	}
	public void setAccEmpName(String accEmpName) {
		this.accEmpName = accEmpName;
	}
	public String getAccEmpId() {
		return accEmpId;
	}
	public void setAccEmpId(String accEmpId) {
		this.accEmpId = accEmpId;
	}
	public String getHidAltAccName() {
		return hidAltAccName;
	}
	public void setHidAltAccName(String hidAltAccName) {
		this.hidAltAccName = hidAltAccName;
	}
	public String getAltAccName() {
		return altAccName;
	}
	public void setAltAccName(String altAccName) {
		this.altAccName = altAccName;
	}
	public String getHidAccName() {
		return hidAccName;
	}
	public void setHidAccName(String hidAccName) {
		this.hidAccName = hidAccName;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public String getSelectAllAppr() {
		return selectAllAppr;
	}
	public void setSelectAllAppr(String selectAllAppr) {
		this.selectAllAppr = selectAllAppr;
	}
	public String getAppEmpName() {
		return appEmpName;
	}
	public void setAppEmpName(String appEmpName) {
		this.appEmpName = appEmpName;
	}
	public String getAppEmpId() {
		return appEmpId;
	}
	public void setAppEmpId(String appEmpId) {
		this.appEmpId = appEmpId;
	}
	public String getHidAltApprover() {
		return hidAltApprover;
	}
	public void setHidAltApprover(String hidAltApprover) {
		this.hidAltApprover = hidAltApprover;
	}
	public String getAltApprover() {
		return altApprover;
	}
	public void setAltApprover(String altApprover) {
		this.altApprover = altApprover;
	}
	public String getHidApprover() {
		return hidApprover;
	}
	public void setHidApprover(String hidApprover) {
		this.hidApprover = hidApprover;
	}
	public String getApproverName() {
		return approverName;
	}
	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}
	public String getSelectAllSchr() {
		return selectAllSchr;
	}
	public void setSelectAllSchr(String selectAllSchr) {
		this.selectAllSchr = selectAllSchr;
	}
	public String getSchEmpName() {
		return schEmpName;
	}
	public void setSchEmpName(String schEmpName) {
		this.schEmpName = schEmpName;
	}
	public String getSchEmpId() {
		return schEmpId;
	}
	public void setSchEmpId(String schEmpId) {
		this.schEmpId = schEmpId;
	}
	public String getHidAltSchCode() {
		return hidAltSchCode;
	}
	public void setHidAltSchCode(String hidAltSchCode) {
		this.hidAltSchCode = hidAltSchCode;
	}
	public String getAltSchName() {
		return altSchName;
	}
	public void setAltSchName(String altSchName) {
		this.altSchName = altSchName;
	}
	public String getSchName() {
		return schName;
	}
	public void setSchName(String schName) {
		this.schName = schName;
	}
	public String getHidSchCode() {
		return hidSchCode;
	}
	public void setHidSchCode(String hidSchCode) {
		this.hidSchCode = hidSchCode;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getHidappFlag() {
		return hidappFlag;
	}
	public void setHidappFlag(String hidappFlag) {
		this.hidappFlag = hidappFlag;
	}
	/*public String getAppFlag() {
		return appFlag;
	}
	public void setAppFlag(String appFlag) {
		this.appFlag = appFlag;
	}*/
	public ArrayList getTypeList() {
		return typeList;
	}
	public void setTypeList(ArrayList typeList) {
		this.typeList = typeList;
	}
	public String getConfChk() {
		return confChk;
	}
	public void setConfChk(String confChk) {
		this.confChk = confChk;
	}
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public String getSelectname() {
		return selectname;
	}
	public void setSelectname(String selectname) {
		this.selectname = selectname;
	}
	public String getHiddencode() {
		return hiddencode;
	}
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	public boolean isOnLoadFlag() {
		return onLoadFlag;
	}
	public void setOnLoadFlag(boolean onLoadFlag) {
		this.onLoadFlag = onLoadFlag;
	}
	public boolean isSaveFlag() {
		return saveFlag;
	}
	public void setSaveFlag(boolean saveFlag) {
		this.saveFlag = saveFlag;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public boolean isEditFlag() {
		return editFlag;
	}
	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}
	public boolean isPageFlag() {
		return pageFlag;
	}
	public void setPageFlag(boolean pageFlag) {
		this.pageFlag = pageFlag;
	}
	public boolean isDblFlag() {
		return dblFlag;
	}
	public void setDblFlag(boolean dblFlag) {
		this.dblFlag = dblFlag;
	}
	public boolean isLoadFlag() {
		return loadFlag;
	}
	public void setLoadFlag(boolean loadFlag) {
		this.loadFlag = loadFlag;
	}
	public boolean isAddFlag() {
		return addFlag;
	}
	public void setAddFlag(boolean addFlag) {
		this.addFlag = addFlag;
	}
	public boolean isModFlag() {
		return modFlag;
	}
	public void setModFlag(boolean modFlag) {
		this.modFlag = modFlag;
	}
	public boolean isDbFlag() {
		return dbFlag;
	}
	public void setDbFlag(boolean dbFlag) {
		this.dbFlag = dbFlag;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getTravelAuth() {
		return travelAuth;
	}
	public void setTravelAuth(String travelAuth) {
		this.travelAuth = travelAuth;
	}
	public boolean isImgflag() {
		return imgflag;
	}
	public void setImgflag(boolean imgflag) {
		this.imgflag = imgflag;
	}
	/*public boolean isBranchFlag() {
		return branchFlag;
	}
	public void setBranchFlag(boolean branchFlag) {
		this.branchFlag = branchFlag;
	}*/
	public String getItEmpId() {
		return itEmpId;
	}
	public void setItEmpId(String itEmpId) {
		this.itEmpId = itEmpId;
	}
	public String getItEmpName() {
		return itEmpName;
	}
	public void setItEmpName(String itEmpName) {
		this.itEmpName = itEmpName;
	}
	public String getAltSchCode() {
		return altSchCode;
	}
	public void setAltSchCode(String altSchCode) {
		this.altSchCode = altSchCode;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public ArrayList getSchlist() {
		return schlist;
	}
	public void setSchlist(ArrayList schlist) {
		this.schlist = schlist;
	}
	public String getHdeleteOp() {
		return hdeleteOp;
	}
	public void setHdeleteOp(String hdeleteOp) {
		this.hdeleteOp = hdeleteOp;
	}
	public String getConfChkop() {
		return confChkop;
	}
	public void setConfChkop(String confChkop) {
		this.confChkop = confChkop;
	}
	public ArrayList getAprlist() {
		return aprlist;
	}
	public void setAprlist(ArrayList aprlist) {
		this.aprlist = aprlist;
	}
	public String getHdeleteApr() {
		return hdeleteApr;
	}
	public void setHdeleteApr(String hdeleteApr) {
		this.hdeleteApr = hdeleteApr;
	}
	public String getConfChkApr() {
		return confChkApr;
	}
	public void setConfChkApr(String confChkApr) {
		this.confChkApr = confChkApr;
	}
	public String getItAprName() {
		return itAprName;
	}
	public void setItAprName(String itAprName) {
		this.itAprName = itAprName;
	}
	public String getAprToken() {
		return aprToken;
	}
	public void setAprToken(String aprToken) {
		this.aprToken = aprToken;
	}
	public String getItAprId() {
		return itAprId;
	}
	public void setItAprId(String itAprId) {
		this.itAprId = itAprId;
	}
	public String getAltAppCode() {
		return altAppCode;
	}
	public void setAltAppCode(String altAppCode) {
		this.altAppCode = altAppCode;
	}
	public String getAltAccCode() {
		return altAccCode;
	}
	public void setAltAccCode(String altAccCode) {
		this.altAccCode = altAccCode;
	}
	public ArrayList getAcclist() {
		return acclist;
	}
	public void setAcclist(ArrayList acclist) {
		this.acclist = acclist;
	}
	public String getItAccId() {
		return itAccId;
	}
	public void setItAccId(String itAccId) {
		this.itAccId = itAccId;
	}
	public String getItAccName() {
		return itAccName;
	}
	public void setItAccName(String itAccName) {
		this.itAccName = itAccName;
	}
	public String getSrNo3() {
		return srNo3;
	}
	public void setSrNo3(String srNo3) {
		this.srNo3 = srNo3;
	}
	public String getSrNo2() {
		return srNo2;
	}
	public void setSrNo2(String srNo2) {
		this.srNo2 = srNo2;
	}
	public String getSrNo1() {
		return srNo1;
	}
	public void setSrNo1(String srNo1) {
		this.srNo1 = srNo1;
	}
	public String getAccToken() {
		return accToken;
	}
	public void setAccToken(String accToken) {
		this.accToken = accToken;
	}
	public String getHdeleteAcc() {
		return hdeleteAcc;
	}
	public void setHdeleteAcc(String hdeleteAcc) {
		this.hdeleteAcc = hdeleteAcc;
	}
	public String getConfChkAcc() {
		return confChkAcc;
	}
	public void setConfChkAcc(String confChkAcc) {
		this.confChkAcc = confChkAcc;
	}
	public String getTableLength3() {
		return tableLength3;
	}
	public String getAppFlag() {
		return appFlag;
	}
	public void setAppFlag(String appFlag) {
		this.appFlag = appFlag;
	}
	public void setTableLength3(String tableLength3) {
		this.tableLength3 = tableLength3;
	}
 
	public String getBranchFlag() {
		return branchFlag;
	}
	public void setBranchFlag(String branchFlag) {
		this.branchFlag = branchFlag;
	}
	public String getHidBranchFlag() {
		return hidBranchFlag;
	}
	public void setHidBranchFlag(String hidBranchFlag) {
		this.hidBranchFlag = hidBranchFlag;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean isMsgFlag() {
		return msgFlag;
	}
	public void setMsgFlag(boolean msgFlag) {
		this.msgFlag = msgFlag;
	}
	public String getHidDtlCode() {
		return hidDtlCode;
	}
	public void setHidDtlCode(String hidDtlCode) {
		this.hidDtlCode = hidDtlCode;
	}
	public String getHidAltSchToken() {
		return hidAltSchToken;
	}
	public void setHidAltSchToken(String hidAltSchToken) {
		this.hidAltSchToken = hidAltSchToken;
	}
	public String getHidSchToken() {
		return hidSchToken;
	}
	public void setHidSchToken(String hidSchToken) {
		this.hidSchToken = hidSchToken;
	}
	public String getHidAltAccToken() {
		return hidAltAccToken;
	}
	public void setHidAltAccToken(String hidAltAccToken) {
		this.hidAltAccToken = hidAltAccToken;
	}
	public String getHidAccToken() {
		return hidAccToken;
	}
	public void setHidAccToken(String hidAccToken) {
		this.hidAccToken = hidAccToken;
	}
	public String getHidAltAppToken() {
		return hidAltAppToken;
	}
	public void setHidAltAppToken(String hidAltAppToken) {
		this.hidAltAppToken = hidAltAppToken;
	}
	public String getHidAppToken() {
		return hidAppToken;
	}
	public void setHidAppToken(String hidAppToken) {
		this.hidAppToken = hidAppToken;
	}
	
	
	

}
