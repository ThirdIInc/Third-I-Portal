package org.struts.action.recruitment;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import org.paradyne.bean.Recruitment.TestTemplate;
import org.paradyne.model.exam.QuestionBankModel;
import org.paradyne.model.recruitment.EmployeeRequisitionModel;
import org.paradyne.model.recruitment.InterviewScheduleModel;
import org.paradyne.model.recruitment.TestTemplateModel;
import org.struts.lib.ParaActionSupport;

public class TestTemplateAction extends ParaActionSupport{
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(TestTemplateAction.class);
	int scriptPageNo = 10;
	TestTemplate testTemplate;
	/** (non-Javadoc)
	 * varunk
	 */
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		testTemplate = new TestTemplate();
		testTemplate.setMenuCode(789);
	}
	
	/** (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	  public Object getModel() {
		// TODO Auto-generated method stub
		return testTemplate;
	}

	public TestTemplate getTestTemplate() {
		return testTemplate;
	}

	public void setTestTemplate(TestTemplate testTemplate) {
		this.testTemplate = testTemplate;
	}
	
	/**@method prepare_withLoginProfileDetails 
	 * @purpose to display the pending applications in tabular format at the time of form loading
	 * @return void
	 */
	public void prepare_withLoginProfileDetails()throws Exception{
		logger.info("in prepare_withLoginProfileDetails");
		testTemplate.setMarksForNoAnswer("0");
		TestTemplateModel model = new TestTemplateModel();
		model.initiate(context,session);	
		model.terminate();
	} //end of prepare_withLoginProfileDetails method
	
	public String input(){
		getNavigationPanel(1);
		
		TestTemplateModel model = new TestTemplateModel();	
		model.initiate(context, session);	
		testTemplate.setCounterVar("1");
		model.showRecords(testTemplate,request);		
		model.loadData(testTemplate);
		model.terminate();	//terminate the TestTemplateModel class
		testTemplate.setNotAvailable("true");
		testTemplate.setListFlag("true");
		testTemplate.setButtonPanelcode("1");
		return "success";
	} //end of input method
	
	/**@method addNew 
	 * @purpose to display the from in enable form
	 * @return String
	 */
	public String addNew() {
		getNavigationPanel(2);
		testTemplate.setButtonPanelcode("1");
		testTemplate.setListFlag("false");
		TestTemplateModel model=new TestTemplateModel();
		model.initiate(context,session);
		model.loadData(testTemplate);
		model.terminate();
		return "addnew";
	} //end of addNew method
	
	/**@method cancelFirst 
	 * @purpose to go back to the previous stage
	 * @return String
	 */
	public String cancelFirst() {
		logger.info("in cancelFirst");
		
		getNavigationPanel(1);
		try {
			reset();
		} catch (Exception e) {
			e.printStackTrace();
		} //end of catch
		input();
		return "addnew";
	} //end of cancelFirst method
	
	/**@method selectQuestion 
	 * @purpose to select the questions from the question bank
	 * @return String
	 */
	public String selectQuestion(){
		logger.info("leaveCombination   ");
		
		TestTemplateModel model = new TestTemplateModel();
		model.initiate(context, session);
		testTemplate.setChFlag("true");
		if(testTemplate.getCompLevel1()!=null)
		{
		testTemplate.setCompLevel1(testTemplate.getCompLevel1());
		} //end of if
		else{
		testTemplate.setCompLevel1("");
		} //end of else
		model.selectQuestion(testTemplate, request);
		model.terminate();
		testTemplate.setHiddenQuestionCode(""); 
		return "questionList";
	} //end of selectQuestion method
	
	public String removeQuestions() {
		try {
			TestTemplateModel model = new TestTemplateModel();
			model.initiate(context, session);
			getNavigationPanel(2);
			String queCodes[] = request.getParameterValues("dupSelectQuCode");
			String checkBox[] = request.getParameterValues("check");
			String subName[] = request.getParameterValues("dupQusSubject");
			String queName[] = request.getParameterValues("dupQusName");
			String complexity[] = request.getParameterValues("dupComplexicity");
			ArrayList list = new ArrayList();
			for (int i = 0, z = 0; i < queCodes.length; i++) {
				TestTemplate bean = new TestTemplate();
				if (z < checkBox.length && checkBox[z].equals(queCodes[i])) {
					z++;
					continue;
				} //end of if
				else {
					bean.setDupSelectQuCode(queCodes[i]);
					bean.setDupQusSubject(subName[i]);
					bean.setDupQusName(queName[i]);
					bean.setDupComplexicity(complexity[i]);
					list.add(bean);
				} //end of else

			} //end of loop
			testTemplate.setAddlist(list);
			if(list.size() == 0){
				testTemplate.setNotAvailable("true");
				testTemplate.setCntRandomHard("0");
				testTemplate.setCntRandomMedium("0");
				testTemplate.setCntRandomEasy("0");
				testTemplate.setMarkRandomHard("0");
				testTemplate.setMarkRandomMedium("0");
				testTemplate.setMarkRandomEasy("0");
				testTemplate.setTotalRandomMarkss("0");
				testTemplate.setTotalRandomnoOfQus("0");
			} //end of if
			else{
				testTemplate.setNotAvailable("false");
			} //end of else
			
			logger.info("testTemplate.getAddlist=====>"+list.size());
			model.loadData(testTemplate);
			model.terminate();
			request.setAttribute("addQuestionTotalPages",(list.size() % scriptPageNo == 0)?(list.size()/scriptPageNo):(list.size()/scriptPageNo)+1);
		} catch (Exception e) {
			logger.error("comments", e);
		} //end of catch
		return SUCCESS;
	}
	
	/**@method f9Search 
	 * @purpose to select saved templates
	 * @return String
	 */
	public String f9Search() throws Exception {
		String query = "SELECT TEMPLATE_TEST_NAME, TO_CHAR(TEMPLATE_DURATION, 'HH24:MI'), DECODE(TEMPLATE_TEST_TYPE, "
						+"'O', 'Objective', 'S', 'Subjective', 'B', 'Both'), TEMPLATE_TOTAL_QUES, "
						+"TEMPLATE_TOTAL_MARKS, TEMPLATE_PASSING_MARKS, TEMPLATE_CODE "
						+"FROM HRMS_REC_TEST_TEMPLATE "
						+"ORDER BY UPPER(TEMPLATE_TEST_NAME)";

		String[] headers = {getMessage("test.name"), getMessage("test.duration"), getMessage("test.type"),
							getMessage("total.ques"), getMessage("total.marks"), getMessage("passing.marks")};

		String[] headerWidth = {"30", "10", "10", "15", "10", "10"};

		String[] fieldNames = {"testName", "testDuration", "testType", "totalNoOfQues", "testTotalMarks", 	
									"passingMark", "testTemplateCode"};

		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6};

		String submitFlag = "true";

		String submitToMethod = "TestTemplate_searchRecord.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}	
	
	/**@method searchRecord 
	 * @purpose to display the details of the selected template
	 * @return String
	 */
	public String searchRecord() throws Exception{
		getNavigationPanel(3);
		testTemplate.setButtonPanelcode("1");
		
		TestTemplateModel model = new TestTemplateModel();
		model.initiate(context, session);
		
		model.searchRecord(testTemplate,request);	
		model.terminate();
		return "viewFirst";
	}
	
	/**@method reset 
	 * @purpose to clear all the from fields
	 * @return String
	 */
	public String reset()throws Exception{	
		testTemplate.setTestTemplateCode("");
		testTemplate.setTestName("");
		testTemplate.setTestType("");
		testTemplate.setTestDuration("");
		testTemplate.setTotalQuestions("");
		testTemplate.setTotalNoQue("");
		testTemplate.setTestTotalMarks("");
		testTemplate.setPassingMark("");
		testTemplate.setInstructionNotes("");		
		testTemplate.setSelectQuesCode("");
		testTemplate.setQuesSubject("");
		testTemplate.setQuesName("");
		testTemplate.setComplexicity("");	
		testTemplate.setEqualWeightage("");
		testTemplate.setMarksForHard("");
		testTemplate.setMarksForMedium("");
		testTemplate.setMarksForEasy("");
		testTemplate.setCorrectCheck("false");
		testTemplate.setMarkWrong("");
		testTemplate.setMarksForNoAnswer("");
		testTemplate.setQuestionFrom("");
		testTemplate.setHard("");
		testTemplate.setMedium("");
		testTemplate.setEasy("");
		testTemplate.setHiddenSubject("");
		testTemplate.setHiddenCategory("");
		testTemplate.setNotAvailable("");
		testTemplate.setQuestionListFlag("");
		testTemplate.setSubjectCategory("");
		testTemplate.setRandomSubCode("");
		testTemplate.setRandomCatCode("");
		testTemplate.setRandomComplexicity("");
		testTemplate.setComplexicityCode("");
		testTemplate.setAvailableQuestion("");
		testTemplate.setTestQuestion("");
		testTemplate.setTestName("");
		testTemplate.setTestDuration("");
		testTemplate.setTotalQuestions("");
		testTemplate.setMarkCorrect("");
		testTemplate.setSelectquestions("");
		testTemplate.setEnableScore("");
		testTemplate.setTestType("");
		testTemplate.setCompLevel("");
		testTemplate.setSubject("");
		testTemplate.setSubjectFlag("");
		testTemplate.setCompLevel1("");
		testTemplate.setMarkCorrect("");
		testTemplate.setCntEasy("");
		testTemplate.setCntHard("");
		testTemplate.setCntMedium("");
		testTemplate.setTotalnoOfQus("");
		testTemplate.setMarkHard("");
		testTemplate.setMarkMedium("");
		testTemplate.setMarkEasy("");
		
		
		testTemplate.setCntRandomEasy("");
		testTemplate.setCntRandomHard("");
		testTemplate.setCntRandomMedium("");
		testTemplate.setTotalRandomnoOfQus("");
		
		testTemplate.setMarkRandomHard("");
		testTemplate.setMarkRandomMedium("");
		testTemplate.setMarkRandomEasy("");
		testTemplate.setTotalMarkss("");
		testTemplate.setTotalRandomMarkss("");
		
		testTemplate.setWrongmarksForEasy("0");
		testTemplate.setWrongmarksForMedium("0");
		testTemplate.setWrongmarksForHard("0");
		testTemplate.setTotalNoOfQues("");
		testTemplate.setMarksAllocatedForEach("0");
		return SUCCESS;
	} //end of reset method
	
	/**@method saveFirst 
	 * @purpose to save or update the template details
	 * @return String
	 */
	public String saveFirst() throws Exception{
		logger.info("in saveFirst ");
		testTemplate.setButtonPanelcode("1");
		//getNavigationPanel(3);
		
		String actionFlag = request.getParameter("actionFlag");	
		
		TestTemplateModel model = new TestTemplateModel();
		model.initiate(context, session);
		
		if(testTemplate.getTestTemplateCode().equals("")){
			String result = model.saveFirst(testTemplate, request);
			
			if(result.equals("saved")){
				addActionMessage(getMessage("save"));
				
				getNavigationPanel(3);
				model.viewDetails(testTemplate,testTemplate.getTestTemplateCode(), request);
				testTemplate.setFlagview("true");	
			} //end of if			
			else if(result.equals("duplicate")){
				
				addActionMessage("Duplicate Test Name Found");
				getNavigationPanel(2);
				model.loadData(testTemplate);
				model.callAddList(testTemplate, request);
				if(testTemplate.getQuestionFrom().equals("random"))
					model.setQuestionList(testTemplate, request);
				
				return "addnew";
			} //end of else if
			else {
				addActionMessage(getMessage("save.error"));
				getNavigationPanel(2);
				
				if(testTemplate.getQuestionFrom().equals("random"))
					model.setQuestionList(testTemplate, request);
				
				return "addnew";
			} //end of else
		} //end of if
		else{
			String result = model.updateData(testTemplate, request);			
			if(result.equals("updated")){
				addActionMessage(getMessage("update"));
				getNavigationPanel(3);
				model.viewDetails(testTemplate,testTemplate.getTestTemplateCode(), request);
				testTemplate.setFlagview("true");	
			} //end of if			
			else if(result.equals("duplicate")){
				getNavigationPanel(2);
				addActionMessage(getMessage("duplicate"));	
				
				if(testTemplate.getQuestionFrom().equals("random"))
					model.setQuestionList(testTemplate, request);
				
				return "addnew";
			} //end of else
			else {
				getNavigationPanel(2);
				addActionMessage(getMessage("update.error"));
				
				if(testTemplate.getQuestionFrom().equals("random"))
					model.setQuestionList(testTemplate, request);
				
				return "addnew";
			} //end of else
		} //end of else
		return "viewFirst";	
	} //end of saveFirst method
	
	/**@method selectRandomQuestions 
	 * @purpose to select random questions
	 * @return String
	 */
	public String selectRandomQuestions(){
		logger.info("in selectRandomQuestions ");
		getNavigationPanel(2);
		testTemplate.setQuestionListFlag("true");
		TestTemplateModel model = new TestTemplateModel();
		model.initiate(context, session);
		model.selectRandomQuestions(testTemplate);
		model.loadData(testTemplate);
		model.terminate();
		return "success";
	} //end of selectRandomQuestions method
	
	/**@method edit 
	 * @purpose to edit the selected template
	 * @return String
	 */
	public String edit(){
		getNavigationPanel(2);
		testTemplate.setButtonPanelcode("3");
		testTemplate.setListFlag("false");		
		TestTemplateModel model=new TestTemplateModel();
		model.initiate(context,session);
		model.getCallForEdit(testTemplate,request);
		model.loadData(testTemplate);
		model.terminate();
		return "success";
	} //end of edit method
	
	public String editdblClk(){
		try{
		getNavigationPanel(3);
		testTemplate.setButtonPanelcode("1");	
		testTemplate.setListFlag("false");
		TestTemplateModel model=new TestTemplateModel();
		model.initiate(context,session);	
		model.getDetails(testTemplate,request);
		model.terminate();
		}catch(Exception e){
			logger.error("exception in editdblClk method",e);
		} //end of catch
		return "viewFirst";
	} //end of editdblClk method
		
	/**@method delete 
	 * @purpose to delete the selected template
	 * @return String
	 */
	public String delete()throws Exception{
		logger.info("in delete");
			
		TestTemplateModel model=new TestTemplateModel();
		model.initiate(context,session);
			
		boolean flag = model.delete(testTemplate);
			
		if(flag){
			logger.info("in delete======"+flag);
			addActionMessage(getMessage("delete"));
			model.showRecords(testTemplate,request);		
			model.loadData(testTemplate);
			testTemplate.setListFlag("true");
			getNavigationPanel(1);
			testTemplate.setButtonPanelcode("1");
			reset();
		} //end of if
		else{
			addActionMessage(getMessage("del.error"));
			getNavigationPanel(3);
		} //end of else
		model.loadData(testTemplate);	
		model.terminate();
		return "success";
	} //end of delete method
	
	/**@method cancelThird 
	 * @purpose to go back to the previous stage
	 * @return String
	 */
	public String cancelThird() throws Exception{
		logger.info("in cancelThird");
			
		getNavigationPanel(3);
		testTemplate.setButtonPanelcode("1");
			
		TestTemplateModel model=new TestTemplateModel();
		model.initiate(context,session);
			
		model.viewDetails(testTemplate,testTemplate.getTestTemplateCode(), request);
		model.terminate();
		return "viewFirst";
	} //end of cancelThird method
		
	/**@method cancelSec 
	 * @purpose to go back to the previous stage
	 * @return String
	 */
	public String cancelSec() throws Exception{
		logger.info("in cancelSec");
		getNavigationPanel(3);
		testTemplate.setButtonPanelcode("1");
		return "viewFirst";
	} //end of cancelSec method
	
	public void selCategory()throws Exception{
		TestTemplateModel model=new TestTemplateModel();
		model.initiate(context,session);
		String responseText = "";
		String responseText1 = "";
		String responseText2 = "";
		String result="";
		Object[][] catObj = model.getCategory(testTemplate);
		String catCode[] = new String[catObj.length];
		String catName[] = new String[catObj.length];
		
		for (int i = 0; i < catObj.length; i++) {
			responseText1 = responseText1 + String.valueOf(catObj[i][0]);
			if(i<catObj.length-1){
				responseText1 += ",";
			} //end of if 
		} //end of loop
		
		for (int i = 0; i < catObj.length; i++) {
			responseText2 = responseText2 + String.valueOf(catObj[i][1]) ;
			if(i<catObj.length-1){
				responseText2 += ",";
			} //end of if
		} //end of loop
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		responseText = responseText1+"&"+responseText2;
		out.println(responseText);
		model.terminate();
	} //end of selCategory method
	
	/**
	 * following function is called when  the Ok button of Add question List is clicked.
	 * @return.
	 */
	public String addQuestions() {
			getNavigationPanel(2);
			TestTemplateModel model=new TestTemplateModel();
			model.initiate(context,session);
			model.addQuestionFromBank(testTemplate,request);
			model.loadData(testTemplate);
			model.terminate();
		return "success";
	 } //end of addQuestions method
	
	public String viewCart()
	{
		try{
		TestTemplateModel model=new TestTemplateModel();
		model.initiate(context,session);
		model.viewCartDetails(testTemplate,request);
		model.terminate();		
		}catch(Exception e){}
		return "viewcart";
	}

	public String deleteQuestions() throws Exception {
		getNavigationPanel(2);
		String[] subcat=(String[])request.getParameterValues("dupQusSubject");
		String[]  questionName=(String[])request.getParameterValues("dupQusName");
		String[]  complexity=(String[])request.getParameterValues("dupComplexicity");
		String[]  selectedQuesCode=(String[])request.getParameterValues("dupSelectQuCode");
		TestTemplateModel model=new TestTemplateModel();
		model.initiate(context,session);
		model.deleteOption(testTemplate,subcat,questionName,complexity,selectedQuesCode);
		model.terminate();
		return "success";
	} //end of deleteQuestions
} //end of class method
