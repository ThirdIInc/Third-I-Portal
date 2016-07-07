package org.struts.action.common;

import org.paradyne.bean.common.ProfileAccess;
import org.paradyne.model.admin.master.BankModel;
import org.paradyne.model.common.ProfileAccessModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author reebaj
 * @since 02/06/2008
 * @modified 4 Nov 2011
 */

public class ProfileAccessAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	ProfileAccess profileAccess;

	public ProfileAccess getProfileAccess() {
		return profileAccess;
	}

	public void setProfileAccess(ProfileAccess profileAccess) {
		this.profileAccess = profileAccess;
	}

	public ProfileAccessAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		profileAccess = new ProfileAccess();
		profileAccess.setMenuCode(614);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return profileAccess;
	}

	/**
	 * FLAG SETTING ON "CREATE PROFILE"
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String createProfile() throws Exception {
		profileAccess.setCheck("CR");
		profileAccess.setCreateFlag(true);
		/* For setting checkboxes after clicking on back button */
		if (profileAccess.getHiddenDivChk().equals("divOn")) {
			profileAccess.setDivBox("true");
		}// end of if
		if (profileAccess.getHiddenDptChk().equals("dptOn")) {
			profileAccess.setDptBox("true");
		}// end of if
		if (profileAccess.getHiddenCntChk().equals("cntOn")) {
			profileAccess.setCntBox("true");
		}// end of if
		if (profileAccess.getHiddenChk().equals("empOn")) {
			profileAccess.setEmpBox("true");
		}// end of if
		logger.info("Create flag=====true");
		return SUCCESS;
	}

	/**
	 * FLAG SETTING ON "UPDATE PROFILE"
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String updateProfile() throws Exception {
		profileAccess.setCheck("U");

		profileAccess.setCreateFlag(true);
		profileAccess.setUpdFlag(true);
		/* For setting checkboxes after clicking on back button */
		if (profileAccess.getHiddenDivChk().equals("divOn")) {
			profileAccess.setDivBox("true");
		}// end of if
		if (profileAccess.getHiddenDptChk().equals("dptOn")) {
			profileAccess.setDptBox("true");
		}// end of if
		if (profileAccess.getHiddenCntChk().equals("cntOn")) {
			profileAccess.setCntBox("true");
		}// end of if
		if (profileAccess.getHiddenChk().equals("empOn")) {
			profileAccess.setEmpBox("true");
		}// end of if

		logger.info("chkflag----=====-----------"
				+ profileAccess.getHiddenDivChk());
		logger.info("Update flag =true");
		return SUCCESS;
	}

	/**
	 * FLAG SETTING ON "GO" ACTION
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String callGoAction() throws Exception {
		ProfileAccessModel model = new ProfileAccessModel();
		model.initiate(context, session);
		profileAccess.setGoFlag(true);
		profileAccess.setSaveFlag(true);
		logger.info("getcheck---------------" + profileAccess.getCheck());
		if (String.valueOf(profileAccess.getCheck()).equals("U")) {
			profileAccess.setChk(true);
		}// end of if
		model.addProfile(profileAccess);
		model.terminate();
		return createNew();
	}

	/**
	 * For method to set value in Check Box
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String createNew() throws Exception {

		try {
			String tab = request.getParameter("tab");
			logger.info("tab-------createNew-------" + tab);
			ProfileAccessModel model = new ProfileAccessModel();
			model.initiate(context, session);
			setCheckBox(model);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception caught in Create new ACTION");
		}
		return "success";
	}

	/**
	 * Method for setting value in Check Box
	 * 
	 * @param model
	 */

	private void setCheckBox(ProfileAccessModel model) {
		String tab = request.getParameter("tab");
		logger.info("tab-------setCheckBox--------" + tab);
		logger.info("hidden value------CheckBox--------"
				+ profileAccess.getHiddenChk());
		profileAccess.setHiddenChk(profileAccess.getHiddenChk());
		profileAccess.setGoFlag(true);
		if (String.valueOf(profileAccess.getCheck()).equals("U")) {
			profileAccess.setChk(true);
		}// end of if

		String[][] centList = null;
		String[][] deptList = null;
		String[][] divList = null;
		String[][] empTypeList = null;

		profileAccess.setForCenter(false);
		profileAccess.setForDept(false);
		profileAccess.setForDiv(false);
		profileAccess.setForEmpType(false);

		Object[][] checkFlags = model.getFlags(profileAccess);

		/* GENERATION OF CENTER LIST */
		if (tab.equals("Cnt")) {
			if (String.valueOf(checkFlags[0][2]).equals("Y")) {
				profileAccess.setSaveFlag(true);
				// If all cnt flags selected
				centList = model.getCenterList(profileAccess, "A");
			} // end of nested if
			else {
				centList = model.getCenterList(profileAccess, "B");
				profileAccess.setSaveFlag(false);
			}// end of else
			profileAccess.setForCenter(true);
			request.setAttribute("centerChk", centList);
		}// end of if

		/* GENERATION OF DEPARTMENT LIST */
		if (tab.equals("Dpt")) {
			if (String.valueOf(checkFlags[0][1]).equals("Y")) {
				profileAccess.setSaveFlag(true);
				// If all dpt flags selected
				deptList = model.generateDeptList(profileAccess, "A");
			}// end of nested if
			else {
				deptList = model.generateDeptList(profileAccess, "B");
				profileAccess.setSaveFlag(false);
			}// end of else
			profileAccess.setForDept(true);
			request.setAttribute("deptChk", deptList);
		}// end of if

		/* GENERATION OF DIVISION LIST */
		if (tab.equals("Div")) {
			if (String.valueOf(checkFlags[0][0]).equals("Y")) {
				profileAccess.setSaveFlag(true);
				// If all div flags selected
				divList = model.generateDivList(profileAccess, "A");

			} // end of nested if
			else {
				divList = model.generateDivList(profileAccess, "B");
				profileAccess.setSaveFlag(false);
			}// end of else
			profileAccess.setForDiv(true);
			request.setAttribute("divisionChk", divList);
		}// end of if

		/* GENERATION OF EMPLOYEE TYPE LIST */
		if (tab.equals("Emp")) {
			if (String.valueOf(checkFlags[0][3]).equals("Y")) {
				profileAccess.setSaveFlag(true);
				// If all emptype flags selected
				empTypeList = model.generateEmpTypeList(profileAccess, "A");
			} // end of nested if
			else {
				empTypeList = model.generateEmpTypeList(profileAccess, "B");
				profileAccess.setSaveFlag(false);
			}// end of else
			profileAccess.setForEmpType(true);
			request.setAttribute("empTypeChk", empTypeList);
		}// end of if
		profileAccess.setGoFlag(true);
	}

	/**
	 * Method for create new or Update Profile
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String create() throws Exception {
		try {
			logger.info("*********Create********************");

			ProfileAccessModel model = new ProfileAccessModel();
			if (profileAccess.getProfile().equals("")
					|| profileAccess.getProfile() == null) {
				addActionMessage("Enter Profile Name");
				return createNew();
			}// end of if
			model.initiate(context, session);
			String cntFlag[] = null;
			String deptFlag[] = null;
			String divisionFlag[] = null;
			String emptypeFlag[] = null;
			try {
				/* ***** CREATE PROFILE FOR CENTER ***** */
				if (profileAccess.isForCenter()) {

					cntFlag = (String[]) request
							.getParameterValues("centerFlag");
					model.createCntProfile(profileAccess, cntFlag);
				}// end of if
				else if (profileAccess.isForDept()) {
					deptFlag = (String[]) request
							.getParameterValues("deptFlag");
					model.createDeptProfile(profileAccess, deptFlag);
				}// end of else if
				else if (profileAccess.isForDiv()) {
					divisionFlag = (String[]) request
							.getParameterValues("divisionFlag");
					model.createDivProfile(profileAccess, divisionFlag);
				}// end of else if
				else if (profileAccess.isForEmpType()) {
					emptypeFlag = (String[]) request
							.getParameterValues("emptypeFlag");
					model.createEmpProfile(profileAccess, emptypeFlag);
				}// end of else if

			} catch (Exception e) {
				logger.error("Exception CAUGHT in Create ACTION----1" + e);
			}

			model.terminate();
			if (cntFlag == null && deptFlag == null && divisionFlag == null
					&& emptypeFlag == null) {
				addActionMessage("Choose any of the Menus for Saving Profile");
				return createNew();
			}// end of if

			addActionMessage("Profile saved successfully");

		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("Exception caught in create ACTION-----2");
		}
		return createNew();
	}

	/** SEARCH PROFILE FOR UPDATE * */
	public String f9action() throws Exception {
		try {
			logger.info("*********F9Window********************");
			String query = "SELECT PROFILE_NAME ,PROFILE_CODE FROM HRMS_PROFILE_ACC_HDR ORDER BY PROFILE_CODE  DESC";
			String[] headers = { "Profile Name" };
			String[] headerWidth = { "100" };
			String[] fieldNames = {	"profile", "profileId" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "true";

			String submitToMethod = "AccessProfile_getAccessProfileRecord.action";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	/**
	 * SETTING ALL FLAGS STATUS WHILE UPDATION (ON F9 ACTION)
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getFlagStatus() throws Exception {

		try {
			ProfileAccessModel model = new ProfileAccessModel();
			model.initiate(context, session);
			Object[][] data = model.getFlags(profileAccess);
			/* ***SETTING CHECKBOX*** */
			if (String.valueOf(data[0][0]).equals("Y")) {
				profileAccess.setDivBox("true");
			}// end of if
			if (String.valueOf(data[0][1]).equals("Y")) {
				profileAccess.setDptBox("true");
			}// end of if
			if (String.valueOf(data[0][2]).equals("Y")) {
				profileAccess.setCntBox("true");
			}// end of if
			if (String.valueOf(data[0][3]).equals("Y")) {
				profileAccess.setEmpBox("true");
			}// end of if
			model.terminate();
		} catch (Exception e) {
		}
		profileAccess.setCheck("U");
		profileAccess.setCreateFlag(true);
		profileAccess.setUpdFlag(true);
		return SUCCESS;
	}

	/** ************* REPORT GENERATION ************* * */
	public String report_old() throws Exception {

		try {
			ProfileAccessModel model = new ProfileAccessModel();
			model.initiate(context, session);

			model.getProfileCenters(profileAccess);
			model.getProfileDeparts(profileAccess);
			model.getProfileDivisions(profileAccess);
			model.getProfileEmpTypes(profileAccess);
			model.terminate();
		} catch (Exception e) {
		}
		return "report";
	}
	//Add By Ganesh 4 Nov 2011
	
	/**
     * Method to add new application.
     * 
     * @return SUCCESS
     */
	public String addNew() { 		
		try {
			profileAccess.setHiddenMapCheckBox("Y");
			profileAccess.setProfileId("");
		} catch (final Exception e) {
			e.printStackTrace();
		}
		//this.getNavigationPanel(3);
		return SUCCESS;
	}
	
	public String f9Div() {
		try {
			//QUERY UPDATED BY PRAJAKTA B (18 JULY 2013)
			String query = " SELECT DISTINCT DIV_ID, DIV_NAME "
					+ " FROM HRMS_DIVISION WHERE IS_ACTIVE='Y'";

			if (profileAccess.getUserProfileDivision() != null
					&& profileAccess.getUserProfileDivision().length() > 0)
				query += " AND DIV_ID IN (" + profileAccess.getUserProfileDivision()
						+ ") " ;
			query += " ORDER BY  DIV_ID ";

			String header = getMessage("division");
			String textAreaId = "paraFrm_sDivName";

			String hiddenFieldId = "paraFrm_sDivCode";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";

		} catch (Exception e) {
			logger
					.error("Error in LWFConfigurationMasterAction.f9Div() method Action : "
							+ e.getMessage());
			return "";
		}
	}

	public String f9Brch() {
		try {
			//QUERY UPDATED BY PRAJAKTA B (18 JULY 2013)
			String query = " SELECT DISTINCT CENTER_ID, CENTER_NAME FROM HRMS_CENTER "
							+ "WHERE IS_ACTIVE='Y' ORDER BY UPPER (CENTER_NAME) ";

			String header = getMessage("branch");
			String textAreaId = "paraFrm_sBranchName";

			String hiddenFieldId = "paraFrm_sBranch";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";
		} catch (Exception e) {
			logger
					.error("Error in LWFConfigurationMasterAction.f9Brch() method Action : "
							+ e.getMessage());
			return "";
		}
	}
	
	String query = " SELECT PAYBILL_ID,PAYBILL_GROUP FROM HRMS_PAYBILL "
		+ " ORDER BY  PAYBILL_ID ";
	
	
	public String f9Paybill() {
		try {
			String query = " SELECT " + " 	DISTINCT PAYBILL_ID ,"
					+ "		PAYBILL_GROUP " + " FROM " + " 	HRMS_PAYBILL "
					+ " ORDER BY " + "		UPPER (PAYBILL_GROUP) ";

			String header = getMessage("paybill");
			String textAreaId = "paraFrm_sPaybillName";

			String hiddenFieldId = "paraFrm_sPaybill";

			String submitFlag = "";
			String submitToMethod = "";

			setMultiSelectF9(query, header, textAreaId, hiddenFieldId,
					submitFlag, submitToMethod);
			return "f9multiSelect";
		} catch (Exception e) {
			logger
					.error("Error in LWFConfigurationMasterAction.f9Paybill() method Action : "
							+ e.getMessage());
			return "";
		}
	}
	
	/**
     * Method to go list page .
     * 
     * @return INPUT
     * * @throws Exception - this.input() throws Exception
     */
	public String back() throws Exception {
		profileAccess.setProfileId("");
		reset();
		this.input();
		return INPUT;
	}
	
	@Override
	public String input() throws Exception {
		ProfileAccessModel model = new ProfileAccessModel();
		model.initiate(context, session);
		
		model.getAccessProfileList(this.profileAccess , request);	
		
		model.terminate();
		return INPUT;
	}
	
	/**
	 * To edit any record in the list by double clicking on it
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		
		
		
		ProfileAccessModel model = new ProfileAccessModel();
		model.initiate(context, session);
		
		profileAccess.setDeleteButFlag(true);
		model.calforedit(profileAccess, request);
		model.terminate();

		//getNavigationPanel(2);
		//profileAccess.setEnableAll("Y");
		return SUCCESS;
	}
	
	/**
	 * Method to save application.
	 * 
	 * @return SUCCESS *
	 * @throws Exception -
	 *             this.input() throws Exception
	 */
	public String saveFunction() throws Exception {
		try {
			ProfileAccessModel model = new ProfileAccessModel();
			model.initiate(context, session);
			
						
			final boolean result;
			if ("".equals(this.profileAccess.getProfileId())) {
				result = model.saveFunction(this.profileAccess, request);
				if (result) {
					this.addActionMessage("Access Profile save successfully.");
					profileAccess.setProfileId("");
				} else {
					this.addActionMessage("Access Profile Name already Exist. Please Enter new Access Profile Name.");
					
				}
			} else {
				result = model.updateRecords(this.profileAccess, request);
				if (result) {
					this.addActionMessage("Access Profile modified successfully.");
					profileAccess.setProfileId("");
				} else {
					this.addActionMessage("Error occured");
					//this.reset();
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		reset();
		this.input();
		return INPUT;
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
		ProfileAccessModel model = new ProfileAccessModel();

		model.initiate(context, session);
		String result = model.deletecheckedRecords(profileAccess, code);

		if (String.valueOf(result).equals("true")) {
			addActionMessage(getMessage("delete"));
		}// end of if
		else {
			addActionMessage(getMessage("multiple.del.error"));
		}// end of else
		//reset();
		model.getAccessProfileList(this.profileAccess , request);	
		//getNavigationPanel(1);
		model.terminate();
		reset();
		return INPUT;
		// return reset();

	}
	
	/**
	 * To delete selected  record
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		ProfileAccessModel model = new ProfileAccessModel();
		model.initiate(context, session);
		boolean result = model.deleteSelAccessProfile(profileAccess);

		if (result) {
			addActionMessage(getMessage("delete"));


		}//end of if
		else {
			addActionMessage(getMessage("del.error"));
		}//end of else
		//reset();
		
		model.getAccessProfileList(this.profileAccess , request);	
		model.terminate();
		reset();
		return INPUT;

	}
	
	
	
	/**
	 * To set the fields after search
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getAccessProfileRecord() {
		ProfileAccessModel model = new ProfileAccessModel();
		model.initiate(context, session);
		model.getAcessProfileRecord(profileAccess, request);
		//getNavigationPanel(2);
		profileAccess.setDeleteButFlag(true);
		profileAccess.setEnableAll("Y");
		model.terminate();
		return "success";

	}
	/**
	 * To generate report
	 * 
	 * @return null
	 * @throws Exception
	 */
	public String report() throws Exception {
		ProfileAccessModel model = new ProfileAccessModel();
		model.initiate(context, session);
		/*	model.getBankReport(bankMaster);
			model.terminate();
			return "report";*/
		model.getReport(profileAccess, request, response, context);
		model.terminate();
		return null;

	}
	
	/**
	 * To reset the fields
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String reset() {
		try {
			profileAccess.setProfile("");
			profileAccess.setDivisionFlag("");
		//	profileAccess.setBankName("");
			profileAccess.setSDivCode("");
			profileAccess.setSDivName("");
			profileAccess.setBranchFlag("");
			profileAccess.setProfile("");
			profileAccess.setSBranch("");
			profileAccess.setSBranchName("");
			profileAccess.setPaybillFlag("");
			profileAccess.setSPaybill("");
			profileAccess.setSPaybillName("");
			profileAccess.setHiddenMapCheckBox("Y");
			profileAccess.setDeleteButFlag(false);
		
			//profileAccess.setInsertBranchFlag(true);
		//	profileAccess.setUpdateFlag(false);
			
			if(!profileAccess.getProfileId().equals(""))
			{
			
				profileAccess.setDeleteButFlag(true);
				
			}
         
            profileAccess.setEnableAll("Y");
           
		} catch (Exception e) {
			
			logger.error("Error in reset"+e);

		}
		return "success";

	}
}
