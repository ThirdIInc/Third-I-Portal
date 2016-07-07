package org.struts.action.D1.reports;

import org.paradyne.bean.D1.reports.D1SuperUser;
import org.paradyne.model.D1.reports.D1SuperUserModel;
import org.struts.lib.ParaActionSupport;

public class D1SuperUserAction extends ParaActionSupport {

	D1SuperUser bean;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		bean=new D1SuperUser();
		bean.setMenuCode(2065);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public D1SuperUser getBean() {
		return bean;
	}

	public void setBean(D1SuperUser bean) {
		this.bean = bean;
	}

	/**
	 * METHOD TO DISPLAY RECORDS
	 * @return
	 * @throws Exception
	 */
	
	public String view() throws Exception{
		D1SuperUserModel model =new D1SuperUserModel();
		model.initiate(context, session);
		String applicationTylpe = request.getParameter("applicationType");
	
		if(applicationTylpe.equals("D1-BRD"))
		{
			bean.setBrdFlag("true");
			model.viewApplication(bean,request);
		}
		else {
			bean.setDisplayApplicationFlag("true");
			model.view(bean,request);
		}
		
		model.terminate();		
		return SUCCESS;
	}
	
	public String f9applicant() throws Exception {

		String query = " SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,EMP_ID "
				+ "	FROM HRMS_EMP_OFFC   ";
		if (bean.getUserProfileDivision()!=null && bean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
					+ bean.getUserProfileDivision() + ")";
		}
		else
		{
			query += " WHERE 1=1 ";
		}
		 		query +=  " AND EMP_STATUS='S' ";
				query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };
		String[] headerWidth = { "25", "75" };

		String[] fieldNames = { "applicantToken", "applicantName","applicantCode" };
		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}// end of f9action
	
	
}
