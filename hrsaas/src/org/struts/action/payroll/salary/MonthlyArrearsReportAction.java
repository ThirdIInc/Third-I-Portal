package org.struts.action.payroll.salary;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.salary.MonthlyArrearsReport;
import org.paradyne.model.payroll.salary.MonthlyArrearsReportModel;
import org.paradyne.model.reimbursement.ReimbursementBalanceReportModel;
import org.struts.lib.ParaActionSupport;

public class MonthlyArrearsReportAction extends ParaActionSupport {

 
	
	MonthlyArrearsReport monArrReport ;
	
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		monArrReport= new MonthlyArrearsReport();
		monArrReport.setMenuCode(643);

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return monArrReport;
	}

	public MonthlyArrearsReport getMonArrReport() {
		return monArrReport;
	}

	public void setMonArrReport(MonthlyArrearsReport monArrReport) {
		this.monArrReport = monArrReport;
	}
	
	public String  report()throws Exception{
		
		try {
			MonthlyArrearsReportModel model = new MonthlyArrearsReportModel();
			model.initiate(context, session);
			/*if (monArrReport.getCheckBrn().equals("Y")) {
				model.generateBrnReport(monArrReport, response, monArrReport
						.getCheckFlag());

			}else if (monArrReport.getCheckDept().equals("Y")) {
				model.generateDeptReport(monArrReport, response, monArrReport
						.getCheckFlag());

			}
			else {*/
			String reportPath = "";
			System.out.println("Report Type :--- Is consolidate : "+monArrReport
					.getCheckFlag());
				model.genRep(monArrReport,request, response, monArrReport
						.getCheckFlag(),reportPath);
			
			

			//}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	public String reset(){
		monArrReport.setArrearType("");
		monArrReport.setBranchCode("");
		monArrReport.setBranchName("");
		monArrReport.setDeptCode("");
		monArrReport.setDeptName("");
		monArrReport.setDivCode("");
		monArrReport.setDivName("");
		monArrReport.setOnHold("");
		monArrReport.setReport("");
		monArrReport.setCheckFlag("N");
		monArrReport.setCheckBrn("");
		monArrReport.setCheckDept("");
		monArrReport.setMonth("");
		monArrReport.setYear("");
		monArrReport.setPaybillname("");
		monArrReport.setPaybillid("");
		monArrReport.setGradeId("");
		monArrReport.setGradeName("");
		monArrReport.setCostcentername("");
		monArrReport.setCostcenterid("");
		return SUCCESS;
	}
	
	
	public final String mailReport(){
		try {
			MonthlyArrearsReportModel model = new MonthlyArrearsReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll" + poolName + "/";
			System.out.println("Report Type :--- Is consolidate : "+monArrReport
					.getCheckFlag());
			model.genRep(monArrReport,request, response, monArrReport
					.getCheckFlag(),reportPath);
			
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	
	public String f9div() throws Exception {
		
		
		try {
			String query = " SELECT DISTINCT DIV_ID,TO_CHAR(DIV_NAME) FROM  HRMS_DIVISION ";		
			if(monArrReport.getUserProfileDivision() !=null && monArrReport.getUserProfileDivision().length()>0)
				query+= " WHERE DIV_ID IN ("+monArrReport.getUserProfileDivision() +")"; 
				query+= " ORDER BY  DIV_ID ";
			String header = getMessage("division");
			String textAreaId = "paraFrm_monArrReport_divName";
			String hiddenFieldId = "paraFrm_monArrReport_divCode";
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
		
		
	}
	
	public String f9deptaction() throws Exception {
		
		try {
			/**
			 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
			 */
			String query = " SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT "
							+ " ORDER BY  DEPT_ID ";
			String header = getMessage("department");
			String textAreaId = "paraFrm_monArrReport_deptName";
			String hiddenFieldId = "paraFrm_monArrReport_deptCode";
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
		
		
	}
	
	public String f9Paybill()throws Exception{
		
		try {
			String query = " SELECT PAYBILL_ID,nvl(PAYBILL_GROUP,'') FROM HRMS_PAYBILL "
				+ " ORDER BY  PAYBILL_ID ";		
			String header = "Pay Bill Name";
			String textAreaId = "paraFrm_paybillname";
			String hiddenFieldId = "paraFrm_paybillid";
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	
	public String f9Grade()throws Exception{
		
		try {
			String query = " SELECT DISTINCT CADRE_ID,TO_CHAR(CADRE_NAME) FROM  HRMS_CADRE   ORDER BY CADRE_ID";
			
			String header = getMessage("reimbursement.grade");
			String textAreaId = "paraFrm_gradeName";
			String hiddenFieldId = "paraFrm_gradeId";
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}	

	public String f9Costcenter()throws Exception{
		
		try {
			String query = " SELECT COST_CENTER_ID,COST_CENTER_NAME FROM HRMS_COST_CENTER ORDER BY COST_CENTER_ID";
			
			String header = getMessage("reimbursement.costcenter");
			String textAreaId = "paraFrm_costcentername";
			String hiddenFieldId = "paraFrm_costcenterid";
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}

	public String f9centeraction() throws Exception {
		
		try {
			/**
			 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
			 */
			String query = " SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER "
				+ " ORDER BY  CENTER_ID ";

			String header = getMessage("branch");
			String textAreaId = "paraFrm_monArrReport_branchName";
			String hiddenFieldId = "paraFrm_monArrReport_branchCode";
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
		
		
	}
	
	

}
