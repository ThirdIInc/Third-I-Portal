package org.paradyne.model.payroll.ot;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.ot.OtCalculations;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.model.payroll.incometax.EmployeeTaxCalculation;

import com.itextpdf.text.BaseColor;

/**
 * Created on 5th Mar 2012.
 * 
 * @author aa1385
 * 
 */
public class OtCalculationsModel extends ModelBase {
	/** formatter. */
	NumberFormat formatter = new DecimalFormat("#0.00");
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(OtCalculationsModel.class);
	
	/**
	 * Method : checkForNullValue. Purpose : This method is used to check for
	 * null decimal values. Checks for the null value and if it finds it to be
	 * null then replaces it with 0.
	 * 
	 * @param result :-
	 *            Input String to be checked
	 * @return : - returns the checked string
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}


	/**
	 * Method : showConfigList. * Purpose : This method is used to show OT
	 * Configuration List.
	 * 
	 * @param bean :
	 *            OtCalculations
	 * @param request :
	 *            HttpServletRequest
	 */
	public void showConfigList(OtCalculations bean, HttpServletRequest request) {
		try {
			String Query = " SELECT HRMS_OT_HDR.OT_ID, TO_CHAR(HRMS_OT_HDR.OT_PROCESS_DATE,'DD-MM-YYYY'), HRMS_OT_HDR.OT_DIV, HRMS_DIVISION.DIV_NAME,"
						+"	HRMS_OT_HDR.OT_PAYBILL, HRMS_OT_HDR.OT_TOTAL_EMP, NVL(HRMS_OT_HDR.NET_OT_AMT,0),  "
						+"	DECODE(HRMS_OT_HDR.OT_PAID_MONTH,'1','Jan','2','Feb','3','Mar','4','Apr','5','May','6','Jun','7','Jul','8','Aug','9', " 
						+"	'Sept','10','Oct','11','Nov','12','Dec')||' '||HRMS_OT_HDR.OT_PAID_YEAR "
						+"	,DECODE(HRMS_OT_HDR.OT_STATUS,'P','Process','L','Lock','U','Unlock')," +
						" DECODE(HRMS_OT_HDR.OT_MONTH,'1','Jan','2','Feb','3','Mar','4','Apr','5','May','6','Jun','7','Jul','8','Aug','9', 	'Sept','10','Oct','11','Nov','12','Dec')||' '||HRMS_OT_HDR.OT_YEAR	" +
						" FROM HRMS_OT_HDR "
						+"	INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_OT_HDR.OT_DIV)"
					//	+"	LEFT JOIN HRMS_PAYBILL ON(HRMS_PAYBILL.PAYBILL_ID= HRMS_OT_HDR.OT_PAYBILL)"
						+"	ORDER BY TO_DATE(HRMS_OT_HDR.OT_PROCESS_DATE,'DD-MM-YYYY') DESC ";

			Object[][] data = getSqlModel().getSingleResult(Query);

			if (data != null && data.length > 0) {
				bean.setModeLength("true");
				bean.setTotalRecords(String.valueOf(data.length));
				String[] pageIndex = Utility.doPaging(bean.getMyPage(),
						data.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}
				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("pageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1"))
					bean.setMyPage("1");
				ArrayList<Object> List = new ArrayList<Object>();
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					OtCalculations bean1 = new OtCalculations();
					bean1.setHiddenOtCalId(checkNull(String
							.valueOf(data[i][0])));
					bean1.setProcessDate(checkNull(String
							.valueOf(data[i][1])));
					bean1.setDivisionID(checkNull(String.valueOf(data[i][2])));
					bean1
							.setDivisionName(checkNull(String
									.valueOf(data[i][3])));
					
					bean1.setPaybillCode(checkNull(String.valueOf(data[i][4])));
					bean1.setPaybillName(getActualPayBillName(String.valueOf(data[i][4])));
					//beanItt.setPayBillNameItr(getActualPayBillName(String.valueOf(initialRecordsObj[i][5])));
					bean1.setTotalEmpCount(checkNull(String.valueOf(data[i][5])));
					
					String totalOtAmount = formatter.format(Double.parseDouble(checkNull(String.valueOf(data[i][6])))); 
					
					bean1.setTotalOtAmount(checkNull(String.valueOf(totalOtAmount)));
					bean1.setPaidInSalMonth(checkNull(String.valueOf(data[i][7])));
					bean1.setStatus(checkNull(String.valueOf(data[i][8])));
					bean1.setOtPeriod(checkNull(String.valueOf(data[i][9])));

					List.add(bean1);
				}// end of loop
				bean.setIteratorlist(List);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method : editConfigSetting. * Purpose : This method is used to Edit OT
	 * Configuration ID.
	 * 
	 * @param bean :
	 *            OtCalculations
	 * @param request :
	 *            HttpServletRequest
	 * @param requestID :
	 *            Perticular Code
	 */
	public void editConfigSetting(OtCalculations bean,
			HttpServletRequest request, String requestID) {
		try {
			String Query = " SELECT HRMS_OT_HDR.OT_ID,HRMS_OT_HDR.OT_MONTH, HRMS_OT_HDR.OT_YEAR, HRMS_OT_HDR.OT_DIV, HRMS_DIVISION.DIV_NAME,"
							+" HRMS_OT_HDR.OT_PAYBILL, "
							+" HRMS_OT_HDR.OT_DEPT, HRMS_OT_HDR.OT_DESG, " +
							" HRMS_OT_HDR.OT_COST_CENTER, HRMS_OT_HDR.OT_BRANCH, " +
							" HRMS_OT_HDR.OT_PAID_COMPONENT, HRMS_CREDIT_HEAD.CREDIT_NAME, "
							+" HRMS_OT_HDR.OT_PAID_FLAG, HRMS_OT_HDR.OT_DEDUCT_TDS_FLAG,HRMS_OT_HDR.OT_PAID_MONTH," +
							"HRMS_OT_HDR.OT_PAID_YEAR, DECODE(HRMS_OT_HDR.OT_STATUS,'P','Pending','L','Lock','U','Unlock') "
							+" , HRMS_OT_HDR.OT_PAID_FLAG || 'Y' , HRMS_OT_HDR.OT_DEDUCT_TDS_FLAG|| 'Y'   FROM HRMS_OT_HDR "
							+" INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_OT_HDR.OT_DIV) "
							//+" LEFT JOIN HRMS_PAYBILL ON(HRMS_PAYBILL.PAYBILL_ID= HRMS_OT_HDR.OT_PAYBILL) "
							//+" LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID =HRMS_OT_HDR.OT_DEPT ) "
							//+" LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID =HRMS_OT_HDR.OT_DESG ) "
							//+" LEFT JOIN HRMS_COST_CENTER ON (HRMS_COST_CENTER.COST_CENTER_ID =HRMS_OT_HDR.OT_COST_CENTER) "
						//	+" LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID =HRMS_OT_HDR.OT_BRANCH) "
							+" LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_OT_HDR.OT_PAID_COMPONENT) "
							+" WHERE HRMS_OT_HDR.OT_ID = " + requestID;

			Object[][] data = getSqlModel().getSingleResult(Query);
			if (data != null && data.length > 0) {
				bean.setOtCalculationId(String.valueOf(data[0][0]));
				bean.setMonth(Utility.checkNull(String.valueOf(data[0][1])));
				bean.setYear(String.valueOf(data[0][2]));
				bean.setDivisionID(String.valueOf(data[0][3]));
				bean.setDivisionName(String.valueOf(data[0][4]));
				bean.setPaybillCode(checkNull(String.valueOf(data[0][5])));
				bean.setPaybillName(checkNull(getActualPayBillName(String.valueOf(data[0][5]))));
				bean.setDeptCode(checkNull(String.valueOf(data[0][6])));
				bean.setDeptName(checkNull(getActualDepartmentName(String.valueOf(data[0][6]))));
				bean.setDesgCode(checkNull(String.valueOf(data[0][7])));
				bean.setDesgName(checkNull(getActualRankName(String.valueOf(data[0][7]))));
				bean.setCostCenterCode(checkNull(String.valueOf(data[0][8])));
				bean.setCostCenterName(checkNull(getActualCostCenterName(String.valueOf(data[0][8]))));
				
				bean.setCenterId(checkNull(String.valueOf(data[0][9])));
				bean.setCenterName(checkNull(getActualCenterName(String.valueOf(data[0][9]))));
				
				bean.setCreditCode(checkNull(String.valueOf(data[0][10])));
				bean.setCreditName(checkNull(String.valueOf(data[0][11])));
				
				
				if (String.valueOf(data[0][12]).equals("Y")) {
					bean.setPayInSalaryFlag("true");
				}
				if (String.valueOf(data[0][13]).equals("Y")) {
					bean.setDeductInconeTaxFlag("true");
				}
				bean.setPaidMonth(String.valueOf(data[0][14]));
				bean.setPaidYear(checkNull(String
						.valueOf(data[0][15])));
				bean.setStatus(checkNull(String
						.valueOf(data[0][16])));
				
				bean.setPaySalFlag(checkNull(String
						.valueOf(data[0][17])));
				bean.setDeductTaxFlag(checkNull(String
						.valueOf(data[0][18])));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * Method : checkNullValue. Purpose : This method is used to check for
	 * null decimal values. Checks for the null value and if it finds it to be
	 * null then replaces it with 0.
	 * 
	 * @param result :-
	 *            Input String to be checked
	 * @return : - returns the checked string
	 */
	public String checkNullValue(final String result) {
		if (result == null || "null".equals(result)) {
			return "0";
		} else {
			return result;
		}
	}
	
	public String checkNullHH_MM(final String result) {
		if (result == null || "null".equals(result)) {
			return "00:00";
		} else {
			return result;
		}
	}
	
	/**THis is used for saveOtCalculations.
	 * @param bean : OtCalculations
	 * @param request
	 * @return result
	 */
	public boolean saveOtCalculations(final OtCalculations bean,
			HttpServletRequest request) {
		boolean result = false;
		try {
			
			///Object[][] flagObj = callCheckWorkFlow();
			String month=bean.getMonth();
			String year=bean.getYear();
			int otCalId = 0;
			
			/* Get Max for LWF_CODE */
			String sQuery = "SELECT NVL(MAX(HRMS_OT_HDR.OT_ID),0) + 1 FROM HRMS_OT_HDR";
			Object[][] objData = getSqlModel().getSingleResult(sQuery);

			if (objData != null) {
				otCalId = Integer.parseInt(String.valueOf(objData[0][0]));
				bean.setOtCalculationId(String.valueOf(otCalId));
			}

			/*String deleteQuery="DELETE FROM HRMS_MISC_SALARY_UPLOAD WHERE APPL_CODE = "+otCalId+" AND  APPL_TYPE='"+"OT"+"' AND MONTH="+bean.getPaidMonth()+" AND  YEAR="+bean.getPaidYear()+" ";
			result = getSqlModel().singleExecute(deleteQuery);*/
			
			String Query = " SELECT DISTINCT HRMS_OT_REGISTER.EMP_ID,HRMS_EMP_OFFC.EMP_FNAME,HRMS_EMP_OFFC.EMP_GENDER"
				+" FROM HRMS_OT_REGISTER "
				+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_OT_REGISTER.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
				+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
	            +" LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
				+" WHERE TO_CHAR(HRMS_OT_REGISTER.OT_DATE,'MM')="+bean.getMonth()+" AND TO_CHAR(HRMS_OT_REGISTER.OT_DATE,'YYYY')="+bean.getYear()+" ";
				
			if(!bean.getDivisionID().equals("")) {
				Query += " AND HRMS_EMP_OFFC.EMP_DIV ="+bean.getDivisionID()+" " ;
				}
				
				if (!bean.getCenterName().equals("")) {
					Query += " AND HRMS_EMP_OFFC.EMP_CENTER IN ("
							+ bean.getCenterId()+") ";
				}
				
				if (!bean.getDeptName().equals("")) {
					Query += " AND HRMS_EMP_OFFC.EMP_DEPT IN ("
							+ bean.getDeptCode()+") ";
				}
				
				if (!bean.getDesgName().equals("")) {
					Query += " AND HRMS_EMP_OFFC.EMP_RANK IN ("
							+ bean.getDesgCode()+") ";
				}
				
				if (!bean.getPaybillName().equals("")) {
					Query += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN ("
							+ bean.getPaybillCode()+") ";
				}
				

				if (!bean.getCostCenterName().equals("")) {
					Query += " AND HRMS_COST_CENTER.COST_CENTER_ID IN("
							+ bean.getCostCenterCode()+")";
				}
				
				//Query += " GROUP BY HRMS_OT_REGISTER.EMP_ID,HRMS_EMP_OFFC.EMP_FNAME  ,HRMS_EMP_OFFC.EMP_GENDER" ;
							
				Object[][] data = getSqlModel().getSingleResult(Query);	

				int totalOtAmount = 0;
				double totalOtAmounthdr =0;
				double totalTds =0;
				double totalNet =0;
				/*try {
					for (int i = 0; i < data.length; i++) {
						//data[i][0] = String.valueOf(i + 1);
						totalOtAmount += getMinute(getHH_MM(String
								.valueOf(data[i][2])))+getMinute(getHH_MM(String
										.valueOf(data[i][3])));
					} // end of loop
				} catch (Exception e) {
					logger.error("exception in houseRentDtl loop", e);
				} // end of catch
*/
				/**
				 * GET DAYWISE SOT AND DOT
				 */
				
				String otQuery="SELECT HRMS_OT_REGISTER.EMP_ID,to_char(HRMS_OT_REGISTER.OT_DATE,'dd-MM-YYYY'), " +
						"HRMS_OT_REGISTER.OT_SHIFT_CODE,TO_CHAR(HRMS_OT_REGISTER.APPR_SINGLE_OT,'HH24:MI'),TO_CHAR(HRMS_OT_REGISTER.APPR_DOUBLE_OT,'HH24:MI')," +
						"HRMS_EMP_OFFC.EMP_GENDER " +
						"FROM HRMS_OT_REGISTER  " +
						"INNER JOIN HRMS_EMP_OFFC ON(HRMS_OT_REGISTER.EMP_ID=HRMS_EMP_OFFC.EMP_ID) " +
						"LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)" +
						" LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)" +
						"  WHERE TO_CHAR(HRMS_OT_REGISTER.OT_DATE,'MM')="+month+" AND TO_CHAR(HRMS_OT_REGISTER.OT_DATE,'YYYY')="+year+"  " +
						"AND HRMS_EMP_OFFC.EMP_DIV ="+bean.getDivisionID()+" ORDER BY HRMS_OT_REGISTER.EMP_ID";
				HashMap<String, Object[][]>OTMap=getSqlModel().getSingleResultMap(otQuery, 0, 2);
				
				/**
				 * GET SINGLE OT AND DOUBLE OT FORMULA FROM HRMS_SHIFT
				 */
				String formulaQuery="SELECT HRMS_SHIFT.SHIFT_ID,NVL(HRMS_SHIFT.SFT_OT_REG_FORMULA,'NA'), NVL(HRMS_SHIFT.SFT_OT_WEEKLY_FORMULA,'NA'), NVL(HRMS_SHIFT.SFT_OT_DOUBLE_FORMULA,'NA') " 
						+" FROM HRMS_SHIFT WHERE HRMS_SHIFT.SFT_OT_ISENABLED='Y'";
				HashMap<String, Object[][]>formulaMap=getSqlModel().getSingleResultMap(formulaQuery, 0, 2);
				
			
			/* Insert Header Table Information */
			String sHeaderQuery;

			
			/* --- Header End --- */
			
			/* --- INSERT INTO HRMS_OT_CALCULATION START --- */
		
			if(data!=null && data.length > 0){
				String otormula="SELECT HRMS_OT_CONF.REGULAR_OT_FORMULA,HRMS_OT_CONF.DOUBLE_OT_FORMULA FROM HRMS_OT_CONF WHERE HRMS_OT_CONF.DIV_CODE="+bean.getDivisionID()+" ";
				Object[][]objOT=getSqlModel().getSingleResult(otormula);
				String singleOT="";
				String doubleOT="";
				if(objOT!=null&&objOT.length>0){
					singleOT=String.valueOf(objOT[0][0]);
					doubleOT=String.valueOf(objOT[0][1]);
				}
				double TOTAL = 0.0;
				Object[][]obj=new Object[data.length][12];
				for (int i = 0; i < data.length; i++) {
					obj[i][0]=otCalId;
					obj[i][1]=String.valueOf(data[i][0]);
					obj[i][2]=bean.getMonth();
					obj[i][3]=bean.getYear();
					
					//GET DATA FROM DALIY OT REGISTER
					Object[][]OTMapOj=OTMap.get(String.valueOf(data[i][0]));
					//formulaMap						
					double formulaValueSOT = 0.0;
					double formulaValueDOT = 0.0;	
					
					int totalSOT = 0;
					int totalDOT = 0;	
					
					String formulaSOT = "";
					String formulaDOT = "";
					
					final String getCreditConfigInfoQuery = "SELECT HRMS_EMP_CREDIT.CREDIT_CODE, NVL(HRMS_EMP_CREDIT.CREDIT_AMT,0) FROM HRMS_EMP_CREDIT WHERE  HRMS_EMP_CREDIT.EMP_ID = "	+ String.valueOf(data[i][0]);
					final Object[][] creditConfigInfoObj = getSqlModel().getSingleResult(getCreditConfigInfoQuery);

					if(OTMapOj!=null&& OTMapOj.length>0){
						for (int j = 0; j < OTMapOj.length; j++) {
							
							double  SOT = 0.0;
							double  DOT = 0.0;	
							Object[][]formulaObj=formulaMap.get(String.valueOf(OTMapOj[j][2]));
							if(formulaObj!=null&& formulaObj.length>0){
								formulaSOT=String.valueOf(formulaObj[0][1]);
								formulaDOT=String.valueOf(formulaObj[0][3]);
								
								try {
									SOT = Utility
											.expressionEvaluate(new Utility()
													.generateFormulaPay(
															creditConfigInfoObj, formulaSOT,
															context, session));
								} catch (Exception e) {
									e.printStackTrace();
								}
								try {
									DOT = Utility
											.expressionEvaluate(new Utility()
													.generateFormulaPay(
															creditConfigInfoObj, formulaDOT,
															context, session));
								} catch (Exception e) {
									e.printStackTrace();
								}
								
								formulaValueSOT+=SOT*getMinute(getHH_MM(checkNullHH_MM(String.valueOf(OTMapOj[j][3]))));
								formulaValueDOT+=DOT*getMinute(getHH_MM(checkNullHH_MM(String.valueOf(OTMapOj[j][4]))));
								
								totalSOT += getMinute(getHH_MM(checkNullHH_MM(String.valueOf(OTMapOj[j][3]))));
								totalDOT += getMinute(getHH_MM(checkNullHH_MM(String.valueOf(OTMapOj[j][4]))));
								
							}
						}
					}
					
					totalOtAmount += totalSOT+ totalDOT;
					
					//formulaValueSOT=formulaValueSOT*getMinute(getHH_MM(String.valueOf(data[i][2])));
					//formulaValueDOT=formulaValueDOT*getMinute(getHH_MM(String.valueOf(data[i][3])));
					//obj[i][4] = getHH_MM(totalSOT + totalDOT);
					obj[i][4] = getHH_MM(totalSOT + totalDOT);
					
					obj[i][5]=getHH_MM(totalSOT);
					obj[i][6]=getHH_MM(totalDOT);
					
					formulaValueSOT=(formulaValueSOT/60);
					formulaValueDOT=(formulaValueDOT/60);					
					
					totalOtAmounthdr +=formulaValueSOT+ formulaValueDOT;
					
					obj[i][7]=formatter.format(formulaValueSOT);
					obj[i][8]=formatter.format(formulaValueDOT);
					obj[i][9]=formatter.format(formulaValueSOT+ formulaValueDOT);
					obj[i][11]=formatter.format(formulaValueSOT+ formulaValueDOT);
					Object[][]taxCalculateObj=new Object[1][3];
					taxCalculateObj[0][0]=data[i][0];
					taxCalculateObj[0][1]=formulaValueSOT+ formulaValueDOT;
					taxCalculateObj[0][2]=data[i][2];
					obj[i][10]="0";
					if(bean.getDeductInconeTaxFlag().equals("true")){
						final EmployeeTaxCalculation taxModel = new EmployeeTaxCalculation();
						taxModel.initiate(context, session);
						final Object[][] insertTaxObj = taxModel.getEmpSlabTaxAmount(taxCalculateObj, bean.getPaidYear(),
								   String.valueOf(Integer.parseInt(bean.getPaidYear()) + 1));
					//	obj[i][9]=insertTaxObj[0][1];
						obj[i][10]=insertTaxObj[0][2];
						obj[i][11]=insertTaxObj[0][1];
						totalTds +=Double.parseDouble(checkNull(String.valueOf(insertTaxObj[0][2])));
						totalNet +=Double.parseDouble(checkNull(String.valueOf(insertTaxObj[0][1])));
					}
					
					/*if(bean.getPayInSalaryFlag().equals("true")){
						String sPayInSalQuery;
						Object[][] payInSalData;
						try {
							sPayInSalQuery = " INSERT INTO HRMS_MISC_SALARY_UPLOAD (EMP_ID, MONTH, YEAR, UPLOAD_PAY_TYPE, " +
							"SALARY_CODE, SALARY_AMOUNT, UPLOAD_IS_OVERWRITE, APPL_CODE, APPL_TYPE, DISPLAY_FLAG, COMMENTS) "
							+ " VALUES (?,?,?, ?, ?, ?,?,?,?,? ,?) ";
									payInSalData = new Object[1][11];
									payInSalData[0][0] = data[i][0];  OT Calculation Id 
									payInSalData[0][1] = bean.getPaidMonth();  Paid Month 
									payInSalData[0][2] = bean.getPaidYear();   Paid Year 
									payInSalData[0][3] = "C";  Pay Type 
									payInSalData[0][4] = bean.getCreditCode();  Credit Code 
									payInSalData[0][5] = formatter.format(formulaValueSOT+ formulaValueDOT);  SALARY_AMOUNT 
									payInSalData[0][6] = "Y";  OverWrite 
									payInSalData[0][7] = bean.getOtCalculationId();  OT CALCULATION ID 
									payInSalData[0][8] = "OT";  Appl Type 
									payInSalData[0][9] = "Y";  Display Flag 
									payInSalData[0][10] = "OT Calculation";  Comment 
								
									result = getSqlModel().singleExecute(sPayInSalQuery, payInSalData);
						
						} catch (Exception e) {
							e.printStackTrace();
						}
					}*/
					TOTAL+=(formulaValueSOT+ formulaValueDOT);
					
				}
				
				Object[][] headerData;
				try {
					sHeaderQuery = " INSERT INTO HRMS_OT_HDR (OT_ID, OT_MONTH, OT_YEAR, OT_PROCESS_DATE, " +
							"OT_DIV, OT_PAYBILL, OT_DEPT, OT_DESG, OT_COST_CENTER, OT_BRANCH, OT_TOTAL_EMP, " +
							"OT_TOTAL_AMOUNT, OT_PAID_MONTH, OT_PAID_YEAR, OT_PAID_COMPONENT, OT_PAID_FLAG, OT_DEDUCT_TDS_FLAG,OT_TDS_AMT,NET_OT_AMT) "
							+ " VALUES (?,?,?, TO_DATE(SYSDATE,'DD-MM-YYYY'), ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
					headerData = new Object[1][18];
					headerData[0][0] = otCalId; /* OT Calculation Id */
					headerData[0][1] = bean.getMonth(); /* Month */
					headerData[0][2] = bean.getYear(); /* Year */
					headerData[0][3] = bean.getDivisionID(); /* Division ID */
					headerData[0][4] = bean.getPaybillCode(); /* Paybill Code */
					headerData[0][5] = bean.getDeptCode(); /* Department Code */
					headerData[0][6] = bean.getDesgCode(); /* Designation Code */
					headerData[0][7] = bean.getCostCenterCode(); /* CostCenter Code */
					headerData[0][8] = bean.getCenterId(); /* Branch Code */
					headerData[0][9] = data.length; /* Total Emp Count */
					headerData[0][10] = formatter.format(totalOtAmounthdr); /* Total OT Amount */
					headerData[0][11] = bean.getPaidMonth(); /* Paid Month */
					headerData[0][12] = bean.getPaidYear(); /* Paid Year */
					headerData[0][13] = bean.getCreditCode(); /* Pay in component */
					if (bean.getPayInSalaryFlag().equals("true")) {
						headerData[0][14] = "Y";
					} else {
						headerData[0][14] = "N";
					}
					if (bean.getDeductInconeTaxFlag().equals("true")) {
						headerData[0][15] = "Y";
					} else {
						headerData[0][15] = "N";
					}
					headerData[0][16] = formatter.format(totalTds); /* Total TDS Amount */
					
					if (bean.getDeductInconeTaxFlag().equals("true")) { /* Deduct in income tax */
						headerData[0][17] = formatter.format(totalNet);  /* Total Net Amount */
					}else{
						headerData[0][17] = formatter.format(totalOtAmounthdr);  /* Total Net Amount */
					}
					
					
					result = getSqlModel().singleExecute(sHeaderQuery, headerData);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				String insertQuery = "INSERT INTO HRMS_OT_DTL "
					+ "(OT_ID, EMP_ID, MONTH, YEAR, TOTAL_OT, SINGLE_OT, DOUBLE_OT,SINGLE_OT_AMT,DOUBLE_OT_AMT,TOTAL_OT_AMT,OT_TDS_AMT,NET_OT_AMT)"
					+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
				result = getSqlModel().singleExecute(insertQuery, obj);
			}
			/* --- INSERT INTO HRMS_OT_CALCULATION END --- */
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * Method : checkOTDtls. * Purpose : This method is used to
	 * Check given Date period and year is available in HRMS_OT_REGISTER.
	 * 
	 * @param bean :
	 *            OtCalculations
	 * @param request :
	 *            HttpServletRequest
	 * @return : result
	 */
	public boolean checkOTDtls(OtCalculations bean) {
		boolean result = false;
		String Query = " SELECT HRMS_OT_REGISTER.EMP_ID,HRMS_EMP_OFFC.EMP_FNAME,TO_CHAR(HRMS_OT_REGISTER.APPR_SINGLE_OT,'HH24:MI'),TO_CHAR(HRMS_OT_REGISTER.APPR_DOUBLE_OT,'HH24:MI')"
			+" FROM HRMS_OT_REGISTER "
			+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_OT_REGISTER.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
			+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
	        +" LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
			+" WHERE TO_CHAR(HRMS_OT_REGISTER.OT_DATE,'MM')="+bean.getMonth()+" AND TO_CHAR(HRMS_OT_REGISTER.OT_DATE,'YYYY')="+bean.getYear()+" ";
			
		if(!bean.getDivisionID().equals("")) {
			Query += " AND HRMS_EMP_OFFC.EMP_DIV ="+bean.getDivisionID()+" " ;
			}
		
		Query += " AND HRMS_EMP_OFFC.EMP_CENTER IS NULL AND HRMS_EMP_OFFC.EMP_DEPT IS NULL AND HRMS_EMP_OFFC.EMP_RANK IS NULL AND HRMS_EMP_OFFC.EMP_PAYBILL IS NULL AND HRMS_COST_CENTER.COST_CENTER_ID IS NULL " ;
		
		
			///Query += " GROUP BY HRMS_OT_REGISTER.EMP_ID,HRMS_EMP_OFFC.EMP_FNAME " ;
		Object[][] data = getSqlModel().getSingleResult(Query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		
		Query = " SELECT HRMS_OT_REGISTER.EMP_ID,HRMS_EMP_OFFC.EMP_FNAME,TO_CHAR(HRMS_OT_REGISTER.APPR_SINGLE_OT,'HH24:MI'),TO_CHAR(HRMS_OT_REGISTER.APPR_DOUBLE_OT,'HH24:MI')"
			+" FROM HRMS_OT_REGISTER "
			+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_OT_REGISTER.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
			+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
	        +" LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
			+" WHERE TO_CHAR(HRMS_OT_REGISTER.OT_DATE,'MM')="+bean.getMonth()+" AND TO_CHAR(HRMS_OT_REGISTER.OT_DATE,'YYYY')="+bean.getYear()+" ";
			
		if(!bean.getDivisionID().equals("")) {
			Query += " AND HRMS_EMP_OFFC.EMP_DIV ="+bean.getDivisionID()+" " ;
			}
			if (!bean.getCenterName().equals("")) {
				Query += " AND HRMS_EMP_OFFC.EMP_CENTER IN ("
						+ bean.getCenterId()+") ";
			}
			
			if (!bean.getDeptName().equals("")) {
				Query += " AND HRMS_EMP_OFFC.EMP_DEPT IN ("
						+ bean.getDeptCode()+") ";
			}
			
			if (!bean.getDesgName().equals("")) {
				Query += " AND HRMS_EMP_OFFC.EMP_RANK IN ("
						+ bean.getDesgCode()+") ";
			}
			
			if (!bean.getPaybillName().equals("")) {
				Query += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN ("
						+ bean.getPaybillCode()+") ";
			}
			

			if (!bean.getCostCenterName().equals("")) {
				Query += " AND HRMS_COST_CENTER.COST_CENTER_ID IN("
						+ bean.getCostCenterCode()+")";
			}
			
			///Query += " GROUP BY HRMS_OT_REGISTER.EMP_ID,HRMS_EMP_OFFC.EMP_FNAME " ;
		data = getSqlModel().getSingleResult(Query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		
		
		
		
		
		return result;
	}
	
	/**
	 * Method : updateOtCalculations. * Purpose : This method is used to
	 * Update Perticular OT Calculation ID.
	 * 
	 * @param bean :
	 *            OtCalculations
	 * @param request :
	 *            HttpServletRequest
	 * @return : result
	 */
	
	public boolean updateOtCalculations(OtCalculations bean,
			HttpServletRequest request) {
		try {
			
			String month = bean.getMonth();
			String year = bean.getYear();
			
			/*String deletePayInSalQuery="DELETE FROM HRMS_MISC_SALARY_UPLOAD WHERE APPL_CODE = "+bean.getOtCalculationId()+" AND  APPL_TYPE='"+"OT"+"' AND MONTH="+bean.getPaidMonth()+" AND  YEAR="+bean.getPaidYear()+" ";
			getSqlModel().singleExecute(deletePayInSalQuery);*/
			
			String Query = " SELECT distinct HRMS_OT_REGISTER.EMP_ID,HRMS_EMP_OFFC.EMP_FNAME,HRMS_EMP_OFFC.EMP_GENDER"
				+" FROM HRMS_OT_REGISTER "
				+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_OT_REGISTER.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
				+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
	            +" LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID) "
				+" WHERE TO_CHAR(HRMS_OT_REGISTER.OT_DATE,'MM')="+bean.getMonth()+" AND TO_CHAR(HRMS_OT_REGISTER.OT_DATE,'YYYY')="+bean.getYear()+" ";
				
			
			if(!bean.getDivisionID().equals("")) {
				Query += " AND HRMS_EMP_OFFC.EMP_DIV ="+bean.getDivisionID()+" " ;
				}
				
			if (!bean.getCenterName().equals("")) {
				Query += " AND HRMS_EMP_OFFC.EMP_CENTER IN ("
						+ bean.getCenterId()+") ";
			}
			
			if (!bean.getDeptName().equals("")) {
				Query += " AND HRMS_EMP_OFFC.EMP_DEPT IN ("
						+ bean.getDeptCode()+") ";
			}
			
			if (!bean.getDesgName().equals("")) {
				Query += " AND HRMS_EMP_OFFC.EMP_RANK IN ("
						+ bean.getDesgCode()+") ";
			}
			
			if (!bean.getPaybillName().equals("")) {
				Query += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN ("
						+ bean.getPaybillCode()+") ";
			}
			

			if (!bean.getCostCenterName().equals("")) {
				Query += " AND HRMS_COST_CENTER.COST_CENTER_ID IN("
						+ bean.getCostCenterCode()+")";
			}
				
				///Query += " GROUP BY HRMS_OT_REGISTER.EMP_ID,HRMS_EMP_OFFC.EMP_FNAME ,HRMS_EMP_OFFC.EMP_GENDER " ;
							
				Object[][] data = getSqlModel().getSingleResult(Query);	

				int totalOtAmount = 0;
				double totalOtAmounthdr = 0;
				double totalTds = 0;
				double totalNet = 0;
				/*try {
					for (int i = 0; i < data.length; i++) {		
						
						//data[i][0] = String.valueOf(i + 1);
						totalOtAmount += getMinute(getHH_MM(String
								.valueOf(data[i][2])))+getMinute(getHH_MM(String
										.valueOf(data[i][3])));
						
					} // end of loop
				} catch (Exception e) {
					logger.error("exception in houseRentDtl loop", e);
				} // end of catch
*/
				String otQuery="SELECT HRMS_OT_REGISTER.EMP_ID,to_char(HRMS_OT_REGISTER.OT_DATE,'dd-MM-YYYY'), " +
							"HRMS_OT_REGISTER.OT_SHIFT_CODE,TO_CHAR(HRMS_OT_REGISTER.APPR_SINGLE_OT,'HH24:MI'),TO_CHAR(HRMS_OT_REGISTER.APPR_DOUBLE_OT,'HH24:MI')," +
							"HRMS_EMP_OFFC.EMP_GENDER " +
							"FROM HRMS_OT_REGISTER  " +
							"INNER JOIN HRMS_EMP_OFFC ON(HRMS_OT_REGISTER.EMP_ID=HRMS_EMP_OFFC.EMP_ID) " +
							"LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)" +
							" LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)" +
							"  WHERE TO_CHAR(HRMS_OT_REGISTER.OT_DATE,'MM')="+month+" AND TO_CHAR(HRMS_OT_REGISTER.OT_DATE,'YYYY')="+year+"  " +
							"AND HRMS_EMP_OFFC.EMP_DIV ="+bean.getDivisionID()+" ORDER BY HRMS_OT_REGISTER.EMP_ID";
				
				HashMap<String, Object[][]>OTMap=getSqlModel().getSingleResultMap(otQuery, 0, 2);
			
				/**
				 * GET SOT AND DOT FORMULA FROM HRMS_SHIFT
				 */
				String formulaQuery="SELECT HRMS_SHIFT.SHIFT_ID,NVL(HRMS_SHIFT.SFT_OT_REG_FORMULA,'NA'), NVL(HRMS_SHIFT.SFT_OT_WEEKLY_FORMULA,'NA'), NVL(HRMS_SHIFT.SFT_OT_DOUBLE_FORMULA,'NA') " 
						+" FROM HRMS_SHIFT WHERE HRMS_SHIFT.SFT_OT_ISENABLED='Y'";
				HashMap<String, Object[][]>formulaMap=getSqlModel().getSingleResultMap(formulaQuery, 0, 2);
				
				
				
			/* Update data from 'HRMS_OT_HDR' */
			boolean result = false;
			
				
				/* --- INSERT INTO HRMS_OT_CALCULATION START --- */
				
			
								
				if(data!=null && data.length > 0){
					
					Object delObj[][] = new Object[1][3];
					delObj[0][0] = String.valueOf(data[0][0]);
					delObj[0][1] = bean.getMonth();
					delObj[0][2] = bean.getYear();
					
					String deleteQuery="DELETE FROM HRMS_OT_DTL WHERE OT_ID="+bean.getOtCalculationId()+" ";
					result = getSqlModel().singleExecute(deleteQuery);
					
					
					Object[][]obj=new Object[data.length][12];
					for (int i = 0; i < data.length; i++) {
						
						obj[i][0]=bean.getOtCalculationId();
						obj[i][1]=String.valueOf(data[i][0]);
						obj[i][2]=bean.getMonth();
						obj[i][3]=bean.getYear();
						//GET DATA FROM DALIY OT REGISTER
						Object[][]OTMapOj=OTMap.get(String.valueOf(data[i][0]));
						//formulaMap						
						double formulaValueSOT = 0.0;
						double formulaValueDOT = 0.0;	
						
						int totalSOT = 0;
						int totalDOT = 0;	
						
						String formulaSOT = "";
						String formulaDOT = "";
						final String getCreditConfigInfoQuery = "SELECT HRMS_EMP_CREDIT.CREDIT_CODE, NVL(HRMS_EMP_CREDIT.CREDIT_AMT,0) FROM HRMS_EMP_CREDIT WHERE  HRMS_EMP_CREDIT.EMP_ID = "	+ String.valueOf(data[i][0]);
						final Object[][] creditConfigInfoObj = getSqlModel().getSingleResult(getCreditConfigInfoQuery);
						
						if(OTMapOj!=null&& OTMapOj.length>0){
							for (int j = 0; j < OTMapOj.length; j++) {
								
								double  SOT = 0.0;
								double  DOT = 0.0;	
								Object[][]formulaObj=formulaMap.get(String.valueOf(OTMapOj[j][2]));
								if(formulaObj!=null&& formulaObj.length>0){
									formulaSOT=String.valueOf(formulaObj[0][1]);
									formulaDOT=String.valueOf(formulaObj[0][3]);
									
									try {
										SOT = Utility
												.expressionEvaluate(new Utility()
														.generateFormulaPay(
																creditConfigInfoObj, formulaSOT,
																context, session));
									} catch (Exception e) {
										e.printStackTrace();
									}
									try {
										DOT = Utility
												.expressionEvaluate(new Utility()
														.generateFormulaPay(
																creditConfigInfoObj, formulaDOT,
																context, session));
									} catch (Exception e) {
										e.printStackTrace();
									}
									
									formulaValueSOT+=SOT*getMinute(getHH_MM(checkNullHH_MM(String.valueOf(OTMapOj[j][3]))));
									formulaValueDOT+=DOT*getMinute(getHH_MM(checkNullHH_MM(String.valueOf(OTMapOj[j][4]))));
									
									
									totalSOT += getMinute(getHH_MM(checkNullHH_MM(String.valueOf(OTMapOj[j][3]))));
									totalDOT += getMinute(getHH_MM(checkNullHH_MM(String.valueOf(OTMapOj[j][4]))));
									
								}
							}
						}
											
						totalOtAmount += totalSOT+ totalDOT;
						
						//formulaValueSOT=formulaValueSOT*getMinute(getHH_MM(String.valueOf(data[i][2])));
						//formulaValueDOT=formulaValueDOT*getMinute(getHH_MM(String.valueOf(data[i][3])));
						obj[i][4] = getHH_MM(totalSOT + totalDOT);
						obj[i][5]=getHH_MM(totalSOT);
						obj[i][6]=getHH_MM(totalDOT);
						
						formulaValueSOT=(formulaValueSOT/60);
						formulaValueDOT=(formulaValueDOT/60);
						
						
						totalOtAmounthdr +=formulaValueSOT+ formulaValueDOT;
						
						
						
						obj[i][7]=formatter.format(formulaValueSOT);
						obj[i][8]=formatter.format(formulaValueDOT);
						obj[i][9]=formatter.format(formulaValueSOT+ formulaValueDOT);
						obj[i][11]=formatter.format(formulaValueSOT+ formulaValueDOT);
						Object[][]taxCalculateObj=new Object[1][3];
						taxCalculateObj[0][0]=data[i][0];
						taxCalculateObj[0][1]=formatter.format(formulaValueSOT+ formulaValueDOT);
						taxCalculateObj[0][2]=data[i][2];
					
						obj[i][10]="0";
						if(bean.getDeductInconeTaxFlag().equals("true")){
							final EmployeeTaxCalculation taxModel = new EmployeeTaxCalculation();
							taxModel.initiate(context, session);
							final Object[][] insertTaxObj = taxModel.getEmpSlabTaxAmount(taxCalculateObj, bean.getPaidYear(),
									   String.valueOf(Integer.parseInt(bean.getPaidYear()) + 1));
//							obj[i][9]=insertTaxObj[0][1];
							obj[i][10]=formatter.format(insertTaxObj[0][2]);
						
							obj[i][11]=insertTaxObj[0][1];
							
							System.out.println("TDS"+obj[i][10]);
							System.out.println("NET"+obj[i][11]);
							
							totalTds +=Double.parseDouble(checkNull(String.valueOf(insertTaxObj[0][2])));
							totalNet +=Double.parseDouble(checkNull(String.valueOf(insertTaxObj[0][1])));
							
						}
						
					/*	if(bean.getPayInSalaryFlag().equals("true")){
							 
							String sPayInSalQuery;

							Object[][] payInSalData;
							try {
								sPayInSalQuery = " INSERT INTO HRMS_MISC_SALARY_UPLOAD (EMP_ID, MONTH, YEAR, UPLOAD_PAY_TYPE, " +
										"SALARY_CODE, SALARY_AMOUNT, UPLOAD_IS_OVERWRITE, APPL_CODE, APPL_TYPE, DISPLAY_FLAG, COMMENTS) "
										+ " VALUES (?,?,?, ?, ?, ?,?,?,?,?,? ) ";
								payInSalData = new Object[1][11];
								payInSalData[0][0] = data[i][0];  OT Calculation Id 
								payInSalData[0][1] = bean.getPaidMonth();   Paid Month 
								payInSalData[0][2] = bean.getPaidYear();   Paid Year 
								payInSalData[0][3] = "C";  Pay Type 
								payInSalData[0][4] = bean.getCreditCode();  Credit Code 
								payInSalData[0][5] = formatter.format(formulaValueSOT+ formulaValueDOT);  SALARY_AMOUNT 
								payInSalData[0][6] = "Y";  OverWrite 
								payInSalData[0][7] = bean.getOtCalculationId();  OT CALCULATION ID 
								payInSalData[0][8] = "OT";  Appl Type 
								payInSalData[0][9] = "Y";  Display Flag 
								payInSalData[0][10] = "OT Calculation";  Comment 
							
							result = getSqlModel().singleExecute(sPayInSalQuery, payInSalData);
							
							} catch (Exception e) {
								e.printStackTrace();
							}
						}*/
					}
					
			String sQueryHeader = " UPDATE HRMS_OT_HDR SET  OT_MONTH = ? , OT_YEAR = ? ," +
					" OT_DIV = ? , OT_PAYBILL = ? , OT_DEPT = ? , OT_DESG = ? , OT_COST_CENTER = ? , " +
					"OT_BRANCH = ? , OT_PAID_MONTH = ? , OT_PAID_YEAR = ? , OT_PAID_COMPONENT = ? , " +
					"OT_PAID_FLAG = ? , OT_DEDUCT_TDS_FLAG = ? , OT_STATUS = ? ,OT_PROCESS_DATE = SYSDATE , OT_TOTAL_EMP = ? , OT_TOTAL_AMOUNT = ? ,OT_TDS_AMT = ?,  NET_OT_AMT =?  WHERE OT_ID = ? ";
			
			Object[][] headerData = new Object[1][19];
				headerData[0][0] = bean.getMonth(); /* Month */
				headerData[0][1] = bean.getYear(); /* Year */
				headerData[0][2] = bean.getDivisionID(); /* Division Id */
				headerData[0][3] = bean.getPaybillCode(); /* Paybill Code */
				headerData[0][4] = bean.getDeptCode();/* Department Id */
				headerData[0][5] = bean.getDesgCode();/* Designation Code */
				headerData[0][6] = bean.getCostCenterCode();/* Cost Center Id */
				headerData[0][7] = bean.getCenterId();/* Branch Id */
				headerData[0][8] = bean.getPaidMonth();/* Paid In Month */
				headerData[0][9] = bean.getPaidYear();/* Paid In Year */
				headerData[0][10] = bean.getCreditCode();/* Pay in Component */
			
				if (bean.getPayInSalaryFlag().equals("true")) { /* Pay in salary flag */
					headerData[0][11] = "Y";
				} else {
					headerData[0][11] = "N";
				}
				if (bean.getDeductInconeTaxFlag().equals("true")) { /* Deduct in income tax */
					headerData[0][12] = "Y";
				} else {
					headerData[0][12] = "N";
				}
				headerData[0][13] = "P";  /* Total Emp */
				headerData[0][14] = data.length;  /* Total Emp */
				headerData[0][15] = formatter.format(totalOtAmounthdr);  /* Total OT Amount */
				headerData[0][16] = formatter.format(totalTds);  /* Total TDS Amount */
				
				if (bean.getDeductInconeTaxFlag().equals("true")) { /* Deduct in income tax */
					headerData[0][17] = formatter.format(totalNet);  /* Total Net Amount */
				}else{
					headerData[0][17] = formatter.format(totalOtAmounthdr);  /* Total Net Amount */
				}
				
				headerData[0][18] = bean.getOtCalculationId();  /* Ot Calculation ID */
			
				result = getSqlModel().singleExecute(sQueryHeader, headerData);
					
				String insertQuery = "INSERT INTO HRMS_OT_DTL "
						+ "(OT_ID, EMP_ID, MONTH, YEAR, TOTAL_OT, SINGLE_OT, DOUBLE_OT,SINGLE_OT_AMT, DOUBLE_OT_AMT, TOTAL_OT_AMT,OT_TDS_AMT,NET_OT_AMT)"
						+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
					
					result = getSqlModel().singleExecute(insertQuery, obj);
				}
				else{
					result =false;
				}
				/* --- INSERT INTO HRMS_OT_CALCULATION END --- */
				
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	public boolean updateOtCalculationStatus(OtCalculations bean,
			String statusToUpdate) {
		boolean lockResult = false;
		try {
			
			String TDS_DEBIT_CODE="";
			String tdsquery = "  SELECT NVL(TDS_DEBIT_CODE,0) from HRMS_TAX_PARAMETER WHERE TDS_FINANCIALYEAR_FROM = "+String.valueOf(Integer.parseInt(bean.getYear()))+" AND TDS_FINANCIALYEAR_TO = "+String.valueOf(Integer.parseInt(bean.getYear()) + 1);
			Object [][] tdsDebitCodeObj=getSqlModel().getSingleResult(tdsquery)	;
			if(tdsDebitCodeObj!=null && tdsDebitCodeObj.length>0){
				TDS_DEBIT_CODE=String.valueOf(tdsDebitCodeObj[0][0]);
			}
			
			String query="SELECT EMP_ID , TOTAL_OT_AMT ,OT_TDS_AMT  FROM HRMS_OT_DTL "
				+"WHERE OT_ID ="+bean.getOtCalculationId()+" AND MONTH="+bean.getMonth()+" AND YEAR = "+bean.getYear()+" ";
			Object[][] data = getSqlModel().getSingleResult(query);	
	
	
			if (statusToUpdate.equals("L")) {
				String deleteQuery="DELETE FROM HRMS_MISC_SALARY_UPLOAD WHERE APPL_CODE = "+bean.getOtCalculationId()+" AND  APPL_TYPE='"+"OT"+"' AND MONTH="+bean.getPaidMonth()+" AND  YEAR="+bean.getPaidYear()+" ";
				lockResult = getSqlModel().singleExecute(deleteQuery);
				
				
				
				String sPayInSalQuery = " INSERT INTO HRMS_MISC_SALARY_UPLOAD (EMP_ID, MONTH, YEAR, UPLOAD_PAY_TYPE, " +
				"SALARY_CODE, SALARY_AMOUNT, UPLOAD_IS_OVERWRITE, APPL_CODE, APPL_TYPE, DISPLAY_FLAG, COMMENTS) "
				+ " VALUES (?,?,?, ?, ?, ?,?,?,?,? ,?) ";
				Object[][] payInSalData= new Object[data.length][11];
				if(bean.getPayInSalaryFlag().equals("true")){	
					if (data != null && data.length > 0) {
					for (int i = 0; i < data.length; i++) {
					
						try {
									payInSalData[i][0] = data[i][0]; /* OT Calculation Id */
									payInSalData[i][1] = bean.getPaidMonth(); /* Paid Month */
									payInSalData[i][2] = bean.getPaidYear(); /*  Paid Year */
									payInSalData[i][3] = "C"; /* Pay Type */
									payInSalData[i][4] = bean.getCreditCode(); /* Credit Code */
									payInSalData[i][5] = formatter.format(data[i][1]); /* SALARY_AMOUNT */
									payInSalData[i][6] = "Y"; /* OverWrite */
									payInSalData[i][7] = bean.getOtCalculationId(); /* OT CALCULATION ID */
									payInSalData[i][8] = "OT"; /* Appl Type */
									payInSalData[i][9] = "Y"; /* Display Flag */
									payInSalData[i][10] = "OT Calculation"; /* Comment */
						
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
				lockResult = getSqlModel().singleExecute(sPayInSalQuery, payInSalData);
				}
				}
				
				//DELETE ROWS
				String delQuery = "DELETE FROM HRMS_MISC_SALARY_UPLOAD WHERE APPL_TYPE='OT' AND DISPLAY_FLAG='N' AND APPL_CODE="+ bean.getOtCalculationId();
				getSqlModel().singleExecute(delQuery);
				
				String insertTDSQuery = "INSERT INTO HRMS_MISC_SALARY_UPLOAD (HRMS_MISC_SALARY_UPLOAD.EMP_ID, HRMS_MISC_SALARY_UPLOAD.MONTH, HRMS_MISC_SALARY_UPLOAD.YEAR, " +
						"HRMS_MISC_SALARY_UPLOAD.UPLOAD_PAY_TYPE, HRMS_MISC_SALARY_UPLOAD.SALARY_CODE, HRMS_MISC_SALARY_UPLOAD.SALARY_AMOUNT, HRMS_MISC_SALARY_UPLOAD.UPLOAD_IS_OVERWRITE" +
				"           ,APPL_CODE,APPL_TYPE,DISPLAY_FLAG,COMMENTS) " + 
							 " VALUES(?, ?, ?, ?, ?, ?, ?,?,?,?,?)";
				Object[][] payInTDSData= new Object[data.length][11];
				if(bean.getDeductInconeTaxFlag().equals("true")){
					if (data != null && data.length > 0) {
					for (int i = 0; i < data.length; i++) {
					
						try {
							payInTDSData[i][0] = data[i][0]; /* OT Calculation Id */
							payInTDSData[i][1] = bean.getPaidMonth(); /* Paid Month */
							payInTDSData[i][2] = bean.getPaidYear(); /*  Paid Year */
							payInTDSData[i][3] = "D"; /* Pay Type */
							payInTDSData[i][4] = TDS_DEBIT_CODE; /* Debit Code */
							payInTDSData[i][5] = formatter.format(data[i][2]); /* SALARY_AMOUNT */
							payInTDSData[i][6] = "Y"; /* OverWrite */
							payInTDSData[i][7] = bean.getOtCalculationId(); /* OT CALCULATION ID */
							payInTDSData[i][8] = "OT"; /* Appl Type */
							payInTDSData[i][9] = "N"; /* Display Flag */
							payInTDSData[i][10] = "OT Calculation"; /* Comment */
						
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				lockResult = getSqlModel().singleExecute(insertTDSQuery, payInTDSData);
					}
				}
				String updateApproverCommentsSql = " UPDATE HRMS_OT_HDR SET OT_STATUS = '"+statusToUpdate+"' " 
					+" WHERE OT_ID = " + bean.getOtCalculationId();
					lockResult=getSqlModel().singleExecute(updateApproverCommentsSql);
		
			}
			if (statusToUpdate.equals("U")) {
				String delQuery = "DELETE FROM HRMS_MISC_SALARY_UPLOAD WHERE APPL_TYPE='OT' AND DISPLAY_FLAG='Y' AND APPL_CODE="+ bean.getOtCalculationId();
				getSqlModel().singleExecute(delQuery);
				
				String delTDSQuery = "DELETE FROM HRMS_MISC_SALARY_UPLOAD WHERE APPL_TYPE='OT' AND DISPLAY_FLAG='N' AND APPL_CODE="+ bean.getOtCalculationId();
				getSqlModel().singleExecute(delTDSQuery);
				
				String updateApproverCommentsSql = " UPDATE HRMS_OT_HDR SET OT_STATUS = '"+statusToUpdate+"' " 
				+" WHERE OT_ID = " + bean.getOtCalculationId();
			lockResult=getSqlModel().singleExecute(updateApproverCommentsSql);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lockResult;
	}

	public void getReport(OtCalculations bean,
			HttpServletResponse response, HttpServletRequest request,
			String reportPath) {
		try {
			ReportDataSet rds = new ReportDataSet();
			final String month = bean.getMonth(); // month
			// String prevMonth = ""+(Integer.parseInt(month)-1);
			// System.out.println("prevMonth=="+prevMonth);
			final String year = bean.getYear(); // year
			String type = bean.getReport();
			rds.setReportType(type);
			String fileName = "OT Calculation Report" + Utility.getRandomNumber(1000);
			String reportPathName = reportPath + fileName + "." + type;
			rds.setFileName(fileName);
			rds.setReportName("OT Calculation Report");
			rds.setUserEmpId(bean.getUserEmpId());
			rds.setPageSize("A4");
			///rds.setPageSize("TABLOID");
			rds.setPageOrientation("portrait");
			rds.setTotalColumns(10);
			/*
			 * rds.setMarginBottom(25f); rds.setMarginLeft(25f);
			 * rds.setMarginRight(25f);
			 */
			rds.setShowPageNo(true);
			// rds.setMarginTop(25f);
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;

			if (reportPath.equals("")) {
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
						session, context, request);
			} else {
				logger.info("################# ATTACHMENT PATH #############"
						+ reportPath + fileName + "." + type);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
						reportPath, session, context, request);
				request.setAttribute("reportPath", reportPath + fileName + "."
						+ type);
				request.setAttribute("fileName", fileName + "." + type);
				request.setAttribute("action",
						"OtCalculations_input.action");
			}
			rg = getReport(rg, bean);
			rg.process();
			if (reportPath.equals("")) {
				rg.createReport(response);
			} else {
				/* Generates the report as attachment */
				rg.saveReport(response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private org.paradyne.lib.ireportV2.ReportGenerator getReport(
			org.paradyne.lib.ireportV2.ReportGenerator rg,
			OtCalculations bean) {

		try {

			String month = bean.getMonth();
			String year = bean.getYear();
			/* Setting filter details */
			String filters = "Period: " + Utility.month(Integer.parseInt(month)) + " - "+ year + "";

			if (!bean.getDivisionName().equals("")) {
				filters += "\n\nDivision : " + bean.getDivisionName();
			}

			filters+= "\n\nPaid Period: " + Utility.month(Integer.parseInt(bean.getPaidMonth())) + " - "+ bean.getPaidYear() + "";
			
			filters+= "\n\nPay in Component: " + bean.getCreditName() + "";
			if(bean.getPayInSalaryFlag().equals("true")){
				filters+= "\n\nPay in Salary: Yes";
			} else{
				filters+= "\n\nPay in Salary: No";
			}
			
			if(bean.getDeductInconeTaxFlag().equals("true")){
				filters+= "\n\nDeduct Income Tax: Yes";
			} else{
				filters+= "\n\nDeduct Income Tax: No";
			}
			

			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][] { { filters } });
			filterData.setCellAlignment(new int[] { 0 });
			//filterData.setBodyFontStyle(1);
			filterData.setCellWidth(new int[] { 100 });
			filterData.setCellColSpan(new int[]{10});
			filterData.setCellNoWrap(new boolean[]{false});
			
		//	filterData.setBodyFontDetails(Font.FontFamily.HELVETICA, 6, Font.NORMAL, new BaseColor(0, 0, 0));
			filterData.setBorder(false);
			//filterData.setBlankRowsBelow(1);
			rg.addTableToDoc(filterData);

			
			String divisionCode =  bean.getDivisionID();
			
			/*
			 * select query to select approver comment data from
			 * hrms_loan_path
			 */
			
			String empQuery = "SELECT HRMS_OT_DTL.EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||EMP_LNAME," +
							" SUM(HRMS_OT_DTL.SINGLE_OT_AMT), SUM(HRMS_OT_DTL.DOUBLE_OT_AMT), SUM(HRMS_OT_DTL.TOTAL_OT_AMT),SUM(HRMS_OT_DTL.OT_TDS_AMT) "
							+" FROM HRMS_OT_DTL "
							+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_OT_DTL.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
							+"LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)" +
							" LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)" 
							+" WHERE  HRMS_OT_DTL.OT_ID="+bean.getOtCalculationId()+"  AND HRMS_OT_DTL.MONTH="+month+" AND HRMS_OT_DTL.YEAR="+year+" "
							+" AND HRMS_EMP_OFFC.EMP_DIV ="+divisionCode+" ";
			////empQuery += " GROUP BY HRMS_OT_DTL.EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||EMP_LNAME " ;
							
					if (!bean.getCenterName().equals("")) {
						empQuery += " AND HRMS_EMP_OFFC.EMP_CENTER IN ("
								+ bean.getCenterId()+") ";
					}
					
					if (!bean.getDeptName().equals("")) {
						empQuery += " AND HRMS_EMP_OFFC.EMP_DEPT IN ("
								+ bean.getDeptCode()+") ";
					}
					
					if (!bean.getDesgName().equals("")) {
						empQuery += " AND HRMS_EMP_OFFC.EMP_RANK IN ("
								+ bean.getDesgCode()+") ";
					}
					
					if (!bean.getPaybillName().equals("")) {
						empQuery += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN ("
								+ bean.getPaybillCode()+") ";
					}
					

					if (!bean.getCostCenterName().equals("")) {
						empQuery += " AND HRMS_COST_CENTER.COST_CENTER_ID IN("
								+ bean.getCostCenterCode()+")";
					}
					//+" GROUP BY HRMS_OT_DTL.EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||EMP_LNAME ";

					empQuery += " GROUP BY HRMS_OT_DTL.EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||EMP_LNAME " ;
						

			Object[][] empDtlObj = getSqlModel().getSingleResult(empQuery);
								
			String[] header =  new String[10];
			
			int[] bcellAlign = new int[header.length];
			int[] bcellWidth = new int[header.length];
			boolean[] bcellwrap = new boolean[header.length];
				header[0] = "Employee ID";
				header[1] = "Employee Name";
				header[2] = "Single OT Hours";
				header[3] = "Double OT Hours";
				header[4] = "Total OT Hours";
				header[5] = "Single OT Amount";
				header[6] = "Double OT Amount";
				header[7] = "OT Amount";
				header[8] = "TDS";
				header[9] = "Net OT Amount";

			for (int i = 0; i < header.length; i++) {
				
				if (i == 0) {
					bcellAlign[i] = 0;
					bcellWidth[i] = 40;
					bcellwrap[i]=false;
				}else if (i == 1) {
					bcellAlign[i] = 0;
					bcellWidth[i] = 40;
					bcellwrap[i]=false;
				}else {
					bcellAlign[i] = 2;
					bcellWidth[i] = 30;
					bcellwrap[i]=true;
				}
			}
			
			Object[][] objTotalTabularData = new Object[empDtlObj.length][header.length];
			
			Object[][] totalByColumn = new Object[1][header.length];
			
			int singleOtAmountTotal = 0;
			int doubleOtAmountTotal = 0;
			
			int MAINCOUNT = 0;
			if (empDtlObj != null && empDtlObj.length > 0) {
				for (int j = 0; j < empDtlObj.length; j++) {
					
					String comQuery = "SELECT HRMS_OT_DTL.EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||EMP_LNAME," +
							"HRMS_OT_DTL.SINGLE_OT,HRMS_OT_DTL.DOUBLE_OT,HRMS_OT_DTL.TOTAL_OT "
							+" FROM HRMS_OT_DTL "
							+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_OT_DTL.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
							+" WHERE  HRMS_OT_DTL.OT_ID="+bean.getOtCalculationId()+"  AND HRMS_OT_DTL.MONTH="+month+" AND HRMS_OT_DTL.YEAR="+year+" "
							+" AND HRMS_EMP_OFFC.EMP_DIV ="+divisionCode+" AND HRMS_OT_DTL.EMP_ID="+empDtlObj[j][0];
					
					Object[][] comDtlObj = getSqlModel().getSingleResult(comQuery);
					int singleOtAmount = 0;
					int doubleOtAmount = 0;
					int totalOtAmount = 0;
					try {
						for (int i = 0; i < comDtlObj.length; i++) {
							
							singleOtAmount+= getMinute(getHH_MM(checkNullHH_MM(String
									.valueOf(comDtlObj[i][3]))));
							
							doubleOtAmount+= getMinute(getHH_MM(checkNullHH_MM(String
											.valueOf(comDtlObj[i][4]))));
							totalOtAmount+= getMinute(getHH_MM(checkNullHH_MM(String
									.valueOf(comDtlObj[i][5]))));
							
						} // end of loop
					} catch (Exception e) {
						logger.error("exception in houseRentDtl loop", e);
					} // end of catch
					
						
					singleOtAmountTotal+=singleOtAmount;
					doubleOtAmountTotal+=doubleOtAmount;
					
						objTotalTabularData[MAINCOUNT][0] = empDtlObj[j][1]; // emp token
						objTotalTabularData[MAINCOUNT][1] = empDtlObj[j][2]; // emp name
						objTotalTabularData[MAINCOUNT][2] = getHH_MM(singleOtAmount); // Single OT Hours
						objTotalTabularData[MAINCOUNT][3] = getHH_MM(doubleOtAmount); // Double OT Hours
						objTotalTabularData[MAINCOUNT][4] = getHH_MM(singleOtAmount + doubleOtAmount); // Total Ot Hours
						
						objTotalTabularData[MAINCOUNT][5] = formatter.format(empDtlObj[j][3]); // Single OT Amount
						
						objTotalTabularData[MAINCOUNT][6] = formatter.format(empDtlObj[j][4]); // Double OT Amount
						objTotalTabularData[MAINCOUNT][7] = formatter.format(empDtlObj[j][5]);  // OT Amount
						objTotalTabularData[MAINCOUNT][8] = formatter.format(empDtlObj[j][6]); // TDS 
						objTotalTabularData[MAINCOUNT][9] = formatter.format(Double.parseDouble(checkNullValue(String.valueOf(empDtlObj[j][5])))-Double.parseDouble(checkNullValue(String.valueOf(empDtlObj[j][6]))));  
						// Net OT Amount  : OT Amount - TDS
						
						totalByColumn[0][5] = formatter.format(Double
								.parseDouble(checkNullValue(String
										.valueOf(totalByColumn[0][5])))
								+ Double
										.parseDouble(checkNullValue(String
												.valueOf(objTotalTabularData[MAINCOUNT][5]))));
						
						totalByColumn[0][6] = formatter.format(Double
								.parseDouble(checkNullValue(String
										.valueOf(totalByColumn[0][6])))
								+ Double
										.parseDouble(checkNullValue(String
												.valueOf(objTotalTabularData[MAINCOUNT][6]))));
						
						totalByColumn[0][7] = formatter.format(Double
								.parseDouble(checkNullValue(String
										.valueOf(totalByColumn[0][7])))
								+ Double
										.parseDouble(checkNullValue(String
												.valueOf(objTotalTabularData[MAINCOUNT][7]))));
						
						totalByColumn[0][8] = formatter.format(Double
								.parseDouble(checkNullValue(String
										.valueOf(totalByColumn[0][8])))
								+ Double
										.parseDouble(checkNullValue(String
												.valueOf(objTotalTabularData[MAINCOUNT][8]))));
						
						totalByColumn[0][9] = formatter.format(Double
								.parseDouble(checkNullValue(String
										.valueOf(totalByColumn[0][9])))
								+ Double
										.parseDouble(checkNullValue(String
												.valueOf(objTotalTabularData[MAINCOUNT][9]))));
						
						MAINCOUNT++;
				}

				totalByColumn[0][2] = getHH_MM(singleOtAmountTotal); // Single OT Hours Total
				totalByColumn[0][3] = getHH_MM(doubleOtAmountTotal); // Double OT Hours Total
				totalByColumn[0][4] = getHH_MM(singleOtAmountTotal + doubleOtAmountTotal); // Total Ot Hours Total
				
			}
			
			if (empDtlObj != null && empDtlObj.length > 0) {

				Object[][] apprDetails = new Object[1][4];// new-------------->
				apprDetails[0][0] = "OT Details :";
				apprDetails[0][1] = "";
				apprDetails[0][2] = "";
				apprDetails[0][3] = "";

				TableDataSet apprInfoTable = new TableDataSet();
				apprInfoTable.setData(apprDetails);
				apprInfoTable
						.setCellWidth(new int[] { 10,10,10,10 });
				apprInfoTable
						.setCellAlignment(new int[] { 0, 0, 0, 0});
				apprInfoTable.setBorderDetail(0);
				apprInfoTable.setBlankRowsAbove(1);
				rg.addTableToDoc(apprInfoTable);

				TableDataSet branchDetails = new TableDataSet();

				branchDetails.setHeader(header);
				branchDetails.setHeaderBorderDetail(3);
				branchDetails.setHeaderBorderColor(new BaseColor(255,
						0, 0));
				branchDetails.setData(objTotalTabularData);
				
				///branchDetails.setColumnSum(new int[] { 2, 3, 4, 5, 6, 7,8,9 });
				
				branchDetails.setCellWidth(bcellWidth);
				branchDetails.setCellAlignment(bcellAlign);
				branchDetails.setBorderDetail(3);
				rg.addTableToDoc(branchDetails);
				
				totalByColumn[0][0] = "Total:";
				totalByColumn[0][1] = " ";
				
				TableDataSet netEarningDataSet = new TableDataSet();
				netEarningDataSet.setData(totalByColumn);
				netEarningDataSet.setCellWidth(bcellWidth);
				netEarningDataSet.setCellAlignment(bcellAlign);
				netEarningDataSet.setBodyFontStyle(1);
				netEarningDataSet.setBorderLines(true);
				rg.addTableToDoc(netEarningDataSet);
				
				
			//	rg.seperatorLine();
				
			}else {
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = " No records available";

				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 0 });
				noData.setCellWidth(new int[] { 100 });
			///	noData.setBodyFontDetails(FontFamily.HELVETICA, 6, Font.BOLD, new BaseColor(0, 0, 0));
				noData.setBorder(false);
				rg.addTableToDoc(noData);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

	/**Method : getActualPayBillName.
	 * Purpose : this method is used to get PayBill names based on supplied pay bill ID
	 * @param payBillID : payBillID
	 * @return String
	 */
	private String getActualPayBillName(final String payBillID) {
		String payBillName = "";
		try {
			if (payBillID == null || payBillID.equals("null")) {
				payBillName = "";
			} else {
				String payBillNameQuery = "SELECT HRMS_PAYBILL.PAYBILL_GROUP FROM HRMS_PAYBILL WHERE HRMS_PAYBILL.PAYBILL_ID IN ("+ payBillID + ")";
				Object[][] payBillNameObj = getSqlModel().getSingleResult(payBillNameQuery);
				if (payBillNameObj != null && payBillNameObj.length > 0) {
					for (int i = 0; i < payBillNameObj.length; i++) {
						payBillName += String.valueOf(payBillNameObj[i][0])+",";
					}
				}
				payBillName = payBillName.substring(0, payBillName.length()-1);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return payBillName;
	}
	
	/**Method : getActualDepartmentName.
	 * Purpose : this method is used to get PayBill names based on supplied pay bill ID
	 * @param payBillID : payBillID
	 * @return String
	 */
	private String getActualDepartmentName(final String deptID) {
		String deptName = "";
		try {
			if (deptID == null || deptID.equals("null")) {
				deptName = "";
			} else {
				String deptNameQuery = " SELECT HRMS_DEPT.DEPT_NAME FROM HRMS_DEPT WHERE HRMS_DEPT.DEPT_ID IN  ("+ deptID + ")";
				Object[][] deptNameObj = getSqlModel().getSingleResult(deptNameQuery);
				if (deptNameObj != null && deptNameObj.length > 0) {
					for (int i = 0; i < deptNameObj.length; i++) {
						deptName += String.valueOf(deptNameObj[i][0])+",";
					}
				}
				deptName = deptName.substring(0, deptName.length()-1);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return deptName;
	}
	
	
	/**Method : getActualRankName.
	 * Purpose : this method is used to get PayBill names based on supplied pay bill ID
	 * @param payBillID : payBillID
	 * @return String
	 */
	private String getActualRankName(final String rankID) {
		String rankName = "";
		try {
			if (rankID == null || rankID.equals("null")) {
				rankName = "";
			} else {
				String rankNameQuery = " SELECT HRMS_RANK.RANK_NAME FROM HRMS_RANK WHERE HRMS_RANK.RANK_ID IN  ("+ rankID + ")";
				Object[][] rankNameObj = getSqlModel().getSingleResult(rankNameQuery);
				if (rankNameObj != null && rankNameObj.length > 0) {
					for (int i = 0; i < rankNameObj.length; i++) {
						rankName += String.valueOf(rankNameObj[i][0])+",";
					}
				}
				rankName = rankName.substring(0, rankName.length()-1);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return rankName;
	}
	
	/**Method : getActualCostCenterName.
	 * Purpose : this method is used to get PayBill names based on supplied pay bill ID
	 * @param payBillID : payBillID
	 * @return String
	 */
	private String getActualCostCenterName(final String costCenterID) {
		String costCenterName = "";
		try {
			if (costCenterID == null || costCenterID.equals("null")) {
				costCenterName = "";
			} else {
				String costCenterNameQuery = " SELECT HRMS_COST_CENTER.COST_CENTER_NAME FROM HRMS_COST_CENTER WHERE HRMS_COST_CENTER.COST_CENTER_ID IN  ("+ costCenterID + ")";
				Object[][] costCenterNameObj = getSqlModel().getSingleResult(costCenterNameQuery);
				if (costCenterNameObj != null && costCenterNameObj.length > 0) {
					for (int i = 0; i < costCenterNameObj.length; i++) {
						costCenterName += String.valueOf(costCenterNameObj[i][0])+",";
					}
				}
				costCenterName = costCenterName.substring(0, costCenterName.length()-1);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return costCenterName;
	}
	
	/**Method : getActualCenterName.
	 * Purpose : this method is used to get PayBill names based on supplied pay bill ID
	 * @param payBillID : payBillID
	 * @return String
	 */
	private String getActualCenterName(final String centerID) {
		String centerName = "";
		try {
			if (centerID == null || centerID.equals("null")) {
				centerName = "";
			} else {
				String centerNameQuery = " SELECT HRMS_CENTER.CENTER_NAME FROM HRMS_CENTER WHERE HRMS_CENTER.CENTER_ID  IN  ("+ centerID + ")";
				Object[][] centerNameObj = getSqlModel().getSingleResult(centerNameQuery);
				if (centerNameObj != null && centerNameObj.length > 0) {
					for (int i = 0; i < centerNameObj.length; i++) {
						centerName += String.valueOf(centerNameObj[i][0])+",";
					}
				}
				centerName = centerName.substring(0, centerName.length()-1);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return centerName;
	}

	public void getOtDetails(OtCalculations bean, HttpServletRequest request) {
		try {
			String Query = " SELECT HRMS_OT_HDR.OT_ID,HRMS_OT_HDR.OT_MONTH, HRMS_OT_HDR.OT_YEAR, HRMS_OT_HDR.OT_DIV, HRMS_DIVISION.DIV_NAME,"
							+" HRMS_OT_HDR.OT_PAYBILL, "
							+" HRMS_OT_HDR.OT_DEPT, HRMS_OT_HDR.OT_DESG, " +
							" HRMS_OT_HDR.OT_COST_CENTER, HRMS_OT_HDR.OT_BRANCH, " +
							" HRMS_OT_HDR.OT_PAID_COMPONENT, HRMS_CREDIT_HEAD.CREDIT_NAME, "
							+" HRMS_OT_HDR.OT_PAID_FLAG, HRMS_OT_HDR.OT_DEDUCT_TDS_FLAG,HRMS_OT_HDR.OT_PAID_MONTH," +
							"HRMS_OT_HDR.OT_PAID_YEAR, DECODE(HRMS_OT_HDR.OT_STATUS,'P','Pending','L','Lock','U','Unlock') "
							+" , HRMS_OT_HDR.OT_PAID_FLAG || 'Y' , HRMS_OT_HDR.OT_DEDUCT_TDS_FLAG|| 'Y'   FROM HRMS_OT_HDR "
							+" INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_OT_HDR.OT_DIV) "
							//+" LEFT JOIN HRMS_PAYBILL ON(HRMS_PAYBILL.PAYBILL_ID= HRMS_OT_HDR.OT_PAYBILL) "
							//+" LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID =HRMS_OT_HDR.OT_DEPT ) "
							//+" LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID =HRMS_OT_HDR.OT_DESG ) "
							//+" LEFT JOIN HRMS_COST_CENTER ON (HRMS_COST_CENTER.COST_CENTER_ID =HRMS_OT_HDR.OT_COST_CENTER) "
						//	+" LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID =HRMS_OT_HDR.OT_BRANCH) "
							+" LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_OT_HDR.OT_PAID_COMPONENT) "
							+" WHERE HRMS_OT_HDR.OT_ID = " + bean.getOtCalculationId();

			Object[][] data = getSqlModel().getSingleResult(Query);
			if (data != null && data.length > 0) {
				bean.setOtCalculationId(String.valueOf(data[0][0]));
				bean.setMonth(Utility.checkNull(String.valueOf(data[0][1])));
				bean.setYear(String.valueOf(data[0][2]));
				bean.setDivisionID(String.valueOf(data[0][3]));
				bean.setDivisionName(String.valueOf(data[0][4]));
				bean.setPaybillCode(checkNull(String.valueOf(data[0][5])));
				bean.setPaybillName(checkNull(getActualPayBillName(String.valueOf(data[0][5]))));
				bean.setDeptCode(checkNull(String.valueOf(data[0][6])));
				bean.setDeptName(checkNull(getActualDepartmentName(String.valueOf(data[0][6]))));
				bean.setDesgCode(checkNull(String.valueOf(data[0][7])));
				bean.setDesgName(checkNull(getActualRankName(String.valueOf(data[0][7]))));
				bean.setCostCenterCode(checkNull(String.valueOf(data[0][8])));
				bean.setCostCenterName(checkNull(getActualCostCenterName(String.valueOf(data[0][8]))));
				bean.setCenterId(checkNull(String.valueOf(data[0][9])));
				bean.setCenterName(checkNull(getActualCenterName(String.valueOf(data[0][9]))));
				bean.setCreditCode(checkNull(String.valueOf(data[0][10])));
				bean.setCreditName(checkNull(String.valueOf(data[0][11])));
				if (String.valueOf(data[0][12]).equals("Y")) {
					bean.setPayInSalaryFlag("true");
				}
				if (String.valueOf(data[0][13]).equals("Y")) {
					bean.setDeductInconeTaxFlag("true");
				}
				bean.setPaidMonth(String.valueOf(data[0][14]));
				bean.setPaidYear(checkNull(String
						.valueOf(data[0][15])));
				bean.setStatus(checkNull(String
						.valueOf(data[0][16])));
				
				
				bean.setPaySalFlag(checkNull(String
						.valueOf(data[0][17])));
				bean.setDeductTaxFlag(checkNull(String
						.valueOf(data[0][18])));
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*public Object[][] callCheckWorkFlow() {
		String sqlSet = " SELECT HRMS_ATTENDANCE_CONF.CONF_DAILY_ATT_CONNECT_FLAG, HRMS_ATTENDANCE_CONF.CONF_LEAVE_CONNECT_FLAG, HRMS_ATTENDANCE_CONF.CONF_SALARY_START_FLAG,"
				+ " HRMS_ATTENDANCE_CONF.CONF_SALARY_START_DATE, CONF_UPLOAD_MONTH_ATT_FLAG,'N' FROM HRMS_ATTENDANCE_CONF,HRMS_SALARY_CONF ";
		Object[][] flagObj = getSqlModel().getSingleResult(sqlSet);
		return flagObj;
		}*/
	
	/**This function is used to see the emp details.
	 * @param bean : OtCalculations
	 * @param requestID : OT Calculation ID
	 * @param request : HttpServletRequest
	 */
	public void  getViewEmpDetails(OtCalculations bean, String requestID,HttpServletRequest request) {
		try {
			Object[][] listData = null;
			ArrayList calEmpList = new ArrayList();
			// Emp Details Begins
			String calEmpListSql = "SELECT HRMS_OT_DTL.OT_ID,HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||EMP_LNAME, "
				+ "HRMS_OT_DTL.EMP_ID,  HRMS_OT_DTL.SINGLE_OT, HRMS_OT_DTL.DOUBLE_OT, HRMS_OT_DTL.TOTAL_OT , "
				+ " HRMS_OT_DTL.SINGLE_OT_AMT, HRMS_OT_DTL.DOUBLE_OT_AMT, HRMS_OT_DTL.TOTAL_OT_AMT, "
				+ " HRMS_OT_DTL.OT_TDS_AMT, HRMS_OT_DTL.NET_OT_AMT ,HRMS_OT_DTL.MONTH, HRMS_OT_DTL.YEAR "
				+ " FROM HRMS_OT_DTL "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_OT_DTL.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
				+ " WHERE  OT_ID= " + requestID;

			listData = getSqlModel().getSingleResult(calEmpListSql);

			String[] pageIndex = Utility.doPaging(bean.getMyPage(), listData.length, 20);
			if(pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			if(pageIndex[4].equals("1")) bean.setMyPage("1");
			bean.setCalOtEmpIteratorList(null);
			if(listData != null && listData.length > 0) {
				bean.setCalOtEmpListLength(true);
				for(int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					OtCalculations beanItt = new OtCalculations();
					beanItt.setIttEmpID(String.valueOf(listData[i][3]));
					beanItt.setIttEmpToken(String.valueOf(listData[i][1]));
					beanItt.setIttEmployeeName(String.valueOf(listData[i][2]));
					beanItt.setIttSingleOtHours(String.valueOf(listData[i][4]));
					beanItt.setIttDoubleOtHours(String.valueOf(listData[i][5]));
					beanItt.setIttTotalOtHours(String.valueOf(listData[i][6]));
					
					String totalSnglOtAmount = formatter.format(Double.parseDouble(checkNull(String.valueOf(listData[i][7])))); 
					beanItt.setIttSingleOtAmount(String.valueOf(totalSnglOtAmount));
					
					String totalDblOtAmount = formatter.format(Double.parseDouble(checkNull(String.valueOf(listData[i][8])))); 
					beanItt.setIttDoubleOtAmount(String.valueOf(totalDblOtAmount));
					
					String totalOtAmount = formatter.format(Double.parseDouble(checkNull(String.valueOf(listData[i][9])))); 
					beanItt.setIttTotalOt(String.valueOf(totalOtAmount));
					String totalTdsAmount = formatter.format(Double.parseDouble(checkNull(String.valueOf(listData[i][10])))); 
					beanItt.setIttTds(String.valueOf(totalTdsAmount));
					beanItt.setIttNetOtAmount(String.valueOf(listData[i][11]));
					beanItt.setIttOTCalID(String.valueOf(listData[i][0]));
					beanItt.setIttMonth(String.valueOf(listData[i][12]));
					beanItt.setIttYear(String.valueOf(listData[i][13]));
					calEmpList.add(beanItt);
				}
				bean.setCalOtEmpIteratorList(calEmpList);
			}
			// Emp Details End
			
			// Hdr Details Begins
			String calEmpHDrSql = "SELECT HRMS_OT_HDR.OT_PAID_COMPONENT,  "
								+" HRMS_OT_HDR.OT_PAID_FLAG, HRMS_OT_HDR.OT_DEDUCT_TDS_FLAG,HRMS_OT_HDR.OT_PAID_MONTH,"
								+" HRMS_OT_HDR.OT_PAID_YEAR, DECODE(HRMS_OT_HDR.OT_STATUS,'P','Pending','L','Lock','U','Unlock')" 
								+" ,HRMS_OT_HDR.OT_ID,HRMS_CREDIT_HEAD.CREDIT_NAME  FROM HRMS_OT_HDR "
								+" INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_OT_HDR.OT_DIV) "
								+" LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_OT_HDR.OT_PAID_COMPONENT) "
								+" WHERE HRMS_OT_HDR.OT_ID = " + requestID;

			Object[][] hdrDtlData  = getSqlModel().getSingleResult(calEmpHDrSql);
			if (hdrDtlData != null && hdrDtlData.length > 0) {
				//bean.setOtCalculationId(String.valueOf(hdrDtlData[0][0]));
				bean.setCreditCode(checkNull(String.valueOf(hdrDtlData[0][0])));
				bean.setCreditName(checkNull(String.valueOf(hdrDtlData[0][7])));
				if (String.valueOf(hdrDtlData[0][1]).equals("Y")) {
					bean.setPayInSalaryFlag("true");
				}
				if (String.valueOf(hdrDtlData[0][2]).equals("Y")) {
					bean.setDeductInconeTaxFlag("true");
				}
				//bean.setPayInSalaryFlag(String.valueOf(hdrDtlData[0][1]));
			///	bean.setDeductInconeTaxFlag(String.valueOf(hdrDtlData[0][2]));
				bean.setPaidMonth(String.valueOf(hdrDtlData[0][3]));
				bean.setPaidYear(checkNull(String.valueOf(hdrDtlData[0][4])));
				bean.setStatus(checkNull(String.valueOf(hdrDtlData[0][5])));
			}
			// Hdr Details End
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**This Function is used to save Calculated Emp OT Details.
	 * @param bean : OtCalculations
	 * @param request : HttpServletRequest
	 * @param requestID
	 * @return
	 */
	public boolean saveCalEmpOtDetails(OtCalculations bean,
			HttpServletRequest request, String requestID) {
		boolean result = false;
		
		try {
			String paySalary = bean.getPayInSalaryFlag();
			String[] ittOTCalID = request.getParameterValues("ittOTCalID");
			String[] ittEmployeeID = request.getParameterValues("ittEmpID");
			String[] ittSingleOtHours = request.getParameterValues("ittSingleOtHours");
			String[] ittDoubleOtHours = request.getParameterValues("ittDoubleOtHours");
			String[] ittTotalOtHours = request.getParameterValues("ittTotalOtHours");
			String[] ittSingleOtAmount = request.getParameterValues("ittSingleOtAmount");
			String[] ittDoubleOtAmount = request.getParameterValues("ittDoubleOtAmount");
			String[] ittTotalOt = request.getParameterValues("ittTotalOt");
			String[] ittTds = request.getParameterValues("ittTds");
			String[] ittNetOtAmount = request.getParameterValues("ittNetOtAmount");
			String[] ittMonth = request.getParameterValues("ittMonth");
			String[] ittYear = request.getParameterValues("ittYear");
			/*
			 * Values insert into HRMS_MISC_SALARY_UPLOAD when pay in salary flag is checked start
			 */
			if(paySalary.equals("true")){
				String deletePayInSalQuery="DELETE FROM HRMS_MISC_SALARY_UPLOAD WHERE APPL_CODE = "+bean.getOtCalculationId()+" AND  APPL_TYPE='"+"OT"+"' AND MONTH="+bean.getPaidMonth()+" AND  YEAR="+bean.getPaidYear()+" ";
				getSqlModel().singleExecute(deletePayInSalQuery);
				try {
					String sPayInSalQuery="";
					String sDeductIncomeTaxQuery="";
					Object[][] payInSalData;
					Object[][] deductIncomeTaxData;
					/*
					 * this is for inserting values for pay in salary start
					 */
					if (ittEmployeeID != null) {
						payInSalData = new Object[ittEmployeeID.length][11];
						for (int i = 0; i < ittEmployeeID.length; i++) {
						sPayInSalQuery = " INSERT INTO HRMS_MISC_SALARY_UPLOAD (EMP_ID, MONTH, YEAR, UPLOAD_PAY_TYPE, " +
								"SALARY_CODE, SALARY_AMOUNT, UPLOAD_IS_OVERWRITE, APPL_CODE, APPL_TYPE, DISPLAY_FLAG, COMMENTS) "
								+ " VALUES (?,?,?, ?, ?, ?,?,?,?,? ,? ) ";
								payInSalData[i][0] = ittEmployeeID[i] ; /* Emp Id */
								payInSalData[i][1] = bean.getPaidMonth(); /* Month */
								payInSalData[i][2] = ittYear[i]; /* Year */
								payInSalData[i][3] = "C"; /* Pay Type */
								payInSalData[i][4] = bean.getCreditCode(); /* Credit Code */
								payInSalData[i][5] =  ittTotalOt[i]; /* SALARY_AMOUNT */
								payInSalData[i][6] = "Y"; /* OverWrite */
								payInSalData[i][7] = bean.getOtCalculationId(); /* OT CALCULATION ID */
								payInSalData[i][8] = "OT"; /* Appl Type */
								payInSalData[i][9] = "Y"; /* Display Flag */
								payInSalData[i][10] = "OT Emp Calculation"; /* Comment */
							}	
						result = getSqlModel().singleExecute(sPayInSalQuery, payInSalData);
					}
					/*
					 * this is for inserting values for pay in salary end
					 */
					
					/*
					 * this is for inserting values for deduction in income tax start
					 */
					if (ittEmployeeID != null) {
						deductIncomeTaxData = new Object[ittEmployeeID.length][11];
						for (int i = 0; i < ittEmployeeID.length; i++) {
							sDeductIncomeTaxQuery = " INSERT INTO HRMS_MISC_SALARY_UPLOAD (EMP_ID, MONTH, YEAR, UPLOAD_PAY_TYPE, " +
								"SALARY_CODE, SALARY_AMOUNT, UPLOAD_IS_OVERWRITE, APPL_CODE, APPL_TYPE, DISPLAY_FLAG, COMMENTS) "
								+ " VALUES (?,?,?, ?, ?, ?,?,?,?,? ,? ) ";
							
						deductIncomeTaxData[i][0] = ittEmployeeID[i] ; /* Emp Id */
						deductIncomeTaxData[i][1] = bean.getPaidMonth(); /* Month */
						deductIncomeTaxData[i][2] = ittYear[i]; /* Year */
						deductIncomeTaxData[i][3] = "D"; /* Pay Type */
						deductIncomeTaxData[i][4] = bean.getCreditCode(); /* Credit Code */
						deductIncomeTaxData[i][5] =  ittTds[i]; /* TDS_AMOUNT */
						deductIncomeTaxData[i][6] = "Y"; /* OverWrite */
						deductIncomeTaxData[i][7] = bean.getOtCalculationId(); /* OT CALCULATION ID */
						deductIncomeTaxData[i][8] = "OT"; /* Appl Type */
						deductIncomeTaxData[i][9] = "N"; /* Display Flag */
						deductIncomeTaxData[i][10] = "OT Emp Calculation"; /* Comment */
					
							}	
						result = getSqlModel().singleExecute(sDeductIncomeTaxQuery, deductIncomeTaxData);
					}
					
					/*
					 * this is for inserting values for deduction in income tax end
					 */
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			/*
			 * Values insert into HRMS_MISC_SALARY_UPLOAD when pay in salary flag is checked end
			 */
				
				if(ittEmployeeID!=null&& ittEmployeeID.length>0){
				
				Object[][]delObj=new Object[ittEmployeeID.length][2];
				
				if (ittEmployeeID != null) {
					Object modParam[][] = new Object[ittEmployeeID.length][12];
					for (int i = 0; i < ittEmployeeID.length; i++) {
						modParam[i][0] = ittOTCalID[i];
						modParam[i][1] = checkNull(String.valueOf(ittEmployeeID[i]));
						modParam[i][2] = ittSingleOtHours[i];
						modParam[i][3] = ittDoubleOtHours[i];
						modParam[i][4] = ittTotalOtHours[i];
						modParam[i][5] = ittSingleOtAmount[i];
						modParam[i][6] = ittDoubleOtAmount[i];
						modParam[i][7] = ittTotalOt[i];
						modParam[i][8] = ittTds[i];
						modParam[i][9] = ittNetOtAmount[i];
						modParam[i][10] = ittMonth[i];
						modParam[i][11] = ittYear[i];

						delObj[i][0]=checkNull(String.valueOf(ittEmployeeID[i]));
						delObj[i][1]=ittOTCalID[i];
						
					}
					
					String deleteQuery="DELETE FROM HRMS_OT_DTL WHERE HRMS_OT_DTL.EMP_ID = ? " +
							"AND HRMS_OT_DTL.OT_ID = ?";
					
					result = getSqlModel().singleExecute(deleteQuery, delObj);
					
					String insertQuery = "INSERT INTO HRMS_OT_DTL(HRMS_OT_DTL.OT_ID, " +
							" HRMS_OT_DTL.EMP_ID, SINGLE_OT, DOUBLE_OT,TOTAL_OT,SINGLE_OT_AMT, DOUBLE_OT_AMT, " +
							" HRMS_OT_DTL.TOTAL_OT_AMT,  HRMS_OT_DTL.OT_TDS_AMT, " +
							" HRMS_OT_DTL.NET_OT_AMT,MONTH, YEAR ) " +
							" VALUES (?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?)";
				
					result = getSqlModel().singleExecute(insertQuery, modParam);
					
					String sQueryHeader = " UPDATE HRMS_OT_HDR SET OT_TOTAL_AMOUNT = ?, OT_TDS_AMT = ?, NET_OT_AMT = ? WHERE OT_ID = ? ";
						Object[][] headerData = new Object[1][4];
						
						headerData[0][0] = bean.getTotalOtAmt(); /* TOTAL TDS AMOUNT */
						headerData[0][1] = bean.getTotalTdsAmt(); /* TOTAL TDS AMOUNT */
						headerData[0][2] = bean.getNetOtAmt();/* TOTAL NET OT AMOUNT */ 
						headerData[0][3] = bean.getOtCalculationId(); /* OtCalculationId */
						/*for (int i = 0; i < headerData.length; i++) {
							for (int j = 0; j < headerData[i].length; j++) {
								logger.info("headerData[" + i + "][" + j
										+ "]  " + headerData[i][j]);
							}
						}*/
						result = getSqlModel().singleExecute(sQueryHeader, headerData);
				}
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * CONVERT HH:MM INTO MINUTS
	 * 
	 * @param result
	 * @return
	 */
	public int getMinute(String result) {
		int min = 0;
		if (result == null || result.equals("null") || result.equals("")) {
			return min;
		}// end of if
		else {
			if (result.contains(":")) {
				String[] chk = result.split(":");
				try {
					min = Integer.parseInt(chk[0]) * 60;
					min = min + Integer.parseInt(chk[1]);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			return min;
		}// end of else
	}

	public String getDAYS_HH_MM(int minuts, int shiftMinuts) {
		logger.info("total min  " + minuts + "  ======  Shift min  "
				+ shiftMinuts);
		String days = "0 Days 00:00";
		String hrs = "00";
		String minute = "00";
		int dd = 0;
		int mm = 0;
		int hh = 0;
		float newDD = 0;
		try {
			dd = minuts > shiftMinuts ? minuts / shiftMinuts : 0;
			minuts = dd > 0 ? (minuts - (dd * shiftMinuts)) : minuts;
			hh = minuts >= 60 ? minuts / 60 : 0;
			mm = hh > 0 ? minuts - (hh * 60) : minuts;
			/**
			 * IF HRS GRATER THAN HALF DAY CONVERT INTO HALF DAY
			 * 
			 */
			// dd=hh>=(shiftMinuts/2):dd
			newDD = dd;

			/*
			 * if((hh)>=(shiftMinuts/120)){ newDD=newDD+0.5f;
			 * hh=hh-(shiftMinuts/120);
			 * System.out.println("hh------------------------- : "+hh); }
			 */
			if ((hh * 60) >= (shiftMinuts / 2)) {
				newDD = newDD + 0.5f;
				hh = (hh * 60) - (shiftMinuts / 2);
				// hh=hh/60>0?hh/60:
				mm = mm + hh % 60;
				hh = hh / 60;
				hh = (mm >= 60) ? (hh + 1) : hh;
				mm = (mm >= 60) ? (mm - 60) : mm;
			}

			if (hh < 10) {
				hrs = "0" + hh;
			}
			minute = "" + mm;
			if (mm < 10) {
				minute = "0" + mm;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		days = newDD + " Days " + hrs + ":" + minute;
		return days;
	}

	
	
	/**
	 * CONVERT MINUTS INTO HH:MM FORMATE
	 * 
	 * @param result
	 * @return
	 */
	public String getHH_MM(int minuts) {
		String hrs = "00";
		String minute = "00";
		int mm = 0;
		int hh = 0;
		try {
			hh = minuts / 60;
			mm = (minuts % 60);
			hrs = "" + hh;
			if (hh < 10) {
				hrs = "0" + hh;
			}
			minute = "" + mm;
			if (mm < 10) {
				minute = "0" + mm;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return (hrs + ":" + minute);
	}
	
	/**
	 * CONVERT H:M INTO HH:MM FORMATE
	 * 
	 * @param result
	 * @return
	 */
	public String getHH_MM(String hh_mm) {
		String hrs = "00";
		String minute = "00";
		int mm = 0;
		int hh = 0;
		try {
			String[] data = hh_mm.split(":");
			hh = Integer.parseInt(String.valueOf(data[0]).trim());
			mm = Integer.parseInt(String.valueOf(data[1]).trim());
			hrs = "" + hh;
			if (hh < 10) {
				hrs = "0" + hh;
			}
			minute = "" + mm;
			if (mm < 10) {
				minute = "0" + mm;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (hrs + ":" + minute);
	}


	/**This function is used to check wheather Ot is already calculated or not for given month and year.
	 * @param bean : OtCalculations
	 * @return result
	 */
	public boolean checkOTHDRDtls(OtCalculations bean) {
		boolean result = true;
		
		String Query = " SELECT OT_MONTH, OT_YEAR, OT_DIV "
			+" FROM HRMS_OT_HDR "
			+" WHERE OT_MONTH="+bean.getMonth()+" AND OT_YEAR ="+bean.getYear()+" ";
			
		if(!bean.getDivisionID().equals("")) {
			Query += " AND OT_DIV ="+bean.getDivisionID()+" " ;
			}
		Query += " AND OT_BRANCH IS NULL AND OT_DEPT IS NULL AND OT_DESG IS NULL AND OT_PAYBILL IS NULL AND OT_COST_CENTER IS NULL  " ;
			
			///Query += " GROUP BY HRMS_OT_REGISTER.EMP_ID,HRMS_EMP_OFFC.EMP_FNAME " ;
		Object[][] data = getSqlModel().getSingleResult(Query);
		if (data != null && data.length > 0) {
			result = false;
		}// end of if
		
		
		
		 Query = " SELECT OT_MONTH, OT_YEAR, OT_DIV "
			+" FROM HRMS_OT_HDR "
			+" WHERE OT_MONTH="+bean.getMonth()+" AND OT_YEAR ="+bean.getYear()+" ";
			
		if(!bean.getDivisionID().equals("")) {
			Query += " AND OT_DIV ="+bean.getDivisionID()+" " ;
			}
			if (!bean.getCenterName().equals("")) {
				Query += " AND OT_BRANCH IN ("
						+ bean.getCenterId()+") ";
			}
			
			if (!bean.getDeptName().equals("")) {
				Query += " AND OT_DEPT IN ("
						+ bean.getDeptCode()+") ";
			}
			
			if (!bean.getDesgName().equals("")) {
				Query += " AND OT_DESG IN ("
						+ bean.getDesgCode()+") ";
			}
			
			if (!bean.getPaybillName().equals("")) {
				Query += " AND OT_PAYBILL IN ("
						+ bean.getPaybillCode()+") ";
			}
			

			if (!bean.getCostCenterName().equals("")) {
				Query += " AND OT_COST_CENTER IN("
						+ bean.getCostCenterCode()+")";
			}
			
			///Query += " GROUP BY HRMS_OT_REGISTER.EMP_ID,HRMS_EMP_OFFC.EMP_FNAME " ;
		 data = getSqlModel().getSingleResult(Query);
		if (data != null && data.length > 0) {
			result = false;
		}// end of if
		return result;
	}


	/**This function  is used to check whether salary is already locked or not for given paid month and year.
	 * @param bean :OtCalculations
	 * @return message
	 */
	public String checkSalaryLock(OtCalculations bean) {
		/**
		 * CHECK SALARY LOCK NOR NOT
		 */
		String message="";
		String paidYear=bean.getPaidYear();
		String paidMonth=bean.getPaidMonth();
		
		String salQUery="SELECT LEDGER_STATUS FROM HRMS_SALARY_LEDGER WHERE LEDGER_STATUS='SAL_FINAL' AND LEDGER_MONTH="+paidMonth+" AND LEDGER_YEAR="+paidYear+" AND "
						+"	LEDGER_DIVISION="+bean.getDivisionID()+" ";
		if(!bean.getCenterId().equals("")){
			salQUery+= " AND LEDGER_BRN="+bean.getCenterId();
		}
		Object[][]salObj=getSqlModel().getSingleResult(salQUery);
		if(bean.getPayInSalaryFlag().equals("true") && salObj!=null && salObj.length>0){
			message="Salary already locked for select paid in month and year";
			return message;
		}
		
		 salQUery="SELECT LEDGER_STATUS FROM HRMS_SALARY_LEDGER WHERE LEDGER_STATUS='SAL_FINAL' AND LEDGER_MONTH="+paidMonth+" AND LEDGER_YEAR="+paidYear+" AND "
				+"	LEDGER_DIVISION="+bean.getDivisionID()+" AND LEDGER_BRN IS NULL ";
		
		salObj=getSqlModel().getSingleResult(salQUery);
		if(bean.getPayInSalaryFlag().equals("true") && salObj!=null && salObj.length>0){
		message="Salary already locked for select paid in month and year";
		return message;
		}
		
		return message;
	}
	
}
