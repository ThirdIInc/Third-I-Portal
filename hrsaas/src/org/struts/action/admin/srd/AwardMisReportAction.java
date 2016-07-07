package org.struts.action.admin.srd;

import org.paradyne.bean.admin.srd.AwardMisReport;
import org.paradyne.model.admin.srd.AwardMisReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author GTL-AA1711
 */

public class AwardMisReportAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(AwardMisReportAction.class);

	AwardMisReport awardReport ;

	/** (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		awardReport = new AwardMisReport();
		awardReport.setMenuCode(2286);		
	}

	/** (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return awardReport;
	}

	/**
	 * @return beanObject
	 */
	public AwardMisReport getAwardReport() {
		return awardReport;
	}

	/**
	 * @param awardReport
	 */
	public void setAwardReport(AwardMisReport awardReport) {
		this.awardReport = awardReport;
	}	
	
	/** Method name :awardReport()
	 * Used to Generate Report using iVreport library
	 * @return String
	 * @throws Exception
	 */
	public String awardReport() throws Exception {
		System.out.println("In Action");
		AwardMisReportModel model = new AwardMisReportModel();
		model.initiate(context, session);
		String reportPath="";
		model.getAwardMISReport(awardReport, request, response, reportPath);
		model.terminate();
		return null;
	}
	
	/**Method Name :mailReport()
	 * Used to generate Mail Report
	 * @return String
	 */
	public final String mailReport(){
		try{
		  final AwardMisReportModel model = new AwardMisReportModel();
		  model.initiate(context, session);
		  String poolName= String.valueOf(session.getAttribute("session_pool"));
		  if(!(poolName.equals("")|| poolName==null))
		  {
	 		 poolName="\\"+poolName;
	      }
		  String reportPath= getText("data_path") + "\\Report\\Master" + poolName + "\\";
		  model.getAwardMISReport(awardReport, request, response, reportPath);
		  model.terminate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return "mailReport";
	}
	

	/**Method Name: clear()
	 * Used to clear the fields
	 * @return String
	 */
	public String clear() {		
		
		awardReport.setEmpid("");
		awardReport.setEmpName("");	
		awardReport.setDesgCode("");
		awardReport.setDesgName("");
		awardReport.setCenterId("");
		awardReport.setCenterName("");			
		awardReport.setToken("");
		awardReport.setStatus("");
		awardReport.setDeptName("");
		awardReport.setDeptCode("");			
		awardReport.setDivCode("");
		awardReport.setDivision("");
		awardReport.setReportType("");		
		return "success";
		}
	
	
	/**Method Name: f9MultiDiv()
	 * Used to display Division List and MultiSelect
	 * @return String
	 */
	public String f9MultiDiv() {
		System.out.println("In Multiple Division");
		try {
			String query = " SELECT " + " 	DISTINCT DIV_ID, " + "		DIV_NAME "
					+ " FROM " + " 	HRMS_DIVISION ";

			if (this.awardReport.getUserProfileDivision()!= null
					&& this.awardReport.getUserProfileDivision().length() > 0)
				query += " WHERE DIV_ID IN (" + this.awardReport.getUserProfileDivision()
						+ ")";
			query += " ORDER BY  DIV_ID ";
			String header = getMessage("division");
			String textAreaId = "paraFrm_division";
			String hiddenFieldId = "paraFrm_divCode";
			String submitFlag = "";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";
		} catch (Exception e) {
			logger.error("Error in AwardMisReportAction.f9MultiDiv() method Action : "
							+ e.getMessage());
			return "";
		}
	}
	
	
	/**Method Name: f9Brch()
	 * Used to display Branch List and MultiSelect
	 * @return String
	 */
	
	public String f9Brch() {
		try {
			String query = " SELECT " + " 	DISTINCT CENTER_ID ,"
					+ "		CENTER_NAME " + " FROM " + " 	HRMS_CENTER "
					+ " ORDER BY " + "		UPPER (CENTER_NAME) ";

			String header = getMessage("branch");
			String textAreaId = "paraFrm_centerName";
			String hiddenFieldId = "paraFrm_centerId";
			String submitFlag = "";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";
		} catch (Exception e) {
			logger
					.error("Error in AwardMISReportAction.f9Brch() method Action : "
							+ e.getMessage());
			return "";
		}
	}
	
	/**Method Name: f9MultiDept()
	 * Used to display Department List and MultiSelect
	 * @return String
	 */
	public String f9MultiDept() {
		try {
			String query = " SELECT " + "		DISTINCT DEPT_ID," + "		DEPT_NAME "
					+ "	FROM " + " 	HRMS_DEPT  " + " ORDER BY "
					+ "		UPPER (DEPT_NAME) ";
			String header = getMessage("department");
			String textAreaId = "paraFrm_deptName";
			String hiddenFieldId = "paraFrm_deptCode";
			String submitFlag = "";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			logger
					.error("Error in AwardMISReport.f9MultiDept() method Action : "
							+ e.getMessage());
			return "";
		}
	}
	
	/**Method Name: f9MultiRank()
	 * Used to display Designation List and MultiSelect
	 * @return String
	 */
	public String f9MultiRank() {
		try {
			String query = " SELECT " + "		DISTINCT RANK_ID," + "		RANK_NAME "
					+ "	FROM " + " 	HRMS_RANK  " + " ORDER BY "
					+ "		UPPER (RANK_NAME) ";
			String header = getMessage("designation");
			String textAreaId = "paraFrm_desgName";
			String hiddenFieldId = "paraFrm_desgCode";
			String submitFlag = "";
			String submitToMethod = "";
			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			logger
					.error("Error in AwardMISReport.f9MultiRank() method Action : "
							+ e.getMessage());
			return "";
		}
	}
	
	
	/**Method Name: f9action()
	 * Used to display Employee List
	 * @return String
	 * @throws Exception
	 */
	public String f9action() throws Exception {
		String query = "SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,"
				+ "  EMP_ID FROM HRMS_EMP_OFFC";
		String[] headers = { "Employee Id", getMessage("employee")};
		String[] headerWidth = { "15", "35" };
		String[] fieldNames = { "token", "empName","empid" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
}