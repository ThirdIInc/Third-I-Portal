package org.struts.action.CR;

import java.util.ArrayList;

import org.paradyne.bean.CR.ManageClientDashBoard;
import org.paradyne.model.CR.AccountMasterModel;
import org.paradyne.model.CR.ManageClientDashBoardModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author vijay.gaikwad
 *
 */

public class ManageClientDashBoardAction extends ParaActionSupport {
	ManageClientDashBoard bean;
	
	public ManageClientDashBoard getBean()
	  {
	    return this.bean;
	  }
	
	public Object getModel()
	  {
	    return this.bean;
	  }
	
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	public void prepare_local() throws Exception {
		    this.bean = new ManageClientDashBoard();
		    this.bean.setMenuCode(5052);
		  }
	/*
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#input()
	 */
	public String input(){
		 ManageClientDashBoardModel model = new ManageClientDashBoardModel();
		 model.initiate(this.context, this.session);
		 model.getDashBoardList(this.bean,request);//getting dashBoard List
		 model.terminate();
		 return "input";
	 }
	/*
	 * this action is user for managing the external user for Accounts External User and DashBoard External User
	 * UI for internal Users
	 * @return String
	 */
	public String callForInternalUsers(){
		String dashBoardID=request.getParameter("dashBoardID");
		String dashBoardName=request.getParameter("dashBoardName");
		String accountID=request.getParameter("accountID");
		String accountName=request.getParameter("accountName");
		String backToAccountLsit=request.getParameter("backToAccountLsit");
		bean.setBackToAccountLsit(backToAccountLsit);
		System.out.println("dashBoardID - "+dashBoardID+"dashBoardName - "+dashBoardName+"accountID - "+accountID+"accountName - "+accountName);
		bean.setDashBoardID(dashBoardID);
		bean.setDashBoardName(dashBoardName);
		bean.setAccountID(accountID);
		bean.setAccountName(accountName);
		bean.setBackToAccountLsit(backToAccountLsit);
		ManageClientDashBoardModel model = new ManageClientDashBoardModel();
		model.initiate(this.context, this.session);
		model.getEmployeeList(this.bean,accountID);
		model.terminate();
		
		return "internalUser";
	}

	
	/**
	 * @author vivek.wadhwani
	 * This function is used for User wise filter for dashboard
	 * UI for internal user filter
	 * @return String "userFilter"
	 */
	public String userFilter(){
		
		boolean result = false;
		addActionMessage("");
		ManageClientDashBoardModel model = new ManageClientDashBoardModel();
		String dashBoardID =request.getParameter("dashBoardID");
		String informCode = request.getParameter("informCode");
		String userType = request.getParameter("userType");
		String dashBoardName = request.getParameter("dashBoardName");
		String backToAccountLsit = request.getParameter("backToAccountLsit");
		String accountID = request.getParameter("accountID");
		String accountName = request.getParameter("accountName");
		String informName=request.getParameter("informName");
		bean.setDashBoardID(dashBoardID);
		bean.setInformCode(informCode);
		bean.setUserType(userType);
		bean.setDashBoardName(dashBoardName);
		bean.setBackToAccountLsit(backToAccountLsit);
		bean.setAccountID(accountID);
		bean.setAccountName(accountName);
		bean.setInformName(informName);
		bean.setShowParameterFlag("false");
		bean.setShowReportFlag("false");
		bean.setShowGraphFlag("false");
		model.initiate(this.context, this.session);
		model.userFilter(this.bean,dashBoardID,informCode,userType,request);
		
		model.terminate();
		
		
		return "userFilter";
	}
	
	
	/**
	 * This function is used for save configued dashBoard Settings
	 * 
	 * @return
	 */
	public String saveFilter() {
		
		boolean result= false;
		
		ManageClientDashBoardModel model = new ManageClientDashBoardModel();
		model.initiate(this.context, this.session);
		String dashBoardID =request.getParameter("dashBoardID");
		String informCode = request.getParameter("informCode");
		String userType = request.getParameter("userType");
		String dashBoardName = request.getParameter("dashBoardName");
		String backToAccountLsit = request.getParameter("backToAccountLsit");
		String accountID = request.getParameter("accountID");
		String accountName = request.getParameter("accountName");
		System.out.println("users id is"+this.bean.getApplUserCode());
		//for applying same user if its not in multiple users
		if(!String.valueOf(this.bean.getApplUserCode()).contains(informCode)){
			model.deleteFilter(this.bean,dashBoardID,informCode,userType,request);
			result = model.saveFilter(this.bean,dashBoardID,informCode,userType,request);
				}
		String applyUsers[]=String.valueOf(this.bean.getApplUserCode()).split(",");
		//Applying all Other Users
		if(applyUsers!=null && !applyUsers[0].equals("")){
		for(int i=0;applyUsers.length>i;i++){
			model.deleteFilter(this.bean,dashBoardID,applyUsers[i],userType,request);
			result = model.saveFilter(this.bean,dashBoardID,applyUsers[i],userType,request);
		}
	} 
	
		//}
		if(result)
		{
			addActionMessage(getMessage("save"));
		}
		else{
			addActionMessage(getMessage("save.error"));
		}
		bean.setBackToAccountLsit(backToAccountLsit);
		bean.setDashBoardID(dashBoardID);
		bean.setDashBoardName(dashBoardName);
		bean.setAccountID(accountID);
		bean.setAccountName(accountName);
		model.terminate();
		if (userType.equals("I")) {
			callForInternalUsers();
			return "internalUser";
		} else {
			callForExternalUsers();
			return "externalUser";
		}
		
	}
	

	/**
	 * this action is user for managing the external user for Accounts External User and DashBoard External User
	 * @return String
	 */

	public String callForExternalUsers(){
		String dashBoardID=request.getParameter("dashBoardID");
		String dashBoardName=request.getParameter("dashBoardName");
		String accountID=request.getParameter("accountID");
		String accountName=request.getParameter("accountName");
		String backToAccountLsit=request.getParameter("backToAccountLsit");
		bean.setBackToAccountLsit(backToAccountLsit);
		System.out.println("dashBoardID - "+dashBoardID+"dashBoardName - "+dashBoardName+"accountID - "+accountID+"accountName - "+accountName);
		bean.setDashBoardID(dashBoardID);
		bean.setDashBoardName(dashBoardName);
		bean.setAccountID(accountID);
		bean.setAccountName(accountName);
		ManageClientDashBoardModel model = new ManageClientDashBoardModel();
		model.initiate(this.context, this.session);
		model.getAccountList(this.bean,accountID,request);
		model.terminate();
		return "externalUser";
	}
	
	/**
	 * This action is used for display account list of corresponding dashBoard
	 * 
	 * @return String
	 */
	public String getDashBoardAccountList(){
		ManageClientDashBoardModel model = new ManageClientDashBoardModel();
		model.initiate(this.context, this.session);
		String searchMessagestr=request.getParameter("searchMessagestr");
		this.bean.setSearchMessagestr(searchMessagestr);
		bean.setMyPage("1");
		ArrayList list=model.getManageAccountList(this.bean.getDashBoardID(),this.bean,request,searchMessagestr);
		this.bean.setKeepInformedList(list);
		model.terminate();
		return "dashBoardAccountList";
	}
	
	/**
	 * This method is used for pagination 
	 * @return String
	 */
	public String callPage(){
		ManageClientDashBoardModel model = new ManageClientDashBoardModel();
		model.initiate(this.context, this.session);
		ArrayList list=model.getManageAccountList(this.bean.getDashBoardID(),this.bean,request,bean.getSearchMessagestr());
		this.bean.setKeepInformedList(list);
		model.terminate();
		return "dashBoardAccountList";
	}
	
	
	/**
	 * This method is used for update dashboard name and its active or inactive status
	 * @return string
	 */
	public String updateDashBoardDetails(){
		System.out.println("dashBoard Name - "+this.bean.getDashBoardName());
		ManageClientDashBoardModel model = new ManageClientDashBoardModel();
		model.initiate(this.context, this.session);
		String dashBoardname=request.getParameter("dashBoardname");
		String isActive=request.getParameter("isActive");
		String dashBoardID=request.getParameter("dashBoardID");
		bean.setDashBoardName(dashBoardname);
		
		if(isActive.equals("Yes")){
			isActive="YY";
		}
		else{
			isActive="NN";
		}
		
		this.bean.setIsActive(isActive);
		this.bean.setDashBoardID(dashBoardID);
		System.out.println("dashBoard Name - "+this.bean.getDashBoardName());
		System.out.println("dashBoard Name - "+this.bean.getIsActive());
		model.getDashBoardReportList(this.bean);
		model.terminate();
		
		return "updateDashBoardDetails";
	}
	
	/**
	 * This method is used for inserting updated dashboard name and its active or inactive status
	 * @return string
	 */
	public String saveDashBoardDetails(){
		ManageClientDashBoardModel model = new ManageClientDashBoardModel();
		model.initiate(this.context, this.session);
		String dashBoardName=request.getParameter("dashBaordName");
		String isActive=request.getParameter("isActive");
		System.out.println("dashBoardID"+this.bean.getDashBoardID());
		model.updateDashBoardDetails(dashBoardName,isActive,this.bean);
		model.getDashBoardList(this.bean,request);
		
		return "input";
	}
	
	
	/**
	 * f9 for DahBoard Employee Search function
	 * @return String
	 * @throws Exception
	 */
	public String f9informTo() throws Exception
	  {
		System.out.println("dashBoard ID- "+this.bean.getDashBoardID());
		try {
	      String[] empCode = this.request.getParameterValues("keepInformToCode");
	      String code = this.bean.getUserEmpId();
	      if (empCode != null) {
	        for (int i = 0; i < empCode.length; i++) {
	          code = code + "," + empCode[i];
	        }
	      }
	    String accountID="";
	    if(this.bean.getAccountID()!=null&&!this.bean.getAccountID().equals("")){
	    	accountID=this.bean.getAccountID();
	    }
	    else{
	    	accountID="0";
	    }
	      
	    String AccountID=this.bean.getAccountID();
	    System.out.println("acccccccccccccccccout id    "+AccountID);
	      String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN ,  HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME \t,HRMS_EMP_OFFC.EMP_ID  FROM HRMS_EMP_OFFC  ";

	    /*  query = query + " WHERE 1=1 AND EMP_STATUS='S'" +
	      				  " and not exists (select user_id from  CR_EMP_CLIENT_MAPP  where user_id=HRMS_EMP_OFFC.emp_id and user_type='I' and dashboard_id= "+this.bean.getDashBoardID()+"" +
	      				  		"and nvl(cr_emp_client_mapp.account_code,0)=" +accountID+
	      				  		")" ;
	      			*/
	      
	      query = query + " INNER JOIN (  SELECT EMP_ID  FROM HRMS_EMP_OFFC   WHERE 1=1 AND EMP_STATUS='S' "
	      +"MINUS SELECT USER_ID FROM  CR_EMP_CLIENT_MAPP WHERE USER_TYPE='I'  AND DASHBOARD_ID="+this.bean.getDashBoardID()+" "
	      +" AND NVL(CR_EMP_CLIENT_MAPP.ACCOUNT_CODE,0)=" +accountID+" ) A ON A.EMP_ID=HRMS_EMP_OFFC.EMP_ID " ;
	      
	      
	     if(this.bean.getAccountID()!=null && !this.bean.getAccountID().equals("") ){
	    	
	     }
	      
	      if ((this.bean.getUserProfileDivision() != null) && 
	        (this.bean.getUserProfileDivision().length() > 0)) {
	        query = query + "AND HRMS_EMP_OFFC.EMP_DIV IN (" + this.bean.getUserProfileDivision() + ")";
	      }

	      query = query + "ORDER BY HRMS_EMP_OFFC.EMP_ID ";
	      String[] headers = { "Employee Id", "Employee Name" };
	      String[] headerWidth = { "20", "80" };
	      String[] fieldNames = { "informToken", "informName", "informCode" };
	      int[] columnIndex = { 0, 1, 2 };
	      String submitFlag = "false";
	      String submitToMethod = "";
	      setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
	        "false", "");
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return "f9page";
	  }
	
	
	/**
	 * f9 for DahBoard External Employee  Search function
	 * @return String
	 * @throws Exception
	 */
	
	public String f9Account() throws Exception
	  {
	    try {
	    	System.out.println("dashBoard ID- "+this.bean.getDashBoardID());
	     String[] empCode = this.request.getParameterValues("keepInformToCode");
	      String code = this.bean.getUserEmpId();
	      if (empCode != null) {
	        for (int i = 0; i < empCode.length; i++) {
	          code = code + "," + empCode[i];
	        }
	      }
	      
	      
	      String accountID="";
	      if(this.bean.getAccountID()!=null&&!this.bean.getAccountID().equals("")){
		    	accountID=this.bean.getAccountID();
		    }
		    else{
		    	accountID="0";
		    }
	      
	      String query = " select  CRUSER_CODE,first_name||' '||last_name  FROM CR_CLIENT_USERS where IS_ACTIVE='Y' " +
	      		" and not exists (select user_id from  CR_EMP_CLIENT_MAPP  where user_id=CR_CLIENT_USERS.CRUSER_CODE  and dashboard_id="+this.bean.getDashBoardID()
	      		+ " and nvl(cr_emp_client_mapp.account_code,0)= " +accountID+") ";
	     // query = query + " WHERE 1=1 AND EMP_STATUS='S'" +
	      //				  " and not exists (select user_id from  CR_EMP_CLIENT_MAPP  where user_id=HRMS_EMP_OFFC.emp_id and user_type='I') ";
	      if ((this.bean.getUserProfileDivision() != null) && 
	        (this.bean.getUserProfileDivision().length() > 0)) {
	       // query = query + "AND EMP_DIV IN (" + this.bean.getUserProfileDivision() + ")";
	     }

	     // query = query + "ORDER BY EMP_ID ";
	      String[] headers = { "ClientUser No", "Client Name" };
	      String[] headerWidth = { "20", "80" };
	      String[] fieldNames = { "informToken", "informName" };
	      int[] columnIndex = { 0, 1};
	      String submitFlag = "false";
	      String submitToMethod = "";
	      setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
	  	        "false", "");
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return "f9page";
	  }
	
	/**
	 * f9 for DahBoard Manage Accounts  Search function
	 * @return String
	 * @throws Exception
	 */
	
	public String f9ManageAccount() throws Exception
	{
	    try {
	    	System.out.println("dashBoard ID- "+this.bean.getDashBoardID());
	     String[] empCode = this.request.getParameterValues("keepInformToCode");
	      String code = this.bean.getUserEmpId();
	      if (empCode != null) {
	        for (int i = 0; i < empCode.length; i++) {
	          code = code + "," + empCode[i];
	        }
	      }

	      String query = " select ccm.account_ID, ccm.account_name ,ccm.account_code " +
			      		" from CR_CLIENT_MASTER ccm " +
			      		" where not exists (select account_id from CR_DASHBOARD_ACCOUNT cad " +
			      		" where ccm.account_code=cad.account_code )";
	     // query = query + " WHERE 1=1 AND EMP_STATUS='S'" +
	      //				  " and not exists (select user_id from  CR_EMP_CLIENT_MAPP  where user_id=HRMS_EMP_OFFC.emp_id and user_type='I') ";
	      if ((this.bean.getUserProfileDivision() != null) && 
	        (this.bean.getUserProfileDivision().length() > 0)) {
	       // query = query + "AND EMP_DIV IN (" + this.bean.getUserProfileDivision() + ")";
	     }

	     // query = query + "ORDER BY EMP_ID ";
	      String[] headers = { "Account Code", "Account Name" };
	      String[] headerWidth = { "20", "80" };
	      String[] fieldNames = { "informToken", "informName","informCode" };
	      int[] columnIndex = { 0, 1 ,2};
	      String submitFlag = "false";
	      String submitToMethod = "";
	      /*setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
	    		  submitFlag,submitToMethod, "POOL_D1");*/
	      setF9Window(query, headers, headerWidth, fieldNames, columnIndex, 
	  	        "false", "");
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return "f9page";
	  
	}
	
	 
	/**
	 * f9 for applying same configuration for all other users
	 * @return
	 */
	public String f9users(){		
		String[] empCode = this.request.getParameterValues("keepInformToCode");
	      String code = this.bean.getUserEmpId();
	      if (empCode != null) {
	        for (int i = 0; i < empCode.length; i++) {
	          code = code + "," + empCode[i];
	        }
	      }
	    String accountID="";
	    if(this.bean.getAccountID()!=null&&!this.bean.getAccountID().equals("")){
	    	accountID=this.bean.getAccountID();
	    }
	    else{
	    	accountID="0";
	    }
	      
	    String AccountID=this.bean.getAccountID();
	    System.out.println("acc id    "+AccountID);
	    String query="";
	    if(String.valueOf(bean.getUserType()).equals("I")){
	    	query = "select distinct heo.emp_id ,heo.EMP_FNAME||' '||heo.EMP_MNAME||' '||heo.EMP_LNAME a,heo.EMP_TOKEN " +
	      		"  from hrms_emp_offc heo,CR_EMP_CLIENT_MAPP cecm  	" +
	      		"  where cecm.user_id=heo.emp_id  " +
	      		"  and cecm.user_type='"+this.bean.getUserType()+"'"+
	      		"  and cecm.dashboard_id="+this.bean.getDashBoardID()+
	      		" order by upper(a)";}
	      	//	"  and nvl(cecm.account_code,0)="+accountID;}
	    else if(String.valueOf(bean.getUserType()).equals("E")){
	    	query=" select  distinct CR_CLIENT_USERS.CRUSER_CODE,first_name||' '||last_name  a" +
	    		  " FROM CR_CLIENT_USERS,cr_emp_client_mapp " +
	    		  " where CR_CLIENT_USERS.IS_ACTIVE='Y' " +
	    		  " and cr_emp_client_mapp.user_id=CR_CLIENT_USERS.CRUSER_CODE " +
	    		  " and cr_emp_client_mapp.user_type='"+this.bean.getUserType()+"'"+
	    		  " and cr_emp_client_mapp.dashboard_id="+this.bean.getDashBoardID()+
	    		  " order by upper(a)";
	    		 // " and nvl(cr_emp_client_mapp.account_code,0)="+accountID;
	    }
	    
	 
		String header =getMessage("division");
		String textAreaId =request.getParameter("applyUserName");
				
		String hiddenFieldId =request.getParameter("applayUserCode");
		
		String submitFlag ="";
		String submitToMethod ="";
		
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return "f9multiSelect";
	}
	
	public String f9addReportToDashBoard(){
		String query="select report_id,report_name " +
				" from cr_report_master" +
				" where report_id not in(select rm.report_id " +
				" from cr_report_master rm, cr_dashboard_reports rd , cr_dashboard cd ,cr_user_report cur" +
				" where rm.report_id=rd.report_id " +
				" and cd.dashboard_id=rd.dashboard_id " +
				" and cur.dashboard_id=rd.dashboard_id  " +
				" and rd.report_id = cur.report_id " +
				" and rd.dashboard_id=" +this.bean.getDashBoardID()+
				" and cur.user_id="+this.bean.getUserEmpId()+ ")";
		
		
		
		
		return "";
		
	}
	
	public String f9crmEmployee() {

		try {
			String query = "SELECT EMP_TOKEN,NVL(EMP_FNAME,' ')||'  '||NVL(EMP_MNAME,' ')||'  '||NVL(EMP_LNAME,' '),EMP_ID "
					+ " FROM HRMS_EMP_OFFC "
					+ " LEFT JOIN HRMS_TITLE t1 ON(t1.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
					+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)    "
					+ "   WHERE   EMP_STATUS ='S' ";

			///query += " CONNECT BY PRIOR EMP_ID = EMP_REPORTING_OFFICER ";

			

			String[] headers = { "Employee Id", "Employee Name" };
			String[] headerwidth = { "20", "80" };

			String[] fieldNames = { "crmToken",
					"crmName", "crmCode" };
			int[] columnIndex = { 0, 1, 2 };
			String submitFlage = "true";
			String submitToMethod = "ManageClientDashBoard_applyFilter.action";
			setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
					submitFlage, submitToMethod);
		} catch (Exception e) {
			
		}

		return "f9page";

	}
	
	public String applyFilter()
	  {
	    try {
	    	ManageClientDashBoardModel model = new ManageClientDashBoardModel();
			model.initiate(this.context, this.session);

	      
	      String searchMessage = this.request.getParameter("searchMessage");
	      if(!(bean.getCrmCode().equals(""))){
				String searchCRMMessage = bean.getCrmCode();
				model.applyFilters(this.bean, this.request, searchMessage,searchCRMMessage);
			
			}else{
				model.applyFilters(this.bean, this.request, searchMessage,"");
			}
	    ///  model.applyFilters(this.bean, this.request, searchMessage);
	      this.bean.setHiddenSearchMessage(searchMessage);
	      getNavigationPanel(1);
	      model.terminate();
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	    return "dashBoardAccountList";
	  }
	
	
	/**
	 * this functio is used for adding employee for DashBoard
	 * @return string
	 */
	public String saveDashBoardEmployeeInfo(){
	ManageClientDashBoardModel model = new ManageClientDashBoardModel();
	model.initiate(this.context, this.session);
	String employeeID=request.getParameter("keepInformCode");
	String keepInformedName=request.getParameter("keepInformedName");
	String keepInformedID= request.getParameter("keepInformedID");
	String accountID=request.getParameter("maccountID");
	System.out.println("saveDashboardEmployeeInfo Account id is -" +accountID);
	
	model.saveEmployeeInfo(this.bean,employeeID,accountID);
	bean.setInformName("");
	bean.setInformCode("");
	bean.setInformToken("");
	model.terminate();
	
	return "internalUser";
	}
	
	/**
	 * this function is used for added account for dashBoard
	 * @return string 
	 */
	public String saveDashBoardAccountInfo(){
		ManageClientDashBoardModel model = new ManageClientDashBoardModel();
		model.initiate(this.context, this.session);
		String maccountName=request.getParameter("maccountName");
		String daccountID= request.getParameter("accountID");
		String maccountID=request.getParameter("maccountID");
		System.out.println("accountName - "+maccountName+" accountID - "+daccountID);
		model.saveAccountInfo(this.bean,daccountID,maccountID);
		bean.setInformName("");
		bean.setInformCode("");
		bean.setInformToken("");
		model.terminate();	
		return "externalUser";	
	}
	
	/**
	 * this function is used for added manage account for dashBoard
	 * @return string 
	 */
	public String saveDashBoardManageAccountInfo(){
		ManageClientDashBoardModel model = new ManageClientDashBoardModel();
		model.initiate(this.context, this.session);
		System.out.println("dashBoard Name - "+this.bean.getDashBoardName());
		String accountID= request.getParameter("accountID");
		//bean.setAccountID(accountID);
		//bean.setAccountName(accountName);		
	//	System.out.println("accountName - "+maccountName+" accountID - "+accountID);
		model.saveManageAccountInfo(this.bean,accountID,request);
		bean.setInformName("");
		bean.setInformCode("");
		bean.setInformToken("");
		model.terminate();	
		return "dashBoardAccountList";	
		
	}
	/**
	 * this function is used for remove employee from table 
	 * @return string 
	 */
	public String removeEmployee(){
		ManageClientDashBoardModel model = new ManageClientDashBoardModel();
		model.initiate(this.context, this.session);
		String EmployeeID=request.getParameter("informCode");
		System.out.println("Employee ID is - "+EmployeeID);
		model.removeEmployee(EmployeeID,this.bean);
		model.getEmployeeList(this.bean,EmployeeID);
		String dashBoardID =request.getParameter("dashBoardID");
		String informCode = request.getParameter("informCode");
		String userType = request.getParameter("userType");
		model.deleteFilter(bean, dashBoardID, informCode, userType, request);
		bean.setInformName("");
		model.terminate();	
		return "internalUser";
	}
	/**
	 * This Action is used for remove account from table 
	 * @return string 
	 */
	public String removeAccount(){
		ManageClientDashBoardModel model = new ManageClientDashBoardModel();
		model.initiate(this.context, this.session);
		String accountID=request.getParameter("informCode");
		System.out.println("AccountID is - "+accountID);
		model.removeAccount(accountID,this.bean);
		model.getAccountList(this.bean,accountID,request);
		String dashBoardID =request.getParameter("dashBoardID");
		String informCode = request.getParameter("informCode");
		String userType = request.getParameter("userType");
		model.deleteFilter(bean, dashBoardID, informCode, userType, request);
		bean.setInformName("");
		model.terminate();	
		return "externalUser";	
	}
	/**
	 * This Action is used for remove manage account from table 
	 * @return string 
	 */
	public String removeManageAccount(){
		ManageClientDashBoardModel model = new ManageClientDashBoardModel();
		model.initiate(this.context, this.session);
		String accountID=request.getParameter("informCode");
		System.out.println("AccountID is - "+accountID);
		model.removeManageAccount(accountID,this.bean);
		ArrayList list=model.getManageAccountList(this.bean.getDashBoardID(),this.bean,request,this.bean.getSearchMessagestr());
		this.bean.setKeepInformedList(list);
		model.terminate();	
		
		return "dashBoardAccountList";
	}
	
	

}
