package org.struts.action.email;

import java.util.HashMap;
import java.util.TreeMap;

import org.paradyne.bean.email.CongEmailAccount;
import org.paradyne.model.email.CongEmailAccountModel;
import org.struts.lib.ParaActionSupport;

public class CongEmailAccountAction extends ParaActionSupport {


	CongEmailAccount congEmailAcc;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		congEmailAcc=new CongEmailAccount();
		congEmailAcc.setMenuCode(876);
	}

	public String callOnLoad() throws Exception{
		CongEmailAccountModel model =new CongEmailAccountModel();
		model.initiate(context, session);
		String accountsAdded=model.getAccountData(congEmailAcc);
		if(accountsAdded.equals("accountsAdded")){
			request.setAttribute("accountsAdded", accountsAdded);
		}
		model.terminate();		
		return SUCCESS;
	}
	
	
	public void prepare_withLoginProfileDetails() throws Exception {
		callOnLoad();
	}
	
	public String addAccount() throws Exception{		
		String query="SELECT NVL(EMP_TOKEN,' '),EMP_FNAME ||' '||EMP_MNAME||' '||EMP_LNAME AS NAME FROM HRMS_EMP_OFFC WHERE EMP_ID="+congEmailAcc.getUserEmpId();		
		CongEmailAccountModel model =new CongEmailAccountModel();
		model.initiate(context, session);
		Object[][]data=model.getSqlModel().getSingleResult(query);
		congEmailAcc.setEmpToken(String.valueOf(data[0][0]));
		congEmailAcc.setEmpName(String.valueOf(data[0][1]));
		setServerNames();		
		return "configureEmail";
	}
	public String save() throws Exception{				
		CongEmailAccountModel model =new CongEmailAccountModel();
		model.initiate(context, session);
		setServerNames();	
		boolean flag=false;
		boolean upFlag=false;
		if(congEmailAcc.getHiddenCode().equals("")){
			 flag=model.saveEmailAccount(congEmailAcc);
		}
		else{
			upFlag=model.updateEmailAccount(congEmailAcc);
		}
		
		if(flag){
			addActionMessage(getMessage("save"));
			callOnLoad();
			return SUCCESS;
		}
		else if(upFlag){
			addActionMessage(getMessage("update"));
			callOnLoad();
			return SUCCESS;
		}
		else{
			addActionMessage(getMessage("save.error"));
			return "configureEmail";
		}		
	}
	
	public String delete() throws Exception{				
		CongEmailAccountModel model =new CongEmailAccountModel();
		model.initiate(context, session);				
		boolean flag=model.deletemailAccount(congEmailAcc);
		if(flag){
			addActionMessage(getMessage("delete"));					
		}		
		callOnLoad();	
		return SUCCESS;	
	}
	
	public String edit() throws Exception{				
		
		CongEmailAccountModel model =new CongEmailAccountModel();
		model.initiate(context, session);			
			boolean flag=model.editEmailAccount(congEmailAcc);			
		System.out.println("UNDER EDIT");		
		setServerNames();
		return "configureEmail";
	}
public String login() throws Exception{				
		
		CongEmailAccountModel model =new CongEmailAccountModel();
		model.initiate(context, session);			
		model.loginEmail(congEmailAcc,request, response);			
		model.terminate();
		return "loginEmail";
	}
	
	
	
	public String resetView() throws Exception{
		congEmailAcc.setAccountName("");
		congEmailAcc.setUserName("");	
		congEmailAcc.setUserPassword("");
		congEmailAcc.setServerList("");
		congEmailAcc.setChkFlag("false");
		setServerNames();
		return "configureEmail";
	}
	
	
	public void setServerNames() throws Exception{
		/**
		 * TO SET SERVER
		 */
		TreeMap serverMap = new TreeMap ();
		CongEmailAccountModel model =new CongEmailAccountModel();
		model.initiate(context, session);
		String serQuery="SELECT SERVER_CODE, NVL(DOMAIN_NAME,' ') FROM HRMS_EMAIL_SERVER WHERE (SERVER_RECORD_EMP IS NULL OR SERVER_RECORD_EMP="+congEmailAcc.getUserEmpId()+")";
		Object[][] serverData=model.getSqlModel().getSingleResult(serQuery);
		if(serverData !=null && serverData.length>0){
			serverMap.put("", "SELECT");
			for (int i = 0; i < serverData.length; i++) {
				serverMap.put(String.valueOf(serverData[i][0]), String.valueOf(serverData[i][1]));
			}
			serverMap.put("O", "OTHER");
		}		
		else{
			serverMap.put("NOT AVAILABLE", "NOT AVAILABLE");
			serverMap.put("O", "OTHER");
		}
		congEmailAcc.setServerMap(serverMap);	
	}
	
	/**
	 * GET SERVER DATA ONCHANGE OF SERVER
	 * @return
	 * @throws Exception
	 */
	public String setServerData() throws Exception{
		CongEmailAccountModel model =new CongEmailAccountModel();
		model.initiate(context, session);
		model.setServerData(congEmailAcc);
		model.terminate();
		setServerNames();
		return "configureEmail";
	}
	

	
	
	public Object getModel() {
		// TODO Auto-generated method stub
		return congEmailAcc;
	}

	public CongEmailAccount getCongEmailAcc() {
		return congEmailAcc;
	}

	public void setCongEmailAcc(CongEmailAccount congEmailAcc) {
		this.congEmailAcc = congEmailAcc;
	}
	
	public void getMyPassword() throws Exception{				
		String hiddenCode=request.getParameter("hiddenCode");
		CongEmailAccountModel model =new CongEmailAccountModel();
		model.initiate(context, session);			
		String myPassword=model.getMyPassword(hiddenCode);	
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("<message>"+myPassword+"</message>");	
	}

}
