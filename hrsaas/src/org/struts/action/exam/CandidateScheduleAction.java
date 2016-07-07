/**
 * 
 */
package org.struts.action.exam;

import org.paradyne.bean.exam.CandidateSchedule;
import org.paradyne.lib.ModelBase;
import org.paradyne.model.exam.CandidateScheduleModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author diptip
 *
 */
public class CandidateScheduleAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class);

	/**
	 * 
	 */
	CandidateSchedule candidateSchedule;
	public CandidateScheduleAction() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		candidateSchedule=new CandidateSchedule();

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return candidateSchedule;
	}

	public CandidateSchedule getCandidateSchedule() {
		return candidateSchedule;
	}

	public void setCandidateSchedule(CandidateSchedule candidateSchedule) {
		this.candidateSchedule = candidateSchedule;
	}
	
	public String save()throws Exception {
		
		try {
			CandidateScheduleModel model = new CandidateScheduleModel();
			model.initiate(context, session);
			String str = "";
			if(candidateSchedule.getScheduleCode().equals("")){
				
				str=model.add(candidateSchedule);
			}
			else {
				str=model.mod(candidateSchedule);
		}
			model.terminate();
			addActionMessage(getText(str));
			return reset();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "success";
		}
		}
	public String reset() throws Exception{
		try{	
		
			candidateSchedule.setCandidate("");
			candidateSchedule.setPaperName("");
			candidateSchedule.setScheduleDate("");
			candidateSchedule.setTime("");
			candidateSchedule.setScheduleCode("");
			candidateSchedule.setCandidateCode("");
			candidateSchedule.setPaperCode("");
								
		}
		catch(Exception e)
		{
			e.printStackTrace();
		} 
		return "success";
	}
	public String delete(){
		CandidateScheduleModel model = new CandidateScheduleModel();
		model.initiate(context, session);
		boolean result=model.delete(candidateSchedule);
		if(result){
			addActionMessage(getText("delMessage", ""));
			try {
				reset();
			} catch (Exception e) {
				// TODO: handle exception
			}
		
		}else{
			addActionMessage("Record already in use therefore cannot be deleted");
		}
		model.terminate();
		return "success";
	}
	
	
	
	
	public String f9actionCandidate() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
				
		//String query="SELECT CANDIDATE_HDR_CODE,CANDIDATE_HDR_FNAME || ' ' || CANDIDATE_HDR_LNAME FROM HRMS_CANDIDATE_HDR "+
					//"WHERE CANDIDATE_HDR_CODE IN(SELECT HRMS_CANDIDATE_HDR.CANDIDATE_HDR_CODE "+
					//"FROM HRMS_CANDIDATE_HDR MINUS SELECT HRMS_TEST_SCHEDULE.SCHEDULE_CANDIDATE_CODE "+ 
					//"FROM HRMS_TEST_SCHEDULE)";
		String query =  "SELECT CANDIDATE_HDR_CODE,CANDIDATE_HDR_FNAME || ' ' || CANDIDATE_HDR_LNAME FROM HRMS_CANDIDATE_HDR "+
			            "left join HRMS_TEST_SCHEDULE on(HRMS_TEST_SCHEDULE.SCHEDULE_CANDIDATE_CODE=HRMS_CANDIDATE_HDR.CANDIDATE_HDR_CODE )"+
						"where HRMS_TEST_SCHEDULE.SCHEDULE_CANDIDATE_CODE is NULL "+
						"ORDER BY CANDIDATE_HDR_CODE";
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={"Candidate Code", "Candidate Name",};
		
		String[] headerWidth={"20", "20"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"candidateSchedule.candidateCode","candidateSchedule.candidate"};
		
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

	
	public String f9actionPaper() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT PAPER_CODE,PAPER_NAME FROM HRMS_PAPER_HDR ORDER BY PAPER_CODE  ";		
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={"Paper Code", "Paper Name"};
		
		String[] headerWidth={"20", "20"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"candidateSchedule.paperCode","candidateSchedule.paperName"};
		
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

	
	public String f9action() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		
		String query = " SELECT CANDIDATE_HDR_FNAME || ' ' || CANDIDATE_HDR_LNAME,TO_char(SCHEDULE_DATE,'dd-mm-yyyy'),SCHEDULE_TIME,"+ 
		"PAPER_NAME,SCHEDULE_PAPER,SCHEDULE_CODE,SCHEDULE_CANDIDATE_CODE "+
		"FROM HRMS_TEST_SCHEDULE "+
		"LEFT JOIN HRMS_CANDIDATE_HDR ON(HRMS_CANDIDATE_HDR.CANDIDATE_HDR_CODE=HRMS_TEST_SCHEDULE.SCHEDULE_CANDIDATE_CODE)"+
		"LEFT JOIN HRMS_PAPER_HDR ON(HRMS_PAPER_HDR.PAPER_CODE=HRMS_TEST_SCHEDULE.SCHEDULE_PAPER) "+
		"ORDER BY HRMS_TEST_SCHEDULE.SCHEDULE_CANDIDATE_CODE";		
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={"Candidate Name", "Schedule Date","Schedule Time","Schedule PaperName"};
		
		String[] headerWidth={"20", "20","20","20"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"candidateSchedule.candidate","candidateSchedule.scheduleDate","candidateSchedule.time","candidateSchedule.paperName","candidateSchedule.paperCode","candidateSchedule_scheduleCode","candidateSchedule.candidateCode"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0,1,2,3,4,5,6};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod="CandidateSchedule_chk.action";
		
				
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}

	public String chk() throws Exception{
		candidateSchedule.setChk(true);
		logger.info("val of flag-----------------"+candidateSchedule.isChk());
		return SUCCESS;
	}

}
