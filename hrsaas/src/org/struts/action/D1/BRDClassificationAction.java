package org.struts.action.D1;

import org.paradyne.bean.D1.BRDClassification;
import org.paradyne.model.D1.BRDClassificationModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1380
 *
 */
public class BRDClassificationAction extends ParaActionSupport {

	BRDClassification bean = null; 
	
	public void prepare_local() throws Exception {
		bean = new BRDClassification();
		bean.setMenuCode(5037);
	}

	/**
	 * @return the bean
	 */
	public BRDClassification getBean() {
		return this.bean;
	}

	/**
	 * @param bean the bean to set
	 */
	public void setBean(BRDClassification bean) {
		this.bean = bean;
	}

	public Object getModel() {
		return bean;
	}
	
	/**method name : input.
	 * Purpose : for getting initial list page
	 * @return String.
	 */
	public String input() {
		BRDClassificationModel model = new BRDClassificationModel();
		model.initiate(context, session);
		model.getInitialData(bean, request);
		this.getNavigationPanel(1);
		model.terminate();
		return INPUT;
	}
	
	/**method name : CallPage.
	   * purpose - Method to get list of applications as per the list type, when clicked on paging buttons.
	   * @return input.
	   */
	public String callPage()  {
		final BRDClassificationModel model = new BRDClassificationModel();
		model.initiate(context, session);
		this.input();
		this.getNavigationPanel(1);
		model.terminate();
		return INPUT;
	}
	
	/**
	 * purpose - Method to add new record.
	 * @return String.
	 */
	public String addNew() {
		final BRDClassificationModel model = new BRDClassificationModel();
		model.initiate(context, session);
		this.reset();
		this.getNavigationPanel(2);
		return SUCCESS;
	}
	
	/**
	 * method name : reset.
	 * purpose - Resetting fields.
	 * @return String
	 */
	public String reset() {
		this.bean.setClassificationCode("");
		this.bean.setClassificationName("");
		this.getNavigationPanel(2);
		return SUCCESS;
	}
	
	
	/**
	 * method name :editData.
	 * purpose - Edit Data.
	 * @return String.
	 * @throws Exception .
	 */
	public String editData() throws Exception  {
		try {
			final BRDClassificationModel model = new BRDClassificationModel();
			model.initiate(context, session);
			model.editBusinessData(this.bean);
			this.getNavigationPanel(3);
			this.bean.setEnableAll("N");
			model.terminate();
		}  catch (final Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	/**
	 * purpose - Set Business Unit Code & Name. 
	 * @return f9page
	 */
	public String f9action() {
		final String query = "SELECT CLASSIFICATION_NAME, CLASSIFICATION_ID FROM HRMS_D1_BRD_CLASSIFICATION ";
		final String[] headers = {this.getMessage("classificationNameLabel")};
		final String[] headerWidth = {"15"};
		final String[] fieldNames = {"classificationName", "classificationCode"};
		final int[] columnIndex = {0, 1};
		final String submitFlag = "true";
		final String submitToMethod = "BRDClassification_setRecord.action";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,	submitFlag, submitToMethod);
		return "f9page";
	}
	
	/*Search Button Functionality*/

	/**
	 * metho name :setRecord.
	 * purpose - setting records. 
	 * @return String.
	 * @throws Exception .
	 */
	public String setRecord() throws Exception {
		try {
			final BRDClassificationModel model = new BRDClassificationModel();
			model.initiate(context, session);
			model.terminate();
			this.getNavigationPanel(3);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.getNavigationPanel(3);
		this.bean.setEnableAll("N");
		return SUCCESS;
	}
	
	/**
	 * method name : cancel. 
	 * purpose  back to previous page  
	 * @return String.
	 */
	public String cancel() {
		this.input();
		this.getNavigationPanel(1);
		this.bean.setEnableAll("Y");
		return INPUT;
	}
	
	/**
	 * method name : delete.
	 * purpose - deleting particular record.
	 * @return String.
	 */
	public String delete()  {
		final BRDClassificationModel model = new BRDClassificationModel();
		model.initiate(context, session);
		boolean result;
		result = model.delete(this.bean);
		model.getInitialData(this.bean, request);
		model.terminate();
		if (result) {
			this.addActionMessage("Record Deleted successfully.");
		}
		this.getNavigationPanel(1);
		return INPUT;
	}
	
	
	/**
	 * method name : saveData.
	 * purpose : for saving data into database.
	 * @param :none
	 * @return String.
	 * @throws Exception .
	 */
	public String saveData() throws Exception {
		try {
			final BRDClassificationModel model = new BRDClassificationModel();
			model.initiate(context, session);
			boolean result;
			if ("".equals(this.bean.getClassificationCode())) {
				result = model.insertData(this.bean);
				if (result) {
					this.addActionMessage(this.getMessage("save"));
					this.getNavigationPanel(3);
					this.bean.setEnableAll("N");
				} else {
					this.addActionMessage(this.getMessage("duplicate"));
					this.reset();
					this.getNavigationPanel(2);
					this.bean.setEnableAll("Y");
				}
			} else {
				result = model.updateData(this.bean);
				if (result) {
					this.addActionMessage(this.getMessage("update"));
				} else {
					this.addActionMessage(this.getMessage("duplicate"));
				}
				this.getNavigationPanel(3);
				this.bean.setEnableAll("N");
		    }
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * method name : deleteChkRecords.
	 * purpose - deleting multiple records.
	 * @return String.
	 */
	public String deleteChkRecords()  {
		final String [] code = request.getParameterValues("hdeleteCode");
		final BRDClassificationModel model = new BRDClassificationModel();

		model.initiate(context, session);
		final boolean result = model.deleteCheckedRecords(this.bean, code);
		if (result) {
			this.addActionMessage(this.getText("delMessage", ""));
			this.reset();
		} else {
			this.addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
		}
		this.input();
		this.getNavigationPanel(1);
		model.terminate();
		return INPUT;
	}

	


}
