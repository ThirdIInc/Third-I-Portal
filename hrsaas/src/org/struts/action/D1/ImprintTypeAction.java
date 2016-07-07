package org.struts.action.D1;

import org.paradyne.bean.D1.ImprintTypeBean;
import org.paradyne.model.D1.ImprintTypeModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1381
 *
 */
public class ImprintTypeAction extends ParaActionSupport {
	/**
	 * Set Return Type as input.
	 */
	private static final String RETURN_TYPE_INPUT = "input";
	/**
	 * Set Return Type as success.
	 */
	private static final String RETURN_TYPE_SUCCESS = "success";
	/**
	 * Set Enable all as N.
	 */
	private static final String ENABLEALL_N = "N";
	/**
	 * Set Duplicate Message.
	 */
	private static final String MESSAGE_DUP = "duplicate";
	
	
	
	/**
	 * Object of ImprintTypeBean.
	 */
	private ImprintTypeBean imprintBean;
	
	/**	
	 * Method - prepare_local.
	 * Purpose - For setting Menu Code 
	 */
	public void prepare_local() throws Exception {
		this.imprintBean = new ImprintTypeBean();
		this.imprintBean.setMenuCode(1199);
	}

	/** (non-Javadoc).
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 * @return bean object.
	 */
	public Object getModel() {
		
		return this.imprintBean;
	}

	/**
	 * @return  imprintBean object.
	 */
	public ImprintTypeBean getImprintBean() {
		return this.imprintBean;
	}

	/**
	 * @param imprintBean the imprintBean to set
	 */
	public void setImprintBean(final ImprintTypeBean imprintBean) {
		this.imprintBean = imprintBean;
	}

	/**(non-Javadoc).
	 * @see org.struts.lib.ParaActionSupport#prepare_withLoginProfileDetails()
	 * method name :prepare_withLoginProfileDetails.
	 * @throws Exception - Login related exception.
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		final ImprintTypeModel model = new ImprintTypeModel();
		model.initiate(context, session);
		model.terminate();
	}

	/** (non-Javadoc).
	 * @see com.opensymphony.xwork2.ActionSupport#input()
	 * method name : input.
	 * @return String.
	 */
	public String input() {
		final ImprintTypeModel model = new ImprintTypeModel();
		model.initiate(context, session);
		model.initialData(this.imprintBean, request);
		model.terminate();
		this.getNavigationPanel(1);
		return ImprintTypeAction.RETURN_TYPE_INPUT;
	}
	
	/**
	 * method name : CallPage. 
	 * @return String.
	 * @throws Exception - giving page numbers not proper way i.e in minus.
	 */
	public String callPage() throws Exception {

		final ImprintTypeModel model = new ImprintTypeModel();
		model.initiate(context, session);
		this.input();
		this.getNavigationPanel(1);
		model.terminate();

		return ImprintTypeAction.RETURN_TYPE_INPUT;
	}

	/**
	 * method name :  addNew.
	 * purpose - To add new record. 
	 * @return String.
	 */
	public String addNew() {
		try {
			final ImprintTypeModel model = new ImprintTypeModel();
			model.initiate(context, session);

			this.getNavigationPanel(1);
			this.reset();
			return ImprintTypeAction.RETURN_TYPE_SUCCESS;
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
		this.imprintBean.setImprintTypeCode("");
		this.imprintBean.setImprintCode("");
		this.imprintBean.setImprintName("");
		this.getNavigationPanel(2);
		return ImprintTypeAction.RETURN_TYPE_SUCCESS;

	}
	
	
	/**
	 * method name : saveImprintData. 
	 * purpose -  method call for saving data into DB. 
	 * @return String.
	 * @throws Exception - 500 Error.
	 */
	public String saveImprintData() throws Exception {
		try {
			
			final ImprintTypeModel model = new ImprintTypeModel();
			model.initiate(context, session);
			boolean result;
			

			if ("".equals(this.imprintBean.getImprintTypeCode())) {
				result = model.insertImprintData(this.imprintBean);
			
				if (result) {

					this.addActionMessage(this.getMessage("save"));

				} else {
					this.addActionMessage(this.getMessage(ImprintTypeAction.MESSAGE_DUP));
					this.reset();
					
				}
			} else {

				result = model.updateImprintData(this.imprintBean);

				if (result) {
					this.addActionMessage(this.getMessage("update"));

				} else {
					this.addActionMessage(this.getMessage(ImprintTypeAction.MESSAGE_DUP));
					this.reset();

				}

			}

		} 	catch (final Exception e) {
			e.printStackTrace();
		}
		this.getNavigationPanel(3);
		this.imprintBean.setEnableAll(ImprintTypeAction.ENABLEALL_N);
		return ImprintTypeAction.RETURN_TYPE_SUCCESS;

	}

	/**
	 * method name : cancel.
	 * purpose - cancel for adding record into DB.
	 * @return String.
	 */
	public String cancel() {
		this.input();
		this.getNavigationPanel(1);
		this.imprintBean.setEnableAll("Y");
		return ImprintTypeAction.RETURN_TYPE_INPUT;
	}

	/**
	 * method name : delete
	 * purpose - delete sing record.
	 * @return String.
	 * @throws Exception  .
	 */
	public String delete() throws Exception {
		try {
			final ImprintTypeModel model = new ImprintTypeModel();
			model.initiate(context, session);
			
			boolean result;
			result = model.delete(this.imprintBean);
			model.initialData(this.imprintBean, request);
			model.terminate();

			if (result) {
				this.addActionMessage("Record Deleted successfully.");

			}

			this.getNavigationPanel(1);
			return ImprintTypeAction.RETURN_TYPE_INPUT;
		} catch (final Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * method name : deleteChkRecords.
	 * purpose - Deleting multiple records.
	 * @return String.
	 * @throws Exception -related to codes if they are not got then no record will get delete.
	 */
	
	public String deleteChkRecords() throws Exception {
		try {
			final String [] code = request.getParameterValues("hdeleteCode");
			final ImprintTypeModel model = new ImprintTypeModel();

			model.initiate(context, session);
			final boolean result = model.deleteCheckedRecords(this.imprintBean, code);

			if (result) {
				this.addActionMessage(this.getText("delMessage", ""));
				this.reset();
			} else {
				this.addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
			}

			model.initialData(this.imprintBean, request);
			this.getNavigationPanel(1);
			model.terminate();

			return ImprintTypeAction.RETURN_TYPE_INPUT;
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		
		}
	}

	/**
	 * method name : editImprintData.
	 * purpose - method called on double clicked on jsp for edit.
	 * @return String.
	 */
	public String editImprintData() {
		try {
			final ImprintTypeModel model = new ImprintTypeModel();
			model.initiate(context, session);
			model.editSoftwareReqData(this.imprintBean);
			this.getNavigationPanel(3);
			this.imprintBean.setEnableAll(ImprintTypeAction.ENABLEALL_N);
			model.terminate();
		} catch (final Exception e) {
			
			e.printStackTrace();
		}

		return ImprintTypeAction.RETURN_TYPE_SUCCESS;
	}
	
	/**
	 * purpose - to set Imprint name to respective field.
	 * @return String.
	 */
	public String f9action()  {

		final String query = "SELECT  IMPRINT_NAME,IMPRINT_CODE,IMPRINT_ID  FROM HRMS_D1_IMPRINT_TYPE ";
                        	         

		final String[] headers = {this.getMessage("imprint_name")};

		final String[] headerWidth = {"40"};

		final String[] fieldNames = {"imprintName", "imprintCode", "imprintTypeCode"};

		final int[] columnIndex = {0, 1, 2};

		final String submitFlag = "true";

		final String submitToMethod = "ImprintType_setRecord.action";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,	submitFlag, submitToMethod);

		return "f9page";

	}
	
	/**
	 * purpose - set the record on search button.
	 * @return String. 
	 */
	public String setRecord() {
		try {
			final ImprintTypeModel model = new ImprintTypeModel();
			model.initiate(context, session);
			model.terminate();
			this.getNavigationPanel(3);

		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.getNavigationPanel(3);
		this.imprintBean.setEnableAll(ImprintTypeAction.ENABLEALL_N);
		return ImprintTypeAction.RETURN_TYPE_SUCCESS;
	}
	
}
