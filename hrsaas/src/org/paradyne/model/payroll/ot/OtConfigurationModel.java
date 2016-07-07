package org.paradyne.model.payroll.ot;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.LMS.WelfareInformationBean;
import org.paradyne.bean.admin.master.LWFConfigurationMaster;
import org.paradyne.bean.payroll.bonus.BonusAllowance;
import org.paradyne.bean.payroll.ot.OtConfiguration;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.report.ReportGenerator;
import org.struts.action.DataMigration.MigratePayrollExcelData;

/**
 * Created on 21th Feb 2012.
 * 
 * @author aa1385
 * 
 */
public class OtConfigurationModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(OtConfigurationModel.class);

	/**
	 * Method : checkForNullValue. Purpose : This method is used to check for
	 * null decimal values. Checks for the null value and if it finds it to be
	 * null then replaces it with 0.
	 * 
	 * @param result :-
	 *            Input String to be checked
	 * @return : - returns the checked string
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	/**
	 * Method:saveConfigurationSettings. * Purpose : This method is used to save
	 * OT Configuration Settings.
	 * 
	 * @param bean
	 *            :OtConfiguration
	 * @param request
	 *            :HttpServletRequest
	 * @return : True or False
	 */
	public boolean saveConfigurationSettings(OtConfiguration bean,
			HttpServletRequest request) {

		boolean result = false;
		try {
			int otConfigId = 0;
			/* Get Max for OT CONFIGURATION CODE */
			String sQuery = "SELECT NVL(MAX(OT_CONF_CODE),0) + 1 FROM HRMS_OT_CONF";
			Object[][] objData = getSqlModel().getSingleResult(sQuery);

			if (objData != null) {
				otConfigId = Integer.parseInt(String.valueOf(objData[0][0]));
				bean.setOtConfigID(String.valueOf(otConfigId));

			}

			Object[][] otConfigSettingDetails = new Object[1][9];

			otConfigSettingDetails[0][0] = otConfigId; /* OT Configuration Id */
			otConfigSettingDetails[0][1] = bean.getDivisionID().trim();
			if (bean.getActualHoursWorkedShiftHoursFlag().equals("true"))
				otConfigSettingDetails[0][2] = "Y";
			else
				otConfigSettingDetails[0][2] = "N";

			if (bean.getActualOutTimeShiftOutTimeFlag().equals("true"))
				otConfigSettingDetails[0][3] = "Y";
			else
				otConfigSettingDetails[0][3] = "N";

			otConfigSettingDetails[0][4] = bean.getOtHoursRoundOffOptions();
			otConfigSettingDetails[0][5] = bean.getCalOtHourlyRateFormula();
			otConfigSettingDetails[0][6] = bean
					.getCalWeeklyOtHourlyRateFormula();
			
			if (bean.getDoubleOtFlag().equals("true"))
				otConfigSettingDetails[0][7] = "Y";
			else
				otConfigSettingDetails[0][7] = "N";
			
			otConfigSettingDetails[0][8] = bean
			.getCalDoubleOtHourlyRateFormula();

			for (int i = 0; i < otConfigSettingDetails.length; i++) {
				for (int j = 0; j < otConfigSettingDetails[i].length; j++) {
					logger.info("otConfigSettingDetails[" + i + "][" + j
							+ "]  " + otConfigSettingDetails[i][j]);
				}
			}

			String Query = "INSERT INTO HRMS_OT_CONF ( OT_CONF_CODE,DIV_CODE, HRS_WORK_FLAG, "
					+ "OT_TIME_FLAG, OT_HRS_ROUND, REGULAR_OT_FORMULA, WEEKLY_OT_FORMULA,DOUBLE_OT_FLAG,DOUBLE_OT_FORMULA)"
					+ " VALUES ( ?,?,?,?,?,?,?,?,?) ";
			result = getSqlModel().singleExecute(Query, otConfigSettingDetails);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * Method : showConfigList. * Purpose : This method is used to show OT
	 * Configuration List.
	 * 
	 * @param bean :
	 *            OtConfiguration
	 * @param request :
	 *            HttpServletRequest
	 */
	public void showConfigList(OtConfiguration bean, HttpServletRequest request) {
		try {
			String Query = " SELECT OT_CONF_CODE,DIV_CODE,DIV_NAME FROM HRMS_OT_CONF "
					+ " INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_OT_CONF.DIV_CODE) "
					+ " ORDER BY OT_CONF_CODE DESC ";

			Object[][] data = getSqlModel().getSingleResult(Query);

			if (data != null && data.length > 0) {
				bean.setModeLength("true");
				bean.setTotalRecords(String.valueOf(data.length));
				String[] pageIndex = Utility.doPaging(bean.getMyPage(),
						data.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}
				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("pageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1"))
					bean.setMyPage("1");
				ArrayList<Object> List = new ArrayList<Object>();
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					OtConfiguration bean1 = new OtConfiguration();
					bean1.setHiddenOtConfigId(checkNull(String
							.valueOf(data[i][0])));
					bean1.setDivisionID(checkNull(String.valueOf(data[i][1])));
					bean1
							.setDivisionName(checkNull(String
									.valueOf(data[i][2])));

					List.add(bean1);
				}// end of loop
				bean.setIteratorlist(List);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method : editConfigSetting. * Purpose : This method is used to Edit OT
	 * Configuration ID.
	 * 
	 * @param bean :
	 *            OtConfiguration
	 * @param request :
	 *            HttpServletRequest
	 * @param requestID :
	 *            Perticular Code
	 */
	public void editConfigSetting(OtConfiguration bean,
			HttpServletRequest request, String requestID) {
		try {
			String Query = " SELECT  OT_CONF_CODE,DIV_CODE,DIV_NAME, HRS_WORK_FLAG,OT_TIME_FLAG, OT_HRS_ROUND, "
					+ " REGULAR_OT_FORMULA, WEEKLY_OT_FORMULA ,DOUBLE_OT_FORMULA,DOUBLE_OT_FLAG FROM HRMS_OT_CONF "
					+ " INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_OT_CONF.DIV_CODE) "
					+ " WHERE OT_CONF_CODE = " + requestID;

			Object[][] data = getSqlModel().getSingleResult(Query);
			if (data != null && data.length > 0) {
				bean.setOtConfigID(String.valueOf(data[0][0]));
				bean.setDivisionID(String.valueOf(data[0][1]));
				bean.setDivisionName(String.valueOf(data[0][2]));
				if (String.valueOf(data[0][3]).equals("Y")) {
					bean.setActualHoursWorkedShiftHoursFlag("true");
				}
				if (String.valueOf(data[0][4]).equals("Y")) {
					bean.setActualOutTimeShiftOutTimeFlag("true");
				}
				bean.setOtHoursRoundOffOptions(String.valueOf(data[0][5]));
				bean.setCalOtHourlyRateFormula(checkNull(String
						.valueOf(data[0][6])));
				bean.setCalWeeklyOtHourlyRateFormula(checkNull(String
						.valueOf(data[0][7])));
				bean.setCalDoubleOtHourlyRateFormula(checkNull(String
						.valueOf(data[0][8])));
				if (String.valueOf(data[0][9]).equals("Y")) {
					bean.setDoubleOtFlag("true");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method : updateConfigurationSettings. * Purpose : This method is used to
	 * Update Perticular OT Configuration ID.
	 * 
	 * @param bean :
	 *            OtConfiguration
	 * @param request :
	 *            HttpServletRequest
	 * @return : result
	 */
	public boolean updateConfigurationSettings(OtConfiguration bean,
			HttpServletRequest request) {
		try {
			/* Update data from 'HRMS_LWF_SLAB_HDR' */
			boolean result = false;
			String sQueryHeader = " UPDATE HRMS_OT_CONF SET DIV_CODE = ?, HRS_WORK_FLAG = ?, "
					+ " OT_TIME_FLAG = ?, OT_HRS_ROUND = ?, "
					+ " REGULAR_OT_FORMULA = ?, WEEKLY_OT_FORMULA  = ? ,DOUBLE_OT_FORMULA = ? WHERE OT_CONF_CODE = ? ";
			Object[][] headerData = new Object[1][9];
			headerData[0][0] = bean.getDivisionID(); /* Division id */

			if (bean.getActualHoursWorkedShiftHoursFlag().equals("true")) {
				headerData[0][1] = "Y";
			} else {
				headerData[0][1] = "N";
			}
			if (bean.getActualOutTimeShiftOutTimeFlag().equals("true")) {
				headerData[0][2] = "Y";
			} else {
				headerData[0][2] = "N";
			}
			headerData[0][3] = bean.getOtHoursRoundOffOptions(); /*
																	 * OT Hours
																	 * Round Off
																	 * Options
																	 */
			headerData[0][4] = bean.getCalOtHourlyRateFormula(); /*
																	 * OT Hourly
																	 * Rate
																	 * formula
																	 */
			headerData[0][5] = bean.getCalWeeklyOtHourlyRateFormula(); /*
																		 * Weekly
																		 * OT
																		 * Hourly
																		 * Rate
																		 * formula
																		 */
			
			if (bean.getDoubleOtFlag().equals("true")) {
				headerData[0][6] = "Y";
			} else {
				headerData[0][6] = "N";
			}
			
			
			
			headerData[0][7] = bean.getCalDoubleOtHourlyRateFormula(); /* Double OT Hourly Rate	  formula */
			headerData[0][8] = bean.getOtConfigID(); /* OtConfigID */

			result = getSqlModel().singleExecute(sQueryHeader, headerData);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Method : deletecheckedHDRRecords. * Purpose : This method is used to
	 * Delete Selected OT Configuration ID from list page.
	 * 
	 * @param bean :
	 *            OtConfiguration
	 * @param code :
	 *            Selected ID From List Page
	 * @return: result
	 */
	public String deletecheckedHDRRecords(OtConfiguration bean, String[] code) {
		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {
				if (!code[i].equals("")) {
					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];

					String deleteQuery = " DELETE FROM HRMS_OT_CONF WHERE "
							+ " OT_CONF_CODE IN (" + delete[0][0] + ")";
					// + " AND TAX_EMP_TYPE IN ('M','F','S') ";

					result = getSqlModel().singleExecute(deleteQuery);

					// result = getSqlModel().singleExecute(deleteQuery,
					// delete);
					if (!result) {
						count++;
					}// end of if
				}// end of nested if
			}// end of loop

		}// end of nested if

		if (count != 0)
			return "false";
		else
			return "true";

	}

	/**
	 * Method : deleteSelectedRecords. * Purpose : This method is used to Delete
	 * OT Configuration ID when select.
	 * 
	 * @param bean :
	 *            OtConfiguration
	 * @return
	 */
	public boolean deleteSelectedRecords(OtConfiguration bean) {

		boolean result = false;
		String deleteQuery;
		try {

			deleteQuery = " DELETE FROM HRMS_OT_CONF WHERE "
					+ " OT_CONF_CODE= " + bean.getOtConfigID();

			result = getSqlModel().singleExecute(deleteQuery);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

}
