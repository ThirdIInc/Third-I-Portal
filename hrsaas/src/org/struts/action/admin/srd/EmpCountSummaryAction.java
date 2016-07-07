/**
 * 
 */
package org.struts.action.admin.srd;

import org.paradyne.bean.admin.srd.EmpCountSummary;
import org.paradyne.model.admin.srd.EmpCountSummaryModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0554
 *
 */
public class EmpCountSummaryAction extends ParaActionSupport {

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	EmpCountSummary empCountSummary;
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		empCountSummary= new EmpCountSummary();
		empCountSummary.setMenuCode(1024);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return empCountSummary;
	}
	
	public String f9divaction() throws Exception {
		String query = " SELECT DIV_NAME,DIV_ID FROM HRMS_DIVISION ";
		 
		if(empCountSummary.getUserProfileDivision() !=null && empCountSummary.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+empCountSummary.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";

		String[] headers = {getMessage("division") };

		String[] headerWidth = { "100" };

		String[] fieldNames = { "divName","divCode"};

		int[] columnIndex = { 0, 1};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String report(){
		EmpCountSummaryModel model=new EmpCountSummaryModel();
		model.initiate(context, session);
		model.getReport(empCountSummary, response);
		model.terminate();
		return null;
	}

}
