/**
 * 
 */
package org.struts.action.payroll;

import org.paradyne.bean.payroll.SalarySlip;
import org.paradyne.model.payroll.SalarySlipModel;
import org.struts.lib.GloActionSupport;
import org.struts.lib.ParaActionSupport;

/**
 * @author pradeep
 * 14-03-2008
 *
 */
public class SalarySlipGeneratorAction extends ParaActionSupport {
	
		SalarySlip salarySlip ;
		
	public SalarySlip getSalarySlip() {
			return salarySlip;
		}

		public void setSalarySlip(SalarySlip salarySlip) {
			this.salarySlip = salarySlip;
		}

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		salarySlip = new SalarySlip();
		salarySlip.setMenuCode(94);
	}
	/* Following function is called when a general user makes login. 
	 * (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_withLoginProfileDetails()
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		
		SalarySlipModel model = new SalarySlipModel();
		model.initiate(context,session);
	
		model.getEmployeeDetails(salarySlip.getUserEmpId(),salarySlip);
		model.terminate();
	
}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	 public Object getModel() {
		// TODO Auto-generated method stub
		return salarySlip;
	}
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String report() throws Exception {
		SalarySlipModel model = new SalarySlipModel();
		model.initiate(context,session);
		String[][] data=null;
		String onHoldChk="false";
		Object[][] prepareData = (model.prepareData(salarySlip));
		if(prepareData==null){
			
		}
		else if(prepareData[0][0].equals("onHold")){
			onHoldChk="true";
		}
		else{
			data = GloActionSupport.scanData(model.prepareData(salarySlip));
		}
		model.generateReport(salarySlip,response,data,onHoldChk);
		model.terminate();
		return null;
	}
	
	public String f9employee() throws Exception {
		String query = "	SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
			+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,HRMS_EMP_OFFC.EMP_ID"
			+ "	FROM HRMS_EMP_OFFC  "
			+ "	INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER";
		
	//		+ "	WHERE HRMS_EMP_OFFC.EMP_CENTER = (SELECT HRMS_EMP_OFFC.EMP_CENTER FROM HRMS_EMP_OFFC "
	//		+ " WHERE HRMS_EMP_OFFC.EMP_ID ='"
	//		+ salarySlip.getEmpCode() + "') "
			
			query += getprofileQuery(salarySlip);
			query +="	ORDER BY HRMS_EMP_OFFC.EMP_ID";

	/**
	 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
	 */

	String[] headers = { getMessage("employee.id"),getMessage("employee") };

	/**
	 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
	 */
	String[] headerWidth = { "30", "70" };

	/**
	 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
	 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
	 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
	 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
	 * FIELDNAMES
	 */

	String[] fieldNames = { "salarySlip.salaryEmpToken",
			"salarySlip.salaryEmpName","salarySlip.salaryEmpCode" };

	/**
	 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
	 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
	 * 
	 * NOTE: COLUMN NUMBERS STARTS WITH 0
	 * 
	 */
	int[] columnIndex = { 0, 1,2 };

	/**
	 * WHEN SET TO 'true' WILL SUBMIT THE FORM
	 * 
	 */
	String submitFlag = "false";

	/**
	 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
	 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
	 * ACTION>_<METHOD TO CALL>.action
	 */
	String submitToMethod = "";

	/**
	 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
	 */

	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);
		
		return "f9page";
	}

}
