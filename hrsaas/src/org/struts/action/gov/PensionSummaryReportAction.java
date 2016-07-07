package org.struts.action.gov;

import org.paradyne.bean.gov.PensionSummaryReport;
import org.paradyne.model.gov.PensionSummaryReportModel;
import org.struts.lib.ParaActionSupport;

public class PensionSummaryReportAction extends ParaActionSupport {

	PensionSummaryReport bean;
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(PensionSummaryReportAction.class); 
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		bean=new PensionSummaryReport();
		bean.setMenuCode(2281);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public PensionSummaryReport getBean() {
		return bean;
	}

	public void setBean(PensionSummaryReport bean) {
		this.bean = bean;
	}

public String getReport(){
		
		PensionSummaryReportModel model = new PensionSummaryReportModel(); 
		model.initiate(context, session);
		model.getReport(bean,response);
		model.terminate();
		return null;
	}

/*
 * Following function is called to show the division name and division id in the jsp 
 */
public String f9div() throws Exception {
	String query = " SELECT DISTINCT NVL(DIV_NAME,' '),div_id FROM HRMS_DIVISION ";
		if(bean.getUserProfileDivision() !=null && bean.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+bean.getUserProfileDivision() +")"; 
			query+= "  ORDER BY Upper(NVL(DIV_NAME,' '))";
	String[] headers = { getMessage("division") };
	String[] headerWidth = { "80" };
	String[] fieldNames = { "divName","divCode"};
	int[] columnIndex = { 0 ,1};
	String submitFlag = "false";
	String submitToMethod = "";
	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);

	return "f9page";
}
}
