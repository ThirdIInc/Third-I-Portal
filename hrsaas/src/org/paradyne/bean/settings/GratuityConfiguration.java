/**
 * 
 */
package org.paradyne.bean.settings;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author aa0623
 *
 */
public class GratuityConfiguration extends BeanBase {
	//private String fieldsFlag = "false";
	private String gratuityFlag="";
	private String minimumTenure="";
	private String creditsCombine="";
	private String creditsCombineCode="";
	private String nearestValue="";
	private String floorValue="";
	private String ceilValue="";
	private String actualValue="";
	private String formula="";
	private String roundOffHidden="";
	private String salOptionHidden="";
	private String lastDrawnSal="";
	private String avgMonthSal="";
	
	//FOR SETTING FIELDS
	private boolean enableFilters=false;
	private String gratuityCode="";
	private String divisionName="";
	private String divisionCode="";
	private String gradeName="";
	private String gradeCode="";
	private String deptName="";
	private String deptCode="";
	private String branchName="";
	private String branchCode="";
	private String desgName="";
	private String desgCode="";
	private String empTypeName="";
	private String empTypeCode="";
	
	//for iterator names
	private String srNo="";
	private String divisionNameItr="";
	private String gradeNameItr="";
	private String deptNameItr="";
	private String branchNameItr="";
	private String desgNameItr="";
	private String empTypeNameItr="";
	
	//for iterator codes
	private String divisionCodeItr="";
	private String gradeCodeItr="";
	private String deptCodeItr="";
	private String branchCodeItr="";
	private String desgCodeItr="";
	private String gratuityCodeItr="";
	private String empTypeCodeItr="";
	//private String benefitSettingIDItr="";
	
	private ArrayList filterTableList=null;
	private String listLength="0";
	private String hiddenEdit="";
	private String subcode;
	
	//FOR LEAVE LIST
	private String creditCodeHid = "";
	private String creditAbbrHid = "";
	
	private String creditCodeHidNext = "";
	private String creditAbbrHidNext = "";
	private String serNo;
	
	private String creditCode;
	private String creditName;
	private String creditAbbr;
	private String check;
	private ArrayList creditDataList;
	private String creditTypeFlag = "false";
	private String checkFlag = "false";

	/**
	 * @return the subcode
	 */
	public String getSubcode() {
		return subcode;
	}

	/**
	 * @param subcode the subcode to set
	 */
	public void setSubcode(String subcode) {
		this.subcode = subcode;
	}

	/**
	 * @return the hiddenEdit
	 */
	public String getHiddenEdit() {
		return hiddenEdit;
	}

	/**
	 * @param hiddenEdit the hiddenEdit to set
	 */
	public void setHiddenEdit(String hiddenEdit) {
		this.hiddenEdit = hiddenEdit;
	}

	/**
	 * @return the gratuityFlag
	 */
	public String getGratuityFlag() {
		return gratuityFlag;
	}

	/**
	 * @param gratuityFlag the gratuityFlag to set
	 */
	public void setGratuityFlag(String gratuityFlag) {
		this.gratuityFlag = gratuityFlag;
	}

	/**
	 * @return the enableFilters
	 */
	public boolean isEnableFilters() {
		return enableFilters;
	}

	/**
	 * @param enableFilters the enableFilters to set
	 */
	public void setEnableFilters(boolean enableFilters) {
		this.enableFilters = enableFilters;
	}

	/**
	 * @return the gratuityCode
	 */
	public String getGratuityCode() {
		return gratuityCode;
	}

	/**
	 * @param gratuityCode the gratuityCode to set
	 */
	public void setGratuityCode(String gratuityCode) {
		this.gratuityCode = gratuityCode;
	}

	/**
	 * @return the divisionName
	 */
	public String getDivisionName() {
		return divisionName;
	}

	/**
	 * @param divisionName the divisionName to set
	 */
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	/**
	 * @return the divisionCode
	 */
	public String getDivisionCode() {
		return divisionCode;
	}

	/**
	 * @param divisionCode the divisionCode to set
	 */
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}

	/**
	 * @return the gradeName
	 */
	public String getGradeName() {
		return gradeName;
	}

	/**
	 * @param gradeName the gradeName to set
	 */
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	/**
	 * @return the gradeCode
	 */
	public String getGradeCode() {
		return gradeCode;
	}

	/**
	 * @param gradeCode the gradeCode to set
	 */
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}

	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * @return the deptCode
	 */
	public String getDeptCode() {
		return deptCode;
	}

	/**
	 * @param deptCode the deptCode to set
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}

	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	/**
	 * @return the branchCode
	 */
	public String getBranchCode() {
		return branchCode;
	}

	/**
	 * @param branchCode the branchCode to set
	 */
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	/**
	 * @return the desgName
	 */
	public String getDesgName() {
		return desgName;
	}

	/**
	 * @param desgName the desgName to set
	 */
	public void setDesgName(String desgName) {
		this.desgName = desgName;
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
	 * @return the srNo
	 */
	public String getSrNo() {
		return srNo;
	}

	/**
	 * @param srNo the srNo to set
	 */
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}

	/**
	 * @return the divisionNameItr
	 */
	public String getDivisionNameItr() {
		return divisionNameItr;
	}

	/**
	 * @param divisionNameItr the divisionNameItr to set
	 */
	public void setDivisionNameItr(String divisionNameItr) {
		this.divisionNameItr = divisionNameItr;
	}

	/**
	 * @return the gradeNameItr
	 */
	public String getGradeNameItr() {
		return gradeNameItr;
	}

	/**
	 * @param gradeNameItr the gradeNameItr to set
	 */
	public void setGradeNameItr(String gradeNameItr) {
		this.gradeNameItr = gradeNameItr;
	}

	/**
	 * @return the deptNameItr
	 */
	public String getDeptNameItr() {
		return deptNameItr;
	}

	/**
	 * @param deptNameItr the deptNameItr to set
	 */
	public void setDeptNameItr(String deptNameItr) {
		this.deptNameItr = deptNameItr;
	}

	/**
	 * @return the branchNameItr
	 */
	public String getBranchNameItr() {
		return branchNameItr;
	}

	/**
	 * @param branchNameItr the branchNameItr to set
	 */
	public void setBranchNameItr(String branchNameItr) {
		this.branchNameItr = branchNameItr;
	}

	/**
	 * @return the desgNameItr
	 */
	public String getDesgNameItr() {
		return desgNameItr;
	}

	/**
	 * @param desgNameItr the desgNameItr to set
	 */
	public void setDesgNameItr(String desgNameItr) {
		this.desgNameItr = desgNameItr;
	}

	/**
	 * @return the divisionCodeItr
	 */
	public String getDivisionCodeItr() {
		return divisionCodeItr;
	}

	/**
	 * @param divisionCodeItr the divisionCodeItr to set
	 */
	public void setDivisionCodeItr(String divisionCodeItr) {
		this.divisionCodeItr = divisionCodeItr;
	}

	/**
	 * @return the gradeCodeItr
	 */
	public String getGradeCodeItr() {
		return gradeCodeItr;
	}

	/**
	 * @param gradeCodeItr the gradeCodeItr to set
	 */
	public void setGradeCodeItr(String gradeCodeItr) {
		this.gradeCodeItr = gradeCodeItr;
	}

	/**
	 * @return the deptCodeItr
	 */
	public String getDeptCodeItr() {
		return deptCodeItr;
	}

	/**
	 * @param deptCodeItr the deptCodeItr to set
	 */
	public void setDeptCodeItr(String deptCodeItr) {
		this.deptCodeItr = deptCodeItr;
	}

	/**
	 * @return the branchCodeItr
	 */
	public String getBranchCodeItr() {
		return branchCodeItr;
	}

	/**
	 * @param branchCodeItr the branchCodeItr to set
	 */
	public void setBranchCodeItr(String branchCodeItr) {
		this.branchCodeItr = branchCodeItr;
	}

	/**
	 * @return the desgCodeItr
	 */
	public String getDesgCodeItr() {
		return desgCodeItr;
	}

	/**
	 * @param desgCodeItr the desgCodeItr to set
	 */
	public void setDesgCodeItr(String desgCodeItr) {
		this.desgCodeItr = desgCodeItr;
	}

	/**
	 * @return the gratuityCodeItr
	 */
	public String getGratuityCodeItr() {
		return gratuityCodeItr;
	}

	/**
	 * @param gratuityCodeItr the gratuityCodeItr to set
	 */
	public void setGratuityCodeItr(String gratuityCodeItr) {
		this.gratuityCodeItr = gratuityCodeItr;
	}

	/**
	 * @return the filterTableList
	 */
	public ArrayList getFilterTableList() {
		return filterTableList;
	}

	/**
	 * @param filterTableList the filterTableList to set
	 */
	public void setFilterTableList(ArrayList filterTableList) {
		this.filterTableList = filterTableList;
	}

	/**
	 * @return the listLength
	 */
	public String getListLength() {
		return listLength;
	}

	/**
	 * @param listLength the listLength to set
	 */
	public void setListLength(String listLength) {
		this.listLength = listLength;
	}

	/**
	 * @return the empTypeName
	 */
	public String getEmpTypeName() {
		return empTypeName;
	}

	/**
	 * @param empTypeName the empTypeName to set
	 */
	public void setEmpTypeName(String empTypeName) {
		this.empTypeName = empTypeName;
	}

	/**
	 * @return the empTypeCode
	 */
	public String getEmpTypeCode() {
		return empTypeCode;
	}

	/**
	 * @param empTypeCode the empTypeCode to set
	 */
	public void setEmpTypeCode(String empTypeCode) {
		this.empTypeCode = empTypeCode;
	}

	/**
	 * @return the empTypeNameItr
	 */
	public String getEmpTypeNameItr() {
		return empTypeNameItr;
	}

	/**
	 * @param empTypeNameItr the empTypeNameItr to set
	 */
	public void setEmpTypeNameItr(String empTypeNameItr) {
		this.empTypeNameItr = empTypeNameItr;
	}

	/**
	 * @return the empTypeCodeItr
	 */
	public String getEmpTypeCodeItr() {
		return empTypeCodeItr;
	}

	/**
	 * @param empTypeCodeItr the empTypeCodeItr to set
	 */
	public void setEmpTypeCodeItr(String empTypeCodeItr) {
		this.empTypeCodeItr = empTypeCodeItr;
	}

	/**
	 * @return the creditCodeHid
	 */
	public String getCreditCodeHid() {
		return creditCodeHid;
	}

	/**
	 * @param creditCodeHid the creditCodeHid to set
	 */
	public void setCreditCodeHid(String creditCodeHid) {
		this.creditCodeHid = creditCodeHid;
	}

	/**
	 * @return the creditAbbrHid
	 */
	public String getCreditAbbrHid() {
		return creditAbbrHid;
	}

	/**
	 * @param creditAbbrHid the creditAbbrHid to set
	 */
	public void setCreditAbbrHid(String creditAbbrHid) {
		this.creditAbbrHid = creditAbbrHid;
	}

	/**
	 * @return the creditCodeHidNext
	 */
	public String getCreditCodeHidNext() {
		return creditCodeHidNext;
	}

	/**
	 * @param creditCodeHidNext the creditCodeHidNext to set
	 */
	public void setCreditCodeHidNext(String creditCodeHidNext) {
		this.creditCodeHidNext = creditCodeHidNext;
	}

	/**
	 * @return the creditAbbrHidNext
	 */
	public String getCreditAbbrHidNext() {
		return creditAbbrHidNext;
	}

	/**
	 * @param creditAbbrHidNext the creditAbbrHidNext to set
	 */
	public void setCreditAbbrHidNext(String creditAbbrHidNext) {
		this.creditAbbrHidNext = creditAbbrHidNext;
	}

	/**
	 * @return the serNo
	 */
	public String getSerNo() {
		return serNo;
	}

	/**
	 * @param serNo the serNo to set
	 */
	public void setSerNo(String serNo) {
		this.serNo = serNo;
	}

	/**
	 * @return the creditCode
	 */
	public String getCreditCode() {
		return creditCode;
	}

	/**
	 * @param creditCode the creditCode to set
	 */
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	/**
	 * @return the creditName
	 */
	public String getCreditName() {
		return creditName;
	}

	/**
	 * @param creditName the creditName to set
	 */
	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}

	/**
	 * @return the creditAbbr
	 */
	public String getCreditAbbr() {
		return creditAbbr;
	}

	/**
	 * @param creditAbbr the creditAbbr to set
	 */
	public void setCreditAbbr(String creditAbbr) {
		this.creditAbbr = creditAbbr;
	}

	/**
	 * @return the check
	 */
	public String getCheck() {
		return check;
	}

	/**
	 * @param check the check to set
	 */
	public void setCheck(String check) {
		this.check = check;
	}

	/**
	 * @return the creditDataList
	 */
	public ArrayList getCreditDataList() {
		return creditDataList;
	}

	/**
	 * @param creditDataList the creditDataList to set
	 */
	public void setCreditDataList(ArrayList creditDataList) {
		this.creditDataList = creditDataList;
	}

	/**
	 * @return the creditTypeFlag
	 */
	public String getCreditTypeFlag() {
		return creditTypeFlag;
	}

	/**
	 * @param creditTypeFlag the creditTypeFlag to set
	 */
	public void setCreditTypeFlag(String creditTypeFlag) {
		this.creditTypeFlag = creditTypeFlag;
	}

	/**
	 * @return the checkFlag
	 */
	public String getCheckFlag() {
		return checkFlag;
	}

	/**
	 * @param checkFlag the checkFlag to set
	 */
	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}

	/**
	 * @return the minimumTenure
	 */
	public String getMinimumTenure() {
		return minimumTenure;
	}

	/**
	 * @param minimumTenure the minimumTenure to set
	 */
	public void setMinimumTenure(String minimumTenure) {
		this.minimumTenure = minimumTenure;
	}

	/**
	 * @return the creditsCombine
	 */
	public String getCreditsCombine() {
		return creditsCombine;
	}

	/**
	 * @param creditsCombine the creditsCombine to set
	 */
	public void setCreditsCombine(String creditsCombine) {
		this.creditsCombine = creditsCombine;
	}

	/**
	 * @return the creditsCombineCode
	 */
	public String getCreditsCombineCode() {
		return creditsCombineCode;
	}

	/**
	 * @param creditsCombineCode the creditsCombineCode to set
	 */
	public void setCreditsCombineCode(String creditsCombineCode) {
		this.creditsCombineCode = creditsCombineCode;
	}

	/**
	 * @return the nearestValue
	 */
	public String getNearestValue() {
		return nearestValue;
	}

	/**
	 * @param nearestValue the nearestValue to set
	 */
	public void setNearestValue(String nearestValue) {
		this.nearestValue = nearestValue;
	}

	/**
	 * @return the floorValue
	 */
	public String getFloorValue() {
		return floorValue;
	}

	/**
	 * @param floorValue the floorValue to set
	 */
	public void setFloorValue(String floorValue) {
		this.floorValue = floorValue;
	}

	/**
	 * @return the ceilValue
	 */
	public String getCeilValue() {
		return ceilValue;
	}

	/**
	 * @param ceilValue the ceilValue to set
	 */
	public void setCeilValue(String ceilValue) {
		this.ceilValue = ceilValue;
	}

	/**
	 * @return the actualValue
	 */
	public String getActualValue() {
		return actualValue;
	}

	/**
	 * @param actualValue the actualValue to set
	 */
	public void setActualValue(String actualValue) {
		this.actualValue = actualValue;
	}

	/**
	 * @return the formula
	 */
	public String getFormula() {
		return formula;
	}

	/**
	 * @param formula the formula to set
	 */
	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getLastDrawnSal() {
		return lastDrawnSal;
	}

	public void setLastDrawnSal(String lastDrawnSal) {
		this.lastDrawnSal = lastDrawnSal;
	}

	public String getAvgMonthSal() {
		return avgMonthSal;
	}

	public void setAvgMonthSal(String avgMonthSal) {
		this.avgMonthSal = avgMonthSal;
	}

	public String getRoundOffHidden() {
		return roundOffHidden;
	}

	public void setRoundOffHidden(String roundOffHidden) {
		this.roundOffHidden = roundOffHidden;
	}

	public String getSalOptionHidden() {
		return salOptionHidden;
	}

	public void setSalOptionHidden(String salOptionHidden) {
		this.salOptionHidden = salOptionHidden;
	}

}
