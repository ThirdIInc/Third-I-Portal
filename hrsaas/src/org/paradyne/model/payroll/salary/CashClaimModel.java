/**
 * 
 */
package org.paradyne.model.payroll.salary;
import javax.servlet.http.HttpServletResponse;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.payroll.salary.CashClaim;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;
import org.paradyne.model.common.ApplCodeTemplateModel;

import com.lowagie.text.Font;
import com.lowagie.text.Image;

/**
 * @author varunk
 * @modified by MangeshJ
 */
public class CashClaimModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);

	/**Added on Date:23-12-2008 by Pradeep Kumar Sahoo
	 * following function is called to generate the report in pdf format.
	 * @param bean
	 * @param response
	 */
	
	public void generateReportOld(CashClaim bean,HttpServletResponse response) {
		try{
			String reportType=new String("Pdf");	
			String reportName="Cash Claim Report";		
	
		org.paradyne.lib.report.ReportGenerator rg=new org.paradyne.lib.report.ReportGenerator(reportType,reportName);
		String empQuery = "SELECT  HRMS_EMP_OFFC.EMP_TOKEN  ,   HRMS_EMP_OFFC.EMP_FNAME||' '|| "
			+" HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,  "
			+" NVL(HRMS_CENTER.CENTER_NAME,' '),NVL(HRMS_RANK.RANK_NAME,' '),  HRMS_EMP_OFFC.EMP_ID,NVL(TO_CHAR(CLAIM_DATE,'DD-MM-YYYY'),' '), "
			+" NVL(CLAIM_PARTICULARS,' '),NVL(CLAIM_TOTAL_AMOUNT,0),CASE WHEN CLAIM_STATUS='A' THEN 'Approved' WHEN CLAIM_STATUS='P' THEN 'Pending' WHEN CLAIM_STATUS='D' THEN 'Disbursed' END   "
			+" FROM HRMS_CASH_CLAIM_HDR  "
			+" LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_CASH_CLAIM_HDR.CLAIM_EMPID) " 
			+" INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
			+" INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) " 
			//+" LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) "  
			+" LEFT JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT  "
			+" WHERE CLAIM_ID ="+bean.getClaimId()+" ";
			Object empData[][] = getSqlModel().getSingleResult(empQuery);
			
			
			
			
			Object[][] emp=new Object[3][6];
			emp[0][0]="Emp Id ";emp[0][1]=":";emp[0][2]=""+empData[0][0]; emp[0][3]="Name ";emp[0][4]=":";emp[0][5]=""+empData[0][1];
			
			emp[1][0]="Branch ";emp[1][1]=":";emp[1][2]=""+empData[0][2]; emp[1][3]="Designation ";emp[1][4]=":";emp[1][5]=""+empData[0][3];
			                                                                                                                     		
			emp[2][0]="Claim Date";emp[2][1]=":";emp[2][2]=""+empData[0][5];emp[2][3]="Claim Status";emp[2][4]=":"; emp[2][5]=""+empData[0][8];
			//emp[3][0]="Cash Claim Code ";emp[3][1]=":";emp[3][2]=""+bean.getClaimId();emp[3][3]="";emp[3][4]="";emp[3][5]="";
			
			int[] cellWidth={6,3,10,6,3,12};
			int[] align={0,0,0,0,0,0};
			rg.addTextBold("Cash Claim Report",0,1,0);
			rg.addText("\n",0,1,0);
			rg.addText("Cash Requisition Code :"+bean.getClaimId(), 0,0,0);
			rg.addText("\n",0,1,0);
			rg.tableBodyNoCellBorder(emp, cellWidth, align,0);
			rg.addText("\n",0,1,0);
			rg.addText("Particulars :"+String.valueOf(empData[0][6]), 0,0,0);
			rg.addText("\n", 0,0,0);
			double totBal=0.00;
			double totClaim=0.00;
			
			
			String query = "SELECT CREDIT_NAME,nvl(CLAIM_AMOUNT,0),CASE WHEN CLAIM_ISPROOF='Y' THEN 'Yes' WHEN CLAIM_ISPROOF='N' THEN 'No' END ,HRMS_CASH_CLAIM_DTL.CLAIM_CREDIT_CODE"
				+" FROM HRMS_CASH_CLAIM_HDR "
				+"INNER JOIN HRMS_CASH_CLAIM_DTL ON (HRMS_CASH_CLAIM_DTL.CLAIM_ID = HRMS_CASH_CLAIM_HDR.CLAIM_ID) "
				+"INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_CASH_CLAIM_DTL.CLAIM_CREDIT_CODE) "
				+"WHERE CLAIM_EMPID="+bean.getEmpId()+" and HRMS_CASH_CLAIM_HDR.claim_id="+bean.getClaimId()+" order by CLAIM_CREDIT_CODE ";
			
			Object amount[][] = getSqlModel().getSingleResult(query);
			
		
			Object[][] amt=new Object[amount.length][5];
			if(amount!=null && amount.length>0){
			int s=1;
			for(int i=0;i<amount.length;i++){
				String balQuery = "SELECT nvl(CASH_BALANCE_AMT,0) FROM HRMS_CASH_BALANCE  "
					+" WHERE EMP_ID="+bean.getEmpId()+ " AND CASH_CREDIT_CODE="+amount[i][3];
				Object[][] bal=getSqlModel().getSingleResult(balQuery);
				
				amt[i][0]=s++;
				amt[i][1]=String.valueOf(amount[i][0]);
				
				if(bal!=null && bal.length>0){
						if(!(String.valueOf(bal[0][0]).equals("") || String.valueOf(bal[0][0]).equals("null"))){
							
							amt[i][2]=Utility.twoDecimals(Math.round(Double.parseDouble(String.valueOf(bal[0][0]))+Double.parseDouble(String.valueOf(amount[i][1]))));
						}else{
							amt[i][2]=Utility.twoDecimals(Math.round(Double.parseDouble(String.valueOf(amount[i][1]))));
						}
					
				}else{
					amt[i][2]=Utility.twoDecimals(Math.round(Double.parseDouble(String.valueOf(amount[i][1]))));
				}
				totBal+=Double.parseDouble(String.valueOf(amt[i][2]));
				amt[i][3]=Utility.twoDecimals(Math.round(Double.parseDouble(String.valueOf(amount[i][1]))));	
				totClaim+=Double.parseDouble(String.valueOf(amt[i][3]));
				amt[i][4]=amount[i][2];
				
			}//End of for loop
			
			
			String[] col={"Srl. No.","Credit Name","Balance Amount","Claim Amount","Proof Attached"};
			int[] cellwidth={5,20,10,10,8};
			int[] alignment={1,0,2,2,1};
			rg.addTextBold("\n\nCash Claim Details :",0,0,0);
			rg.tableBody(col,amt, cellwidth, alignment);
			Object[][] other=new Object[1][6];
			other[0][0]="";other[0][1]="Total Amount";other[0][2]=""+Utility.twoDecimals(totBal);other[0][3]=""+Utility.twoDecimals(totClaim);other[0][4]="";
			rg.tableBody(other, cellwidth, alignment);
			
			
			
			String approveQuery=" SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,NVL(TO_CHAR(APPR_DATE,'DD-MM-YYYY'),' ') ,NVL(CASH_REMARK,' '),EMP_TOKEN FROM"
				+" HRMS_CASH_CLAIM_PATH INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_CASH_CLAIM_PATH.APPROVER_CODE)"
				+" WHERE CASH_CODE="+bean.getClaimId()+" ORDER BY APPROVER_CODE";
			Object[][] approver=getSqlModel().getSingleResult(approveQuery);
			
			if(approver!=null && approver.length>0){
				int[] apprvCell={5,10,15,10,10};
				int[] apprvAlign={1,1,0,1,0};
				String column[]={"Srl. No.","Approver Id","Approver Name","Approved Date","Remark"};
				int count=1;
				Object[][] data=new Object[approver.length][5];
				for(int i=0;i<approver.length;i++){
				data[i][0]=count++;
				data[i][1]=approver[i][3];//Emp Id
				data[i][2]=approver[i][0];//Emp Name
				data[i][3]=approver[i][1];//Approved Date
				data[i][4]=approver[i][2];//Remark
				}
				rg.addText("\n\n",0,0,0);
				rg.addTextBold("Approver Details :",0,0,0);
				rg.tableBody(column, data, apprvCell,apprvAlign);
				
			}//End of approver length
			
		
			
			}//End if
			
			rg.createReport(response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	
		
		
		
	}
	
	public void generateReport(CashClaim bean,HttpServletResponse response) {
		try{
			String reportName="Cash Claim Report";		
			ReportDataSet rds = new ReportDataSet();
			rds.setReportType("Pdf");
			rds.setFileName(reportName);
		org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(rds);
		
		Object [][]nameObj=new Object[1][1];
		boolean isLogo = false;
		String companyDtlQuery="SELECT COMPANY_CODE,COMPANY_LOGO,COMPANY_NAME,NVL(COMPANY_ADDRESS,'') FROM HRMS_COMPANY";
		  Object companyDtl[][]=getSqlModel().getSingleResult(companyDtlQuery);
		try{		
			
			  if(companyDtl!=null && companyDtl.length>0)
			  {
				  String filename="";
				  if(!String.valueOf(companyDtl[0][1]).equals(""))
				  {
					  filename=String.valueOf(companyDtl[0][1]);
					  String filePath = context.getRealPath("/")+"pages/Logo/"+session.getAttribute("session_pool")+"/"+filename;
					  nameObj[0][0]=Image.getInstance(filePath);
					  isLogo = true;
						
				 }else
					  nameObj[0][0]="";
				
			  }else{
				  nameObj[0][0]="";
			  }
			//Image im=Image.getInstance("C:/hrwork/dataFiles/sal_logo.jpg");
			
	//	nameObj[0][1]=divisionName+"\n\n  "+title+" "+subTitle;
		}catch(Exception eee)
		{
			logger.info("when assign the image...!"+eee);
		
			nameObj[0][0]=" ";
			//nameObj[0][1]=divisionName+"\n\n  "+title+" "+subTitle;
		}
		String companyName="",companyAddress="";
		
		if(companyDtl!=null && companyDtl.length>0)
		{
			companyName=String.valueOf(companyDtl[0][2]);
			companyAddress=String.valueOf(checkNull(""+companyDtl[0][3]));
			
		}
		TableDataSet logoData = new TableDataSet();
		logoData.setData(nameObj);
		logoData.setCellAlignment(new int[]{0});
		logoData.setCellWidth(new int[]{100});
		logoData.setBorder(false);
		//PdfPTable nameTable0 = rg.createTable(titleName);
		
		Object [][]titleObj=new Object[1][1];
		titleObj[0][0]=""+companyName;
		
		TableDataSet titleName = new TableDataSet();
		titleName.setData(titleObj);
		titleName.setCellAlignment(new int[]{1});
		titleName.setCellWidth(new int[]{100});
		titleName.setBodyFontDetails(Font.HELVETICA,12, Font.BOLD, new Color(0,0,0));		
		titleName.setBorder(false);
		
		Object [][]subtitleObj=new Object[1][1];
		subtitleObj[0][0]=""+companyAddress;
		
		TableDataSet subtitleName = new TableDataSet();
		subtitleName.setData(subtitleObj);
		subtitleName.setCellAlignment(new int[]{1});
		subtitleName.setCellWidth(new int[]{100});
		subtitleName.setBodyFontDetails(Font.HELVETICA,8, Font.BOLD, new Color(0,0,0));		
		subtitleName.setBorder(false);
		
		Object [][]subtitleObj2=new Object[1][1];
		subtitleObj2[0][0]=reportName;
		
		TableDataSet subtitleName2 = new TableDataSet();
		subtitleName2.setData(subtitleObj2);
		subtitleName2.setCellAlignment(new int[]{1});
		subtitleName2.setCellWidth(new int[]{100});
		subtitleName2.setBodyFontDetails(Font.HELVETICA,10, Font.BOLD, new Color(0,0,0));		
		subtitleName2.setBorder(false);
		
		HashMap<String,Object> mapOne = rg.joinTableDataSet(titleName, subtitleName, false,0);
		
		HashMap<String,Object> mapTwo = rg.joinTableDataSet(mapOne,subtitleName2, false,0);
		HashMap<String,Object> mapThree = null;
		if(isLogo)
			mapThree = rg.joinTableDataSet(logoData,mapTwo, true,30);
		else
			mapThree = rg.joinTableDataSet(logoData,mapTwo, true,5);
		
		//rg.createHeader(mapThree);
		rg.addTableToDoc(mapThree);
		
		
		String empQuery = "SELECT  HRMS_EMP_OFFC.EMP_TOKEN  ,   HRMS_EMP_OFFC.EMP_FNAME||' '|| "
			+" HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,  "
			+" NVL(HRMS_CENTER.CENTER_NAME,' '),NVL(HRMS_RANK.RANK_NAME,' '),  HRMS_EMP_OFFC.EMP_ID,NVL(TO_CHAR(CLAIM_DATE,'DD-MM-YYYY'),' '), "
			+" NVL(CLAIM_PARTICULARS,' '),NVL(CLAIM_TOTAL_AMOUNT,0),CASE WHEN CLAIM_STATUS='A' THEN 'Approved' WHEN CLAIM_STATUS='P' THEN 'Pending' WHEN CLAIM_STATUS='D' THEN 'Disbursed' END   "
			+" ,TO_CHAR(NVL(CLAIM_VOUCHER_TOTAL_AMOUNT,0),999999999999990.99), CLAIM_APPL_NO FROM HRMS_CASH_CLAIM_HDR  "
			+" LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_CASH_CLAIM_HDR.CLAIM_EMPID) " 
			+" INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
			+" INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) " 
			//+" LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) "  
			+" LEFT JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT  "
			+" WHERE CLAIM_ID ="+bean.getClaimId()+" ";
			Object empData[][] = getSqlModel().getSingleResult(empQuery);
			
			
			
			
			Object[][] emp=new Object[4][6];
			emp[0][0]="Emp Id :";emp[0][1]=""+empData[0][0]; emp[0][2]="Name ";emp[0][3]=""+empData[0][1];
			
			emp[1][0]="Branch ";emp[1][1]=""+empData[0][2]; emp[1][2]="Designation ";emp[1][3]=""+empData[0][3];
			                                                                                                                     		
			emp[2][0]="Claim Date";emp[2][1]=""+empData[0][5];emp[2][2]="Claim Status"; emp[2][3]=""+empData[0][8];
			
			emp[3][0]="Cash Requisition Code";emp[3][1]=""+checkNull(String.valueOf(empData[0][10]));emp[3][2]=""; emp[3][3]="";
			//emp[3][0]="Cash Claim Code ";emp[3][1]=":";emp[3][2]=""+bean.getClaimId();emp[3][3]="";emp[3][4]="";emp[3][5]="";
			
			
			int[] cellWidth={25,25,25,25};
			int[] align={0,0,0,0};
			//rg.addTextBold("Cash Claim Report",0,1,0);
			//rg.addText("\n",0,1,0);
			//rg.addText("Cash Requisition Code :"+bean.getClaimId(), 0,0,0);
			//rg.addText("\n",0,1,0);
			//rg.tableBodyNoCellBorder(emp, cellWidth, align,0);
			//rg.addText("\n",0,1,0);
			//rg.addText("Particulars :"+String.valueOf(empData[0][6]), 0,0,0);
			//rg.addText("\n", 0,0,0);
			double totBal=0.00;
			double totClaim=0.00;
			
			TableDataSet tdsEmpData = new TableDataSet();
			tdsEmpData.setData(emp);
			tdsEmpData.setBorder(true);
			tdsEmpData.setCellAlignment(align);
			tdsEmpData.setCellWidth(cellWidth);
			tdsEmpData.setBlankRowsAbove(1);
			rg.addTableToDoc(tdsEmpData);
			
			String query = "SELECT CREDIT_NAME,nvl(CLAIM_AMOUNT,0),CASE WHEN CLAIM_ISPROOF='Y' THEN 'Yes' WHEN CLAIM_ISPROOF='N' THEN 'No' END ,HRMS_CASH_CLAIM_DTL.CLAIM_CREDIT_CODE"
				+" FROM HRMS_CASH_CLAIM_HDR "
				+"INNER JOIN HRMS_CASH_CLAIM_DTL ON (HRMS_CASH_CLAIM_DTL.CLAIM_ID = HRMS_CASH_CLAIM_HDR.CLAIM_ID) "
				+"INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_CASH_CLAIM_DTL.CLAIM_CREDIT_CODE) "
				+"WHERE CLAIM_EMPID="+bean.getEmpId()+" and HRMS_CASH_CLAIM_HDR.claim_id="+bean.getClaimId()+" order by CLAIM_CREDIT_CODE ";
			
			Object amount[][] = getSqlModel().getSingleResult(query);
			
		
			Object[][] amt=new Object[amount.length][5];
			if(amount!=null && amount.length>0){
			int s=1;
			for(int i=0;i<amount.length;i++){
				String balQuery = "SELECT nvl(CASH_BALANCE_AMT,0) FROM HRMS_CASH_BALANCE  "
					+" WHERE EMP_ID="+bean.getEmpId()+ " AND CASH_CREDIT_CODE="+amount[i][3];
				Object[][] bal=getSqlModel().getSingleResult(balQuery);
				
				amt[i][0]=s++;
				amt[i][1]=String.valueOf(amount[i][0]);
				
				if(bal!=null && bal.length>0){
						if(!(String.valueOf(bal[0][0]).equals("") || String.valueOf(bal[0][0]).equals("null"))){
							
							amt[i][2]=Utility.twoDecimals(Math.round(Double.parseDouble(String.valueOf(bal[0][0]))+Double.parseDouble(String.valueOf(amount[i][1]))));
						}else{
							amt[i][2]=Utility.twoDecimals(Math.round(Double.parseDouble(String.valueOf(amount[i][1]))));
						}
					
				}else{
					amt[i][2]=Utility.twoDecimals(Math.round(Double.parseDouble(String.valueOf(amount[i][1]))));
				}
				totBal+=Double.parseDouble(String.valueOf(amt[i][2]));
				amt[i][3]=Utility.twoDecimals(Math.round(Double.parseDouble(String.valueOf(amount[i][1]))));	
				totClaim+=Double.parseDouble(String.valueOf(amt[i][3]));
				amt[i][4]=amount[i][2];
				
			}//End of for loop
			
			
			String[] col={"Srl. No.","Credit Name","Balance Amount","Claim Amount","Proof Attached"};
			int[] cellwidth={5,20,10,10,8};
			int[] alignment={1,0,2,2,1};
			//rg.addTextBold("\n\nCash Claim Details :",0,0,0);
			
			
			//rg.tableBody(col,amt, cellwidth, alignment);
			Object[][] other=new Object[1][6];
			other[0][0]="";other[0][1]="Total Amount";other[0][2]=""+Utility.twoDecimals(totBal);other[0][3]=""+Utility.twoDecimals(totClaim);other[0][4]="";
			
			Object cashHeading[][]=new Object[1][1];
			cashHeading [0][0]="Cash Claim Details :";
			TableDataSet cashTitle = new TableDataSet();
			cashTitle.setData(cashHeading);
			cashTitle.setCellAlignment(new int[] {0 });
			cashTitle.setCellWidth(new int[] { 100 });
			cashTitle.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
			cashTitle.setBlankRowsAbove(1);
			rg.addTableToDoc(cashTitle);
			
			amt= Utility.joinArrays(amt, other, 1, 0);
			TableDataSet tdstable = new TableDataSet();
			tdstable.setData(amt);
			tdstable.setCellAlignment(alignment);
			tdstable.setCellWidth(cellwidth);
			tdstable.setHeader(col);
			tdstable.setBorder(true);
			//tdstable.setBlankRowsAbove(1);
			tdstable.setHeaderBGColor(new Color(225,225,225));
			rg.addTableToDoc(tdstable);
			
			Object particulars[][]=new Object[1][2];
			particulars [0][0]="Particulars :";
			particulars [0][1]=checkNull(String.valueOf(empData[0][6]));
			TableDataSet particularsTds = new TableDataSet();
			particularsTds.setData(particulars);
			particularsTds.setCellAlignment(new int[] {0,0 });
			particularsTds.setCellWidth(new int[] { 20,80 });
			particularsTds.setBlankRowsAbove(1);
			rg.addTableToDoc(particularsTds);
			
			} //End if
			
			String voucherQuery=" SELECT VCH_NAME,TO_CHAR(CLAIM_VOUCHER_AMOUNT,999999999999990.99),DECODE(CLAIM_VOUCHER_IS_PROOF,'Y','Yes','N','No') FROM HRMS_CASH_CLAIM_VOUCHER "
							+" LEFT JOIN HRMS_VCH_HD ON(VCH_CODE=CLAIM_VOUCHER_CODE) WHERE CLAIM_ID="+bean.getClaimId();
			Object[][] voucherObj=getSqlModel().getSingleResult(voucherQuery);
			
			if(voucherObj!=null && voucherObj.length>0){
				int[] vchCell={5,10,15,10};
				int[] vchAlign={1,0,2,1};
				String column[]={"Sr. No.","Voucher Head","Voucher Amount","Proof Attached"};
				int count=1;
				Object[][] vchData=new Object[voucherObj.length][4];
				double totalVchAmt=0;
				for(int i=0;i<vchData.length;i++){
					vchData[i][0]=count++;
					vchData[i][1]=voucherObj[i][0];	
					vchData[i][2]=voucherObj[i][1];	
					vchData[i][3]=voucherObj[i][2];	
					totalVchAmt+= Double.parseDouble(String.valueOf(voucherObj[i][1]));
				}
				Object[][] voucherTotalObj=new Object[1][4];
				voucherTotalObj[0][0]="";
				voucherTotalObj[0][1]="Total";
				voucherTotalObj[0][2]=String.valueOf(totalVchAmt);
				voucherTotalObj[0][3]="";
				
				Object vchHeading[][]=new Object[1][1];
				vchHeading [0][0]="Voucher Details :";
				TableDataSet vchTds = new TableDataSet();
				vchTds.setData(vchHeading);
				vchTds.setCellAlignment(new int[] {0 });
				vchTds.setCellWidth(new int[] { 100 });
				vchTds.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
				vchTds.setBlankRowsAbove(1);
				rg.addTableToDoc(vchTds);
				
				if(!(bean.getApplForDivisionCode().equals(""))){
				Object vchSubHeading[][]=new Object[1][1];
				vchSubHeading [0][0]="Voucher Apply For Division :"+bean.getApplForDivision();
				TableDataSet vchSubTds = new TableDataSet();
				vchSubTds.setData(vchSubHeading);
				vchSubTds.setCellAlignment(new int[] {0 });
				vchSubTds.setCellWidth(new int[] { 100 });
				vchSubTds.setBlankRowsBelow(1);
				rg.addTableToDoc(vchSubTds);
				}
				
				vchData=Utility.joinArrays(vchData, voucherTotalObj, 1, 0);
				TableDataSet vchDataTds = new TableDataSet();
				vchDataTds.setData(vchData);
				vchDataTds.setCellAlignment(vchAlign);
				vchDataTds.setCellWidth(vchCell);
				vchDataTds.setBorder(true);
				vchDataTds.setHeader(column);
				vchDataTds.setHeaderBGColor(new Color(225,225,225));
				//tdstable.setBlankRowsAbove(0);
				vchDataTds.setBlankRowsBelow(1);
				rg.addTableToDoc(vchDataTds);
				
			} //End of if
			
			String approveQuery=" SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,NVL(TO_CHAR(APPR_DATE,'DD-MM-YYYY'),' ') ,NVL(CASH_REMARK,' '),EMP_TOKEN FROM"
				+" HRMS_CASH_CLAIM_PATH INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_CASH_CLAIM_PATH.APPROVER_CODE)"
				+" WHERE CASH_CODE="+bean.getClaimId()+" ORDER BY APPROVER_CODE";
			Object[][] approver=getSqlModel().getSingleResult(approveQuery);
			
			if(approver!=null && approver.length>0){
				int[] apprvCell={5,10,15,10,10};
				int[] apprvAlign={1,1,0,1,0};
				String column[]={"Srl. No.","Approver Id","Approver Name","Approved Date","Remark"};
				int count=1;
				Object[][] data=new Object[approver.length][5];
				for(int i=0;i<approver.length;i++){
				data[i][0]=count++;
				data[i][1]=approver[i][3];//Emp Id
				data[i][2]=approver[i][0];//Emp Name
				data[i][3]=approver[i][1];//Approved Date
				data[i][4]=approver[i][2];//Remark
				}
				//rg.addTextBold("Approver Details :",0,0,0);
				
				Object apprHeading[][]=new Object[1][1];
				apprHeading [0][0]="Approver Details :";
				TableDataSet apprTitle = new TableDataSet();
				apprTitle.setData(apprHeading);
				apprTitle.setCellAlignment(new int[] {0 });
				apprTitle.setCellWidth(new int[] { 100 });
				apprTitle.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
				apprTitle.setBlankRowsAbove(1);
				rg.addTableToDoc(apprTitle);
				
				TableDataSet tdsApproverTable = new TableDataSet();
				tdsApproverTable.setData(data);
				tdsApproverTable.setCellAlignment(apprvAlign);
				tdsApproverTable.setCellWidth(apprvCell);
				tdsApproverTable.setBorder(true);
				tdsApproverTable.setHeader(column);
				tdsApproverTable.setHeaderBGColor(new Color(225,225,225));
				//tdstable.setBlankRowsAbove(0);
				tdsApproverTable.setBlankRowsBelow(1);
				rg.addTableToDoc(tdsApproverTable);
				//rg.tableBody(column, data, apprvCell,apprvAlign);
				
			} //End of approver length
			
			rg.process();
			rg.createReport(response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void sysDate(CashClaim bean)
	{
		String query = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
		
		Object date[][]=getSqlModel().getSingleResult(query);
		
		bean.setClaimDate(String.valueOf(date[0][0]));
	}
	
	public void getGeneralDetails(CashClaim claim)
	{
		String sql = "SELECT   HRMS_EMP_OFFC.EMP_TOKEN  ,  "+
		" HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "+ 
		" HRMS_CENTER.CENTER_NAME, HRMS_RANK.RANK_NAME, "+
		" HRMS_EMP_OFFC.EMP_ID  FROM HRMS_EMP_OFFC   INNER JOIN HRMS_RANK ON  "+
		" (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "+
		" INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) "+
		//" LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) "+
		" left join HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT  "
		+"where HRMS_EMP_OFFC.EMP_ID="+claim.getUserEmpId()+"";
		Object generalData[][]=getSqlModel().getSingleResult(sql);
			
			if(generalData.length>0){
				claim.setEmpToken(String.valueOf(generalData[0][0]));
				claim.setEmpName(String.valueOf(generalData[0][1]));
				claim.setEmpCenter(String.valueOf(generalData[0][2]));
				claim.setEmpRank(String.valueOf(generalData[0][3]));
				claim.setEmpId(String.valueOf(generalData[0][4]));
				
			}
		
	}
	
	public CashClaim viewCashBalance(CashClaim claim) {
		
		String query = "SELECT CASH_CREDIT_CODE,CREDIT_NAME,NVL(CASH_BALANCE_AMT,0),NVL(CASH_ONHOLD_AMT,0) "
				+" FROM HRMS_CASH_BALANCE "
				+" LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_CASH_BALANCE.CASH_CREDIT_CODE ) "
				+" WHERE EMP_ID = "+claim.getEmpId()+" order by CASH_CREDIT_CODE";
		
		ArrayList<Object> viewList = new ArrayList<Object>();
		
		Object cashBal[][] = getSqlModel().getSingleResult(query);
		for(int i=0;i<cashBal.length;i++){
			
			CashClaim att = new CashClaim();
			att.setCreditId(String.valueOf(cashBal[i][0]));
			att.setCreditName(String.valueOf(cashBal[i][1]));
			att.setBalanceAmt(String.valueOf(cashBal[i][2]));
			att.setOnHoldAmt(String.valueOf(cashBal[i][3]));
			
			att.setClaimAmt("0");
			viewList.add(att);
		}
		claim.setTotalAmt("0");
		if(viewList.size() > 0){
			claim.setIsDataAvailable("true");
			claim.setIsSaveButton("true");
		}
		claim.setAtt(viewList);
		
		return claim;
	}
public void getVoucherMasterList(CashClaim claim) {
		
		Object vchHeadObj[][] = getSqlModel().getSingleResult("SELECT VCH_CODE,VCH_NAME FROM HRMS_VCH_HD ");
		String vchClaimQuery=" SELECT CLAIM_VOUCHER_CODE,NVL(CLAIM_VOUCHER_AMOUNT,0),CLAIM_VOUCHER_IS_PROOF FROM HRMS_CASH_CLAIM_VOUCHER "
							+" WHERE CLAIM_ID="+claim.getClaimId();
		
		Object vchClaimObj[][] = getSqlModel().getSingleResult(vchClaimQuery);
		vchClaimObj =compareObject(vchHeadObj,vchClaimObj);
		
		ArrayList<Object> vchList = new ArrayList<Object>();
		
		
		double vchTotal =0.0;
		for(int i=0;i<vchClaimObj.length;i++){
			
			CashClaim bean = new CashClaim();
			bean.setVchCode(String.valueOf(vchClaimObj[i][0]));
			bean.setVchName(String.valueOf(vchClaimObj[i][1]));
			bean.setVchAmt(String.valueOf(vchClaimObj[i][2]));
			bean.setVchProof(String.valueOf(vchClaimObj[i][3]));
			vchTotal+= Double.parseDouble(String.valueOf(vchClaimObj[i][2]));
			vchList.add(bean);
		}
		
		if(vchList.size() > 0){
			//claim.setIsDataAvailable("true");
			claim.setIsSaveButton("true");
		}
		claim.setVoucherList(vchList);
		claim.setTotalVchAmt(String.valueOf(vchTotal));
	}

	public boolean saveClaim(String[] claimAmt, String[] creditId, String[] claimId,Object [][] empFlow,String[] onHoldAmt,String[] isProof,String[] balanceAmt, CashClaim claim) {
		boolean flag=false;
		boolean result=false;
		boolean result1=false;
		
		if(claim.getClaimId().equals("") || claim.getClaimId().equals("null") ){
			
			System.out.println("value of total Amount------------------------->>"+claim.getTotalAmt());
			logger.info("claim.getEmpId()========="+claim.getEmpId());
			logger.info("claim.getClaimDate()========="+claim.getClaimDate());
			logger.info("claim.getParticulars()========="+claim.getParticulars());
			logger.info("empFlow[0][0]========="+empFlow[0][0]);
			logger.info("empFlow[0][3]========="+empFlow[0][3]);
			logger.info("claim.getHproof()======="+claim.getHproof());
			if(claim.getTotalAmt().equals("")||claim.getTotalAmt().equals(null)){
				claim.setTotalAmt("0");
			}
			Object insertObj[][]=new Object[1][8];
			insertObj[0][0]	=	claim.getEmpId();
			insertObj[0][1]	=	claim.getClaimDate();
			insertObj[0][2]	=	claim.getParticulars();
			insertObj[0][3]	=	claim.getTotalAmt();
			insertObj[0][4]	=	empFlow[0][0];
			insertObj[0][5]	=	empFlow[0][3];
			insertObj[0][6]	=	claim.getTotalVchAmt();
			insertObj[0][7]	=	claim.getApplForDivisionCode();
			String query = "INSERT INTO HRMS_CASH_CLAIM_HDR (CLAIM_ID,CLAIM_EMPID,CLAIM_DATE,CLAIM_PARTICULARS,CLAIM_STATUS,CLAIM_TOTAL_AMOUNT,CLAIM_APPROVER,CLAIM_ALTER_APPROVER,CLAIM_VOUCHER_TOTAL_AMOUNT, CLAIM_VOUCHER_APPLY_DIV) "
				+"VALUES ((SELECT NVL(MAX(CLAIM_ID),0)+1 FROM HRMS_CASH_CLAIM_HDR),?,TO_DATE(?,'DD-MM-YYYY'),?,'P',?,?,?,?,?)";
			result = getSqlModel().singleExecute(query,insertObj);
			if(result){
				String balance = "";
				String holdAmt = "";
				String query3 = "SELECT  MAX(CLAIM_ID) FROM HRMS_CASH_CLAIM_HDR";
				Object[][] maxClaimId = getSqlModel().getSingleResult(query3);
				claim.setClaimId(String.valueOf(maxClaimId[0][0]));
				try{
				if (creditId != null && creditId.length > 0) {
					
				for (int i = 0; i < creditId.length; i++) {
					balance = String.valueOf(Double.parseDouble(String.valueOf(balanceAmt[i])) - 
												Double.parseDouble(String.valueOf(claimAmt[i])));
					holdAmt = String.valueOf(Double.parseDouble(String.valueOf(onHoldAmt[i])) + 
							Double.parseDouble(String.valueOf(claimAmt[i])));
					logger.info("balance==========="+balance);
				
					String query1 = "UPDATE HRMS_CASH_BALANCE SET CASH_BALANCE_AMT="+balance+",CASH_ONHOLD_AMT="+holdAmt+" WHERE CASH_CREDIT_CODE="+creditId[i]+" AND EMP_ID="+claim.getEmpId()+"";
					result = getSqlModel().singleExecute(query1);
					
					
					String query2 = "INSERT INTO HRMS_CASH_CLAIM_DTL (CLAIM_ID,CLAIM_CREDIT_CODE,CLAIM_AMOUNT,CLAIM_ISPROOF) "
									+"VALUES("+claim.getClaimId()+","+creditId[i]+","+claimAmt[i]+",'"+isProof[i]+"')";
					result = getSqlModel().singleExecute(query2);
				
				}
				}
				
				}catch (Exception e) {
					logger.error("Exception in cash dtl");
				}
				
			}
	
		}
		else{
			System.out.println("value of total amt in else------------->>"+claim.getTotalAmt());
			
			if(claim.getTotalAmt().equals("")||claim.getTotalAmt().equals(null)){
				claim.setTotalAmt("0");
			}
			Object updateObj[][]=new Object[1][9];
			updateObj[0][0]	=	claim.getEmpId();
			updateObj[0][1]	=	claim.getClaimDate();
			updateObj[0][2]	=	claim.getParticulars();
			updateObj[0][3]	=	claim.getTotalAmt();
			updateObj[0][4]	=	empFlow[0][0];
			updateObj[0][5]	=	empFlow[0][3];
			updateObj[0][6]	=	claim.getTotalVchAmt();
			updateObj[0][7]	=	claim.getApplForDivisionCode();
			updateObj[0][8]	=	claim.getClaimId();
			String query = "UPDATE HRMS_CASH_CLAIM_HDR SET CLAIM_EMPID=?,CLAIM_DATE=TO_DATE(?,'DD-MM-YYYY'),CLAIM_PARTICULARS=?,CLAIM_STATUS='P',CLAIM_TOTAL_AMOUNT=?," +
					" CLAIM_APPROVER=?,CLAIM_ALTER_APPROVER=?,CLAIM_VOUCHER_TOTAL_AMOUNT= ?, CLAIM_VOUCHER_APPLY_DIV=?"
							+" WHERE CLAIM_ID=?";
			result1 = getSqlModel().singleExecute(query,updateObj);
			
			if(result1){
				
				String query2 = "DELETE FROM HRMS_CASH_CLAIM_DTL WHERE CLAIM_ID="+claim.getClaimId()+"";
				result1 = getSqlModel().singleExecute(query2);
				try{
					if (creditId != null && creditId.length > 0) {
				for (int i = 0; i < creditId.length; i++) {
					String query1 = "UPDATE HRMS_CASH_BALANCE SET CASH_ONHOLD_AMT="+onHoldAmt[i]+" WHERE CASH_CREDIT_CODE="+creditId[i]+" AND EMP_ID="+claim.getEmpId()+"";
					result1 = getSqlModel().singleExecute(query1);
					
						String query3 = "INSERT INTO HRMS_CASH_CLAIM_DTL (CLAIM_ID,CLAIM_CREDIT_CODE,CLAIM_AMOUNT,CLAIM_ISPROOF) "
						+"VALUES("+claim.getClaimId()+","+creditId[i]+","+claimAmt[i]+",'"+isProof[i]+"')";
						result1 = getSqlModel().singleExecute(query3);

				}
					}
				}catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		}
		
		if(result)
			return result;
		
			else 
			{
				return false;
			}
		
	}
	
	public boolean saveVoucher(HttpServletRequest request,CashClaim bean){
		String vchCode[]=request.getParameterValues("vchCode");
		String vchAmt[]=request.getParameterValues("vchAmt");
		String vchProof[]=request.getParameterValues("vchProof");
		boolean result=false;
		Vector<Object[]> tempVect=new Vector<Object[]>();
		try{
		for (int i = 0; i < vchProof.length; i++) {
			if(Double.parseDouble(vchAmt[i])>0){
				Object []tempObj=new Object[4];
				tempObj[0]=bean.getClaimId();
				tempObj[1]=vchCode[i];
				tempObj[2]=vchAmt[i];
				tempObj[3]=vchProof[i];
				tempVect.add(tempObj);
			}
		}
		
		Object[][]saveVchObj=new Object[tempVect.size()][4];
		for (int i = 0; i < saveVchObj.length; i++) {
			saveVchObj[i]=tempVect.get(i);
		}
		
		getSqlModel().singleExecute("DELETE FROM HRMS_CASH_CLAIM_VOUCHER WHERE CLAIM_ID="+bean.getClaimId());
		String saveVchQuery="INSERT INTO HRMS_CASH_CLAIM_VOUCHER(CLAIM_ID, CLAIM_VOUCHER_CODE, CLAIM_VOUCHER_AMOUNT, CLAIM_VOUCHER_IS_PROOF) "
						+" VALUES (?,?,?,?)";
		result= getSqlModel().singleExecute(saveVchQuery, saveVchObj);
		}catch (Exception e) {
			logger.error("exception in saveVoucher"+e);
		}
		/*for (int i = 0; i < saveObj.length; i++) {
			for (int j = 0; j < saveObj[0].length; j++) {
			logger.info("saveObj["+i+"]["+j+"]===="+saveObj[i][j]);
		}
		}*/
		return result;
	}
	
	public String checkNull(String result){
		if(result ==null ||result.equals("null")){
			return "";
		}
		else{
			return result;
		}
	}
	public void generateApplNo(CashClaim bean){
		ApplCodeTemplateModel model = new ApplCodeTemplateModel();
		model.initiate(context, session);
		String applnCode = model.generateApplicationCode(bean
				.getClaimId(), "Cash", bean.getEmpId(), bean
				.getClaimDate());

		logger.info("final appln code in application=="+ applnCode);
		getSqlModel().singleExecute("UPDATE HRMS_CASH_CLAIM_HDR SET CLAIM_APPL_NO='"
						+ applnCode + "' WHERE CLAIM_ID="
						+ bean.getClaimId());
		model.terminate();
	}
	public Object [][] compareObject(Object [][]sourceObj,Object[][]destObj){
		Object returnObj [][]= new Object[sourceObj.length][4];
		int j = 0;
		try{
		for (int i = 0; i < returnObj.length; i++) {
			if(j<destObj.length && String.valueOf(sourceObj[i][0]).equals(String.valueOf(destObj[j][0]))){
				
					returnObj[i][0] = sourceObj[i][0];				//vch code
					returnObj[i][1] = sourceObj[i][1];				//vch name
					returnObj[i][2] = destObj[j][1];				//vch amt
					returnObj[i][3] = destObj[j++][2];				//vch proof
				
			}else{
				returnObj[i][0] = sourceObj[i][0];
				returnObj[i][1] = sourceObj[i][1];
				returnObj[i][2] = 0;
				returnObj[i][3] = "N";
				
			}
		}
		}catch (Exception e) {
			for (int i = 0; i < returnObj.length; i++) {
				returnObj[i][0] = sourceObj[i][0];
				returnObj[i][1] = sourceObj[i][1];
				returnObj[i][2] = 0;
				returnObj[i][3] = "N";
			}
		}
		return returnObj;
	}

	public CashClaim getClaimData(CashClaim claim) {
		
		String query1 = "SELECT CLAIM_ID,TO_CHAR(CLAIM_DATE,'DD-MM-YYYY'),CLAIM_PARTICULARS,CLAIM_TOTAL_AMOUNT,DECODE(CLAIM_STATUS,'D','DISBURSED','P','PENDING','A','APPROVED','R','REJECTED')," 
			+" CLAIM_VOUCHER_APPLY_DIV,DIV_NAME FROM HRMS_CASH_CLAIM_HDR "
			+" LEFT JOIN HRMS_DIVISION ON(CLAIM_VOUCHER_APPLY_DIV=DIV_ID)"
			+" WHERE CLAIM_ID="+claim.getClaimId()+"";
			Object cashHdr[][] = getSqlModel().getSingleResult(query1);
				claim.setClaimId(String.valueOf(cashHdr[0][0]));
				claim.setClaimDate(String.valueOf(cashHdr[0][1]));
				if(String.valueOf(cashHdr[0][2]).equals("") || String.valueOf(cashHdr[0][2]).equals(null) ){
					System.out.println("in if particulars--------------");
					claim.setParticulars("");
				}else{
					System.out.println("in else particulars--------------");
					claim.setParticulars(checkNull(String.valueOf(cashHdr[0][2])));
				}
				
				claim.setTotalAmt(checkNull(String.valueOf(cashHdr[0][3])));
				claim.setClaimStatus(checkNull(String.valueOf(cashHdr[0][4])));
				claim.setApplForDivisionCode(checkNull(String.valueOf(cashHdr[0][5])));
				claim.setApplForDivision(checkNull(String.valueOf(cashHdr[0][6])));
				
		
		String query = "SELECT CREDIT_NAME,nvl(CLAIM_AMOUNT,0),CLAIM_CREDIT_CODE,CLAIM_EMPID,CLAIM_ISPROOF "
						+"FROM HRMS_CASH_CLAIM_HDR "
						+"INNER JOIN HRMS_CASH_CLAIM_DTL ON (HRMS_CASH_CLAIM_DTL.CLAIM_ID = HRMS_CASH_CLAIM_HDR.CLAIM_ID) "
						+"INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_CASH_CLAIM_DTL.CLAIM_CREDIT_CODE) "
						+"WHERE CLAIM_EMPID="+claim.getEmpId()+" and HRMS_CASH_CLAIM_HDR.claim_id="+claim.getClaimId()+" order by CLAIM_CREDIT_CODE ";
		
		String query2 = "SELECT nvl(CASH_BALANCE_AMT,0),NVL(CASH_ONHOLD_AMT,0) FROM HRMS_CASH_BALANCE  "
						+" WHERE EMP_ID="+claim.getEmpId()+" ORDER BY CASH_CREDIT_CODE";
		
			ArrayList<Object> viewList = new ArrayList<Object>();
			Object balanceAmt[][] = getSqlModel().getSingleResult(query2);
			Object cashBal[][] = getSqlModel().getSingleResult(query);
			String finalBalanceAmt = ""; 
			for(int i=0;i<cashBal.length;i++){
				//logger.info("balanceAmt============"+balanceAmt[i][0]);
				CashClaim att = new CashClaim();
				att.setCreditName(String.valueOf(cashBal[i][0]));
				//int balanceAmount=0;
				//int onHldAmt=0;
				if(balanceAmt !=null ){
					if(balanceAmt.length>0){
				// balanceAmount =Integer.parseInt(String.valueOf(balanceAmt[i][0]));
				// onHldAmt = Integer.parseInt(String.valueOf(balanceAmt[i][1]));
				 finalBalanceAmt = String.valueOf(Double.parseDouble(String.valueOf(balanceAmt[i][0])) + 
						 							Double.parseDouble(String.valueOf(cashBal[i][1])));
				 att.setBalanceAmt(finalBalanceAmt);
				 att.setOnHoldAmt(String.valueOf(balanceAmt[i][1]));
					}
					else{
						att.setBalanceAmt(String.valueOf(cashBal[i][1]));
						att.setOnHoldAmt("0");
					}	
				}
							
				Object claimAmount =cashBal[i][1];
				if(claimAmount == null){
					att.setClaimAmt("0");
				}
				else{
					att.setClaimAmt(String.valueOf(cashBal[i][1]));
				}
					att.setHproof(String.valueOf(cashBal[i][4]));
					att.setCreditId(String.valueOf(cashBal[i][2]));
				
				
				
				viewList.add(att);
			}
			if(viewList.size() > 0){
				claim.setIsDataAvailable("true");
				claim.setViewCashClaim("false");
				claim.setIsSaveButton("true");
			}
			claim.setAtt(viewList);
			
			
		return claim;
	}
	
	public void showDetails(CashClaim claim, String claimCode){
		logger.info("claimCode============="+claimCode);
		String empQuery = "SELECT  HRMS_EMP_OFFC.EMP_TOKEN  , HRMS_EMP_OFFC.EMP_FNAME||' '|| "
						+" HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,  "
						+" HRMS_CENTER.CENTER_NAME, HRMS_RANK.RANK_NAME,  HRMS_EMP_OFFC.EMP_ID,TO_CHAR(CLAIM_DATE,'DD-MM-YYYY'), "
						+" CLAIM_PARTICULARS,CLAIM_TOTAL_AMOUNT,DECODE(CLAIM_STATUS,'A','APPROVED','R','REJECTED','P','PENDING','D','DISBURSED')  "
						+" FROM HRMS_CASH_CLAIM_HDR  "
						+" LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_CASH_CLAIM_HDR.CLAIM_EMPID) " 
						+" INNER JOIN HRMS_RANK ON   (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
						+" INNER JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) " 
						//+" LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE = HRMS_TITLE.TITLE_CODE) "  
						+" left join HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT  "
						+" WHERE CLAIM_ID ="+claimCode+" ";
		Object empData[][] = getSqlModel().getSingleResult(empQuery);
		
		if(empData.length > 0 ){
			claim.setEmpToken(checkNull(String.valueOf(empData[0][0])));
			claim.setEmpName(checkNull(String.valueOf(empData[0][1])));
			claim.setEmpCenter(checkNull(String.valueOf(empData[0][2])));
			claim.setEmpRank(checkNull(String.valueOf(empData[0][3])));
			claim.setEmpId(checkNull(String.valueOf(empData[0][4])));
			claim.setClaimDate(checkNull(String.valueOf(empData[0][5])));
			claim.setParticulars(checkNull(String.valueOf(empData[0][6])));
			claim.setTotalAmt(checkNull(String.valueOf(empData[0][7])));
			claim.setStatus(checkNull(String.valueOf(empData[0][8])));
		}
	}

	public CashClaim showRecord(CashClaim claim, HttpServletRequest request,String claimCode) {
		
		String query = "SELECT CREDIT_NAME,nvl(CLAIM_AMOUNT,0),CLAIM_CREDIT_CODE,CLAIM_EMPID,CLAIM_ISPROOF "
			+" FROM HRMS_CASH_CLAIM_HDR "
			+"INNER JOIN HRMS_CASH_CLAIM_DTL ON (HRMS_CASH_CLAIM_DTL.CLAIM_ID = HRMS_CASH_CLAIM_HDR.CLAIM_ID) "
			+"INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_CASH_CLAIM_DTL.CLAIM_CREDIT_CODE) "
			+"WHERE CLAIM_EMPID="+claim.getEmpId()+" and HRMS_CASH_CLAIM_HDR.claim_id="+claimCode+" order by CLAIM_CREDIT_CODE ";

		String query2 = "SELECT nvl(CASH_BALANCE_AMT,0),NVL(CASH_ONHOLD_AMT,0) FROM HRMS_CASH_BALANCE  "
					+" WHERE EMP_ID="+claim.getEmpId()+" ORDER BY CASH_CREDIT_CODE";

		ArrayList<Object> viewList = new ArrayList<Object>();
		Object balanceAmt[][] = getSqlModel().getSingleResult(query2);
		Object cashBal[][] = getSqlModel().getSingleResult(query);
		
		String finalBalanceAmt = ""; 
		for(int i=0;i<cashBal.length;i++){
			//logger.info("balanceAmt============"+balanceAmt[i][0]);
			CashClaim att = new CashClaim();
			att.setCreditName(String.valueOf(cashBal[i][0]));
			//int balanceAmount=0;
			//int onHldAmt=0;
			if(balanceAmt !=null ){
				if(balanceAmt.length>0){
			// balanceAmount =Integer.parseInt(String.valueOf(balanceAmt[i][0]));
			// onHldAmt = Integer.parseInt(String.valueOf(balanceAmt[i][1]));
			 finalBalanceAmt = String.valueOf(Double.parseDouble(String.valueOf(balanceAmt[i][0])) + 
					 							Double.parseDouble(String.valueOf(cashBal[i][1])));
			 att.setBalanceAmt(finalBalanceAmt);
			 att.setOnHoldAmt(String.valueOf(balanceAmt[i][1]));
				}
				else{
					att.setBalanceAmt("0");
					att.setOnHoldAmt("0");
				}	
			}
						
			Object claimAmount =cashBal[i][1];
			if(claimAmount == null){
				att.setClaimAmt("0");
			}
			else{
				att.setClaimAmt(String.valueOf(cashBal[i][1]));
			}
				att.setHproof(String.valueOf(cashBal[i][4]));
				att.setCreditId(String.valueOf(cashBal[i][2]));
			
			
			
			viewList.add(att);
		}
		if(viewList.size() > 0){
			claim.setIsDataAvailable("true");
			claim.setViewCashClaim("false");
			claim.setIsSaveButton("true");
		}
		claim.setAtt(viewList);
		
		return claim;
		
	}

}
