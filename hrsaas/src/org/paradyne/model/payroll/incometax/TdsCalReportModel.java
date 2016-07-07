package org.paradyne.model.payroll.incometax;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.incometax.TdsCalReport;
import org.paradyne.bean.payroll.incometax.TdsCalculation;
import org.paradyne.lib.ModelBase;

public class TdsCalReportModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	TdsCalReport  tdsCalReport =null;
	
	public void getFilters(TdsCalReport tdsCalReport){
		try {
				String query="SELECT DECODE(CONF_BRN_FLAG,'Y','true','N','false'),DECODE(CONF_DEPT_FLAG,'Y','true','N','false'),DECODE(CONF_PAYBILL_FLAG,'Y','true','N','false')" +
						",DECODE(CONF_EMPTYPE_FLAG,'Y','true','N','false'),DECODE(CONF_DIVISION_FLAG,'Y','true','N','false') FROM HRMS_SALARY_CONF";
				Object[][] result = getSqlModel().getSingleResult(query);
				tdsCalReport.setBranchFlag(String.valueOf(result[0][0]));
				tdsCalReport.setDepartmentFlag(String.valueOf(result[0][1]));
				tdsCalReport.setPaybillFlag(String.valueOf(result[0][2]));
				tdsCalReport.setEmptypeFlag(String.valueOf(result[0][3]));
				tdsCalReport.setDivisionFlag(String.valueOf(result[0][4]));
		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public boolean getReport(TdsCalReport tdsCalReport,HttpServletResponse response)
	{
		
		
		/*String query ="select HRMS_EMP_OFFC.EMP_TOKEN ,NVL(HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '|| "
		+" HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') as empname, TDS_GROSS_SALARY,TDS_EXCEMPTIONS,TDS_OTH_INCOME, "
		+" TDS_DEDUCTIONS,TDS_REBATE,TDS_PROF_TAX,TDS_TAXABLE_INCOME,TDS_TOTAL_TAX,TDS_SURCHARGE,TDS_EDUC_CESS,TDS_NET_TAX "
		+" ,TDS_TAX_PAID,TDS_TAXPERMONTH,decode(TDS_SALARY_DEDUCT_FLAG,'Y','Yes','N','No') FROM HRMS_TDS   "
		+" INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = HRMS_TDS.TDS_EMP_ID   "
		+" LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "
		+" WHERE 1=1 AND (EMP_LEAVE_DATE > TO_DATE('01-04-"
		+ tdsCalReport.getFromYear()+ "','DD-MM-YYYY') OR EMP_LEAVE_DATE IS NULL ) " +
				"AND TDS_GROSS_SALARY>0 AND TDS_FROM_YEAR="+tdsCalReport.getFromYear()+" " +
						"and TDS_TO_YEAR="+tdsCalReport.getToYear()+"  ";*/
		
		String query ="select HRMS_EMP_OFFC.EMP_TOKEN ,NVL(HRMS_EMP_OFFC.EMP_FNAME||' '|| "
			+" HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') as empname,NVL(TDS_CREDIT_AMT,0),NVL(TDS_PERQS_AMT,0),NVL(TDS_LEAVE_ENCASH_AMT,0),"
			+" NVL(TDS_CREDIT_AMT,0)+NVL(TDS_PERQS_AMT,0)+NVL(TDS_LEAVE_ENCASH_AMT,0) AS Total_Salary,NVL(TDS_OTH_INCOME,0),"
			+" TDS_GROSS_SALARY,TDS_EXCEMPTIONS,TDS_DEDUCTIONS,TDS_REBATE,TDS_PROF_TAX,TDS_TAXABLE_INCOME,TDS_TOTAL_TAX,TDS_SURCHARGE,TDS_EDUC_CESS,TDS_NET_TAX "
			+" ,TDS_TAX_PAID,TDS_REMAIN_MONTH,TDS_TAXPERMONTH FROM HRMS_TDS   "
			+" INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = HRMS_TDS.TDS_EMP_ID   "
			//+" LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "
			+" WHERE 1=1 AND (EMP_LEAVE_DATE > TO_DATE('01-04-"
			+ tdsCalReport.getFromYear()+ "','DD-MM-YYYY') OR EMP_LEAVE_DATE IS NULL ) " +
					"AND TDS_GROSS_SALARY>0 AND TDS_FROM_YEAR="+tdsCalReport.getFromYear()+" " +
							"and TDS_TO_YEAR="+tdsCalReport.getToYear()+"  ";
		
		if(!tdsCalReport.getDivisionCode().equals("")){
				query = query + " AND HRMS_EMP_OFFC.EMP_DIV="+tdsCalReport.getDivisionCode();
		}else{
			if(tdsCalReport.getUserProfileDivision() != null && tdsCalReport.getUserProfileDivision().length() > 0) {
				query = query + " AND HRMS_EMP_OFFC.EMP_DIV IN ("+tdsCalReport.getUserProfileDivision()+")";
			}
		}
		if(!tdsCalReport.getBranchCode().equals("")){
			query = query + " AND EMP_CENTER="+tdsCalReport.getBranchCode();
		}
		if(!tdsCalReport.getTypeCode().equals("")){
			query = query +" AND EMP_TYPE="+tdsCalReport.getTypeCode();
		}
		if(!tdsCalReport.getPayBillNo().equals("")){
			query = query + " AND EMP_PAYBILL="+tdsCalReport.getPayBillNo();
		}
		if(!tdsCalReport.getDeptCode().equals("")){
			query = query +" AND EMP_DEPT="+tdsCalReport.getDeptCode(); 
		}
		
		
		query = query+" order by UPPER(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) ";
		
			
			Object data[][]= getSqlModel().getSingleResult(query);
			
			String reportType="Xls";	
	
			String reportName="TDS Calculation Report";		
			
		
			
			String[] colNames={"Employee Id. ","Employee Name","Salary","Perks","Leave Encashment","Total Salary","Others","Gross Income","Exemptions","Chapter VI-A Deductions",
					"Section 80C ","PTax","Net Taxable Income","Total Tax","Surcharge","Education Cess","Net Tax","Tax Paid","Remaining Months","Tax Per Month"};

			int [] cellWidth={20,30,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25};

	       

			int [] alignment={1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};

			
			
			org.paradyne.lib.report.ReportGenerator rg=new org.paradyne.lib.report.ReportGenerator(reportType,reportName);
			rg.addText("TDS Calculation Report", 1, 1, 0);
			rg.addText("For Financial Year "+tdsCalReport.getFromYear()+" - "+tdsCalReport.getToYear(), 1,1,0);
			rg.setFName(""+reportName+"."+reportType);
			if(!tdsCalReport.getBranchCode().equals("")){
				rg.addText("Branch:"+tdsCalReport.getBranchName(),0,0,0);
			}
			if(!tdsCalReport.getTypeCode().equals("")){
				rg.addText("Employee Type:"+tdsCalReport.getTypeName(),0,0,0);
			}
			if(!tdsCalReport.getPayBillNo().equals("")){
				rg.addText("Paybill No:"+tdsCalReport.getPayBillNo(),0,0,0);
			}
			if(!tdsCalReport.getDeptCode().equals("")){
				rg.addText("Department:"+tdsCalReport.getDeptName(),0,0,0);
			}
			if(!tdsCalReport.getDivisionCode().equals("")){
				rg.addText("Division:"+tdsCalReport.getDivisionName(),0,0,0);
			}
			rg.tableBody(colNames,data,cellWidth,alignment);
           
			 rg.createReport(response);
			 	
		return true;
	}
}
