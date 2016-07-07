/**
 * 
 */
package org.paradyne.bean.TravelManagement.ExpenseClaim;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0651
 *
 */
public class ExpenseClaimApp extends BeanBase {
	 private String apprflag="";
	 private String listLength="";
	 private String expListLength="";
	 private String status="";
	 private String noData ="";
	 private String newReq="";
	 private String pen="";
	 private String app="";
	 private String rej ="";
	 private String myPage="";
	 private String show="";
	 private String level;
	 private String isApprove="false";
	 private String commentFlag;
	 private String approverName ;
	 private String approvedDate ;
	 private String approveStatus ;
	 private String approverComment="";
	 private String approverComments="";
	 private ArrayList apprList;
	 private String expAmt="";
	 private String expAmount="";
	 private String maxLimit ="";
	 private String expLength ="";
	 private String hiddenEdit ="";
	 private String srNo ="";
	 private String itvoucherAmt ="";
	 private String hidAmt ="";
	 private String hidMode ="";
	 private String hidMonth ="";
	 private String itExpId="";
	 
	 
	 
	// control flags for iterator
	 	private boolean newReqFlag = false;
		private boolean penFlag = false;
		private boolean appFlag = false;
		private boolean rejFlag = false;
		
		
		//flags for diff jsp
		private boolean viewApprFlag = true;
		private boolean viewRejFlag = true;
	 
	 
	 //for iterator
	 ArrayList travelList=new ArrayList();
	 private String travelEmpId;
	 private String empName;
	 private String reqName;
	 private String trvDate;
	 private String travelAppId;
	 private String  empId;
	 private String travelViewNo;
	 private String  hiddencode;
	 private String hidSts;
	 

	 
	 
		// for paging 
	 private String hdPage="";
	 private String fromTotRec="";
	 private String toTotRec="";
	 
	 
	 /**
		 * Flags Required  
		 */
		
		private boolean onLoadFlag=false;
		private boolean saveFlag=false;
		private boolean flag=false;
		private boolean editFlag=false;
		private boolean pageFlag=false;
		private boolean dblFlag=false;
		private String cancelFlag="";
		private String approverFlag="";
		private String approvedFlag="";
		private boolean addNewFlag=false;
		
		
		
		/**
		 * Flags For Cancel Button
		 */
		
		private boolean loadFlag;
		private boolean addFlag;
		private boolean modFlag;
		private boolean dbFlag;
		
		//for dtl
		private String empToken;
		private String employeeName;
		private String branchName;
		private String deptName;
		private String desgName;
		private String applDate;
		private String statusFld;
		private String grdName;
		private String employeeId;
		private String upload;
		private String particulars;
		private String proof;
		private String amount;
		private String voucherHeadCode;
		private String voucherHead;
		private String advAmt;
		private String requestCode;
		private String requestName;
		private String grdId;
		private String policyId;
		private String voucherAmt;
		private String uploadFileName;
		private String salMonth;
		private String year;
		private String accNo;
		private String mode;
		private String expenseDate;
		//for dtl iterator
		private String itParticulars;
		private String itVoucher;
		private String itExpenseDate;
		private String itAmount;
		private String itValAmount;
		private String itproof;
		private String itUpload; 
		private ArrayList expList=null;
		private String hdelete;
		private String confChk;
		private String balAmt;
		private String comment;
		private String totAmt;
		private String isProof;
		private String trvAppIdDtl;
		private String itVoucherHeadCode;
		private String bankName;
		private String exAppId="";	
		private String exAppIdDtl;
		private String month="";
	
		//for delete
		private String expHdr;
	public String getExpHdr() {
			return expHdr;
		}
		public void setExpHdr(String expHdr) {
			this.expHdr = expHdr;
		}
	public String getBankName() {
			return bankName;
		}
		public void setBankName(String bankName) {
			this.bankName = bankName;
		}
	public String getItVoucherHeadCode() {
			return itVoucherHeadCode;
		}
		public void setItVoucherHeadCode(String itVoucherHeadCode) {
			this.itVoucherHeadCode = itVoucherHeadCode;
		}
	public String getTrvAppIdDtl() {
			return trvAppIdDtl;
		}
		public void setTrvAppIdDtl(String trvAppIdDtl) {
			this.trvAppIdDtl = trvAppIdDtl;
		}
	public String getIsProof() {
			return isProof;
		}
		public void setIsProof(String isProof) {
			this.isProof = isProof;
		}
	public String getTotAmt() {
			return totAmt;
		}
		public void setTotAmt(String totAmt) {
			this.totAmt = totAmt;
		}
	public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
	public String getBalAmt() {
			return balAmt;
		}
		public void setBalAmt(String balAmt) {
			this.balAmt = balAmt;
		}
	public String getHdelete() {
			return hdelete;
		}
		public void setHdelete(String hdelete) {
			this.hdelete = hdelete;
		}
		public String getConfChk() {
			return confChk;
		}
		public void setConfChk(String confChk) {
			this.confChk = confChk;
		}
	public String getExpenseDate() {
			return expenseDate;
		}
		public void setExpenseDate(String expenseDate) {
			this.expenseDate = expenseDate;
		}
	
		public String getSalMonth() {
			return salMonth;
		}
		public void setSalMonth(String salMonth) {
			this.salMonth = salMonth;
		}
		public String getYear() {
			return year;
		}
		public void setYear(String year) {
			this.year = year;
		}
		public String getAccNo() {
			return accNo;
		}
		public void setAccNo(String accNo) {
			this.accNo = accNo;
		}
		public String getMode() {
			return mode;
		}
		public void setMode(String mode) {
			this.mode = mode;
		}
	public String getUploadFileName() {
			return uploadFileName;
		}
		public void setUploadFileName(String uploadFileName) {
			this.uploadFileName = uploadFileName;
		}
	public String getVoucherAmt() {
			return voucherAmt;
		}
		public void setVoucherAmt(String voucherAmt) {
			this.voucherAmt = voucherAmt;
		}
	public String getPolicyId() {
			return policyId;
		}
		public void setPolicyId(String policyId) {
			this.policyId = policyId;
		}
	public String getGrdId() {
			return grdId;
		}
		public void setGrdId(String grdId) {
			this.grdId = grdId;
		}
	public String getUpload() {
			return upload;
		}
		public void setUpload(String upload) {
			this.upload = upload;
		}
		public String getParticulars() {
			return particulars;
		}
		public void setParticulars(String particulars) {
			this.particulars = particulars;
		}
		public String getProof() {
			return proof;
		}
		public void setProof(String proof) {
			this.proof = proof;
		}
		public String getAmount() {
			return amount;
		}
		public void setAmount(String amount) {
			this.amount = amount;
		}
		public String getVoucherHeadCode() {
			return voucherHeadCode;
		}
		public void setVoucherHeadCode(String voucherHeadCode) {
			this.voucherHeadCode = voucherHeadCode;
		}
		public String getVoucherHead() {
			return voucherHead;
		}
		public void setVoucherHead(String voucherHead) {
			this.voucherHead = voucherHead;
		}
		public String getAdvAmt() {
			return advAmt;
		}
		public void setAdvAmt(String advAmt) {
			this.advAmt = advAmt;
		}
		public String getRequestCode() {
			return requestCode;
		}
		public void setRequestCode(String requestCode) {
			this.requestCode = requestCode;
		}
		public String getRequestName() {
			return requestName;
		}
		public void setRequestName(String requestName) {
			this.requestName = requestName;
		}
	public String getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(String employeeId) {
			this.employeeId = employeeId;
		}
	public String getApprflag() {
		return apprflag;
	}
	public void setApprflag(String apprflag) {
		this.apprflag = apprflag;
	}
	public String getListLength() {
		return listLength;
	}
	public void setListLength(String listLength) {
		this.listLength = listLength;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNoData() {
		return noData;
	}
	public void setNoData(String noData) {
		this.noData = noData;
	}
	public String getPen() {
		return pen;
	}
	public void setPen(String pen) {
		this.pen = pen;
	}
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public String getRej() {
		return rej;
	}
	public void setRej(String rej) {
		this.rej = rej;
	}
	public String getHdPage() {
		return hdPage;
	}
	public void setHdPage(String hdPage) {
		this.hdPage = hdPage;
	}
	public String getFromTotRec() {
		return fromTotRec;
	}
	public void setFromTotRec(String fromTotRec) {
		this.fromTotRec = fromTotRec;
	}
	public String getToTotRec() {
		return toTotRec;
	}
	public void setToTotRec(String toTotRec) {
		this.toTotRec = toTotRec;
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
	public ArrayList getTravelList() {
		return travelList;
	}
	public void setTravelList(ArrayList travelList) {
		this.travelList = travelList;
	}
	public String getTravelEmpId() {
		return travelEmpId;
	}
	public void setTravelEmpId(String travelEmpId) {
		this.travelEmpId = travelEmpId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getReqName() {
		return reqName;
	}
	public void setReqName(String reqName) {
		this.reqName = reqName;
	}
	public String getTrvDate() {
		return trvDate;
	}
	public void setTrvDate(String trvDate) {
		this.trvDate = trvDate;
	}
	public String getTravelAppId() {
		return travelAppId;
	}
	public void setTravelAppId(String travelAppId) {
		this.travelAppId = travelAppId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
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
	public String getTravelViewNo() {
		return travelViewNo;
	}
	public void setTravelViewNo(String travelViewNo) {
		this.travelViewNo = travelViewNo;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDesgName() {
		return desgName;
	}
	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}
	public String getApplDate() {
		return applDate;
	}
	public void setApplDate(String applDate) {
		this.applDate = applDate;
	}
	public String getStatusFld() {
		return statusFld;
	}
	public void setStatusFld(String statusFld) {
		this.statusFld = statusFld;
	}
	public String getGrdName() {
		return grdName;
	}
	public void setGrdName(String grdName) {
		this.grdName = grdName;
	}
	public String getItParticulars() {
		return itParticulars;
	}
	public void setItParticulars(String itParticulars) {
		this.itParticulars = itParticulars;
	}
	public String getItVoucher() {
		return itVoucher;
	}
	public void setItVoucher(String itVoucher) {
		this.itVoucher = itVoucher;
	}
	public String getItExpenseDate() {
		return itExpenseDate;
	}
	public void setItExpenseDate(String itExpenseDate) {
		this.itExpenseDate = itExpenseDate;
	}
	public String getItAmount() {
		return itAmount;
	}
	public void setItAmount(String itAmount) {
		this.itAmount = itAmount;
	}
	public String getItValAmount() {
		return itValAmount;
	}
	public void setItValAmount(String itValAmount) {
		this.itValAmount = itValAmount;
	}
	public String getItproof() {
		return itproof;
	}
	public void setItproof(String itproof) {
		this.itproof = itproof;
	}
	public String getItUpload() {
		return itUpload;
	}
	public void setItUpload(String itUpload) {
		this.itUpload = itUpload;
	}
	public ArrayList getExpList() {
		return expList;
	}
	public void setExpList(ArrayList expList) {
		this.expList = expList;
	}
	public String getExAppId() {
		return exAppId;
	}
	public void setExAppId(String exAppId) {
		this.exAppId = exAppId;
	}
	public String getExAppIdDtl() {
		return exAppIdDtl;
	}
	public void setExAppIdDtl(String exAppIdDtl) {
		this.exAppIdDtl = exAppIdDtl;
	}
	public String getNewReq() {
		return newReq;
	}
	public void setNewReq(String newReq) {
		this.newReq = newReq;
	}
	public boolean isNewReqFlag() {
		return newReqFlag;
	}
	public void setNewReqFlag(boolean newReqFlag) {
		this.newReqFlag = newReqFlag;
	}
	public boolean isPenFlag() {
		return penFlag;
	}
	public void setPenFlag(boolean penFlag) {
		this.penFlag = penFlag;
	}
	public boolean isAppFlag() {
		return appFlag;
	}
	public void setAppFlag(boolean appFlag) {
		this.appFlag = appFlag;
	}
	public boolean isRejFlag() {
		return rejFlag;
	}
	public void setRejFlag(boolean rejFlag) {
		this.rejFlag = rejFlag;
	}
	public String getHiddencode() {
		return hiddencode;
	}
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	public String getCancelFlag() {
		return cancelFlag;
	}
	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	public boolean isViewApprFlag() {
		return viewApprFlag;
	}
	public void setViewApprFlag(boolean viewApprFlag) {
		this.viewApprFlag = viewApprFlag;
	}
	public boolean isViewRejFlag() {
		return viewRejFlag;
	}
	public void setViewRejFlag(boolean viewRejFlag) {
		this.viewRejFlag = viewRejFlag;
	}
	public boolean isAddNewFlag() {
		return addNewFlag;
	}
	public void setAddNewFlag(boolean addNewFlag) {
		this.addNewFlag = addNewFlag;
	}
	public String getExpListLength() {
		return expListLength;
	}
	public void setExpListLength(String expListLength) {
		this.expListLength = expListLength;
	}
	public String getHidSts() {
		return hidSts;
	}
	public void setHidSts(String hidSts) {
		this.hidSts = hidSts;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getApproverComments() {
		return approverComments;
	}
	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
	}
	
	public String getIsApprove() {
		return isApprove;
	}
	public void setIsApprove(String isApprove) {
		this.isApprove = isApprove;
	}
	public String getCommentFlag() {
		return commentFlag;
	}
	public void setCommentFlag(String commentFlag) {
		this.commentFlag = commentFlag;
	}
	public String getApproverName() {
		return approverName;
	}
	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}
	public String getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(String approvedDate) {
		this.approvedDate = approvedDate;
	}
	public String getApproveStatus() {
		return approveStatus;
	}
	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}
	public ArrayList getApprList() {
		return apprList;
	}
	public void setApprList(ArrayList apprList) {
		this.apprList = apprList;
	}
	public String getApproverComment() {
		return approverComment;
	}
	public void setApproverComment(String approverComment) {
		this.approverComment = approverComment;
	}
	public String getExpAmt() {
		return expAmt;
	}
	public void setExpAmt(String expAmt) {
		this.expAmt = expAmt;
	}
	public String getMaxLimit() {
		return maxLimit;
	}
	public void setMaxLimit(String maxLimit) {
		this.maxLimit = maxLimit;
	}
	public String getExpAmount() {
		return expAmount;
	}
	public void setExpAmount(String expAmount) {
		this.expAmount = expAmount;
	}
	public String getExpLength() {
		return expLength;
	}
	public void setExpLength(String expLength) {
		this.expLength = expLength;
	}
	public String getHiddenEdit() {
		return hiddenEdit;
	}
	public void setHiddenEdit(String hiddenEdit) {
		this.hiddenEdit = hiddenEdit;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getItvoucherAmt() {
		return itvoucherAmt;
	}
	public void setItvoucherAmt(String itvoucherAmt) {
		this.itvoucherAmt = itvoucherAmt;
	}
	public String getHidAmt() {
		return hidAmt;
	}
	public void setHidAmt(String hidAmt) {
		this.hidAmt = hidAmt;
	}
	public String getHidMode() {
		return hidMode;
	}
	public void setHidMode(String hidMode) {
		this.hidMode = hidMode;
	}
	public String getHidMonth() {
		return hidMonth;
	}
	public void setHidMonth(String hidMonth) {
		this.hidMonth = hidMonth;
	}
	public String getApproverFlag() {
		return approverFlag;
	}
	public void setApproverFlag(String approverFlag) {
		this.approverFlag = approverFlag;
	}
	public String getApprovedFlag() {
		return approvedFlag;
	}
	public void setApprovedFlag(String approvedFlag) {
		this.approvedFlag = approvedFlag;
	}
	public String getItExpId() {
		return itExpId;
	}
	public void setItExpId(String itExpId) {
		this.itExpId = itExpId;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
 
	
	
}
