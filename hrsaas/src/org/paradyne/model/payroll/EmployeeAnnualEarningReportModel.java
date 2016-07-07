package org.paradyne.model.payroll;

import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.EmployeeAnnualEarningReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import com.itextpdf.text.BaseColor;

/**
 * @author aa1383
 *
 */
public class EmployeeAnnualEarningReportModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(EmployeeAnnualEarningReportModel.class);
	/** This method is used to set up the variables for generating the report.
	 * @param annualBean
	 * @param response
	 */
	public void generateReport(EmployeeAnnualEarningReport annualBean, HttpServletRequest request, HttpServletResponse response, String reportPath) {
		try {
			ReportDataSet rds = new ReportDataSet();
			String type = annualBean.getReportType();
			rds.setReportType(type);
			String fileName = annualBean.getDivisionAbbrevation()+Utility.getRandomNumber(1000);
			String reportPathName=reportPath+fileName+"."+type;
			rds.setFileName(fileName);
			rds.setReportName("EMPLOYEE ANNUAL EARNING REPORT");
			rds.setPageOrientation("landscape");
			rds.setTotalColumns(15);
			rds.setPageSize("A4");
			rds.setShowPageNo(true);
			rds.setUserEmpId(annualBean.getUserEmpId());
			
			org.paradyne.lib.ireportV2.ReportGenerator rg=null;
			if(reportPath.equals("")){
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName+"."+type);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context, request);
				request.setAttribute("reportPath", reportPath+fileName+"."+type);
				request.setAttribute("fileName", fileName+"."+type);
				request.setAttribute("action", "EmployeeAnnualEarningReport_input.action");
			}
			rg = getReport(rg, annualBean);
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

	/** This method sets the actual data to be displayed in the report.
	 * @param rg - report generator instance
	 * @param annualBean - bean
	 * @return rg
	 */
	public org.paradyne.lib.ireportV2.ReportGenerator getReport(org.paradyne.lib.ireportV2.ReportGenerator  rg, EmployeeAnnualEarningReport annualBean) {
		try {
			Date date = new Date();
			
			/* Setting filter details */
			String filters = fetchFilters(annualBean);
			
			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][]{{filters}});
			filterData.setCellAlignment(new int[]{0});
			filterData.setCellWidth(new int[]{100});
			filterData.setCellColSpan(new int[]{15});
			filterData.setBlankRowsBelow(1);
			//filterData.setBodyFontStyle(1);
			rg.addTableToDoc(filterData);
			
			/* Employee data for each month calculation begins*/
			String earningType="";
			if (!annualBean.getEarningCompId().equals("")) {
				earningType="C";
			}
			
			 
			Object[][] employeeDataObj = fetchEmployeesByTypeCode(annualBean, earningType);
			
			HashMap empSalmap = fetchEmployeesSalaryMap(annualBean, earningType);
			
			HashMap empArrearsMap = fetchEmployeesArrearsMap(annualBean, earningType);
			
			if(annualBean.getSalaryCheck().equals("true")){
			
				Object [][] finalDataObj = null;
				String [] headers = new String[15];
				headers[0] = "Emp Id";
				headers[1] = "Employee Name";
				headers[14] = "Total";
				int [] cellWidth = new int[15];
				cellWidth[0] = 20;
				cellWidth[1] = 25;
				cellWidth[14] = 15;
				int [] cellAlignment = new int[15];
				cellAlignment[0] = 0;
				cellAlignment[1] = 0;
				cellAlignment[14] = 2;
				boolean[] cellWrap = new boolean[15];
				cellWrap[0] = true;
				cellWrap[1] = true;
				cellWrap[14] = true;
				int count=4;
				int count1=1;
				for (int i = 1; i <= 12; i++) {
					if(i<10){
						headers[i + 1] = Utility.month(count).substring(0, 3);
						count++;
					}else{
						headers[i + 1] = Utility.month(count1).substring(0, 3);
						count1++;
					}
					cellWidth[i + 1] = 10;
					cellAlignment[i + 1] = 2;
					cellWrap[i+1] = true;
				}
				int totalEmployees = 0;
				if (employeeDataObj != null && employeeDataObj.length > 0) {
					totalEmployees = employeeDataObj.length;
					finalDataObj = new Object[employeeDataObj.length][15];
					for (int i = 0; i < employeeDataObj.length; i++) {
						double total = 0.0;
						int mCount=10;
						int mCount1=1;
						finalDataObj[i][0] = String.valueOf(employeeDataObj[i][1]);
						finalDataObj[i][1] = String.valueOf(employeeDataObj[i][2]);
						for (int j = 1; j <= 12; j++) {
							double salAmount = 0.0;
							double arrearsAmount = 0.0;
							Object[][] amountObject = null;
							Object[][] arrearsObject = null;
							Object[][] consolidatedObject = null;
							try {
								amountObject = (Object[][]) empSalmap.get(String.valueOf(employeeDataObj[i][0]) + "#" + j);
								if(annualBean.getConsolidateCheck().equals("true")){
									arrearsObject = (Object[][]) empArrearsMap.get(String.valueOf(employeeDataObj[i][0]) + "#" + j);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							if(amountObject != null && amountObject.length > 0){
								for (int k = 0; k < amountObject.length; k++) {
									salAmount += Double.parseDouble(Utility.twoDecimals(String.valueOf(amountObject[k][1])));
								}
							}
							if(arrearsObject != null && arrearsObject.length > 0){
								for (int l = 0; l < arrearsObject.length; l++) {
									if(String.valueOf(arrearsObject[l][2]).equals("R")){
										arrearsAmount -= Double.parseDouble(Utility.twoDecimals(String.valueOf(arrearsObject[l][1])));
									} else {
										arrearsAmount += Double.parseDouble(Utility.twoDecimals(String.valueOf(arrearsObject[l][1])));
									}
								}
							}
							
							/*
							 if((amountObject != null && amountObject.length > 0) && (arrearsObject != null && arrearsObject.length > 0)){
								consolidatedObject = Utility.consolidateArrearsObject(amountObject, arrearsObject, 0, new int[]{1}, 2);
							}
							*/
							
							double totalAmount = 0.0;
							if(annualBean.getConsolidateCheck().equals("true")){
								totalAmount = salAmount + arrearsAmount;
							}else{
								totalAmount = salAmount;
							}
							
							if(j<4){
								finalDataObj[i][1 + mCount] = Utility.twoDecimals(totalAmount);
								total += totalAmount;
								mCount++;
							}else{
								finalDataObj[i][1 + mCount1] = Utility.twoDecimals(totalAmount);
								total += totalAmount;
								mCount1++;
							}
							
						}
						finalDataObj[i][14] = Utility.twoDecimals(total);
					}
					TableDataSet tableData = new TableDataSet();
					tableData.setHeader(headers);
					tableData.setHeaderBorderDetail(3);
					tableData.setHeaderTable(true);
					tableData.setHeaderFontStyle(1);
					tableData.setData(finalDataObj);
					tableData.setColumnSum(new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14});
					tableData.setCellAlignment(cellAlignment);
					tableData.setCellWidth(cellWidth);
					tableData.setBorderDetail(3);
					tableData.setCellNoWrap(cellWrap);
					rg.addTableToDoc(tableData);
					
					TableDataSet totalEmp = new TableDataSet();
					totalEmp.setData(new Object[][]{{"Total Employees : "+totalEmployees}});
					totalEmp.setCellAlignment(new int[]{0});
					totalEmp.setCellWidth(new int[]{100});
					totalEmp.setBorderDetail(0);
					totalEmp.setBodyFontStyle(1);
					totalEmp.setBodyBGColor(new BaseColor(200,200,200));
					totalEmp.setBlankRowsAbove(1);
					rg.addTableToDoc(totalEmp);
				} else {
					TableDataSet noDataTable = new TableDataSet();
					noDataTable.setData(new Object[][] {{"No data to display" }});
					noDataTable.setCellAlignment(new int[]{1});
					noDataTable.setCellWidth(new int[]{100});
					noDataTable.setBorderDetail(0);
					rg.addTableToDoc(noDataTable);
				}
			}
			
			if(annualBean.getArrearsCheck().equals("true") && annualBean.getConsolidateCheck().equals("false")){
				rg.addProperty(rg.PAGE_BREAK);
				rg = getArrearReport(rg, annualBean);
			}
			
			/*
			String generatedByQuery = " SELECT TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) FROM HRMS_EMP_OFFC "
				+ " WHERE HRMS_EMP_OFFC.EMP_ID = "+annualBean.getUserEmpId();

			Object[][] empDataObj = getSqlModel().getSingleResult(generatedByQuery);

			if(empDataObj!=null && empDataObj.length >0){
				TableDataSet generatedByData = new TableDataSet();
				generatedByData.setData(new Object[][]{{"Generated By : "+String.valueOf(empDataObj[0][0])+" on "+date.toString()}});
				generatedByData.setCellAlignment(new int[]{0});
				generatedByData.setCellWidth(new int[]{100});
				generatedByData.setBorderDetail(0);
				generatedByData.setBlankRowsAbove(1);
				rg.addTableToDoc(generatedByData);
				Vector lineVector=new Vector();
				lineVector.add(new BaseColor(255, 0, 0));
				lineVector.add(Rectangle.TOP);
				rg.addLine(lineVector);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

	public org.paradyne.lib.ireportV2.ReportGenerator getArrearReport(org.paradyne.lib.ireportV2.ReportGenerator  rg, EmployeeAnnualEarningReport annualBean) {
		
		/* Employee data for each month calculation begins*/
		
		try {
			TableDataSet generatedByData = new TableDataSet();
			generatedByData.setData(new Object[][] { { "Arrears : " } });
			generatedByData.setCellAlignment(new int[] { 0 });
			generatedByData.setCellWidth(new int[] { 100 });
			generatedByData.setBorderDetail(0);
			generatedByData.setBlankRowsAbove(1);
			generatedByData.setBodyFontStyle(1);
			rg.addTableToDoc(generatedByData);
			
			String earningType = "";
			if (!annualBean.getEarningCompId().equals("")) {
				earningType = "C";
			}
			Object[][] employeeDataObj = fetchEmployeesByTypeCode(annualBean, earningType);
			HashMap empArrearsMap = fetchEmployeesArrearsMap(annualBean, earningType);
			Object[][] finalDataObj = null;
			String[] headers = new String[15];
			headers[0] = "Emp Id";
			headers[1] = "Employee Name";
			headers[14] = "Total";
			int[] cellWidth = new int[15];
			cellWidth[0] = 20;
			cellWidth[1] = 25;
			cellWidth[14] = 15;
			int[] cellAlignment = new int[15];
			cellAlignment[0] = 0;
			cellAlignment[1] = 0;
			cellAlignment[14] = 2;
			boolean[] cellWrap = new boolean[15];
			cellWrap[0] = true;
			cellWrap[1] = true;
			cellWrap[14] = true;
			int count = 4;
			int count1 = 1;
			for (int i = 1; i <= 12; i++) {
				if (i < 10) {
					headers[i + 1] = Utility.month(count).substring(0, 3);
					count++;
				} else {
					headers[i + 1] = Utility.month(count1).substring(0, 3);
					count1++;
				}
				cellWrap[i + 1] = true;
				cellWidth[i + 1] = 10;
				cellAlignment[i + 1] = 2;
			}
			int totalEmployees = 0;
			if (employeeDataObj != null && employeeDataObj.length > 0) {
				totalEmployees = employeeDataObj.length;
				finalDataObj = new Object[employeeDataObj.length][15];
				for (int i = 0; i < employeeDataObj.length; i++) {
					double total = 0.0;
					int mCount = 10;
					int mCount1 = 1;
					finalDataObj[i][0] = String.valueOf(employeeDataObj[i][1]);
					finalDataObj[i][1] = String.valueOf(employeeDataObj[i][2]);
					for (int j = 1; j <= 12; j++) {
						double arrearsAmount = 0.0;
						Object[][] arrearsObject = null;
						try {
							arrearsObject = (Object[][]) empArrearsMap.get(String.valueOf(employeeDataObj[i][0])
											+ "#" + j);
						} catch (Exception e) {
							e.printStackTrace();
						}

						if (arrearsObject != null && arrearsObject.length > 0) {
							for (int l = 0; l < arrearsObject.length; l++) {
								if(String.valueOf(arrearsObject[l][2]).equals("R")){
									arrearsAmount -= Double.parseDouble(Utility.twoDecimals(String.valueOf(arrearsObject[l][1])));
								} else {
									arrearsAmount += Double.parseDouble(Utility.twoDecimals(String.valueOf(arrearsObject[l][1])));
								}
							}
						}

						if (j < 4) {
							finalDataObj[i][1 + mCount] = Utility
									.twoDecimals(arrearsAmount);
							total += arrearsAmount;
							mCount++;
						} else {
							finalDataObj[i][1 + mCount1] = Utility
									.twoDecimals(arrearsAmount);
							total += arrearsAmount;
							mCount1++;
						}

					}
					finalDataObj[i][14] = Utility.twoDecimals(total);
				}
				TableDataSet tableData = new TableDataSet();
				tableData.setHeader(headers);
				tableData.setHeaderBorderDetail(3);
				tableData.setHeaderTable(true);
				tableData.setData(finalDataObj);
				tableData.setColumnSum(new int[] { 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 });
				tableData.setCellAlignment(cellAlignment);
				tableData.setCellWidth(cellWidth);
				tableData.setCellNoWrap(cellWrap);
				tableData.setBorderDetail(3);
				rg.addTableToDoc(tableData);
				
				TableDataSet totalEmp = new TableDataSet();
				totalEmp.setData(new Object[][]{{"Total Employees : "+totalEmployees}});
				totalEmp.setCellAlignment(new int[]{0});
				totalEmp.setCellWidth(new int[]{100});
				totalEmp.setBorderDetail(0);
				totalEmp.setBodyBGColor(new BaseColor(200,200,200));
				totalEmp.setBodyFontStyle(1);
				totalEmp.setBlankRowsAbove(1);
				rg.addTableToDoc(totalEmp);
			}else {
				TableDataSet noDataTable = new TableDataSet();
				noDataTable.setData(new Object[][] {{"No arrears data to display" }});
				noDataTable.setCellAlignment(new int[]{1});
				noDataTable.setCellWidth(new int[]{100});
				noDataTable.setBorderDetail(0);
				rg.addTableToDoc(noDataTable);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}
	/** This method returns the object of employees.
	 * @param annualBean
	 * @param earningType
	 * @return Object
	 */
	public Object[][] fetchEmployeesByTypeCode(EmployeeAnnualEarningReport annualBean, String earningType) {
		Object[][] empDataObj = null;
		String query = "";
		
		if (!annualBean.getDivisionId().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_DIV IN ("+ annualBean.getDivisionId()+ ")";
		}
		if (!annualBean.getBranchId().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_CENTER IN (" + annualBean.getBranchId()+ ")";
		}
		if (!annualBean.getPaybillId().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN (" + annualBean.getPaybillId()+ ")";
		}
		if (!annualBean.getDepartmentId().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_DEPT IN (" + annualBean.getDepartmentId()+ ")";
		}
		if (!annualBean.getEmpId().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_ID IN (" + annualBean.getEmpId() + ")";
		}
		if (!annualBean.getEmpTypeId().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_TYPE IN (" + annualBean.getEmpTypeId()+ ")";
		}
		
		if (earningType.equals("C")) {
			query = "SELECT DISTINCT HRMS_SAL_CREDITS_"+annualBean.getFromYear() + ".EMP_ID, NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '), TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME) FROM HRMS_SAL_CREDITS_"+annualBean.getFromYear()
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_CREDITS_" + annualBean.getFromYear() + ".EMP_ID) "
				+ " WHERE HRMS_SAL_CREDITS_"+annualBean.getFromYear() + ".SAL_CREDIT_CODE=" + annualBean.getEarningCompId() + " AND HRMS_SAL_CREDITS_"+annualBean.getFromYear() + ".SAL_YEAR=" + annualBean.getFromYear() + " AND HRMS_SAL_CREDITS_" + annualBean.getFromYear() + ".SAL_MONTH IN( 4,5,6,7,8,9,10,11,12 ) "+query
				+ " UNION "
				+ " SELECT DISTINCT HRMS_SAL_CREDITS_"+annualBean.getToYear() + ".EMP_ID, NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '), TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME) FROM HRMS_SAL_CREDITS_"+annualBean.getToYear()
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_CREDITS_" + annualBean.getToYear() + ".EMP_ID) "
				+ " WHERE HRMS_SAL_CREDITS_"+annualBean.getToYear() + ".SAL_CREDIT_CODE=" + annualBean.getEarningCompId() + " AND HRMS_SAL_CREDITS_"+annualBean.getToYear() + ".SAL_YEAR=" + annualBean.getToYear() + " AND HRMS_SAL_CREDITS_" + annualBean.getToYear() + ".SAL_MONTH IN(1, 2, 3)"+query;
		} else {
			query = "SELECT DISTINCT HRMS_SAL_DEBITS_"+annualBean.getFromYear()+".EMP_ID, NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '), TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME) FROM HRMS_SAL_DEBITS_"+annualBean.getFromYear()
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"+annualBean.getFromYear()+".EMP_ID) "
				+ " WHERE HRMS_SAL_DEBITS_"+annualBean.getFromYear()+".SAL_DEBIT_CODE=" + annualBean.getDeductionCompId() + " AND HRMS_SAL_DEBITS_"+annualBean.getFromYear()+".SAL_YEAR=" + annualBean.getFromYear() + " AND HRMS_SAL_DEBITS_"+ annualBean.getFromYear()+ ".SAL_MONTH IN( 4,5,6,7,8,9,10,11,12 ) "+query
				+ " UNION "
				+ " SELECT DISTINCT HRMS_SAL_DEBITS_"+annualBean.getToYear()+".EMP_ID, NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '), TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME) FROM HRMS_SAL_DEBITS_"+annualBean.getToYear()
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"+annualBean.getToYear()+".EMP_ID) "
				+ " WHERE HRMS_SAL_DEBITS_"+annualBean.getToYear()+".SAL_DEBIT_CODE=" + annualBean.getDeductionCompId() + " AND HRMS_SAL_DEBITS_"+annualBean.getToYear()+".SAL_YEAR=" + annualBean.getToYear()+ " AND HRMS_SAL_DEBITS_"+ annualBean.getToYear()+ ".SAL_MONTH IN(1, 2, 3)"+query;
		}
		
		empDataObj= getSqlModel().getSingleResult(query);
		return empDataObj;
	}
	
	/**This method returns the map with key as empId#month.
	 * @param annualBean
	 * @param earningType
	 * @return map
	 */
	public HashMap fetchEmployeesSalaryMap(EmployeeAnnualEarningReport annualBean, String earningType){
		HashMap empMap = new HashMap();
		String query ="";
		if (!annualBean.getDivisionId().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_DIV IN ("+ annualBean.getDivisionId()+ ")";
		}
		if (!annualBean.getBranchId().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_CENTER IN (" + annualBean.getBranchId()+ ")";
		}
		if (!annualBean.getPaybillId().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN (" + annualBean.getPaybillId()+ ")";
		}
		if (!annualBean.getDepartmentId().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_DEPT IN (" + annualBean.getDepartmentId()+ ")";
		}
		if (!annualBean.getEmpId().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_ID IN (" + annualBean.getEmpId() + ")";
		}
		if (!annualBean.getEmpTypeId().equals("")) {
			query += " AND HRMS_EMP_OFFC.EMP_TYPE IN (" + annualBean.getEmpTypeId()+ ")";
		}
		
		if (earningType.equals("C")) {
		query = "SELECT HRMS_SAL_CREDITS_"+annualBean.getFromYear()+".EMP_ID||'#'||HRMS_SAL_CREDITS_"+annualBean.getFromYear()+".SAL_MONTH," 
			+ " NVL(SAL_AMOUNT,0) FROM HRMS_SAL_CREDITS_"+annualBean.getFromYear() 
			+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_CREDITS_"+annualBean.getFromYear()+".EMP_ID)" 
			+ " WHERE HRMS_SAL_CREDITS_"+annualBean.getFromYear()+".SAL_CREDIT_CODE="+annualBean.getEarningCompId()+" AND HRMS_SAL_CREDITS_"+annualBean.getFromYear()+".SAL_YEAR="+annualBean.getFromYear()+ " AND HRMS_SAL_CREDITS_" + annualBean.getFromYear() + ".SAL_MONTH IN(4, 5, 6, 7, 8, 9, 10, 11, 12) "+query
			+ " UNION "
			+ " SELECT HRMS_SAL_CREDITS_"+annualBean.getToYear()+".EMP_ID||'#'||HRMS_SAL_CREDITS_"+annualBean.getToYear()+".SAL_MONTH," 
			+ " NVL(SAL_AMOUNT,0) FROM HRMS_SAL_CREDITS_"+annualBean.getToYear() 
			+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_CREDITS_"+annualBean.getToYear()+".EMP_ID)" 
			+ " WHERE HRMS_SAL_CREDITS_"+annualBean.getToYear()+".SAL_CREDIT_CODE="+annualBean.getEarningCompId()+" AND HRMS_SAL_CREDITS_"+annualBean.getToYear()+".SAL_YEAR="+annualBean.getToYear()+ " AND HRMS_SAL_CREDITS_" + annualBean.getToYear() + ".SAL_MONTH IN(1, 2, 3) "+query;;
		} else {
			query = "SELECT HRMS_SAL_DEBITS_"+annualBean.getFromYear()+".EMP_ID||'#'||HRMS_SAL_DEBITS_"+annualBean.getFromYear()+".SAL_MONTH," 
			+ " NVL(SAL_AMOUNT,0) FROM HRMS_SAL_DEBITS_"+annualBean.getFromYear() 
			+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"+annualBean.getFromYear()+".EMP_ID)" 
			+ " WHERE SAL_DEBIT_CODE="+annualBean.getDeductionCompId()+" AND SAL_YEAR="+annualBean.getFromYear()+ " AND HRMS_SAL_DEBITS_"+ annualBean.getFromYear()+ ".SAL_MONTH IN( 4,5,6,7,8,9,10,11,12 ) "+query
			+ " UNION "
			+ " SELECT HRMS_SAL_DEBITS_"+annualBean.getToYear()+".EMP_ID||'#'||HRMS_SAL_DEBITS_"+annualBean.getToYear()+".SAL_MONTH," 
			+ " NVL(SAL_AMOUNT,0) FROM HRMS_SAL_DEBITS_"+annualBean.getToYear() 
			+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"+annualBean.getToYear()+".EMP_ID)" 
			+ " WHERE HRMS_SAL_DEBITS_"+annualBean.getToYear()+".SAL_DEBIT_CODE="+annualBean.getDeductionCompId()+" AND HRMS_SAL_DEBITS_"+annualBean.getToYear()+".SAL_YEAR="+annualBean.getToYear()+ " AND HRMS_SAL_DEBITS_"+ annualBean.getToYear()+ ".SAL_MONTH IN(1, 2, 3) "+query;
		}

		empMap = getSqlModel().getSingleResultMap(query, 0, 2);
		return empMap;
	}
	
	/**This method returns the map with key as empId#month.
	 * @param annualBean
	 * @param earningType
	 * @return map
	 */
	public HashMap fetchEmployeesArrearsMap(EmployeeAnnualEarningReport annualBean, String earningType){
		HashMap empCreditDebitMap = new HashMap();
		String query ="";
		if (earningType.equals("C")) {
			if (!annualBean.getDivisionId().equals("")) {
				query += " AND HRMS_EMP_OFFC.EMP_DIV IN ("+ annualBean.getDivisionId()+ ")";
			}
			if (!annualBean.getBranchId().equals("")) {
				query += " AND HRMS_EMP_OFFC.EMP_CENTER IN (" + annualBean.getBranchId()+ ")";
			}
			if (!annualBean.getPaybillId().equals("")) {
				query += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN (" + annualBean.getPaybillId()+ ")";
			}
			if (!annualBean.getDepartmentId().equals("")) {
				query += " AND HRMS_EMP_OFFC.EMP_DEPT IN (" + annualBean.getDepartmentId()+ ")";
			}
			if (!annualBean.getEmpId().equals("")) {
				query += " AND HRMS_EMP_OFFC.EMP_ID IN (" + annualBean.getEmpId() + ")";
			}
			if (!annualBean.getEmpTypeId().equals("")) {
				query += " AND HRMS_EMP_OFFC.EMP_TYPE IN (" + annualBean.getEmpTypeId()+ ")";
			}
			
		query = "SELECT HRMS_ARREARS_CREDIT_"+annualBean.getFromYear()+".ARREARS_EMP_ID||'#'||HRMS_ARREARS_CREDIT_"+annualBean.getFromYear()+".ARREARS_MONTH, NVL(HRMS_ARREARS_CREDIT_"+annualBean.getFromYear()+".ARREARS_AMT,0), NVL(ARREARS_PAY_TYPE,'A')"
			+ " FROM HRMS_ARREARS_CREDIT_"+annualBean.getFromYear()
			+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_CREDIT_"+annualBean.getFromYear()+".ARREARS_EMP_ID)"
			+ " WHERE ARREARS_CREDIT_CODE="+annualBean.getEarningCompId()+" AND ARREARS_YEAR="+annualBean.getFromYear()
			+ " UNION "
			+ " SELECT HRMS_ARREARS_CREDIT_"+annualBean.getToYear()+".ARREARS_EMP_ID||'#'||HRMS_ARREARS_CREDIT_"+annualBean.getToYear()+".ARREARS_MONTH, NVL(HRMS_ARREARS_CREDIT_"+annualBean.getToYear()+".ARREARS_AMT,0), NVL(ARREARS_PAY_TYPE,'A')"
			+ " FROM HRMS_ARREARS_CREDIT_"+annualBean.getToYear()
			+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_CREDIT_"+annualBean.getToYear()+".ARREARS_EMP_ID)"
			+ " WHERE ARREARS_CREDIT_CODE="+annualBean.getEarningCompId()+" AND ARREARS_YEAR="+annualBean.getToYear();
		} else {
			query = "SELECT HRMS_ARREARS_DEBIT_"+annualBean.getFromYear()+".ARREARS_EMP_ID||'#'||HRMS_ARREARS_DEBIT_"+annualBean.getFromYear()+".ARREARS_MONTH, NVL(HRMS_ARREARS_DEBIT_"+annualBean.getFromYear()+".ARREARS_AMT,0),NVL(ARREARS_PAY_TYPE,'A') "
			+ " FROM HRMS_ARREARS_DEBIT_"+annualBean.getFromYear()
			+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_DEBIT_"+annualBean.getFromYear()+".ARREARS_EMP_ID)"
			+ " WHERE ARREARS_DEBIT_CODE="+annualBean.getDeductionCompId()+" AND ARREARS_YEAR="+annualBean.getFromYear()+ " AND HRMS_ARREARS_DEBIT_" + annualBean.getFromYear() + ".ARREARS_MONTH IN (4, 5, 6, 7, 8, 9, 10, 11, 12) "+query
			+ " UNION "
			+ " SELECT HRMS_ARREARS_DEBIT_"+annualBean.getToYear()+".ARREARS_EMP_ID||'#'||HRMS_ARREARS_DEBIT_"+annualBean.getToYear()+".ARREARS_MONTH, NVL(HRMS_ARREARS_DEBIT_"+annualBean.getToYear()+".ARREARS_AMT,0), NVL(ARREARS_PAY_TYPE,'A') "
			+ " FROM HRMS_ARREARS_DEBIT_"+annualBean.getToYear()
			+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_DEBIT_"+annualBean.getToYear()+".ARREARS_EMP_ID)"
			+ " WHERE ARREARS_DEBIT_CODE="+annualBean.getDeductionCompId()+" AND ARREARS_YEAR="+annualBean.getToYear()+ " AND HRMS_ARREARS_DEBIT_" + annualBean.getToYear() + ".ARREARS_MONTH IN (1, 2, 3) "+query;
		}
		
		empCreditDebitMap = getSqlModel().getSingleResultMap(query, 0, 2);
		
		return empCreditDebitMap;
	}
	
	private String fetchFilters(EmployeeAnnualEarningReport annualBean) {
		String fromYr = annualBean.getFromYear();
		String toYr = annualBean.getToYear();
		
		String filters = "Report Period : " + fromYr + " - " + toYr;
		if (!annualBean.getDivisionId().equals("")) {
			filters += "\n\nDivision : " + annualBean.getDivisionName();
		}
		if (!annualBean.getBranchId().equals("")) {
			filters += "\n\nBranch : " + annualBean.getBranchName();
		}
		if (!annualBean.getDepartmentId().equals("")) {
			filters += "\n\nDepartment : " + annualBean.getDepartmentName();
		}
		if (!annualBean.getPaybillId().equals("")) {
			filters += "\n\nPaybill : " + annualBean.getPaybillName();
		}
		if (!annualBean.getEmpTypeId().equals("")) {
			filters += "\n\nEmployee Type : " + annualBean.getEmpTypeName();
		}
		if (!annualBean.getEarningCompId().equals("")) {
			filters += "\n\nEarning Component : " + annualBean.getEarningCompName();
		} else {
			filters += "\n\nDeduction Component : " + annualBean.getDeductionCompName();
		}
		return filters;
	}
}