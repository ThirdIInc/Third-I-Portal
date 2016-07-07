package org.paradyne.model.payroll;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.PFChallan;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;

/**
 * @author Prakash Date:19-08-2009
 */
public class PFChallanModel extends ModelBase {
	
	NumberFormat formatter = new DecimalFormat("#0.00");
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PFChallanModel.class);

	/**
	 * THIS METHOD IS USED FOR SAVING PF CHALLAN RECORD
	 * 
	 * @param pfChallan
	 * @return boolean
	 */
	public boolean savePfChallan(PFChallan pfChallan) {
		boolean queryResult=false;
		try {
			Object insertObj[][] = new Object[1][44];
			String codeQuery = "SELECT NVL(MAX(CHALLAN_CODE),0)+1 FROM HRMS_PF_CHALLAN ";
			Object[][] result = getSqlModel().getSingleResult(codeQuery);
			if (result != null && result.length > 0) {

				pfChallan.setChallanCode(String.valueOf(result[0][0]));

				insertObj[0][0] = pfChallan.getChallanCode();
				insertObj[0][1] = pfChallan.getMonth();
				insertObj[0][2] = pfChallan.getYear();
				insertObj[0][3] = pfChallan.getDivId();
				insertObj[0][4] = pfChallan.getModePayment();
				insertObj[0][5] = pfChallan.getDateOfPay();

				if (pfChallan.getModePayment().equals("CH")
						|| pfChallan.getModePayment().equals("DD")) {

					insertObj[0][6] = pfChallan.getChequeNo();
					insertObj[0][7] = pfChallan.getCheqDate();
					insertObj[0][8] = pfChallan.getBankMicrId();

				} else {

					insertObj[0][6] = "";
					insertObj[0][7] = "01-01-0001";
					insertObj[0][8] = "";
				}
				insertObj[0][9] = pfChallan.getEstabNo();
				insertObj[0][10] = pfChallan.getAccGrpNo();
				insertObj[0][11] = pfChallan.getType();

				logger.info("444444444" + pfChallan.getCheckVpf());

				if (pfChallan.getCheckVpf().equals("on")) {
					insertObj[0][12] = "Y";
					insertObj[0][13] = "0";
				} else {
					insertObj[0][12] = "N";
					insertObj[0][13] = "0";
				}
				insertObj[0][14] = pfChallan.getPfOfficeName();
				/* Added by Prashant*/
				insertObj[0][15] = pfChallan.getEmployersPfContDeductedAc1();
				insertObj[0][16] = pfChallan.getEmployersPensionDeductedAc10();
				insertObj[0][17] = pfChallan.getEmployersEdliDeductedAc21();
				insertObj[0][18] = pfChallan.getEmpPfContDeductedAc1();
				insertObj[0][19] = pfChallan.getAdminPfAdminDeductedAc2();
				insertObj[0][20] = pfChallan.getAdminEdliAdminDeductedAc22();
				insertObj[0][21] = pfChallan.getInspectionPfAdminDeductedAc2();
				insertObj[0][22] = pfChallan.getInspectionEdliAdminDeductedAc22();
				insertObj[0][23] = pfChallan.getSubscribersPfContDeductedAc1();
				insertObj[0][24] = pfChallan.getSubscribersPensionDeductedAc10();
				insertObj[0][25] = pfChallan.getSubscribersEdliDeductedAc21();
				insertObj[0][26] = pfChallan.getWagesPfContDeductedAc1();
				insertObj[0][27] = pfChallan.getWagesPensionDeductedAc10();
				insertObj[0][28] = pfChallan.getWagesEdliDeductedAc21();
				insertObj[0][29] = pfChallan.getEmployersPfContDepositedAc1().equals("")?"0":pfChallan.getEmployersPfContDepositedAc1();
				insertObj[0][30] = pfChallan.getEmployersPensionDepositedAc10().equals("")?"0":pfChallan.getEmployersPensionDepositedAc10();
				insertObj[0][31] = pfChallan.getEmployersEdliDepositedAc21().equals("")?"0":pfChallan.getEmployersEdliDepositedAc21();
				insertObj[0][32] = pfChallan.getEmpPfContDepositedAc1().equals("")?"0":pfChallan.getEmpPfContDepositedAc1();
				insertObj[0][33] = pfChallan.getAdminPfAdminDepositedAc2().equals("")?"0":pfChallan.getAdminPfAdminDepositedAc2();
				insertObj[0][34] = pfChallan.getAdminEdliDepositedAc22().equals("")?"0":pfChallan.getAdminEdliDepositedAc22();
				insertObj[0][35] = pfChallan.getInspectionPfAdminDepositedAc2().equals("")?"0":pfChallan.getInspectionPfAdminDepositedAc2();
				insertObj[0][36] = pfChallan.getInspectionEdliDepositedAc22().equals("")?"0":pfChallan.getInspectionEdliDepositedAc22();
				insertObj[0][37] = pfChallan.getPenalDamagesPfContDepositedAc1().equals("")?"0":pfChallan.getPenalDamagesPfContDepositedAc1();
				insertObj[0][38] = pfChallan.getPenalDamagesPfAdminDepositedAc2().equals("")?"0":pfChallan.getPenalDamagesPfAdminDepositedAc2();
				insertObj[0][39] = pfChallan.getPenalDamagesPensionDepositedAc10().equals("")?"0":pfChallan.getPenalDamagesPensionDepositedAc10();
				insertObj[0][40] = pfChallan.getPenalDamagesEdliDepositedAc21().equals("")?"0":pfChallan.getPenalDamagesEdliDepositedAc21();
				insertObj[0][41] = pfChallan.getPenalDamagesEdliAdminDepositedAc22().equals("")?"0":pfChallan.getPenalDamagesEdliAdminDepositedAc22();
				insertObj[0][42] = pfChallan.getMiscPfAdminDepositedAc2().equals("")?"0":pfChallan.getMiscPfAdminDepositedAc2();
				insertObj[0][43] = pfChallan.getMiscEdliAdminDepositedAc22().equals("")?"0":pfChallan.getMiscEdliAdminDepositedAc22();
			}
			String insertQuery = " INSERT INTO HRMS_PF_CHALLAN(CHALLAN_CODE,CHALLAN_MONTH,CHALLAN_YEAR,CHALLAN_DIVISION,CHALLAN_PAY_MODE,CHALLAN_PAY_DATE,CHALLAN_CHEQ_NO, "
					+ " CHALLAN_CHEQ_DATE,CHALLAN_BANK_MICR_CODE,CHALLAN_EXTABLISHMENT_CODE,CHALLAN_ACCOUNT_GROUP_CODE,CHALLAN_TYPE ,CHALLAN_VPF_CHECK,CHALLAN_VPF_HEAD,CHALLAN_PF_OFFC_NAME," 
					+ " PF_DED_EMPLR_AC1, PF_DED_EMPLR_AC10, PF_DED_EMPLR_AC21, PF_DED_EMPL_AC1, PF_DED_ADMIN_AC2, PF_DED_ADMIN_AC22, PF_DED_INSPECTION_AC2, PF_DED_INSPECTION_AC22, PF_DED_SUB_AC1,"
					+ " PF_DED_SUB_AC10, PF_DED_SUB_AC21, PF_DED_WAGES_AC1, PF_DED_WAGES_AC10, PF_DED_WAGES_AC21, PF_DEP_EMPLR_AC1, PF_DEP_EMPLR_AC10, PF_DEP_EMPLR_AC21, PF_DEP_EMPL_AC1, PF_DEP_ADMIN_AC2,"
					+ " PF_DEP_ADMIN_AC22, PF_DEP_INSPECTION_AC2, PF_DEP_INSPECTION_AC22, PF_DEP_PENAL_AC1, PF_DEP_PENAL_AC2, PF_DEP_PENAL_AC10, PF_DEP_PENAL_AC21, PF_DEP_PENAL_AC22, PF_DEP_MISC_AC2, PF_DEP_MISC_AC22)"
					+ "  VALUES (?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			if (insertObj != null && insertObj.length > 0) {
				queryResult = getSqlModel().singleExecute(insertQuery, insertObj);
			}
			if(queryResult){
				queryResult = true;
			}else{
				queryResult = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryResult;
	}// end of savePfChallan
	
	public boolean checkDuplicate(PFChallan pfChallan){
		
		String query = "SELECT * FROM HRMS_PF_CHALLAN WHERE CHALLAN_MONTH = "+pfChallan.getMonth()+" AND CHALLAN_YEAR ="+pfChallan.getYear()+" AND CHALLAN_DIVISION ="+pfChallan.getDivId()
						+" AND  CHALLAN_TYPE ='"+pfChallan.getType()+"'";
		
		Object [][] result = getSqlModel().getSingleResult(query);
		
		if(result != null && result.length > 0 )
			return true;
		else
			return false;
	}
	/**
	 * THIS METHOD IS USED FOR UPDATING PF CHALLAN RECORD
	 * 
	 * @param pfChallan
	 * @return boolean
	 */
	public boolean updatePfChallan(PFChallan pfChallan) {

		// TODO Auto-generated method stub
		 
		Object updateObj[][] = new Object[1][44];

		updateObj[0][0] = pfChallan.getMonth();
		updateObj[0][1] = pfChallan.getYear();
		updateObj[0][2] = pfChallan.getDivId();
		updateObj[0][3] = pfChallan.getModePayment();
		updateObj[0][4] = pfChallan.getDateOfPay();
		
		if(pfChallan.getModePayment().equals("CH") || pfChallan.getModePayment().equals("DD")){
			
			updateObj[0][5] = pfChallan.getChequeNo();
			updateObj[0][6] = pfChallan.getCheqDate();
			updateObj[0][7] = pfChallan.getBankMicrId();
		}else{
			
			updateObj[0][5] ="";
			updateObj[0][6] ="01-01-0001";
			updateObj[0][7] ="";
		}				
		
		updateObj[0][8] = pfChallan.getEstabNo();
		updateObj[0][9] = pfChallan.getAccGrpNo();
		updateObj[0][10] = pfChallan.getType();
		
		logger.info("444444444"+pfChallan.getCheckVpf());
		
		if(pfChallan.getCheckVpf().equals("on")){
			updateObj[0][11] = "Y";
			updateObj[0][12] = "0";
		}
		else{
			updateObj[0][11] = "N";
			updateObj[0][12] = "0";
		}
		updateObj[0][13] = pfChallan.getPfOfficeName();
		/* Added by Prashant*/
		updateObj[0][14] = pfChallan.getEmployersPfContDeductedAc1();
		updateObj[0][15] = pfChallan.getEmployersPensionDeductedAc10();
		updateObj[0][16] = pfChallan.getEmployersEdliDeductedAc21();
		updateObj[0][17] = pfChallan.getEmpPfContDeductedAc1();
		updateObj[0][18] = pfChallan.getAdminPfAdminDeductedAc2();
		updateObj[0][19] = pfChallan.getAdminEdliAdminDeductedAc22();
		updateObj[0][20] = pfChallan.getInspectionPfAdminDeductedAc2();
		updateObj[0][21] = pfChallan.getInspectionEdliAdminDeductedAc22();
		updateObj[0][22] = pfChallan.getSubscribersPfContDeductedAc1();
		updateObj[0][23] = pfChallan.getSubscribersPensionDeductedAc10();
		updateObj[0][24] = pfChallan.getSubscribersEdliDeductedAc21();
		updateObj[0][25] = pfChallan.getWagesPfContDeductedAc1();
		updateObj[0][26] = pfChallan.getWagesPensionDeductedAc10();
		updateObj[0][27] = pfChallan.getWagesEdliDeductedAc21();
		updateObj[0][28] = pfChallan.getEmployersPfContDepositedAc1().equals("")?"0":pfChallan.getEmployersPfContDepositedAc1();
		updateObj[0][29] = pfChallan.getEmployersPensionDepositedAc10().equals("")?"0":pfChallan.getEmployersPensionDepositedAc10();
		updateObj[0][30] = pfChallan.getEmployersEdliDepositedAc21().equals("")?"0":pfChallan.getEmployersEdliDepositedAc21();
		updateObj[0][31] = pfChallan.getEmpPfContDepositedAc1().equals("")?"0":pfChallan.getEmpPfContDepositedAc1();
		updateObj[0][32] = pfChallan.getAdminPfAdminDepositedAc2().equals("")?"0":pfChallan.getAdminPfAdminDepositedAc2();
		updateObj[0][33] = pfChallan.getAdminEdliDepositedAc22().equals("")?"0":pfChallan.getAdminEdliDepositedAc22();
		updateObj[0][34] = pfChallan.getInspectionPfAdminDepositedAc2().equals("")?"0":pfChallan.getInspectionPfAdminDepositedAc2();
		updateObj[0][35] = pfChallan.getInspectionEdliDepositedAc22().equals("")?"0":pfChallan.getInspectionEdliDepositedAc22();
		updateObj[0][36] = pfChallan.getPenalDamagesPfContDepositedAc1().equals("")?"0":pfChallan.getPenalDamagesPfContDepositedAc1();
		updateObj[0][37] = pfChallan.getPenalDamagesPfAdminDepositedAc2().equals("")?"0":pfChallan.getPenalDamagesPfAdminDepositedAc2();
		updateObj[0][38] = pfChallan.getPenalDamagesPensionDepositedAc10().equals("")?"0":pfChallan.getPenalDamagesPensionDepositedAc10();
		updateObj[0][39] = pfChallan.getPenalDamagesEdliDepositedAc21().equals("")?"0":pfChallan.getPenalDamagesEdliDepositedAc21();
		updateObj[0][40] = pfChallan.getPenalDamagesEdliAdminDepositedAc22().equals("")?"0":pfChallan.getPenalDamagesEdliAdminDepositedAc22();
		updateObj[0][41] = pfChallan.getMiscPfAdminDepositedAc2().equals("")?"0":pfChallan.getMiscPfAdminDepositedAc2();
		updateObj[0][42] = pfChallan.getMiscEdliAdminDepositedAc22().equals("")?"0":pfChallan.getMiscEdliAdminDepositedAc22();
		updateObj[0][43] = pfChallan.getChallanCode();
		

		String updateQuery = " UPDATE HRMS_PF_CHALLAN  SET CHALLAN_MONTH = ?,CHALLAN_YEAR = ?,CHALLAN_DIVISION = ?,CHALLAN_PAY_MODE = ?, CHALLAN_PAY_DATE = TO_DATE(?,'DD-MM-YYYY'),CHALLAN_CHEQ_NO = ?, CHALLAN_CHEQ_DATE=TO_DATE(?,'DD-MM-YYYY'), CHALLAN_BANK_MICR_CODE = ? ,"
								+ " CHALLAN_EXTABLISHMENT_CODE=?, CHALLAN_ACCOUNT_GROUP_CODE=?,CHALLAN_TYPE=? ,CHALLAN_VPF_CHECK=?, CHALLAN_VPF_HEAD=?, CHALLAN_PF_OFFC_NAME=?, "
								+ " PF_DED_EMPLR_AC1=?, PF_DED_EMPLR_AC10=?, PF_DED_EMPLR_AC21=?, PF_DED_EMPL_AC1=?, PF_DED_ADMIN_AC2=?, PF_DED_ADMIN_AC22=?," 
								+ " PF_DED_INSPECTION_AC2=?, PF_DED_INSPECTION_AC22=?, PF_DED_SUB_AC1=?, PF_DED_SUB_AC10=?, PF_DED_SUB_AC21=?, PF_DED_WAGES_AC1=?, PF_DED_WAGES_AC10=?, PF_DED_WAGES_AC21=?, "
								+ " PF_DEP_EMPLR_AC1=?,PF_DEP_EMPLR_AC10=?,PF_DEP_EMPLR_AC21=?,PF_DEP_EMPL_AC1=?,PF_DEP_ADMIN_AC2=?,"
								+ " PF_DEP_ADMIN_AC22=?,PF_DEP_INSPECTION_AC2=?,PF_DEP_INSPECTION_AC22=?,PF_DEP_PENAL_AC1=?,PF_DEP_PENAL_AC2=?,"
								+ " PF_DEP_PENAL_AC10=?,PF_DEP_PENAL_AC21=?, PF_DEP_PENAL_AC22=?,PF_DEP_MISC_AC2=?,PF_DEP_MISC_AC22=?"
								+ " WHERE CHALLAN_CODE = ? ";

		return getSqlModel().singleExecute(updateQuery, updateObj);
		 
	}// end of updatePfChallan

	public void calforedit(PFChallan pfChallan){
		
		String query = " SELECT TO_CHAR(DIV_NAME),CHALLAN_MONTH,CHALLAN_YEAR, "
					+ " CHALLAN_TYPE,CHALLAN_CODE ,CHALLAN_DIVISION ,NVL(DIV_ADDRESS1,' ')||' '||NVL(DIV_ADDRESS2,' ')||' '||NVL(DIV_ADDRESS3,'  ') as div ,"
					+ " NVL(LOCATION_NAME,' ')||' '|| DIV_PINCODE as city,NVL(CHALLAN_PF_OFFC_NAME,' '), "
					+ " NVL(PF_DED_EMPLR_AC1,0), NVL(PF_DED_EMPLR_AC10,0), NVL(PF_DED_EMPLR_AC21,0), NVL(PF_DED_EMPL_AC1,0), NVL(PF_DED_ADMIN_AC2,0), NVL(PF_DED_ADMIN_AC22,0), NVL(PF_DED_INSPECTION_AC2,0), NVL(PF_DED_INSPECTION_AC22,0), NVL(PF_DED_SUB_AC1,0),"
					+ " NVL(PF_DED_SUB_AC10,0), NVL(PF_DED_SUB_AC21,0), NVL(PF_DED_WAGES_AC1,0), NVL(PF_DED_WAGES_AC10,0), NVL(PF_DED_WAGES_AC21,0), NVL(PF_DEP_EMPLR_AC1,0), NVL(PF_DEP_EMPLR_AC10,0), NVL(PF_DEP_EMPLR_AC21,0), NVL(PF_DEP_EMPL_AC1,0), NVL(PF_DEP_ADMIN_AC2,0),"
					+ " NVL(PF_DEP_ADMIN_AC22,0), NVL(PF_DEP_INSPECTION_AC2,0), NVL(PF_DEP_INSPECTION_AC22,0), NVL(PF_DEP_PENAL_AC1,0), NVL(PF_DEP_PENAL_AC2,0), NVL(PF_DEP_PENAL_AC10,0), NVL(PF_DEP_PENAL_AC21,0), NVL(PF_DEP_PENAL_AC22,0), NVL(PF_DEP_MISC_AC2,0), NVL(PF_DEP_MISC_AC22,0)"
					+ " FROM HRMS_PF_CHALLAN " 
					+ " INNER JOIN HRMS_DIVISION ON HRMS_DIVISION.DIV_ID = HRMS_PF_CHALLAN.CHALLAN_DIVISION "
					+ " LEFT JOIN HRMS_LOCATION ON ( HRMS_DIVISION.DIV_CITY_ID = HRMS_LOCATION.LOCATION_CODE ) "
					+ " WHERE CHALLAN_CODE = "+pfChallan.getHiddencode();
		
		Object [][] resultObj = getSqlModel().getSingleResult(query);
		
		if(resultObj != null && resultObj.length > 0){
			
			pfChallan.setDivName(String.valueOf(resultObj[0][0]));
			pfChallan.setMonth(String.valueOf(resultObj[0][1]));
			pfChallan.setYear(String.valueOf(resultObj[0][2]));
			pfChallan.setType(String.valueOf(resultObj[0][3]));
			pfChallan.setChallanCode(String.valueOf(resultObj[0][4]));
			pfChallan.setDivId(String.valueOf(resultObj[0][5]));
			pfChallan.setDivAdd(String.valueOf(resultObj[0][6]));
			pfChallan.setDivCity(String.valueOf(resultObj[0][7]));
			pfChallan.setPfOfficeName(String.valueOf(resultObj[0][8]));
			/* Added by Prashant*/
			pfChallan.setEmployersPfContDeductedAc1(Utility.twoDecimals(String.valueOf(resultObj[0][9])));
			pfChallan.setEmployersPensionDeductedAc10(Utility.twoDecimals(String.valueOf(resultObj[0][10])));
			pfChallan.setEmployersEdliDeductedAc21(Utility.twoDecimals(String.valueOf(resultObj[0][11])));
			pfChallan.setEmpPfContDeductedAc1(Utility.twoDecimals(String.valueOf(resultObj[0][12])));
			pfChallan.setAdminPfAdminDeductedAc2(Utility.twoDecimals(String.valueOf(resultObj[0][13])));
			pfChallan.setAdminEdliAdminDeductedAc22(Utility.twoDecimals(String.valueOf(resultObj[0][14])));
			pfChallan.setInspectionPfAdminDeductedAc2(Utility.twoDecimals(String.valueOf(resultObj[0][15])));
			pfChallan.setInspectionEdliAdminDeductedAc22(Utility.twoDecimals(String.valueOf(resultObj[0][16])));
			pfChallan.setSubscribersPfContDeductedAc1(String.valueOf(resultObj[0][17]));
			pfChallan.setSubscribersPensionDeductedAc10(String.valueOf(resultObj[0][18]));
			pfChallan.setSubscribersEdliDeductedAc21(String.valueOf(resultObj[0][19]));
			pfChallan.setWagesPfContDeductedAc1(Utility.twoDecimals(String.valueOf(resultObj[0][20])));
			pfChallan.setWagesPensionDeductedAc10(Utility.twoDecimals(String.valueOf(resultObj[0][21])));
			pfChallan.setWagesEdliDeductedAc21(Utility.twoDecimals(String.valueOf(resultObj[0][22])));
			
			pfChallan.setEmployersPfContDepositedAc1(Utility.twoDecimals(String.valueOf(resultObj[0][23])));
			pfChallan.setEmployersPensionDepositedAc10(Utility.twoDecimals(String.valueOf(resultObj[0][24])));
			pfChallan.setEmployersEdliDepositedAc21(Utility.twoDecimals(String.valueOf(resultObj[0][25])));
			pfChallan.setEmpPfContDepositedAc1(Utility.twoDecimals(String.valueOf(resultObj[0][26])));
			pfChallan.setAdminPfAdminDepositedAc2(Utility.twoDecimals(String.valueOf(resultObj[0][27])));
			pfChallan.setAdminEdliDepositedAc22(Utility.twoDecimals(String.valueOf(resultObj[0][28])));
			pfChallan.setInspectionPfAdminDepositedAc2(Utility.twoDecimals(String.valueOf(resultObj[0][29])));
			pfChallan.setInspectionEdliDepositedAc22(Utility.twoDecimals(String.valueOf(resultObj[0][30])));
			pfChallan.setPenalDamagesPfContDepositedAc1(Utility.twoDecimals(String.valueOf(resultObj[0][31])));
			pfChallan.setPenalDamagesPfAdminDepositedAc2(Utility.twoDecimals(String.valueOf(resultObj[0][32])));
			pfChallan.setPenalDamagesPensionDepositedAc10(Utility.twoDecimals(String.valueOf(resultObj[0][33])));
			pfChallan.setPenalDamagesEdliDepositedAc21(Utility.twoDecimals(String.valueOf(resultObj[0][34])));
			pfChallan.setPenalDamagesEdliAdminDepositedAc22(Utility.twoDecimals(String.valueOf(resultObj[0][35])));
			pfChallan.setMiscPfAdminDepositedAc2(Utility.twoDecimals(String.valueOf(resultObj[0][36])));
			pfChallan.setMiscEdliAdminDepositedAc22(Utility.twoDecimals(String.valueOf(resultObj[0][37])));
		}
				
		getModOfPay(pfChallan);
		
	}
	
	/** This method sets the ReportDataSet
	 * @param pfChallan
	 * @param request
	 * @param response
	 * @param reportPath - attachment path
	 * @param logoPath - path of the logo to be used
	 */
	public void generateReport(PFChallan pfChallan, HttpServletRequest request, HttpServletResponse response, String reportPath, String logoPath) {
		try {
			ReportDataSet rds = new ReportDataSet();
			String type = pfChallan.getReportType();
			rds.setReportType(type);
			String fileName = "PF Challan Report"+Utility.getRandomNumber(1000);
			String reportPathName=reportPath+fileName+"."+type;
			rds.setFileName(fileName);
			rds.setPageSize("A4");
			rds.setPageOrientation("landscape");
			rds.setShowPageNo(true);
			rds.setReportHeaderRequired(false);
			org.paradyne.lib.ireportV2.ReportGenerator rg=null;
			/* Added by Prashant*/
			if(reportPath.equals("")){
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName+"."+type);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context, request);
				request.setAttribute("reportPath", reportPath+fileName+"."+type);
				request.setAttribute("fileName", fileName+"."+type);
				request.setAttribute("action", "PFChallan_input.action");
			}
			rg = getReport(rg, pfChallan, logoPath);
			rg.process();
			if(reportPath.equals("")){
				rg.createReport(response);
			}else{
				/* Generates the report as attachment*/
				rg.saveReport(response);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * THIS METHOD IS USED FOR GENERATING REPORT
	 * 
	 * @param pfChallan
	 * @param response
	 */
	public org.paradyne.lib.ireportV2.ReportGenerator getReport(org.paradyne.lib.ireportV2.ReportGenerator rg, PFChallan pfChallan, String logoPath) {
		try {
			
			Object[][] nameObj = null;
			nameObj = new Object[1][3];
			nameObj[0][0] = rg.getImage(logoPath);
			nameObj[0][1] = "\n\n COMBINED CHALLAN - A/C. NO. 1,2,10,21 & 22\n\n\nEMPLOYEE'S PROVIDENT FUND ORGANISATION";
			nameObj[0][2] = "";
			
			TableDataSet tdsName = new TableDataSet();
			tdsName.setData(nameObj);
			tdsName.setCellAlignment(new int[]{0,1,0});
			tdsName.setCellWidth(new int[]{15,70,15});
			tdsName.setBorderDetail(0);
			tdsName.setBodyFontStyle(1);
			rg.addTableToDoc(tdsName);
			
			TableDataSet sepChallanTable = new TableDataSet();
			sepChallanTable.setData(new Object[][]{{"","(USE SEPERATE CHALLENS FOR EACH MONTH)",""}});
			sepChallanTable.setCellAlignment(new int[]{0,1,0});
			sepChallanTable.setCellWidth(new int[]{15,70,15});
			sepChallanTable.setBorderDetail(0);
			sepChallanTable.setBlankRowsBelow(1);
			rg.addTableToDoc(sepChallanTable);
			
			String mode="";
			
			if(pfChallan.getModePayment().equals("CH")){
				mode="Cheque";
			}else if(pfChallan.getModePayment().equals("CS")){
				mode="Cash";
			}else if(pfChallan.getModePayment().equals("DD")){
				mode="DD";
			}else{ 
				mode="Transfer";
			}
			
			TableDataSet tdsEstab = new TableDataSet();
			tdsEstab.setData(new Object[][]{{"ESTABLISHMENT CODE NO.:    "+pfChallan.getEstabNo(), "ACCOUNT GROUP NO.: "+pfChallan.getAccGrpNo(), "PAID BY CHEQUE / CASH : "+mode}});
			tdsEstab.setCellAlignment(new int[] {0, 1, 2 });
			tdsEstab.setCellWidth(new int[] {35, 30, 35});
			tdsEstab.setBorderDetail(0);
			tdsEstab.setBodyFontStyle(1);
			rg.addTableToDoc(tdsEstab);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(Utility.getDate("01-" + pfChallan.getMonth() + "-"+ pfChallan.getYear()));
			cal.setFirstDayOfWeek(Calendar.SUNDAY);
			
			int year = Integer.parseInt(pfChallan.getYear());
			int month = Integer.parseInt(pfChallan.getMonth());
				
				Object duesObj[][] = new Object[3][6];
				
				duesObj[0][0] = "";
				duesObj[0][1] = "";
				duesObj[0][2] = "MM";
				duesObj[0][3] = "YYYY";
				duesObj[0][4] = "";
				duesObj[0][5] = "";
				
				duesObj[1][0] = "DUES FOR THE MONTH OF "+Utility.month(Integer.parseInt(pfChallan.getMonth())).toUpperCase()+" "+pfChallan.getYear();
				duesObj[1][1] = "EMPLOYEES SHARE";
				duesObj[1][2] = month;
				duesObj[1][3] = year;
				duesObj[1][4] = "";
				duesObj[1][5] = "DATE OF PAYMENT : "+pfChallan.getDateOfPay();
				
				duesObj[2][0] = "";
				duesObj[2][1] = "EMPLOYER SHARE";
				duesObj[2][2] = month;
				duesObj[2][3] = year;
				duesObj[2][4] = "";
				duesObj[2][5] = "";
				
				TableDataSet duesTable = new TableDataSet();
				duesTable.setCellAlignment(new int[] { 0, 0, 1, 1, 1, 2 });
				duesTable.setCellWidth(new int[] { 30, 20, 5, 5, 10, 30 });
				duesTable.setBorderDetail(0);
				duesTable.setData(duesObj);
				duesTable.setBodyFontStyle(1);
				duesTable.setBlankRowsBelow(1);
				rg.addTableToDoc(duesTable);
				
				//SUBSCRIBERS TABLE
				
				Object subscribers1[][] = new Object[3][6];
				subscribers1[0][0] = "";
				subscribers1[0][1] = "A/C 1 ";
				subscribers1[0][2] = "A/C 10 ";
				subscribers1[0][3] = "A/C 21 ";
				subscribers1[0][4] = "";
				subscribers1[0][5] = "";
				
				subscribers1[1][0] = "Total No. of Subscribers";
				subscribers1[1][1] = pfChallan.getSubscribersPfContDeductedAc1();
				subscribers1[1][2] = pfChallan.getSubscribersPensionDeductedAc10();
				subscribers1[1][3] = pfChallan.getSubscribersEdliDeductedAc21();
				subscribers1[1][4] = "";
				subscribers1[1][5] = "";
				
				subscribers1[2][0] = "Total wages due";
				subscribers1[2][1] = Utility.twoDecimals(pfChallan.getWagesPfContDeductedAc1());
				subscribers1[2][2] = Utility.twoDecimals(pfChallan.getWagesPensionDeductedAc10());
				subscribers1[2][3] = Utility.twoDecimals(pfChallan.getWagesEdliDeductedAc21());
				subscribers1[2][4] = "";
				subscribers1[2][5] = "";
				
				TableDataSet subscribersData = new TableDataSet();
				subscribersData.setCellAlignment(new int[] { 0, 0, 0, 0, 0, 0 });
				subscribersData.setCellWidth(new int[] { 30, 20, 10, 10, 10, 20 });
				subscribersData.setBorderDetail(0);
				subscribersData.setData(subscribers1);
				subscribersData.setBlankRowsBelow(1);
				rg.addTableToDoc(subscribersData);
					
				String [] objTabularHeader = new String[8];
				
				Object[][] objTabularData = new Object[8][8];
				
				int [] cellwidth = {8,20,12,12,12,12,12,12};
				int [] alignment = {1,0,2,2,2,2,2,2};
				
				objTabularHeader[0] = "S.NO.";
				objTabularHeader[1] = "PARTICULARS";
				objTabularHeader[2] = "A/C.NO.1";
				objTabularHeader[3] = "A/C.NO.2";
				objTabularHeader[4] = "A/C.NO.10";
				objTabularHeader[5] = "A/C.NO.21";
				objTabularHeader[6] = "A/C NO.22";
				objTabularHeader[7] = "TOTAL";
				
				objTabularData[0][0] = " PART -01";
				objTabularData[0][1] = " ";
				objTabularData[0][2] = " ";
				objTabularData[0][3] = " ";
				objTabularData[0][4] = " ";
				objTabularData[0][5] = " ";
				objTabularData[0][6] = " ";
				objTabularData[0][7] = " ";
				
				objTabularData[1][0] = "1";
				objTabularData[1][1] = "EMPLOYER'S SHARES OF CONTRIBUTION";
				objTabularData[1][2] = pfChallan.getEmployersPfContDepositedAc1().equals("")?"0.00":pfChallan.getEmployersPfContDepositedAc1();
				objTabularData[1][3] = " ";
				objTabularData[1][4] = pfChallan.getEmployersPensionDepositedAc10().equals("")?"0.00":pfChallan.getEmployersPensionDepositedAc10();
				objTabularData[1][5] = pfChallan.getEmployersEdliDepositedAc21().equals("")?"0.00":pfChallan.getEmployersEdliDepositedAc21();
				objTabularData[1][6] = " ";
				objTabularData[1][7] = pfChallan.getEmployersTotalDeposited();
				
				objTabularData[2][0] = "2";
				objTabularData[2][1] = "EMPLOYEE'S SHARE OF CONTRIBUTION";
				//UPDATE BY REEBA
				objTabularData[2][2] = pfChallan.getEmpPfContDepositedAc1().equals("")?"0.00":pfChallan.getEmpPfContDepositedAc1(); 
				objTabularData[2][3] = " ";
				objTabularData[2][4] = " ";
				objTabularData[2][5] = " ";
				objTabularData[2][6] = " ";
				objTabularData[2][7] = pfChallan.getEmpTotalDeposited();
				
				objTabularData[3][0] = "3";
				objTabularData[3][1] = "ADM.CHARGES";
				objTabularData[3][2] = " ";
				objTabularData[3][3] = pfChallan.getAdminPfAdminDepositedAc2().equals("")?"0.00":pfChallan.getAdminPfAdminDepositedAc2();
				objTabularData[3][4] = " ";
				objTabularData[3][5] = " ";
				objTabularData[3][6] = pfChallan.getAdminEdliDepositedAc22().equals("")?"0.00":pfChallan.getAdminEdliDepositedAc22();
				objTabularData[3][7] = pfChallan.getAdminTotalDeposited();
				
				objTabularData[4][0] = "4";
				objTabularData[4][1] = "INSP CHARGES";
				objTabularData[4][2] = " ";
				objTabularData[4][3] = pfChallan.getInspectionPfAdminDepositedAc2().equals("")?"0.00":pfChallan.getInspectionPfAdminDepositedAc2();
				objTabularData[4][4] = " ";
				objTabularData[3][5] = " ";
				objTabularData[4][6] = pfChallan.getInspectionEdliDepositedAc22().equals("")?"0.00":pfChallan.getInspectionEdliDepositedAc22();
				objTabularData[4][7] = pfChallan.getInspectionTotalDeposited();
								
				objTabularData[5][0] = "5";
				objTabularData[5][1] = "PENAL DAMAGES";
				objTabularData[5][2] = pfChallan.getPenalDamagesPfContDepositedAc1().equals("")?"0.00":pfChallan.getPenalDamagesPfContDepositedAc1();
				objTabularData[5][3] = pfChallan.getPenalDamagesPfAdminDepositedAc2().equals("")?"0.00":pfChallan.getPenalDamagesPfAdminDepositedAc2();
				objTabularData[5][4] = pfChallan.getPenalDamagesPensionDepositedAc10().equals("")?"0.00":pfChallan.getPenalDamagesPensionDepositedAc10();
				objTabularData[5][5] = pfChallan.getPenalDamagesEdliDepositedAc21().equals("")?"0.00":pfChallan.getPenalDamagesEdliDepositedAc21();
				objTabularData[5][6] = pfChallan.getPenalDamagesEdliAdminDepositedAc22().equals("")?"0.00":pfChallan.getPenalDamagesEdliAdminDepositedAc22();
				objTabularData[5][7] = pfChallan.getPenalDamagesTotalDeposited();
				
				objTabularData[6][0] = "6";
				objTabularData[6][1] = "MISC PAYMENT";
				objTabularData[6][2] = " ";
				objTabularData[6][3] = pfChallan.getMiscPfAdminDepositedAc2().equals("")?"0.00":pfChallan.getMiscPfAdminDepositedAc2();
				objTabularData[6][4] = " ";
				objTabularData[6][5] = " ";
				objTabularData[6][6] = pfChallan.getMiscEdliAdminDepositedAc22().equals("")?"0.00":pfChallan.getMiscEdliAdminDepositedAc22();
				objTabularData[6][7] = pfChallan.getMiscTotalDeposited();
				
				objTabularData[7][0] = " ";
				objTabularData[7][1] = "Total";
				objTabularData[7][2] = pfChallan.getTotalPfContDepositedAc1();
				objTabularData[7][3] = pfChallan.getTotalPfAdminDepositedAc2();
				objTabularData[7][4] = pfChallan.getTotalPensionDepositedAc10();
				objTabularData[7][5] = pfChallan.getTotalEdliDepositedAc21();
				objTabularData[7][6] = pfChallan.getTotalEdliAdminDepositedAc22();
				objTabularData[7][7] = pfChallan.getTotalDeposited();
								
				TableDataSet tdstable = new TableDataSet();
				tdstable.setHeader(objTabularHeader);
				tdstable.setHeaderBorderDetail(3);
				tdstable.setHeaderLines(false);
				tdstable.setData(objTabularData);
				tdstable.setCellAlignment(alignment);
				tdstable.setCellWidth(cellwidth);
				tdstable.setHeaderTable(true);
				tdstable.setBorderDetail(3);
				tdstable.setBlankRowsAbove(1);
				
				rg.addTableToDoc(tdstable);
				
				int amountData = (int)(Double.parseDouble(String.valueOf(objTabularData[7][7]))); 
									
				TableDataSet modeData = new TableDataSet();
				modeData.setData(new Object[][]{{"(Amount in words Rupees "+Utility.convert(amountData)+" )"}});
				modeData.setCellAlignment(new int[] {1 });
				modeData.setCellWidth(new int[] { 100 });
				modeData.setBorderDetail(0);
				modeData.setBlankRowsAbove(1);
				modeData.setBlankRowsBelow(1);
				rg.addTableToDoc(modeData);
				
				
				Object objFinal[][] = new Object[4][2];
				objFinal = Utility.checkNullObjArr(objFinal);
				
				objFinal[0][0] = "NAME OF ESTABLISHMENT :";
				objFinal[0][1] = pfChallan.getDivName();
										
				objFinal[1][0] = "ADDRESS :";
				objFinal[1][1] = pfChallan.getDivAdd()+"\n"+pfChallan.getDivCity();;
										
				objFinal[2][0] = "Name of Depositor :";
				objFinal[2][1] = "";
										
				objFinal[3][0] = "Signature of Depositor :";
				objFinal[3][1] = "";
				
				TableDataSet tableData1 = new TableDataSet();
				tableData1.setData(objFinal);
				tableData1.setCellAlignment(new int[] {0,0 });
				tableData1.setCellWidth(new int[] { 40,60 });
				tableData1.setBorderDetail(0);
				
				TableDataSet forBankTable = new TableDataSet();
				forBankTable.setData(new Object[][]{{"(For Bank Use Only)"}});
				forBankTable.setCellAlignment(new int[] {0});
				forBankTable.setCellWidth(new int[] { 100 });
				forBankTable.setBorderDetail(0);
				forBankTable.setBodyFontStyle(1);
				
				Object objFinal1[][] = new Object[6][2];
				
				objFinal1[0][0] = "Amount Received Rs.";
				objFinal1[0][1] = String.valueOf(objTabularData[7][7]);
				objFinal1[1][0] = "For Cheques Only:";
				objFinal1[1][1] = "";
				objFinal1[2][0] = "Date Of Presentation:";
				objFinal1[2][1] = pfChallan.getDateOfPay();
				objFinal1[3][0] = "Date Of Realisation:";
				objFinal1[3][1] = pfChallan.getCheqDate();
				objFinal1[4][0] = "Branch Name:";
				objFinal1[4][1] = pfChallan.getBankBranchName();
				objFinal1[5][0] = "Branch Code No:";
				objFinal1[5][1] = pfChallan.getBankMicrId();
				
				TableDataSet tableData2 = new TableDataSet();
				tableData2.setData(objFinal1);
				tableData2.setCellAlignment(new int[] {0,0 });
				tableData2.setCellWidth(new int[] { 40,60 });
				tableData2.setBorderDetail(0);
				
				HashMap<String ,Object> map = rg.joinTableDataSet(forBankTable, tableData2, false,100);
				HashMap<String ,Object> map1 = rg.joinTableDataSet(tableData1, map, true,50);
				rg.addTableToDoc(map1);
															
				TableDataSet instrData1 = new TableDataSet();
				instrData1.setData(new Object[][]{{"(TO BE FILLED IN BY THE EMPLOYER)"}});
				instrData1.setCellAlignment(new int[] {1 });
				instrData1.setCellWidth(new int[] { 100 });
				instrData1.setBlankRowsAbove(1);
				instrData1.setBodyFontStyle(1);
				instrData1.setBorderDetail(0);
				rg.addTableToDoc(instrData1);
				
				TableDataSet employerTable = new TableDataSet();
				employerTable.setData(new Object[][]{{"Name of the Bank : "+pfChallan.getBankName(), "Cheque No.: "+pfChallan.getChequeNo(), "Date : "+pfChallan.getCheqDate()}});
				employerTable.setCellAlignment(new int[] {0, 0, 0});
				employerTable.setCellWidth(new int[] {40, 30, 30});
				employerTable.setBlankRowsAbove(1);
				employerTable.setBodyFontStyle(1);
				employerTable.setBorderDetail(0);
				rg.addTableToDoc(employerTable);
					
		}catch(Exception e){
			logger.info("Error in generating pf challan report"+e);
			e.printStackTrace();
		}
		return rg;
	}
	
	public void  Data(PFChallan bean, HttpServletRequest request) {

			String query = " SELECT CHALLAN_CODE,CHALLAN_DIVISION,TO_CHAR(DIV_NAME),NVL(DIV_ADDRESS1,' ')||' '||NVL(DIV_ADDRESS2,' ')||' '||NVL(DIV_ADDRESS3,'  ') as div, "
							+" NVL(LOCATION_NAME,' ')||' '|| DIV_PINCODE as city,DECODE(CHALLAN_MONTH, 1, 'January', 2, 'February', 3, 'March', 4, 'April', 5, 'May', "
							+" 6, 'June', 7, 'July', 8, 'August', 9, 'September', 10, 'October', 11, 'November', 12, 'December') as month,CHALLAN_YEAR, "
							+" DECODE(CHALLAN_TYPE,'A','All','S','Salary','R','Arrear','T','Settlement') " 
							+" FROM HRMS_PF_CHALLAN " 	
							+" INNER JOIN HRMS_DIVISION ON HRMS_DIVISION.DIV_ID = HRMS_PF_CHALLAN.CHALLAN_DIVISION "
							+" LEFT JOIN HRMS_LOCATION ON ( HRMS_DIVISION.DIV_CITY_ID = HRMS_LOCATION.LOCATION_CODE ) ";
		
			Object [][] obj = getSqlModel().getSingleResult(query);
						
			String[] pageIndex = Utility.doPaging(bean.getMyPage(),obj.length,20);	
			if(pageIndex==null){
				pageIndex[0] = "0";
				pageIndex[1] ="20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			
			request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			
			
			
			 if(pageIndex[4].equals("1"))
				   bean.setMyPage("1");
			  			
				
				ArrayList<Object> list = new ArrayList<Object>();
				if (obj != null && obj.length > 0) {

					 for(int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++){

						 PFChallan  bean1 = new PFChallan ();
						 
							bean1.setChallanCode(Utility.checkNull(String.valueOf(obj[i][0])));
							bean1.setDivId(Utility.checkNull(String.valueOf(obj[i][1])));	
							bean1.setDivName(Utility.checkNull(String.valueOf(obj[i][2])));	
							bean1.setDivAdd(Utility.checkNull(String.valueOf(obj[i][3])));	
							bean1.setDivCity(Utility.checkNull(String.valueOf(obj[i][4])));	
							bean1.setMonth(Utility.checkNull(String.valueOf(obj[i][5])));	
							bean1.setYear(Utility.checkNull(String.valueOf(obj[i][6])));
							bean1.setType(Utility.checkNull(String.valueOf(obj[i][7])));	

						list.add(bean1);
					}
					
					 bean.setTotalRecords(""+obj.length);
				}
				else{
					bean.setNoData("true");
					bean.setListLength("false");
					
				}
				bean.setTotalRecords(String.valueOf(list.size()));
			    if(list.size()>0){
			    	bean.setListLength("true");
			    } else {
			    	bean.setListLength("false");
			    }
			
			    bean.setRecordList(list);
			
	}
	public void getModOfPay(PFChallan pfChallan){
		
		String query = " SELECT CHALLAN_PAY_MODE,NVL(TO_CHAR(CHALLAN_PAY_DATE,'DD-MM-YYYY'),''),nvl(CHALLAN_CHEQ_NO,''),"
			+ "  TO_CHAR(CHALLAN_CHEQ_DATE,'DD-MM-YYYY'),nvl(CHALLAN_BANK_MICR_CODE,''),nvl(BANK_NAME,''),"
			+ " CHALLAN_EXTABLISHMENT_CODE,nvl(CHALLAN_ACCOUNT_GROUP_CODE,''),CHALLAN_TYPE,CHALLAN_VPF_CHECK,CHALLAN_VPF_HEAD,nvl(DEBIT_NAME,''),nvl(BRANCH_NAME,'') "
			+ " FROM HRMS_PF_CHALLAN  "
			+ " left JOIN HRMS_BANK ON HRMS_PF_CHALLAN.CHALLAN_BANK_MICR_CODE = HRMS_BANK.BANK_MICR_CODE "
			+ " left JOIN HRMS_DEBIT_HEAD ON HRMS_PF_CHALLAN.CHALLAN_VPF_HEAD = HRMS_DEBIT_HEAD.DEBIT_CODE "
			+ " WHERE HRMS_PF_CHALLAN.CHALLAN_CODE = "
			+ pfChallan.getChallanCode();

		Object result[][] = getSqlModel().getSingleResult(query);
	
			if(result != null && result.length > 0){
				
			pfChallan.setModePayment(String.valueOf( result[0][0]));
			pfChallan.setDateOfPay(Utility.checkNull(String.valueOf(result[0][1])));
			pfChallan.setChequeNo(String.valueOf(result[0][2]));
			pfChallan.setCheqDate(String.valueOf(result[0][3]));
			pfChallan.setBankMicrId(String.valueOf(result[0][4]));
			pfChallan.setBankName(String.valueOf(result[0][5]));
			pfChallan.setEstabNo(String.valueOf(result[0][6]));
			pfChallan.setAccGrpNo(String.valueOf(result[0][7]));
			pfChallan.setType(String.valueOf(result[0][8]));
			pfChallan.setHcheckVpf(String.valueOf(result[0][9]));
			if(String.valueOf(result[0][9]).equals("Y")){
				pfChallan.setCheckVpf("checked");
			} else {
				pfChallan.setCheckVpf("");
			}
			pfChallan.setVpfCode(String.valueOf(result[0][10]));
			pfChallan.setVpfName(String.valueOf(result[0][11]));
			pfChallan.setBankBranchName(String.valueOf(result[0][12]));
				
		}
	}
	
	public boolean deleteChallan(String delCode){
		
		String query = " DELETE FROM HRMS_PF_CHALLAN WHERE CHALLAN_CODE = "+delCode;
		
		return getSqlModel().singleExecute(query);
		
	}
	public boolean deleteChallan1(String[] code) {

		boolean result = false;
		boolean flag = false;
		int cnt = 0;
		if (code != null) {
			String query = " DELETE FROM HRMS_PF_CHALLAN WHERE CHALLAN_CODE = ?";
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {
					logger.info(" into delete<----------------checkkkkkkkkkkkkkkkkkkkkkkk--->"+ code[i]);
					Object[][] delete = new Object[1][1];// 
					delete[0][0] = code[i];
					flag = getSqlModel().singleExecute(query, delete);
					if (!flag) {
						cnt++;
					}
					// result=true;
				}// end of nested if
			}// end of loop
		}// end of if
		if (cnt > 0) {
			result = false;
		}// end of if
		else
			result = true;
		return result;

	}
	
public void fetchPfChallanDeductions(PFChallan pfChallan){
		
		//GET PERCENT CONTRIBUTIONS FORM HRMS_PF_CONF
		
		try {
			
			int year = Integer.parseInt(pfChallan.getYear());
			int month = Integer.parseInt(pfChallan.getMonth());
			String stringMonth=String.valueOf(month);
			if(month<10){
				stringMonth="0"+month;
			}
			
			/*
			 * Insert the data in HRMS_PF_DATA if not present
			 * 
			 */
			
			  String PFDataQuery = "SELECT * FROM HRMS_PF_DATA WHERE PF_MONTH ="+month+" AND PF_YEAR = "+year 
			  +" AND  PF_EMP_DIV = "+pfChallan.getDivId();
			  
			  Object [][] pfExist = getSqlModel().getSingleResult(PFDataQuery);
			 
			if(!(pfExist !=null && pfExist.length>0)){
			try {

				PFDataModel pfModel = new PFDataModel();
				pfModel.initiate(context, session);
				pfModel.insertPFReportData(String.valueOf(month), String.valueOf(year),
						pfChallan.getDivId());
				pfModel.terminate();

			} catch (Exception e) {
				logger.error("Exception in calling PFData model for inserting PF data"
								+ e);
			}
			}
			
			String percentContribution = "SELECT PF_EMPLOYEE, PF_EMP_FAMILY, PF_COMPANY, PF_CMP_FAMILY, PF_ADMIN_CHARGES, PF_EDLI_EMPCONT, PF_EDLI_ADMIN_CHARGES, PF_PERCENTAGE, PF_EMOL_NO_MAX_LIMIT_CHK," 
				+" PF_EMOL_MAX_LIMIT, PF_FORMULA, PF_DEBIT_CODE FROM HRMS_PF_CONF "
				+ " WHERE TO_CHAR(PF_DATE,'dd-MON-yyyy') = (select max(PF_DATE) "
				+ " from HRMS_PF_CONF where to_char(PF_DATE,'yyyy-mm')<='"+ year + "-" + stringMonth + "')";
			
			Object[][] contributionData = getSqlModel().getSingleResult( percentContribution);
			
			double pf_emp = 0.0;
			double pf_emp_family = 0.0;
			double pf_comp = 0.0;
			double pf_comp_family = 0.0;
			double pf_admin_charges = 0.0;
			double pf_edli_empcont = 0.0;
			double pf_edli_admin_charges = 0.0;
			double pf_percentage = 0.0;
			String checkEmolumentFlag = "N";
			double emolumentAmtLimit = 0.0;
			String pf_formula = "";
			double pf_debit_code = 0.0;
			
			if (contributionData != null && contributionData.length > 0) {
				pf_emp = Double.parseDouble(Utility.twoDecimals(String.valueOf(contributionData[0][0])));
				pf_emp_family = Double.parseDouble(Utility.twoDecimals(String.valueOf(contributionData[0][1])));
				pf_comp = Double.parseDouble(Utility.twoDecimals(String.valueOf(contributionData[0][2])));
				pf_comp_family = Double.parseDouble(Utility.twoDecimals(String.valueOf(contributionData[0][3])));
				pf_admin_charges = Double.parseDouble(Utility.twoDecimals(String.valueOf(contributionData[0][4])));
				pf_edli_empcont = Double.parseDouble(Utility.twoDecimals(String.valueOf(contributionData[0][5])));
				pf_edli_admin_charges = Double.parseDouble(Utility.twoDecimals(String.valueOf(contributionData[0][6])));
				pf_percentage = Double.parseDouble(Utility.twoDecimals(String.valueOf(contributionData[0][7])));
				checkEmolumentFlag = String.valueOf(contributionData[0][8]);
				emolumentAmtLimit = Double.parseDouble(Utility.twoDecimals(String.valueOf(contributionData[0][9])));
				pf_formula = String.valueOf(contributionData[0][10]);
				pf_debit_code = Double.parseDouble(Utility.twoDecimals(String.valueOf(contributionData[0][11])));
			}
			
			//GET TOTAL SUBSCRIBERS QUERY
			
			String noOfSubscribers = "SELECT SUM("
				+ " CASE WHEN ('Y'='"+checkEmolumentFlag
				+ "' AND PF_EMP_BASIC < "
				+emolumentAmtLimit
				+") THEN PF_EMP_BASIC WHEN ('Y'='"
				+checkEmolumentFlag
				+ "' AND PF_EMP_BASIC > "
				+emolumentAmtLimit
				+ " ) THEN "
				+emolumentAmtLimit
				+ " ELSE PF_EMP_BASIC END),COUNT(HRMS_PF_DATA.PF_EMP_ID) "
				+ " FROM HRMS_PF_DATA "
			//	+ " LEFT JOIN HRMS_PF_CHALLAN ON (HRMS_PF_CHALLAN.CHALLAN_DIVISION = HRMS_PF_DATA.PF_EMP_DIV)"
				+ " WHERE PF_EMP_PF>0 AND PF_MONTH="+ month+ " AND PF_YEAR = "+ year
				+ " AND PF_EMP_DIV = "+ pfChallan.getDivId();
			if(pfChallan.getType().equals("S")){
				noOfSubscribers+= " AND PF_TYPE = 'S'";
			}else if(pfChallan.getType().equals("R")){
				noOfSubscribers+= " AND PF_TYPE IN ('AM','AP')";
			}else if(pfChallan.getType().equals("T")){
				noOfSubscribers+= " AND PF_TYPE = 'T'";
			}
			
			Object[][] totalSubscribersObj = getSqlModel().getSingleResult(noOfSubscribers);
			
			String subscribersTotal="0";
			double wagesDue=0.00;
			
			
			if(totalSubscribersObj!=null && totalSubscribersObj.length > 0){
				subscribersTotal = String.valueOf(totalSubscribersObj[0][1]);
				wagesDue = Double.parseDouble(String.valueOf(totalSubscribersObj[0][0]));
			}
			//Setting Subscribers total
			pfChallan.setSubscribersPfContDeductedAc1(subscribersTotal);
			pfChallan.setSubscribersPensionDeductedAc10(subscribersTotal);
			pfChallan.setSubscribersEdliDeductedAc21(subscribersTotal);
			
			//GETTING TOTAL WAGES QUERY 
			
			/*String wagesQuery =" SELECT SUM("
				+ " CASE WHEN ('Y'='"+checkEmolumentFlag
				+ "' AND PF_EMP_BASIC < "
				+emolumentAmtLimit
				+") THEN PF_EMP_BASIC WHEN ('Y'='"
				+checkEmolumentFlag
				+ "' AND PF_EMP_BASIC > "
				+emolumentAmtLimit
				+ " ) THEN "
				+emolumentAmtLimit
				+ " ELSE PF_EMP_BASIC END)"			//,NVL(TO_CHAR(CHALLAN_PAY_DATE,'dd-mm-yy'),'') "
				+ " FROM HRMS_PF_DATA "
				//+ " LEFT JOIN HRMS_PF_CHALLAN ON (HRMS_PF_CHALLAN.CHALLAN_DIVISION = HRMS_PF_DATA.PF_EMP_DIV)"
				+ " WHERE PF_EMP_PF>0 AND PF_MONTH="+ month+ " AND PF_YEAR = "+ year
				+ " AND PF_EMP_DIV = "+ pfChallan.getDivId();
			if(pfChallan.getType().equals("S")){
				wagesQuery+= " AND PF_TYPE = 'S'";
			}else if(pfChallan.getType().equals("R")){
				wagesQuery+= " AND PF_TYPE IN ('AM','AP')";
			}else if(pfChallan.getType().equals("T")){
				wagesQuery+= " AND PF_TYPE = 'T'";
			}
			wagesQuery+= " GROUP BY PF_MONTH";  //, CHALLAN_PAY_DATE";
				
			Object [][] wagesData = getSqlModel().getSingleResult(wagesQuery);*/
				
			
			
			pfChallan.setWagesPfContDeductedAc1(Utility.twoDecimals(wagesDue));
			pfChallan.setWagesPensionDeductedAc10(Utility.twoDecimals(wagesDue));
			pfChallan.setWagesEdliDeductedAc21(Utility.twoDecimals(wagesDue));
			
			//Setting wages total
			
			Object[][] salData = null;
			if (pfChallan.getType().equals("A")) {
				//UPDATED BY REEBA
				String total = "";
				if (checkEmolumentFlag.equals("Y")) {
					total = " SELECT NVL(SUM(PF_EMP_BASIC),0),NVL(SUM(ROUND(PF_EMP_PF)),0),NVL(SUM(ROUND(PF_CMP_PF)),0),NVL(SUM(ROUND(PF_CMP_F_PF)),0),NVL(SUM(ROUND(PF_EDLI)),0), NVL(SUM(ROUND(PF_EMP_F_PF)),0) FROM HRMS_PF_DATA WHERE PF_EMP_PF>0 AND PF_MONTH="
							+ month + " AND PF_YEAR = "
							+ year + " AND PF_EMP_DIV = "
							+ pfChallan.getDivId();
				} else {
					total = " SELECT NVL(SUM(PF_EMP_BASIC),0),NVL(SUM(ROUND(PF_EMP_PF)),0),NVL(SUM(ROUND(PF_CMP_PF)),0),NVL(SUM(ROUND(PF_CMP_F_PF)),0),NVL(SUM(ROUND(PF_EDLI)),0), NVL(SUM(ROUND(PF_EMP_F_PF)),0) FROM HRMS_PF_DATA WHERE PF_EMP_PF>0 AND PF_MONTH="
							+ month
							+ " AND PF_YEAR = "
							+ year
							+ " AND PF_EMP_DIV = "
							+ pfChallan.getDivId();
				}
				salData = getSqlModel().getSingleResult(total);
			}
			if (pfChallan.getType().equals("S")) {
				String total = "";
				if (checkEmolumentFlag.equals("Y")) {
					//UPDATED BY REEBA
					total = " SELECT NVL(SUM(PF_EMP_BASIC),0),NVL(SUM(ROUND(PF_EMP_PF)),0),NVL(SUM(ROUND(PF_CMP_PF)),0),NVL(SUM(ROUND(PF_CMP_F_PF)),0),NVL(SUM(ROUND(PF_EDLI)),0), NVL(SUM(ROUND(PF_EMP_F_PF)),0) FROM HRMS_PF_DATA WHERE PF_MONTH="
							+ month
							+ " AND PF_EMP_PF>0 AND PF_YEAR = "
							+ year + " AND PF_EMP_DIV = "
							+ pfChallan.getDivId() + " AND PF_TYPE = 'S'";
				} else {
					//UPDATED BY REEBA
					total = " SELECT NVL(SUM(PF_EMP_BASIC),0),NVL(SUM(ROUND(PF_EMP_PF)),0),NVL(SUM(ROUND(PF_CMP_PF)),0),NVL(SUM(ROUND(PF_CMP_F_PF)),0),NVL(SUM(ROUND(PF_EDLI)),0), NVL(SUM(ROUND(PF_EMP_F_PF)),0) FROM HRMS_PF_DATA  WHERE PF_MONTH="
							+ month
							+ "  AND PF_EMP_PF>0 AND PF_YEAR = "
							+ year
							+ " AND PF_EMP_DIV = "
							+ pfChallan.getDivId() + " AND PF_TYPE = 'S'";
				}
				salData = getSqlModel().getSingleResult(total);
			}
			if (pfChallan.getType().equals("R")) {
				//UPDATED BY REEBA
				String total = " SELECT NVL(SUM(PF_EMP_BASIC),0),NVL(SUM(ROUND(PF_EMP_PF)),0),NVL(SUM(ROUND(PF_CMP_PF)),0),NVL(SUM(ROUND(PF_CMP_F_PF)),0),NVL(SUM(ROUND(PF_EDLI)),0), NVL(SUM(ROUND(PF_EMP_F_PF)),0) FROM HRMS_PF_DATA  WHERE PF_MONTH="
						+ month
						+ " AND PF_EMP_PF>0 AND PF_YEAR = "
						+ year
						+ " AND PF_EMP_DIV = "
						+ pfChallan.getDivId() + " AND PF_TYPE IN ('AM','AP')";

				salData = getSqlModel().getSingleResult(total);
			}
			if (pfChallan.getType().equals("T")) {
				//UPDATED BY REEBA	
				String total = " SELECT NVL(SUM(PF_EMP_BASIC),0),NVL(SUM(ROUND(PF_EMP_PF)),0),NVL(SUM(ROUND(PF_CMP_PF)),0),NVL(SUM(ROUND(PF_CMP_F_PF)),0),NVL(SUM(ROUND(PF_EDLI)),0), NVL(SUM(ROUND(PF_EMP_F_PF)),0) FROM HRMS_PF_DATA WHERE PF_EMP_PF>0 AND PF_MONTH="
						+ month
						+ " AND PF_YEAR = "
						+ year
						+ " AND PF_EMP_DIV = "
						+ pfChallan.getDivId() + " AND PF_TYPE = 'T'";

				salData = getSqlModel().getSingleResult(total);
			}
			
			double vpfAmt = 0.0;
			
			if(pfChallan.getCheckVpf().equals("on")){
				
				String vpfApplicablQuery=" SELECT CONF_VPF FROM HRMS_SALARY_CONF "; 
				
				Object[][] resultObj = getSqlModel().getSingleResult(vpfApplicablQuery);
				
				if(resultObj != null && resultObj.length > 0){
					
					if(String.valueOf(resultObj[0][0]).equals("Y")){
						
						String vpfCodeQUery = " SELECT VPF_DEBIT_CODE FROM HRMS_VPF_CONF ";
						
						Object[][] vpfObj = getSqlModel().getSingleResult(vpfCodeQUery);
						
						if(vpfObj != null && vpfObj.length > 0){
							
							logger.info("vpf flag on---------------- ");
							String ledgerQuery = "SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH = "
								+ month
								+ " AND LEDGER_YEAR ="
								+ year
								+ " AND LEDGER_DIVISION ="
								+ pfChallan.getDivId()
								+ " AND LEDGER_STATUS IN ('SAL_START','SAL_FINAL')";

							Object ledgerData[][] = getSqlModel().getSingleResult(ledgerQuery);
							
							String ledgerCode = "";

								if (ledgerData != null && ledgerData.length > 0) {
				
									for (int i = 0; i < ledgerData.length; i++) {
										ledgerCode += String.valueOf(ledgerData[i][0]) + ",";
									}
									
									ledgerCode = ledgerCode.substring(0,ledgerCode.length() - 1);
									
									String vpfQuery = " SELECT NVL(SUM(SAL_AMOUNT,0)) FROM HRMS_SAL_DEBITS_"+year+" WHERE SAL_LEDGER_CODE IN ("+ledgerCode+") AND SAL_DEBIT_CODE = "+String.valueOf(vpfObj[0][0]);
									
									Object [][] salVpfAmt = getSqlModel().getSingleResult(vpfQuery);
									
									if (salVpfAmt != null && salVpfAmt.length > 0)
										vpfAmt += Double.parseDouble(Utility.twoDecimals(String.valueOf(salVpfAmt[0][0])));
									else
										logger.info("vpf amount not present ");
								}
						}
					}
				}
			}
			//Setting deduction data
			if(salData!=null && salData.length >0){
				pfChallan.setEmployersPfContDeductedAc1(formatter.format(Double.parseDouble((String.valueOf(salData[0][3])))));
				pfChallan.setEmployersPensionDeductedAc10(formatter.format(Double.parseDouble(String.valueOf(salData[0][2]))));
				pfChallan.setEmployersEdliDeductedAc21(formatter.format(Double.parseDouble(String.valueOf(Math.round(Double.parseDouble(String.valueOf(salData[0][4])) * pf_edli_empcont / 100 )))));
				pfChallan.setEmployersTotalDeducted(formatter.format(Double.parseDouble(String.valueOf((Double.parseDouble(String.valueOf(salData[0][4])) * pf_edli_empcont / 100)+Double.parseDouble(String.valueOf(salData[0][3]))+Double.parseDouble(String.valueOf(salData[0][2]))))));

				pfChallan.setEmpPfContDeductedAc1(formatter.format(Double.parseDouble(String.valueOf(Double.parseDouble(String.valueOf(salData[0][1])) + vpfAmt+ Double.parseDouble(String.valueOf(salData[0][5]))))));
				pfChallan.setEmpTotalDeducted(pfChallan.getEmpPfContDeductedAc1());

				pfChallan.setAdminPfAdminDeductedAc2(formatter.format(Double.parseDouble(String.valueOf(Math.round(Double.parseDouble(String.valueOf(salData[0][4])) * pf_admin_charges / 100)))));
				pfChallan.setAdminEdliAdminDeductedAc22(formatter.format(Double.parseDouble(String.valueOf(Math.round(Double.parseDouble(String.valueOf(salData[0][4])) * pf_edli_admin_charges / 100)))));
				pfChallan.setAdminTotalDeducted(formatter.format(Double.parseDouble(String.valueOf(Math.round(Double.parseDouble(String.valueOf(salData[0][0])) * pf_admin_charges / 100))+Math.round((Double.parseDouble(String.valueOf(salData[0][4])) * pf_edli_admin_charges / 100)))));

				pfChallan.setInspectionPfAdminDeductedAc2("0.00");
				pfChallan.setInspectionEdliAdminDeductedAc22("0.00");
				pfChallan.setInspectionTotalDeducted(formatter.format(Double.parseDouble(String.valueOf(Double.parseDouble(pfChallan.getInspectionPfAdminDeductedAc2())+Double.parseDouble(pfChallan.getInspectionEdliAdminDeductedAc22())))));
				
				pfChallan.setTotalPfContDeductedAc1(String.valueOf(formatter.format(Double.parseDouble(pfChallan.getEmployersPfContDeductedAc1())+Double.parseDouble(pfChallan.getEmpPfContDeductedAc1()))));
				pfChallan.setTotalPfAdminDeductedAc2(String.valueOf(formatter.format(Double.parseDouble(pfChallan.getAdminPfAdminDeductedAc2())+Double.parseDouble(pfChallan.getInspectionPfAdminDeductedAc2()))));
				pfChallan.setTotalPensionDeductedAc10(String.valueOf(formatter.format(Double.parseDouble(pfChallan.getEmployersPensionDeductedAc10()))));
				pfChallan.setTotalEdliDeductedAc21(String.valueOf(formatter.format(Double.parseDouble(pfChallan.getEmployersEdliDeductedAc21()))));
				pfChallan.setTotalEdliAdminDeductedAc22(String.valueOf(formatter.format(Double.parseDouble(pfChallan.getAdminEdliAdminDeductedAc22())+Double.parseDouble(pfChallan.getInspectionEdliAdminDeductedAc22()))));
				pfChallan.setTotalDeducted(String.valueOf(formatter.format(Double.parseDouble(pfChallan.getEmployersTotalDeducted())+Double.parseDouble(pfChallan.getEmpTotalDeducted())+Double.parseDouble(pfChallan.getAdminTotalDeducted())+Double.parseDouble(pfChallan.getInspectionTotalDeducted()))));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}// end of class
