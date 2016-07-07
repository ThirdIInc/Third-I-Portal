package org.paradyne.model.payroll.salary;
import java.awt.Color;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.salary.ClaimMisReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;
import org.paradyne.lib.report.ReportGenerator;

import com.lowagie.text.Font;
import com.lowagie.text.Image;
/*
 * author:Pradeep
 * Date:05-04-2008
 */
public class ClaimMisReportModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(org.paradyne.model.payroll.salary.ClaimMisReportModel.class);
	
	public void getEmployeeDetails(String empCode,ClaimMisReport cmr){
		
		String query=" SELECT EMP_ID,EMP_TOKEN,TITLE_NAME||' '||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME FROM HRMS_EMP_OFFC LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE)"
			+" WHERE EMP_ID="+empCode;
		
		Object[][] values = getSqlModel().getSingleResult(query);
		
		cmr.setEmpId(String.valueOf(values[0][0]));
		cmr.setEmpToken(String.valueOf(values[0][1]));
		cmr.setEmpName(String.valueOf(values[0][2]));
		
	}
	
	
	public void generateReport(ClaimMisReport bean,HttpServletResponse response){
		
		String reportName="Cash Claim Report";		
		ReportDataSet rds = new ReportDataSet();
		rds.setReportType(bean.getReporttype());
		rds.setFileName(reportName);
		rds.setPageOrientation("landscape");
		org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(rds);
		
		String status=bean.getStatus();
		if(status.equals("P"))
			status="Pending";
		else if(status.equals("A"))
			status="Approved";
		else if(status.equals("D"))
			status="Disbursed";
		else if(status.equals("R"))
			status="Rejected";
		else
			status="All";
		
		String filterQuery ="";	
		String query=" SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(CLAIM_DATE,'DD-MM-YYYY'),HRMS_CREDIT_HEAD.CREDIT_NAME ,"
					+" TO_CHAR(NVL(CLAIM_AMOUNT,0),9999999999990.99),CASE WHEN CLAIM_STATUS='A' THEN 'Approved' WHEN CLAIM_STATUS='D' THEN 'Disbursed' WHEN CLAIM_STATUS='R' THEN 'Rejected' else 'Pending' END,NVL(CLAIM_APPL_NO,'') " +
					" FROM HRMS_CASH_CLAIM_HDR "
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_CASH_CLAIM_HDR.CLAIM_EMPID)"
					+" INNER JOIN HRMS_CASH_CLAIM_DTL ON(HRMS_CASH_CLAIM_DTL.CLAIM_ID=HRMS_CASH_CLAIM_HDR.CLAIM_ID)"
					+" INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CASH_CLAIM_DTL.CLAIM_CREDIT_CODE=HRMS_CREDIT_HEAD.CREDIT_CODE)"
					+" WHERE 1=1 AND CLAIM_AMOUNT>0 ";
		
		String vchQuery=" SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(CLAIM_DATE,'DD-MM-YYYY'),VCH_NAME ,"
					+" TO_CHAR(NVL(CLAIM_VOUCHER_AMOUNT,0),9999999999990.99),CASE WHEN CLAIM_STATUS='A' THEN 'Approved' WHEN CLAIM_STATUS='D' THEN 'Approved' WHEN CLAIM_STATUS='R' THEN 'Rejected' else 'Pending' END,NVL(CLAIM_APPL_NO,'') " +
					" FROM HRMS_CASH_CLAIM_HDR "
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_CASH_CLAIM_HDR.CLAIM_EMPID)"
					+" INNER JOIN HRMS_CASH_CLAIM_VOUCHER ON(HRMS_CASH_CLAIM_VOUCHER.CLAIM_ID=HRMS_CASH_CLAIM_HDR.CLAIM_ID)"
					+" INNER JOIN HRMS_VCH_HD ON(VCH_CODE=CLAIM_VOUCHER_CODE)"
					+" WHERE 1=1 AND CLAIM_VOUCHER_AMOUNT>0";
		String vchFilterQuery="";
		if(!(bean.getEmpName().equals("") || bean.getEmpName()==null)){
			filterQuery+=" AND EMP_ID="+bean.getEmpId();
			
			
		}
		
		if(!(bean.getFromDate().equals("") || bean.getFromDate()==null)){
			filterQuery+=" AND CLAIM_DATE >=TO_DATE('"+bean.getFromDate()+"','DD-MM-YYYY')";
			
		}
		
		if(!bean.getToDate().equals("") || bean.getToDate().length()!=0){
			
			filterQuery+=" AND CLAIM_DATE <=TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY')";  
			
		}
         if(!bean.getBranchcode().equals("") || bean.getBranchcode().length()!=0){
			
        	 filterQuery+=" AND  HRMS_EMP_OFFC.EMP_CENTER="+bean.getBranchcode();  
			
		}
         if(!bean.getDeptCode().equals("")){
 			
        	 filterQuery+=" AND HRMS_EMP_OFFC.EMP_DEPT ="+bean.getDeptCode();  
 			
 		}
         if(!bean.getDesgCode().equals("")){
  			
        	 filterQuery+=" AND HRMS_EMP_OFFC.EMP_DESG ="+bean.getDesgCode();  
  			
  		}
         if(!bean.getDivCode().equals("")){
   			
        	 filterQuery+=" AND HRMS_EMP_OFFC.EMP_DIV ="+bean.getDivCode();  
   			
   		}
         
         if(!bean.getStatus().equals("")){
        	 filterQuery+=" AND CLAIM_STATUS='"+bean.getStatus()+"'";  
    		 }
         vchFilterQuery=filterQuery;
         
         if(!bean.getCreditCode().equals("")){
        	 filterQuery+=" AND HRMS_CASH_CLAIM_DTL.CLAIM_CREDIT_CODE="+bean.getCreditCode();  
    		 }
         
         if(!bean.getVchCode().equals("")){
        	 vchFilterQuery+=" AND CLAIM_VOUCHER_CODE="+bean.getVchCode();  
    		 }
         
         filterQuery+=" ORDER BY CLAIM_ID";
         vchFilterQuery+=" ORDER BY CLAIM_ID";
		
		Object data[][]=new Object[6][2];
		
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
			
		}catch(Exception eee)
		{
			logger.info("when assign the image...!"+eee);
		
			nameObj[0][0]=" ";
		}
		String companyName="",companyAddress="";
		
		if(companyDtl!=null && companyDtl.length>0)
		{
			companyName=String.valueOf(companyDtl[0][2]);
			companyAddress=String.valueOf(Utility.checkNull(""+companyDtl[0][3]));
			
		}
		TableDataSet logoData = new TableDataSet();
		logoData.setData(nameObj);
		logoData.setCellAlignment(new int[]{0});
		logoData.setCellWidth(new int[]{100});
		logoData.setBorder(false);
		logoData.setHeaderTable(true);
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
		
			//rg.addTableToDoc(mapThree);
		rg.createHeader(mapThree);
		  
			if(!bean.getEmpId().equals(""))
				data[1][0]="Employee Name    :"+ bean.getEmpName();
			if(!bean.getFromDate().equals(""))
				data[0][0]="From date  :"+bean.getFromDate();
			
			if(!bean.getToDate().equals(""))
				data[0][1]="To date  :"+bean.getToDate();
		
			if(!bean.getDivCode().equals(""))
				data[2][0]="Division Name  :"+bean.getDivName();
			
			if(!bean.getBranchcode().equals(""))
				data[2][1]="Branch Name  :"+bean.getBranchname();
			
			if(!bean.getDeptCode().equals(""))
				data[3][0]="Department Name  :"+bean.getDepartment();
		
			if(!bean.getDesgCode().equals(""))
				data[3][1]="Designation Name  :"+bean.getDesgname();
			
			if(!bean.getStatus().equals(""))
				data[4][0]="Status :"+status;
			
			if(!bean.getCreditCode().equals(""))
				data[5][0]="Credit Name :"+bean.getCreditName();
			
			if(!bean.getVchCode().equals(""))
				data[5][1]="Voucher Head :"+bean.getVchName();
			
			int[] cellWidth={50,50};
			int[] align={0,0};
			
			TableDataSet tdsEmpData = new TableDataSet();
			tdsEmpData.setData(data);
			tdsEmpData.setCellAlignment(align);
			tdsEmpData.setCellWidth(cellWidth);
			rg.addTableToDoc(tdsEmpData);
		
		try {
			Object[][] claimData = getSqlModel().getSingleResult(query+filterQuery);
			Object[][] vchData = getSqlModel().getSingleResult(vchQuery+vchFilterQuery);
			Object[][] claimFinalObj;
			Object[][] vchFinalObj;
			double totalcreditamt=0.0;
			double totalVchAmt=0.0;
			if (claimData!=null && claimData.length>0) {
				/*if(!bean.getCreditCode().equals(""))
				{*/
					claimFinalObj = new Object[claimData.length+1][7];
					int i;
					for ( i= 0; i < claimData.length; i++) {

						claimFinalObj[i][0] = claimData[i][6]; //REQ. NO.
						claimFinalObj[i][1] = claimData[i][0];//Employee Id
						claimFinalObj[i][2] = claimData[i][1];//Employee Name
						claimFinalObj[i][3] = claimData[i][2];//Claim Date
						claimFinalObj[i][4] = claimData[i][3]; //credit name 
						claimFinalObj[i][5] = claimData[i][4]; //Claim Amount
						claimFinalObj[i][6] = claimData[i][5];//Status
						totalcreditamt+=Double.parseDouble(String.valueOf(claimData[i][4]));

					}
					for (int k = 0; k <7; k++) {
						claimFinalObj[i][k]="";
					}
					claimFinalObj[i][2]="Total Amount:";
					claimFinalObj[i][5]=String.valueOf(totalcreditamt);
					
					int[] alignment1={1,0,0,1,0,2,0};
					int[] cellWidth1={10,15,30,15,15,15,15};
					String []colNames1={"Req.No","Employee Id","Employee Name","Claim Date","Credit Name","Claim Amount","Claim Status"};
					
					Object cashHeading[][]=new Object[1][1];
					cashHeading [0][0]="Cash Claim Details :";
					TableDataSet cashTitle = new TableDataSet();
					cashTitle.setData(cashHeading);
					cashTitle.setCellAlignment(new int[] {0 });
					cashTitle.setCellWidth(new int[] { 100 });
					cashTitle.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
					rg.addTableToDoc(cashTitle);
					
					TableDataSet tdstable = new TableDataSet();
					tdstable.setData(claimFinalObj);
					tdstable.setCellAlignment(alignment1);
					tdstable.setCellWidth(cellWidth1);
					tdstable.setHeader(colNames1);
					tdstable.setBorder(true);
					tdstable.setHeaderBGColor(new Color(225,225,225));
					tdstable.setHeaderTable(false);
					rg.addTableToDoc(tdstable);
				
			}
			
			if (vchData!=null && vchData.length>0) {
					vchFinalObj = new Object[vchData.length+1][7];
					int i;
					for ( i= 0; i < vchData.length; i++) {

						vchFinalObj[i][0] = vchData[i][6]; //REQ. NO.
						vchFinalObj[i][1] = vchData[i][0];//Employee Id
						vchFinalObj[i][2] = vchData[i][1];//Employee Name
						vchFinalObj[i][3] = vchData[i][2];//Claim Date
						vchFinalObj[i][4] = vchData[i][3]; //credit name 
						vchFinalObj[i][5] = vchData[i][4]; //Claim Amount
						vchFinalObj[i][6] = vchData[i][5];//Status
						totalVchAmt+=Double.parseDouble(String.valueOf(vchData[i][4]));

					}
					for (int k = 0; k <7; k++) {
						vchFinalObj[i][k]="";
					}
					vchFinalObj[i][2]="Total Amount:";
					vchFinalObj[i][5]=String.valueOf(totalVchAmt);
					
					int[] alignment1={1,0,0,1,0,2,0};
					int[] cellWidth1={10,15,30,15,15,15,15};
					String []colNames1={"Req.No","Employee Id","Employee Name","Claim Date","Voucher Head","Voucher Amount","Claim Status"};
					
					Object voucherHeading[][]=new Object[1][1];
					voucherHeading [0][0]="Voucher Details :";
					TableDataSet vchTitle = new TableDataSet();
					vchTitle.setData(voucherHeading);
					vchTitle.setCellAlignment(new int[] {0 });
					vchTitle.setCellWidth(new int[] { 100 });
					vchTitle.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
					vchTitle.setBlankRowsAbove(1);
					rg.addTableToDoc(vchTitle);
					
					TableDataSet vchTable = new TableDataSet();
					vchTable.setData(vchFinalObj);
					vchTable.setCellAlignment(alignment1);
					vchTable.setCellWidth(cellWidth1);
					vchTable.setHeader(colNames1);
					vchTable.setBorder(true);
					vchTable.setHeaderTable(false);
					vchTable.setHeaderBGColor(new Color(225,225,225));
					rg.addTableToDoc(vchTable);
			}
				
			if ((claimData==null || claimData.length<=0) &&(vchData==null || vchData.length<=0)) {
				Object noDataObj[][]=new Object[1][1];
				noDataObj [0][0]="No data to display ";
				TableDataSet noDataTable= new TableDataSet();
				noDataTable.setData(noDataObj);
				noDataTable.setCellAlignment(new int[] {1 });
				noDataTable.setCellWidth(new int[] { 100 });
				noDataTable.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
				noDataTable.setBlankRowsAbove(1);
				rg.addTableToDoc(noDataTable);
				
			}else if(bean.getEmpId().equals("")){
				Vector deptStatVect=getDeptStatistic(bean,claimData,filterQuery,vchData,vchFilterQuery);
				rg.addProperty(rg.PAGE_BREAK);
				Object statHeading[][]=new Object[1][1];
				statHeading [0][0]="Department Statistics :";
				TableDataSet deptTitle = new TableDataSet();
				deptTitle.setData(statHeading);
				deptTitle.setCellAlignment(new int[] {0 });
				deptTitle.setCellWidth(new int[] { 100 });
				deptTitle.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0,0,0));
				deptTitle.setBlankRowsAbove(1);
				rg.addTableToDoc(deptTitle);
				
				if(!(claimData==null || claimData.length<=0)){
					TableDataSet cashStatTable = new TableDataSet();
					cashStatTable.setHeader((String[])deptStatVect.get(0));
					cashStatTable.setData((Object[][])deptStatVect.get(1));
					cashStatTable.setCellWidth((int[])deptStatVect.get(2));
					cashStatTable.setCellAlignment((int[])deptStatVect.get(3));
					cashStatTable.setBorder(true);
					cashStatTable.setHeaderBGColor(new Color(225,225,225));
					rg.addTableToDoc(cashStatTable);
				
					if(!(vchData==null || vchData.length<=0)){
						TableDataSet vchStatTable = new TableDataSet();
						vchStatTable.setHeader((String[])deptStatVect.get(4));
						vchStatTable.setData((Object[][])deptStatVect.get(5));
						vchStatTable.setCellWidth((int[])deptStatVect.get(6));
						vchStatTable.setCellAlignment((int[])deptStatVect.get(7));
						vchStatTable.setBorder(true);
						vchStatTable.setHeaderBGColor(new Color(225,225,225));
						deptTitle.setBlankRowsAbove(1);
						rg.addTableToDoc(vchStatTable);
					}
				}else{
					
					TableDataSet vchStatTable = new TableDataSet();
					vchStatTable.setHeader((String[])deptStatVect.get(0));
					vchStatTable.setData((Object[][])deptStatVect.get(1));
					vchStatTable.setCellWidth((int[])deptStatVect.get(2));
					vchStatTable.setCellAlignment((int[])deptStatVect.get(3));
					vchStatTable.setBorder(true);
					vchStatTable.setHeaderBGColor(new Color(225,225,225));
					deptTitle.setBlankRowsAbove(1);
					rg.addTableToDoc(vchStatTable);
				}
				
				
			}
			
			
			
			rg.process();
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	
	}
public void generateReportOld(ClaimMisReport bean,HttpServletResponse response){
		
		String title="Claim Mis Report";
		String type=bean.getReporttype();
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(type,title,"A4");
		rg.setFName(title);
		
		String status=bean.getStatus();
		if(status.equals("P"))
			status="Pending";
		else if(status.equals("A"))
			status="Approved";
		else if(status.equals("D"))
			status="Disbursed";
		else if(status.equals("R"))
			status="Rejected";
		else
			status="All";
		
		String filterQuery ="";	
		String query=" SELECT EMP_TOKEN,EMP_FNAME||''||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(CLAIM_DATE,'DD-MM-YYYY'),HRMS_CREDIT_HEAD.CREDIT_NAME ,"
					+" TO_CHAR(NVL(CLAIM_AMOUNT,0),9999999999990.99),CASE WHEN CLAIM_STATUS='A' THEN 'Approved' WHEN CLAIM_STATUS='D' THEN 'Disbursed' WHEN CLAIM_STATUS='R' THEN 'Rejected' else 'Pending' END,NVL(CLAIM_APPL_NO,'') " +
					" FROM HRMS_CASH_CLAIM_HDR "
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_CASH_CLAIM_HDR.CLAIM_EMPID)"
					+" INNER JOIN HRMS_CASH_CLAIM_DTL ON(HRMS_CASH_CLAIM_DTL.CLAIM_ID=HRMS_CASH_CLAIM_HDR.CLAIM_ID)"
					+" INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CASH_CLAIM_DTL.CLAIM_CREDIT_CODE=HRMS_CREDIT_HEAD.CREDIT_CODE)"
					+" WHERE 1=1";
		
		String vchQuery=" SELECT EMP_TOKEN,EMP_FNAME||''||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(CLAIM_DATE,'DD-MM-YYYY'),VCH_NAME ,"
					+" TO_CHAR(NVL(CLAIM_VOUCHER_AMOUNT,0),9999999999990.99),CASE WHEN CLAIM_STATUS='A' THEN 'Approved' WHEN CLAIM_STATUS='D' THEN 'Approved' WHEN CLAIM_STATUS='R' THEN 'Rejected' else 'Pending' END,NVL(CLAIM_APPL_NO,'') " +
					" FROM HRMS_CASH_CLAIM_HDR "
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_CASH_CLAIM_HDR.CLAIM_EMPID)"
					+" INNER JOIN HRMS_CASH_CLAIM_VOUCHER ON(HRMS_CASH_CLAIM_VOUCHER.CLAIM_ID=HRMS_CASH_CLAIM_HDR.CLAIM_ID)"
					+" INNER JOIN HRMS_VCH_HD ON(VCH_CODE=CLAIM_VOUCHER_CODE)"
					+" WHERE 1=1";
		String vchFilterQuery="";
		if(!(bean.getEmpName().equals("") || bean.getEmpName()==null)){
			filterQuery+=" AND EMP_ID="+bean.getEmpId();
			
			
		}
		
		if(!(bean.getFromDate().equals("") || bean.getFromDate()==null)){
			filterQuery+=" AND CLAIM_DATE >=TO_DATE('"+bean.getFromDate()+"','DD-MM-YYYY')";
			
		}
		
		if(!bean.getToDate().equals("") || bean.getToDate().length()!=0){
			
			filterQuery+=" AND CLAIM_DATE <=TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY')";  
			
		}
         if(!bean.getBranchcode().equals("") || bean.getBranchcode().length()!=0){
			
        	 filterQuery+=" AND  HRMS_EMP_OFFC.EMP_CENTER="+bean.getBranchcode();  
			
		}
         if(!bean.getDeptCode().equals("")){
 			
        	 filterQuery+=" AND HRMS_EMP_OFFC.EMP_DEPT ="+bean.getDeptCode();  
 			
 		}
         if(!bean.getDesgCode().equals("")){
  			
        	 filterQuery+=" AND HRMS_EMP_OFFC.EMP_DESG ="+bean.getDesgCode();  
  			
  		}
         if(!bean.getDivCode().equals("")){
   			
        	 filterQuery+=" AND HRMS_EMP_OFFC.EMP_DIV ="+bean.getDivCode();  
   			
   		}
         
         if(!bean.getStatus().equals("")){
        	 filterQuery+=" AND CLAIM_STATUS='"+bean.getStatus()+"'";  
    		 }
         vchFilterQuery=filterQuery;
         
         if(!bean.getCreditCode().equals("")){
        	 filterQuery+=" AND HRMS_CASH_CLAIM_DTL.CLAIM_CREDIT_CODE="+bean.getCreditCode();  
    		 }
         
         if(!bean.getVchCode().equals("")){
        	 vchFilterQuery+=" AND CLAIM_VOUCHER_CODE="+bean.getVchCode();  
    		 }
         
         filterQuery+=" ORDER BY CLAIM_ID";
         vchFilterQuery+=" ORDER BY CLAIM_ID";
		
		String datequery="  SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
		Object [][]dateobj=getSqlModel().getSingleResult(datequery);
		int [] bcellWidth={50,50};
		int [] bcellAlign={0,0};
		Object data[][]=new Object[6][2];
		if(!bean.getReporttype().equalsIgnoreCase("Xls")){
		
			rg.addFormatedText(title, 6, 0, 1, 0);
			rg.addText("Date: "+String.valueOf(dateobj[0][0]), 0, 2, 0);
			rg.addText("\n", 0, 0, 0);
		
		  
			if(!bean.getEmpId().equals(""))
				data[1][0]="Employee Name    :"+ bean.getEmpName();
			if(!bean.getFromDate().equals(""))
				data[0][0]="From date  :"+bean.getFromDate();
			
			if(!bean.getToDate().equals(""))
				data[0][1]="To date  :"+bean.getToDate();
		
			if(!bean.getDivCode().equals(""))
				data[2][0]="Division Name  :"+bean.getDivName();
			
			if(!bean.getBranchcode().equals(""))
				data[2][1]="Branch Name  :"+bean.getBranchname();
			
			if(!bean.getDeptCode().equals(""))
				data[3][0]="Department Name  :"+bean.getDepartment();
		
			if(!bean.getDesgCode().equals(""))
				data[3][1]="Designation Name  :"+bean.getDesgname();
			
			if(!bean.getStatus().equals(""))
				data[4][0]="Status :"+status;
			
			if(!bean.getCreditCode().equals(""))
				data[5][0]="Credit Name :"+bean.getCreditName();
			
			if(!bean.getVchCode().equals(""))
				data[5][1]="Voucher Head :"+bean.getVchName();
			
		
			//rg.addFormatedText(""+titles, 0, 0, 0, 0);
			//rg.addText("\t"+titles, 0, 0, 0);
			if(data!=null && data.length!=0)
				rg.tableBodyNoBorder(data,bcellWidth,bcellAlign);
		
		    
		}else
		{
			rg.addText(title, 0, 0, 0);
			rg.addText("Date: "+String.valueOf(dateobj[0][0]), 0, 2, 0);
			
			rg.addText("", 0,  1, 0);
			rg.addText("\n", 0, 0, 0);
			
			if(!bean.getEmpId().equals(""))
				rg.addText("\nEmployee Name :"+ bean.getEmpName(), 0, 1, 0);
			if(!bean.getToDate().equals(""))
				rg.addText("To date:"+bean.getToDate(), 0, 1, 0);
			if(!bean.getFromDate().equals(""))
				rg.addText("From date:"+bean.getFromDate(), 0, 1, 0);
			
			if(!bean.getBranchcode().equals(""))
				rg.addText("Branch Name:"+bean.getBranchname(), 0, 1, 0);
		    	//titles+="\t\t\tBranch Name:"+bean.getBranchname()+"\t\t\t\t\t\t\t\t\t\t\t";
			
			if(!bean.getDeptCode().equals(""))
				rg.addText("Department Name:"+bean.getDepartment(), 0, 1, 0);
		    	//titles+="\t\t\tDepartment Name:"+bean.getDepartment()+"\t\t\t\t\t\t\t\t\t\t\t";
		
			if(!bean.getDesgCode().equals(""))
				rg.addText("Designation Name:"+bean.getDesgname(), 0, 1, 0);
		    	//titles+="\t\t\t Designation Name:"+bean.getDesgname()+"\t\t\t\t\t\t\t\t\t\t\t";
			
			if(!bean.getDivCode().equals(""))
				rg.addText("Division Name:"+bean.getDivName(), 0, 1, 0);
		    //	titles+="\t\t\tDivision Name:"+bean.getDivName()+"\t\t\t\t\t\t\t\t\t\t\t";
			if(!bean.getStatus().equals(""))
				rg.addText("Status:"+String.valueOf(status), 0, 1, 0);
			
			if(!bean.getCreditCode().equals(""))
				rg.addText("Credit Name:"+bean.getCreditName(), 0, 1, 0);
			
			if(!bean.getVchCode().equals(""))
				rg.addText("Voucher Head:"+bean.getVchName(), 0, 1, 0);
			
		}
		
		
		try {
			System.out.println("Query..........!!!!"+query);
			Object[][] claimData = getSqlModel().getSingleResult(query+filterQuery);
			Object[][] vchData = getSqlModel().getSingleResult(vchQuery+vchFilterQuery);
			Object[][] claimFinalObj;
			Object[][] vchFinalObj;
			double totalcreditamt=0.0;
			double totalVchAmt=0.0;
			if (claimData!=null && claimData.length>0) {
				/*if(!bean.getCreditCode().equals(""))
				{*/
					claimFinalObj = new Object[claimData.length+1][7];
					int i;
					for ( i= 0; i < claimData.length; i++) {

						claimFinalObj[i][0] = claimData[i][6]; //REQ. NO.
						claimFinalObj[i][1] = claimData[i][0];//Employee Id
						claimFinalObj[i][2] = claimData[i][1];//Employee Name
						claimFinalObj[i][3] = claimData[i][2];//Claim Date
						claimFinalObj[i][4] = claimData[i][3]; //credit name 
						claimFinalObj[i][5] = claimData[i][4]; //Claim Amount
						claimFinalObj[i][6] = claimData[i][5];//Status
						totalcreditamt+=Double.parseDouble(String.valueOf(claimData[i][4]));

					}
					for (int k = 0; k <7; k++) {
						claimFinalObj[i][k]="";
					}
					claimFinalObj[i][2]="Total Amount:";
					claimFinalObj[i][5]=String.valueOf(totalcreditamt);
					
					rg.addText("\n", 0, 0, 0);
					rg.addText("\n", 0, 0, 0);
					int[] alignment1={1,0,0,1,0,2,0};
					int[] cellWidth1={10,15,30,15,15,15,15};
					String []colNames1={"Req.No","Employee Id","Employee Name","Claim Date","Credit Name","Claim Amount","Claim Status"};
					rg.addText("\nClaim Details :", 0, 0, 0);
					rg.tableBodyBold(colNames1, claimFinalObj, cellWidth1, alignment1, 12);
					
				
			}
			
			if (vchData!=null && vchData.length>0) {
				/*if(!bean.getCreditCode().equals(""))
				{*/
					vchFinalObj = new Object[vchData.length+1][7];
					int i;
					for ( i= 0; i < vchData.length; i++) {

						vchFinalObj[i][0] = vchData[i][6]; //REQ. NO.
						vchFinalObj[i][1] = vchData[i][0];//Employee Id
						vchFinalObj[i][2] = vchData[i][1];//Employee Name
						vchFinalObj[i][3] = vchData[i][2];//Claim Date
						vchFinalObj[i][4] = vchData[i][3]; //credit name 
						vchFinalObj[i][5] = vchData[i][4]; //Claim Amount
						vchFinalObj[i][6] = vchData[i][5];//Status
						totalVchAmt+=Double.parseDouble(String.valueOf(vchData[i][4]));

					}
					for (int k = 0; k <7; k++) {
						vchFinalObj[i][k]="";
					}
					vchFinalObj[i][2]="Total Amount:";
					vchFinalObj[i][5]=String.valueOf(totalVchAmt);
					
					rg.addText("\n", 0, 0, 0);
					rg.addText("\n", 0, 0, 0);
					int[] alignment1={1,0,0,1,0,2,0};
					int[] cellWidth1={10,15,30,15,15,15,15};
					String []colNames1={"Req.No","Employee Id","Employee Name","Claim Date","Voucher Head","Voucher Amount","Claim Status"};
					rg.addText("\nVoucher Details :", 0, 0, 0);
					rg.tableBodyBold(colNames1, vchFinalObj, cellWidth1, alignment1, 12);
			}
				
			if ((claimData==null || claimData.length<=0) &&(vchData==null || vchData.length<=0)) {
				rg.addText("\t\tNo records to display", 0, 1, 0);
				
			}else if(bean.getEmpId().equals(""))
				//rg=getDeptStatistic(bean,claimData,filterQuery,vchData,vchFilterQuery);
			
			
			//rg.addText("\n", 0, 0, 0);
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	
	}
	
public Vector getDeptStatistic(ClaimMisReport bean,Object[][] claimObj,String filterSubQuery,Object[][] vchObj,String vchSubQuery){
		
		Object [][]creditHead =null;
		Object [][]vchHead =null;
		Object [][]deptName=null;
		Vector deptStaticVect=new Vector();
		if(!bean.getCreditCode().equals("")){
			creditHead =new Object[1][2];
			creditHead[0][0]=bean.getCreditCode();
			creditHead[0][1]=bean.getCreditName();
		}else{
			creditHead =getSqlModel().getSingleResult("SELECT DISTINCT HRMS_CASH_CLAIM_DTL.CLAIM_CREDIT_CODE,CREDIT_NAME FROM HRMS_CASH_CLAIM_DTL"
													+" LEFT JOIN HRMS_CREDIT_HEAD ON(CREDIT_CODE=CLAIM_CREDIT_CODE) ORDER BY UPPER(CREDIT_NAME)");
		}
		if(!bean.getVchCode().equals("")){
			vchHead =new Object[1][2];
			vchHead[0][0]=bean.getVchCode();
			vchHead[0][1]=bean.getVchName();
		}else{
			vchHead =getSqlModel().getSingleResult("SELECT DISTINCT CLAIM_VOUCHER_CODE,VCH_NAME FROM HRMS_CASH_CLAIM_VOUCHER"
													+" LEFT JOIN HRMS_VCH_HD ON(VCH_CODE=CLAIM_VOUCHER_CODE) ORDER BY UPPER(VCH_NAME)");
		}
		if(!bean.getDeptCode().equals("")){
			deptName =new Object[1][2];
			deptName[0][0]=bean.getDeptCode();
			deptName[0][1]=bean.getDepartment();
		}else{
		deptName =getSqlModel().getSingleResult("SELECT DISTINCT DEPT_ID,DEPT_NAME FROM HRMS_CASH_CLAIM_HDR"
								+" INNER JOIN HRMS_EMP_OFFC ON(EMP_ID=CLAIM_EMPID)"
								+" INNER JOIN HRMS_DEPT ON(EMP_DEPT=DEPT_ID) ORDER BY UPPER(DEPT_NAME)");
		}
		/*
		 * FOR CASH CLAIM
		 * 
		 */
		Object [][] deptStaticData=null;
		String dataQuery="";
		int deptNameLength =deptName.length;
		
		
		String colNames []=new String[deptNameLength+3] ;
		int cellwidth[]=new int[deptNameLength+3];
		int alignment[]=new int[deptNameLength+3];
		
		int colNameLength =colNames.length;
		colNames[1]="Credit Name";
		cellwidth[1]=25;
		alignment[1]=0;
		
		colNames[0]="Sr.No.";
		cellwidth[0]=5;
		alignment[0]=1;
		
		colNames[colNameLength-1]="Total";
		cellwidth[colNameLength-1]=25;
		alignment[colNameLength-1]=2;
		
		for (int i = 2; i < colNameLength-1; i++) {
			colNames[i]=String.valueOf(deptName[i-2][1]);
			cellwidth[i]=20;
			alignment[i]=2;
		}
		if (claimObj!=null && claimObj.length>0) {
			
		
		dataQuery ="SELECT TO_CHAR(NVL(SUM(CLAIM_AMOUNT),0),9999999999990.99) FROM HRMS_CREDIT_HEAD "
								+" LEFT JOIN HRMS_CASH_CLAIM_DTL ON(CLAIM_CREDIT_CODE=CREDIT_CODE)"
								+" LEFT JOIN HRMS_CASH_CLAIM_HDR ON(HRMS_CASH_CLAIM_HDR.CLAIM_ID=HRMS_CASH_CLAIM_DTL.CLAIM_ID)"
								+" LEFT JOIN HRMS_EMP_OFFC ON(EMP_ID=CLAIM_EMPID)"
								+" LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=EMP_DEPT)";
		deptStaticData =new Object[creditHead.length+1][deptName.length+3];
		Object tempAmtData[][]=null;
		
		
		for (int i = 0; i < deptStaticData.length; i++) {
			if(i !=deptStaticData.length-1){
				double creditHeadTot =0.0;
			for (int j = 0; j < deptStaticData[0].length; j++) {
				
				if(j==0){
					deptStaticData[i][j] = i+1;
				}else if(j==1 && i !=deptStaticData.length-1){
					deptStaticData[i][j] = String.valueOf(creditHead[i][1]);
				}else if(j==deptStaticData[0].length-1){
					deptStaticData[i][j] = Utility.twoDecimals(creditHeadTot);
				}
				else{
					tempAmtData =getSqlModel().getSingleResult(dataQuery +" WHERE CLAIM_CREDIT_CODE="+String.valueOf(creditHead[i][0])+" AND DEPT_ID="+String.valueOf(deptName[j-2][0])+filterSubQuery);
					if(tempAmtData !=null || tempAmtData.length>0){
						deptStaticData[i][j] = String.valueOf(tempAmtData[0][0]);
						creditHeadTot += Double.parseDouble(String.valueOf(deptStaticData[i][j]));
					}
				}
			}
			}else{
				deptStaticData[i][1] ="Total";
			}
		}
			double totalAmt =0.00;
			for (int j = 2; j < deptStaticData[0].length-1; j++) {
				double deptTot =0.0;
				for (int i = 0; i < deptStaticData.length; i++) {
					if(i !=deptStaticData.length-1){
						deptTot +=Double.parseDouble(String.valueOf(deptStaticData[i][j]));
					}else {
						deptStaticData[i][j] =Utility.twoDecimals(deptTot);
						totalAmt +=deptTot;
					}
				}
			}
			deptStaticData[deptStaticData.length-1][deptStaticData[0].length-1]=Utility.twoDecimals(totalAmt);
			deptStaticData[deptStaticData.length-1][0]="";
			
		
		
		
		deptStaticVect.add(colNames);
		deptStaticVect.add(deptStaticData);
		deptStaticVect.add(cellwidth);
		deptStaticVect.add(alignment);
		
		}
		
		/*
		 * END CASH CLAIM
		 */
		
		
		/*
		 *FOR VOUCHER START 
		 */
		if (vchObj!=null && vchObj.length>0) {
	   dataQuery =" SELECT TO_CHAR(NVL(SUM(CLAIM_VOUCHER_AMOUNT),0),9999999999990.99) FROM HRMS_VCH_HD" 
				  +" LEFT JOIN HRMS_CASH_CLAIM_VOUCHER ON(CLAIM_VOUCHER_CODE=VCH_CODE)"
				  +" LEFT JOIN HRMS_CASH_CLAIM_HDR ON(HRMS_CASH_CLAIM_HDR.CLAIM_ID=HRMS_CASH_CLAIM_VOUCHER.CLAIM_ID)"
				  +" LEFT JOIN HRMS_EMP_OFFC ON(EMP_ID=CLAIM_EMPID)"
				  +" LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=EMP_DEPT)";
		deptStaticData = new Object[vchHead.length + 1][deptName.length + 3];
		Object tempVchData[][] = null;

		for (int i = 0; i < deptStaticData.length; i++) {
			if (i != deptStaticData.length - 1) {
				double vchHeadTot = 0.0;
				for (int j = 0; j < deptStaticData[0].length; j++) {

					if (j == 0) {
						deptStaticData[i][j] = i + 1;
					} else if (j == 1 && i != deptStaticData.length - 1) {
						deptStaticData[i][j] = String.valueOf(vchHead[i][1]);
					} else if (j == deptStaticData[0].length - 1) {
						deptStaticData[i][j] = Utility
								.twoDecimals(vchHeadTot);
					} else {
						tempVchData = getSqlModel().getSingleResult(
								dataQuery + " WHERE CLAIM_VOUCHER_CODE="
										+ String.valueOf(vchHead[i][0])
										+ " AND DEPT_ID="
										+ String.valueOf(deptName[j - 2][0])
										+ vchSubQuery);
						if (tempVchData != null || tempVchData.length > 0) {
							deptStaticData[i][j] = String
									.valueOf(tempVchData[0][0]);
							vchHeadTot += Double.parseDouble(String
									.valueOf(deptStaticData[i][j]));
						}
					}
				}
			} else {
				deptStaticData[i][1] = "Total";
			}
		}
		double totalVchAmt = 0.00;
		for (int j = 2; j < deptStaticData[0].length - 1; j++) {
			double deptTot = 0.0;
			for (int i = 0; i < deptStaticData.length; i++) {
				if (i != deptStaticData.length - 1) {
					deptTot += Double.parseDouble(String
							.valueOf(deptStaticData[i][j]));
				} else {
					deptStaticData[i][j] = Utility.twoDecimals(deptTot);
					totalVchAmt += deptTot;
				}
			}
		}
		deptStaticData[deptStaticData.length - 1][deptStaticData[0].length - 1] = Utility
				.twoDecimals(totalVchAmt);
		deptStaticData[deptStaticData.length - 1][0] = "";
		colNames[1]="Voucher Head";
		
		deptStaticVect.add(colNames);
		deptStaticVect.add(deptStaticData);
		deptStaticVect.add(cellwidth);
		deptStaticVect.add(alignment);
		}

		return deptStaticVect;
	}

	public void generateReportGeneral(ClaimMisReport bean,
			HttpServletResponse response, String code) {

		String title = "Claim Mis Report";
		String type = bean.getReporttype();
		System.out.println("Claim Mis Report Type" + String.valueOf(type));
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(type,
				title);

		String comname = "Navy";
		String[] colNames = { "S.No.", "Employee Id", "Employee Name",
				"Claim Date", "Claim Amount", "Claim Status" };
		int[] alignment = { 1, 0, 0, 1, 1, 1 };
		int[] cellWidth = { 10, 15, 30, 15, 15, 15 };

		String query =

		" SELECT EMP_TOKEN,TITLE_NAME||' '||EMP_FNAME||''||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(CLAIM_DATE,'DD-MM-YYYY'),"
				+ " NVL(CLAIM_TOTAL_AMOUNT,0),CASE WHEN CLAIM_STATUS='A' THEN 'Approved' WHEN CLAIM_STATUS='D' THEN 'Disbursed' else 'Pending' END  FROM HRMS_CASH_CLAIM_HDR "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_CASH_CLAIM_HDR.CLAIM_EMPID)"
				+ " LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE) WHERE EMP_ID="
				+ code;

		// if(!(bean.getEmpName().equals("") || bean.getEmpName()==null)){
		// query=query+" AND EMP_ID="+bean.getEmpId();

		// }

		if (!(bean.getFromDate().equals("") || bean.getFromDate() == null)) {
			query = query + " AND TO_CHAR(CLAIM_DATE,'DD-MM-YYYY') >='"
					+ bean.getFromDate() + "'";

		}

		if (!bean.getToDate().equals("") || bean.getToDate().length() != 0) {

			query = query + " AND TO_CHAR(CLAIM_DATE,'DD-MM-YYYY') <='"
					+ bean.getToDate() + "'";

		}

		query = query + " ORDER BY CLAIM_ID";
		int j = 1;
		Object[][] values = getSqlModel().getSingleResult(query);
		Object[][] param = new Object[values.length][6];
		for (int i = 0; i < values.length; i++) {

			param[i][0] = j++;//Serial No.
			param[i][1] = values[i][0];//Employee Id
			param[i][2] = values[i][1];//Employee Name
			param[i][3] = values[i][2];//Claim Date
			param[i][4] = values[i][3];//Claim Amount
			param[i][5] = values[i][4];//Status

		}
		rg.addText("\n", 0, 0, 0);
		rg.addText(title, 0, 0, 0);
		rg.addText("\n", 0, 0, 0);

		rg.tableBody(colNames, param, cellWidth, alignment);

		
		
		rg.addText("\n",0,0,0);
		rg.createReport(response);
		
	
	}
	
	
	

}
