package org.paradyne.model.leave;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nfunk.jep.function.Str;
import org.paradyne.bean.leave.LeaveApplication;
import org.paradyne.bean.leave.LeaveEncashment;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

public class LeaveEncashmentModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(LeaveEncashmentModel.class);
	NumberFormat formatter = new DecimalFormat("#0.00");

	/**
	 * THIS METHOD IS USED FOR GETTING LEAVE RECORD
	 * 
	 * @param leaveEn
	 * @param policyCode
	 * @return String[][]
	 */

	public String[][] getLeaveRecord(LeaveEncashment leaveEn, String policyCode) {

		String[][] total = null;
		try {
			logger.info("policyCode-------getLeaveRecord--------------"
					+ policyCode);

			String query = "SELECT  TO_CHAR(LEAVE_NAME) ,NVL(LEAVE_CLOSING_BALANCE,0),HRMS_LEAVE.LEAVE_ID,"
					+ " HRMS_LEAVE_POLICY_DTL.LEAVE_ENC_FORMULA,HRMS_LEAVE_POLICY_DTL.LEAVE_ENC_MAXLIMIT," 
					+ " NVL(HRMS_LEAVE_BALANCE.LEAVE_ONHOLD,0), "
					+ " NVL(DECODE(HRMS_LEAVE_POLICY_DTL.LEAVE_ENC_MAXLIMIT_DURATION,'Mo','Monthly','Qu','Quarterly','Hy','Half Yearly','Ye','Yearly'),'Yearly') "
					+ " FROM HRMS_LEAVE "
					+ "LEFT JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_BALANCE.LEAVE_CODE AND EMP_ID ="
					+ leaveEn.getEmpId()
					+ ") "
					+ "LEFT JOIN HRMS_EMP_OFFC ON(HRMS_LEAVE_BALANCE.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
					+ "INNER JOIN HRMS_LEAVE_POLICY_DTL ON(HRMS_LEAVE_POLICY_DTL.LEAVE_CODE=HRMS_LEAVE_BALANCE.LEAVE_CODE AND LEAVE_APPLICABLE='Y' AND LEAVE_ENC_FLAG='Y'"
					+ " AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_GENDER IN('B','"+leaveEn.getGender()+"')   AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE="
					+ policyCode
					+ ")"
					+ " WHERE  LEAVE_ENC_FORMULA IS NOT NULL ORDER BY LEAVE_ID ";

			Object[][] data = getSqlModel().getSingleResult(query);

			total = new String[data.length][11];

			for (int i = 0; i < data.length; i++) {
				double totEncashDays = 0.0d;
				double availabelimit = 0.0d;
				total[i][0] = String.valueOf(data[i][0]); // leave name
				total[i][1] = String.valueOf(data[i][1]);// leave closing
															// balance
				total[i][2] = "0";// leave to be encashed set to zero
				total[i][3] = "0";// amount set to zero
				total[i][4] = String.valueOf(data[i][2]);// leave id
				total[i][6] = String.valueOf(data[i][4]);// leave encash max
				// limit from leave
				// policy
				total[i][7] = String.valueOf(data[i][5]);// on hold
				if (data[i][4]==null || data[i][4]=="null" || String.valueOf(data[i][4]).trim().equals("")) {
					total[i][8] = "NA";
				} else {
					total[i][8] = String.valueOf(data[i][4]) + " " + String.valueOf(data[i][6]) ;// encash limit
				}
				try {
					double amount = 0;
					amount = Utility.expressionEvaluate(new Utility()
							.generateFormula(leaveEn.getEmpId(), String
									.valueOf(data[i][3]), context, // string.valueof(data[i][3])=leave
									// encash
									// formula
									session));
					total[i][5] = String.valueOf(amount);//Math.round(amount)); // leave
					// encash
					// amount
				}// end of try
				catch (Exception e) {
					// TODO: handle exception
					logger.error("Exception in getLeaveRecord1---------------"
							+ e);
				}// end of catch
				
				
				final String checkQuery = " SELECT NVL(LEAVE_ENC_MAXLIMIT,0), LEAVE_ENC_MAXLIMIT_DURATION,LEAVE_MGTYEAR_MONTH_START,LEAVE_MGTYEAR_MONTH_END " + 
				" , LEAVE_NAME FROM HRMS_LEAVE_POLICY_DTL " + 
				" LEFT JOIN HRMS_LEAVE_POLICY_HDR ON (HRMS_LEAVE_POLICY_HDR.LEAVE_POLICY_CODE = HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE) " + 
				" LEFT JOIN HRMS_LEAVE ON (HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_POLICY_DTL.LEAVE_CODE)  " + 
				" WHERE HRMS_LEAVE_POLICY_HDR.LEAVE_POLICY_CODE = " + policyCode + 
				" AND LEAVE_CODE = " + String.valueOf(data[i][2]);
				final Object[][] checkObj = getSqlModel().getSingleResult(checkQuery);
				final int yearStartMonth = Integer.parseInt(String.valueOf(checkObj[0][2]));
		
				final int encashMonth = Integer.parseInt(leaveEn.getEncashDate().substring(3,5));
				final int encashYear = Integer.parseInt(leaveEn.getEncashDate().substring(6,10));
				final int[] monthArray = this.getMonthArray(encashMonth, String.valueOf(checkObj[0][1]), yearStartMonth);
				int[] monthArray2 = null;
				int[] monthArray3 = null;
				for (int j = 0; j < monthArray.length; j++) {	
					System.out.println("monthArray.length:"+monthArray.length);
					System.out.println("j:" +j+ "      monthArray[j] :" + monthArray[j]);
					if(monthArray[j] == 12 && monthArray.length > j+1){
						monthArray2 =  new int[j+1];
						monthArray3 =  new int[monthArray.length - j+1];
						for (int k = 0; k < monthArray.length; k++) {
							if(k < monthArray2.length){
								monthArray2[k] = monthArray[k];
							} else {
								monthArray3[k - monthArray2.length] = monthArray[k];
							}
							
						}
						break;
					}					
				}
				
						
				if (monthArray.length > 0) {
					String chkPrevEncQuery = " SELECT HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE, " +
							" NVL(LEAVE_ENCASHED,0), TO_CHAR(ENCASH_DATE,'DD-MM-YYYY') "	+ 
							" FROM HRMS_LEAVE_ENCASH_HDR " + 
							" LEFT JOIN HRMS_LEAVE_ENCASH_DTL ON (HRMS_LEAVE_ENCASH_DTL.ENCASH_CODE = HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE) " + 
							" WHERE EMP_ID = " + leaveEn.getEmpId() + 
							" AND LEAVE_CODE = " + String.valueOf(data[i][2]) + 
							" AND ENCASH_STATUS IN ('A','P') " ;
					if(monthArray2 == null) {
						chkPrevEncQuery += " AND TO_CHAR(ENCASH_DATE,'MM') IN (" + getStringWithComma(monthArray) + ") AND TO_CHAR(ENCASH_DATE,'YYYY') ='"+(encashYear)+"'";
					} else {
						chkPrevEncQuery += " AND (TO_CHAR(ENCASH_DATE,'MM') IN (" + getStringWithComma(monthArray2) + ") AND TO_CHAR(ENCASH_DATE,'YYYY') ='"+(encashYear-1)+"'" 
								+	"OR TO_CHAR(ENCASH_DATE,'MM') IN (" + getStringWithComma(monthArray3) + ") AND TO_CHAR(ENCASH_DATE,'YYYY') ='"+ encashYear +"')";
					}
					Object[][] chkPrevEncObj = getSqlModel().getSingleResult(chkPrevEncQuery);
					if (chkPrevEncObj != null && chkPrevEncObj.length > 0) {
						for (int j = 0; j < chkPrevEncObj.length; j++) {
							totEncashDays += Double.parseDouble(String.valueOf(chkPrevEncObj[j][1]));
						}
					}
					
					
					String chkPrevEncQuery1 = " SELECT HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_ENCASH_LEAVE "
									+ " FROM HRMS_ENCASHMENT_PROCESS_DTL "
									+ " INNER JOIN HRMS_ENCASHMENT_PROCESS_HDR ON(HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_CODE = HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_PROCESS_CODE) " 
									+ " WHERE HRMS_ENCASHMENT_PROCESS_DTL.EMP_ID= " + leaveEn.getEmpId() 
									+ " AND HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_LEAVE_CODE= " + String.valueOf(data[i][2]);
					if(monthArray2 == null) {
						chkPrevEncQuery += " AND TO_CHAR(HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_DATE,'MM') IN (" + getStringWithComma(monthArray) + ") "
								+ " AND TO_CHAR(HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_DATE,'YYYY') ='"+(encashYear)+"'";
					} else {
						chkPrevEncQuery += " AND (TO_CHAR(HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_DATE,'MM') IN (" + getStringWithComma(monthArray2) + ") " 
								+ " AND TO_CHAR(HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_DATE,'YYYY') ='"+(encashYear-1)+"'" 
								+ " OR TO_CHAR(HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_DATE,'MM') IN (" + getStringWithComma(monthArray3) + ") " 
								+ " AND TO_CHAR(HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_DATE,'YYYY') ='"+ encashYear +"')";
					}
					Object[][] chkPrevEncObj1 = getSqlModel().getSingleResult(chkPrevEncQuery1);
					if (chkPrevEncObj1 != null && chkPrevEncObj1.length > 0) {
						totEncashDays += Double.parseDouble(String.valueOf(chkPrevEncObj1[0][0]));
					}
					
					
				}

				total[i][9] = "" + totEncashDays;
				if (data[i][4]==null || data[i][4]=="null" || String.valueOf(data[i][4]).trim().equals("")) {
					total[i][10] = "NA";
				} else {
					availabelimit = Double.parseDouble(String.valueOf(data[i][4])) - totEncashDays;
					total[i][10] = "" + formatter.format(availabelimit);
				}
			}// end of for
		} catch (Exception e) {
			logger.error("Exception in getLeaveRecord2---------------" + e);
			e.printStackTrace();
		}
		return total;

	}// end of getLeaveRecord

	/**
	 * THIS METHOD IS USED FOR GETTING LEAVE RECORD
	 * 
	 * @param leaveEn
	 * @return String[][]
	 */
	public String[][] getLevRecord(LeaveEncashment leaveEn) {

		String[][] total = null;

		try {
			String policyCode = getLeavePolicyCode(leaveEn.getEmpId());
			String query = "	SELECT LEAVE_NAME,NVL(LEAVE_CLOSING_BALANCE,0),NVL(LEAVE_ENCASHED,0),NVL(ENCASH_AMOUNT,0),LEAVE_CODE,ENCASH_AMOUNT,HRMS_LEAVE_POLICY_DTL.LEAVE_ENC_MAXLIMIT,NVL(HRMS_LEAVE_BALANCE.LEAVE_ONHOLD,0),HRMS_LEAVE_POLICY_DTL.LEAVE_ENC_FORMULA"
					+ " FROM HRMS_LEAVE_ENCASH_DTL "
					+ "	LEFT JOIN HRMS_LEAVE  ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_ENCASH_DTL.LEAVE_CODE)"
					+ " INNER JOIN HRMS_LEAVE_BALANCE  ON(HRMS_LEAVE_BALANCE.LEAVE_CODE=HRMS_LEAVE_ENCASH_DTL.LEAVE_CODE AND HRMS_LEAVE_BALANCE.EMP_ID="
					+ leaveEn.getEmpId()
					+ " )"
					+ "INNER JOIN HRMS_LEAVE_POLICY_DTL ON(HRMS_LEAVE_POLICY_DTL.LEAVE_CODE=HRMS_LEAVE_BALANCE.LEAVE_CODE AND LEAVE_APPLICABLE='Y' AND LEAVE_ENC_FLAG='Y'"
					+ "AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE="
					+ policyCode
					+ ")"
					+ "		WHERE ENCASH_CODE="
					+ leaveEn.getEnCode() + "  ORDER BY LEAVE_ID ";

			Object[][] data = getSqlModel().getSingleResult(query);

			total = new String[data.length][8];

			for (int i = 0; i < data.length; i++) {
				total[i][0] = String.valueOf(data[i][0]); // leave name
				total[i][1] = String.valueOf(data[i][1]); // leave closing
															// balance
				total[i][2] = String.valueOf(data[i][3]); // encash amount
				total[i][3] = String.valueOf(data[i][2]); // leave to be
															// encashed
				total[i][4] = String.valueOf(data[i][4]); // leave code

				try {
					double amount = 0;
					amount = Utility.expressionEvaluate(new Utility()
							.generateFormula(leaveEn.getEmpId(), String
									.valueOf(data[i][8]), context, // string.valueof(data[i][3])=leave
									// encash
									// formula
									session));
					total[i][5] = String.valueOf(Math.round(amount)); // leave
					// encash
					// amount
				}// end of try
				catch (Exception e) {
					// TODO: handle exception
					logger.error("Exception in getLevRecord1---------------"
							+ e);
				}// end of catch
				total[i][6] = String.valueOf(data[i][6]); // max encash limit
				total[i][7] = String.valueOf(data[i][7]); // on hold

			}// end of for
		} catch (Exception e) {
			logger.error("Exception in getLevRecord2---------------" + e);
		}
		return total;
	}// end of getLevRecord

	/**
	 * THIS METHOD IS USED FOR GETTING EMPLOYEE INFORMATION
	 * 
	 * @param empId---employee
	 *            id
	 * @param leaveEn
	 * @return LeaveEncashment
	 */
	public LeaveEncashment empDetails(String empId, LeaveEncashment leaveEn) {
		try {
			Object[] beanObj = new Object[1];
			beanObj[0] = empId;
			Object[][] data = getSqlModel().getSingleResult(getQuery(4),
					beanObj);
			for (int i = 0; i < data.length; i++) {
				leaveEn.setEmpToken(String.valueOf(data[i][0]));// token
				leaveEn.setEmpName(checkNull(String.valueOf(data[i][1])));// employee name
				leaveEn.setCenterName(checkNull(String.valueOf(data[i][2])));// branch
				leaveEn.setRankName(checkNull(String.valueOf(data[i][3])));// designation
				leaveEn.setEmpId(checkNull(String.valueOf(data[i][4])));// employee id
				leaveEn.setDept(checkNull(String.valueOf(data[i][6])));// dept name
			}// end of for
			String query = " SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL ";
			Object result[][] = getSqlModel().getSingleResult(query);
			leaveEn.setEncashDate(String.valueOf(result[0][0]));
		} catch (Exception e) {
			logger.error("Exception in empDetails---------------" + e);
		}
		return leaveEn;
	}// end of empDetails

	/**
	 * THIS METHOD IS USED FOR GETTING SAVED LEAVE RECORD
	 * 
	 * @param leaveEn
	 * @return
	 */
	public String[][] getViewLeaveRecord(LeaveEncashment leaveEn) {

		String[][] total1 = null;
		try {
			String policyCode = getLeavePolicyCode(leaveEn.getEmpId());

			String query = " SELECT TO_CHAR(LEAVE_NAME),NVL(HRMS_LEAVE_BALANCE.LEAVE_CLOSING_BALANCE,0),"
					+ " NVL(LEAVE_ENCASHED,0),NVL(HRMS_LEAVE_ENCASH_DTL.ENCASH_AMOUNT,0),HRMS_LEAVE.LEAVE_ID,"
					+ " HRMS_LEAVE_POLICY_DTL.LEAVE_ENC_FORMULA,"
					+ " NVL(HRMS_LEAVE_POLICY_DTL.LEAVE_ENC_MAXLIMIT,0),NVL(HRMS_LEAVE_BALANCE.LEAVE_ONHOLD,0),"
					+ " DECODE(HRMS_LEAVE_POLICY_DTL.LEAVE_ENC_MAXLIMIT_DURATION,'Mo','Monthly','Qu','Quarterly','Hy','Half Yearly','Ye','Yearly') "
					+ " FROM HRMS_LEAVE_ENCASH_HDR "
					+ " LEFT JOIN HRMS_LEAVE_ENCASH_DTL ON(HRMS_LEAVE_ENCASH_DTL.ENCASH_CODE=HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE) "
					+ " INNER JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE_BALANCE.LEAVE_CODE=HRMS_LEAVE_ENCASH_DTL.LEAVE_CODE AND HRMS_LEAVE_BALANCE.EMP_ID="
					+ leaveEn.getEmpId()
					+ ") "
					+ " LEFT JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_ENCASH_DTL.LEAVE_CODE) "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LEAVE_ENCASH_HDR.EMP_ID)  "
					+ " INNER JOIN HRMS_LEAVE_POLICY_DTL ON(HRMS_LEAVE_POLICY_DTL.LEAVE_CODE=HRMS_LEAVE.LEAVE_ID AND LEAVE_APPLICABLE='Y' AND LEAVE_ENC_FLAG='Y'"
					+ " AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE="
					+ policyCode
					+ ")"
					+ " WHERE HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE="
					+ leaveEn.getEnCode()
					+ " "
					+ "order by HRMS_LEAVE.LEAVE_ID ";

			Object[][] data1 = getSqlModel().getSingleResult(query);

			total1 = new String[data1.length][11];

			if (data1 != null && data1.length > 0) {

				for (int i = 0; i < data1.length; i++) {
					double totEncashDays = 0.0d;
					double availabelimit = 0.0d;
					total1[i][0] = String.valueOf(data1[i][0]);// leave name
					total1[i][1] = String.valueOf(data1[i][1]);// leave closing
					// balance
					total1[i][2] = String.valueOf(data1[i][3]);// encash amount
					total1[i][3] = String.valueOf(data1[i][2]);// leave to be
					// encashed
					total1[i][4] = String.valueOf(data1[i][4]);// leave code
					total1[i][6] = String.valueOf(data1[i][6]);// leave encash
					// maximum limit
					total1[i][7] = String.valueOf(data1[i][7]);// leave onhold
					if (data1[i][6]==null || data1[i][6]=="null" || String.valueOf(data1[i][6]).trim().equals("")) {
						total1[i][8] = "NA";
					} else {
						total1[i][8] = String.valueOf(data1[i][6]) + " " + String.valueOf(data1[i][8]) ;// encash limit
					}
					try {
						double amount = 0;
						amount = Utility
								.expressionEvaluate(new Utility()
										.generateFormula(leaveEn.getEmpId(),
												String.valueOf(data1[i][5]),
												context, session));
						total1[i][5] = String.valueOf(Math.round(amount));// encash
						// formula
					} // end of try
					catch (Exception e) {
						// TODO: handle exception
						logger
								.error("Exception in getViewLeaveRecord1---------------"
										+ e);
					} // end of catch
					
					final String checkQuery = " SELECT NVL(LEAVE_ENC_MAXLIMIT,0), LEAVE_ENC_MAXLIMIT_DURATION,LEAVE_MGTYEAR_MONTH_START,LEAVE_MGTYEAR_MONTH_END " + 
					" , LEAVE_NAME FROM HRMS_LEAVE_POLICY_DTL " + 
					" LEFT JOIN HRMS_LEAVE_POLICY_HDR ON (HRMS_LEAVE_POLICY_HDR.LEAVE_POLICY_CODE = HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE) " + 
					" LEFT JOIN HRMS_LEAVE ON (HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_POLICY_DTL.LEAVE_CODE)  " + 
					" WHERE HRMS_LEAVE_POLICY_HDR.LEAVE_POLICY_CODE = " + policyCode + 
					" AND LEAVE_CODE = " + String.valueOf(data1[i][4]);
					final Object[][] checkObj = getSqlModel().getSingleResult(checkQuery);
					final int yearStartMonth = Integer.parseInt(String.valueOf(checkObj[0][2]));
			
					final int encashMonth = Integer.parseInt(leaveEn.getEncashDate().substring(3,5));
					final int encashYear = Integer.parseInt(leaveEn.getEncashDate().substring(6,10));
					final int[] monthArray = this.getMonthArray(encashMonth, String.valueOf(checkObj[0][1]), yearStartMonth);
					int[] monthArray2 = null;
					int[] monthArray3 = null;
					for (int j = 0; j < monthArray.length; j++) {	
						System.out.println("monthArray.length:"+monthArray.length);
						System.out.println("j:" +j+ "      monthArray[j] :" + monthArray[j]);
						if(monthArray[j] == 12 && monthArray.length > j+1){
							monthArray2 =  new int[j+1];
							monthArray3 =  new int[monthArray.length - j+1];
							for (int k = 0; k < monthArray.length; k++) {
								if(k < monthArray2.length){
									monthArray2[k] = monthArray[k];
								} else {
									monthArray3[k - monthArray2.length] = monthArray[k];
								}
								
							}
							break;
						}					
					}
					
							
					if (monthArray.length > 0) {
						String chkPrevEncQuery = " SELECT HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE, " +
								" NVL(LEAVE_ENCASHED,0), TO_CHAR(ENCASH_DATE,'DD-MM-YYYY') "	+ 
								" FROM HRMS_LEAVE_ENCASH_HDR " + 
								" LEFT JOIN HRMS_LEAVE_ENCASH_DTL ON (HRMS_LEAVE_ENCASH_DTL.ENCASH_CODE = HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE) " + 
								" WHERE EMP_ID = " + leaveEn.getEmpId() + 
								" AND LEAVE_CODE = " + String.valueOf(data1[i][4]) + 
								" AND ENCASH_STATUS IN ('A','P') " ;
						if(monthArray2 == null) {
							chkPrevEncQuery += " AND TO_CHAR(ENCASH_DATE,'MM') IN (" + getStringWithComma(monthArray) + ") AND TO_CHAR(ENCASH_DATE,'YYYY') ='"+(encashYear)+"'";
						} else {
							chkPrevEncQuery += " AND (TO_CHAR(ENCASH_DATE,'MM') IN (" + getStringWithComma(monthArray2) + ") AND TO_CHAR(ENCASH_DATE,'YYYY') ='"+(encashYear-1)+"'" 
									+	"OR TO_CHAR(ENCASH_DATE,'MM') IN (" + getStringWithComma(monthArray3) + ") AND TO_CHAR(ENCASH_DATE,'YYYY') ='"+ encashYear +"')";
						}
						Object[][] chkPrevEncObj = getSqlModel().getSingleResult(chkPrevEncQuery);
						if (chkPrevEncObj != null && chkPrevEncObj.length > 0) {
							for (int j = 0; j < chkPrevEncObj.length; j++) {
								totEncashDays += Double.parseDouble(String.valueOf(chkPrevEncObj[j][1]));
							}
						}
						
						
						String chkPrevEncQuery1 = " SELECT HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_ENCASH_LEAVE "
										+ " FROM HRMS_ENCASHMENT_PROCESS_DTL "
										+ " INNER JOIN HRMS_ENCASHMENT_PROCESS_HDR ON(HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_CODE = HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_PROCESS_CODE) " 
										+ " WHERE HRMS_ENCASHMENT_PROCESS_DTL.EMP_ID= " + leaveEn.getEmpId() 
										+ " AND HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_LEAVE_CODE= " + String.valueOf(data1[i][4]);
						if(monthArray2 == null) {
							chkPrevEncQuery += " AND TO_CHAR(HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_DATE,'MM') IN (" + getStringWithComma(monthArray) + ") "
									+ " AND TO_CHAR(HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_DATE,'YYYY') ='"+(encashYear-1)+"'";
						} else {
							chkPrevEncQuery += " AND (TO_CHAR(HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_DATE,'MM') IN (" + getStringWithComma(monthArray2) + ") " 
									+ " AND TO_CHAR(HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_DATE,'YYYY') ='"+(encashYear-1)+"'" 
									+ " OR TO_CHAR(HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_DATE,'MM') IN (" + getStringWithComma(monthArray3) + ") " 
									+ " AND TO_CHAR(HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_DATE,'YYYY') ='"+ encashYear +"')";
						}
						Object[][] chkPrevEncObj1 = getSqlModel().getSingleResult(chkPrevEncQuery1);
						if (chkPrevEncObj1 != null && chkPrevEncObj1.length > 0) {
							totEncashDays += Double.parseDouble(String.valueOf(chkPrevEncObj1[0][0]));
						}
						
						
					}
					total1[i][9] = "" + totEncashDays;
					if (data1[i][6]==null || data1[i][6]=="null" || String.valueOf(data1[i][6]).trim().equals("")) {
						total1[i][10] = "NA";
					} else {
						availabelimit = Double.parseDouble(String.valueOf(data1[i][6])) - totEncashDays;
						total1[i][10] = "" + formatter.format(availabelimit);
					}
					
				}// end of for

			}// end of if
		} catch (Exception e) {
			logger.error("Exception in getViewLeaveRecord2---------------" + e);
			e.printStackTrace();
		}
		return total1;
	}// end of getViewLeaveRecord

	/**
	 * THIS METHOD IS USED FOR GETTING SAVED LEAVE RECORD
	 * 
	 * @param leaveEn
	 * @return
	 */
	public String[][] getEncashDetails(LeaveEncashment leaveEn) {

		String[][] total1 = null;
		try {
			final String policyCode = getLeavePolicyCode(leaveEn.getEmpId());
			
			String query = " SELECT TO_CHAR(LEAVE_NAME),NVL(HRMS_LEAVE_BALANCE.LEAVE_CLOSING_BALANCE,0),"
					+ " NVL(LEAVE_ENCASHED,0),NVL(HRMS_LEAVE_ENCASH_DTL.ENCASH_AMOUNT,0),HRMS_LEAVE.LEAVE_ID,"
					+ " NVL(HRMS_LEAVE_BALANCE.LEAVE_ONHOLD,0), HRMS_LEAVE_POLICY_DTL.LEAVE_ENC_MAXLIMIT, "					
					+ " NVL(DECODE(HRMS_LEAVE_POLICY_DTL.LEAVE_ENC_MAXLIMIT_DURATION,'Mo','Monthly','Qu','Quarterly','Hy','Half Yearly','Ye','Yearly'),'Yearly') "
					+ " FROM HRMS_LEAVE_ENCASH_HDR "
					+ " LEFT JOIN HRMS_LEAVE_ENCASH_DTL ON(HRMS_LEAVE_ENCASH_DTL.ENCASH_CODE=HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE) "
					+ " INNER JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE_BALANCE.LEAVE_CODE=HRMS_LEAVE_ENCASH_DTL.LEAVE_CODE AND HRMS_LEAVE_BALANCE.EMP_ID="
					+ leaveEn.getEmpId()
					+ ") "
					+ " LEFT JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_ENCASH_DTL.LEAVE_CODE) "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LEAVE_ENCASH_HDR.EMP_ID)  "					
					+ " INNER JOIN HRMS_LEAVE_POLICY_DTL ON(HRMS_LEAVE_POLICY_DTL.LEAVE_CODE=HRMS_LEAVE_ENCASH_DTL.LEAVE_CODE " 
					+ " AND HRMS_LEAVE_POLICY_DTL.LEAVE_APPLICABLE='Y' AND HRMS_LEAVE_POLICY_DTL.LEAVE_ENC_FLAG='Y'"
					+ " AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_GENDER IN('B',HRMS_EMP_OFFC.EMP_GENDER)" 
					+ " AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE="
					+ policyCode
					+ " )"					
					+ " WHERE HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE="
					+ leaveEn.getEnCode()
					+ " "
					+ " ORDER BY HRMS_LEAVE.LEAVE_ID ";

			Object[][] data1 = getSqlModel().getSingleResult(query);

			total1 = new String[data1.length][9];

			if (data1 != null && data1.length > 0) {
				for (int i = 0; i < data1.length; i++) {
					total1[i][0] = String.valueOf(data1[i][0]);// leave name
					total1[i][1] = String.valueOf(data1[i][1]);// leave closing
					// balance
					total1[i][2] = String.valueOf(data1[i][3]);// encash amount
					total1[i][3] = String.valueOf(data1[i][2]);// leave to be
					// encashed
					total1[i][4] = String.valueOf(data1[i][4]);// leave code
					total1[i][6] = "0"; // leave encash
					// maximum limit
					total1[i][7] = String.valueOf(data1[i][5]);// leave onhold
					try {
						double amount = 0;
						amount = Utility
								.expressionEvaluate(new Utility()
										.generateFormula(leaveEn.getEmpId(),
												String.valueOf(data1[i][5]),
												context, session));
						total1[i][5] = String.valueOf(Math.round(amount));// encash
						// formula
					} // end of try
					catch (Exception e) {
						// TODO: handle exception
						logger
								.error("Exception in getEncashDetails1---------------"
										+ e);
					} // end of catch
					
					if (data1[i][6]==null || data1[i][6]=="null" || String.valueOf(data1[i][6]).trim().equals("")) {
						total1[i][8] = "NA";
					} else {
						total1[i][8] = String.valueOf(data1[i][6]) + " " + String.valueOf(data1[i][7]) ;// encash limit
					}
					
				}// end of for

			}// end of if
		} catch (Exception e) {
			logger.error("Exception in getEncashDetails2---------------" + e);
		}
		return total1;
	}// end of getEncashDetails

	/**
	 * THIS METHOD IS USED FOR GETTING LEAVE ENCASHMENT HISTORY
	 * 
	 * @param leaveEn
	 */
	public void getEncashHistory(LeaveEncashment leaveEn) {
		// TODO Auto-generated method stub
		try {
			String query = "";
			if (leaveEn.getEncashSelect().equals("A")) {
				query = " SELECT TO_CHAR(LEAVE_NAME),HRMS_LEAVE_BALANCE.LEAVE_CLOSING_BALANCE,NVL(HRMS_LEAVE_BALANCE.LEAVE_ONHOLD,0),LEAVE_ENCASHED,HRMS_LEAVE_ENCASH_DTL.ENCASH_AMOUNT,DECODE(HRMS_LEAVE_ENCASH_HDR.ENCASH_STATUS,'P','Pending','N','Cancelled','R','Rejected','A','Approved'),TO_CHAR(HRMS_LEAVE_ENCASH_HDR.ENCASH_DATE,'DD-MM-YYYY'),HRMS_LEAVE.LEAVE_ID  "
						+ " FROM HRMS_LEAVE_ENCASH_HDR  "
						+ " LEFT JOIN HRMS_LEAVE_ENCASH_DTL ON(HRMS_LEAVE_ENCASH_DTL.ENCASH_CODE=HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE) "
						+ " INNER JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE_BALANCE.LEAVE_CODE=HRMS_LEAVE_ENCASH_DTL.LEAVE_CODE AND HRMS_LEAVE_BALANCE.EMP_ID="
						+ leaveEn.getEmpId()
						+ " ) "
						+ " LEFT JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_ENCASH_DTL.LEAVE_CODE) "
						+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LEAVE_ENCASH_HDR.EMP_ID)  "
						+ " WHERE HRMS_LEAVE_ENCASH_HDR.EMP_ID="
						+ leaveEn.getEmpId()
						+ " AND LEAVE_ENCASHED!=0  "
						+ " ORDER BY  HRMS_LEAVE.LEAVE_ID ";

			}// end of if
			else if (leaveEn.getEncashSelect().equals("CM")) {
				query = " SELECT TO_CHAR(LEAVE_NAME),HRMS_LEAVE_BALANCE.LEAVE_CLOSING_BALANCE,NVL(HRMS_LEAVE_BALANCE.LEAVE_ONHOLD,0),LEAVE_ENCASHED,HRMS_LEAVE_ENCASH_DTL.ENCASH_AMOUNT,DECODE(HRMS_LEAVE_ENCASH_HDR.ENCASH_STATUS,'P','Pending','N','Cancelled','R','Rejected','A','Approved'),TO_CHAR(HRMS_LEAVE_ENCASH_HDR.ENCASH_DATE,'DD-MM-YYYY'),HRMS_LEAVE.LEAVE_ID  "
						+ " FROM HRMS_LEAVE_ENCASH_HDR  "
						+ " LEFT JOIN HRMS_LEAVE_ENCASH_DTL ON(HRMS_LEAVE_ENCASH_DTL.ENCASH_CODE=HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE) "
						+ " INNER JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE_BALANCE.LEAVE_CODE=HRMS_LEAVE_ENCASH_DTL.LEAVE_CODE AND HRMS_LEAVE_BALANCE.EMP_ID="
						+ leaveEn.getEmpId()
						+ " ) "
						+ " LEFT JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_ENCASH_DTL.LEAVE_CODE) "
						+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LEAVE_ENCASH_HDR.EMP_ID)  "
						+ " WHERE HRMS_LEAVE_ENCASH_HDR.EMP_ID="
						+ leaveEn.getEmpId()
						+ " AND LEAVE_ENCASHED!=0 "
						+ " AND TO_CHAR(HRMS_LEAVE_ENCASH_HDR.ENCASH_DATE,'MM')=TO_CHAR(SYSDATE,'MM') "
						+ " ORDER BY  HRMS_LEAVE.LEAVE_ID ";
			}// end of else if
			else if (leaveEn.getEncashSelect().equals("PM")) {
				query = " SELECT TO_CHAR(LEAVE_NAME),HRMS_LEAVE_BALANCE.LEAVE_CLOSING_BALANCE,NVL(HRMS_LEAVE_BALANCE.LEAVE_ONHOLD,0),LEAVE_ENCASHED,HRMS_LEAVE_ENCASH_DTL.ENCASH_AMOUNT,DECODE(HRMS_LEAVE_ENCASH_HDR.ENCASH_STATUS,'P','Pending','N','Cancelled','R','Rejected','A','Approved'),TO_CHAR(HRMS_LEAVE_ENCASH_HDR.ENCASH_DATE,'DD-MM-YYYY'),HRMS_LEAVE.LEAVE_ID  "
						+ " FROM HRMS_LEAVE_ENCASH_HDR  "
						+ " LEFT JOIN HRMS_LEAVE_ENCASH_DTL ON(HRMS_LEAVE_ENCASH_DTL.ENCASH_CODE=HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE) "
						+ " INNER JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE_BALANCE.LEAVE_CODE=HRMS_LEAVE_ENCASH_DTL.LEAVE_CODE AND HRMS_LEAVE_BALANCE.EMP_ID="
						+ leaveEn.getEmpId()
						+ " ) "
						+ " LEFT JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_ENCASH_DTL.LEAVE_CODE) "
						+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LEAVE_ENCASH_HDR.EMP_ID)  "
						+ " WHERE HRMS_LEAVE_ENCASH_HDR.EMP_ID="
						+ leaveEn.getEmpId()
						+ " AND LEAVE_ENCASHED!=0 "
						+ " AND TO_CHAR(HRMS_LEAVE_ENCASH_HDR.ENCASH_DATE,'MM')=TO_CHAR(SYSDATE,'MM')-1 "
						+ " ORDER BY  HRMS_LEAVE.LEAVE_ID ";
			}// end of else if
			else if (leaveEn.getEncashSelect().equals("CY")) {
				query = " SELECT TO_CHAR(LEAVE_NAME),HRMS_LEAVE_BALANCE.LEAVE_CLOSING_BALANCE,NVL(HRMS_LEAVE_BALANCE.LEAVE_ONHOLD,0),LEAVE_ENCASHED,HRMS_LEAVE_ENCASH_DTL.ENCASH_AMOUNT,DECODE(HRMS_LEAVE_ENCASH_HDR.ENCASH_STATUS,'P','Pending','N','Cancelled','R','Rejected','A','Approved'),TO_CHAR(HRMS_LEAVE_ENCASH_HDR.ENCASH_DATE,'DD-MM-YYYY'),HRMS_LEAVE.LEAVE_ID  "
						+ " FROM HRMS_LEAVE_ENCASH_HDR  "
						+ " LEFT JOIN HRMS_LEAVE_ENCASH_DTL ON(HRMS_LEAVE_ENCASH_DTL.ENCASH_CODE=HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE) "
						+ " INNER JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE_BALANCE.LEAVE_CODE=HRMS_LEAVE_ENCASH_DTL.LEAVE_CODE AND HRMS_LEAVE_BALANCE.EMP_ID="
						+ leaveEn.getEmpId()
						+ " ) "
						+ " LEFT JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_ENCASH_DTL.LEAVE_CODE) "
						+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LEAVE_ENCASH_HDR.EMP_ID)  "
						+ " WHERE HRMS_LEAVE_ENCASH_HDR.EMP_ID="
						+ leaveEn.getEmpId()
						+ " AND LEAVE_ENCASHED!=0  "
						+ " AND TO_CHAR(HRMS_LEAVE_ENCASH_HDR.ENCASH_DATE,'YYYY')=TO_CHAR(SYSDATE,'YYYY') "
						+ " ORDER BY  HRMS_LEAVE.LEAVE_ID ";
			}// end of else if
			else if (leaveEn.getEncashSelect().equals("PY")) {
				query = " SELECT TO_CHAR(LEAVE_NAME),HRMS_LEAVE_BALANCE.LEAVE_CLOSING_BALANCE,NVL(HRMS_LEAVE_BALANCE.LEAVE_ONHOLD,0),LEAVE_ENCASHED,HRMS_LEAVE_ENCASH_DTL.ENCASH_AMOUNT,DECODE(HRMS_LEAVE_ENCASH_HDR.ENCASH_STATUS,'P','Pending','N','Cancelled','R','Rejected','A','Approved'),TO_CHAR(HRMS_LEAVE_ENCASH_HDR.ENCASH_DATE,'DD-MM-YYYY'),HRMS_LEAVE.LEAVE_ID  "
						+ " FROM HRMS_LEAVE_ENCASH_HDR  "
						+ " LEFT JOIN HRMS_LEAVE_ENCASH_DTL ON(HRMS_LEAVE_ENCASH_DTL.ENCASH_CODE=HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE) "
						+ " INNER JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE_BALANCE.LEAVE_CODE=HRMS_LEAVE_ENCASH_DTL.LEAVE_CODE AND HRMS_LEAVE_BALANCE.EMP_ID="
						+ leaveEn.getEmpId()
						+ " ) "
						+ " LEFT JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_ENCASH_DTL.LEAVE_CODE) "
						+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LEAVE_ENCASH_HDR.EMP_ID)  "
						+ " WHERE HRMS_LEAVE_ENCASH_HDR.EMP_ID="
						+ leaveEn.getEmpId()
						+ "  AND LEAVE_ENCASHED!=0 "
						+ " AND TO_CHAR(HRMS_LEAVE_ENCASH_HDR.ENCASH_DATE,'YYYY')=TO_CHAR(SYSDATE,'YYYY')-1 "
						+ " ORDER BY  HRMS_LEAVE.LEAVE_ID ";
			}// end of else if
			else {
				query = " SELECT TO_CHAR(LEAVE_NAME),HRMS_LEAVE_BALANCE.LEAVE_CLOSING_BALANCE,NVL(HRMS_LEAVE_BALANCE.LEAVE_ONHOLD,0),LEAVE_ENCASHED,HRMS_LEAVE_ENCASH_DTL.ENCASH_AMOUNT,DECODE(HRMS_LEAVE_ENCASH_HDR.ENCASH_STATUS,'P','Pending','N','Cancelled','R','Rejected','A','Approved'),TO_CHAR(HRMS_LEAVE_ENCASH_HDR.ENCASH_DATE,'DD-MM-YYYY'),HRMS_LEAVE.LEAVE_ID  "
						+ " FROM HRMS_LEAVE_ENCASH_HDR  "
						+ " LEFT JOIN HRMS_LEAVE_ENCASH_DTL ON(HRMS_LEAVE_ENCASH_DTL.ENCASH_CODE=HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE) "
						+ " INNER JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE_BALANCE.LEAVE_CODE=HRMS_LEAVE_ENCASH_DTL.LEAVE_CODE AND HRMS_LEAVE_BALANCE.EMP_ID="
						+ leaveEn.getEmpId()
						+ " ) "
						+ " LEFT JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_ENCASH_DTL.LEAVE_CODE) "
						+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LEAVE_ENCASH_HDR.EMP_ID)  "
						+ " WHERE HRMS_LEAVE_ENCASH_HDR.EMP_ID="
						+ leaveEn.getEmpId()
						+ " AND LEAVE_ENCASHED!=0 "
						+ " AND TO_CHAR(HRMS_LEAVE_ENCASH_HDR.ENCASH_DATE,'MM')=TO_CHAR(SYSDATE,'MM') "
						+ " ORDER BY HRMS_LEAVE.LEAVE_ID ";
			}// end of else
			Object[][] resultObj = getSqlModel().getSingleResult(query);
			ArrayList<Object> arrList = new ArrayList<Object>();
			if (resultObj != null && resultObj.length > 0) {
				for (int i = 0; i < resultObj.length; i++) {
					LeaveEncashment leaveEncash = new LeaveEncashment();
					leaveEncash.setLeaveType(String.valueOf(resultObj[i][0]));// leave
					// name
					leaveEncash.setLevCloseBalance(String
							.valueOf(resultObj[i][1])); // leave closing balance
					leaveEncash.setLeaveOnhold(String.valueOf(resultObj[i][2])); // leave
					// on
					// hold
					leaveEncash.setLeaveEncashed(String
							.valueOf(resultObj[i][3])); // leave to be encashed
					leaveEncash
							.setEncashAmount(String.valueOf(resultObj[i][4])); // encash
					// amount
					leaveEncash
							.setEncashStatus(String.valueOf(resultObj[i][5])); // encash
					// status
					leaveEncash.setLeaveId(String.valueOf(resultObj[i][7])); // leave
					// code
					arrList.add(leaveEncash);
				}// end of for
				leaveEn.setList(arrList);
			}// end of if
		} // end of try
		catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Exception in getEncashHistory---------------" + e);
		}// end of catch
	}// end of getEncashHistory

	/**
	 * THIS METHOD IS USED FOR GETTING LEAVE RECORD
	 * 
	 * @param leaveEn
	 */

	public void getViewlevRecord(LeaveEncashment leaveEn) {

		try {
			String query = " SELECT HRMS_LEAVE_ENCASH_HDR.ENCASH_COMMENT,HRMS_LEAVE_ENCASH_HDR.ENCASH_AMOUNT "
					+ " FROM HRMS_LEAVE_ENCASH_HDR  "
					+ " LEFT JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE)  "
					+ " WHERE EMP_ID="
					+ leaveEn.getEmpId()
					+ " AND HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE="
					+ leaveEn.getEnCode() + " ";
			Object[][] values = getSqlModel().getSingleResult(query);
			if (values.length > 0) {
				leaveEn.setEnComment(checkNull(String.valueOf(values[0][0])));// encash
				// comments
				leaveEn.setTotalAmt(checkNull(String.valueOf(values[0][1])));// encash
				// total
				// amount
			}// end of if
		} catch (Exception e) {
			logger.error("Exception in getViewlevRecord---------------" + e);
		}

	}// end of getViewlevRecord

	/**
	 * THIS METHOD IS USED FOR SAVING RECORD
	 * 
	 * @param leaveEn
	 * @param leaveid------leave
	 *            code
	 * @param balanceleave------leave
	 *            balance
	 * @param encashable-----------leave
	 *            to be encashed
	 * @param singleamount----------------encash
	 *            amount
	 * @return boolean
	 */
	public boolean addEncashHdr(LeaveEncashment leaveEn, String[] leaveid,
			String[] balanceleave, String[] encashable, String[] singleamount,
			String[] onhold, String[] keepInformToCode) {

		boolean result = false;
		String encashCode = "";
		Object encashCodeObj[][] = null;
		String keepInformCode = "";
		if(keepInformToCode != null && keepInformToCode.length > 0){
			for(int i=0; i<keepInformToCode.length; i++){
				keepInformCode = keepInformCode + keepInformToCode[i] + ",";
			}
		}
		
		try {

			if (leaveEn.getEnCode().equals("")) {
				String policyCode = getLeavePolicyCode(leaveEn.getEmpId());
				String queryForPolicy = "SELECT NVL(LEAVE_ENCASH_AUTOAPPROVAL,'N') FROM HRMS_LEAVE_POLICY_HDR WHERE LEAVE_POLICY_CODE="
						+ policyCode;
				String hdrQuery = " INSERT INTO HRMS_LEAVE_ENCASH_HDR (ENCASH_CODE,EMP_ID,ENCASH_DATE," +
						"ENCASH_AMOUNT,ENCASH_COMMENT,ENCASH_STATUS,ENCASH_KEEP_INFORM_ID, ENCASH_APP_CODE" +
						")"
						+ " VALUES ((SELECT NVL(MAX(ENCASH_CODE),0)+1 FROM HRMS_LEAVE_ENCASH_HDR),"
						+ leaveEn.getEmpId()
						+ ","
						+ " (SYSDATE),"
						+ leaveEn.getTotalAmt()
						+ ",'"
						+ leaveEn.getEnComment()
						+ "',";
				Object[][] policyObj = getSqlModel().getSingleResult(
						queryForPolicy);
				if (policyObj != null && policyObj.length > 0
						&& String.valueOf(policyObj[0][0]).equals("Y")) {
					hdrQuery += "'A',";
				}else{
					hdrQuery += "'D',";	
				}
				
				hdrQuery += "'" + keepInformCode + "',";
				hdrQuery += "(SELECT 'LEAVE_ENCASH'||(NVL(MAX(ENCASH_CODE),0)+1) FROM HRMS_LEAVE_ENCASH_HDR))"; 

				result = getSqlModel().singleExecute(hdrQuery);

				if (result) {

					String encashCodeQuery = " SELECT MAX(ENCASH_CODE) FROM HRMS_LEAVE_ENCASH_HDR ";

					encashCodeObj = getSqlModel().getSingleResult(
							encashCodeQuery);
					leaveEn.setEnCode(String.valueOf(encashCodeObj[0][0]));
					encashCode = String.valueOf(encashCodeObj[0][0]);
				}

				if (leaveid != null && leaveid.length > 0) {
					for (int i = 0; i < leaveid.length; i++) {
						String insertDtlQuery = " INSERT INTO HRMS_LEAVE_ENCASH_DTL(ENCASH_CODE,LEAVE_CODE,LEAVE_BALANCE,LEAVE_ENCASHED,ENCASH_AMOUNT) "
								+ " VALUES ("
								+ encashCode
								+ ","
								+ leaveid[i]
								+ ","
								+ balanceleave[i]
								+ ","
								+ encashable[i]
								+ "," + singleamount[i] + ") ";
						result = getSqlModel().singleExecute(insertDtlQuery);
					}// end of for

					if (result) {
						Object updateLeavBal[][] = new Object[leaveid.length][3];
						for (int i = 0; i < updateLeavBal.length; i++) {
							double closingBal = 0;
							double onholdvar = 0;

							closingBal = Double.parseDouble(balanceleave[i])
									- Double.parseDouble(encashable[i]);
							String finCloseBal = new Utility()
									.twoDecimals(closingBal);

							if (policyObj != null && policyObj.length > 0
									&& String.valueOf(policyObj[0][0]).equals("Y")) {
								onholdvar =0;
							}else{
								onholdvar = Double.parseDouble(onhold[i])
								+ Double.parseDouble(encashable[i]);
	
							}
							
							updateLeavBal[i][0] = closingBal;// closing
																// balance
							updateLeavBal[i][1] = leaveid[i];// leave code
							updateLeavBal[i][2] = leaveEn.getEmpId();// employee
							// id
							String updateBalanceQuery = "UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE="
									+ finCloseBal
									+ ",LEAVE_ONHOLD ="
									+ onholdvar
									+ "  WHERE  LEAVE_CODE="
									+ updateLeavBal[i][1]
									+ " AND EMP_ID="
									+ updateLeavBal[i][2] + "   ";
							result = getSqlModel().singleExecute(
									updateBalanceQuery);
						}
					}

				}
			}// end of if
		} catch (Exception e) {
			logger.info("Exception in addEncashHdr-----------" + e);
		}
		return result;

	}// end of addEncashHdr

	/**
	 * THIS METHOD IS USED FOR MODIFYING RECORD
	 * 
	 * @param leaveEn
	 * @param onhold
	 * @param oldEncashDays
	 * @param apprStatus 
	 * @param leaveid------leave
	 *            code
	 * @param balanceleave------leave
	 *            balance
	 * @param encashable-----------leave
	 *            to be encashed
	 * @param singleamount----------------encash
	 *            amount
	 * @return boolean
	 */
	public boolean modEncashHdr(LeaveEncashment leaveEn, String[] leaveid,
			String[] balanceleave, String[] encashable, String[] singleamount,
			String[] onhold, String[] oldEncashDays, String[] keepInformToCode, String apprStatus) {
		boolean result = false;
		System.out.println("apprStatus-------------------"+apprStatus);
		String keepInformCode = "";
		if(keepInformToCode != null && keepInformToCode.length > 0){
			for(int i=0; i<keepInformToCode.length; i++){
				keepInformCode = keepInformCode + keepInformToCode[i] + ",";
			}
		}
		try {
			String updateHdrQuery = " UPDATE HRMS_LEAVE_ENCASH_HDR SET EMP_ID="
					+ leaveEn.getEmpId() + ",ENCASH_AMOUNT="
					+ leaveEn.getTotalAmt() + ", " + "ENCASH_COMMENT='"	+ leaveEn.getEnComment() + "',ENCASH_STATUS = '"+apprStatus+"',ENCASH_LEVEL = '1' WHERE ENCASH_CODE="
					+ leaveEn.getEnCode() + "";

			result = getSqlModel().singleExecute(updateHdrQuery);

			if (result) {
				String deleteDtlQuery = " DELETE FROM HRMS_LEAVE_ENCASH_DTL WHERE ENCASH_CODE="
						+ leaveEn.getEnCode() + "";

				result = getSqlModel().singleExecute(deleteDtlQuery);
			}
			
			if (result) {
				String updateQuery = " UPDATE HRMS_LEAVE_ENCASH_HDR SET ENCASH_KEEP_INFORM_ID='"
					+ keepInformCode 
					+ "' WHERE ENCASH_CODE="
					+ leaveEn.getEnCode() + "";

				result = getSqlModel().singleExecute(updateQuery);
			}
			
			

			if (result) {
				if (leaveid != null && leaveid.length > 0) {
					for (int i = 0; i < leaveid.length; i++) {
						String dt2 = "INSERT INTO HRMS_LEAVE_ENCASH_DTL(ENCASH_CODE,LEAVE_CODE,LEAVE_BALANCE,LEAVE_ENCASHED,ENCASH_AMOUNT)"
								+ "VALUES ("
								+ leaveEn.getEnCode()
								+ ","
								+ leaveid[i]
								+ ","
								+ balanceleave[i]
								+ ","
								+ encashable[i] + "," + singleamount[i] + ")";

						result = getSqlModel().singleExecute(dt2);
					}// end of for

					if (result) {
						Object updateLeavBal[][] = new Object[leaveid.length][3];
						for (int i = 0; i < updateLeavBal.length; i++) {
							double closingBal = 0;
							double onholdvar = 0;

							closingBal = (Double.parseDouble(balanceleave[i]) + Double
									.parseDouble(oldEncashDays[i]))
									- Double.parseDouble(encashable[i]);
							String finCloseBal = new Utility()
									.twoDecimals(closingBal);

							onholdvar = (Double.parseDouble(onhold[i]) - Double
									.parseDouble(oldEncashDays[i]))
									+ Double.parseDouble(encashable[i]);
							updateLeavBal[i][0] = closingBal;// closing
																// balance
							updateLeavBal[i][1] = leaveid[i];// leave code
							updateLeavBal[i][2] = leaveEn.getEmpId();// employee
							// id
							String updateBalanceQuery = "UPDATE HRMS_LEAVE_BALANCE SET LEAVE_CLOSING_BALANCE="
									+ finCloseBal
									+ ",LEAVE_ONHOLD ="
									+ onholdvar
									+ "  WHERE  LEAVE_CODE="
									+ updateLeavBal[i][1]
									+ " AND EMP_ID="
									+ updateLeavBal[i][2] + "   ";
							result = getSqlModel().singleExecute(
									updateBalanceQuery);
						}
					}

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Exception in modEncashHdr------------ " + e);
		}
		return result;
	}// end of modEncashHdr

	/**
	 * THIS METHOD IS USED FOR LOCK THE RECORD
	 * 
	 * @param leaveEn
	 * @return boolean
	 */
	public boolean lockrecord(LeaveEncashment leaveEn) {

		boolean result = false;
		try {
			String query2 = "UPDATE HRMS_LEAVE_ENCASH_HDR SET ENCASH_LOCK='L' WHERE EMP_ID="
					+ leaveEn.getEmpId()
					+ " "
					+ "AND ENCASH_CODE="
					+ leaveEn.getEnCode() + " ";
			result = getSqlModel().singleExecute(query2);
		} catch (Exception e) {
			logger.info("Exception in lockrecord----------" + e);
		}
		return result;
	}// end of lockrecord

	/**
	 * THIS METHOD IS USED FOR GENERATING REPORT
	 * 
	 * @param request
	 * @param response
	 * @param leaveEn
	 */
	public void getReport(HttpServletRequest request,
			HttpServletResponse response, LeaveEncashment leaveEn) {
		try {
			String title = "\n Leave Encashment Report\n\n";
			
			String reportType = "pdf";
			
			ReportDataSet  rds = new ReportDataSet();
			String fileName = title;
			String reportPathName=fileName+"."+reportType;
			rds.setFileName(fileName);
			rds.setReportName(title);
			rds.setReportType(reportType);
			rds.setUserEmpId(leaveEn.getUserEmpId());
			rds.setReportHeaderRequired(true);
			
			ReportGenerator rg = null;	
			rg = new ReportGenerator(rds,session,context, request);
			
			String query1 = " SELECT  ENCASH_CODE,EMP_TOKEN, "
					+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '|| "
					+ "	HRMS_EMP_OFFC.EMP_LNAME EMPLOYEENAME, CENTER_NAME,RANK_NAME,DECODE(ENCASH_STATUS,'P','PENDING','A','APPROVED','R','REJECTED','F','FORWARDED')"
					+ "	FROM HRMS_LEAVE_ENCASH_HDR "
					+ "	LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_LEAVE_ENCASH_HDR.EMP_ID)"
					+ "	 LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER)"
					+ "	 LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) "
					+ "  WHERE  ENCASH_CODE=" + leaveEn.getEnCode();
			String query2 = "SELECT ROWNUM,"
					+ "LEAVE_NAME,LEAVE_BALANCE,LEAVE_ENCASHED,ENCASH_AMOUNT"
					+ " FROM HRMS_LEAVE_ENCASH_HDR"
					+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_LEAVE_ENCASH_HDR.EMP_ID)"

					+ " LEFT JOIN HRMS_LEAVE_ENCASH_DTL ON (HRMS_LEAVE_ENCASH_DTL.ENCASH_CODE = HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE)"
					+ "LEFT JOIN HRMS_LEAVE ON (HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_ENCASH_DTL.LEAVE_CODE)"
					+ "WHERE  ENCASH_CODE=" + leaveEn.getEnCode()
					+ " AND LEAVE_ENCASHED !=0 ";
			Object[][] Data = getSqlModel().getSingleResult(query1);
			Object[][] LeavData = getSqlModel().getSingleResult(query2);
			for (int i = 0; i < LeavData.length; i++) {
				if (String.valueOf(LeavData[i][3]).equals("0")) {
					LeavData[i][3] = "";
				}// end of if
				double amount = Double.parseDouble(String.valueOf(LeavData[i][4]));
				LeavData[i][4] = formatter.format(amount);
				
				
			}// end of for
			Object[][] approv = new Object[Data.length][5];
			String approver = " SELECT EMP_TOKEN,(EMP_FNAME ||' '||EMP_MNAME ||' '|| EMP_LNAME ||' '),TO_CHAR(ENCASH_ASSESS_DATE,'DD-MM-YYYY'), "
					+ "		 ENCASH_COMMENTS ABC ,DECODE(ENCASH_STATUS,'P','PENDING','A','APPROVED','R','REJECTED')"
					+ "		 FROM HRMS_LEAVE_ENCASH_PATH"
					+ "		INNER JOIN HRMS_EMP_OFFC ON (HRMS_LEAVE_ENCASH_PATH.ENCASH_ASSESED_BY= HRMS_EMP_OFFC.EMP_ID) "
					+ "	    WHERE ENCASH_CODE="
					+ leaveEn.getEnCode()
					+ " UNION "
					+ "		 SELECT EMP_TOKEN,(EMP_FNAME ||' '||EMP_MNAME ||' '|| EMP_LNAME ||' '),'',"
					+ "	    CAST(''AS NVARCHAR2(100)) A,  DECODE(ENCASH_STATUS,'P','PENDING','A','APPROVED','R','REJECTED') "
					+ "		FROM HRMS_LEAVE_ENCASH_HDR"
					+ "		INNER JOIN HRMS_EMP_OFFC ON (HRMS_LEAVE_ENCASH_HDR.APPROVED_BY= HRMS_EMP_OFFC.EMP_ID) "
					+ "	  WHERE ENCASH_STATUS='P' AND HRMS_LEAVE_ENCASH_HDR.ENCASH_CODE ="
					+ leaveEn.getEnCode() + "";
			Object[][] approverData = getSqlModel().getSingleResult(approver);
			Object[][] approvalTable = new Object[approverData.length][7];
			for (int i = 0; i < approverData.length; i++) {
				approvalTable[i][0] = String.valueOf(i + 1);
				approvalTable[i][1] = checkNull(String
						.valueOf(approverData[i][0]));
				approvalTable[i][2] = checkNull(String
						.valueOf(approverData[i][1]));
				approvalTable[i][3] = checkNull(String
						.valueOf(approverData[i][2]));
				approvalTable[i][4] = checkNull(String
						.valueOf(approverData[i][3]));
				approvalTable[i][5] = checkNull(String
						.valueOf(approverData[i][4]));
			}// end of for
			Object obj[][] = new Object[5][4];
			obj[0][0] = "Encash Code ";
			obj[0][1] = ": " + leaveEn.getEnCode();
			obj[0][2] = "Employee ID  ";
			obj[0][3] = ": " + leaveEn.getEmpToken();
			obj[1][0] = "Employee Name ";
			obj[1][1] = ": " + leaveEn.getEmpName();
			obj[1][2] = "Branch ";
			obj[1][3] = ": " + leaveEn.getCenterName();
			obj[2][0] = "Designation ";
			obj[2][1] = ": " + leaveEn.getRankName();
			obj[2][2] = "Status";
			obj[2][3] = ": " + checkNull(String.valueOf(Data[0][5]));
			obj[3][0] = "Encash Date";
			obj[3][1] = ": " + leaveEn.getEncashDate();
			obj[3][2] = " ";
			obj[3][3] = " ";
			obj[4][0] = "Encash Comment ";
			obj[4][1] = ": " + leaveEn.getEnComment();
			int[] bcellWidth = { 20, 30, 20, 30 };
			int[] bcellAlign = { 0, 0, 0, 0 };
			String appCol[] = { "Sr. No", "Approver Id", "Approver Name",
					"Date", "Remarks", "Status" };
			int appCell1[] = { 5, 10, 25, 10, 30, 10 };
			int apprAlign1[] = { 1, 1, 0, 1, 0, 0 };
			//rg.addFormatedText(title, 6, 0, 1, 0);
			//rg.tableBodyNoBorder(obj, bcellWidth, bcellAlign);
			TableDataSet subtitleName = null;
			subtitleName = new TableDataSet();
			subtitleName.setData(obj);
			subtitleName.setCellAlignment(bcellAlign);
			subtitleName.setCellWidth(bcellWidth);
			subtitleName.setBorder(false);
			subtitleName.setBlankRowsBelow(2);
			rg.addTableToDoc(subtitleName);
			
			
			String[] header = { "Sr No", "Leave Type", "Balance Leave",
					"Leave Encashed", "Amount" };
			int appCell[] = { 5, 30, 15, 20, 30 };
			int apprAlign[] = { 0, 0, 0, 1, 0 };
			//rg.addText("\n\n", 0, 0, 0);
			//rg.tableBody(header, LeavData, appCell, apprAlign);
			
			subtitleName = new TableDataSet();
			subtitleName.setData(LeavData);
			subtitleName.setHeader(header);
			subtitleName.setCellAlignment(apprAlign);
			subtitleName.setCellWidth(appCell);
			subtitleName.setBorderDetail(3);
			subtitleName.setHeaderTable(true);
			subtitleName.setHeaderBorderDetail(3);
			rg.addTableToDoc(subtitleName);
			
			//rg.addText("\n", 0, 0, 0);
			if (LeavData.length > 0) {
				//rg.addText("Total : " + leaveEn.getTotalAmt() + "", 0, 2, 0);
				
				Object[][] data = new Object[1][1];
				data[0][0] = "Total : " + leaveEn.getTotalAmt() ;
					
				subtitleName = new TableDataSet();
				subtitleName.setData(data);
				subtitleName.setCellAlignment(new int[] {2});
				subtitleName.setCellWidth(new int[] {100});
				rg.addTableToDoc(subtitleName);
			}// end of if
			//rg.addFormatedText("Approver Details :", 6, 0, 0, 0);
			
			Object[][] data = new Object[1][1];	
			data[0][0] = "Approver Details :" ;
			subtitleName = new TableDataSet();
			subtitleName.setData(data);
			subtitleName.setCellAlignment(new int[] {0});
			subtitleName.setCellWidth(new int[] {100});
			subtitleName.setBodyFontStyle(1);
			rg.addTableToDoc(subtitleName);
			
			//rg.tableBody(appCol, approvalTable, appCell1, apprAlign1);
			
			subtitleName = new TableDataSet();
			subtitleName.setData(approvalTable);
			subtitleName.setHeader(appCol);
			subtitleName.setCellAlignment(apprAlign1);
			subtitleName.setCellWidth(appCell1);
			subtitleName.setBorderDetail(3);
			subtitleName.setHeaderTable(true);
			subtitleName.setHeaderBorderDetail(3);
			rg.addTableToDoc(subtitleName);
			
			//rg.createReport(response);
			rg.process();
			rg.createReport(response);
		}// end of try
		catch (Exception e) {
			logger.error("Exception in getReport---------------" + e);

		}// end of catch
	}// end of getReport

	/**
	 * THIS METHOD IS USED FOR CHECKING NULL VALUES
	 * 
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

	/**
	 * THIS METHOD IS USED FOR CALCULATING ENCASHMENT AMOUNT
	 * 
	 * @param leaveEn
	 * @param encasheable
	 * @param balanceleave
	 * @param context
	 * @param request
	 * @return
	 */
	public String[][] calculatEncash(LeaveEncashment leaveEn,
			String[] encasheable, String[] balanceleave,
			ServletContext context, HttpServletRequest request) {

		String policyCode = getLeavePolicyCode(leaveEn.getEmpId());

		String leave = "SELECT  TO_CHAR(LEAVE_NAME) ,NVL(LEAVE_CLOSING_BALANCE,'0'),HRMS_LEAVE.LEAVE_ID "
				+ "FROM HRMS_LEAVE "
				+ "LEFT JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_BALANCE.LEAVE_CODE AND EMP_ID ="
				+ leaveEn.getEmpId()
				+ ") "
				+ "LEFT JOIN HRMS_EMP_OFFC ON(HRMS_LEAVE_BALANCE.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
				+ "INNER JOIN HRMS_LEAVE_POLICY_DTL ON(HRMS_LEAVE_POLICY_DTL.LEAVE_CODE=HRMS_LEAVE_BALANCE.LEAVE_CODE AND LEAVE_APPLICABLE='Y' AND LEAVE_ENC_FLAG='Y'"
				+ "AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE="
				+ policyCode
				+ ")" + " ORDER BY LEAVE_ID ";
		Object[][] leaveData = getSqlModel().getSingleResult(leave);
		String query = "SELECT  HRMS_LEAVE_POLICY_DTL.LEAVE_CODE,LEAVE_ENC_FORMULA "
				+ "FROM HRMS_LEAVE "
				+ "INNER JOIN HRMS_LEAVE_POLICY_DTL ON(HRMS_LEAVE_POLICY_DTL.LEAVE_CODE=HRMS_LEAVE.LEAVE_ID AND LEAVE_APPLICABLE='Y' AND LEAVE_ENC_FLAG='Y'"
				+ "AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE="
				+ policyCode
				+ ")" + " ORDER BY LEAVE_ID ";
		Object[][] dataFormula = getSqlModel().getSingleResult(query);
		String[][] total = new String[leaveData.length][5];
		double totalAmount = 0;
		try {
			for (int i = 0; i < leaveData.length; i++) {
				total[i][0] = String.valueOf(leaveData[i][0]);// leave name
				total[i][1] = String.valueOf(leaveData[i][1]);// leave closing
				// balance
				total[i][2] = "0";
				total[i][3] = String.valueOf(encasheable[i]);
				total[i][4] = String.valueOf(leaveData[i][2]);// leave code
				for (int j = 0; j < dataFormula.length; j++) {
					if (String.valueOf(leaveData[i][2]).equals(
							String.valueOf(dataFormula[j][0]))) {
						double amount = 0;
						try {
							amount = Utility.expressionEvaluate(new Utility()
									.generateFormula(leaveEn.getEmpId(), String
											.valueOf(dataFormula[j][1]),
											context, session));
							amount = amount
									* (Double.parseDouble(String
											.valueOf(encasheable[i])));
						} // end of try
						catch (Exception e) {
							// TODO Auto-generated catch block
							logger
									.error("Exception in calculatEncash first---------------"
											+ e);
						}// end of catch
						total[i][2] = String.valueOf(Math.round(amount));
						totalAmount += Double.parseDouble(total[i][2]);
					}// end of if
				}// end of nested for
			}// end of for
		} // end of try
		catch (Exception e) {
			logger.error("Exception in calculatEncash second---------------"
					+ e);
		}// end of catch
		leaveEn.setTotalAmt(String.valueOf(Math.round(totalAmount)));
		return total;
	}// end of calculatEncash

	/**
	 * THIS METHOD IS USED FOR FORWARDING LEAVE ENCASHMENT APPLICATION
	 * 
	 * @param leaveEn
	 * @param empFlow---------it
	 *            gives approver id
	 * @param leaveid--------leave
	 *            code
	 * @param balanceleave---------leave
	 *            balance
	 * @param encashable-------leave
	 *            to be encashed
	 * @param singleamount---------encash
	 *            amount
	 * @param onhold
	 * @return boolean
	 */
	public boolean forwardToApprover(LeaveEncashment leaveEn,
			Object[][] empFlow, String[] leaveid, String[] balanceleave,
			String[] encashable, String[] singleamount, String[] onhold) {

		boolean result = false;
		try {
			String approverQuery = " UPDATE HRMS_LEAVE_ENCASH_HDR SET  APPROVED_BY="
					+ empFlow[0][0]
					+ ",LEAVE_ALTER_APPROVER="
					+ empFlow[0][3]
					+ ",ENCASH_STATUS='P'" +
					" WHERE ENCASH_CODE=" + leaveEn.getEnCode() + " ";
			result = getSqlModel().singleExecute(approverQuery);
		} catch (Exception e) {
			logger.info("Exception in forwardToApprover-----------" + e);
		}
		return result;
	}// end of forwardToApprover

	/**
	 * THIS METHOD IS USED FOR GENERATING REPORT
	 * 
	 * @param leaveApp
	 */

	public void getRecord(LeaveEncashment leaveApp) {

		try {
			Object[] param = new Object[1];
			/*
			 * GETRECORD DISPLAYING THE LEAVE DETAILS AFTER SELECTING THE
			 * APPLICATION FROM F9 ACTION
			 * 
			 * 
			 */
			param[0] = leaveApp.getEnCode();
			/*
			 * GETQUERY(12) FOR SELECTING THE STATUS ,DETAILS,LEVEL
			 * 
			 */
			Object applData[][] = getSqlModel().getSingleResult(getQuery(3),
					param);
			/*
			 * SETTING THE STATUS OF THE APPLICATION 1)IF LEVEL IS 1 & STATUS
			 * WILL BE SET TO VALUE COMING FROM DATABASE
			 * 
			 * 
			 */
			leaveApp.setApproveStatusFlag("P"+String.valueOf(applData[0][0]));
			if (String.valueOf(applData[0][1]).equals("1")) {
				leaveApp.setStatus(String.valueOf(applData[0][0]));
				leaveApp.setHiddenStatus(String.valueOf(applData[0][0]));
			}// end of if
			/*
			 * 2)IF LEVEL IS HIGHER THAN 1 & APPLICATION IS PENDING STATUS WILL
			 * BE SET TO "FORWARDED"
			 * 
			 */
			else if (!(String.valueOf(applData[0][1]).equals("1"))
					&& String.valueOf(applData[0][0]).equals("P")) {
				leaveApp.setStatus("F");
				leaveApp.setHiddenStatus("F");
			}// end of else if
			/*
			 * 3)if level is higher than 1 & application is not pending status
			 * will be set to value coming from database
			 * 
			 */
			else {
				leaveApp.setStatus(String.valueOf(applData[0][0]));
				leaveApp.setHiddenStatus(String.valueOf(applData[0][0]));
			}// end of else
			leaveApp.setLevel(String.valueOf(applData[0][1]));
			leaveApp.setAppcode(String.valueOf(applData[0][2]));
			
			String query = "SELECT NVL(ENCASH_KEEP_INFORM_ID,'') FROM HRMS_LEAVE_ENCASH_HDR WHERE ENCASH_CODE=" + leaveApp.getEnCode();
			Object keepInformData[][] = getSqlModel().getSingleResult(query);
			String keepInformToCode = "";
			if(keepInformData != null && keepInformData.length > 0){
				keepInformToCode = String.valueOf(keepInformData[0][0]);
			}
			
			String commentQuery = "SELECT ENCASH_ASSESED_BY FROM HRMS_LEAVE_ENCASH_PATH WHERE ENCASH_CODE=" + leaveApp.getEnCode(); 
			Object commentData[][] = getSqlModel().getSingleResult(commentQuery);
			if(commentData != null && commentData.length > 0){
				///leaveApp.setApprComments("true"); 
				leaveApp.setApproverCode(String.valueOf(commentData[0][0]).trim());
			}
			
			String paymentQuery = "SELECT NVL(ENCASH_MONTH,0), NVL(ENCASH_YEAR,0), NVL(ENCASH_PAID_COMPONENT,0), NVL(ENCASH_PAID_SAL_FLAG,'') FROM HRMS_LEAVE_ENCASH_HDR WHERE ENCASH_CODE=" + leaveApp.getEnCode();
			Object paymentData[][] = getSqlModel().getSingleResult(paymentQuery);
			if(paymentData != null && paymentData.length > 0){
				leaveApp.setSalarymonth(String.valueOf(paymentData[0][0]).trim());
				if(String.valueOf(paymentData[0][1]).equals("0")){
					leaveApp.setSalaryyear("");
				}else{
					leaveApp.setSalaryyear(String.valueOf(paymentData[0][1]).trim());
				}
				if(String.valueOf(paymentData[0][2]).equals("0")){
					leaveApp.setCreditCode("");
				}else{
					leaveApp.setCreditCode(String.valueOf(paymentData[0][2]).trim());
				}
				if(String.valueOf(paymentData[0][3]).equals("Y")){
					leaveApp.setSalaryCheck("true");
				}else{
					leaveApp.setSalaryCheck("");
				}
				
				if(leaveApp.getStatus().equalsIgnoreCase("D") || leaveApp.getStatus().equalsIgnoreCase("P")){
					leaveApp.setSalaryCheck("true");
				}
				
				String creditQuery = "SELECT CREDIT_NAME FROM HRMS_CREDIT_HEAD "
					+ " WHERE CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' "	
					+ " AND CREDIT_CODE=" + String.valueOf(paymentData[0][2]);
				Object creditData[][] = getSqlModel().getSingleResult(creditQuery);
				if(creditData != null && creditData.length > 0){
					leaveApp.setCreditType(String.valueOf(creditData[0][0]));
				}
			}
			
			
			
			
			if(keepInformToCode.equals("") || keepInformToCode == null || keepInformToCode.equals("null")){
				leaveApp.setKeepInformedList(null);
			}else{
				ArrayList list = new ArrayList();
				String[] result = keepInformToCode.split(",");
				for(int i=0; i<result.length; i++){
					LeaveEncashment bean = new LeaveEncashment();
					String empid = result[i];
					String empQuery = "SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME " +  
						" FROM HRMS_EMP_OFFC WHERE HRMS_EMP_OFFC.EMP_ID=" + empid;
					Object empData[][] = getSqlModel().getSingleResult(empQuery);
					if(empData != null && empData.length > 0){
						bean.setKeepInformToName(String.valueOf(empData[0][0]));
						bean.setKeepInformToCode(empid);
					}
					list.add(bean);
				}
				leaveApp.setKeepInformedList(list);
			}
			
		} catch (Exception e) {
			logger.info("Exception in getRecord---------" + e);
		}

	}// end of getRecord

	/**
	 * THIS METHOD IS USED FOR GETTING EMPLOYEE INFORMATION
	 * 
	 * @param leaveApp
	 * @return String
	 */

	public String showEmp(LeaveEncashment leaveApp) {
		try {
			Object[] emp = new Object[1];
			emp[0] = leaveApp.getEmpId();// employee id
			Object[][] empdata = getSqlModel()
					.getSingleResult(getQuery(2), emp);
			leaveApp.setEmpToken(String.valueOf(empdata[0][0]));// token
			leaveApp.setEmpName(String.valueOf(empdata[0][1]));// employee name
			leaveApp.setCenterName(String.valueOf(empdata[0][2]));// branch
			leaveApp.setRankName(String.valueOf(empdata[0][3]));// designation
			leaveApp.setEncashDate(String.valueOf(empdata[0][4]));// encash date
			leaveApp.setEmpId(String.valueOf(empdata[0][5]));// employee id
			leaveApp.setDept(String.valueOf(empdata[0][6]));// dept name
		} catch (Exception e) {
			logger.error("Exception in showEmp------" + e);
		}
		return "success";

	}// end of showEmp

	private Object[][] toString(double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * THIS METHOD IS USED FOR APPROVER DETAILS
	 * 
	 * @param leaveEn
	 */
	public void setApproverDetailsRecord(LeaveEncashment leaveEn) {
		// TODO Auto-generated method stub

		try {
			String approverQuery = " SELECT EMP_TOKEN,(EMP_FNAME ||' '||EMP_MNAME ||' '|| EMP_LNAME ||' '), "
					+ " TO_CHAR(HRMS_LEAVE_ENCASH_PATH.ENCASH_ASSESS_DATE,'DD-MM-YYYY'),HRMS_LEAVE_ENCASH_PATH.ENCASH_COMMENTS   "
					+ " FROM HRMS_LEAVE_ENCASH_PATH "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_LEAVE_ENCASH_PATH.ENCASH_ASSESED_BY= HRMS_EMP_OFFC.EMP_ID) "
					+ " LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE)"
					+ "   WHERE HRMS_LEAVE_ENCASH_PATH.ENCASH_CODE ="
					+ leaveEn.getEnCode()
					+ " ORDER BY HRMS_LEAVE_ENCASH_PATH.ENCASH_ASSESS_DATE ";
			Object[][] approverDataObj = getSqlModel().getSingleResult(
					approverQuery);
			ArrayList<Object> approverList = new ArrayList<Object>();
			leaveEn.setApproveList(null);
			if (approverDataObj != null && approverDataObj.length != 0) {
				for (int i = 0; i < approverDataObj.length; i++) {
					LeaveEncashment encashBean = new LeaveEncashment();
					encashBean.setApprName(checkNull(String
							.valueOf(approverDataObj[i][1])));
					encashBean.setApprDate(checkNull(String
							.valueOf(approverDataObj[i][2])));
					if (String.valueOf(approverDataObj[i][3]).equals("null")
							|| String.valueOf(approverDataObj[i][3]) == null) {
						encashBean.setApprComments("");
					}// end of if
					encashBean.setApprComments(checkNull(String
							.valueOf(approverDataObj[i][3])));
					approverList.add(encashBean);

				}// end of for
				leaveEn.setApproveList(approverList);
			}// end of if
			
		} catch (Exception e) {
			logger.error("Exception in setApproverDetailsRecord------" + e);
		}
	}// end of setApproverDetailsRecord

	public String getLeavePolicyCode(String empId) {
		String leavePolicyCode = "";
		try {
			LeavePolicyModel model = new LeavePolicyModel();
			model.initiate(context, session);
			System.out.println("leaveBean.getEmpCode()------" + empId);
			leavePolicyCode = model.getLeavePolicy(empId);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in setApproverDetailsRecord------" + e);
		}
		return leavePolicyCode;
	}
	
	
	public void setApproverData(LeaveEncashment leaveEn,
			Object[][] empFlow) {

		/*
		 * String str = "0"; if (empFlow != null) { for (int i = 0; i <
		 * empFlow.length; i++) { str += "," + empFlow[i][0]; } }
		 * logger.info("str________________" + str);
		 */

		try {
			if (empFlow != null && empFlow.length > 0) {
				Object[][] approverDataObj = new Object[empFlow.length][1];
				for (int i = 0; i < empFlow.length; i++) {

					String selectQuery = "  SELECT HRMS_EMP_OFFC.EMP_TOKEN ||'-'||' '||' '||' '|| "
							+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,' '||'('||HRMS_RANK.RANK_NAME||')' "
							+ "  FROM HRMS_EMP_OFFC "
							+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
							+ " WHERE EMP_ID IN(" + empFlow[i][0] + ")";

					Object temObj[][] = getSqlModel().getSingleResult(
							selectQuery);
					approverDataObj[i][0] = String.valueOf(temObj[0][0]);
				}

				if (approverDataObj != null && approverDataObj.length > 0) {
					ArrayList arrList = new ArrayList();
					for (int i = 0; i < approverDataObj.length; i++) {
						LeaveEncashment leaveBean = new LeaveEncashment();
						leaveBean.setApproverName(String
								.valueOf(approverDataObj[i][0]));
						String srNo = i + 1 + getOrdinalFor(i + 1)
								+ "-Approver";
						leaveBean.setSrNoIterator(srNo);
						arrList.add(leaveBean);
					}
					leaveEn.setApproverList(arrList);
				}

			}
		} catch (Exception e) {
			logger.error("Exception in setApproverData------" + e);
		}
	}

	/**
	 * @Method getOrdinalFor
	 * @Purpose to append the ordinal with serial number
	 * @param value
	 *            value to which ordinal will be appended
	 * @return String
	 */
	public String getOrdinalFor(int value) {
		int hundredRemainder = value % 100;

		if (hundredRemainder >= 10 && hundredRemainder <= 20) {
			return "th";
		} // end of if

		int tenRemainder = value % 10;

		switch (tenRemainder) {
		case 1:
			return "st";
		case 2:
			return "nd";
		case 3:
			return "rd";
		default:
			return "th";
		} // end of switch
	}

	
	public int[] getMonthArray(final int encashMonth, final String period,
			int yearStartMonth) {

		final ArrayList<int[]> obj = new ArrayList<int[]>();

		if (yearStartMonth == 1) {
			yearStartMonth = 12;
		} else {
			yearStartMonth = yearStartMonth - 1;
		}
		if (period.equals("Mo")) {
			return new int[]{encashMonth};
		} else if (period.equals("Qu")) {
			int counter = 1;
			for (int i = 0; i < 4; i++) {
				int[] quarterArray = new int[] {
						this.getExactMonth(yearStartMonth + counter++),
						this.getExactMonth(yearStartMonth + counter++),
						this.getExactMonth(yearStartMonth + counter++) };
				obj.add(quarterArray);
			}

		} else if (period.equals("Hy")) {
			int counter = 1;
			for (int i = 0; i < 1; i++) {
				int[] quarterArray = new int[] {
						this.getExactMonth(yearStartMonth + counter++),
						this.getExactMonth(yearStartMonth + counter++),
						this.getExactMonth(yearStartMonth + counter++),
						this.getExactMonth(yearStartMonth + counter++),
						this.getExactMonth(yearStartMonth + counter++),
						this.getExactMonth(yearStartMonth + counter++) };
				obj.add(quarterArray);
			}
			
		} else if (period.equals("Ye")) {
			int counter = 1;
		
			final int[] quarterArray = new int[] {
					this.getExactMonth(yearStartMonth + counter++),
					this.getExactMonth(yearStartMonth + counter++),
					this.getExactMonth(yearStartMonth + counter++),
					this.getExactMonth(yearStartMonth + counter++),
					this.getExactMonth(yearStartMonth + counter++),
					this.getExactMonth(yearStartMonth + counter++),
					this.getExactMonth(yearStartMonth + counter++),
					this.getExactMonth(yearStartMonth + counter++),
					this.getExactMonth(yearStartMonth + counter++),
					this.getExactMonth(yearStartMonth + counter++),
					this.getExactMonth(yearStartMonth + counter++),
					this.getExactMonth(yearStartMonth + counter++) 
			};
			obj.add(quarterArray);
			
			
		}

		for (int k = 0; k < obj.size(); k++) {
			final int[] name = (int[]) obj.get(k);
			for (int j = 0; j < name.length; j++) {
				if (encashMonth == name[j]) {
					return name;
				}
			}

		}
		return new int[0];
	}

	public int getExactMonth(final int mon) {
		if (mon > 12) {
			return mon - 12;
		}
		return mon;
	}
	public String getStringWithComma(final int[] str) {
		String strg = "";
		try {
			for (int i = 0; i < str.length; i++) {
				strg += String.valueOf(str[i]);
				strg += ",";
			}
			strg = strg.substring(0, strg.length() - 1);
			
		} catch (final Exception e) {
			this.logger.error(e);
		} //end of try-catch block
		return strg;
	}
}// end of class
