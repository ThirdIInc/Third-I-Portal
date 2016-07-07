package org.paradyne.model.extraWorkBenefits;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.extraWorkBenefits.ExtraWorkBenifitCalculation;
import org.paradyne.bean.extraWorkBenefits.ExtraWorkingBenefitCalReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font.FontFamily;
import com.lowagie.text.Font;

public class ExtraWorkingBenefitCalReportModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ExtraWorkingBenefitCalReportModel.class);
	
	NumberFormat formatter = new DecimalFormat("#0.00");

	public void getReport(ExtraWorkingBenefitCalReport extraWorkBenifitReport,
			HttpServletResponse response, HttpServletRequest request, String reportPath ) {
		// TODO Auto-generated method stub
		try {
			String reportName = "Extra Working Benefit Calculation Report";
			String fileName = "Extra Working Benefit Calculation Report";
			String reportType =  extraWorkBenifitReport.getReportType();
			int year = Integer.parseInt(extraWorkBenifitReport.getYear());
			int month = Integer.parseInt(extraWorkBenifitReport.getMonth());
			
			ReportDataSet rds = new ReportDataSet();
			rds.setReportType(reportType);
			rds.setFileName(fileName);
			rds.setReportName(fileName);
			rds.setPageOrientation("landscape");			
			rds.setUserEmpId(extraWorkBenifitReport.getUserEmpId());
			rds.setTotalColumns(10);
			org.paradyne.lib.ireportV2.ReportGenerator rg = null; 
				
			if(reportPath.equals("")){
				rg = new ReportGenerator(rds,session,context,request);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName+"."+reportType);
				rg = new ReportGenerator(rds,reportPath,session,context,request);
				request.setAttribute("reportPath", reportPath+fileName+"."+reportType);
				request.setAttribute("fileName", fileName+"."+reportType);
				request.setAttribute("action", "ExtraWorkingBenefitCalReport_input.action");
			}	
				
//			rg.addTextBold("Extra Working Benefit Calculation Report", 0, 1, 0);
//			rg.addTextBold("\n", 0, 1, 0);
			
			String divisionQuery = " SELECT HRMS_DIVISION.DIV_NAME, NVL(HRMS_DIVISION.DIV_ADDRESS1,' '), NVL(HRMS_DIVISION.DIV_ADDRESS2,' '), NVL(HRMS_DIVISION.DIV_ADDRESS3,' '), NVL(HRMS_DIVISION.DIV_PFACCOUNTNO,' '), NVL(HRMS_DIVISION.ESTABLISHMENT_CODE,0), NVL(HRMS_DIVISION.ACCOUNT_GROUP_CODE,0) FROM HRMS_DIVISION WHERE HRMS_DIVISION.DIV_ID IN("
				+ extraWorkBenifitReport.getDivisionCode() + ") ";
			Object divisionObj[][] = getSqlModel().getSingleResult(divisionQuery);
		
			
			TableDataSet subtitleName = new TableDataSet();
			String filterObj = "";
			filterObj = "Period  : " + Utility.month(month) + " - " + year;
			filterObj += "\n\nDivision : " + extraWorkBenifitReport.getDivisionName(); //String.valueOf(divisionObj[0][0]);
			
			int cnt=2;
			if(!extraWorkBenifitReport.getBrnId().equals("")){
				filterObj += "\n\nBranch : " + extraWorkBenifitReport.getBrnName();
			}
			if(!extraWorkBenifitReport.getDeptId().equals("")){
				filterObj += "\n\nDepartment : " + extraWorkBenifitReport.getDeptName();
			}
			if(!extraWorkBenifitReport.getPayBillNo().equals("")){
				filterObj += "\n\nPay Bill : " + extraWorkBenifitReport.getPayBillName();
			}
			if(!extraWorkBenifitReport.getCadreCode().equals("")){
				filterObj += "\n\nGrade : " + extraWorkBenifitReport.getCadreName();
			}	
			
			subtitleName.setData(new Object[][] { { filterObj } });
			subtitleName.setCellAlignment(new int[] { 0 });
			subtitleName.setCellWidth(new int[] { 100 });
			//subtitleName.setBodyFontStyle(1);
			subtitleName.setBorder(false);
			subtitleName.setCellNoWrap(new boolean[]{false});
			subtitleName.setCellColSpan(new int[]{ 8 });
			subtitleName.setHeaderTable(false);
			subtitleName.setBlankRowsBelow(1);
			//rg.createHeader(subtitleName);
			rg.addTableToDoc(subtitleName);
			/*
			Object[][] nameData = new Object[2][4];
			nameData[0][0] = "Month :";
			nameData[0][1] = "";// name
			nameData[0][2] = "Year :";
			nameData[0][3] = "";

			nameData[1][0] = "Division :";
			nameData[1][1] = "";
			nameData[1][2] = "";
			nameData[1][3] = "";

			if (!(extraWorkBenifitReport.getMonth().equals(""))) {
				nameData[0][1] = getMonth(Integer
						.parseInt(extraWorkBenifitReport.getMonth()));
			}// end of if
			if (!(extraWorkBenifitReport.getYear().equals(""))) {
				nameData[0][3] = extraWorkBenifitReport.getYear();
			}// end of if
			if (!(extraWorkBenifitReport.getDivisionName().equals(""))) {
				nameData[1][1] = extraWorkBenifitReport.getDivisionName();
			}// end of if

			int[] width_1 = { 15, 25, 15, 25 };
			int[] align_1 = { 0, 0, 0, 0 };
			 */
			String processCodeQuery = "   SELECT HRMS_EXTRAWORK_PROCESS_HDR.EXTRAWORK_PROCESS_CODE FROM HRMS_EXTRAWORK_PROCESS_HDR "
					+ " WHERE HRMS_EXTRAWORK_PROCESS_HDR.EXTRAWORK_PROCESS_MONTH="
					+ extraWorkBenifitReport.getMonth()
					+ " AND HRMS_EXTRAWORK_PROCESS_HDR.EXTRAWORK_PROCESS_YEAR="
					+ extraWorkBenifitReport.getYear()
					+ " AND "
					+ " HRMS_EXTRAWORK_PROCESS_HDR.EXTRAWORK_PROCESS_DIVISION IN("
					+ extraWorkBenifitReport.getDivisionCode() + ") ";

			Object processDataObj[][] = getSqlModel().getSingleResult(
					processCodeQuery);
			String code="0";
			if(processDataObj!=null && processDataObj.length>0){
				for (int i = 0; i < processDataObj.length; i++) {
					code+=","+String.valueOf(processDataObj[i][0]);
				}
			}

			if (processDataObj != null && processDataObj.length > 0)
				extraWorkBenifitReport.setProcessCode(code);
			else
				extraWorkBenifitReport.setProcessCode("0");

			String query1="";
			if(!extraWorkBenifitReport.getBrnId().equals("")){
				query1+=" AND HRMS_EMP_OFFC.EMP_CENTER IN("+extraWorkBenifitReport.getBrnId()+")";
			}
			if(!extraWorkBenifitReport.getDeptId().equals("")){
				query1+=" AND HRMS_EMP_OFFC.EMP_DEPT IN("+extraWorkBenifitReport.getDeptId()+")";
			}
			if(!extraWorkBenifitReport.getPayBillNo().equals("")){
				query1+=" AND HRMS_EMP_OFFC.EMP_PAYBILL IN("+extraWorkBenifitReport.getPayBillNo()+")";
			}
			if(!extraWorkBenifitReport.getCadreCode().equals("")){
				query1+=" AND HRMS_EMP_OFFC.EMP_CADRE IN("+extraWorkBenifitReport.getCadreCode()+")";
			}
			
			String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME AS emp_name "
					+ " ,TO_CHAR(HRMS_EXTRAWORK_PROCESS_DTL.EXTRAWORK_BENEFIT_DATE,'DD-MM-YYYY'),HRMS_EXTRAWORK_PROCESS_DTL.EXTRAWORK_BENEFIT_FOR,DECODE(HRMS_EXTRAWORK_PROCESS_DTL.EXTRAWORK_BENEFIT_TYPE,'EP','Extra-Day Pay','EL','Extra-Day Leave','FB','Fixed Benefits','VB','Variable Benefits'),NVL(HRMS_EXTRAWORK_PROCESS_DTL.EXTRAWORK_BENEFIT_TOTAL_AMT,0) "
					+ " FROM HRMS_EXTRAWORK_PROCESS_DTL "
					+ " INNER JOIN  HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_EXTRAWORK_PROCESS_DTL.EMP_ID)"
					+ "  WHERE HRMS_EXTRAWORK_PROCESS_DTL.EXTRAWORK_PROCESS_CODE IN("+ extraWorkBenifitReport.getProcessCode()+")"
					+ query1
					+"  ORDER BY HRMS_EXTRAWORK_PROCESS_DTL.EXTRAWORK_DTL_CODE ";
			Object data[][] = getSqlModel().getSingleResult(query);

			String creditHeadQuery = "  SELECT DISTINCT HRMS_EXTRAWORK_PROCESS_CREDITS.CREDIT_CODE,HRMS_CREDIT_HEAD.CREDIT_ABBR FROM HRMS_EXTRAWORK_PROCESS_CREDITS "
					+ "  INNER JOIN  HRMS_CREDIT_HEAD ON(hrms_CREDIT_HEAD.CREDIT_CODE=HRMS_EXTRAWORK_PROCESS_CREDITS.CREDIT_CODE)"
					+ "  WHERE HRMS_EXTRAWORK_PROCESS_CREDITS.EXTRAWORK_PROCESS_CODE IN("+ extraWorkBenifitReport.getProcessCode()+")"
					+" ORDER BY HRMS_EXTRAWORK_PROCESS_CREDITS.CREDIT_CODE ";
			Object creditHeadQueryObj[][] = getSqlModel().getSingleResult(
					creditHeadQuery);
			 
			Object finalObj1[][] = new Object[data.length][6];
			Object finalObj[][] = new Object[data.length][creditHeadQueryObj.length + 1];
			int s = 1;
			double totalAmount = 0.0;
			if (data != null && data.length > 0) {
				for (int i = 0; i < data.length; i++) {
					finalObj1[i][0] = s++;//sr no
					finalObj1[i][1] = data[i][0];//employee token
					finalObj1[i][2] = data[i][1];//employee name
					finalObj1[i][3] = data[i][2];//extraworking benefit date
					finalObj1[i][4] = data[i][3];//benefit for
					finalObj1[i][5] = data[i][4];//benefit type
					finalObj[i][creditHeadQueryObj.length]= formatter.format(Double.parseDouble( String.valueOf(data[i][5])));//total amount
					totalAmount = totalAmount + Double.parseDouble(String.valueOf(data[i][5]));
				}

			}
			String crediAmtQuery = " SELECT HRMS_EXTRAWORK_PROCESS_CREDITS.CREDIT_AMOUNT FROM HRMS_EXTRAWORK_PROCESS_CREDITS  WHERE HRMS_EXTRAWORK_PROCESS_CREDITS.EXTRAWORK_PROCESS_CODE IN("
				+ extraWorkBenifitReport.getProcessCode() + ")"
				+ " ORDER BY  HRMS_EXTRAWORK_PROCESS_CREDITS.EXTRAWORK_DTL_CODE ,HRMS_EXTRAWORK_PROCESS_CREDITS.CREDIT_CODE ";
		Object crediAmtQueryObj[][] = getSqlModel().getSingleResult(
				crediAmtQuery);
		if (crediAmtQueryObj != null && crediAmtQueryObj.length > 0 && finalObj != null && finalObj.length > 0) {
			cnt = 0;
			int k = 0;
			for (int i = 0; i < crediAmtQueryObj.length
					/ creditHeadQueryObj.length; i++) {
				for (int j = 0; j < creditHeadQueryObj.length; j++) {
					finalObj[cnt][j] = formatter.format(Double.parseDouble( String.valueOf(crediAmtQueryObj[k++][0])));
				}
				cnt++;
			}
	 	 
		}
			Object final_obj[][] = null;
			if (finalObj1 != null && finalObj1.length > 0) {
				final_obj = Utility.joinArrays(finalObj1, finalObj, 0, 0);

				int totalCol = 6 + creditHeadQueryObj.length + 1;
				String[] colNames = new String[totalCol];
				int[] cellWidth = new int[totalCol];
				int[] alignment = new int[totalCol];
				boolean[] bcellwrap = new boolean[totalCol];
				

				int counter = 0;
				colNames[counter] = "Sn";
				cellWidth[counter] = 5;
				alignment[counter] = 1;
				bcellwrap[counter] = true;
				counter++;
				colNames[counter] = "Employee Id";
				cellWidth[counter] = 20;
				alignment[counter] = 0;
				bcellwrap[counter] = true;
				counter++;
				colNames[counter] = "Name";
				cellWidth[counter] = 30;
				alignment[counter] = 0;
				bcellwrap[counter] = true;
				counter++;
				colNames[counter] = "Extra Work Date";
				cellWidth[counter] = 20;
				alignment[counter] = 0;
				bcellwrap[counter] = false;
				counter++;
				colNames[counter] = "ExtraWork Benefit For";
				cellWidth[counter] = 20;
				alignment[counter] = 0;
				bcellwrap[counter] = false;
				counter++;
				colNames[counter] = "Benefit Type";
				cellWidth[counter] = 20;
				alignment[counter] = 0;
				bcellwrap[counter] = false;
				counter++;
				if (creditHeadQueryObj != null && creditHeadQueryObj.length > 0) {
					for (int i = 0; i < creditHeadQueryObj.length; i++) {
						colNames[counter] = String
								.valueOf(creditHeadQueryObj[i][1]);
						cellWidth[counter] = 10;
						alignment[counter] = 2;
						bcellwrap[counter] = true;
						counter++;
					}
					colNames[counter] = "Total";
					cellWidth[counter] = 10;
					alignment[counter] = 2;
					bcellwrap[counter] = true;
				}
				if (final_obj != null && final_obj.length > 0) {
					/*
					if (extraWorkBenifitReport.getReportType().equals("Xls")) {
						String[] name_xls = { "",
								"Extra Working Benefit Calculation Report", "",
								"" };
						//rg.xlsTableBody(name_xls, nameData, width_1, align_1);
						//rg.addText("\n", 0, 0, 0);
						//rg.xlsTableBody(colNames, final_obj, cellWidth,alignment);
					} else if (extraWorkBenifitReport.getReportType().equals(
							"Pdf")) {
						//rg.addText("\n", 0, 0, 0);
						//rg.tableBodyNoBorder(nameData, width_1, align_1);
						//rg.addText("\n", 0, 0, 0);
						//rg.tableBody(colNames, final_obj, cellWidth, alignment);
					} else if (extraWorkBenifitReport.getReportType().equals(
							"Txt")) {
						//rg.tableBodyNoBorder(nameData, width_1, align_1);
						//rg.tableBody(colNames, final_obj, cellWidth, alignment);
					}
					*/
					
					TableDataSet addData = new TableDataSet();
					addData.setData(new Object[][] { { "Extra Work Calculation Details :" }, { "" } });
					addData.setCellAlignment(new int[] { 0 });
					addData.setCellColSpan(new int[] {2});
					addData.setCellWidth(new int[] { 100 });
					addData.setBodyFontStyle(1);
					addData.setBorder(false);
					addData.setHeaderTable(false);
					rg.addTableToDoc(addData);
					
					TableDataSet additionsData = new TableDataSet();
					additionsData.setData(final_obj);
					additionsData.setHeader(colNames);
					additionsData.setHeaderBorderDetail(3);
					additionsData.setCellAlignment(alignment);
					additionsData.setCellWidth(cellWidth);
					additionsData.setBorder(true);
					additionsData.setBorderDetail(3);
					additionsData.setHeaderTable(true);
					//additionsData.setHeaderLines(true);
					additionsData.setHeaderBGColor(new BaseColor(225, 225, 225));
					additionsData.setBlankRowsBelow(1);
					rg.addTableToDoc(additionsData);
					
					TableDataSet reportObjData = new TableDataSet();
					Object [][] paramTotalRec=new Object[2][2];
					paramTotalRec[0][0]="Total Employees : " + final_obj.length;
					paramTotalRec[1][1]="Total Amount : " + formatter.format(totalAmount);
					reportObjData.setCellAlignment(new int[] {0,2});
					reportObjData.setCellWidth(new int[] {50,50});
					reportObjData.setCellColSpan(new int[] { 2,final_obj[1].length-2 });
					reportObjData.setData(paramTotalRec);
					//reportObjData.setBorder(true);
					reportObjData.setBorderDetail(0);
					reportObjData.setBodyFontStyle(1);
					reportObjData.setBlankRowsBelow(1);
					reportObjData.setBodyBGColor(new BaseColor(200,200,200));
					rg.addTableToDoc(reportObjData);
				}

			}

			else {
				//rg.addTextBold("There is no data to display.", 0, 1, 0);
				TableDataSet noData = new TableDataSet();
				noData.setData(new Object[][] { { "" },	{ "Extra Work Calculation Details : There is no data to display." } });
				noData.setCellAlignment(new int[] { 0 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBodyFontStyle(1);
				noData.setBorder(false);
				noData.setHeaderTable(false);
				rg.addTableToDoc(noData);
			}// end of else

			/*
			String sql = "SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME FROM HRMS_EMP_OFFC WHERE  EMP_ID=" + extraWorkBenifitReport.getUserEmpId();
			Object[][] loginEmp = getSqlModel().getSingleResult(sql);
			Object[][] loginObj = new Object[1][1];
			SimpleDateFormat formatter = new SimpleDateFormat("dd, MMM yyyy HH:mm");
			loginObj[0][0] = "Generated By " + loginEmp[0][0] + " on " + formatter.format(new java.util.Date());// "05, Dec 2011 18:30 " ;
			
			TableDataSet genBy = new TableDataSet();			
			genBy.setData(loginObj);
			genBy.setCellAlignment(new int[] { 0 });
			genBy.setCellWidth(new int[] { 100 });
			genBy.setBodyFontDetails(FontFamily.HELVETICA, 10,	Font.NORMAL, new BaseColor(0, 0, 0));
			genBy.setBorder(false);
			genBy.setBlankRowsBelow(1);
			rg.addTableToDoc(genBy);
			*/
			rg.process();
			if(reportPath.equals("")){
				rg.createReport(response);
			}else{
				/* Generates the report as attachment*/
				rg.saveReport(response);
			}
			
			
		} catch (Exception e) {
			logger.error("Exception in getReport-------------" + e);
			e.printStackTrace();

		}
	}

	public String getMonth(int month) {
		String str = "";
		switch (month) {
		case 1:
			str = "January";
			break;
		case 2:
			str = "February";
			break;
		case 3:
			str = "March";
			break;
		case 4:
			str = "April";
			break;
		case 5:
			str = "May";
			break;
		case 6:
			str = "June";
			break;
		case 7:
			str = "July";
			break;
		case 8:
			str = "August";
			break;
		case 9:
			str = "September";
			break;
		case 10:
			str = "October";
			break;
		case 11:
			str = "November";
			break;
		case 12:
			str = "December";
		}
		return str;
	}
	

}
