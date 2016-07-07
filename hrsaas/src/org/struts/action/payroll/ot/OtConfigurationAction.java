package org.struts.action.payroll.ot;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.paradyne.bean.payroll.ot.OtConfiguration;
import org.paradyne.lib.Utility;
import org.paradyne.model.admin.master.LWFConfigurationMasterModel;
import org.paradyne.model.payroll.bonus.BonusAllowanceModel;
import org.paradyne.model.payroll.incometax.TaxSlabModel;
import org.paradyne.model.payroll.ot.OtConfigurationModel;
import org.struts.lib.ParaActionSupport;

/** Created on 21th Feb 2012.
 * @author aa1385
 *
 */
public class OtConfigurationAction extends ParaActionSupport {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);
	
	/** formatter. */
	NumberFormat formatter = new DecimalFormat("#0.00");
	
	/** bonusBean. */
	private OtConfiguration bean; 
	
	/** falseValueConst. * */
	private final String falseValueConst = "false";
	
	/** f9PageConst. * */
	private final String f9PageConst = "f9page";
	
	/** divisionConst. * */
	private final String divisionConst = "division";
	/** trueValueConst. * */
	private final String trueValueConst = "true";
	
	
	/** hundreadConst. * */
	private final String hundreadConst = "100";
	
	/** f9MultiSelectConst. * */
	private final String f9MultiSelectConst = "f9multiSelect";
	
	/** returnSuccessConst. * */
	private final String returnSuccessConst = "success";
	

	/** Method : prepare_local.
	 * Purpose : This method is used to set menuCode 
	 * @throws Exception : Exception
	 * */
	public void prepare_local() throws Exception {
		this.bean = new OtConfiguration();
		this.bean.setMenuCode(2297);
	}
	
	/** Method : getModel.
	 * Purpose : This method is used to return bonusBean 
	 * @return Object : bonusBean
	 * */
	public Object getModel() {
		return this.bean;
	}
	
	/** Method : getBonusBean.
	 * Purpose : This method is used to get bonusBean 
	 * @return BonusAllowance
	 * */
	public OtConfiguration getbean() {
		return this.bean;
	}

	/** Method : setBonusBean.
	 * Purpose : This method is used to set bonusBean 
	 * @param bonusBean : bonusBean
	 * */
	public void setbean(final OtConfiguration bean) {
		this.bean = bean;
	}
	
	/** Method : prepare_withLoginProfileDetails.
	 * Purpose : This method is used to set data path 
	 * */
	public void prepare_withLoginProfileDetails() {
		
	}
	
	/** Method : input.
	 * Purpose : this method is used to get initial list page
	 * @return String
	 */
	public String input() {
		try {
			final OtConfigurationModel model = new OtConfigurationModel();
			model.initiate(context, session);
			model.showConfigList(bean,request);
			model.terminate();
			this.getNavigationPanel(1);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "input";
	}
	public String addNew() {
		try {
			
			getNavigationPanel(2);
			return this.returnSuccessConst;
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	
	/**Method : f9Div.
	 *Purpose : Popup window contains list of all division
	 * @return String f9page
	 */
	public String f9Division() {
		try {
			String query = " SELECT HRMS_DIVISION.DIV_NAME, HRMS_DIVISION.DIV_ID FROM HRMS_DIVISION ";
			if (this.bean.getUserProfileDivision() != null && this.bean.getUserProfileDivision().length() > 0) {
				query += " WHERE HRMS_DIVISION.DIV_ID IN(" + this.bean.getUserProfileDivision() + ") ";
			}
			
			if(bean.getOtConfigID().equals("")) {
				query += " WHERE HRMS_DIVISION.DIV_ID NOT IN (SELECT NVL(HRMS_OT_CONF.DIV_CODE,0) FROM HRMS_OT_CONF)";
			}
			
			query += " ORDER BY UPPER(HRMS_DIVISION.DIV_NAME) ";

			final String[] headers = {this.getMessage(this.divisionConst)};
			final String[] headerWidth = {this.hundreadConst};
			final String[] fieldNames = {"divisionName", "divisionID"};
			final int[] columnIndex = {0, 1};
			final String submitFlag = this.falseValueConst;
			final String submitToMethod = "";
			this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.f9PageConst;
	}
	
	
	/**Method : reset.
	 * Purpose : This method is used to reset all the form fields 
	 * @return String 
	 */
	public String reset() {
		try {
			this.bean.setDivisionID("");
			this.bean.setDivisionName("");
			this.bean.setActualHoursWorkedShiftHoursFlag("");
			this.bean.setActualOutTimeShiftOutTimeFlag("");
			this.bean.setOtHoursRoundOffOptions("");
			this.bean.setCalOtHourlyRateFormula("");
			this.bean.setCalWeeklyOtHourlyRateFormula("");
			this.bean.setCalDoubleOtHourlyRateFormula("");
			this.bean.setOtConfigID("");
			this.getNavigationPanel(2);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.returnSuccessConst;
	}

	/**
	 * method name : saveDailyAttendanceDetails purpose : to save and update the daily attendance settings return type : String
	 * parameter : none
	 */
	public String saveConfiguration() {
		boolean result = false;
		
		OtConfigurationModel model = new OtConfigurationModel();
		model.initiate(context, session);

		
		
		if ("".equals(this.bean.getOtConfigID())) {
			result = model.saveConfigurationSettings(bean, request);
			if (result) {
				
				this.addActionMessage("OT Configuration save successfully.");
			} else {
				this.addActionMessage("Error occured");
				this.reset();
			}
		} else {
			result = model.updateConfigurationSettings(bean, request);
			if (result) {
				
				this.addActionMessage("OT Configuration update successfully.");
			} else {
				this.addActionMessage("Error occured");
				this.reset();
			}
		}
		
		getNavigationPanel(3);
		bean.setEnableAll("N");
		model.terminate();
		return "success";
	}
	/**
	 * To edit any record in the list by double clicking on it
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		
		String requestID = request.getParameter("otConfigId");
		
		OtConfigurationModel model = new OtConfigurationModel();
		model.initiate(context, session);
						
		model.editConfigSetting(bean, request,requestID);
		model.terminate();

		getNavigationPanel(3);
		bean.setEnableAll("N");
		return SUCCESS;
	}
	
	/**Method : searchProcessedRecords.
	 *Purpose : Popup window contains list of all division
	 * @return String f9page
	 */
	public String f9action() {
		try {
			final String query = " SELECT DIV_NAME,OT_CONF_CODE FROM HRMS_OT_CONF " +
									" INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_OT_CONF.DIV_CODE) " +
									" ORDER BY OT_CONF_CODE DESC ";

			final String[] headers = {this.getMessage("division")};
			final String[] headerWidth = {this.hundreadConst};
			final String[] fieldNames = {"divisionNameItr",  "hiddenOtConfigId" };
			final int[] columnIndex = {0, 1};
			final String submitFlag = this.trueValueConst;
			final String submitToMethod = "OtConfiguration_setConfigDetails.action";
			this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.f9PageConst;
	}
	
	/**Method : setBonusDetails.
	 * Purpose : This method is used to view record details
	 * @return String
	 */
	public String setConfigDetails() {
		
		try {
			
			
			String requestID = request.getParameter("hiddenOtConfigId");
			
			final OtConfigurationModel model = new OtConfigurationModel();
			model.initiate(context, session);
							
			model.editConfigSetting(bean, request,requestID);
			model.terminate();

			getNavigationPanel(3);
			bean.setEnableAll("N");
			
		} catch (final Exception e) {
			e.printStackTrace();
		}
		
		
		return this.returnSuccessConst;
	}
	
	/**
	 * To delete one or more records from the list
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");
		System.out.println("code[]====" + code);
		final OtConfigurationModel model = new OtConfigurationModel();

		model.initiate(context, session);
		String result = model.deletecheckedHDRRecords(bean, code);
		model.showConfigList(bean,request);
		model.terminate();
		if (String.valueOf(result).equals("true")) {
			addActionMessage(getMessage("delete"));
		}// end of if
		else {
			addActionMessage(getMessage("multiple.del.error"));
		}// end of else
		//reset();
		
		getNavigationPanel(1);
		
		this.input();
		return INPUT;

	}
	
	public String deleteSelectedRecords() throws Exception {

		logger.info("################## IN MULTIPLE DELETE ##############");
		boolean result = false;
		final OtConfigurationModel model = new OtConfigurationModel();
		model.initiate(context, session);
		String requestID = request.getParameter("otConfigId");
		
		result = model.deleteSelectedRecords(bean);
		if (result)
			addActionMessage(getText("delMessage", ""));
		else
			//addActionMessage(getMessage("del.error"));
			addActionMessage(getText("delMessage", ""));
		model.terminate();
		getNavigationPanel(1);
		return input();
	}
}
