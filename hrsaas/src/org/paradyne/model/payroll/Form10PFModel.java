package org.paradyne.model.payroll;
import java.awt.Color;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.Form10PF;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
/**
 * @author reebaj
 * @modifed by Anantha lakshmi
 */
public class Form10PFModel extends ModelBase {
	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ModelBase.class);
	/**
	 * Generation of report.
	 * @param form10
	 * @param response
	 */
	public void getForm10Report(Form10PF form10,HttpServletRequest request, HttpServletResponse response,String logoPath, String reportPath) {
		
		final ReportDataSet rds = new ReportDataSet();
		final String reportType = form10.getReportType();
		rds.setReportType(reportType);
		
		final String companyQuery = " SELECT HRMS_DIVISION.DIV_NAME, NVL(HRMS_DIVISION.DIV_ADDRESS1,' '),NVL(HRMS_DIVISION.DIV_ADDRESS2,' '),NVL(HRMS_DIVISION.DIV_ADDRESS3,' '),NVL(HRMS_DIVISION.ESTABLISHMENT_CODE,0), NVL(HRMS_DIVISION.DIV_ABBR,' ') FROM HRMS_DIVISION WHERE HRMS_DIVISION.DIV_ID = " + form10.getDivCode() + " ";
		final Object[][] companyData = this.getSqlModel().getSingleResult(companyQuery);
		
		final String strFileName = "PF10_" + companyData[0][5] + "_"
			+ Utility.month(Integer.parseInt(form10.getMonth())).substring(0, 3)
			+ form10.getYear().substring(form10.getYear().length() - 2,form10.getYear().length()) + "_"
			+ Utility.getRandomNumber(1000); 		

		rds.setFileName(strFileName);
		rds.setReportName("Form 10 Report");
		rds.setPageSize("A4");
		rds.setReportHeaderRequired(false);
		rds.setPageOrientation("landscape");
		//rds.setUserEmpId(form10.getUserEmpId());
		org.paradyne.lib.ireportV2.ReportGenerator rg = null;
		
		
		if(reportPath.equals("")){
			rg = new ReportGenerator(rds,session,context,request);
		}else{
			logger.info("################# ATTACHMENT PATH #############"+reportPath+strFileName+"."+reportType);
			rg = new ReportGenerator(rds,reportPath,session,context,request);
			request.setAttribute("reportPath", reportPath+strFileName+"."+reportType);
			request.setAttribute("fileName", strFileName + "."	+ reportType);
			request.setAttribute("action", "Form10Report_input.action");
		}
		
		if(reportType.equalsIgnoreCase("pdf")){
			final TableDataSet headerName = new TableDataSet();
			final Object[][] headerObj = new Object[1][3];
			try{
				//headerObj[0][0] = rg.getImage(logoPath); //Image.getInstance(logoPath);
				Image newLogo = Image.getInstance(logoPath);
				newLogo.setScaleToFitLineWhenOverflow(false);
				newLogo.scaleAbsolute(90f, 90f);
				headerObj[0][0] = newLogo;
			}catch(Exception e){
				headerObj[0][0] = "";
			}
			System.out.println("logoPath:"+ logoPath);
			/*
			headerObj[0][1] = " Form 10 \n" +
							  " THE EMPLOYEES PROVIDENT FUND SCHEME, 1952 [PARA 36(2)(a) AND (b) AND  \n" +
							  " THE EMPLOYEES' PENSION SCHEME 1995(PARA 20(2)) \n";
			*/
			headerObj[0][1] = " Form - 10 \n" +
					"The Employees' Provident Fund Scheme,1952 \n Paragraph 36 (2) (a) and (b) & 42 \n The Employees' Pension Scheme,1995 \n Paragraph 20 (4)";
			headerName.setData(headerObj);
			headerName.setCellAlignment(new int[] { 0,1 });
			headerName.setCellWidth(new int[] { 15,85 });
			headerName.setBodyFontStyle(1);
			headerName.setBorder(false);
			//headerName.setHeaderTable(true);
			headerName.setBlankRowsBelow(1);
			//rg.createHeader(headerName);
			rg.addTableToDoc(headerName);
		}else{
			final TableDataSet headerName = new TableDataSet();
			final Object[][] headerObj = new Object[1][1];
			
			/*
			headerObj[0][1] = " Form 10 \n" +
							  " THE EMPLOYEES PROVIDENT FUND SCHEME, 1952 [PARA 36(2)(a) AND (b) AND  \n" +
							  " THE EMPLOYEES' PENSION SCHEME 1995(PARA 20(2)) \n";
			*/
			headerObj[0][0] = " Form - 10 \n" +
					"The Employees' Provident Fund Scheme,1952 \n Paragraph 36 (2) (a) and (b) & 42 \n The Employees' Pension Scheme,1995 \n Paragraph 20 (4)";
			headerName.setData(headerObj);
			headerName.setCellAlignment(new int[] {1 });
			headerName.setCellWidth(new int[] {100 });
			headerName.setBodyFontStyle(1);
			headerName.setCellColSpan(new int[] { 7 });
			headerName.setBorder(false);
			//headerName.setHeaderTable(true);
			headerName.setBlankRowsBelow(1);
			//rg.createHeader(headerName);
			rg.addTableToDoc(headerName);
		}
		
		
		
		final Object[][] secData = new Object[3][1];
		final TableDataSet headerName1 = new TableDataSet();
		secData[0][0] = "Return of the members leaving service during month of : " + Utility.month(Integer.parseInt(form10.getMonth())) + " " + form10.getYear();
		if (companyData != null && companyData.length > 0) {	
			secData[1][0] = "Name and Address of the Factory/Establishment : " +
			this.checkNull(String.valueOf(companyData[0][0])); 
			//+ ", " + this.checkNull(String.valueOf(companyData[0][1])) + ", " + this.checkNull(String.valueOf(companyData[0][2])) + ", " + this.checkNull(String.valueOf(companyData[0][3])) ;
			
			if(!String.valueOf(companyData[0][1]).trim().equals("")){
				secData[1][0] = secData[1][0] + "," + this.checkNull(String.valueOf(companyData[0][1]));  
			}
			if(!String.valueOf(companyData[0][2]).trim().equals("")){
				secData[1][0] = secData[1][0] + "," + this.checkNull(String.valueOf(companyData[0][2]));
			}
			if(!String.valueOf(companyData[0][3]).trim().equals("")){
				secData[1][0] = secData[1][0] + "," + this.checkNull(String.valueOf(companyData[0][3]));
			}
			//secData[1][0] = this.checkNull(String.valueOf(companyData[0][0])) + " , " + this.checkNull(String.valueOf(companyData[0][1])) + " , " + this.checkNull(String.valueOf(companyData[0][2])) + " , " + this.checkNull(String.valueOf(companyData[0][3]));
			secData[2][0] = "Code No. of the Factory/Establishment : " + this.checkNull(String.valueOf(companyData[0][4]));
		} else {
			secData[1][0] = "Name and Address of the Factory/Establishment : ";
			secData[2][0] = "Code No. of the Factory/Establishment : ";
		}

		headerName1.setData(secData);
		headerName1.setCellAlignment(new int[] { 0 });
		headerName1.setCellWidth(new int[] { 100 });
		//headerName1.setBodyFontDetails(FontFamily.HELVETICA , 10 , Font.NORMAL , new BaseColor(0 , 0 , 0));
		headerName1.setBorder(false);
		headerName1.setCellColSpan(new int[] { 7 });
		//headerName1.setHeaderTable(true);
		headerName1.setBlankRowsBelow(1);
		rg.addTableToDoc(headerName1);
		
		
		
		final String fromMonth = form10.getMonth();
		final String fromYear = form10.getYear();	
		final String query = " SELECT HRMS_EMP_OFFC.EMP_ID, NVL(HRMS_SALARY_MISC.SAL_ACCNO_REGULAR,' '),  UPPER(NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,'')), " +
					 " ROWNUM ,TO_CHAR(HRMS_EMP_OFFC.EMP_LEAVE_DATE,'DD-MM-YYYY'), NVL(HRMS_RESIGN.RESIGN_REASON,' ')" +
					 " FROM HRMS_EMP_OFFC " +
					 " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID) " +
					 " LEFT JOIN HRMS_EMP_FML ON (HRMS_EMP_FML.EMP_ID = HRMS_EMP_OFFC.EMP_ID AND HRMS_EMP_FML.FML_RELATION=5) " +
					 " LEFT JOIN HRMS_EMP_PERS ON (HRMS_EMP_PERS.EMP_ID = HRMS_EMP_OFFC.EMP_ID)" +
					 " LEFT JOIN HRMS_RESIGN ON (HRMS_RESIGN.RESIGN_EMP = HRMS_EMP_OFFC.EMP_ID) " +
					 " LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) " +
					"  WHERE TO_CHAR(HRMS_EMP_OFFC.EMP_LEAVE_DATE,'MM') = '" + fromMonth + "' AND TO_CHAR(HRMS_EMP_OFFC.EMP_LEAVE_DATE,'YYYY') = '" + fromYear + "' AND HRMS_DIVISION.DIV_ID = " + form10.getDivCode(); 
		
		final Object[][] tableDataTemp = this.getSqlModel().getSingleResult(query);
		//Object[][] tableData =null;
		if (tableDataTemp != null && tableDataTemp.length != 0) {
	                for (int i = 0; i < tableDataTemp.length; i = i + 1) {
	                	final String middleNameQuery = " SELECT UPPER(HRMS_EMP_FML.FML_FNAME),UPPER(HRMS_RELATION.RELATION_NAME),HRMS_EMP_OFFC.EMP_GENDER FROM HRMS_EMP_FML " +
							" INNER JOIN HRMS_RELATION ON (HRMS_RELATION.RELATION_CODE =HRMS_EMP_FML.FML_RELATION) " +
							" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_FML.EMP_ID) " +
							" WHERE HRMS_EMP_OFFC.EMP_ID = " + tableDataTemp[i][0] + 
							" AND (UPPER(HRMS_RELATION.RELATION_NAME) = 'FATHER' OR UPPER(HRMS_RELATION.RELATION_NAME) = 'HUSBAND') " +
							" ORDER BY UPPER(HRMS_RELATION.RELATION_NAME) DESC "; 
				String middleName = "";
				final Object[][] middleNameObject = this.getSqlModel().getSingleResult(middleNameQuery);
				try {
					for (int j = 0; j < middleNameObject.length; j = j + 1) {
						if (String.valueOf(middleNameObject[j][1]).equals("HUSBAND")) {
							middleName = "W/O " + String.valueOf(middleNameObject[j][0]);
							break;
						} else if (String.valueOf(middleNameObject[j][1]).equals("FATHER")) {
							if (String.valueOf(middleNameObject[j][2]).equals("M"))	
								middleName = "S/O " + String.valueOf(middleNameObject[j][0]);
						    else if (String.valueOf(middleNameObject[j][2]).equals("F"))  
								middleName = "D/O " + String.valueOf(middleNameObject[j][0]);
							continue;
						}
					} //for loop closed........
				} catch (final Exception e) {
				        middleName = "";
				}	
				tableDataTemp [i][3] = middleName;
		        } //for loop close
		} // if close
		 
		Object[][] finalData = null;
		final int[] cellwidth = {5, 15, 15, 25, 10, 10, 20 };
		final int[] alignment = {1, 0, 0, 0, 0, 0, 0 };
		if (tableDataTemp != null && tableDataTemp.length > 0) {
			finalData = new Object[tableDataTemp.length][9];
			for (int cnt = 0; cnt < finalData.length; cnt++) {
				/*if (cnt == 0) {
					finalData[0][0] = "Sr.No";
					finalData[0][1] = "Account Number";
					finalData[0][2] = "Name of The Member (in BLOCK letters)";
					finalData[0][3] = "Father's Name or Husband's name in case of married woman ";
					finalData[0][4] = "Date of Leaving Service";
					finalData[0][5] = "Reason For Leaving Service";
					finalData[0][6] = "Remarks";
				} else if (cnt == 1) {
					finalData[1][0] = "1";
					finalData[1][1] = "2";
					finalData[1][2] = "3";
					finalData[1][3] = "4";
					finalData[1][4] = "5";
					finalData[1][5] = "6";
					finalData[1][6] = "7";		
				} else {*/
					finalData[cnt][0] = String.valueOf(cnt + 1);
					finalData[cnt][1] = this.checkNull(String.valueOf(tableDataTemp[ cnt ][1]));
					finalData[cnt][2] = this.checkNull(String.valueOf(tableDataTemp[ cnt ][2]));
					finalData[cnt][3] = this.checkNull(String.valueOf(tableDataTemp[ cnt ][3]));
					finalData[cnt][4] = this.checkNull(String.valueOf(tableDataTemp[ cnt ][4]));
					finalData[cnt][5] = this.checkNull(String.valueOf(tableDataTemp[ cnt ][5]));
					finalData[cnt][6] = " ";
				//}
			} // for loop close
		
		
			String header[][] = new String[2][7];

			header[0][0] = "Sr.No";
			header[0][1] = "Account Number";
			header[0][2] = "Name of The Member (in BLOCK letters)";
			header[0][3] = "Father's Name or Husband's name in case of married woman ";
			header[0][4] = "Date of Leaving Service";
			header[0][5] = "Reason For Leaving Service";
			header[0][6] = "Remarks";

			header[1][0] = "1";
			header[1][1] = "2";
			header[1][2] = "3";
			header[1][3] = "4";
			header[1][4] = "5";
			header[1][5] = "6";
			header[1][6] = "7";		
		
			
			final TableDataSet headerName3 = new TableDataSet();
			headerName3.setData(finalData);
			headerName3.setCellAlignment(alignment);
			headerName3.setCellWidth(cellwidth);
			headerName3.setBorder(true);
			headerName3.setBorderDetail(3);
			headerName3.setHeaderData(header);
			headerName3.setHeaderTable(true);
			headerName3.setHeaderBorderDetail(3);
			headerName3.setHeaderLines(false);
			headerName3.setHeaderBGColor(new BaseColor(225, 225, 225));
			headerName3.setBlankRowsBelow(1);
			rg.addTableToDoc(headerName3);
		} else {
			/*finalData = new Object[tableDataTemp.length + 2][9];
			for (int i = 0; i < finalData.length; i = i + 1) {
				if (i == 0) {
					finalData[0][0] = "Sr.No";
					finalData[0][1] = "Account Number";
					finalData[0][2] = "Name of The Member (in BLOCK letters)";
					finalData[0][3] = "Father's Name or Husband's name in case of married woman ";
					finalData[0][4] = "Date of Leaving Service";
					finalData[0][5] = "Reason For Leaving Service";
					finalData[0][6] = "Remarks";
				} else if (i == 1) {
					finalData[1][0] = "1";
					finalData[1][1] = "2";
					finalData[1][2] = "3";
					finalData[1][3] = "4";
					finalData[1][4] = "5";
					finalData[1][5] = "6";
					finalData[1][6] = "7";		
				}
			} //loop close
			final TableDataSet hName = new TableDataSet();
			hName.setData(finalData);
			hName.setCellAlignment(alignment);
			hName.setCellWidth(cellwidth);
			hName.setBorder(true);
			hName.setBorderDetail(3);
			//hName.setHeaderTable(true);
			hName.setBlankRowsBelow(4);
			rg.addTableToDoc(hName);*/
			
			String header[][] = new String[2][7];

			header[0][0] = "Sr.No";
			header[0][1] = "Account Number";
			header[0][2] = "Name of The Member (in BLOCK letters)";
			header[0][3] = "Father's Name or Husband's name in case of married woman ";
			header[0][4] = "Date of Leaving Service";
			header[0][5] = "Reason For Leaving Service";
			header[0][6] = "Remarks";

			header[1][0] = "1";
			header[1][1] = "2";
			header[1][2] = "3";
			header[1][3] = "4";
			header[1][4] = "5";
			header[1][5] = "6";
			header[1][6] = "7";		
		
			
			final TableDataSet headerName3 = new TableDataSet();
			headerName3.setCellAlignment(alignment);
			headerName3.setCellWidth(cellwidth);
			headerName3.setBorder(true);
			headerName3.setBorderDetail(3);
			headerName3.setHeaderData(header);
			headerName3.setHeaderTable(true);
			headerName3.setHeaderBorderDetail(3);
			headerName3.setHeaderLines(false);
			headerName3.setHeaderBGColor(new BaseColor(225, 225, 225));
			headerName3.setBlankRowsBelow(1);
			rg.addTableToDoc(headerName3);
			
			
		} //if close
	
		
		final String dateQuery = " SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
		final Object[][] dateData = this.getSqlModel().getSingleResult(dateQuery);
		final Object[][]  bottomData = new Object[1][1];
		bottomData[0][0]="Signature of the Employee or other authorised officer and stamp of the Factory/Establishment";
		
		final TableDataSet headerName4 = new TableDataSet();
		headerName4.setData(bottomData);
		headerName4.setCellAlignment(new int[] { 2 });
		headerName4.setCellWidth(new int[] { 100 });
		headerName4.setBodyFontStyle(1);
		headerName4.setBorder(false);
		//headerName4.setHeaderTable(true);
		headerName4.setCellColSpan(new int[] {7});
		headerName4.setBlankRowsAbove(3);
		headerName4.setBlankRowsBelow(1);
		rg.addTableToDoc(headerName4);
		
		final Object[][]  bottomData1 = new Object[3][1];
		bottomData1[0][0]="Please state whether is (a)retiring according to para 69(1)(a) or (b)of the scheme; " +
				"(b) leaving India for permanent settlement abroad; (c)retrenched; (d)ordinarily dismissed for serious" +
				" and willful misconduct; (e)discharged; (f)resigning from or leaving service; (g)taking up employement" +
				" elsewhere (the name and address the new employer should be stated); (h)dead.";
		
		bottomData1[1][0]="\n(1) A request for deduction from the account of a member dismissed for serious and willful " +
				"misconduct should be reported by the following \"certified that the member mentioned at Sr. No. _________" +
				" Shri. ________________________________ was dismissed from the service for willful misconduct. I recommend that " +
				" the employer's contribution for ___________ should be forfeited from his account in the fund. A copy of order of" +
				" dismissal is enclosed.";
		
		bottomData1[2][0]="\n(2) In case of discharge from service, the following certificate should be filled. \nCertified that the " +
				" member mentioned in Sr. No. _________ Shri. ________________________________ was paid/unpaid retrenchment compensation of Rs. _______" +
				"under the Industrial Disputes Act, 1947"; 
		
		
		final TableDataSet bottomDataTds = new TableDataSet();
		bottomDataTds.setData(bottomData1);
		bottomDataTds.setCellAlignment(new int[] { 0 });
		bottomDataTds.setCellWidth(new int[] { 100 });
		//bottomDataTds.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.NORMAL, new BaseColor(0 , 0 , 0));
		bottomDataTds.setBorder(false);
		bottomDataTds.setCellColSpan(new int[] { 7 });
		//bottomDataTds.setHeaderTable(true);
		bottomDataTds.setBlankRowsBelow(1);
		rg.addTableToDoc(bottomDataTds);
		
		rg.process();
		if(reportPath.equals("")){
			rg.createReport(response);
		}else{
			/* Generates the report as attachment*/
			rg.saveReport(response);
		}
	}// end of getReport
	/**
	 * Check Null values.
	 * @param result
	 * @return result
	 */
	public String checkNull(final String result) {
		if (result == null || result.equals("null") || result.equals("")) {	
			return " ";
		} else {
			return result;
		}
	}// end of checkNull
	
}
