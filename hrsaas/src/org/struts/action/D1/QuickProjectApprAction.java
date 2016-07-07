package org.struts.action.D1;

import org.paradyne.bean.D1.QuickProjectBean;
import org.paradyne.model.D1.QuickProjectModel;
import org.struts.lib.ParaActionSupport;

public class QuickProjectApprAction extends ParaActionSupport {

	QuickProjectBean bean;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		bean = new QuickProjectBean();
		bean.setMenuCode(2049);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public QuickProjectBean getBean() {
		return bean;
	}

	public void setBean(QuickProjectBean bean) {
		this.bean = bean;
	}
	/**
	 * INPUT METHOD
	 */
	public String input(){
		QuickProjectModel model=new QuickProjectModel();
		model.initiate(context, session);
		model.inputAppr(bean,request);
		getNavigationPanel(1);
		model.terminate();
		return "input";
	}
	
	/**
	 * BACK METHOD
	 */
	public String back(){
		bean.setQuickProjectCode("");
		addActionMessage("");
		return input();
	}
	
	/**
	 *  METHOD TO FORWARD  APPLICATION
	 */
	public String forward() throws Exception{
		QuickProjectModel model=new QuickProjectModel();	
		model.initiate(context, session);
		boolean result=model.approve(bean,"F",request);
		addActionMessage("Application forwarded successfully");
		model.terminate();
		return input();
	}
	/**
	 *  METHOD TO REJECT  APPLICATION
	 */
	public String reject() throws Exception{
		QuickProjectModel model=new QuickProjectModel();	
		model.initiate(context, session);
		boolean result=model.approve(bean,"R",request);
		addActionMessage("Application rejected successfully");
		model.terminate();
		return input();
	}
	
	/**
	 *  METHOD TO SENDBACK  APPLICATION
	 */
	public String sendBack() throws Exception{
		QuickProjectModel model=new QuickProjectModel();	
		model.initiate(context, session);
		boolean result=model.approve(bean,"B",request);
		addActionMessage("Application send back successfully");
		model.terminate();
		return input();
	}
	/**
	 *  METHOD TO SENDBACK  APPLICATION
	 */
	public String authorizedSign() throws Exception{
		QuickProjectModel model=new QuickProjectModel();	
		model.initiate(context, session);
		boolean result=model.approve(bean,"S",request);
		addActionMessage("Application authorized sign off successfully");
		model.terminate();
		return input();
	}
	/**
	 *  METHOD TO SENDBACK  APPLICATION
	 */
	public String approve() throws Exception{
		QuickProjectModel model=new QuickProjectModel();	
		model.initiate(context, session);
		boolean result=model.approve(bean,"A",request);
		addActionMessage("Application approved successfully");
		model.terminate();
		return input();
	}
	
	//
	/**
	 *  METHOD TO EDIT APPLICATION
	 */
	public String editApplication(){
		QuickProjectModel model=new QuickProjectModel();	
		model.initiate(context, session);
		/*
		 * FOR SUPER USER
		 */
		String applCode=request.getParameter("applCode");
		if(applCode !=null &&applCode.length()>0){
			bean.setQuickProjectCode(applCode);
		}
        
		model.setEmployeeData(bean,"No");		
		model.getApproverComments(bean);
		getNavigationPanel(3);
		if(bean.getStatus().equals("P")||bean.getStatus().equals("F")){
			getNavigationPanel(6);
			bean.setStatus("PP");
		}
		else {
			getNavigationPanel(2);
			bean.setEnableAll("N");
		}
		System.out.println("bean.getStatus() :::: "+bean.getStatus());
		if(bean.getStatus().equals("S"))
		{
			getNavigationPanel(7);
			bean.setStatus("SS");
		}
		else if(bean.getForwardToHidden().equals("SS"))
		{
			getNavigationPanel(8);
			bean.setStatus("SS");
		}
		
		/**
		 * FOR APPROVED & REJECTED LIST 
		 */
		if(bean.getFlag().equals("AA")||bean.getFlag().equals("RR")){
			bean.setStatus("AA");
			getNavigationPanel(2);
			bean.setEnableAll("N");
		}
		
		//FOR SUPER USER
		if(applCode !=null &&applCode.length()>0){
			if(bean.getStatus().equals("D") || bean.getStatus().equals("A")|| bean.getStatus().equals("B")|| bean.getStatus().equals("R"))
			{
				getNavigationPanel(10);
				bean.setEnableAll("N");
			}else
				{
					getNavigationPanel(9);
					bean.setEnableAll("N");
				}
		}
		bean.setEnableAll("N");
		return SUCCESS;
	}
	
	/**
	 * METHOD TO SET NEXT APPROVER
	 * @return
	 * @throws Exception
	 */
	public String f9nextAppr() throws Exception {
		String query = " SELECT 	EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,EMP_ID FROM HRMS_EMP_OFFC	  ";
		if (bean.getUserProfileDivision() != null
				&& bean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
					+ bean.getUserProfileDivision() + ")";
		} else {
			query += " WHERE 1=1 ";
		}

		query += " AND HRMS_EMP_OFFC.EMP_STATUS='S'   "
				+ "	AND HRMS_EMP_OFFC.EMP_ID NOT IN(" + bean.getManagerCode()
				+ "," + bean.getUserEmpId() + ")   ORDER BY UPPER(EMP_FNAME) ";

		String[] headers = { getMessage("employee.id"), getMessage("employee") };
		String[] headerWidth = { "30", "70" };
		String[] fieldNames = { "nextAppToken", "nextAppName", "nextAppCode" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
}
