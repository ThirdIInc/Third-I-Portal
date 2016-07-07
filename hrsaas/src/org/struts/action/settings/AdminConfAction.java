package org.struts.action.settings;

import org.paradyne.bean.settings.AdminConf;
import org.paradyne.model.settings.AdminConfModel;
import org.struts.lib.ParaActionSupport;

public class AdminConfAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AdminConfAction.class);
	AdminConf bean;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		bean = new AdminConf();
		bean.setMenuCode(2247);

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public AdminConf getBean() {
		return bean;
	}

	public void setBean(AdminConf bean) {
		this.bean = bean;
	}
	public String input() {
		AdminConfModel model=new AdminConfModel();
		model.initiate(context, session);
		model.getConfDetails(bean);
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;
	}
	public String addAdminConfDtl(){
		String[] adminName=request.getParameterValues("adminNameIt");
		String[] divNameIt=request.getParameterValues("divNameIt");
		String[] adminCodeIt=request.getParameterValues("adminCodeIt");
		String[] divCodeIt=request.getParameterValues("divCodeIt");
		
		String[] accAdminNameIt=request.getParameterValues("accAdminNameIt");
		String[] accDivNameIt=request.getParameterValues("accDivNameIt");
		String[] accAdminCodeIt=request.getParameterValues("accAdminCodeIt");
		String[] accDivCodeIt=request.getParameterValues("accDivCodeIt");
		
		String[] bCardAdminNameIt=request.getParameterValues("bCardAdminNameIt");
		String[] bCardDivNameIt=request.getParameterValues("bCardDivNameIt");
		String[] bCardAdminCodeIt=request.getParameterValues("bCardAdminCodeIt");
		String[] bCardDivCodeIt=request.getParameterValues("bCardDivCodeIt");
		
		String[] eCardAdminNameIt=request.getParameterValues("eCardAdminNameIt");
		String[] eCardDivNameIt=request.getParameterValues("eCardDivNameIt");
		String[] eCardAdminCodeIt=request.getParameterValues("eCardAdminCodeIt");
		String[] eCardDivCodeIt=request.getParameterValues("eCardDivCodeIt");

		String adminConfId[]=request.getParameterValues("adminConfIdIt");
		String accConfId[]=request.getParameterValues("accConfIdIt");
		String bCardConfId[]=request.getParameterValues("bCardConfIdIt");
		String eCardConfId[]=request.getParameterValues("eCardConfIdIt");
		
		AdminConfModel model = new AdminConfModel();
		model.initiate(context, session);
		model.addAdminConfDetails(bean,adminName,adminCodeIt,divNameIt,divCodeIt,adminConfId);
		
		if(accAdminNameIt!=null && accAdminNameIt.length>0)
			model.setAcctIteratorValues(bean,accAdminNameIt,accDivNameIt,accAdminCodeIt,accDivCodeIt,accConfId);
		
		if(bCardAdminNameIt!=null && bCardAdminNameIt.length>0)
			model.setBCardIteratorValues(bean,bCardAdminNameIt,bCardDivNameIt,bCardAdminCodeIt,bCardDivCodeIt,bCardConfId);
		
		if(eCardAdminNameIt!=null && eCardAdminNameIt.length>0)
			model.setECardIteratorValues(bean,eCardAdminNameIt,eCardDivNameIt,eCardAdminCodeIt,eCardDivCodeIt,eCardConfId);
		
		bean.setDivision("");
		bean.setEmpName("");
		bean.setEmpId("");
		bean.setEmpToken("");
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;
	}
	public String addAccountantConfDtl(){
		String[] adminNameIt=request.getParameterValues("adminNameIt");
		String[] divNameIt=request.getParameterValues("divNameIt");
		String[] adminCodeIt=request.getParameterValues("adminCodeIt");
		String[] divCodeIt=request.getParameterValues("divCodeIt");
		
		String[] accAdminNameIt=request.getParameterValues("accAdminNameIt");
		String[] accDivNameIt=request.getParameterValues("accDivNameIt");
		String[] accAdminCodeIt=request.getParameterValues("accAdminCodeIt");
		String[] accDivCodeIt=request.getParameterValues("accDivCodeIt");
		
		String[] bCardAdminNameIt=request.getParameterValues("bCardAdminNameIt");
		String[] bCardDivNameIt=request.getParameterValues("bCardDivNameIt");
		String[] bCardAdminCodeIt=request.getParameterValues("bCardAdminCodeIt");
		String[] bCardDivCodeIt=request.getParameterValues("bCardDivCodeIt");
		
		String[] eCardAdminNameIt=request.getParameterValues("eCardAdminNameIt");
		String[] eCardDivNameIt=request.getParameterValues("eCardDivNameIt");
		String[] eCardAdminCodeIt=request.getParameterValues("eCardAdminCodeIt");
		String[] eCardDivCodeIt=request.getParameterValues("eCardDivCodeIt");

		String adminConfId[]=request.getParameterValues("adminConfIdIt");
		String accConfId[]=request.getParameterValues("accConfIdIt");
		String bCardConfId[]=request.getParameterValues("bCardConfIdIt");
		String eCardConfId[]=request.getParameterValues("eCardConfIdIt");
		
		AdminConfModel model = new AdminConfModel();
		model.initiate(context, session);
		model.addAcctConfDetails(bean,accAdminNameIt,accDivNameIt,accAdminCodeIt,accDivCodeIt,accConfId);
		
		if(adminNameIt!=null && adminNameIt.length>0)
			model.setAdminIteratorValues(bean,adminNameIt,divNameIt,adminCodeIt,divCodeIt,adminConfId);
		
		if(bCardAdminNameIt!=null && bCardAdminNameIt.length>0)
			model.setBCardIteratorValues(bean,bCardAdminNameIt,bCardDivNameIt,bCardAdminCodeIt,bCardDivCodeIt,bCardConfId);
		
		if(eCardAdminNameIt!=null && eCardAdminNameIt.length>0)
			model.setECardIteratorValues(bean,eCardAdminNameIt,eCardDivNameIt,eCardAdminCodeIt,eCardDivCodeIt,eCardConfId);
		bean.setAccDivision("");
		bean.setAccEmpName("");
		bean.setAccEmpId("");
		bean.setAccEmpToken("");
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;
	}
	public String addBCardAdminConfDtl(){
		String[] adminNameIt=request.getParameterValues("adminNameIt");
		String[] divNameIt=request.getParameterValues("divNameIt");
		String[] adminCodeIt=request.getParameterValues("adminCodeIt");
		String[] divCodeIt=request.getParameterValues("divCodeIt");
		
		String[] accAdminNameIt=request.getParameterValues("accAdminNameIt");
		String[] accDivNameIt=request.getParameterValues("accDivNameIt");
		String[] accAdminCodeIt=request.getParameterValues("accAdminCodeIt");
		String[] accDivCodeIt=request.getParameterValues("accDivCodeIt");
		
		String[] bCardAdminNameIt=request.getParameterValues("bCardAdminNameIt");
		String[] bCardDivNameIt=request.getParameterValues("bCardDivNameIt");
		String[] bCardAdminCodeIt=request.getParameterValues("bCardAdminCodeIt");
		String[] bCardDivCodeIt=request.getParameterValues("bCardDivCodeIt");
		
		String[] eCardAdminNameIt=request.getParameterValues("eCardAdminNameIt");
		String[] eCardDivNameIt=request.getParameterValues("eCardDivNameIt");
		String[] eCardAdminCodeIt=request.getParameterValues("eCardAdminCodeIt");
		String[] eCardDivCodeIt=request.getParameterValues("eCardDivCodeIt");
		
		String adminConfId[]=request.getParameterValues("adminConfIdIt");
		String accConfId[]=request.getParameterValues("accConfIdIt");
		String bCardConfId[]=request.getParameterValues("bCardConfIdIt");
		String eCardConfId[]=request.getParameterValues("eCardConfIdIt");
		
		AdminConfModel model = new AdminConfModel();
		model.initiate(context, session);
		model.addBCardAdminConfDetails(bean,bCardAdminNameIt,bCardDivNameIt,bCardAdminCodeIt,bCardDivCodeIt,bCardConfId);
		
		if(adminNameIt!=null && adminNameIt.length>0)
			model.setAdminIteratorValues(bean,adminNameIt,divNameIt,adminCodeIt,divCodeIt,adminConfId);
		
		if(accAdminNameIt!=null && accAdminNameIt.length>0)
			model.setAcctIteratorValues(bean,accAdminNameIt,accDivNameIt,accAdminCodeIt,accDivCodeIt,accConfId);
		
		if(eCardAdminNameIt!=null && eCardAdminNameIt.length>0)
			model.setECardIteratorValues(bean,eCardAdminNameIt,eCardDivNameIt,eCardAdminCodeIt,eCardDivCodeIt,eCardConfId);
		
		
		bean.setBCardDivCode("");
		bean.setBCardDivision("");
		bean.setBCardEmpId("");
		bean.setBCardEmpName("");
		bean.setBCardEmpToken("");
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;
	}
	public String addECardAdminConfDtl(){
		String[] adminNameIt=request.getParameterValues("adminNameIt");
		String[] divNameIt=request.getParameterValues("divNameIt");
		String[] adminCodeIt=request.getParameterValues("adminCodeIt");
		String[] divCodeIt=request.getParameterValues("divCodeIt");
		
		String[] accAdminNameIt=request.getParameterValues("accAdminNameIt");
		String[] accDivNameIt=request.getParameterValues("accDivNameIt");
		String[] accAdminCodeIt=request.getParameterValues("accAdminCodeIt");
		String[] accDivCodeIt=request.getParameterValues("accDivCodeIt");
		
		String[] bCardAdminNameIt=request.getParameterValues("bCardAdminNameIt");
		String[] bCardDivNameIt=request.getParameterValues("bCardDivNameIt");
		String[] bCardAdminCodeIt=request.getParameterValues("bCardAdminCodeIt");
		String[] bCardDivCodeIt=request.getParameterValues("bCardDivCodeIt");
		
		String[] eCardAdminNameIt=request.getParameterValues("eCardAdminNameIt");
		String[] eCardDivNameIt=request.getParameterValues("eCardDivNameIt");
		String[] eCardAdminCodeIt=request.getParameterValues("eCardAdminCodeIt");
		String[] eCardDivCodeIt=request.getParameterValues("eCardDivCodeIt");
		String adminConfId[]=request.getParameterValues("adminConfIdIt");
		String accConfId[]=request.getParameterValues("accConfIdIt");
		String bCardConfId[]=request.getParameterValues("bCardConfIdIt");
		String eCardConfId[]=request.getParameterValues("eCardConfIdIt");
		//Vendor Details
		String eCardVndrNameIt[]=request.getParameterValues("eCardVndrNameIt");
		String eCardVndrEmailIt[]=request.getParameterValues("eCardVndrEmailIt");
		String eCardVndrPhNumIt[]=request.getParameterValues("eCardVndrPhNumIt");
				
		AdminConfModel model = new AdminConfModel();
		model.initiate(context, session);
		model.addECardAdminConfDetails(bean,eCardAdminNameIt,eCardDivNameIt,eCardAdminCodeIt,eCardDivCodeIt,eCardConfId);
		
		if(adminNameIt!=null && adminNameIt.length>0)
			model.setAdminIteratorValues(bean,adminNameIt,divNameIt,adminCodeIt,divCodeIt,adminConfId);
		
		if(accAdminNameIt!=null && accAdminNameIt.length>0)
			model.setAcctIteratorValues(bean,accAdminNameIt,accDivNameIt,accAdminCodeIt,accDivCodeIt,accConfId);
		
		if(bCardAdminNameIt!=null && bCardAdminNameIt.length>0)
			model.setBCardIteratorValues(bean,bCardAdminNameIt,bCardDivNameIt,bCardAdminCodeIt,bCardDivCodeIt,bCardConfId);
		if(eCardVndrNameIt!=null && eCardVndrNameIt.length>0)
			model.setECardVendorIteratorValues(bean,eCardVndrNameIt,eCardVndrEmailIt,eCardVndrPhNumIt);
		
		bean.setECardDivCode("");
		bean.setECardDivision("");
		bean.setECardEmpId("");
		bean.setECardEmpName("");
		bean.setECardEmpToken("");
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;
	}
	
	
	public String deleteAdminConfDetails(){
		String[] adminNameIt=request.getParameterValues("adminNameIt");
		String[] divNameIt=request.getParameterValues("divNameIt");
		String[] adminCodeIt=request.getParameterValues("adminCodeIt");
		String[] divCodeIt=request.getParameterValues("divCodeIt");
		
		String[] accAdminNameIt=request.getParameterValues("accAdminNameIt");
		String[] accDivNameIt=request.getParameterValues("accDivNameIt");
		String[] accAdminCodeIt=request.getParameterValues("accAdminCodeIt");
		String[] accDivCodeIt=request.getParameterValues("accDivCodeIt");
		
		String[] bCardAdminNameIt=request.getParameterValues("bCardAdminNameIt");
		String[] bCardDivNameIt=request.getParameterValues("bCardDivNameIt");
		String[] bCardAdminCodeIt=request.getParameterValues("bCardAdminCodeIt");
		String[] bCardDivCodeIt=request.getParameterValues("bCardDivCodeIt");
		
		String[] eCardAdminNameIt=request.getParameterValues("eCardAdminNameIt");
		String[] eCardDivNameIt=request.getParameterValues("eCardDivNameIt");
		String[] eCardAdminCodeIt=request.getParameterValues("eCardAdminCodeIt");
		String[] eCardDivCodeIt=request.getParameterValues("eCardDivCodeIt");
		
		String adminConfId[]=request.getParameterValues("adminConfIdIt");
		String accConfId[]=request.getParameterValues("accConfIdIt");
		String bCardConfId[]=request.getParameterValues("bCardConfIdIt");
		String eCardConfId[]=request.getParameterValues("eCardConfIdIt");
		String confid=request.getParameter("adminConfIdIt");
		AdminConfModel model = new AdminConfModel();
		model.initiate(context, session);
		model.deleteAdminConfDtl(bean,adminNameIt,divNameIt,adminCodeIt,divCodeIt,adminConfId,confid);
		
		
				
		if(accAdminNameIt!=null && accAdminNameIt.length>0)
			model.setAcctIteratorValues(bean,accAdminNameIt,accDivNameIt,accAdminCodeIt,accDivCodeIt,accConfId);
		
		if(bCardAdminNameIt!=null && bCardAdminNameIt.length>0)
			model.setBCardIteratorValues(bean,bCardAdminNameIt,bCardDivNameIt,bCardAdminCodeIt,bCardDivCodeIt,bCardConfId);
		
		if(eCardAdminNameIt!=null && eCardAdminNameIt.length>0)
			model.setECardIteratorValues(bean,eCardAdminNameIt,eCardDivNameIt,eCardAdminCodeIt,eCardDivCodeIt,eCardConfId);
		//setIteratorValues();
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;
	}
	public String deleteAccConfDetails(){
		System.out.println("-----DELETE ACC CONF DETAILS--------");
		String[] accAdminNameIt=request.getParameterValues("accAdminNameIt");
		String[] accDivNameIt=request.getParameterValues("accDivNameIt"); 
		String[] accAdminCodeIt=request.getParameterValues("accAdminCodeIt");
		String[] accDivCodeIt=request.getParameterValues("accDivCodeIt");
		
		String[] adminNameIt=request.getParameterValues("adminNameIt");
		String[] divNameIt=request.getParameterValues("divNameIt");
		String[] adminCodeIt=request.getParameterValues("adminCodeIt");
		String[] divCodeIt=request.getParameterValues("divCodeIt");
		
		String[] bCardAdminNameIt=request.getParameterValues("bCardAdminNameIt");
		String[] bCardDivNameIt=request.getParameterValues("bCardDivNameIt");
		String[] bCardAdminCodeIt=request.getParameterValues("bCardAdminCodeIt");
		String[] bCardDivCodeIt=request.getParameterValues("bCardDivCodeIt");
		
		String[] eCardAdminNameIt=request.getParameterValues("eCardAdminNameIt");
		String[] eCardDivNameIt=request.getParameterValues("eCardDivNameIt");
		String[] eCardAdminCodeIt=request.getParameterValues("eCardAdminCodeIt");
		String[] eCardDivCodeIt=request.getParameterValues("eCardDivCodeIt");
		
		String adminConfId[]=request.getParameterValues("adminConfIdIt");
		String accConfId[]=request.getParameterValues("accConfIdIt");
		String bCardConfId[]=request.getParameterValues("bCardConfIdIt");
		String eCardConfId[]=request.getParameterValues("eCardConfIdIt");
		String confid=request.getParameter("accConfIdIt");
		AdminConfModel model = new AdminConfModel();
		model.initiate(context, session);
		model.deleteAcctConfDetails(bean,accAdminNameIt,accDivNameIt,accAdminCodeIt,accDivCodeIt,accConfId,confid);
		
		if(adminNameIt!=null && adminNameIt.length>0)
			model.setAdminIteratorValues(bean,adminNameIt,divNameIt,adminCodeIt,divCodeIt,adminConfId);
		
		
		
		if(bCardAdminNameIt!=null && bCardAdminNameIt.length>0)
			model.setBCardIteratorValues(bean,bCardAdminNameIt,bCardDivNameIt,bCardAdminCodeIt,bCardDivCodeIt,bCardConfId);
		
		if(eCardAdminNameIt!=null && eCardAdminNameIt.length>0)
			model.setECardIteratorValues(bean,eCardAdminNameIt,eCardDivNameIt,eCardAdminCodeIt,eCardDivCodeIt,eCardConfId);
		//setIteratorValues();
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;
	}
	
	public String deleteBCardConfDetails(){ 
		String[] adminNameIt=request.getParameterValues("adminNameIt");
		String[] divNameIt=request.getParameterValues("divNameIt");
		String[] adminCodeIt=request.getParameterValues("adminCodeIt");
		String[] divCodeIt=request.getParameterValues("divCodeIt");
		
		String[] accAdminNameIt=request.getParameterValues("accAdminNameIt");
		String[] accDivNameIt=request.getParameterValues("accDivNameIt");
		String[] accAdminCodeIt=request.getParameterValues("accAdminCodeIt");
		String[] accDivCodeIt=request.getParameterValues("accDivCodeIt");
				
		String[] eCardAdminNameIt=request.getParameterValues("eCardAdminNameIt");
		String[] eCardDivNameIt=request.getParameterValues("eCardDivNameIt");
		String[] eCardAdminCodeIt=request.getParameterValues("eCardAdminCodeIt");
		String[] eCardDivCodeIt=request.getParameterValues("eCardDivCodeIt");
		
		String[] bCardAdminNameIt=request.getParameterValues("bCardAdminNameIt");
		String[] bCardDivNameIt=request.getParameterValues("bCardDivNameIt"); 
		String[] bCardAdminCodeIt=request.getParameterValues("bCardAdminCodeIt");
		String[] bCardDivCodeIt=request.getParameterValues("bCardDivCodeIt"); 

		String adminConfId[]=request.getParameterValues("adminConfIdIt");
		String accConfId[]=request.getParameterValues("accConfIdIt");
		String bCardConfId[]=request.getParameterValues("bCardConfIdIt");
		String eCardConfId[]=request.getParameterValues("eCardConfIdIt");
		String confid=request.getParameter("bCardConfIdIt");
		AdminConfModel model = new AdminConfModel();
		model.initiate(context, session);
		model.deleteBCardConfDtl(bean,bCardAdminNameIt,bCardDivNameIt,bCardAdminCodeIt,bCardDivCodeIt,bCardConfId,confid);
		
		if(adminNameIt!=null && adminNameIt.length>0)
			model.setAdminIteratorValues(bean,adminNameIt,divNameIt,adminCodeIt,divCodeIt,adminConfId);
		
		if(accAdminNameIt!=null && accAdminNameIt.length>0)
			model.setAcctIteratorValues(bean,accAdminNameIt,accDivNameIt,accAdminCodeIt,accDivCodeIt,accConfId);
		
		if(eCardAdminNameIt!=null && eCardAdminNameIt.length>0)
			model.setECardIteratorValues(bean,eCardAdminNameIt,eCardDivNameIt,eCardAdminCodeIt,eCardDivCodeIt,eCardConfId);
		//setIteratorValues();
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;
	}
	public String deleteECardConfDetails(){	
		String[] eCardAdminName=request.getParameterValues("eCardAdminNameIt");
		String[] eCardDivNameIt=request.getParameterValues("eCardDivNameIt"); 
		String[] eCardAdminCode=request.getParameterValues("eCardAdminCodeIt");
		String[] eCardDivCodeIt=request.getParameterValues("eCardDivCodeIt"); 
		
		String[] adminNameIt=request.getParameterValues("adminNameIt");
		String[] divNameIt=request.getParameterValues("divNameIt");
		String[] adminCodeIt=request.getParameterValues("adminCodeIt");
		String[] divCodeIt=request.getParameterValues("divCodeIt");
		
		String[] accAdminNameIt=request.getParameterValues("accAdminNameIt");
		String[] accDivNameIt=request.getParameterValues("accDivNameIt");
		String[] accAdminCodeIt=request.getParameterValues("accAdminCodeIt");
		String[] accDivCodeIt=request.getParameterValues("accDivCodeIt");
		
		String[] bCardAdminNameIt=request.getParameterValues("bCardAdminNameIt");
		String[] bCardDivNameIt=request.getParameterValues("bCardDivNameIt");
		String[] bCardAdminCodeIt=request.getParameterValues("bCardAdminCodeIt");
		String[] bCardDivCodeIt=request.getParameterValues("bCardDivCodeIt");
		String confId[]=request.getParameterValues("eCardConfIdIt");
		String confBusinessId[]=request.getParameterValues("bCardConfIdIt");
		String confid=request.getParameter("eCardConfIdIt");
		AdminConfModel model = new AdminConfModel();
		model.initiate(context, session);
		model.deleteECardConfDtl(bean,eCardAdminName,eCardDivNameIt,eCardAdminCode,eCardDivCodeIt,confId,confid);
		
		if(adminNameIt!=null && adminNameIt.length>0)
			model.setAdminIteratorValues(bean,adminNameIt,divNameIt,adminCodeIt,divCodeIt,confId);
		
		if(accAdminNameIt!=null && accAdminNameIt.length>0)
			model.setAcctIteratorValues(bean,accAdminNameIt,accDivNameIt,accAdminCodeIt,accDivCodeIt,confId);
		
		if(bCardAdminNameIt!=null && bCardAdminNameIt.length>0)
			model.setBCardIteratorValues(bean,bCardAdminNameIt,bCardDivNameIt,bCardAdminCodeIt,bCardDivCodeIt,confBusinessId);
		
		
		//setIteratorValues();
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;
	}
	
	
	
	public String saveConfDetails(){
		boolean result;
		String[] adminNameIt=request.getParameterValues("adminCodeIt");
		String[] divNameIt=request.getParameterValues("divCodeIt"); 
		String[] accAdminCodeIt=request.getParameterValues("accAdminCodeIt");
		String[] accDivCodeIt=request.getParameterValues("accDivCodeIt"); 
				
		String[] bCardAdminCodeIt=request.getParameterValues("bCardAdminCodeIt");
		String[] bCardDivCodeIt=request.getParameterValues("bCardDivCodeIt"); 
		
		String[] eCardAdminCodeIt=request.getParameterValues("eCardAdminCodeIt");
		String[] eCardDivCodeIt=request.getParameterValues("eCardDivCodeIt"); 
		
		AdminConfModel model = new AdminConfModel();
		model.initiate(context, session);
		result = model.saveConfDtl(bean,adminNameIt,divNameIt,accAdminCodeIt,accDivCodeIt,
				bCardAdminCodeIt,bCardDivCodeIt,eCardAdminCodeIt,eCardDivCodeIt);
		if(result){
			addActionMessage(" Records saved sucessfully");
		}else{
			addActionMessage(" Records not saved sucessfully");
		}
		model.getConfDetails(bean);
		reset();
		getNavigationPanel(1);
		bean.setEnableAll("N");
		model.terminate();
		return SUCCESS;
	}
	public String f9Admin() throws Exception {
		String query = " SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) EMP_NAME,"
					   + " EMP_ID FROM HRMS_EMP_OFFC WHERE HRMS_EMP_OFFC.EMP_STATUS='S'ORDER BY EMP_ID";
		String[] headers = { getMessage("empToken"), getMessage("empName") };
		String[] headerWidth = { "10", "30" };
		String[] fieldNames = { "empToken", "empName", "empId" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = " ";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,submitFlag, submitToMethod);
		return "f9page";
	}
	public String f9AccAdmin() throws Exception {
		String query = " SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) EMP_NAME, EMP_ID FROM HRMS_EMP_OFFC "
				+ " ORDER BY EMP_ID";
		String[] headers = { getMessage("accEmpToken"), getMessage("accEmpName") };
		String[] headerWidth = { "10", "30" };
		String[] fieldNames = { "accEmpToken", "accEmpName", "accEmpId" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = " ";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,submitFlag, submitToMethod);
		return "f9page";
	}
	public String f9BCardAdmin() throws Exception {
		String query = " SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) EMP_NAME, "
					   + " EMP_ID FROM HRMS_EMP_OFFC WHERE HRMS_EMP_OFFC.EMP_STATUS='S'"
					   + " ORDER BY EMP_ID";
		String[] headers = { getMessage("empToken"), getMessage("empName") };
		String[] headerWidth = { "10", "30" };
		String[] fieldNames = { "bCardEmpToken", "bCardEmpName", "bCardEmpId" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = " ";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,submitFlag, submitToMethod);
		return "f9page";
	}
	public String f9ECardAdmin() throws Exception {
		String query =" SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) EMP_NAME, "
			   		  + " EMP_ID FROM HRMS_EMP_OFFC WHERE HRMS_EMP_OFFC.EMP_STATUS='S'"
			   		  + " ORDER BY EMP_ID";
		String[] headers = { getMessage("empToken"), getMessage("empName") };
		String[] headerWidth = { "10", "30" };
		String[] fieldNames = { "eCardEmpToken", "eCardEmpName", "eCardEmpId" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = " ";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,submitFlag, submitToMethod);
		return "f9page";
	}
	public String f9Div() throws Exception {
		String query = " SELECT  DIV_ID, DIV_NAME	FROM HRMS_DIVISION  ORDER BY DIV_ID";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Division Code", getMessage("division")};
		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "15", "35" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "divCode", "division" };
		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0 
		 */
		int[] columnIndex = { 0, 1 };
		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";
		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}// end of f9div

	public String f9AccDiv() throws Exception {
		String query = " SELECT  DIV_ID, DIV_NAME	FROM HRMS_DIVISION  ORDER BY DIV_ID";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Division Code", getMessage("division")};
		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "15", "35" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "accDivCode", "accDivision" };
		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0 
		 */
		int[] columnIndex = { 0, 1 };
		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";
		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}// end of f9div
	public String f9BCardDiv() throws Exception {
		String query = " SELECT  DIV_ID, DIV_NAME	FROM HRMS_DIVISION  ORDER BY DIV_ID";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Division Code", getMessage("division")};
		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "15", "35" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "bCardDivCode", "bCardDivision" };
		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0 
		 */
		int[] columnIndex = { 0, 1 };
		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";
		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}// end of f9div

	public String f9ECardDiv() throws Exception {
		String query = " SELECT  DIV_ID, DIV_NAME	FROM HRMS_DIVISION  ORDER BY DIV_ID";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Division Code", getMessage("division")};
		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "15", "35" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "eCardDivCode", "eCardDivision" };
		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0 
		 */
		int[] columnIndex = { 0, 1 };
		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "false";
		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";
		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}// end of f9div
	public String reset(){
		bean.setDivCode("");
		bean.setDivision("");
		
		bean.setAccDivision("");
		bean.setAccDivCode("");
		
		bean.setBCardDivCode("");
		bean.setBCardDivision("");
		
		bean.setECardDivCode("");
		bean.setECardDivision("");
		
		bean.setEmpId("");
		bean.setEmpName("");
		bean.setEmpToken("");
		
		bean.setAccEmpId("");
		bean.setAccEmpName("");
		bean.setAccEmpToken("");
		
		bean.setBCardEmpId("");
		bean.setBCardEmpName("");
		bean.setBCardEmpToken("");
		
		//bean.setBCardVendorFlag("");
		//bean.setBCardVndrName("");
		//bean.setBCardVndrEmail("");
		//bean.setBCardVndrPhNum("");

		
		bean.setECardEmpId("");
		bean.setECardEmpName("");
		bean.setECardEmpToken("");
		
	//	bean.setECardVendorFlag("");
	//	bean.setECardVndrName("");
	//	bean.setECardVndrEmail("");
	//	bean.setECardVndrPhNum("");
		getNavigationPanel(1);
		return SUCCESS;
	}
	/*public String editHr() {
		AdminConfModel model = new AdminConfModel();
		model.initiate(context, session);
		model.editHr(bean);// edit
		model.saveHrComm(bean, "show", poolDir, fileName);// save
		//bean.setCheckFlag_ql("true");
		//bean.setCheckFlag_gs("true");
		model.terminate();
		return "success";
	}*/

}
