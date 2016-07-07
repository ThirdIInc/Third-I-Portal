package org.paradyne.model.payroll.salary;

import java.awt.Color;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.salary.Form12BA;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

import org.paradyne.lib.ireportV2.ReportDataSet;

import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Font.FontFamily;

//import org.paradyne.lib.report.ReportGenerator;


//import com.lowagie.text.pdf.PdfPTable;

public class Form12BAModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class);
	
	public void generateReport(Form12BA frm12, HttpServletResponse response,
			HttpServletRequest request, String reportPath, String[] data) {
		try {
			ReportDataSet rds = new ReportDataSet();
			
			String type = frm12.getRptType();
			System.out.println("type==="+type);
			rds.setReportType(type);
			String fileName = frm12.getEmpName()+Utility.getRandomNumber(1000);
			String reportPathName=reportPath+fileName+"."+type;
			rds.setFileName(fileName);
			rds.setReportName("FORM NO. 12BA");
			
			rds.setPageSize("A4");
			rds.setReportHeaderRequired(false);
			
			rds.setShowPageNo(true);
			
			org.paradyne.lib.ireportV2.ReportGenerator rg=null;
			
			if(reportPath.equals("")){
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context,request);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName+"."+type);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context,request);
				request.setAttribute("reportPath", reportPath+fileName+"."+type);
				request.setAttribute("fileName", fileName+"."+type);
				request.setAttribute("action", "Form12BA_input.action");
			}
			
			
		
			rg = getReport( rg, data);
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
	
	
	public org.paradyne.lib.ireportV2.ReportGenerator getReport(org.paradyne.lib.ireportV2.ReportGenerator rg, String[] data) {	
		try {
			
			Object[][]headingForm16 = new Object[1][1];
			headingForm16[0][0] = "FORM NO. 12BA";
			int [] cellWidthheadingForm16={100};
			int [] cellAlignheadingForm16={1};
			TableDataSet tableheadingForm16 = new TableDataSet();
			tableheadingForm16.setData(headingForm16);
			tableheadingForm16.setBodyFontStyle(1);
			tableheadingForm16.setCellWidth(cellWidthheadingForm16);
			tableheadingForm16.setCellAlignment(cellAlignheadingForm16);
			tableheadingForm16.setBorderDetail(0);
			//tableheadingForm16.setBodyFontDetails(Font.FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(0, 0, 0));
			rg.addTableToDoc(tableheadingForm16);
			
			Object[][]headingFormSubHead = new Object[1][1];
			headingFormSubHead[0][0] = "[See rule 26A(2)(b)]";
			int [] cellWidthheadingSubHead={100};
			int [] cellAlignheadingSubHead={1};
			TableDataSet tableheadingSubHead = new TableDataSet();
			tableheadingSubHead.setData(headingFormSubHead);
			tableheadingSubHead.setCellWidth(cellWidthheadingSubHead);
			tableheadingSubHead.setCellAlignment(cellAlignheadingSubHead);
			tableheadingSubHead.setBorderDetail(0);
			//tableheadingForm16.setBodyFontDetails(Font.FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(0, 0, 0));
			rg.addTableToDoc(tableheadingSubHead);
			
			Object[][]heading = new Object[1][1];
			//heading[0][0] = "[See rule 26A(2)]";
			heading[0][0] = "Statement showing particulars of perquisites,other fringe benefits " +
					"or amenities and profits in lieu of salary with value thereof";
			int [] cellWidthHeader={100};
			int [] cellAlignHeader={1};
			TableDataSet tableheading = new TableDataSet();
			tableheading.setData(heading);
			tableheading.setBodyFontStyle(1);
			tableheading.setCellWidth(cellWidthHeader);
			tableheading.setCellAlignment(cellAlignHeader);
			tableheading.setBorderDetail(0);
			tableheading.setBlankRowsBelow(1);
			//PdfPTable pTableheading = rg.createTable(tableheading);
			rg.addTableToDoc(tableheading);
			
			Object [][] divData = null;
			try {
				String sqlDiv =" SELECT DIV_NAME||'\n'|| DIV_ADDRESS1 ||'\n '|| DIV_ADDRESS2 ||'\n'||DIV_ADDRESS3,NVL(DIV_TANNO,' ') FROM HRMS_DIVISION "
					   +" WHERE DIV_ID ="+String.valueOf(data[5]);
				divData = getSqlModel().getSingleResult(sqlDiv);
			} catch (Exception e) {
				logger.error("exception in div data",e);
			} //end of catch
		
			
			
			Object [][] result = new  Object[4][2];
			result[0][0] ="1. Name and address of employer : "; 
			result[0][1] =divData[0][0]; 
			result[1][0] ="2. TAN -"; 		   
			result[1][1] =divData[0][1]; 		   
			result[2][0] ="3. TDS Assessment Range of the employer :";
			result[2][1] ="DCIT(TDS)1(2) ";
			result[3][0] ="4. Name,Designation and PAN of Employee : ";
			result[3][1] =String.valueOf(data[1])+"\n"+String.valueOf(data[4])+"\n"+String.valueOf(data[6]);
			int cellwidth[] = {40,60};
			int alignment[] = {0,0}; 
			TableDataSet tableresult = new TableDataSet();
			tableresult.setData(result);
			tableresult.setCellWidth(cellwidth);
			tableresult.setCellAlignment(alignment);
			tableresult.setBorderDetail(0);
			//PdfPTable pTableresult = rg.createTable(tableresult);
			rg.addTableToDoc(tableresult);
			
			Object [][] sqlData = null;
			try {
				String tabSql = " SELECT PERQ_NAME,NVL(TDS_PERQ_AMOUNT,0) FROM HRMS_PERQUISITE_HEAD "
					+" LEFT JOIN  HRMS_TDS_PERQ ON (HRMS_TDS_PERQ.TDS_PERQ_HEAD = HRMS_PERQUISITE_HEAD.PERQ_CODE) "
					+"	WHERE TDS_EMP_ID ="+String.valueOf(data[0])+" AND TDS_YEAR_FROM ="+String.valueOf(data[2])+" AND TDS_YEAR_TO ="+String.valueOf(data[3])+"  ORDER BY HRMS_PERQUISITE_HEAD.PERQ_CODE "; 
				sqlData = getSqlModel().getSingleResult(tabSql);	
				 
			 if(!(sqlData !=null && sqlData.length >0))
			  { 
				String perSqlQuery ="SELECT  PERQ_NAME, '0' AS PER_AMOUNT FROM HRMS_PERQUISITE_HEAD ORDER BY HRMS_PERQUISITE_HEAD.PERQ_CODE ";
				sqlData = getSqlModel().getSingleResult(perSqlQuery);	
			  }
			} catch (Exception e) {
				logger.error("exception in perq table",e);
			} //end of catch
			 int count = 1;
			 float  valOfPrequisit =0;
			 float totPrequisit =0;
			 float valeOfRecover =0;
			 Object[][] tabData = new Object[sqlData.length+2][5]; 
			 if(sqlData !=null && sqlData.length >0)
			 { 
				for ( int i =0 ;i <sqlData.length ; i++)
				 {
				 	tabData[i][0] = ""+count;
				 	tabData[i][1] = ""+sqlData[i][0];
				 	tabData[i][2] = ""+Math.round(Float.parseFloat(""+sqlData[i][1]));
				 	tabData[i][3] = ""+Math.round(Float.parseFloat(""+0));
				 	tabData[i][4] = Float.parseFloat(""+tabData[i][2]) - Float.parseFloat(""+tabData[i][3]);
				 	valOfPrequisit += Float.parseFloat(""+tabData[i][2]);
				 	totPrequisit += Float.parseFloat(""+tabData[i][4]);
				 	valeOfRecover+= Float.parseFloat(""+tabData[i][3]);
				 	count++;
				   } 
				tabData[sqlData.length][0]=count++;
				tabData[sqlData.length][1]="Total value of perquisities";
				tabData[sqlData.length][2]=""+Math.round(valOfPrequisit);
				tabData[sqlData.length][3]= ""+Math.round(valeOfRecover);
				tabData[sqlData.length][4]=""+Math.round(totPrequisit);
				
				tabData[sqlData.length+1][0]=count++;
				tabData[sqlData.length+1][1]="Total value of profits in lieu of salary as per 1 7(3)";
				tabData[sqlData.length+1][2]="0";
				tabData[sqlData.length+1][3]="0";
				tabData[sqlData.length+1][4]="0";
			 }
			 
			 String grossSql =" SELECT TDS_GROSS_SALARY FROM HRMS_TDS "
 				+" WHERE TDS_EMP_ID ="+String.valueOf(data[0])+" AND TDS_FROM_YEAR ="+String.valueOf(data[2])+" AND  TDS_TO_YEAR ="+String.valueOf(data[3]);
			 Object [][] grossSalData = getSqlModel().getSingleResult(grossSql);
			 String grossSalAmt ="0";
			 if(grossSalData!= null && grossSalData.length >0)
			 {
			 	grossSalAmt = ""+grossSalData[0][0];
			 }
			 Object [][] dataLabel = new Object[4][2];	    
			 dataLabel [0][0] = "5. Is the employee a director or a person with : \n   Substantial interest in the company (where the employer is a compny)";
			 dataLabel [0][1] = "NO";
			 dataLabel [1][0]= "6. Income undrer the head Salaries of the employee: ";
			 dataLabel [1][1]= "Rs. "+grossSalAmt;
			 dataLabel [2][0] = "7. Financial Year : ";
			 dataLabel [2][1] = String.valueOf(data[2]) +" - "+String.valueOf(data[3]);
			 dataLabel [3][0]= "8. Valuation of Perquisites : ";  
			 dataLabel [3][1]= "Rs. "+Math.round(valOfPrequisit);  
			 /*int[] bcellWidth = {100};
			 int[] bcellAlign = {0}; */
			 TableDataSet tabledataLabel = new TableDataSet();
			 tabledataLabel.setData(dataLabel);
			 tabledataLabel.setCellWidth(cellwidth);
			 tabledataLabel.setCellAlignment(alignment);
			 tabledataLabel.setBorderDetail(0);
			 tabledataLabel.setBlankRowsBelow(1);
			 //PdfPTable pTabledataLabel = rg.createTable(tabledataLabel);
			 rg.addTableToDoc(tabledataLabel);
			 
			 String[][] tableHeader = new String[2][5];
			 tableHeader[0][0] = "Sr.No";
			 tableHeader[0][1] = "Nature of perquisite \n(see rule 3) \n (Rs.)";
			 tableHeader[0][2] = "Value of perquisite as per rules \n (Rs.)";
			 tableHeader[0][3] = "Amount,if any recovered from the employee \n (Rs.)";
			 tableHeader[0][4] = "Amount of perquisite chargeable to tax col(3)-col(4)\n(Rs.)";
			 
			 tableHeader[1][0] ="(1)";
			 tableHeader[1][1] ="(2)";
			 tableHeader[1][2] ="(3)";
			 tableHeader[1][3] ="(4)";
			 tableHeader[1][4] ="(5)";
			 
			 int cellwidthTab[] = {8,38,18,18,18};
			 int alignmentTab[] = {1,0,2,2,2}; 
			 TableDataSet tableHeaderPerq = new TableDataSet();
			 tableHeaderPerq.setHeaderData(tableHeader);
			 tableHeaderPerq.setHeaderBorderDetail(3);
			 tableHeaderPerq.setCellWidth(cellwidthTab);
			 tableHeaderPerq.setCellAlignment(alignmentTab);
			 tableHeaderPerq.setBorderDetail(3);
			
			 //PdfPTable pTableHeader = rg.createTable(tableHeaderPerq);
			// rg.addTableToDoc(tableHeaderPerq);
			 
			 int cellwidthPerqData[] = {8,38,18,18,18};
			 int alignmentPerqData[] = {1,0,2,2,2}; 
			 TableDataSet tablealignmentPerqData = new TableDataSet();
			 tablealignmentPerqData.setHeaderData(tableHeader);
			 tablealignmentPerqData.setHeaderBorderDetail(3);
			 tablealignmentPerqData.setData(tabData);
			 tablealignmentPerqData.setCellWidth(cellwidthPerqData);
			 tablealignmentPerqData.setCellAlignment(alignmentPerqData);
			 tablealignmentPerqData.setBorderDetail(3);
			 tablealignmentPerqData.setHeaderTable(true);
			 //PdfPTable pTablePerqData = rg.createTable(tablealignmentPerqData);
			 rg.addTableToDoc(tablealignmentPerqData);
			 
			 Object[][]details = new Object[1][1];
			 details[0][0] = "9. Details of tax-\n\n";
			 int[] cellWidthdetails = {100};
			 int[] cellAligndetails = {0}; 
			 TableDataSet tabledetails = new TableDataSet();
			 tabledetails.setData(details);
			 tabledetails.setCellWidth(cellWidthdetails);
			 tabledetails.setCellAlignment(cellAligndetails);
			 tabledetails.setBorderDetail(0);
			// PdfPTable pTabledetails = rg.createTable(tabledetails);
			 rg.addTableToDoc(tabledetails);
			 
			 	Object [][] taxdata = new Object[4][3];		    
				int cellwidthDtl[] = {8,55,37};
				int alignmentDtl[] = {1,0,0}; 
				taxdata[0][0] ="(a)";
				taxdata[0][1] ="Tax deducted from salary of the employee under section 192(1)";
				taxdata[0][2] =" ";
				
				taxdata[1][0] ="(b)";
				taxdata[1][1] ="Tax paid by employer on behalfof the employee under section 192(1A)";
				taxdata[1][2] ="As stated in para 16 of form";
				
				taxdata[2][0] ="(c)";
				taxdata[2][1] ="Total tax paid ";
				taxdata[2][2] ="No.16 of even date issued.";
				
				taxdata[3][0] ="(d)";
				taxdata[3][1] ="Date of payment into Government treasury ";
				taxdata[3][2] ="As stated on page 3 of Form No.16 of even date issued."; 
				
				 TableDataSet tabletaxdata = new TableDataSet();
				 tabletaxdata.setData(taxdata);
				 tabletaxdata.setCellWidth(cellwidthDtl);
				 tabletaxdata.setCellAlignment(alignmentDtl);
				 tabletaxdata.setBorderDetail(3);
				 tabletaxdata.setBlankRowsBelow(1);
				 //PdfPTable pTabletaxdata = rg.createTable(tabletaxdata);
				 rg.addTableToDoc(tabletaxdata);
			 
				 Object[][]declaration = new Object[1][1];
				 declaration[0][0] = "DECLARATION BY EMPLOYER";
				 int[] cellWidthdeclaration = {100};
				 int[] cellAligndeclaration = {1}; 
				 TableDataSet tabledeclaration = new TableDataSet();
				 tabledeclaration.setData(declaration);
				 tabledeclaration.setCellWidth(cellWidthdeclaration);
				 tabledeclaration.setCellAlignment(cellAligndeclaration);
				 tabledeclaration.setBodyFontStyle(1);
				 tabledeclaration.setBorderDetail(0);
				 tabledeclaration.setBlankRowsAbove(1);
				 //PdfPTable pTabledeclaration = rg.createTable(tabledeclaration);
				 rg.addTableToDoc(tabledeclaration);
				 
				 String AuthSql ="   SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,NVL(FML_FNAME ||' '|| FML_MNAME ||' '||FML_LNAME,' ') , NVL(RANK_NAME,' ') ,NVL(DIV_NAME ,' '),"
					    +"  NVL(LOCATION_NAME,' ') ,TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER  "
						+"	LEFT JOIN HRMS_EMP_OFFC  ON( HRMS_TAX_PARAMETER.TDS_SIGNING_AUTH = HRMS_EMP_OFFC.EMP_ID) "
						+"  LEFT JOIN HRMS_EMP_FML ON (HRMS_EMP_FML.EMP_ID =   HRMS_EMP_OFFC.EMP_ID ) "
						+"	LEFT JOIN HRMS_RELATION ON (HRMS_RELATION.RELATION_CODE =  HRMS_EMP_FML.FML_RELATION) "
						+"	LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK ) "
						+"	LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV ) "
						+"  LEFT JOIN HRMS_LOCATION ON(HRMS_DIVISION.DIV_CITY_ID = HRMS_LOCATION.LOCATION_CODE)"
						+"  WHERE RELATION_NAME ='FATHER' AND HRMS_TAX_PARAMETER.TDS_FINANCIALYEAR_FROM="+String.valueOf(data[2])+" ";
			 Object [][] authData = getSqlModel().getSingleResult(AuthSql);
			 String authPerson ="________________";
			 String authPersonMid ="________________";
			 String authPersonDesg ="________________";
			 String authPersonDiv ="________________";
			 String authPlace ="";
			 String sysDate ="";
			 if(authData != null && authData.length >0)
			 {
				 authPerson=""+authData[0][0];
				 authPersonMid=""+authData[0][1];
				 authPersonDesg=""+authData[0][2];
				 authPersonDiv=""+authData[0][3];
				 authPlace=""+authData[0][4];
				 sysDate=""+authData[0][5];
			 }
			 String dclLabel ="I  "+authPerson+" S/o. "+authPersonMid+" working as ("+authPersonDesg+") do hereby declare on behalf of "+authPersonDiv+".that the information given above " 
				+" based on the books of account,documents and other relevant records or information available with us and the details of value of each such perquisite are in accordance with section 17 and riles framed"
			    +" there under and that such information is true and correct.";
			Object [][] authDataDisp = new Object[1][1];
			authDataDisp[0][0]=dclLabel;
			int cellwidthAth[] ={100};
			int alignmentAth[] ={0}; 
			TableDataSet tableauthDataDisp = new TableDataSet();
			tableauthDataDisp.setData(authDataDisp);
			tableauthDataDisp.setCellWidth(cellwidthAth);
			tableauthDataDisp.setCellAlignment(alignmentAth);
			tableauthDataDisp.setBorderDetail(0);
			// PdfPTable pTableauthDataDisp= rg.createTable(tableauthDataDisp);
			 rg.addTableToDoc(tableauthDataDisp);
			 
			 
			 String signLabel ="Signature of the person responsible for deduction of tax";
			Object [][] signDataDisp = new Object[1][1];
			signDataDisp[0][0]=signLabel;
			int cellwidthSign[] ={100};
			int alignmentSign[] ={2}; 
			TableDataSet tableSignDataDisp = new TableDataSet();
			tableSignDataDisp.setData(signDataDisp);
			tableSignDataDisp.setCellWidth(cellwidthSign);
			tableSignDataDisp.setCellAlignment(alignmentSign);
			tableSignDataDisp.setBorderDetail(0);
			tableSignDataDisp.setBodyFontStyle(1);
			tableSignDataDisp.setBlankRowsAbove(1);
			// PdfPTable pTableauthDataDisp= rg.createTable(tableauthDataDisp);
			 rg.addTableToDoc(tableSignDataDisp);
			 
			 
			 
			 
			 Object [][] bottomData = new Object [2][3];
			 int cellwidthbottomData[] = {30,37,30};
			 int alignmentbottomData[] = {0,0,0}; 
			 
			 bottomData[0][0] ="Place : "+authPlace;
			 bottomData[0][1] =""; 
			 bottomData[0][2] ="Full Name  "+authPerson; 
			 
			 bottomData[1][0] ="Date : "+sysDate;
			 bottomData[1][1] =""; 
			 bottomData[1][2] ="Designation  "+authPersonDesg;
			 		       
			
			 
			 TableDataSet tablebottomData = new TableDataSet();
			 tablebottomData.setData(bottomData);
			 tablebottomData.setCellWidth(cellwidthbottomData);
			 tablebottomData.setCellAlignment(alignmentbottomData);
			 tablebottomData.setBorderDetail(0);
			 tablebottomData.setBlankRowsAbove(1);
			 //PdfPTable pTablebottomData= rg.createTable(tablebottomData);
			 rg.addTableToDoc(tablebottomData);
			
		} catch (Exception e) {
			logger.error("exception in generatin from 12BA report",e);
			e.printStackTrace();
		}
		logger.info("############### IN Form12BAModel ############### ");
		return rg;
	}

	


}
