package org.struts.action.exam;

import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.exam.ExamMaster;
import org.paradyne.model.exam.ExamModel;

/**
 * 
 * @author Prasad  
 * and Pradeep Sahoo
 *
 */

public class ExamMasterAction extends ParaActionSupport {

	private static final long serialVersionUID = 1L;

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.action.exam.ExamMasterAction.class);

	ExamMaster bean;

	public void prepare_local() throws Exception {
		bean = new ExamMaster();
		bean.setMenuCode(572);
	}
	
	public Object getModel() {
		return bean;
	}

	public ExamMaster getBean() {
		return bean;
	}

	public void setBean(ExamMaster bean) {
		this.bean = bean;
	}

	public String input() throws Exception {
		/*Default Method with default modeCode(1)*/
		getNavigationPanel(1);
		ExamModel model = new ExamModel();
		model.initiate(context, session);
		model.getRecords(bean, request);
		//bean.setFlagView("false");
		bean.setFlagShow("true");
		model.terminate();
		return "view";
	}

	public String addNew() throws Exception {
		ExamModel model=null;
		try {
			/*Default Method with save modeCode(2)*/
			getNavigationPanel(4);
			model = new ExamModel();
			model.initiate(context, session);
			//bean.setEditFlag("false");
			bean.setFlagShow("false");
			bean.setEnableAll("Y");
			bean.setSubjectCode("");
			bean.setSubjectAbbr("");
			bean.setSubjectName("");
		} catch (Exception e) {
			// TODO: handle exception
		}
		model.terminate();
		return "view";
	}

	public String cancelSecond() throws Exception{
		reset();
		logger.info("Cancel Second---->");
		getNavigationPanel(1);
		ExamModel model=new ExamModel();
		model.initiate(context, session);
		model.getRecords(bean, request);
		bean.setFlagShow("true");		
		model.terminate();
		return "view";
	}
	
	public String save() throws Exception {
		String result = "";
		String page = "";
		ExamModel model = new ExamModel();
		model.initiate(context, session);
		Object[] sNo = request.getParameterValues("SrNo");
		Object[] catName = request.getParameterValues("subjectCategoryNameItr");

		if (bean.getSubjectCode().equals("")) {
			result = model.addSubject(bean, request);
			if (result.equals("saved")) {
				getNavigationPanel(2);
				// bean.setCatList(true);
				// bean.setFlagView("true");
				addActionMessage(getText("addMessage", ""));
				page = "view";
			} else if (result.equals("duplicate")) {
				getNavigationPanel(2);
				addActionMessage(getText("Duplicate entry found!"));
				// bean.setCatList(true);
				bean.setEditFlag("false");
				page = "view";
			} else {
				getNavigationPanel(2);
				addActionMessage(getText("Record can not be saved!"));
				// bean.setCatList(true);
				bean.setEditFlag("false");
				page = "view";
			}
		} else {
			result = model.modSubject(bean, request);

			if (result.equals("modified")) {
				getNavigationPanel(2);
				// bean.setFlagView("true");
				addActionMessage(getText("Record updated successfully!"));
				// bean.setCatList(true);
				page = "view";
			} else if (result.equals("duplicate")) {
				getNavigationPanel(2);
				addActionMessage(getText("Duplicate entry found!"));
				// bean.setCatList(true);
				bean.setEditFlag("false");
				page = "view";
			} else {
				getNavigationPanel(2);
				addActionMessage(getText("Record can not be updated!"));
				// bean.setCatList(true);
				bean.setEditFlag("false");
				page = "view";
			}
		}
		model.getSubjectOnDoubleClick(bean, request);
		model.getRecords(bean, request);
		// bean.setLoadFlag(false);
		// bean.setAddFlag(true);

		model.terminate();
		return page;
	}

	/**
	 * following function is called when delete button is clicked in the jsp page
	 * @return
	 */
	public String delete() throws Exception{
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(1);
		ExamModel model=new ExamModel();
		model.initiate(context,session);
		bean.setFlagShow("true");
		boolean result=model.deleteSubject(bean);
		if(result){
			addActionMessage(getText("Record deleted successfully!"));
			reset();
		}else{
			addActionMessage(getText("This record is referenced in other resources. So, can not be deleted"));
		}
		//bean.setOnLoadFlag(true);
		model.getRecords(bean, request);
		model.terminate();
		return "view";
	}
	
	public String delete1() throws Exception{
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(1);
		boolean result;
		ExamModel model = new ExamModel();
		model.initiate(context,session);
		
		String[] code=request.getParameterValues("hdeleteCode");
		//String []catCode=request.getParameterValues("hdeleteCatId");
		result=model.delChkdRec(bean,code);
		bean.setFlagShow("true");
		if(result){
			
			addActionMessage(getText("delMessage",""));
		}
		else
			addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
		bean.setOnLoadFlag(true);	
		model.getRecords(bean,request);
		reset();
		return "view";
	}
	
	public String addSection() throws Exception {
		getNavigationPanel(2);
		String result = "";		
		ExamModel model = new ExamModel();
		model.initiate(context, session);
		if (bean.getHEditCatCode().equals("")) {
			result = model.addCategory(bean, request);
			if (result.equals("success")) {
				//addActionMessage(getText("Record added successfully!"));
			} else if (result.equalsIgnoreCase("duplicate")) {
				addActionMessage(getText("Duplicate record found !"));
			} else {
				addActionMessage(getText("Record can not be added !"));
			}
		}
		else {			
			result = model.updateCategory(bean, request);
			if (result.equals("success")) {
				//addActionMessage(getText("Record updated successfully!"));
			} else if (result.equalsIgnoreCase("duplicate")) {
				addActionMessage(getText("Duplicate record found !"));
			} else {
				addActionMessage(getText("Record can not be updated !"));
			}
		}
		model.getSubjectRec(bean, request);
		bean.setCategoryStatus("Y");
		bean.setFlagShow("false");
		bean.setHEditCatCode("");
		return "view";
	}
	
	
	public void updateCategoryStatus() throws Exception {
		String result = "";
		ExamModel model = new ExamModel();
		model.initiate(context, session);
		result = model.updateCategoryStatus(request);
		if(result.equalsIgnoreCase("success")){
			response.getOutputStream().print("success");
		} else {
			response.getOutputStream().print("error");
		}
		//return "view";
	}
	
	public String deleteSection() throws Exception {
		getNavigationPanel(2);
		String result = "";
		ExamModel model = new ExamModel();
		model.initiate(context, session);
		String[] code=request.getParameterValues("hdeleteCatCode");
		result = model.deleteCategory(bean, request, code);
		if (result.equals("success")) {			
			addActionMessage(getText("Record deleted successfully!"));
		} else {
			addActionMessage(getText("Record can not be deleted !"));
		}
		model.getSubjectRec(bean, request);
		bean.setFlagShow("false");
		return "view";
	}

	/**
	 * following function is called to reset the fields.
	 * @return
	 */
	public String reset() {
		ExamModel model = new ExamModel();
		model.initiate(context, session);
		bean.setSubjectCode("");
		bean.setSubjectName("");
		bean.setSubjectAbbr("");
		bean.setSubjectDesc("");
		bean.setHiddencode("");
		bean.setCategoryCode("");
		bean.setHdeleteCode("");
		bean.setHdeleteCatCode("");
		bean.setCategoryListlength("");
		bean.setCategoryCodeItr("");
		bean.setSubjectCategoryNameItr("");
		bean.setCheckEdit("");
		bean.setUpdateCatFlag("false");
		bean.setCtgryCode("");
		model.terminate();
		return "view";
	}

	/**
	 * following function is called to display all the records when the link is clicked
	 * @return
	 * @throws Exception
	 */
	public String callPage() throws Exception {
		getNavigationPanel(1);
		ExamModel model = new ExamModel();
		model.initiate(context, session);
		model.getRecords(bean, request);
		bean.setFlagShow("true");
		//bean.setOnLoadFlag(true);
		model.terminate();
		reset();
		return "view";
	}

	/**
	 * following function is called when 
	 * @return
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		getNavigationPanel(2);
		ExamModel model = new ExamModel();
		model.initiate(context, session);
		//bean.setFlagView("true");
		model.getSubjectOnDoubleClick(bean, request);
		model.getRecords(bean, request);
		//bean.setEditFlag("false");
		bean.setEnableAll("Y");
		//bean.setCatList(true);
		model.terminate();
		return "view";
	}

	public String setContent() {
		String from = request.getParameter("from");
		if(from == null || from.equals("")) {
			from = "list";
		}
		ExamModel model = new ExamModel();
		model.initiate(context, session);
		model.setContent(bean, request, from);
		model.terminate();
		return "contentPage";
	}
	
	public String updateContent() throws Exception {
		String from = request.getParameter("from");
		if(from == null || from.equals("")) {
			from = "list";
		}
		ExamModel model = new ExamModel();
		model.initiate(context, session);
		boolean result = false;
		if (!bean.getSubjectCode().equals("") || !bean.getCategoryCode().equals("")) {
			result = model.updateContent(bean, request, from);
			if (result) {
				addActionMessage("Record updated successfully");
			} else {
				addActionMessage("Record can not be updated");
			}
		} else {
			addActionMessage("Record can not be updated");
		}
		model.terminate();		
		/*if(from.equals("edit") || from.equals("catedit")){
			return calforedit();
		} else {
			reset();
			return input();
		}*/
		request.setAttribute("labelName", "Content");
		request.setAttribute("from", from);
		setContent();
		return "contentPage";
	}
	
	public String setInstruction() {
		String from = request.getParameter("from");
		if(from == null || from.equals("") || !from.equals("edit")) {
			from = "list";
		}
		ExamModel model = new ExamModel();
		model.initiate(context, session);
		model.setInstruction(bean, request, from);
		model.terminate();
		return "instructionPage";
	}

	public String updateInstruction() throws Exception {
		String from = request.getParameter("from");
		if(from == null || from.equals("") || !from.equals("edit")) {
			from = "list";
		}
		request.setAttribute("labelName", "Instructions");
		request.setAttribute("from", from);
		ExamModel model = new ExamModel();
		model.initiate(context, session);
		boolean result = false;
		if (!bean.getSubjectCode().equals("")) {
			result = model.updateInstruction(bean, request);
			if (result) {
				addActionMessage("Record updated successfully");
			} else {
				addActionMessage("Record can not be updated");
			}
		} else {
			addActionMessage("Record can not be updated");
		}
		model.terminate();		
		/*if(from.equals("edit")){
			return calforedit();
		} else {
			return reset();
		}*/
		setInstruction();
		return "instructionPage";
	}
	
	/**
	 * 
	 * The Following Method is used to display Search Window to get Record to
	 * modify
	 */
	public String f9Action() throws Exception {
		String query = " SELECT SUBJECT_NAME,SUBJECT_ABBR,SUBJECT_CODE  FROM HRMS_REC_SUBJECT "
				+ " ORDER BY SUBJECT_CODE";
		String[] headers = { getMessage("modulenamelabel"), getMessage("module.abbr") };
		String[] headerwidth = { "50","50"};
		String fieldNames[] = { "subjectName", "subjectAbbr", "subjectCode" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlage = "true";
		String submitToMethod = "ExamMaster_calforedit.action";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";
	}

	/**
	 * following function is called to set the field values when a record is selected from the search window
	 * @throws Exception
	 */
	public String details() {
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(2);
		ExamModel model = new ExamModel();
		model.initiate(context, session);
		//bean.setFlagView("true");
		model.getSubjectRec(bean, request);
		//bean.setCatList(true);
		model.getRecords(bean, request);
		model.terminate();
		return "view";
	}

	
	/**
	 * following function is called to get the Pdf report for list of Subject Master records
	 * 
	 */
	public String report() {
		getNavigationPanel(3);
		ExamModel model = new ExamModel();
		model.initiate(context, session);
		String[] label = { "Sr.No", getMessage("subj.name"),
				getMessage("subj.abbr") };
		model.generateReport(bean, response, label);
		model.terminate();
		return null;
	}

	public String editContent() {
		String from = request.getParameter("from");
		if(from == null || from.equals("")) {
			from = "list";
		}
		ExamModel model = new ExamModel();
		model.initiate(context, session);
		model.setContent(bean, request, from);
		model.terminate();
		return "contentPage";
	}
	
	public String deleteContent() {
		String from = request.getParameter("from");
		if(from == null || from.equals("")) {
			from = "list";
		}
		ExamModel model = new ExamModel();
		model.initiate(context, session);
		boolean result = model.deleteContent(bean, request, from);
		if(result){
			addActionMessage(getText("Record deleted successfully!"));
		}else{
			addActionMessage(getText("This record is referenced in other resources. So, can not be deleted"));
		}
		model.terminate();
		return "contentPage";
	}

	
	public String contentOrderUp() {
		String from = request.getParameter("from");
		if(from == null || from.equals("")) {
			from = "list";
		}
		ExamModel model = new ExamModel();
		model.initiate(context, session);
		boolean result = model.contentOrderUp(bean, request, from);
		if(result){
			
		}else{
			
		}
		model.terminate();
		return "contentPage";
	}
	
	public String contentOrderDown() {
		String from = request.getParameter("from");
		if(from == null || from.equals("")) {
			from = "list";
		}
		ExamModel model = new ExamModel();
		model.initiate(context, session);
		boolean result = model.contentOrderDown(bean, request, from);
		if(result){
			
		}else{
			
		}
		model.terminate();
		return "contentPage";
	}
}
