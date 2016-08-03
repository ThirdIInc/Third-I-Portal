package org.struts.action.PAS;

import org.paradyne.bean.PAS.ApprFormGeneralInfo;
import org.paradyne.model.PAS.ApprFormGeneralInfoModel;

import org.struts.lib.ParaActionSupport;

public class ApprFormGeneralInfoAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(ApprFormGeneralInfoAction.class);
	
	ApprFormGeneralInfo apprFormGeneralInfo;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		apprFormGeneralInfo = new ApprFormGeneralInfo();
		apprFormGeneralInfo.setMenuCode(759);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return apprFormGeneralInfo;
	}

	public ApprFormGeneralInfo getApprFormGeneralInfo() {
		return apprFormGeneralInfo;
	}

	public void setApprFormGeneralInfo(ApprFormGeneralInfo apprFormGeneralInfo) {
		this.apprFormGeneralInfo = apprFormGeneralInfo;
	}
	
	public String input(){
		String forwardStatus =""+(String)request.getAttribute("forwardStatus");
		try {
			if(forwardStatus.equals("true")){
				addActionMessage(getMessage("appraisal.form.head")+" forwarded successfully");
			}else if(forwardStatus.equals("false")){
				System.out.println("1<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<manager phase>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>1 ");
				addActionMessage("Error while forwarding the "+getMessage("appraisal.form.head"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getNavigationPanel(1);
		apprFormGeneralInfo.setApprId("");
		apprFormGeneralInfo.setTemplateCode("");
		apprFormGeneralInfo.setApprCode("");
		apprFormGeneralInfo.setApprStartDate("");
		apprFormGeneralInfo.setApprEndDate("");
		apprFormGeneralInfo.setPhaseCode("");
		apprFormGeneralInfo.setAppraiserPhaseName("");
		apprFormGeneralInfo.setPhaseStartDate("");
		apprFormGeneralInfo.setPhaseEndDate("");
		apprFormGeneralInfo.setEmpCode("");
		apprFormGeneralInfo.setEmpId("");
		apprFormGeneralInfo.setEmpName("");
		apprFormGeneralInfo.setEmpDesgName("");
		apprFormGeneralInfo.setPhaseName("");
		/*logger.info("apprFormGeneralInfo.getRetrieveFlag()"+apprFormGeneralInfo.getRetrieveFlag());
		if(apprFormGeneralInfo.getRetrieveFlag().equals("true")){
			retrieveDetails();
		}*/
					
		return SUCCESS;
	}
	
	public String searchAppraisal() {

		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT  DISTINCT APPR_CAL_CODE, TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),PAS_APPR_PHASE_SCHEDULE.APPR_ID, "
				+ " TO_CHAR(APPR_CAL_VALID_DATE,'DD-MM-YYYY')  FROM PAS_APPR_PHASE_SCHEDULE "
				+ " LEFT JOIN PAS_APPR_CALENDAR  ON (PAS_APPR_PHASE_SCHEDULE.APPR_ID = PAS_APPR_CALENDAR.APPR_ID) "
				+" INNER JOIN PAS_APPR_ELIGIBLE_EMP ON  (PAS_APPR_CALENDAR.APPR_ID = PAS_APPR_ELIGIBLE_EMP.APPR_ID AND APPR_EMP_ID = "+apprFormGeneralInfo.getUserEmpId()+")"
				+ " ORDER BY PAS_APPR_PHASE_SCHEDULE.APPR_ID ";

		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */
		String[] headers = { "Appraisal Code", "Start Date", "End Date" };

		/**
		 * 		SET THE WIDTH OF TABLE COLUMNS.	
		 */
		String[] headerWidth = { "20", "30", "30" };

		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */
		String[] fieldNames = { "apprCode", "apprStartDate", "apprEndDate", "apprId", "apprValidTillDate" };

		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4 };

		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "ApprFormGeneralInfo_retrieveDetails.action";

		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String retrieveDetails(){
			
		
		ApprFormGeneralInfoModel model = new ApprFormGeneralInfoModel();
		model.initiate(context, session);
		
				
		if(apprFormGeneralInfo.getDetailFLag().equals("false"))
			apprFormGeneralInfo.setEmpId(apprFormGeneralInfo.getUserEmpId());
		else	
			apprFormGeneralInfo.setEmpId(apprFormGeneralInfo.getEmpId());
			
		//logger.info("in first phase of appraisal ");
		//logger.info("in first phase of appraisal .........empcode"+apprFormGeneralInfo.getEmpId());
		//logger.info("in first phase of appraisal .........apprcode"+apprFormGeneralInfo.getApprId());
		model.getPhases(apprFormGeneralInfo);
		//logger.info("in first phase of appraisal .........phasecode"+apprFormGeneralInfo.getPhaseCode());
		//logger.info("in first phase of appraisal .........phasecode1"+apprFormGeneralInfo.getPhaseCode());
		model.getEmployeeDetails(apprFormGeneralInfo);
		model.getAppraiserDetails(apprFormGeneralInfo);
		model.getNextPhaseVisibility(apprFormGeneralInfo);
		model.getRating(apprFormGeneralInfo);
		model.terminate();
		//logger.info("in first phase of appraisal .........rating2"+apprFormGeneralInfo.getRatingDefined());
		getNavigationPanel(1);
		
		return SUCCESS;
		
	}
	
	public String retrieveAppraisalDetails(){
		
		logger.info("########## IN RETREIVE APPRAISAL DETAILS ##################");
		getNavigationPanel(1);
		ApprFormGeneralInfoModel model = new ApprFormGeneralInfoModel();
		model.initiate(context, session);
		
		apprFormGeneralInfo.setDetailFLag("true");
		
		String source=request.getParameter("src");
		apprFormGeneralInfo.setSource(source);
		
		String apprId = request.getParameter("apprId");
		String empId = request.getParameter("empId");
		String phaseId = request.getParameter("phaseCode");
		
		
		apprFormGeneralInfo.setEmpId(empId);
		apprFormGeneralInfo.setApprId(apprId);
		apprFormGeneralInfo.setPhaseCode(phaseId);
		
		model.getAppraisalData(apprFormGeneralInfo);
		
		logger.info("in next phase of appraisal ");
		logger.info("in next phase of appraisal .........empcode"+apprFormGeneralInfo.getEmpId());
		logger.info("in next phase of appraisal .........apprcode"+apprFormGeneralInfo.getApprId());
		logger.info("in next phase of appraisal .........phasecode"+apprFormGeneralInfo.getPhaseCode());
		
		model.getPhases(apprFormGeneralInfo);
		model.getEmployeeDetails(apprFormGeneralInfo);
		model.getAppraiserDetails(apprFormGeneralInfo);
		model.getNextPhaseVisibility(apprFormGeneralInfo);
		model.getRating(apprFormGeneralInfo);
		model.terminate();
		return SUCCESS;
		
		
	}
	
	public String sendBackToApplicant(){
		
		//logger.info("in sendBackToApplicant appraisal ");
		//logger.info("in sendBackToApplicant appraisal .........empcode"+apprFormGeneralInfo.getEmpId());
		//logger.info("in sendBackToApplicant appraisal .........templateCode"+apprFormGeneralInfo.getTemplateCode());
		//logger.info("in sendBackToApplicant appraisal .........apprcode"+apprFormGeneralInfo.getApprId());
		//logger.info("in sendBackToApplicant appraisal .........phasecode"+apprFormGeneralInfo.getPhaseCode());
		
		getNavigationPanel(1);
		
		String apprCode= apprFormGeneralInfo.getEmpId();
		String templateCode=apprFormGeneralInfo.getTemplateCode();
		String phaseCode=apprFormGeneralInfo.getPhaseCode();
		String empCode=apprFormGeneralInfo.getApprId();
		String appraiserCode=apprFormGeneralInfo.getUserEmpId();
		ApprFormGeneralInfoModel model = new ApprFormGeneralInfoModel();
		model.initiate(context, session);
			
		model.sendBackAppraisal(apprCode,templateCode,phaseCode,empCode,appraiserCode);
		model.terminate();
		
		return SUCCESS;
	}
	
	public String getApprStartDetails(){
		
		
		String source = request.getParameter("src");

		//String source =(String) request.getAttribute("src");

		System.out.println("source--------------" + source);
		apprFormGeneralInfo.setSource(source);
		getNavigationPanel(1);
		//logger.info("in getApprStartDetails action------------");
		
		ApprFormGeneralInfoModel model = new ApprFormGeneralInfoModel();
		model.initiate(context, session);
		//logger.info("in getApprStartDetails action------------"+apprFormGeneralInfo.getApprId());
		//logger.info("in getApprStartDetails action------------"+apprFormGeneralInfo.getUserEmpId());
		model.getApprStart(apprFormGeneralInfo);
		retrieveDetails();
			
		return SUCCESS;
	}
	
public String retrieveAppraisalDetailsDashlet(){
		
		getNavigationPanel(1);
		ApprFormGeneralInfoModel model = new ApprFormGeneralInfoModel();
		model.initiate(context, session);
		
		String apprId = request.getParameter("apprId");
		String empId = request.getParameter("empId");
		String phaseId = request.getParameter("phaseCode");
		
		//logger.info("apprId============ "+apprId);
		//logger.info("empId============ "+empId);
		//logger.info("phaseId============ "+phaseId);
		
		apprFormGeneralInfo.setEmpId(empId);
		apprFormGeneralInfo.setApprId(apprId);
		apprFormGeneralInfo.setPhaseCode(phaseId);
		
		apprFormGeneralInfo.setDetailFLag("true");
		
		
		model.getAppraisalData(apprFormGeneralInfo);
		
		//logger.info("in next phase of appraisal ");
		//logger.info("in next phase of appraisal .........empcode"+apprFormGeneralInfo.getEmpId());
		//logger.info("in next phase of appraisal .........apprcode"+apprFormGeneralInfo.getApprId());
		//logger.info("in next phase of appraisal .........phasecode"+apprFormGeneralInfo.getPhaseCode());
		
		model.getPhases(apprFormGeneralInfo);
		model.getEmployeeDetails(apprFormGeneralInfo);
		model.getAppraiserDetails(apprFormGeneralInfo);
		model.getNextPhaseVisibility(apprFormGeneralInfo);
		model.getRating(apprFormGeneralInfo);
		model.terminate();
		return SUCCESS;
		
		
	}

}
