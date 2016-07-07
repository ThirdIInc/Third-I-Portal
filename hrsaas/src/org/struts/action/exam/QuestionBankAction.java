package org.struts.action.exam;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.paradyne.bean.exam.QuestionBank;
import org.paradyne.lib.ModelBase;
import org.paradyne.model.exam.QuestionBankModel;
import org.struts.lib.ParaActionSupport;
/**
 * Date:20-01-2009
 * @author Pradeep Kumar Sahoo
 *
 */
public class QuestionBankAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class);
/**
 * The return types of the methods are :
 * success:It will display the pages.exam.questionBank jsp page
 * viewAns:It will display the answerOptions jsp page
 * view:It will display the questionBankView jsp page 
 */
	
	QuestionBank questionBank;
	@Override
	public void prepare_local() throws Exception {
		questionBank=new QuestionBank();
		questionBank.setMenuCode(572);
	}

	public Object getModel() {
		return questionBank;
	}

	public QuestionBank getQuestionBank() {
		return questionBank;
	}

	public void setQuestionBank(QuestionBank questionBank) {
		this.questionBank = questionBank;
	}
	public void prepare_withLoginProfileDetails() throws Exception {
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		if (!("".equals(poolName) || poolName == null)) {
			poolName = "/" + poolName;
		}
		final String fileDataPath = this.getText("data_path") + "/upload" + poolName + "/OnlineTestDocs/";
		this.questionBank.setDataPath(fileDataPath);
	}
	
	
	/**
	 * following function is called when Add New button is clicked.
	 * When Add New button is clicked all the fields will be activated for adding a new record. 
	 * Save and Cancel button will be enabled.
	 * @return
	 */
	public String addNew() {
			getNavigationPanel(4);
			QuestionBankModel model=new QuestionBankModel();
			model.initiate(context,session);
			//questionBank.setOptionFlag("true");
			model.questionData(questionBank,request);
			questionBank.setEditFlag("false");
			
			model.terminate();
			return "success";
	}
	/**
	 * following function is called when the link is clicked.
	 * Add New and search button will be enabled.
	 * All the fields will be in read only mode.
	 */
	public String input(){
			getNavigationPanel(1);
			QuestionBankModel model=new QuestionBankModel();
			model.initiate(context,session);
			questionBank.setFlagShow("true");
			questionBank.setFlagView("true");
			questionBank.setQuestionView("true");			
			model.questionData(questionBank,request);
			model.terminate();
			return "view";
	}
	
	public String setSubjectQues() {
		/*String subjectCode = request.getParameter("subject");
		if(subjectCode == null || subjectCode.equals("null") || subjectCode.equals("")){
			subjectCode = "0";		}
			
		questionBank.setSubjectCode(subjectCode);*/
		String from = request.getParameter("from");
		questionBank.setFrom(from);
		if( from!=null && from.equals("list")){
			questionBank.setFromFlag("SubjectList");
		} else {
			questionBank.setFromFlag("Subject");
		}
		getNavigationPanel(4);
		QuestionBankModel model=new QuestionBankModel();
		model.initiate(context,session);
		questionBank.setFlagShow("true");
		questionBank.setQuestionView("true");
		model.getSubjectQuestions(questionBank, request);	
		model.terminate();
		return "view";
	}
	
	public String setCategoryQues() {
		/*String subjectCode = request.getParameter("subject");
		if(subjectCode == null || subjectCode.equals("null") || subjectCode.equals("")){
			subjectCode = "0";		}
			
		questionBank.setSubjectCode(subjectCode);*/
		getNavigationPanel(4);
		QuestionBankModel model=new QuestionBankModel();
		model.initiate(context,session);
		questionBank.setFlagShow("true");
		questionBank.setQuestionView("true"); 
		questionBank.setFromFlag("Category");
		model.getCategoryQuestions(questionBank, request);
		model.terminate();
		return "view";
	}
		
/**
 * following function is called when any page is selected to display the listb of records
 * @return
 * @throws Exception
 */	
	
	public String callPage() throws Exception {
			getNavigationPanel(4);
			QuestionBankModel model=new QuestionBankModel();
			model.initiate(context,session);
			questionBank.setOptionFlag("false");
			questionBank.setFlagShow("true");
			questionBank.setFlagView("true");
			questionBank.setQuestionView("true");
			
			model.questionData(questionBank,request);
			//getRecord();
			model.terminate();
		    return SUCCESS;
		
		}
	/**
	 * following function is called when any page is selected in the view mode.
	 * @return
	 */
	public String callPageView(){
		getNavigationPanel(1);
		QuestionBankModel model=new QuestionBankModel();
		model.initiate(context,session);
		questionBank.setOptionFlag("false");
		questionBank.setFlagShow("true");
		questionBank.setFlagView("true");
		questionBank.setQuestionView("true");
		
		if (questionBank.getFromFlag().equals("Subject")|| questionBank.getFromFlag().equals("SubjectList")) {
			setSubjectQues();			
		} else {
			setCategoryQues();
		}
		//model.questionData(questionBank,request);
		model.terminate();
	    return "view";
		
		
	}
	
		
	public String getRecord() throws Exception{
			QuestionBankModel model=new QuestionBankModel();
			model.initiate(context,session);
			String [] option=request.getParameterValues("optionName");
			String [] flagItt=request.getParameterValues("flagItt");
			model.getOptionRec(questionBank, flagItt,option);
			model.terminate();
			return "success";
	}
	/**
	 * following function is called when  the Add button is clicked for adding the answer in the option list.
	 * @return.
	 */
	public String addOption() {
		try {
			getNavigationPanel(4);
			String[] srNo = request.getParameterValues("srNo");
			String[] option = request.getParameterValues("optionName");
			String[] flagItt = request.getParameterValues("flagItt");
			String[] quesChoiceLen = request
					.getParameterValues("quesChoiceLen");
			QuestionBankModel model = new QuestionBankModel();
			model.initiate(context, session);
			questionBank.setOptionFlag("true");
			/**
			 * following if condition checks whether the question code exists or
			 * not.If question code exists then system will set the option code
			 * while adding any option to the list.
			 */
			boolean result = model.chkDuplicate(questionBank, srNo, option,
					flagItt, srNo, request, quesChoiceLen);
			if (questionBank.getQuesCode().equals("")
					|| questionBank.getQuesCode().equals("null")) {
				/**
				 * following if condition checks whether getParaId() has any value.
				 * If it has any value then it will modify the existing record which
				 * has been selected for modifying the answer. In Jsp when any
				 * answer option is selected for editing paraid value is set. If
				 * paraId is null or blank then the addOption method is called from
				 * the model
				 */

				if (questionBank.getParaId().equals("")) {
					if (result)
						addActionMessage("Duplicate entry found");
					else {
						model.addOption(questionBank, srNo, option, flagItt,
								srNo, request);
						//addActionMessage("Record added successfully");
					}
				} else {
					if (result)
						addActionMessage("Duplicate entry found");
					else {
						model.editOption(questionBank, srNo, option, flagItt,
								srNo, request);
						//addActionMessage("Record updated successfully");
					}
				}
			} else {
				String[] opcode = request.getParameterValues("optionCode");
				if (questionBank.getParaId().equals("")) {
					if (result)
						addActionMessage("Duplicate entry found");
					else {
						model.addOption(questionBank, srNo, option, flagItt,
								opcode, request);
						//addActionMessage("Record added successfully");
					}
				} else {
					if (result)
						addActionMessage("Duplicate entry found");
					else
						model.editOption(questionBank, srNo, option, flagItt,
								opcode, request);
					//addActionMessage("Record updated successfully");
				}
			}
			// model.questionData(questionBank,request);
			questionBank.setFlagView("true");
			questionBank.setQuestionView("true");
			questionBank.setFlagShow("true");
			if (questionBank.getFromFlag().equals("Subject")
					|| questionBank.getFromFlag().equals("SubjectList")) {
				setSubjectQues();
			} else {
				setCategoryQues();
			}
			model.terminate();
			// questionBank.setOption("");
			questionBank.setHflag("");
			questionBank.setParaId("");
			questionBank.setOptionId("");
			questionBank.setOptionTextarea("");
			questionBank.setFlag("");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "view";
	}

	
/**
 * following function is called when the Add Answer Option button is clicked.
 * Added on Dt:18-06-2009
 * 
 * @return
 */	

	public String getAnsOption() {
		try {
			QuestionBankModel model = new QuestionBankModel();
			model.initiate(context, session);
			model.getOptionDet(questionBank);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "viewAns";
	}
	
	
	/**
	 * following function is called when a new record is inserted or edited.
	 * After the record is inserted or updated Application will appear in Edit,Delete and Cancel mode.
	 * @return
	 * @throws Exception
	 */
	
	public String save() throws Exception {
		try {
			getNavigationPanel(4);
			QuestionBankModel model = new QuestionBankModel();
			String[] srNo = request.getParameterValues("srNo");
			String[] option = request.getParameterValues("optionName");// questionBank.getOptionString().split(",");//request.getParameterValues("optionName");
			String[] flagItt = request.getParameterValues("flagItt");// questionBank.getFlagString().split(",");//
			String[] opcode = request.getParameterValues("optionCode");
			model.initiate(context, session);
			if (questionBank.getQuesCode().equals("")) {
				boolean result = model
						.save(questionBank, srNo, option, flagItt);
				if (result) {
					questionBank.setQuestionView("true");
					questionBank.setAnsFlgView(true);
					addActionMessage("Record saved successfully.");
					 reset();
					model.getQsnDetails(questionBank);
					model.getOptionDet(questionBank);
				} else {
					addActionMessage("Duplicate entry found.");
					setOptionData(option, srNo, flagItt, questionBank);
				}

			} else {
				boolean result = model.updateData(questionBank, option,
						flagItt, opcode);
				if (result) {
					questionBank.setQuestionView("true");
					addActionMessage("Record updated successfully.");
					model.getQsnDetails(questionBank);
					model.getOptionDet(questionBank);
					 reset();
				} else {
					addActionMessage("Duplicate entry found.");
					questionBank.setEditFlag("false");
					setOptionData(option, srNo, flagItt, questionBank);
				}
			}
			questionBank.setFlagView("true");
			questionBank.setQuestionView("true");
			questionBank.setFlagShow("true");
			
			//model.questionData(questionBank, request);
			if (questionBank.getFromFlag().equals("Subject")
					|| questionBank.getFromFlag().equals("SubjectList")) {
				setSubjectQues();
			} else {
				setCategoryQues();
			}
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "view";
	}
	
	
	public void setOptionData(String[] option,String[] srNo,String[]flagItt,QuestionBank questionBank)
	{
		try {
			ArrayList<Object> optionList = new ArrayList<Object>();
			if (option != null) {
				System.out.println("1111111111");
				for (int i = 0; i < option.length; i++) {
					QuestionBank bean = new QuestionBank();
					bean.setSrNo(srNo[i]);
					bean.setOptionName(option[i]);
					 
					if (option[i].length() > 25) {
						bean.setQuesChoiceLen(option[i].substring(0, 20));
					} else {
						bean.setQuesChoiceLen(option[i]);
					}
					bean.setFlagItt(flagItt[i]);
					if (!(questionBank.getQuesCode().equals("") || questionBank
							.getQuesCode().equals("null"))) {
						bean.setOptionCode(srNo[i]);
					}
					optionList.add(bean);
				}
				questionBank.setOptionList(optionList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * following function is called when any record is double clicked from the list
	 *Save and Cancel button will be enabled.It means that we can now modify the record.
	 * @return
	 */	
		public String calforedit(){
			try {
				getNavigationPanel(4);
				QuestionBankModel model = new QuestionBankModel();
				model.initiate(context, session);
				// questionBank.setOptionFlag("true");
				questionBank.setFlagView("true");
				questionBank.setFlagShow("true");
				questionBank.setQuesCode(questionBank.getHiddencode());
				questionBank.setOption("");
				questionBank.setQuestionView("true");
				model
						.getCallForEdit(questionBank.getHiddencode(),
								questionBank);
				//model.questionData(questionBank, request);
				if (questionBank.getFromFlag().equals("Subject")
						|| questionBank.getFromFlag().equals("SubjectList")) {
					setSubjectQues();
				} else {
					setCategoryQues();
				}
				// questionBank.setEditFlag("false");
				model.terminate();
			} catch (Exception e) {
				// TODO: handle exception
			}
			return "view";
		}
	

	/**
	 * following function is called to reset the fields.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String reset()throws Exception{
		
		/*questionBank.setOption("");
		questionBank.setOptionCode("");*/
			questionBank.setSubject("");
//			questionBank.setSubjectCode("");
			questionBank.setQuestion("");
			questionBank.setMark("");
			questionBank.setUpload("");
			questionBank.setSubjective("");
			questionBank.setOptionItt("");
			questionBank.setFlag("");
			questionBank.setLevel("");
			questionBank.setSrNo("");
			questionBank.setQuesDesc("");
			questionBank.setQuesType("");
			questionBank.setQuesCode("");
			questionBank.setQuestionCode("");
			questionBank.setOptionName("");
			questionBank.setTableLength("");
			questionBank.setHiddenEdit("");
			questionBank.setHiddensub("");
			questionBank.setQuestionItt("");
			questionBank.setHiddenView("");
			questionBank.setFlagItt("");
			questionBank.setHiddenCode("");
			questionBank.setHiddenCode_option("");
			questionBank.setQsnWtg("");
			questionBank.setCompLevel("");
			questionBank.setLimit("");
			questionBank.setAnsOptions("");
			questionBank.setOptionFlag("false");
			questionBank.setCategory("");
//			questionBank.setCategoryCode("");
			questionBank.setQsnStatus("");
			questionBank.setOptionTextarea("");
			questionBank.setQuestionView("false");
			//questionBank.setMyPage("");
			//questionBank.setShow("");
			questionBank.setUploadFileName("");			
			return SUCCESS;
	}
	
	/**
	 * following function to go to the Add New and Search button panel
	 * @return
	 * @throws Exception
	 */
	public String cancel() throws Exception{
		try {
					getNavigationPanel(1);
		reset();
		QuestionBankModel model=new QuestionBankModel();
		model.initiate(context, session);
		model.questionData(questionBank, request);
		model.terminate();
		//return "readOnly";	
		} catch (Exception e) {
			// TODO: handle exception
		}	
		return "view";
	}
	/**
	 * following function is called when the cancel button is clicked and the application is about to be modified.
	 * If for any reason the user does not want to modify the record then he/she can click cancel and the application will appear in 
	 * Edit,Delete and Cancel mode.The application will appear in View mode.  
	 * @return
	 */
	public String cancelSecond() throws Exception{
		try {
			logger.info("Cancel Second---->");
			getNavigationPanel(1);
			QuestionBankModel model = new QuestionBankModel();
			model.initiate(context, session);
			model.questionData(questionBank, request);
			questionBank.setFlagShow("true");
			reset();
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "view";
	}

	public String cancelFirst() throws Exception{
		String str="";
		try {
			logger.info("Cancel First---->");
			QuestionBankModel model = new QuestionBankModel();
			model.initiate(context, session);
			if (questionBank.getEditFlag().equals("true")) {
				logger.info("If-------->");
				model.getQsnDetailsRec(questionBank);
				model.getOptionDet(questionBank);
				model.questionData(questionBank, request);
				getNavigationPanel(3);
				str = "view";
			} else {
				logger.info("Else-------->");
				getNavigationPanel(1);
				questionBank.setFlagShow("true");
				model.questionData(questionBank, request);
				reset();
				str = "view";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return str;
	}
	
	
	/**
	 * following function is called when delete button is clicked.The application will appear in Add New and Search mode.
	 * @return
	 * @throws Exception
	 */
	public String delete()throws Exception{
		try {
			getNavigationPanel(1);
			questionBank.setFlagShow("true");
			QuestionBankModel model = new QuestionBankModel();
			model.initiate(context, session);
			boolean flag = model.delete(questionBank);
			if (flag) {
				addActionMessage("Record deleted successfully");
			} else
				addActionMessage("This record is referenced in other resources.So cannot be  deleted. !");
			model.questionData(questionBank, request);
			model.terminate();
			reset();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "view";
	}
	
	
	/**
	 * following function is called when any row in the Option List is deleted. 
	 * @return
	 * @throws Exception
	 */
	public String deleteForOption() throws Exception {
		try {
			getNavigationPanel(4);
			String[] srNo = request.getParameterValues("optionCode");
			String[] option = request.getParameterValues("optionName");
			String[] flagItt = request.getParameterValues("flagItt");
			String[] delete = null;
			if (option != null) {
				delete = new String[option.length];
			}
			int j = 1;
			for (int i = 0; i < option.length; i++) {
				delete[i] = (String) request.getParameter("hdeleteOp" + j);
			}
			QuestionBankModel model = new QuestionBankModel();
			model.initiate(context, session);
			model.deleteOption(questionBank, srNo, option, flagItt, delete);
			//	model.questionData(questionBank, request);
			questionBank.setParaId("");
			questionBank.setOptionId("");
			questionBank.setOptionName("");
			questionBank.setHflag("");
			questionBank.setOption("");
			questionBank.setOptionTextarea("");
			questionBank.setFlagView("true");
			questionBank.setFlagShow("true");
			questionBank.setQuestionView("true");
			if (questionBank.getFromFlag().equals("Subject")
					|| questionBank.getFromFlag().equals("SubjectList")) {
				setSubjectQues();
			} else {
				setCategoryQues();
			}
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "view";
	}
	
	/**
	 * following function is called when delete button is clicked in the list.
	 * @return
	 * @throws Exception
	 */	
	public String delete1() throws Exception {
		try {
			getNavigationPanel(4);
			questionBank.setFlagShow("true");
			String code[] = request.getParameterValues("hdeleteCode");
			QuestionBankModel model = new QuestionBankModel();
			model.initiate(context, session);
			boolean result = model.deletecheckedRecords(questionBank, code);
			if (result) {
				addActionMessage(getText("delMessage", ""));
			} else {
				addActionMessage("One or more records can't be deleted \n as they are associated with some other records.");
			}
			//model.questionData(questionBank, request);
			reset();
			questionBank.setFlagView("true");
			questionBank.setFlagShow("true");
			questionBank.setQuestionView("true");
			if (questionBank.getFromFlag().equals("Subject")
					|| questionBank.getFromFlag().equals("SubjectList")) {
				setSubjectQues();
			} else {
				setCategoryQues();
			}
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "view";

	}
	

	
	public String deleteOption()throws Exception {
		try {
			String code[] = request.getParameterValues("hdeleteOp");
			//String [] srNo=request.getParameterValues("srNo");
			String[] option = request.getParameterValues("optionName");
			String[] flagItt = request.getParameterValues("flagItt");
			QuestionBankModel model = new QuestionBankModel();
			model.initiate(context, session);
			//	model.deleteOption(questionBank,code,option,flagItt);
			model.questionData(questionBank, request);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;

	}

/**
 * following function is called when any record is selected from the search window.
 * @return
 */
public String getDetails(){
	try {
		getNavigationPanel(3);
		QuestionBankModel model = new QuestionBankModel();
		model.initiate(context, session);
		questionBank.setFlagView("true");
		model.getQsnDetails(questionBank);
		model.questionData(questionBank, request);
		questionBank.setAnsFlgView(true);
		model.getOptionDet(questionBank);
		model.terminate();
	} catch (Exception e) {
		// TODO: handle exception
	}
	return "view";
}
/**
 * following function is called when edit button is clicked.
 * @return
 */
public String edit(){
	try {
		getNavigationPanel(4);
		QuestionBankModel model = new QuestionBankModel();
		model.initiate(context, session);
		questionBank.setCancelFlag("true");
		model.getQsnDetails(questionBank);
		model.getOptionDet(questionBank);
		model.questionData(questionBank, request);
		questionBank.setEditFlag("true");
		model.terminate();
	} catch (Exception e) {
		// TODO: handle exception
	}
	return "success";	
}
	
/*public String callClose() throws Exception{
	System.out.println("in call close================= ");
	getNavigationPanel(4);
	String [] option=request.getParameterValues("optionName");
	System.out.println("option.length)================== "+option.length);
	String [] flagItt=request.getParameterValues("flagItt"); 
	QuestionBankModel model=new QuestionBankModel();
	 model.initiate(context,session); 
    model.getOptionRec(questionBank, flagItt,option);
	model.questionData(questionBank,request);
	questionBank.setEditFlag("true");
	model.terminate();
	return "success";	
}
*/
public String f9action() throws Exception {
	/*
	 * 
	 * Select the Voucher Head from Voucher Head master F9
	 * 
	 * 
	 */

	String query = "  SELECT  NVL(SUBJECT_NAME,' '),SUBJECT_CODE FROM HRMS_REC_SUBJECT WHERE SUBJECT_STATUS='A' ORDER BY SUBJECT_CODE  ";

	String[] headers = { getMessage("sub") };

	String[] headerWidth = {"80"};

	String[] fieldNames = {"subject","subjectCode"};

	int[] columnIndex = { 0,1 };

	String submitFlag = "false";

	String submitToMethod = "";

	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);

	return "f9page";
}

public String f9actionCat() throws Exception {
	
	String query = "  SELECT NVL(CAT_NAME,' '),CAT_CODE FROM HRMS_REC_CATEGORY WHERE" 
			+" CAT_SUB_CODE="+questionBank.getSubjectCode()+" ORDER BY CAT_CODE  ";

	String[] headers = { getMessage("cat") };

	String[] headerWidth = {"80"};

	String[] fieldNames = { "category","categoryCode" };

	int[] columnIndex = {0,1};

	String submitFlag = "false";

	String submitToMethod = "";

	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);

	return "f9page";
}

public String f9Search() throws Exception {
	  String query =" SELECT HRMS_REC_SUBJECT.SUBJECT_NAME,NVL(CAT_NAME,' '),NVL(QUES_NAME,' '),DECODE(QUES_TYPE,'O','Objective','S','Subjective','B','Both'),QUES_CODE "+ 
				  " FROM HRMS_REC_QUESBANK"+ 
				  " Inner join HRMS_REC_SUBJECT on   (HRMS_REC_QUESBANK.QUES_SUB_CODE=HRMS_REC_SUBJECT.SUBJECT_CODE)"+ 
				  " LEFT JOIN HRMS_REC_CATEGORY ON(HRMS_REC_CATEGORY.CAT_CODE=HRMS_REC_QUESBANK.QUES_CAT_CODE)"+
				  " ORDER BY QUES_CODE " ;
	String[] headers = { getMessage("sub") ,getMessage("cat"),getMessage("qsn"),getMessage("qsnType")};
	String[] headerWidth = {"25","25","50","30"};
	String[] fieldNames = { "subject","category","question","ansOptions","quesCode"};
	int[] columnIndex = {0,1,2,3,4};
	String submitFlag = "true";
	String submitToMethod = "QuestionBankAction_getDetails.action";
	setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
			submitFlag, submitToMethod);

	return "f9page";
}
	
public String report()throws Exception{
	QuestionBankModel model=new QuestionBankModel();
	model.initiate(context, session);
	String[]label={"Sr.No",getMessage("sub"),getMessage("qsn"),getMessage("sts")};
	model.generateReport(questionBank,response,label);
	model.terminate();
	return null;
}

/**
 * following function is called when Add Answer Option button is clicked.
 * Added by Pradeep on Dt:19-06-2009
 * @return
 */	
  public String showAnsOpt(){
	try {
		String[] srNo = request.getParameterValues("srNo");
		String[] option = request.getParameterValues("optionName");
		String[] flagItt = request.getParameterValues("flagItt");
		QuestionBankModel model = new QuestionBankModel();
		model.initiate(context, session);
		model.addOptionShow(questionBank, srNo, option, flagItt, srNo);
		model.terminate();
	} catch (Exception e) {
		// TODO: handle exception
	}
	return "viewAns";  
  }
  
  /**
   * following function is called when the Edit button is clicked in the option list.
   * It's purpose is to set the Option value to the Answer Option and the answer value to Answer Value 
   * in the Answer Options window. 
   * @return
   */
  public String callUpdate(){
	  try {
		String opt = request.getParameter("opt");
		String id = request.getParameter("para");
		String flg = request.getParameter("flg");
		questionBank.setOptionTextarea(opt);
		questionBank.setParaId(id);
		if (flg.equals("Right"))
			questionBank.setFlag("Y");
		else
			questionBank.setFlag("N");
	} catch (Exception e) {
		// TODO: handle exception
	}
	return "viewAns";
  }
  

  /**
	 * Method : openAttachedFile().
	 * Purpose : Method to open attached file.
	 * @throws IOException - Exception.
	 */
	public void openUploadedFile() throws IOException {
		try {
			String addedFile = request.getParameter("fileName");
			//final String addedFile = this.brdBean.getAttachFile();
			final String[] extension = addedFile.replace(".", "#").split("#");
			final String ext = extension[extension.length - 1];
			String mimeType = "";
			final String dataPath = this.questionBank.getDataPath();
			final String filePath = dataPath + addedFile;
			OutputStream oStream = null;
			FileInputStream fsStream = null;
			try {
				final String applnPdf = "pdf";
				final String applnDoc = "doc";
				final String applnXls = "xls";
				final String applnXlsx = "xlsx";
				final String applnJpg = "jpg";
				final String applnTxt = "gif";
				final String applnGif = "txt";

				final String mimeTypeAcrobat = "acrobat";
				final String mimeTypeMSWord = "msword";
				final String mimeTypeMSExcel = "msexcel";
				final String mimeTypeJpg = "jpg";
				final String mimeTypeTxt = "gif";
				final String mimeTypeGif = "txt";

				if (applnPdf.equals(ext)) {
					mimeType = mimeTypeAcrobat;
				} else if (applnDoc.equals(ext)) {
					mimeType = mimeTypeMSWord;
				} else if (applnXls.equals(ext)) {
					mimeType = mimeTypeMSExcel;
				} else if (applnXlsx.equals(ext)) {
					mimeType = mimeTypeMSExcel;
				} else if (applnJpg.equals(ext)) {
					mimeType = mimeTypeJpg;
				} else if (applnTxt.equals(ext)) {
					mimeType = mimeTypeTxt;
				} else if (applnGif.equals(ext)) {
					mimeType = mimeTypeGif;
				}

				response.setHeader("Content-type", "application/" + mimeType);
				response.setHeader("Content-disposition",
						"attachment;filename=\"" + addedFile + "\"");

				int iChar;
				fsStream = new FileInputStream(filePath);
				oStream = response.getOutputStream();

				while ((iChar = fsStream.read()) != -1) {
					oStream.write(iChar);
				}
			} catch (final Exception e) {
				e.printStackTrace();
			} finally {
				if (fsStream != null) {
					fsStream.close();
				}

				if (oStream != null) {
					oStream.flush();
					oStream.close();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	
}