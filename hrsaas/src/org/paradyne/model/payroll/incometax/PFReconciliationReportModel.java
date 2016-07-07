package org.paradyne.model.payroll.incometax;

import java.awt.Color;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.incometax.PFReconciliationReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.payroll.PFDataModel;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;;

public class PFReconciliationReportModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(PFReconciliationReportModel.class);
	public void getReport(PFReconciliationReport bean, HttpServletRequest request, HttpServletResponse response, String reportPath) {
		
		try {
			org.paradyne.lib.ireportV2.ReportGenerator rg=null;
			ReportDataSet rds = new ReportDataSet();
			String reportType = bean.getReportType();
			rds.setReportType(reportType);
			String fileName = bean.getDivisionAbbrevation()+" Reconciliation Report"+Utility.getRandomNumber(1000);
			rds.setFileName(fileName);			
			rds.setReportName("Reconciliation Report");
			rds.setPageOrientation("landscape");
			rds.setUserEmpId(bean.getUserEmpId());
			rds.setTotalColumns(15);

			if(reportPath.equals("")){
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context,request);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context,request);
				//request.setAttribute("reportPath", reportPath);
				request.setAttribute("reportPath", reportPath+fileName+"."+reportType);
				request.setAttribute("action", "PFReconciliation_input.action");
				request.setAttribute("fileName", fileName + "." + reportType);
			}
			TableDataSet subtitleName = new TableDataSet();
			Object obj[][] = new Object[2][1];
			obj[0][0] = "SUMMARY FOR THE CONTRIBUTION PERIOD : "+bean.getFromYear() + " To "+ bean.getToYear();
			obj[1][0] = "RECONCILIATION OF DUES AND REMITTANCE AS PER FORM 12A AND CHALLANS RESPECTIVELY";
			
			subtitleName.setData(obj);
			subtitleName.setCellAlignment(new int[] { 1 });
			subtitleName.setCellWidth(new int[] { 100 });
			//subtitleName.setBodyFontDetails(Font.FontFamily.HELVETICA, 10, Font.BOLD,new BaseColor(0, 0, 0));
			subtitleName.setBorderDetail(0);
			subtitleName.setCellColSpan(new int[]{15});
			subtitleName.setBodyFontStyle(1);
			//subtitleName.setHeaderTable(true);
			subtitleName.setBlankRowsBelow(1);
			rg.addTableToDoc(subtitleName);
			
			String divisionQuery = " SELECT DIV_NAME, DIV_ADDRESS1, DIV_ADDRESS2, DIV_ADDRESS3, NVL(DIV_PFACCOUNTNO,' '), NVL(ESTABLISHMENT_CODE,0), NVL(ACCOUNT_GROUP_CODE,0) FROM HRMS_DIVISION WHERE DIV_ID="+bean.getDivId()+" " ;
			Object divisionObj[][] = getSqlModel().getSingleResult(divisionQuery);
			
			String pfOfficeNameQuery = " SELECT NVL(CHALLAN_PF_OFFC_NAME,'N/A') FROM HRMS_PF_CHALLAN WHERE CHALLAN_DIVISION ="+bean.getDivId(); 
			
			Object pfOfficeNameObj[][] = getSqlModel().getSingleResult(pfOfficeNameQuery);
			
			String pfOfficeName="";
			if(pfOfficeNameObj!=null && pfOfficeNameObj.length > 0){
				pfOfficeName = String.valueOf(pfOfficeNameObj[0][0]);
			}
			Object division[][] = new Object[4][1];
			
			if(divisionObj!=null && divisionObj.length > 0){
				division[0][0] = "Name & Address of the Establishment :"+String.valueOf(divisionObj[0][0])+""+String.valueOf(divisionObj[0][1])+""+String.valueOf(divisionObj[0][2])+""+String.valueOf(divisionObj[0][3]);
				division[1][0] = "Code no of Establishment : "+String.valueOf(divisionObj[0][5]);
				//division[1][0] = String.valueOf(divisionObj[0][0]);
				division[2][0] = "Group No : "+String.valueOf(divisionObj[0][6]);
				//division[2][0] = String.valueOf(divisionObj[0][1]);
				//division[2][1] = "";
				//division[3][0] = String.valueOf(divisionObj[0][2]);
				division[3][0] = "PF Office Name : "+pfOfficeName;
			//	division[4][0] = String.valueOf(divisionObj[0][3]);
				//division[4][1] = "";
				
			}
			
			TableDataSet establishmentData = new TableDataSet();
			//establishmentData.setHeader(new String[] { "Name & Address of the Establishment" });
			establishmentData.setCellAlignment(new int[] { 0 });
			establishmentData.setCellWidth(new int[] { 100 });
			establishmentData.setData(division);
			establishmentData.setCellColSpan(new int[]{15});
			establishmentData.setBorderDetail(0);
			establishmentData.setBlankRowsBelow(1);
			rg.addTableToDoc(establishmentData);
			
		/*	Object groupObj[][] = new Object[2][6];
			groupObj[0][0]="";
			groupObj[0][1]="";
			groupObj[0][2]="";
			groupObj[0][3]="";
			groupObj[0][4]="";
			groupObj[0][5]="";
			groupObj[1][0]="";
			groupObj[1][1]="";
			groupObj[1][2]="AMOUNT DUE AS PER FORM NO.12A";
			groupObj[1][3]="AMOUNT PAID AS PER CHALLANS";
			groupObj[1][4]="";
			groupObj[1][5]="";
			
			TableDataSet accountGroup = new TableDataSet();
			accountGroup.setCellAlignment(new int[] { 0, 0, 1, 1, 0, 0 });
			accountGroup.setCellWidth(new int[] { 5, 5, 40, 40 , 5, 5 });
			accountGroup.setData(groupObj);
			accountGroup.setBorderDetail(0);
			accountGroup.setBodyFontStyle(1);
			accountGroup.setHeaderTable(false);
			rg.addTableToDoc(accountGroup);*/
			String mainHeader[]= {"","AMOUNT DUE AS PER FORM NO.12A","AMOUNT PAID AS PER CHALLANS"};
			TableDataSet mainHeaderData = new TableDataSet();
			//establishmentData.setHeader(new String[] { "Name & Address of the Establishment" });
			mainHeaderData.setCellAlignment(new int[] { 0, 1, 1 });
			mainHeaderData.setHeaderColSpan(new int[]{2,6,7});
			mainHeaderData.setHeader(mainHeader);
			mainHeaderData.setCellWidth(new int[] { 25, 25, 35, 35, 25, 25, 25, 25, 25, 20, 20, 25 , 25 , 30, 30 });
			mainHeaderData.setData(null);
			mainHeaderData.setHeaderBorderDetail(3);
			//mainHeaderData.setHeaderTable(false);
			//mainHeaderData.setBlankRowsBelow(1);
			rg.addTableToDoc(mainHeaderData);
			
			
			String header[]= {"MONTH",
					"WAGES",
					"A/C.I",
					"A/C.II",
					"A/C.X",
					"A/C.XXI",
					"A/C.XXII", "TOTAL DUES",
					"A/C.I",
					"A/C.II",
					"A/C.X",
					"A/C.XXI",
					"A/C.XXII", "TOTAL PAYMENT","DATE OF PAYMENT" };
			
			int[] cellwidth = { 25, 25, 35, 35, 25, 25, 25, 25, 25, 20, 20, 25 , 25 , 30, 30 };
			int[] alignment = { 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1 };
			
			int year = Integer.parseInt(bean.getFromYear());
			
			// GET THE PF PERCENT TO CALCULATE THE AC DATA
			String percentContribution = "SELECT PF_EMPLOYEE, PF_EMP_FAMILY, PF_COMPANY, PF_CMP_FAMILY, PF_ADMIN_CHARGES, PF_EDLI_EMPCONT, PF_EDLI_ADMIN_CHARGES, PF_PERCENTAGE, PF_EMOL_NO_MAX_LIMIT_CHK, PF_EMOL_MAX_LIMIT FROM HRMS_PF_CONF WHERE " 
				+ " TO_NUMBER(NVL(TO_CHAR(PF_DATE,'yyyy'),0)) <="
				+ year
				+ " ORDER BY PF_DATE DESC";
			Object[][] contributionData = getSqlModel().getSingleResult(percentContribution);
			
			
			double pf_emp = 0.0;
			double pf_emp_family = 0.0;
			double pf_comp = 0.0;
			double pf_comp_family = 0.0;
			double pf_admin_charges = 0.0;
			double pf_edli_empcont = 0.0;
			double pf_edli_admin_charges = 0.0;
			double pf_percentage = 0.0;
			String checkFlag = "N";
			double emolumentAmtLimit = 0.0;
			
			if(contributionData!=null && contributionData.length > 0){
				pf_emp = Double.parseDouble(String.valueOf(contributionData[0][0]));
				pf_emp_family = Double.parseDouble(String.valueOf(contributionData[0][1]));
				pf_comp = Double.parseDouble(String.valueOf(contributionData[0][2]));
				pf_comp_family = Double.parseDouble(String.valueOf(contributionData[0][3]));
				pf_admin_charges = Double.parseDouble(String.valueOf(contributionData[0][4]));
				pf_edli_empcont = Double.parseDouble(String.valueOf(contributionData[0][5]));
				pf_edli_admin_charges = Double.parseDouble(String.valueOf(contributionData[0][6]));
				pf_percentage = Double.parseDouble(String.valueOf(contributionData[0][7]));
				checkFlag = String.valueOf(contributionData[0][8]);
				emolumentAmtLimit = Double.parseDouble(String.valueOf(contributionData[0][9]));
			}
			String [] monthNames = new String []{"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
			int month1=1;
			int month2=4;
			
			Object[][] wagesDataObj = new Object[12][15];
			for (int i = 0; i < 12; i++) {
				int month=0;
				if(i<9){
					year = Integer.parseInt(bean.getFromYear()); 
					month=month2++;
				}else{
					year = Integer.parseInt(bean.getToYear());
					month=month1++;
				}
				
				/*
				 * Insert the data in HRMS_PF_DATA if not present
				 * 
				 */
				
				  String PFDataQuery = "SELECT * FROM HRMS_PF_DATA WHERE PF_MONTH ="+month+" AND PF_YEAR = "+year 
				  +" AND  PF_EMP_DIV = "+bean.getDivId();
				  
				  Object [][] pfExist = getSqlModel().getSingleResult(PFDataQuery);
				 
				if(!(pfExist !=null && pfExist.length>0)){
				try {

					PFDataModel pfModel = new PFDataModel();
					pfModel.initiate(context, session);
					pfModel.insertPFReportData(String.valueOf(month), String.valueOf(year),
							bean.getDivId());
					pfModel.terminate();

				} catch (Exception e) {
					logger.error("Exception in calling PFData model for inserting PF data"
									+ e);
				}
				}
				/*
				 * End insert data in HRMS_PF_DATA
				 * 
				 */
				
				
				String query = "SELECT DECODE(PF_MONTH,'1','JAN','2','FEB','3','MAR','4','APR','5','MAY','6','JUN','7','JUL','8','AUG','9','SEP','10','OCT','11','NOV','12','DEC')||'-'||"
					+year  
					+ ", " 
					+"CEIL(SUM(PF_EMP_BASIC)),round(SUM(PF_EMP_PF+PF_EMP_F_PF)),CEIL(SUM(PF_CMP_PF)),CEIL(SUM(PF_CMP_F_PF)),SUM(ROUND(PF_EDLI)),COUNT(*)," 
					+ "SUM( CASE WHEN PF_CMP_PF=0 THEN 0"
					+ " ELSE " 
					+ " CASE WHEN ('Y'='Y' AND PF_EMP_BASIC < "
					+emolumentAmtLimit
					+ " )"
					+ " THEN PF_EMP_BASIC WHEN ('Y'='Y' AND PF_EMP_BASIC > "
					+emolumentAmtLimit
					+ " )"
					+ " THEN "
					+emolumentAmtLimit
					+ " ELSE PF_EMP_BASIC END "
					+ " END)" 
					+ ", TO_CHAR(CHALLAN_PAY_DATE,'dd/mm/yy') " 
					+ " FROM HRMS_PF_DATA"
					+ " LEFT JOIN HRMS_PF_CHALLAN ON (HRMS_PF_CHALLAN.CHALLAN_DIVISION = HRMS_PF_DATA.PF_EMP_DIV"
					+ " AND CHALLAN_MONTH=PF_MONTH AND CHALLAN_YEAR=PF_YEAR)"
					+ " WHERE PF_EMP_PF>0 AND PF_MONTH="+month
					+ " AND PF_YEAR ="
					+year  
					+ " AND PF_EMP_DIV="
					+bean.getDivId()
					+ " GROUP BY PF_MONTH, TO_CHAR(CHALLAN_PAY_DATE,'dd/mm/yy')";
				
				Object monthObj[][] = getSqlModel().getSingleResult(query);
				
				Object [][]amountDataObj = new Object[1][15];
				
					if(monthObj!=null && monthObj.length > 0){
						amountDataObj[0][0] = String.valueOf(monthObj[0][0]);
						amountDataObj[0][1] = String.valueOf(monthObj[0][1]);
						amountDataObj[0][2] = Utility.twoDecimals(Double.parseDouble(String.valueOf(monthObj[0][2]))+Double.parseDouble(String.valueOf(monthObj[0][4])));//A/C I
						amountDataObj[0][3] = Utility.twoDecimals(Double.parseDouble(String.valueOf(monthObj[0][5]))* pf_admin_charges/100);//A/C II
						amountDataObj[0][4] = Utility.twoDecimals(Double.parseDouble(String.valueOf(monthObj[0][3])));//A/C X
						amountDataObj[0][5] = Utility.twoDecimals(Double.parseDouble(String.valueOf(monthObj[0][5]))* pf_edli_empcont/100);//A/C XXI
						amountDataObj[0][6] = Utility.twoDecimals(Double.parseDouble(String.valueOf(monthObj[0][5]))* pf_edli_admin_charges/100);//A/C XXII
						
						amountDataObj[0][7] =Utility.twoDecimals(Double.parseDouble(String.valueOf(amountDataObj[0][2])) + Double.parseDouble(String.valueOf(amountDataObj[0][3])) +Double.parseDouble(String.valueOf(amountDataObj[0][4]))+Double.parseDouble(String.valueOf(amountDataObj[0][5]))+Double.parseDouble(String.valueOf(amountDataObj[0][6])));//TOTAL DUES
							
							//Utility.twoDecimals(Double.parseDouble(String.valueOf(monthObj[0][2]))+Double.parseDouble(String.valueOf(monthObj[0][4])))+Math.round(Double.parseDouble(String.valueOf(monthObj[0][1]))* pf_admin_charges/100)+Math.round(Double.parseDouble(String.valueOf(monthObj[0][1]))* pf_comp/100)+Math.round(Double.parseDouble(String.valueOf(monthObj[0][1]))* pf_edli_empcont/100)+Math.round(Double.parseDouble(String.valueOf(monthObj[0][1]))* pf_edli_admin_charges/100);//TOTAL DUES
						
						amountDataObj[0][8] = Utility.twoDecimals(Double.parseDouble(String.valueOf(monthObj[0][2]))+Double.parseDouble(String.valueOf(monthObj[0][4])));//A/C I
						amountDataObj[0][9] = Utility.twoDecimals(Double.parseDouble(String.valueOf(monthObj[0][5]))* pf_admin_charges/100);//A/C II
						amountDataObj[0][10] = Utility.twoDecimals(Double.parseDouble(String.valueOf(monthObj[0][3])));//A/C X
						amountDataObj[0][11] = Utility.twoDecimals(Double.parseDouble(String.valueOf(monthObj[0][5]))* pf_edli_empcont/100);//A/C XXI
						amountDataObj[0][12] = Utility.twoDecimals(Double.parseDouble(String.valueOf(monthObj[0][5]))* pf_edli_admin_charges/100);//A/C XXII
						
						amountDataObj[0][13] = Utility.twoDecimals(Double.parseDouble(String.valueOf(amountDataObj[0][2])) + Double.parseDouble(String.valueOf(amountDataObj[0][3])) +Double.parseDouble(String.valueOf(amountDataObj[0][4]))+Double.parseDouble(String.valueOf(amountDataObj[0][5]))+Double.parseDouble(String.valueOf(amountDataObj[0][6])));
						
						amountDataObj[0][14] = String.valueOf(monthObj[0][8]);//DATE OF PAYMENT
					}else{
						amountDataObj[0][0] = String.valueOf(monthNames[month-1])+"-"+year;
						for (int j = 1; j < amountDataObj[0].length-1; j++) {
							amountDataObj[0][j]= "0.00";
						}
						amountDataObj[0][14] = "    ";
					}
					
					if(amountDataObj!=null && amountDataObj.length > 0){
						for (int j = 0; j < amountDataObj[0].length; j++) {
							wagesDataObj[i][j] = String.valueOf(amountDataObj[0][j]);
						}
					}
					month++;
			
			}
			
			if(wagesDataObj!=null && wagesDataObj.length > 0){
				TableDataSet pfData = new TableDataSet();
				pfData.setHeader(header);
				pfData.setData(wagesDataObj);
				pfData.setColumnSum(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13});
				pfData.setCellAlignment(alignment);
				pfData.setCellWidth(cellwidth);
				pfData.setHeaderBorderDetail(3);
				pfData.setBorder(true);
				pfData.setHeaderTable(true);
				pfData.setBorderDetail(3);
				//pfData.setHeaderBGColor(new BaseColor(225, 225, 225));
				pfData.setBlankRowsBelow(1);
				rg.addTableToDoc(pfData);
				
				Object [][]divisionDataObj = new Object[3][1];
				//divisionDataObj[0][0]="";
				divisionDataObj[0][0]="For "+bean.getDivName();
				//divisionDataObj[1][0]="";
				divisionDataObj[1][0]="";
				divisionDataObj[2][0]="Partner";
				//divisionDataObj[3][0]="Partner";
				
				TableDataSet divisionData = new TableDataSet();
				divisionData.setCellAlignment(new int[] { 2 });
				divisionData.setCellWidth(new int[] { 100 });
				divisionData.setData(divisionDataObj);
				divisionData.setBorderDetail(0);
				divisionData.setCellColSpan(new int[]{15});
				divisionData.setHeaderTable(false);
				divisionData.setBlankRowsBelow(1);
				divisionData.setBodyFontStyle(1);
				rg.addTableToDoc(divisionData);
				
			}else{
				TableDataSet noData = new TableDataSet();
				noData.setData(new Object[][] { {""}, { "No records avaliable for selected criteria" } });
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				/*noData.setBodyFontDetails(Font.FontFamily.HELVETICA, 10, Font.BOLD,
						new BaseColor(0, 0, 0));*/
				noData.setBorder(false);
				noData.setHeaderTable(false);
				rg.addTableToDoc(noData);
			}
			rg.process();
			if(reportPath.equals("")){
				rg.createReport(response);
			}else{
				
				rg.saveReport(response);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
