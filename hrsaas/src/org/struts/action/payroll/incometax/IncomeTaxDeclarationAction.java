/**
 * 
 */
package org.struts.action.payroll.incometax;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.paradyne.bean.payroll.incometax.IncomeTaxDeclaration;
import org.paradyne.model.payroll.incometax.IncomeTaxDeclarationModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0623
 * 
 */
public class IncomeTaxDeclarationAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.action.payroll.incometax.IncomeTaxDeclarationAction.class);

	/**
	 * 
	 */
	IncomeTaxDeclaration taxDeclaration;

	/**
	 * @return the taxDeclaration
	 */
	public IncomeTaxDeclaration getTaxDeclaration() {
		return taxDeclaration;
	}

	/**
	 * @param taxDeclaration
	 *            the taxDeclaration to set
	 */
	public void setTaxDeclaration(IncomeTaxDeclaration taxDeclaration) {
		this.taxDeclaration = taxDeclaration;
	}

	public IncomeTaxDeclarationAction() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		taxDeclaration = new IncomeTaxDeclaration();
		taxDeclaration.setMenuCode(692);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return taxDeclaration;
	}

	/**
	 * this method is called when the general user logs in the system.
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		IncomeTaxDeclarationModel model = new IncomeTaxDeclarationModel();
		model.initiate(context, session);

		if (taxDeclaration.isGeneralFlag()) {
			taxDeclaration.setEmpID(taxDeclaration.getUserEmpId());
			GenEmpRecord();
		}// end if
		model.terminate();
		
		Date date = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		String sysdate = formater.format(date);
		String[]split = sysdate.split("/");
		int year = Integer.parseInt(String.valueOf(split[2]));
		taxDeclaration.setFromYear(String.valueOf(year));
		taxDeclaration.setToYear(String.valueOf(year + 1));
	}// end of prepare_withLoginProfileDetails method

	/**
	 * The record for the general employee is retrieved and set in the field.
	 * 
	 * @return String
	 */
	public String GenEmpRecord() {
		IncomeTaxDeclarationModel model = new IncomeTaxDeclarationModel();
		model.initiate(context, session);
		taxDeclaration = model.generalRecord(taxDeclaration);
		model.terminate();
		return "success";
	}// end of GenEmpRecord method

	/**
	 * To generate income tax declaration form for all investments made
	 * 
	 * @return String
	 */
	public String generateDeclaration() {
		IncomeTaxDeclarationModel model = new IncomeTaxDeclarationModel();
		model.initiate(context, session);

		boolean result = model.generateDeclaration(taxDeclaration, response);

		if (!result) {
			addActionMessage("No Data Available");
		}// end if
		else {
			addActionMessage("Problem in Report Generation");
		}// end else
		model.terminate();
		return null;
	}

	/**
	 * this method is used to reset all the fields
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String reset() throws Exception {
		try {

			taxDeclaration.setTaxCode("");
			taxDeclaration.setEmpID("");
			taxDeclaration.setEmpName("");
			taxDeclaration.setEmpTokenNo("");
			taxDeclaration.setDepartment("");
			taxDeclaration.setCenter("");
			taxDeclaration.setFromYear("");
			taxDeclaration.setToYear("");
		} catch (Exception e) {
			logger.error("Error in reset method");
		}
		return SUCCESS;
	}// end of reset method

	/**
	 * This action is called to get the list of all the employee records.
	 * 
	 * @return String (f9 page)
	 * @throws Exception
	 */
	public String f9empaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */

		String query = " SELECT EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '),"
				+ " NVL(RANK_NAME,' '),NVL(CENTER_NAME,' ') , EMP_ID FROM HRMS_EMP_OFFC  	"
				+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)	"
				+ " LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
				+ " LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ";
				//+ " WHERE EMP_STATUS = 'S' ";//ORDER BY EMP_ID ";
		
		query += getprofileQuery(taxDeclaration);

		query += "	AND EMP_STATUS = 'S' ORDER BY HRMS_EMP_OFFC.EMP_ID";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.id"),
				getMessage("employee"), getMessage("designation"),
				getMessage("branch") };
		String[] headerWidth = { "10", "30", "30", "30" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "taxDeclaration.empTokenNo",
				"taxDeclaration.empName", "taxDeclaration.department",
				"taxDeclaration.center", "taxDeclaration.empID" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4 };

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
	}// end of f9empaction method

}// end of class
