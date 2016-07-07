package org.struts.action.attendance;
import org.paradyne.bean.attendance.OutdoorVisitMisReport;
import org.paradyne.model.attendance.OutdoorVisitMisReportModel;
import org.struts.lib.ParaActionSupport;

/*
 *@author Saipavan v
 * 07-06-2008
 */
public class OutdoorVisitMisReportAction extends ParaActionSupport { //for generating Mis report

	 OutdoorVisitMisReport outMisbean; //creating bean object 
	@Override
	public void prepare_local() throws Exception {  
		
		outMisbean=new OutdoorVisitMisReport();
		outMisbean.setMenuCode(579);  //setting menu code for this form
	}

	public Object getModel() {
		
		return outMisbean;
	}
	public String report(){ //generating report.
		try {
			OutdoorVisitMisReportModel model = new OutdoorVisitMisReportModel();
			model.initiate(context, session);
			model.getReport(request, response, outMisbean);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
public String f9actionEname() throws Exception {
		
		/*
		 * Select the Employee  information
		 * 
		 * 
		 */
	
		String query = "SELECT EMP_TOKEN ,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),"
				+" EMP_ID FROM HRMS_EMP_OFFC  ";
			//	+ "	LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE)"
			 
		query += getprofileQuery(outMisbean);
		query += " AND  EMP_STATUS='S'";
		query += "	ORDER BY EMP_ID ";
		
		String[] headers = {getMessage("employee.id"), getMessage("employee")};

		String[] headerWidth = {"25", "75" };

		String[] fieldNames = {"empToken","ename", "empCode" };

		int[] columnIndex = {0,1,2};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}
public String reset(){
	//clearing all the text fields.
	outMisbean.setEmpCode("");
	outMisbean.setEname("");
	outMisbean.setFrmDate("");
	outMisbean.setToDate("");
	outMisbean.setEmpToken("");
	outMisbean.setStatus("");
	return SUCCESS;
}

public OutdoorVisitMisReport getOutMisbean() {
	return outMisbean;
}

public void setOutMisbean(OutdoorVisitMisReport outMisbean) {
	this.outMisbean = outMisbean;
}

}
