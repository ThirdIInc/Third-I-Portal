package org.struts.action.recruitment;

import org.paradyne.bean.Recruitment.QuestionnaireMaster;
import org.paradyne.model.recruitment.QuestionnaireMasterModel;
import org.struts.lib.ParaActionSupport;

public class QuestionnarieMasterAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);

	QuestionnaireMaster quest;
		@Override
	
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		quest = new QuestionnaireMaster();
		quest.setMenuCode(395);

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return quest;
	}

	public QuestionnaireMaster getQuest() {
		return quest;
	}

	public void setQuest(QuestionnaireMaster quest) {
		this.quest = quest;
	}

	public String input() throws Exception {
		quest.setEnableAll("N");
		getNavigationPanel(1);
		return SUCCESS;
	}
	public String cancel() {
		try {
			reset();
			prepare_withLoginProfileDetails();
			getNavigationPanel(1);
			return SUCCESS;
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}

//  to display the save mode
	public String addNew() {
		try {
			getNavigationPanel(2);
			return "questData";
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	public String save()throws Exception
	{
		
		String [] attribute=request.getParameterValues("attribute");
		String [] value=request.getParameterValues("value");
		boolean result;
		QuestionnaireMasterModel model= new QuestionnaireMasterModel();
		model.initiate(context,session);
		if(quest.getQuesCode().equals("")){
			result=model.addQuest(quest,attribute,value);
			if (result) {
				addActionMessage(getText("addMessage", ""));
				model.showRecord(quest);
				getNavigationPanel(3);
				return "questData";
			} else {
				addActionMessage("Duplicate entry found.");
				reset();
				getNavigationPanel(1);
				return "success";
				
			}
		} 
		else {
			System.out.println("------------------------------------");
			result=model.modQuest(quest,attribute,value);
			if (result) {
				addActionMessage(getText("modMessage", ""));
				model.showRecord(quest);
				getNavigationPanel(3);
				return "questData";
			} else {
				addActionMessage("Duplicate entry found.");
				reset();
				getNavigationPanel(1);
				return "success";
			}
		}
		/*model.questionData(quest,request);
		model.terminate();
		
		return reset();*/
	}
	public String addItem()throws Exception{
		
		String [] srNo=request.getParameterValues("srNo");
		String [] attribute=request.getParameterValues("attribute");
		String [] value=request.getParameterValues("value");
		QuestionnaireMasterModel model= new QuestionnaireMasterModel();
		model.initiate(context, session);
		
		if(attribute !=null){
			model.getDuplicate(quest, srNo, attribute, value,1);
		}
		
		if (quest.getHiddenEdit().equals("")){
			model.addItem(quest, srNo, attribute, value,1);
		}
		else{
			model.moditem(quest, srNo, attribute, value,1);
		}
		model.terminate();
		quest.setQuesAttr("");
		quest.setQuesValue("");
		quest.setSubcode("");
		quest.setHiddenEdit("");
		getNavigationPanel(2);
		return "questData";
	}

	public String reset() throws Exception{
		
		/*private String quesCode;
		private String quesName;
		private String quesType;
		private String quesAttr;
		private String quesValue;
		private String Attribute;
		private String Value;
		private String subCode;
		private String subcode;
		private String srNo;
		private String myPage;
		private ArrayList list;
		private String hiddenEdit;
		private String hiddenCode;
		private String hdeleteOp;
		private String tableLength="0";
		private String confChkop="";*/
		quest.setQuesCode("");                          
		quest.setQuesName("");
		quest.setQuesType("");
		quest.setAttribute("");
		quest.setValue("");
		quest.setQuesAttr("");
		quest.setQuesValue("");
		quest.setHiddenCode("");
		quest.setSubcode("");
		quest.setSrNo("");
		quest.setHiddenEdit("");
		quest.setHdeleteOp("");
		quest.setConfChkop("");
		quest.setTableLength("0");
		getNavigationPanel(2);
		return "questData";
	}
	public String delete () throws Exception{
		System.out.println("=========delete===========");
		boolean result;
		QuestionnaireMasterModel model= new QuestionnaireMasterModel();
		model.initiate(context,session);
		result=model.deleteQuest(quest);
		if(result){
			addActionMessage("Record deleted successfully !");
			reset();
		}
		else 
			addActionMessage("This record is referenced in other resources.So cannot be deleted. !");
		model.questionData(quest,request);
		getNavigationPanel(1);
		model.terminate();
		
		return SUCCESS;
	}
	/*public String deleteDtl()throws Exception{
		QuestionnaireMasterModel model= new QuestionnaireMasterModel();
		model.initiate(context,session);
		model.delDtl(quest);
		model.terminate();
		quest.setAttribute("");
		quest.setValue("");
		quest.setSubcode("");
		return QuesRecord();
	}*/
	public String deleteDtl()throws Exception {
		System.out.println("===========deleteDtl==========*********88");
		String code[]=request.getParameterValues("hdeleteOp");
		//String [] srNo=request.getParameterValues("srNo");
		String [] attr=request.getParameterValues("attribute");
		String [] val=request.getParameterValues("value");
		QuestionnaireMasterModel model= new QuestionnaireMasterModel();
		model.initiate(context,session);
		model.delDtl(quest,code,attr,val);
		getNavigationPanel(2);
		//model.delDtl(quest,request);
		model.terminate();
		quest.setHiddenEdit("");
		//quest.setHdeleteOp("");
		//quest.setConfChkop("");
		return "questData";

	}
	
	public String edit()throws Exception {	
		String [] srNo=request.getParameterValues("srNo");
		String [] attribute=request.getParameterValues("attribute");
		String [] value=request.getParameterValues("value");
		
		QuestionnaireMasterModel model = new QuestionnaireMasterModel();
		model.initiate(context,session);
		model.addItem(quest, srNo, attribute, value,0);
		getNavigationPanel(3);
		model.terminate();
		return "questData";
		//	return SUCCESS;
	}
	public String QuesRecord()throws Exception {	
		QuestionnaireMasterModel model = new QuestionnaireMasterModel();
		model.initiate(context,session);
		model.showRecord(quest);
		getNavigationPanel(3);
		model.terminate();
		return "questData";
		//return SUCCESS;
	}
	
	/**
	 * To generate report
	 * 
	 * @return null
	 * @throws Exception
	 */
	public String report() throws Exception {
		QuestionnaireMasterModel model = new QuestionnaireMasterModel();
		model.initiate(context, session);
		String []label={"Sr.No",getMessage("questionname"),getMessage("questiontype")};
		model.getReport(quest, request, response, context, session,label);
		model.terminate();
		return null;
	}
public String showRecord () throws Exception{
		
		QuestionnaireMasterModel model= new QuestionnaireMasterModel();
		model.initiate(context,session);
		model.showRecord(quest);
		getNavigationPanel(3);
		model.terminate();
		return "questData";
	}


	public String f9action() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */
		String query = " SELECT   QUES_NAME , QUES_TYPE,QUES_CODE  FROM HRMS_QUES " 
					+" ORDER BY upper(QUES_NAME) ";		
		
		/**
		 * 		SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW.		 * 
		 */ 
		String[] headers={ getMessage("questionname")};
		
		String[] headerWidth={ "100"};
		
		/**
		 * 		-SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED.
		 * 		-USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * 		-PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX.
		 * 		NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES		 
		 * */ 	
		
		String[] fieldNames={"quesName","quesType","quesCode"};
		
		/**
		 * 	 	SET THE COLUMN INDEX
		 * 		E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW
		 * 			ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS
		 * 			THEN THE COLUMN INDEX CAN BE {1,3}
		 * 		
		 * 		NOTE: COLUMN NUMBERS STARTS WITH 0		 	
		 * 
		 */ 
		int[] columnIndex={0,1,2};
		
		/**
		 * 		WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";
		
		/**		 
		 * 		IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * 
		 * 		NAMING CONVENSTION: <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod="QuestionnaireMaster_showRecord.action";
		
		/**
		 * 		CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED		 * 
		 */
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	
	public void prepare_withLoginProfileDetails() throws Exception {
		
		QuestionnaireMasterModel model= new QuestionnaireMasterModel();
			
			model.initiate(context,session);
			
			model.questionData(quest,request);
			model.terminate();
	}
	
	public String callPage() throws Exception {
		
		QuestionnaireMasterModel model= new QuestionnaireMasterModel();
			model.initiate(context,session);
			model.questionData(quest,request);
			getNavigationPanel(1);
			model.terminate();
		 return SUCCESS;
		
		}
	
	public String calforedit() throws Exception{
		QuestionnaireMasterModel model= new QuestionnaireMasterModel();
		model.initiate(context,session);
		model.calforedit(quest);
		model.questionData(quest,request);
		getNavigationPanel(3);
		quest.setEnableAll("N");
		model.terminate();
		return "questData";
	}
	public String deletequestion()throws Exception {
		QuestionnaireMasterModel model= new QuestionnaireMasterModel();
		model.initiate(context,session);
		String delques[]=request.getParameterValues("hdeleteCode");
		boolean result =model.checkeddeletequestion(quest,delques);
		if(result) {
			addActionMessage(getText("delMessage",""));
		} else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other record(s) .");
		}
	
		model.questionData(quest,request);
		getNavigationPanel(1);
		model.terminate();
		return "success";
		//return reset();
		
		
	}


}
