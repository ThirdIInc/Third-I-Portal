/**
 * 
 */
package org.struts.action.admin.srd;

import org.paradyne.bean.admin.srd.NomineeMisReport;
import org.paradyne.model.admin.srd.NomineeMisReportModel;

import org.struts.lib.ParaActionSupport;

/**
 * @author AA0563
 *
 */
public class NomineeMisReportAction extends ParaActionSupport {

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	NomineeMisReport  nominee;
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		nominee=new NomineeMisReport();
		nominee.setMenuCode(750);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return nominee;
	}

	public NomineeMisReport getNominee() {
		return nominee;
	}

	public void setNominee(NomineeMisReport nominee) {
		this.nominee = nominee;
	}


	public String report() {
		NomineeMisReportModel  model = new NomineeMisReportModel ();
		model.initiate(context, session);
		String result = model.getReport(nominee, response);
		
		model.terminate();
		clear();
		return null;

	}
	
	
	
	public String clear() {
		nominee.setEmpid("");
		nominee.setEmpName("");
		nominee.setToken("");
		nominee.setDeptCode("");
		nominee.setDeptName("");
		nominee.setDesgCode("");
		nominee.setDesgName("");
		nominee.setCenterId("");
		nominee.setCenterName("");
		nominee.setDivCode("");
		nominee.setStatus("");
		nominee.setDivsion("");
		nominee.setReportType("");
		return "success";
	}
	
	
	public String f9desg() throws Exception {
		/*String query = " SELECT  RANK_ID,RANK_NAME" + "	FROM HRMS_RANK  ";

		String[] headers = { "designation Id", getMessage("designation") };
		String[] headerWidth = { "15", "35" };

		String[] fieldNames = { "desgCode", "desgName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";*/
		
		try {
			String query = " SELECT  RANK_ID,RANK_NAME" + "	FROM HRMS_RANK  ";
			String header = "Employee";
			String textAreaId ="paraFrm_desgName";
			String hiddenFieldId ="paraFrm_desgCode";
			String submitFlag = "false";
			String submitToMethod = "";
			
			setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}

	/**
	 * THIS METHOD IS USED OFR SELECTING BRANCH
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9center() throws Exception {

		/*String query = " SELECT   CENTER_ID , CENTER_NAME FROM HRMS_CENTER ORDER BY CENTER_ID ";
		String[] headers = { "Branch Id", getMessage("branch") };
		String[] headerWidth = { "40", "60" };
		String[] fieldNames = { "centerId",
				"centerName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";*/
		

		try {
			String query = " SELECT   CENTER_ID , CENTER_NAME FROM HRMS_CENTER ORDER BY CENTER_ID";
			String header = "Branches";
			String textAreaId = "paraFrm_centerName";
			String hiddenFieldId = "paraFrm_centerId";
			String submitFlag = "false";
			String submitToMethod = "";
			
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9multiSelect";
	}
	public String f9action() throws Exception {
		
		String query = "SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,"
				+ "  EMP_ID FROM HRMS_EMP_OFFC";
		String[] headers = { "Employee ID", getMessage("employee") };
		String[] headerWidth = { "15", "35" };
		String[] fieldNames = { "token", "empName","empid" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	public String f9div() throws Exception {

		/*String query =  " SELECT DISTINCT DIV_ID,NVL(DIV_NAME,' ') FROM HRMS_DIVISION ";
		
		if(nominee.getUserProfileDivision() !=null && nominee.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+nominee.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";
			
		String[] headers = { "Division Code", getMessage("division")};
		String[] headerWidth = { "15", "35" };
		String[] fieldNames = { "divCode", "divsion" };

		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
		*/
		
		try {
			String query = " SELECT DISTINCT DIV_ID,NVL(DIV_NAME,' ') FROM HRMS_DIVISION ";
			String header = "DIVISIONS";
			String textAreaId ="paraFrm_divsion";
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

		/*String query = " SELECT  DEPT_ID,DEPT_NAME FROM HRMS_DEPT ";
		String[] headers = { "Department Id", getMessage("department") };
		String[] headerWidth = { "15", "35" };
		String[] fieldNames = { "deptCode", "deptName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";*/
		try {
			String query = " SELECT  DEPT_ID,DEPT_NAME FROM HRMS_DEPT";
			String header = "Departments";
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

	public String report2() throws Exception {
		NomineeMisReportModel  model = new NomineeMisReportModel ();
		model.initiate(context, session);
		 String reportPath="";			
		model.getReport2(nominee, request, response, reportPath);
		model.terminate();
		return null;
	}

	
    public final String mailReport(){
		try {
			final NomineeMisReportModel model = new NomineeMisReportModel();

			model.initiate(context, session);

			String poolName = String.valueOf(session
					.getAttribute("session_pool"));

			if (!(poolName.equals("") || poolName == null)) {
				poolName = "\\" + poolName;
			}
			String reportPath = getText("data_path") + "\\Report\\Master"
					+ poolName + "\\";
			// model.generateReport(bean, request, response, reportPath);

			model.getReport2(nominee, request, response, reportPath);
			model.terminate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
  

}
