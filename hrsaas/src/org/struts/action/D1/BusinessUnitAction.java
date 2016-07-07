package org.struts.action.D1;

import org.paradyne.bean.D1.BusinessUnitBean;
import org.paradyne.model.D1.BusinessUnitModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1381
 *
 */
public class BusinessUnitAction extends ParaActionSupport {
	
	 /**
     * Report Type PDF.
     */
	public static final String RETURN_TYPE_INPUT = "input"; 
	/**
	 * Set Success.
	 */
	public static final String RETURN_TYPE_SUCCESS = "success";
	/**
	 * Set F9 PAge.
	 */
	public static final String F9_PAGE = "f9page";
	/**
     * Used to set N.
     */
	private static final String SET_ENABLE = "N";
	/**
	 * Set Duplicate.
	 */
	private static final String MESSAGE_DUP = "duplicate";
	
	
	
	

	/**
	 * Object of BusinessUnitBean.
	 */
	private BusinessUnitBean businessUnitBean;

	/**	
	 * Method - prepare_local.
	 * Purpose - For setting Menu Code 
	 */
	public void prepare_local() throws Exception {
		this.businessUnitBean = new BusinessUnitBean();
		this.businessUnitBean.setMenuCode(1197);
	}

	/**
	 * (non-Javadoc).
	 * @see com.opensymphony.xwork2.ModelDriven#getModel().
	 * @return Object.
	 */
	public Object getModel() {

		return this.businessUnitBean;
	}

	/**
	 * @return bean Object.
	 */
	public BusinessUnitBean getBusinessUnitBean() {
		return this.businessUnitBean;
	}

	/**
	 * @param businessUnitBean bean Object.
	 */
	public void setBusinessUnitBean(final BusinessUnitBean businessUnitBean) {
		this.businessUnitBean = businessUnitBean;
	}

	/**
	 * method name :prepare_withLoginProfileDetails.
	 * @throws Exception - if login is not proper.
	 * @see org.struts.lib.ParaActionSupport#prepare_withLoginProfileDetails()
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		final BusinessUnitModel model = new BusinessUnitModel();
		model.initiate(context, session);
		model.terminate();
	}

	/**method name : input.
	 * @see com.opensymphony.xwork2.ActionSupport#input()
	 * @return String.
	 */
	public String input() {
		final BusinessUnitModel model = new BusinessUnitModel();
		model.initiate(context, session);
		model.initialData(this.businessUnitBean, request);
		model.terminate();
		this.getNavigationPanel(1);
		return BusinessUnitAction.RETURN_TYPE_INPUT;
	}

	/**method name : CallPage.
	   * purpose - Method to get list of applications as per the list type, when clicked on paging buttons.
	   * @return input.
	   */
	public String callPage()  {

		final BusinessUnitModel model = new BusinessUnitModel();
		model.initiate(context, session);
		this.input();
		this.getNavigationPanel(1);
		model.terminate();
		return BusinessUnitAction.RETURN_TYPE_INPUT;
	}

	/**
	 * purpose - Method to add new record.
	 * @return String.
	 */
	public String addNew() {
		final BusinessUnitModel model = new BusinessUnitModel();
		model.initiate(context, session);
		this.reset();
		this.getNavigationPanel(2);
		return BusinessUnitAction.RETURN_TYPE_SUCCESS;
	}

	/**
	 * method name : reset.
	 * purpose - Resetting fields.
	 * @return String
	 */
	public String reset() {
		this.businessUnitBean.setUnitCode("");
		this.businessUnitBean.setUnitName("");
		this.businessUnitBean.setBusinessCode("");
		this.businessUnitBean.setEmpID("");
		this.businessUnitBean.setEmpName("");
		this.businessUnitBean.setEmpToken("");
		this.getNavigationPanel(2);
		return BusinessUnitAction.RETURN_TYPE_SUCCESS;
	}

	
	/**
	 * method name : saveVendorData.
	 * purpose : for saving data into database.
	 * @param :none
	 * @return String.
	 * @throws Exception .
	 */
	public String saveVendorData() throws Exception {
		try {
			final BusinessUnitModel model = new BusinessUnitModel();
			model.initiate(context, session);
			boolean result;
			if ("".equals(this.businessUnitBean.getBusinessCode())) {
				result = model.insertBusinessData(this.businessUnitBean);
				if (result) {
					this.addActionMessage(this.getMessage("save"));
				} else {
					this.addActionMessage(this
							.getMessage(BusinessUnitAction.MESSAGE_DUP));
					this.reset();
				}
				this.getNavigationPanel(3);
				this.businessUnitBean
						.setEnableAll(BusinessUnitAction.SET_ENABLE);
			} else {
				result = model.updateBusinessData(this.businessUnitBean);
				if (result) {
					this.addActionMessage(this.getMessage("update"));
				} else {
					this.addActionMessage(this
							.getMessage(BusinessUnitAction.MESSAGE_DUP));	
				}
				model.editBusinessData(this.businessUnitBean);
				this.getNavigationPanel(3);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return BusinessUnitAction.RETURN_TYPE_SUCCESS;
	}

	/**
	 * method name : cancel. 
	 * purpose  back to previous page  
	 * @return String.
	 */
	public String cancel() {
		this.input();
		this.getNavigationPanel(1);
		this.businessUnitBean.setEnableAll("Y");
		return BusinessUnitAction.RETURN_TYPE_INPUT;
	}

	/**
	 * method name : delete.
	 * purpose - deleting particular record.
	 * @return String.
	 */
	public String delete()  {
		final BusinessUnitModel model = new BusinessUnitModel();
		model.initiate(context, session);		
		boolean result;
		result = model.delete(this.businessUnitBean);
		model.initialData(this.businessUnitBean, request);
		model.terminate();
		if (result) {
			this.addActionMessage("Record Deleted successfully.");
		}
		this.getNavigationPanel(1);
		return BusinessUnitAction.RETURN_TYPE_INPUT;
	}

	

	/**
	 * method name : deleteChkRecords.
	 * purpose - deleting multiple records.
	 * @return String.
	 */
	public String deleteChkRecords()  {
		final String [] code = request.getParameterValues("hdeleteCode");
		final BusinessUnitModel model = new BusinessUnitModel();
		model.initiate(context, session);
		final boolean result = model.deleteCheckedRecords(this.businessUnitBean, code);
		if (result) {
			this.addActionMessage(this.getText("delMessage", ""));
			this.reset();
		} else {
			this.addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
		}
		model.initialData(this.businessUnitBean, request);
		this.getNavigationPanel(1);
		model.terminate();
		return BusinessUnitAction.RETURN_TYPE_INPUT;
	}
	
	/**
	 * method name :editBusinessData.
	 * purpose - Edit Data.
	 * @return String.
	 * @throws Exception .
	 */
	public String editBusinessData() throws Exception  {
		try {
			final BusinessUnitModel model = new BusinessUnitModel();
			model.initiate(context, session);
			model.editBusinessData(this.businessUnitBean);
			this.getNavigationPanel(3);
			this.businessUnitBean.setEnableAll(BusinessUnitAction.SET_ENABLE);
			model.terminate();
		}  catch (final Exception e) {
			e.printStackTrace();
		}
		return BusinessUnitAction.RETURN_TYPE_SUCCESS;
	}

	
	/**
	 * purpose - Set Division.
	 * @return f9page.
	 */
	public String f9division()  {

		String query = "SELECT DISTINCT DIV_ID, DIV_NAME FROM HRMS_DIVISION WHERE IS_ACTIVE ='Y'";

		if (this.businessUnitBean.getUserProfileDivision() != null && this.businessUnitBean.getUserProfileDivision().length() > 0)	{
			query += " AND DIV_ID IN (" + this.businessUnitBean.getUserProfileDivision() + ")"; 
			query += " ORDER BY  DIV_ID ";
		}
				
		final String[] headers = {"DivisionId", this.getMessage("business_unit_div")};

		final String[] headerWidth = {"20", "80"};

		final String[] fieldNames = {"divId", "unitDivision"};

		final int[] columnIndex = {0, 1};

		final String submitFlag = "false";

		final String submitToMethod = "";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return BusinessUnitAction.F9_PAGE;

	}	
	
	
	/**
	 * purpose - Set Business Unit Code & Name. 
	 * @return f9page
	 */
	public String f9action() {

		final String query = "SELECT BUSS_UNIT_NAME,BUSS_UNIT_CODE,BUSS_UNIT_ID  FROM HRMS_D1_BUSSINESS_UNIT ";
                         		         

		final String[] headers = {this.getMessage("business_unit_name")};

		final String[] headerWidth = {"15"};

		final String[] fieldNames = {"unitName", "unitCode", "businessCode"};

		final int[] columnIndex = {0, 1, 2};

		final String submitFlag = "true";

		final String submitToMethod = "BusinessUnit_setRecord.action";

		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex,	submitFlag, submitToMethod);

		return BusinessUnitAction.F9_PAGE;

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
			final BusinessUnitModel model = new BusinessUnitModel();
			model.initiate(context, session);
			model.terminate();
			this.getNavigationPanel(3);

		} catch (final Exception e) {
			e.printStackTrace();
		}

		this.getNavigationPanel(3);
		this.businessUnitBean.setEnableAll(BusinessUnitAction.SET_ENABLE);
		return BusinessUnitAction.RETURN_TYPE_SUCCESS;
	}
	public String  f9EmpAction(){
		 String query = "SELECT EMP_TOKEN, EMP_FNAME ||'  '||EMP_MNAME||'  '||EMP_LNAME,"
			 			+ " EMP_ID EMP_STATUS FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S'";
		 String[] headers = {getMessage("business_empToken"),getMessage("business_empName") };
		 String[] headerWidth = { "30","70" };
		 String[] fieldNames = { "empToken","empName","empID" };
		 int[] columnIndex = { 0,1,2};
		 String submitFlag = "false";
		 String submitToMethod = "";
		 setF9Window(query, headers, headerWidth, fieldNames, columnIndex, "false", "");
		 return "f9page";
	}
	
	
	
}
