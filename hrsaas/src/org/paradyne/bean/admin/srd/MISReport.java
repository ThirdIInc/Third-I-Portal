package org.paradyne.bean.admin.srd;

import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

/**
 * @author Reeba_Joseph
 * @date 30-11-2009
 */
public class MISReport extends BeanBase {
	private String tradeFlag="";
	private String marriageDateFlag="";
	private String pfNoFlag="";
	private String esicNoFlag="";
	private String panNoFlag="";
	private String empToken = "";
	private String empID = "";
	private String firstName = "";
	private String lastName = "";
	private String middleName = "";
	private String gender = "";
	private String birthDate = "";
	private String deptCode = "";
	private String deptName = "";
	private String desgCode = "";
	private String desgName = "";
	private String rankCode = "";
	private String rankName = "";
	private String centerCode = "";
	private String centerName = "";
	private String typeCode = "";
	private String typeName = "";
	private String payBillName = "";
	private String gradeCode = "";
	private String gradeName = "";
	private String shiftCode = "";
	private String shiftType = "";
	private String status = "";
	private String regularDate = "";
	private String leaveDate = "";
	private String divCode = "";
	private String divName = "";
	private String reportingTo = "";
	private String reportingID = "";
	private String bornFlag = "";
	private String reportingToken = "";
	private String uploadFileName = "";
	private String payBillId = "";
	private String bDayMonth = "";
	private String DOBfromDate = "";
	private String DOBToDate = "";
	private String regularFromDate = "";
	private String regularToDate = "";
	private String leaveFromDate = "";
	private String leaveToDate = "";
	private String retFromDate = "";
	private String retToDate = "";
	private String maritalStatus = "";
	private String religion = "";
	private String religionCode = "";
	private String castName = "";
	private String castCode = "";
	private String castCategory = "";
	private String castCategoryCode = "";
	private String payScaleCode = "";
	private String payScaleName = "";
	private String bldGroup = "";

	private String empTokenFlag = "";
	private String empNameFlag = "";
	private String rankFlag = "";
	private String dateRegFlag = "";
	private String dateBirthFlag = "";
	private String centerFlag = "";
	private String deptFlag = "";
	private String divFlag = "";
	private String bloodGrpFlag = "";
	private String addressFlag = "";
	private String desgFlag = "";
	private String casteFlag = "";
	private String religionFlag = "";
	private String catagoryFlag = "";
	private String marStatusFlag = "";
	private String payScaleFlag = "";
	private String genderFlag = "";
	private String dateOfRetFlag = "";
	private String sepFromDate="";
	private String sepToDate="";
	private String  dateOfSepFlag="";
	private String payModeFlag="";
	private String salaryFlag="";
	private String salBankFlag="";
	
	private String salAccFlag="";
	private String typeFlag="";
	private String emailFlag="";
	private String mobFlag="";
	
	
	private TreeMap map;
	
	private String yearexperienceFlag="";
	private String overexperienceFlag="";
	private String ctcFlag="";
	private String ageFlag="";
	
	
	private String dateOfConf="";
	private String confFromDate="";
	private String confToDate="";
	private String grade="";
	private String gradeFlage="";
	private String empGradeFlag="";
	
	//ADDED BY REEBA
	private String shiftFlag="";
	private String reportType="";
	private String reportTitle="";
	private String settingName="";
	private String reportId="";
	
	private String empStatusFlag="";
	private String reportingToFlag="";
	//private String accCategoryFlag="";
	//private String costCenterFlag="";
	//private String subCostCenterFlag="";
	
	private String sortBy="";
	private String hiddenSortBy="";
	private String  sortByOrder="";
	private String  thenBy1="";
	private String hiddenThenBy1="";
	private String  thenByOrder1="";
	private String  thenByOrder2=""; 
	private String  thenBy2="";
	private String hiddenThenBy2="";
	
	private String  sortByAsc="checked"; 	
	private String  sortByDsc=""; 
	private String  thenByOrder1Asc="checked"; 	
	private String  thenByOrder1Dsc=""; 
	private String  thenByOrder2Asc="checked"; 	
	private String  thenByOrder2Dsc="";
	
	private String reqStatus="";
	
	private String dobSelect="";
	private String dojSelect="";
	private String docSelect="";
	private String dolSelect="";
	private String ageSelect="";
	private String grossSalSelect="";
	private String ctcSelect="";
	
	private String ageFrom="";
	private String grossSalFrom="";
	private String ctcFrom="";
	private String ageTo="";
	private String grossSalTo="";
	private String ctcTo="";
	
	private String columnOrdering="";
	private String hiddenColumns="";
	
	private String myPage="";
	private String show="";
	private String dataLength="";
	private String hidReportView="checked"; 	
	private String hidReportRadio="";
	private String exportAll="";
	private String noData="false";
	
	private String backFlag = "";
	
	private String reportTypeStr = "";
	
	//ADDED BY VISHWAMBHAR FOR GROUP JOINING DATE
	private String groupjoindateFlag  = "";
	private String groupjoinToDate = "";
	private String groupjoinFromDate = "";
	private String groupjoindateSelect = "";
	
	//ADDED BY VISHWAMBHAR
	private String  dateFormatSelect = "";
	
	//ADDED BY Lakkichand FOR GROUP JOINING DATE
	private String roleFlag  = "";
	
	
	
	
 
	
	public String getGroupjoinToDate() {
		return groupjoinToDate;
	}

	public void setGroupjoinToDate(String groupjoinToDate) {
		this.groupjoinToDate = groupjoinToDate;
	}

	public String getGroupjoinFromDate() {
		return groupjoinFromDate;
	}

	public void setGroupjoinFromDate(String groupjoinFromDate) {
		this.groupjoinFromDate = groupjoinFromDate;
	}

	public String getGroupjoindateSelect() {
		return groupjoindateSelect;
	}

	public void setGroupjoindateSelect(String groupjoindateSelect) {
		this.groupjoindateSelect = groupjoindateSelect;
	}

	public String getGroupjoindateFlag() {
		return groupjoindateFlag;
	}

	public void setGroupjoindateFlag(String groupjoindateFlag) {
		this.groupjoindateFlag = groupjoindateFlag;
	}

	/**
	 * @return the myPage
	 */
	public String getMyPage() {
		return myPage;
	}

	/**
	 * @param myPage the myPage to set
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	/**
	 * @return the show
	 */
	public String getShow() {
		return show;
	}

	/**
	 * @param show the show to set
	 */
	public void setShow(String show) {
		this.show = show;
	}

	/**
	 * @return the dataLength
	 */
	public String getDataLength() {
		return dataLength;
	}

	/**
	 * @param dataLength the dataLength to set
	 */
	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}

	/**
	 * @return the hiddenColumns
	 */
	public String getHiddenColumns() {
		return hiddenColumns;
	}

	/**
	 * @param hiddenColumns the hiddenColumns to set
	 */
	public void setHiddenColumns(String hiddenColumns) {
		this.hiddenColumns = hiddenColumns;
	}

	/**
	 * @return the columnOrdering
	 */
	public String getColumnOrdering() {
		return columnOrdering;
	}

	/**
	 * @param columnOrdering the columnOrdering to set
	 */
	public void setColumnOrdering(String columnOrdering) {
		this.columnOrdering = columnOrdering;
	}

	/**
	 * @return the ageSelect
	 */
	public String getAgeSelect() {
		return ageSelect;
	}

	/**
	 * @param ageSelect the ageSelect to set
	 */
	public void setAgeSelect(String ageSelect) {
		this.ageSelect = ageSelect;
	}

	/**
	 * @return the grossSalSelect
	 */
	public String getGrossSalSelect() {
		return grossSalSelect;
	}

	/**
	 * @param grossSalSelect the grossSalSelect to set
	 */
	public void setGrossSalSelect(String grossSalSelect) {
		this.grossSalSelect = grossSalSelect;
	}

	/**
	 * @return the ctcSelect
	 */
	public String getCtcSelect() {
		return ctcSelect;
	}

	/**
	 * @param ctcSelect the ctcSelect to set
	 */
	public void setCtcSelect(String ctcSelect) {
		this.ctcSelect = ctcSelect;
	}

	/**
	 * @return the dobSelect
	 */
	public String getDobSelect() {
		return dobSelect;
	}

	/**
	 * @param dobSelect the dobSelect to set
	 */
	public void setDobSelect(String dobSelect) {
		this.dobSelect = dobSelect;
	}

	/**
	 * @return the dojSelect
	 */
	public String getDojSelect() {
		return dojSelect;
	}

	/**
	 * @param dojSelect the dojSelect to set
	 */
	public void setDojSelect(String dojSelect) {
		this.dojSelect = dojSelect;
	}

	/**
	 * @return the docSelect
	 */
	public String getDocSelect() {
		return docSelect;
	}

	/**
	 * @param docSelect the docSelect to set
	 */
	public void setDocSelect(String docSelect) {
		this.docSelect = docSelect;
	}

	/**
	 * @return the dolSelect
	 */
	public String getDolSelect() {
		return dolSelect;
	}

	/**
	 * @param dolSelect the dolSelect to set
	 */
	public void setDolSelect(String dolSelect) {
		this.dolSelect = dolSelect;
	}

	/**
	 * @return the sortBy
	 */
	public String getSortBy() {
		return sortBy;
	}

	/**
	 * @param sortBy the sortBy to set
	 */
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	/**
	 * @return the sortByOrder
	 */
	public String getSortByOrder() {
		return sortByOrder;
	}

	/**
	 * @param sortByOrder the sortByOrder to set
	 */
	public void setSortByOrder(String sortByOrder) {
		this.sortByOrder = sortByOrder;
	}

	/**
	 * @return the thenBy1
	 */
	public String getThenBy1() {
		return thenBy1;
	}

	/**
	 * @param thenBy1 the thenBy1 to set
	 */
	public void setThenBy1(String thenBy1) {
		this.thenBy1 = thenBy1;
	}

	/**
	 * @return the thenByOrder1
	 */
	public String getThenByOrder1() {
		return thenByOrder1;
	}

	/**
	 * @param thenByOrder1 the thenByOrder1 to set
	 */
	public void setThenByOrder1(String thenByOrder1) {
		this.thenByOrder1 = thenByOrder1;
	}

	/**
	 * @return the thenByOrder2
	 */
	public String getThenByOrder2() {
		return thenByOrder2;
	}

	/**
	 * @param thenByOrder2 the thenByOrder2 to set
	 */
	public void setThenByOrder2(String thenByOrder2) {
		this.thenByOrder2 = thenByOrder2;
	}

	/**
	 * @return the thenBy2
	 */
	public String getThenBy2() {
		return thenBy2;
	}

	/**
	 * @param thenBy2 the thenBy2 to set
	 */
	public void setThenBy2(String thenBy2) {
		this.thenBy2 = thenBy2;
	}

	/**
	 * @return the reqStatus
	 */
	public String getReqStatus() {
		return reqStatus;
	}

	/**
	 * @param reqStatus the reqStatus to set
	 */
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}

	/**
	 * @return the reportingToFlag
	 */
	public String getReportingToFlag() {
		return reportingToFlag;
	}

	/**
	 * @param reportingToFlag the reportingToFlag to set
	 */
	public void setReportingToFlag(String reportingToFlag) {
		this.reportingToFlag = reportingToFlag;
	}

	/**
	 * @return the empStatusFlag
	 */
	public String getEmpStatusFlag() {
		return empStatusFlag;
	}

	/**
	 * @param empStatusFlag the empStatusFlag to set
	 */
	public void setEmpStatusFlag(String empStatusFlag) {
		this.empStatusFlag = empStatusFlag;
	}

	/**
	 * @return the reportTitle
	 */
	public String getReportTitle() {
		return reportTitle;
	}

	/**
	 * @param reportTitle the reportTitle to set
	 */
	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}

	public String getShiftFlag() {
		return shiftFlag;
	}

	public void setShiftFlag(String shiftFlag) {
		this.shiftFlag = shiftFlag;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getDateOfConf() {
		return dateOfConf;
	}

	public void setDateOfConf(String dateOfConf) {
		this.dateOfConf = dateOfConf;
	}

	public String getConfFromDate() {
		return confFromDate;
	}

	public void setConfFromDate(String confFromDate) {
		this.confFromDate = confFromDate;
	}

	public String getConfToDate() {
		return confToDate;
	}

	public void setConfToDate(String confToDate) {
		this.confToDate = confToDate;
	}



	public String getEmailFlag() {
		return emailFlag;
	}

	public void setEmailFlag(String emailFlag) {
		this.emailFlag = emailFlag;
	}

	public String getMobFlag() {
		return mobFlag;
	}

	public void setMobFlag(String mobFlag) {
		this.mobFlag = mobFlag;
	}

	public String getPayModeFlag() {
		return payModeFlag;
	}

	public void setPayModeFlag(String payModeFlag) {
		this.payModeFlag = payModeFlag;
	}

	public String getSalaryFlag() {
		return salaryFlag;
	}

	public void setSalaryFlag(String salaryFlag) {
		this.salaryFlag = salaryFlag;
	}

	public String getSalBankFlag() {
		return salBankFlag;
	}

	public void setSalBankFlag(String salBankFlag) {
		this.salBankFlag = salBankFlag;
	}



	
	public String getTypeFlag() {
		return typeFlag;
	}

	public void setTypeFlag(String typeFlag) {
		this.typeFlag = typeFlag;
	}

	public String getDateOfSepFlag() {
		return dateOfSepFlag;
	}

	public void setDateOfSepFlag(String dateOfSepFlag) {
		this.dateOfSepFlag = dateOfSepFlag;
	}

	public String getSepFromDate() {
		return sepFromDate;
	}

	public void setSepFromDate(String sepFromDate) {
		this.sepFromDate = sepFromDate;
	}

	public String getSepToDate() {
		return sepToDate;
	}

	public void setSepToDate(String sepToDate) {
		this.sepToDate = sepToDate;
	}


	/**
	 * @return the bldGroup
	 */
	public String getBldGroup() {
		return bldGroup;
	}

	/**
	 * @param bldGroup
	 *            the bldGroup to set
	 */
	public void setBldGroup(String bldGroup) {
		this.bldGroup = bldGroup;
	}



	/**
	 * @return the birthDate
	 */
	public String getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate
	 *            the birthDate to set
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the bornFlag
	 */
	public String getBornFlag() {
		return bornFlag;
	}

	/**
	 * @param bornFlag
	 *            the bornFlag to set
	 */
	public void setBornFlag(String bornFlag) {
		this.bornFlag = bornFlag;
	}

	/**
	 * @return the gradeCode
	 */
	public String getGradeCode() {
		return gradeCode;
	}

	/**
	 * @param gradeCode
	 *            the gradeCode to set
	 */
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}

	/**
	 * @return the gradeName
	 */
	public String getGradeName() {
		return gradeName;
	}

	/**
	 * @param gradeName
	 *            the gradeName to set
	 */
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	/**
	 * @return the centerCode
	 */
	public String getCenterCode() {
		return centerCode;
	}

	/**
	 * @param centerCode
	 *            the centerCode to set
	 */
	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
	}

	/**
	 * @return the centerName
	 */
	public String getCenterName() {
		return centerName;
	}

	/**
	 * @param centerName
	 *            the centerName to set
	 */
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	/**
	 * @return the deptCode
	 */
	public String getDeptCode() {
		return deptCode;
	}

	/**
	 * @param deptCode
	 *            the deptCode to set
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @param deptName
	 *            the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * @return the desgCode
	 */
	public String getDesgCode() {
		return desgCode;
	}

	/**
	 * @param desgCode
	 *            the desgCode to set
	 */
	public void setDesgCode(String desgCode) {
		this.desgCode = desgCode;
	}

	/**
	 * @return the desgName
	 */
	public String getDesgName() {
		return desgName;
	}

	/**
	 * @param desgName
	 *            the desgName to set
	 */
	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}


	/**
	 * @return the divName
	 */
	public String getDivName() {
		return divName;
	}

	/**
	 * @param divName
	 *            the divName to set
	 */
	public void setDivName(String divName) {
		this.divName = divName;
	}

	/**
	 * @return the empID
	 */
	public String getEmpID() {
		return empID;
	}

	/**
	 * @param empID
	 *            the empID to set
	 */
	public void setEmpID(String empID) {
		this.empID = empID;
	}

	/**
	 * @return the empToken
	 */
	public String getEmpToken() {
		return empToken;
	}

	/**
	 * @param empToken
	 *            the empToken to set
	 */
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the leaveDate
	 */
	public String getLeaveDate() {
		return leaveDate;
	}

	/**
	 * @param leaveDate
	 *            the leaveDate to set
	 */
	public void setLeaveDate(String leaveDate) {
		this.leaveDate = leaveDate;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the payBillId
	 */
	public String getPayBillId() {
		return payBillId;
	}

	/**
	 * @param payBillId
	 *            the payBillId to set
	 */
	public void setPayBillId(String payBillId) {
		this.payBillId = payBillId;
	}

	/**
	 * @return the payBillName
	 */
	public String getPayBillName() {
		return payBillName;
	}

	/**
	 * @param payBillName
	 *            the payBillName to set
	 */
	public void setPayBillName(String payBillName) {
		this.payBillName = payBillName;
	}

	/**
	 * @return the rankCode
	 */
	public String getRankCode() {
		return rankCode;
	}

	/**
	 * @param rankCode
	 *            the rankCode to set
	 */
	public void setRankCode(String rankCode) {
		this.rankCode = rankCode;
	}

	/**
	 * @return the rankName
	 */
	public String getRankName() {
		return rankName;
	}

	/**
	 * @param rankName
	 *            the rankName to set
	 */
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

	/**
	 * @return the regularDate
	 */
	public String getRegularDate() {
		return regularDate;
	}

	/**
	 * @param regularDate
	 *            the regularDate to set
	 */
	public void setRegularDate(String regularDate) {
		this.regularDate = regularDate;
	}

	/**
	 * @return the reportingID
	 */
	public String getReportingID() {
		return reportingID;
	}

	/**
	 * @param reportingID
	 *            the reportingID to set
	 */
	public void setReportingID(String reportingID) {
		this.reportingID = reportingID;
	}

	/**
	 * @return the reportingTo
	 */
	public String getReportingTo() {
		return reportingTo;
	}

	/**
	 * @param reportingTo
	 *            the reportingTo to set
	 */
	public void setReportingTo(String reportingTo) {
		this.reportingTo = reportingTo;
	}

	/**
	 * @return the reportingToken
	 */
	public String getReportingToken() {
		return reportingToken;
	}

	/**
	 * @param reportingToken
	 *            the reportingToken to set
	 */
	public void setReportingToken(String reportingToken) {
		this.reportingToken = reportingToken;
	}

	/**
	 * @return the shiftCode
	 */
	public String getShiftCode() {
		return shiftCode;
	}

	/**
	 * @param shiftCode
	 *            the shiftCode to set
	 */
	public void setShiftCode(String shiftCode) {
		this.shiftCode = shiftCode;
	}

	/**
	 * @return the shiftType
	 */
	public String getShiftType() {
		return shiftType;
	}

	/**
	 * @param shiftType
	 *            the shiftType to set
	 */
	public void setShiftType(String shiftType) {
		this.shiftType = shiftType;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * @return the typeCode
	 */
	public String getTypeCode() {
		return typeCode;
	}

	/**
	 * @param typeCode
	 *            the typeCode to set
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName
	 *            the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return the uploadFileName
	 */
	public String getUploadFileName() {
		return uploadFileName;
	}

	/**
	 * @param uploadFileName
	 *            the uploadFileName to set
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/**
	 * @return the dOBfromDate
	 */
	public String getDOBfromDate() {
		return DOBfromDate;
	}

	/**
	 * @param bfromDate
	 *            the dOBfromDate to set
	 */
	public void setDOBfromDate(String bfromDate) {
		DOBfromDate = bfromDate;
	}

	/**
	 * @return the dOBtoDate
	 */
	public String getDOBToDate() {
		return DOBToDate;
	}

	/**
	 * @param btoDate
	 *            the dOBtoDate to set
	 */
	public void setDOBToDate(String DOBToDate) {
		this.DOBToDate = DOBToDate;
	}

	/**
	 * @return the leaveFromDate
	 */
	public String getLeaveFromDate() {
		return leaveFromDate;
	}

	/**
	 * @param leaveFromDate
	 *            the leaveFromDate to set
	 */
	public void setLeaveFromDate(String leaveFromDate) {
		this.leaveFromDate = leaveFromDate;
	}

	/**
	 * @return the leaveToDate
	 */
	public String getLeaveToDate() {
		return leaveToDate;
	}

	/**
	 * @param leaveToDate
	 *            the leaveToDate to set
	 */
	public void setLeaveToDate(String leaveToDate) {
		this.leaveToDate = leaveToDate;
	}

	/**
	 * @return the regularFromDate
	 */
	public String getRegularFromDate() {
		return regularFromDate;
	}

	/**
	 * @param regularFromDate
	 *            the regularFromDate to set
	 */
	public void setRegularFromDate(String regularFromDate) {
		this.regularFromDate = regularFromDate;
	}

	/**
	 * @return the regularToDate
	 */
	public String getRegularToDate() {
		return regularToDate;
	}

	/**
	 * @param regularToDate
	 *            the regularToDate to set
	 */
	public void setRegularToDate(String regularToDate) {
		this.regularToDate = regularToDate;
	}

	/**
	 * @return the castCategory
	 */
	public String getCastCategory() {
		return castCategory;
	}

	/**
	 * @param castCategory
	 *            the castCategory to set
	 */
	public void setCastCategory(String castCategory) {
		this.castCategory = castCategory;
	}

	/**
	 * @return the castCategoryCode
	 */
	public String getCastCategoryCode() {
		return castCategoryCode;
	}

	/**
	 * @param castCategoryCode
	 *            the castCategoryCode to set
	 */
	public void setCastCategoryCode(String castCategoryCode) {
		this.castCategoryCode = castCategoryCode;
	}

	/**
	 * @return the castCode
	 */
	public String getCastCode() {
		return castCode;
	}

	/**
	 * @param castCode
	 *            the castCode to set
	 */
	public void setCastCode(String castCode) {
		this.castCode = castCode;
	}

	/**
	 * @return the castName
	 */
	public String getCastName() {
		return castName;
	}

	/**
	 * @param castName
	 *            the castName to set
	 */
	public void setCastName(String castName) {
		this.castName = castName;
	}

	/**
	 * @return the maritalStatus
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * @param maritalStatus
	 *            the maritalStatus to set
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * @return the religion
	 */
	public String getReligion() {
		return religion;
	}

	/**
	 * @param religion
	 *            the religion to set
	 */
	public void setReligion(String religion) {
		this.religion = religion;
	}

	/**
	 * @return the religionCode
	 */
	public String getReligionCode() {
		return religionCode;
	}

	/**
	 * @param religionCode
	 *            the religionCode to set
	 */
	public void setReligionCode(String religionCode) {
		this.religionCode = religionCode;
	}

	/**
	 * @return the payScaleCode
	 */
	public String getPayScaleCode() {
		return payScaleCode;
	}

	/**
	 * @param payScaleCode
	 *            the payScaleCode to set
	 */
	public void setPayScaleCode(String payScaleCode) {
		this.payScaleCode = payScaleCode;
	}

	/**
	 * @return the payScaleName
	 */
	public String getPayScaleName() {
		return payScaleName;
	}

	/**
	 * @param payScaleName
	 *            the payScaleName to set
	 */
	public void setPayScaleName(String payScaleName) {
		this.payScaleName = payScaleName;
	}

	/**
	 * @return the empNameFlag
	 */
	public String getEmpNameFlag() {
		return empNameFlag;
	}

	/**
	 * @param empNameFlag
	 *            the empNameFlag to set
	 */
	public void setEmpNameFlag(String empNameFlag) {
		this.empNameFlag = empNameFlag;
	}

	/**
	 * @return the empTokenFlag
	 */
	public String getEmpTokenFlag() {
		return empTokenFlag;
	}

	/**
	 * @param empTokenFlag
	 *            the empTokenFlag to set
	 */
	public void setEmpTokenFlag(String empTokenFlag) {
		this.empTokenFlag = empTokenFlag;
	}

	/**
	 * @return the rankFlag
	 */
	public String getRankFlag() {
		return rankFlag;
	}

	/**
	 * @param rankFlag
	 *            the rankFlag to set
	 */
	public void setRankFlag(String rankFlag) {
		this.rankFlag = rankFlag;
	}

	/**
	 * @return the addressFlag
	 */
	public String getAddressFlag() {
		return addressFlag;
	}

	/**
	 * @param addressFlag
	 *            the addressFlag to set
	 */
	public void setAddressFlag(String addressFlag) {
		this.addressFlag = addressFlag;
	}

	/**
	 * @return the bloodGrpFlag
	 */
	public String getBloodGrpFlag() {
		return bloodGrpFlag;
	}

	/**
	 * @param bloodGrpFlag
	 *            the bloodGrpFlag to set
	 */
	public void setBloodGrpFlag(String bloodGrpFlag) {
		this.bloodGrpFlag = bloodGrpFlag;
	}

	/**
	 * @return the casteFlag
	 */
	public String getCasteFlag() {
		return casteFlag;
	}

	/**
	 * @param casteFlag
	 *            the casteFlag to set
	 */
	public void setCasteFlag(String casteFlag) {
		this.casteFlag = casteFlag;
	}

	/**
	 * @return the catagoryFlag
	 */
	public String getCatagoryFlag() {
		return catagoryFlag;
	}

	/**
	 * @param catagoryFlag
	 *            the catagoryFlag to set
	 */
	public void setCatagoryFlag(String catagoryFlag) {
		this.catagoryFlag = catagoryFlag;
	}

	/**
	 * @return the centerFlag
	 */
	public String getCenterFlag() {
		return centerFlag;
	}

	/**
	 * @param centerFlag
	 *            the centerFlag to set
	 */
	public void setCenterFlag(String centerFlag) {
		this.centerFlag = centerFlag;
	}

	/**
	 * @return the dateBirthFlag
	 */
	public String getDateBirthFlag() {
		return dateBirthFlag;
	}

	/**
	 * @param dateBirthFlag
	 *            the dateBirthFlag to set
	 */
	public void setDateBirthFlag(String dateBirthFlag) {
		this.dateBirthFlag = dateBirthFlag;
	}

	/**
	 * @return the dateRegFlag
	 */
	public String getDateRegFlag() {
		return dateRegFlag;
	}

	/**
	 * @param dateRegFlag
	 *            the dateRegFlag to set
	 */
	public void setDateRegFlag(String dateRegFlag) {
		this.dateRegFlag = dateRegFlag;
	}


	/**
	 * @return the deptFlag
	 */
	public String getDeptFlag() {
		return deptFlag;
	}

	/**
	 * @param deptFlag
	 *            the deptFlag to set
	 */
	public void setDeptFlag(String deptFlag) {
		this.deptFlag = deptFlag;
	}

	/**
	 * @return the desgFlag
	 */
	public String getDesgFlag() {
		return desgFlag;
	}

	/**
	 * @param desgFlag
	 *            the desgFlag to set
	 */
	public void setDesgFlag(String desgFlag) {
		this.desgFlag = desgFlag;
	}


	/**
	 * @return the marStatusFlag
	 */
	public String getMarStatusFlag() {
		return marStatusFlag;
	}

	/**
	 * @param marStatusFlag
	 *            the marStatusFlag to set
	 */
	public void setMarStatusFlag(String marStatusFlag) {
		this.marStatusFlag = marStatusFlag;
	}


	/**
	 * @return the religionFlag
	 */
	public String getReligionFlag() {
		return religionFlag;
	}

	/**
	 * @param religionFlag
	 *            the religionFlag to set
	 */
	public void setReligionFlag(String religionFlag) {
		this.religionFlag = religionFlag;
	}


	/**
	 * @return the payScaleFlag
	 */
	public String getPayScaleFlag() {
		return payScaleFlag;
	}

	/**
	 * @param payScaleFlag
	 *            the payScaleFlag to set
	 */
	public void setPayScaleFlag(String payScaleFlag) {
		this.payScaleFlag = payScaleFlag;
	}

	/**
	 * @return the genderFlag
	 */
	public String getGenderFlag() {
		return genderFlag;
	}

	/**
	 * @param genderFlag
	 *            the genderFlag to set
	 */
	public void setGenderFlag(String genderFlag) {
		this.genderFlag = genderFlag;
	}

	/**
	 * @return the dateOfRetFlag
	 */
	public String getDateOfRetFlag() {
		return dateOfRetFlag;
	}

	/**
	 * @param dateOfRetFlag
	 *            the dateOfRetFlag to set
	 */
	public void setDateOfRetFlag(String dateOfRetFlag) {
		this.dateOfRetFlag = dateOfRetFlag;
	}

	/**
	 * @return the retFromDate
	 */
	public String getRetFromDate() {
		return retFromDate;
	}

	/**
	 * @param retFromDate
	 *            the retFromDate to set
	 */
	public void setRetFromDate(String retFromDate) {
		this.retFromDate = retFromDate;
	}

	/**
	 * @return the retToDate
	 */
	public String getRetToDate() {
		return retToDate;
	}

	/**
	 * @param retToDate
	 *            the retToDate to set
	 */
	public void setRetToDate(String retToDate) {
		this.retToDate = retToDate;
	}

	/**
	 * @return the bDayMonth
	 */
	public String getBDayMonth() {
		return bDayMonth;
	}

	/**
	 * @param dayMonth
	 *            the bDayMonth to set
	 */
	public void setBDayMonth(String dayMonth) {
		bDayMonth = dayMonth;
	}

	public String getDivCode() {
		return divCode;
	}

	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}

	public String getDivFlag() {
		return divFlag;
	}

	public void setDivFlag(String divFlag) {
		this.divFlag = divFlag;
	}


	public String getSalAccFlag() {
		return salAccFlag;
	}

	public void setSalAccFlag(String salAccFlag) {
		this.salAccFlag = salAccFlag;
	}

	
	public String getYearexperienceFlag() {
		return yearexperienceFlag;
	}

	public void setYearexperienceFlag(String yearexperienceFlag) {
		this.yearexperienceFlag = yearexperienceFlag;
	}

	public String getOverexperienceFlag() {
		return overexperienceFlag;
	}

	public void setOverexperienceFlag(String overexperienceFlag) {
		this.overexperienceFlag = overexperienceFlag;
	}

	public String getCtcFlag() {
		return ctcFlag;
	}

	public void setCtcFlag(String ctcFlag) {
		this.ctcFlag = ctcFlag;
	}

	public String getAgeFlag() {
		return ageFlag;
	}

	public void setAgeFlag(String ageFlag) {
		this.ageFlag = ageFlag;
	}



	public void setMap(TreeMap map) {
		this.map = map;
	}

	public TreeMap getMap() {
		return map;
	}

	public String getGradeFlage() {
		return gradeFlage;
	}

	public void setGradeFlage(String gradeFlage) {
		this.gradeFlage = gradeFlage;
	}

	/**
	 * @return the reportType
	 */
	public String getReportType() {
		return reportType;
	}

	/**
	 * @param reportType the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	/**
	 * @return the ageFrom
	 */
	public String getAgeFrom() {
		return ageFrom;
	}

	/**
	 * @param ageFrom the ageFrom to set
	 */
	public void setAgeFrom(String ageFrom) {
		this.ageFrom = ageFrom;
	}

	/**
	 * @return the grossSalFrom
	 */
	public String getGrossSalFrom() {
		return grossSalFrom;
	}

	/**
	 * @param grossSalFrom the grossSalFrom to set
	 */
	public void setGrossSalFrom(String grossSalFrom) {
		this.grossSalFrom = grossSalFrom;
	}

	/**
	 * @return the ctcFrom
	 */
	public String getCtcFrom() {
		return ctcFrom;
	}

	/**
	 * @param ctcFrom the ctcFrom to set
	 */
	public void setCtcFrom(String ctcFrom) {
		this.ctcFrom = ctcFrom;
	}

	/**
	 * @return the ageTo
	 */
	public String getAgeTo() {
		return ageTo;
	}

	/**
	 * @param ageTo the ageTo to set
	 */
	public void setAgeTo(String ageTo) {
		this.ageTo = ageTo;
	}

	/**
	 * @return the grossSalTo
	 */
	public String getGrossSalTo() {
		return grossSalTo;
	}

	/**
	 * @param grossSalTo the grossSalTo to set
	 */
	public void setGrossSalTo(String grossSalTo) {
		this.grossSalTo = grossSalTo;
	}

	/**
	 * @return the ctcTo
	 */
	public String getCtcTo() {
		return ctcTo;
	}

	public String getDateFormatSelect() {
		return dateFormatSelect;
	}

	public void setDateFormatSelect(String dateFormatSelect) {
		this.dateFormatSelect = dateFormatSelect;
	}

	/**
	 * @param ctcTo the ctcTo to set
	 */
	public void setCtcTo(String ctcTo) {
		this.ctcTo = ctcTo;
	}



	/**
	 * @return the sortByAsc
	 */
	public String getSortByAsc() {
		return sortByAsc;
	}

	/**
	 * @param sortByAsc the sortByAsc to set
	 */
	public void setSortByAsc(String sortByAsc) {
		this.sortByAsc = sortByAsc;
	}

	/**
	 * @return the sortByDsc
	 */
	public String getSortByDsc() {
		return sortByDsc;
	}

	/**
	 * @param sortByDsc the sortByDsc to set
	 */
	public void setSortByDsc(String sortByDsc) {
		this.sortByDsc = sortByDsc;
	}

	/**
	 * @return the thenByOrder1Asc
	 */
	public String getThenByOrder1Asc() {
		return thenByOrder1Asc;
	}

	/**
	 * @param thenByOrder1Asc the thenByOrder1Asc to set
	 */
	public void setThenByOrder1Asc(String thenByOrder1Asc) {
		this.thenByOrder1Asc = thenByOrder1Asc;
	}

	/**
	 * @return the thenByOrder1Dsc
	 */
	public String getThenByOrder1Dsc() {
		return thenByOrder1Dsc;
	}

	/**
	 * @param thenByOrder1Dsc the thenByOrder1Dsc to set
	 */
	public void setThenByOrder1Dsc(String thenByOrder1Dsc) {
		this.thenByOrder1Dsc = thenByOrder1Dsc;
	}

	/**
	 * @return the thenByOrder2Asc
	 */
	public String getThenByOrder2Asc() {
		return thenByOrder2Asc;
	}

	/**
	 * @param thenByOrder2Asc the thenByOrder2Asc to set
	 */
	public void setThenByOrder2Asc(String thenByOrder2Asc) {
		this.thenByOrder2Asc = thenByOrder2Asc;
	}

	/**
	 * @return the thenByOrder2Dsc
	 */
	public String getThenByOrder2Dsc() {
		return thenByOrder2Dsc;
	}

	/**
	 * @param thenByOrder2Dsc the thenByOrder2Dsc to set
	 */
	public void setThenByOrder2Dsc(String thenByOrder2Dsc) {
		this.thenByOrder2Dsc = thenByOrder2Dsc;
	}

	/**
	 * @return the hidReportView
	 */
	public String getHidReportView() {
		return hidReportView;
	}

	/**
	 * @param hidReportView the hidReportView to set
	 */
	public void setHidReportView(String hidReportView) {
		this.hidReportView = hidReportView;
	}

	/**
	 * @return the hidReportRadio
	 */
	public String getHidReportRadio() {
		return hidReportRadio;
	}

	/**
	 * @param hidReportRadio the hidReportRadio to set
	 */
	public void setHidReportRadio(String hidReportRadio) {
		this.hidReportRadio = hidReportRadio;
	}

	/**
	 * @return the exportAll
	 */
	public String getExportAll() {
		return exportAll;
	}

	/**
	 * @param exportAll the exportAll to set
	 */
	public void setExportAll(String exportAll) {
		this.exportAll = exportAll;
	}

	/**
	 * @return the noData
	 */
	public String getNoData() {
		return noData;
	}

	/**
	 * @param noData the noData to set
	 */
	public void setNoData(String noData) {
		this.noData = noData;
	}

	/**
	 * @return the settingName
	 */
	public String getSettingName() {
		return settingName;
	}

	/**
	 * @param settingName the settingName to set
	 */
	public void setSettingName(String settingName) {
		this.settingName = settingName;
	}

	/**
	 * @return the reportId
	 */
	public String getReportId() {
		return reportId;
	}

	/**
	 * @param reportId the reportId to set
	 */
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getBackFlag() {
		return backFlag;
	}

	public void setBackFlag(String backFlag) {
		this.backFlag = backFlag;
	}

	/**
	 * @return the empGradeFlag
	 */
	public String getEmpGradeFlag() {
		return empGradeFlag;
	}

	/**
	 * @param empGradeFlag the empGradeFlag to set
	 */
	public void setEmpGradeFlag(String empGradeFlag) {
		this.empGradeFlag = empGradeFlag;
	}

	/**
	 * @return the hiddenSortBy
	 */
	public String getHiddenSortBy() {
		return hiddenSortBy;
	}

	/**
	 * @param hiddenSortBy the hiddenSortBy to set
	 */
	public void setHiddenSortBy(String hiddenSortBy) {
		this.hiddenSortBy = hiddenSortBy;
	}

	/**
	 * @return the hiddenThenBy1
	 */
	public String getHiddenThenBy1() {
		return hiddenThenBy1;
	}

	/**
	 * @param hiddenThenBy1 the hiddenThenBy1 to set
	 */
	public void setHiddenThenBy1(String hiddenThenBy1) {
		this.hiddenThenBy1 = hiddenThenBy1;
	}

	/**
	 * @return the hiddenThenBy2
	 */
	public String getHiddenThenBy2() {
		return hiddenThenBy2;
	}

	/**
	 * @param hiddenThenBy2 the hiddenThenBy2 to set
	 */
	public void setHiddenThenBy2(String hiddenThenBy2) {
		this.hiddenThenBy2 = hiddenThenBy2;
	}

	public String getReportTypeStr() {
		return reportTypeStr;
	}

	public void setReportTypeStr(String reportTypeStr) {
		this.reportTypeStr = reportTypeStr;
	}

	public String getRoleFlag() {
		return roleFlag;
	}

	public void setRoleFlag(String roleFlag) {
		this.roleFlag = roleFlag;
	}

	public String getPfNoFlag() {
		return pfNoFlag;
	}

	public void setPfNoFlag(String pfNoFlag) {
		this.pfNoFlag = pfNoFlag;
	}

	public String getEsicNoFlag() {
		return esicNoFlag;
	}

	public void setEsicNoFlag(String esicNoFlag) {
		this.esicNoFlag = esicNoFlag;
	}

	public String getPanNoFlag() {
		return panNoFlag;
	}

	public void setPanNoFlag(String panNoFlag) {
		this.panNoFlag = panNoFlag;
	}

	public String getMarriageDateFlag() {
		return marriageDateFlag;
	}

	public void setMarriageDateFlag(String marriageDateFlag) {
		this.marriageDateFlag = marriageDateFlag;
	}

	public String getTradeFlag() {
		return tradeFlag;
	}

	public void setTradeFlag(String tradeFlag) {
		this.tradeFlag = tradeFlag;
	}

	 
}
