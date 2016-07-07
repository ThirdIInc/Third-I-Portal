package org.paradyne.model.payroll;

import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.EmployeeMonthlyEarningReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;

public class EmployeeMonthlyEarningReportModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(EmployeeMonthlyEarningReportModel.class);

	public void generateReport(EmployeeMonthlyEarningReport earningBean, HttpServletRequest request, HttpServletResponse response, String reportPath) {
		try {
			ReportDataSet rds = new ReportDataSet();
			String type = earningBean.getReportType();
			rds.setReportType(type);
			String fileName = earningBean.getDivisionAbbrevation()+Utility.getRandomNumber(1000);
			String reportPathName=reportPath+fileName+"."+type;
			rds.setFileName(fileName);
			rds.setReportName("EMPLOYEE MONTHLY EARNING REPORT");
			rds.setPageSize("A4");
			rds.setMarginBottom(25f);
			rds.setMarginLeft(25f);
			rds.setMarginRight(25f);
			rds.setShowPageNo(true);
			rds.setMarginTop(25f);
			org.paradyne.lib.ireportV2.ReportGenerator rg=null;

			if(reportPath.equals("")){
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName+"."+type);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context);
				request.setAttribute("reportPath", reportPath+fileName+"."+type);
				request.setAttribute("action", "EmployeeMonthlyEarningReport_input.action");
			}
			rg = getReport(rg, earningBean);
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

	private org.paradyne.lib.ireportV2.ReportGenerator getReport(org.paradyne.lib.ireportV2.ReportGenerator rg, EmployeeMonthlyEarningReport earningBean) {
		try {
			Date date = new Date();

			String month = earningBean.getEarningMonth();
			String year = earningBean.getEarningYear();

			TableDataSet titleData = new TableDataSet();
			titleData.setData(new Object[][] {{ "Employeewise Monthly Earning/Deduction"}});
			titleData.setCellAlignment(new int[] {  1 });
			titleData.setCellWidth(new int[] { 100 });
			titleData.setBodyFontDetails(Font.FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(0, 0, 0));
			titleData.setBlankRowsBelow(1);
			titleData.setBorder(false);
			rg.addTableToDoc(titleData);
			//rg.createHeader(titleData);

			/* Setting filter details */
			String filters = "Report Period : "+Utility.month(Integer.parseInt(month))+" - "+year;

			if(!earningBean.getDivisionId().equals("")){
				filters+="\nDivision : "+earningBean.getDivisionName();
			}
			if(!earningBean.getBranchId().equals("")){
				filters+="\nBranch : "+earningBean.getBranchName();
			}
			if(!earningBean.getDepartmentId().equals("")){
				filters+="\nDepartment : "+earningBean.getDepartmentName();
			}
			if(!earningBean.getPaybillId().equals("")){
				filters+="\nPaybill : "+earningBean.getPaybillName();
			}
			if(!earningBean.getEmpTypeId().equals("")){
				filters+="\nEmployee Type : "+earningBean.getEmpTypeName();
			}

			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][]{{filters}});
			filterData.setCellAlignment(new int[]{0});
			filterData.setCellWidth(new int[]{100});
			filterData.setBodyFontDetails(Font.FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(0, 0, 0));
			filterData.setBorder(false);
			filterData.setBlankRowsBelow(1);
			rg.addTableToDoc(filterData);


			/* Setting credits & debits details*/
			Object[][] creditsObj = null;
			Object[][] debitsObj = null;

			String creditsQuery = "SELECT DISTINCT NVL(HRMS_CREDIT_HEAD.CREDIT_NAME,' '), HRMS_CREDIT_HEAD.CREDIT_CODE FROM HRMS_CREDIT_HEAD "
				+ " WHERE HRMS_CREDIT_HEAD.CREDIT_CODE IN ("
				+ earningBean.getEarningCompId()
				+ ")" + "ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
			
			String debitsQuery = "SELECT DISTINCT NVL(HRMS_DEBIT_HEAD.DEBIT_NAME,' '), HRMS_DEBIT_HEAD.DEBIT_CODE FROM HRMS_DEBIT_HEAD "
				+ " WHERE HRMS_DEBIT_HEAD.DEBIT_CODE IN ("
				+ earningBean.getDeductionCompId()
				+ ")" + "ORDER BY HRMS_DEBIT_HEAD.DEBIT_CODE";

			if(!earningBean.getEarningCompId().equals("")){
				creditsObj = getSqlModel().getSingleResult(creditsQuery);
			}
			if(!earningBean.getDeductionCompId().equals("")){
				debitsObj = getSqlModel().getSingleResult(debitsQuery);
			}

			//Get Employee Details
			String empWithCreditsQuery = "SELECT HRMS_SAL_CREDITS_"+year+".SAL_CREDIT_CODE, NVL(HRMS_CREDIT_HEAD.CREDIT_NAME,' '),NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '), TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME), "
				+ " HRMS_SAL_CREDITS_"+year+".SAL_AMOUNT "
				+ " FROM HRMS_SAL_CREDITS_"+year
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_CREDITS_"+year+".EMP_ID)"
				+ " INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_SAL_CREDITS_"+year+".SAL_CREDIT_CODE)"
				+ " WHERE HRMS_SAL_CREDITS_"+year+".SAL_MONTH="+month+" AND HRMS_SAL_CREDITS_"+year+".SAL_YEAR="+year 
				+ " AND HRMS_SAL_CREDITS_"+year+".SAL_CREDIT_CODE IN ("+earningBean.getEarningCompId()+")";

				if(!earningBean.getDivisionId().equals("")){
					empWithCreditsQuery+= " AND HRMS_EMP_OFFC.EMP_DIV ="+earningBean.getDivisionId();
				}
				if(!earningBean.getBranchId().equals("")){
					empWithCreditsQuery+= " AND HRMS_EMP_OFFC.EMP_CENTER ="+earningBean.getBranchId();
				}
				if(!earningBean.getPaybillId().equals("")){
					empWithCreditsQuery+= " AND HRMS_EMP_OFFC.EMP_PAYBILL ="+earningBean.getPaybillId();
				}
				if(!earningBean.getDepartmentId().equals("")){
					empWithCreditsQuery+= " AND HRMS_EMP_OFFC.EMP_DEPT =" +earningBean.getDepartmentId();
				}
				if(!earningBean.getEmpId().equals("")){
					empWithCreditsQuery+= " AND HRMS_EMP_OFFC.EMP_ID IN ("+earningBean.getEmpId()+")";
				}
				if(!earningBean.getEmpTypeId().equals("")){
					empWithCreditsQuery+= " AND HRMS_EMP_OFFC.EMP_TYPE =" +earningBean.getEmpTypeId();
				}
				empWithCreditsQuery+= " ORDER BY HRMS_SAL_CREDITS_"+year+".SAL_CREDIT_CODE";

			String empWithDebitsQuery = "SELECT HRMS_SAL_DEBITS_"+year+".SAL_DEBIT_CODE, NVL(HRMS_DEBIT_HEAD.DEBIT_NAME,' '),NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '), TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME),"
				+ " HRMS_SAL_DEBITS_"+year+".SAL_AMOUNT"
				+ " FROM HRMS_SAL_DEBITS_"+year
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SAL_DEBITS_"+year+".EMP_ID)"
				+ " INNER JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_SAL_DEBITS_"+year+".SAL_DEBIT_CODE)"
				+ " WHERE HRMS_SAL_DEBITS_"+year+".SAL_MONTH="+month+" AND HRMS_SAL_DEBITS_"+year+".SAL_YEAR="+year
				+ " AND HRMS_SAL_DEBITS_"+year+".SAL_DEBIT_CODE IN ("+earningBean.getDeductionCompId()+")";

				if(!earningBean.getDivisionId().equals("")){
					empWithDebitsQuery+= " AND HRMS_EMP_OFFC.EMP_DIV ="+earningBean.getDivisionId();
				}
				if(!earningBean.getBranchId().equals("")){
					empWithDebitsQuery+= " AND HRMS_EMP_OFFC.EMP_CENTER ="+earningBean.getBranchId();
				}
				if(!earningBean.getPaybillId().equals("")){
					empWithDebitsQuery+= " AND HRMS_EMP_OFFC.EMP_PAYBILL ="+earningBean.getPaybillId();
				} 
				if(!earningBean.getDepartmentId().equals("")){
					empWithDebitsQuery+= " AND HRMS_EMP_OFFC.EMP_DEPT =" +earningBean.getDepartmentId();
				} 
				if(!earningBean.getEmpId().equals("")){
					empWithDebitsQuery+= " AND HRMS_EMP_OFFC.EMP_ID IN ("+earningBean.getEmpId()+")";
				}
				if(!earningBean.getEmpTypeId().equals("")){
					empWithDebitsQuery+= " AND HRMS_EMP_OFFC.EMP_TYPE =" +earningBean.getEmpTypeId();
				} 
				empWithDebitsQuery+= " ORDER BY HRMS_SAL_DEBITS_"+year+".SAL_DEBIT_CODE";

			HashMap empCreditsMap=null;
			if(!earningBean.getEarningCompId().equals("")){
				empCreditsMap= getSqlModel().getSingleResultMap(empWithCreditsQuery, 0, 2);
			}
			HashMap empDebitsMap = null;
			if(!earningBean.getDeductionCompId().equals("")){
				empDebitsMap = getSqlModel().getSingleResultMap(empWithDebitsQuery, 0, 2);
			}
			String []header = new String[4];
			header[0] = "Earning/Deduction";
			header[1] = "Employee Id";
			header[2] = "Employee Name";
			header[3] = "Net (Rs.)";

			int[] cellwidth = { 25, 25, 25, 25};
			int[] alignment = { 0, 0, 0, 2 };

			/* Setting credits data to be displayed in the report*/
			if(!earningBean.getEarningCompId().equals("")){
				if(creditsObj!=null && creditsObj.length >0){
					for (int i = 0; i < creditsObj.length; i++) {
						int totalEmpCredits = 0;
						double totalAmountCredits = 0;
						TableDataSet tableData = new TableDataSet();
						if(i<1){
							tableData.setHeader(header);
							//tableData.setHeaderBorderDetail(1);
							tableData.setHeaderLines(true);
							tableData.setHeaderBorderColor(new BaseColor(255,0,0));
						}
						Object[][] currentCreditsObj=null;
						try {
							currentCreditsObj = (Object[][]) empCreditsMap.get(Utility.checkNull(String.valueOf(creditsObj[i][1])));
							Object[][] finalCreditsObj = null;
							if(currentCreditsObj!=null && currentCreditsObj.length >0){
								finalCreditsObj = new Object[currentCreditsObj.length][4];
								for (int j = 0; j < currentCreditsObj.length; j++) {
									for (int k = 1; k < currentCreditsObj[0].length; k++) {
										if(k==4){
											finalCreditsObj[j][k-1] = Utility.twoDecimals(String.valueOf(currentCreditsObj[j][k]));
										}else{
											finalCreditsObj[j][k-1] = String.valueOf(currentCreditsObj[j][k]);
										}
										
										finalCreditsObj[j][0] = "";
									}
									totalEmpCredits++;
									totalAmountCredits+=Double.parseDouble(Utility.twoDecimals(String.valueOf(currentCreditsObj[j][4])));
								}

								tableData.setData(new Object[][]{{String.valueOf(creditsObj[i][0]), " ", " ", " "}});
								tableData.setCellAlignment(new int[]{0, 0, 0, 0});
								tableData.setCellWidth(new int[]{25, 25, 25, 25});
								tableData.setBodyFontDetails(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(0, 0, 0));
								tableData.setBorder(true);
								tableData.setBorderDetail(0);
								tableData.setHeaderTable(true);
								//tableData.setHeaderBGBaseColor(new BaseColor(200, 200, 200));
								rg.addTableToDoc(tableData);

								TableDataSet tableData1 = new TableDataSet();
								tableData1.setData(finalCreditsObj);
								tableData1.setCellAlignment(alignment);
								tableData1.setCellWidth(cellwidth);
								tableData1.setBodyFontDetails(FontFamily.HELVETICA,10, Font.NORMAL, new BaseColor(0, 0, 0));
								tableData1.setBorder(true);
								tableData1.setBorderDetail(0);
								tableData1.setHeaderTable(true);
								rg.addTableToDoc(tableData1);

								TableDataSet totalData = new TableDataSet();
								totalData.setData(new Object[][]{{"","","", "Total : "+Utility.twoDecimals(totalAmountCredits)}});
								totalData.setCellAlignment(new int[]{0,0,0,2});
								totalData.setCellWidth(new int[]{25, 25,25,25});
								totalData.setBodyFontDetails(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(0, 0, 0));
								totalData.setBorder(true);
								totalData.setBorderDetail(2);

								totalData.setHeaderTable(true);
								totalData.setBlankRowsBelow(1);
								rg.addTableToDoc(totalData);
								Vector lineVector=new Vector();
								lineVector.add(new BaseColor(0, 0, 0));
								lineVector.add(Rectangle.TOP);
								rg.addLine(lineVector);

								TableDataSet totalData1 = new TableDataSet();
								totalData1.setData(new Object[][]{{"Total No Of Employees : "+totalEmpCredits,"","", ""}});
								totalData1.setCellAlignment(new int[]{0,0,0 ,2});
								totalData1.setCellWidth(new int[]{25, 25,25,25});
								totalData1.setBodyFontDetails(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(0, 0, 0));
								//totalData.setBorder(true);
								totalData1.setBorderDetail(0);

								//totalData1.setHeaderTable(true);
								rg.addTableToDoc(totalData1);
								lineVector=new Vector();
								lineVector.add(new BaseColor(0, 0, 0));
								lineVector.add(Rectangle.TOP);
								rg.addLine(lineVector);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}else{
					TableDataSet noDataTable = new TableDataSet();
					noDataTable.setData(new Object[][] { { "Employee earnings not available for the selected filter." } });
					noDataTable.setCellAlignment(new int[] { 1 });
					noDataTable.setCellWidth(new int[] { 100 });
					noDataTable.setBorder(false);
					noDataTable.setBorderDetail(1);
					rg.addTableToDoc(noDataTable);
				}
			}
			/* Setting debits data to be displayed in the report*/
			if(!earningBean.getDeductionCompId().equals("")){
				if(debitsObj!=null && debitsObj.length >0){
					for (int i = 0; i < debitsObj.length; i++) {
						int totalEmpDebits = 0;
						double totalAmountDebits = 0;
						Object[][] currentDebitsObj=null;
						try {
							currentDebitsObj = (Object[][]) empDebitsMap.get(Utility.checkNull(String.valueOf(debitsObj[i][1])));
							Object[][] finalDebitsObj = null;
							if(currentDebitsObj!=null && currentDebitsObj.length >0){
								finalDebitsObj = new Object[currentDebitsObj.length][4];
								for (int j = 0; j < currentDebitsObj.length; j++) {
									for (int k = 1; k < currentDebitsObj[0].length; k++) {
										if(k==4){
											finalDebitsObj[j][k-1] = Utility.twoDecimals(String.valueOf(currentDebitsObj[j][k]));
										}else{
											finalDebitsObj[j][k-1] = String.valueOf(currentDebitsObj[j][k]);
										}
										finalDebitsObj[j][0] = "";
									}
									totalEmpDebits++;
									totalAmountDebits+=Double.parseDouble(Utility.twoDecimals(String.valueOf(currentDebitsObj[j][4])));
								}
								TableDataSet tableData = new TableDataSet();
								tableData.setData(new Object[][]{{String.valueOf(debitsObj[i][0]), " ", " ", " "}});
								tableData.setCellAlignment(new int[]{0, 0, 0, 0});
								tableData.setCellWidth(new int[]{25, 25, 25, 25});
								tableData.setBodyFontDetails(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(0, 0, 0));
								tableData.setBorderDetail(0);
								tableData.setHeaderTable(true);
								tableData.setHeaderBGColor(new BaseColor(200, 200, 200));
								rg.addTableToDoc(tableData);

								TableDataSet tableData1 = new TableDataSet();
								tableData1.setData(finalDebitsObj);
								tableData1.setCellAlignment(alignment);
								tableData1.setCellWidth(cellwidth);
								tableData1.setBodyFontDetails(FontFamily.HELVETICA,10, Font.NORMAL, new BaseColor(0, 0, 0));
								tableData1.setBorderDetail(0);
								tableData1.setHeaderTable(true);
								rg.addTableToDoc(tableData1);

								TableDataSet totalData = new TableDataSet();
								totalData.setData(new Object[][]{{"","","", "Total : "+Utility.twoDecimals(totalAmountDebits)}});
								totalData.setCellAlignment(new int[]{0,0,0,2});
								totalData.setCellWidth(new int[]{25, 25,25,25});
								totalData.setBodyFontDetails(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(0, 0, 0));
								totalData.setBorder(true);
								totalData.setBorderDetail(2);
								totalData.setHeaderTable(true);
								totalData.setBlankRowsBelow(1);
								rg.addTableToDoc(totalData);
								Vector lineVector=new Vector();
								lineVector.add(new BaseColor(0, 0, 0));
								lineVector.add(Rectangle.TOP);
								rg.addLine(lineVector);

								TableDataSet totalData1 = new TableDataSet();
								totalData1.setData(new Object[][]{{"Total No Of Employees : "+totalEmpDebits,"","", ""}});
								totalData1.setCellAlignment(new int[]{0,0,0 ,2});
								totalData1.setCellWidth(new int[]{25, 25,25,25});
								totalData1.setBodyFontDetails(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(0, 0, 0));
								totalData1.setBorderDetail(0);
								rg.addTableToDoc(totalData1);
								lineVector=new Vector();
								lineVector.add(new BaseColor(0, 0, 0));
								lineVector.add(Rectangle.TOP);
								rg.addLine(lineVector);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}else{
					TableDataSet noDataTable = new TableDataSet();
					noDataTable.setData(new Object[][] { { "Employee deductions not available for the selected filter." } });
					noDataTable.setCellAlignment(new int[] { 1 });
					noDataTable.setCellWidth(new int[] { 100 });
					noDataTable.setBorder(false);
					//noDataTable.setBorderDetail(0);
					rg.addTableToDoc(noDataTable);
				}
			}
			String generatedByQuery = " SELECT TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) FROM HRMS_EMP_OFFC "
				//+ " LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
				+ " WHERE HRMS_EMP_OFFC.EMP_ID = "+earningBean.getUserEmpId();

			Object[][] empDataObj = getSqlModel().getSingleResult(generatedByQuery);

			if(empDataObj!=null && empDataObj.length >0){
				TableDataSet generatedByData = new TableDataSet();
				generatedByData.setData(new Object[][]{{"Generated By : "+String.valueOf(empDataObj[0][0])+" on "+date.toString()}});
				generatedByData.setCellAlignment(new int[]{0});
				generatedByData.setCellWidth(new int[]{100});
				generatedByData.setBodyFontDetails(FontFamily.HELVETICA, 7, Font.BOLD, new BaseColor(0, 0, 0));
				generatedByData.setBorder(false);
				//generatedByData.setBorderDetail(1);
				generatedByData.setHeaderTable(true);
				generatedByData.setBlankRowsAbove(1);
				rg.addTableToDoc(generatedByData);
				Vector lineVector=new Vector();
				lineVector.add(new BaseColor(255, 0, 0));
				lineVector.add(Rectangle.TOP);
				rg.addLine(lineVector);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}
}
