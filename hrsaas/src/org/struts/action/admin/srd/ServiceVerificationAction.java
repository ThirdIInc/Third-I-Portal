/**
 * 
 */
package org.struts.action.admin.srd;

import org.paradyne.bean.admin.srd.ServiceVerification;

import org.paradyne.model.admin.srd.ServiceVerificationModel;
 import org.struts.lib.ParaActionSupport;

/**
 * @author lakkichand
 *
 */
public class ServiceVerificationAction extends ParaActionSupport{
	
	ServiceVerification servVerification;
	
	/**
	 *
	 * 
	 */
	public ServiceVerificationAction() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the servVerification
	 */
	public ServiceVerification getServVerification() {
		return servVerification;
	}

	/**
	 * @param servVerification the servVerification to set
	 */
	public void setServVerification(ServiceVerification servVerification) {
		this.servVerification = servVerification;
	}
	
	 public Object getModel() {
		return servVerification;
	}
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
		servVerification=new ServiceVerification();
		servVerification.setMenuCode(51);
	}
	
	public String save()throws Exception{
		
		ServiceVerificationModel model=new ServiceVerificationModel();
		model.initiate(context,session);
		boolean result;
		if(servVerification.getServiceID().equals("")){
			logger.info("in add mode");
			 result = model.addService(servVerification);
			 if(result) {
					addActionMessage(getText("addMessage",""));
				
				}
		}else{
		logger.info("in modify mode");
			 result = model.updateService(servVerification);
			 if(result) {
					addActionMessage(getText("modMessage",""));
			 }
		}
		 if(result) {
				
				servVerification.setServiceID("");
				servVerification.setFromDate("");
				servVerification.setToDate("");
				servVerification.setQualSerYear("");
				servVerification.setQualSerMonth("");
				servVerification.setQualSerDays("");
				servVerification.setRemark("");
				servVerification.setPay("");
				servVerification.setPayID("");
				servVerification.setPayScale("");
				servVerification.setAttestOfficer("");
				servVerification.setVerifOfficer("");
				servVerification.setParaID("");
				
				
			} else {
				addActionMessage("Service  can not be added");
			}
		logger.info("4");
		model.terminate();
		
		
		logger.info("5");
		
		
		return serviceRecord();
		
	}
	
	public String reset() throws Exception{
		try{	
			servVerification.setAttestOfficer("");
			servVerification.setCenter("");
			servVerification.setEmpCode("");
			servVerification.setEmpToken("");
			servVerification.setEmpName("");
			servVerification.setRank("");
			servVerification.setFromDate("");
			servVerification.setParaID("");
			servVerification.setQualSerDays("");
			servVerification.setQualSerMonth("");
			servVerification.setQualSerYear("");
			servVerification.setPay("");
			servVerification.setPayScale("");
			servVerification.setRemark("");
			servVerification.setServiceID("");
			servVerification.setToDate("");
			servVerification.setVerifOfficer("");
			servVerification.setServiceList(null);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		} 
		return "success";
	}
	
		public String serviceRecord()throws Exception{
			ServiceVerificationModel model=new ServiceVerificationModel();
			model.initiate(context,session);
			model.getEmployeeDetails(servVerification.getEmpCode(),servVerification);
			//model.getServiceRecord(servVerification);
			model.terminate();
			
			return SUCCESS;
		}
		
		public String edit() throws Exception{
			ServiceVerificationModel model=new ServiceVerificationModel();
			model.initiate(context,session);
			model.getRecord(servVerification);
			model.terminate();
			return serviceRecord();
		}
		
		public String delete() throws Exception{
			ServiceVerificationModel model=new ServiceVerificationModel();
			model.initiate(context,session);
			boolean result=model.delServiceRecord(servVerification);
			model.terminate();
			if (result){
				addActionMessage(getText("delMessage",""));
				servVerification.setServiceID("");
				servVerification.setFromDate("");
				servVerification.setToDate("");
				servVerification.setQualSerYear("");
				servVerification.setQualSerMonth("");
				servVerification.setQualSerDays("");
				servVerification.setPay("");
				servVerification.setPayScale("");
				servVerification.setRemark("");
				servVerification.setAttestOfficer("");
				servVerification.setVerifOfficer("");
				
			}
			else{
				addActionMessage("Service  can not be deleted");
			}
			return serviceRecord();
		}
		
		
		
		
		public void prepare_withLoginProfileDetails() throws Exception {
			logger.info("*****LOGIN PROFILE");
			ServiceVerificationModel model = new ServiceVerificationModel();
			model.initiate(context,session);
			if (servVerification.isGeneralFlag()) {
				
				model.getEmployeeDetails(servVerification.getUserEmpId(),servVerification);
				
				
			}else{
				String offcEmp =(String)request.getSession().getAttribute("srdEmpCode");
				logger.info("Selected Employee record ****************"+offcEmp);
				model.getEmployeeDetails(offcEmp,servVerification);
			}
			model.terminate();
		}
		
		
		
		
		
		public String f9empaction() throws Exception {
			/**
			 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
			 */
			String query =" SELECT EMP_TOKEN,TO_CHAR(EMP_FNAME||' '||EMP_MNAME||' '||'  '||EMP_LNAME),TO_CHAR(CENTER_NAME), "
				+" TO_CHAR(RANK_NAME),HRMS_EMP_OFFC.EMP_ID,NVL(PAYSC_NAME,'') ,CREDIT_AMT"							
				+" FROM HRMS_EMP_OFFC "
				+" INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)"
				+" INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
				+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
				+" LEFT JOIN HRMS_PAYSCALE ON(HRMS_PAYSCALE.PAYSC_ID=HRMS_SALARY_MISC.SAL_PAYSCALE)"
				+" LEFT JOIN HRMS_EMP_CREDIT ON(HRMS_EMP_CREDIT.EMP_ID=HRMS_EMP_OFFC.EMP_ID AND CREDIT_CODE=1) ";
			
			query += getprofileQuery(servVerification);
			query+=" ORDER BY EMP_ID  ";
			/**
			 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
			 */ 
			String[] headers={"Employee Id","Employee Name", "Branch","Designation"};
			
			String[] headerWidth={"10", "30","30","30"};
			
			/**
			 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
			 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
			 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
			 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
			 * */ 	
			String[] fieldNames={"servVerification.empToken","servVerification.empName","servVerification.center","servVerification.rank","servVerification.empCode","servVerification.pay"};
			//String[] fieldNames={"servVerification.empToken","servVerification.empName","servVerification.center","servVerification.rank","servVerification.empCode","servVerification.payScale","servVerification.pay"};
			
			/**
			 * 	 	SET THE COLUMN INDEX
			 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
			 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
			 * 			THEN THE COLUMN INDEX CAN BE {1,3}
			 * 		
			 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
			 * 
			 */ 
			int[] columnIndex={0,1,2,3,4,5};
			
			/**
			 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
			 * 
			 */
			String submitFlag = "true";
			
			/**		 
			 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
			 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
			 */
			String submitToMethod="ServiceVerification_serviceRecord.action";
			
			/**
			 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
			 */
			setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
			
			return "f9page";
		}
	
		
		public String f9PayScaleaction() throws Exception {
			/**
			 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
			 */
			String query = "SELECT PAYSC_ID	,PAYSC_NAME FROM HRMS_PAYSCALE ORDER BY PAYSC_ID";		
						
			
			/**
			 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
			 */ 
			String[] headers={"Pay Code","Pay Name"};
			
			String[] headerWidth={"10", "30"};
			
			/**
			 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
			 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
			 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
			 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
			 * */ 	
			
			String[] fieldNames={"servVerification.payID","servVerification.payScale"};
			
			/**
			 * 	 	SET THE COLUMN INDEX
			 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
			 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
			 * 			THEN THE COLUMN INDEX CAN BE {1,3}
			 * 		
			 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
			 * 
			 */ 
			int[] columnIndex={0,1};
			
			/**
			 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
			 * 
			 */
			String submitFlag = "false";
			
			/**		 
			 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
			 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
			 */
			String submitToMethod=" ";
			
			/**
			 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
			 */
			setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
			
			return "f9page";
		}
		

}
