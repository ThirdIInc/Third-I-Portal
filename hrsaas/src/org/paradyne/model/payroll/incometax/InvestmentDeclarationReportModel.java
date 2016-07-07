package org.paradyne.model.payroll.incometax;

import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.incometax.InvestmentDeclarationReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;

import com.lowagie.text.Font;

public class InvestmentDeclarationReportModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(InvestmentDeclarationReportModel.class);
	
	public String checkNull(String result) {
		
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
	
	public void generateReport(InvestmentDeclarationReport investmentBean,
			HttpServletResponse response) {
		
		try {
			ReportDataSet rds = new ReportDataSet();
			String reportType = "Xls";
			rds.setReportType(reportType);
			rds.setFileName("Investment Declaration Report");
			rds.setReportName("Investment Declaration Report");
			rds.setPageOrientation("landscape");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(rds);
			TableDataSet subtitleName = new TableDataSet();
			Object obj[][] = new Object[1][2];
			obj[0][0] = "Investment Declaration Report";
			obj[0][1] = " for the period " + investmentBean.getFromYear()+" to " + investmentBean.getToYear();
			subtitleName.setData(obj);
			subtitleName.setCellAlignment(new int[] { 1 });
			subtitleName.setCellWidth(new int[] { 100 });
			subtitleName.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,new Color(0, 0, 0));
			subtitleName.setBorder(false);
			subtitleName.setHeaderTable(true);
			subtitleName.setBlankRowsBelow(obj.length);
			rg.createHeader(subtitleName);
			
			String investmentNameQuery = "SELECT NVL(INV_NAME, ' '), INV_CODE  FROM HRMS_TAX_INVESTMENT ORDER BY INV_CODE ";
			Object[][] investmentNameObj = getSqlModel().getSingleResult(investmentNameQuery);
			
			String header[] = new String[investmentNameObj.length+2];
			
			if(investmentNameObj!=null && investmentNameObj.length > 0){
				header[0] = "Employee Id";
				header[1] = "Employee Name";
				for (int i = 2; i < investmentNameObj.length+2; i++) {
					header[i] = String.valueOf(investmentNameObj[i-2][0]);
				}
			}
			
			int[]alignment = new int [investmentNameObj.length+2];
			int[]cellwidth = new int [investmentNameObj.length+2];
			for (int i = 0; i < investmentNameObj.length+2; i++) {
				alignment[i] = 0;
				if(i==0){
					cellwidth[i] = 10;
				}else{
					cellwidth[i] = 30;
				}
			}
			
			HashMap empMap=null;
			String compositeKey = "SELECT HRMS_EMP_INVESTMENT.EMP_ID|| '#' || HRMS_EMP_INVESTMENT.INV_CODE, "
				+ " NVL(CASE  " +
				  " WHEN INV_TYPE = 'N' THEN NVL(INV_VALID_AMOUNT,0)"
				+ " WHEN (SELECT TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL) > (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER "
				+ " WHERE TDS_FINANCIALYEAR_FROM = "
				+ investmentBean.getFromYear()
				+ " AND  TDS_FINANCIALYEAR_TO = "
				+ investmentBean.getToYear()
				+ ") "
				+ " THEN NVL(INV_VERIFIED_AMOUNT,INV_VALID_AMOUNT) "
				+ " WHEN (SELECT TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL) < (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER "
				+ " WHERE TDS_FINANCIALYEAR_FROM = "
				+ investmentBean.getFromYear()
				+ " AND  TDS_FINANCIALYEAR_TO = "
				+ investmentBean.getToYear()
				+ ")  "
				+ " THEN NVL(INV_VALID_AMOUNT,0) "
				+ "  WHEN (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER " 
				+ "  WHERE TDS_FINANCIALYEAR_FROM = "+investmentBean.getFromYear()+" AND  TDS_FINANCIALYEAR_TO = "+investmentBean.getToYear()+") IS NULL THEN NVL(INV_VALID_AMOUNT,0)" 
				+ "  END,0) AS INVESTMENT_AMOUNT  "
				+ " FROM HRMS_EMP_INVESTMENT"
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_EMP_INVESTMENT.EMP_ID) "
				+" INNER JOIN HRMS_TAX_INVESTMENT ON (HRMS_TAX_INVESTMENT.INV_CODE=HRMS_EMP_INVESTMENT.INV_CODE)"
				+ " WHERE INV_FINYEAR_FROM = "
				+investmentBean.getFromYear()
				+ " AND INV_FINYEAR_TO = "
				+investmentBean.getToYear();
			
			if(!investmentBean.getDivisionId().equals("")){
				compositeKey+= " AND EMP_DIV ="+investmentBean.getDivisionId();
			}
			if(!investmentBean.getCenterID().equals("")){
				compositeKey+= " AND EMP_CENTER ="+investmentBean.getCenterID();
			}
			if(!investmentBean.getDesigID().equals("")){
				compositeKey+= " AND EMP_RANK ="+investmentBean.getDesigID();
			}
			compositeKey+= " ORDER BY HRMS_EMP_OFFC.EMP_ID, INV_CODE";
			
			empMap = getSqlModel().getSingleResultMap(compositeKey,0, 2 );
			
			String empDetailQuery = "SELECT DISTINCT EMP_TOKEN, NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '),HRMS_EMP_OFFC.EMP_ID" 
				+ " FROM HRMS_EMP_INVESTMENT " 
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_EMP_INVESTMENT.EMP_ID) " 
				+ " WHERE INV_FINYEAR_FROM = "
				+  investmentBean.getFromYear()
				+ " AND INV_FINYEAR_TO = "
				+investmentBean.getToYear();
			
			if(!investmentBean.getDivisionId().equals("")){
				empDetailQuery+= " AND EMP_DIV ="+investmentBean.getDivisionId();
			}
			if(!investmentBean.getCenterID().equals("")){
				empDetailQuery+= " AND EMP_CENTER ="+investmentBean.getCenterID();
			}
			if(!investmentBean.getDesigID().equals("")){
				empDetailQuery+= " AND EMP_RANK ="+investmentBean.getDesigID();
			}
			
			empDetailQuery+= " ORDER BY EMP_ID";
			
			Object[][] empObj = getSqlModel().getSingleResult(empDetailQuery);
			
			Object[][] finalObj = new Object[empObj.length][header.length];
			
			if(empObj!=null && empObj.length > 0){
				for (int i = 0; i < empObj.length; i++) {
					finalObj[i][0] = String.valueOf(empObj[i][0]);//emp_token
					finalObj[i][1] = String.valueOf(empObj[i][1]);//emp_name
					for (int j = 0; j < investmentNameObj.length; j++) {
						Object[][] amtObj=null;
						try {
							
							 amtObj = (Object[][]) empMap.get(checkNull(String.valueOf(empObj[i][2]))
											+ "#"+ checkNull(String.valueOf(investmentNameObj[j][1])));
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						//Check if amount exists for this investment
						if(amtObj!=null && amtObj.length>0){
							finalObj[i][j+2] =String.valueOf(amtObj[0][1]);
						}else{
							finalObj[i][j+2] = "0";
							
						}
					}
				}
			}
			
			if(finalObj!=null && finalObj.length > 0){
				TableDataSet empDetailsData = new TableDataSet();
				empDetailsData.setData(finalObj);
				empDetailsData.setHeader(header);
				empDetailsData.setCellAlignment(alignment);
				empDetailsData.setCellWidth(cellwidth);
				empDetailsData.setBorder(true);
				empDetailsData.setHeaderTable(true);
				empDetailsData.setBlankRowsBelow(1);
				rg.addTableToDoc(empDetailsData);
			}else{
				TableDataSet noData = new TableDataSet();
				noData.setData(new Object[][] { { "No data to display" } });
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
						new Color(0, 0, 0));
				noData.setBorder(false);
				noData.setHeaderTable(false);
				rg.addTableToDoc(noData);
			}
			rg.process();
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void generateHouseRentReport(
			InvestmentDeclarationReport investmentBean,
			HttpServletResponse response) {
		try {
			ReportDataSet rds = new ReportDataSet();
			String reportType = "Xls";
			rds.setReportType(reportType);
			rds.setFileName("House Rent Report");
			rds.setReportName("House Rent Report");
			rds.setPageOrientation("landscape");
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(rds);
			TableDataSet subtitleName = new TableDataSet();
			Object obj[][] = new Object[1][2];
			obj[0][0] = "House rent report";
			obj[0][1] = " for the period " + investmentBean.getFromYear()
					+ " to " + investmentBean.getToYear();
			subtitleName.setData(obj);
			subtitleName.setCellAlignment(new int[] { 1 });
			subtitleName.setCellWidth(new int[] { 100 });
			subtitleName.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,new Color(0, 0, 0));
			subtitleName.setBorder(false);
			subtitleName.setHeaderTable(true);
			subtitleName.setBlankRowsBelow(obj.length);
			rg.createHeader(subtitleName);
		
			String fromYear=investmentBean.getFromYear();
			String toYear=investmentBean.getToYear();
			
			
			HashMap AmtMap=new HashMap<String, String>();
			String hraAmountQuery=" SELECT DISTINCT EMP_ID||'#'||RENT_MONTH, RENT_AMOUNT  FROM HRMS_HOUSERENT_DTL "
			  +" INNER JOIN HRMS_HOUSERENT_HDR ON(HRMS_HOUSERENT_HDR.RENT_CODE=HRMS_HOUSERENT_DTL.RENT_CODE)"
			  +" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_HOUSERENT_HDR.RENT_EMPID) "
			  +" WHERE ";   
			if(!investmentBean.getFromYear().equals("")){
				hraAmountQuery+=" RENT_FROMYEAR ="+investmentBean.getFromYear();  
			}
			if(!investmentBean.getToYear().equals("")){
				hraAmountQuery +=" AND RENT_TOYEAR="+investmentBean.getToYear();
			}
			if(!investmentBean.getDivisionId().equals("")){
				hraAmountQuery +=" AND EMP_DIV="+investmentBean.getDivisionId();
			}	
			//hraAmountQuery+=" order by mon";
			Object [][]empRentObj=getSqlModel().getSingleResult(hraAmountQuery);
			if(empRentObj !=null && empRentObj.length>0){
				for (int i = 0; i < empRentObj.length; i++) {
					AmtMap.put(String.valueOf(empRentObj[i][0]), String.valueOf(empRentObj[i][1]));
				}
			}
			//AmtMap = getSqlModel().getSingleResultMap(hraAmountQuery,0, 2 );
			
			String empDetailQuery="SELECT DISTINCT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EMP_ID FROM HRMS_HOUSERENT_HDR"   
				 // +" INNER JOIN HRMS_HOUSERENT_HDR ON(HRMS_HOUSERENT_HDR.RENT_CODE=HRMS_HOUSERENT_DTL.RENT_CODE)"   
				  +" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_HOUSERENT_HDR.RENT_EMPID)"     
				  +" WHERE ";

			if(!investmentBean.getFromYear().equals("")){
			empDetailQuery+="RENT_FROMYEAR ="+investmentBean.getFromYear();  
			}
			if(!investmentBean.getToYear().equals("")){
			empDetailQuery +=" AND RENT_TOYEAR="+investmentBean.getToYear();
			}
			if(!investmentBean.getDivisionId().equals("")){
			empDetailQuery +=" AND EMP_DIV="+investmentBean.getDivisionId();
			}	
			Object[][] empObj = getSqlModel().getSingleResult(empDetailQuery);
			
			
			String header[] = {"Employee Id","Employee Name","Apr-"+fromYear+"","May-"+fromYear+"","Jun-"+fromYear+"","Jul-","Aug-","Sep-","Oct-"+fromYear+"","Nov-"+fromYear+"","Dec-"+fromYear+"","Jan-"+toYear+"","Feb-"+toYear+"","Mar-"+toYear+""};
			int[]alignment = {0,0,0,0,0,0,0,0,0,0,0,0,0,0};
			int[]cellwidth = {30,30,30,30,30,30,30,30,30,30,30,30,30,30};
			
			Object[][] finalObj = new Object[empObj.length][header.length];
			
			if(empObj!=null && empObj.length > 0){
				for (int i = 0; i < empObj.length; i++) {
					finalObj[i][0] = String.valueOf(empObj[i][0]);//emp_token
					finalObj[i][1] = String.valueOf(empObj[i][1]);//emp_name
					int k=4;
					for (int j = 2; j < header.length; j++) {
						
						String amountObj="0";
						try {
							if(k==13)
								k=1;
								try {
									amountObj = String.valueOf(AmtMap
											.get(checkNull(String
													.valueOf(empObj[i][2]))
													+ "#" + k));
								} catch (Exception e) {
									amountObj="0";
								}
								finalObj[i][j]=amountObj;
							
							/*else{
								amountObj=(Object[][])AmtMap.get(checkNull(String.valueOf(empObj[i][2]))+ "#"+ k);
								finalObj[i][j]=String.valueOf(amountObj[0][1]);
							}*/
							k++;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		
			if(finalObj!=null && finalObj.length > 0){
				TableDataSet empDetailsData = new TableDataSet();
				empDetailsData.setData(finalObj);
				empDetailsData.setHeader(header);
				empDetailsData.setCellAlignment(alignment);
				empDetailsData.setCellWidth(cellwidth);
				empDetailsData.setBorder(true);
				empDetailsData.setHeaderTable(true);
				empDetailsData.setBlankRowsBelow(1);
				rg.addTableToDoc(empDetailsData);
			}else{
				TableDataSet noData = new TableDataSet();
				noData.setData(new Object[][] { { "No data to display" } });
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,new Color(0, 0, 0));
				noData.setBorder(false);
				noData.setHeaderTable(false);
				rg.addTableToDoc(noData);
			}
			rg.process();
			rg.createReport(response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
