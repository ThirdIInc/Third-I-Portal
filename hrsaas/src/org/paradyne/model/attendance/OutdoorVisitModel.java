/**
 * @author saipavan 29-05-2008
 */

package org.paradyne.model.attendance;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.attendance.OutdoorVisit;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

/*
 * outdoor visit model for all business logic methods
 */

public class OutdoorVisitModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(OutdoorVisitModel.class);
	OutdoorVisit outdoor = new OutdoorVisit();

	/**
	 * @param outdoor
	 * @return a String after setting the purpose field.
	 */
	public void applicationDtl(OutdoorVisit bean) {
		try {
			String query = " SELECT OUTDOORVISIT_PURPOSE, OUTDOORVISIT_STATUS FROM HRMS_OUTDOORVISIT WHERE OUTDOORVISIT_CODE= "
					+ bean.getOutvCode();
			Object Appdtl[][] = getSqlModel().getSingleResult(query);
			if (Appdtl != null && Appdtl.length > 0) {
				bean.setPurpose(checkNull(String.valueOf(Appdtl[0][0])));
				bean.setStatus(String.valueOf(Appdtl[0][1]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean checkDuplicateApp(OutdoorVisit outdoor) {
		boolean result = false;
		try {
			long frm_Time = Long.parseLong(outdoor.getFrTime().replace(":", ""));
			long to_Time = Long.parseLong(outdoor.getToTime().replace(":", ""));
			String duplicateQuery = " SELECT OUTDOORVISIT_CODE,OUTDOORVISIT_ECODE, "
					+ " TO_NUMBER(SUBSTR(OUTDOORVISIT_FRTIME,0,2)||''||SUBSTR(OUTDOORVISIT_FRTIME,4,6)) AS FTIME, "
					+ " TO_NUMBER(SUBSTR(OUTDOORVISIT_TOTIME,0,2)||''||SUBSTR(OUTDOORVISIT_TOTIME,4,6)) AS TTIME "
					+ ",TO_CHAR(OUTDOORVISIT_DATE,'DD-MM-YYYY') AS TM FROM HRMS_OUTDOORVISIT "
					+ " WHERE TO_CHAR(OUTDOORVISIT_DATE,'DD-MM-YYYY')='"
					+ outdoor.getOutDate()
					+ "' AND OUTDOORVISIT_ECODE="
					+ outdoor.getEcode()
					+ " AND ((TO_NUMBER(SUBSTR(OUTDOORVISIT_FRTIME,0,2)||''||SUBSTR(OUTDOORVISIT_FRTIME,4,6))<= "
					+ frm_Time
					+ " AND "
					+ " TO_NUMBER(SUBSTR(OUTDOORVISIT_TOTIME,0,2)||''||SUBSTR(OUTDOORVISIT_TOTIME,4,6))>="
					+ frm_Time
					+ ")"
					+ " OR (TO_NUMBER(SUBSTR(OUTDOORVISIT_FRTIME,0,2)||''||SUBSTR(OUTDOORVISIT_FRTIME,4,6))<="
					+ to_Time
					+ " AND "
					+ " TO_NUMBER(SUBSTR(OUTDOORVISIT_TOTIME,0,2)||''||SUBSTR(OUTDOORVISIT_TOTIME,4,6))>="
					+ to_Time + ")) ";
			Object duplicateObject[][] = getSqlModel().getSingleResult(duplicateQuery);
			if (duplicateObject != null && duplicateObject.length > 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean checkDuplicateAtupdate(OutdoorVisit outdoor) {
		try {
			long frm_Time = Long
					.parseLong(outdoor.getFrTime().replace(":", ""));
			long to_Time = Long.parseLong(outdoor.getToTime().replace(":", ""));
			String query = "    SELECT OUTDOORVISIT_CODE,OUTDOORVISIT_ECODE, "
					+ " TO_NUMBER(SUBSTR(OUTDOORVISIT_FRTIME,0,2)||''||SUBSTR(OUTDOORVISIT_FRTIME,4,6)) AS FTIME, "
					+ " TO_NUMBER(SUBSTR(OUTDOORVISIT_TOTIME,0,2)||''||SUBSTR(OUTDOORVISIT_TOTIME,4,6)) AS TTIME "
					+ ",TO_CHAR(OUTDOORVISIT_DATE,'DD-MM-YYYY') AS TM FROM HRMS_OUTDOORVISIT "
					+ " WHERE TO_CHAR(OUTDOORVISIT_DATE,'DD-MM-YYYY')='"
					+ outdoor.getOutDate()
					+ "' AND OUTDOORVISIT_ECODE="
					+ outdoor.getEcode()
					+ " AND OUTDOORVISIT_CODE !="
					+ outdoor.getOutvCode()
					+ " AND ((TO_NUMBER(SUBSTR(OUTDOORVISIT_FRTIME,0,2)||''||SUBSTR(OUTDOORVISIT_FRTIME,4,6))<= "
					+ frm_Time
					+ " AND "
					+ " TO_NUMBER(SUBSTR(OUTDOORVISIT_TOTIME,0,2)||''||SUBSTR(OUTDOORVISIT_TOTIME,4,6))>="
					+ frm_Time
					+ ")"
					+ " OR (TO_NUMBER(SUBSTR(OUTDOORVISIT_FRTIME,0,2)||''||SUBSTR(OUTDOORVISIT_FRTIME,4,6))<="
					+ to_Time
					+ " AND "
					+ " TO_NUMBER(SUBSTR(OUTDOORVISIT_TOTIME,0,2)||''||SUBSTR(OUTDOORVISIT_TOTIME,4,6))>="
					+ to_Time + ")) ";
			Object Appdtl[][] = getSqlModel().getSingleResult(query);

			if (Appdtl != null && Appdtl.length > 0) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public String checkNull(String result) {
		if (result == null || result.equals("") || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	/*
	 * Method for deleting the applications
	 */

	public boolean delete(OutdoorVisit bean) {
		Object addObj[][] = new Object[1][1];
		addObj[0][0] = bean.getOutvCode();

		String empquery = " SELECT OUTDOORVISIT_ECODE, OUTDOORVISIT_APPR FROM HRMS_OUTDOORVISIT WHERE OUTDOORVISIT_CODE = "
				+ bean.getOutvCode();

		Object obj[][] = getSqlModel().getSingleResult(empquery);

		if (String.valueOf(addObj[0][0]) != null) {
			getSqlModel().singleExecute(getQuery(7), addObj);
		}

		boolean result = getSqlModel().singleExecute(getQuery(6), addObj);
		if (result) {
			try {
				Object[][] to_mailIds = new Object[1][1];
				Object[][] from_mailIds = new Object[1][1];

				to_mailIds[0][0] = String.valueOf(obj[0][1]);
				from_mailIds[0][0] = String.valueOf(obj[0][0]);

				MailUtility mail = new MailUtility();
				mail.initiate(context, session);
				mail.sendMail(to_mailIds, from_mailIds, "Outdoor", "", "D");

				mail.terminate();
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return result;
	}

	/*
	 * method for setting General Employee details
	 */
	public void getEmployeeDetails(String userEmpId, OutdoorVisit bean) {
		// query for login employee id & employee name
		String query = " SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) EMP_NAME FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ) "
				+ " WHERE EMP_ID = " + userEmpId;
		Object[][] empdata = getSqlModel().getSingleResult(query);

		bean.setEToken(String.valueOf(empdata[0][0])); // employee id setting
		bean.setEmpName(String.valueOf(empdata[0][1])); // employee name setting
		bean.setEcode(userEmpId);
	}

	public void getRecord(OutdoorVisit bean) {
		Object[] param = new Object[1];
		param[0] = bean.getOutvCode();
		Object applData[][] = getSqlModel().getSingleResult(getQuery(4), param);

		if (String.valueOf(applData[0][3]).equals("1")) { // checking the Application level to be equal to one.
			bean.setStatus(String.valueOf(applData[0][2])); // setting status of application.
			bean.setHiddenStatus(String.valueOf(applData[0][2])); // setting status of application.
		} else {
			bean.setStatus(String.valueOf(applData[0][2])); // setting status of application.
			bean.setHiddenStatus(String.valueOf(applData[0][2])); // setting status of application.
		}
		bean.setOutLoc(String.valueOf(applData[0][3])); // setting location in the application.
	}

	/*
	 * method for Generating Pdf report.
	 */
	public void getReport(HttpServletRequest request,
			HttpServletResponse response, OutdoorVisit bean) {
		String s = "\nOUTDOOR VISIT REPORT\n\n\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				s);

		String query = " SELECT DECODE(OUTDOORVISIT_STATUS, 'D', 'Draft', 'P', 'Pending', 'A', 'Approved', 'R', 'Rejected', 'B', 'Sent Back'), OUTDOOR_APP_LEVEL "
				+ " FROM HRMS_OUTDOORVISIT WHERE OUTDOORVISIT_CODE = "
				+ bean.getOutvCode();
		Object[][] status = getSqlModel().getSingleResult(query);

		String stat = String.valueOf(status[0][0]);
		if (String.valueOf(status[0][0]).equals("Pending")
				&& !String.valueOf(status[0][1]).equals("1")) {
			stat = "Forwarded";
		}

		// query for getting approval details.
		String approver = " SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) EMP_NAME, TO_CHAR(OUTDOOR_PATH_DATE,'DD-MM-YYYY'), "
				+ " DECODE(OUTDOOR_PATH_STATUS, 'P', 'Pending', 'A', 'Approved', 'R', 'Rejected', 'B','Sent Back') FROM HRMS_OUTDOOR_PATH "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_OUTDOOR_PATH.OUTDOOR_PATH_APPRCODE = HRMS_EMP_OFFC.EMP_ID) "
				+ " WHERE OUTDOOR_PATH_OUTCODE = "
				+ bean.getOutvCode()
				+ " UNION "
				+ " SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) EMP_NAME, TO_CHAR(OUTDOORVISIT_DATE,'DD-MM-YYYY'), "
				+ " DECODE(OUTDOORVISIT_STATUS, 'P', 'Pending', 'A', 'Approved', 'R', 'Rejected','B','Sent Back') FROM  HRMS_OUTDOORVISIT "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_OUTDOORVISIT.OUTDOORVISIT_APPR = HRMS_EMP_OFFC.EMP_ID) "
				+ " WHERE OUTDOORVISIT_STATUS = 'P'AND OUTDOORVISIT_CODE = "
				+ bean.getOutvCode();
		Object[][] approverData = getSqlModel().getSingleResult(approver);

		Object[][] approvalTable = new Object[approverData.length][6];
		for (int i = 0; i < approverData.length; i++) {
			approvalTable[i][0] = String.valueOf(i + 1);
			approvalTable[i][1] = checkNull(String.valueOf(approverData[i][0])); // employee
			// token
			// no
			approvalTable[i][2] = checkNull(String.valueOf(approverData[i][1])); // employee
			// name
			approvalTable[i][3] = checkNull(String.valueOf(approverData[i][2])); // date
			approvalTable[i][4] = checkNull(String.valueOf(approverData[i][3])); // Application
			// status
			approvalTable[i][5] = " ";
		}

		Object data[][] = new Object[5][5];
		data[0][0] = "Employee ID ";
		data[0][1] = ": " + bean.getEToken();

		data[0][2] = "Employee Name ";
		data[0][3] = ": " + bean.getEmpName();

		data[2][0] = "Date ";
		data[2][1] = ": " + bean.getOutDate();
		data[3][0] = "Start Time ";
		data[3][1] = ": " + bean.getFrTime();
		data[3][2] = "End Time ";
		data[3][3] = ": " + bean.getToTime();

		data[4][0] = "Location ";
		data[4][1] = ": " + bean.getOutLoc();
		data[4][2] = "Purpose ";
		data[4][3] = ": " + bean.getPurpose();

		int[] bcellWidth = { 20, 30, 20, 30 };
		int[] bcellAlign = { 0, 0, 0, 0 };
		rg.addFormatedText(s, 6, 0, 1, 0);

		rg.tableBodyNoBorder(data, bcellWidth, bcellAlign);

		String appCol[] = { "Sr no", "Approver Id", "Approver Name", "Date",
				"Status", "Signature" };
		int appCell[] = { 5, 10, 30, 10, 15, 10 };
		int apprAlign[] = { 1, 1, 0, 1, 0, 0 };

		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("Approver Details :", 6, 0, 0, 0);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);

		rg.tableBody(appCol, approvalTable, appCell, apprAlign);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("", 1, 0, 2, 3);
		rg.addFormatedText("__________________", 1, 0, 0, 3);
		rg.addFormatedText("", 1, 0, 0, 3);
		rg.addFormatedText("Employee Signature", 1, 0, 0, 3);

		rg.createReport(response);
	}

	/**
	 * @param outdoor
	 * @param empFlow
	 * @param request
	 * @return a String after inserting new record.
	 */
	
	public void setApplication(OutdoorVisit bean, String outCode) {
		Object[] outCodeObj = new Object[1];
		outCodeObj[0] = outCode;
		Object[][] result = getSqlModel().getSingleResult(getQuery(3),
				outCodeObj);

		bean.setOutvCode(String.valueOf(result[0][0]));
		bean.setEmpAppr(String.valueOf(result[0][1]));
		bean.setEmpName(String.valueOf(result[0][2]));
		bean.setOutDate(String.valueOf(result[0][3]));
		bean.setEToken(String.valueOf(result[0][4]));
		bean.setFrTime(String.valueOf(result[0][5]));
		bean.setToTime(String.valueOf(result[0][6]));
		bean.setOutLoc(String.valueOf(result[0][7]));
		bean.setPurpose(String.valueOf(result[0][8]));
		bean.setStatus(String.valueOf(result[0][9]));
		bean.setApplicationLevel(String.valueOf(result[0][10]));
		bean.setEcode(String.valueOf(result[0][11]));
	}

	// Method for setting approver for the employee who is feeling form
	public void setApplicationApproverData(OutdoorVisit bean, Object[][] empFlow) {
		try {
			if (empFlow != null && empFlow.length > 0) {
				Object[][] approverDataObj = new Object[empFlow.length][1];
				for (int i = 0; i < empFlow.length; i++) {

					String selectQuery = "  SELECT HRMS_EMP_OFFC.EMP_TOKEN ||'-'||' '||' '||' '|| "
							+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,' '||'('||HRMS_RANK.RANK_NAME||')' "
							+ "  FROM HRMS_EMP_OFFC "
							+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
							+ " WHERE EMP_ID IN(" + empFlow[i][0] + ")";

					Object temObj[][] = getSqlModel().getSingleResult(
							selectQuery);
					approverDataObj[i][0] = String.valueOf(temObj[0][0]);
				}

				if (approverDataObj != null && approverDataObj.length > 0) {
					ArrayList arrList = new ArrayList();
					for (int i = 0; i < approverDataObj.length; i++) {
						OutdoorVisit innerBean = new OutdoorVisit();
						innerBean.setApplicationApproverName(String
								.valueOf(approverDataObj[i][0]));
						String srNo = i + 1 + getOrdinalFor(i + 1)
								+ "-Approver";
						innerBean.setSrNoIterator(srNo);
						arrList.add(innerBean);
					}
					bean.setApplicationApproverIteratorList(arrList);
				}
			}
		} catch (Exception e) {
			logger.error("Exception in setApproverData------" + e);
		}
	}

	/**
	 * @Method getOrdinalFor
	 * @Purpose to append the ordinal with serial number
	 * @param value
	 *            value to which ordinal will be appended
	 * @return String
	 */
	public String getOrdinalFor(int value) {
		int hundredRemainder = value % 100;

		if (hundredRemainder >= 10 && hundredRemainder <= 20) {
			return "th";
		} // end of if

		int tenRemainder = value % 10;

		switch (tenRemainder) {
		case 1:
			return "st";
		case 2:
			return "nd";
		case 3:
			return "rd";
		default:
			return "th";
		} // end of switch
	}

	public void setApproverComments(OutdoorVisit bean) {
		String query = " SELECT  EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) EMP_NAME, TO_CHAR(OUTDOOR_PATH_DATE, 'DD-MM-YYYY'), "
				+ " OUTDOOR_PATH_PURPOSE FROM HRMS_OUTDOOR_PATH "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_OUTDOOR_PATH.OUTDOOR_PATH_APPRCODE = HRMS_EMP_OFFC.EMP_ID) "
				+ " WHERE OUTDOOR_PATH_OUTCODE = "
				+ bean.getOutvCode()
				+ " ORDER BY OUTDOOR_PATH_DATE ";
		Object apprData[][] = getSqlModel().getSingleResult(query);

		ArrayList<Object> apprList = new ArrayList<Object>();
		try {
			if (apprData != null && apprData.length != 0) { // approval list
				// bean.setIsApprove("true");
				bean.setApproverCommentsListFlag(true);
				for (int i = 0; i < apprData.length; i++) {
					OutdoorVisit bean1 = new OutdoorVisit();
					bean1.setApprName(String.valueOf(apprData[i][1]));
					bean1.setApprDate(String.valueOf(apprData[i][2]));
					if (String.valueOf(apprData[i][3]).equals("null")
							|| String.valueOf(apprData[i][3]) == null) {
						bean1.setApprComments("");
					} else {
						bean1.setApprComments(String.valueOf(apprData[i][3]));
					}
					apprList.add(bean1);
				} // end of for loop
			} // end of if statement
			bean.setApproveList(apprList);
		} catch (Exception e) {
			logger.error(e);
		} // end of try-catch block
	}

	/**
	 * @param outdoor
	 * @param empFlow
	 * @param request
	 * @return a String after modifying the already existing record.
	 */
	public void addInformList(OutdoorVisit outDoorBean,
			String[] keepInformCode, String[] keepInform, String chk) {
		try {
			outDoorBean.setInformList(null);
			ArrayList arrList = new ArrayList();
			int count = 0;
			if(!outDoorBean.getKeepHidden().equals("")){
				count = Integer.parseInt(outDoorBean.getKeepHidden());
			}
			outDoorBean.setKeepHidden("");
			if (keepInform != null && keepInform.length > 0) {
				if (chk.equals("remove")) {
					for (int i = 0; i < keepInform.length; i++) {
						OutdoorVisit innerBean = new OutdoorVisit();
						if (i != (count)) {
							innerBean.setKeepInformCode(keepInformCode[i]);
							innerBean.setKeepInform(keepInform[i]);
							arrList.add(innerBean);
						}
					}
				}else {
					for (int i = 0; i < keepInform.length; i++) {
						OutdoorVisit innerBean = new OutdoorVisit();
						innerBean.setKeepInformCode(keepInformCode[i]);
						innerBean.setKeepInform(keepInform[i]);
						arrList.add(innerBean);
					}
				}
			}
			if (chk.equals("add")) {
				OutdoorVisit innerBean = new OutdoorVisit();
				innerBean.setKeepInformCode(outDoorBean.getInformCode());
				innerBean.setKeepInform(outDoorBean.getInformName());
				arrList.add(innerBean);
			}
			outDoorBean.setInformList(arrList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setKeepInform(OutdoorVisit bean) {
		try {
			String query = "SELECT NVL(APPL_KEEP_INFO,'0') FROM  HRMS_OUTDOORVISIT WHERE OUTDOORVISIT_CODE="
					+ bean.getOutvCode();
			Object[][] hdrData = getSqlModel().getSingleResult(query);
			bean.setInformList(null);
			ArrayList arrList = new ArrayList();
			if (hdrData != null && hdrData.length > 0) {
				String info = String.valueOf(hdrData[0][0]);
				if (!info.equals("0")) {
					String emp = "SELECT EMP_ID,EMP_FNAME ||' ' || EMP_MNAME ||' '||EMP_LNAME FROM HRMS_EMP_OFFC WHERE EMP_ID IN("
							+ info + ")";
					Object[][] empInfoData = getSqlModel().getSingleResult(emp);

					if (empInfoData != null && empInfoData.length > 0) {
						for (int i = 0; i < empInfoData.length; i++) {
							OutdoorVisit leaveBean = new OutdoorVisit();
							leaveBean.setKeepInformCode(String
									.valueOf(empInfoData[i][0]));
							leaveBean.setKeepInform(String
									.valueOf(empInfoData[i][1]));
							arrList.add(leaveBean);
						}
						bean.setInformList(arrList);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setKeepInform(OutdoorVisit bean, String code) {
		String query = "SELECT NVL(APPL_KEEP_INFO,'0') FROM  HRMS_OUTDOORVISIT WHERE OUTDOORVISIT_CODE="
				+ code;

		Object[][] hdrData = getSqlModel().getSingleResult(query);
		bean.setInformList(null);
		if (hdrData != null && hdrData.length > 0) {
			String info = String.valueOf(hdrData[0][0]);
			if (!info.equals("0")) {
				String emp = "SELECT EMP_ID,EMP_FNAME ||' ' || EMP_MNAME ||' '||EMP_LNAME FROM HRMS_EMP_OFFC WHERE EMP_ID IN("
						+ info + ")";
				Object[][] empInfoData = getSqlModel().getSingleResult(emp);
				ArrayList arrList = new ArrayList();
				if (empInfoData != null && empInfoData.length > 0) {
					for (int i = 0; i < empInfoData.length; i++) {
						OutdoorVisit leaveBean = new OutdoorVisit();
						leaveBean.setKeepInformCode(String
								.valueOf(empInfoData[i][0]));
						leaveBean.setKeepInform(String
								.valueOf(empInfoData[i][1]));
						arrList.add(leaveBean);
					}
					bean.setInformList(arrList);
				}
			}

		}
	}

	public void getPendingList(OutdoorVisit outdoor, HttpServletRequest request) {
		try {
			ArrayList draftList = new ArrayList();
			String query = "SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' ||EMP_LNAME) NAME, "
					+ " TO_CHAR(OUTDOORVISIT_DATE,'DD-MM-YYYY'), OUTDOORVISIT_CODE, OUTDOORVISIT_STATUS "
					+ " FROM HRMS_OUTDOORVISIT "
					+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_OUTDOORVISIT.OUTDOORVISIT_ECODE) "
					+ " WHERE OUTDOORVISIT_STATUS='D' AND OUTDOORVISIT_ECODE = "
					+ outdoor.getUserEmpId()
					+ " ORDER BY OUTDOORVISIT_DATE DESC";
			Object[][] draftListData = getSqlModel().getSingleResult(query);

			String[] pageIndexDrafted = Utility.doPaging(outdoor.getMyPage(),
					draftListData.length, 20);
			if (pageIndexDrafted == null) {
				pageIndexDrafted[0] = "0";
				pageIndexDrafted[1] = "20";
				pageIndexDrafted[2] = "1";
				pageIndexDrafted[3] = "1";
				pageIndexDrafted[4] = "";
			}

			request.setAttribute("totalDraftPage", Integer.parseInt(String
					.valueOf(pageIndexDrafted[2])));
			request.setAttribute("draftPageNo", Integer.parseInt(String
					.valueOf(pageIndexDrafted[3])));
			if (pageIndexDrafted[4].equals("1"))
				outdoor.setMyPage("1");

			if (draftListData != null && draftListData.length > 0) {
				outdoor.setDraftListLength(true);
				for (int i = Integer.parseInt(pageIndexDrafted[0]); i < Integer
						.parseInt(pageIndexDrafted[1]); i++) {
					OutdoorVisit beanItt = new OutdoorVisit();
					beanItt.setEmployeeNumberIterator(checkNull(String
							.valueOf(draftListData[i][0])));
					beanItt.setEmployeeNameIterator(checkNull(String
							.valueOf(draftListData[i][1])));
					beanItt.setApplicationDateIterator(checkNull(String
							.valueOf(draftListData[i][2])));
					beanItt.setVisitHiddenID(checkNull(String
							.valueOf(draftListData[i][3])));
					beanItt.setVisitHiddenStatus(checkNull(String
							.valueOf(draftListData[i][4])));
					draftList.add(beanItt);
				}
				outdoor.setDraftIteratorList(draftList);
			}
			// Drafted List Ends

			// For in-Process application Begins
			Object[][] inProcessListData = null;
			ArrayList inProcessVoucherList = new ArrayList();

			String inProcessQuery = "SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) NAME, "
					+ " TO_CHAR(OUTDOORVISIT_DATE,'DD-MM-YYYY'), OUTDOORVISIT_CODE, OUTDOORVISIT_STATUS "
					+ " FROM HRMS_OUTDOORVISIT "
					+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_OUTDOORVISIT.OUTDOORVISIT_ECODE) "
					+ " WHERE OUTDOORVISIT_STATUS IN ('P','F') AND OUTDOORVISIT_ECODE = "
					+ outdoor.getUserEmpId()
					+ " ORDER BY OUTDOORVISIT_DATE DESC";
			inProcessListData = getSqlModel().getSingleResult(inProcessQuery);

			String[] pageIndexInProcess = Utility.doPaging(outdoor
					.getMyPageInProcess(), inProcessListData.length, 20);
			if (pageIndexInProcess == null) {
				pageIndexInProcess[0] = "0";
				pageIndexInProcess[1] = "20";
				pageIndexInProcess[2] = "1";
				pageIndexInProcess[3] = "1";
				pageIndexInProcess[4] = "";
			}

			request.setAttribute("totalInProcessPage", Integer.parseInt(String
					.valueOf(pageIndexInProcess[2])));
			request.setAttribute("inProcessPageNo", Integer.parseInt(String
					.valueOf(pageIndexInProcess[3])));
			if (pageIndexInProcess[4].equals("1"))
				outdoor.setMyPageInProcess("1");

			if (inProcessListData != null && inProcessListData.length > 0) {
				outdoor.setInProcessListLength(true);
				for (int i = Integer.parseInt(pageIndexInProcess[0]); i < Integer
						.parseInt(pageIndexInProcess[1]); i++) {
					OutdoorVisit beanItt = new OutdoorVisit();
					beanItt.setEmployeeNumberIterator(checkNull(String
							.valueOf(inProcessListData[i][0])));
					beanItt.setEmployeeNameIterator(checkNull(String
							.valueOf(inProcessListData[i][1])));
					beanItt.setApplicationDateIterator(checkNull(String
							.valueOf(inProcessListData[i][2])));
					beanItt.setVisitHiddenID(checkNull(String
							.valueOf(inProcessListData[i][3])));
					beanItt.setVisitHiddenStatus(checkNull(String
							.valueOf(inProcessListData[i][4])));
					inProcessVoucherList.add(beanItt);
				}
				outdoor.setInProcessIteratorList(inProcessVoucherList);
			}
			// For in-Process application Ends

			// Sent-Back application Begins
			Object[][] sentBackListData = null;
			ArrayList sentBackList = new ArrayList();

			String sentBackQuery = "SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) NAME, "
					+ " TO_CHAR(OUTDOORVISIT_DATE,'DD-MM-YYYY'), OUTDOORVISIT_CODE, OUTDOORVISIT_STATUS "
					+ " FROM HRMS_OUTDOORVISIT "
					+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_OUTDOORVISIT.OUTDOORVISIT_ECODE) "
					+ " WHERE OUTDOORVISIT_STATUS='B' AND OUTDOORVISIT_ECODE = "
					+ outdoor.getUserEmpId()
					+ " ORDER BY OUTDOORVISIT_DATE DESC";
			sentBackListData = getSqlModel().getSingleResult(sentBackQuery);

			String[] pageIndexSentBack = Utility.doPaging(outdoor
					.getMyPageSentBack(), sentBackListData.length, 20);
			if (pageIndexSentBack == null) {
				pageIndexSentBack[0] = "0";
				pageIndexSentBack[1] = "20";
				pageIndexSentBack[2] = "1";
				pageIndexSentBack[3] = "1";
				pageIndexSentBack[4] = "";
			}

			request.setAttribute("totalSentBackPage", Integer.parseInt(String
					.valueOf(pageIndexSentBack[2])));
			request.setAttribute("sentBackPageNo", Integer.parseInt(String
					.valueOf(pageIndexSentBack[3])));
			if (pageIndexSentBack[4].equals("1"))
				outdoor.setMyPageSentBack("1");

			if (sentBackListData != null && sentBackListData.length > 0) {
				outdoor.setSentBackListLength(true);
				for (int i = Integer.parseInt(pageIndexSentBack[0]); i < Integer
						.parseInt(pageIndexSentBack[1]); i++) {
					OutdoorVisit beanItt = new OutdoorVisit();
					beanItt.setEmployeeNumberIterator(checkNull(String
							.valueOf(sentBackListData[i][0])));
					beanItt.setEmployeeNameIterator(checkNull(String
							.valueOf(sentBackListData[i][1])));
					beanItt.setApplicationDateIterator(checkNull(String
							.valueOf(sentBackListData[i][2])));
					beanItt.setVisitHiddenID(checkNull(String
							.valueOf(sentBackListData[i][3])));
					beanItt.setVisitHiddenStatus(checkNull(String
							.valueOf(sentBackListData[i][4])));
					sentBackList.add(beanItt);
				}
				outdoor.setSentBackIteratorList(sentBackList);
			}
			// Sent-Back application Ends
		} catch (Exception e) {

		}

	}

	public void viewApplicationFunction(OutdoorVisit outdoor,
			String hiddenVisitID) {
		try {
			String viewQuery = "SELECT EMP_ID, EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) NAME, "
					+ " TO_CHAR(OUTDOORVISIT_DATE,'DD-MM-YYYY'),	OUTDOORVISIT_FRTIME, OUTDOORVISIT_TOTIME, "
					+ " OUTDOORVISIT_LOCATION , OUTDOORVISIT_PURPOSE, OUTDOORVISIT_STATUS, "
					+ " OUTDOORVISIT_APPR, OUTDOORVISIT_CODE FROM HRMS_OUTDOORVISIT "
					+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_OUTDOORVISIT.OUTDOORVISIT_ECODE) "
					+ " WHERE   OUTDOORVISIT_CODE = " + hiddenVisitID;
			Object[][] viewQueryObj = getSqlModel().getSingleResult(viewQuery);
			if (viewQueryObj != null && viewQueryObj.length > 0) {
				outdoor.setEcode(checkNull(String.valueOf(viewQueryObj[0][0])));
				outdoor
						.setEToken(checkNull(String.valueOf(viewQueryObj[0][1])));
				outdoor
						.setEmpName(checkNull(String
								.valueOf(viewQueryObj[0][2])));
				outdoor
						.setOutDate(checkNull(String
								.valueOf(viewQueryObj[0][3])));
				outdoor
						.setFrTime(checkNull(String.valueOf(viewQueryObj[0][4])));
				outdoor
						.setToTime(checkNull(String.valueOf(viewQueryObj[0][5])));
				outdoor
						.setOutLoc(checkNull(String.valueOf(viewQueryObj[0][6])));
				outdoor
						.setPurpose(checkNull(String
								.valueOf(viewQueryObj[0][7])));
				outdoor
						.setStatus(checkNull(String.valueOf(viewQueryObj[0][8])));

				outdoor.setOutvCode(checkNull(String
						.valueOf(viewQueryObj[0][10])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean isCurrentUser(String userId) {
		String currentUserSql = " SELECT * FROM HRMS_EMP_OFFC WHERE EMP_ID="
				+ userId;
		Object[][] currentUserObj = getSqlModel().getSingleResult(
				currentUserSql);
		if (currentUserObj != null && currentUserObj.length > 0) {
			return true;
		}
		return false;
	}

	public void getApprovedList(OutdoorVisit outdoor,
			HttpServletRequest request, String userId) {

		try {
			Object[][] approvedListData = null;
			ArrayList approvedList = new ArrayList();

			// Approved application Begins
			String selQuery = "SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) NAME, "
					+ " TO_CHAR(OUTDOORVISIT_DATE,'DD-MM-YYYY'), OUTDOORVISIT_CODE, OUTDOORVISIT_STATUS "
					+ " FROM HRMS_OUTDOORVISIT "
					+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_OUTDOORVISIT.OUTDOORVISIT_ECODE) "
					+ " WHERE OUTDOORVISIT_STATUS='A' AND OUTDOORVISIT_ECODE = "
					+ outdoor.getUserEmpId()
					+ " ORDER BY OUTDOORVISIT_DATE DESC";
			approvedListData = getSqlModel().getSingleResult(selQuery);

			String[] pageIndexApproved = Utility.doPaging(outdoor
					.getMyPageApproved(), approvedListData.length, 20);
			if (pageIndexApproved == null) {
				pageIndexApproved[0] = "0";
				pageIndexApproved[1] = "20";
				pageIndexApproved[2] = "1";
				pageIndexApproved[3] = "1";
				pageIndexApproved[4] = "";
			}

			request.setAttribute("totalApprovedPage", Integer.parseInt(String
					.valueOf(pageIndexApproved[2])));
			request.setAttribute("approvedPageNo", Integer.parseInt(String
					.valueOf(pageIndexApproved[3])));
			if (pageIndexApproved[4].equals("1"))
				outdoor.setMyPageApproved("1");

			if (approvedListData != null && approvedListData.length > 0) {
				outdoor.setApprovedListLength(true);
				for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer
						.parseInt(pageIndexApproved[1]); i++) {
					OutdoorVisit beanItt = new OutdoorVisit();
					beanItt.setEmployeeNumberIterator(checkNull(String
							.valueOf(approvedListData[i][0])));
					beanItt.setEmployeeNameIterator(checkNull(String
							.valueOf(approvedListData[i][1])));
					beanItt.setApplicationDateIterator(checkNull(String
							.valueOf(approvedListData[i][2])));
					beanItt.setVisitHiddenID(checkNull(String
							.valueOf(approvedListData[i][3])));
					beanItt.setVisitHiddenStatus(checkNull(String
							.valueOf(approvedListData[i][4])));
					approvedList.add(beanItt);
				}
				outdoor.setApprovedIteratorList(approvedList);
			}
			// Approved application Ends
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getRejectedList(OutdoorVisit outdoor,
			HttpServletRequest request, String userId) {

		Object[][] rejectedListData = null;
		ArrayList rejectedList = new ArrayList();

		// Rejected application Begins
		String selQuery = "SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) NAME, "
				+ " TO_CHAR(OUTDOORVISIT_DATE,'DD-MM-YYYY'), OUTDOORVISIT_CODE, OUTDOORVISIT_STATUS "
				+ " FROM HRMS_OUTDOORVISIT "
				+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_OUTDOORVISIT.OUTDOORVISIT_ECODE) "
				+ " WHERE OUTDOORVISIT_STATUS='R' AND OUTDOORVISIT_ECODE = "
				+ outdoor.getUserEmpId() + " ORDER BY OUTDOORVISIT_DATE DESC";
		rejectedListData = getSqlModel().getSingleResult(selQuery);

		String[] pageIndexRejected = Utility.doPaging(outdoor
				.getMyPageRejected(), rejectedListData.length, 20);
		if (pageIndexRejected == null) {
			pageIndexRejected[0] = "0";
			pageIndexRejected[1] = "20";
			pageIndexRejected[2] = "1";
			pageIndexRejected[3] = "1";
			pageIndexRejected[4] = "";
		}

		request.setAttribute("totalRejectedPage", Integer.parseInt(String
				.valueOf(pageIndexRejected[2])));
		request.setAttribute("rejectedPageNo", Integer.parseInt(String
				.valueOf(pageIndexRejected[3])));
		if (pageIndexRejected[4].equals("1"))
			outdoor.setMyPageRejected("1");

		if (rejectedListData != null && rejectedListData.length > 0) {
			outdoor.setRejectedListLength(true);
			for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer
					.parseInt(pageIndexRejected[1]); i++) {
				OutdoorVisit beanItt = new OutdoorVisit();
				beanItt.setEmployeeNumberIterator(checkNull(String
						.valueOf(rejectedListData[i][0])));
				beanItt.setEmployeeNameIterator(checkNull(String
						.valueOf(rejectedListData[i][1])));
				beanItt.setApplicationDateIterator(checkNull(String
						.valueOf(rejectedListData[i][2])));
				beanItt.setVisitHiddenID(checkNull(String
						.valueOf(rejectedListData[i][3])));
				beanItt.setVisitHiddenStatus(checkNull(String
						.valueOf(rejectedListData[i][4])));
				rejectedList.add(beanItt);
			}
			outdoor.setRejectedIteratorList(rejectedList);
		}
		// Rejected application Ends
	}

	public boolean saveRecords(OutdoorVisit outdoor, Object[][] empFlow,
			HttpServletRequest request) {
		boolean result = false;
		try {
			String[] empCode = request.getParameterValues("keepInformCode");
			String keepInformCode = "";
			if (empCode != null && empCode.length > 0) {
				for (int i = 0; i < empCode.length; i++) {
					keepInformCode += String.valueOf(empCode[i]) + ",";
				}
				keepInformCode = keepInformCode.substring(0, keepInformCode
						.length() - 1);
			}
			String odIDSql = " SELECT NVL(MAX(OUTDOORVISIT_CODE), 0) + 1 FROM HRMS_OUTDOORVISIT ";
			Object[][] odIDObj = getSqlModel().getSingleResult(odIDSql);
			outdoor.setOutvCode(String.valueOf(odIDObj[0][0]));

			Object data[][] = new Object[1][11];
			data[0][0] = outdoor.getOutvCode();
			data[0][1] = outdoor.getEcode();
			data[0][2] = outdoor.getOutDate();
			data[0][3] = outdoor.getFrTime();
			data[0][4] = outdoor.getToTime();
			data[0][5] = outdoor.getOutLoc();
			data[0][6] = outdoor.getPurpose();
			data[0][7] = (empFlow != null && empFlow.length > 0) ? empFlow[0][0]
					: "0";
			data[0][8] = (empFlow != null && empFlow.length > 0) ? empFlow[0][3]
					: "0";
			data[0][9] = keepInformCode;
			data[0][10] = outdoor.getStatus();
			result = getSqlModel().singleExecute(getQuery(1), data);

		} catch (NullPointerException e) {
			logger.error(e);
		}

		return result;
	}

	public boolean updateRecords(OutdoorVisit outdoor, Object[][] empFlow,
			HttpServletRequest request) {
		boolean result = false;
		try {
			String[] empCode = request.getParameterValues("keepInformCode");
			String code = "";
			if (empCode != null && empCode.length > 0) {
				for (int i = 0; i < empCode.length; i++) {
					code += String.valueOf(empCode[i]) + ",";
				}
				code = code.substring(0, code.length() - 1);
			}
			Object data[][] = new Object[1][11];
			data[0][0] = outdoor.getEcode();
			data[0][1] = outdoor.getOutDate();
			data[0][2] = outdoor.getFrTime();
			data[0][3] = outdoor.getToTime();
			data[0][4] = outdoor.getOutLoc();
			data[0][5] = outdoor.getPurpose();
			data[0][6] = (empFlow != null && empFlow.length > 0) ? empFlow[0][0]
					: "0";
			data[0][7] = (empFlow != null && empFlow.length > 0) ? empFlow[0][3]
					: "0";
			data[0][8] = code;
			data[0][9] = outdoor.getStatus();
			data[0][10] = outdoor.getOutvCode();
			result = getSqlModel().singleExecute(getQuery(2), data);

		} catch (NullPointerException e) {
			logger.error(e);
		}

		return result;
	}

	public boolean sendForApprovalFunction(OutdoorVisit outdoor,
			Object[][] empFlow, HttpServletRequest request) {
		boolean result = false;
		if (outdoor.getOutvCode().equals("")) {
			result = saveRecords(outdoor, empFlow, request);
		} else {
			result = updateRecords(outdoor, empFlow, request);
		}
		return result;
	}

	public void getCurrenUserInformation(OutdoorVisit outdoor) {
		try {
			String query = "SELECT EMP_ID, EMP_TOKEN, EMP_FNAME||' '||EMP_LNAME FROM HRMS_EMP_OFFC "
					+ " WHERE EMP_ID = " + outdoor.getUserEmpId();
			Object[][] queryObj = getSqlModel().getSingleResult(query);
			if (queryObj != null && queryObj.length > 0) {
				outdoor.setEcode(checkNull(String.valueOf(queryObj[0][0])));
				outdoor.setEToken(checkNull(String.valueOf(queryObj[0][1])));
				outdoor.setEmpName(checkNull(String.valueOf(queryObj[0][2])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean deleteApplication(OutdoorVisit outdoor) {
		boolean result = false;
		try {
			String deleteQuery = "DELETE FROM HRMS_OUTDOORVISIT WHERE OUTDOORVISIT_CODE = "
					+ outdoor.getOutvCode();
			result = getSqlModel().singleExecute(deleteQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void setAttendanceData(OutdoorVisit outdoor, String outDoorVisitDate) {
		try {
			String year = outDoorVisitDate.substring(6);
			String attendanceQuery = "SELECT TO_CHAR(ATT_DATE,'DD-MM-YYYY'), SHIFT_NAME, TO_CHAR(ATT_LOGIN,'hh24:mi') AS IN_TIME, "
									+" TO_CHAR(ATT_LOGOUT,'hh24:mi') AS OUT_TIME, NVL(TO_CHAR(ATT_WORKING_HRS,'hh24:mi'),0) AS WORKING_HOURS, " 
									+" NVL(TO_CHAR(ATT_EXTRAHRS,'hh24:mi'),0) AS EXTRA_HOURS, NVL(TO_CHAR(ATT_LATE_HRS,'hh24:mi'),0) AS LATE_HOURS, " 
									+" NVL(TO_CHAR(ATT_EARLY_HRS,'hh24:mi'),0) AS EARLY_HOURS, " 
									+" DECODE(ATT_STATUS_ONE,'PR','Present','WO','Week-Off','HO','Holiday','AB','Absent') AS STATUS_ONE, " 
									+" DECODE(ATT_STATUS_TWO,'IN','In-Time','LC','Late-Coming','EL','Early-Leaving','DL','Dual-Late', "
									+" 'HD','Half-Day','AB','Absent')  AS STATUS_TWO "
									+" FROM HRMS_DAILY_ATTENDANCE_"+year
									+" INNER JOIN HRMS_SHIFT ON (HRMS_SHIFT.SHIFT_ID = HRMS_DAILY_ATTENDANCE_"+year+".ATT_SHIFT_ID) "
									+" WHERE TO_CHAR(ATT_DATE,'dd-mm-yyyy')='"+outDoorVisitDate+"' AND ATT_EMP_ID = "+outdoor.getEcode();
		Object[][] attendanceQueryObj = getSqlModel().getSingleResult(attendanceQuery);
		if (attendanceQueryObj != null && attendanceQueryObj.length > 0) {
			outdoor.setDataAvailableAttendanceFlag(true);
			outdoor.setAttDate(checkNull(String.valueOf(attendanceQueryObj[0][0])));
			outdoor.setAttShiftName(checkNull(String.valueOf(attendanceQueryObj[0][1])));
			outdoor.setAttInTime(checkNull(String.valueOf(attendanceQueryObj[0][2])));
			outdoor.setAttOutTime(checkNull(String.valueOf(attendanceQueryObj[0][3])));
			outdoor.setAttWorkingHours(checkNull(String.valueOf(attendanceQueryObj[0][4])));
			outdoor.setAttExtraHours(checkNull(String.valueOf(attendanceQueryObj[0][5])));
			outdoor.setAttLateHours(checkNull(String.valueOf(attendanceQueryObj[0][6])));
			outdoor.setAttEarlyHours(checkNull(String.valueOf(attendanceQueryObj[0][7])));
			outdoor.setAttStatusOne(checkNull(String.valueOf(attendanceQueryObj[0][8])));
			outdoor.setAttStatusTwo(checkNull(String.valueOf(attendanceQueryObj[0][9])));
		}else{
			outdoor.setNoDataAvailableAttendanceFlag(true);
			outdoor.setAttDate(outDoorVisitDate);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void setLevel(OutdoorVisit bean) {
		try {
		String sqlQuery = " SELECT NVL(OUTDOOR_APP_LEVEL,0) FROM HRMS_OUTDOORVISIT "
					+ " WHERE OUTDOORVISIT_CODE=" + bean.getOutvCode().trim();
			Object[][] levelObj = getSqlModel().getSingleResult(sqlQuery);
			if (levelObj != null && levelObj.length > 0) {
				bean.setApplicationLevel(String.valueOf(levelObj[0][0]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}