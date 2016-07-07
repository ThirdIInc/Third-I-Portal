package org.paradyne.model.payroll.incometax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.incometax.InvestmentVerification;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;


public class InvestmentVerificationModel extends ModelBase {
static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(InvestmentVerificationModel.class);

	/** Fetches the investment details for the selected period & employee/division.
	 * @param invbean
	 * @param request
	 */
	public final void investmentDetails(final InvestmentVerification invbean, final HttpServletRequest request) {
		try {
			HashMap investmentMap=null;
			
			String invDtlquery = "SELECT NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') NAME, "
					+ " NVL(INV_NAME,' '), NVL(INV_SECTION,' '), NVL(INV_CHAPTER,' '), TO_CHAR(NVL(INV_VALID_AMOUNT,0),9999999990.99), 'Y', NVL(INV_UPLOAD,' '), "     //INV_PROOF_ATTACH, NVL(INV_UPLOAD,' '), "
					+ " INV_IS_VERIFIED, TO_CHAR(NVL(INV_VERIFIED_AMOUNT,0),9999999990.99), INV_ID,"
					+ " HRMS_EMP_OFFC.EMP_ID, HRMS_TAX_INVESTMENT.INV_GROUP"
					+ " FROM HRMS_EMP_INVESTMENT "
					+ " INNER JOIN HRMS_TAX_INVESTMENT ON(HRMS_TAX_INVESTMENT.INV_CODE=HRMS_EMP_INVESTMENT.INV_CODE)"
					+ " INNER JOIN HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_INVESTMENT.EMP_ID)"
					+ " WHERE 1=1 AND HRMS_TAX_INVESTMENT.INV_TYPE='I' AND (NVL(INV_VERIFIED_AMOUNT,0)>0 OR NVL(INV_VALID_AMOUNT,0)>0) ";
					if (!invbean.getFromYear().equals("")){
						invDtlquery += " AND INV_FINYEAR_FROM=" + invbean.getFromYear();
					}
					if (!invbean.getToYear().equals("")){
						invDtlquery += " AND INV_FINYEAR_TO=" + invbean.getToYear();
					}
					if (invbean.getEmpId()!=null && !invbean.getEmpId().equals("")){
						invDtlquery += " AND HRMS_EMP_INVESTMENT.EMP_ID="+ invbean.getEmpId();
					}
					
					if(!invbean.isSettlementFlag()){
						if (!invbean.getDivId().equals("")){
							invDtlquery += " AND HRMS_EMP_OFFC.EMP_DIV="+ invbean.getDivId();
						}
						if (!invbean.getDeptId().equals("")){
							invDtlquery += " AND HRMS_EMP_OFFC.EMP_DEPT="+ invbean.getDeptId();
						}
						
						if (!invbean.getBranchId().equals("")){
							invDtlquery += "  AND HRMS_EMP_OFFC.EMP_CENTER="+ invbean.getBranchId();
						}
						
						if (!invbean.getEstatus().equals("")){
							if (!invbean.getEstatus().equals("A")) {
								invDtlquery += " AND HRMS_EMP_OFFC.EMP_STATUS='"+invbean.getEstatus()+"'";
							}
						}
						if (!(invbean.getEmpId()!=null && !invbean.getEmpId().equals(""))){
							if(invbean.getListType().equals("N")){ 
								logger.info(" non verified..!!"+invbean.getListType());
								invDtlquery += " AND INV_IS_VERIFIED='N' ";
							} else if(invbean.getListType().equals("V")){ 
								logger.info(" verified..!!"+invbean.getListType());
								invDtlquery += " AND INV_IS_VERIFIED='Y' ";
							}
						}
						
						if(!invbean.getInvestmentCode().equals("")){
							invDtlquery+= " AND HRMS_EMP_INVESTMENT.INV_CODE ="+invbean.getInvestmentCode();
						}
						if (!invbean.getPaybillId().equals("")) {
							invDtlquery += " AND HRMS_EMP_OFFC.EMP_PAYBILL =" + invbean.getPaybillId();
						}
					}
					invDtlquery +=" ORDER BY HRMS_TAX_INVESTMENT.INV_GROUP"; 
					
					investmentMap = getSqlModel().getSingleResultMap(invDtlquery, 11, 2 );
					
					Object[][] exemptionsObj=null;
					Object[][] otherObj=null;
					Object[][] deductionsVIAObj=null;
					Object[][] deductionsVIObj=null;
					try {
						exemptionsObj = (Object[][]) investmentMap.get("E");
						otherObj = (Object[][]) investmentMap.get("O");
						deductionsVIAObj = (Object[][]) investmentMap.get("D");
						deductionsVIObj = (Object[][]) investmentMap.get("S");
					} catch (Exception e) {
						e.printStackTrace();
					}
					/*Setting Pagination variables if emp division & invest name is selected*/
					
					String[] pageIndex = null;
					int iteratorInitCount = 0;
					if (!(invbean.getEmpId()!=null && !invbean.getEmpId().equals(""))){
						
						int totalRecLength = 0;
						
						if(exemptionsObj != null && exemptionsObj.length > 0){
							totalRecLength = exemptionsObj.length;
						}
						if(otherObj != null && otherObj.length > 0){
							totalRecLength = otherObj.length;
						}
						if(deductionsVIAObj != null && deductionsVIAObj.length > 0){
							totalRecLength = deductionsVIAObj.length;
						}
						if(deductionsVIObj != null && deductionsVIObj.length > 0){
							totalRecLength = deductionsVIObj.length;
						}
						pageIndex = Utility.doPaging(invbean.getMyPage(), totalRecLength, 20);	
						
						if(pageIndex==null){
							pageIndex[0] = "0";
							pageIndex[1] ="20";
							pageIndex[2] = "1";
							pageIndex[3] = "1";
							pageIndex[4] = "";
						}
						
						request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
						request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
						
						 if(pageIndex[4].equals("1")){
							 invbean.setMyPage("1");
						 }
						 iteratorInitCount = Integer.parseInt(pageIndex[0]);
						 invbean.setPaginationFlag(true);
					}
					int dataObjLength = 0;
					if(exemptionsObj != null && exemptionsObj.length > 0){
						if (invbean.getEmpId()!=null && !invbean.getEmpId().equals("")){
							dataObjLength = exemptionsObj.length;
						}else{
							dataObjLength = Integer.parseInt(pageIndex[1]);
						}
						ArrayList exemptionsList = new ArrayList();
						
						for (int i = iteratorInitCount; i < dataObjLength; i++) {
							InvestmentVerification bean = new InvestmentVerification();
							
							bean.setExemptionsEmpNameItt(String.valueOf(exemptionsObj[i][0]));
							bean.setExemptionsInvestNameItt(String.valueOf(exemptionsObj[i][1]));
							bean.setExemptionsInvSectionNameItt(String.valueOf(exemptionsObj[i][2]));
							bean.setExemptionsChapterNameItt(String.valueOf(exemptionsObj[i][3]));
							bean.setExemptionsInvAmountItt(String.valueOf(exemptionsObj[i][4]));
							bean.setExemptionsProofAttachedItt(String.valueOf(exemptionsObj[i][5]));
							
							if(String.valueOf(exemptionsObj[i][5]).equals("Y")){
								String[] totalAttachments = String.valueOf(exemptionsObj[i][6]).split(",");
								ArrayList attachmentList = new ArrayList();
								if(totalAttachments != null && totalAttachments.length > 0 ){
									for (int j = 0; j < totalAttachments.length; j++) {
										InvestmentVerification attachmentBean = new InvestmentVerification();
										attachmentBean.setExemptionsViewDocItt(totalAttachments[j]);
										attachmentList.add(attachmentBean);
									}
								}
								bean.setExemptionsAttachmentList(attachmentList);
							}
							
							bean.setExemptionsVerifiedItt(String.valueOf(exemptionsObj[i][7]));
							bean.setExemptionsVerifiedAmountItt(String.valueOf(exemptionsObj[i][8]));
							if(String.valueOf(exemptionsObj[i][5]).equals("Y")){
								bean.setExemptionsAttachViewFlagItt(true);
							}else{
								bean.setExemptionsAttachViewFlagItt(false);
							}
							bean.setExemptionsInvestmentIdItt(String.valueOf(exemptionsObj[i][9]));
							bean.setExemptionsEmpIdItt(String.valueOf(exemptionsObj[i][10]));
							exemptionsList.add(bean);
						}
						invbean.setExemptionsInvestmentList(exemptionsList);
						invbean.setExemptionsListViewFlag(true);
					}
					if(otherObj != null && otherObj.length > 0){
						if (invbean.getEmpId()!=null && !invbean.getEmpId().equals("")){
							dataObjLength = otherObj.length;
						}else{
							dataObjLength = Integer.parseInt(pageIndex[1]);
						}
						ArrayList otherList = new ArrayList();
						
						for (int i = iteratorInitCount; i < dataObjLength; i++) {
							InvestmentVerification bean = new InvestmentVerification();
							
							bean.setOtherEmpNameItt(String.valueOf(otherObj[i][0]));
							bean.setOtherInvestNameItt(String.valueOf(otherObj[i][1]));
							bean.setOtherInvSectionNameItt(String.valueOf(otherObj[i][2]));
							bean.setOtherChapterNameItt(String.valueOf(otherObj[i][3]));
							bean.setOtherInvAmountItt(String.valueOf(otherObj[i][4]));
							bean.setOtherProofAttachedItt(String.valueOf(otherObj[i][5]));
							
							if(String.valueOf(otherObj[i][5]).equals("Y")){
								String[] totalAttachments = String.valueOf(otherObj[i][6]).split(",");
								ArrayList attachmentList = new ArrayList();
								if(totalAttachments != null && totalAttachments.length > 0 ){
									for (int j = 0; j < totalAttachments.length; j++) {
										InvestmentVerification attachmentBean = new InvestmentVerification();
										attachmentBean.setOtherViewDocItt(totalAttachments[j]);
										attachmentList.add(attachmentBean);
									}
								}
								bean.setOtherAttachmentList(attachmentList);
							}
							
							bean.setOtherViewDocItt(String.valueOf(otherObj[i][6]));
							bean.setOtherVerifiedItt(String.valueOf(otherObj[i][7]));
							bean.setOtherVerifiedAmountItt(String.valueOf(otherObj[i][8]));
							if(String.valueOf(otherObj[i][5]).equals("Y")){
								bean.setOtherAttachViewFlagItt(true);
							}else{
								bean.setOtherAttachViewFlagItt(false);
							}
							bean.setOtherInvestmentIdItt(String.valueOf(otherObj[i][9]));
							bean.setOtherEmpIdItt(String.valueOf(otherObj[i][10]));
							otherList.add(bean);
						}
						invbean.setOtherInvestmentList(otherList);
						invbean.setOtherListViewFlag(true);
					}
					if(deductionsVIAObj != null && deductionsVIAObj.length > 0){
						if (invbean.getEmpId()!=null && !invbean.getEmpId().equals("")){
							dataObjLength = deductionsVIAObj.length;
						}else{
							dataObjLength = Integer.parseInt(pageIndex[1]);
						}
						ArrayList deductionsVIAList = new ArrayList();
						
						for (int i = iteratorInitCount; i < dataObjLength; i++) {
							InvestmentVerification bean = new InvestmentVerification();
							
							bean.setDeductionsVIAEmpNameItt(String.valueOf(deductionsVIAObj[i][0]));
							bean.setDeductionsVIAInvestNameItt(String.valueOf(deductionsVIAObj[i][1]));
							bean.setDeductionsVIAInvSectionNameItt(String.valueOf(deductionsVIAObj[i][2]));
							bean.setDeductionsVIAChapterNameItt(String.valueOf(deductionsVIAObj[i][3]));
							bean.setDeductionsVIAInvAmountItt(String.valueOf(deductionsVIAObj[i][4]));
							bean.setDeductionsVIAProofAttachedItt(String.valueOf(deductionsVIAObj[i][5]));
							
							if(String.valueOf(deductionsVIAObj[i][5]).equals("Y")){
								String[] totalAttachments = String.valueOf(deductionsVIAObj[i][6]).split(",");
								ArrayList attachmentList = new ArrayList();
								if(totalAttachments != null && totalAttachments.length > 0 ){
									for (int j = 0; j < totalAttachments.length; j++) {
										InvestmentVerification attachmentBean = new InvestmentVerification();
										attachmentBean.setDeductionsVIAViewDocItt(totalAttachments[j]);
										attachmentList.add(attachmentBean);
									}
								}
								bean.setDeductionsVIAAttachmentList(attachmentList);
							}
							
							bean.setDeductionsVIAViewDocItt(String.valueOf(deductionsVIAObj[i][6]));
							bean.setDeductionsVIAVerifiedItt(String.valueOf(deductionsVIAObj[i][7]));
							bean.setDeductionsVIAVerifiedAmountItt(String.valueOf(deductionsVIAObj[i][8]));
							if(String.valueOf(deductionsVIAObj[i][5]).equals("Y")){
								bean.setDeductionsVIAAttachViewFlagItt(true);
							}else{
								bean.setDeductionsVIAAttachViewFlagItt(false);
							}
							bean.setDeductionsVIAInvestmentIdItt(String.valueOf(deductionsVIAObj[i][9]));
							bean.setDeductionsVIAEmpIdItt(String.valueOf(deductionsVIAObj[i][10]));
							deductionsVIAList.add(bean);
						}
						invbean.setDeductionsVIAInvestmentList(deductionsVIAList);
						invbean.setDeductionsVIAListViewFlag(true);
					}
					if(deductionsVIObj != null && deductionsVIObj.length > 0){
						if (invbean.getEmpId()!=null && !invbean.getEmpId().equals("")){
							dataObjLength = deductionsVIObj.length;
						}else{
							dataObjLength = Integer.parseInt(pageIndex[1]);
						}
						ArrayList deductionsVIList = new ArrayList();
						
						for (int i = iteratorInitCount; i < dataObjLength; i++) {
							InvestmentVerification bean = new InvestmentVerification();
							
							bean.setDeductionsVIEmpNameItt(String.valueOf(deductionsVIObj[i][0]));
							bean.setDeductionsVIInvestNameItt(String.valueOf(deductionsVIObj[i][1]));
							bean.setDeductionsVIInvSectionNameItt(String.valueOf(deductionsVIObj[i][2]));
							bean.setDeductionsVIChapterNameItt(String.valueOf(deductionsVIObj[i][3]));
							bean.setDeductionsVIInvAmountItt(String.valueOf(deductionsVIObj[i][4]));
							bean.setDeductionsVIProofAttachedItt(String.valueOf(deductionsVIObj[i][5]));
							
							if(String.valueOf(deductionsVIObj[i][5]).equals("Y")){
								String[] totalAttachments = String.valueOf(deductionsVIObj[i][6]).split(",");
								ArrayList attachmentList = new ArrayList();
								if(totalAttachments != null && totalAttachments.length > 0 ){
									for (int j = 0; j < totalAttachments.length; j++) {
										InvestmentVerification attachmentBean = new InvestmentVerification();
										attachmentBean.setDeductionsVIViewDocItt(totalAttachments[j]);
										attachmentList.add(attachmentBean);
									}
								}
								bean.setDeductionsVIAttachmentList(attachmentList);
							}
							bean.setDeductionsVIViewDocItt(String.valueOf(deductionsVIObj[i][6]));
							bean.setDeductionsVIVerifiedItt(String.valueOf(deductionsVIObj[i][7]));
							bean.setDeductionsVIVerifiedAmountItt(String.valueOf(deductionsVIObj[i][8]));
							if(String.valueOf(deductionsVIObj[i][5]).equals("Y")){
								bean.setDeductionsVIAttachViewFlagItt(true);
							}else{
								bean.setDeductionsVIAttachViewFlagItt(false);
							}
							bean.setDeductionsVIInvestmentIdItt(String.valueOf(deductionsVIObj[i][9]));
							bean.setDeductionsVIEmpIdItt(String.valueOf(deductionsVIObj[i][10]));
							deductionsVIList.add(bean);
						}
						invbean.setDeductionsVIInvestmentList(deductionsVIList);
						invbean.setDeductionsVIListViewFlag(true);
					}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (invbean.getEmpId()!=null && !invbean.getEmpId().equals("")){
			fetchEmployeeDonationDetails(invbean);
			fetchMonthlyAccomodationInvestmentDetails(invbean, invbean.getEmpId());
		}
		invbean.setEmpListFlag(true);
	}
	
	/** This function fetches the donation details of the employee.
	 * @param invbean - invbean
	 */
	public final void fetchEmployeeDonationDetails(final InvestmentVerification invbean){
		try {
			String query = "SELECT NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') NAME,"
					+ " NVL(DONATION_NAME,' '), NVL(DONATION_PER,0), TO_CHAR(NVL(HRMS_EMP_INV_DONATION.INV_VALID_AMOUNT,0),9999999990.99),"
					+ " 'Y', NVL(HRMS_EMP_INV_DONATION.INV_UPLOAD,' '), HRMS_EMP_INV_DONATION.INV_IS_VERIFIED,"
					+ " TO_CHAR(NVL(HRMS_EMP_INV_DONATION.INV_VERIFIED_AMOUNT,0),9999999990.99), HRMS_EMP_INV_DONATION.DONATION_CODE , HRMS_EMP_INV_DONATION.EMP_ID"
					+ " FROM HRMS_EMP_INV_DONATION "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_INV_DONATION.EMP_ID)"
					+ " LEFT JOIN HRMS_DONATION ON (HRMS_DONATION.DONATION_ID = HRMS_EMP_INV_DONATION.DONATION_CODE)"
					+ " WHERE 1=1 AND (NVL(HRMS_EMP_INV_DONATION.INV_VERIFIED_AMOUNT,0)>0 OR NVL(HRMS_EMP_INV_DONATION.INV_VALID_AMOUNT,0)>0)"
					+ " AND INV_FINYEAR_FROM=" + invbean.getFromYear()+" AND INV_FINYEAR_TO=" + invbean.getToYear()
					+ " AND HRMS_EMP_INV_DONATION.EMP_ID="+ invbean.getEmpId();
			
			Object[][] donationObject = getSqlModel().getSingleResult(query);
			
			if(donationObject != null && donationObject.length > 0){
				ArrayList donationList = new ArrayList();
				
				for (int i = 0; i < donationObject.length; i++) {
					InvestmentVerification bean = new InvestmentVerification();
					
					bean.setDonationEmpNameItt(String.valueOf(donationObject[i][0]));
					bean.setDonationNameItt(String.valueOf(donationObject[i][1]));
					bean.setDonationExemptionPercentageItt(String.valueOf(donationObject[i][2]));
					bean.setDonationInvAmountItt(String.valueOf(donationObject[i][3]));
					bean.setDonationProofAttachedItt(String.valueOf(donationObject[i][4]));
					
					if(String.valueOf(donationObject[i][4]).equals("Y")){
						String[] totalAttachments = String.valueOf(donationObject[i][5]).split(",");
						ArrayList attachmentList = new ArrayList();
						if(totalAttachments != null && totalAttachments.length > 0 ){
							for (int j = 0; j < totalAttachments.length; j++) {
								InvestmentVerification attachmentBean = new InvestmentVerification();
								attachmentBean.setDonationViewDocItt(totalAttachments[j]);
								attachmentList.add(attachmentBean);
							}
						}
						bean.setDonationAttachmentList(attachmentList);
					}
					bean.setDonationVerifiedItt(String.valueOf(donationObject[i][6]));
					bean.setDonationVerifiedAmountItt(String.valueOf(donationObject[i][7]));
					if(String.valueOf(donationObject[i][6]).equals("Y")){
						bean.setDonationAttachViewFlagItt(true);
					}else{
						bean.setDonationAttachViewFlagItt(false);
					}
					bean.setDonationIdItt(String.valueOf(donationObject[i][8]));
					bean.setDonationEmpIdItt(String.valueOf(donationObject[i][9]));
					donationList.add(bean);
				}
				invbean.setDonationInvestmentList(donationList);
				invbean.setDonationListViewFlag(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/** This function displays the list of monthly accommodation investments for the period specified
	 * 
	 * @param invbean - invbean
	 */
	
	public final void fetchMonthlyAccomodationInvestmentDetails(final InvestmentVerification invbean, final String empId) {
		
		try {
			String monthlyInvestmentQuery = "SELECT HRMS_HOUSERENT_DTL.RENT_CODE, HRMS_HOUSERENT_HDR.RENT_EMPID,NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '),"
					+ " HRMS_HOUSERENT_DTL.RENT_MONTH, NVL(HRMS_HOUSERENT_DTL.RENT_AMOUNT,0),NVL(HRMS_HOUSERENT_DTL.RENT_AMOUNT_VERIFY,0),NVL(HRMS_HOUSERENT_DTL.IS_METRO,'N'), "
					+ " NVL(HRMS_HOUSERENT_DTL.METRO_VERIFY,'N'),NVL(HRMS_HOUSERENT_DTL.IS_COMP_OWN_HOUSE,'N'),NVL(HRMS_HOUSERENT_DTL.COMP_OWN_HOUSE_VERIFY,'N'),NVL(HRMS_HOUSERENT_DTL.IS_CAR_USED,'N'),"
					+ " NVL(HRMS_HOUSERENT_DTL.CAR_USED_VERIFY,'N'),NVL(HRMS_HOUSERENT_DTL.IS_CUBIC,'N'),NVL(HRMS_HOUSERENT_DTL.CUBIC_VERIFY,'N'),NVL(HRMS_HOUSERENT_DTL.IS_DRIVER,'N'),"
					+ " NVL(HRMS_HOUSERENT_DTL.DRIVER_VERIFY,'N'), NVL(HRMS_HOUSERENT_DTL.IS_HOUSE_RENT_PAID,'N'),NVL(HRMS_HOUSERENT_DTL.HOUSE_RENT_PAID_VERIFY,'N'),"
					+ " NVL(HRMS_HOUSERENT_DTL.IS_IN_INDIA,'N'), NVL(HRMS_HOUSERENT_DTL.IN_INDIA_VERIFY,'N'),NVL(HRMS_HOUSERENT_HDR.EMP_PROOF,' '),"
					+ " TO_CHAR(TO_DATE(RENT_MONTH,'MM'),'Mon')||'-'|| CASE WHEN RENT_MONTH<4 THEN RENT_TOYEAR ELSE RENT_FROMYEAR END AS MONTHYEAR, TO_DATE(RENT_MONTH||'-'||CASE WHEN RENT_MONTH < 4 THEN RENT_TOYEAR ELSE RENT_FROMYEAR END,'MM-yyyy') AS DATE1,"
					+ " NVL(HRMS_HOUSERENT_DTL.IS_POPULATION_HIGHER,'N'), NVL(HRMS_HOUSERENT_DTL.POPULATION_HIGHER_VERIFY,'N') " 
					+ " FROM HRMS_HOUSERENT_DTL "
					+ " INNER JOIN HRMS_HOUSERENT_HDR ON (HRMS_HOUSERENT_HDR.RENT_CODE = HRMS_HOUSERENT_DTL.RENT_CODE)"
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_HOUSERENT_HDR.RENT_EMPID)"
					+ " WHERE HRMS_HOUSERENT_HDR.RENT_FROMYEAR ="+invbean.getFromYear()+" AND HRMS_HOUSERENT_HDR.RENT_TOYEAR ="+invbean.getToYear()
					+ " AND HRMS_HOUSERENT_HDR.RENT_EMPID = "+empId
					+ " ORDER BY DATE1";
		
			Object[][] monthlyObject = getSqlModel().getSingleResult(monthlyInvestmentQuery);
			
			ArrayList list = new ArrayList();
			
			if(monthlyObject!=null && monthlyObject.length >0){
				for (int i = 0; i < monthlyObject.length; i++) {
					InvestmentVerification bean = new InvestmentVerification();
					
					bean.setMonthlyRentCodeItt(String.valueOf(monthlyObject[i][0])); 
					bean.setMonthlyEmployeeIdItt(String.valueOf(monthlyObject[i][1]));  
					bean.setMonthlyEmployeeNameItt(String.valueOf(monthlyObject[i][2])); 
					bean.setInvestmentMonthItt(String.valueOf(monthlyObject[i][3]));
					bean.setMonthlyRentPaidDeclaredItt(String.valueOf(monthlyObject[i][4]));  
					bean.setMonthlyRentPaidVerifiedItt(String.valueOf(monthlyObject[i][5]));
					if(String.valueOf(monthlyObject[i][6]).equals("Y")){
						bean.setMonthlyIsMetroDeclaredItt("true");  
					}else{
						bean.setMonthlyIsMetroDeclaredItt("false");
					}
					bean.setMonthlyIsMetroVerifiedItt(String.valueOf(monthlyObject[i][7])); 
					if(String.valueOf(monthlyObject[i][8]).equals("Y")){
						bean.setMonthlyCompanyOwnedHouseDeclaredItt("true");  
					}else{
						bean.setMonthlyCompanyOwnedHouseDeclaredItt("false");
					}
					bean.setMonthlyCompanyOwnedHouseVerifiedItt(String.valueOf(monthlyObject[i][9])); 
					if(String.valueOf(monthlyObject[i][10]).equals("Y")){
						bean.setMonthlyCarUsedDeclaredItt("true");  
					}else{
						bean.setMonthlyCarUsedDeclaredItt("false");
					}
					bean.setMonthlyCarUsedVerifiedItt(String.valueOf(monthlyObject[i][11]));  
					if(String.valueOf(monthlyObject[i][12]).equals("Y")){
						bean.setMonthlyCubicCapacityDeclaredItt("true");  
					}else{
						bean.setMonthlyCubicCapacityDeclaredItt("false");
					}
					bean.setMonthlyCubicCapacityVerifiedItt(String.valueOf(monthlyObject[i][13])); 
					if(String.valueOf(monthlyObject[i][14]).equals("Y")){
						bean.setMonthlyDriverUsedDeclaredItt("true");  
					}else{
						bean.setMonthlyDriverUsedDeclaredItt("false");
					}
					bean.setMonthlyDriverUsedVerifiedItt(String.valueOf(monthlyObject[i][15]));  
					if(String.valueOf(monthlyObject[i][16]).equals("Y")){
						bean.setMonthlyHouseRentPaidByCompanyDeclaredItt("true");  
					}else{
						bean.setMonthlyHouseRentPaidByCompanyDeclaredItt("false");
					}
					bean.setMonthlyHouseRentPaidByCompanyVerifiedItt(String.valueOf(monthlyObject[i][17])); 
					if(String.valueOf(monthlyObject[i][18]).equals("Y")){
						bean.setMonthlyInIndiaDeclaredItt("true");  
					}else{
						bean.setMonthlyInIndiaDeclaredItt("false");
					}
					bean.setMonthlyInIndiaVerifiedItt(String.valueOf(monthlyObject[i][19]));  
					
					//bean.setMonthlyAttachmentFileItt(String.valueOf(monthlyObject[i][20])); 
					bean.setInvestmentMonthNameItt(String.valueOf(monthlyObject[i][21]));
					
					if(String.valueOf(monthlyObject[i][23]).equals("Y")){
						bean.setMonthlyCityPopulationDeclaredItt("true");  
					}else{
						bean.setMonthlyCityPopulationDeclaredItt("false");
					}
					bean.setMonthlyCityPopulationVerifiedItt(String.valueOf(monthlyObject[i][24]));  
					list.add(bean);
				}
				
				String[] totalAttachments = String.valueOf(monthlyObject[0][20]).split(",");
				ArrayList attachmentList = new ArrayList();
				if(totalAttachments != null && totalAttachments.length > 0 ){
					if(!(totalAttachments[0].trim().equals(""))){
						for (int j = 0; j < totalAttachments.length; j++) {
							InvestmentVerification attachmentBean = new InvestmentVerification();
							attachmentBean.setMonthlyAttachmentFileItt(totalAttachments[j]);
							attachmentList.add(attachmentBean);
						}
						invbean.setProofAttachedFlag(true);
					}
				}
				invbean.setMonthlyAttachmentList(attachmentList);
				invbean.setMonthlyInvestmentList(list);
				invbean.setMonthlyFlag(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**This method sets the parameters to generate the report.
	 * @param invbean
	 * @param response
	 */
	public final void generateReport(final InvestmentVerification invbean,final HttpServletResponse response){
		String invDtlquery = "SELECT EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') NAME, "
				+ " INV_NAME, INV_SECTION, INV_CHAPTER, NVL(INV_VALID_AMOUNT,0), INV_PROOF_ATTACH, "
				//+ " INV_IS_VERIFIED," 
				+ " CASE WHEN INV_IS_VERIFIED='Y' THEN NVL(INV_VERIFIED_AMOUNT,0) ELSE 0 END FROM HRMS_EMP_INVESTMENT "
			//	+ " HRMS_EMP_OFFC.EMP_ID " 
				+ " INNER JOIN HRMS_TAX_INVESTMENT ON(HRMS_TAX_INVESTMENT.INV_CODE=HRMS_EMP_INVESTMENT.INV_CODE)"
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_INVESTMENT.EMP_ID)"
				+ " WHERE 1=1 AND HRMS_TAX_INVESTMENT.INV_TYPE='I' AND (NVL(INV_VERIFIED_AMOUNT,0)>0 OR NVL(INV_VALID_AMOUNT,0)>0)";
		if (!invbean.getToYear().equals("")) {
			invDtlquery += " and INV_FINYEAR_TO=" + invbean.getToYear();
		}
		
		if (!invbean.getFromYear().equals("")) {
			invDtlquery += " and INV_FINYEAR_FROM=" + invbean.getFromYear();
		}
		
		
		if (invbean.getEmpId()!=null && !invbean.getEmpId().equals("")) {
			invDtlquery += " and HRMS_EMP_INVESTMENT.EMP_ID="+ invbean.getEmpId();
		}
		
		if (!invbean.getDeptId().equals("")) {
			invDtlquery += " and HRMS_EMP_OFFC.EMP_DEPT="+ invbean.getDeptId();
		}
		
		if (!invbean.getBranchId().equals("")) {
			invDtlquery += "  and HRMS_EMP_OFFC.EMP_CENTER="+ invbean.getBranchId();
		}
		
		if (!invbean.getDivId().equals("")) {
			invDtlquery += " and HRMS_EMP_OFFC.EMP_DIV="+ invbean.getDivId();
		}
		
		if (!invbean.getEstatus().equals(""))
			{
			if (!invbean.getEstatus().equals("A")) {
				invDtlquery += " and HRMS_EMP_OFFC.EMP_STATUS='"+invbean.getEstatus()+"'";
			}
			}
		
		if(invbean.getListType().equals("N"))
			{ logger.info("u r doing..! non verified..!!"+invbean.getListType());
			invDtlquery += " and INV_IS_VERIFIED='N' ";
			
			}
		else if(invbean.getListType().equals("V"))
		{
			invDtlquery += " and INV_IS_VERIFIED='Y' ";
		}
		invDtlquery +=" order by NAME,INV_SECTION"; 
		
		Object[][] InvDetails = getSqlModel().getSingleResult(invDtlquery);
		Object[][] finalInvDetails=new Object[InvDetails.length][7];
		/*for(int cnt=0;cnt<InvDetails.length;cnt++){
			
			finalInvDetails[cnt][0]=InvDetails[cnt][0];
			finalInvDetails[cnt][1]=InvDetails[cnt][1];
			finalInvDetails[cnt][2]=InvDetails[cnt][2];
			finalInvDetails[cnt][3]=InvDetails[cnt][3];
			finalInvDetails[cnt][4]=InvDetails[cnt][4];
			finalInvDetails[cnt][5]=InvDetails[cnt][5];
			finalInvDetails[cnt][6]=InvDetails[cnt][7];
		}*/
		
		String reportName = "Investment Verification Report";
		String reportType = "Xls";
		org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(reportType, reportName, "A4");
		rg.addTextBold("Investment Verification Report", 0, 1, 0);
		if (!(InvDetails == null || InvDetails.length == 0)) {
			int[] attCellWidth = { 20, 40, 40, 20, 20, 20, 20, 20, };
			int[] attAlign = { 0, 0, 0, 0, 0, 0, 0, 0};
			String[] attCol = {"Employee Id","Employee Name", "Investment name","Section", "Chapter", "Investment Amount",
								"Proof Attach",	"Verified Amount"};
			rg.setFName("Investment Verification Report");
			rg.addFormatedText("\n", 0, 0, 1, 0);
			if (reportType.equals("Xls")) {
				rg.tableBody(attCol, InvDetails, attCellWidth, attAlign);
				rg.addText("\n\n", 0, 0, 0);
			}
		} else {
			rg.addTextBold("No records to display ", 0, 0, 1, 0);
		}
		rg.createReport(response);
	}

	
	/** This function updates the modified verified status of the listed monthly accommodation investments.
	 * 
	 * @param invbean
	 * @param monthlyRentCode
	 * @param monthlyEmployeeId
	 * @param monthlyRent
	 * @param monthlyIsMetro
	 * @param monthlyCompany
	 * @param monthlyCarUsed
	 * @param monthlyCubicCapacity
	 * @param monthlyDriverUsed
	 * @param monthlyHouseRentPaidByCompany
	 * @param monthlyInIndia
	 * @param investmentMonth 
	 * @return -  result
	 */

	public final boolean updateMonthlyAccommodationDetails( final InvestmentVerification invbean, final Object[] monthlyRentCode,	final Object[] monthlyEmployeeId, final Object[] monthlyRent, final Object[] monthlyIsMetro, final Object[] monthlyCompany, Object[] monthlyCityPopulation,
			final Object[] monthlyCarUsed, final Object[] monthlyCubicCapacity, final Object[] monthlyDriverUsed, final Object[] monthlyHouseRentPaidByCompany,	final Object[] monthlyInIndia, Object[] investmentMonth) {
		
		boolean result = false;
		
		String updateQuery = "UPDATE HRMS_HOUSERENT_DTL SET RENT_AMOUNT_VERIFY = ?, METRO_VERIFY = ?, COMP_OWN_HOUSE_VERIFY = ?, CAR_USED_VERIFY = ?, CUBIC_VERIFY = ?, DRIVER_VERIFY = ?, HOUSE_RENT_PAID_VERIFY = ?, " 
			+ " IN_INDIA_VERIFY = ?, POPULATION_HIGHER_VERIFY = ? WHERE RENT_CODE = ? AND RENT_MONTH=?";
		
		Object[][] updateDateObj = new Object[monthlyEmployeeId.length][11];
		
		for (int i = 0; i < monthlyEmployeeId.length; i++) {
			updateDateObj[i][0] = String.valueOf(monthlyRent[i]);
			updateDateObj[i][1] = String.valueOf(monthlyIsMetro[i]);
			updateDateObj[i][2] = String.valueOf(monthlyCompany[i]);
			updateDateObj[i][3] = String.valueOf(monthlyCarUsed[i]);
			updateDateObj[i][4] = String.valueOf(monthlyCubicCapacity[i]);
			updateDateObj[i][5] = String.valueOf(monthlyDriverUsed[i]);
			updateDateObj[i][6] = String.valueOf(monthlyHouseRentPaidByCompany[i]);
			updateDateObj[i][7] = String.valueOf(monthlyInIndia[i]);
			updateDateObj[i][8] = String.valueOf(monthlyCityPopulation[i]);
			updateDateObj[i][9] = String.valueOf(monthlyRentCode[i]);
			updateDateObj[i][10] = String.valueOf(investmentMonth[i]);
		}
		
		result =  getSqlModel().singleExecute(updateQuery,updateDateObj);
		
		return result;
	}

	/** This function updates the verified investment details. 
	 * @param invbean
	 * @param investmentObject
	 * @param donationObject
	 * @return result
	 */
	public final boolean saveVerifiedInvestmentDetails(final InvestmentVerification invbean, final Object[][] investmentObject, final Object[][] donationObject) {
		boolean result = false;
		boolean donationResult = false;
		int totalQuery = 0;
		try {
			Vector<Object[][]> objectVector = new Vector<Object[][]>();
			HashMap<Integer, String> queryMap = new HashMap<Integer, String>();
			
			String updateInvestmentQuery = "UPDATE HRMS_EMP_INVESTMENT SET INV_IS_VERIFIED=?, INV_VERIFIED_AMOUNT=? WHERE INV_ID=? AND EMP_ID=?";
			
			String  updateDonationQuery = "UPDATE HRMS_EMP_INV_DONATION SET INV_IS_VERIFIED=?, INV_VERIFIED_AMOUNT=? WHERE DONATION_CODE=? AND EMP_ID=?";
			
			if(investmentObject != null && investmentObject.length > 0){
				objectVector.add(investmentObject);
				queryMap.put(0, updateInvestmentQuery);
				totalQuery+=1;
			}
			if(donationObject != null && donationObject.length > 0){
				objectVector.add(donationObject);
				queryMap.put(1, updateDonationQuery);
				totalQuery+=1;
			}
			
			Object[] queryArray = new Object[totalQuery];
			
			if(0 < totalQuery){
				for (int i = 0; i < totalQuery; i++) {
					queryArray[i] = queryMap.get(i);
				}
			}
			
			result = getSqlModel().multiExecute(queryArray, objectVector);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}