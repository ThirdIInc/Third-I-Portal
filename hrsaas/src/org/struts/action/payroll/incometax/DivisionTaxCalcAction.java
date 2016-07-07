/**
 * 
 */
package org.struts.action.payroll.incometax;

import java.util.Date;

import org.paradyne.bean.payroll.incometax.DivisionTaxCalc;
import org.paradyne.model.payroll.incometax.CommonTaxCalculationModel;
import org.paradyne.model.payroll.incometax.TdsCalReportModel;
import org.struts.lib.ParaActionSupport;

import com.ibm.icu.util.Calendar;

/**
 * @author aa0554
 *
 */
public class DivisionTaxCalcAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(DivisionTaxCalcAction.class);
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	DivisionTaxCalc divTaxCalc;
	@Override
	public void prepare_local() throws Exception {
		divTaxCalc=new DivisionTaxCalc();
		divTaxCalc.setMenuCode(1096);

	}
	

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return divTaxCalc;
	}
	
	public String input(){
		return SUCCESS;
	}


	public DivisionTaxCalc getDivTaxCalc() {
		return divTaxCalc;
	}


	public void setDivTaxCalc(DivisionTaxCalc divTaxCalc) {
		this.divTaxCalc = divTaxCalc;
	}
	/**
	 * This function will call the method to calculate the tax of employees based on filters selected
	 * @return
	 */
	public String calculateTaxForDivision(){
		try {
			CommonTaxCalculationModel taxmodel = new CommonTaxCalculationModel();
			taxmodel.initiate(context, session);
			logger.info("calling tax calculation method");
			Object[][]checkObj = null;
			try {
				/*
				 * First check to verify whether tax workflow is enabled for organization
				 */
				String query = "SELECT CONF_TAX_WORKFLOW_FLAG FROM HRMS_SALARY_CONF ";
				checkObj = taxmodel.getSqlModel().getSingleResult(query);
			} catch (Exception e) {
				logger.error("exception in taxWorkFlow query", e);
			} // end of catch
			if (String.valueOf(checkObj[0][0]).equals("N")) {
				addActionMessage("Tax workflow is not enabled");
			} else {
				/*
				 * check to verify whether tax process is locked for financial Year 
				 */
				try {
					String query = "SELECT NVL(TDS_LOCK_FLAG,'N') FROM HRMS_TAX_PARAMETER WHERE TDS_FINANCIALYEAR_FROM="+divTaxCalc.getFromYear();
					checkObj = taxmodel.getSqlModel().getSingleResult(query);
				} catch (Exception e) {
					logger.error("exception in taxWorkFlow query",e);
				} //end of catch
						//logger.info("In taxc thread	");
				if(checkObj !=null && checkObj.length >0){
					if(String.valueOf(checkObj[0][0]).equals("Y")){
						addActionMessage("Taxation process is finalized for this financial year.");
					}
				else{
				String empQuery="SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE 1=1 ";
				if(!divTaxCalc.getDivCode().equals("")){
					empQuery+=" AND EMP_DIV IN ("+divTaxCalc.getDivCode()+") ";
				}
				if(!divTaxCalc.getDeptCode().equals("")){
					empQuery+=" AND EMP_DEPT IN ("+divTaxCalc.getDeptCode()+") ";
				}
				if(!divTaxCalc.getEmployeeId().equals("")){
					empQuery+=" AND EMP_ID IN ("+divTaxCalc.getEmployeeId()+") ";
				}
				if(!divTaxCalc.getBranchCode().equals("")){
					empQuery+=" AND EMP_CENTER IN ("+divTaxCalc.getBranchCode()+") ";
				}
				empQuery+=" ORDER BY UPPER(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME)";
				Object[][] empList = taxmodel.getSqlModel().getSingleResult(empQuery);
				long startTime = System.currentTimeMillis();
				if (empList != null && empList.length > 0) {
					// check if employees are available in selected filter to calculate
					taxmodel.calculateTaxThread(empList, divTaxCalc
							.getFromYear(), divTaxCalc.getToYear());
					addActionMessage("Tax calculated successfully");
					logger.info("time required by process is====="
							+ (System.currentTimeMillis() - startTime));
				} else {
					addActionMessage("Employees not available for Tax calculation");
				}

					}

				}

			}
			taxmodel.terminate();
		} catch (Exception e) {
			logger.error("Exception in while calling calculateTax : " + e);
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String f9div() {
		try {
			String query = " SELECT DISTINCT DIV_ID,NVL(DIV_NAME,' ') FROM HRMS_DIVISION";

			if (divTaxCalc.getUserProfileDivision() != null && divTaxCalc.getUserProfileDivision().length() > 0) {
				query+= " WHERE DIV_ID IN ("+divTaxCalc.getUserProfileDivision() +")";
			}
				query+= " ORDER BY  DIV_ID ";
			
			String header = getMessage("division");
			
			String textAreaId ="paraFrm_divName";
			String hiddenFieldId ="paraFrm_divCode";
			
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	
	
	public String f9dept() throws Exception {
		try {
			String query = " SELECT DISTINCT DEPT_ID,NVL(DEPT_NAME,' ') FROM HRMS_DEPT ORDER BY NVL(DEPT_NAME,' ')";
			String header = getMessage("department");
			String textAreaId = "paraFrm_deptName";
			String hiddenFieldId = "paraFrm_deptCode";
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	
	public String f9Employee_old() throws Exception {
		try {
		
			String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
				+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,HRMS_EMP_OFFC.EMP_ID,"
				+ "	HRMS_EMP_OFFC.EMP_DIV,NVL(DIV_NAME,' ') FROM HRMS_EMP_OFFC  "
				+ "	INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER"
				+ "	INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV)";
				query += "WHERE 1=1";
				
				if(divTaxCalc.getDivCode() !=null && !divTaxCalc.getDivCode().equals("")){
					query+=" AND DIV_ID="+divTaxCalc.getDivCode();
				}
				query +=  "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

			
			String[] headers = { "Employee Id", "employee" };
			String[] headerWidth = { "25", "75" };
			String[] fieldNames = { "employeeToken", "employeeName",
					"employeeId" };
			int[] columnIndex = { 0, 1, 2 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}// end of f9action
	
	public String f9Branch() {
		try	{
			String query = " SELECT  CENTER_ID,CENTER_NAME FROM HRMS_CENTER  ORDER BY  CENTER_ID ";
			
			
			String header = "Branch";
			String textAreaId = "paraFrm_branchName";
			String hiddenFieldId = "paraFrm_branchCode";
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);

		
		}
		catch (Exception e)
		{
			logger.info("Exception in f9Branch method");
			return "";
		} //end of catch
		return "f9multiSelect";
	 }//end of f9Branch method
	public String f9type() throws Exception {
			try {
				String query = " SELECT  TYPE_ID,TYPE_NAME  FROM HRMS_EMP_TYPE";
				String header = "EMPLOYEE TYPE";
				String textAreaId = "paraFrm_empTypeName";
				String hiddenFieldId = "paraFrm_empTypeCode";
				String submitFlag = "false";
				String submitToMethod = "";
				setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
						submitFlag, submitToMethod);
			} catch (Exception e) {
				e.printStackTrace();
			}
		//logger.info("4");
		return "f9multiSelect";
	} //end of f9Type method
	
	/*
	 * Following function is called to show the pay bill id and name in the jsp
	 */
	
	public String f9payBill() throws Exception {
		try {
			/**
			 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
			 * OUTPUT ALONG WITH PROFILES
			 */
			String query = " SELECT DISTINCT PAYBILL_ID,PAYBILL_GROUP FROM HRMS_PAYBILL "
					+ " LEFT JOIN HRMS_EMP_OFFC ON (EMP_PAYBILL=PAYBILL_ID) ";
			String header = getMessage("pay.bill");
			String textAreaId = "paraFrm_payBillName";
			String hiddenFieldId = "paraFrm_payBillNo";
			String submitFlag = "false";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("4");
		return "f9multiSelect";
	}
	/**
	 * Method to resets the form fields
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String reset() throws Exception {
		try {
			divTaxCalc.setDivCode("");
			divTaxCalc.setDivName("");
			divTaxCalc.setDeptCode("");
			divTaxCalc.setDeptName("");
			divTaxCalc.setBranchCode("");
			divTaxCalc.setBranchName("");
			divTaxCalc.setPayBillNo("");
			divTaxCalc.setPayBillName("");
			divTaxCalc.setEmpTypeCode("");
			divTaxCalc.setEmpTypeName("");
			divTaxCalc.setEmployeeId("");;
			divTaxCalc.setEmployeeName("");
			divTaxCalc.setEmployeeToken("");
			divTaxCalc.setDeclaredInvestments("false");
			divTaxCalc.setVerifiedInvestments("false");
		}// end of try
		catch (Exception e) {
			e.printStackTrace();
		} // end of catch
		return "success";
	}
	
	public String f9Employee()
	{
		try
		{
			String query = " SELECT EMP_TOKEN, EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME,EMP_ID,'','','','','','','',''" 
						 + " FROM HRMS_EMP_OFFC WHERE 1=1 ";
						 //";//ORDER BY UPPER(EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME)";

			try {
				if (divTaxCalc.getUserProfileDivision() != null
						&& divTaxCalc.getUserProfileDivision().length() > 0) {
					query += " AND HRMS_EMP_OFFC.EMP_DIV IN ("
							+ divTaxCalc.getUserProfileDivision() + ")";
				} else {
					//query += " WHERE 1=1 ";
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			query += " AND EMP_STATUS IN ('S','N')  ORDER BY UPPER(EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME)";
			String[] headers={getMessage("employee.id"),getMessage("employee")};
			String[] headerWidth = {"25","75"};

			String[] fieldNames = {"employeeToken", "employeeName","employeeId"};

			int[] columnIndex = {0,1,2};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			logger.info("Exception in f9Employee method");
			//e.printStackTrace();
			return "";
		} //end of catch
	} //end of f9Div method
	
	public String report() throws Exception {
		CommonTaxCalculationModel model = new CommonTaxCalculationModel();
		model.initiate(context, session);
		
		boolean result = model.getReport(divTaxCalc, response,request);
		if (result) {
			addActionMessage("Report Generated Successfully");
		} else {
			addActionMessage("No Data Available");
		}
		model.terminate();

		return null;
	}
	
}
