package org.struts.action.D1;

import java.util.List;
import org.paradyne.bean.D1.ApprovalSettings;
import org.paradyne.model.D1.ApprovalSettingsModel;
import org.paradyne.model.D1.BusinessUnitModel;
import org.paradyne.model.D1.ClassEnrollmentFormModel;
import org.paradyne.model.D1.ClassRequestModel;
import org.paradyne.model.D1.RegionMasterModel;
import org.paradyne.model.admin.srd.AddressDetailsModel;
import org.struts.lib.ParaActionSupport;

/**
 * Bhushan Dasare Feb 14, 2011
 */
/*Nilesh D*/
public class ApprovalSettingsAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ApprovalSettingsAction.class);

	ApprovalSettings bean;

	public String addAdminApprover() {
		try {
			String searchAdminEmpId = bean.getSearchAdminEmpId();
			//String divisionId = bean.getDivId();
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			String mail = bean.getEmailId();
			String message = model.addApprover(searchAdminEmpId, "A", mail);
			addActionMessage(message);
			showAdminApproverList();
			bean.setSearchAdminEmpId("");
			bean.setSearchAdminEmpToken("");
			bean.setSearchAdminEmpName("");
			bean.setListType("Admin");
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	// added ganesh start

	public String addFinanceApprover() {
		try {
			String searchFinanceEmpId = bean.getSearchFinanceEmpId();
			//String divisionId = bean.getDivId();

			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			String mail = bean.getEmailId();
			String message = model.addApprover(searchFinanceEmpId, "F", mail);
			addActionMessage(message);
			showFinanceApproverList();
			bean.setSearchFinanceEmpId("");
			bean.setSearchFinanceEmpName("");
			bean.setSearchFinanceEmpToken("");
			bean.setListType("Finance");
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	//Added by Nilesh today

	public String addFinanceATRApprover() {
		try {

			String searchFinanceATREmpId = bean.getSearchFinanceATREmpId();
			//String divisionId = bean.getDivId();

			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			String mail = bean.getEmailId();
			String message = model
					.addApprover(searchFinanceATREmpId, "R", mail);
			addActionMessage(message);
			showFinanceATRApproverList();
			bean.setSearchFinanceATREmpId("");
			bean.setSearchATREmpName("");
			bean.setSearchATREmpToken("");
			bean.setListType("FinanceATR");
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	public String addCorporateApprover() {
		try {
			//searchCorporateEmpId1  searchFinanceATREmpId
			String searchCorporateEmpId1 = bean.getSearchCorporateEmpId1();
			//String divisionId = bean.getDivId();

			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			String mail = bean.getEmailId();
			String message = model
					.addApprover(searchCorporateEmpId1, "C", mail);
			addActionMessage(message);
			showCorporateProcList();
			bean.setSearchCorporateEmpId1("");
			bean.setSearchCorporateEmpName1("");
			bean.setSearchCorporateToken1("");
			bean.setListType("Corporate");
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	public String addPayrollApprover() {
		try {
			String searchPayrollEmpId = bean.getSearchPayrollEmpId();
			//String divisionId = bean.getDivId();

			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			String mail = bean.getEmailId();
			String message = model.addApprover(searchPayrollEmpId, "P", mail);
			addActionMessage(message);
			showPayrollApproverList();
			bean.setSearchPayrollEmpId("");
			bean.setSearchPayrollEmpName("");
			bean.setSearchPayrollEmpToken("");
			bean.setListType("Payroll");
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	public String addLogisticsApprover() {
		try {
			String searchLogisticsEmpId = bean.getSearchLogisticsEmpId();
			//String divisionId = bean.getDivId();

			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			String mail = bean.getEmailId();
			String message = model.addApprover(searchLogisticsEmpId, "L", mail);
			addActionMessage(message);
			showLogisticsApproverList();
			bean.setSearchLogisticsEmpId("");
			bean.setSearchLogisticsEmpName("");
			bean.setSearchLogisticsEmpToken("");
			bean.setListType("Logistics");
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	public String addItApprover() {
		try {
			String searchItEmpId = bean.getSearchItEmpId();
			//String divisionId = bean.getDivId();

			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			String mail = bean.getEmailId();
			String message = model.addApprover(searchItEmpId, "I", mail);
			addActionMessage(message);
			showItApproverList();
			bean.setSearchItEmpId("");
			bean.setSearchItEmpName("");
			bean.setSearchItEmpToken("");
			bean.setListType("IT");
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	public String addTrainingAuth() {
		try {
			String searchTrainingAuthId = bean.getSearchTrainingAuthId();
			//String divisionId = bean.getDivId();

			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			String mail = bean.getEmailId();
			System.out
					.println("emil address here###################################");

			String message = model.addApprover(searchTrainingAuthId, "T", mail);
			addActionMessage(message);
			showTrainingAuthApproverList();
			bean.setSearchTrainingAuthId("");
			bean.setSearchTrainingAuthToken("");
			bean.setSearchTrainingAuthName("");
			bean.setListType("Training Authority");
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	public String addEducationMinistry() {
		try {
			String searchEducationMinistryId = bean
					.getSearchEducationMinistryId();
			//String divisionId = bean.getDivId();
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			String mail = bean.getEmailId();
			String message = model.addApprover(searchEducationMinistryId, "E",
					mail);
			addActionMessage(message);
			showEducationMinistryApproverList();
			bean.setSearchEducationMinistryId("");
			bean.setSearchEducationMinistryName("");
			bean.setSearchEducationMinistryToken("");
			bean.setListType("Education Ministry");
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	//added by Nilesh D
	public String addWorldTavel() {
		try {
			String configureType = request
			.getParameter("configureGroupType");
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			boolean result;
			if (bean.getWorldId().equals("")) {
				result = model.addEmail(bean);

				if (result) {

					//addActionMessage(getMessage("save"));
					addActionMessage("Email Id added in World Travel Group successfully.");

				} else {
					addActionMessage(getMessage("duplicate"));
					bean.setEmailId("");
				}
			}
			if (configureType.equals("W")) 
			{
				bean.setWorldTravelFlag(true);
				bean.setConfigureOptionTypeFlag(false);
				List worldTravelList = model.showWorldTravelList(bean, request);
			}else 
			{
				bean.setWorldTravelFlag(false);
				bean.setConfigureOptionTypeFlag(true);
				List worldTravelList = model.showConfigEmailList(bean,configureType, request);
				List trainingAuthList = model.configureOptionType(bean,configureType, request);
			}
			bean.setWorldId("");
			bean.setEmailId("");
			showWorldTravelList();
			bean.setListType("WorldTravel");
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	// added ganesh end
	
	public String addConfigEmail()throws Exception {
		try {
			String configureType = request
			.getParameter("configureGroupType");
			
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			boolean result;
			if (bean.getConfigHiddenEmailId().equals("")) {
				result = model.addConfigEmail(bean);
				System.out.println("bean.getConfigEmailId()====" + bean.getConfigEmailId());
				if (result) {

					//addActionMessage(getMessage("save"));
					
					String type = "";

					if (bean.getConfigureGroupType().equals("H")) {
						type = "HR";
						addActionMessage("Group Email Id added in HR Group successfully.");
					} else if (bean.getConfigureGroupType().equals("A")) {
						type = "Admin";
						addActionMessage("Group Email Id added in Admin Group successfully.");
					} else if (bean.getConfigureGroupType().equals("F")) {
						type = "Finance";
						addActionMessage("Group Email Id added in Finance Group successfully.");
					} 
					else if (bean.getConfigureGroupType().equals("R")) {
						type = "FinanceATR";
						addActionMessage("Group Email Id added in FinanceATR Group successfully.");
					}
					else if (bean.getConfigureGroupType().equals("P")) {
						type = "Payroll";
						addActionMessage("Group Email Id added in Payroll Group successfully.");
					} else if (bean.getConfigureGroupType().equals("L")) {
						type = "Logistics";
						addActionMessage("Group Email Id added in Corporate Procurement Group-NONINV successfully.");
					}
					 else if (bean.getConfigureGroupType().equals("C")) {
							type = "Corporate";
							addActionMessage("Group Email Id added in Corporate Procurement Group-HS successfully.");
						}
					else if (bean.getConfigureGroupType().equals("I")) {
						type = "IT Group(CD Rom)";
						addActionMessage("Group Email Id added in IT Group(CD Rom) successfully.");
					} 
					else if (bean.getConfigureGroupType().equals("K")) {
						type = "IT Group(App Security)";
						addActionMessage("Group Email Id added in IT Group(App Security) successfully.");
					} 
					else if (bean.getConfigureGroupType().equals("M")) {
						type = "IT Group(Report Request)";
						addActionMessage("Group Email Id added in IT Group(Report Request) successfully.");
					} 
					else if (bean.getConfigureGroupType().equals("N")) {
						type = "IT Group(Information Security)";
						addActionMessage("Group Email Id added in IT Group(System Change) successfully.");
					} 
					else if (bean.getConfigureGroupType().equals("T")) {
						type = "Training Authority";
						addActionMessage("Group Email Id added in Training Authority Group successfully.");
					} else if (bean.getConfigureGroupType().equals("E")) {
						type = "Education Ministry";
						addActionMessage("Group Email Id added in Education Ministry Group successfully.");
					}
					else if (bean.getConfigureGroupType().equals("S")) {
						type = "Education Ministry";
						addActionMessage("Group Email Id added in CEAR Approval Group successfully.");
					}


				} else {
					addActionMessage(getMessage("duplicate"));
					//bean.setEmailId("");
				}
			}else 
			{
				result = model.updateEmailRecords(bean,request);
				if (result) 
				{
					//addActionMessage("Group Email Id modified successfully.");
					
					String type = "";

					if (bean.getConfigureGroupType().equals("H")) {
						type = "HR";
						addActionMessage("Group Email Id modified in HR Group successfully.");
					} else if (bean.getConfigureGroupType().equals("A")) {
						type = "Admin";
						addActionMessage("Group Email Id modified in Admin Group successfully.");
					} else if (bean.getConfigureGroupType().equals("F")) {
						type = "Finance";
						addActionMessage("Group Email Id modified in Finance Group successfully.");
					} 
					else if (bean.getConfigureGroupType().equals("R")) {
						type = "FinanceATR";
						addActionMessage("Group Email Id modified in FinanceATR Group successfully.");
					}
					else if (bean.getConfigureGroupType().equals("P")) {
						type = "Payroll";
						addActionMessage("Group Email Id modified in Payroll Group successfully.");
					} else if (bean.getConfigureGroupType().equals("L")) {
						type = "Logistics";
						addActionMessage("Group Email Id modified in Corporate Procurement Group-NONINV successfully.");
					}
					 else if (bean.getConfigureGroupType().equals("C")) {
							type = "Corporate";
							addActionMessage("Group Email Id modified in Corporate Procurement Group-HS successfully.");
						}
					else if (bean.getConfigureGroupType().equals("I")) {
						type = "IT Group(CD ROM)";
						addActionMessage("Group Email Id modified in IT Group(CD ROM) successfully.");
					} 
					else if (bean.getConfigureGroupType().equals("K")) {
						type = "IT Group(App Security)";
						addActionMessage("Group Email Id modified in IT Group(App Security) successfully.");
					}
					else if (bean.getConfigureGroupType().equals("M")) {
						type = "IT Group(Report Request)";
						addActionMessage("Group Email Id modified in IT Group(Report Request) successfully.");
					}
					else if (bean.getConfigureGroupType().equals("N")) {
						type = "IT Group(System Change)";
						addActionMessage("Group Email Id modified in IT Group(System Change) successfully.");
					}
					else if (bean.getConfigureGroupType().equals("T")) {
						type = "Training Authority";
						addActionMessage("Group Email Id modified in Training Authority Group successfully.");
					} else if (bean.getConfigureGroupType().equals("E")) {
						type = "Education Ministry";
						addActionMessage("Group Email Id modified in Education Ministry Group successfully.");
					}
					else if (bean.getConfigureGroupType().equals("S")) {
						type = "Education Ministry";
						addActionMessage("Group Email Id modified in CEAR Approval Group successfully.");
					}
				} 
				else 
				{
					addActionMessage("Error occured");
//					/reset();
				}
			}
			if (configureType.equals("W")) 
			{
				bean.setWorldTravelFlag(true);
				bean.setConfigureOptionTypeFlag(false);
				List worldTravelList = model.showWorldTravelList(bean, request);
			}else 
			{
				bean.setWorldTravelFlag(false);
				bean.setConfigureOptionTypeFlag(true);
				List worldTravelList = model.showConfigEmailList(bean,configureType, request);
				List trainingAuthList = model.configureOptionType(bean,configureType, request);
			}
			
			bean.setConfigEmail("");
			bean.setConfigHiddenEmailId("");
			/*bean.setWorldId("");
			bean.setEmailId("");
			showWorldTravelList();
			bean.setListType("WorldTravel");*/
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public String addHRApprover() {
		System.out
				.println("here in addHRApprover=================================== ");

		try {
			String searchHrEmpId = bean.getSearchHrEmpId();
			//	String divisionId = bean.getDivId();
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			String mail = bean.getEmailId();
			String message = model.addApprover(searchHrEmpId, "H", mail);
			addActionMessage(message);
			showHrApproverList();
			bean.setSearchHrEmpId("");
			bean.setSearchHrEmpName("");
			bean.setSearchHrEmpToken("");
			bean.setEmailId("");
			bean.setListType("HR");
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	public String deleteAdminApprover() {
		try {
			String adminApproverEmpId = request
					.getParameter("adminApproverEmpId");
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			String message = model.deleteApprover(adminApproverEmpId, "A");
			showAdminApproverList();
			addActionMessage(message);
			bean.setListType("Admin");
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	public String deleteHRApprover() {
		try {
			String hrApproverEmpId = request.getParameter("hrApproverEmpId");
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			String message = model.deleteApprover(hrApproverEmpId, "H");
			showHrApproverList();
			addActionMessage(message);
			bean.setListType("HR");
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	// added ganesh start

	public String deleteFinanceApprover() {
		try {
			String financeApproverEmpId = request
					.getParameter("financeApproverEmpId");
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			String message = model.deleteApprover(financeApproverEmpId, "F");
			showFinanceApproverList();
			addActionMessage(message);
			bean.setListType("Finance");
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	//added by Nilesh for Finance ATR

	public String deleteFinanceATRApprover() {
		try {
			String financeATRApproverEmpId = request
					.getParameter("financeATRApproverEmpId");
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			String message = model.deleteApprover(financeATRApproverEmpId, "R");
			showFinanceATRApproverList();
			addActionMessage(message);
			bean.setListType("FinanceATR");
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	public String deleteCorporateProcurement() {
		try {
			String corporateApproverEmpId = request
					.getParameter("corporateApproverEmpId");
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			String message = model.deleteApprover(corporateApproverEmpId, "C");
			showCorporateProcList();
			addActionMessage(message);
			bean.setListType("Corporate");
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	// added ganesh end
	public String deletePayrollApprover() {
		try {
			String payrollApproverEmpId = request
					.getParameter("payrollApproverEmpId");
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			String message = model.deleteApprover(payrollApproverEmpId, "P");
			showPayrollApproverList();
			addActionMessage(message);
			bean.setListType("Payroll");
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	public String deleteLogisticsApprover() {
		try {
			String logisticsApproverEmpId = request
					.getParameter("logisticsApproverEmpId");
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			String message = model.deleteApprover(logisticsApproverEmpId, "L");
			showLogisticsApproverList();
			addActionMessage(message);
			bean.setListType("Logistics");
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	public String deleteItApprover() {
		try {
			String itApproverEmpId = request.getParameter("itApproverEmpId");
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			String message = model.deleteApprover(itApproverEmpId, "I");
			showItApproverList();
			addActionMessage(message);
			bean.setListType("IT");
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	public String deleteTrainingAuth() {
		try {
			String trainingAuthEmpId = request
					.getParameter("trainingAuthEmpId");
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			String message = model.deleteApprover(trainingAuthEmpId, "T");
			showTrainingAuthApproverList();
			addActionMessage(message);
			bean.setListType("Training Authority");
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	public String deleteEducationMinistry() {
		try {
			String educationMinistryEmpId = request
					.getParameter("educationMinistryEmpId");
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			String message = model.deleteApprover(educationMinistryEmpId, "E");
			showEducationMinistryApproverList();
			addActionMessage(message);
			bean.setListType("Education Ministry");
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	//World Travel delete 
	public String deleteWorldTravel() {
		try {
			String travelId = request.getParameter("travelId");

			System.out.println("Travael Id in deleteWorldTravel is === "
					+ travelId);

			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			boolean result;
			result = model.deleteApprover(bean, request, travelId);
			showWorldTravelList();
			model.terminate();

			if (result) {
				//addActionMessage("Record Deleted successfully.");
				addActionMessage("Email Id Deleted in World Travel Group successfully.");
			}
			bean.setListType("WorldTravel");
			bean.setWorldTravelFlag(true);
			bean.setConfigureOptionTypeFlag(false);
			List worldTravelList = model.showWorldTravelList(bean, request);
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	public String f9AdminEmployee() {
		try {
			String f9SearchEmployeeSql = "SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_LNAME) AS EMP_NAME, EMP_ID FROM HRMS_EMP_OFFC ";
			if (bean.getUserProfileDivision() != null
					&& bean.getUserProfileDivision().length() > 0) {
				f9SearchEmployeeSql += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
						+ bean.getUserProfileDivision() + ")";
			} else {
				f9SearchEmployeeSql += " WHERE 1=1 ";
			}

			f9SearchEmployeeSql += " AND EMP_ID NOT IN(SELECT APP_SETTINGS_EMP_ID FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'A') ";

			String[] headers = { "Employee Id", "Employee Name" };

			String[] headerWidth = { "30", "70" };

			String[] fieldNames = { "searchAdminEmpToken",
					"searchAdminEmpName", "searchAdminEmpId" };

			int[] columnIndex = { 0, 1, 2 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(f9SearchEmployeeSql, headers, headerWidth, fieldNames,
					columnIndex, submitFlag, submitToMethod);
			return "f9page";
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	public String f9HREmployee() {
		try {

			String f9SearchEmployeeSql = "SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_LNAME) AS EMP_NAME, EMP_ID FROM HRMS_EMP_OFFC ";
			//f9SearchEmployeeSql += getprofileQuery(bean);

			if (bean.getUserProfileDivision() != null
					&& bean.getUserProfileDivision().length() > 0) {
				f9SearchEmployeeSql += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
						+ bean.getUserProfileDivision() + ")";
			} else {
				f9SearchEmployeeSql += " WHERE 1=1 ";
			}

			f9SearchEmployeeSql += " AND EMP_ID NOT IN(SELECT APP_SETTINGS_EMP_ID FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'H') ";
			String[] headers = { "Employee Id", "Employee Name" };

			String[] headerWidth = { "30", "70" };

			String[] fieldNames = { "searchHrEmpToken", "searchHrEmpName",
					"searchHrEmpId" };

			int[] columnIndex = { 0, 1, 2 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(f9SearchEmployeeSql, headers, headerWidth, fieldNames,
					columnIndex, submitFlag, submitToMethod);
			return "f9page";
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	// added ganesh start
	public String f9FinanceEmployee() {
		try {
			String f9SearchEmployeeSql = "SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_LNAME) AS EMP_NAME, EMP_ID FROM HRMS_EMP_OFFC ";
			//f9SearchEmployeeSql += getprofileQuery(bean);

			if (bean.getUserProfileDivision() != null
					&& bean.getUserProfileDivision().length() > 0) {
				f9SearchEmployeeSql += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
						+ bean.getUserProfileDivision() + ")";
			} else {
				f9SearchEmployeeSql += " WHERE 1=1 ";
			}

			f9SearchEmployeeSql += " AND EMP_ID NOT IN(SELECT APP_SETTINGS_EMP_ID FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'F') ";

			/*	f9SearchEmployeeSql += " WHERE EMP_DIV=" + bean.getDivId();
					+ " ORDER BY UPPER(EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME), EMP_TOKEN ";
			 */
			String[] headers = { "Employee Id", "Employee Name" };

			String[] headerWidth = { "30", "70" };

			String[] fieldNames = { "searchFinanceEmpToken",
					"searchFinanceEmpName", "searchFinanceEmpId" };

			int[] columnIndex = { 0, 1, 2 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(f9SearchEmployeeSql, headers, headerWidth, fieldNames,
					columnIndex, submitFlag, submitToMethod);
			return "f9page";
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	//Added by Nilesh for Finance ATR
	public String f9FinanceATREmployee() {
		try {
			String f9SearchEmployeeSql = "SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_LNAME) AS EMP_NAME, EMP_ID FROM HRMS_EMP_OFFC ";
			//f9SearchEmployeeSql += getprofileQuery(bean);

			if (bean.getUserProfileDivision() != null
					&& bean.getUserProfileDivision().length() > 0) {
				f9SearchEmployeeSql += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
						+ bean.getUserProfileDivision() + ")";
			} else {
				f9SearchEmployeeSql += " WHERE 1=1 ";
			}
			f9SearchEmployeeSql += " AND EMP_ID NOT IN(SELECT APP_SETTINGS_EMP_ID FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'R') ";

			String[] headers = { "Employee Id", "Employee Name" };

			String[] headerWidth = { "30", "70" };

			String[] fieldNames = { "searchATREmpToken", "searchATREmpName",
					"searchFinanceATREmpId" };

			int[] columnIndex = { 0, 1, 2 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(f9SearchEmployeeSql, headers, headerWidth, fieldNames,
					columnIndex, submitFlag, submitToMethod);
			return "f9page";
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	//End Finance ATR

	//Corporate Pro Grp
	public String f9CorporateEmployee() {
		try {
			String f9SearchEmployeeSql = "SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_LNAME) AS EMP_NAME, EMP_ID FROM HRMS_EMP_OFFC ";
			//f9SearchEmployeeSql += getprofileQuery(bean);
			if (bean.getUserProfileDivision() != null
					&& bean.getUserProfileDivision().length() > 0) {
				f9SearchEmployeeSql += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
						+ bean.getUserProfileDivision() + ")";
			} else {
				f9SearchEmployeeSql += " WHERE 1=1 ";
			}

			f9SearchEmployeeSql += " AND EMP_ID NOT IN(SELECT APP_SETTINGS_EMP_ID FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'C') ";

			/*f9SearchEmployeeSql += " WHERE EMP_DIV=" + bean.getDivId();
			
					+ " ORDER BY UPPER(EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME), EMP_TOKEN ";
			 */
			String[] headers = { "Employee Id", "Employee Name" };

			String[] headerWidth = { "30", "70" };

			String[] fieldNames = { "searchCorporateToken1",
					"searchCorporateEmpName1", "searchCorporateEmpId1" };

			int[] columnIndex = { 0, 1, 2 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(f9SearchEmployeeSql, headers, headerWidth, fieldNames,
					columnIndex, submitFlag, submitToMethod);
			return "f9page";
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	//End Corp Pro Grp

	public String f9PayrollEmployee() {
		try {
			String f9SearchEmployeeSql = "SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_LNAME) AS EMP_NAME, EMP_ID FROM HRMS_EMP_OFFC ";
			//f9SearchEmployeeSql += getprofileQuery(bean);
			if (bean.getUserProfileDivision() != null
					&& bean.getUserProfileDivision().length() > 0) {
				f9SearchEmployeeSql += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
						+ bean.getUserProfileDivision() + ")";
			} else {
				f9SearchEmployeeSql += " WHERE 1=1 ";
			}
			f9SearchEmployeeSql += " AND EMP_ID NOT IN(SELECT APP_SETTINGS_EMP_ID FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'P') ";

			/*f9SearchEmployeeSql += " WHERE EMP_DIV=" + bean.getDivId();
			
					+ " ORDER BY UPPER(EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME), EMP_TOKEN ";
			 */
			String[] headers = { "Employee Id", "Employee Name" };

			String[] headerWidth = { "30", "70" };

			String[] fieldNames = { "searchPayrollEmpToken",
					"searchPayrollEmpName", "searchPayrollEmpId" };

			int[] columnIndex = { 0, 1, 2 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(f9SearchEmployeeSql, headers, headerWidth, fieldNames,
					columnIndex, submitFlag, submitToMethod);
			return "f9page";
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	public String f9LogisticsEmployee() {
		try {
			String f9SearchEmployeeSql = "SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_LNAME) AS EMP_NAME, EMP_ID FROM HRMS_EMP_OFFC ";
			//f9SearchEmployeeSql += getprofileQuery(bean);
			if (bean.getUserProfileDivision() != null
					&& bean.getUserProfileDivision().length() > 0) {
				f9SearchEmployeeSql += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
						+ bean.getUserProfileDivision() + ")";
			} else {
				f9SearchEmployeeSql += " WHERE 1=1 ";
			}

			f9SearchEmployeeSql += " AND EMP_ID NOT IN(SELECT APP_SETTINGS_EMP_ID FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'L') ";
			/*f9SearchEmployeeSql += " WHERE EMP_DIV=" + bean.getDivId();
			
					+ " ORDER BY UPPER(EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME), EMP_TOKEN ";
			 */
			String[] headers = { "Employee Id", "Employee Name" };

			String[] headerWidth = { "30", "70" };

			String[] fieldNames = { "searchLogisticsEmpToken",
					"searchLogisticsEmpName", "searchLogisticsEmpId" };

			int[] columnIndex = { 0, 1, 2 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(f9SearchEmployeeSql, headers, headerWidth, fieldNames,
					columnIndex, submitFlag, submitToMethod);
			return "f9page";
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	public String f9ItEmployee() {
		try {
			String f9SearchEmployeeSql = "SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_LNAME) AS EMP_NAME, EMP_ID FROM HRMS_EMP_OFFC ";
			//	f9SearchEmployeeSql += getprofileQuery(bean);
			if (bean.getUserProfileDivision() != null
					&& bean.getUserProfileDivision().length() > 0) {
				f9SearchEmployeeSql += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
						+ bean.getUserProfileDivision() + ")";
			} else {
				f9SearchEmployeeSql += " WHERE 1=1 ";
			}

			f9SearchEmployeeSql += " AND EMP_ID NOT IN(SELECT APP_SETTINGS_EMP_ID FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'I') ";

			/*	f9SearchEmployeeSql += " WHERE EMP_DIV=" + bean.getDivId();
			
					+ " ORDER BY UPPER(EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME), EMP_TOKEN ";
			 */
			String[] headers = { "Employee Id", "Employee Name" };

			String[] headerWidth = { "30", "70" };

			String[] fieldNames = { "searchItEmpToken", "searchItEmpName",
					"searchItEmpId" };

			int[] columnIndex = { 0, 1, 2 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(f9SearchEmployeeSql, headers, headerWidth, fieldNames,
					columnIndex, submitFlag, submitToMethod);
			return "f9page";
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	public String f9TrainingAuth() {
		try {
			String f9SearchEmployeeSql = "SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_LNAME) AS EMP_NAME, EMP_ID FROM HRMS_EMP_OFFC ";
			//	f9SearchEmployeeSql += getprofileQuery(bean);
			if (bean.getUserProfileDivision() != null
					&& bean.getUserProfileDivision().length() > 0) {
				f9SearchEmployeeSql += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
						+ bean.getUserProfileDivision() + ")";
			} else {
				f9SearchEmployeeSql += " WHERE 1=1 ";
			}
			f9SearchEmployeeSql += " AND EMP_ID NOT IN(SELECT APP_SETTINGS_EMP_ID FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'T') ";

			/*f9SearchEmployeeSql += " WHERE EMP_DIV=" + bean.getDivId();
			
					+ " ORDER BY UPPER(EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME), EMP_TOKEN ";*/

			String[] headers = { "Employee Id", "Employee Name" };

			String[] headerWidth = { "30", "70" };

			String[] fieldNames = { "searchTrainingAuthToken",
					"searchTrainingAuthName", "searchTrainingAuthId" };

			int[] columnIndex = { 0, 1, 2 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(f9SearchEmployeeSql, headers, headerWidth, fieldNames,
					columnIndex, submitFlag, submitToMethod);
			return "f9page";
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	public String f9EducationMinistry() {
		try {
			String f9SearchEmployeeSql = "SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_LNAME) AS EMP_NAME, EMP_ID FROM HRMS_EMP_OFFC ";
			//f9SearchEmployeeSql += getprofileQuery(bean);
			if (bean.getUserProfileDivision() != null
					&& bean.getUserProfileDivision().length() > 0) {
				f9SearchEmployeeSql += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
						+ bean.getUserProfileDivision() + ")";
			} else {
				f9SearchEmployeeSql += " WHERE 1=1 ";
			}

			f9SearchEmployeeSql += " AND EMP_ID NOT IN(SELECT APP_SETTINGS_EMP_ID FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_TYPE = 'E') ";

			/*	f9SearchEmployeeSql += " WHERE EMP_DIV=" + bean.getDivId();
			
					+ " ORDER BY UPPER(EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME), EMP_TOKEN ";*/

			String[] headers = { "Employee Id", "Employee Name" };

			String[] headerWidth = { "30", "70" };

			String[] fieldNames = { "searchEducationMinistryToken",
					"searchEducationMinistryName", "searchEducationMinistryId" };

			int[] columnIndex = { 0, 1, 2 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(f9SearchEmployeeSql, headers, headerWidth, fieldNames,
					columnIndex, submitFlag, submitToMethod);
			return "f9page";
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}

	// added ganesh end
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		bean = new ApprovalSettings();
		bean.setMenuCode(1167);
		/*
		 * showHrApproverList(); showAdminApproverList();
		 * showFinanceApproverList(); showPayrollApproverList();
		 * showLogisticsApproverList(); showItApproverList();
		 * showTrainingAuthApproverList();
		 */
	}

	// master input method//
	public String input() {
		System.out.println("-------inside input method-----");
		ApprovalSettingsModel model = new ApprovalSettingsModel();
		model.initiate(context, session);
		//getHrList();
		String configureGroupType = bean.getConfigureGroupType();
		if (configureGroupType=="") 
		{
			
			bean.setConfigureOptionTypeFlag(false);
		}else 
		{
			bean.setConfigureOptionTypeFlag(true);
		}
		
		model.terminate();
		getNavigationPanel(1);
		return "input";
	}

	public String callPage() throws Exception {

		ApprovalSettingsModel model = new ApprovalSettingsModel();
		model.initiate(context, session);
		input();
		getNavigationPanel(1);
		model.terminate();

		return "input";
	}

	public String getHrList() {
		try {
			System.out
					.println("in getgetHrList ---------111111111111-----------------");
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			model.showHRApproverList(bean, request);
			System.out
					.println("in getgetHrList ---------22222222222-----------------");
			bean.setListType("HR");
			model.terminate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(4);

		return SUCCESS;
	}

	public String getAdminList() {
		try {
			System.out.println("sdfsdf");
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();

			model.showAdminApproverList(bean, request);

			bean.setListType("Admin");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(4);

		return SUCCESS;
	}

	public String getFinanceList() {
		try {
			System.out.println("sdfsdf");
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();

			model.showFinanceApproverList(bean, request);

			bean.setListType("Finance");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(4);
		return INPUT;
	}

	/*Added by Nilesh for Finance ATR*/
	public String getFinanceGroupATRList() {
		try {
			System.out.println("sdfsdf");
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();

			model.showFinanceATRApproverList(bean, request);

			bean.setListType("FinanceATR");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(4);
		return INPUT;
	}

	public String getCorporateProcGroupList() {
		try {
			System.out.println("sdfsdf");
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();

			model.showCorporateProcList(bean, request);

			bean.setListType("Corporate");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(4);
		return INPUT;
	}

	public String getPayrollList() {
		try {
			System.out.println("sdfsdf");
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();

			model.showPayrollApproverList(bean, request);

			bean.setListType("Payroll");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(4);
		return INPUT;
	}

	public String getLogisticsList() {
		try {
			System.out.println("sdfsdf");
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();

			model.showLogisticsApproverList(bean, request);

			bean.setListType("Logistics");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(4);
		return INPUT;
	}

	public String getITList() {
		try {
			System.out.println("sdfsdf");
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();

			model.showItApproverList(bean, request);

			bean.setListType("IT");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(4);
		return INPUT;
	}

	public String getTrainingList() {
		try {
			System.out.println("sdfsdf");
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();

			model.showTrainingAuthList(bean, request);

			bean.setListType("Training Authority");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(4);
		return INPUT;
	}

	public String getEducationList() {
		try {
			System.out.println("sdfsdf");
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();

			model.showEducationMinistryList(bean, request);

			bean.setListType("Education Ministry");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(4);
		return INPUT;
	}

	//World Travel
	public String getWorldTravelList() {
		try {
			System.out
					.println("in getWorldTravelList------------------------------");
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			model.showWorldTravelList(bean, request);

			bean.setListType("WorldTravel");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(4);
		return INPUT;
	}

	public void showAdminApproverList() {
		try {
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			List adminApproverList = model.showAdminApproverList(bean, request);

			model.terminate();
		} catch (Exception e) {
			logger.error(e);
		}
	}

	// added ganesh start

	public void showFinanceApproverList() {
		try {
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			List financeApproverList = model.showFinanceApproverList(bean,
					request);

			model.terminate();
		} catch (Exception e) {
			logger.error(e);
		}
	}

	//added by Nilesh for Finance ATR

	public void showFinanceATRApproverList() {
		try {
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			List financeApproverList = model.showFinanceATRApproverList(bean,
					request);

			model.terminate();
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public void showCorporateProcList() {
		try {
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			List financeApproverList = model.showCorporateProcList(bean,
					request);

			model.terminate();
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public void showPayrollApproverList() {
		try {
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			List payrollApproverList = model.showPayrollApproverList(bean,
					request);

			model.terminate();
		} catch (Exception e) {
			logger.error(e);
		}
	}

	// added ganesh end
	public void showHrApproverList() {
		try {
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			List hrApproverList = model.showHRApproverList(bean, request);

			model.terminate();
		} catch (Exception e) {
			logger.error(e);
		}
	}

	private void showLogisticsApproverList() {
		try {
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			List logisticsApproverList = model.showLogisticsApproverList(bean,
					request);

			model.terminate();
		} catch (Exception e) {
			logger.error(e);
		}

	}

	private void showItApproverList() {
		try {
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			List itApproverList = model.showItApproverList(bean, request);

			model.terminate();
		} catch (Exception e) {
			logger.error(e);
		}

	}

	private void showTrainingAuthApproverList() {
		try {
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			List trainingAuthList = model.showTrainingAuthList(bean, request);

			model.terminate();
		} catch (Exception e) {
			logger.error(e);
		}

	}

	private void showEducationMinistryApproverList() {
		try {
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			List educationMinistryList = model.showEducationMinistryList(bean,
					request);

			model.terminate();
		} catch (Exception e) {
			logger.error(e);
		}

	}

	//world travel show method
	private void showWorldTravelList() {
		System.out
				.println("in showWorldTravelList %%%%%%%%%%%%%%%%%%%%%%%%%%%%% ");
		try {
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			List worldTravelList = model.showWorldTravelList(bean, request);

			model.terminate();
		} catch (Exception e) {
			logger.error(e);
		}

	}
	
	
	public String configureOptionType() {
		try {
			System.out.println("############ ");
			String configureType = request
					.getParameter("configureGroupType");
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			//bean.setConfigureOptionTypeFlag(true);
			
			
			if (configureType.equals("W")) 
			{
				bean.setWorldTravelFlag(true);
				bean.setConfigureOptionTypeFlag(false);
				List worldTravelList = model.showWorldTravelList(bean, request);
			}else 
			{
				bean.setWorldTravelFlag(false);
				bean.setConfigureOptionTypeFlag(true);
				List worldTravelList = model.showConfigEmailList(bean,configureType, request);
				List trainingAuthList = model.configureOptionType(bean,configureType, request);
			}
			
			bean.setConfigAuthId("");
			bean.setConfigHiddenEmailId("");
			bean.setConfigAuthHiddenId("");
			bean.setSearchConfigAuthId("");
			bean.setSearchConfigAuthName("");
			bean.setSearchConfigAuthToken("");
			bean.setConfigReceivedFlag("");
			bean.setConfigEmail("");
			//showAdminApproverList();
			//addActionMessage(message);
			//bean.setListType("Admin");
			model.terminate();
			
		} catch (Exception e) {
			logger.error(e);
			//return "";
		}
		return INPUT;
	}
	public String f9ConfigAuth() {
		try {
			String str = "0";
			String configureType = request
			.getParameter("configureGroupType");
			
			 if (!bean.getSearchConfigEmpAuthId().equals("")){
					str = bean.getSearchConfigEmpAuthId();
					
					System.out.println("bean.getSearchConfigEmpAuthId()" + bean.getSearchConfigEmpAuthId());
				}

				
			
			
			String f9SearchEmployeeSql = "SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_LNAME) AS EMP_NAME, EMP_ID FROM HRMS_EMP_OFFC ";
			//	f9SearchEmployeeSql += getprofileQuery(bean);
			if (bean.getUserProfileDivision() != null
					&& bean.getUserProfileDivision().length() > 0) {
				f9SearchEmployeeSql += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
						+ bean.getUserProfileDivision() + ")";
			} else {
				f9SearchEmployeeSql += " WHERE 1=1 ";
			}
			//f9SearchEmployeeSql += " AND EMP_STATUS='S' AND EMP_ID NOT IN(" + str + ")";
			f9SearchEmployeeSql += " AND EMP_ID NOT IN(" + str + ") ";
			/*f9SearchEmployeeSql += " WHERE EMP_DIV=" + bean.getDivId();
			
					+ " ORDER BY UPPER(EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME), EMP_TOKEN ";*/

			String[] headers = { "Employee Id", "Employee Name" };

			String[] headerWidth = { "30", "70" };

			String[] fieldNames = { "searchConfigAuthToken",
					"searchConfigAuthName", "searchConfigAuthId" };

			int[] columnIndex = { 0, 1, 2 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(f9SearchEmployeeSql, headers, headerWidth, fieldNames,
					columnIndex, submitFlag, submitToMethod);
			return "f9page";
		} catch (Exception e) {
			logger.error(e);
			return "";
		}
	}
	
	public String addConfigAuth()throws Exception {
		try {
			String configureType = request
			.getParameter("configureGroupType");
			
			ApprovalSettingsModel model = new ApprovalSettingsModel();
			model.initiate(context, session);
			boolean result;
			if (bean.getConfigAuthId().equals("")) {
				result = model.addConfigAuth(bean);
				if (result) {

					//addActionMessage(getMessage("save"));
					String type = "";

					if (bean.getConfigureGroupType().equals("H")) {
						type = "HR";
						addActionMessage("Employee added in HR Group successfully.");
					} else if (bean.getConfigureGroupType().equals("A")) {
						type = "Admin";
						addActionMessage("Employee added in Admin Group successfully.");
					} else if (bean.getConfigureGroupType().equals("F")) {
						type = "Finance";
						addActionMessage("Employee added in Finance Group successfully.");
					} 
					else if (bean.getConfigureGroupType().equals("R")) {
						type = "FinanceATR";
						addActionMessage("Employee added in FinanceATR Group successfully.");
					}
					else if (bean.getConfigureGroupType().equals("P")) {
						type = "Payroll";
						addActionMessage("Employee added in Payroll Group successfully.");
					} else if (bean.getConfigureGroupType().equals("L")) {
						type = "Logistics";
						addActionMessage("Employee added in Corporate Procurement Group-NONINV successfully.");
					}
					 else if (bean.getConfigureGroupType().equals("C")) {
							type = "Corporate";
							addActionMessage("Employee added in Corporate Procurement Group-HS successfully.");
						}
					else if (bean.getConfigureGroupType().equals("I")) {
						type = "IT Group(CD Rom)";
						addActionMessage("Employee added in IT Group(CD Rom) successfully.");
					}
					else if (bean.getConfigureGroupType().equals("K")) {
						type = "IT Group(App Security)";
						addActionMessage("Employee added in IT Group(App Security) successfully.");
					}
					else if (bean.getConfigureGroupType().equals("M")) {
						type = "IT Group(Report Request)";
						addActionMessage("Employee added in IT Group(Report Request) successfully.");
					}
					else if (bean.getConfigureGroupType().equals("N")) {
						type = "IT Group(System Change)";
						addActionMessage("Employee added in IT Group(System Change) successfully.");
					}
					else if (bean.getConfigureGroupType().equals("T")) {
						type = "Training Authority";
						addActionMessage("Employee added in Training Authority Group successfully.");
					} else if (bean.getConfigureGroupType().equals("E")) {
						type = "Education Ministry";
						addActionMessage("Employee added in Education Ministry Group successfully.");
					}
					else if (bean.getConfigureGroupType().equals("S")) {
						type = "Education Ministry";
						addActionMessage("Employee added in CEAR Approval Group successfully.");
					}

				} else {
					addActionMessage(getMessage("duplicate"));
					//bean.setEmailId("");
				}
			}
			else 
			{
				result = model.updateRecords(bean,request);
				if (result) 
				{
					//addActionMessage("Application modified successfully.");
					String type = "";

					if (bean.getConfigureGroupType().equals("H")) {
						type = "HR";
						addActionMessage("Employee modified in HR Group successfully.");
					} else if (bean.getConfigureGroupType().equals("A")) {
						type = "Admin";
						addActionMessage("Employee modified in Admin Group successfully.");
					} else if (bean.getConfigureGroupType().equals("F")) {
						type = "Finance";
						addActionMessage("Employee modified in Finance Group successfully.");
					} 
					else if (bean.getConfigureGroupType().equals("R")) {
						type = "FinanceATR";
						addActionMessage("Employee modified in FinanceATR Group successfully.");
					}
					else if (bean.getConfigureGroupType().equals("P")) {
						type = "Payroll";
						addActionMessage("Employee modified in Payroll Group successfully.");
					} else if (bean.getConfigureGroupType().equals("L")) {
						type = "Logistics";
						addActionMessage("Employee modified in Corporate Procurement Group-NONINV successfully.");
					}
					 else if (bean.getConfigureGroupType().equals("C")) {
							type = "Corporate";
							addActionMessage("Employee modified in Corporate Procurement Group-HS successfully.");
						}
					else if (bean.getConfigureGroupType().equals("I")) {
						type = "IT Group(CD Rom)";
						addActionMessage("Employee modified in IT Group(CD Rom) successfully.");
					}
					else if (bean.getConfigureGroupType().equals("K")) {
						type = "IT Group(App Security)";
						addActionMessage("Employee modified in IT Group(App Security) successfully.");
					}
					else if (bean.getConfigureGroupType().equals("M")) {
						type = "IT Group(Report Request)";
						addActionMessage("Employee modified in IT Group(Report Request) successfully.");
					}
					else if (bean.getConfigureGroupType().equals("N")) {
						type = "IT Group(System Change)";
						addActionMessage("Employee modified in IT Group(System Change) successfully.");
					}
					else if (bean.getConfigureGroupType().equals("T")) {
						type = "Training Authority";
						addActionMessage("Employee modified in Training Authority Group successfully.");
					} else if (bean.getConfigureGroupType().equals("E")) {
						type = "Education Ministry";
						addActionMessage("Employee modified in Education Ministry Group successfully.");
					}
					else if (bean.getConfigureGroupType().equals("S")) {
						type = "Education Ministry";
						addActionMessage("Employee modified in CEAR Approval Group successfully.");
					}
				} 
				else 
				{
					addActionMessage("Error occured");
//					/reset();
				}
			}
			if (configureType.equals("W")) 
			{
				bean.setWorldTravelFlag(true);
				bean.setConfigureOptionTypeFlag(false);
				List worldTravelList = model.showWorldTravelList(bean, request);
			}else 
			{
				bean.setWorldTravelFlag(false);
				bean.setConfigureOptionTypeFlag(true);
				List worldTravelList = model.showConfigEmailList(bean,configureType, request);
				List trainingAuthList = model.configureOptionType(bean,configureType, request);
			}
			
			bean.setConfigHiddenEmailId("");
			bean.setConfigAuthHiddenId("");
			bean.setSearchConfigAuthId("");
			bean.setSearchConfigAuthName("");
			bean.setSearchConfigAuthToken("");
			bean.setConfigReceivedFlag("");
			/*showWorldTravelList();
			bean.setListType("WorldTravel");*/
			model.terminate();
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * Method to set values of all the fields in the form for edit.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String edit() throws Exception {
		String requestID = request.getParameter("configAuthHiddenId");
		ApprovalSettingsModel model = new ApprovalSettingsModel();
		model.initiate(context, session);
		String configureType = request
		.getParameter("configureGroupType");
		
		
		bean.setConfigAuthId("");
		bean.setConfigHiddenEmailId("");
		bean.setConfigAuthHiddenId("");
		bean.setSearchConfigAuthId("");
		bean.setSearchConfigAuthName("");
		bean.setSearchConfigAuthToken("");
		bean.setConfigReceivedFlag("");
		
		model.getConfigRecord(bean,requestID);
		if (configureType.equals("W")) 
		{
			bean.setWorldTravelFlag(true);
			bean.setConfigureOptionTypeFlag(false);
			List worldTravelList = model.showWorldTravelList(bean, request);
		}else 
		{
			bean.setWorldTravelFlag(false);
			bean.setConfigureOptionTypeFlag(true);
			List worldTravelList = model.showConfigEmailList(bean,configureType, request);
			List trainingAuthList = model.configureOptionType(bean,configureType, request);
		}
		
		model.terminate();
		return SUCCESS;
	}
	/**
	 * Method to set values of all the fields in the form for edit.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String editEmail() throws Exception {
		String requestID = request.getParameter("configEmailId");
		System.out.println("requestID===== " + requestID);
		ApprovalSettingsModel model = new ApprovalSettingsModel();
		model.initiate(context, session);
		String configureType = request
		.getParameter("configureGroupType");
		
		
		bean.setConfigAuthId("");
		bean.setConfigHiddenEmailId("");
		bean.setConfigEmail("");
		bean.setConfigHiddenEmailId("");
		bean.setConfigAuthHiddenId("");
		bean.setSearchConfigAuthId("");
		bean.setSearchConfigAuthName("");
		bean.setSearchConfigAuthToken("");
		bean.setConfigReceivedFlag("");
		
		model.getEmailRecord(bean,requestID);
		if (configureType.equals("W")) 
		{
			bean.setWorldTravelFlag(true);
			bean.setConfigureOptionTypeFlag(false);
			List worldTravelList = model.showWorldTravelList(bean, request);
		}else 
		{
			bean.setWorldTravelFlag(false);
			bean.setConfigureOptionTypeFlag(true);
			List worldTravelList = model.showConfigEmailList(bean,configureType, request);
			List trainingAuthList = model.configureOptionType(bean,configureType, request);
		}
		
		model.terminate();
		return SUCCESS;
	}
	
	/**
	 * Method to delete the address details of an employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		
		ApprovalSettingsModel model = new ApprovalSettingsModel();
		model.initiate(context, session);
		
		String requestID = request.getParameter("configAuthId");
		
		String configureType = request
		.getParameter("configureGroupType");
		
		boolean result = model.delAddRecord(bean,requestID,request);
		model.terminate();
		if (result) {
			//addActionMessage(getMessage("delete"));
			String type = "";

			if (bean.getConfigureGroupType().equals("H")) {
				type = "HR";
				addActionMessage("Employee deleted in HR Group successfully.");
			} else if (bean.getConfigureGroupType().equals("A")) {
				type = "Admin";
				addActionMessage("Employee deleted in Admin Group successfully.");
			} else if (bean.getConfigureGroupType().equals("F")) {
				type = "Finance";
				addActionMessage("Employee deleted in Finance Group successfully.");
			} 
			else if (bean.getConfigureGroupType().equals("R")) {
				type = "FinanceATR";
				addActionMessage("Employee deleted in FinanceATR Group successfully.");
			}
			else if (bean.getConfigureGroupType().equals("P")) {
				type = "Payroll";
				addActionMessage("Employee deleted in Payroll Group successfully.");
			} else if (bean.getConfigureGroupType().equals("L")) {
				type = "Logistics";
				addActionMessage("Employee deleted in Corporate Procurement Group-NONINV successfully.");
			}
			 else if (bean.getConfigureGroupType().equals("C")) {
					type = "Corporate";
					addActionMessage("Employee deleted in Corporate Procurement Group-HS successfully.");
				}
			else if (bean.getConfigureGroupType().equals("I")) {
				type = "IT Group(CD Rom)";
				addActionMessage("Employee deleted in IT Group(CD Rom) successfully.");
			} 
			else if (bean.getConfigureGroupType().equals("K")) {
				type = "IT Group(App Security)";
				addActionMessage("Employee deleted in IT Group(App Security) successfully.");
			} 
			else if (bean.getConfigureGroupType().equals("M")) {
				type = "IT Group(Report Request)";
				addActionMessage("Employee deleted in IT Group(Report Request) successfully.");
			}
			else if (bean.getConfigureGroupType().equals("N")) {
				type = "IT Group(System Change)";
				addActionMessage("Employee deleted in IT Group(System Change) successfully.");
			}
			else if (bean.getConfigureGroupType().equals("T")) {
				type = "Training Authority";
				addActionMessage("Employee deleted in Training Authority Group successfully.");
			} else if (bean.getConfigureGroupType().equals("E")) {
				type = "Education Ministry";
				addActionMessage("Employee deleted in Education Ministry Group successfully.");
			}
			else if (bean.getConfigureGroupType().equals("S")) {
				type = "Education Ministry";
				addActionMessage("Employee deleted in CEAR Approval Group successfully.");
			}

		}// end of if
		else {
			addActionMessage(getMessage("del.error"));
		}// end of else
		/*addDet.setAddress1("");
		addDet.setAddress2("");
		addDet.setAddress3("");
		addDet.setCityName("");
		addDet.setCountry("");
		addDet.setEmailId("");
		addDet.setFaxNo("");
		addDet.setMobNo("");
		addDet.setPhone1("");
		addDet.setPhone2("");
		addDet.setStateName("");
		addDet.setType("");
		addDet.setPinCode("");
		addDet.setHiddType("");
		addDet.setNewFlag("");*/
		bean.setConfigAuthId("");
		if (configureType.equals("W")) 
		{
			bean.setWorldTravelFlag(true);
			bean.setConfigureOptionTypeFlag(false);
			List worldTravelList = model.showWorldTravelList(bean, request);
		}else 
		{
			bean.setWorldTravelFlag(false);
			bean.setConfigureOptionTypeFlag(true);
			List worldTravelList = model.showConfigEmailList(bean,configureType, request);
			List trainingAuthList = model.configureOptionType(bean,configureType, request);
		}
		
		return SUCCESS;

	}
	
	/**
	 * Method to delete the address details of an employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String deleteEmail() throws Exception {
		
		ApprovalSettingsModel model = new ApprovalSettingsModel();
		model.initiate(context, session);
		
		String requestID = request.getParameter("configHiddenEmailId");
		System.out.println("requestID===== " + requestID);
		
		String configureType = request
		.getParameter("configureGroupType");
		
		boolean result = model.delEmailRecord(bean,requestID,request);
		model.terminate();
		if (result) {
			//addActionMessage(getMessage("delete"));
			String type = "";

			if (bean.getConfigureGroupType().equals("H")) {
				type = "HR";
				addActionMessage("Group Email Id deleted in HR Group successfully.");
			} else if (bean.getConfigureGroupType().equals("A")) {
				type = "Admin";
				addActionMessage("Group Email Id deleted in Admin Group successfully.");
			} else if (bean.getConfigureGroupType().equals("F")) {
				type = "Finance";
				addActionMessage("Group Email Id deleted in Finance Group successfully.");
			} 
			else if (bean.getConfigureGroupType().equals("R")) {
				type = "FinanceATR";
				addActionMessage("Group Email Id deleted in FinanceATR Group successfully.");
			}
			else if (bean.getConfigureGroupType().equals("P")) {
				type = "Payroll";
				addActionMessage("Group Email Id deleted in Payroll Group successfully.");
			} else if (bean.getConfigureGroupType().equals("L")) {
				type = "Logistics";
				addActionMessage("Group Email Id deleted in Corporate Procurement Group-NONINV successfully.");
			}
			 else if (bean.getConfigureGroupType().equals("C")) {
					type = "Corporate";
					addActionMessage("Group Email Id deleted in Corporate Procurement Group-HS successfully.");
				}
			else if (bean.getConfigureGroupType().equals("I")) {
				type = "IT Group(CD Rom)";
				addActionMessage("Group Email Id deleted in IT Group(CD Rom) successfully.");
			}
			else if (bean.getConfigureGroupType().equals("K")) {
				type = "IT Group(App Security)";
				addActionMessage("Group Email Id deleted in IT Group(App Security) successfully.");
			}
			else if (bean.getConfigureGroupType().equals("M")) {
				type = "IT Group(Report Request)";
				addActionMessage("Group Email Id deleted in IT Group(Report Request) successfully.");
			}
			else if (bean.getConfigureGroupType().equals("N")) {
				type = "IT Group(System Change)";
				addActionMessage("Group Email Id deleted in IT Group(System Change) successfully.");
			}
			else if (bean.getConfigureGroupType().equals("T")) {
				type = "Training Authority";
				addActionMessage("Group Email Id deleted in Training Authority Group successfully.");
			} else if (bean.getConfigureGroupType().equals("E")) {
				type = "Education Ministry";
				addActionMessage("Group Email Id deleted in Education Ministry Group successfully.");
			}
			else if (bean.getConfigureGroupType().equals("S")) {
				type = "Education Ministry";
				addActionMessage("Group Email Id deleted in CEAR Approval Group successfully.");
			}


		}// end of if
		else {
			addActionMessage(getMessage("del.error"));
		}// end of else
		
		bean.setConfigHiddenEmailId("");
		/*addDet.setAddress1("");
		addDet.setAddress2("");
		addDet.setAddress3("");
		addDet.setCityName("");
		addDet.setCountry("");
		addDet.setEmailId("");
		addDet.setFaxNo("");
		addDet.setMobNo("");
		addDet.setPhone1("");
		addDet.setPhone2("");
		addDet.setStateName("");
		addDet.setType("");
		addDet.setPinCode("");
		addDet.setHiddType("");
		addDet.setNewFlag("");*/
		
		if (configureType.equals("W")) 
		{
			bean.setWorldTravelFlag(true);
			bean.setConfigureOptionTypeFlag(false);
			List worldTravelList = model.showWorldTravelList(bean, request);
		}else 
		{
			bean.setWorldTravelFlag(false);
			bean.setConfigureOptionTypeFlag(true);
			List worldTravelList = model.showConfigEmailList(bean,configureType, request);
			List trainingAuthList = model.configureOptionType(bean,configureType, request);
		}
		
		return SUCCESS;

	}
	
}