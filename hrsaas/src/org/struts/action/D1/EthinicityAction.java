package org.struts.action.D1;

import org.paradyne.bean.D1.EthinicityBean;
import org.paradyne.model.D1.EthinicityModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1381
 *
 */
public class EthinicityAction extends ParaActionSupport {
	/**
	 * Set Return Type as input.
	 */
	private static final String RETURN_TYPE_INPUT = "input";
	/**
	 * Set Return Type as success.
	 */
	private static final String RETURN_TYPE_SUCCESS = "success";
	/**
	 * Set Message for duplicate.
	 */
	private static final String DUPLICATE_MESG = "duplicate";	
	/**
	 * Set SetEnable All as N.
	 */
	private static final String SETENABLE_ALL_N = "N";

	/**
	 * bean instance created.
	 */
	private EthinicityBean ethinicity;
	

	/**	
	 * Method - prepare_local.
	 * Purpose - For setting Menu Code 
	 */
	public void prepare_local() throws Exception {
		this.ethinicity = new EthinicityBean();
		this.ethinicity.setMenuCode(2037);
	}

	/** (non-Javadoc).
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 * @return Object.
	 */
	public Object getModel() {
		
		return this.ethinicity;
	}

	
	/**
	 * @return bean instance.
	 */
	public EthinicityBean getEthinicity() {
		return this.ethinicity;
	}

	
	/**
	 * @param ethinicity - set bean object.
	 */
	public void setEthinicity(final EthinicityBean ethinicity) {
		this.ethinicity = ethinicity;
	}

	/** (non-Javadoc).
	 * @see com.opensymphony.xwork2.ActionSupport#input()
	 * method name : input.
	 * @return String.
	 */
	public String input() {
		
		final EthinicityModel model = new EthinicityModel();
		model.initiate(context, session);
		model.initialData(this.ethinicity, request);
		model.terminate();
		this.getNavigationPanel(1);
		return EthinicityAction.RETURN_TYPE_INPUT;
	}
	
	/**
	 * method name : CallPage.
	 * purpose -  paging functionality.
	 * @return String.
	 */
	public String callPage() {

		final EthinicityModel model = new EthinicityModel();
		model.initiate(context, session);
		this.input();
		this.getNavigationPanel(1);
		model.terminate();

		return EthinicityAction.RETURN_TYPE_INPUT;
	}


	/**
	 * method name : addNew.
	 * purpose - Add new record.
	 * @return String.
	 */
	public String addNew() {
		final EthinicityModel model = new EthinicityModel();
		model.initiate(context, session);
		this.reset();
		this.getNavigationPanel(2);
		return EthinicityAction.RETURN_TYPE_SUCCESS;
	}

	/**
	 * method name : reset.
	 * purpose - Reset fields.
	 * @return String.
	 */
	public String reset() {
		this.ethinicity.setEthinicity("");
		this.ethinicity.setEthicCode("");
		this.getNavigationPanel(2);
		return EthinicityAction.RETURN_TYPE_SUCCESS;

	}

	/**
	 * method name : saveImprintData.
	 * purpose - Method call for Saving Data into DB.
	 * @return String
	 * @throws Exception .
	 */
	public String save() throws Exception {
		try {
			final EthinicityModel model = new EthinicityModel();
			model.initiate(context, session);
			boolean result;

			if ("".equals(this.ethinicity.getEthicCode())) {
				result = model.insert(this.ethinicity);
				
				if (result) {

					this.addActionMessage(this.getMessage("save"));

				} else {
					this.addActionMessage(this.getMessage(EthinicityAction.DUPLICATE_MESG));
					this.reset();
					
				}
			} else {

				result = model.update(this.ethinicity);

				if (result) {
					this.addActionMessage(this.getMessage("update"));
					
				} else {
					this.addActionMessage(this.getMessage(EthinicityAction.DUPLICATE_MESG));
					this.reset();

				}

			}

		}  	catch (final Exception e) {
			e.printStackTrace();
		}
		
		this.getNavigationPanel(3);
		this.ethinicity.setEnableAll(EthinicityAction.SETENABLE_ALL_N);
		return EthinicityAction.RETURN_TYPE_SUCCESS;

	}

	/**
	 * method name : cancel.
	 * purpose - cancel adding record.
	 * @return String.
	 */
	public String cancel() {
		this.input();
		this.getNavigationPanel(1);
		this.ethinicity.setEnableAll("Y");
		return EthinicityAction.RETURN_TYPE_INPUT;
	}

	/**
	 * method name : delete.
	 * @return String.
	 */
	public String delete()  {
		final EthinicityModel model = new EthinicityModel();
		model.initiate(context, session);
		
		boolean result;
		result = model.delete(this.ethinicity);
		model.initialData(this.ethinicity, request);
		model.terminate();

		if (result) {
			this.addActionMessage("Record Deleted successfully.");

		}

		this.getNavigationPanel(1);

		return EthinicityAction.RETURN_TYPE_INPUT;
	}

	
	/**
	 * method name : deleteChkRecords.
	 * purpose - Delete multiple records.
	 * @return String.
	 * @throws Exception .
	 */
	public String deleteChkRecords() throws Exception {
		final String [] code = request.getParameterValues("hdeleteCode");
		final EthinicityModel model = new EthinicityModel();

		model.initiate(context, session);
		final boolean result = model.deleteCheckedRecords(this.ethinicity, code);

		if (result) {
			this.addActionMessage(this.getText("delMessage", ""));
			this.reset();
		} else {
			this.addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
		}

		model.initialData(this.ethinicity, request);
		this.getNavigationPanel(1);
		model.terminate();

		return EthinicityAction.RETURN_TYPE_INPUT;
	}
	
	/**
	 * method name : editBusinessData.
	 * purpose - method called on double clicked on lit jsp for edit.
	 * @return String.
	 */
	public String editBusinessData() {
		try {
			final EthinicityModel model = new EthinicityModel();
			model.initiate(context, session);
			model.editBusinessData(this.ethinicity);
			this.getNavigationPanel(3);
			this.ethinicity.setEnableAll(EthinicityAction.SETENABLE_ALL_N);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace(); 
		}
		return EthinicityAction.RETURN_TYPE_SUCCESS;
	}
	
	/**
	 * purpose - set Ethnicity Name into respective field.
	 * @return String.
	 */ 
	public String f9action()  {

		final String query = "SELECT  ETHINICITY,ETHIC_CODE  FROM HRMS_D1_ETHIC ";
                        	         

		final String[] headers = {this.getMessage("ethic_name")};

		final String[] headerWidth = {"40"};

		final String[] fieldNames = {"ethinicity", "ethicCode"};

		final int[] columnIndex = {0, 1};

		final String submitFlag = "true";

		final String submitToMethod = "Ethinicity_setRecord.action";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

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
			final EthinicityModel model = new EthinicityModel();
			model.initiate(context, session);
			model.terminate();
			this.getNavigationPanel(3);

		} catch (final Exception e) {
			e.printStackTrace();
		}

		this.getNavigationPanel(3);
		this.ethinicity.setEnableAll(EthinicityAction.SETENABLE_ALL_N);
		return EthinicityAction.RETURN_TYPE_SUCCESS;
	}
	
}
