package org.struts.action.payroll;

import org.paradyne.bean.payroll.SalaryRegister;
import org.paradyne.model.payroll.SalaryRegisterModel;
import org.struts.lib.ParaActionSupport;

/*
 *   @author Venkatesh and Pradeep
 *   Date:21-04-2008
 */
public class SalaryRegisterAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	
	SalaryRegister salReg;

	public Object getModel() {
		return salReg;
	}

	@Override
	public void prepare_withLoginProfileDetails() throws Exception {
		salReg.setRadioChk("department");
	}

	public void prepare_local() throws Exception {
		salReg = new SalaryRegister();
		salReg.setMenuCode(84);

	}

	public SalaryRegister getSalReg() {
		return salReg;
	}

	public void setSalReg(SalaryRegister salReg) {
		this.salReg = salReg;
	}

	/*
	 * Following function is called in the jsp page.
	 */
	public String report() throws Exception {
		SalaryRegisterModel model = new SalaryRegisterModel();
		model.initiate(context, session);
		String reportPath = "";
		model.generateReport(salReg, request, response, reportPath);
		model.terminate();
		return null;
	}
	
	/**
	 * Used to send the generated report through mail
	 * @return
	 */
	public final String mailReport(){
		try {
			SalaryRegisterModel model = new SalaryRegisterModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll" + poolName + "/";
			model.generateReport(salReg, request, response, reportPath);
			
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	/*
	 * Following function is called in jsp page to show the designation code and
	 * name
	 */
	public String f9rankaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DISTINCT RANK_ID,TO_CHAR(RANK_NAME) FROM  HRMS_RANK  "
				+ " ORDER BY  RANK_ID ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		
		String header = getMessage("designation");
		String textAreaId ="paraFrm_desgName";
		String hiddenFieldId ="paraFrm_desgCode";
		String submitFlag = "false";
		String submitToMethod = "";
		
		setMultiSelectF9(query, header, textAreaId, hiddenFieldId,submitFlag,submitToMethod);
	
		return "f9multiSelect";
	}


	/*
	 * Following function is called in jsp page to show the division code and
	 * name
	 */
	public final String f9div() {
		try {
			String query = "SELECT DISTINCT DIV_ID, TO_CHAR(DIV_NAME) FROM  HRMS_DIVISION";
			if (salReg.getUserProfileDivision() != null
					&& salReg.getUserProfileDivision().length() > 0)
				query += " WHERE DIV_ID IN (" + salReg.getUserProfileDivision()
						+ ")";
			query += " ORDER BY  DIV_ID ";
			String header = getMessage("division");
			String textAreaId ="paraFrm_divName";
			String hiddenFieldId ="paraFrm_divCode";
			String submitFlag = "false";
			String submitToMethod = "";
			
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,submitFlag,submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}

	/*
	 * Following function is called in jsp page to show the type code and name
	 */

	
	public final String f9type() {
		try {
			String query = "SELECT HRMS_EMP_TYPE.TYPE_ID, HRMS_EMP_TYPE.TYPE_NAME FROM HRMS_EMP_TYPE " 
				+ " WHERE HRMS_EMP_TYPE.IS_ACTIVE='Y'"
				+ " ORDER BY HRMS_EMP_TYPE.TYPE_ID";
			
			String header = getMessage("employee.type");
			String textAreaId ="paraFrm_typeName";
			String hiddenFieldId ="paraFrm_typeCode";
			String submitFlag = "false";
			String submitToMethod = "";
			
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,submitFlag,submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	

	/*
	 * Following function is called in jsp page to show the department code and
	 * name
	 */
	
	
	public final String f9deptaction() {
		try {
			String query = " SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT "
				+ " ORDER BY  DEPT_NAME ";
			
			String header = getMessage("department");
			String textAreaId ="paraFrm_deptName";
			String hiddenFieldId ="paraFrm_deptCode";
			String submitFlag = "false";
			String submitToMethod = "";
			
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,submitFlag,submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}

	/*
	 * Following function is called in jsp page to show the branch code and name
	 */
	public String f9centeraction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER "
				+ " ORDER BY  CENTER_NAME ";

		String header = getMessage("branch");
		String textAreaId ="paraFrm_branchName";
		String hiddenFieldId ="paraFrm_branchCode";
		String submitFlag = "false";
		String submitToMethod = "";
		
		setMultiSelectF9(query, header, textAreaId, hiddenFieldId,submitFlag,submitToMethod);

		return "f9multiSelect";
	}
	/*
	 * Following function is called in jsp page to show the paybill code and
	 * name
	 */
	public String f9paybill() {
		try {
			String query = " SELECT DISTINCT HRMS_PAYBILL.PAYBILL_ID, NVL(HRMS_PAYBILL.PAYBILL_GROUP,' ') FROM HRMS_PAYBILL "; 
			query +=" ORDER BY HRMS_PAYBILL.PAYBILL_ID";
			
			String header = getMessage("pay.bill");
			String textAreaId ="paraFrm_paybillName";
			String hiddenFieldId ="paraFrm_paybillId";
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,submitFlag,submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	
	/*
	 * Following function is called in jsp page to show the emp Grade code and
	 * name
	 */
	public String f9empgrade() throws Exception{
		try {
			String query = " SELECT DISTINCT HRMS_CADRE.CADRE_ID, NVL(HRMS_CADRE.CADRE_NAME,' ') FROM HRMS_CADRE ORDER BY HRMS_CADRE.CADRE_ID";
			
			String header = getMessage("grade");
			String textAreaId = "paraFrm_empGradeName";
			String hiddenFieldId = "paraFrm_empGradeId";
			String submitFlag = "false";
			String submitToMethod = "";
			
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag, submitToMethod);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	
	/*
	 * Following function is called in jsp page to show the costcenter code and
	 * name
	 */
	public String f9Costcenter() throws Exception{
		
		try {
			String query = " SELECT HRMS_COST_CENTER.COST_CENTER_ID,HRMS_COST_CENTER.COST_CENTER_NAME FROM HRMS_COST_CENTER ORDER BY COST_CENTER_ID";
			
			String header = getMessage("costcenter");
			String textAreaId = "paraFrm_costcentername";
			String hiddenFieldId = "paraFrm_costcenterid";
			String submitFlag = "false";
			String submitToMethod = "";
			
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag, submitToMethod);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	
	/*
	 * Following function is called in jsp page to show the sub-costcenter code and
	 * name
	 */
	public String f9SubCostcenter()throws Exception{
		
		try {
			String query = " SELECT HRMS_SUB_COST_CENTER.SUB_COST_CENTER_ID,HRMS_SUB_COST_CENTER.SUB_COST_CENTER_NAME FROM HRMS_SUB_COST_CENTER ORDER BY HRMS_SUB_COST_CENTER.SUB_COST_CENTER_ID";
			
			String header = getMessage("subcostcenter");
			String textAreaId = "paraFrm_subcostcentername";
			String hiddenFieldId = "paraFrm_subcostcenterid";
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