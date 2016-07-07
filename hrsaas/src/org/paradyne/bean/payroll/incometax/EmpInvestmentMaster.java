package org.paradyne.bean.payroll.incometax;

import java.util.ArrayList;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

/**
 * @author Venkatesh
 *
 */
public class EmpInvestmentMaster extends BeanBase {
	
	
	private String invCode="";
	private String empID="";
	private String empName="";
	private String department="";
	private String center="";
	private String centerId="";
	private String gender = "";
	private String age = "";
	private String joinedMonth = "";
	private String fromYear="";
	private String toYear="";
	private String invAmount="";
	private String invName="";
	private String paraID="";
	private String empTokenNo="";
	private String invId="";
	private String invChapter="";
	private String chkEdit="";
	private String invSection="";
	private String isFromEdit="";
	private String invValid="";
	private String headName="";
	private String salAmount="";
	private String invLimit="";
	private String invLimBlank="";
	private String dataPath;
	private String isProofAttach = "";
	private String uploadFileName="";
	private String invProofAttach = "";
	private String uploadDocName="";
	
	ArrayList empInvList=null;
	ArrayList taxExempt=null;
	
	//==========house rent bean========//
	private String rentCode="";
	private String monthNo="";
	private String month="";
	private String amount="";
	private String designation="";
	private String rentFlag="false";
	private ArrayList rentList=null;
	private String totAmt="";
	private String joinFlag="false";
	//update by Anantha lakshmi
	private String panNum="";
	private String owner1Name="";
	private String owner1Address="";
	private String owner1Agreement="";
	private String owner1Period="";
	private String owner2Name="";
	private String owner2Address="";
	private String owner2Agreement="";
	private String owner2Period="";
	private String proofAttach="";

	//Added by ganesh 11/10/2011
	private String grade = "";
	private String joiningDate = "";
	private String investmentSection = "";
	TreeMap map;
	private String invDecListId = "";
	private String investmentName = "";
	private String investmentlimit = "";
	private String investmentAmt = "";
	private String investmentProof = "";
	private ArrayList invDecList=null;
	boolean invDecFlag = false;
	private boolean invDecListLength = false;
	private String validAmt = "";
	
private String uploadLocFileName = "";
	
	ArrayList proofList;
	private String proofSrNo="";
	private String proofName="";
	private String proofFileName="";
	private String checkRemoveUpload="";
	private String hiddenfrmId = "";
	private String ittproofName="";
	ArrayList ittUploadList;
	
	boolean monthAccomodationFlag = false;
	private ArrayList monthList=null;
	private String rentAmt = "";
	private String isMetroflag = "";
	private String compOwnedHouseflag = "";
	private String houseRentpaidbyCompanyflag = "";
	private String carUsedflag = "";
	private String cubicCapacityflag = "";
	private String driverUsedflag = "";
	private String inIndiaflag = "";
	
	private String isMetroflagCheck = "";
	private String compOwnedHouseflagCheck = "";
	private String houseRentpaidbyCompanyflagCheck = "";
	private String carUsedflagCheck = "";
	private String cubicCapacityflagCheck = "";
	private String driverUsedflagCheck = "";
	private String inIndiaflagCheck = "";
	private String ittRentCode = "";
	private String uploadRentFileName = "";
	private String ittMonthCode="";
	boolean monthIttFlag  = false;
	ArrayList proofInvestList;
	private String investmentType="";
	boolean donationFlag = false;
	
	private ArrayList donationList=null;
	
	private boolean donationListLength = false;
	private String donationListId = "";
	private String donationName = "";
	private String exemption = "";
	private String donationAmt = "";
	private String donationValidAmt = "";
	
	private String report = "";
	
	
	private String isCityPopulationflag="";
	
	private String inIndiaCheckedHiddenflag = "";
	private String isMetroCheckedHiddenflag = "";
	private String houseRentpaidbyCompanyCheckedHiddenflag = "";
	private String compOwnedHouseCheckedHiddenflag = "";
	private String isCityPopulationCheckedHiddenflag = "";
	private String carUsedCheckedHiddenflag = "";
	private String cubicCapacityCheckedHiddenflag = "";
	private String driverUsedCheckedHiddenflag = "";
	
	boolean cutOffDateFlag = false;
	private String  periodFlag = "";
	private String rentApplFlag = "";

	/**
	 * @return the cutOffDateFlag
	 */
	public boolean isCutOffDateFlag() {
		return cutOffDateFlag;
	}

	/**
	 * @param cutOffDateFlag the cutOffDateFlag to set
	 */
	public void setCutOffDateFlag(boolean cutOffDateFlag) {
		this.cutOffDateFlag = cutOffDateFlag;
	}

	

	/**
	 * @return the periodFlag
	 */
	public String getPeriodFlag() {
		return periodFlag;
	}

	/**
	 * @param periodFlag the periodFlag to set
	 */
	public void setPeriodFlag(String periodFlag) {
		this.periodFlag = periodFlag;
	}

	public String getInIndiaCheckedHiddenflag() {
		return inIndiaCheckedHiddenflag;
	}

	public void setInIndiaCheckedHiddenflag(String inIndiaCheckedHiddenflag) {
		this.inIndiaCheckedHiddenflag = inIndiaCheckedHiddenflag;
	}

	public String getIsMetroCheckedHiddenflag() {
		return isMetroCheckedHiddenflag;
	}

	public void setIsMetroCheckedHiddenflag(String isMetroCheckedHiddenflag) {
		this.isMetroCheckedHiddenflag = isMetroCheckedHiddenflag;
	}

	public String getHouseRentpaidbyCompanyCheckedHiddenflag() {
		return houseRentpaidbyCompanyCheckedHiddenflag;
	}

	public void setHouseRentpaidbyCompanyCheckedHiddenflag(
			String houseRentpaidbyCompanyCheckedHiddenflag) {
		this.houseRentpaidbyCompanyCheckedHiddenflag = houseRentpaidbyCompanyCheckedHiddenflag;
	}

	public String getCompOwnedHouseCheckedHiddenflag() {
		return compOwnedHouseCheckedHiddenflag;
	}

	public void setCompOwnedHouseCheckedHiddenflag(
			String compOwnedHouseCheckedHiddenflag) {
		this.compOwnedHouseCheckedHiddenflag = compOwnedHouseCheckedHiddenflag;
	}

	public String getIsCityPopulationCheckedHiddenflag() {
		return isCityPopulationCheckedHiddenflag;
	}

	public void setIsCityPopulationCheckedHiddenflag(
			String isCityPopulationCheckedHiddenflag) {
		this.isCityPopulationCheckedHiddenflag = isCityPopulationCheckedHiddenflag;
	}

	public String getCarUsedCheckedHiddenflag() {
		return carUsedCheckedHiddenflag;
	}

	public void setCarUsedCheckedHiddenflag(String carUsedCheckedHiddenflag) {
		this.carUsedCheckedHiddenflag = carUsedCheckedHiddenflag;
	}

	public String getCubicCapacityCheckedHiddenflag() {
		return cubicCapacityCheckedHiddenflag;
	}

	public void setCubicCapacityCheckedHiddenflag(
			String cubicCapacityCheckedHiddenflag) {
		this.cubicCapacityCheckedHiddenflag = cubicCapacityCheckedHiddenflag;
	}

	public String getDriverUsedCheckedHiddenflag() {
		return driverUsedCheckedHiddenflag;
	}

	public void setDriverUsedCheckedHiddenflag(String driverUsedCheckedHiddenflag) {
		this.driverUsedCheckedHiddenflag = driverUsedCheckedHiddenflag;
	}

	public String getIsCityPopulationflag() {
		return isCityPopulationflag;
	}

	public void setIsCityPopulationflag(String isCityPopulationflag) {
		this.isCityPopulationflag = isCityPopulationflag;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public String getDonationListId() {
		return donationListId;
	}

	public void setDonationListId(String donationListId) {
		this.donationListId = donationListId;
	}

	public String getDonationName() {
		return donationName;
	}

	public void setDonationName(String donationName) {
		this.donationName = donationName;
	}

	public String getExemption() {
		return exemption;
	}

	public void setExemption(String exemption) {
		this.exemption = exemption;
	}

	public String getDonationAmt() {
		return donationAmt;
	}

	public void setDonationAmt(String donationAmt) {
		this.donationAmt = donationAmt;
	}

	public String getDonationValidAmt() {
		return donationValidAmt;
	}

	public void setDonationValidAmt(String donationValidAmt) {
		this.donationValidAmt = donationValidAmt;
	}

	public boolean isDonationFlag() {
		return donationFlag;
	}

	public void setDonationFlag(boolean donationFlag) {
		this.donationFlag = donationFlag;
	}

	public ArrayList getProofInvestList() {
		return proofInvestList;
	}

	public void setProofInvestList(ArrayList proofInvestList) {
		this.proofInvestList = proofInvestList;
	}

	public boolean isMonthIttFlag() {
		return monthIttFlag;
	}

	public void setMonthIttFlag(boolean monthIttFlag) {
		this.monthIttFlag = monthIttFlag;
	}

	public String getIttMonthCode() {
		return ittMonthCode;
	}

	public void setIttMonthCode(String ittMonthCode) {
		this.ittMonthCode = ittMonthCode;
	}

	public String getUploadRentFileName() {
		return uploadRentFileName;
	}

	public void setUploadRentFileName(String uploadRentFileName) {
		this.uploadRentFileName = uploadRentFileName;
	}

	public String getIttRentCode() {
		return ittRentCode;
	}

	public void setIttRentCode(String ittRentCode) {
		this.ittRentCode = ittRentCode;
	}

	public String getIsMetroflagCheck() {
		return isMetroflagCheck;
	}

	public void setIsMetroflagCheck(String isMetroflagCheck) {
		this.isMetroflagCheck = isMetroflagCheck;
	}

	public String getCompOwnedHouseflagCheck() {
		return compOwnedHouseflagCheck;
	}

	public void setCompOwnedHouseflagCheck(String compOwnedHouseflagCheck) {
		this.compOwnedHouseflagCheck = compOwnedHouseflagCheck;
	}

	public String getHouseRentpaidbyCompanyflagCheck() {
		return houseRentpaidbyCompanyflagCheck;
	}

	public void setHouseRentpaidbyCompanyflagCheck(
			String houseRentpaidbyCompanyflagCheck) {
		this.houseRentpaidbyCompanyflagCheck = houseRentpaidbyCompanyflagCheck;
	}

	public String getCarUsedflagCheck() {
		return carUsedflagCheck;
	}

	public void setCarUsedflagCheck(String carUsedflagCheck) {
		this.carUsedflagCheck = carUsedflagCheck;
	}

	public String getCubicCapacityflagCheck() {
		return cubicCapacityflagCheck;
	}

	public void setCubicCapacityflagCheck(String cubicCapacityflagCheck) {
		this.cubicCapacityflagCheck = cubicCapacityflagCheck;
	}

	public String getDriverUsedflagCheck() {
		return driverUsedflagCheck;
	}

	public void setDriverUsedflagCheck(String driverUsedflagCheck) {
		this.driverUsedflagCheck = driverUsedflagCheck;
	}

	public String getInIndiaflagCheck() {
		return inIndiaflagCheck;
	}

	public void setInIndiaflagCheck(String inIndiaflagCheck) {
		this.inIndiaflagCheck = inIndiaflagCheck;
	}

	public String getRentAmt() {
		return rentAmt;
	}

	public void setRentAmt(String rentAmt) {
		this.rentAmt = rentAmt;
	}

	public String getIsMetroflag() {
		return isMetroflag;
	}

	public void setIsMetroflag(String isMetroflag) {
		this.isMetroflag = isMetroflag;
	}

	public String getCompOwnedHouseflag() {
		return compOwnedHouseflag;
	}

	public void setCompOwnedHouseflag(String compOwnedHouseflag) {
		this.compOwnedHouseflag = compOwnedHouseflag;
	}

	public String getHouseRentpaidbyCompanyflag() {
		return houseRentpaidbyCompanyflag;
	}

	public void setHouseRentpaidbyCompanyflag(String houseRentpaidbyCompanyflag) {
		this.houseRentpaidbyCompanyflag = houseRentpaidbyCompanyflag;
	}

	public String getCarUsedflag() {
		return carUsedflag;
	}

	public void setCarUsedflag(String carUsedflag) {
		this.carUsedflag = carUsedflag;
	}

	public String getCubicCapacityflag() {
		return cubicCapacityflag;
	}

	public void setCubicCapacityflag(String cubicCapacityflag) {
		this.cubicCapacityflag = cubicCapacityflag;
	}

	public String getDriverUsedflag() {
		return driverUsedflag;
	}

	public void setDriverUsedflag(String driverUsedflag) {
		this.driverUsedflag = driverUsedflag;
	}

	public String getInIndiaflag() {
		return inIndiaflag;
	}

	public void setInIndiaflag(String inIndiaflag) {
		this.inIndiaflag = inIndiaflag;
	}

	public boolean isMonthAccomodationFlag() {
		return monthAccomodationFlag;
	}

	public void setMonthAccomodationFlag(boolean monthAccomodationFlag) {
		this.monthAccomodationFlag = monthAccomodationFlag;
	}

	public ArrayList getIttUploadList() {
		return ittUploadList;
	}

	public void setIttUploadList(ArrayList ittUploadList) {
		this.ittUploadList = ittUploadList;
	}

	public String getIttproofName() {
		return ittproofName;
	}

	public void setIttproofName(String ittproofName) {
		this.ittproofName = ittproofName;
	}

	public String getHiddenfrmId() {
		return hiddenfrmId;
	}

	public void setHiddenfrmId(String hiddenfrmId) {
		this.hiddenfrmId = hiddenfrmId;
	}

	public String getUploadLocFileName() {
		return uploadLocFileName;
	}

	public void setUploadLocFileName(String uploadLocFileName) {
		this.uploadLocFileName = uploadLocFileName;
	}

	public ArrayList getProofList() {
		return proofList;
	}

	public void setProofList(ArrayList proofList) {
		this.proofList = proofList;
	}

	public String getProofSrNo() {
		return proofSrNo;
	}

	public void setProofSrNo(String proofSrNo) {
		this.proofSrNo = proofSrNo;
	}

	public String getProofName() {
		return proofName;
	}

	public void setProofName(String proofName) {
		this.proofName = proofName;
	}

	public String getProofFileName() {
		return proofFileName;
	}

	public void setProofFileName(String proofFileName) {
		this.proofFileName = proofFileName;
	}

	public String getCheckRemoveUpload() {
		return checkRemoveUpload;
	}

	public void setCheckRemoveUpload(String checkRemoveUpload) {
		this.checkRemoveUpload = checkRemoveUpload;
	}

	public String getInvDecListId() {
		return invDecListId;
	}

	public void setInvDecListId(String invDecListId) {
		this.invDecListId = invDecListId;
	}

	public String getInvestmentName() {
		return investmentName;
	}

	public void setInvestmentName(String investmentName) {
		this.investmentName = investmentName;
	}

	public String getInvestmentlimit() {
		return investmentlimit;
	}

	public void setInvestmentlimit(String investmentlimit) {
		this.investmentlimit = investmentlimit;
	}

	public String getInvestmentAmt() {
		return investmentAmt;
	}

	public void setInvestmentAmt(String investmentAmt) {
		this.investmentAmt = investmentAmt;
	}

	public String getInvestmentProof() {
		return investmentProof;
	}

	public void setInvestmentProof(String investmentProof) {
		this.investmentProof = investmentProof;
	}

	public TreeMap getMap() {
		return map;
	}

	public void setMap(TreeMap map) {
		this.map = map;
	}

	public String getInvestmentSection() {
		return investmentSection;
	}

	public void setInvestmentSection(String investmentSection) {
		this.investmentSection = investmentSection;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public ArrayList getTaxExempt() {
		return taxExempt;
	}

	public void setTaxExempt(ArrayList taxExempt) {
		this.taxExempt = taxExempt;
	}

	public EmpInvestmentMaster()
	{
		
	}
	
	public EmpInvestmentMaster(String invCode,String empID,String empName,String department,String center,String fromYear,String toYear,String invAmount,String invName,boolean chkEdit)
	{
		super();
		this.invCode=invCode;
		this.empID=empID;
		this.empName=empName;
		this.center=center;
		this.department=department;
		this.fromYear=fromYear;
		this.toYear=toYear;
		this.invAmount=invAmount;
		this.invName=invName;
		
	}
	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public ArrayList getEmpInvList() {
		return empInvList;
	}

	public void setEmpInvList(ArrayList empInvList) {
		this.empInvList = empInvList;
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

	public String getInvAmount() {
		return invAmount;
	}

	public void setInvAmount(String invAmount) {
		this.invAmount = invAmount;
	}

	public String getInvCode() {
		return invCode;
	}

	public void setInvCode(String invCode) {
		this.invCode = invCode;
	}

	public String getToYear() {
		return toYear;
	}

	public void setToYear(String toYear) {
		this.toYear = toYear;
	}

	public String getInvName() {
		return invName;
	}

	public void setInvName(String invName) {
		this.invName = invName;
	}

	public String getParaID() {
		return paraID;
	}

	public void setParaID(String paraID) {
		this.paraID = paraID;
	}

	
	/**
	 * @return the chkEdit
	 */
	public String getChkEdit() {
		return chkEdit;
	}

	/**
	 * @param chkEdit the chkEdit to set
	 */
	public void setChkEdit(String chkEdit) {
		this.chkEdit = chkEdit;
	}

	public String getEmpTokenNo() {
		return empTokenNo;
	}

	public void setEmpTokenNo(String empTokenNo) {
		this.empTokenNo = empTokenNo;
	}

	/**
	 * @return the isFromEdit
	 */
	public String getIsFromEdit() {
		return isFromEdit;
	}

	/**
	 * @param isFromEdit the isFromEdit to set
	 */
	public void setIsFromEdit(String isFromEdit) {
		this.isFromEdit = isFromEdit;
	}

	/**
	 * @return the invId
	 */
	public String getInvId() {
		return invId;
	}

	/**
	 * @param invId the invId to set
	 */
	public void setInvId(String invId) {
		this.invId = invId;
	}

	public String getInvChapter() {
		return invChapter;
	}

	public void setInvChapter(String invChapter) {
		this.invChapter = invChapter;
	}

	public String getInvSection() {
		return invSection;
	}

	public void setInvSection(String invSection) {
		this.invSection = invSection;
	}

	public String getInvValid() {
		return invValid;
	}

	public void setInvValid(String invValid) {
		this.invValid = invValid;
	}

	public String getHeadName() {
		return headName;
	}

	public void setHeadName(String headName) {
		this.headName = headName;
	}

	public String getSalAmount() {
		return salAmount;
	}

	public void setSalAmount(String salAmount) {
		this.salAmount = salAmount;
	}

	public String getInvLimit() {
		return invLimit;
	}

	public void setInvLimit(String invLimit) {
		this.invLimit = invLimit;
	}

	public String getInvLimBlank() {
		return invLimBlank;
	}

	public void setInvLimBlank(String invLimBlank) {
		this.invLimBlank = invLimBlank;
	}

	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}

	public String getIsProofAttach() {
		return isProofAttach;
	}

	public void setIsProofAttach(String isProofAttach) {
		this.isProofAttach = isProofAttach;
	}



	public String getInvProofAttach() {
		return invProofAttach;
	}

	public void setInvProofAttach(String invProofAttach) {
		this.invProofAttach = invProofAttach;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadDocName() {
		return uploadDocName;
	}

	public void setUploadDocName(String uploadDocName) {
		this.uploadDocName = uploadDocName;
	}

	public String getRentCode() {
		return rentCode;
	}

	public void setRentCode(String rentCode) {
		this.rentCode = rentCode;
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

	public ArrayList getRentList() {
		return rentList;
	}

	public void setRentList(ArrayList rentList) {
		this.rentList = rentList;
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

	public String getCenterId() {
		return centerId;
	}

	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getJoinedMonth() {
		return joinedMonth;
	}

	public void setJoinedMonth(String joinedMonth) {
		this.joinedMonth = joinedMonth;
	}

	public String getPanNum() {
		return panNum;
	}

	public void setPanNum(String panNum) {
		this.panNum = panNum;
	}

	public String getOwner1Name() {
		return owner1Name;
	}

	public void setOwner1Name(String owner1Name) {
		this.owner1Name = owner1Name;
	}

	public String getOwner1Address() {
		return owner1Address;
	}

	public void setOwner1Address(String owner1Address) {
		this.owner1Address = owner1Address;
	}

	public String getOwner1Agreement() {
		return owner1Agreement;
	}

	public void setOwner1Agreement(String owner1Agreement) {
		this.owner1Agreement = owner1Agreement;
	}

	public String getOwner1Period() {
		return owner1Period;
	}

	public void setOwner1Period(String owner1Period) {
		this.owner1Period = owner1Period;
	}

	public String getOwner2Name() {
		return owner2Name;
	}

	public void setOwner2Name(String owner2Name) {
		this.owner2Name = owner2Name;
	}

	public String getOwner2Address() {
		return owner2Address;
	}

	public void setOwner2Address(String owner2Address) {
		this.owner2Address = owner2Address;
	}

	public String getOwner2Agreement() {
		return owner2Agreement;
	}

	public void setOwner2Agreement(String owner2Agreement) {
		this.owner2Agreement = owner2Agreement;
	}

	public String getOwner2Period() {
		return owner2Period;
	}

	public void setOwner2Period(String owner2Period) {
		this.owner2Period = owner2Period;
	}

	public String getProofAttach() {
		return proofAttach;
	}

	public void setProofAttach(String proofAttach) {
		this.proofAttach = proofAttach;
	}

	public ArrayList getInvDecList() {
		return invDecList;
	}

	public void setInvDecList(ArrayList invDecList) {
		this.invDecList = invDecList;
	}

	public boolean isInvDecFlag() {
		return invDecFlag;
	}

	public void setInvDecFlag(boolean invDecFlag) {
		this.invDecFlag = invDecFlag;
	}

	public boolean isInvDecListLength() {
		return invDecListLength;
	}

	public void setInvDecListLength(boolean invDecListLength) {
		this.invDecListLength = invDecListLength;
	}

	public String getValidAmt() {
		return validAmt;
	}

	public void setValidAmt(String validAmt) {
		this.validAmt = validAmt;
	}

	public ArrayList getMonthList() {
		return monthList;
	}

	public void setMonthList(ArrayList monthList) {
		this.monthList = monthList;
	}

	public String getInvestmentType() {
		return investmentType;
	}

	public void setInvestmentType(String investmentType) {
		this.investmentType = investmentType;
	}

	public ArrayList getDonationList() {
		return donationList;
	}

	public void setDonationList(ArrayList donationList) {
		this.donationList = donationList;
	}

	public boolean isDonationListLength() {
		return donationListLength;
	}

	public void setDonationListLength(boolean donationListLength) {
		this.donationListLength = donationListLength;
	}

	/**
	 * @return the rentApplFlag
	 */
	public String getRentApplFlag() {
		return rentApplFlag;
	}

	/**
	 * @param rentApplFlag the rentApplFlag to set
	 */
	public void setRentApplFlag(String rentApplFlag) {
		this.rentApplFlag = rentApplFlag;
	}

}