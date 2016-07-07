/**
 * @author sai pavan v 
 * 26-08-2008
 *
 */

package org.struts.action.settings;
import org.paradyne.bean.settings.SuggestionMisReport;
import org.paradyne.model.settings.SuggestionMisReportModel;
import org.struts.lib.ParaActionSupport;

public class SuggestionMisReportAction extends ParaActionSupport {

	 SuggestionMisReport Misbean;
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		Misbean=new SuggestionMisReport();
		Misbean.setMenuCode(667);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return Misbean;
	}
	public String report(){
		SuggestionMisReportModel model=new SuggestionMisReportModel();
		model.initiate(context, session);
	
		model.getReport(request,response,Misbean);
		model.terminate();
		return null;
	}
public String f9actionEname() throws Exception {
		
		/*
		 * Select the Employee F9
		 * 
		 * 
		 */
	
		String query = "SELECT EMP_TOKEN ,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),"
				+" EMP_ID FROM HRMS_EMP_OFFC ";
			//	+ "	LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE)"
				
		query += getprofileQuery(Misbean);
		 query +=" AND EMP_STATUS='S'";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
		
		 
		
		String[] headers = {getMessage("employee.id"),getMessage("employee")};

		String[] headerWidth = {"30", "70" };

		String[] fieldNames = {"empToken","ename", "empCode" };

		int[] columnIndex = {0,1,2};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}
public String reset(){ //resetting fields
	Misbean.setEmpCode("");
	Misbean.setEname("");
	Misbean.setFrmDate("");
	Misbean.setToDate("");
	Misbean.setEmpToken("");
	Misbean.setStatus("");
	Misbean.setRptType("");
	return SUCCESS;
}

public SuggestionMisReport getMisbean() {
	return Misbean;
}

public void setMisbean(SuggestionMisReport Misbean) {
	this.Misbean = Misbean;
}

}
