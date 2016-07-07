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

import org.paradyne.bean.payroll.ot.OtMISReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;

/**
 * Created on 8th Mar 2012.
 * 
 * @author aa1385
 * 
 */
public class OtMISReportModel extends ModelBase {
	/** formatter. */
	NumberFormat formatter = new DecimalFormat("#0.00");
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(OtMISReportModel.class);
	
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
	
	
	public void getReport(OtMISReport bean,
			HttpServletResponse response, HttpServletRequest request,
			String reportPath) {

		try {
			ReportDataSet rds = new ReportDataSet();
			final String month = bean.getMonth(); // month
			final String year = bean.getYear(); // year
			String type = bean.getReport();
			rds.setReportType(type);
			String fileName = "OT Calculation MIS Report" + Utility.getRandomNumber(1000);
			String reportPathName = reportPath + fileName + "." + type;
			rds.setFileName(fileName);
			rds.setReportName("OT Calculation MIS Report");
			rds.setUserEmpId(bean.getUserEmpId());
			rds.setPageSize("A4");
			rds.setPageSize("TABLOID");
			rds.setTotalColumns(10);
			rds.setShowPageNo(true);
			
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
						"/ot/OtMISReport_input.action");
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
			OtMISReport bean) {
		try {
			String month = bean.getMonth();
			String year = bean.getYear();
			/* Setting filter details */
			String filters = "Period: " + Utility.month(Integer.parseInt(month)) + " - "+ year + "";
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

			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][] { { filters } });
			filterData.setCellAlignment(new int[] { 0 });
			filterData.setCellWidth(new int[] { 100 });
			filterData.setCellColSpan(new int[]{10});
			filterData.setCellNoWrap(new boolean[]{false});
			filterData.setBorder(false);
			rg.addTableToDoc(filterData);
			
			String divisionCode =  bean.getDivisionID();
			/*
			 * select query to select approver comment data from
			 * hrms_loan_path
			 */
			String commentQuery = "SELECT HRMS_OT_DTL.EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||EMP_LNAME," +
								" HRMS_CENTER.CENTER_NAME, HRMS_DEPT.DEPT_NAME, " +
								" NVL(HRMS_CADRE.CADRE_NAME,'') , HRMS_SALARY_MISC.SAL_ACCNO_REGULAR, " + 
								" HRMS_BANK.BANK_NAME,  " +
								" SINGLE_OT,DOUBLE_OT,TOTAL_OT,NVL(SINGLE_OT_AMT,0.0), " +
								" NVL(DOUBLE_OT_AMT,0.0), TOTAL_OT,NVL(OT_TDS_AMT,0.0),NVL(TOTAL_OT_AMT,0.0) "
								+" FROM HRMS_OT_DTL "
								+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_OT_DTL.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"+
								" LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE) " +
								" LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) " +
								" LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) " 
								+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)	"
								+" LEFT JOIN HRMS_BANK ON (HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_MICR_REGULAR) " 
								+" LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
								+" WHERE  HRMS_OT_DTL.MONTH="+month+" AND HRMS_OT_DTL.YEAR="+year+" "
								+" AND HRMS_EMP_OFFC.EMP_DIV ="+divisionCode+" ";
								
								if (!bean.getCenterName().equals("")) {
									commentQuery += " AND HRMS_EMP_OFFC.EMP_CENTER IN ("
											+ bean.getCenterId()+") ";
								}
								
								if (!bean.getDeptName().equals("")) {
									commentQuery += " AND HRMS_EMP_OFFC.EMP_DEPT IN ("
											+ bean.getDeptCode()+") ";
								}
								if (!bean.getDesgName().equals("")) {
									commentQuery += " AND HRMS_EMP_OFFC.EMP_RANK IN ("
											+ bean.getDesgCode()+") ";
								}
								if (!bean.getPaybillName().equals("")) {
									commentQuery += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN ("
											+ bean.getPaybillCode()+") ";
								}
								if (!bean.getCostCenterName().equals("")) {
									commentQuery += " AND HRMS_COST_CENTER.COST_CENTER_ID IN("
											+ bean.getCostCenterCode()+")";
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
			int headerCount = 2;
			
		//	header[0] = "Sr. No.";
			header[0] = "Employee ID";
			header[1] = "Employee Name";
			
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
			header[headerCount] = "Single OT Hours";
			headerCount++;
			
			if(bean.getDoubleOTflag().equals("YY")){
				header[headerCount] = "Double OT Hours";
				headerCount++;
			}
			
			header[headerCount] = "Total OT ";
			headerCount++;
			
			header[headerCount] = "Total OT Amount";
			headerCount++;
						
			header[headerCount] = "TDS";
			headerCount++;
			header[headerCount] = "Net OT Amount";
			headerCount++;

			int[] bcellAlign = new int[headerObjCount];
			int[] bcellWidth = new int[headerObjCount];
			int allignWidthCount = 2;
			bcellAlign[0] = 0;
			bcellWidth[0] = 7;
			bcellAlign[1] = 0;
			bcellWidth[1] = 13;
				
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
				
			bcellAlign[allignWidthCount] = 2;
			bcellWidth[allignWidthCount] = 7;
			allignWidthCount++;
			
			if(bean.getDoubleOTflag().equals("YY")){
			bcellAlign[allignWidthCount] = 2;
			bcellWidth[allignWidthCount] = 7;
			allignWidthCount++;
			}
			
			bcellAlign[allignWidthCount] = 2;
			bcellWidth[allignWidthCount] = 7;
			allignWidthCount++;
			
			bcellAlign[allignWidthCount] = 2;
			bcellWidth[allignWidthCount] = 7;
			allignWidthCount++;
			
			bcellAlign[allignWidthCount] = 2;
			bcellWidth[allignWidthCount] = 7;
			allignWidthCount++;
				
			bcellAlign[allignWidthCount] = 2;
			bcellWidth[allignWidthCount] = 7;
			
			Object[][] objTotalTabularData = new Object[comment.length][headerCount];
			
			if (comment != null && comment.length > 0) {
				double valueTotal = 0.0;
				for (int j = 0; j < comment.length; j++) {
					//objTotalTabularData[j][0] = countSr++;// token
					objTotalTabularData[j][0] = comment[j][1]; // emp token
					objTotalTabularData[j][1] = comment[j][2]; // emp name
					
					int headerDataObjCount = 2;
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
					
					objTotalTabularData[j][headerDataObjCount] = String.valueOf(comment[j][8]); // Single OT Hours
					headerDataObjCount++;
					
					if(bean.getDoubleOTflag().equals("YY")){
					objTotalTabularData[j][headerDataObjCount] = String.valueOf(comment[j][9]); // Double OT Hours
					headerDataObjCount++;
					}
					
					objTotalTabularData[j][headerDataObjCount] = String.valueOf(comment[j][13]);//Total OT
					headerDataObjCount++;
					
					objTotalTabularData[j][headerDataObjCount] = formatter.format(Double.parseDouble(checkNull(String.valueOf(comment[j][15]))));//Total OT Amount
					headerDataObjCount++;
					
					objTotalTabularData[j][headerDataObjCount] = formatter.format(Double.parseDouble(checkNull(String.valueOf(comment[j][14]))));//TDS 
					headerDataObjCount++;
					
					objTotalTabularData[j][headerDataObjCount] = formatter.format(Double.parseDouble(checkNullValue(String.valueOf(comment[j][15])))-Double.parseDouble(checkNullValue(String.valueOf(comment[j][14])))); // NET OT AMOUNT
					
					// Total NET OT Amount
					valueTotal = valueTotal
									+ Double
											.parseDouble(checkNullValue(String
													.valueOf(objTotalTabularData[j][headerDataObjCount])));
					headerDataObjCount++;
					
				}
				
				Object[][] apprDetails = new Object[1][4];// new-------------->
				apprDetails[0][0] = "OT Calculation Details :";
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
				rg.addTableToDoc(branchDetails);
				
				String totQuery = " SELECT HRMS_OT_DTL.SINGLE_OT, 	"
						+"  HRMS_OT_DTL.DOUBLE_OT, " 
						+"  HRMS_OT_DTL.TOTAL_OT ,"
						+"  NVL(HRMS_OT_DTL.OT_TDS_AMT,0.0),NVL(HRMS_OT_DTL.TOTAL_OT_AMT,0.0) "
						+"  FROM HRMS_OT_DTL "
						+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_OT_DTL.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"+
						" LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE) " +
						" LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) " +
						" LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) " 
						+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)	"
						+" LEFT JOIN HRMS_BANK ON (HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_MICR_REGULAR) " 
						+" LEFT JOIN HRMS_COST_CENTER ON(HRMS_COST_CENTER.COST_CENTER_ID=HRMS_SALARY_MISC.COST_CENTER_ID)"
						
				+" WHERE  HRMS_OT_DTL.MONTH="+month+" AND HRMS_OT_DTL.YEAR="+year+" "
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
				if (!bean.getManagerName().equals("")) {
					commentQuery += " AND HRMS_EMP_OFFC.EMP_REPORTING_OFFICER IN ("
							+ bean.getManagerID()+") ";
					System.out.println("+bean.getManagerID() ="
							+ bean.getManagerID());
				}
				Object[][] totalDataObject = getSqlModel().getSingleResult(
						totQuery);

					Object finalTotalObj[][] = new Object[1][headerCount];
					
					int[] toalCellAlign = new int[headerCount];
					int[] toalCellWidth = new int[headerCount];
					
					if (totalDataObject != null && totalDataObject.length > 0) {
					
						int totalSingleMinuts = 0;
						int totalDoubleMinuts = 0;
						int totalMinuts = 0;
						double totalOTAmt = 0;
						double totalTDS = 0;
						for (int i = 0; i < totalDataObject.length; i++) {
							try {
								totalSingleMinuts+= getMinute(getHH_MM(String.valueOf(totalDataObject[i][0])));
								totalDoubleMinuts+= getMinute(getHH_MM(String.valueOf(totalDataObject[i][1])));
								totalMinuts+= getMinute(getHH_MM(String.valueOf(totalDataObject[i][2])));
								totalOTAmt+= Double.parseDouble(String.valueOf(totalDataObject[i][4]));
								totalTDS+= Double.parseDouble(String.valueOf(totalDataObject[i][3]));
								
							} catch (Exception e) {
								e.printStackTrace();
								////totalMinuts = totalMinuts;
							}
						}
					
					
					int totalCount = 2;
					finalTotalObj[0][0] = "Total ";
					toalCellAlign[0] = 0;
					toalCellWidth[0] = 7;
					finalTotalObj[0][1] = " ";
					toalCellAlign[1] = 0;
					toalCellWidth[1] = 13;
					
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
						
					
						
					finalTotalObj[0][totalCount] = getHH_MM(totalSingleMinuts); 
					toalCellAlign[totalCount] = 2;
					toalCellWidth[totalCount] = 7;
					totalCount++;
					
					if(bean.getDoubleOTflag().equals("YY")){
						finalTotalObj[0][totalCount] = getHH_MM(totalDoubleMinuts);
						toalCellAlign[totalCount] = 2;
						toalCellWidth[totalCount] = 7;
						totalCount++;
					}
					
					finalTotalObj[0][totalCount] = getHH_MM(totalMinuts);
					toalCellAlign[totalCount] = 2;
					toalCellWidth[totalCount] = 7;
					totalCount++;
					
					finalTotalObj[0][totalCount] = formatter.format(Double.parseDouble(checkNullValue(String.valueOf(totalOTAmt))));
					toalCellAlign[totalCount] = 2;
					toalCellWidth[totalCount] = 7;
					totalCount++;
					
					finalTotalObj[0][totalCount] = formatter.format(Double.parseDouble(checkNullValue(String.valueOf(totalTDS))));
					toalCellAlign[totalCount] = 2;
					toalCellWidth[totalCount] = 7;
					totalCount++;
					
					finalTotalObj[0][totalCount] = formatter.format(Double.parseDouble(String.valueOf(valueTotal)));
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
					
					
					Object totalNumberOfEmp[][] = new Object[1][1];
					totalNumberOfEmp[0][0] = "\n\nTotal No of Employees : "+String.valueOf(comment.length);
					
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
				noDataObj[0][0] = "OT Calculation Details : No records available";
				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 0 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBorder(false);
				rg.addTableToDoc(noData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

	public void getDoubleOtFlag(OtMISReport bean, HttpServletRequest request) {
		try {
			String query = " SELECT DOUBLE_OT_FLAG FROM HRMS_OT_CONF";

			Object[][] data = getSqlModel().getSingleResult(query);
				if(data!= null && data.length >0){
					bean.setDoubleOTflag("Y"+String.valueOf(data[0][0]));//Double OT Flag
				}
			
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setDoubleOtFlagDtl(OtMISReport bean, HttpServletRequest request) {
			String query = " SELECT DOUBLE_OT_FLAG FROM HRMS_OT_CONF  WHERE DIV_CODE = " + bean.getDivisionID();
			Object[][] data = getSqlModel().getSingleResult(query);
			// setter
			if(data!= null && data.length >0){
				if(String.valueOf(data[0][0]).equals("Y")){
					bean.setDoubleOTflag("Y"+String.valueOf(data[0][0]));
				}else{
					bean.setDoubleOTflag("Y"+String.valueOf(data[0][0]));
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
