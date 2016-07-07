package org.paradyne.model.TravelManagement;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.TravelManagement.TravelSchApprover;
import org.paradyne.lib.ModelBase;

public class TravelSchApproverModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TravelSchApproverModel.class);

	public void callStatus(TravelSchApprover bean, String status,
			HttpServletRequest request) {

		// check the status here

		bean.setStat(status);
		logger.info("Status---------------" + bean.getStat());

		String query = "";

		// check status here

		if (bean.getStat().equals("P")) {
			logger.info("PENDING CASE---------------");
			query = " SELECT TRAVEL_APP_ID ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS EMPNAME,"
					+ "	TRAVEL_APP_REQUEST,"
					+ "	TO_CHAR(TRAVEL_APP_APPDATE,'DD-MM-YYYY'),TRAVEL_APP_STATUS"
					+ "	FROM HRMS_TMS_TRAVEL_APP"
					+ "	LEFT JOIN HRMS_EMP_OFFC ON(HRMS_TMS_TRAVEL_APP.TRAVEL_APP_EMPID=HRMS_EMP_OFFC.EMP_ID)"
					+ "	WHERE TRAVEL_SCH_APPR_STATUS='S'    ";
			/*+"	AND HRMS_EMP_OFFC.EMP_CENTER IN( SELECT TRAVEL_ADMIN_BRANCH_CODE  FROM HRMS_TRAVEL_ADMIN WHERE TRAVEL_ADMIN_EMP_ID="+bean.getUserEmpId()+")";*/

		}

		else {

			logger.info("other CASE---------------");
			query = " SELECT TRAVEL_APP_ID ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS EMPNAME,"
					+ "	TRAVEL_APP_REQUEST,"
					+ "	TO_CHAR(TRAVEL_APP_APPDATE,'DD-MM-YYYY'),TRAVEL_APP_STATUS"
					+ "	FROM HRMS_TMS_TRAVEL_APP"
					+ "	LEFT JOIN HRMS_EMP_OFFC ON(HRMS_TMS_TRAVEL_APP.TRAVEL_APP_EMPID=HRMS_EMP_OFFC.EMP_ID)"
					+ "	WHERE TRAVEL_SCH_APPR_STATUS='S'    ";
			/*+"	AND HRMS_EMP_OFFC.EMP_CENTER IN( SELECT TRAVEL_ADMIN_BRANCH_CODE  FROM HRMS_TRAVEL_ADMIN WHERE TRAVEL_ADMIN_EMP_ID="+bean.getUserEmpId()+")";*/

		}

		Object[][] result = getSqlModel().getSingleResult(query);

		doPaging(bean, result.length, new Object[][] {}, request);

		int fromTotRec = Integer.parseInt(bean.getFromTotRec());
		int toTotRec = Integer.parseInt(bean.getToTotRec());

		ArrayList travelList = new ArrayList();
		for (int i = fromTotRec; i < toTotRec; i++) {
			TravelSchApprover bean1 = new TravelSchApprover();

			bean1.setTravelAppId(checkNull(String.valueOf(result[i][0])));

			bean1.setEmpName(checkNull(String.valueOf(result[i][1])));
			bean1.setTrvlReqName(checkNull(String.valueOf(result[i][2])));
			bean1.setTrvlDate(checkNull(String.valueOf(result[i][3])));
			bean1.setStatus(checkNull(String.valueOf(result[i][4])));

			travelList.add(bean1);

			if (status.equals("P")) {
				bean1.setPenFlag(true);
			} else if (status.equals("A")) {
				bean1.setRegctedFlag(true);
			} else {
				bean1.setRegctedFlag(true);
			}

		} // end of for loop

		bean.setTrvlSchList(travelList);

		logger.info("ITERATOR SIZE________________" + travelList.size());
		logger.info("no data flag========" + bean.isNoData());

		if (travelList.size() == 0) { // bean.setNoData("true"); 
			bean.setNoData(true);
			logger.info("no data flag========" + bean.isNoData());
		} else {
			bean.setNoData(false);
		}

	} // end of callStatus

	public void doPaging(TravelSchApprover bean, int empLength,
			Object[][] attnObj, HttpServletRequest request) {
		try {
			/*
			 * totalRec -: No. of records per page fromTotRec -: Starting No. of
			 * record to show on a current page toTotRec -: Last No. of record
			 * to show on a current page pageNo -: Current page to show
			 * totalPage -: Total No. of Pages
			 */

			String pagingSql = " SELECT CONF_RECORDS_PER_PAGE FROM HRMS_SALARY_CONF ";
			Object[][] pagingObj = getSqlModel().getSingleResult(pagingSql);
			int totalRec = Integer.parseInt(String.valueOf(pagingObj[0][0]));

			int pageNo = 1;
			int fromTotRec = 0;
			int toTotRec = totalRec;
			int searchCount = 0;
			int totalPage = 0;
			String empExists = "false";

			java.math.BigDecimal bigDecRow1 = new java.math.BigDecimal(
					(double) empLength / totalRec);
			totalPage = Integer.parseInt(bigDecRow1.setScale(0,
					java.math.BigDecimal.ROUND_UP).toString());

			if (String.valueOf(bean.getHdPage()).equals("null")
					|| String.valueOf(bean.getHdPage()).equals(null)
					|| String.valueOf(bean.getHdPage()).equals("")) {
				pageNo = 1;
				fromTotRec = 0;
				toTotRec = totalRec;

				if (toTotRec > empLength) {
					toTotRec = empLength;
				}
				bean.setHdPage("1");
			} else {
				pageNo = Integer.parseInt(bean.getHdPage());

				if (pageNo == 1) {
					fromTotRec = 0;
					toTotRec = totalRec;
				} else {
					toTotRec = toTotRec * pageNo;
					fromTotRec = toTotRec - totalRec;
				}
				if (toTotRec > empLength) {
					toTotRec = empLength;
				}

			}

			bean.setFromTotRec(String.valueOf(fromTotRec));
			bean.setToTotRec(String.valueOf(toTotRec));

			request.setAttribute("totalPage", totalPage);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("fromTotRec", fromTotRec);
			request.setAttribute("toTotRec", toTotRec);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else1
	}

}
