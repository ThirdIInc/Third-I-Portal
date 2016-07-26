package org.struts.action.common;

import java.util.TreeMap;

import org.paradyne.bean.common.ChangePsswdBean;
import org.paradyne.lib.StringEncrypter.EncryptionException;
import org.paradyne.model.common.ChangePsswdModel;
import org.paradyne.model.common.UserModel;

@SuppressWarnings("serial")
public class ChangePasswordAction extends org.struts.lib.ParaActionSupport {
	ChangePsswdBean bean;

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void prepare_local() throws Exception {
		bean = new ChangePsswdBean();
		bean.setMenuCode(192);
	}

	public Object getModel() {

		return bean;
	}

	/**
	 * To set namer and id of employee
	 * 
	 * @return String
	 * @throws EncryptionException
	 */
	public String changePass() throws EncryptionException {
		ChangePsswdModel model = new ChangePsswdModel();
		model.initiate(context, session);
		model.changePsswd(bean);
		model.terminate();
		return "success";
	}
	
	
	
	public String saveAuthorizationPsswd() throws EncryptionException, Exception
	{
		try {
			ChangePsswdModel model = new ChangePsswdModel();
			model.initiate(context, session);
			UserModel userModel = new UserModel();
			/* sets employee id and name */
			userModel.initiate(context, session);
			String password = bean.getAuthorizationPassword();
			logger.info("New Password >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "
					+ bean.getAuthorizationPassword());
			String resultMsg = userModel.checkPasswordSettings(password);
			logger.info("resultMsg >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  "
					+ resultMsg);
			if (!resultMsg.equals("")) {
				addActionMessage(resultMsg);
				return SUCCESS;
			}// END if
			/* Password Re-usability */
			if (!model.checkReusePassword(bean, request)) {
				if ((Integer) request.getAttribute("reusePeriodicity") == 1)
					addActionMessage("Previous password cannot be reused. Please select different password!");
				else
					addActionMessage("Previous "
							+ request.getAttribute("reusePeriodicity")
									.toString()
							+ " password cannot be reused. Please Select different Password!");
			}// END if checkReusepassword
			else {
				// save password
				boolean res = model.saveAuthorizationPsswd(bean);
				// setting Login password
				//model.setLoginPassword(bean);
				if (res) {
					addActionMessage("Password saved successfully");
				} else {
					addActionMessage("Password can't saved ");
				}
			}// END else
			bean.setHiddenDivID("AP");
			bean.setAuthorizationPassword("");
			bean.setConfirmAuthorizationPassword("");
			model.terminate();
			userModel.terminate();
		} catch (Exception e) {
			logger.error("Exception in saveAuthorizationPsswd-------------"+e);
		}
		return "success";
	}

	/**
	 * Save or Change Password
	 * 
	 * @return
	 * @throws EncryptionException
	 * @throws Exception
	 */
	public String savePsswd() throws EncryptionException, Exception {
		logger.info(" the change password is==================");
		ChangePsswdModel model = new ChangePsswdModel();
		UserModel userModel = new UserModel();
		/* sets employee id and name */
		changePass();
		model.initiate(context, session);
		/*
		 * Checking password setting from UserModel initiating UserModel
		 */
		userModel.initiate(context, session);
		String password = bean.getNewpsswd1();
		logger.info("New Password >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "
				+ bean.getNewpsswd1());
		String resultMsg = userModel.checkPasswordSettings(password);
		logger.info("resultMsg >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  "
				+ resultMsg);
		if (!resultMsg.equals("")) {
			addActionMessage(resultMsg);
			return SUCCESS;
		}// END if

		/* Password Re-usability */
		if (!model.checkReusePassword(bean, request)) {
			if ((Integer) request.getAttribute("reusePeriodicity") == 1)
				addActionMessage("Previous password cannot be reused. Please select different password!");
			else
				addActionMessage("Previous "
						+ request.getAttribute("reusePeriodicity").toString()
						+ " password cannot be reused. Please Select different Password!");
		}// END if checkReusepassword
		else {
			// save password
			model.savePsswd(bean);
			// setting Login password
			model.setLoginPassword(bean);
			addActionMessage("Password changed successfully");
		}// END else
		bean.setHiddenDivID("HR");
		bean.setNewpsswd1("");
		bean.setNewpsswd2("");
		bean.setOldpsswd("");
		model.terminate();
		userModel.terminate();
		return "success";

	}
	
	public String saveImage()
	{
		ChangePsswdModel model = new ChangePsswdModel();
		model.initiate(context, session);
		boolean result= model.saveImage(bean);
		if(result)
		{
			addActionMessage("Record saved successfully");
			model.getImage(bean,request);
		}
		bean.setHiddenDivID("IM");
		bean.setHiddenRadio("");
		model.terminate();
		return SUCCESS;
	}
	
	public String saveQuestion()
	{
		ChangePsswdModel model = new ChangePsswdModel();
		model.initiate(context, session);
		boolean result= model.saveQuestion(bean);
		if(result)
		{
			addActionMessage("Record saved successfully");
		}
		bean.setHiddenDivID("EM");
		model.terminate();
		return SUCCESS;
	}
	public String resetView() throws Exception{
		bean.setAccountName("");
		bean.setUserName("");	
		bean.setUserPassword("");
		bean.setServerList("");
		bean.setChkFlag("false");
		setServerNames();
		return "configureEmail";
	}
	

	/**
	 * To save Email password
	 * 
	 * @return String
	 */
	public String saveMailPass() {
		logger.info("inside saveMailPass");
		boolean result = false;
		ChangePsswdModel model = new ChangePsswdModel();
		model.initiate(context, session);
		try {
			// call to model
			result = model.saveMailPassword(bean);
		} catch (Exception e) {
			logger.error("Exception Caught Mail Pss 1: " + e);
		}
		bean.setUserName("");
		if (result) {
			addActionMessage("Email address and password changed successfully");
		}// End if
		else {
			addActionMessage("Email address and password cannot be changed ");
		}// END else
		bean.setHiddenDivID("EM");
		try {
			// setting password and length on load
			prepare_withLoginProfileDetails();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception Caught Mail Pss 2: " + e);
		}

		model.terminate();
		return "success";
	}

	/**
	 * On Load setting Login password, password length, old password
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		logger.info("in side prepare_withLoginProfile");
		ChangePsswdModel model = new ChangePsswdModel();
		model.initiate(context, session);
		// call to model.getTableDetail method retrieve the table details
		model.setLoginPassword(bean);
		model.getPassword(bean);
		model.getPasswordLength(request);
		setQuestion();
		model.getImage(bean,request);
		model.setHomeTab(bean);
		model.terminate();
		callOnLoad();
	}
	

	
	public void setQuestion() {
		try {
			ChangePsswdModel model = new ChangePsswdModel();
			model.initiate(context, session);
			int cnt = 0;
			String questionQuery = "SELECT SECURITY_QUESCODE,SECURITY_QUESNAME FROM HRMS_SECURITY_QUESTIONS ORDER BY DBMS_RANDOM.VALUE ";
			Object questionObj[][] = model.getSqlModel().getSingleResult(
					questionQuery);
			TreeMap map_1 = new TreeMap();
			TreeMap map_2 = new TreeMap();
			TreeMap map_3 = new TreeMap();
			int length = questionObj.length / 3;
			if (questionObj != null && questionObj.length > 0 && !String.valueOf(questionObj).equals("null")) {
				for (int i = 0; i < questionObj.length; i++) {
					if (i < length) {
						map_1.put(String.valueOf(questionObj[i][0]), String
								.valueOf(questionObj[i][1]));
					} else if (cnt < length) {
						map_2.put(String.valueOf(questionObj[i][0]), String
								.valueOf(questionObj[i][1]));
						cnt++;
					} else {
						map_3.put(String.valueOf(questionObj[i][0]), String
								.valueOf(questionObj[i][1]));
					}
				}
				bean.setTmap(map_1);
				bean.setQmap(map_2);
				bean.setQuesmap(map_3);
				model.terminate();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public ChangePsswdBean getBean() {
		return bean;
	}

	public void setBean(ChangePsswdBean bean) {
		this.bean = bean;
	}
	
	public void saveHomeTab()
	{
		ChangePsswdModel model = new ChangePsswdModel();
		model.initiate(context, session);
		String homeCode=request.getParameter("homeCode"),loginCode=bean.getUserLoginCode();
		if(homeCode != null && !homeCode.equals("null"))
		{
			model.saveHomeTab(homeCode, loginCode);
			session.setAttribute("homeCode", homeCode);
		}
		model.terminate();
	}
	/**
	 * CONFIGURE EMAIL ACCOUNT
	 */
	public String callOnLoad() throws Exception{
		ChangePsswdModel model = new ChangePsswdModel();
		model.initiate(context, session);
		model.getAccountData(bean);
		model.terminate();		
		return SUCCESS;
	}
	
	public String callBackOnLoad() throws Exception{
		ChangePsswdModel model = new ChangePsswdModel();
		bean.setFlagForCongMail("shashi");
		model.initiate(context, session);
		model.getAccountData(bean);
		model.terminate();		
		return SUCCESS;
	}
	
	public String addAccount() throws Exception{		
		String query="SELECT NVL(EMP_TOKEN,' '),EMP_FNAME ||' '||EMP_MNAME||' '||EMP_LNAME AS NAME FROM HRMS_EMP_OFFC WHERE EMP_ID="+bean.getUserEmpId();		
		ChangePsswdModel model = new ChangePsswdModel();
		model.initiate(context, session);
		Object[][]data=model.getSqlModel().getSingleResult(query);
		bean.setEmpToken(String.valueOf(data[0][0]));
		bean.setEmpName(String.valueOf(data[0][1]));
		setServerNames();		
		return "configureEmail";
	}
	public void setServerNames() throws Exception{
		/**
		 * TO SET SERVER
		 */
		TreeMap serverMap = new TreeMap ();
		ChangePsswdModel model = new ChangePsswdModel();
		model.initiate(context, session);
		String serQuery="SELECT SERVER_CODE, NVL(DOMAIN_NAME,' ') FROM HRMS_EMAIL_SERVER WHERE (SERVER_RECORD_EMP IS NULL OR SERVER_RECORD_EMP="+bean.getUserEmpId()+")";
		Object[][] serverData=model.getSqlModel().getSingleResult(serQuery);
		if(serverData !=null && serverData.length>0){
			serverMap.put("", "SELECT");
			for (int i = 0; i < serverData.length; i++) {
				serverMap.put(String.valueOf(serverData[i][0]), String.valueOf(serverData[i][1]));
			}
			//serverMap.put("O", "OTHER");
		}		
		else{
			serverMap.put("NOT AVAILABLE", "NOT AVAILABLE");
			//serverMap.put("O", "OTHER");
		}
		bean.setServerMap(serverMap);	
	}
public String edit() throws Exception{				
		
	ChangePsswdModel model = new ChangePsswdModel();
		model.initiate(context, session);			
			boolean flag=model.editEmailAccount(bean);			
		System.out.println("UNDER EDIT");		
		setServerNames();
		return "configureEmail";
	}

public String delete() throws Exception{				
	ChangePsswdModel model = new ChangePsswdModel();
	model.initiate(context, session);				
	boolean flag=model.deletemailAccount(bean);
	if(flag){
		addActionMessage(getMessage("delete"));					
	}		
	callOnLoad();	
	return SUCCESS;	
}

public String save() throws Exception{				
	ChangePsswdModel model = new ChangePsswdModel();
	model.initiate(context, session);
	setServerNames();	
	boolean flag=false;
	boolean upFlag=false;
	if(bean.getHiddenCode().equals("")){
		 flag=model.saveEmailAccount(bean);
	}
	else{
		upFlag=model.updateEmailAccount(bean);
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
/**
 * GET SERVER DATA ONCHANGE OF SERVER
 * @return
 * @throws Exception
 */
public String setServerData() throws Exception{
	ChangePsswdModel model = new ChangePsswdModel();
	model.initiate(context, session);
	model.setServerData(bean);
	model.terminate();
	setServerNames();
	return "configureEmail";
}

}
