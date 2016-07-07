package org.paradyne.bean.payroll;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class PFChallan extends BeanBase {
	
	private String reportType = "";
	private String month = "";
	private String year = "";
	private String dateOfPay = "";
	private String chequeNo = "";
	private String cheqDate = "";
	private String divId = "";
	private String divName = "";
	private String modePayment = "";
	private String challanCode = "";
	private String bankName = "";
	private String bankMicrId = "";
	private String bankBranchName = "";
	private String pfAmount = "";
	private String type="";
	private String estabNo="";
	private String accGrpNo="";
	private String checkVpf="";
	private String vpfCode="";
	private String vpfName="";
	
	private String divAdd="";
	private String divCity="";
	private String hcheckVpf="";
	private String myPage ;
	private String hiddencode;
	private String show;
	private String hdeleteCode;
	
	private String totalRecords="";
	private String listLength="false";	
	ArrayList recordList;
	private String noData;
	private String pfOfficeName="";
	
	
	//Adding deducted & deposited variables
	
	private String employersPfContDeductedAc1 = "";
	private String employersPensionDeductedAc10 = "";
	private String employersEdliDeductedAc21 = "";
	private String employersTotalDeducted = "";

	private String empPfContDeductedAc1 = "";
	private String empTotalDeducted = "";

	private String adminPfAdminDeductedAc2 = "";
	private String adminEdliAdminDeductedAc22 = "";
	private String adminTotalDeducted = "";

	private String inspectionPfAdminDeductedAc2 = "";
	private String inspectionEdliAdminDeductedAc22 = "";
	private String inspectionTotalDeducted = "";

	private String totalPfContDeductedAc1 = "";
	private String totalPfAdminDeductedAc2 = "";
	private String totalPensionDeductedAc10 = "";
	private String totalEdliDeductedAc21 = "";
	private String totalEdliAdminDeductedAc22 = "";
	private String totalDeducted = "";

	private String subscribersPfContDeductedAc1 = "";
	private String subscribersPensionDeductedAc10 = "";
	private String subscribersEdliDeductedAc21 = "";

	private String wagesPfContDeductedAc1 = "";
	private String wagesPensionDeductedAc10 = "";
	private String wagesEdliDeductedAc21 = "";
	
	// Deposited variables
	private String employersPfContDepositedAc1 = "";
	private String employersPensionDepositedAc10 = "";
	private String employersEdliDepositedAc21 = "";
	private String employersTotalDeposited = "";
	
	private String empPfContDepositedAc1 = "";
	private String empTotalDeposited = "";
	private String adminPfAdminDepositedAc2 = "";
	private String adminEdliDepositedAc22 = "";
	private String adminTotalDeposited = "";


	private String inspectionPfAdminDepositedAc2 = "";
	private String inspectionEdliDepositedAc22 = "";
	private String inspectionTotalDeposited = "";

	private String penalDamagesPfContDepositedAc1 = "";
	private String penalDamagesPfAdminDepositedAc2 = "";
	private String penalDamagesPensionDepositedAc10 = "";
	private String penalDamagesEdliDepositedAc21 = "";
	private String penalDamagesEdliAdminDepositedAc22 = "";
	private String penalDamagesTotalDeposited = "";

	private String miscPfAdminDepositedAc2 = "";
	private String miscEdliAdminDepositedAc22 = "";
	private String miscTotalDeposited = "";

	private String totalPfContDepositedAc1 = "";
	private String totalPfAdminDepositedAc2 = "";
	private String totalPensionDepositedAc10 = "";
	private String totalEdliDepositedAc21 = "";
	private String totalEdliAdminDepositedAc22 = "";
	private String totalDeposited = "";

	public String getNoData() {
		return noData;
	}

	public void setNoData(String noData) {
		this.noData = noData;
	}

	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getListLength() {
		return listLength;
	}

	public void setListLength(String listLength) {
		this.listLength = listLength;
	}

	public ArrayList getRecordList() {
		return recordList;
	}

	public void setRecordList(ArrayList recordList) {
		this.recordList = recordList;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public String getHiddencode() {
		return hiddencode;
	}

	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public String getHdeleteCode() {
		return hdeleteCode;
	}

	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}

	public String getCheckVpf() {
		return checkVpf;
	}

	public void setCheckVpf(String checkVpf) {
		this.checkVpf = checkVpf;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPfAmount() {
		return pfAmount;
	}

	public void setPfAmount(String pfAmount) {
		this.pfAmount = pfAmount;
	}

	public String getBankMicrId() {
		return bankMicrId;
	}

	public void setBankMicrId(String bankMicrId) {
		this.bankMicrId = bankMicrId;
	}

	public String getChallanCode() {
		return challanCode;
	}

	public void setChallanCode(String challanCode) {
		this.challanCode = challanCode;
	}

	public String getModePayment() {
		return modePayment;
	}

	public void setModePayment(String modePayment) {
		this.modePayment = modePayment;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDateOfPay() {
		return dateOfPay;
	}

	public void setDateOfPay(String dateOfPay) {
		this.dateOfPay = dateOfPay;
	}

	public String getChequeNo() {
		return chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
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

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getEstabNo() {
		return estabNo;
	}

	public void setEstabNo(String estabNo) {
		this.estabNo = estabNo;
	}

	public String getAccGrpNo() {
		return accGrpNo;
	}

	public void setAccGrpNo(String accGrpNo) {
		this.accGrpNo = accGrpNo;
	}

	public String getVpfCode() {
		return vpfCode;
	}

	public void setVpfCode(String vpfCode) {
		this.vpfCode = vpfCode;
	}

	public String getDivAdd() {
		return divAdd;
	}

	public void setDivAdd(String divAdd) {
		this.divAdd = divAdd;
	}

	public String getDivCity() {
		return divCity;
	}

	public void setDivCity(String divCity) {
		this.divCity = divCity;
	}
	public String getVpfName() {
		return vpfName;
	}

	public void setVpfName(String vpfName) {
		this.vpfName = vpfName;
	}

	public String getCheqDate() {
		return cheqDate;
	}

	public void setCheqDate(String cheqDate) {
		this.cheqDate = cheqDate;
	}

	public String getHcheckVpf() {
		return hcheckVpf;
	}

	public void setHcheckVpf(String hcheckVpf) {
		this.hcheckVpf = hcheckVpf;
	}

	public String getBankBranchName() {
		return bankBranchName;
	}

	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}

	public String getPfOfficeName() {
		return pfOfficeName;
	}

	public void setPfOfficeName(String pfOfficeName) {
		this.pfOfficeName = pfOfficeName;
	}

	public String getEmployersPfContDeductedAc1() {
		return employersPfContDeductedAc1;
	}

	public void setEmployersPfContDeductedAc1(String employersPfContDeductedAc1) {
		this.employersPfContDeductedAc1 = employersPfContDeductedAc1;
	}

	public String getEmployersPensionDeductedAc10() {
		return employersPensionDeductedAc10;
	}

	public void setEmployersPensionDeductedAc10(String employersPensionDeductedAc10) {
		this.employersPensionDeductedAc10 = employersPensionDeductedAc10;
	}

	public String getEmployersEdliDeductedAc21() {
		return employersEdliDeductedAc21;
	}

	public void setEmployersEdliDeductedAc21(String employersEdliDeductedAc21) {
		this.employersEdliDeductedAc21 = employersEdliDeductedAc21;
	}

	public String getEmployersTotalDeducted() {
		return employersTotalDeducted;
	}

	public void setEmployersTotalDeducted(String employersTotalDeducted) {
		this.employersTotalDeducted = employersTotalDeducted;
	}

	public String getEmpPfContDeductedAc1() {
		return empPfContDeductedAc1;
	}

	public void setEmpPfContDeductedAc1(String empPfContDeductedAc1) {
		this.empPfContDeductedAc1 = empPfContDeductedAc1;
	}

	public String getEmpTotalDeducted() {
		return empTotalDeducted;
	}

	public void setEmpTotalDeducted(String empTotalDeducted) {
		this.empTotalDeducted = empTotalDeducted;
	}

	public String getAdminPfAdminDeductedAc2() {
		return adminPfAdminDeductedAc2;
	}

	public void setAdminPfAdminDeductedAc2(String adminPfAdminDeductedAc2) {
		this.adminPfAdminDeductedAc2 = adminPfAdminDeductedAc2;
	}

	public String getAdminEdliAdminDeductedAc22() {
		return adminEdliAdminDeductedAc22;
	}

	public void setAdminEdliAdminDeductedAc22(String adminEdliAdminDeductedAc22) {
		this.adminEdliAdminDeductedAc22 = adminEdliAdminDeductedAc22;
	}

	public String getAdminTotalDeducted() {
		return adminTotalDeducted;
	}

	public void setAdminTotalDeducted(String adminTotalDeducted) {
		this.adminTotalDeducted = adminTotalDeducted;
	}

	public String getInspectionEdliAdminDeductedAc22() {
		return inspectionEdliAdminDeductedAc22;
	}

	public void setInspectionEdliAdminDeductedAc22(
			String inspectionEdliAdminDeductedAc22) {
		this.inspectionEdliAdminDeductedAc22 = inspectionEdliAdminDeductedAc22;
	}

	public String getInspectionTotalDeducted() {
		return inspectionTotalDeducted;
	}

	public void setInspectionTotalDeducted(String inspectionTotalDeducted) {
		this.inspectionTotalDeducted = inspectionTotalDeducted;
	}

	public String getTotalPfContDeductedAc1() {
		return totalPfContDeductedAc1;
	}

	public void setTotalPfContDeductedAc1(String totalPfContDeductedAc1) {
		this.totalPfContDeductedAc1 = totalPfContDeductedAc1;
	}

	public String getTotalPfAdminDeductedAc2() {
		return totalPfAdminDeductedAc2;
	}

	public void setTotalPfAdminDeductedAc2(String totalPfAdminDeductedAc2) {
		this.totalPfAdminDeductedAc2 = totalPfAdminDeductedAc2;
	}

	public String getTotalPensionDeductedAc10() {
		return totalPensionDeductedAc10;
	}

	public void setTotalPensionDeductedAc10(String totalPensionDeductedAc10) {
		this.totalPensionDeductedAc10 = totalPensionDeductedAc10;
	}

	public String getTotalEdliDeductedAc21() {
		return totalEdliDeductedAc21;
	}

	public void setTotalEdliDeductedAc21(String totalEdliDeductedAc21) {
		this.totalEdliDeductedAc21 = totalEdliDeductedAc21;
	}

	public String getTotalEdliAdminDeductedAc22() {
		return totalEdliAdminDeductedAc22;
	}

	public void setTotalEdliAdminDeductedAc22(String totalEdliAdminDeductedAc22) {
		this.totalEdliAdminDeductedAc22 = totalEdliAdminDeductedAc22;
	}

	public String getTotalDeducted() {
		return totalDeducted;
	}

	public void setTotalDeducted(String totalDeducted) {
		this.totalDeducted = totalDeducted;
	}

	public String getSubscribersPfContDeductedAc1() {
		return subscribersPfContDeductedAc1;
	}

	public void setSubscribersPfContDeductedAc1(String subscribersPfContDeductedAc1) {
		this.subscribersPfContDeductedAc1 = subscribersPfContDeductedAc1;
	}

	public String getSubscribersPensionDeductedAc10() {
		return subscribersPensionDeductedAc10;
	}

	public void setSubscribersPensionDeductedAc10(
			String subscribersPensionDeductedAc10) {
		this.subscribersPensionDeductedAc10 = subscribersPensionDeductedAc10;
	}

	public String getSubscribersEdliDeductedAc21() {
		return subscribersEdliDeductedAc21;
	}

	public void setSubscribersEdliDeductedAc21(String subscribersEdliDeductedAc21) {
		this.subscribersEdliDeductedAc21 = subscribersEdliDeductedAc21;
	}

	public String getWagesPfContDeductedAc1() {
		return wagesPfContDeductedAc1;
	}

	public void setWagesPfContDeductedAc1(String wagesPfContDeductedAc1) {
		this.wagesPfContDeductedAc1 = wagesPfContDeductedAc1;
	}

	public String getWagesPensionDeductedAc10() {
		return wagesPensionDeductedAc10;
	}

	public void setWagesPensionDeductedAc10(String wagesPensionDeductedAc10) {
		this.wagesPensionDeductedAc10 = wagesPensionDeductedAc10;
	}

	public String getWagesEdliDeductedAc21() {
		return wagesEdliDeductedAc21;
	}

	public void setWagesEdliDeductedAc21(String wagesEdliDeductedAc21) {
		this.wagesEdliDeductedAc21 = wagesEdliDeductedAc21;
	}

	public String getEmployersPfContDepositedAc1() {
		return employersPfContDepositedAc1;
	}

	public void setEmployersPfContDepositedAc1(String employersPfContDepositedAc1) {
		this.employersPfContDepositedAc1 = employersPfContDepositedAc1;
	}

	public String getEmployersPensionDepositedAc10() {
		return employersPensionDepositedAc10;
	}

	public void setEmployersPensionDepositedAc10(
			String employersPensionDepositedAc10) {
		this.employersPensionDepositedAc10 = employersPensionDepositedAc10;
	}

	public String getEmployersEdliDepositedAc21() {
		return employersEdliDepositedAc21;
	}

	public void setEmployersEdliDepositedAc21(String employersEdliDepositedAc21) {
		this.employersEdliDepositedAc21 = employersEdliDepositedAc21;
	}

	public String getEmployersTotalDeposited() {
		return employersTotalDeposited;
	}

	public void setEmployersTotalDeposited(String employersTotalDeposited) {
		this.employersTotalDeposited = employersTotalDeposited;
	}

	public String getEmpPfContDepositedAc1() {
		return empPfContDepositedAc1;
	}

	public void setEmpPfContDepositedAc1(String empPfContDepositedAc1) {
		this.empPfContDepositedAc1 = empPfContDepositedAc1;
	}

	public String getEmpTotalDeposited() {
		return empTotalDeposited;
	}

	public void setEmpTotalDeposited(String empTotalDeposited) {
		this.empTotalDeposited = empTotalDeposited;
	}

	public String getAdminPfAdminDepositedAc2() {
		return adminPfAdminDepositedAc2;
	}

	public void setAdminPfAdminDepositedAc2(String adminPfAdminDepositedAc2) {
		this.adminPfAdminDepositedAc2 = adminPfAdminDepositedAc2;
	}

	public String getAdminEdliDepositedAc22() {
		return adminEdliDepositedAc22;
	}

	public void setAdminEdliDepositedAc22(String adminEdliDepositedAc22) {
		this.adminEdliDepositedAc22 = adminEdliDepositedAc22;
	}

	public String getAdminTotalDeposited() {
		return adminTotalDeposited;
	}

	public void setAdminTotalDeposited(String adminTotalDeposited) {
		this.adminTotalDeposited = adminTotalDeposited;
	}

	public String getInspectionEdliDepositedAc22() {
		return inspectionEdliDepositedAc22;
	}

	public void setInspectionEdliDepositedAc22(String inspectionEdliDepositedAc22) {
		this.inspectionEdliDepositedAc22 = inspectionEdliDepositedAc22;
	}

	public String getInspectionTotalDeposited() {
		return inspectionTotalDeposited;
	}

	public void setInspectionTotalDeposited(String inspectionTotalDeposited) {
		this.inspectionTotalDeposited = inspectionTotalDeposited;
	}

	public String getPenalDamagesPfContDepositedAc1() {
		return penalDamagesPfContDepositedAc1;
	}

	public void setPenalDamagesPfContDepositedAc1(
			String penalDamagesPfContDepositedAc1) {
		this.penalDamagesPfContDepositedAc1 = penalDamagesPfContDepositedAc1;
	}

	public String getPenalDamagesPfAdminDepositedAc2() {
		return penalDamagesPfAdminDepositedAc2;
	}

	public void setPenalDamagesPfAdminDepositedAc2(
			String penalDamagesPfAdminDepositedAc2) {
		this.penalDamagesPfAdminDepositedAc2 = penalDamagesPfAdminDepositedAc2;
	}

	public String getPenalDamagesPensionDepositedAc10() {
		return penalDamagesPensionDepositedAc10;
	}

	public void setPenalDamagesPensionDepositedAc10(
			String penalDamagesPensionDepositedAc10) {
		this.penalDamagesPensionDepositedAc10 = penalDamagesPensionDepositedAc10;
	}

	public String getPenalDamagesEdliDepositedAc21() {
		return penalDamagesEdliDepositedAc21;
	}

	public void setPenalDamagesEdliDepositedAc21(
			String penalDamagesEdliDepositedAc21) {
		this.penalDamagesEdliDepositedAc21 = penalDamagesEdliDepositedAc21;
	}

	public String getPenalDamagesEdliAdminDepositedAc22() {
		return penalDamagesEdliAdminDepositedAc22;
	}

	public void setPenalDamagesEdliAdminDepositedAc22(
			String penalDamagesEdliAdminDepositedAc22) {
		this.penalDamagesEdliAdminDepositedAc22 = penalDamagesEdliAdminDepositedAc22;
	}

	public String getPenalDamagesTotalDeposited() {
		return penalDamagesTotalDeposited;
	}

	public void setPenalDamagesTotalDeposited(String penalDamagesTotalDeposited) {
		this.penalDamagesTotalDeposited = penalDamagesTotalDeposited;
	}

	public String getMiscPfAdminDepositedAc2() {
		return miscPfAdminDepositedAc2;
	}

	public void setMiscPfAdminDepositedAc2(String miscPfAdminDepositedAc2) {
		this.miscPfAdminDepositedAc2 = miscPfAdminDepositedAc2;
	}

	public String getMiscEdliAdminDepositedAc22() {
		return miscEdliAdminDepositedAc22;
	}

	public void setMiscEdliAdminDepositedAc22(String miscEdliAdminDepositedAc22) {
		this.miscEdliAdminDepositedAc22 = miscEdliAdminDepositedAc22;
	}

	public String getMiscTotalDeposited() {
		return miscTotalDeposited;
	}

	public void setMiscTotalDeposited(String miscTotalDeposited) {
		this.miscTotalDeposited = miscTotalDeposited;
	}

	public String getTotalPfContDepositedAc1() {
		return totalPfContDepositedAc1;
	}

	public void setTotalPfContDepositedAc1(String totalPfContDepositedAc1) {
		this.totalPfContDepositedAc1 = totalPfContDepositedAc1;
	}

	public String getTotalPfAdminDepositedAc2() {
		return totalPfAdminDepositedAc2;
	}

	public void setTotalPfAdminDepositedAc2(String totalPfAdminDepositedAc2) {
		this.totalPfAdminDepositedAc2 = totalPfAdminDepositedAc2;
	}

	public String getTotalPensionDepositedAc10() {
		return totalPensionDepositedAc10;
	}

	public void setTotalPensionDepositedAc10(String totalPensionDepositedAc10) {
		this.totalPensionDepositedAc10 = totalPensionDepositedAc10;
	}

	public String getTotalEdliDepositedAc21() {
		return totalEdliDepositedAc21;
	}

	public void setTotalEdliDepositedAc21(String totalEdliDepositedAc21) {
		this.totalEdliDepositedAc21 = totalEdliDepositedAc21;
	}

	public String getTotalEdliAdminDepositedAc22() {
		return totalEdliAdminDepositedAc22;
	}

	public void setTotalEdliAdminDepositedAc22(String totalEdliAdminDepositedAc22) {
		this.totalEdliAdminDepositedAc22 = totalEdliAdminDepositedAc22;
	}

	public String getTotalDeposited() {
		return totalDeposited;
	}

	public void setTotalDeposited(String totalDeposited) {
		this.totalDeposited = totalDeposited;
	}

	public String getInspectionPfAdminDeductedAc2() {
		return inspectionPfAdminDeductedAc2;
	}

	public void setInspectionPfAdminDeductedAc2(String inspectionPfAdminDeductedAc2) {
		this.inspectionPfAdminDeductedAc2 = inspectionPfAdminDeductedAc2;
	}

	public String getInspectionPfAdminDepositedAc2() {
		return inspectionPfAdminDepositedAc2;
	}

	public void setInspectionPfAdminDepositedAc2(
			String inspectionPfAdminDepositedAc2) {
		this.inspectionPfAdminDepositedAc2 = inspectionPfAdminDepositedAc2;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

}
