package org.paradyne.bean.payroll;

import java.util.ArrayList;
import org.paradyne.lib.BeanBase;

public class TaxInv extends BeanBase {
	
	private String myPage;
	//private String fromYear = "";
	//private String toYear = "";
	private String investmentCode = "";
	private String investmentName = "";
	private String investmentChapter = "";
	private String investmentSection = "";
	private String investmentGroup = "";
	private String isAdditive = "";
	private String investmentType = "";
	private String investmentLimit = "";
	private boolean investmentFlag = false;
	private boolean pullInvestmentFlag = false;
	
	private String investmentFromYearListItt = "";
	private String investmentToYearListItt = "";
	private ArrayList investmentPeriodList =null ;
	
	//Iterator variables for each block
	private String investmentNameExemptItt = "";
	private String investmentChapterExemptItt = "";
	private String investmentSectionExemptItt = "";
	private String investmentCodeExemptItt = "";
	private String investmentLimitExemptItt = "";
	private String investmentTypeNameExemptItt = "";
	//private String investmentFromYearExemptItt = "";
	//private String investmentToYearExemptItt = "";
	private String investmentGroupExemptItt = "";
	private ArrayList investmentExemptList =null ;
	
	private String investmentNameDeductionItt = "";
	private String investmentChapterDeductionItt = "";
	private String investmentSectionDeductionItt = "";
	private String investmentCodeDeductionItt = "";
	private String investmentLimitDeductionItt = "";
	private String investmentTypeNameDeductionItt = "";
	//private String investmentFromYearDeductionItt = "";
	//private String investmentToYearDeductionItt = "";
	private String investmentGroupDeductionItt = "";
	private ArrayList investmentDeductionList =null ;
	
	private String investmentNameSectionItt = "";
	private String investmentChapterSectionItt = "";
	private String investmentSectionSectionItt = "";
	private String investmentCodeSectionItt = "";
	private String investmentLimitSectionItt = "";
	private String investmentTypeNameSectionItt = "";
	//private String investmentFromYearSectionItt = "";
	//private String investmentToYearSectionItt = "";
	private String investmentGroupSectionItt = "";
	private ArrayList investmentSectionList =null ;
	
	private String investmentNameOtherItt = "";
	private String investmentChapterOtherItt = "";
	private String investmentSectionOtherItt = "";
	private String investmentCodeOtherItt = "";
	private String investmentLimitOtherItt = "";
	private String investmentTypeNameOtherItt = "";
	//private String investmentFromYearOtherItt = "";
	//private String investmentToYearOtherItt = "";
	private String investmentGroupOtherItt = "";
	private String investmentIsAdditiveItt = "";
	private ArrayList investmentOtherList =null ;
	
	private boolean exemptionFlag=false;
	private boolean deductionFlag=false;
	private boolean sectionFlag=false;
	private boolean otherFlag=false;
	
	private String invIncludeCheck = "";
	
	public String getInvestmentName() {
		return investmentName;
	}
	public void setInvestmentName(String investmentName) {
		this.investmentName = investmentName;
	}
	public String getInvestmentChapter() {
		return investmentChapter;
	}
	public void setInvestmentChapter(String investmentChapter) {
		this.investmentChapter = investmentChapter;
	}
	public String getInvestmentSection() {
		return investmentSection;
	}
	public void setInvestmentSection(String investmentSection) {
		this.investmentSection = investmentSection;
	}
	public String getInvestmentLimit() {
		return investmentLimit;
	}
	public void setInvestmentLimit(String investmentLimit) {
		this.investmentLimit = investmentLimit;
	}
	public String getInvestmentNameExemptItt() {
		return investmentNameExemptItt;
	}
	public void setInvestmentNameExemptItt(String investmentNameExemptItt) {
		this.investmentNameExemptItt = investmentNameExemptItt;
	}
	public String getInvestmentChapterExemptItt() {
		return investmentChapterExemptItt;
	}
	public void setInvestmentChapterExemptItt(String investmentChapterExemptItt) {
		this.investmentChapterExemptItt = investmentChapterExemptItt;
	}
	public String getInvestmentSectionExemptItt() {
		return investmentSectionExemptItt;
	}
	public void setInvestmentSectionExemptItt(String investmentSectionExemptItt) {
		this.investmentSectionExemptItt = investmentSectionExemptItt;
	}
	public String getInvestmentLimitExemptItt() {
		return investmentLimitExemptItt;
	}
	public void setInvestmentLimitExemptItt(String investmentLimitExemptItt) {
		this.investmentLimitExemptItt = investmentLimitExemptItt;
	}
	public String getInvestmentTypeNameExemptItt() {
		return investmentTypeNameExemptItt;
	}
	public void setInvestmentTypeNameExemptItt(String investmentTypeNameExemptItt) {
		this.investmentTypeNameExemptItt = investmentTypeNameExemptItt;
	}
	public ArrayList getInvestmentExemptList() {
		return investmentExemptList;
	}
	public void setInvestmentExemptList(ArrayList investmentExemptList) {
		this.investmentExemptList = investmentExemptList;
	}
	public String getInvestmentNameDeductionItt() {
		return investmentNameDeductionItt;
	}
	public void setInvestmentNameDeductionItt(String investmentNameDeductionItt) {
		this.investmentNameDeductionItt = investmentNameDeductionItt;
	}
	public String getInvestmentChapterDeductionItt() {
		return investmentChapterDeductionItt;
	}
	public void setInvestmentChapterDeductionItt(
			String investmentChapterDeductionItt) {
		this.investmentChapterDeductionItt = investmentChapterDeductionItt;
	}
	public String getInvestmentSectionDeductionItt() {
		return investmentSectionDeductionItt;
	}
	public void setInvestmentSectionDeductionItt(
			String investmentSectionDeductionItt) {
		this.investmentSectionDeductionItt = investmentSectionDeductionItt;
	}
	public String getInvestmentLimitDeductionItt() {
		return investmentLimitDeductionItt;
	}
	public void setInvestmentLimitDeductionItt(String investmentLimitDeductionItt) {
		this.investmentLimitDeductionItt = investmentLimitDeductionItt;
	}
	public String getInvestmentTypeNameDeductionItt() {
		return investmentTypeNameDeductionItt;
	}
	public void setInvestmentTypeNameDeductionItt(
			String investmentTypeNameDeductionItt) {
		this.investmentTypeNameDeductionItt = investmentTypeNameDeductionItt;
	}
	public String getInvestmentCodeDeductionItt() {
		return investmentCodeDeductionItt;
	}
	public void setInvestmentCodeDeductionItt(String investmentCodeDeductionItt) {
		this.investmentCodeDeductionItt = investmentCodeDeductionItt;
	}
	public ArrayList getInvestmentDeductionList() {
		return investmentDeductionList;
	}
	public void setInvestmentDeductionList(ArrayList investmentDeductionList) {
		this.investmentDeductionList = investmentDeductionList;
	}
	public String getInvestmentNameSectionItt() {
		return investmentNameSectionItt;
	}
	public void setInvestmentNameSectionItt(String investmentNameSectionItt) {
		this.investmentNameSectionItt = investmentNameSectionItt;
	}
	public String getInvestmentChapterSectionItt() {
		return investmentChapterSectionItt;
	}
	public void setInvestmentChapterSectionItt(String investmentChapterSectionItt) {
		this.investmentChapterSectionItt = investmentChapterSectionItt;
	}
	public String getInvestmentSectionSectionItt() {
		return investmentSectionSectionItt;
	}
	public void setInvestmentSectionSectionItt(String investmentSectionSectionItt) {
		this.investmentSectionSectionItt = investmentSectionSectionItt;
	}
	public String getInvestmentLimitSectionItt() {
		return investmentLimitSectionItt;
	}
	public void setInvestmentLimitSectionItt(String investmentLimitSectionItt) {
		this.investmentLimitSectionItt = investmentLimitSectionItt;
	}
	public String getInvestmentTypeNameSectionItt() {
		return investmentTypeNameSectionItt;
	}
	public void setInvestmentTypeNameSectionItt(String investmentTypeNameSectionItt) {
		this.investmentTypeNameSectionItt = investmentTypeNameSectionItt;
	}
	public ArrayList getInvestmentSectionList() {
		return investmentSectionList;
	}
	public void setInvestmentSectionList(ArrayList investmentSectionList) {
		this.investmentSectionList = investmentSectionList;
	}
	public String getInvestmentNameOtherItt() {
		return investmentNameOtherItt;
	}
	public void setInvestmentNameOtherItt(String investmentNameOtherItt) {
		this.investmentNameOtherItt = investmentNameOtherItt;
	}
	public String getInvestmentChapterOtherItt() {
		return investmentChapterOtherItt;
	}
	public void setInvestmentChapterOtherItt(String investmentChapterOtherItt) {
		this.investmentChapterOtherItt = investmentChapterOtherItt;
	}
	public String getInvestmentSectionOtherItt() {
		return investmentSectionOtherItt;
	}
	public void setInvestmentSectionOtherItt(String investmentSectionOtherItt) {
		this.investmentSectionOtherItt = investmentSectionOtherItt;
	}
	public String getInvestmentLimitOtherItt() {
		return investmentLimitOtherItt;
	}
	public void setInvestmentLimitOtherItt(String investmentLimitOtherItt) {
		this.investmentLimitOtherItt = investmentLimitOtherItt;
	}
	public String getInvestmentTypeNameOtherItt() {
		return investmentTypeNameOtherItt;
	}
	public void setInvestmentTypeNameOtherItt(String investmentTypeNameOtherItt) {
		this.investmentTypeNameOtherItt = investmentTypeNameOtherItt;
	}
	public ArrayList getInvestmentOtherList() {
		return investmentOtherList;
	}
	public void setInvestmentOtherList(ArrayList investmentOtherList) {
		this.investmentOtherList = investmentOtherList;
	}
	public String getInvestmentCodeExemptItt() {
		return investmentCodeExemptItt;
	}
	public void setInvestmentCodeExemptItt(String investmentCodeExemptItt) {
		this.investmentCodeExemptItt = investmentCodeExemptItt;
	}
	public String getInvestmentCodeSectionItt() {
		return investmentCodeSectionItt;
	}
	public void setInvestmentCodeSectionItt(String investmentCodeSectionItt) {
		this.investmentCodeSectionItt = investmentCodeSectionItt;
	}
	public String getInvestmentCodeOtherItt() {
		return investmentCodeOtherItt;
	}
	public void setInvestmentCodeOtherItt(String investmentCodeOtherItt) {
		this.investmentCodeOtherItt = investmentCodeOtherItt;
	}
	public String getInvestmentType() {
		return investmentType;
	}
	public void setInvestmentType(String investmentType) {
		this.investmentType = investmentType;
	}
	public boolean isExemptionFlag() {
		return exemptionFlag;
	}
	public void setExemptionFlag(boolean exemptionFlag) {
		this.exemptionFlag = exemptionFlag;
	}
	public boolean isDeductionFlag() {
		return deductionFlag;
	}
	public void setDeductionFlag(boolean deductionFlag) {
		this.deductionFlag = deductionFlag;
	}
	public boolean isSectionFlag() {
		return sectionFlag;
	}
	public void setSectionFlag(boolean sectionFlag) {
		this.sectionFlag = sectionFlag;
	}
	public boolean isOtherFlag() {
		return otherFlag;
	}
	public void setOtherFlag(boolean otherFlag) {
		this.otherFlag = otherFlag;
	}
	public String getInvestmentCode() {
		return investmentCode;
	}
	public void setInvestmentCode(String investmentCode) {
		this.investmentCode = investmentCode;
	}
	public String getInvestmentFromYearListItt() {
		return investmentFromYearListItt;
	}
	public void setInvestmentFromYearListItt(String investmentFromYearListItt) {
		this.investmentFromYearListItt = investmentFromYearListItt;
	}
	public String getInvestmentToYearListItt() {
		return investmentToYearListItt;
	}
	public void setInvestmentToYearListItt(String investmentToYearListItt) {
		this.investmentToYearListItt = investmentToYearListItt;
	}
	public ArrayList getInvestmentPeriodList() {
		return investmentPeriodList;
	}
	public void setInvestmentPeriodList(ArrayList investmentPeriodList) {
		this.investmentPeriodList = investmentPeriodList;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public boolean isPullInvestmentFlag() {
		return pullInvestmentFlag;
	}
	public void setPullInvestmentFlag(boolean pullInvestmentFlag) {
		this.pullInvestmentFlag = pullInvestmentFlag;
	}
	public String getInvestmentGroup() {
		return investmentGroup;
	}
	public void setInvestmentGroup(String investmentGroup) {
		this.investmentGroup = investmentGroup;
	}
	public String getInvestmentGroupExemptItt() {
		return investmentGroupExemptItt;
	}
	public void setInvestmentGroupExemptItt(String investmentGroupExemptItt) {
		this.investmentGroupExemptItt = investmentGroupExemptItt;
	}
	public String getInvestmentGroupDeductionItt() {
		return investmentGroupDeductionItt;
	}
	public void setInvestmentGroupDeductionItt(String investmentGroupDeductionItt) {
		this.investmentGroupDeductionItt = investmentGroupDeductionItt;
	}
	public String getInvestmentGroupSectionItt() {
		return investmentGroupSectionItt;
	}
	public void setInvestmentGroupSectionItt(String investmentGroupSectionItt) {
		this.investmentGroupSectionItt = investmentGroupSectionItt;
	}
	public String getInvestmentGroupOtherItt() {
		return investmentGroupOtherItt;
	}
	public void setInvestmentGroupOtherItt(String investmentGroupOtherItt) {
		this.investmentGroupOtherItt = investmentGroupOtherItt;
	}
	public String getIsAdditive() {
		return isAdditive;
	}
	public void setIsAdditive(String isAdditive) {
		this.isAdditive = isAdditive;
	}
	public String getInvestmentIsAdditiveItt() {
		return investmentIsAdditiveItt;
	}
	public void setInvestmentIsAdditiveItt(String investmentIsAdditiveItt) {
		this.investmentIsAdditiveItt = investmentIsAdditiveItt;
	}
	public boolean isInvestmentFlag() {
		return investmentFlag;
	}
	public void setInvestmentFlag(boolean investmentFlag) {
		this.investmentFlag = investmentFlag;
	}
	/**
	 * @return the invIncludeCheck
	 */
	public String getInvIncludeCheck() {
		return invIncludeCheck;
	}
	/**
	 * @param invIncludeCheck the invIncludeCheck to set
	 */
	public void setInvIncludeCheck(String invIncludeCheck) {
		this.invIncludeCheck = invIncludeCheck;
	}
	
}
	