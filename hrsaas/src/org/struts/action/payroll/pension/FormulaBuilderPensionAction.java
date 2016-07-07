/**
 * 
 */
package org.struts.action.payroll.pension;

import org.paradyne.bean.payroll.pension.FormulaBuilderPension;
import org.paradyne.model.payroll.pension.FormulaBuilderPensionModel;
import org.struts.lib.ParaActionSupport;

public class FormulaBuilderPensionAction extends ParaActionSupport {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */

	FormulaBuilderPension frmBuild;

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public FormulaBuilderPension getFrmBuild() {
		return frmBuild;
	}

	public void setFrmBuild(FormulaBuilderPension frmBuild) {
		this.frmBuild = frmBuild;
	}

	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		frmBuild = new FormulaBuilderPension();
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

		FormulaBuilderPensionModel model = new FormulaBuilderPensionModel();
		model.initiate(context, session);
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
		return SUCCESS;
	}

	public String save() throws Exception {

		Object[] srNo = request.getParameterValues("srNo");

		Object[] salC = request.getParameterValues("salCode1");
		Object[] salH = request.getParameterValues("salHead1");
		Object[] salT = request.getParameterValues("salType1");
		Object[] salA = request.getParameterValues("salAmt1");

		String flag = "";

		FormulaBuilderPensionModel model = new FormulaBuilderPensionModel();
		model.initiate(context, session);

		if (frmBuild.getFrmbuildCode().equals("")) {
			logger.info("IN saveeeeeeeeeeeeee");
			// saveValidate();
			flag = model.save(frmBuild, srNo, salC, salH, salT, salA);

			if (flag.equals("saved")) {
				addActionMessage("Record Saved Successfully !");
				model.getRecord(frmBuild);
			} else if (flag.equals("notsaved"))
				addActionMessage("Record Can't be saved !");
			reset();
		} else {
			/*
			 * Call for the modifying the application.
			 * 
			 * 
			 */

			flag = model.modify(frmBuild, srNo, salC, salH, salT, salA);
			if (flag.equals("modified")) {
				/*
				 * Give the message on Modifying the application./
				 * 
				 */
				addActionMessage("Record Modified Successfully !");
				model.getRecord(frmBuild);
			} else if (flag.equals("notmodified"))
				addActionMessage("Record Can't be Modified !");

			addReset();

		}
		model.terminate();

		return SUCCESS;

	}

	public void saveValidate() {

		String[] srNo = request.getParameterValues("srNo");

		String[] salC = request.getParameterValues("salCode1");
		String[] salA = request.getParameterValues("salAmt1");

		String flag = "";

		FormulaBuilderPensionModel model = new FormulaBuilderPensionModel();
		model.initiate(context, session);

		flag = model.saveValidate(frmBuild, srNo, salC, salA);
		if (!flag.equals("")) {
			addActionMessage(flag);
		}
		model.terminate();
	}

	public String edit() {
		Object[] srNo = request.getParameterValues("srNo");

		Object[] salC = request.getParameterValues("salCode1");
		Object[] salH = request.getParameterValues("salHead1");
		Object[] salT = request.getParameterValues("salType1");
		Object[] salA = request.getParameterValues("salAmt1");
		Object[] salP = request.getParameterValues("salPrior1");
		// Object[] salPr = request.getParameterValues("lengthp");
		String serial = request.getParameter("serial");

		FormulaBuilderPensionModel model = new FormulaBuilderPensionModel();
		model.initiate(context, session);
		String res = model.editFormula(srNo, salC, salH, salT, salA, salP,
				frmBuild, 1);
		logger.info("res======" + res);

		model.terminate();
		frmBuild.setChkEdit("");
		return SUCCESS;
	}

	public String remove() {
		Object[] srNo = request.getParameterValues("srNo");

		Object[] salC = request.getParameterValues("salCode1");
		Object[] salH = request.getParameterValues("salHead1");
		Object[] salT = request.getParameterValues("salType1");
		Object[] salA = request.getParameterValues("salAmt1");
		Object[] salP = request.getParameterValues("salPrior1");
		// Object[] salPr = request.getParameterValues("lengthp");
		String serial = request.getParameter("serial");

		FormulaBuilderPensionModel model = new FormulaBuilderPensionModel();
		model.initiate(context, session);

		boolean flag = model.removeList(srNo, salC, salH, salT, salA, salP,
				frmBuild, 1, serial);
		if (flag == false) {
			addActionMessage("Salary Code is already in used so can't delete the record!");
		} else {
			addActionMessage("Record removed successfully!");
		}
		model.terminate();
		frmBuild.setChkEdit("");
		return SUCCESS;
	}

	public String retrive() { // Retrive data on f9 action
		FormulaBuilderPensionModel model = new FormulaBuilderPensionModel();
		model.initiate(context, session);
		model.getRecord(frmBuild);

		model.terminate();
		return "success";
	}

	public String delete() throws Exception {

		FormulaBuilderPensionModel model = new FormulaBuilderPensionModel();
		model.initiate(context, session);

		boolean result;

		result = model.delFormula(frmBuild);

		if (result) {
			addActionMessage("Record Deleted Successfully !");

		} else {
			addActionMessage("Record can't be deleted !");
		}

		model.terminate();

		return reset();
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
		return "success";
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

		String[] headers = { getMessage("for.id"),getMessage("for.name") };
		String[] headerwidth = { "25", "75" };
		String[] fieldNames = { "frmbuildCode", "frmbuildName" };
		int[] columnIndex = { 0, 1 };
		String submitFlage = "true";
		String submitToMethod = "FormulaBuilderPension_retrive.action";
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
		String[] headers = { "Salary Code", getMessage("salhead"), "Salary Priority","Formula Code"  };
		String[] headerwidth = { "20", "40", "20","20" };
		String[] fieldNames = { "salCodeCalc", "salHeadCalc", "salAbbrCalc","dupformulacode" };
		int[] columnIndex = { 0, 1, 2,3 };
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
		String[] headers = { "Salary Code", getMessage("salhead"), "Salary Priority"};

		String[] headerwidth = { "25", "50", "25"};

		String[] fieldNames = { "salCode", "salHead", "salprior" };

		int[] columnIndex = { 0, 1, 2};

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

}
