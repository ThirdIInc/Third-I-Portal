/**
 * 
 */
package org.struts.action.payroll;

import org.paradyne.bean.payroll.Form11PF;
import org.paradyne.model.payroll.Form11PFModel;

import org.struts.lib.ParaActionSupport;

/**
 * @author Vijay
 * 
 */
public class Form11PFAction extends ParaActionSupport {
	/**
	 * logger initialization.
	 */
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	
	/**
	 *  
	 */
	private Form11PF form11;

	/**
	 * implementation of prepare_local() abstract method
	 * @throws Exception
	 */
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		this.form11 = new Form11PF();
		this.form11.setMenuCode(2272);
	}

	/**
	 * getter for model
	 * @return Object
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return this.form11;
	}

	/**
	 * getter for form11
	 * @return Form11
	 */
	public Form11PF getForm11() {
		return this.form11;
	}

	/**
	 * setter for form11
	 * @param form11
	 */
	public void setForm11(final Form11PF form11) {
		this.form11 = form11;
	}

	/**
	 * To generate PF form 11 report.
	 * 
	 * @return String
	 */
	public String report() {
		final Form11PFModel model = new Form11PFModel();
		model.initiate(context, session);
		model.getForm11Report(this.form11, request, response, "");
		model.terminate();
		return null;
	}

	/**
	 * To select an employee
	 * 
	 * @return f9page
	 */
	public String f9Employee() {
		try {
			String query = "SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, RANK_NAME,EMP_ID  " + 
					" FROM HRMS_EMP_OFFC " + 
					" INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV )" + 
					" INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)" + 
					" LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_OFFC.EMP_TYPE= HRMS_EMP_TYPE.TYPE_ID)";
			// query += getprofileQuery(form11);
			query += " WHERE HRMS_EMP_TYPE.TYPE_ID IN(SELECT TYPE_ID FROM HRMS_EMP_TYPE WHERE TYPE_PF='Y')";
			query += " ORDER BY EMP_ID ";

			final String[] headers = {getMessage("employee.id"), getMessage("employee"), getMessage("designation")};

			final String[] headerWidth = {"15", "50", "35"};

			final String[] fieldNames = {"empToken", "empName", "empDesignation", "empId"};

			final int[] columnIndex = {0, 1, 2, 3};

			final String submitFlag = "true";

			final String submitToMethod = "Form11Report_setEmployerDetails.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		}catch (final Exception e) {
			this.logger.error("" + e);
			e.printStackTrace();
			return null;
		} // end of catch
	}

	/**
	 * To select an employee
	 * 
	 * @return f9page
	 */
	public String f9SigningAuthority() {
		try {
			String query = "SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, EMP_ID "
					+ "  FROM HRMS_EMP_OFFC "
					+ " INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV )"
					+ " LEFT JOIN HRMS_EMP_TYPE ON(HRMS_EMP_OFFC.EMP_TYPE= HRMS_EMP_TYPE.TYPE_ID)";
			query += getprofileQuery(form11);
			query += "	AND EMP_STATUS ='S' "
					+ " AND  HRMS_EMP_TYPE.TYPE_ID IN(SELECT TYPE_ID FROM HRMS_EMP_TYPE WHERE TYPE_PF='Y')";
			query += " ORDER BY EMP_ID ";

			final String[] headers = {getMessage("employee.id"),getMessage("employee")};

			final String[] headerWidth = {"15", "50", "35"};

			final String[] fieldNames = {"signAuthToken", "signAuthName", "signAuthId"};

			final int[] columnIndex = {0, 1, 2};

			final String submitFlag = "false";

			final String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		}// end of try
		catch (Exception e) {
			logger.error("" + e);
			e.printStackTrace();
			return null;
		} // end of catch
	}

	/**
	 * Set the previous employer details fields of the form
	 * 
	 * @return String
	 */
	public String setEmployerDetails() {

		try {
			Form11PFModel model = new Form11PFModel();
			model.initiate(context, session);
			model.setEmployerDetails(form11);
			model.terminate();
		} // end of try
		catch (Exception e) {
			e.printStackTrace();
		} // end of catch

		return SUCCESS;
	}

	/**
	 * Reset the fields of the form
	 * 
	 * @return String
	 */
	public String reset() {
		this.form11.setEmpId("");
		this.form11.setEmpName("");
		this.form11.setEmpToken("");
		this.form11.setSignAuthId("");
		this.form11.setSignAuthName("");
		this.form11.setSignAuthToken("");
		this.form11.setPreEmployer1("");
		this.form11.setPreEmployer2("");
		this.form11.setPfAccNo1("");
		this.form11.setPfAccNo2("");
		return SUCCESS;

	}

	/**
	 * 
	 * Sending report as a mail attachment 
	 * @return String
	 */
	public final String mailReport() {
		try {
			final Form11PFModel model = new Form11PFModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			final String reportPath = getText("data_path") + "/Report/Payroll/" + 
					poolName + "/";
			model.getForm11Report(this.form11, request, response, reportPath);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}

}
