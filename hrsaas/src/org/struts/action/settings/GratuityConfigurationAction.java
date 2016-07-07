/**
 * 
 */
package org.struts.action.settings;

import org.paradyne.bean.settings.GratuityConfiguration;
import org.paradyne.model.extraWorkBenefits.ExtraWorkingBenefitsModel;
import org.paradyne.model.settings.GratuityConfigurationModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa0623
 *
 */
public class GratuityConfigurationAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(GratuityConfigurationAction.class);
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	GratuityConfiguration gratuityConfiguration = null;
	
	/**
	 * @return the gratuityConfiguration
	 */
	public GratuityConfiguration getGratuityConfiguration() {
		return gratuityConfiguration;
	}

	/**
	 * @param gratuityConfiguration the gratuityConfiguration to set
	 */
	public void setGratuityConfiguration(GratuityConfiguration gratuityConfiguration) {
		this.gratuityConfiguration = gratuityConfiguration;
	}

	@Override
	public void prepare_local() throws Exception {
		gratuityConfiguration = new GratuityConfiguration();
		gratuityConfiguration.setMenuCode(1006);

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return gratuityConfiguration;
	}
	
	@Override
	public String input() throws Exception {
		GratuityConfigurationModel model = new GratuityConfigurationModel();
		model.initiate(context, session);
		model.getHeaderRecords(gratuityConfiguration);
		model.getFiltersList(gratuityConfiguration, request);
		model.terminate();
		getNavigationPanel(1);
		return INPUT;
	}
	
	/*public String callFields() throws Exception {
		logger.info("Gratuity flag  ... "+gratuityConfiguration.getGratuityFlag());
		gratuityConfiguration.setFieldsFlag("true");
		getNavigationPanel(1);
		return SUCCESS;
	}*/
	
	/**
	 * To select any division
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9division() throws Exception {
		String query = " SELECT DIV_NAME,DIV_ID FROM HRMS_DIVISION";
		
		if(gratuityConfiguration.getUserProfileDivision() !=null && gratuityConfiguration.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+gratuityConfiguration.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";

		String[] headers = { getMessage("division") };
		String[] headerWidth = { "100" };
		String[] fieldNames = { "divisionName","divisionCode" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		gratuityConfiguration.setMasterMenuCode(42);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end f9division method

	/**
	 * To select any department
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9dept() throws Exception {
		String query = " SELECT DEPT_NAME,DEPT_ID FROM HRMS_DEPT "
				+ " ORDER BY  DEPT_ID ";

		String[] headers = { getMessage("department") };
		String[] headerWidth = { "100" };
		String[] fieldNames = { "deptName", "deptCode" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		gratuityConfiguration.setMasterMenuCode(23);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end f9dept method

	/**
	 * To select any branch
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9branch() throws Exception {
		String query = " SELECT CENTER_NAME,CENTER_ID FROM HRMS_CENTER "
				+ " ORDER BY  CENTER_ID ";

		String[] headers = { getMessage("branch") };
		String[] headerWidth = { "100" };
		String[] fieldNames = { "branchName","branchCode" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		gratuityConfiguration.setMasterMenuCode(31);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end f9branch method

	/**
	 * To select any designation
	 * 
	 * @return String(f9 page)
	 * @throws Exception
	 */
	public String f9designation() throws Exception {

		String query = " SELECT RANK_NAME, RANK_ID FROM HRMS_RANK "
				+ " ORDER BY  RANK_ID ";

		String[] headers = { getMessage("designation") };
		String[] headerWidth = { "100" };
		String[] fieldNames = { "desgName", "desgCode" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		gratuityConfiguration.setMasterMenuCode(26);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}// end f9designation method
	
	/**
	 * Method to select the Grade of an employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9gradeaction() throws Exception {

		String query = " SELECT DISTINCT TO_CHAR(CADRE_NAME),CADRE_ID FROM  HRMS_CADRE  "
				+ " ORDER BY CADRE_ID ";

		String[] headers = { getMessage("grade") };

		String[] headerWidth = { "100" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = "";
		String[] fieldNames = { "gradeName", "gradeCode" };
		gratuityConfiguration.setMasterMenuCode(19);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	/**
	 * Searching employee type
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9empType() throws Exception {
		String query = " SELECT  TYPE_ID,TYPE_NAME FROM HRMS_EMP_TYPE ORDER BY TYPE_ID ";

		String[] headers = { "Type Code", getMessage("employee.type") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "empTypeCode", "empTypeName" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";
		String submitToMethod = "";

		gratuityConfiguration.setMasterMenuCode(41);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}// end f9empType method
	
	public String addItem() throws Exception {
		GratuityConfigurationModel model = new GratuityConfigurationModel();
		model.initiate(context, session);
		String[] srNo = request.getParameterValues("srNo");
		try {
			String[] divCode = request.getParameterValues("divisionCodeItr");
			String[] divName = request.getParameterValues("divisionNameItr");
			String[] gradeCode = request.getParameterValues("gradeCodeItr");
			String[] gradeName = request.getParameterValues("gradeNameItr");
			String[] deptCode = request.getParameterValues("deptCodeItr");
			String[] deptName = request.getParameterValues("deptNameItr");
			String[] brnCode = request.getParameterValues("branchCodeItr");
			String[] brnName = request.getParameterValues("branchNameItr");
			String[] desgCode = request.getParameterValues("desgCodeItr");
			String[] desgName = request.getParameterValues("desgNameItr");
			String[] eTypeCode = request.getParameterValues("empTypeCodeItr");
			String[] eTypeName = request.getParameterValues("empTypeNameItr");
			if (srNo != null) {
				boolean result = model.checkDuplicate(gratuityConfiguration, srNo,
						divCode, divName, gradeCode, gradeName, deptCode,
						deptName, brnCode, brnName, desgCode, desgName,eTypeCode,eTypeName, 1);
				if (result) {
					addActionMessage("Selected filters have already been assigned for this benefit.");
					resetSettings();
					getNavigationPanel(1);
					return SUCCESS;
				}

			}
			if (gratuityConfiguration.getHiddenEdit().equals("")) {
				model.addItem(gratuityConfiguration, srNo,
						divCode, divName, gradeCode, gradeName, deptCode,
						deptName, brnCode, brnName, desgCode, desgName,eTypeCode,eTypeName, 1);
				resetSettings();
			} else {
				model.moditem(gratuityConfiguration, srNo,
						divCode, divName, gradeCode, gradeName, deptCode,
						deptName, brnCode, brnName, desgCode, desgName,eTypeCode,eTypeName, 1);
				resetSettings();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
		getNavigationPanel(1);
		gratuityConfiguration.setEnableAll("Y");
		return SUCCESS;
	}
	public String deleteRow(){
		getNavigationPanel(1);
		GratuityConfigurationModel model=new GratuityConfigurationModel();
		model.initiate(context, session);
		model.deleteItem(gratuityConfiguration,request);
		gratuityConfiguration.setHiddenEdit("");
		model.terminate();
		
		return SUCCESS;
	}
	public String resetSettings() {
		getNavigationPanel(1);
		gratuityConfiguration.setDivisionCode("");
		gratuityConfiguration.setDivisionName("");
		gratuityConfiguration.setGradeCode("");
		gratuityConfiguration.setGradeName("");
		gratuityConfiguration.setDeptCode("");
		gratuityConfiguration.setDeptName("");
		gratuityConfiguration.setDesgCode("");
		gratuityConfiguration.setDesgName("");
		gratuityConfiguration.setBranchCode("");
		gratuityConfiguration.setBranchName("");
		gratuityConfiguration.setEmpTypeCode("");
		gratuityConfiguration.setEmpTypeName("");
		gratuityConfiguration.setHiddenEdit("");
		// gratuityConfiguration.setBenefitSettingID("");
		return SUCCESS;
	}
	
	public String creditCombination() {
		gratuityConfiguration.setCreditCodeHidNext(gratuityConfiguration.getCreditCodeHid());
		gratuityConfiguration.setCreditAbbrHidNext(gratuityConfiguration.getCreditAbbrHid());
		GratuityConfigurationModel model = new GratuityConfigurationModel();
		model.initiate(context, session);
		model.creditCombination(gratuityConfiguration);
		model.terminate();
		return "creditList";
	}
	
	public String save() throws Exception {
		GratuityConfigurationModel model = new GratuityConfigurationModel();
		model.initiate(context, session);
		boolean result = false;
		String[] srNo = request.getParameterValues("srNo");
		String[] divCode = request.getParameterValues("divisionCodeItr");
		String[] eTypeCode = request.getParameterValues("empTypeCodeItr");
		String[] deptCode = request.getParameterValues("deptCodeItr");
		String[] brnCode = request.getParameterValues("branchCodeItr");
		String[] desgCode = request.getParameterValues("desgCodeItr");
		String[] gradeCode = request.getParameterValues("gradeCodeItr");
		boolean duplicate = model.checkSavedDuplicate(gratuityConfiguration, srNo,
				divCode, eTypeCode, deptCode, brnCode, desgCode, gradeCode);
		//if (!duplicate) {
		result = model.saveSettings(gratuityConfiguration);
				if (result) {
					String check = model.saveFilterDtl(gratuityConfiguration, request);
					if (check == "setting saved") {
						addActionMessage(getMessage("save"));
						input();
						getNavigationPanel(1);
					}else{
						addActionMessage(getMessage("save.error"));
					}

				} else {
					addActionMessage(getMessage("save.error"));
					getNavigationPanel(1);
				}
		/*}else{
			logger.info("duplicate");
			addActionMessage(getMessage("duplicate"));
			reset();
			getNavigationPanel(2);
		}*/
		gratuityConfiguration.setEnableAll("Y");
		model.terminate();
		return SUCCESS;
	}

}
