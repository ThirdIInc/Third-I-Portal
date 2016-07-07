package org.struts.action.recruitment;

import java.util.HashMap;

import org.paradyne.bean.Recruitment.CandidateStatusReport;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.model.admin.srd.QualificationMisReportModel;
import org.paradyne.model.recruitment.CandidateStatusModel;
import org.paradyne.model.recruitment.ManpowerRequisitionAnalysisModel;
import org.paradyne.model.recruitment.OpenVacReportModel;
import org.struts.lib.ParaActionSupport;
public class CandidateStatusReportAction extends ParaActionSupport{
	
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(CandidateStatusReportAction.class);
	
	
	public CandidateStatusReportAction() {
		// TODO Auto-generated constructor stub
	}
	
	CandidateStatusReport candidatestatus;
	public void prepare_local() throws Exception {
		System.out.println("Preapre_local().......");
		// TODO Auto-generated method stub
		candidatestatus=new CandidateStatusReport();
		candidatestatus.setMenuCode(831);
		
	}
	
	public String input(){
		CandidateStatusModel  model = new CandidateStatusModel();
		model.initiate(context, session);
	String quer = "SELECT APPREP_CODE,APPREP_NAME FROM HRMS_REC_APPREP_FILTERS WHERE APPREP_USEREMPID="+candidatestatus.getUserEmpId();
	Object[][] iterator = model.getSqlModel().getSingleResult(quer);
	HashMap mp = new HashMap();
	for (int i = 0; i < iterator.length; i++) {
		mp.put(String.valueOf(iterator[i][0]),String.valueOf(iterator[i][1]));

	}
		candidatestatus.setHashMap(mp);
		getFilterDetails();
		model.terminate();
		
		return "success";
	}
	
	public void prepare_withLoginProfileDetails(){
		CandidateStatusModel  model = new CandidateStatusModel();
		model.initiate(context, session);
		model.callSavedLIst(candidatestatus);
		model.terminate();
	}
	
	/**
	 * following function is called to display the filter option,sorting options,column definitions and advanced filters
	 * when and record is being selected from the select saved report criteria 
	 * @return
	 */
	public String getFilterDetails(){
		CandidateStatusModel  model = new CandidateStatusModel();
		model.initiate(context, session);
		if(!(candidatestatus.getAppRepCode().equals("") || candidatestatus.getAppRepCode().equals("null"))){
				model.getFilterOptions(candidatestatus);//Displays filter options
				model.getReqsnDetails(candidatestatus);//Displays requisition details in the select requisition text area
				model.getSortingDet(candidatestatus);//Displays the sorting details
				model.getColumnDef(candidatestatus, request);//Displays the column definitions 
				model.getAdvanceDet(candidatestatus);//Displays the  advanced filter details
		}
		model.terminate();
		return "success";
	}
	
	public Object getModel() {
		// TODO Auto-generated method stub
		return candidatestatus;
	}
	
	
	/**
	 * following function is called to insert or update the records in the database
	 * when Save Settings button is clicked.
	 * @return
	 */
	public String saveSettings(){
		CandidateStatusModel  model = new CandidateStatusModel();
		model.initiate(context, session);
		
		if(candidatestatus.getAppRepCode().equals("") || candidatestatus.getAppRepCode().equals("null")){
			Object[][] obj=model.chkUser(candidatestatus);
			if(obj!=null && obj.length>0){
				addActionMessage("Setting name already exists.");
			}else{
					boolean result=model.saveRepSettings(candidatestatus);
					if(result){
						model.callSavedLIst(candidatestatus);
						Object[][] orderObj=model.chkUser(candidatestatus);
						candidatestatus.setSearchSetting(String.valueOf(orderObj[0][1]));
						addActionMessage("Record saved successfully.");
						
						
					}
				}	
		}else{
			     String str=(String)candidatestatus.getHashMap().get(candidatestatus.getSearchSetting());
			     if(str.equalsIgnoreCase(candidatestatus.getSaveSetting())){
			    	    model.updateSettings(candidatestatus);
				    	model.callSavedLIst(candidatestatus);
				    	Object[][] orderObj=model.chkUser(candidatestatus);
				    	candidatestatus.setSearchSetting(String.valueOf(orderObj[0][1]));
				       	addActionMessage("Record updated successfully.");
			     }else{

				    	Object[][] obj=model.chkUser(candidatestatus);
						if(obj!=null && obj.length>0){
							Object[][] orderObj=model.chkUser(candidatestatus);
							candidatestatus.setSearchSetting(String.valueOf(orderObj[0][1]));
							addActionMessage("Setting name already exists.");
							
						}else{
						
						boolean result=model.saveRepSettings(candidatestatus);
					
						if(result){
							model.callSavedLIst(candidatestatus);
							Object[][] orderObj=model.chkUser(candidatestatus);
							candidatestatus.setSearchSetting(String.valueOf(orderObj[0][1]));
							addActionMessage("Record saved successfully.");
							 }
					    	}
		        	     }
			    
		
		}
		 getFilterDetails();
		return "success";
	}
	
	/**
	 * following function is called to display the requisition details when select requisition 
	 * button is clicked. 
	 * @return
	 */
	public String displayReq(){ 
		CandidateStatusModel  model = new CandidateStatusModel();
		model.initiate(context, session);
		model.displayReq(candidatestatus);
		model.terminate();
		return "viewReq";
	}
	
	public String getSummary(){
		CandidateStatusModel  model = new CandidateStatusModel();
		model.initiate(context, session);
		model.displaySummaryDetails(candidatestatus,request);
		model.terminate();
		return "viewSummary";
	}
	
	/**
	 * following function is called to display the requisition details page wise
	 * @return
	 */
	public String showReqsnList(){
		CandidateStatusModel  model = new CandidateStatusModel();
		model.initiate(context, session);
		model.displayReqsInJsp(candidatestatus,request);
		model.displayCandidates(candidatestatus,request);
		model.terminate();
		return "viewInJsp";
	}
	
	/**
	 * following function is called to display the candidate details page wise for the corresponding requisition
	 * @return
	 */
 public String showCandidateList(){
		CandidateStatusModel  model = new CandidateStatusModel();
		model.initiate(context,session);
		model.displayCandidates(candidatestatus,request);
		model.displayReqsInJsp(candidatestatus,request);
		model.terminate();
		return "viewInJsp";
	 
 }
	
	/**
	 * @author AA0877
	 * This function allows user to select requisition.
	 * @return
	 */
	public String f9actionReqName()
	{
		
			/**
			 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
			 */
			String query = "SELECT PUB_REQS_CODE,REQS_NAME "							   
						   +"FROM HRMS_REC_REQS_HDR "							   						
						   +"INNER JOIN HRMS_REC_VACPUB_HDR ON (PUB_REQS_CODE =  REQS_CODE) " 
						 // +"INNER JOIN HRMS_REC_VACPUB_RECDTL ON (HRMS_REC_VACPUB_HDR.PUB_CODE =  HRMS_REC_VACPUB_RECDTL.PUB_CODE )"
						   +"WHERE REQS_APPROVAL_STATUS = 'A' AND PUB_STATUS = 'P' "
						   +"ORDER BY REQS_CODE";
			
			/**
			 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
			 */ 
			String [] headers = {getMessage("requisition.code"), getMessage("reqname")};
			
			/**
			 * 		SET THE WIDTH OF TABLE COLUMNS.	
			 */ 
			String [] headerWidth = {"50", "50"};
			
			/**
			 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
			 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
			 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
			 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
			 * */ 	
			String [] fieldNames = {"reqCode", "reqname"};
			
			/**
			 * 	 	SET THE COLUMN INDEX
			 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
			 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
			 * 			THEN THE COLUMN INDEX CAN BE {1,3}
			 * 		
			 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
			 * 
			 */ 
			int [] columnIndex = {0, 1};
			
			/**
			 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
			 * 
			 */
			String submitFlag = "false";
			
			/**		 
			 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
			 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
			 */
			String submitToMethod = "";
			
			
			/**
			 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
			 */
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
	
	}
	/**
	 * following function is called when Generate Report button is clicked.
	 * @return
	 */
	public String generateReport(){
		try{
			
			CandidateStatusModel  model = new CandidateStatusModel();
			model.initiate(context, session);
			String type=request.getParameter("reportType");
			if(!(type==null || type.equals("null") || type.equals(""))){
				candidatestatus.setFlag(type);
			}
			
			model.getReportForAll(candidatestatus,response);
			model.terminate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	/**
	 * @author AA0877
	 * This function allows user to select candidate 
	 * @return
	 * @throws Exception
	 */
	
	public String f9actionCandidate()throws Exception {
		//String query = 	"SELECT nvl(CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME,''),nvl(TO_CHAR(CAND_DOB,'dd-mm-yyyy'),' '),nvl(CAND_EXP_YEAR,0) ||' Year '||nvl(CAND_EXP_MONTH,0)||' Months'," +
		//				"nvl(TO_CHAR(CAND_POSTING_DATE, 'DD-MM-YYYY'),' '),nvl(CAND_SHORT_STATUS,' '),CAND_CODE FROM HRMS_REC_CAND_DATABANK ";
		String query = 	"SELECT nvl(CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME,''),nvl(CAND_EXP_YEAR,0) ||' Year '|| nvl(CAND_EXP_MONTH,0)||' Months'," +
						"nvl(TO_CHAR(CAND_POSTING_DATE, 'DD-MM-YYYY'),' '),CAND_CODE FROM HRMS_REC_CAND_DATABANK ";
					
		String []headers ={getMessage("candhead"),getMessage("exphead"),getMessage("posthead")};
		String []headerwidth={"40","15","25"};
		String []fieldNames={"candidateName","experience","postingDate","candidateCode"};
		int []columnIndex={0,1,2,3};
		String submitFlage ="false";
		String submitToMethod = "";
		setF9Window(query,headers,headerwidth,fieldNames,columnIndex,submitFlage,submitToMethod);
		return "f9page";
	}
	
	/**
	 * @author AA0877
	 * Added for clearing form 
	 * @return String
	 */
	public String reset(){
		candidatestatus.setFrmDate("");
		candidatestatus.setToDate("");
		candidatestatus.setCandidate("");
		candidatestatus.setCandidateCode("");
		candidatestatus.setCandidateName("");
		candidatestatus.setReqname("");
		candidatestatus.setReqCode("");
		candidatestatus.setRequisitionCode("");
		candidatestatus.setRankId("");
		candidatestatus.setRankName("");
		candidatestatus.setAdvIntRnd("");
		candidatestatus.setAdvAppntStat("");
		candidatestatus.setAdvScrn("");
		candidatestatus.setAdvOffStat("");
		candidatestatus.setIntRndVal("");
		candidatestatus.setSecondBy("");
		candidatestatus.setFirstBy("");
		candidatestatus.setSortBy("");
		candidatestatus.setAsc1("Y");
		candidatestatus.setAsce1("Y");
		candidatestatus.setAscc1("Y");
		candidatestatus.setPageFlag("false");
		candidatestatus.setRadioFlag("false");
		candidatestatus.setAdvAppntStat("");
		candidatestatus.setAdvScrn("");
		candidatestatus.setAdvIntRnd("");
		candidatestatus.setIntRndVal("");
		candidatestatus.setAdvOffStat("");
		candidatestatus.setSortBy("");
		candidatestatus.setFirstBy("");
		candidatestatus.setSecondBy("");
		candidatestatus.setSearchSetting("");
		candidatestatus.setReqsDateCombo("");
		candidatestatus.setSelectedReq("");
		candidatestatus.setSelectedReqName("");
		candidatestatus.setReqsStatus("");
		candidatestatus.setReportFlag("false");
		
		return "success";
	}
	
	
		
		
		
		/**
		 * @author AA0877
		 * Function will allow user to select Position
		 * @return
		 * @throws Exception
		 */
		public String f9actionPosition()throws Exception {

			String query = 	" SELECT DISTINCT HRMS_RANK.RANK_ID,HRMS_RANK.RANK_NAME FROM  HRMS_RANK,HRMS_REC_REQS_HDR"+
							" WHERE (HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION) ORDER BY HRMS_RANK.RANK_ID"; 
 
						
			String []headers ={getMessage("rankId"),getMessage("position")};
			String []headerwidth={"40","30"};
			String []fieldNames={"rankId","rankName"};
			int []columnIndex={0,1};
			String submitFlage ="false";
			String submitToMethod = "";
			setF9Window(query,headers,headerwidth,fieldNames,columnIndex,submitFlage,submitToMethod);
			return "f9page";
		}
		
		

		// Added by Tinshuk Banafar...Begin...
		/**
		 * THIS METHOD IS USED FOR GENERATING REPORT
		 * 
		 * @return String
		 * @throws Exception
		 */

		public final String getReport() throws Exception {
			CandidateStatusModel model = new CandidateStatusModel();
			model.initiate(context, session);
			String reportPath = "";
			model.getCandStausReport(candidatestatus, request, response, reportPath);
			model.terminate();
			return null;
		}
		/**
		 * THIS METHOD IS USED FOR GENERATING E-MAIL REPORT
		 * 
		 * @return String
		 * @throws Exception
		 */

		public final String mailReport() {
			try {
			    final CandidateStatusModel model = new CandidateStatusModel();
				model.initiate(context, session);
				String poolName = String.valueOf(session
						.getAttribute("session_pool"));
				if (!(poolName.equals("") || poolName == null)) {
					poolName = "\\" + poolName;
				}
				String reportPath = getText("data_path") + "\\Report\\Master"
						+ poolName + "\\";
				model.getCandStausReport(candidatestatus, request, response, reportPath);
				model.terminate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "mailReport";
		}
		// Added by Tinshuk Banafar...End...
		
	}

	

