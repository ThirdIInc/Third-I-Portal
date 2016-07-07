/**
 * 
 */
package org.struts.action.payroll.salary;

import org.paradyne.bean.payroll.salary.SalaryGrade;
import org.paradyne.model.payroll.salary.SalaryGradeModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author varunk
 * 
 * @modified ayyappa
 *
 */
public class SalaryGradeAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(SalaryGradeAction.class);

	SalaryGrade salGrade;

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		salGrade = new SalaryGrade();
		salGrade.setMenuCode(527);
		//onClickDisplay();

	}

	public void prepare_withLoginProfileDetails() throws Exception {
		SalaryGradeModel model = new SalaryGradeModel();
		model.initiate(context, session);

		model.Data(salGrade, request);
		model.terminate();
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return salGrade;
	}

	public SalaryGrade getSalGrade() {
		return salGrade;
	}

	public void setSalGrade(SalaryGrade salGrade) {
		this.salGrade = salGrade;
	}

	public String onClickDisplay() {
		SalaryGradeModel model = new SalaryGradeModel();
		model.initiate(context, session);

		String[][] data = model.getRecord(salGrade);

		model.terminate();
		return SUCCESS;
	}

	public String input() throws Exception {
		getNavigationPanel(1);
		return SUCCESS;
	}

	public String addNew() throws Exception {
		getNavigationPanel(2);
		onClickDisplay();
		return "showData";
	}

	public void reset() {
		salGrade.setSalAmt("");
		salGrade.setCreditCode("");
		salGrade.setGradeName("");
		salGrade.setGradeCode("");
	}

	public String reset1() {
		salGrade.setSalAmt("");
		salGrade.setCreditCode("");
		salGrade.setGradeName("");
		salGrade.setGradeCode("");
		getNavigationPanel(2);
		onClickDisplay();
		return "showData";
	}

	public String cancel() throws Exception {
		reset();
		SalaryGradeModel model = new SalaryGradeModel();
		model.initiate(context, session);
		model.Data(salGrade, request);
		getNavigationPanel(1);
		model.terminate();
		salGrade.setEnableAll("Y");
		return SUCCESS;
	}

	public String callPage() throws Exception {

		SalaryGradeModel model = new SalaryGradeModel();
		model.initiate(context, session);
		model.Data(salGrade, request);
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;

	}

	public String save() {
		SalaryGradeModel model = new SalaryGradeModel();
		model.initiate(context, session);
		String[] creditCode = (String[]) request
				.getParameterValues("creditCode");
		String[] creditAmt = (String[]) request.getParameterValues("salAmt");
		String[] creditName = (String[]) request
				.getParameterValues("creditName");
		/*logger.info("---------------------------------------------------------------- "+(creditName == null));
		logger.info("creditName - Length -- "+creditName.length);
		logger.info("----------------------------------------------------------------");*/
		boolean result = false;
		boolean result1 = false;
		String gradeCode = salGrade.getGradeCode();

		if (!(salGrade.getGradeCode().equals("") || salGrade.getGradeCode()
				.equals(null))) {

			result = model.deleteRecord(salGrade);
			if (result) {

				result1 = model.updateGrade(salGrade, creditCode, creditName,
						creditAmt);
				//System.out.println("in model.updateGrade============="+result1);
				if (result1) {
					addActionMessage("Record Updated Succcessfully");
				} else {
					addActionMessage("Salary grade cann't be updated.");
				}
			}
		} else {
			result = model.saveGrade(salGrade, creditCode, creditName,
					creditAmt);
			if (result) {
				addActionMessage("Record Saved Succcessfully");
			} else {
				addActionMessage("Salary grade cann't be added.");
			}
		}
		//reset();
		getNavigationPanel(3);
		salGrade.setEnableAll("N");
		model.terminate();
		return "showData";
	}

	public String getData() {
		SalaryGradeModel model = new SalaryGradeModel();
		model.initiate(context, session);
		String[][] data = model.getData(salGrade);
		request.setAttribute("data", data);
		String name = salGrade.getGradeName();
		//logger.info("value of name------------------>>"+name);
		salGrade.setGradeName(name);
		model.terminate();
		getNavigationPanel(3);
		salGrade.setEnableAll("N");
		return "showData";
	}

	public String delete() {
		SalaryGradeModel model = new SalaryGradeModel();
		model.initiate(context, session);
		String salCode = salGrade.getGradeCode();
		boolean result = false;
		result = model.deleteGrade(salGrade, salCode);
		reset();
		if (result) {
			addActionMessage("Record Deleted Successfully");
		}
		reset();
		getNavigationPanel(1);
		model.Data(salGrade, request);
		model.terminate();
		return SUCCESS;
	}

	public String calforedit() {
		try {
			SalaryGradeModel model = new SalaryGradeModel();
			model.initiate(context, session);
			String[][] data = model.calforedit(salGrade);
			request.setAttribute("data", data);
			getNavigationPanel(3);
			salGrade.setEnableAll("N");
			model.terminate();

			return "showData";
		} catch (Exception e) {
			logger.info("Exception in calforedit --  " + e);
			return null;
		}
	}

	public String f9SelectGrade() throws Exception {
		/**
		 * 		BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT 
		 */

		logger.info("In f9 action===========1");
		String query = "SELECT SALGRADE_TYPE,SALGRADE_CODE FROM HRMS_SALGRADE_HDR ORDER BY UPPER(SALGRADE_TYPE) ";

		String[] headers = { getMessage("salary.gradeName") };
		String[] headerWidth = { "30" };
		//logger.info("In f9 action===========2");
		String[] fieldNames = { "salGrade.gradeName", "salGrade.gradeCode" };

		int[] columnIndex = { 0, 1 };
		String submitFlag = "true";

		//logger.info("In f9 action===========3");

		String submitToMethod = "SalaryGrade_getData.action";

		//logger.info("In f9 action===========4");

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	public String delete1() {

		try {
			String code[] = request.getParameterValues("hdeleteCode");
			SalaryGradeModel model = new SalaryGradeModel();
			model.initiate(context, session);
			boolean result = model.deletecheckedRecords(salGrade, code);

			if (result) {
				addActionMessage(getText("delMessage", ""));
			} else {
				addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
			}

			reset();
			getNavigationPanel(1);
			model.Data(salGrade, request);
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.info("Exception in delete1 --> " + e);
			return null;
		}
	}

}
