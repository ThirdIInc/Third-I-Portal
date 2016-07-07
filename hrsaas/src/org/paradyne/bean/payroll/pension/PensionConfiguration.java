package org.paradyne.bean.payroll.pension;

import java.util.ArrayList;
import java.util.List;

import org.paradyne.lib.BeanBase;

public class PensionConfiguration extends BeanBase {

	private String readFlag = "true";
	private String myPage = null;
	private String sSrNo = null;
	
	/* Used in list */
	private String sPensionEffFrm = null;
	private String sPensionMinService = null;
	private String sPensionMaxService = null;
	private String sPensionMinAmt = null;
	private String sPensionEmpStatus = null;
	private String sPensionEmolFormula = null;
	private String sPensionEmolMonths = null;
	private String sPensionAvgEmol = null;
	private String sPensionFormula = null;
	private String sPensionVolWeiyears = null;
	private String sPensionCompFormula = null;
	/* ---End--- */
	
	private String sPensionCode = null;
	private String sPensionTypeCode = null;
	private String prePensionTypeCode ;
	
	private String sPensionEffFrm1 = null;
	private String sPensionMinService1 = null;
	private String sPensionMaxService1 = null;
	private String sPensionMinAmt1 = null;
	private String sPensionEmpStatus1 = null;
	private String sPensionEmolFormula1 = null;
	private String sPensionEmolMonths1 = null;
	private String sPensionAvgEmol1 = null;
	private String sPensionFormula1 = null;
	private String sPensionVolWeiyears1 = null;
	private String sPensionCompFormula1 = null;
	
	
	private String sPensionEffFrm2 = null;
	private String sPensionMinService2 = null;
	private String sPensionMaxService2 = null;
	private String sPensionMinAmt2 = null;
	private String sPensionEmpStatus2 = null;
	private String sPensionEmolFormula2 = null;
	private String sPensionEmolMonths2 = null;
	private String sPensionAvgEmol2 = null;
	private String sPensionFormula2 = null;
	private String sPensionVolWeiyears2 = null;
	private String sPensionCompFormula2 = null;
	
	private String sPensionEffFrm3 = null;
	private String sPensionMinService3 = null;
	private String sPensionMaxService3 = null;
	private String sPensionMinAmt3 = null;
	private String sPensionEmpStatus3 = null;
	private String sPensionEmolFormula3 = null;
	private String sPensionEmolMonths3 = null;
	private String sPensionAvgEmol3 = null;
	private String sPensionFormula3 = null;
	private String sPensionVolWeiyears3 = null;
	private String sPensionCompFormula3 = null;
	
	
	private String sPensionEffFrm4 = null;
	private String sPensionMinService4 = null;
	private String sPensionMaxService4 = null;
	private String sPensionMinAmt4 = null;
	private String sPensionEmpStatus4 = null;
	private String sPensionEmolFormula4 = null;
	private String sPensionEmolMonths4 = null;
	private String sPensionAvgEmol4 = null;
	private String sPensionFormula4 = null;
	private String sPensionVolWeiyears4 = null;
	private String sPensionCompFormula4 = null;
	
	private String sMode = null;
	private String hiddencode = null;
	
	private List lstPensionConfiguration = new ArrayList();
	
	private String sPensionAmtCreditCode1 = null;
	private String sPensionAmtCreditHead1 = null;
	private String sPensionAmtCreditValue1 = null;
	
	private String sPensionAmtCreditCode2 = null;
	private String sPensionAmtCreditHead2 = null;
	private String sPensionAmtCreditValue2 = null;
	
	private String sPensionAmtCreditCode3 = null;
	private String sPensionAmtCreditHead3 = null;
	private String sPensionAmtCreditValue3 = null;

	/**
	 * Fields Added By Nilesh. 
	 */
	/**
	 * Credit Head Code Hidden Field.
	 */
	private String creditHeadCode = "";	
	/**
	 * Credit Head. 
	 */
	private String creditHead = "";
	/**
	 * Credit Formula.
	 */
	private String creditFormula = "";
	/**
	 * Pension Value.
	 */
	private String pensionValue = "";

	/**
	 * Para Id Hidden Field.
	 */
	private String paraId = "";
	/**
	 * Credit Head Iterator Field.
	 */
	private String creditHeadItt = ""; 
	/**
	 *  Credit Formula Iterator Field.
	 */
	private String creditFormulaItt = "";
	/**
	 * Pension Value Iterator Field.
	 */
	private String valueItt = "";
	/**
	 * Credit Details List.
	 */
	private List<PensionConfiguration> creditDetailsList; 
	/**
	 * Serial No.
	 */
	private String srNo = "";
	/**
	 * For Delete Hidden Field.
	 */
	private String sPensionDeleteCode = "";
	/**
	 * Edit Id Hidden.
	 */
	private String editDataId = "";
	
	/**
	 * @return the srNo
	 */
	public String getSrNo() {
		return this.srNo;
	}

	/**
	 * @param srNo the srNo to set
	 */
	public void setSrNo(final String srNo) {
		this.srNo = srNo;
	}

	/**
	 * @return the creditHeadCode
	 */
	public String getCreditHeadCode() {
		return this.creditHeadCode;
	}

	/**
	 * @param creditHeadCode the creditHeadCode to set
	 */
	public void setCreditHeadCode(final String creditHeadCode) {
		this.creditHeadCode = creditHeadCode;
	}

	/**
	 * @return the creditHead
	 */
	public String getCreditHead() {
		return this.creditHead;
	}

	/**
	 * @param creditHead the creditHead to set
	 */
	public void setCreditHead(final String creditHead) {
		this.creditHead = creditHead;
	}

	/**
	 * @return the creditFormula
	 */
	public String getCreditFormula() {
		return this.creditFormula;
	}

	/**
	 * @param creditFormula the creditFormula to set
	 */
	public void setCreditFormula(final String creditFormula) {
		this.creditFormula = creditFormula;
	}

	/**
	 * @return the pensionValue
	 */
	public String getPensionValue() {
		return this.pensionValue;
	}

	/**
	 * @param pensionValue the pensionValue to set
	 */
	public void setPensionValue(final String pensionValue) {
		this.pensionValue = pensionValue;
	}

	/**
	 * @return the paraId
	 */
	public String getParaId() {
		return this.paraId;
	}

	/**
	 * @param paraId the paraId to set
	 */
	public void setParaId(final String paraId) {
		this.paraId = paraId;
	}

	/**
	 * @return the creditHeadItt
	 */
	public String getCreditHeadItt() {
		return this.creditHeadItt;
	}

	/**
	 * @param creditHeadItt the creditHeadItt to set
	 */
	public void setCreditHeadItt(final String creditHeadItt) {
		this.creditHeadItt = creditHeadItt;
	}

	/**
	 * @return the creditFormulaItt
	 */
	public String getCreditFormulaItt() {
		return this.creditFormulaItt;
	}

	/**
	 * @param creditFormulaItt the creditFormulaItt to set
	 */
	public void setCreditFormulaItt(final String creditFormulaItt) {
		this.creditFormulaItt = creditFormulaItt;
	}

	/**
	 * @return the valueItt
	 */
	public String getValueItt() {
		return this.valueItt;
	}

	/**
	 * @return the creditDetailsList
	 */
	public List<PensionConfiguration> getCreditDetailsList() {
		return this.creditDetailsList;
	}

	/**
	 * @param creditDetailsList the creditDetailsList to set
	 */
	public void setCreditDetailsList(final List<PensionConfiguration> creditDetailsList) {
		this.creditDetailsList = creditDetailsList;
	}

	
	
	
	/**
	 * @param valueItt the valueItt to set
	 */
	public void setValueItt(final String valueItt) {
		this.valueItt = valueItt;
	}

	public String getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
	}

	public String getMyPage() {
		return myPage;
	}

	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}

	public List getLstPensionConfiguration() {
		return lstPensionConfiguration;
	}

	public void setLstPensionConfiguration(List lstPensionConfiguration) {
		this.lstPensionConfiguration = lstPensionConfiguration;
	}

	public String getSPensionCode() {
		return sPensionCode;
	}

	public void setSPensionCode(String pensionCode) {
		sPensionCode = pensionCode;
	}

	public String getSPensionTypeCode() {
		return sPensionTypeCode;
	}

	public void setSPensionTypeCode(String pensionTypeCode) {
		sPensionTypeCode = pensionTypeCode;
	}

	public String getSMode() {
		return sMode;
	}

	public void setSMode(String mode) {
		sMode = mode;
	}

	public String getHiddencode() {
		return hiddencode;
	}

	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}

	public String getSSrNo() {
		return sSrNo;
	}

	public void setSSrNo(String srNo) {
		sSrNo = srNo;
	}

	public String getSPensionEffFrm() {
		return sPensionEffFrm;
	}

	public void setSPensionEffFrm(String pensionEffFrm) {
		sPensionEffFrm = pensionEffFrm;
	}

	public String getSPensionMinService() {
		return sPensionMinService;
	}

	public void setSPensionMinService(String pensionMinService) {
		sPensionMinService = pensionMinService;
	}

	public String getSPensionMaxService() {
		return sPensionMaxService;
	}

	public void setSPensionMaxService(String pensionMaxService) {
		sPensionMaxService = pensionMaxService;
	}

	public String getSPensionMinAmt() {
		return sPensionMinAmt;
	}

	public void setSPensionMinAmt(String pensionMinAmt) {
		sPensionMinAmt = pensionMinAmt;
	}

	public String getSPensionEmpStatus() {
		return sPensionEmpStatus;
	}

	public void setSPensionEmpStatus(String pensionEmpStatus) {
		sPensionEmpStatus = pensionEmpStatus;
	}

	public String getSPensionEmolFormula() {
		return sPensionEmolFormula;
	}

	public void setSPensionEmolFormula(String pensionEmolFormula) {
		sPensionEmolFormula = pensionEmolFormula;
	}

	public String getSPensionEmolMonths() {
		return sPensionEmolMonths;
	}

	public void setSPensionEmolMonths(String pensionEmolMonths) {
		sPensionEmolMonths = pensionEmolMonths;
	}

	public String getSPensionAvgEmol() {
		return sPensionAvgEmol;
	}

	public void setSPensionAvgEmol(String pensionAvgEmol) {
		sPensionAvgEmol = pensionAvgEmol;
	}

	public String getSPensionFormula() {
		return sPensionFormula;
	}

	public void setSPensionFormula(String pensionFormula) {
		sPensionFormula = pensionFormula;
	}

	public String getSPensionVolWeiyears() {
		return sPensionVolWeiyears;
	}

	public void setSPensionVolWeiyears(String pensionVolWeiyears) {
		sPensionVolWeiyears = pensionVolWeiyears;
	}

	public String getSPensionCompFormula() {
		return sPensionCompFormula;
	}

	public void setSPensionCompFormula(String pensionCompFormula) {
		sPensionCompFormula = pensionCompFormula;
	}

	public String getSPensionEffFrm1() {
		return sPensionEffFrm1;
	}

	public void setSPensionEffFrm1(String pensionEffFrm1) {
		sPensionEffFrm1 = pensionEffFrm1;
	}

	public String getSPensionMinService1() {
		return sPensionMinService1;
	}

	public void setSPensionMinService1(String pensionMinService1) {
		sPensionMinService1 = pensionMinService1;
	}

	public String getSPensionMaxService1() {
		return sPensionMaxService1;
	}

	public void setSPensionMaxService1(String pensionMaxService1) {
		sPensionMaxService1 = pensionMaxService1;
	}

	public String getSPensionMinAmt1() {
		return sPensionMinAmt1;
	}

	public void setSPensionMinAmt1(String pensionMinAmt1) {
		sPensionMinAmt1 = pensionMinAmt1;
	}

	public String getSPensionEmpStatus1() {
		return sPensionEmpStatus1;
	}

	public void setSPensionEmpStatus1(String pensionEmpStatus1) {
		sPensionEmpStatus1 = pensionEmpStatus1;
	}

	public String getSPensionEmolFormula1() {
		return sPensionEmolFormula1;
	}

	public void setSPensionEmolFormula1(String pensionEmolFormula1) {
		sPensionEmolFormula1 = pensionEmolFormula1;
	}

	public String getSPensionEmolMonths1() {
		return sPensionEmolMonths1;
	}

	public void setSPensionEmolMonths1(String pensionEmolMonths1) {
		sPensionEmolMonths1 = pensionEmolMonths1;
	}

	public String getSPensionAvgEmol1() {
		return sPensionAvgEmol1;
	}

	public void setSPensionAvgEmol1(String pensionAvgEmol1) {
		sPensionAvgEmol1 = pensionAvgEmol1;
	}

	public String getSPensionFormula1() {
		return sPensionFormula1;
	}

	public void setSPensionFormula1(String pensionFormula1) {
		sPensionFormula1 = pensionFormula1;
	}

	public String getSPensionVolWeiyears1() {
		return sPensionVolWeiyears1;
	}

	public void setSPensionVolWeiyears1(String pensionVolWeiyears1) {
		sPensionVolWeiyears1 = pensionVolWeiyears1;
	}

	public String getSPensionCompFormula1() {
		return sPensionCompFormula1;
	}

	public void setSPensionCompFormula1(String pensionCompFormula1) {
		sPensionCompFormula1 = pensionCompFormula1;
	}

	public String getSPensionEffFrm2() {
		return sPensionEffFrm2;
	}

	public void setSPensionEffFrm2(String pensionEffFrm2) {
		sPensionEffFrm2 = pensionEffFrm2;
	}

	public String getSPensionMinService2() {
		return sPensionMinService2;
	}

	public void setSPensionMinService2(String pensionMinService2) {
		sPensionMinService2 = pensionMinService2;
	}

	public String getSPensionMaxService2() {
		return sPensionMaxService2;
	}

	public void setSPensionMaxService2(String pensionMaxService2) {
		sPensionMaxService2 = pensionMaxService2;
	}

	public String getSPensionMinAmt2() {
		return sPensionMinAmt2;
	}

	public void setSPensionMinAmt2(String pensionMinAmt2) {
		sPensionMinAmt2 = pensionMinAmt2;
	}

	public String getSPensionEmpStatus2() {
		return sPensionEmpStatus2;
	}

	public void setSPensionEmpStatus2(String pensionEmpStatus2) {
		sPensionEmpStatus2 = pensionEmpStatus2;
	}

	public String getSPensionEmolFormula2() {
		return sPensionEmolFormula2;
	}

	public void setSPensionEmolFormula2(String pensionEmolFormula2) {
		sPensionEmolFormula2 = pensionEmolFormula2;
	}

	public String getSPensionEmolMonths2() {
		return sPensionEmolMonths2;
	}

	public void setSPensionEmolMonths2(String pensionEmolMonths2) {
		sPensionEmolMonths2 = pensionEmolMonths2;
	}

	public String getSPensionAvgEmol2() {
		return sPensionAvgEmol2;
	}

	public void setSPensionAvgEmol2(String pensionAvgEmol2) {
		sPensionAvgEmol2 = pensionAvgEmol2;
	}

	public String getSPensionFormula2() {
		return sPensionFormula2;
	}

	public void setSPensionFormula2(String pensionFormula2) {
		sPensionFormula2 = pensionFormula2;
	}

	public String getSPensionVolWeiyears2() {
		return sPensionVolWeiyears2;
	}

	public void setSPensionVolWeiyears2(String pensionVolWeiyears2) {
		sPensionVolWeiyears2 = pensionVolWeiyears2;
	}

	public String getSPensionCompFormula2() {
		return sPensionCompFormula2;
	}

	public void setSPensionCompFormula2(String pensionCompFormula2) {
		sPensionCompFormula2 = pensionCompFormula2;
	}

	public String getSPensionEffFrm3() {
		return sPensionEffFrm3;
	}

	public void setSPensionEffFrm3(String pensionEffFrm3) {
		sPensionEffFrm3 = pensionEffFrm3;
	}

	public String getSPensionMinService3() {
		return sPensionMinService3;
	}

	public void setSPensionMinService3(String pensionMinService3) {
		sPensionMinService3 = pensionMinService3;
	}

	public String getSPensionMaxService3() {
		return sPensionMaxService3;
	}

	public void setSPensionMaxService3(String pensionMaxService3) {
		sPensionMaxService3 = pensionMaxService3;
	}

	public String getSPensionMinAmt3() {
		return sPensionMinAmt3;
	}

	public void setSPensionMinAmt3(String pensionMinAmt3) {
		sPensionMinAmt3 = pensionMinAmt3;
	}

	public String getSPensionEmpStatus3() {
		return sPensionEmpStatus3;
	}

	public void setSPensionEmpStatus3(String pensionEmpStatus3) {
		sPensionEmpStatus3 = pensionEmpStatus3;
	}

	public String getSPensionEmolFormula3() {
		return sPensionEmolFormula3;
	}

	public void setSPensionEmolFormula3(String pensionEmolFormula3) {
		sPensionEmolFormula3 = pensionEmolFormula3;
	}

	public String getSPensionEmolMonths3() {
		return sPensionEmolMonths3;
	}

	public void setSPensionEmolMonths3(String pensionEmolMonths3) {
		sPensionEmolMonths3 = pensionEmolMonths3;
	}

	public String getSPensionAvgEmol3() {
		return sPensionAvgEmol3;
	}

	public void setSPensionAvgEmol3(String pensionAvgEmol3) {
		sPensionAvgEmol3 = pensionAvgEmol3;
	}

	public String getSPensionFormula3() {
		return sPensionFormula3;
	}

	public void setSPensionFormula3(String pensionFormula3) {
		sPensionFormula3 = pensionFormula3;
	}

	public String getSPensionVolWeiyears3() {
		return sPensionVolWeiyears3;
	}

	public void setSPensionVolWeiyears3(String pensionVolWeiyears3) {
		sPensionVolWeiyears3 = pensionVolWeiyears3;
	}

	public String getSPensionCompFormula3() {
		return sPensionCompFormula3;
	}

	public void setSPensionCompFormula3(String pensionCompFormula3) {
		sPensionCompFormula3 = pensionCompFormula3;
	}

	public String getSPensionEffFrm4() {
		return sPensionEffFrm4;
	}

	public void setSPensionEffFrm4(String pensionEffFrm4) {
		sPensionEffFrm4 = pensionEffFrm4;
	}

	public String getSPensionMinService4() {
		return sPensionMinService4;
	}

	public void setSPensionMinService4(String pensionMinService4) {
		sPensionMinService4 = pensionMinService4;
	}

	public String getSPensionMaxService4() {
		return sPensionMaxService4;
	}

	public void setSPensionMaxService4(String pensionMaxService4) {
		sPensionMaxService4 = pensionMaxService4;
	}

	public String getSPensionMinAmt4() {
		return sPensionMinAmt4;
	}

	public void setSPensionMinAmt4(String pensionMinAmt4) {
		sPensionMinAmt4 = pensionMinAmt4;
	}

	public String getSPensionEmpStatus4() {
		return sPensionEmpStatus4;
	}

	public void setSPensionEmpStatus4(String pensionEmpStatus4) {
		sPensionEmpStatus4 = pensionEmpStatus4;
	}

	public String getSPensionEmolFormula4() {
		return sPensionEmolFormula4;
	}

	public void setSPensionEmolFormula4(String pensionEmolFormula4) {
		sPensionEmolFormula4 = pensionEmolFormula4;
	}

	public String getSPensionEmolMonths4() {
		return sPensionEmolMonths4;
	}

	public void setSPensionEmolMonths4(String pensionEmolMonths4) {
		sPensionEmolMonths4 = pensionEmolMonths4;
	}

	public String getSPensionAvgEmol4() {
		return sPensionAvgEmol4;
	}

	public void setSPensionAvgEmol4(String pensionAvgEmol4) {
		sPensionAvgEmol4 = pensionAvgEmol4;
	}

	public String getSPensionFormula4() {
		return sPensionFormula4;
	}

	public void setSPensionFormula4(String pensionFormula4) {
		sPensionFormula4 = pensionFormula4;
	}

	public String getSPensionVolWeiyears4() {
		return sPensionVolWeiyears4;
	}

	public void setSPensionVolWeiyears4(String pensionVolWeiyears4) {
		sPensionVolWeiyears4 = pensionVolWeiyears4;
	}

	public String getSPensionCompFormula4() {
		return sPensionCompFormula4;
	}

	public void setSPensionCompFormula4(String pensionCompFormula4) {
		sPensionCompFormula4 = pensionCompFormula4;
	}

	public String getPrePensionTypeCode() {
		return prePensionTypeCode;
	}

	public void setPrePensionTypeCode(String prePensionTypeCode) {
		this.prePensionTypeCode = prePensionTypeCode;
	}

	public String getSPensionAmtCreditCode1() {
		return sPensionAmtCreditCode1;
	}

	public void setSPensionAmtCreditCode1(String pensionAmtCreditCode1) {
		sPensionAmtCreditCode1 = pensionAmtCreditCode1;
	}

	public String getSPensionAmtCreditValue1() {
		return sPensionAmtCreditValue1;
	}

	public void setSPensionAmtCreditValue1(String pensionAmtCreditValue1) {
		sPensionAmtCreditValue1 = pensionAmtCreditValue1;
	}

	public String getSPensionAmtCreditCode2() {
		return sPensionAmtCreditCode2;
	}

	public void setSPensionAmtCreditCode2(String pensionAmtCreditCode2) {
		sPensionAmtCreditCode2 = pensionAmtCreditCode2;
	}

	public String getSPensionAmtCreditValue2() {
		return sPensionAmtCreditValue2;
	}

	public void setSPensionAmtCreditValue2(String pensionAmtCreditValue2) {
		sPensionAmtCreditValue2 = pensionAmtCreditValue2;
	}

	public String getSPensionAmtCreditCode3() {
		return sPensionAmtCreditCode3;
	}

	public void setSPensionAmtCreditCode3(String pensionAmtCreditCode3) {
		sPensionAmtCreditCode3 = pensionAmtCreditCode3;
	}

	public String getSPensionAmtCreditValue3() {
		return sPensionAmtCreditValue3;
	}

	public void setSPensionAmtCreditValue3(String pensionAmtCreditValue3) {
		sPensionAmtCreditValue3 = pensionAmtCreditValue3;
	}

	public String getSPensionAmtCreditHead1() {
		return sPensionAmtCreditHead1;
	}

	public void setSPensionAmtCreditHead1(String pensionAmtCreditHead1) {
		sPensionAmtCreditHead1 = pensionAmtCreditHead1;
	}

	public String getSPensionAmtCreditHead2() {
		return sPensionAmtCreditHead2;
	}

	public void setSPensionAmtCreditHead2(String pensionAmtCreditHead2) {
		sPensionAmtCreditHead2 = pensionAmtCreditHead2;
	}

	public String getSPensionAmtCreditHead3() {
		return sPensionAmtCreditHead3;
	}

	public void setSPensionAmtCreditHead3(String pensionAmtCreditHead3) {
		sPensionAmtCreditHead3 = pensionAmtCreditHead3;
	}

	/**
	 * @return the sPensionDeleteCode
	 */
	public String getSPensionDeleteCode() {
		return this.sPensionDeleteCode;
	}

	/**
	 * @param pensionDeleteCode the sPensionDeleteCode to set
	 */
	public void setSPensionDeleteCode(final String pensionDeleteCode) {
		sPensionDeleteCode = pensionDeleteCode;
	}

	/**
	 * @return the editId
	 */
	public String getEditDataId() {
		return this.editDataId;
	}

	/**
	 * @param editId the editId to set
	 */
	public void setEditDataId(final String editDataId) {
		this.editDataId = editDataId;
	}

	
}
