/**
 * 
 */
package org.struts.action.FormulaBuilder;

import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.FormulaBuilder.FormilaBuilder;
import org.paradyne.model.FormulaBuilder.FormulaBuilderModel;
import org.paradyne.model.Promotion.PromotionModel;
import org.paradyne.model.admin.master.ProfTaxModel;

/**
 * @author ritac
 * 
 */
public class FormulaBuilderAction extends ParaActionSupport {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */

	FormilaBuilder frmBuild;

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public FormilaBuilder getFrmBuild() {
		return frmBuild;
	}

	public void setFrmBuild(FormilaBuilder frmBuild) {
		this.frmBuild = frmBuild;
	}

	public String input() throws Exception {
		FormulaBuilderModel model = new FormulaBuilderModel();
		model.initiate(context, session);

		model.formulaDataList(frmBuild, request);

		//frmBuild.setEnableAll("Y");
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;
	}

	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		frmBuild = new FormilaBuilder();
		frmBuild.setMenuCode(563);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return frmBuild;
	}

	/*
	 * public String addFormula()throws Exception{
	 * 
	 * logger.info("addddddddd==="); Object []
	 * srNo=request.getParameterValues("srNo");
	 * 
	 * 
	 * Object [] salC=request.getParameterValues("salCode1");
	 * //logger.info("salC==="+String.valueOf(salC[0])); Object []
	 * salH=request.getParameterValues("salHead1"); Object []
	 * salT=request.getParameterValues("salType1"); Object []
	 * salA=request.getParameterValues("salAmt1"); //
	 * logger.info("salA==="+String.valueOf(salA[0]));
	 * 
	 * FormulaBuilderModel model = new FormulaBuilderModel();
	 * model.initiate(context, session);
	 * model.addFormula(srNo,salC,salH,salT,salA,1,frmBuild);
	 * 
	 * model.terminate(); return SUCCESS; }
	 */

	public String addFormula() throws Exception {

		Object[] srNo = request.getParameterValues("srNo");

		Object[] salC = request.getParameterValues("salCode1");
		Object[] salH = request.getParameterValues("salHead1");
		Object[] salT = request.getParameterValues("salType1");
		Object[] salA = request.getParameterValues("salAmt1");
		Object[] salP = request.getParameterValues("salPrior1");
		// Object[] prC = request.getParameterValues("lengthp");
		FormulaBuilderModel model = new FormulaBuilderModel();
		model.initiate(context, session);
		getNavigationPanel(2);
		String res = model.addFormula(srNo, salC, salH, salT, salA, salP, 1,
				frmBuild);
		if (res == "PriorityGreater") {
			addActionMessage("Salary priority is greater!");
		}
		if (res == "PriorityGreaterEdit") {
			addActionMessage("Salary priority is greater!");
		}
		if (res == "noRemove") {
			addActionMessage("can't remove!");
		}
		frmBuild.setChkEdit("");
		addReset();
		model.terminate();
		return "showData";
	}
	/* OLD METHOD COMMENTED BY Prashant*/
	/*public String save() throws Exception {

		Object[] srNo = request.getParameterValues("srNo");

		Object[] salC = request.getParameterValues("salCode1");
		Object[] salH = request.getParameterValues("salHead1");
		Object[] salT = request.getParameterValues("salType1");
		Object[] salA = request.getParameterValues("salAmt1");

		String flag = "";

		FormulaBuilderModel model = new FormulaBuilderModel();
		model.initiate(context, session);

		if (frmBuild.getFrmbuildCode().equals("")) {
			flag = model.save(frmBuild, srNo, salC, salH, salT, salA);
			
			if (flag.equals("saved")) {
				addActionMessage("Record Saved Successfully !");
				model.getRecord(frmBuild);
			} else if (flag.equals("notsaved"))
				addActionMessage("Record Can't be saved !");
			reset();
		} else {
			
			flag = model.modify(frmBuild, srNo, salC, salH, salT, salA);
			if (flag.equals("modified")) {
				
				getNavigationPanel(3);
				addActionMessage("Record Modified Successfully !");
				model.getRecord(frmBuild);
			} else if (flag.equals("notmodified"))
				addActionMessage("Record Can't be Modified !");

			addReset();

		}
		model.terminate();
		frmBuild.setEnableAll("N");
		return "showData";

	}*/

	public String save() throws Exception {

		Object[] srNo = request.getParameterValues("srNo");
		Object[] salC = request.getParameterValues("salCode1");
		Object[] salH = request.getParameterValues("salHead1");
		Object[] salT = request.getParameterValues("salType1");
		Object[] salA = request.getParameterValues("salAmt1");

		String flag = "";

		FormulaBuilderModel model = new FormulaBuilderModel();
		model.initiate(context, session);
		String formulaName = frmBuild.getFrmbuildName();
		if (frmBuild.getFrmbuildCode().equals("")) {
			flag = model.save(frmBuild, srNo, salC, salH, salT, salA, formulaName);
			if (flag.equals("saved")) {
				getNavigationPanel(3);
				frmBuild.setEnableAll("N");
				addActionMessage("Record Saved Successfully !");
				model.getRecord(frmBuild, frmBuild.getFrmbuildCode());
			} else {
				getNavigationPanel(2);
				addActionMessage(getMessage("save.error"));
			}
		} else {
			flag = model.modify(frmBuild, srNo, salC, salH, salT, salA);
			getNavigationPanel(2);
			frmBuild.setEnableAll("N");
			if (flag.equals("modified")) {
				getNavigationPanel(3);
				frmBuild.setEnableAll("N");
				addActionMessage("Record Updated Successfully !");
				model.getRecord(frmBuild, frmBuild.getFrmbuildCode());
			} else {
				getNavigationPanel(2);
				addActionMessage(getMessage("Cannot update record"));
			}
		}
		model.terminate();
		return "showData";
	}

	public void saveValidate() {

		String[] srNo = request.getParameterValues("srNo");

		String[] salC = request.getParameterValues("salCode1");
		String[] salA = request.getParameterValues("salAmt1");

		String flag = "";

		FormulaBuilderModel model = new FormulaBuilderModel();
		model.initiate(context, session);

		flag = model.saveValidate(frmBuild, srNo, salC, salA);
		if (!flag.equals("")) {
			addActionMessage(flag);
		}
		model.terminate();
	}

	public String edit() {
		try {
			Object[] srNo = request.getParameterValues("srNo");
			Object[] salC = request.getParameterValues("salCode1");
			Object[] salH = request.getParameterValues("salHead1");
			Object[] salT = request.getParameterValues("salType1");
			Object[] salA = request.getParameterValues("salAmt1");
			Object[] salP = request.getParameterValues("salPrior1");
			String formulaCode = frmBuild.getFrmbuildCode();
			// Object[] salPr = request.getParameterValues("lengthp");
			String serial = request.getParameter("serial");
			FormulaBuilderModel model = new FormulaBuilderModel();
			model.initiate(context, session);
			getNavigationPanel(2);
			String res = model.editFormula(srNo, salC, salH, salT, salA, salP,
					frmBuild, 1);
			logger.info("res======" + res);
			model.getRecord(frmBuild, formulaCode);
			model.terminate();
			frmBuild.setChkEdit("");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "showData";
	}

	public String remove() {
		Object[] srNo = request.getParameterValues("srNo");

		Object[] salC = request.getParameterValues("salCode1");
		Object[] salH = request.getParameterValues("salHead1");
		Object[] salT = request.getParameterValues("salType1");
		Object[] salA = request.getParameterValues("salAmt1");
		Object[] salP = request.getParameterValues("salPrior1");
		String serial = request.getParameter("serial");

		FormulaBuilderModel model = new FormulaBuilderModel();
		model.initiate(context, session);

		boolean flag = model.removeList(srNo, salC, salH, salT, salA, salP,
				frmBuild, 1, serial);
		if (flag == false) {
			addActionMessage("Salary Code is already in use so can't delete the record!");
			getNavigationPanel(2);
		} else {
			getNavigationPanel(2);
			addActionMessage("Record removed successfully!");
		}
		
		model.terminate();
		frmBuild.setChkEdit("");
		return "showData";
	}

	public String retrive() { // Retrieve data on f9 action
		FormulaBuilderModel model = new FormulaBuilderModel();
		model.initiate(context, session);
		getNavigationPanel(3);
		String formulaCode = frmBuild.getFrmbuildCode();
		model.getRecord(frmBuild, formulaCode);

		model.terminate();
		frmBuild.setEnableAll("N");
		return "showData";
	}

	public String delete() throws Exception {

		FormulaBuilderModel model = new FormulaBuilderModel();
		model.initiate(context, session);

		boolean result;

		result = model.delFormula(frmBuild);

		if (result) {
			addActionMessage("Record Deleted Successfully !");

		} else {
			addActionMessage("Record can't be deleted !");
		}

		model.terminate();

		return input();
	}

	public String reset() {
		frmBuild.setFrmbuildCode("");
		frmBuild.setFrmbuildName("");
		frmBuild.setFrmdtlCode("");
		frmBuild.setSalCode("");
		frmBuild.setSalHead("");
		frmBuild.setSalAmt("");
		frmBuild.setSalOrder("");
		frmBuild.setSalAbr("");
		frmBuild.setSalType("");
		frmBuild.setChkEdit("");
		frmBuild.setSalprior("");
		frmBuild.setFrmList(null);
		getNavigationPanel(2);
		return "showData";
	}

	public String addReset() {
		frmBuild.setSalHead("");
		frmBuild.setSalCode("");
		frmBuild.setSalAmt("");
		frmBuild.setSalType("");
		return "success";
	}

	public String f9action() {
		String query = "SELECT FORMULA_ID,FORMULA_NAME FROM HRMS_FORMULABUILDER_HDR WHERE 1=1 ORDER BY FORMULA_ID";

		String[] headers = { getMessage("for.id"), getMessage("for.name") };
		String[] headerwidth = { "25", "75" };
		String[] fieldNames = { "frmbuildCode", "frmbuildName" };
		int[] columnIndex = { 0, 1 };
		String submitFlage = "true";
		String submitToMethod = "FormulaBuilder_retrive.action";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";

	}

	public String f9calcaction() {

		String salCode = "";

		logger.info("sallllllcodeeeeeeeeee===" + frmBuild.getSalCode()
				+ "nonlisttt========" + frmBuild.getNotInList());
		String query = "SELECT CREDIT_CODE,CREDIT_NAME,CREDIT_PRIORITY,'C'||CREDIT_CODE  FROM HRMS_CREDIT_HEAD ";
		/*
		 * if (frmBuild.getSalCode() != null &&
		 * (!frmBuild.getSalCode().equals(""))) query += " WHERE CREDIT_CODE NOT
		 * IN ("+frmBuild.getSalCode() + ")"; if (frmBuild.getNotInList() !=
		 * null && (!frmBuild.getNotInList().equals(" "))) query += " AND
		 * CREDIT_CODE IN ("+frmBuild.getNotInList()+")";
		 * 
		 * logger.info("SSSSSSSSSSSSSSSSSSS" + frmBuild.getNotInList());
		 */

		query += " ORDER BY CREDIT_CODE ";
		String[] headers = { "Salary Code", getMessage("salhead"),
				"Salary Priority", "Formula Code" };
		String[] headerwidth = { "20", "40", "20", "20" };
		String[] fieldNames = { "salCodeCalc", "salHeadCalc", "salAbbrCalc",
				"dupformulacode" };
		int[] columnIndex = { 0, 1, 2, 3 };
		String submitFlage = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		return "f9page";

	}

	public String f9salaction() {
		String salCode = "";
		String[] salIds = request.getParameterValues("salCode1");

		if (salIds != null) {
			for (int i = 0; i < salIds.length - 1; i++)
				salCode += salIds[i] + ",";
			salCode += salIds[salIds.length - 1];
		} else
			salCode = "0";

		logger.info("SALLLLLLLLLL==" + salCode);
		String query = "SELECT CREDIT_CODE,CREDIT_NAME,CREDIT_PRIORITY FROM HRMS_CREDIT_HEAD "
				+ "WHERE CREDIT_CODE NOT IN ("
				+ salCode
				+ ") "
				+ "ORDER BY CREDIT_CODE ";
		String[] headers = { "Salary Code", getMessage("salhead"),
				"Salary Priority" };

		String[] headerwidth = { "25", "50", "25" };

		String[] fieldNames = { "salCode", "salHead", "salprior" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String formulaCalc() throws Exception {
		logger.info("___________________________in formulaCalc()");

		String formula = (String) request.getParameter("id");
		logger.info("formula====" + formula);
		frmBuild.setFrmId(formula);

		return "f9calc";

	}

	public String addNew() {
		try {
			getNavigationPanel(2);
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);

		}
		return reset();
	}

	public String callForEdit() {

		try {
			FormulaBuilderModel model = new FormulaBuilderModel();
			model.initiate(context, session);

			String formulaAutoId = request.getParameter("autoCode");

			model.callForEdit(frmBuild, formulaAutoId);
			frmBuild.setFrmbuildCode(formulaAutoId);
			model.getRecord(frmBuild, formulaAutoId);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(3);
		frmBuild.setEnableAll("N");
		return "showData";
	}

	public String back() throws Exception {
		getNavigationPanel(1);
		input();
		return SUCCESS;
	}

	public String deleteFormula() throws Exception {
		String[] deleteCode = request.getParameterValues("hdeleteCode");

		FormulaBuilderModel model = new FormulaBuilderModel();
		model.initiate(context, session);

		boolean result = model.deleteSelectedRecord(deleteCode);
		if (result) {
			addActionMessage(getMessage("delete"));
		} else {
			addActionMessage(getMessage("multiple.del.error"));
		}
		model.terminate();
		getNavigationPanel(1);
		return input();
	}
	
	public String saveCopiedFormula(){
		try {
			Object[] srNo = request.getParameterValues("srNo");
			Object[] salC = request.getParameterValues("salCode1");
			Object[] salH = request.getParameterValues("salHead1");
			Object[] salT = request.getParameterValues("salType1");
			Object[] salA = request.getParameterValues("salAmt1");
			frmBuild.setHiddenFormulaId(frmBuild.getFrmbuildCode());
			String flag = "";
			FormulaBuilderModel model = new FormulaBuilderModel();
			model.initiate(context, session);
			String formulaCode = frmBuild.getHiddenFormulaId();//setting old Id to fetch previous formula
			String formulaName = frmBuild.getCopiedFormulaName();
			
			flag = model.save(frmBuild, srNo, salC, salH, salT, salA, formulaName);
			if (flag.equals("saved")) {
				getNavigationPanel(3);
				frmBuild.setEnableAll("N");
				addActionMessage("Formula copied Successfully !");
				model.getRecord(frmBuild, formulaCode);
			} else {
				getNavigationPanel(2);
				addActionMessage("Cannot copy formula");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showData";
	}
}
