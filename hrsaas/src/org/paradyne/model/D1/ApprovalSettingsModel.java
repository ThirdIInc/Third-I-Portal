package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.D1.ApprovalSettings;
import org.paradyne.bean.D1.BusinessUnitBean;
import org.paradyne.bean.D1.PersonalDataChange;
import org.paradyne.bean.D1.RegionMasterBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.common.ReportingModel;

/**
 * Bhushan Dasare Feb 14, 2011
 */

public class ApprovalSettingsModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ApprovalSettingsModel.class);

	public String addApprover(String searchHrEmpId, String approvalType,String email) {
		String message = "HR Approver cannot be added!";
System.out.println("email id here we get is======================="+email);
		try {

			String addApproverSql = "INSERT INTO HRMS_D1_APPROVAL_SETTINGS (APP_SETTINGS_ID, APP_SETTINGS_EMP_ID, APP_SETTINGS_TYPE,APP_EMAIL_ID) "
					+ " VALUES ((SELECT NVL(MAX(APP_SETTINGS_ID), 0) + 1 FROM HRMS_D1_APPROVAL_SETTINGS), "
					+ searchHrEmpId
					+ ", '"
					+ approvalType
					+ "','"
					+ email
					+ "')";
			boolean isApproverAdded = getSqlModel().singleExecute(
					addApproverSql);

			if (isApproverAdded) {
				String type = "";

				if (approvalType.equals("H")) {
					type = "HR";
				} else if (approvalType.equals("A")) {
					type = "Admin";
				} else if (approvalType.equals("F")) {
					type = "Finance";
				} 
				else if (approvalType.equals("R")) {
					type = "FinanceATR";
				}
				else if (approvalType.equals("P")) {
					type = "Payroll";
				} else if (approvalType.equals("L")) {
					type = "Logistics";
				}
				 else if (approvalType.equals("C")) {
						type = "Corporate";
					}
				else if (approvalType.equals("I")) {
					type = "IT";
				} else if (approvalType.equals("T")) {
					type = "Training Authority";
				} else if (approvalType.equals("E")) {
					type = "Education Ministry";
				}

				message = type + " Approver added Successfully!";
			}

		} catch (Exception e) {
			logger.error(e);
		}

		return message;
	}

	/* for checking duplicate entry of record during insertion */

	/*
	 * public boolean checkDuplicate(String divisionId) { boolean result =
	 * false; String query = "SELECT APP_DIVISION_CODE FROM
	 * HRMS_D1_APPROVAL_SETTINGS WHERE UPPER(APP_DIVISION_CODE) LIKE '"
	 * +divisionId; Object[][] data = getSqlModel().getSingleResult(query); if
	 * (data != null && data.length > 0) { result = true; } return result;
	 *  }
	 * 
	 */
	public String deleteApprover(String hrApproverEmpId, String approvalType) {
		String message = "HR Approver cannot be deleted!";
		try {
			String deleteHRApproversql = " DELETE FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_EMP_ID = "
					+ hrApproverEmpId
					+ " AND APP_SETTINGS_TYPE = '"
					+ approvalType + "'";
			boolean isHRApproverDeleted = getSqlModel().singleExecute(
					deleteHRApproversql);

			if (isHRApproverDeleted) {
				String type = "";

				if (approvalType.equals("H")) {
					type = "HR";

				} else if (approvalType.equals("A")) {
					type = "Admin";
				} else if (approvalType.equals("F")) {
					type = "Finance";
				} else if (approvalType.equals("R")) {
					type = "FinanceATR";
				}
				else if (approvalType.equals("P")) {
					type = "Payroll";
				} else if (approvalType.equals("L")) {
					type = "Logistics";
				}
				else if (approvalType.equals("C")) {
					type = "Corporate";
				} else if (approvalType.equals("I")) {
					type = "IT";
				} else if (approvalType.equals("T")) {
					type = "Training Authority";
				} else if (approvalType.equals("E")) {
					type = "Education Ministry";
				}
				else if (approvalType.equals("W")) {
					type = "WorldTravel";
				}
				
				
				message = type + " Approver deleted Successfully!";
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return message;
	}
	
	//World Travel
	
	public boolean deleteApprover(ApprovalSettings bean,
			HttpServletRequest request,String deleteId ) {
		boolean result = false;
		

		String delQuery = "DELETE FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_ID="
				+ deleteId;
		result = getSqlModel().singleExecute(delQuery);
		return result;
	}
	
	
	/**For inserting record into DB */
	public boolean addEmail(ApprovalSettings bean) {
		

			Object addObj[][] = new Object[1][2];

			
			String query1 = "SELECT NVL(MAX(APP_SETTINGS_ID),0)+1 FROM  HRMS_D1_APPROVAL_SETTINGS";
			Object[][] mail = getSqlModel().getSingleResult(query1);
			
			addObj[0][0] = checkNull(String.valueOf(mail[0][0]));
			addObj[0][1] = bean.getEmailId().trim();

			bean.setWorldId(String.valueOf(mail[0][0]));
			
			return getSqlModel().singleExecute(getQuery(1), addObj);

		
	}
	
		
	public List showHRApproverList(ApprovalSettings bean,
			HttpServletRequest request) {
		List hrApproverList = new ArrayList();

		/*String query = "SELECT DIV_ID, DIV_NAME from HRMS_DIVISION where DIV_ID NOT IN(SELECT distinct APP_DIVISION_CODE FROM HRMS_D1_APPROVAL_SETTINGS "
				+ " where APP_DIVISION_CODE is not null)";
		Object[][] result = getSqlModel().getSingleResult(query);*/

		System.out.println("**********************model**********************************");
			try {
				String showHrApproverListSql = " SELECT APP_SETTINGS_EMP_ID, EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME ,APP_EMAIL_ID"
						+ " FROM HRMS_D1_APPROVAL_SETTINGS  "
						+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_APPROVAL_SETTINGS.APP_SETTINGS_EMP_ID) "
						+ " WHERE APP_SETTINGS_TYPE = 'H'  ORDER BY APP_SETTINGS_ID ";
				Object[][] hrApproverObj = getSqlModel().getSingleResult(
						showHrApproverListSql);

				String[] pageIndexHrApproved = Utility.doPaging(bean
						.getMyPage(), hrApproverObj.length, 20);
				if (pageIndexHrApproved == null) {
					pageIndexHrApproved[0] = "0";
					pageIndexHrApproved[1] = "20";
					pageIndexHrApproved[2] = "1";
					pageIndexHrApproved[3] = "1";
					pageIndexHrApproved[4] = "";
				}

				request.setAttribute("totalHrApprovePage", Integer
						.parseInt(String.valueOf(pageIndexHrApproved[2])));
				request.setAttribute("hrApprovePageNo", Integer.parseInt(String
						.valueOf(pageIndexHrApproved[3])));
				if (pageIndexHrApproved[4].equals("1"))
					bean.setMyPage("1");

				if (hrApproverObj != null && hrApproverObj.length > 0) {
					// hrApproverList = new ArrayList(hrApproverObj.length);
					bean.setHrApproveList(true);
					for (int i = Integer.parseInt(pageIndexHrApproved[0]); i < Integer
							.parseInt(pageIndexHrApproved[1]); i++) {
						ApprovalSettings bean1 = new ApprovalSettings();
						bean1.setHrApproverEmpId(String
								.valueOf(hrApproverObj[i][0]));
						bean1.setHrApproverEmpToken(String
								.valueOf(hrApproverObj[i][1]));
						bean1.setHrApproverEmpName(String
								.valueOf(hrApproverObj[i][2]));
						bean1.setEmailId(String
								.valueOf(hrApproverObj[i][3]));
						hrApproverList.add(bean1);
					}
				}
				bean.setHrApproverList(hrApproverList);
			} catch (Exception e) {
				e.printStackTrace();
			}

		
		return hrApproverList;
	}

	public List showAdminApproverList(ApprovalSettings bean,
			HttpServletRequest request) {
		List adminApproverList = new ArrayList();

		try {
			String showAdminApproverListSql = " SELECT APP_SETTINGS_EMP_ID, EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME"
					+ " FROM HRMS_D1_APPROVAL_SETTINGS "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_APPROVAL_SETTINGS.APP_SETTINGS_EMP_ID) "
					+ " WHERE APP_SETTINGS_TYPE = 'A'  ORDER BY APP_SETTINGS_ID ";
			Object[][] adminApproverObj = getSqlModel().getSingleResult(
					showAdminApproverListSql);

			String[] pageIndexAdminApproved = Utility.doPaging(bean
					.getMyAdminPage(), adminApproverObj.length, 20);
			if (pageIndexAdminApproved == null) {
				pageIndexAdminApproved[0] = "0";
				pageIndexAdminApproved[1] = "20";
				pageIndexAdminApproved[2] = "1";
				pageIndexAdminApproved[3] = "1";
				pageIndexAdminApproved[4] = "";
			}

			request.setAttribute("totalAdminApprovePage", Integer
					.parseInt(String.valueOf(pageIndexAdminApproved[2])));
			request.setAttribute("adminApprovePageNo", Integer.parseInt(String
					.valueOf(pageIndexAdminApproved[3])));
			if (pageIndexAdminApproved[4].equals("1"))
				bean.setMyAdminPage("1");

			if (adminApproverObj != null && adminApproverObj.length > 0) {
				// adminApproverList = new ArrayList(adminApproverObj.length);
				bean.setAdminApproverListPage(true);
				for (int i = Integer.parseInt(pageIndexAdminApproved[0]); i < Integer
						.parseInt(pageIndexAdminApproved[1]); i++) {
					ApprovalSettings bean1 = new ApprovalSettings();
					bean1.setAdminApproverEmpId(String
							.valueOf(adminApproverObj[i][0]));
					bean1.setAdminApproverEmpToken(String
							.valueOf(adminApproverObj[i][1]));
					bean1.setAdminApproverEmpName(String
							.valueOf(adminApproverObj[i][2]));

					adminApproverList.add(bean1);
				}
			}
			bean.setAdminApproverList(adminApproverList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return adminApproverList;
	}

	// added ganesh start

	public List showFinanceApproverList(ApprovalSettings bean,
			HttpServletRequest request) {
		List financeApproverList = new ArrayList();

		try {
			String showFinanceApproverListSql = " SELECT APP_SETTINGS_EMP_ID, EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME"
					+ " FROM HRMS_D1_APPROVAL_SETTINGS "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_APPROVAL_SETTINGS.APP_SETTINGS_EMP_ID) "
					+ " WHERE APP_SETTINGS_TYPE = 'F' ORDER BY APP_SETTINGS_ID ";

			Object[][] financeApproverObj = getSqlModel().getSingleResult(
					showFinanceApproverListSql);

			String[] pageIndexFinanceApproved = Utility.doPaging(bean
					.getMyFinancePage(), financeApproverObj.length, 20);
			if (pageIndexFinanceApproved == null) {
				pageIndexFinanceApproved[0] = "0";
				pageIndexFinanceApproved[1] = "20";
				pageIndexFinanceApproved[2] = "1";
				pageIndexFinanceApproved[3] = "1";
				pageIndexFinanceApproved[4] = "";
			}

			request.setAttribute("totalFinanceApprovePage", Integer
					.parseInt(String.valueOf(pageIndexFinanceApproved[2])));
			request.setAttribute("financeApprovePageNo", Integer
					.parseInt(String.valueOf(pageIndexFinanceApproved[3])));
			if (pageIndexFinanceApproved[4].equals("1"))
				bean.setMyFinancePage("1");

			if (financeApproverObj != null && financeApproverObj.length > 0) {
				// financeApproverList = new
				// ArrayList(financeApproverObj.length);
				bean.setFinanceApproverListPage(true);
				for (int i = Integer.parseInt(pageIndexFinanceApproved[0]); i < Integer
						.parseInt(pageIndexFinanceApproved[1]); i++) {
					ApprovalSettings bean1 = new ApprovalSettings();
					bean1.setFinanceApproverEmpId(String
							.valueOf(financeApproverObj[i][0]));
					bean1.setFinanceApproverEmpToken(String
							.valueOf(financeApproverObj[i][1]));
					bean1.setFinanceApproverEmpName(String
							.valueOf(financeApproverObj[i][2]));

					financeApproverList.add(bean1);
				}
			}
			bean.setFinanceApproverList(financeApproverList);
		} catch (Exception e) {
			logger.error(e);
		}

		return financeApproverList;
	}

	/* For Finance ATR */

	public List showFinanceATRApproverList(ApprovalSettings bean,
			HttpServletRequest request) {
		List financeATRApproverList = new ArrayList();

		try {
			String showFinanceATRApproverListSql = " SELECT APP_SETTINGS_EMP_ID, EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME"
					+ " FROM HRMS_D1_APPROVAL_SETTINGS "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_APPROVAL_SETTINGS.APP_SETTINGS_EMP_ID) "
					+ " WHERE APP_SETTINGS_TYPE = 'R' ORDER BY APP_SETTINGS_ID ";

			Object[][] financeatrApproverObj = getSqlModel().getSingleResult(
					showFinanceATRApproverListSql);

			String[] pageIndexFinanceATRApproved = Utility.doPaging(bean
					.getMyFinancePage(), financeatrApproverObj.length, 20);
			if (pageIndexFinanceATRApproved == null) {
				pageIndexFinanceATRApproved[0] = "0";
				pageIndexFinanceATRApproved[1] = "20";
				pageIndexFinanceATRApproved[2] = "1";
				pageIndexFinanceATRApproved[3] = "1";
				pageIndexFinanceATRApproved[4] = "";
			}

			request.setAttribute("totalFinanceApprovePage", Integer
					.parseInt(String.valueOf(pageIndexFinanceATRApproved[2])));
			request.setAttribute("financeApprovePageNo", Integer
					.parseInt(String.valueOf(pageIndexFinanceATRApproved[3])));
			if (pageIndexFinanceATRApproved[4].equals("1"))
				bean.setMyFinancePage("1");

			if (financeatrApproverObj != null
					&& financeatrApproverObj.length > 0) {
				// financeApproverList = new
				// ArrayList(financeApproverObj.length);
				bean.setFinanceATRApproverListPage(true);
				for (int i = Integer.parseInt(pageIndexFinanceATRApproved[0]); i < Integer
						.parseInt(pageIndexFinanceATRApproved[1]); i++) {
					ApprovalSettings bean1 = new ApprovalSettings();
					bean1.setFinanceATRApproverEmpId(String
							.valueOf(financeatrApproverObj[i][0]));
					bean1.setFinanceATRApproverEmpToken(String
							.valueOf(financeatrApproverObj[i][1]));
					bean1.setFinanceATRApproverEmpName(String
							.valueOf(financeatrApproverObj[i][2]));

					financeATRApproverList.add(bean1);
				}
			}
			bean.setFinanceATRApproverList(financeATRApproverList);
		} catch (Exception e) {
			logger.error(e);
		}

		return financeATRApproverList;
	}

	// for Corporate Procurement
	public List showCorporateProcList(ApprovalSettings bean,
			HttpServletRequest request) {
		List corporateProcGroupList = new ArrayList();

		try {
			String showCorporateProcListSql = " SELECT APP_SETTINGS_EMP_ID, EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME"
					+ " FROM HRMS_D1_APPROVAL_SETTINGS "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_APPROVAL_SETTINGS.APP_SETTINGS_EMP_ID) "
					+ " WHERE APP_SETTINGS_TYPE = 'C' ORDER BY APP_SETTINGS_ID ";

			Object[][] corporateApproverObj = getSqlModel().getSingleResult(
					showCorporateProcListSql);

			String[] pageIndexCorporateApproved = Utility.doPaging(bean
					.getMyFinancePage(), corporateApproverObj.length, 20);
			if (pageIndexCorporateApproved == null) {
				pageIndexCorporateApproved[0] = "0";
				pageIndexCorporateApproved[1] = "20";
				pageIndexCorporateApproved[2] = "1";
				pageIndexCorporateApproved[3] = "1";
				pageIndexCorporateApproved[4] = "";
			}

			request.setAttribute("totalFinanceApprovePage", Integer
					.parseInt(String.valueOf(pageIndexCorporateApproved[2])));
			request.setAttribute("financeApprovePageNo", Integer
					.parseInt(String.valueOf(pageIndexCorporateApproved[3])));
			if (pageIndexCorporateApproved[4].equals("1"))
				bean.setMyFinancePage("1");

			if (corporateApproverObj != null && corporateApproverObj.length > 0) {
				// financeApproverList = new
				// ArrayList(financeApproverObj.length);
				bean.setCorporateApproverListPage(true);
				for (int i = Integer.parseInt(pageIndexCorporateApproved[0]); i < Integer
						.parseInt(pageIndexCorporateApproved[1]); i++) {
					ApprovalSettings bean1 = new ApprovalSettings();
					bean1.setCorporateApproverEmpId(String
							.valueOf(corporateApproverObj[i][0]));
					bean1.setCorporateEmpToken(String
							.valueOf(corporateApproverObj[i][1]));
					bean1.setCorporateEmpName(String
							.valueOf(corporateApproverObj[i][2]));

					corporateProcGroupList.add(bean1);
				}
			}
			bean.setCorporateProcGroupList(corporateProcGroupList);
		} catch (Exception e) {
			logger.error(e);
		}

		return corporateProcGroupList;
	}

	public List showPayrollApproverList(ApprovalSettings bean,
			HttpServletRequest request) {
		List payrollApproverList = new ArrayList();

		try {
			String showPayrollApproverListSql = " SELECT APP_SETTINGS_EMP_ID, EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME"
					+ " FROM HRMS_D1_APPROVAL_SETTINGS "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_APPROVAL_SETTINGS.APP_SETTINGS_EMP_ID) "
					+ " WHERE APP_SETTINGS_TYPE = 'P' ORDER BY APP_SETTINGS_ID ";
			Object[][] payrollApproverObj = getSqlModel().getSingleResult(
					showPayrollApproverListSql);

			String[] pageIndexPayrollApproved = Utility.doPaging(bean
					.getMyPayrollPage(), payrollApproverObj.length, 20);
			if (pageIndexPayrollApproved == null) {
				pageIndexPayrollApproved[0] = "0";
				pageIndexPayrollApproved[1] = "20";
				pageIndexPayrollApproved[2] = "1";
				pageIndexPayrollApproved[3] = "1";
				pageIndexPayrollApproved[4] = "";
			}

			request.setAttribute("totalPayrollApprovePage", Integer
					.parseInt(String.valueOf(pageIndexPayrollApproved[2])));
			request.setAttribute("payrollApprovePageNo", Integer
					.parseInt(String.valueOf(pageIndexPayrollApproved[3])));
			if (pageIndexPayrollApproved[4].equals("1"))
				bean.setMyPayrollPage("1");

			if (payrollApproverObj != null && payrollApproverObj.length > 0) {
				// payrollApproverList = new
				// ArrayList(payrollApproverObj.length);
				bean.setPayrollApproverListPage(true);
				for (int i = Integer.parseInt(pageIndexPayrollApproved[0]); i < Integer
						.parseInt(pageIndexPayrollApproved[1]); i++) {
					ApprovalSettings bean1 = new ApprovalSettings();
					bean1.setPayrollApproverEmpId(String
							.valueOf(payrollApproverObj[i][0]));
					bean1.setPayrollApproverEmpToken(String
							.valueOf(payrollApproverObj[i][1]));
					bean1.setPayrollApproverEmpName(String
							.valueOf(payrollApproverObj[i][2]));

					payrollApproverList.add(bean1);
				}
			}
			bean.setPayrollApproverList(payrollApproverList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return payrollApproverList;
	}

	public List showLogisticsApproverList(ApprovalSettings bean,
			HttpServletRequest request) {
		List logisticsApproverList = new ArrayList();

		try {
			String showLogisticsApproverListSql = " SELECT APP_SETTINGS_EMP_ID, EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME"
					+ " FROM HRMS_D1_APPROVAL_SETTINGS "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_APPROVAL_SETTINGS.APP_SETTINGS_EMP_ID) "
					+ " WHERE APP_SETTINGS_TYPE = 'L' ORDER BY APP_SETTINGS_ID ";

			Object[][] logisticsApproverObj = getSqlModel().getSingleResult(
					showLogisticsApproverListSql);

			String[] pageIndexLogisticsApproved = Utility.doPaging(bean
					.getMyLogisticsPage(), logisticsApproverObj.length, 20);
			if (pageIndexLogisticsApproved == null) {
				pageIndexLogisticsApproved[0] = "0";
				pageIndexLogisticsApproved[1] = "20";
				pageIndexLogisticsApproved[2] = "1";
				pageIndexLogisticsApproved[3] = "1";
				pageIndexLogisticsApproved[4] = "";
			}

			request.setAttribute("totallogisticsApprovePage", Integer
					.parseInt(String.valueOf(pageIndexLogisticsApproved[2])));
			request.setAttribute("logisticsApprovePageNo", Integer
					.parseInt(String.valueOf(pageIndexLogisticsApproved[3])));
			if (pageIndexLogisticsApproved[4].equals("1"))
				bean.setMyLogisticsPage("1");

			if (logisticsApproverObj != null && logisticsApproverObj.length > 0) {
				// logisticsApproverList = new
				// ArrayList(logisticsApproverObj.length);
				bean.setLogisticsApproverListPage(true);
				for (int i = Integer.parseInt(pageIndexLogisticsApproved[0]); i < Integer
						.parseInt(pageIndexLogisticsApproved[1]); i++) {
					ApprovalSettings bean1 = new ApprovalSettings();
					bean1.setLogisticsApproverEmpId(String
							.valueOf(logisticsApproverObj[i][0]));
					bean1.setLogisticsApproverEmpToken(String
							.valueOf(logisticsApproverObj[i][1]));
					bean1.setLogisticsApproverEmpName(String
							.valueOf(logisticsApproverObj[i][2]));

					logisticsApproverList.add(bean1);
				}
			}
			bean.setLogisticsApproverList(logisticsApproverList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return logisticsApproverList;
	}

	public List showItApproverList(ApprovalSettings bean,
			HttpServletRequest request) {
		List itApproverList = new ArrayList();

		try {
			String showItApproverListSql = " SELECT APP_SETTINGS_EMP_ID, EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME"
					+ " FROM HRMS_D1_APPROVAL_SETTINGS "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_APPROVAL_SETTINGS.APP_SETTINGS_EMP_ID) "
					+ " WHERE APP_SETTINGS_TYPE = 'I' ORDER BY APP_SETTINGS_ID ";
			Object[][] itApproverObj = getSqlModel().getSingleResult(
					showItApproverListSql);

			String[] pageIndexItApproved = Utility.doPaging(bean.getMyItPage(),
					itApproverObj.length, 20);
			if (pageIndexItApproved == null) {
				pageIndexItApproved[0] = "0";
				pageIndexItApproved[1] = "20";
				pageIndexItApproved[2] = "1";
				pageIndexItApproved[3] = "1";
				pageIndexItApproved[4] = "";
			}

			request.setAttribute("totalItApprovePage", Integer.parseInt(String
					.valueOf(pageIndexItApproved[2])));
			request.setAttribute("iTApprovePageNo", Integer.parseInt(String
					.valueOf(pageIndexItApproved[3])));
			if (pageIndexItApproved[4].equals("1"))
				bean.setMyItPage("1");

			if (itApproverObj != null && itApproverObj.length > 0) {
				// itApproverList = new ArrayList(itApproverObj.length);
				bean.setItApproverListPage(true);
				for (int i = Integer.parseInt(pageIndexItApproved[0]); i < Integer
						.parseInt(pageIndexItApproved[1]); i++) {
					ApprovalSettings bean1 = new ApprovalSettings();
					bean1.setItApproverEmpId(String
							.valueOf(itApproverObj[i][0]));
					bean1.setItApproverEmpToken(String
							.valueOf(itApproverObj[i][1]));
					bean1.setItApproverEmpName(String
							.valueOf(itApproverObj[i][2]));

					itApproverList.add(bean1);
				}
			}
			bean.setItApproverList(itApproverList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return itApproverList;
	}

	public List showTrainingAuthList(ApprovalSettings bean,
			HttpServletRequest request) {
		List trainingAuthList = new ArrayList();

		try {
			String showTrainingAuthListSql = " SELECT APP_SETTINGS_EMP_ID, EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME"
					+ " FROM HRMS_D1_APPROVAL_SETTINGS "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_APPROVAL_SETTINGS.APP_SETTINGS_EMP_ID) "
					+ " WHERE APP_SETTINGS_TYPE = 'T' ORDER BY APP_SETTINGS_ID ";
			Object[][] itApproverObj = getSqlModel().getSingleResult(
					showTrainingAuthListSql);

			String[] pageIndexTrainingAuthApproved = Utility.doPaging(bean
					.getMyTrainingAuthPage(), itApproverObj.length, 20);
			if (pageIndexTrainingAuthApproved == null) {
				pageIndexTrainingAuthApproved[0] = "0";
				pageIndexTrainingAuthApproved[1] = "20";
				pageIndexTrainingAuthApproved[2] = "1";
				pageIndexTrainingAuthApproved[3] = "1";
				pageIndexTrainingAuthApproved[4] = "";
			}

			request
					.setAttribute("totalTrainingAuthPage", Integer
							.parseInt(String
									.valueOf(pageIndexTrainingAuthApproved[2])));
			request.setAttribute("trainingAuthPageNo", Integer.parseInt(String
					.valueOf(pageIndexTrainingAuthApproved[3])));
			if (pageIndexTrainingAuthApproved[4].equals("1"))
				bean.setMyTrainingAuthPage("1");

			if (itApproverObj != null && itApproverObj.length > 0) {
				// trainingAuthList = new ArrayList(itApproverObj.length);
				bean.setTrainingAuthListPage(true);

				for (int i = Integer.parseInt(pageIndexTrainingAuthApproved[0]); i < Integer
						.parseInt(pageIndexTrainingAuthApproved[1]); i++) {

					ApprovalSettings bean1 = new ApprovalSettings();
					bean1.setTrainingAuthEmpId(String
							.valueOf(itApproverObj[i][0]));
					bean1.setTrainingAuthEmpToken(String
							.valueOf(itApproverObj[i][1]));
					bean1.setTrainingAuthEmpName(String
							.valueOf(itApproverObj[i][2]));

					trainingAuthList.add(bean1);
				}
			}
			bean.setTrainingAuthList(trainingAuthList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return trainingAuthList;
	}

	public List showEducationMinistryList(ApprovalSettings bean,
			HttpServletRequest request) {
		List educationMinistryList = new ArrayList();

		try {
			String showEducationMinistryListSql = " SELECT APP_SETTINGS_EMP_ID, EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME"
					+ " FROM HRMS_D1_APPROVAL_SETTINGS "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_APPROVAL_SETTINGS.APP_SETTINGS_EMP_ID) "
					+ " WHERE APP_SETTINGS_TYPE = 'E' ORDER BY APP_SETTINGS_ID ";
			Object[][] itApproverObj = getSqlModel().getSingleResult(
					showEducationMinistryListSql);

			String[] pageIndexEducationMinistryApproved = Utility.doPaging(bean
					.getMyEducationMinistryPage(), itApproverObj.length, 20);
			if (pageIndexEducationMinistryApproved == null) {
				pageIndexEducationMinistryApproved[0] = "0";
				pageIndexEducationMinistryApproved[1] = "20";
				pageIndexEducationMinistryApproved[2] = "1";
				pageIndexEducationMinistryApproved[3] = "1";
				pageIndexEducationMinistryApproved[4] = "";
			}

			request.setAttribute("totalEducationMinistryPage", Integer
					.parseInt(String
							.valueOf(pageIndexEducationMinistryApproved[2])));
			request.setAttribute("educationMinistryPageNo", Integer
					.parseInt(String
							.valueOf(pageIndexEducationMinistryApproved[3])));
			if (pageIndexEducationMinistryApproved[4].equals("1"))
				bean.setMyEducationMinistryPage("1");

			if (itApproverObj != null && itApproverObj.length > 0) {
				// trainingAuthList = new ArrayList(itApproverObj.length);
				bean.setEducationMinistryListPage(true);

				for (int i = Integer
						.parseInt(pageIndexEducationMinistryApproved[0]); i < Integer
						.parseInt(pageIndexEducationMinistryApproved[1]); i++) {

					ApprovalSettings bean1 = new ApprovalSettings();
					bean1.setEducationMinistryEmpId(String
							.valueOf(itApproverObj[i][0]));
					bean1.setEducationMinistryEmpToken(String
							.valueOf(itApproverObj[i][1]));
					bean1.setEducationMinistryEmpName(String
							.valueOf(itApproverObj[i][2]));

					educationMinistryList.add(bean1);
				}
			}
			bean.setEducationMinistryList(educationMinistryList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return educationMinistryList;
	}

	
	//World Travel Start 
	public List showWorldTravelList(ApprovalSettings bean,
			HttpServletRequest request) {
		
		System.out.println("showWorldTravelList method=============================");
		List worldTravelList = new ArrayList();

		try {
			String showWorldTravelListSql = " SELECT APP_SETTINGS_ID,APP_EMAIL_ID  FROM HRMS_D1_APPROVAL_SETTINGS "
					+ " WHERE APP_SETTINGS_TYPE = 'W' ORDER BY APP_SETTINGS_ID ";
			Object[][] wtaddObj = getSqlModel().getSingleResult(
					showWorldTravelListSql);

			String[] pageIndexWorldTravelAdd = Utility.doPaging(bean
					.getMyWorldTravelPage(), wtaddObj.length, 20);
			if (pageIndexWorldTravelAdd == null) {
				pageIndexWorldTravelAdd[0] = "0";
				pageIndexWorldTravelAdd[1] = "20";
				pageIndexWorldTravelAdd[2] = "1";
				pageIndexWorldTravelAdd[3] = "1";
				pageIndexWorldTravelAdd[4] = "";
			}

			request.setAttribute("totalWorldTravelPage", Integer
					.parseInt(String
							.valueOf(pageIndexWorldTravelAdd[2])));
			request.setAttribute("worldTravelPageNo", Integer
					.parseInt(String
							.valueOf(pageIndexWorldTravelAdd[3])));
			if (pageIndexWorldTravelAdd[4].equals("1"))
				bean.setMyWorldTravelPage("1");

			if (wtaddObj != null && wtaddObj.length > 0) {
				
				bean.setWorldTravelListPage(true);

				for (int i = Integer
						.parseInt(pageIndexWorldTravelAdd[0]); i < Integer
						.parseInt(pageIndexWorldTravelAdd[1]); i++) {

					ApprovalSettings bean1 = new ApprovalSettings();
					bean1.setTravelId(String
							.valueOf(wtaddObj[i][0]));
					bean1.setEmailId(String
							.valueOf(wtaddObj[i][1]));

					worldTravelList.add(bean1);
				}
			}
			bean.setWorldTravelList(worldTravelList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return worldTravelList;
	}
//world travel end
	
	
	
	
	
	
	
	
	// added ganesh end

	// mater methods start here//

	/** Generating the list Onload */
	public void initialData(ApprovalSettings bean, HttpServletRequest request) {

		Object[][] regData = getSqlModel().getSingleResult(getQuery(3));

		if (regData != null && regData.length > 0) {
			bean.setModeLength("true");

			bean.setTotalNoOfRecords(String.valueOf(regData.length));

			String[] pageIndex = Utility.doPaging(bean.getMyPage1(),
					regData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";

			}

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				bean.setMyPage1("1");
			ArrayList<Object> List = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {

				ApprovalSettings bean1 = new ApprovalSettings();
				bean1.setDivisionCode(checkNull(String.valueOf(regData[i][0])));
				bean1.setApprovalDivision(checkNull(String
						.valueOf(regData[i][1])));

				List.add(bean1);
			}// end of loop
			System.out.println("in main page---" + List.size());
			bean.setDivisionList(List);
		}

		else {

			bean.setDivisionList(null);

		}
	}

	// Check Null Function//

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	/** For inserting record into DB */
	public boolean insert(ApprovalSettings bean) {
		if (!checkDuplicateAdd(bean)) {

			Object addObj[][] = new Object[1][2];

			String query1 = "SELECT NVL(MAX(APP_SETTINGS_ID),0)+1 FROM  HRMS_D1_APPROVAL_SETTINGS";
			Object[][] divisionId = getSqlModel().getSingleResult(query1);

			addObj[0][0] = checkNull(String.valueOf(divisionId[0][0]));
			addObj[0][1] = bean.getDivId().trim();

			for (int i = 0; i < addObj.length; i++) {
				for (int j = 0; j < addObj[i].length; j++) {
					System.out.println("insertObj[" + i + "][" + j + "] "
							+ addObj[i][j]);
				}
			}

			bean.setDivisionCode(String.valueOf(divisionId[0][0]));

			return getSqlModel().singleExecute(getQuery(1), addObj);
		}

		else {

			return false;
		}

	}

	/* for checking duplicate entry of record during insertion */

	public boolean checkDuplicateAdd(ApprovalSettings bean) {
		boolean result = false;
		String query = "SELECT APP_DIVISION_CODE FROM HRMS_D1_APPROVAL_SETTINGS WHERE UPPER(APP_DIVISION_CODE) LIKE '"
				+ bean.getDivId().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}

	/** Modifing the record */
	public boolean update(ApprovalSettings bean) {
		Object updateObj[][] = new Object[1][2];

		updateObj[0][0] = bean.getDivId().trim();
		updateObj[0][1] = bean.getDivisionCode().trim();
		return getSqlModel().singleExecute(getQuery(2), updateObj);

	}

	/**
	 * for selecting the data from list and setting those data in respective
	 * fields
	 */
	public void editData(ApprovalSettings bean) {

		try {
			System.out
					.println("bean.getDivisionCode() here we get in editData is-------------"
							+ bean.getDivisionCode());
			String query = " SELECT DIV_ID,HRMS_DIVISION.DIV_NAME FROM HRMS_DIVISION "
					+ "  WHERE DIV_ID= " + bean.getDivisionCode();

			Object[][] data = getSqlModel().getSingleResult(query);

			bean.setDivId(String.valueOf(data[0][0]));
			bean.setApprovalDivision(String.valueOf(data[0][1]));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean deleteCheckedRecords(ApprovalSettings bean, String[] code) {
		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {

					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					result = getSqlModel().singleExecute(getQuery(4), delete);
					if (!result)
						count++;

				}// end of if
			}// end of for loop
		}// end of if
		if (count != 0) {
			result = false;
			return result;
		}// end of if
		else {
			result = true;
			return result;
		}// end of else
	}

	public boolean delete(ApprovalSettings bean, HttpServletRequest request) {
		boolean result = false;
		System.out
				.println("bean.getDivisionCode() in delete method call ======"
						+ bean.getDivisionCode());
		String deleteId = bean.getDivisionCode();

		String delQuery = "DELETE FROM HRMS_DIVISION WHERE DIV_ID=" + deleteId;
		result = getSqlModel().singleExecute(delQuery);
		return result;
	}

	public void getRecord(ApprovalSettings bean) {

		System.out
				.println("here ingetRecord-------------------------------------------- ");

		Object[][] data = getSqlModel().getSingleResult(getQuery(5));
		bean.setDivId(checkNull(String.valueOf(data[0][0])));

	}

	public List configureOptionType(ApprovalSettings bean, String configureType, HttpServletRequest request) {
		List trainingAuthList = new ArrayList();
		System.out.println("@@@@@@@@@@@@@@@ ");
		try {
			String showTrainingAuthListSql = " SELECT APP_SETTINGS_ID,APP_SETTINGS_EMP_ID, EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME,DECODE(APP_ISACTION_EMP,'Y','Yes','N','No')"
					+ " FROM HRMS_D1_APPROVAL_SETTINGS "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_APPROVAL_SETTINGS.APP_SETTINGS_EMP_ID) "
					+ " WHERE APP_SETTINGS_TYPE = '"+ configureType + "' ORDER BY APP_SETTINGS_ID ";
			Object[][] itApproverObj = getSqlModel().getSingleResult(
					showTrainingAuthListSql);

			String[] pageIndexTrainingAuthApproved = Utility.doPaging(bean
					.getMyConfigureOptionPage(), itApproverObj.length, 20);
			if (pageIndexTrainingAuthApproved == null) {
				pageIndexTrainingAuthApproved[0] = "0";
				pageIndexTrainingAuthApproved[1] = "20";
				pageIndexTrainingAuthApproved[2] = "1";
				pageIndexTrainingAuthApproved[3] = "1";
				pageIndexTrainingAuthApproved[4] = "";
			}

			request
					.setAttribute("totalConfigureOptionPage", Integer
							.parseInt(String
									.valueOf(pageIndexTrainingAuthApproved[2])));
			request.setAttribute("configureOptionPageNo", Integer.parseInt(String
					.valueOf(pageIndexTrainingAuthApproved[3])));
			if (pageIndexTrainingAuthApproved[4].equals("1"))
				bean.setMyConfigureOptionPage("1");

			if (itApproverObj != null && itApproverObj.length > 0) {
				// trainingAuthList = new ArrayList(itApproverObj.length);
				bean.setConfigureOptionListPage(true);

				for (int i = Integer.parseInt(pageIndexTrainingAuthApproved[0]); i < Integer
						.parseInt(pageIndexTrainingAuthApproved[1]); i++) {

					ApprovalSettings bean1 = new ApprovalSettings();
					bean1.setConfigAuthHiddenId(checkNull(String
							.valueOf(itApproverObj[i][0])));
					bean1.setSearchConfigEmpAuthId(checkNull(String
							.valueOf(itApproverObj[i][1])));
					bean1.setConfigEmpToken(checkNull(String
							.valueOf(itApproverObj[i][2])));
					bean1.setConfigEmpName(checkNull(String
							.valueOf(itApproverObj[i][3])));
					bean1.setIsActive(checkNull(String
							.valueOf(itApproverObj[i][4])));
					trainingAuthList.add(bean1);
				}
			}
			bean.setTrainingAuthList(trainingAuthList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return trainingAuthList;
	}

	public boolean addConfigEmail(ApprovalSettings bean) {
		boolean result = false;
		System.out.println("Status : "+bean.getConfigureGroupType());
		try {
			System.out.println("bean.getConfigEmailId()====" + bean.getConfigEmailId());
		Object addObj[][] = new Object[1][2];

		
		addObj[0][0] = bean.getConfigEmail();
		addObj[0][1] = bean.getConfigureGroupType();
		
		String insertQuery = "INSERT INTO HRMS_D1_APPROVAL_SETTINGS"
			+ "(APP_SETTINGS_ID,APP_EMAIL_ID,APP_SETTINGS_TYPE) "
			+ " VALUES((SELECT NVL(MAX(APP_SETTINGS_ID),0)+1 FROM HRMS_D1_APPROVAL_SETTINGS),?,?)";

		result = getSqlModel().singleExecute(insertQuery, addObj);
		
		if (result) {
			String autoCodeQuery = " SELECT NVL(MAX(APP_SETTINGS_ID),0)+1 FROM  HRMS_D1_APPROVAL_SETTINGS ";
			Object[][] data = getSqlModel().getSingleResult(
					autoCodeQuery);
			if (data != null && data.length > 0) {
				bean.setConfigEmailId(String.valueOf(data[0][0]));
			}
		}
		
		for(int i = 0; i < addObj.length; i++) {
			for(int j = 0; j < addObj[i].length; j++) {
				logger.info("addObj[" + i + "][" + j + "]  " + addObj[i][j]);
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;	
		
	}

	public List showConfigEmailList(ApprovalSettings bean,
			String configureType, HttpServletRequest request) {
		System.out.println("showWorldTravelList method=============================");
		List worldTravelList = new ArrayList();

		try {
			String showWorldTravelListSql = " SELECT APP_SETTINGS_ID,APP_EMAIL_ID  FROM HRMS_D1_APPROVAL_SETTINGS "
					+ " WHERE APP_SETTINGS_TYPE = '"+ configureType + "' AND APP_EMAIL_ID IS NOT NULL ORDER BY APP_SETTINGS_ID ";
			Object[][] wtaddObj = getSqlModel().getSingleResult(
					showWorldTravelListSql);

			String[] pageIndexWorldTravelAdd = Utility.doPaging(bean
					.getMyWorldTravelPage(), wtaddObj.length, 20);
			if (pageIndexWorldTravelAdd == null) {
				pageIndexWorldTravelAdd[0] = "0";
				pageIndexWorldTravelAdd[1] = "20";
				pageIndexWorldTravelAdd[2] = "1";
				pageIndexWorldTravelAdd[3] = "1";
				pageIndexWorldTravelAdd[4] = "";
			}

			request.setAttribute("totalWorldTravelPage", Integer
					.parseInt(String
							.valueOf(pageIndexWorldTravelAdd[2])));
			request.setAttribute("worldTravelPageNo", Integer
					.parseInt(String
							.valueOf(pageIndexWorldTravelAdd[3])));
			if (pageIndexWorldTravelAdd[4].equals("1"))
				bean.setMyWorldTravelPage("1");

			if (wtaddObj != null && wtaddObj.length > 0) {
				
				bean.setWorldTravelListPage(true);

				for (int i = Integer
						.parseInt(pageIndexWorldTravelAdd[0]); i < Integer
						.parseInt(pageIndexWorldTravelAdd[1]); i++) {

					ApprovalSettings bean1 = new ApprovalSettings();
					bean1.setConfigEmailId(checkNull(String
							.valueOf(wtaddObj[i][0])));
					bean1.setConfigEmail(checkNull(String
							.valueOf(wtaddObj[i][1])));

					worldTravelList.add(bean1);
				}
			}
			bean.setWorldTravelList(worldTravelList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return worldTravelList;
	}

	public boolean addConfigAuth(ApprovalSettings bean) {
		boolean result = false;
		
		String message = "HR Approver cannot be added!";
		System.out.println("Status : "+bean.getConfigureGroupType());
		try {
			System.out.println("bean.getConfigEmailId()====" + bean.getConfigEmailId());
		Object addObj[][] = new Object[1][3];

		
		addObj[0][0] = bean.getSearchConfigAuthId();
		//addObj[0][1] = bean.getIsActive();
		
		if (bean.getConfigReceivedFlag().equals("true"))// IF ACTION Checked
			addObj[0][1] = "Y";
		else
			addObj[0][1] = "N";
		
		addObj[0][2] = bean.getConfigureGroupType();
		
		String insertQuery = "INSERT INTO HRMS_D1_APPROVAL_SETTINGS"
			+ "(APP_SETTINGS_ID,APP_SETTINGS_EMP_ID,APP_ISACTION_EMP,APP_SETTINGS_TYPE) "
			+ " VALUES((SELECT NVL(MAX(APP_SETTINGS_ID),0)+1 FROM HRMS_D1_APPROVAL_SETTINGS),?,?,?)";

		result = getSqlModel().singleExecute(insertQuery, addObj);
		
		if (result) {
			String type = "";

			if (bean.getConfigureGroupType().equals("H")) {
				type = "HR";
			} else if (bean.getConfigureGroupType().equals("A")) {
				type = "Admin";
			} else if (bean.getConfigureGroupType().equals("F")) {
				type = "Finance";
			} 
			else if (bean.getConfigureGroupType().equals("R")) {
				type = "FinanceATR";
			}
			else if (bean.getConfigureGroupType().equals("P")) {
				type = "Payroll";
			} else if (bean.getConfigureGroupType().equals("L")) {
				type = "Logistics";
			}
			 else if (bean.getConfigureGroupType().equals("C")) {
					type = "Corporate";
				}
			else if (bean.getConfigureGroupType().equals("I")) {
				type = "IT";
			} else if (bean.getConfigureGroupType().equals("T")) {
				type = "Training Authority";
			} else if (bean.getConfigureGroupType().equals("E")) {
				type = "Education Ministry";
			}

			message = type + " Approver added Successfully!";
		}
		
		
		if (result) {
			String autoCodeQuery = " SELECT NVL(MAX(APP_SETTINGS_ID),0)+1 FROM  HRMS_D1_APPROVAL_SETTINGS ";
			Object[][] data = getSqlModel().getSingleResult(
					autoCodeQuery);
			if (data != null && data.length > 0) {
				bean.setConfigEmailId(String.valueOf(data[0][0]));
			}
		}
		
		for(int i = 0; i < addObj.length; i++) {
			for(int j = 0; j < addObj[i].length; j++) {
				logger.info("addObj[" + i + "][" + j + "]  " + addObj[i][j]);
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;	
	}

	public void getConfigRecord(ApprovalSettings bean,
			String requestID) {
		try {
		String editQuery = "select APP_SETTINGS_ID,APP_SETTINGS_EMP_ID,EMP_TOKEN, " +
				" EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME AS EMP_NAME,"
					+ " APP_ISACTION_EMP,APP_SETTINGS_TYPE "
					+ "  FROM HRMS_D1_APPROVAL_SETTINGS  "
					+ "  INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_APPROVAL_SETTINGS.APP_SETTINGS_EMP_ID)"
					+ "  WHERE APP_SETTINGS_ID="+requestID;
		
		Object[][] editObj = getSqlModel().getSingleResult(editQuery);
			if(editObj!=null && editObj.length>0)
			{
				bean.setConfigAuthId(checkNull(String.valueOf(editObj[0][0])));
				bean.setSearchConfigAuthId(checkNull(String.valueOf(editObj[0][1])));
				bean.setSearchConfigAuthToken(checkNull(String.valueOf(editObj[0][2])));
				bean.setSearchConfigAuthName(checkNull(String.valueOf(editObj[0][3])));
				//bean.setConfigReceivedFlag(checkNull(String.valueOf(editObj[0][4])));
				if (String.valueOf(editObj[0][4]).equals("Y")) {
					bean.setConfigReceivedFlag("true");
				}
				bean.setConfigureGroupType(checkNull(String.valueOf(editObj[0][5])));
				//reqbean.setCourseId(checkNull(String.valueOf(editObj[0][5])));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public boolean updateRecords(ApprovalSettings bean,
			HttpServletRequest request) {
		boolean result = false;
		System.out.println("Status : "+bean.getConfigureGroupType());
		try {
			System.out.println("bean.getConfigEmailId()====" + bean.getConfigEmailId());
		Object updateObj[][] = new Object[1][3];

		
		updateObj[0][0] = bean.getSearchConfigAuthId();
		//addObj[0][1] = bean.getIsActive();
		
		if (bean.getConfigReceivedFlag().equals("true"))// IF ACTION Checked
			updateObj[0][1] = "Y";
		else
			updateObj[0][1] = "N";
		
		updateObj[0][2] = bean.getConfigureGroupType();
		
		String insertQuery = "UPDATE HRMS_D1_APPROVAL_SETTINGS SET "
			+ " APP_SETTINGS_EMP_ID = ? ,APP_ISACTION_EMP = ? ,APP_SETTINGS_TYPE = ? WHERE APP_SETTINGS_ID = " + bean.getConfigAuthId() ;
			

		result = getSqlModel().singleExecute(insertQuery, updateObj);
		
		
		for(int i = 0; i < updateObj.length; i++) {
			for(int j = 0; j < updateObj[i].length; j++) {
				logger.info("updateObj[" + i + "][" + j + "]  " + updateObj[i][j]);
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;	
	}

	public boolean delAddRecord(ApprovalSettings bean, 	String requestID, HttpServletRequest request) {
		Object del[][] = null;
		try {
			del = new Object[1][1];
			// to delete the single record after clicking on saving or searching
			// button
			del[0][0] = requestID;
		} catch(Exception e) {
			// TODO: handle exception
		}
		String deleteQuery = "DELETE FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_ID=? ";

		return getSqlModel().singleExecute(deleteQuery, del);
	}

	public void getEmailRecord(ApprovalSettings bean, String requestID) {
		try {
			String editQuery = "select APP_SETTINGS_ID,APP_EMAIL_ID ,APP_SETTINGS_TYPE" 
						+ "  FROM HRMS_D1_APPROVAL_SETTINGS  "
						+ "  WHERE APP_SETTINGS_ID="+requestID;
			
			Object[][] editObj = getSqlModel().getSingleResult(editQuery);
				if(editObj!=null && editObj.length>0)
				{
					bean.setConfigHiddenEmailId(checkNull(String.valueOf(editObj[0][0])));
					bean.setConfigEmail(checkNull(String.valueOf(editObj[0][1])));
					bean.setConfigureGroupType(checkNull(String.valueOf(editObj[0][2])));
					//reqbean.setCourseId(checkNull(String.valueOf(editObj[0][5])));
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}

	public boolean updateEmailRecords(ApprovalSettings bean,
			HttpServletRequest request) {
		boolean result = false;
		System.out.println("Status : "+bean.getConfigureGroupType());
		try {
			System.out.println("bean.getConfigHiddenEmailId()====" + bean.getConfigHiddenEmailId());
		Object updateObj[][] = new Object[1][2];

		
		updateObj[0][0] = bean.getConfigEmail();
		//addObj[0][1] = bean.getIsActive();
		
		
		updateObj[0][1] = bean.getConfigureGroupType();
		
		String insertQuery = "UPDATE HRMS_D1_APPROVAL_SETTINGS SET "
			+ " APP_EMAIL_ID = ? ,APP_SETTINGS_TYPE = ? WHERE APP_SETTINGS_ID = " + bean.getConfigHiddenEmailId() ;
			

		result = getSqlModel().singleExecute(insertQuery, updateObj);
		
		
		for(int i = 0; i < updateObj.length; i++) {
			for(int j = 0; j < updateObj[i].length; j++) {
				logger.info("updateObj[" + i + "][" + j + "]  " + updateObj[i][j]);
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;	
	}

	public boolean delEmailRecord(ApprovalSettings bean, String requestID,
			HttpServletRequest request) {
		Object del[][] = null;
		try {
			del = new Object[1][1];
			// to delete the single record after clicking on saving or searching
			// button
			del[0][0] = requestID;
		} catch(Exception e) {
			// TODO: handle exception
		}
		String deleteQuery = "DELETE FROM HRMS_D1_APPROVAL_SETTINGS WHERE APP_SETTINGS_ID=? ";

		return getSqlModel().singleExecute(deleteQuery, del);
	}
	
	
}