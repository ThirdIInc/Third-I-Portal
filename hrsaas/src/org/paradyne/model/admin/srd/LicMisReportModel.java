package org.paradyne.model.admin.srd;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.admin.srd.LICMisReportMaster;
import org.paradyne.bean.payroll.CreditMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;


public class LicMisReportModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LicMisReportModel.class);
	//NumberFormat formatter = new DecimalFormat("#0.00");
//	public String getLICMISReport(HttpServletResponse response,
//			LICMisReportMaster bean) {
//
//		try {
//			String reportName = "LIC Mis Report";
//			org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator("Xls", reportName);
//			rg.addTextBold("LIC Mis Report", 0, 1, 0);
//			
// 			String query = "SELECT EMP_TOKEN,EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME,LIC_NAME,LIC_POLICY_NUMBER," 
// 					+" LIC_INSURANCE_AMOUNT, LIC_MONTHLY_PREMIUM,HRMS_DEBIT_HEAD.DEBIT_NAME FROM  HRMS_LIC"
// 					+" LEFT JOIN HRMS_DEBIT_HEAD ON HRMS_LIC.LIC_PAID_IN_DEBIT_CODE = HRMS_DEBIT_HEAD.DEBIT_CODE	"
//					+" INNER JOIN  HRMS_EMP_OFFC ON(HRMS_LIC.EMP_ID=HRMS_EMP_OFFC.EMP_ID) WHERE  HRMS_EMP_OFFC.EMP_STATUS='S'";
//			 			
//		 				if (!(bean.getDivCode().equals(""))
//						   	&& !(bean.getDivCode() == null)
//						   	&& !bean.getDivCode().equals("null")) {
//							query += " AND HRMS_EMP_OFFC.EMP_DIV=" + bean.getDivCode()+ " ";
//						}
// 						if (!(bean.getEmpid().equals(""))
//							   	&& !(bean.getEmpid() == null)
//							   	&& !bean.getEmpid().equals("null")) {
//								query += " AND HRMS_EMP_OFFC.EMP_ID=" + bean.getEmpid()+ " ";
//						}
//					    
//					   if (!(bean.getCenterId().equals(""))
//							&& !(bean.getCenterId() == null)
//							&& !bean.getCenterId().equals("null")) {
//							query += " AND HRMS_EMP_OFFC.EMP_CENTER="+ bean.getCenterId() + " ";
//						}
//					   
//						if (!(bean.getDeptCode().equals(""))
//						    && !(bean.getDeptCode() == null)
//						    && !bean.getDeptCode().equals("null")) {
//							query += " AND HRMS_EMP_OFFC.EMP_DEPT="+ bean.getDeptCode() + " ";
//						}
//						if (!(bean.getDesgCode().equals(""))
//							&& !(bean.getDesgCode() == null)
//							&& !bean.getDesgCode().equals("null")) {
//							query += " AND HRMS_EMP_OFFC.EMP_RANK="+ bean.getDesgCode() + " ";
//						}	
//						if (!(bean.getPayBillId().equals(""))
//								&& !(bean.getPayBillId() == null)
//								&& !bean.getPayBillId().equals("null")) {
//								query += " AND HRMS_EMP_OFFC.EMP_PAYBILL="+ bean.getPayBillId()+ " ";
//						}
//						query+=" ORDER BY EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME ";
//
//			Object licReportData[][] = getSqlModel().getSingleResult(query);
//			Object finalData[][]=null;
//			String[] colNames = { "Employee Id", "Employee Name","L.I.C Name", "Policy No","Insurance Amount", 
//					"Premium Amount","LIC Deducted Under Debit"};
//			int[] cellWidth = { 20, 25, 25, 15, 25, 25, 25 };
//			int[] alignment = { 1, 1, 1, 1, 1, 1, 1 };
//			if(licReportData != null && licReportData.length > 0) {
//				finalData = new Object[licReportData.length][colNames.length];
//				for(int cnt=0;cnt<licReportData.length;cnt++)
//				{
//				  finalData[cnt][0]=checkNull(String.valueOf(licReportData[cnt][0]));
//				  finalData[cnt][1]=checkNull(String.valueOf(licReportData[cnt][1]));
//				  finalData[cnt][2]=checkNull(String.valueOf(licReportData[cnt][2]));
//				  finalData[cnt][3]=checkNull(String.valueOf(licReportData[cnt][3]));
//				  finalData[cnt][4]=checkNull(String.valueOf(licReportData[cnt][4]));
//				  finalData[cnt][5]=checkNull(String.valueOf(licReportData[cnt][5]));
//				  finalData[cnt][6]=checkNull(String.valueOf(licReportData[cnt][6]));
//				}
//			}
//			
//			 rg.setFName("LIC MIS Report");
//			 rg.addFormatedText("\n",0, 0, 1, 0);
//
//			if (finalData != null && finalData.length > 0) {
//				rg.tableBody(colNames, finalData, cellWidth, alignment);
//				rg.addText("\n", 0, 0, 0);
//			} else {
//				rg.addTextBold("There is no data to display.", 0, 1, 0);
//			}// end of else
//
//			rg.createReport(response);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	public String checkNull(String result){
//		if(result== null ||result.equals("null")||result.equals("")){
//			return "";
//		}
//		else{
//			return result;
//		}
//		
//	}
	
//Added by Shashank....Begin....
	
	public void getLICReport(LICMisReportMaster licBean, HttpServletRequest request,
			HttpServletResponse response, String reportPath) {

		ReportDataSet rds = new ReportDataSet();
		String type = licBean.getReport(); // Pdf/Xls/Doc
		rds.setReportType(type);
		String fileName = "LIC MIS Report Details " + Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setReportName("LIC MIS Report Details ");
		//rds.setTotalColumns(13);
		rds.setShowPageNo(true);
		rds.setGeneratedByText(licBean.getUserEmpId());
		rds.setUserEmpId(licBean.getUserEmpId());
		
		rds.setPageSize("TABLOID");
		rds.setPageOrientation("landscape");
		rds.setShowPageNo(true);
		rds.setTotalColumns(8);
		
		// Report Generator Starts here
		org.paradyne.lib.ireportV2.ReportGenerator rg = null;
		
		String filters = "";
		boolean flag = false;
		if (!licBean.getDivisionName().equals("")) {
			filters += "\n\nDivision : " + licBean.getDivisionName();
		}
		if (!licBean.getCenterName().equals("")) {
			
			filters += "\n\nBranch : " + licBean.getCenterName();
		}

		if (!licBean.getEmpName().equals("")) {
			
			filters += "\n\nEmployee Name : " + licBean.getEmpName();
		}
		
        if (!licBean.getPayBillName().equals("")) {
			
			filters += "\n\nPay Bill Name : " + licBean.getPayBillName();
		}
		
		
		
		if (reportPath.equals("")) {
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);
		}
		else{
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context, request);
			request.setAttribute("reportPath", reportPath+fileName+"."+type);			
			request.setAttribute("action", "/srd/LicMisReport_input.action");
			request.setAttribute("fileName", fileName + "." + type);
			// Initial Page Action name
			}
		
		TableDataSet filterData = new TableDataSet();
		filterData.setData(new Object[][] { { filters } });
		filterData.setCellAlignment(new int[] { 0 });
		filterData.setCellWidth(new int[] { 100 });
		filterData.setBodyFontStyle(1);
		filterData.setCellColSpan(new int[] { 8 });
		filterData.setBorder(false);
		
		filterData.setCellNoWrap(new boolean[]{false});

		rg.addTableToDoc(filterData);

		
		String query = "SELECT distinct(rownum),EMP_TOKEN,EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME,LIC_NAME,LIC_POLICY_NUMBER," 
				+" LIC_INSURANCE_AMOUNT, LIC_MONTHLY_PREMIUM,HRMS_DEBIT_HEAD.DEBIT_NAME FROM  HRMS_LIC"
				+" LEFT JOIN HRMS_DEBIT_HEAD ON HRMS_LIC.LIC_PAID_IN_DEBIT_CODE = HRMS_DEBIT_HEAD.DEBIT_CODE	"
			+" INNER JOIN  HRMS_EMP_OFFC ON(HRMS_LIC.EMP_ID=HRMS_EMP_OFFC.EMP_ID) WHERE  HRMS_EMP_OFFC.EMP_STATUS='S'";
	 	
		
		
		if (!(licBean.getDivCode().equals(""))
			   	&& !(licBean.getDivCode() == null)
			   	&& !licBean.getDivCode().equals("null")) {
				query += " AND HRMS_EMP_OFFC.EMP_DIV IN (" + licBean.getDivCode()+ ") ";
			}
				if (!(licBean.getEmpid().equals(""))
				   	&& !(licBean.getEmpid() == null)
				   	&& !licBean.getEmpid().equals("null")) {
					query += " AND HRMS_EMP_OFFC.EMP_ID=" + licBean.getEmpid()+ " ";
			}
		    
		   if (!(licBean.getCenterId().equals(""))
				&& !(licBean.getCenterId() == null)
				&& !licBean.getCenterId().equals("null")) {
				query += " AND HRMS_EMP_OFFC.EMP_CENTER IN ("+ licBean.getCenterId() + ") ";
			}
		   
			if (!(licBean.getPayBillId().equals(""))
					&& !(licBean.getPayBillId() == null)
					&& !licBean.getPayBillId().equals("null")) {
					query += " AND HRMS_EMP_OFFC.EMP_PAYBILL IN ("+ licBean.getPayBillId()+ ") ";
			}
			//query+=" ORDER BY EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME ";
		
		
		Object[][] queryData = getSqlModel().getSingleResult(query);
		//Defining Tabular Structure and data //NVL(CREDIT_ISEXEMPT,'N')
		TableDataSet tdstable = new TableDataSet();
			
		String[] colNames = {"SrNo", "Employee Id", "Employee Name","L.I.C Name", "Policy No","Insurance Amount", 
				"Premium Amount","LIC Deducted Under Debit"};
		int[] cellWidth = { 10,10, 25, 25, 15, 25, 25, 25 };
		int[] alignment = { 0,0, 0, 0, 0, 2, 2,0 };
		

		
		tdstable.setHeader(colNames);
		
		tdstable.setHeaderTable(true);
		tdstable.setData(queryData);
		tdstable.setHeaderBorderDetail(3);
		tdstable.setCellAlignment(alignment);
		tdstable.setCellWidth(cellWidth);
		tdstable.setBorderDetail(3);
        tdstable.setHeaderBorderColor(new BaseColor(0,255,0));
		rg.addTableToDoc(tdstable);
		//tdstable.setCellColSpan(new int[] { 8 });
		
		
		
		
		rg.process();
		
		if(reportPath.equals("")){
		rg.createReport(response);
		}
		else{
		/* Generates the report as attachment*/
		rg.saveReport(response);
		}
			
	}
	//Added by Shashank....End.....
	
	}
