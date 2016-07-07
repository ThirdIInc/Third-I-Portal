package org.paradyne.model.admin.srd;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.paradyne.bean.admin.srd.GratuityDetails;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class GratuityDetailsModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(GratuityDetailsModel.class);
	
	GratuityDetails gratuityBean=null;
	NumberFormat formatter = new DecimalFormat("#0.00");
	
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	public void getEmployeeDetails(String empId, GratuityDetails gratuityBean) {
		try {
			String query = " SELECT HRMS_EMP_OFFC.EMP_ID,EMP_TOKEN, "
					+ "	HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
					+ "	HRMS_RANK.RANK_NAME,TO_CHAR(CENTER_NAME), TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'),TO_CHAR(EMP_LEAVE_DATE,'DD-MM-YYYY') "
					+ "	FROM HRMS_EMP_OFFC "
					+ "	INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
					+ "	INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
					+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "
					+ "  WHERE HRMS_EMP_OFFC.EMP_ID ="
					+empId;
			Object[][] dataObj = getSqlModel().getSingleResult(query);
			
			if (dataObj != null && dataObj.length > 0) {
				gratuityBean.setEmpId(checkNull(String.valueOf(dataObj[0][0])));
				gratuityBean.setEmpToken(checkNull(String
						.valueOf(dataObj[0][1])));
				gratuityBean
						.setEmpName(checkNull(String.valueOf(dataObj[0][2])));
				gratuityBean.setRankName(checkNull(String
						.valueOf(dataObj[0][3])));
				gratuityBean.setCenterName(checkNull(String
						.valueOf(dataObj[0][4])));
				gratuityBean.setJoiningDate(checkNull(String
						.valueOf(dataObj[0][5])));
				gratuityBean.setLeavingDate(checkNull(String
						.valueOf(dataObj[0][6])));
			}
			
			/*String tenureQuery=" SELECT FLOOR(MONTHS_BETWEEN(EMP_LEAVE_DATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)/12)||' '||'Years'||' '|| 						 FLOOR(MONTHS_BETWEEN(EMP_LEAVE_DATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)-FLOOR(MONTHS_BETWEEN(EMP_LEAVE_DATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)/12) *12) ||' '||'Months' AS EXPERRIENCE "
                +"  FROM HRMS_EMP_OFFC WHERE EMP_ID = "+empId+" AND EMP_LEAVE_DATE IS NOT NULL ";
			Object[][]tenureData=getSqlModel().getSingleResult(tenureQuery);
			if(tenureData !=null && tenureData.length>0){
				gratuityBean.setServiceTenure(checkNull(String.valueOf(tenureData[0][0])));
			}else{
				gratuityBean.setServiceTenure("");
			}*/
			
			String gratuityConfQuery = "SELECT GRATUITY_APPLICABLE, NVL(GRATUITY_MIN_SERV_TENURE,0), GRATUITY_AVGSAL_CREDIT_COMP, GRATUITY_SERV_YRS_ROUNDEDTO, nvl(GRATUITY_FORMULA_FACTOR,1), GRATUITY_SAL_OPTION "
				+ " FROM HRMS_GRATUITY_CONFIG_HDR";
			Object gratuityConfObj[][] = getSqlModel().getSingleResult(
				gratuityConfQuery);
			double serviceYears = 0;
			String serviceYearsQuery = "SELECT NVL((MONTHS_BETWEEN(TO_DATE('"
					+ gratuityBean.getLeavingDate()
					+ "','DD-MM-YYYY')"
					+ ",HRMS_EMP_OFFC.EMP_REGULAR_DATE)/12),0) FROM HRMS_EMP_OFFC WHERE EMP_ID="
					+ gratuityBean.getEmpId();
			try {
				Object[][] serviceYearsObj = getSqlModel().getSingleResult(
						serviceYearsQuery);
				serviceYears = Double.parseDouble(String
						.valueOf(serviceYearsObj[0][0]));
				if (String.valueOf(gratuityConfObj[0][3]).equals("N")) {
					serviceYears = Math.round(serviceYears);
				} else if (String.valueOf(gratuityConfObj[0][3]).equals("F")) {
					serviceYears = Math.floor(serviceYears);
				}
				if (String.valueOf(gratuityConfObj[0][3]).equals("C")) {
					serviceYears = Math.ceil(serviceYears);
				}
				if (String.valueOf(gratuityConfObj[0][3]).equals("A")) {
					serviceYears = Double.parseDouble(formatter.format(serviceYears));
				}
			} catch (Exception e) {
				logger.error("exception in service years " + e);
				serviceYears = Double
						.parseDouble(Utility.twoDecimals(serviceYears));
			}
			gratuityBean.setServiceTenure(String.valueOf(serviceYears));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean saveGratuityDetails(HttpServletRequest request, GratuityDetails gratuityBean) {
		boolean result=false;
		try {
			String gratuityQuery = "INSERT INTO HRMS_EMP_GRATUITY (GRAT_ID, EMP_ID, GRAT_FORM_NO, GRAT_CLAIM_AMT, GRAT_CLAIM_DATE, GRAT_CLAIM_STATUS, GRAT_PAYABLE_AMT, GRAT_PAY_MODE, GRAT_APPR_REMARKS, GRAT_REJ_REASONS)"
				+ " VALUES((SELECT NVL(MAX(GRAT_ID),0)+1 FROM HRMS_EMP_GRATUITY),?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?)";
			Object gratuityObj[][] = new Object[1][9];
			gratuityObj[0][0] = gratuityBean.getEmpId();
			gratuityObj[0][1] = gratuityBean.getApplicationFormNo();
			gratuityObj[0][2] = gratuityBean.getGratuity();
			gratuityObj[0][3] = gratuityBean.getClaimDate();
			gratuityObj[0][4] = gratuityBean.getAppStatus();
			gratuityObj[0][5] = gratuityBean.getAmountPayable();
			gratuityObj[0][6] = gratuityBean.getPaymentMode();
			gratuityObj[0][7] = gratuityBean.getApprovalRemark();
			gratuityObj[0][8] = gratuityBean.getRejectionReason();
			result = getSqlModel().singleExecute(gratuityQuery, gratuityObj);
			
			String gratuityIdQuery = " SELECT MAX(GRAT_ID) FROM HRMS_EMP_GRATUITY";
			Object gratuityIdObj[][] = getSqlModel().getSingleResult(gratuityIdQuery);
			
			gratuityBean.setGratuityId(String.valueOf(gratuityIdObj[0][0]));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean updateGratuityDetails(HttpServletRequest request, GratuityDetails gratuityBean) {
		boolean result=false;
		try {
			String updateQuery = " UPDATE HRMS_EMP_GRATUITY SET  GRAT_CLAIM_DATE=TO_DATE(?,'DD-MM-YYYY'), GRAT_CLAIM_STATUS=?, GRAT_PAYABLE_AMT=?, GRAT_PAY_MODE=?, GRAT_APPR_REMARKS=?, GRAT_REJ_REASONS=? WHERE EMP_ID=?";
			Object gratuityUpdateObj[][] = new Object[1][7];
			gratuityUpdateObj[0][0] = gratuityBean.getClaimDate();
			gratuityUpdateObj[0][1] = gratuityBean.getAppStatus();
			gratuityUpdateObj[0][2] = gratuityBean.getAmountPayable();
			gratuityUpdateObj[0][3] = gratuityBean.getPaymentMode();
			gratuityUpdateObj[0][4] = gratuityBean.getApprovalRemark();
			gratuityUpdateObj[0][5] = gratuityBean.getRejectionReason();
			gratuityUpdateObj[0][6] = gratuityBean.getEmpId();
			result = getSqlModel().singleExecute(updateQuery, gratuityUpdateObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void calculateGratuityAmount(GratuityDetails gratuityBean) {
		String gratuityConfQuery = "SELECT GRATUITY_APPLICABLE, NVL(GRATUITY_MIN_SERV_TENURE,0), GRATUITY_AVGSAL_CREDIT_COMP, GRATUITY_SERV_YRS_ROUNDEDTO, nvl(GRATUITY_FORMULA_FACTOR,1), GRATUITY_SAL_OPTION "
				+ " FROM HRMS_GRATUITY_CONFIG_HDR";
		Object gratuityConfObj[][] = getSqlModel().getSingleResult(
				gratuityConfQuery);

		String applEmpQuery = "SELECT HRMS_GRATUITY_CONFIG_DTL.EMP_DIVISION, HRMS_GRATUITY_CONFIG_DTL.EMP_CENTER, HRMS_GRATUITY_CONFIG_DTL.EMP_DEPT, HRMS_GRATUITY_CONFIG_DTL.EMP_RANK,HRMS_GRATUITY_CONFIG_DTL.EMP_GRADE, HRMS_GRATUITY_CONFIG_DTL.EMP_TYPE FROM HRMS_GRATUITY_CONFIG_DTL"
				+ " LEFT JOIN HRMS_EMP_OFFC ON(EMP_DIVISION=EMP_DIV) WHERE EMP_ID="
				+ gratuityBean.getEmpId()
				+ " ORDER BY "
				+ " (CASE WHEN EMP_DIVISION IS NULL THEN 1 ELSE 0 END )+ "
				+ " (CASE WHEN EMP_DEPT IS NULL THEN 1 ELSE 0 END ) +  "
				+ " (CASE WHEN EMP_CENTER  IS NULL THEN 1 ELSE 0 END )+  "
				+ " (CASE WHEN EMP_RANK IS NULL THEN 1 ELSE 0 END )+  "
				+ " (CASE WHEN EMP_TYPE IS NULL THEN 1 ELSE 0 END) DESC ";

		boolean gratuityApplicable = false;
		try {
			if(gratuityConfObj != null && gratuityConfObj.length > 0){
			if (String.valueOf(gratuityConfObj[0][0]).equals("Y")) {
				Object[][] applFiltersObj = getSqlModel().getSingleResult(applEmpQuery);
				try {
					for (int i = 0; i < applFiltersObj.length; i++) {
						String query = " SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE 1=1 ";

						if (!(String.valueOf(applFiltersObj[i][5]).equals(""))
								&& !(String.valueOf(applFiltersObj[i][5]) == null)
								&& !String.valueOf(applFiltersObj[i][5])
										.equals("null")) {
							// if emp type not null
							query += " AND EMP_TYPE ="
									+ String.valueOf(applFiltersObj[i][5]);

						} // end if
						if (!(String.valueOf(applFiltersObj[i][2]).equals(""))
								&& !(String.valueOf(applFiltersObj[i][2]) == null)
								&& !String.valueOf(applFiltersObj[i][2])
										.equals("null")) {
							// if dept not null
							query += " AND EMP_DEPT ="
									+ String.valueOf(applFiltersObj[i][2]);
						} // end if
						if (!(String.valueOf(applFiltersObj[i][1]).equals(""))
								&& !(String.valueOf(applFiltersObj[i][1]) == null)
								&& !String.valueOf(applFiltersObj[i][1])
										.equals("null")) {
							// if branch not null
							query += " AND EMP_CENTER ="
									+ String.valueOf(applFiltersObj[i][1]);

						} // end if
						if (!(String.valueOf(applFiltersObj[i][3]).equals(""))
								&& !(String.valueOf(applFiltersObj[i][3]) == null)
								&& !String.valueOf(applFiltersObj[i][3])
										.equals("null")) {
							// if desg not null
							query += " AND EMP_RANK ="
									+ String.valueOf(applFiltersObj[i][3]);
						} // end if

						if (!(String.valueOf(applFiltersObj[i][4]).equals(""))
								&& !(String.valueOf(applFiltersObj[i][4]) == null)
								&& !String.valueOf(applFiltersObj[i][4])
										.equals("null")) {
							// if desg not null
							query += " AND EMP_CADRE ="
									+ String.valueOf(applFiltersObj[i][4]);
						} // end if

						query += " AND HRMS_EMP_OFFC.EMP_ID="
								+ gratuityBean.getEmpId();
						System.out.println("value of query---------" + query);
						Object obj[][] = getSqlModel().getSingleResult(query);
						if (obj != null && obj.length > 0) {
							gratuityApplicable = true;
							break;
						}
					}
					logger.info("gratuityApplicable===" + gratuityApplicable);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			}
		} catch (Exception e) {
			logger.error("exception in gratuity config " + e);
			e.printStackTrace();
			gratuityApplicable = false;
		}
		double avgSal = 0;
		double serviceYears = 0;
		String serviceYearsQuery = "SELECT NVL((MONTHS_BETWEEN(TO_DATE('"
				+ gratuityBean.getLeavingDate()
				+ "','DD-MM-YYYY')"
				+ ",HRMS_EMP_OFFC.EMP_REGULAR_DATE)/12),0) FROM HRMS_EMP_OFFC WHERE EMP_ID="
				+ gratuityBean.getEmpId();

		try {
			Object[][] serviceYearsObj = getSqlModel().getSingleResult(
					serviceYearsQuery);
			serviceYears = Double.parseDouble(String
					.valueOf(serviceYearsObj[0][0]));
			if (String.valueOf(gratuityConfObj[0][3]).equals("N")) {
				serviceYears = Math.round(serviceYears);
			} else if (String.valueOf(gratuityConfObj[0][3]).equals("F")) {
				serviceYears = Math.floor(serviceYears);
			}
			if (String.valueOf(gratuityConfObj[0][3]).equals("C")) {
				serviceYears = Math.ceil(serviceYears);
			}
			if (String.valueOf(gratuityConfObj[0][3]).equals("A")) {
				serviceYears = Double.parseDouble(formatter.format(serviceYears));
			}
		} catch (Exception e) {
			logger.error("exception in service years " + e);
			serviceYears = Double
					.parseDouble(Utility.twoDecimals(serviceYears));
		}
		if (gratuityApplicable) {

			if (serviceYears >= Double.parseDouble(String
					.valueOf(gratuityConfObj[0][1]))) {

				avgSal = getAvegareSalary(gratuityConfObj, gratuityBean);

				logger.info("service years===" + serviceYears);
				logger.info("min service years==="
						+ String.valueOf(gratuityConfObj[0][1]));

				logger.info("service years after round off===" + serviceYears
						+ " round off type==" + gratuityConfObj[0][3]);
			} else {
				gratuityBean.setGratuity("0");

			}
		}

		gratuityBean.setServiceTenure(String.valueOf(serviceYears));
		gratuityBean.setGratuityAvgSalary(String.valueOf(avgSal));
		String gratuityFormula = "0";
		try {
			gratuityFormula = serviceYears + "*" + String.valueOf(avgSal) + "*"
					+ (String.valueOf(gratuityConfObj[0][4]));
		} catch (Exception e) {
			gratuityFormula = "0";
		}
		double gratuityAmt = Utility.expressionEvaluate(gratuityFormula);
		logger.info("gratuityAmt===" + gratuityAmt);
		gratuityBean.setGratuity(formatter.format(gratuityAmt));

	}
	
	public double getAvegareSalary(Object gratuityConfObj[][],GratuityDetails bean){
		double avgSal=0.0;
		try{
		if(String.valueOf(gratuityConfObj[0][5]).equals("L")){
			String empSalCreditQuery="SELECT SUM(CREDIT_AMT) FROM HRMS_EMP_CREDIT WHERE CREDIT_CODE IN("+String.valueOf(gratuityConfObj[0][2])+") AND EMP_ID="+bean.getEmpId();
			
			Object [][]empSalCreditObj=getSqlModel().getSingleResult(empSalCreditQuery);
			try{
			if(empSalCreditObj!= null && empSalCreditObj.length >0){
				avgSal = Double.parseDouble(String.valueOf(empSalCreditObj[0][0]));
			}
			}catch (Exception e) {
				logger.error("exception in getAvegareSalary"+e);
			}
		}else{
			avgSal=getLastSalaryAverage(bean,String.valueOf(gratuityConfObj[0][2]));
		}
		}catch (Exception e) {
			logger.error("exception in getAvegareSalary"+e);
		}
		return avgSal;
	}
	
	public double getLastSalaryAverage(GratuityDetails bean,String creditCodes){
		double avgSal=0.0;
		int lastSalaryMonth =Integer.parseInt((bean.getLeavingDate().split("-"))[1]);
		int lastSalaryYear =Integer.parseInt((bean.getLeavingDate().split("-"))[2]);
		int totalMonth=10;
		int month =lastSalaryMonth, year=lastSalaryYear;
		
		for (int i = 0; i < totalMonth; i++) {
			try{
			String salQuery="SELECT NVL(SUM(SAL_AMOUNT),0) FROM HRMS_SAL_CREDITS_"+year+" WHERE SAL_CREDIT_CODE IN("+creditCodes+")"
				+" AND EMP_ID ="+bean.getEmpId()+" AND SAL_LEDGER_CODE IN(SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH="+month+" AND LEDGER_YEAR= "+year+" AND LEDGER_DIVISION ="
				+" (SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="+bean.getEmpId()+"))";
			
			Object salObj[][]=getSqlModel().getSingleResult(salQuery);
			avgSal +=Double.parseDouble(String.valueOf(salObj[0][0]));
			}catch (Exception e) {
				logger.error("exception in getLastSalaryAverage"+e);
			}
			if(month ==1){
				month = 12;
				year  -= 1; 
			}else{
				month  -= 1;
			}
		}
		return avgSal/totalMonth;
	}

	public void getGratuityDetails(GratuityDetails gratuityBean) {
		try {
			String gratuityQuery = "SELECT NVL(GRAT_FORM_NO,' '), NVL(GRAT_CLAIM_AMT,0), NVL(TO_CHAR(GRAT_CLAIM_DATE,'DD-MM-YYYY'),' '), NVL(GRAT_CLAIM_STATUS,' '), NVL(GRAT_PAYABLE_AMT,0), NVL(GRAT_PAY_MODE,' '), NVL(GRAT_APPR_REMARKS,' '), NVL(GRAT_REJ_REASONS,' '), GRAT_ID"
				+ " FROM HRMS_EMP_GRATUITY WHERE EMP_ID = "+gratuityBean.getEmpId();
			Object gratuityObj[][] = getSqlModel().getSingleResult(gratuityQuery);
			if(gratuityObj != null && gratuityObj.length > 0){
				gratuityBean.setApplicationFormNo(String.valueOf(gratuityObj[0][0]));
				gratuityBean.setGratuity(String.valueOf(gratuityObj[0][1]));
				gratuityBean.setClaimDate(String.valueOf(gratuityObj[0][2]));
				gratuityBean.setAppStatus(String.valueOf(gratuityObj[0][3]));
				gratuityBean.setAmountPayable(String.valueOf(gratuityObj[0][4]));
				gratuityBean.setPaymentMode(String.valueOf(gratuityObj[0][5]));
				gratuityBean.setApprovalRemark(String.valueOf(gratuityObj[0][6]));
				gratuityBean.setRejectionReason(String.valueOf(gratuityObj[0][7]));
				gratuityBean.setGratuityId(String.valueOf(gratuityObj[0][8]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
