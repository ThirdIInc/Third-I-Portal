/**
 * 
 */
package org.paradyne.model.ApplicationStudio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.paradyne.lib.Utility;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.paradyne.bean.ApplicationStudio.AutoMailAlertSettings;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author Ganesh
 * 
 */
public class AutoMailAlertSettingsModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(BirthdayMailSettingsModel.class);

	public String saveSettings(AutoMailAlertSettings autoMailSettings) {
		// TODO Auto-generated method stub

		String finalresult = "";

		boolean result = false;

		if (!checkDuplicate(autoMailSettings)) {
			logger
					.info("branch flag======="
							+ autoMailSettings.getIndBrnFlag());

			Object[][] addObj = new Object[1][14];

			if (autoMailSettings.getAutoFlag().equals("true")) {
				addObj[0][0] = "Y";
			} else
				addObj[0][0] = "N";

			if (autoMailSettings.getIndDptFlag().equals("true")) {
				addObj[0][1] = "Y";
			} else
				addObj[0][1] = "N";

			if (autoMailSettings.getAllDptFlag().equals("true")) {
				addObj[0][2] = "Y";
			} else
				addObj[0][2] = "N";

			if (autoMailSettings.getIndBrnFlag().equals("true")) {
				addObj[0][3] = "Y";
			} else
				addObj[0][3] = "N";

			if (autoMailSettings.getAllBrnFlag().equals("true")) {
				addObj[0][4] = "Y";
			} else
				addObj[0][4] = "N";

			if (autoMailSettings.getIndDesgFlag().equals("true")) {
				addObj[0][5] = "Y";
			} else
				addObj[0][5] = "N";

			if (autoMailSettings.getAllDesgFlag().equals("true")) {
				addObj[0][6] = "Y";
			} else
				addObj[0][6] = "N";

			if (autoMailSettings.getIndDivFlag().equals("true")) {
				addObj[0][7] = "Y";
			} else
				addObj[0][7] = "N";

			if (autoMailSettings.getAllDivFlag().equals("true")) {
				addObj[0][8] = "Y";
			} else
				addObj[0][8] = "N";

			addObj[0][9] = autoMailSettings.getTempCode();
			addObj[0][10] = "S";
			if (!autoMailSettings.getHiddRandomCode().equals("")) {
				String str = autoMailSettings.getHiddRandomCode();
				addObj[0][9] = str;
				addObj[0][10] = "M";
			} else {
				addObj[0][9] = autoMailSettings.getTempCode();
				addObj[0][10] = "S";
			}

			System.out.println("autoMailSettings.getindEmpFlag()  :"
					+ autoMailSettings.getAllEmpFlag());
			if (autoMailSettings.getAllEmpFlag().equals("true")) {
				addObj[0][11] = "Y";
			} else
				addObj[0][11] = "N";

			if (autoMailSettings.getReportingToFlag().equals("true")) {
				addObj[0][12] = "Y";
			} else
				addObj[0][12] = "N";
			addObj[0][13] = autoMailSettings.getAutoMailName();

			/*
			 * String delQuery = " DELETE FROM HRMS_AUTO_MAIL_ALERT "; result =
			 * getSqlModel().singleExecute(delQuery);
			 */

			String addQuery = " INSERT INTO HRMS_AUTO_MAIL_ALERT (AUTO_CODE, AUTO_SEND, INDIV_DEPT, ALL_DEPT, INDIV_BRANCH, "
					+ " ALL_BRANCH, INDIV_DESG, ALL_DESG, INDIV_DIV, ALL_DIV,TEMPLATE_ID,TEMP_FLAG,INDIV_EMP, REPORTINGTO_FLAG, AUTO_MAIL_NAME) VALUES ((SELECT NVL(MAX(AUTO_CODE),0)+1 FROM  HRMS_AUTO_MAIL_ALERT),?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			result = getSqlModel().singleExecute(addQuery, addObj);

			if (result) {
				finalresult = "saved";
			}
		} else {
			finalresult = "duplicate";
		}

		return finalresult;
	}

	/**
	 * to Show list page
	 * 
	 * @param
	 * @return
	 */
	public void displaySettings(AutoMailAlertSettings autoMailSettings,
			HttpServletRequest request) {
		// TODO Auto-generated method stub

		String query = " SELECT AUTO_CODE,AUTO_MAIL_NAME,DECODE(AUTO_SEND,'Y','Yes','N','No') FROM HRMS_AUTO_MAIL_ALERT  ORDER BY AUTO_CODE";
		Object[][] repData = getSqlModel().getSingleResult(query);
		if (repData != null && repData.length > 0) {
			autoMailSettings.setTotalRecords(String.valueOf(repData.length)); // to
																				// display
																				// the
																				// total
																				// records
																				// inn
																				// the
																				// list
			autoMailSettings.setModeLength("true");
			String[] pageIndex = Utility.doPaging(autoMailSettings.getMyPage(),
					repData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2]))); // to display the total number
												// of page
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3]))); // to display the page number
			if (pageIndex[4].equals("1"))
				autoMailSettings.setMyPage("1");
			ArrayList<Object> List = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {

				AutoMailAlertSettings bean = new AutoMailAlertSettings();
				bean.setTempCode(String.valueOf(repData[i][0]));
				bean.setTempName(String.valueOf(repData[i][1]));
				bean.setAutoFlag(String.valueOf(repData[i][2]));

				List.add(bean); // add the details in the list
			}// end of loop
			autoMailSettings.setRandItt(List);
		}
	}

	/**
	 * to check the null value
	 * 
	 * @param result
	 * @return
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	// for checking duplicate entry of record during insertion

	public boolean checkDuplicate(AutoMailAlertSettings bean) {
		boolean result = false;

		try {
			String query = "SELECT * FROM HRMS_AUTO_MAIL_ALERT WHERE UPPER(AUTO_MAIL_NAME) LIKE '"
					+ bean.getAutoMailName().trim().toUpperCase() + "'";
			if (!bean.getAutoCode().equals("")) {
				query += " AND AUTO_CODE NOT IN(" + bean.getAutoCode() + ")";
			}
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				result = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;

	}

	// for checking duplicate entry of record during modification

	public boolean checkDuplicateMod(AutoMailAlertSettings bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_AUTO_MAIL_ALERT WHERE UPPER(AUTO_CODE) LIKE '"
				+ bean.getAutoMailName().trim().toUpperCase()
				+ "' AND AUTO_CODE not in(" + bean.getAutoCode() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	/* for selecting the data from list */
	public void calforedit(AutoMailAlertSettings autoMailSettings,
			String autoCode) {

		String query = " SELECT AUTO_CODE,AUTO_MAIL_NAME FROM HRMS_AUTO_MAIL_ALERT where AUTO_CODE= "
				+ autoCode;

		// to get the record in double clck for update
		Object[][] data = getSqlModel().getSingleResult(query);
		autoMailSettings.setAutoCode(String.valueOf(data[0][0])); // AUTO CODE
		autoMailSettings.setAutoMailName(String.valueOf(data[0][1])); // AUTO
																		// MAIL
																		// NAME

	}

	/* to generating the report */
	public void getReport(AutoMailAlertSettings cntrMaster2,
			HttpServletRequest request, HttpServletResponse response,
			ServletContext context, HttpSession session, String[] label) {
		// TODO Auto-generated method stub

		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);

		String reportName = "\n\n Auto mail Alert \n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);
		rg.setFName("Auto Mail Alert Master.Pdf");
		String query = " SELECT AUTO_MAIL_NAME,DECODE(AUTO_SEND,'Y','Yes','N','No'),DECODE(REPORTINGTO_FLAG,'Y','Yes','N','No') FROM HRMS_AUTO_MAIL_ALERT  ORDER BY AUTO_CODE";
		Object[][] data = getSqlModel().getSingleResult(query);
		Object[][] Data = new Object[data.length][4];
		if (data != null && data.length > 0) {
			int j = 1;
			for (int i = 0; i < data.length; i++) {
				Data[i][0] = j;
				Data[i][1] = data[i][0];
				Data[i][2] = data[i][1];
				Data[i][3] = data[i][2];
				j++;
			}
			int cellwidth[] = { 25, 25, 25, 25 };
			int alignment[] = { 1, 0, 0, 0 };
			rg.addFormatedText(reportName, 6, 0, 1, 0);
			rg.addText("\n", 0, 0, 0);
			rg.addTextBold("Date :" + toDay, 0, 2, 0);
			rg.addText("\n", 0, 0, 0);
			rg.tableBody(label, Data, cellwidth, alignment);
		} else {
			rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
		}

		rg.createReport(response);

	}

	/**
	 * to delete the single record
	 * 
	 * @param cntrMaster
	 * @return
	 */
	public boolean calfordelete(AutoMailAlertSettings autoMailSettings) {

		String query = "";
		Object[][] delete = null;
		try {

			logger
					.info("AUto Mail  Alert into	 delete<----------------checkkkkkkkkkkkkkkkkkkkkkkk----------------------->"
							+ query);
			delete = new Object[1][1];
			delete[0][0] = autoMailSettings.getTempCode(); // to get id for
															// delete
			query = " DELETE FROM  HRMS_AUTO_MAIL_ALERT WHERE AUTO_CODE=? ";

		} catch (Exception e) {
			// TODO: handle exception
		}
		// the single record
		return getSqlModel().singleExecute(query, delete);
		// TODO Auto-generated method stub

	}

	// for deleting one or more records from list
	public boolean deleteAutoAlert(AutoMailAlertSettings autoMailSettings,
			String[] code) {
		boolean result = false;
		boolean flag = false;
		String query = "";

		int cnt = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {
					logger
							.info("AUto Mail  Alert into	 delete<----------------checkkkkkkkkkkkkkkkkkkkkkkk----------------------->"
									+ query);
					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					query = " DELETE FROM  HRMS_AUTO_MAIL_ALERT WHERE AUTO_CODE=? ";
					flag = getSqlModel().singleExecute(query, delete);
					if (!flag) {
						cnt++;
					}// end of if
					// result=true;
				}// end of nested if
			}// end of loop
		}// end of nested if
		if (cnt > 0) {
			result = false;
		}// end of if
		else
			result = true;
		return result;

	}

	/**
	 * to Update the single record
	 * 
	 * @param autoMailSettings
	 * @return
	 */

	public String updateSettings(AutoMailAlertSettings autoMailSettings) {
		// TODO Auto-generated method stub
		String finalresult = "";

		boolean result = false;

		if (!checkDuplicate(autoMailSettings)) {
			logger
					.info("branch flag======="
							+ autoMailSettings.getIndBrnFlag());

			Object[][] addObj = new Object[1][15];

			if (autoMailSettings.getAutoFlag().equals("true")) {
				addObj[0][0] = "Y";
			} else
				addObj[0][0] = "N";

			if (autoMailSettings.getIndDptFlag().equals("true")) {
				addObj[0][1] = "Y";
			} else
				addObj[0][1] = "N";

			if (autoMailSettings.getAllDptFlag().equals("true")) {
				addObj[0][2] = "Y";
			} else
				addObj[0][2] = "N";

			if (autoMailSettings.getIndBrnFlag().equals("true")) {
				addObj[0][3] = "Y";
			} else
				addObj[0][3] = "N";

			if (autoMailSettings.getAllBrnFlag().equals("true")) {
				addObj[0][4] = "Y";
			} else
				addObj[0][4] = "N";

			if (autoMailSettings.getIndDesgFlag().equals("true")) {
				addObj[0][5] = "Y";
			} else
				addObj[0][5] = "N";

			if (autoMailSettings.getAllDesgFlag().equals("true")) {
				addObj[0][6] = "Y";
			} else
				addObj[0][6] = "N";

			if (autoMailSettings.getIndDivFlag().equals("true")) {
				addObj[0][7] = "Y";
			} else
				addObj[0][7] = "N";

			if (autoMailSettings.getAllDivFlag().equals("true")) {
				addObj[0][8] = "Y";
			} else
				addObj[0][8] = "N";

			addObj[0][9] = autoMailSettings.getTempCode();
			addObj[0][10] = "S";
			if (!autoMailSettings.getHiddRandomCode().equals("")) {
				String str = autoMailSettings.getHiddRandomCode();
				addObj[0][9] = str;
				addObj[0][10] = "M";
			} else {
				addObj[0][9] = autoMailSettings.getTempCode();
				addObj[0][10] = "S";
			}

			System.out.println("autoMailSettings.getindEmpFlag()  :"
					+ autoMailSettings.getAllEmpFlag());
			if (autoMailSettings.getAllEmpFlag().equals("true")) {
				addObj[0][11] = "Y";
			} else
				addObj[0][11] = "N";

			if (autoMailSettings.getReportingToFlag().equals("true")) {
				addObj[0][12] = "Y";
			} else
				addObj[0][12] = "N";
			addObj[0][13] = autoMailSettings.getAutoMailName();

			addObj[0][14] = autoMailSettings.getAutoCode();

			String updateQuery = " UPDATE HRMS_AUTO_MAIL_ALERT SET AUTO_SEND=?,INDIV_DEPT=?,ALL_DEPT=?,INDIV_BRANCH=?,"

					+ " ALL_BRANCH=?,INDIV_DESG=?,ALL_DESG=?,INDIV_DIV=?, ALL_DIV=?,TEMPLATE_ID=?,TEMP_FLAG=?,INDIV_EMP=?, REPORTINGTO_FLAG =?, AUTO_MAIL_NAME =? "
					+ " WHERE AUTO_CODE=? ";

			result = getSqlModel().singleExecute(updateQuery, addObj);

			if (result) {
				finalresult = "update";
			}
		} else {
			finalresult = "duplicate";
		}

		return finalresult;
	}

}
