package org.paradyne.model.payroll.ot;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.paradyne.bean.payroll.ot.OtRegisterMISReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;

/**
 * Created on 12th Mar 2012.
 * 
 * @author aa1385
 * 
 */
public class OtRegisterMISReportModel extends ModelBase {
	/** formatter. */
	NumberFormat formatter = new DecimalFormat("#0.00");
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(OtRegisterMISReportModel.class);
	
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

	/**Method : checkNullValue.
	 * Purpose : This method is used to check for null decimal values.
	 * Checks for the null value and if it finds it to be null then replaces it
	 * with 0.
	 * 
	 * @param result :- Input String to be checked
	 * @return : - returns the checked string
	 */
	public String checkNullValue(String result) {
		if (result == null || result.equals("null")) {
			return "0";
		} else {
			return result;
		}
	}
	
	
	public void getReport(OtRegisterMISReport bean,
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
			String fileName = "OT Register MIS Report for "+bean.getDivisionName() +" "+ Utility.getRandomNumber(1000);
			String reportPathName = reportPath + fileName + "." + type;
			rds.setFileName(fileName);
			rds.setReportName("OT Register MIS Report");
			rds.setUserEmpId(bean.getUserEmpId());
			rds.setPageSize("A4");
			rds.setPageSize("TABLOID");
			//rds.setPageOrientation("landscape");
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
					"/ot/OtRegisterMISReport_input.action");
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
			OtRegisterMISReport bean) {


		try {

			String fromDate = bean.getFromDate();
			String toDate = bean.getToDate();
			/* Setting filter details */
			String filters = "Period: " + fromDate + " to "+ toDate + "";

			if (!bean.getDivisionName().equals("")) {
				filters += "\n\nDivision: " + bean.getDivisionName();
			}
			
			if (!bean.getCenterName().equals("")) {
				filters += "\n\nBranch: " + bean.getCenterName();
			}
			if (!bean.getDeptName().equals("")) {
				filters += "\n\nDepartment: " + bean.getDeptName();
			}
			if (!bean.getDesgName().equals("")) {
				filters += "\n\nDesignation: " + bean.getDesgName();
			}
			if (!bean.getPaybillName().equals("")) {
				filters += "\n\nPaybill: " + bean.getPaybillName();
			}
			if (!bean.getCostCenterName().equals("")) {
				filters += "\n\nCost Center: " + bean.getCostCenterName();
			}
			if (!bean.getManagerName().equals("")) {
				filters += "\n\nManager: " + bean.getManagerName();
			}
			if (!bean.getShiftName().equals("")) {
				filters += "\n\nShift: " + bean.getShiftName();
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
			
			String commentQuery = "SELECT HRMS_EMP_OFFC.EMP_TOKEN,EMP_FNAME||' '||EMP_LNAME,   " +
						"SHIFT_NAME,HRMS_CENTER.CENTER_NAME, HRMS_DEPT.DEPT_NAME, "
						+" NVL(HRMS_CADRE.CADRE_NAME,'') , HRMS_SALARY_MISC.SAL_ACCNO_REGULAR, "
						+" HRMS_BANK.BANK_NAME,TO_CHAR(OT_DATE, 'DD-MM-YYYY'), REGULAR_OT,NVL(TO_CHAR(APPR_SINGLE_OT, 'HH24:MI'),'00:00')," +
								" DOUBLE_OT,NVL(TO_CHAR(APPR_DOUBLE_OT, 'HH24:MI'),'00:00') ,HRMS_EMP_OFFC.EMP_ID "	 
						+" FROM HRMS_OT_REGISTER 	 "
						+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_OT_REGISTER.EMP_ID)	 "
						+" INNER JOIN HRMS_SHIFT ON (HRMS_SHIFT.SHIFT_ID = HRMS_OT_REGISTER.OT_SHIFT_CODE)	"
						+" LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE) "
						+"  LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
						+" LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT)  "
						+"  LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
						+" 	LEFT JOIN HRMS_BANK ON (HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_MICR_REGULAR) "
						+" LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
						+"  WHERE  OT_DATE >=TO_DATE('" + fromDate + "','DD-MM-YYYY')  " +
								"	AND OT_DATE <=TO_DATE('" + toDate + "','DD-MM-YYYY')"
								+" AND HRMS_EMP_OFFC.EMP_DIV ="+divisionCode+" ";
								
								if (!bean.getCenterName().equals("")) {
									commentQuery += " AND HRMS_EMP_OFFC.EMP_CENTER IN ("
											+ bean.getCenterId()+") ";
									System.out.println("+bean.getCenterId() ="
											+ bean.getCenterId());
								}
								
								if (!bean.getDeptName().equals("")) {
									commentQuery += " AND HRMS_EMP_OFFC.EMP_DEPT IN ("
											+ bean.getDeptCode()+") ";
									System.out.println("+bean.getDeptId() =" + bean.getDeptCode());
								}
								
								if (!bean.getDesgName().equals("")) {
									commentQuery += " AND HRMS_EMP_OFFC.EMP_RANK IN ("
											+ bean.getDesgCode()+") ";
									System.out.println("+bean.getDesgCode() =" + bean.getDesgCode());
								}
								
								if (!bean.getPaybillName().equals("")) {
									commentQuery += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN ("
											+ bean.getPaybillCode()+") ";
									System.out.println("+bean.getPaybillId() ="
											+ bean.getPaybillCode());
								}
								

								if (!bean.getCostCenterName().equals("")) {
									commentQuery += " AND HRMS_COST_CENTER.COST_CENTER_ID IN("
											+ bean.getCostCenterCode()+")";
									System.out
											.println("+bean.getCostCenterCode() =" + bean.getCostCenterCode());
								}
								
								if (!bean.getShiftName().equals("")) {
									commentQuery += " AND HRMS_OT_REGISTER.OT_SHIFT_CODE IN ("
											+ bean.getShiftCode()+") ";
									System.out.println("+bean.getShiftCode() ="
											+ bean.getShiftCode());
								}
								
								if (!bean.getManagerName().equals("")) {
									commentQuery += " AND HRMS_EMP_OFFC.EMP_REPORTING_OFFICER IN ("
											+ bean.getManagerID()+") ";
									System.out.println("+bean.getManagerID() ="
											+ bean.getManagerID());
								}
								
								//+" GROUP BY HRMS_OT_DTL.EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||EMP_LNAME ";

								commentQuery += " ORDER BY HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ASC " ;
								
			
								/*
			 * execute select query and set the comment data
			 */
			Object[][] comment = getSqlModel().getSingleResult(
					commentQuery);

			int headerObjCount=7;
			if(bean.getDoubleOTflag().equals("YY")){
				headerObjCount=8;
			}
			if(bean.getCheckBrn().equals("true")){
				headerObjCount++;
			}
			if(bean.getCheckDept().equals("true")){
				headerObjCount++;
			}
			if(bean.getCheckEmpGrade().equals("true")){
				headerObjCount++;
			}
			if(bean.getCheckAccount().equals("true")){
				headerObjCount++;
			}
			if(bean.getCheckBank().equals("true")){
				headerObjCount++;
			}
			
			String[] header =  new String[headerObjCount];
			int headerCount = 3;
			
		//	header[0] = "Sr. No.";
			header[0] = "Employee ID";
			header[1] = "Employee Name";
			header[2] = "Shift Name";
			
			if(bean.getCheckBrn().equals("true")){
				header[headerCount] = "Branch";
				headerCount++;
			}
			if(bean.getCheckDept().equals("true")){
				header[headerCount] = "Department";
				headerCount++;
			}
			 
			if(bean.getCheckEmpGrade().equals("true")){
				header[headerCount] = "Grade";
				headerCount++;
			}
			if(bean.getCheckAccount().equals("true")){
				header[headerCount] = "Account Number";
				headerCount++;
			}
			
			if(bean.getCheckBank().equals("true")){
				header[headerCount] = "Bank";
				headerCount++;
			}
			
			
			header[headerCount] = "Date";
			headerCount++;
			
			header[headerCount] = "Single OT Hours";
			headerCount++;
			
			header[headerCount] = "Approved Single OT Hours";
			headerCount++;
			
			if(bean.getDoubleOTflag().equals("YY")){
				
				header[headerCount] = "Double OT Hours";
				headerCount++;
				
			header[headerCount] = "Approved Double OT Hours";
			headerCount++;
			}

			int[] bcellAlign = new int[headerObjCount];
			int[] bcellWidth = new int[headerObjCount];
			int allignWidthCount = 3;
			bcellAlign[0] = 0;
			bcellWidth[0] = 7;
			bcellAlign[1] = 0;
			bcellWidth[1] = 13;
			bcellAlign[2] = 0;
			bcellWidth[2] = 7;
				
			if(bean.getCheckBrn().equals("true")){
				bcellAlign[allignWidthCount] = 0;
				bcellWidth[allignWidthCount] = 7;
				allignWidthCount++;
			}
				
			if(bean.getCheckDept().equals("true")){
				bcellAlign[allignWidthCount] = 0;
				bcellWidth[allignWidthCount] = 9;
				allignWidthCount++;
			}
				 
			if(bean.getCheckEmpGrade().equals("true")){
				bcellAlign[allignWidthCount] = 0;
				bcellWidth[allignWidthCount] = 7;
				allignWidthCount++;
			}
				
			if(bean.getCheckAccount().equals("true")){
				bcellAlign[allignWidthCount] = 0;
				bcellWidth[allignWidthCount] = 7;
				allignWidthCount++;
			}
				
			if(bean.getCheckBank().equals("true")){
				bcellAlign[allignWidthCount] = 0;
				bcellWidth[allignWidthCount] = 7;
				allignWidthCount++;
			}
			
				
			bcellAlign[allignWidthCount] = 0;
			bcellWidth[allignWidthCount] = 7;
			allignWidthCount++;
			
			bcellAlign[allignWidthCount] = 2;
			bcellWidth[allignWidthCount] = 7;
			allignWidthCount++;
			
			bcellAlign[allignWidthCount] = 2;
			bcellWidth[allignWidthCount] = 7;
			allignWidthCount++;
			
			if(bean.getDoubleOTflag().equals("YY")){
				bcellAlign[allignWidthCount] = 2;
				bcellWidth[allignWidthCount] = 7;
				allignWidthCount++;
				
			bcellAlign[allignWidthCount] = 2;
			bcellWidth[allignWidthCount] = 7;
			allignWidthCount++;
			}
			
			
			Object[][] objTotalTabularData = new Object[comment.length][headerCount];
			
			if (comment != null && comment.length > 0) {
				double valueTotal = 0.0;
				for (int j = 0; j < comment.length; j++) {
					//objTotalTabularData[j][0] = countSr++;// token
					objTotalTabularData[j][0] = comment[j][0]; // emp token
					objTotalTabularData[j][1] = comment[j][1]; // emp name
					objTotalTabularData[j][2] = comment[j][2]; // shift name
					
					int headerDataObjCount = 3;
					if(bean.getCheckBrn().equals("true")){
						objTotalTabularData[j][headerDataObjCount] = Utility.checkNull(String.valueOf(comment[j][3]));//BRANCH
						headerDataObjCount++;
					}
					if(bean.getCheckDept().equals("true")){
						objTotalTabularData[j][headerDataObjCount] = Utility.checkNull(String.valueOf(comment[j][4]));//DEPARTMENT
						headerDataObjCount++;
					}
					if(bean.getCheckEmpGrade().equals("true")){
						objTotalTabularData[j][headerDataObjCount] = Utility.checkNull(String.valueOf(comment[j][5]));//GRADE
						headerDataObjCount++;
					}
					if(bean.getCheckAccount().equals("true")){
						objTotalTabularData[j][headerDataObjCount] = Utility.checkNull(String.valueOf(comment[j][6]));//ACCOUNT NUMBER
						headerDataObjCount++;
					}
					if(bean.getCheckBank().equals("true")){
						objTotalTabularData[j][headerDataObjCount] = Utility.checkNull(String.valueOf(comment[j][7]));//BANK
						headerDataObjCount++;
					}
					
					
					objTotalTabularData[j][headerDataObjCount] = checkNull(String.valueOf(comment[j][8])); // Date
					headerDataObjCount++;
					
					objTotalTabularData[j][headerDataObjCount] = formatter.format(Double.parseDouble(checkNullValue(String.valueOf(comment[j][9])))); //  Single OT Hours
					headerDataObjCount++;
					
					objTotalTabularData[j][headerDataObjCount] = String.valueOf(comment[j][10]); // Appr Single OT Hours
					
					
					if(bean.getDoubleOTflag().equals("YY")){
						headerDataObjCount++;
						
						objTotalTabularData[j][headerDataObjCount] = formatter.format(Double.parseDouble(checkNullValue(String.valueOf(comment[j][11])))); // Double OT Hours
						headerDataObjCount++;
						
					objTotalTabularData[j][headerDataObjCount] = String.valueOf(comment[j][12]);//Appr Double OT Hours
					
					}
					
					
				}
				
				Object[][] apprDetails = new Object[1][4];// new-------------->
				apprDetails[0][0] = "OT Register Details :";
				apprDetails[0][1] = "";
				apprDetails[0][2] = "";
				apprDetails[0][3] = "";

				TableDataSet apprInfoTable = new TableDataSet();
				apprInfoTable.setData(apprDetails);
				apprInfoTable.setCellWidth(new int[] { 10,10,10,10 });
				apprInfoTable.setCellAlignment(new int[] { 0, 0, 0, 0});
				apprInfoTable.setBorderDetail(0);
				apprInfoTable.setBlankRowsAbove(1);
				rg.addTableToDoc(apprInfoTable);

				TableDataSet branchDetails = new TableDataSet();
				branchDetails.setHeader(header);
				branchDetails.setHeaderBorderDetail(3);
				branchDetails.setHeaderBorderColor(new BaseColor(255,
						0, 0));
				branchDetails.setData(objTotalTabularData);
				branchDetails.setCellWidth(bcellWidth);
				branchDetails.setCellAlignment(bcellAlign);
				branchDetails.setBorderDetail(3);
				//branchDetails.setBlankRowsBelow(1);
				rg.addTableToDoc(branchDetails);
				
				
				String totQuery = " SELECT NVL(HRMS_OT_REGISTER.REGULAR_OT,0.0), 	"
						+"	NVL(TO_CHAR(HRMS_OT_REGISTER.APPR_SINGLE_OT, 'HH24:MI'),'00:00'),  "
						+"	NVL(HRMS_OT_REGISTER.DOUBLE_OT,0.0) ,"
						+"	NVL(TO_CHAR(HRMS_OT_REGISTER.APPR_DOUBLE_OT, 'HH24:MI'),'00:00') "
						+"	FROM HRMS_OT_REGISTER " 
						+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_OT_REGISTER.EMP_ID)	 "
						+" INNER JOIN HRMS_SHIFT ON (HRMS_SHIFT.SHIFT_ID = HRMS_OT_REGISTER.OT_SHIFT_CODE)	"
						+" LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE) "
						+"  LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "
						+" LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT)  "
						+"  LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
						+" 	LEFT JOIN HRMS_BANK ON (HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_MICR_REGULAR) "
						+" LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
						
						+"  WHERE  OT_DATE >=TO_DATE('" + fromDate + "','DD-MM-YYYY')  " +
						"	AND OT_DATE <=TO_DATE('" + toDate + "','DD-MM-YYYY')"
						+" AND HRMS_EMP_OFFC.EMP_DIV ="+divisionCode+" ";
						
								if (!bean.getCenterName().equals("")) {
									totQuery += " AND HRMS_EMP_OFFC.EMP_CENTER IN ("
											+ bean.getCenterId()+") ";
								}
								
								if (!bean.getDeptName().equals("")) {
									totQuery += " AND HRMS_EMP_OFFC.EMP_DEPT IN ("
											+ bean.getDeptCode()+") ";
								}
								if (!bean.getDesgName().equals("")) {
									totQuery += " AND HRMS_EMP_OFFC.EMP_RANK IN ("
											+ bean.getDesgCode()+") ";
								}
								if (!bean.getPaybillName().equals("")) {
									totQuery += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN ("
											+ bean.getPaybillCode()+") ";
								}
								if (!bean.getCostCenterName().equals("")) {
									totQuery += " AND HRMS_COST_CENTER.COST_CENTER_ID IN("
											+ bean.getCostCenterCode()+")";
								}
								
								if (!bean.getShiftName().equals("")) {
									totQuery += " AND HRMS_OT_REGISTER.OT_SHIFT_CODE IN ("
											+ bean.getShiftCode()+") ";
									
								}
								
								if (!bean.getManagerName().equals("")) {
									totQuery += " AND HRMS_EMP_OFFC.EMP_REPORTING_OFFICER IN ("
											+ bean.getManagerID()+") ";
									
								}
								
								Object[][] totalDataObject = getSqlModel().getSingleResult(
										totQuery);
								
								
					Object finalTotalObj[][] = new Object[1][headerCount];
					if (finalTotalObj != null && finalTotalObj.length > 0) {
						int totalSingleOT = 0;
						int totalApprSingleMinuts = 0;
						int totalDoubleOT = 0;
						int totalApprDoubleMinuts = 0;
						
						for (int i = 0; i < totalDataObject.length; i++) {
							
							try {
								
								totalSingleOT+= Double.parseDouble(String.valueOf(totalDataObject[i][0]));
								totalApprSingleMinuts+= getMinute(getHH_MM(String.valueOf(totalDataObject[i][1])));
								totalDoubleOT+= Double.parseDouble(String.valueOf(totalDataObject[i][2]));
								totalApprDoubleMinuts+= getMinute(getHH_MM(String.valueOf(totalDataObject[i][3])));
								
							} catch (Exception e) {
								e.printStackTrace();
								////totalMinuts = totalMinuts;
							}
						}
						
					int[] toalCellAlign = new int[headerCount];
					int[] toalCellWidth = new int[headerCount];
					
					int totalCount = 3;
					finalTotalObj[0][0] = "Total ";
					toalCellAlign[0] = 0;
					toalCellWidth[0] = 7;
					finalTotalObj[0][1] = " ";
					toalCellAlign[1] = 0;
					toalCellWidth[1] = 13;
					
					finalTotalObj[0][2] = " ";
					toalCellAlign[2] = 0;
					toalCellWidth[2] = 7;
					
					if(bean.getCheckBrn().equals("true")){
						finalTotalObj[0][totalCount] = " ";
						toalCellAlign[totalCount] = 0;
						toalCellWidth[totalCount] = 7;
						totalCount++;
					}
						
					if(bean.getCheckDept().equals("true")){
						finalTotalObj[0][totalCount] = " ";
						toalCellAlign[totalCount] = 0;
						toalCellWidth[totalCount] = 9;
						totalCount++;
					}
						 
					if(bean.getCheckEmpGrade().equals("true")){
						finalTotalObj[0][totalCount] = " ";
						toalCellAlign[totalCount] = 0;
						toalCellWidth[totalCount] = 7;
						totalCount++;
					}
						
					if(bean.getCheckAccount().equals("true")){
						finalTotalObj[0][totalCount] = " ";
						toalCellAlign[totalCount] = 0;
						toalCellWidth[totalCount] = 7;
						totalCount++;
					}
						
					if(bean.getCheckBank().equals("true")){
						finalTotalObj[0][totalCount] = " ";
						toalCellAlign[totalCount] = 0;
						toalCellWidth[totalCount] = 7;
						totalCount++;
					}
						
					
						
					finalTotalObj[0][totalCount] = " "; 
					toalCellAlign[totalCount] = 2;
					toalCellWidth[totalCount] = 7;
					totalCount++;
					
					
					finalTotalObj[0][totalCount] = formatter.format(Double.parseDouble(checkNullValue(String.valueOf(totalSingleOT))));
					toalCellAlign[totalCount] = 2;
					toalCellWidth[totalCount] = 7;
					totalCount++;
					
					
					finalTotalObj[0][totalCount] = getHH_MM(totalApprSingleMinuts); 
					toalCellAlign[totalCount] = 2;
					toalCellWidth[totalCount] = 7;
					totalCount++;
					
					if(bean.getDoubleOTflag().equals("YY")){
						finalTotalObj[0][totalCount] = formatter.format(Double.parseDouble(checkNullValue(String.valueOf(totalDoubleOT))));
						toalCellAlign[totalCount] = 2;
						toalCellWidth[totalCount] = 7;
						totalCount++;
						
						finalTotalObj[0][totalCount] = getHH_MM(totalApprDoubleMinuts); 
						toalCellAlign[totalCount] = 2;
						toalCellWidth[totalCount] = 7;
					}
					
					TableDataSet totalDataSet = new TableDataSet();
					totalDataSet.setData(finalTotalObj);
					totalDataSet.setCellWidth(toalCellWidth);
					totalDataSet.setCellAlignment(toalCellAlign);
					totalDataSet.setBodyFontStyle(1);
					totalDataSet.setBorderDetail(3);
					rg.addTableToDoc(totalDataSet);
					}
					
					
					String totalEmpQuery = " select distinct HRMS_OT_REGISTER.EMP_ID  FROM HRMS_OT_REGISTER "
						+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_OT_REGISTER.EMP_ID)	 "+
						" LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE) " 
						+" LEFT JOIN HRMS_SHIFT ON (HRMS_SHIFT.SHIFT_ID = HRMS_OT_REGISTER.OT_SHIFT_CODE)	" +
						" LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) " +
						" LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) " 
						+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)	"
						+" LEFT JOIN HRMS_BANK ON (HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_MICR_REGULAR) " 
						+" LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
						+" WHERE  OT_DATE >=TO_DATE('"+fromDate+"','DD-MM-YYYY')  "
						+" AND OT_DATE <=TO_DATE('"+toDate+"','DD-MM-YYYY') "
						+" AND HRMS_EMP_OFFC.EMP_DIV ="+divisionCode+" ";
						
						if (!bean.getCenterName().equals("")) {
							totalEmpQuery += " AND HRMS_EMP_OFFC.EMP_CENTER IN ("
									+ bean.getCenterId()+") ";
							
						}
						
						if (!bean.getDeptName().equals("")) {
							totalEmpQuery += " AND HRMS_EMP_OFFC.EMP_DEPT IN ("
									+ bean.getDeptCode()+") ";
							
						}
						
						if (!bean.getDesgName().equals("")) {
							totalEmpQuery += " AND HRMS_EMP_OFFC.EMP_RANK IN ("
									+ bean.getDesgCode()+") ";
							
						}
						
						if (!bean.getPaybillName().equals("")) {
							totalEmpQuery += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN ("
									+ bean.getPaybillCode()+") ";
							
						}
						

						if (!bean.getCostCenterName().equals("")) {
							totalEmpQuery += " AND HRMS_COST_CENTER.COST_CENTER_ID IN("
									+ bean.getCostCenterCode()+")";
							
						}
						//+" GROUP BY HRMS_OT_DTL.EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||EMP_LNAME ";

						//commentQuery += " ORDER BY HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ASC " ;
						

						/*
	 * execute select query and set the comment data
	 */
	Object[][] totalEmp = getSqlModel().getSingleResult(
			totalEmpQuery);
					
					
					
					Object totalNumberOfEmp[][] = new Object[1][1];
					totalNumberOfEmp[0][0] = "\n\nTotal No of Employees : "+String.valueOf(totalEmp.length);
					
					TableDataSet totalCountDataSet = new TableDataSet();
					totalCountDataSet.setData(totalNumberOfEmp);
					totalCountDataSet.setCellWidth(new int[]{100});
					totalCountDataSet.setCellAlignment(new int[]{0});
					totalCountDataSet.setBodyFontStyle(1);
					totalCountDataSet.setBorderDetail(0);
					rg.addTableToDoc(totalCountDataSet);
			}
			
			
			
			if (comment != null && comment.length > 0) {

				
			//	rg.seperatorLine();
			}else {
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "OT Register Details : No records available";

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

	public void getDoubleOtFlag(OtRegisterMISReport bean, HttpServletRequest request) {
		try {
			String query = " SELECT DOUBLE_OT_FLAG FROM HRMS_OT_CONF";

			Object[][] data = getSqlModel().getSingleResult(query);
				if(data!= null && data.length >0){
					bean.setDoubleOTflag("Y"+String.valueOf(data[0][0]));//Double OT Flag
					System.out.println("String.valueOf(data[0][0])===="+String.valueOf(data[0][0]));
				}
			
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void setDoubleOtFlagDtl(OtRegisterMISReport bean, HttpServletRequest request) {
					String query = " SELECT DOUBLE_OT_FLAG FROM HRMS_OT_CONF  WHERE DIV_CODE = " + bean.getDivisionID();
			
			Object[][] data = getSqlModel().getSingleResult(query);
			// setter
			if(data!= null && data.length >0){
				if(String.valueOf(data[0][0]).equals("Y")){
					bean.setDoubleOTflag("Y"+String.valueOf(data[0][0]));
				}else{
					bean.setDoubleOTflag("N"+String.valueOf(data[0][0]));
				}
				
			}
		
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

}
