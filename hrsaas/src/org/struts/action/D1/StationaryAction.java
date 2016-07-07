package org.struts.action.D1;

import org.paradyne.bean.D1.StationaryBean;
import org.paradyne.model.D1.StationaryModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1381
 *
 */
public class StationaryAction extends ParaActionSupport {
	/**
	 * Set Return type as input.
	 */
	private static final String RETURN_TYPE_INPUT = "input";
	/**
	 * Set Return type as success.
	 */
	private static final String RETURN_TYPE_SUCCESS = "success";
	/**
	 * Set Message  as duplicate.
	 */
	private static final String MESG_DUPLICATE = "duplicate";
	/**
	 * Set Set Enable All as N.
	 */
	private static final String ENABLEALL_N = "N";

	
	/**
	 * Object of StationaryBean.
	 */
	private StationaryBean stationarybean;

	/**	
	 * Method - prepare_local.
	 * Purpose - For setting Menu Code 
	 */
	public void prepare_local() throws Exception {
		this.stationarybean = new StationaryBean();
		this.stationarybean.setMenuCode(2000);

	}

	/** (non-Javadoc).
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 * @return Object.
	 */
	public Object getModel() {

		return this.stationarybean;
	}

	/**
	 * @return the bean object.
	 */
	public StationaryBean getStationarybean() {
		return this.stationarybean;
	}

	/**
	 * @param stationarybean - to stationarybean to set
	 */
	public void setStationarybean(final StationaryBean stationarybean) {
		this.stationarybean = stationarybean;
	}

	/** (non-Javadoc).
	 * @see org.struts.lib.ParaActionSupport#prepare_withLoginProfileDetails()
	 * method name : prepare_withLoginProfileDetails.
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		final StationaryModel model = new StationaryModel();
		model.initiate(context, session);
		model.terminate();
	}

	/** (non-Javadoc).
	 * @see com.opensymphony.xwork2.ActionSupport#input()
	 * method name : input.
	 * @return String.
	 */
	public String input() {
		final StationaryModel model = new StationaryModel();
		model.initiate(context, session);
		model.initialData(this.stationarybean, request);
		model.terminate();
		this.getNavigationPanel(1);
		return StationaryAction.RETURN_TYPE_INPUT;
	}

	/**
	 * method name : CallPage.
	 * purpose -  paging functionality.
	 * @return String.
	 */
	public String callPage() {

		final StationaryModel model = new StationaryModel();
		model.initiate(context, session);
		this.input();
		this.getNavigationPanel(1);
		model.terminate();

		return StationaryAction.RETURN_TYPE_INPUT;
	}

	/**
	 * method name : addNew.
	 * purpose - Add new record.
	 * @return String.
	 */
	public String addNew() {
		try {
			final StationaryModel model = new StationaryModel();
			model.initiate(context, session);

			this.getNavigationPanel(2);
			this.reset();
			return StationaryAction.RETURN_TYPE_SUCCESS;
		} catch (final Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * method name : reset.
	 * purpose - Reset fields.
	 * @return String.
	 */
	public String reset() {
		this.stationarybean.setStationaryCode("");
		this.stationarybean.setStationaryhiddenCode("");
		this.stationarybean.setStationaryName("");
		this.getNavigationPanel(2);
		return StationaryAction.RETURN_TYPE_SUCCESS;

	}
	
	/**
	 * method name : saveImprintData.
	 * purpose - Method call for Saving Data into DB.
	 * @return String
	 * @throws Exception .
	 */
	public String saveImprintData() throws Exception {
		try {

			
			final StationaryModel model = new StationaryModel();
			model.initiate(context, session);
			boolean result;

			if ("".equals(this.stationarybean.getStationaryhiddenCode())) {
				result = model.insertImprintData(this.stationarybean);
				
				if (result) {

					this.addActionMessage(this.getMessage("save"));

				} else {
					this.addActionMessage(this.getMessage(StationaryAction.MESG_DUPLICATE));
					this.reset();
				}
			} else {

				result = model.updateImprintData(this.stationarybean);

				if (result) {
					this.addActionMessage(this.getMessage("update"));

				} else {
					this.addActionMessage(this.getMessage(StationaryAction.MESG_DUPLICATE));
					this.reset();

				}

			}
		} 	catch (final Exception e) {
			e.printStackTrace();
		}
		
		this.getNavigationPanel(3);
		this.stationarybean.setEnableAll(StationaryAction.ENABLEALL_N);
		return StationaryAction.RETURN_TYPE_SUCCESS;

	}

	/**
	 * method name : cancel.
	 * purpose - cancel adding record.
	 * @return String.
	 */
	public String cancel() {
		this.input();
		this.getNavigationPanel(1);
		this.stationarybean.setEnableAll("Y");
		return StationaryAction.RETURN_TYPE_INPUT;
	}

	/**
	 * method name : delete.
	 * @return String.
	 * @throws Exception .
	 */
	public String delete() throws Exception {
		try {
			final StationaryModel model = new StationaryModel();
			model.initiate(context, session);
			
			boolean result;
			result = model.delete(this.stationarybean);
			model.initialData(this.stationarybean, request);
			model.terminate();

			if (result) {
				this.addActionMessage("Record Deleted successfully.");

			}

			this.getNavigationPanel(1);
			return StationaryAction.RETURN_TYPE_INPUT;
		} catch (final Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * method name : deleteChkRecords.
	 * purpose - Delete multiple records.
	 * @return String.
	 * @throws Exception .
	 */
	public String deleteChkRecords() throws Exception {
		try {
			final String [] code = request.getParameterValues("hdeleteCode");
			final StationaryModel model = new StationaryModel();

			model.initiate(context, session);
			final boolean result = model.deleteCheckedRecords(this.stationarybean, code);

			if (result) {
				this.addActionMessage(this.getText("delMessage", ""));
				this.reset();
			} else {
				this.addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
			}

			model.initialData(this.stationarybean, request);
			this.getNavigationPanel(1);
			model.terminate();

			return StationaryAction.RETURN_TYPE_INPUT;
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		
		}
	}
	
	/**
	 * method name : editSoftwareReqData.
	 * purpose - method called on double clicked on lit jsp for edit.
	 * @return String.
	 */
	public String editSoftwareReqData() {
		try {
			final StationaryModel model = new StationaryModel();
			model.initiate(context, session);
			model.editSoftwareReqData(this.stationarybean);
			this.getNavigationPanel(3);
			this.stationarybean.setEnableAll(StationaryAction.ENABLEALL_N);
			model.terminate();
		} catch (final Exception e) {
			
			e.printStackTrace();
		}

		return StationaryAction.RETURN_TYPE_SUCCESS;
	}
	
	/**
	 * purpose - set Stationary Name into respective field.
	 * @return String.
	 */ 
	public String f9action()  {

		final String query = "SELECT  STATIONARY_NAME,STATIONARY_CODE,STATIONARY_ID  FROM HRMS_D1_STATIONARY ";
		

		final String[] headers = {this.getMessage("stationary_name")};

		final String[] headerWidth = {"40"};

		final String[] fieldNames = {"stationaryName", "stationaryCode", "stationaryhiddenCode"};

		final int[] columnIndex = {0, 1, 2};

		final String submitFlag = "true";

		final String submitToMethod = "Stationary_setRecord.action";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	/**
	 * method name : setRecord.
	 * purpose - Set Records.
	 * @return String.
	 * @throws Exception .
	 */
	public String setRecord() throws Exception {
		try {
			final StationaryModel model = new StationaryModel();
			model.initiate(context, session);
			model.terminate();
			this.getNavigationPanel(3);

		} catch (final Exception e) {
			e.printStackTrace();
		}

		this.getNavigationPanel(3);
		this.stationarybean.setEnableAll(StationaryAction.ENABLEALL_N);
		return StationaryAction.RETURN_TYPE_SUCCESS;
	}
	
}
