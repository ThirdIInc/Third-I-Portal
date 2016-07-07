package org.struts.action.D1;

import org.paradyne.bean.D1.ITApplicationBean;
import org.paradyne.model.D1.ITApplicationModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1381
 *
 */
public class ITApplicationAction extends ParaActionSupport {
	
	 /**
     * Report Type PDF.
     */
	public static final String RETURN_TYPE_INPUT = "input"; 
	/**
	 * Set Success.
	 */
	public static final String RETURN_TYPE_SUCCESS = "success";
	/**
	 * Set F9 Page.
	 */
	public static final String F9_PAGE = "f9page";
	/**
     * Used to set N.
     */
	private static final String SET_ENABLE = "N";
	

	/**
	 * Object of ITApplicationBean.
	 */
	private ITApplicationBean itBean;
	
	/**	
	 * Method - prepare_local.
	 * Purpose - For setting Menu Code 
	 */
	public void prepare_local() throws Exception {
		this.itBean = new ITApplicationBean();
		this.itBean.setMenuCode(2047);

	}
	/**
	 * (non-Javadoc).
	 * @see com.opensymphony.xwork2.ModelDriven#getModel().
	 * @return Object.
	 */
	public Object getModel() {

		return this.itBean;
	}

	/**
	 * @return bean Object.
	 */
	public ITApplicationBean getItBean() {
		return this.itBean;
	}

	/**
	 * @param itBean - bean Object.
	 */
	public void setItBean(final ITApplicationBean itBean) {
		this.itBean = itBean;
	}

	/**method name : input.
	 * @see com.opensymphony.xwork2.ActionSupport#input()
	 * @return String.
	 */
	public String input() {
	
		final ITApplicationModel model = new ITApplicationModel();
		model.initiate(context, session);
		model.initialData(this.itBean, request);
		model.terminate();
		this.getNavigationPanel(1);

		return INPUT;
	}

	/**method name : CallPage.
	   * purpose - Method to get list of applications as per the list type, when clicked on paging buttons.
	   * @return input.
	   */
	public String callPage() {

		final ITApplicationModel model = new ITApplicationModel();
		model.initiate(context, session);
		this.input();
		this.getNavigationPanel(1);
		model.terminate();

		return ITApplicationAction.RETURN_TYPE_INPUT;
	}

	/**
	 * purpose - Method to add new record.
	 * @return String.
	 */
	public String addNew() {
		final ITApplicationModel model = new ITApplicationModel();
		model.initiate(context, session);
		this.reset();
		this.getNavigationPanel(2);
		return ITApplicationAction.RETURN_TYPE_SUCCESS;
	}

	/**
	 * method name : reset.
	 * purpose - Resetting fields.
	 * @return String
	 */
	public String reset() {
		this.itBean.setApplicationName("");
		this.itBean.setApplicationSection("");
		this.itBean.setApplicationActive("");
		this.itBean.setItCode("");
		this.getNavigationPanel(2);
		return ITApplicationAction.RETURN_TYPE_SUCCESS;

	}

	/**
	 * method name : save.
	 * purpose : for saving data into database.
	 * @param :none
	 * @return String.
	 * @throws Exception .
	 */
	public String save() throws Exception {
		try {
			
			final ITApplicationModel model = new ITApplicationModel();
			model.initiate(context, session);
			boolean result;
			boolean result1;
			boolean result2;

			if ("".equals(this.itBean.getItCode())) {

				result1 = model.checkDuplicateName(this.itBean);
				result2 = model.checkDuplicateSec(this.itBean);
				if (!result1 && !result2) {
					result = model.insert(this.itBean);
					
					if (result) {

						this.addActionMessage(this.getMessage("save"));

					}

				} 	else if (result1 == true && result2 == true) {
					this.addActionMessage("both Application Name and Application Section duplicate");
					this.reset();
				} 	else if (result1) {
					this.addActionMessage("Duplicate Application Name");
					this.reset();
				} else if (result2) {
					this.addActionMessage("Duplicate Application Section");
					this.reset();
				} 
			} else {
				result = model.update(this.itBean);

				if (result) {
					this.addActionMessage(this.getMessage("update"));

				} else {
					this.addActionMessage(this.getMessage("duplicate"));
					this.reset();

				}

			}

		} catch (final Exception e) {
			e.printStackTrace();
		}

		this.getNavigationPanel(3);
		this.itBean.setEnableAll(ITApplicationAction.SET_ENABLE);
		return ITApplicationAction.RETURN_TYPE_SUCCESS;

	}

	/**
	 * method name : cancel. 
	 * purpose  back to previous page  
	 * @return String.
	 */
	public String cancel() {
		this.input();
		this.getNavigationPanel(1);
		this.itBean.setEnableAll("Y");
		return ITApplicationAction.RETURN_TYPE_INPUT;
	}

	/**
	 * method name : delete.
	 * purpose - deleting particular record.
	 * @return String.
	 */
	public String delete()  {
		final ITApplicationModel model = new ITApplicationModel();
		model.initiate(context, session);
		
		boolean result;
		result = model.delete(this.itBean);
		model.initialData(this.itBean, request);
		model.terminate();

		if (result) {
			this.addActionMessage("Record Deleted successfully.");

		}

		this.getNavigationPanel(1);

		return ITApplicationAction.RETURN_TYPE_INPUT;
	}

	/**
	 * method name : deleteChkRecords.
	 * purpose - deleting multiple records.
	 * @return String.
	 */
	public String deleteChkRecords() {
		final String [] code = request.getParameterValues("hdeleteCode");
		final ITApplicationModel model = new ITApplicationModel();

		model.initiate(context, session);
		final boolean result = model.deleteCheckedRecords(code);

		if (result) {
			this.addActionMessage(this.getText("delMessage", ""));
			this.reset();
		} else {
			this.addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
		}

		model.initialData(this.itBean, request);
		this.getNavigationPanel(1);
		model.terminate();

		return ITApplicationAction.RETURN_TYPE_INPUT;
	}

	/**
	 * method name :editBusinessData.
	 * purpose - Edit Data.
	 * @return String.
	 */
	public String editBusinessData() {
		try {
			final ITApplicationModel model = new ITApplicationModel();
			model.initiate(context, session);
			model.editBusinessData(this.itBean);
			this.getNavigationPanel(3);
			this.itBean.setEnableAll(ITApplicationAction.SET_ENABLE);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();

		}

		return ITApplicationAction.RETURN_TYPE_SUCCESS;
	}

	/**
	 * purpose - Set Application Name.
	 * @return f9page.
	 */
	public String f9itAction()  {

		final String query = "SELECT APPLN_NAME,DECODE(APPLN_ACTIVE,'Y','YES','N','NO'),APPLN_SECTION,APPLN_ID  FROM HRMS_D1_IT_SEC_APPLICATIONS " + " order by APPLN_NAME";

		final String[] headers = {this.getMessage("application_name"), this.getMessage("application_isactive")};

		final String[] headerWidth = {"40", "20"};

		final String[] fieldNames = {"applicationName", "applicationActive", "applicationSection", "itCode"};

		final int[] columnIndex = {0, 1, 2, 3};

		final String submitFlag = "true";

		final String submitToMethod = "ITApplication_setRecord.action";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,	submitFlag, submitToMethod);

		return ITApplicationAction.F9_PAGE;

	}

	/**
	 * metho name :setRecord.
	 * purpose - setting records. 
	 * @return String.
	 * @throws Exception .
	 */
	public String setRecord() throws Exception {
		try {
			final ITApplicationModel model = new ITApplicationModel();
			model.initiate(context, session);
			model.editBusinessData(this.itBean);
			model.terminate();
			this.getNavigationPanel(3);

		} catch (final Exception e) {
			e.printStackTrace();
		}

		this.getNavigationPanel(3);
		this.itBean.setEnableAll(ITApplicationAction.SET_ENABLE);
		return ITApplicationAction.RETURN_TYPE_SUCCESS;
	}

}
