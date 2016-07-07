package org.struts.action.recruitment;

import org.paradyne.bean.Recruitment.OnlinePaper;
import org.paradyne.model.recruitment.OnlinePaperModel;
import org.struts.lib.ParaActionSupport;


public class OnlinePaperAction extends ParaActionSupport {

	OnlinePaper onlinePaper;
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	public OnlinePaper getOnlinePaper() 
	{
		return onlinePaper;
	}

	public void setOnlinePaper(OnlinePaper onlinePaper) {
		this.onlinePaper = onlinePaper;
	}

	public void prepare_local() throws Exception {
		
		onlinePaper = new OnlinePaper();
	}
	
	public Object getModel() {
		
		return onlinePaper;
	}
	
	public String save()throws Exception{		
		String []id=request.getParameterValues("srNo");
		String []subItt=request.getParameterValues("subItt");
		String []subectCodeItt=request.getParameterValues("subjectCodeItt");
		String []numofItt=request.getParameterValues("numofItt");
		String []hardItt=request.getParameterValues("hardItt");
		   
		String []mediumItt=request.getParameterValues("mediumItt");
		String []easyItt=request.getParameterValues("easyItt");  
	
		OnlinePaperModel model=new OnlinePaperModel();
		
		model.initiate(context,session);
		if(onlinePaper.getPaperCode().equals("")||onlinePaper.getPaperCode()==null){
			
		model.save(onlinePaper,id,subItt,numofItt,hardItt,easyItt,mediumItt,subectCodeItt);
		addActionMessage("Record saved Successfully");
		}
				
		else{
	
			model.update(onlinePaper , id ,subItt ,numofItt,hardItt,easyItt,mediumItt,subectCodeItt);
		addActionMessage("Record Modified Successfully");
		}
	
		reset();
		model.terminate();
		return SUCCESS;
		
	}
		
public String showRecord()throws Exception {	
		
		OnlinePaperModel model = new OnlinePaperModel();
			model.initiate(context,session);
			model.showRecord(onlinePaper,request);
			model.terminate();	
		return "success"; 
		}
	
public String add()throws Exception 
           {	
		
		    OnlinePaperModel model = new OnlinePaperModel();
			model.initiate(context,session);
			String []id=request.getParameterValues("srNo");
			String []subItt=request.getParameterValues("subItt");
			String []subectCodeItt=request.getParameterValues("subjectCodeItt");
			String []numofItt=request.getParameterValues("numofItt");
			String []hardItt=request.getParameterValues("hardItt");
			String []mediumItt=request.getParameterValues("mediumItt");
			String []easyItt=request.getParameterValues("easyItt");
					
            model.add(onlinePaper,id,subItt,numofItt,hardItt,easyItt,mediumItt,subectCodeItt );
			model.terminate();	
			onlinePaper.setSubject("");
			onlinePaper.setHardQuestion("");
			onlinePaper.setMediumQuestion("");
			onlinePaper.setEasyQuestion("");
			onlinePaper.setNoOfQuestion("");
			
			return "success"; 
            }
public String delete() throws Exception {
	
	logger.info("Inside Delete");
	OnlinePaperModel model= new OnlinePaperModel();
	model.initiate(context,session);
	boolean result=model.delete(onlinePaper);
	model.terminate();
	if (result){
		addActionMessage(getText("delMessage",""));
		onlinePaper.setPaperCode("");
		onlinePaper.setPaperName("");
		
		onlinePaper.setSubject("");
		onlinePaper.setHardQuestion("");
		onlinePaper.setMediumQuestion("");
		onlinePaper.setEasyQuestion("");
		onlinePaper.setPassMarks("");
		onlinePaper.setPaperTimeDuration ("");
		onlinePaper.setPaperPassCrieteria("");
		
		onlinePaper.setNoOfQuestion("");
        onlinePaper.setTotalNoOfQues("");
        onlinePaper.setNoOfQuestionPerPage("");
		}
	else {
	addActionMessage("Online Paper not  deleted");
	}
	return "success";
}
	
public String reset() throws Exception {
			try{	
				onlinePaper.setPaperCode("");
				onlinePaper.setPaperName("");
				onlinePaper.setSubjectMarks("");
				onlinePaper.setSubject("");
				onlinePaper.setHardQuestion("");
				onlinePaper.setMediumQuestion("");
				onlinePaper.setEasyQuestion("");
				onlinePaper.setPassMarks("");
				onlinePaper.setPaperTimeDuration("");
				onlinePaper.setPaperPassCrieteria("");
				onlinePaper.setTotalNoOfQues("");
				onlinePaper.setNoOfQuestion("");
				onlinePaper.setNoOfQuestionPerPage("");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			} 
			return "success";
		}
		
		
		public String edit() {			

		    OnlinePaperModel model = new OnlinePaperModel();
			model.initiate(context,session);
			String []id=request.getParameterValues("srNo");
			String []subItt=request.getParameterValues("subItt");
			String []subectCodeItt=request.getParameterValues("subjectCodeItt");
			String []numofItt=request.getParameterValues("numofItt");
			String []hardItt=request.getParameterValues("hardItt");
			String []mediumItt=request.getParameterValues("mediumItt");
			String []easyItt=request.getParameterValues("easyItt");
					
            model.edit(onlinePaper,id,subItt,numofItt,hardItt,easyItt,mediumItt,subectCodeItt);
			
            return SUCCESS;
		}
		
			
		public String deleteSubject() {
			OnlinePaperModel model= new OnlinePaperModel();
			String []id=request.getParameterValues("srNo");
			String []subItt=request.getParameterValues("subItt");
			String []subectCodeItt=request.getParameterValues("subjectCodeItt");
			String []numofItt=request.getParameterValues("numofItt");
			String []hardItt=request.getParameterValues("hardItt");
			
			String []mediumItt=request.getParameterValues("mediumItt");
			String []easyItt=request.getParameterValues("easyItt");
					
            model.deleteSubject(onlinePaper,id,subItt,numofItt,hardItt,easyItt,mediumItt,subectCodeItt);
		
			return SUCCESS;
		}	
	
	public String f9action() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " 	 SELECT PAPER_CODE,PAPER_NAME,PAPER_TIME,PAPER_PASS_CREITERIA,TOTAL_NO_QUESTION ,NO_QUESTION_PER_PAGE FROM HRMS_PAPER_HDR ORDER BY PAPER_CODE  ";		
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={"Paper Code", "Paper Name"};
		
		String[] headerWidth={"20", "80"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"onlinePaper.paperCode","onlinePaper.paperName","onlinePaper.paperTimeDuration","onlinePaper.paperPassCrieteria","onlinePaper.totalNoOfQues","onlinePaper.noOfQuestionPerPage"};
		
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
		String submitToMethod="OnlinePaper_showRecord.action";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	
	public String f9actionSubject() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String check = "";
		Object[] subjectCode=request.getParameterValues("subjectCodeItt");
		if(subjectCode!=null)
		{	
			for (int i = 0; i < subjectCode.length - 1; i++) 
				check+= subjectCode[i]+",";
			check+=subjectCode[subjectCode.length - 1];
		}
		else
			check="0";		
		
		
		String query = "SELECT SUBJECT_CODE, SUBJECT_NAME FROM HRMS_TEST_SUBJECT WHERE SUBJECT_CODE NOT IN ("+check+") ORDER BY SUBJECT_CODE ";	
			
				
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={"Subject Code", "Subject Name"};
		
		String[] headerWidth={"20", "80"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"onlinePaper.subjectCode","onlinePaper.subject"};
		
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
		String submitToMethod="OnlinePaper_getRecord.action";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	
}