package org.struts.action.D1;

import org.paradyne.bean.D1.SoftwareRequestBean;
import org.paradyne.model.D1.SoftwareRequestModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1381
 *
 */
public class SoftwareRequestAction extends ParaActionSupport {
	/**
	 * Set Return Type as input.
	 */
	private static final String RETURN_TYPE = "input";	
	/**
	 * Set Return Type as success.
	 */
	private static final String RETURN_TYPE_SUCCESS = "success";	
	/**
	 * Set Set Enable All as N.
	 */
	private static final String SET_ENABLE = "N";	
	/**
	 * Set Message Duplicate.
	 */
	private static final String MESG_DISPLAY = "duplicate";

	/**
	 * Object of SoftwareRequestBean.
	 */
	private SoftwareRequestBean softwarereqBean;
	

	/**	
	 * Method - prepare_local.
	 * Purpose - For setting Menu Code 
	 */
	public void prepare_local() throws Exception {
		this.softwarereqBean = new SoftwareRequestBean();
		this.softwarereqBean.setMenuCode(1198);
	}

	/** (non-Javadoc).
	 * @see com.opensymphony.xwork2.ModelDriven#getModel().
	 * @return bean object.
	 */
	public Object getModel() {
		
		return this.softwarereqBean;
	}

	
	/**
	 * @return bean object. 
	 */
	public SoftwareRequestBean getSoftwarereqBean() {
		return this.softwarereqBean;
	}

	/**
	 * @param softwarereqBean - set softwarereqBean.
	 */
	public void setSoftwarereqBean(final SoftwareRequestBean softwarereqBean) {
		this.softwarereqBean = softwarereqBean;
	}

	/**
	 * (non-Javadoc).
	 * method name : prepare_withLoginProfileDetails.
	 * purpose - to retrieve the details for daily work flow.
	 * @throws Exception - if login is not proper.
	 * @see org.struts.lib.ParaActionSupport#prepare_withLoginProfileDetails()
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		final SoftwareRequestModel model = new SoftwareRequestModel();
		model.initiate(context, session);
		model.terminate();
	}

	
	/** (non-Javadoc).
	 * @see com.opensymphony.xwork2.ActionSupport#input()
	 * method name : input, this is the first method call before any action & it initialize all populated fields in this method.
	 * @return String.
	 */
	public String input() {
		final SoftwareRequestModel model = new SoftwareRequestModel();
		model.initiate(context, session);
		model.initialData(this.softwarereqBean, request);
		model.terminate();
		this.getNavigationPanel(1);
		return SoftwareRequestAction.RETURN_TYPE;
	}
	
	
	/**
	 * method name : CallPage.
	 * purpose - paging purpose.
	 * @return String.
	 */
	public String callPage() {

		final SoftwareRequestModel model = new SoftwareRequestModel();
		model.initiate(context, session);
		this.input();
		this.getNavigationPanel(1);
		model.terminate();
		return SoftwareRequestAction.RETURN_TYPE;
	}

	/**
	 * method name : addNew.
	 * purpose - add new record.
	 * @return String.
	 */
	public String addNew() {
		try {
			final SoftwareRequestModel model = new SoftwareRequestModel();
			model.initiate(context, session);
			this.reset();
			this.getNavigationPanel(2);
			return SoftwareRequestAction.RETURN_TYPE_SUCCESS;
		} catch (final Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * method name : reset.
	 * purpose - Reset respective fields.
	 * @return String.
	 */
	public String reset() {
		this.softwarereqBean.setSoftwareReqCode("");
		this.softwarereqBean.setSoftwareCode("");
		this.softwarereqBean.setSoftwareName("");
		this.getNavigationPanel(2);
		return SoftwareRequestAction.RETURN_TYPE_SUCCESS;

	}
	
	
	/**
	 * method name : saveSoftwareReqData.
	 * purpose - Method call for Saving Data into DB.
	 * @return String.
	 * @throws Exception .
	 */
	public String saveSoftwareReqData() throws Exception {
		try {
			
			final SoftwareRequestModel model = new SoftwareRequestModel();
			model.initiate(context, session);
			boolean result;

			if ("".equals(this.softwarereqBean.getSoftwareReqCode())) {
				result = model.insertSoftReqData(this.softwarereqBean);
				
				if (result) {

					this.addActionMessage(this.getMessage("save"));

				} else {
					this.addActionMessage(this.getMessage(SoftwareRequestAction.MESG_DISPLAY));
					this.reset();
					
				}
			} else {

				result = model.updateSoftReqData(this.softwarereqBean);

				if (result) {
					this.addActionMessage(this.getMessage("update"));

				} else {
					this.addActionMessage(this.getMessage(SoftwareRequestAction.MESG_DISPLAY));
					this.reset();
				}
			}
		}  	catch (final Exception e) {
			e.printStackTrace();
		}
		
		this.getNavigationPanel(3);
		this.softwarereqBean.setEnableAll(SoftwareRequestAction.SET_ENABLE);
		return SoftwareRequestAction.RETURN_TYPE_SUCCESS;

	}

	/**
	 * method name : cancel.
	 * purpose - cancel to saving data.
	 * @return String.
	 */
	public String cancel() {
		this.input();
		this.getNavigationPanel(1);
		this.softwarereqBean.setEnableAll("Y");
		return SoftwareRequestAction.RETURN_TYPE;
	}

	/**
	 * method name : delete.
	 * purpose - deleting particular record.
	 * @return String.
	 * @throws Exception .
	 */
	public String delete() throws Exception {
		try {
			final SoftwareRequestModel model = new SoftwareRequestModel();
			model.initiate(context, session);
			boolean result;
			result = model.delete(this.softwarereqBean);
			model.initialData(this.softwarereqBean, request);
			model.terminate();
			if (result) {
				this.addActionMessage("Record Deleted successfully.");
			}
			this.getNavigationPanel(1);
			return SoftwareRequestAction.RETURN_TYPE;
		} catch (final Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * method name : deleteChkRecords.
	 *  purpose - following function is called when delete button is clicked in the jsp page.  
	 * @return String.
	 * @throws Exception .
	 */
	public String deleteChkRecords() throws Exception {
		try {
			final String [] code = request.getParameterValues("hdeleteCode");
			final SoftwareRequestModel model = new SoftwareRequestModel();

			model.initiate(context, session);
			final boolean result = model.deleteCheckedRecords(this.softwarereqBean, code);

			if (result) {
				this.addActionMessage(this.getText("delMessage", ""));
				this.reset();
			} else {
				this.addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
			}

			model.initialData(this.softwarereqBean, request);
			this.getNavigationPanel(1);
			model.terminate();

			return SoftwareRequestAction.RETURN_TYPE;
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		
		}
	}

	/**
	 * method name : editSoftwareReqData.
	 * purpose - Method call on Double clicked on jsp for edit.
	 * @return String.
	 */
	public String editSoftwareReqData() {
		try {
			final SoftwareRequestModel model = new SoftwareRequestModel();
			model.initiate(context, session);
			model.editSoftwareReqData(this.softwarereqBean);
			this.getNavigationPanel(3);
			this.softwarereqBean.setEnableAll(SoftwareRequestAction.SET_ENABLE);
			model.terminate();
		} catch (final Exception e) {
			
			e.printStackTrace();
		}

		return SoftwareRequestAction.RETURN_TYPE_SUCCESS;
	}
	
	/**
	 * purpose - set software name.
	 * @return String.
	 */
	public String f9action()  {

		final String query = "SELECT  SPECIAL_SOFTWARE_NAME,SPECIAL_SOFTWARE_CODE,SPECIAL_SOFTWARE_ID  FROM HRMS_D1_SPECIAL_SW_REQ ";
                        	         

		final String[] headers = {this.getMessage("software_name")};

		final String[] headerWidth = {"40"};

		final String[] fieldNames = {"softwareName", "softwareCode", "softwareReqCode"};

		final int[] columnIndex = {0, 1, 2};

		final String submitFlag = "true";

		final String submitToMethod = "SoftwareRequest_setRecord.action";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}
	
	/**
	 * method name : setRecord.
	 * purpose - Set Record.
	 * @return String.
	 */
	public String setRecord()  {
		
		final SoftwareRequestModel model = new SoftwareRequestModel();
		model.initiate(context, session);
		model.terminate();
		this.getNavigationPanel(3);

		this.getNavigationPanel(3);
		this.softwarereqBean.setEnableAll(SoftwareRequestAction.SET_ENABLE);
		return SoftwareRequestAction.RETURN_TYPE_SUCCESS;
	}
	
	
	
}
