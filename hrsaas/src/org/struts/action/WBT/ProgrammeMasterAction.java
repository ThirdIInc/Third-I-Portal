package org.struts.action.WBT;

import org.paradyne.bean.WBT.ProgrammeMaster;
import org.paradyne.model.WBT.ProgrammeMasterModel;
import org.paradyne.model.WBT.TechPortalWebServiceModel;

import org.struts.lib.ParaActionSupport;

public class ProgrammeMasterAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;

	ProgrammeMaster programBean;
	public void prepare_local() throws Exception {
		programBean = new ProgrammeMaster();
		programBean.setMenuCode(2311);
	}

	public Object getModel() {
		return programBean;
	}

	public ProgrammeMaster getProgramBean() {
		return programBean;
	}

	public void setProgramBean(ProgrammeMaster programBean) {
		this.programBean = programBean;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		ProgrammeMasterModel model = new ProgrammeMasterModel();
		model.initiate(context, session);
		model.getProgramList(request, programBean);
		model.terminate();
	}

	public String input() throws Exception {
		try {
			ProgrammeMasterModel model = new ProgrammeMasterModel();
			model.initiate(context, session);
			model.getProgramList(request, programBean);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String addNew() {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		cancel();
		return "programmeJSP";
	}
	
	
	public String sendProgramListToTechPortal() throws Exception
	{
		
		String programXml ="";
		try {
			
			programXml="<PROGRAM>"
			+"<PROGRAMCODE>"+programBean.getProgrameId()+"</PROGRAMCODE>"
			+"<PROGRAMNAME>"+programBean.getProgrameName()+"</PROGRAMNAME>"
			+"</PROGRAM>";
			
			System.out.println("hello---programXml-----------"+programXml);
			TechPortalWebServiceModel model=new TechPortalWebServiceModel();
			String resultAddProgram=model.addPrograms(programXml);

			System.out.println("resultAddProgram  "+resultAddProgram);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return programXml;
	}

	public String save()throws Exception {
		boolean result = false;
		try {
			String[] mod = request.getParameterValues("moduleItt");
			String[] modCode = request.getParameterValues("moduleCodeItt");
			String[] isOrder = request.getParameterValues("hiddenOrderCheck");
			String[] order = request.getParameterValues("moduleOrder");
			String[] content = request.getParameterValues("hiddenContentCheck");
			String[] question = request.getParameterValues("hiddenQuesCheck");
			String[] passMod = request.getParameterValues("passMarksMod");
			String[] noOfAttempts = request.getParameterValues("noOfAttempts");
			ProgrammeMasterModel model = new ProgrammeMasterModel();
			model.initiate(context, session);

			if (programBean.getProgrameId().equals("")) {

				String chkDupQuery = "SELECT PROGRAM_NAME FROM  WBT_PROGRAM_HDR WHERE UPPER(PROGRAM_NAME) = '"
						+ programBean.getProgrameName().toUpperCase().trim()
						+ "'";
				Object[][] progDup = model.getSqlModel().getSingleResult(
						chkDupQuery);
				if (progDup != null && progDup.length > 0) {
					addActionMessage("Duplicate Record Found");
					model.getRecord(request, programBean);
					return "programmeJSP";
				}
				result = model.save(programBean, mod, modCode, isOrder, order,
						content, question, passMod, noOfAttempts);
				if (result) {
					addActionMessage("Record Saved Successfully");
					try {
						sendProgramListToTechPortal();
					} catch (Exception e) {
						// TODO: handle exception
					}
					
				} else {
					addActionMessage(getMessage("save.error"));
				}
			} else {

				String chkDupQuery = "SELECT PROGRAM_NAME FROM  WBT_PROGRAM_HDR WHERE UPPER(PROGRAM_NAME) = '"
						+ programBean.getProgrameName().toUpperCase().trim()
						+ "' AND PROGRAM_ID NOT IN ("
						+ programBean.getProgrameId() + ")";
				Object[][] progDup = model.getSqlModel().getSingleResult(
						chkDupQuery);
				if (progDup != null && progDup.length > 0) {
					addActionMessage("Duplicate Record Found");
					model.getRecord(request, programBean);
					return "programmeJSP";
				}

				result = model.update(programBean, mod, modCode, isOrder,
						order, content, question, passMod, noOfAttempts);
				if (result) {
					addActionMessage("Record Saved Successfully");
					try {
						sendProgramListToTechPortal();
					} catch (Exception e) {
						// TODO: handle exception
					}
					
				} else {
					addActionMessage("Record can not be saved");
				}
			}
			model.getRecord(request, programBean);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "programmeJSP";
	}

	public String f9Module() throws Exception {

		String[] moduleCodeItt = request.getParameterValues("moduleCodeItt");
		String moduleCode = "0";
		if (moduleCodeItt != null) {
			for (int i = 0; i < moduleCodeItt.length; i++) {
				if (i == 0) {
					moduleCode += moduleCodeItt[i];
				} else {
					moduleCode += "," + moduleCodeItt[i];
				}
			}
		}
		System.out.println("In f9Module--------");
		String query = " SELECT SUBJECT_CODE, SUBJECT_NAME FROM HRMS_REC_SUBJECT "
				+ "  WHERE SUBJECT_CODE NOT IN("
				+ moduleCode
				+ ") "
				+ " AND SUBJECT_STATUS ='A' ORDER BY SUBJECT_CODE  ";
		String[] headers = { getMessage("moduleCode"), getMessage("moduleNm") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "moduleCode", "module" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String callForEdit() {
		try {
			ProgrammeMasterModel model = new ProgrammeMasterModel();
			model.initiate(context, session);
			model.getRecord(request, programBean);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "programmeJSP";
	}

	public String getRecords() {
		try {
			ProgrammeMasterModel model = new ProgrammeMasterModel();
			model.initiate(context, session);
			model.getRecord(request, programBean);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "programmeJSP";
	}

	public String f9action() {
		String query = " SELECT PROGRAM_ID, PROGRAM_NAME FROM WBT_PROGRAM_HDR ORDER BY PROGRAM_ID";

		String[] headers = { getMessage("programeID"), getMessage("programe") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "programeId", "programeName" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "true";
		String submitToMethod = "ProgrammeMaster_getRecords.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String addModule() {
		ProgrammeMasterModel model = new ProgrammeMasterModel();
		model.initiate(context, session);

		String SrNo[] = request.getParameterValues("SrNo");
		String modName[] = request.getParameterValues("moduleItt");
		String modCode[] = request.getParameterValues("moduleCodeItt");
		String modOrder[] = request.getParameterValues("moduleOrder");
		String orderReq[] = request.getParameterValues("hiddenOrderCheck");
		String contentReq[] = request.getParameterValues("hiddenContentCheck");
		String enableQue[] = request.getParameterValues("hiddenQuesCheck");
		String passMarks[] = request.getParameterValues("passMarksMod");

		String result = model.addModuleList(request, programBean, SrNo, modName, modCode,
				modOrder, orderReq, contentReq, enableQue, passMarks);
		if(result.equals("Success")){
			
		} else if(result.equals("Duplicate")){
			addActionMessage("Duplicate Program name found");
		} else {
			
		}		
		programBean.setModuleCode("");
		programBean.setModule("");
		model.terminate();
		
		
		
		return "programmeJSP";
	}

	public String cancel() {
		programBean.setProgrameId("");
		programBean.setProgrameName("");
		programBean.setType("");
		programBean.setDays("");
		programBean.setDuration("");
		return "programmeJSP";
	}

	public String callManage() {
		ProgrammeMasterModel model = new ProgrammeMasterModel();
		model.initiate(context, session);
		try {
			String programID = request.getParameter("programID");
			String moduleID = request.getParameter("moduleID");
			String progName = request.getParameter("progName");
			String modName = request.getParameter("modName");
			/*
			 * String query = " SELECT * FROM WBT_PROGRAM_SECTION " + " WHERE
			 * PROGRAM_ID =" + programID + " AND MODULE_ID =" + moduleID;
			 * 
			 * Object data[][] = model.getSqlModel().getSingleResult(query);
			 */
			programBean.setSectionModuleName(modName);
			programBean.setSectionModuleCode(moduleID);
			programBean.setSectionProgrameName(progName);
			programBean.setSectionProgrameCode(programID);
			/*
			 * if (data != null && data.length > 0) {
			 * model.setSectionData(programBean,programID,moduleID); } else { }
			 */
			model.callSection(request, programBean, programID, moduleID,
					progName, modName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
		return "manageSectionPage";
	}

	public String saveSection() {
		ProgrammeMasterModel model = new ProgrammeMasterModel();
		model.initiate(context, session);
		boolean SectionResult = false;
		try {
			String[] sectCode = request.getParameterValues("sectionCodeItt");
			String[] pMark = request.getParameterValues("passMarkSecItt");
			String[] content = request.getParameterValues("hiddenSecContChk");
			String[] question = request.getParameterValues("hiddenSecQuesChk");
			String[] secOrder = request.getParameterValues("sectionOrder");
			String secProgCode = programBean.getSectionProgrameCode();
			String secModCode = programBean.getSectionModuleCode();			
			if (programBean.getSectionProgrameCode().equals("")) {
				SectionResult = model.saveSection(programBean, sectCode, pMark,
						content, question, secOrder);
				if (SectionResult) {
					addActionMessage("Record Saved Successfully");
				} else {
					addActionMessage(getMessage("save.error"));
				}
			} else {
				SectionResult = model.updateSection(programBean, sectCode,
						pMark, content, question, secOrder);
				if (SectionResult) {
					addActionMessage("Record Updated  Successfully");
				} else {
					addActionMessage("Record Cannot be Updated");
				}
			}			
			/*model.getSectionRecord(request, programBean, secProgCode,
					secModCode);
			*/
			model.callSection(request, programBean, secProgCode, secModCode,
					programBean.getSectionProgrameName(), programBean.getSectionModuleName());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
		return "manageSectionPage";
	}

	public String backSection() {
		try {
			ProgrammeMasterModel model = new ProgrammeMasterModel();
			model.initiate(context, session);
			programBean.setProgrameId(programBean.getSectionProgrameCode());
			model.getRecord(request, programBean);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "programmeJSP";
	}

	public String backQuestion() {
		try {
			ProgrammeMasterModel model = new ProgrammeMasterModel();
			model.initiate(context, session);
			programBean.setProgrameId(programBean.getQueProgrameCode());

			if (programBean.getQuestionFlag().equalsIgnoreCase("section")) {
				String programID = programBean.getQueProgrameCode();
				String moduleID = programBean.getQueModuleCodeItt();
				String progName = programBean.getQueProgrameName();
				String modName = programBean.getQueModuleItt();
				programBean.setSectionModuleName(programBean.getQueModuleItt());
				programBean.setSectionModuleCode(programBean
						.getQueModuleCodeItt());
				programBean.setSectionProgrameName(programBean
						.getQueProgrameName());
				programBean.setSectionProgrameCode(programBean
						.getQueProgrameCode());
				/*
				 * if (data != null && data.length > 0) {
				 * model.setSectionData(programBean,programID,moduleID); } else { }
				 */
				model.callSection(request, programBean, programID, moduleID,
						progName, modName);
				return "manageSectionPage";
			} else {
				model.getRecord(request, programBean);
				model.terminate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "programmeJSP";
	}

	public String callQuesManage() {
		System.out.println("In callQuesManage---------");
		ProgrammeMasterModel model = new ProgrammeMasterModel();
		model.initiate(context, session);
		try {
			// String modID = request.getParameter("moduleCodeItt");
			// String questionFalg = request.getParameter("qFlag");
			// programBean.setSectionFlag(questionFalg);
			String programID = request.getParameter("programID");
			String moduleID = request.getParameter("moduleID");
			String progName = request.getParameter("progName");
			String modName = request.getParameter("modName");
			// String questionFalg = request.getParameter("questionFalg");
			String sectionCode = request.getParameter("sectionCode");
			String sectionName = request.getParameter("sectionName");
			programBean.setProgrameId(programID);
			
			model.callQuestion(request, programBean, programID, moduleID,
					progName, modName, sectionCode, sectionName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
		return "manageQuestionPage";
	}
	
	public String callQuesManagePageView(){
		System.out.println("In callQuesManagePageView---------");
		ProgrammeMasterModel model = new ProgrammeMasterModel();
		model.initiate(context, session);
		try {
			// String modID = request.getParameter("moduleCodeItt");
			// String questionFalg = request.getParameter("qFlag");
			// programBean.setSectionFlag(questionFalg);
			String programID = request.getParameter("programID");
			String moduleID = request.getParameter("moduleID");
			String progName = request.getParameter("progName");
			String modName = request.getParameter("modName");
			// String questionFalg = request.getParameter("questionFalg");
			String sectionCode = request.getParameter("sectionCode");
			String sectionName = request.getParameter("sectionName");
			programBean.setProgrameId(programID);
			
			model.callQuestionPageView(request, programBean, programID, moduleID,
					progName, modName, sectionCode, sectionName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
		return "manageQuestionPage";
	}

	public String f9Question() {

		ProgrammeMasterModel model = new ProgrammeMasterModel();
		model.initiate(context, session);
		
		String quesCodeItt = request.getParameter("quesCodeItt");
		String programID = request.getParameter("programID");
		
		String queryQuesCode =  " SELECT WBT_PROGRAM_QUES_DTL.QUES_CODE " 
			+ " FROM WBT_PROGRAM_QUES_DTL" 
			+ " WHERE WBT_PROGRAM_QUES_DTL.PROGRAM_QUES_ID = "
			+ " (SELECT PROGRAM_QUES_ID  FROM WBT_PROGRAM_QUES_HDR  WHERE PROGRAM_ID = "+programID+" ";
		if (programBean.getQueModuleCodeItt() != null
				&& !programBean.getQueModuleCodeItt().equals("")) {
			queryQuesCode += " AND MODULE_ID = "+ programBean.getQueModuleCodeItt(); 
			queryQuesCode += " AND SECTION_ID is null "; 
		}
		if (programBean.getQueSectionCodeItt() != null
				&& !programBean.getQueSectionCodeItt().equals("")) {
			queryQuesCode += " AND SECTION_ID =  " +  programBean.getQueSectionCodeItt(); 
		}
		
		queryQuesCode += ")";
		
		
		Object[][] queData = model.getSqlModel().getSingleResult(queryQuesCode);
		if (queData != null && queData.length > 0) {
			for (int i = 0; i < queData.length; i++) {
				if (i == 0) {
					quesCodeItt = queData[i][0].toString();
				} else {
					quesCodeItt += "," + queData[i][0].toString();
				}
			}
		}
		
		String quesCode = "";
		if (quesCodeItt != null && !quesCodeItt.equals("")) {
			System.out.println("quesCodeItt[i]---------" + quesCodeItt);
			quesCode = " AND QUES_CODE NOT IN(" + quesCodeItt + ")";
		} else {
			quesCode = "";
		}
		String cond = "";
		if (programBean.getQueModuleCodeItt() != null
				&& !programBean.getQueModuleCodeItt().equals("")) {
			cond = " WHERE QUES_SUB_CODE=" + programBean.getQueModuleCodeItt();
			cond = cond + " AND QUES_CAT_CODE IS NULL ";
		}
		if (programBean.getQueSectionCodeItt() != null
				&& !programBean.getQueSectionCodeItt().equals("")) {
			cond = " WHERE QUES_CAT_CODE=" + programBean.getQueSectionCodeItt();
		}
		/*
		 * else { cond = " WHERE 1=1 "; }
		 */
		String query = "SELECT QUES_CODE, QUES_NAME, DECODE(QUES_TYPE,'O','Objective','S','Subjective'),"
				+ " DECODE(QUES_LEVEL,'E','Easy','M','Medium','H','Hard')"
				+ " FROM HRMS_REC_QUESBANK "
				+ cond
				+ quesCode
				+ " AND QUES_STATUS ='A' ORDER BY QUES_CODE";

		String header = getMessage("division");
		String textAreaId = "paraFrm_quesName";
		String hiddenFieldId = "paraFrm_quesCode";
		String submitFlag = "false";
		String submitToMethod = "";
		setMultiSelectF9(query, header, textAreaId, hiddenFieldId, submitFlag,
				submitToMethod);
		return "f9multiSelect";
	}

	public String addQuestion() {
		ProgrammeMasterModel model = new ProgrammeMasterModel();
		boolean result = false;
		model.initiate(context, session);
		try {
			String SrNo[] = request.getParameterValues("SrNoQ");
			String quesName[] = request.getParameterValues("quesItt");
			String quesCode[] = request.getParameterValues("quesCodeItt");
			String quesType[] = request.getParameterValues("quesTypeItt");
			String quesLevel[] = request.getParameterValues("quesLevelItt");
			String quesMark[] = request.getParameterValues("quesMarkItt");
			String[] queOrder = request.getParameterValues("quesOrderItt");
			model.addQuestionList(request,programBean, SrNo, quesCode, quesName,
					quesType, quesLevel, quesMark, queOrder);
			programBean.setQuesCode("");
			programBean.setQuesName("");			
			
			
			model
			.callQuestion(request, programBean, programBean
					.getQueProgrameCode(), programBean
					.getQueModuleCodeItt(), programBean
					.getQueProgrameName(), programBean.getQueModuleItt(),
					programBean.getQueSectionCodeItt(), programBean
							.getQueSectionItt());

			model.terminate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manageQuestionPage";
	}

	public String deleteQues() {

		ProgrammeMasterModel model = new ProgrammeMasterModel();
		model.initiate(context, session);
		boolean result = false;
		try {
			String[] queID = request.getParameterValues("quesCodeItt");
			String[] queName = request.getParameterValues("quesItt");
			String[] queType = request.getParameterValues("quesTypeItt");
			String[] queLevel = request.getParameterValues("quesLevelItt");
			String[] queMark = request.getParameterValues("quesMarkItt");
			String[] queOrder = request.getParameterValues("quesOrderItt");

			result = model.deleteQuestion(request, programBean, queID, queName, queType,
					queLevel, queMark, queOrder);
			
			model
			.callQuestion(request, programBean, programBean
					.getQueProgrameCode(), programBean
					.getQueModuleCodeItt(), programBean
					.getQueProgrameName(), programBean.getQueModuleItt(),
					programBean.getQueSectionCodeItt(), programBean
							.getQueSectionItt());
			
			if (result) {
				System.out.println("Save in Action");
				addActionMessage("Question deleted Successfully");
			} else {
				addActionMessage(getMessage("save.error"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
		return "manageQuestionPage";
	}

	public String saveQuestion() {

		ProgrammeMasterModel model = new ProgrammeMasterModel();
		model.initiate(context, session);
		boolean result = false;
		try {
			String[] queID = request.getParameterValues("quesCodeItt");
			String[] queName = request.getParameterValues("quesItt");
			String[] queType = request.getParameterValues("quesTypeItt");
			String[] queLevel = request.getParameterValues("quesLevelItt");
			String[] queMark = request.getParameterValues("quesMarkItt");
			String[] queOrder = request.getParameterValues("quesOrderItt");
			result = model.saveQuestion(programBean, queID, queName, queType,
					queLevel, queMark, queOrder);
			if (result) {
				System.out.println("Save in Action");
				addActionMessage("Question Saved Successfully");
			} else {
				addActionMessage(getMessage("save.error"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model
				.callQuestion(request, programBean, programBean
						.getQueProgrameCode(), programBean
						.getQueModuleCodeItt(), programBean
						.getQueProgrameName(), programBean.getQueModuleItt(),
						programBean.getQueSectionCodeItt(), programBean
								.getQueSectionItt());

		model.terminate();
		programBean.setProgrameId(programBean.getQueProgrameCode());
		return "manageQuestionPage";
	}

	public String setInstruction() {
		try {
			String from = request.getParameter("from");
			System.out.println("from:" + from);
			if (from == null || from.equals("")) {
				from = "programedit";
			}
			ProgrammeMasterModel model = new ProgrammeMasterModel();
			model.initiate(context, session);
			model.setInstruction(programBean, request, from);
			model.terminate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "instructionPage";
	}

	public String updateInstruction() throws Exception {
		String from = request.getParameter("from");
		if (from == null || from.equals("")) {
			from = "programedit";
		}
		request.setAttribute("labelName", "Instructions");
		request.setAttribute("from", from);
		ProgrammeMasterModel model = new ProgrammeMasterModel();
		model.initiate(context, session);
		boolean result = false;
		if (!programBean.getProgrameId().equals("")) {
			result = model.updateInstruction(programBean, request);
			if (result) {
				addActionMessage("Record updated successfully");
			} else {
				addActionMessage("Record can not be updated");
			}
		} else {
			addActionMessage("Record can not be updated");
		}
		model.terminate();
		/*if (from.equals("programedit")) {
			return callForEdit();
		} else if (from.equals("programlist")) {
			return input();
		} else {
			return input();
		}*/
		setInstruction();
		return "instructionPage";
	}

	public String callPage() throws Exception {
		try {
			ProgrammeMasterModel model = new ProgrammeMasterModel();
			model.initiate(context, session);
			model.getProgramList(request, programBean);
			model.terminate();
			cancel();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}

}
