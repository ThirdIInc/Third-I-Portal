/**
 * 
 */
package org.struts.action.ApplicationStudio;

import java.util.TreeMap;

import org.paradyne.bean.ApplicationStudio.LetterTemplate;
import org.paradyne.model.ApplicationStudio.LetterTemplateModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Reeba Joseph
 */
public class LetterTemplateAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	LetterTemplate letterTemplate;

	/**
	 * @return the letterTemplate
	 */
	public LetterTemplate getLetterTemplate() {
		return letterTemplate;
	}

	/**
	 * @param letterTemplate
	 *            the letterTemplate to set
	 */
	public void setLetterTemplate(LetterTemplate letterTemplate) {
		this.letterTemplate = letterTemplate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		letterTemplate = new LetterTemplate();
		letterTemplate.setMenuCode(743);

	}

	public void prepare_withLoginProfileDetails() throws Exception {
		LetterTemplateModel model = new LetterTemplateModel();
		model.initiate(context, session);
		model.getTemplateType(letterTemplate);
		
		model.terminate();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return letterTemplate;
	}

	/**
	 * Creates query list and query header list
	 * 
	 * @return String
	 */
	public String add() {
		logger.info("Query Name=========" + letterTemplate.getQName());
		String[] srNo = request.getParameterValues("srNo");
		String[] queryName = request.getParameterValues("queryName");
		String[] queryType = request.getParameterValues("queryType");
		String[] noOfRows = request.getParameterValues("noOfRowsItt");
		String[] noOfColumn = request.getParameterValues("noOfColumnItt");
		String[] colsWidth = request.getParameterValues("colsWidthItt");
		String[] queryNameItt = request.getParameterValues("queryNameItt");
		// String hiddenEdit=request.getParameter("hiddenEdit");
		String[] field = request.getParameterValues("field");
		LetterTemplateModel model = new LetterTemplateModel();
		model.initiate(context, session);
		String str = "";
		// sets list of query headers

		if (letterTemplate.getHiddenEdit().equals(""))
		// adds queries into the list
		{
			str = model.createTempList(letterTemplate, srNo, queryName, field,
					queryType, noOfRows, noOfColumn,colsWidth);
			model.addItem(letterTemplate, srNo, queryName, 1, queryType,
					noOfRows, noOfColumn,colsWidth, queryNameItt);
		}

		else
		// modifies queries under the list
		{
			str = model.createTempListForEdit(letterTemplate, srNo, queryName,
					field, queryType, noOfRows, noOfColumn,colsWidth);
			model.moditem(letterTemplate, srNo, queryName, 1, queryType,
					noOfRows, noOfColumn, colsWidth,queryNameItt);
		}

		addActionMessage("" + str);
		model.terminate();

		letterTemplate.setQName("");
		letterTemplate.setSubcode("");
		letterTemplate.setHiddenEdit("");
		letterTemplate.setQType("");
		letterTemplate.setQueryNameTab("");
		letterTemplate.setNoOfColumn("");
		letterTemplate.setNoOfRows("");
		/*
		 * edited by Prashant on 6/09/2010 to add data from
		 * HRMS_LETTERTEMPLATE_VAR table
		 */

		String letterTempVar = "SELECT TEMPLATE_ID, VAR_NAME, VAR_FORMULA, VAR_PRIORITY FROM HRMS_LETTERTEMPLATE_VAR WHERE TEMPLATE_ID = "
				+ letterTemplate.getTempCode();

		Object[][] letterTempData = model.getSqlModel().getSingleResult(
				letterTempVar);
		TreeMap letterTempMap = new TreeMap();
		letterTempMap.put("Select", "Select");

		if (letterTempData != null && letterTempData.length > 0) {
			for (int i = 0; i < letterTempData.length; i++) {
				letterTempMap.put(String.valueOf(letterTempData[i][1]), String
						.valueOf(letterTempData[i][1]));

			}

		}
		letterTemplate.setLetterTempMap(letterTempMap);
		letterTemplate.setTemplateOptFlag("true");

		return SUCCESS;

	}

	/**
	 * remove queries from the list
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String deleteDtl() throws Exception {

		String code[] = request.getParameterValues("hdeleteOp");
		String[] queryName = request.getParameterValues("queryName");
		String[] srNo = request.getParameterValues("srNo");
		String[] field = request.getParameterValues("field");
		boolean result = false;
		LetterTemplateModel model = new LetterTemplateModel();
		model.initiate(context, session);
		result = model.delDtl(letterTemplate, code, queryName);
		if (result) {
			addActionMessage(getMessage("delete"));
		}
		model.terminate();
		letterTemplate.setHiddenEdit("");
		letterTemplate.setHdeleteOp("");
		return SUCCESS;

	}
	public String editQuery() {
		try {
			String[] srNo = request.getParameterValues("srNo");
			String[] queryName = request.getParameterValues("queryName");
			String[] queryType = request.getParameterValues("queryType");
			String[] noOfRows = request.getParameterValues("noOfRowsItt");
			String[] noOfColumn = request.getParameterValues("noOfColumnItt");
			String[] colsWidth = request.getParameterValues("colsWidthItt"); 
			String[] queryNameItt = request.getParameterValues("queryNameItt");
		    //String hiddenEdit=request.getParameter("hiddenEdit");
			String[] field = request.getParameterValues("field");
			LetterTemplateModel model = new LetterTemplateModel();
			model.initiate(context, session);
			int rowEdited = 0;
			rowEdited = Integer.parseInt(letterTemplate.getHiddenEdit()) - 1;
			String editQueryName = queryName[rowEdited];
			String editQueryType = queryType[rowEdited];

			System.out.println("editQueryName-------------" + editQueryName);
			System.out.println("editQueryType-------------" + editQueryType);
			
			letterTemplate.setQName(editQueryName);
			letterTemplate.setQueryNameTab(queryNameItt[rowEdited]);
			letterTemplate.setNoOfRows(noOfRows[rowEdited]);
			letterTemplate.setNoOfColumn(noOfColumn[rowEdited]);
			letterTemplate.setCols_width(colsWidth[rowEdited]);
			
			
			if (editQueryType.equals("Salary")) {
				letterTemplate.setQType("S");
			}
			if (editQueryType.equals("Other")) {
				letterTemplate.setQType("O");
			}
			if (editQueryType.equals("Table Data")) {
				letterTemplate.setQType("D");
			}
			model.createTempListForEdit1(letterTemplate, srNo, queryName,
					field, queryType);
			model.moditem(letterTemplate, srNo, queryName, 2, queryType,
					noOfRows, noOfColumn,colsWidth  ,queryNameItt);
			// model.editQuery(letterTemplate, srNo, queryName, 1,
			// queryType,noOfRows,noOfColumn,queryNameItt);

			/*
			 * edited by Prashant on 6/09/2010 to add data from
			 * HRMS_LETTERTEMPLATE_VAR table
			 */

			String letterTempVar = "SELECT TEMPLATE_ID, VAR_NAME, VAR_FORMULA, VAR_PRIORITY FROM HRMS_LETTERTEMPLATE_VAR WHERE TEMPLATE_ID = "
					+ letterTemplate.getTempCode();

			Object[][] letterTempData = model.getSqlModel().getSingleResult(
					letterTempVar);
			TreeMap letterTempMap = new TreeMap();
			letterTempMap.put("Select", "Select");

			if (letterTempData != null && letterTempData.length > 0) {
				for (int i = 0; i < letterTempData.length; i++) {
					letterTempMap.put(String.valueOf(letterTempData[i][1]),
							String.valueOf(letterTempData[i][1]));

				}

			}
			letterTemplate.setLetterTempMap(letterTempMap);
			//letterTemplate.setTemplateOptFlag("true");

			model.terminate();
			// letterTemplate.setQueryNameTab("");
			// letterTemplate.setNoOfColumn("");
			// letterTemplate.setNoOfRows("");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}

		public String deleteQuery() {
		try {
			String[] srNo = request.getParameterValues("srNo");
			String[] queryName = request.getParameterValues("queryName");
			String[] queryType = request.getParameterValues("queryType");
			String[] noOfRows = request.getParameterValues("noOfRowsItt");
			String[] noOfColumn = request.getParameterValues("noOfColumnItt");
			String[] colsWidth = request.getParameterValues("colsWidthItt");
			String[] queryNameItt = request.getParameterValues("queryNameItt");
			// String hiddenEdit=request.getParameter("hiddenEdit");
			String[] field = request.getParameterValues("field");
			LetterTemplateModel model = new LetterTemplateModel();
			model.initiate(context, session);
			boolean result = model.deleteQuery(srNo, queryName, queryType,
					letterTemplate, noOfRows, noOfColumn,colsWidth, queryNameItt);

			if (result) {
				addActionMessage(getMessage("delete"));
			}

			model.createTempListForDelete(letterTemplate, srNo, queryName,
					field, queryType);
			letterTemplate.setQueryName("");
			letterTemplate.setQType("");
			letterTemplate.setQueryType("");
			letterTemplate.setHiddenEdit("");
			/*
			 * edited by Prashant on 6/09/2010 to add data from
			 * HRMS_LETTERTEMPLATE_VAR table
			 */

			String letterTempVar = "SELECT TEMPLATE_ID, VAR_NAME, VAR_FORMULA, VAR_PRIORITY FROM HRMS_LETTERTEMPLATE_VAR WHERE TEMPLATE_ID = "
					+ letterTemplate.getTempCode();

			Object[][] letterTempData = model.getSqlModel().getSingleResult(
					letterTempVar);
			TreeMap letterTempMap = new TreeMap();
			letterTempMap.put("Select", "Select");

			if (letterTempData != null && letterTempData.length > 0) {
				for (int i = 0; i < letterTempData.length; i++) {
					letterTempMap.put(String.valueOf(letterTempData[i][1]),
							String.valueOf(letterTempData[i][1]));

				}

			}
			letterTemplate.setLetterTempMap(letterTempMap);
			letterTemplate.setTemplateOptFlag("true");

			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return SUCCESS;
	}

	public String save() throws Exception {
		String[] srNo = request.getParameterValues("srNo");
		String[] queryName = request.getParameterValues("queryName");
		String[] queryType = request.getParameterValues("queryType");
		String[] noOfRows = request.getParameterValues("noOfRowsItt");
		String[] noOfColumn = request.getParameterValues("noOfColumnItt");
		String[] colsWidth = request.getParameterValues("colsWidthItt");
		String[] queryNameItt = request.getParameterValues("queryNameItt");
		LetterTemplateModel model = new LetterTemplateModel();
		model.initiate(context, session);
		String poolDir = String.valueOf(session.getAttribute("session_pool"));

		String fileName = getText("data_path") + "\\datafiles\\" + poolDir
				+ "\\Template\\LetterTemplates\\"
				+ letterTemplate.getTempName() + ".doc";

		System.out.println("fileName-------------------------" + fileName);
		// call method to create filename
		// String dirName = getDirName();
		// call model method to initialize the file name
		// logger.info(" NAME========" + dirName);
		// model.setDirName(dirName);

		boolean result = false;
		logger.info("Temp code---------" + letterTemplate.getTempCode());
		if (letterTemplate.getTempCode() == ""
				|| letterTemplate.getTempCode().equals(null)
				|| letterTemplate.getTempCode().equals("")) {
			logger.info("inside save action");
			result = model.save(letterTemplate, srNo, queryName, queryType,
					noOfRows, noOfColumn,colsWidth, queryNameItt);
			if (result) {

				addActionMessage(getMessage("save"));
			} else {
				addActionMessage(getMessage("save.error"));
			}

		} else {
			logger.info("in Update block Temp code---------"
					+ letterTemplate.getTempCode());
			result = model.update(letterTemplate, srNo, queryName, queryType,
					noOfRows, noOfColumn,colsWidth, queryNameItt);
			if (result) {

				addActionMessage(getMessage("update"));
			} else {
				addActionMessage(getMessage("update.error"));
			}
		}

		model.terminate();

		return setTemplate();

	}

	/**
	 * method to get the Directory Name to save the file containing the text
	 * ,which has to be saved in database as CLOB.
	 * 
	 * @return String
	 */
	public String getDirName() {
		String poolDir = String.valueOf(session.getAttribute("session_pool"));

		String dirName = getText("data_path") + "\\datafiles\\" + poolDir
				+ "\\Template\\LetterTemplates";
		return dirName;
	}

	public String f9action() throws Exception {
		String query = " SELECT  TEMPLATE_ID,TEMPLATE_NAME , DECODE(TEMPLATE_TYPE,'O','Offer Template','P','Appointment Template','A','Acceptance Template','E','Experience Template' )"
				+ " FROM HRMS_LETTERTEMPLATE_HDR ORDER BY TEMPLATE_ID";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("template.code"),
				getMessage("template.name"), getMessage("temp.type") };
		String[] headerWidth = { "20", "50", "30" };
		String[] fieldNames = { "tempCode", "tempName", "tempType" };
		/**
		 * ,"tempContent",TEMPLATE_CONTENT SET THE COLUMN INDEX E.G. SUPPOSE THE
		 * POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH
		 * COLUMN VALUES NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE
		 * COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */

		String submitFlag = "true";
		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */

		String submitToMethod = "LetterTemplate_setTemplate.action";
		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String setTemplate() {
		String[] srNo = request.getParameterValues("srNo");
		String[] queryName = request.getParameterValues("queryName");
		String[] queryType = request.getParameterValues("queryType");
		String[] field = request.getParameterValues("field");
		LetterTemplateModel model = new LetterTemplateModel();
		model.initiate(context, session);
		// call method to create filename
		String dirName = getDirName();
		// call model method to initialize the file name
		logger.info("FILE NAME========" + dirName);
		model.setDirName(dirName);
		model
				.setTemplateData(letterTemplate, srNo, queryName, field,
						queryType);
		// model.setSalaryData();

		/*
		 * model.setTemplateOptData(letterTemplate, srNo, queryName, field,
		 * queryType);
		 */
		model.terminate();
		return SUCCESS;
	}

	public String addVariable() {
		boolean result = false;
		LetterTemplateModel model = new LetterTemplateModel();
		try {

			model.initiate(context, session);
			result = model.addTemplateVariable(letterTemplate);
			if(result){
				addActionMessage("Variable added successfully");
			}else{
				addActionMessage("Duplicate variable name found");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
		return setTemplate();
	}

	public String resetLetterTemplate() {

		letterTemplate.setTemplateType("");
		letterTemplate.setTempType("");
		letterTemplate.setHtmlcode("");
		letterTemplate.setTempCode("");
		letterTemplate.setDupTempName("");
		letterTemplate.setField("");
		letterTemplate.setTemplateId("");
		letterTemplate.setTempName("");
		letterTemplate.setTemplateId("");
		letterTemplate.setQName("");
		letterTemplate.setQId("");
		letterTemplate.setSrNo("");
		letterTemplate.setHiddenEdit("");
		letterTemplate.setSubcode("");
		letterTemplate.setTableLength("");
		letterTemplate.setHdeleteOp("");
		letterTemplate.setQType("");
		letterTemplate.setQueryType("");
		letterTemplate.setChk("");
		letterTemplate.setChkFlag("");
		letterTemplate.setTemplateOpt("");
		letterTemplate.setTemplateOptFlag("");
		letterTemplate.setTemplateOptSalary("");
		letterTemplate.setTemplateOptSalaryFlag("");
		letterTemplate.setNoOfColumnItt("");
		letterTemplate.setNoOfRowsItt("");
		letterTemplate.setNoOfColumn("");
		letterTemplate.setNoOfRows("");
		letterTemplate.setVariableName("");
		letterTemplate.setVariableValue("");
		letterTemplate.setVariablePriority("");
		return SUCCESS;
	}

	public String deleteVariableLetterTemplate() {
		System.out.println("########## IN DELETE VARIABLE LETTER TEMPLATE FUNCTION ###############");
		LetterTemplateModel model = new LetterTemplateModel();
		boolean result = false;
		try {

			model.initiate(context, session);
			result = model.deleteTemplateVariable(letterTemplate);
			if(result){
				addActionMessage("Variable deleted successfully");
			} else {
				addActionMessage("Could not delete variable");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
		return setTemplate();

	}
	public String duplicate(){
		LetterTemplateModel model = new LetterTemplateModel();
		model.initiate(context, session);
		boolean result=false;
		result=model.duplicate(letterTemplate);
		if(result)
		{
			addActionMessage("Saved Duplicate Name Successfully");
		}
		else
		{
			addActionMessage("Error while creating duplicate template");
		}
		model.terminate();
		return "success";
	}

}
