 package org.struts.action.recruitment;

import org.paradyne.bean.Recruitment.SchInterviewAnalysis;
import org.paradyne.model.recruitment.SchInterviewAnalysisModel;
import org.struts.lib.ParaActionSupport;

/**
 * @modified By @author Prajakta.Bhandare
 * @date 15 April 2013
 */
public class SchInterviewAnalysisAction extends ParaActionSupport {

	SchInterviewAnalysis bean;
		@Override
		public void prepare_local() throws Exception {
			// TODO Auto-generated method stub
			bean=new SchInterviewAnalysis();
			bean.setMenuCode(830);
			
		}

		public Object getModel() {
			// TODO Auto-generated method stub
			return bean;
		}
		
		
		public SchInterviewAnalysis getOpenvac() {
			return bean;
		}
 
		public void setSchInterviewAnalysis(SchInterviewAnalysis bean) {
			this.bean = bean;
		}

		/**
		 * @author AA0877
		 * Added for displaying open vacancies report 
		 * @return String 
		 */
		public String report() {  
			SchInterviewAnalysisModel model=new SchInterviewAnalysisModel();
			model.initiate(context, session);
			String[] colnames = {getMessage("serial.no"),getMessage("cand.name"),
					getMessage("emailIDLabel"),getMessage("ContactLabel"),
					getMessage("Interview.Round"),getMessage("schDateChkLabel"),getMessage("interviewDate"),getMessage("interviewVenueLabel"),
					getMessage("interviewerChkLabel"),getMessage("rec.name"),
					getMessage("schStatusChkLabel"),getMessage("intStatusChkLabel"),
					getMessage("reqs.code"),getMessage("reqDateLabel"),getMessage("BranchLabel"),
					getMessage("DepartmentLabel"),
					getMessage("dividionLabel"),getMessage("position"),getMessage("hiringMangerLabel"),
					getMessage("AppStatusLabel"),getMessage("reqStatusLabel")};
		  	model.getReport(request,response ,bean,colnames);
			model.terminate();
			return null;
		}
		 
		
		
		/**
		 * Display the report on screen
		 * @return
		 */
		public String save(){
			SchInterviewAnalysisModel model=new SchInterviewAnalysisModel();
			model.initiate(context, session);
			boolean chekDup=false;
			boolean result=false;
			if(bean.getSearchSetting().equals("B") || (! bean.getSettingName().trim().equals(bean.getSettingNameDup().trim()))){ 
				 Object[][] data  =model.checkDuplicate(bean);
				if(data!= null && data.length>0){ 
					chekDup =true;
					addActionMessage("Setting Name already exist.");
					// reset();
					bean.setSearchSetting("B"); 
					return "success"; 
				}else{
					chekDup =false;
				} 
				 result = model.callSave(bean);  
				if(result)
				{
					addActionMessage(getMessage("save"));
				}else{
					addActionMessage(getMessage("save.error"));
				}
			}else{
				
				 result = model.callUpdate(bean);  
				if(result)
				{
					addActionMessage(getMessage("update"));
				}else{
					addActionMessage(getMessage("update.error"));
				}
				
			}
			 Object[][] repData  = model.checkDuplicate(bean);
				if(repData!= null && repData.length>0){
					bean.setSearchSetting(String.valueOf(repData[0][1])); 
				}
			model.terminate(); 
			if(!chekDup){
			viewSavedRecord();
			}
			return "success"; 
		}
		
		
		/**
		 * Display the report on screen
		 * @return
		 */
		public String viewOnScreen(){
			bean.setScreenFlag("true");
			bean.setCandidateFlag("false");
			SchInterviewAnalysisModel model=new SchInterviewAnalysisModel();
			model.initiate(context, session);
			model.callViewScreen(bean,request);  
			model.terminate();
			return "viewOnScreen"; 
		}
		
		
		
		public String viewCandidate(){
			bean.setCandidateFlag("true");
			bean.setScreenFlag("false");
			SchInterviewAnalysisModel model=new SchInterviewAnalysisModel();
			model.initiate(context, session);
			model.callCandidateQuery(bean,request);  
			model.terminate();
			return "viewOnScreen"; 
		}
		
		public String callBack(){
			SchInterviewAnalysisModel model=new SchInterviewAnalysisModel();
			model.initiate(context, session);
			model.callBack(bean);
			model.callPreviousRecord(bean); 
			model.terminate();
			return "success"; 
		} 
		
		public String input(){
			SchInterviewAnalysisModel model=new SchInterviewAnalysisModel();
			model.initiate(context, session);
			model.input(bean);
			model.callPreviousRecord(bean);
			model.terminate();
			return "success"; 
		}
		/**
		 * @author AA0877
		 * Added for clearing form 
		 * @return String
		 */
		public String reset(){
			bean.setFrmDate("");
			bean.setToDate("");
			bean.setIntFrmDate("");
			bean.setIntToDate("");
			bean.setRecruiter(""); 
			bean.setReqname("");
			bean.setReqCode("");
			bean.setReportType("");
			bean.setReportType("1");   
			bean.setReqname1(""); 
			bean.setSelectedReq("");
			bean.setPosition("");
			bean.setPositionId("");
			bean.setDateFilter("1");  
			bean.setIntDateFilter("1");
			bean.setMyPage("");
			bean.setShow("");
			bean.setDataLength("");
			bean.setSelectedReqName("");
			bean.setSelectedReqFlag("");
			bean.setEditReqFlag("false"); 
			bean.setSettingName(""); 
			bean.setSortBy("");
			bean.setThenBy1("");
			bean.setThenBy2(""); 
			bean.setReqStatus("V"); 
			bean.setHidReportView("checked");
			bean.setHidReportRadio("");   
			bean.setSortByAsc("checked");
			bean.setSortByDsc("");  
			bean.setThenByOrder1Asc("checked");
			bean.setThenByOrder1Dsc("");  
			bean.setThenByOrder2Asc("checked");
			bean.setThenByOrder2Dsc("");  
			bean.setSearchSetting("B");    
			bean.setRadioStatus(""); 
			bean.setRadioReq("");
			bean.setRadioRecr("");  
			bean.setRadioHiringMgr("");
			bean.setRadioPosition("");   
			bean.setSchStatusCom("1");
            bean.setInterviewerCode("");
            bean.setInterviewerName("");
			bean.setCandidateCode("");
			bean.setCandidateName("");
			bean.setHiringMgrCode("");
			bean.setHiringMgrName("");
			
			SchInterviewAnalysisModel model=new SchInterviewAnalysisModel();
			model.initiate(context, session);
			model.input(bean);
		 	model.callPreviousRecord(bean);
			model.terminate();
			return "success";
		}
		
		public String displayReq(){ 
			SchInterviewAnalysisModel model=new SchInterviewAnalysisModel();
			model.initiate(context, session);
			model.displayReq(bean);
			model.terminate();
			return "viewInterviewReq";
		}
		
		public String viewSummary(){ 
			SchInterviewAnalysisModel model=new SchInterviewAnalysisModel();
			model.initiate(context, session);
			model.viewSummary(bean);
			model.terminate();
			return "viewSummary";
		}
		
		public String viewSavedRecord(){
			SchInterviewAnalysisModel model=new SchInterviewAnalysisModel();
			model.initiate(context, session);
			model.callFilterSavedData(bean); 
			model.callSortSavedData(bean); 
			model.callColumnSavedData(bean);
			model.callAdvanceSavedData(bean); 
			model.callPreviousRecord(bean);
			model.terminate();
			return "success"; 
		}
		
		/**
		 * @author AA0877
		 * Added For getting all Requisitions from Db
		 * @return String
		 */
		public String f9actionReqName()
		{
			 
		String query = "  SELECT NVL(REQS_NAME,' '),RANK_NAME,TO_CHAR(REQS_DATE ,'DD-MM-YYYY') AS REQ_DATE," +
						" DECODE(REQS_APPROVAL_STATUS,'P','Pending','R','Rejected','A' ,'Approved','H','On Hold','B','New Requisition','Q','Quick Requisition') AS APPR_STATUS,REQS_CODE "
						+"  FROM HRMS_REC_REQS_HDR  INNER JOIN  HRMS_RANK ON (RANK_ID =REQS_POSITION) "
						+" WHERE REQS_APPROVAL_STATUS IN ('A','Q') ORDER BY  REQS_DATE DESC";
				 
				String [] headers = {getMessage("reqs.code"),getMessage("position"),getMessage("reqs.date"),"Status"}; 
				 
				String [] headerWidth = {"35","35","15", "15"}; 
				 
				String [] fieldNames = {"reqname","common","common","common","reqCode"};
				 
				int [] columnIndex = {0,1,2,3,4}; 
				
				String submitFlag = "false"; 
				 
				String submitToMethod = "";
				 
				setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
				
				return "f9page";
		
		}
		
		
		public String f9Interviewer()
		{
			try
			{
				
			  String query = " SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , EMP_ID "
				  			+" FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S'   ORDER BY  EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME	";
				 
			        
				String[] headers = {getMessage("interviewerCodeLabel"), getMessage("interviewerChkLabel")};

				String[] headerWidth = {"20", "80"};

				String[] fieldNames = {"common","interviewerName" ,"interviewerCode"};

				int[] columnIndex = {0, 1,2};   

				String submitFlag = "false";

				String submitToMethod = "";

				setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
				
				return "f9page";
			}catch (Exception e) {
			//	logger.error(e.getMessage());
				return null;
			}
		} // end of f9Employee
		
		 
		
		
		/**
		 * @author AA0877
		 * Added for getting all recruiter names from db
		 * @return String
		 */
		public String f9Candidate()
		{  
			String query = "SELECT NVL(CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME,''),CAND_CODE FROM HRMS_REC_CAND_DATABANK ORDER BY UPPER(CAND_FIRST_NAME) ";
				 
				String [] headers = {getMessage("cand.name")};
				 
				String [] headerWidth = {"100"};
				 
				String [] fieldNames = {"candidateName" , "candidateCode"};
				 
				int [] columnIndex = {0,1};
				 
				String submitFlag = "false";
				 
				String submitToMethod = ""; 
				 
				setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
				
				return "f9page";
		
		} 
	 
 
		public String f9HiringManger()
		{  
				String query = "SELECT EMP_TOKEN,(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) as name, EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S'  ORDER BY upper(name)";
				 
				String [] headers = {getMessage("hiring.mgrId"), getMessage("hiring.mgr")};
				 
				String [] headerWidth = {"50", "50"};
				 
				String [] fieldNames = {"common", "hiringMgrName","hiringMgrCode"};
				 
				int [] columnIndex = {0, 1,2};
				 
				String submitFlag = "false";
				 
				String submitToMethod = ""; 
				 
				setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
				
				return "f9page";
		
		}
		
		
		public String f9Position()
		{  
				String query = " SELECT RANK_NAME , RANK_ID FROM HRMS_RANK   ORDER BY UPPER(RANK_NAME)";
				 
				String [] headers = {getMessage("position")};
				 
				String [] headerWidth = {"100"};
				 
				String [] fieldNames = {"position","positionId"};
				 
				int [] columnIndex = {0, 1};
				 
				String submitFlag = "false";
				 
				String submitToMethod = ""; 
				 
				setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
				
				return "f9page";
		
		}
		
		
}
