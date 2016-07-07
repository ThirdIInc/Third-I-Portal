package org.struts.action.admin.srd;
import org.paradyne.bean.admin.srd.EmpLic;

import org.paradyne.model.admin.srd.EmpLicModel;

 import org.struts.lib.ParaActionSupport;
/**
 * 
 * @author pradeep k sahoo
 * Date:09-07-2007
 *
 */
/**
 * SELECT INV_CODE , INV_NAME FROM HRMS_TAX_INVESTMENT  ORDER BY INV_CODE
 */
public class EmpLicAction extends ParaActionSupport {
	
	
      EmpLic el;
      public EmpLic getEl() {
		return el;
	   }

	    public void setEl(EmpLic el) {
		this.el = el;
	    }

	
		 public Object getModel() {
			return el;
		}
		
		static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);public void prepare_local() throws Exception {
			el=new EmpLic();
			el.setMenuCode(242);
		}
		
		public String input(){
			
			return "success";
		}
		
		public String save()throws Exception{
			
			EmpLicModel model=new EmpLicModel();
			model.initiate(context,session);
			String str="";
			str = model.addLic(el,request);
			if(str.equals("add")){
				 addActionMessage("Record saved successfully.");
			}else{
				 addActionMessage("Record updated successfully.");
			}
			lifeCorpRecord();
			model.terminate();
			return "success";
			
		}
		
		public String reset() throws Exception{
			try{	
				    el.setEmpToken("");
					el.setEmpRank("");
					el.setEmpName("");
					el.setEmpCent("");
					el.setLicName("");
					el.setLicPolicyNo("");
					el.setInsuranceAmt("");
					el.setLicPremiumAmt("");
					el.setLicStDate("");
					el.setLicEndDt("");
					el.setTaxExempt("");
					el.setPaidInSal("");
					el.setDebitName("");
					el.setLicActive("");
					el.setLicId("");
					el.setDebitCode("");
					el.setDebitName("");
					el.setInvCode("");
					el.setInvName("");
					el.setFlag("false");
					el.setLicList(null);
					el.setLicIdHead("");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			} 
			return "success";
		}
		public String lifeCorpRecord()throws Exception{
			EmpLicModel model=new EmpLicModel();
			model.initiate(context,session);
			model.getLicRecord(el);
			el.setFlag("true");
			resetDetails();
			model.terminate();
			
			return SUCCESS;
		}
	         			
		
			public String lifeRecord()throws Exception{
				EmpLicModel model=new EmpLicModel();
				model.initiate(context,session);
				model.getRecord(el);
				model.terminate();
				
				return SUCCESS;
			}
			
			public String edit() throws Exception{
				EmpLicModel model=new EmpLicModel();
				model.initiate(context,session);
				model.getRecord(el);
				model.terminate();
			//	return lifeRecord();
				return lifeCorpRecord();
			}
			public String resetDetails(){
				el.setLicName("");
				el.setLicPolicyNo("");
				el.setInsuranceAmt("");
				el.setLicPremiumAmt("");
				el.setLicStDate("");
				el.setLicEndDt("");
				el.setTaxExempt("");
				el.setPaidInSal("");
				el.setInvCode("");
				el.setInvName("");
				el.setDebitCode("");
				el.setDebitName("");
				el.setInvCode("");
				el.setInvName("");
				el.setLicIdHead("");
				return SUCCESS;
			}
			/*public String delete() throws Exception{
				EmpLicModel model=new EmpLicModel();
				model.initiate(context,session);
				String[] licN=request.getParameterValues("hLicName");
				String[] policyNo=request.getParameterValues("hPolicyNo");
				String[] insAmt=request.getParameterValues("hInsAMt");
				String[] premAmt=request.getParameterValues("hPremAMt");
				String[] startDate=request.getParameterValues("hStartDate");
				String[] endDate=request.getParameterValues("hEndDate");
				String[] taxEx=request.getParameterValues("hTaxEx");
				String[] debitExmpt=request.getParameterValues("hDebitExempt");
				String[] debitCode=request.getParameterValues("hDebitCode");
				String[] debitName=request.getParameterValues("hDebitName");
				String[] debitExmptCode=request.getParameterValues("hDebitExemptCode");
				String[] licId=request.getParameterValues("licId");
			//	boolean result=model.delLicRecord(el);
				model.terminate();
			//	if (result){
					addActionMessage(getText("delMessage",""));
						el.setLicName("");
						el.setLicPolicyNo("");
						el.setInsuranceAmt("");
						el.setLicPremiumAmt("");
						el.setLicStDate("");
						el.setLicEndDt("");
						el.setTaxExempt("");
						el.setPaidInSal("");
						el.setInvCode("");
						el.setInvName("");
						el.setDebitCode("");
						el.setDebitName("");
						el.setInvCode("");
						el.setInvName("");
			model.delPolicy(el, licN, policyNo, insAmt, premAmt, startDate, endDate, taxEx, debitExmpt, debitExmptCode, debitCode, debitName, licId);			
			model.terminate();			
						
			//	}
			//	else{
					//addActionMessage("Lic can not be deleted");
			//	}
				//return lifeRecord();
			//	return lifeCorpRecord();
						return "success";
			}*/
			
			public String delete() throws Exception{
				EmpLicModel model=new EmpLicModel();
				model.initiate(context,session);
				boolean result=model.delLicRecord(el);
				model.getLicRecord(el);
			if (result){
					addActionMessage(getText("delMessage",""));
					resetDetails();
			}
			//model.delPolicy(el, licN, policyNo, insAmt, premAmt, startDate, endDate, taxEx, debitExmpt, debitExmptCode, debitCode, debitName, licId);			
			model.terminate();			
						
			//	}
			//	else{
					//addActionMessage("Lic can not be deleted");
			//	}
				//return lifeRecord();
			//	return lifeCorpRecord();
						return "success";
			}
			public void prepare_withLoginProfileDetails() throws Exception {
				logger.info("*****LOGIN PROFILE");
				EmpLicModel model = new EmpLicModel();
				model.initiate(context,session);
				if (el.isGeneralFlag()) {
					
					model.getEmployeeDetails(el.getUserEmpId(),el);
					model.getEmpLic(el.getUserEmpId(), el);
				
					
				}else{
					String offcEmp =(String)request.getSession().getAttribute("srdEmpCode");
					logger.info("Selected Employee record ****************"+offcEmp);
					model.getEmployeeDetails(offcEmp,el);
					model.getEmpLic(offcEmp, el);
				}
				model.terminate();
			}
			
			public String insertPolicyDet(){
				EmpLicModel model = new EmpLicModel();
				model.initiate(context, session);
				boolean result = false;
				result= model.insertPolicyDet(el);
				model.getLicRecord(el);
				if(result){
					addActionMessage(getMessage("save"));
					resetDetails();
				}
				resetDetails();
				model.terminate();
				return SUCCESS;
			}
			public String updatePolicyDet(){
				EmpLicModel model = new EmpLicModel();
				model.initiate(context, session);
				boolean result = false;
				result = model.updatePolicyDet(el);
				model.getLicRecord(el);
				if(result){
					addActionMessage(getMessage("update"));
					resetDetails();
				}
				model.terminate();
				return SUCCESS;
			}
			public String addPolicyDet(){
			//	el.setFlag("true");
				
				EmpLicModel model = new EmpLicModel();
				String[] licN=request.getParameterValues("hLicName");
				String[] policyNo=request.getParameterValues("hPolicyNo");
				String[] insAmt=request.getParameterValues("hInsAMt");
				String[] premAmt=request.getParameterValues("hPremAMt");
				String[] startDate=request.getParameterValues("hStartDate");
				String[] endDate=request.getParameterValues("hEndDate");
				String[] taxEx=request.getParameterValues("hTaxEx");
				String[] debitExmpt=request.getParameterValues("hDebitExempt");
				String[] debitCode=request.getParameterValues("hDebitCode");
				String[] debitName=request.getParameterValues("hDebitName");
				String[] debitExmptCode=request.getParameterValues("hDebitExemptCode");
				String[] licId=request.getParameterValues("licId");
				model.initiate(context,session);
				if(el.getParaId().equals("") || el.getParaId().equals("null")){
				   model.addPolicy(el, licN, policyNo, insAmt, premAmt, startDate, endDate, taxEx, debitExmpt, debitExmptCode, debitCode, debitName, licId);
				}else{
					model.modPolicy(el, licN, policyNo, insAmt, premAmt, startDate, endDate, taxEx, debitExmpt, debitExmptCode, debitCode, debitName, licId);
				}
				el.setLicName("");
				el.setLicPolicyNo("");
				el.setInsuranceAmt("");
				el.setLicPremiumAmt("");
				el.setLicStDate("");
				el.setLicEndDt("");
				el.setTaxExempt("");
				el.setPaidInSal("");
				el.setInvCode("");
				el.setInvName("");
				el.setDebitCode("");
				el.setDebitName("");
				el.setParaId("");
				el.setUpdatePlicyFlag("false");
				model.terminate();
				return "success";
				
			}
			
			public String flag(){
				el.setFlag("true");
				return "success";
			}
			
			public String f9licEmp() throws Exception {
				/**
				 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
				 */
				String query = "SELECT DISTINCT EMP_TOKEN,(EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME),(HRMS_CENTER.CENTER_NAME),HRMS_RANK.RANK_NAME,HRMS_EMP_OFFC.EMP_ID" 
								+" FROM HRMS_EMP_OFFC "
								+" INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
								+" INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
								+" LEFT JOIN HRMS_LIC ON(HRMS_LIC.EMP_ID=HRMS_EMP_OFFC.EMP_ID) ";
								query+=getprofileQuery(el);
								query+=" AND EMP_STATUS='S' ORDER BY EMP_ID "	;
										
							
				
				/**
				 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
				 */ 
				String[] headers={getMessage("employee.id"),getMessage("employee"),getMessage("branch"),getMessage("designation")};
				
				String[] headerWidth={"15","30","27","28"};
				
				/**
				 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
				 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
				 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
				 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
				 * */
				
				String[] fieldNames={"empToken","empName","empCent","empRank","empId"};
				
				/**
				 * 	 	SET THE COLUMN INDEX
				 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
				 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
				 * 			THEN THE COLUMN INDEX CAN BE {1,3}
				 * 		
				 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
				 * 
				 */ 
				int[] columnIndex={0,1,2,3,4};
				
				/**
				 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
				 * 
				 */
				String submitFlag = "true";
				
				/**		 
				 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
				 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
				 */
				
				String submitToMethod="EmpLic_lifeCorpRecord.action";
				
				/**
				 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
				 */
				setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
				
				return "f9page";
			}
			
			
			public String f9debaction() throws Exception {
				/**
				 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
				 */
				
				logger.info("In f9 action===========1");
				String query= "SELECT DEBIT_NAME,DEBIT_CODE FROM HRMS_DEBIT_HEAD order by debit_code";
					
					String[] headers={getMessage("debit.name")};
				String[] headerWidth={ "30"};
				logger.info("In f9 action===========2");
				String[] fieldNames={"el.debitName","el.debitCode"};
				
				int[] columnIndex={0,1};
				String submitFlag = "false";
				
				logger.info("In f9 action===========3");
				
				
				String submitToMethod="";
				
				logger.info("In f9 action===========4");
				
				setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
				
				return "f9page";
				
			}
			
			
			public String f9empaction() throws Exception {
				
				/**
				 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
				 */
				String query = "SELECT EMP_TOKEN,(EMP_FNAME||'  '||EMP_MNAME||'  '||EMP_LNAME),(HRMS_CENTER.CENTER_NAME),HRMS_RANK.RANK_NAME,EMP_ID" 
								+" FROM HRMS_EMP_OFFC "
								+" INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "
								+" INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) WHERE EMP_STATUS='S'";
								query+=getprofileQuery(el);
								query+=" ORDER BY EMP_ID "	;
										
							
				
				/**
				 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
				 */ 
				String[] headers={"Employee Id","Employee Name","Branch","Designation"};
				
				String[] headerWidth={"15","30","27","28"};
				
				/**
				 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
				 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
				 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
				 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
				 * */
				
				String[] fieldNames={"empToken","empName","empCent","empRank","empId"};
				
				/**
				 * 	 	SET THE COLUMN INDEX
				 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
				 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
				 * 			THEN THE COLUMN INDEX CAN BE {1,3}
				 * 		
				 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
				 * 
				 */ 
				int[] columnIndex={0,1,2,3,4};
				
				/**
				 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
				 * 
				 */
				String submitFlag = "true";
				
				/**		 
				 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
				 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
				 */
				String submitToMethod="EmpLic_flag.action";
				//String submitToMethod="EmpLic_lifeCorpRecord.action";
				
				/**
				 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
				 */
				setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
				
				return "f9page";
			}

		
			
			public String f9debitExempt() throws Exception {
				/**
				 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
				 */
				String query = "SELECT NVL(INV_NAME,' '),INV_CODE FROM HRMS_TAX_INVESTMENT  ORDER BY INV_CODE "	;
										
							
				
				/**
				 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
				 */ 
				String[] headers={getMessage("investSec")};
				
				String[] headerWidth={"35"};
				
				/**
				 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
				 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
				 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
				 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
				 * */
				
				String[] fieldNames={"invName","invCode"};
				
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
				String submitToMethod="";
				
				/**
				 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
				 */
				setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
				
				return "f9page";
			}
			
			
			public String f9debitHead() throws Exception {
				/**
				 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
				 */
				String query = "SELECT NVL(DEBIT_NAME,' '),DEBIT_CODE FROM HRMS_DEBIT_HEAD  ORDER BY DEBIT_CODE "	;
										
							
				
				/**
				 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
				 */ 
				String[] headers={"Debit Head"};
				
				String[] headerWidth={"35"};
				
				/**
				 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
				 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
				 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
				 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
				 * */
				
				String[] fieldNames={"debitName","debitCode"};
				
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
				String submitToMethod="";
				
				/**
				 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
				 */
				setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
				
				return "f9page";
			}
		

}
	

