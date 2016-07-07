package org.paradyne.bean.payroll.bonus;

import java.util.List;

import org.paradyne.lib.BeanBase;

/**Created on 17th Jan 2012.
 * @author aa1380
 */
public class BonusAllowance extends BeanBase {
	
	//List Page Variales -- BEGINS
	/** note. * */
	private String note = "";
	/** status. * */
	private String status = "Fail";
	/** displayNoteFlag. * */
	private boolean displayNoteFlag;
	/** myPage. * */
	private String myPage;
	
	/** initialRecordListFlag. * */
	private boolean initialRecordListFlag;
	
	/** initialRecordListIterator. * */
	private List<BonusAllowance> initialRecordListIterator;
	
	/** initialEmptyListFlag. * */
	private boolean initialEmptyListFlag;
	
	/** bonusAllowanceIDItr. * */
	private String bonusAllowanceIDItr;
	
	/** bonusAllowancePeriodItr. * */
	private String bonusAllowancePeriodItr;
	
	/** processDateItr. * */
	private String processDateItr;
	
	/** divisionIDItr. * */
	private String divisionIDItr = "";
	
	/** divisionNameItr. * */
	private String divisionNameItr;
	
	/** payBillIDItr. * */
	private String payBillIDItr = "";;
	
	/** payBillNameItr. * */
	private String payBillNameItr;
	
	/** totalBonusAllowanceItr. * */
	private String totalBonusAllowanceItr;
	
	/** headCountItr. * */
	private String headCountItr;
	
	/** paidInSalaryMonthItr. * */
	private String paidInSalaryMonthItr;
	
	/** lockUnlockItr. * */
	private String lockUnlockItr;

	//List Page Variables -- ENDS
	
	
	//Details Page Variables -- BEGINS
	/** processedRecordFlag. * */
	private boolean processedRecordFlag;
	/** dataPath. * */
	private String dataPath = "";
	/** divisionID. * */
	private String divisionID = "";
	/** divisionName. * */
	private String divisionName = "";
	/** branchID. * */
	private String branchID = "";;
	/** branchName. * */
	private String branchName = "";
	/** paybillID. * */
	private String paybillID = "";;
	/** paybillName. * */
	private String paybillName = "";
	/** departmentID. * */
	private String departmentID = "";;
	/** departmentName. * */
	private String departmentName = "";
	/** filterEmpId. * */
	private String filterEmpId = "";
	/** filterEmpToken. * */
	private String filterEmpToken = "";
	/** filterEmpName. * */
	private String filterEmpName = "";
	
	/** bonusAllowanceID. * */
	private String bonusAllowanceID;
	/** fromMonth. * */
	private String fromMonth;
	/** fromYear. * */
	private String fromYear;
	/** toMonth. * */
	private String toMonth;
	/** toYear. * */
	private String toYear;
	/** processedFromMonth. * */
	private String processedFromMonth = "";
	/** processedToMonth. * */
	private String processedToMonth = "";
	/** payInSalaryCheckBox. * */
	private String payInSalaryCheckBox;
	/** salaryMonth. * */
	private String salaryMonth;
	/** salaryYear. * */
	private String salaryYear;
	/** payInComponentID. * */
	private String payInComponentID;
	/** payInComponentAbbreviation. * */
	private String payInComponentAbbreviation;
	/** payInComponentName. * */
	private String payInComponentName;
	/** deductIncomeTaxCheckBox. * */
	private String deductIncomeTaxCheckBox;
	/** systemCalculatedBonusCheckBox. * */
	private String systemCalculatedBonusCheckBox;
	/** manuallyCalculatedBonusCheckBox. * */
	private String manuallyCalculatedBonusCheckBox;
	/** calculatePaidDaysCheckBox. * */
	private String calculatePaidDaysCheckBox;
	/** calCulatedBonusComponents. * */
	private String calCulatedBonusComponents;
	/** slabwiseMethodCheckbox. * */
	private String slabwiseMethodCheckbox;
	/** individualRatingsMethodCheckbox. * */
	private String individualRatingsMethodCheckbox;
	/** flatRateMethodCheckbox. * */
	private String flatRateMethodCheckbox;
	
	/** fileNameForIndividualRatings. * */
	private String fileNameForIndividualRatings;
	/** fileNameIndividualRatingsUploaded. * */
	private String fileNameIndividualRatingsUploaded;
	/** tempFileNameIndividualRatingsUploaded. * */
	private String tempFileNameIndividualRatingsUploaded;
	/** viewAlreadyUploadedRatingsDetailsFlag. *	 */
	private boolean viewAlreadyUploadedRatingsDetailsFlag; 
	
	/** flatRateBonusAllowanceCalculation. * */
	private String flatRateBonusAllowanceCalculation;
	/** flatRateMaximumBonusAllowance. * */
	private String flatRateMaximumBonusAllowance;
	/** flatRateMinimumBonusAllowance. * */
	private String flatRateMinimumBonusAllowance;
	
	/** fileNameForManuallyCalculatedBonusAllowance. * */
	private String fileNameForManuallyCalculatedBonusAllowance;
	/** fileNameManuallyUploadedBonusAllowance. * */
	private String fileNameManuallyUploadedBonusAllowance;
	/** tempFileNameManuallyUploadedBonus. * */
	private String tempFileNameManuallyUploadedBonus;
	/** viewAlreadyUploadedBonusFlag. * */
	private boolean viewAlreadyUploadedBonusFlag;
	/** bonusAllowanceStatus. * */
	private String bonusAllowanceStatus;
	
	//Slab Wise Iterator Variables -- BEGINS
	/** slabDetailsIterator. * */
	private List<BonusAllowance> slabDetailsIterator;
	/** slabItrID. * */
	private String slabItrID;
	/** slabItrFromAmount. * */
	private String slabItrFromAmount;
	/** slabItrToAmount. * */
	private String slabItrToAmount;
	/** slabItrPercentage. * */
	private String slabItrPercentage;
	/** slabItrMin. * */
	private String slabItrMin;
	/** slabItrMax. * */
	private String slabItrMax;
	//Slab Wise Iterator Variables -- BEGINS
	
	//Details Page Variables -- ENDS
	
	//Employee Editing Records Page Variable -- BEGINS
	/** employeeIDEditRecord. * */
	private String employeeIDEditRecord;
	/** employeeTokenEditRecord. * */
	private String employeeTokenEditRecord;
	/** employeeNameEditRecord. * */
	private String employeeNameEditRecord;
	/** bonusAllowanceAmountEditRecord. * */
	private String bonusAllowanceAmountEditRecord;
	/** tdsEditRecord. * */
	private String tdsEditRecord;
	/** unlockStatusFlag. * */
	private boolean unlockStatusFlag;
	
	
	/** employeeListEditRecordIterator. * */
	private List<BonusAllowance> employeeListEditRecordIterator;
	/** employeeListEditRecordFlag. * */
	private boolean employeeListEditRecordFlag;
	/** empTokenEditRecordItr. * */
	private String empTokenEditRecordItr;
	/** empIDEditRecordItr. * */
	private String empIDEditRecordItr;
	/** empNameEditRecordItr. * */
	private String empNameEditRecordItr;
	/** bonusAllowanceAmountEditRecordItr. * */
	private String bonusAllowanceAmountEditRecordItr;
	/** taxEditRecordItr. * */
	private String taxEditRecordItr;
	/** totalAmountEditRecordItr. * */
	private String totalAmountEditRecordItr;
	//Employee Editing Records Page Variable -- ENDS
	/** bonuseStatus. * */
	private String bonuseStatus = "";
	
	/** getBonuseStatus. 
	 * @return String
	 * * */
	public String getBonuseStatus() {
		return this.bonuseStatus;
	}
	/** setBonuseStatus. 
	 * @param bonuseStatus : bonuseStatus
	*/
	public void setBonuseStatus(final String bonuseStatus) {
		this.bonuseStatus = bonuseStatus;
	}

	/** getMyPage. 
	 * @return String
	 * * */
	public String getMyPage() {
		return this.myPage;
	}
	
	/** setMyPage. 
	 * @param myPage : myPage
	*/
	public void setMyPage(final String myPage) {
		this.myPage = myPage;
	}
	
	/** isInitialRecordListFlag. 
	 * @return boolean
	 * * */
	public boolean isInitialRecordListFlag() {
		return this.initialRecordListFlag;
	}

	/** setInitialRecordListFlag. 
	 * @param initialRecordListFlag : initialRecordListFlag
	*/
	public void setInitialRecordListFlag(final boolean initialRecordListFlag) {
		this.initialRecordListFlag = initialRecordListFlag;
	}

	/** getInitialRecordListIterator. 
	 * @return ArrayList
	 * * */
	public List<BonusAllowance> getInitialRecordListIterator() {
		return this.initialRecordListIterator;
	}

	/** setInitialRecordListIterator. 
	 * @param initialRecordListIterator : initialRecordListIterator
	*/
	public void setInitialRecordListIterator(
			final List<BonusAllowance> initialRecordListIterator) {
		this.initialRecordListIterator = initialRecordListIterator;
	}

	/** isInitialEmptyListFlag. 
	 * @return boolean
	 * * */
	public boolean isInitialEmptyListFlag() {
		return this.initialEmptyListFlag;
	}

	/** setInitialEmptyListFlag. 
	 * @param initialEmptyListFlag : initialEmptyListFlag
	*/
	public void setInitialEmptyListFlag(final boolean initialEmptyListFlag) {
		this.initialEmptyListFlag = initialEmptyListFlag;
	}

	/** getInitialRecordListIterator. 
	 * @return ArrayList
	 * * */
	public String getBonusAllowanceIDItr() {
		return this.bonusAllowanceIDItr;
	}
	/** setBonusAllowanceIDItr. 
	 * @param bonusAllowanceIDItr : bonusAllowanceIDItr
	*/
	public void setBonusAllowanceIDItr(final String bonusAllowanceIDItr) {
		this.bonusAllowanceIDItr = bonusAllowanceIDItr;
	}

	/** getInitialRecordListIterator. 
	 * @return ArrayList
	 * * */
	public String getBonusAllowancePeriodItr() {
		return this.bonusAllowancePeriodItr;
	}
	/** setBonusAllowancePeriodItr. 
	 * @param bonusAllowancePeriodItr : bonusAllowancePeriodItr
	*/
	public void setBonusAllowancePeriodItr(final String bonusAllowancePeriodItr) {
		this.bonusAllowancePeriodItr = bonusAllowancePeriodItr;
	}

	/** getProcessDateItr. 
	 * @return String
	 * * */
	public String getProcessDateItr() {
		return this.processDateItr;
	}
	/** setProcessDateItr. 
	 * @param processDateItr : processDateItr
	*/
	public void setProcessDateItr(final String processDateItr) {
		this.processDateItr = processDateItr;
	}

	/** getDivisionIDItr. 
	 * @return String
	 * * */
	public String getDivisionIDItr() {
		return this.divisionIDItr;
	}
	/** setDivisionIDItr. 
	 * @param divisionIDItr : divisionIDItr
	*/
	public void setDivisionIDItr(final String divisionIDItr) {
		this.divisionIDItr = divisionIDItr;
	}

	/** getDivisionNameItr. 
	 * @return String
	 * * */
	public String getDivisionNameItr() {
		return this.divisionNameItr;
	}
	/** setDivisionNameItr. 
	 * @param divisionNameItr : divisionNameItr
	*/
	public void setDivisionNameItr(final String divisionNameItr) {
		this.divisionNameItr = divisionNameItr;
	}

	/** getPayBillIDItr. 
	 * @return String
	 * * */
	public String getPayBillIDItr() {
		return this.payBillIDItr;
	}
	/** setPayBillIDItr. 
	 * @param payBillIDItr : payBillIDItr
	*/
	public void setPayBillIDItr(final String payBillIDItr) {
		this.payBillIDItr = payBillIDItr;
	}
	
	/** getPayBillNameItr. 
	 * @return String
	 * * */
	public String getPayBillNameItr() {
		return this.payBillNameItr;
	}
	/** setPayBillNameItr. 
	 * @param payBillNameItr : payBillNameItr
	*/
	public void setPayBillNameItr(final String payBillNameItr) {
		this.payBillNameItr = payBillNameItr;
	}

	/** getTotalBonusAllowanceItr. 
	 * @return String
	 * * */
	public String getTotalBonusAllowanceItr() {
		return this.totalBonusAllowanceItr;
	}
	/** setTotalBonusAllowanceItr. 
	 * @param totalBonusAllowanceItr : totalBonusAllowanceItr
	*/
	public void setTotalBonusAllowanceItr(final String totalBonusAllowanceItr) {
		this.totalBonusAllowanceItr = totalBonusAllowanceItr;
	}

	/** getHeadCountItr. 
	 * @return String
	 * * */
	public String getHeadCountItr() {
		return this.headCountItr;
	}
	/** setHeadCountItr. 
	 * @param headCountItr : headCountItr
	*/
	public void setHeadCountItr(final String headCountItr) {
		this.headCountItr = headCountItr;
	}

	/** getPaidInSalaryMonthItr. 
	 * @return String
	 * * */
	public String getPaidInSalaryMonthItr() {
		return this.paidInSalaryMonthItr;
	}
	/** setPaidInSalaryMonthItr. 
	 * @param paidInSalaryMonthItr : paidInSalaryMonthItr
	*/
	public void setPaidInSalaryMonthItr(final String paidInSalaryMonthItr) {
		this.paidInSalaryMonthItr = paidInSalaryMonthItr;
	}

	/** getLockUnlockItr. 
	 * @return String
	 * * */
	public String getLockUnlockItr() {
		return this.lockUnlockItr;
	}
	/** setLockUnlockItr. 
	 * @param lockUnlockItr : lockUnlockItr
	*/
	public void setLockUnlockItr(final String lockUnlockItr) {
		this.lockUnlockItr = lockUnlockItr;
	}

	/** getDataPath. 
	 * @return String
	 * * */
	public String getDataPath() {
		return this.dataPath;
	}
	/** setDataPath. 
	 * @param dataPath : dataPath
	*/
	public void setDataPath(final String dataPath) {
		this.dataPath = dataPath;
	}

	/** getDivisionID. 
	 * @return String
	 * * */
	public String getDivisionID() {
		return this.divisionID;
	}
	/** setDivisionID. 
	 * @param divisionID : divisionID
	*/
	public void setDivisionID(final String divisionID) {
		this.divisionID = divisionID;
	}

	/** getDivisionName. 
	 * @return String
	 * * */
	public String getDivisionName() {
		return this.divisionName;
	}
	/** setDivisionName. 
	 * @param divisionName : divisionName
	*/
	public void setDivisionName(final String divisionName) {
		this.divisionName = divisionName;
	}

	/** getBranchID. 
	 * @return String
	 * * */
	public String getBranchID() {
		return this.branchID;
	}
	/** setBranchID. 
	 * @param branchID : branchID
	*/
	public void setBranchID(final String branchID) {
		this.branchID = branchID;
	}

	/** getBranchName. 
	 * @return String
	 * * */
	public String getBranchName() {
		return this.branchName;
	}
	/** setBranchName. 
	 * @param branchName : branchName
	*/
	public void setBranchName(final String branchName) {
		this.branchName = branchName;
	}

	/** getPaybillID. 
	 * @return String
	 * * */
	public String getPaybillID() {
		return this.paybillID;
	}
	/** setPaybillID. 
	 * @param paybillID : paybillID
	*/
	public void setPaybillID(final String paybillID) {
		this.paybillID = paybillID;
	}

	/** getPaybillName. 
	 * @return String
	 * * */
	public String getPaybillName() {
		return this.paybillName;
	}
	/** setPaybillName. 
	 * @param paybillName : paybillName
	*/
	public void setPaybillName(final String paybillName) {
		this.paybillName = paybillName;
	}

	/** getDepartmentID. 
	 * @return String
	 * * */
	public String getDepartmentID() {
		return this.departmentID;
	}
	/** setDepartmentID. 
	 * @param departmentID : departmentID
	*/
	public void setDepartmentID(final String departmentID) {
		this.departmentID = departmentID;
	}

	/** getDepartmentName. 
	 * @return String
	 * * */
	public String getDepartmentName() {
		return this.departmentName;
	}
	/** setDepartmentName. 
	 * @param departmentName : departmentName
	*/
	public void setDepartmentName(final String departmentName) {
		this.departmentName = departmentName;
	}

	/** getBonusAllowanceID. 
	 * @return String
	 * * */
	public String getBonusAllowanceID() {
		return this.bonusAllowanceID;
	}
	/** setBonusAllowanceID. 
	 * @param bonusAllowanceID : bonusAllowanceID
	*/
	public void setBonusAllowanceID(final String bonusAllowanceID) {
		this.bonusAllowanceID = bonusAllowanceID;
	}

	/** getFromMonth. 
	 * @return String
	 * * */
	public String getFromMonth() {
		return this.fromMonth;
	}
	/** setFromMonth. 
	 * @param fromMonth : fromMonth
	*/
	public void setFromMonth(final String fromMonth) {
		this.fromMonth = fromMonth;
	}

	/** getFromYear. 
	 * @return String
	 * * */
	public String getFromYear() {
		return this.fromYear;
	}
	/** setFromYear. 
	 * @param fromYear : fromYear
	*/
	public void setFromYear(final String fromYear) {
		this.fromYear = fromYear;
	}

	/** getToMonth. 
	 * @return String
	 * * */
	public String getToMonth() {
		return this.toMonth;
	}
	/** setToMonth. 
	 * @param toMonth : toMonth
	*/
	public void setToMonth(final String toMonth) {
		this.toMonth = toMonth;
	}

	/** getToYear. 
	 * @return String
	 * * */
	public String getToYear() {
		return this.toYear;
	}
	/** setToYear. 
	 * @param toYear : toYear
	*/
	public void setToYear(final String toYear) {
		this.toYear = toYear;
	}
	
	/** getPayInSalaryCheckBox. 
	 * @return String
	 * * */
	public String getPayInSalaryCheckBox() {
		return this.payInSalaryCheckBox;
	}
	/** setPayInSalaryCheckBox. 
	 * @param payInSalaryCheckBox : payInSalaryCheckBox
	*/
	public void setPayInSalaryCheckBox(final String payInSalaryCheckBox) {
		this.payInSalaryCheckBox = payInSalaryCheckBox;
	}

	/** getSalaryMonth. 
	 * @return String
	 * * */
	public String getSalaryMonth() {
		return this.salaryMonth;
	}
	/** setSalaryMonth. 
	 * @param salaryMonth : salaryMonth
	*/
	public void setSalaryMonth(final String salaryMonth) {
		this.salaryMonth = salaryMonth;
	}

	/** getSalaryYear. 
	 * @return String
	 * * */
	public String getSalaryYear() {
		return this.salaryYear;
	}
	/** setSalaryYear. 
	 * @param salaryYear : salaryYear
	*/
	public void setSalaryYear(final String salaryYear) {
		this.salaryYear = salaryYear;
	}

	/** getPayInComponentID. 
	 * @return String
	 * * */
	public String getPayInComponentID() {
		return this.payInComponentID;
	}
	/** setPayInComponentID. 
	 * @param payInComponentID : payInComponentID
	*/
	public void setPayInComponentID(final String payInComponentID) {
		this.payInComponentID = payInComponentID;
	}

	/** getPayInComponentAbbreviation. 
	 * @return String
	 * * */
	public String getPayInComponentAbbreviation() {
		return this.payInComponentAbbreviation;
	}
	/** setPayInComponentAbbreviation. 
	 * @param payInComponentAbbreviation : payInComponentAbbreviation
	*/
	public void setPayInComponentAbbreviation(final String payInComponentAbbreviation) {
		this.payInComponentAbbreviation = payInComponentAbbreviation;
	}

	/** getPayInComponentName. 
	 * @return String
	 * * */
	public String getPayInComponentName() {
		return this.payInComponentName;
	}
	/** setPayInComponentName. 
	 * @param payInComponentName : payInComponentName
	*/
	public void setPayInComponentName(final String payInComponentName) {
		this.payInComponentName = payInComponentName;
	}

	/** getDeductIncomeTaxCheckBox. 
	 * @return String
	 * * */
	public String getDeductIncomeTaxCheckBox() {
		return this.deductIncomeTaxCheckBox;
	}
	/** setDeductIncomeTaxCheckBox. 
	 * @param deductIncomeTaxCheckBox : deductIncomeTaxCheckBox
	*/
	public void setDeductIncomeTaxCheckBox(final String deductIncomeTaxCheckBox) {
		this.deductIncomeTaxCheckBox = deductIncomeTaxCheckBox;
	}

	/** getSystemCalculatedBonusCheckBox. 
	 * @return String
	 * * */
	public String getSystemCalculatedBonusCheckBox() {
		return this.systemCalculatedBonusCheckBox;
	}
	/** setSystemCalculatedBonusCheckBox. 
	 * @param systemCalculatedBonusCheckBox : systemCalculatedBonusCheckBox
	*/
	public void setSystemCalculatedBonusCheckBox(
			final String systemCalculatedBonusCheckBox) {
		this.systemCalculatedBonusCheckBox = systemCalculatedBonusCheckBox;
	}

	/** getManuallyCalculatedBonusCheckBox. 
	 * @return String
	 * * */
	public String getManuallyCalculatedBonusCheckBox() {
		return this.manuallyCalculatedBonusCheckBox;
	}
	/** setManuallyCalculatedBonusCheckBox. 
	 * @param manuallyCalculatedBonusCheckBox : manuallyCalculatedBonusCheckBox
	*/
	public void setManuallyCalculatedBonusCheckBox(
			final String manuallyCalculatedBonusCheckBox) {
		this.manuallyCalculatedBonusCheckBox = manuallyCalculatedBonusCheckBox;
	}

	/** getCalculatePaidDaysCheckBox. 
	 * @return String
	 * * */
	public String getCalculatePaidDaysCheckBox() {
		return this.calculatePaidDaysCheckBox;
	}
	/** setCalculatePaidDaysCheckBox. 
	 * @param calculatePaidDaysCheckBox : calculatePaidDaysCheckBox
	*/
	public void setCalculatePaidDaysCheckBox(final String calculatePaidDaysCheckBox) {
		this.calculatePaidDaysCheckBox = calculatePaidDaysCheckBox;
	}

	/** getCalCulatedBonusComponents. 
	 * @return String
	 * * */
	public String getCalCulatedBonusComponents() {
		return this.calCulatedBonusComponents;
	}
	/** setCalCulatedBonusComponents. 
	 * @param calCulatedBonusComponents : calCulatedBonusComponents
	*/
	public void setCalCulatedBonusComponents(final String calCulatedBonusComponents) {
		this.calCulatedBonusComponents = calCulatedBonusComponents;
	}

	/** getSlabwiseMethodCheckbox. 
	 * @return String
	 * * */
	public String getSlabwiseMethodCheckbox() {
		return this.slabwiseMethodCheckbox;
	}
	/** setSlabwiseMethodCheckbox. 
	 * @param slabwiseMethodCheckbox : slabwiseMethodCheckbox
	*/
	public void setSlabwiseMethodCheckbox(final String slabwiseMethodCheckbox) {
		this.slabwiseMethodCheckbox = slabwiseMethodCheckbox;
	}

	/** getCalCulatedBonusComponents. 
	 * @return String
	 * * */
	public String getIndividualRatingsMethodCheckbox() {
		return this.individualRatingsMethodCheckbox;
	}
	/** setIndividualRatingsMethodCheckbox. 
	 * @param individualRatingsMethodCheckbox : individualRatingsMethodCheckbox
	*/
	public void setIndividualRatingsMethodCheckbox(
			final String individualRatingsMethodCheckbox) {
		this.individualRatingsMethodCheckbox = individualRatingsMethodCheckbox;
	}

	/** getCalCulatedBonusComponents. 
	 * @return String
	 * * */
	public String getFlatRateMethodCheckbox() {
		return this.flatRateMethodCheckbox;
	}
	/** setFlatRateMethodCheckbox. 
	 * @param flatRateMethodCheckbox : flatRateMethodCheckbox
	*/
	public void setFlatRateMethodCheckbox(final String flatRateMethodCheckbox) {
		this.flatRateMethodCheckbox = flatRateMethodCheckbox;
	}

	/** getFileNameForIndividualRatings. 
	 * @return String
	 * * */
	public String getFileNameForIndividualRatings() {
		return this.fileNameForIndividualRatings;
	}
	/** setFileNameForIndividualRatings. 
	 * @param fileNameForIndividualRatings : fileNameForIndividualRatings
	*/
	public void setFileNameForIndividualRatings(final String fileNameForIndividualRatings) {
		this.fileNameForIndividualRatings = fileNameForIndividualRatings;
	}

	/** getCalCulatedBonusComponents. 
	 * @return String
	 * * */
	public String getFlatRateBonusAllowanceCalculation() {
		return this.flatRateBonusAllowanceCalculation;
	}
	/** setFlatRateBonusAllowanceCalculation. 
	 * @param flatRateBonusAllowanceCalculation : flatRateBonusAllowanceCalculation
	*/
	public void setFlatRateBonusAllowanceCalculation(
			final String flatRateBonusAllowanceCalculation) {
		this.flatRateBonusAllowanceCalculation = flatRateBonusAllowanceCalculation;
	}

	/** getFileNameForManuallyCalculatedBonusAllowance. 
	 * @return String
	 * * */
	public String getFileNameForManuallyCalculatedBonusAllowance() {
		return this.fileNameForManuallyCalculatedBonusAllowance;
	}
	/** setFileNameForManuallyCalculatedBonusAllowance. 
	 * @param fileNameForManuallyCalculatedBonusAllowance : fileNameForManuallyCalculatedBonusAllowance
	*/
	public void setFileNameForManuallyCalculatedBonusAllowance(
			final String fileNameForManuallyCalculatedBonusAllowance) {
		this.fileNameForManuallyCalculatedBonusAllowance = fileNameForManuallyCalculatedBonusAllowance;
	}

	/** getFlatRateMaximumBonusAllowance. 
	 * @return String
	 * * */
	public String getFlatRateMaximumBonusAllowance() {
		return this.flatRateMaximumBonusAllowance;
	}
	/** setFlatRateMaximumBonusAllowance. 
	 * @param flatRateMaximumBonusAllowance : flatRateMaximumBonusAllowance
	*/
	public void setFlatRateMaximumBonusAllowance(
			final String flatRateMaximumBonusAllowance) {
		this.flatRateMaximumBonusAllowance = flatRateMaximumBonusAllowance;
	}

	/** getFlatRateMinimumBonusAllowance. 
	 * @return String
	 * * */
	public String getFlatRateMinimumBonusAllowance() {
		return this.flatRateMinimumBonusAllowance;
	}
	/** setFlatRateMinimumBonusAllowance. 
	 * @param flatRateMinimumBonusAllowance : flatRateMinimumBonusAllowance
	*/
	public void setFlatRateMinimumBonusAllowance(
			final String flatRateMinimumBonusAllowance) {
		this.flatRateMinimumBonusAllowance = flatRateMinimumBonusAllowance;
	}

	/** getNote. 
	 * @return String
	 * * */
	public String getNote() {
		return this.note;
	}
	/** setNote. 
	 * @param note : note
	*/
	public void setNote(final String note) {
		this.note = note;
	}

	/** getStatus. 
	 * @return String
	 * * */
	public String getStatus() {
		return this.status;
	}
	/** setStatus. 
	 * @param status : status
	*/
	public void setStatus(final String status) {
		this.status = status;
	}

	/** isDisplayNoteFlag. 
	 * @return boolean
	 * * */
	public boolean isDisplayNoteFlag() {
		return this.displayNoteFlag;
	}
	/** setDisplayNoteFlag. 
	 * @param displayNoteFlag : displayNoteFlag
	*/
	public void setDisplayNoteFlag(final boolean displayNoteFlag) {
		this.displayNoteFlag = displayNoteFlag;
	}

	/** getFileNameManuallyUploadedBonusAllowance. 
	 * @return String
	 * * */
	public String getFileNameManuallyUploadedBonusAllowance() {
		return this.fileNameManuallyUploadedBonusAllowance;
	}
	/** setFileNameManuallyUploadedBonusAllowance. 
	 * @param fileNameManuallyUploadedBonusAllowance : fileNameManuallyUploadedBonusAllowance
	*/
	public void setFileNameManuallyUploadedBonusAllowance(
			final String fileNameManuallyUploadedBonusAllowance) {
		this.fileNameManuallyUploadedBonusAllowance = fileNameManuallyUploadedBonusAllowance;
	}

	/** getTempFileNameManuallyUploadedBonus. 
	 * @return String
	 * * */
	public String getTempFileNameManuallyUploadedBonus() {
		return this.tempFileNameManuallyUploadedBonus;
	}
	/** setTempFileNameManuallyUploadedBonus. 
	 * @param tempFileNameManuallyUploadedBonus : tempFileNameManuallyUploadedBonus
	*/
	public void setTempFileNameManuallyUploadedBonus(
			final String tempFileNameManuallyUploadedBonus) {
		this.tempFileNameManuallyUploadedBonus = tempFileNameManuallyUploadedBonus;
	}

	/** getBonusAllowanceStatus. 
	 * @return String
	 * * */
	public String getBonusAllowanceStatus() {
		return this.bonusAllowanceStatus;
	}
	/** setBonusAllowanceStatus. 
	 * @param bonusAllowanceStatus : bonusAllowanceStatus
	*/
	public void setBonusAllowanceStatus(final String bonusAllowanceStatus) {
		this.bonusAllowanceStatus = bonusAllowanceStatus;
	}

	/** getFileNameIndividualRatingsUploaded. 
	 * @return String
	 * * */
	public String getFileNameIndividualRatingsUploaded() {
		return this.fileNameIndividualRatingsUploaded;
	}
	/** setFileNameIndividualRatingsUploaded. 
	 * @param fileNameIndividualRatingsUploaded : fileNameIndividualRatingsUploaded
	*/
	public void setFileNameIndividualRatingsUploaded(
			final String fileNameIndividualRatingsUploaded) {
		this.fileNameIndividualRatingsUploaded = fileNameIndividualRatingsUploaded;
	}

	/** getTempFileNameIndividualRatingsUploaded. 
	 * @return String
	 * * */
	public String getTempFileNameIndividualRatingsUploaded() {
		return this.tempFileNameIndividualRatingsUploaded;
	}
	/** setTempFileNameIndividualRatingsUploaded. 
	 * @param tempFileNameIndividualRatingsUploaded : tempFileNameIndividualRatingsUploaded
	*/
	public void setTempFileNameIndividualRatingsUploaded(
			final String tempFileNameIndividualRatingsUploaded) {
		this.tempFileNameIndividualRatingsUploaded = tempFileNameIndividualRatingsUploaded;
	}

	/** isViewAlreadyUploadedRatingsDetailsFlag. 
	 * @return boolean
	 * * */
	public boolean isViewAlreadyUploadedRatingsDetailsFlag() {
		return this.viewAlreadyUploadedRatingsDetailsFlag;
	}
	/** setViewAlreadyUploadedRatingsDetailsFlag. 
	 * @param viewAlreadyUploadedRatingsDetailsFlag : viewAlreadyUploadedRatingsDetailsFlag
	*/
	public void setViewAlreadyUploadedRatingsDetailsFlag(
			final boolean viewAlreadyUploadedRatingsDetailsFlag) {
		this.viewAlreadyUploadedRatingsDetailsFlag = viewAlreadyUploadedRatingsDetailsFlag;
	}
	
	/** getSlabDetailsIterator. 
	 * @return List
	 * * */
	public List<BonusAllowance> getSlabDetailsIterator() {
		return this.slabDetailsIterator;
	}
	/** setSlabDetailsIterator. 
	 * @param slabDetailsIterator : slabDetailsIterator
	*/
	public void setSlabDetailsIterator(final List<BonusAllowance> slabDetailsIterator) {
		this.slabDetailsIterator = slabDetailsIterator;
	}

	/** getSlabItrFromAmount. 
	 * @return String
	 * * */
	public String getSlabItrFromAmount() {
		return this.slabItrFromAmount;
	}
	/** setSlabItrFromAmount. 
	 * @param slabItrFromAmount : slabItrFromAmount
	*/
	public void setSlabItrFromAmount(final String slabItrFromAmount) {
		this.slabItrFromAmount = slabItrFromAmount;
	}

	/** getSlabItrToAmount. 
	 * @return String
	 * * */
	public String getSlabItrToAmount() {
		return this.slabItrToAmount;
	}
	/** setSlabItrToAmount. 
	 * @param slabItrToAmount : slabItrToAmount
	*/
	public void setSlabItrToAmount(final String slabItrToAmount) {
		this.slabItrToAmount = slabItrToAmount;
	}

	/** getSlabItrPercentage. 
	 * @return String
	 * * */
	public String getSlabItrPercentage() {
		return this.slabItrPercentage;
	}
	/** setSlabItrPercentage. 
	 * @param slabItrPercentage : slabItrPercentage
	*/
	public void setSlabItrPercentage(final String slabItrPercentage) {
		this.slabItrPercentage = slabItrPercentage;
	}

	/** getSlabItrMin. 
	 * @return String
	 * * */
	public String getSlabItrMin() {
		return this.slabItrMin;
	}
	/** setSlabItrMin. 
	 * @param slabItrMin : slabItrMin
	*/
	public void setSlabItrMin(final String slabItrMin) {
		this.slabItrMin = slabItrMin;
	}

	/** getSlabItrMax. 
	 * @return String
	 * * */
	public String getSlabItrMax() {
		return this.slabItrMax;
	}
	/** setSlabItrMax. 
	 * @param slabItrMax : slabItrMax
	*/
	public void setSlabItrMax(final String slabItrMax) {
		this.slabItrMax = slabItrMax;
	}

	/** getSlabItrID. 
	 * @return String
	 * * */
	public String getSlabItrID() {
		return this.slabItrID;
	}
	/** setSlabItrID. 
	 * @param slabItrID : slabItrID
	*/
	public void setSlabItrID(final String slabItrID) {
		this.slabItrID = slabItrID;
	}

	/** isViewAlreadyUploadedBonusFlag. 
	 * @return boolean
	 * * */
	public boolean isViewAlreadyUploadedBonusFlag() {
		return this.viewAlreadyUploadedBonusFlag;
	}
	/** setViewAlreadyUploadedBonusFlag. 
	 * @param viewAlreadyUploadedBonusFlag : viewAlreadyUploadedBonusFlag
	*/
	public void setViewAlreadyUploadedBonusFlag(final boolean viewAlreadyUploadedBonusFlag) {
		this.viewAlreadyUploadedBonusFlag = viewAlreadyUploadedBonusFlag;
	}

	/** getEmployeeIDEditRecord. 
	 * @return String
	 * * */
	public String getEmployeeIDEditRecord() {
		return this.employeeIDEditRecord;
	}
	/** setEmployeeIDEditRecord. 
	 * @param employeeIDEditRecord : employeeIDEditRecord
	*/
	public void setEmployeeIDEditRecord(final String employeeIDEditRecord) {
		this.employeeIDEditRecord = employeeIDEditRecord;
	}

	/** getEmployeeTokenEditRecord. 
	 * @return String
	 * * */
	public String getEmployeeTokenEditRecord() {
		return this.employeeTokenEditRecord;
	}
	/** setEmployeeTokenEditRecord. 
	 * @param employeeTokenEditRecord : employeeTokenEditRecord
	*/
	public void setEmployeeTokenEditRecord(final String employeeTokenEditRecord) {
		this.employeeTokenEditRecord = employeeTokenEditRecord;
	}

	/** getEmployeeNameEditRecord. 
	 * @return String
	 * * */
	public String getEmployeeNameEditRecord() {
		return this.employeeNameEditRecord;
	}
	/** setEmployeeNameEditRecord. 
	 * @param employeeNameEditRecord : employeeNameEditRecord
	*/
	public void setEmployeeNameEditRecord(final String employeeNameEditRecord) {
		this.employeeNameEditRecord = employeeNameEditRecord;
	}

	/** getBonusAllowanceAmountEditRecord. 
	 * @return String
	 * * */
	public String getBonusAllowanceAmountEditRecord() {
		return this.bonusAllowanceAmountEditRecord;
	}
	/** setBonusAllowanceAmountEditRecord. 
	 * @param bonusAllowanceAmountEditRecord : bonusAllowanceAmountEditRecord
	*/
	public void setBonusAllowanceAmountEditRecord(
			final String bonusAllowanceAmountEditRecord) {
		this.bonusAllowanceAmountEditRecord = bonusAllowanceAmountEditRecord;
	}

	/** getTdsEditRecord. 
	 * @return String
	 * * */
	public String getTdsEditRecord() {
		return this.tdsEditRecord;
	}
	/** setTdsEditRecord. 
	 * @param tdsEditRecord : tdsEditRecord
	*/
	public void setTdsEditRecord(final String tdsEditRecord) {
		this.tdsEditRecord = tdsEditRecord;
	}

	/** getEmployeeListEditRecordIterator. 
	 * @return List
	 * * */
	public List<BonusAllowance> getEmployeeListEditRecordIterator() {
		return this.employeeListEditRecordIterator;
	}
	/** setEmployeeListEditRecordIterator. 
	 * @param employeeListEditRecordIterator : employeeListEditRecordIterator
	*/
	public void setEmployeeListEditRecordIterator(
			final List<BonusAllowance> employeeListEditRecordIterator) {
		this.employeeListEditRecordIterator = employeeListEditRecordIterator;
	}

	/** isEmployeeListEditRecordFlag. 
	 * @return boolean
	 * * */
	public boolean isEmployeeListEditRecordFlag() {
		return this.employeeListEditRecordFlag;
	}
	/** setEmployeeListEditRecordFlag. 
	 * @param employeeListEditRecordFlag : employeeListEditRecordFlag
	*/
	public void setEmployeeListEditRecordFlag(final boolean employeeListEditRecordFlag) {
		this.employeeListEditRecordFlag = employeeListEditRecordFlag;
	}

	/** getEmpTokenEditRecordItr. 
	 * @return String
	 * * */
	public String getEmpTokenEditRecordItr() {
		return this.empTokenEditRecordItr;
	}
	/** setEmpTokenEditRecordItr. 
	 * @param empTokenEditRecordItr : empTokenEditRecordItr
	*/
	public void setEmpTokenEditRecordItr(final String empTokenEditRecordItr) {
		this.empTokenEditRecordItr = empTokenEditRecordItr;
	}

	/** getEmpIDEditRecordItr. 
	 * @return String
	 * * */
	public String getEmpIDEditRecordItr() {
		return this.empIDEditRecordItr;
	}
	/** setEmpIDEditRecordItr. 
	 * @param empIDEditRecordItr : empIDEditRecordItr
	*/
	public void setEmpIDEditRecordItr(final String empIDEditRecordItr) {
		this.empIDEditRecordItr = empIDEditRecordItr;
	}

	/** getEmpNameEditRecordItr. 
	 * @return String
	 * * */
	public String getEmpNameEditRecordItr() {
		return this.empNameEditRecordItr;
	}
	/** setEmpNameEditRecordItr. 
	 * @param empNameEditRecordItr : empNameEditRecordItr
	*/
	public void setEmpNameEditRecordItr(final String empNameEditRecordItr) {
		this.empNameEditRecordItr = empNameEditRecordItr;
	}

	/** getBonusAllowanceAmountEditRecordItr. 
	 * @return String
	 * * */
	public String getBonusAllowanceAmountEditRecordItr() {
		return this.bonusAllowanceAmountEditRecordItr;
	}
	/** setBonusAllowanceAmountEditRecordItr. 
	 * @param bonusAllowanceAmountEditRecordItr : bonusAllowanceAmountEditRecordItr
	*/
	public void setBonusAllowanceAmountEditRecordItr(
			final String bonusAllowanceAmountEditRecordItr) {
		this.bonusAllowanceAmountEditRecordItr = bonusAllowanceAmountEditRecordItr;
	}

	/** getTaxEditRecordItr. 
	 * @return String
	 * * */
	public String getTaxEditRecordItr() {
		return this.taxEditRecordItr;
	}
	/** setTaxEditRecordItr. 
	 * @param taxEditRecordItr : taxEditRecordItr
	*/
	public void setTaxEditRecordItr(final String taxEditRecordItr) {
		this.taxEditRecordItr = taxEditRecordItr;
	}

	/** getTotalAmountEditRecordItr. 
	 * @return String
	 * * */
	public String getTotalAmountEditRecordItr() {
		return this.totalAmountEditRecordItr;
	}
	/** setTotalAmountEditRecordItr. 
	 * @param totalAmountEditRecordItr : totalAmountEditRecordItr
	*/
	public void setTotalAmountEditRecordItr(final String totalAmountEditRecordItr) {
		this.totalAmountEditRecordItr = totalAmountEditRecordItr;
	}

	/** getFilterEmpId. 
	 * @return String
	 * * */
	public String getFilterEmpId() {
		return this.filterEmpId;
	}
	/** setFilterEmpId. 
	 * @param filterEmpId : filterEmpId
	*/
	public void setFilterEmpId(final String filterEmpId) {
		this.filterEmpId = filterEmpId;
	}

	/** getFilterEmpName. 
	 * @return String
	 * * */
	public String getFilterEmpName() {
		return this.filterEmpName;
	}
	/** setFilterEmpName. 
	 * @param filterEmpName : filterEmpName
	*/
	public void setFilterEmpName(final String filterEmpName) {
		this.filterEmpName = filterEmpName;
	}

	/** getFilterEmpToken. 
	 * @return String
	 * * */
	public String getFilterEmpToken() {
		return this.filterEmpToken;
	}
	/** setFilterEmpToken. 
	 * @param filterEmpToken : filterEmpToken
	*/
	public void setFilterEmpToken(final String filterEmpToken) {
		this.filterEmpToken = filterEmpToken;
	}

	/** isProcessedRecordFlag. 
	 * @return boolean
	 * * */
	public boolean isProcessedRecordFlag() {
		return this.processedRecordFlag;
	}
	/** setProcessedRecordFlag. 
	 * @param processedRecordFlag : processedRecordFlag
	*/
	public void setProcessedRecordFlag(final boolean processedRecordFlag) {
		this.processedRecordFlag = processedRecordFlag;
	}

	/** getProcessedFromMonth. 
	 * @return String
	 * * */
	public String getProcessedFromMonth() {
		return this.processedFromMonth;
	}
	/** setProcessedFromMonth. 
	 * @param processedFromMonth : processedFromMonth
	*/
	public void setProcessedFromMonth(final String processedFromMonth) {
		this.processedFromMonth = processedFromMonth;
	}

	/** getProcessedToMonth. 
	 * @return String
	 * * */
	public String getProcessedToMonth() {
		return this.processedToMonth;
	}
	/** setProcessedToMonth. 
	 * @param processedToMonth : processedToMonth
	*/
	public void setProcessedToMonth(final String processedToMonth) {
		this.processedToMonth = processedToMonth;
	}
	/** isUnlockStatusFlag. 
	 * @return boolean
	 * * */
	public boolean isUnlockStatusFlag() {
		return this.unlockStatusFlag;
	}
	/** setUnlockStatusFlag. 
	 * @param unlockStatusFlag : unlockStatusFlag
	*/
	public void setUnlockStatusFlag(final boolean unlockStatusFlag) {
		this.unlockStatusFlag = unlockStatusFlag;
	}

}
