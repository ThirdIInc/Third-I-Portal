package org.struts.action.D1;

import org.paradyne.bean.D1.SalaryPlanBean;
import org.paradyne.model.D1.ImprintTypeModel;
import org.paradyne.model.D1.SalaryPlanModel;
import org.paradyne.model.D1.StationaryModel;
import org.struts.lib.ParaActionSupport;

public class SalaryPlanAction extends ParaActionSupport {

	SalaryPlanBean salPlanBean;

	public void prepare_local() throws Exception {
		salPlanBean = new SalaryPlanBean();
		salPlanBean.setMenuCode(2027);
	}

	public Object getModel() {

		return salPlanBean;
	}

	public SalaryPlanBean getSalPlanBean() {
		return salPlanBean;
	}

	public void setSalPlanBean(SalaryPlanBean salPlanBean) {
		this.salPlanBean = salPlanBean;
	}

	public String input() {

		System.out.println("-------inside input method-----");
		SalaryPlanModel model = new SalaryPlanModel();
		model.initiate(context, session);
		model.initialData(salPlanBean, request);
		model.terminate();
		getNavigationPanel(1);
		return "input";
	}

	public String callPage() throws Exception {

		SalaryPlanModel model = new SalaryPlanModel();
		model.initiate(context, session);
		input();
		getNavigationPanel(1);
		model.terminate();

		return "input";
	}

	public String addNew() {
		try {
			SalaryPlanModel model = new SalaryPlanModel();
			model.initiate(context, session);

			getNavigationPanel(1);
			reset();
			return "success";
		} catch (RuntimeException e) {

			e.printStackTrace();
			return null;
		}
	}

	public String reset() {
		salPlanBean.setSalCode("");
		salPlanBean.setZipCode("");
		salPlanBean.setSalaryPlan("");
		getNavigationPanel(2);
		return "success";

	}

	/** Method call for Saving Data* */
	public String saveSalaryPlan() throws Exception {
		try {

			System.out.println("in save call-------------------------");
			SalaryPlanModel model = new SalaryPlanModel();
			model.initiate(context, session);
			boolean result;
			
		//String code= salPlanBean.getZipCode();
	
			if (salPlanBean.getSalCode().equals("")) {
				
			/*	String values[] = request.getParameterValues("textValue");
				String pinCodes="";
				if(values != null && values.length > 0){
					for(int i=0;i<values.length;i++){
						System.out.println("values[i]======"+values[i]);
						if(values.length-1==i){
							pinCodes+=String.valueOf(values[i]);
						}else{
							pinCodes+=String.valueOf(values[i])+",";
						}
					}
				}*/
				
				result = model.insertData(salPlanBean);
				
				if (result) {

					addActionMessage(getMessage("save"));
				//	model.editSalaryplan(salPlanBean, request);
				} else {
					addActionMessage(getMessage("duplicate"));
					reset();
					// new added
				}
			} else {
				/*String values[] = request.getParameterValues("textValue");
				String pinCodes="";
				if(values != null && values.length > 0){
					for(int i=0;i<values.length;i++){
						System.out.println("values[i]======"+values[i]);
						if(values.length-1==i){
							pinCodes+=String.valueOf(values[i]);
						}else{
							pinCodes+=String.valueOf(values[i])+",";
						}
					}
				}*/
				result = model.updateData(salPlanBean);

				if (result) {
					addActionMessage(getMessage("update"));

				} else {
					addActionMessage(getMessage("duplicate"));
					reset();// new added

				}

			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		getNavigationPanel(3);
		salPlanBean.setEnableAll("N");
		return "success";

	}

	public String cancel() {
		input();
		getNavigationPanel(1);
		salPlanBean.setEnableAll("Y");
		return "input";
	}

	public String delete() throws Exception {
		try {
			SalaryPlanModel model = new SalaryPlanModel();
			model.initiate(context, session);
			System.out.println("delete method ------------------------");
			boolean result;
			result = model.delete(salPlanBean, request);
			model.initialData(salPlanBean, request);
			model.terminate();

			if (result) {
				addActionMessage("Record Deleted successfully.");

			}

			getNavigationPanel(1);
			return "input";
		} catch (RuntimeException e) {

			e.printStackTrace();
			return null;
		}
	}

	/**following function is called when delete button is clicked in the jsp page*/

	public String deleteChkRecords() throws Exception {
		try {
			String code[] = request.getParameterValues("hdeleteCode");
			SalaryPlanModel model = new SalaryPlanModel();

			model.initiate(context, session);
			boolean result = model.deleteCheckedRecords(salPlanBean, code);

			if (result) {
				addActionMessage(getText("delMessage", ""));
				reset();
			} else {
				addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
			}

			model.initialData(salPlanBean, request);
			getNavigationPanel(1);
			model.terminate();

			return "input";
		} catch (RuntimeException e) {
			e.printStackTrace();
			return null;

		}
	}

	/**Method call on Double clicked on jsp for edit**/
	public String editSalaryplan() {
		try {
			SalaryPlanModel model = new SalaryPlanModel();
			model.initiate(context, session);
			model.editSalaryplan(salPlanBean, request);
			getNavigationPanel(3);
			salPlanBean.setEnableAll("N");
			model.terminate();
		} catch (RuntimeException e) {

			e.printStackTrace();
		}

		return "success";
	}

	/** This f9action is for Search pop up window */
	public String f9action() throws Exception {

		String query = "SELECT  ZIP_CODE,SALARY_PLAN,ZIP_ID  FROM HRMS_D1_HIRE_ZIP ";
	

		String[] headers = { getMessage("zip_no")};

		String[] headerWidth = { "40"};

		String[] fieldNames = { "zipCode", "salaryPlan","salCode" };

		int[] columnIndex = { 0, 1, 2};

		String submitFlag = "true";

		String submitToMethod = "SalaryPlan_setRecord.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}
	
	/*Search Button Functionality*/

	public String setRecord() throws Exception {
		try {
			SalaryPlanModel model = new SalaryPlanModel();
			model.initiate(context, session);
			model.terminate();
			getNavigationPanel(3);

		} catch (Exception e) {
			e.printStackTrace();
		}

		getNavigationPanel(3);
		salPlanBean.setEnableAll("N");
		return "success";
	}
	
	
	
}
