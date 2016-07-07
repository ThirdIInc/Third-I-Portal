
package org.struts.action.attendance;
import org.paradyne.bean.attendance.CompOffMisReport;
import org.paradyne.model.attendance.CompOffMisReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author balajim
 *  date 18-08-2008
 */
public class CompOffMisReportAction extends ParaActionSupport {

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */


	CompOffMisReport compReport ;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		compReport= new CompOffMisReport();
		compReport.setMenuCode(567);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return compReport ;
	}

	public CompOffMisReport getCompReport() {
		return compReport;
	}

	public void setCompReport(CompOffMisReport compReport) {
		this.compReport = compReport;
	}
	
	/**
	 * @return a String  after Generating Report...!
	 */
	public String report(){
		CompOffMisReportModel model = new CompOffMisReportModel();
		model.initiate(context, session);
      //	model.getReport(request,response ,compReport);
		 model.terminate();
		 return null;
	}
	
	/**
	 * @return null
	 */
	public String getReport() {
		final CompOffMisReportModel model = new CompOffMisReportModel();
		model.initiate(context, session);
		
		String reportPath = "";
		
		model.getReport(compReport, response,  request, reportPath);
		
		model.terminate();
		return null;
	}
	
	public final String mailReport(){
		try {
			final CompOffMisReportModel model = new CompOffMisReportModel();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String reportPath =  getText("data_path") + "/Report/Payroll" + poolName + "/";
			
			
			
		//	model.generateReport(bean, request, response, reportPath);
			model.getReport(compReport, response,  request, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}
	
	public String input(){
		//deleteFile();
		return INPUT;
	}
	
	public String reset()
	{
		compReport.setEmpToken("");
		compReport.setEmpId("");
		compReport.setEmpName("");
		compReport.setStatus("-1");
		compReport.setFromDate("");
		compReport.setToDate("");
		return "success";
	}
	
	
public String f9Employee() throws Exception {
		
		/*
		 * Select the Employee F9
		 * 
		 * 
		 */
	
		String query = "SELECT EMP_TOKEN ,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),"
				+" EMP_ID FROM HRMS_EMP_OFFC ";
				//+ "	LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE)"
		 		query += getprofileQuery(compReport);
		 		query +=  " AND EMP_STATUS='S' ";
				query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
			 
		
		String[] headers = {  getMessage("employee.id"), getMessage("employee")};

		String[] headerWidth = { "25", "75" };

		String[] fieldNames = { "empToken","empName", "empId" };

		int[] columnIndex = { 0,1,2};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}

}
