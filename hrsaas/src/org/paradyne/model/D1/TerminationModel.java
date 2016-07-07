package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.D1.Termination;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.common.ApplCodeTemplateModel;

/**
 * @author aa1380 : Manish Sakpal. Created on 16th March 2011
 */
public class TerminationModel extends ModelBase {
	/**	 voStr. *	 */
	private final String voStr = "vo";
	/**	 ivoStr. *	 */
	private final String ivoStr = "ivo";
	/**	 otStr. *	 */
	private final String otStr = "ot";
	/**	 zeroStr. *	 */
	private final String zeroStr = "0";
	/**	 twentyStr. *	 */
	private final String twentyStr = "20";
	/**	 oneStr. *	 */
	private final String oneStr = "1";
	/**
	 * Method : isCurrentUser. Purpose : Used to check whether current user is
	 * applicant or not.
	 * 
	 * @param userId :
	 * @return boolean
	 */
	public boolean isCurrentUser(final String userId) {
		final Object[][] currentUserObj = this.getSqlModel().getSingleResult(
				" SELECT * FROM HRMS_EMP_OFFC WHERE EMP_ID=" + userId);
		if (currentUserObj != null && currentUserObj.length > 0) {
			return true;
		}
		return false;
	}

	// Pending List Begins
	/**
	 * Method : Used to get pending list of applications.
	 * @param trBean :
	 * @param request :
	 * @param userId :
	 */
	public void getPendingList(final Termination trBean, final HttpServletRequest request, final String userId) {
		try {
			// Drafted List Begins
			final List<Termination> draftList = new ArrayList<Termination>();
			final String selQuery = " SELECT TER_TRACKING_NUMBER, EMP_FNAME||' '||EMP_LNAME, TO_CHAR(TER_TERMINATION_DATE,'DD-MM-YYYY'), TER_ID, TER_STATUS " + 
					" FROM HRMS_D1_TERMINATION " + 
					" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_TERMINATION.TER_EMP_ID)" + 
					" WHERE TER_STATUS='D' AND TER_COMPLETED_BY= " + userId + 
					" ORDER BY HRMS_D1_TERMINATION.TER_TRACKING_NUMBER DESC";
			final Object[][] selQueryObj = this.getSqlModel().getSingleResult(selQuery);
			final String[] pageIndex = Utility.doPaging(trBean.getMyPage(), selQueryObj.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = this.twentyStr;
				pageIndex[2] = this.oneStr;
				pageIndex[3] = this.oneStr;
				pageIndex[4] = "";
			}

			request.setAttribute("totalDraftPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("draftPageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			if (pageIndex[4].equals(this.oneStr)) {
				trBean.setMyPage(this.oneStr);
			}
			if (selQueryObj != null && selQueryObj.length > 0) {
				trBean.setDraftVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					final Termination beanItt = new Termination();
					beanItt.setTrackingNumIterator(Utility.checkNull(String.valueOf(selQueryObj[i][0])));
					beanItt.setEmployeeNameIterator(Utility.checkNull(String.valueOf(selQueryObj[i][1])));
					beanItt.setTerminationDateIterator(Utility.checkNull(String.valueOf(selQueryObj[i][2])));
					beanItt.setTerminationHiddenID(Utility.checkNull(String.valueOf(selQueryObj[i][3])));
					beanItt.setHiddenStatus(Utility.checkNull(String.valueOf(selQueryObj[i][4])));
					draftList.add(beanItt);
				}
				trBean.setDraftVoucherIteratorList(draftList);
			}
			// Drafted List Ends

			// For in-Process application Begins
			final List<Termination> inProcessVoucherList = new ArrayList<Termination>();
			final String inProcessQuery = " SELECT TER_TRACKING_NUMBER, EMP_FNAME||' '||EMP_LNAME, TO_CHAR(TER_TERMINATION_DATE,'DD-MM-YYYY'), TER_ID, TER_STATUS " + 
					" FROM HRMS_D1_TERMINATION " + 
					" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_TERMINATION.TER_EMP_ID)" + 
					" WHERE TER_STATUS='P' AND TER_COMPLETED_BY= " + userId +
					" ORDER BY HRMS_D1_TERMINATION.TER_TRACKING_NUMBER DESC";
			final Object[][] inProcessListData = this.getSqlModel().getSingleResult(inProcessQuery);

			final String[] pageIndexInProcess = Utility.doPaging(trBean.getMyPageInProcess(), inProcessListData.length, 20);
			if (pageIndexInProcess == null) {
				pageIndexInProcess[0] = "0";
				pageIndexInProcess[1] = this.twentyStr;
				pageIndexInProcess[2] = this.oneStr;
				pageIndexInProcess[3] = this.oneStr;
				pageIndexInProcess[4] = "";
			}

			request.setAttribute("totalInProcessPage", Integer.parseInt(String.valueOf(pageIndexInProcess[2])));
			request.setAttribute("inProcessPageNo", Integer.parseInt(String.valueOf(pageIndexInProcess[3])));
			if (pageIndexInProcess[4].equals(this.oneStr)) {
				trBean.setMyPageInProcess(this.oneStr);
			}

			if (inProcessListData != null && inProcessListData.length > 0) {
				trBean.setInProcessVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexInProcess[0]); i < Integer.parseInt(pageIndexInProcess[1]); i++) {
					final Termination beanItt = new Termination();
					beanItt.setTrackingNumIterator(Utility.checkNull(String.valueOf(inProcessListData[i][0])));
					beanItt.setEmployeeNameIterator(Utility.checkNull(String.valueOf(inProcessListData[i][1])));
					beanItt.setTerminationDateIterator(Utility.checkNull(String.valueOf(inProcessListData[i][2])));
					beanItt.setTerminationHiddenID(Utility.checkNull(String.valueOf(inProcessListData[i][3])));
					beanItt.setHiddenStatus(Utility.checkNull(String.valueOf(inProcessListData[i][4])));
					inProcessVoucherList.add(beanItt);
				}
				trBean.setInProcessVoucherIteratorList(inProcessVoucherList);
			}
			// For in-Process application Ends

			// Sent-Back application Begins
			final List<Termination> sentBackVoucherList = new ArrayList<Termination>();
			final String sentBackQuery = " SELECT TER_TRACKING_NUMBER, EMP_FNAME||' '||EMP_LNAME, TO_CHAR(TER_TERMINATION_DATE,'DD-MM-YYYY'), TER_ID, TER_STATUS " + 
					" FROM HRMS_D1_TERMINATION " + 
					" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_TERMINATION.TER_EMP_ID)" + 
					" WHERE TER_STATUS='B' AND TER_COMPLETED_BY= " + userId +
					" ORDER BY HRMS_D1_TERMINATION.TER_TRACKING_NUMBER DESC";
			final Object[][] sentBackListData = this.getSqlModel().getSingleResult(sentBackQuery);

			final String[] pageIndexSentBack = Utility.doPaging(trBean.getMyPageSentBack(), sentBackListData.length, 20);
			if (pageIndexSentBack == null) {
				pageIndexSentBack[0] = "0";
				pageIndexSentBack[1] = this.twentyStr;
				pageIndexSentBack[2] = this.oneStr;
				pageIndexSentBack[3] = this.oneStr;
				pageIndexSentBack[4] = "";
			}

			request.setAttribute("totalSentBackPage", Integer.parseInt(String.valueOf(pageIndexSentBack[2])));
			request.setAttribute("sentBackPageNo", Integer.parseInt(String.valueOf(pageIndexSentBack[3])));
			if (pageIndexSentBack[4].equals(this.oneStr)) {
				trBean.setMyPageSentBack(this.oneStr);
			}

			if (sentBackListData != null && sentBackListData.length > 0) {
				trBean.setSentBackVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndexSentBack[0]); i < Integer.parseInt(pageIndexSentBack[1]); i++) {
					final Termination beanItt = new Termination();
					beanItt.setTrackingNumIterator(Utility.checkNull(String.valueOf(sentBackListData[i][0])));
					beanItt.setEmployeeNameIterator(Utility.checkNull(String.valueOf(sentBackListData[i][1])));
					beanItt.setTerminationDateIterator(Utility.checkNull(String.valueOf(sentBackListData[i][2])));
					beanItt.setTerminationHiddenID(Utility.checkNull(String.valueOf(sentBackListData[i][3])));
					beanItt.setHiddenStatus(Utility.checkNull(String.valueOf(sentBackListData[i][4])));
					sentBackVoucherList.add(beanItt);
				}
				trBean.setSentBackVoucherIteratorList(sentBackVoucherList);
			}
			// Sent-Back application Ends

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	// Pending List Ends

	// Approved List Begins
	/**Used to get approved list of aplication.
	 * @param trBean : 
	 * @param request : 
	 * @param userId : 
	 */
	public void getApprovedList(final Termination trBean, final HttpServletRequest request, final String userId) {
		try {
			final List<Termination> approvedList = new ArrayList<Termination>();
			final String selQuery = " SELECT TER_TRACKING_NUMBER, EMP_FNAME||' '||EMP_LNAME, TO_CHAR(TER_TERMINATION_DATE,'DD-MM-YYYY'), TER_ID, TER_STATUS " + 
					" FROM HRMS_D1_TERMINATION " + 
					" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_TERMINATION.TER_EMP_ID)" + 
					" WHERE TER_STATUS='A' AND TER_COMPLETED_BY= " + userId +
					" ORDER BY HRMS_D1_TERMINATION.TER_TRACKING_NUMBER DESC";
			final Object[][] approvedQueryObj = this.getSqlModel().getSingleResult(selQuery);
			final String[] pageIndex = Utility.doPaging(trBean.getMyPageApproved(), approvedQueryObj.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = this.twentyStr;
				pageIndex[2] = this.oneStr;
				pageIndex[3] = this.oneStr;
				pageIndex[4] = "";
			}

			request.setAttribute("totalApprovedPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("approvedPageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			if (pageIndex[4].equals(this.oneStr)) {
				trBean.setMyPageApproved(this.oneStr);
			}
			if (approvedQueryObj != null && approvedQueryObj.length > 0) {
				trBean.setApprovedVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					final Termination beanItt = new Termination();
					beanItt.setTrackingNumIterator(Utility.checkNull(String.valueOf(approvedQueryObj[i][0])));
					beanItt.setEmployeeNameIterator(Utility.checkNull(String.valueOf(approvedQueryObj[i][1])));
					beanItt.setTerminationDateIterator(Utility.checkNull(String.valueOf(approvedQueryObj[i][2])));
					beanItt.setTerminationHiddenID(Utility.checkNull(String.valueOf(approvedQueryObj[i][3])));
					beanItt.setHiddenStatus(Utility.checkNull(String.valueOf(approvedQueryObj[i][4])));
					approvedList.add(beanItt);
				}
				trBean.setApprovedVoucherIteratorList(approvedList);
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	// Approved List Ends

	// Rejected List Begins
	/**Used to get rejected list of application.
	 * @param trBean : 
	 * @param request : 
	 * @param userId : 
	 */
	public void getRejectedList(final Termination trBean, final HttpServletRequest request, final String userId) {
		try {
			final List<Termination> rejectedList = new ArrayList<Termination>();
			final String selQuery = " SELECT TER_TRACKING_NUMBER, EMP_FNAME||' '||EMP_LNAME, TO_CHAR(TER_TERMINATION_DATE,'DD-MM-YYYY'), TER_ID, TER_STATUS " + 
					" FROM HRMS_D1_TERMINATION " + 
					" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_D1_TERMINATION.TER_EMP_ID)" + 
					" WHERE TER_STATUS='R' AND TER_COMPLETED_BY = " + userId + 
					" ORDER BY HRMS_D1_TERMINATION.TER_TRACKING_NUMBER DESC";
			final Object[][] rejectedQueryObj = this.getSqlModel().getSingleResult(selQuery);
			final String[] pageIndex = Utility.doPaging(trBean.getMyPageRejected(), rejectedQueryObj.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = this.twentyStr;
				pageIndex[2] = this.oneStr;
				pageIndex[3] = this.oneStr;
				pageIndex[4] = "";
			}

			request.setAttribute("totalRejectedPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("rejectedPageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			if (pageIndex[4].equals(this.oneStr)) {
				trBean.setMyPageRejected(this.oneStr);
			}
			if (rejectedQueryObj != null && rejectedQueryObj.length > 0) {
				trBean.setRejectedVoucherListLength(true);
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					final Termination beanItt = new Termination();
					beanItt.setTrackingNumIterator(Utility.checkNull(String.valueOf(rejectedQueryObj[i][0])));
					beanItt.setEmployeeNameIterator(Utility.checkNull(String.valueOf(rejectedQueryObj[i][1])));
					beanItt.setTerminationDateIterator(Utility.checkNull(String.valueOf(rejectedQueryObj[i][2])));
					beanItt.setTerminationHiddenID(Utility.checkNull(String.valueOf(rejectedQueryObj[i][3])));
					beanItt.setHiddenStatus(Utility.checkNull(String.valueOf(rejectedQueryObj[i][4])));
					rejectedList.add(beanItt);
				}
				trBean.setRejectedVoucherIteratorList(rejectedList);
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	// Rejected List Ends

	/**Used to get applicant details.
	 * @param trBean : 
	 * @param userId : 
	 */
	public void getLoginUserInformation(final Termination trBean, final String userId) {
		try {
			final String query = "SELECT EMP_ID, EMP_FNAME||' '||EMP_LNAME, TO_CHAR(SYSDATE,'DD-MM-YYYY   HH24:MI') FROM HRMS_EMP_OFFC WHERE EMP_ID = "	+ userId;
			final Object[][] queryObj = this.getSqlModel().getSingleResult(
					query);
			if (queryObj != null && queryObj.length > 0) {
				trBean.setCompletedByID(Utility.checkNull(String.valueOf(queryObj[0][0])));
				trBean.setCompletedByName(Utility.checkNull(String.valueOf(queryObj[0][1])));
				trBean.setCompletedDate(Utility.checkNull(String.valueOf(queryObj[0][2])));
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}

	}

	/**Used to set address and city.
	 * @param trBean : 
	 */
	public void setsetAddressCity(final Termination trBean) {
		try {
			final String selQuery = "SELECT ADD_1||' '||ADD_2||' '||ADD_3, ADD_PINCODE, ADD_CITY, LOCATION_NAME, ADD_STATE, " + 
					" DEPT_ID,DEPT_NUMBER, EMP_RANK, RANK_NAME ,MGR.EMP_TOKEN,MGR.EMP_FNAME||' '||MGR.EMP_MNAME||' '||MGR.EMP_LNAME , MGR.EMP_ID  FROM HRMS_EMP_OFFC OFFC" + 
					" LEFT JOIN  HRMS_RANK ON (HRMS_RANK.RANK_ID = OFFC.EMP_RANK) " + 
					" LEFT JOIN HRMS_D1_DEPARTMENT ON (HRMS_D1_DEPARTMENT.DEPT_ID = OFFC.EMP_DEPT_NO) " + 
					" INNER JOIN HRMS_EMP_ADDRESS ON (HRMS_EMP_ADDRESS.EMP_ID = OFFC.EMP_ID) " + 
					" LEFT JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE = HRMS_EMP_ADDRESS.ADD_CITY) " + 
					" LEFT JOIN HRMS_EMP_OFFC MGR ON(MGR.EMP_ID = OFFC.EMP_REPORTING_OFFICER) " + 
					" WHERE OFFC.EMP_ID = " + trBean.getEmployeeNumber();
			final Object[][] queryObj = this.getSqlModel().getSingleResult(selQuery);
			trBean.setManagerName("");
			if (queryObj != null && queryObj.length > 0) {
				trBean.setHomeAddress(Utility.checkNull(String.valueOf(queryObj[0][0])));
				trBean.setZipCode(Utility.checkNull(String.valueOf(queryObj[0][1])));
				trBean.setCityID(Utility.checkNull(String.valueOf(queryObj[0][2])));
				trBean.setCityName(Utility.checkNull(String.valueOf(queryObj[0][3])));
				trBean.setState(Utility.checkNull(String.valueOf(queryObj[0][4])));
				// trBean.setDeptID(Utility.checkNull(String.valueOf(queryObj[0][5])));
				// trBean.setDepetNumber(Utility.checkNull(String.valueOf(queryObj[0][6])));
				trBean.setExecutiveID(Utility.checkNull(String.valueOf(queryObj[0][7])));
				trBean.setExecutiveName(Utility.checkNull(String.valueOf(queryObj[0][8])));
				trBean.setManagerToken(Utility.checkNull(String.valueOf(queryObj[0][9])));
				trBean.setManagerName(Utility.checkNull(String.valueOf(queryObj[0][10])));
				trBean.setManagerCode(Utility.checkNull(String.valueOf(queryObj[0][11])));
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**Used to get state and city.
	 * @param trBean : 
	 */
	public void getStateFromCity(final Termination trBean) {
		try {
			final String stateQuery = "SELECT LOCATION_NAME FROM HRMS_LOCATION WHERE LOCATION_CODE = (SELECT LOCATION_PARENT_CODE FROM HRMS_LOCATION WHERE LOCATION_CODE = " + trBean.getCityID() + ") ";
			final Object[][] stateObj = this.getSqlModel().getSingleResult(stateQuery);
			if (stateObj != null && stateObj.length > 0) {
				trBean.setState(Utility.checkNull(String.valueOf(stateObj[0][0])));
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**Used to save application details.
	 * @param trBean : 
	 * @return boolean
	 */
	public boolean saveRecords(final Termination trBean) {
		boolean result = false;
		try {
			// Tracking Number Begins
			final String trackingQuery = "SELECT NVL(MAX(TER_ID),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'),NVL(MAX(TER_ID),0)+1	,TO_CHAR(SYSDATE,'DD-MM-YYYY')	 FROM HRMS_D1_TERMINATION	";
			final Object[][] trackingObj = this.getSqlModel().getSingleResult(trackingQuery);
			if (trackingObj != null && trackingObj.length > 0) {
				try {
					final ApplCodeTemplateModel model = new ApplCodeTemplateModel();
					model.initiate(context, session);
					final String autoIncrementApplicationCode = model.generateApplicationCode(String.valueOf(trackingObj[0][1]), "D1-TERMNT",	trBean.getUserEmpId(), String.valueOf(trackingObj[0][2]));
					trBean.setTrackingNumber(Utility.checkNull(autoIncrementApplicationCode));
					model.terminate();
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
			// Tracking Number Ends

			final Object[][] addObj = new Object[1][21];
			addObj[0][0] = trBean.getEmployeeNumber();
			addObj[0][1] = trBean.getTerminationDate();
			addObj[0][2] = trBean.getLastDayWorkDate();
			addObj[0][3] = trBean.getIfTerDateAndLastDayWorkDateDiffer();

			if (this.voStr.equals(trBean.getTerminationTypeRadioOptionValue())) {
				addObj[0][4] = "V";
				addObj[0][5] = trBean.getVoluntaryTerminationReason();
			} else if (this.ivoStr.equals(trBean.getTerminationTypeRadioOptionValue())) {
				addObj[0][4] = "N";
				addObj[0][5] = trBean.getInVoluntaryTerminationReason();
			} else {
				addObj[0][4] = "O";
				addObj[0][5] = trBean.getOtherTerminationReason();
			}

			addObj[0][6] = trBean.getEligibleToRehire();
			addObj[0][7] = trBean.getIfNoOrProvisional();

			// addObj[0][14] = trBean.getManagerName();
			// addObj[0][15] = trBean.getManagerPhone();

			addObj[0][8] = trBean.getVacationHrsTaken();
			addObj[0][9] = trBean.getCommentsEntered();
			addObj[0][10] = trBean.getCompletedByID();
			addObj[0][11] = trBean.getCompletedDate();
			addObj[0][12] = trBean.getStatus();
			addObj[0][13] = trBean.getIfOtherTerminationReasonTextArea();
			addObj[0][14] = trBean.getTrackingNumber();

			addObj[0][15] = trBean.getCityName();
			addObj[0][16] = trBean.getState();
			addObj[0][17] = trBean.getHomeAddress();
			addObj[0][18] = trBean.getZipCode();
			addObj[0][19] = trBean.getDepetNumber();
			addObj[0][20] = trBean.getManagerCode();

			final String insertQuery = "INSERT INTO HRMS_D1_TERMINATION (TER_ID, TER_EMP_ID, TER_TERMINATION_DATE, TER_LAST_DAY_WORKED, " + 
					" TER_DIFFER_DATE_EXPLAIN, TER_TERMINATION_TYPE, TER_TERMINATION_REASON, TER_ELIGIBLE_TO_REHIRE, TER_IF_NO_PRO_EXPLAIN, " + 
					" TER_VACATION_HOURS, TER_COMMENTS, TER_COMPLETED_BY, TER_COMPLETED_DATE, TER_STATUS, TER_IF_OTHER_REASON, TER_TRACKING_NUMBER, " + 
					" TER_CITY, TER_STATE, TER_ADDRESS, TER_ZIPCODE, TER_DEPT ,TER_MANG_NAME) " + 
					" VALUES ((SELECT NVL(MAX(TER_ID),0)+1 FROM HRMS_D1_TERMINATION), ?, TO_DATE(?,'DD-MM-YYYY'), " + 
					" TO_DATE(?,'DD-MM-YYYY'), ?, ?, ?, ?, ?, ?, ?, ?, TO_DATE(?,'DD-MM-YYYY  HH24:MI'),?,?,?,?,?,?,?,?,?)";
			result = this.getSqlModel().singleExecute(insertQuery, addObj);
			if (result) {
				final String autoCodeQuery = " SELECT NVL(MAX(TER_ID),0) FROM HRMS_D1_TERMINATION ";
				final Object[][] data = this.getSqlModel().getSingleResult(
						autoCodeQuery);
				if (data != null && data.length > 0) {
					trBean.setTerminationID(String.valueOf(data[0][0]));
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**Used to update data.
	 * @param trBean : 
	 * @return boolean
	 */
	public boolean updateRecords(final Termination trBean) {
		boolean result = false;
		try {
			final Object[][] updateObj = new Object[1][22];
			updateObj[0][0] = trBean.getEmployeeNumber();
			updateObj[0][1] = trBean.getTerminationDate();
			updateObj[0][2] = trBean.getLastDayWorkDate();
			updateObj[0][3] = trBean.getIfTerDateAndLastDayWorkDateDiffer();

			if (this.voStr.equals(trBean.getTerminationTypeRadioOptionValue())) {
				updateObj[0][4] = "V";
				updateObj[0][5] = trBean.getVoluntaryTerminationReason();
			} else if (this.ivoStr.equals(trBean.getTerminationTypeRadioOptionValue())) {
				updateObj[0][4] = "N";
				updateObj[0][5] = trBean.getInVoluntaryTerminationReason();
			} else {
				updateObj[0][4] = "O";
				updateObj[0][5] = trBean.getOtherTerminationReason();
			}

			updateObj[0][6] = trBean.getEligibleToRehire();
			updateObj[0][7] = trBean.getIfNoOrProvisional();
			updateObj[0][8] = trBean.getVacationHrsTaken();
			updateObj[0][9] = trBean.getCommentsEntered();
			updateObj[0][10] = trBean.getCompletedByID();
			updateObj[0][11] = trBean.getCompletedDate();
			updateObj[0][12] = trBean.getStatus();
			updateObj[0][13] = trBean.getIfOtherTerminationReasonTextArea();
			updateObj[0][14] = trBean.getTrackingNumber();
			updateObj[0][15] = trBean.getCityName();
			updateObj[0][16] = trBean.getState();
			updateObj[0][17] = trBean.getHomeAddress();
			updateObj[0][18] = trBean.getZipCode();
			updateObj[0][19] = trBean.getDepetNumber();
			updateObj[0][20] = trBean.getManagerCode();
			updateObj[0][21] = trBean.getTerminationID();

			final String updateQuery = "UPDATE HRMS_D1_TERMINATION SET TER_EMP_ID=?, TER_TERMINATION_DATE=TO_DATE(?,'DD-MM-YYYY'), TER_LAST_DAY_WORKED=TO_DATE(?,'DD-MM-YYYY'), " + 
					" TER_DIFFER_DATE_EXPLAIN=?, TER_TERMINATION_TYPE=?, TER_TERMINATION_REASON=?, TER_ELIGIBLE_TO_REHIRE=?, " + 
					" TER_IF_NO_PRO_EXPLAIN=?, TER_VACATION_HOURS=?, TER_COMMENTS=?, TER_COMPLETED_BY=?, TER_COMPLETED_DATE=TO_DATE(?,'DD-MM-YYYY  HH24:MI'), " + 
					" TER_STATUS=?, TER_IF_OTHER_REASON=?, TER_TRACKING_NUMBER=?, " + 
					" TER_CITY=?, TER_STATE=?, TER_ADDRESS=?, TER_ZIPCODE=?, TER_DEPT=? , TER_MANG_NAME = ? WHERE TER_ID=? ";
			result = this.getSqlModel().singleExecute(updateQuery, updateObj);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Used to view application details.
	 * 
	 * @param trBean :
	 * @param terminationHiddenID :
	 */
	public void viewApplication(final Termination trBean,
			final String terminationHiddenID) {
		final String viewQuery = " SELECT TER_ID, TER_EMP_ID, OFFC1.EMP_TOKEN, OFFC1.EMP_FNAME||' '||OFFC1.EMP_LNAME, " + 
				" TER_ADDRESS, TER_ZIPCODE, TER_CITY, TER_STATE, " + 
				" DEPT_ID,TER_DEPT, EMP_RANK, RANK_NAME, TO_CHAR(TER_TERMINATION_DATE,'DD-MM-YYYY'), " + 
				" TO_CHAR(TER_LAST_DAY_WORKED,'DD-MM-YYYY'), TER_DIFFER_DATE_EXPLAIN, TER_TERMINATION_TYPE, " + 
				" TER_TERMINATION_REASON, TER_ELIGIBLE_TO_REHIRE, TER_IF_NO_PRO_EXPLAIN,TER_VACATION_HOURS, " + 
				" TER_COMMENTS, TER_COMPLETED_BY, OFFC2.EMP_FNAME||' '||OFFC2.EMP_LNAME, " + 
				" TO_CHAR(TER_COMPLETED_DATE,'DD-MM-YYYY  HH24:MI'), '_'||TER_STATUS,TER_IF_OTHER_REASON, TER_TRACKING_NUMBER ,MGR.EMP_TOKEN, MGR.EMP_FNAME||' '||MGR.EMP_MNAME||' '||MGR.EMP_LNAME ,TER_MANG_NAME  " + 
				" FROM HRMS_D1_TERMINATION " + 
				" INNER JOIN HRMS_EMP_OFFC OFFC1 ON (OFFC1.EMP_ID = HRMS_D1_TERMINATION.TER_EMP_ID) " + 
				" LEFT JOIN  HRMS_RANK ON (HRMS_RANK.RANK_ID = OFFC1.EMP_RANK) " + 
				" LEFT JOIN HRMS_D1_DEPARTMENT ON (HRMS_D1_DEPARTMENT.DEPT_ID = OFFC1.EMP_DEPT_NO) " + 
				" INNER JOIN HRMS_EMP_OFFC OFFC2 ON (OFFC2.EMP_ID = HRMS_D1_TERMINATION.TER_COMPLETED_BY) " + 
				" LEFT JOIN HRMS_EMP_OFFC MGR ON (MGR.EMP_ID = HRMS_D1_TERMINATION.TER_MANG_NAME) WHERE TER_ID = " + terminationHiddenID;

		final Object[][] viewQueryObj = this.getSqlModel().getSingleResult(
				viewQuery);
		if (viewQueryObj != null && viewQueryObj.length > 0) {
			trBean.setTerminationID(Utility.checkNull(String.valueOf(viewQueryObj[0][0])));
			trBean.setEmployeeNumber(Utility.checkNull(String.valueOf(viewQueryObj[0][1])));
			trBean.setEmployeeToken(Utility.checkNull(String.valueOf(viewQueryObj[0][2])));
			trBean.setEmployeeName(Utility.checkNull(String.valueOf(viewQueryObj[0][3])));
			trBean.setHomeAddress(Utility.checkNull(String.valueOf(viewQueryObj[0][4])));
			trBean.setZipCode(Utility.checkNull(String.valueOf(viewQueryObj[0][5])));
			trBean.setCityName(Utility.checkNull(String.valueOf(viewQueryObj[0][6])));
			trBean.setState(Utility.checkNull(String.valueOf(viewQueryObj[0][7])));
			trBean.setDeptID(Utility.checkNull(String.valueOf(viewQueryObj[0][8])));
			trBean.setDepetNumber(Utility.checkNull(String.valueOf(viewQueryObj[0][9])));
			trBean.setExecutiveID(Utility.checkNull(String.valueOf(viewQueryObj[0][10])));
			trBean.setExecutiveName(Utility.checkNull(String.valueOf(viewQueryObj[0][11])));
			trBean.setTerminationDate(Utility.checkNull(String.valueOf(viewQueryObj[0][12])));
			trBean.setLastDayWorkDate(Utility.checkNull(String.valueOf(viewQueryObj[0][13])));
			trBean.setIfTerDateAndLastDayWorkDateDiffer(Utility.checkNull(String.valueOf(viewQueryObj[0][14])));

			if ("V".equals(Utility.checkNull(String.valueOf(viewQueryObj[0][15])))) {
				trBean.setTerminationType(this.voStr);
				trBean.setTerminationTypeRadioOptionValue(this.voStr);
				trBean.setVoluntaryTerminationReason(Utility.checkNull(String.valueOf(viewQueryObj[0][16])));
			} else if ("N".equals(Utility.checkNull(String.valueOf(viewQueryObj[0][15])))) {
				trBean.setTerminationType(this.ivoStr);
				trBean.setTerminationTypeRadioOptionValue(this.ivoStr);
				trBean.setInVoluntaryTerminationReason(Utility.checkNull(String.valueOf(viewQueryObj[0][16])));
			} else {
				trBean.setTerminationType(this.otStr);
				trBean.setTerminationTypeRadioOptionValue(this.otStr);
				trBean.setOtherTerminationReason(Utility.checkNull(String.valueOf(viewQueryObj[0][16])));
			}

			trBean.setEligibleToRehire(Utility.checkNull(String.valueOf(viewQueryObj[0][17])));
			trBean.setIfNoOrProvisional(Utility.checkNull(String.valueOf(viewQueryObj[0][18])));
			trBean.setVacationHrsTaken(Utility.checkNull(String.valueOf(viewQueryObj[0][19])));
			trBean.setCommentsEntered(Utility.checkNull(String.valueOf(viewQueryObj[0][20])));
			trBean.setCompletedByID(Utility.checkNull(String.valueOf(viewQueryObj[0][21])));
			trBean.setCompletedByName(Utility.checkNull(String.valueOf(viewQueryObj[0][22])));
			trBean.setCompletedDate(Utility.checkNull(String.valueOf(viewQueryObj[0][23])));
			trBean.setStatus(Utility.checkNull(String.valueOf(viewQueryObj[0][24])));
			trBean.setIfOtherTerminationReasonTextArea(Utility.checkNull(String.valueOf(viewQueryObj[0][25])));
			trBean.setTrackingNumber(Utility.checkNull(String.valueOf(viewQueryObj[0][26])));
			trBean.setManagerToken(Utility.checkNull(String.valueOf(viewQueryObj[0][27])));
			trBean.setManagerName(Utility.checkNull(String.valueOf(viewQueryObj[0][28])));
			trBean.setManagerCode(Utility.checkNull(String.valueOf(viewQueryObj[0][29])));
		}
	}

	/**
	 * Method : deleteRecord. Purpose : Used to delete record
	 * 
	 * @param trBean :
	 *            Instance of Termination
	 * @return boolean
	 */
	public boolean deleteRecord(final Termination trBean) {
		boolean result = false;
		try {
			final String deleteQuery = "DELETE FROM HRMS_D1_TERMINATION WHERE TER_ID = " + trBean.getTerminationID();
			result = this.getSqlModel().singleExecute(deleteQuery);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Method : getApproverCommentList. Purpose : Used to check whether current
	 * login user is HR person or not
	 * @param logInUserID : current login user id
	 * @return boolean
	 */
	public boolean isHRandPayRoll(final String logInUserID) {
		final String isHRandPayRollQuery = " SELECT * FROM HRMS_D1_APPROVAL_SETTINGS " + 
				" WHERE APP_SETTINGS_TYPE IN('H','P') AND APP_SETTINGS_EMP_ID = " + logInUserID;
		final Object[][] isHRandPayRollObj = this.getSqlModel().getSingleResult(isHRandPayRollQuery);
		if (isHRandPayRollObj != null && isHRandPayRollObj.length > 0) {
			return true;
		}
		return false;
	}

	/**
	 * Method : sendForApprovalFunction. Purpose : Used to send application for
	 * approval
	 * 
	 * @param trBean :
	 *            Instance of Termination
	 * @param status :
	 *            application status
	 * @return boolean
	 */
	public boolean sendForApprovalFunction(final Termination trBean,
			final String status) {
		boolean result = false;
		if ("".equals(trBean.getTerminationID().trim())) {
			result = this.saveRecords(trBean);
		} else {
			result = this.updateRecords(trBean);
		}
		return result;
	}

	/**
	 * Method : getApproverCommentList. Purpose : Used to get approver comments
	 * 
	 * @param trBean :
	 *            Instance of Termination
	 * @param applicationId :
	 *            application code
	 */
	public void getApproverCommentList(final Termination trBean,
			final String applicationId) {
		final String apprCommentListSql = " SELECT OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME, TER_COMMENTS, " + 
				" TO_CHAR(TER_APPROVED_DATE, 'DD-MM-YYYY') AS APPROVED_DATE, " + 
				" DECODE(TER_STATUS, 'P', 'Pending', 'A', 'Approved', 'F', 'Forwarded', 'X', 'Cancellation Approved', 'R', 'Rejected', 'B', 'Sent Back', 'Z', 'Cancellation Rejected') AS STATUS " + 
				" FROM HRMS_D1_TER_DATA_PATH " + 
				" INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = HRMS_D1_TER_DATA_PATH.TER_APPROVER) " + 
				" WHERE TER_ID = " + applicationId +
				" ORDER BY HRMS_D1_TER_DATA_PATH.TER_ID DESC";

		final Object[][] apprCommentListObj = this.getSqlModel().getSingleResult(apprCommentListSql);
		final List<Termination> approverList = new ArrayList<Termination>(apprCommentListObj.length);
		if (apprCommentListObj != null && apprCommentListObj.length > 0) {
			trBean.setApproverCommentFlag(true);
			for (int i = 0; i < apprCommentListObj.length; i++) {
				final Termination innerBean = new Termination();
				innerBean.setApprName(Utility.checkNull(String.valueOf(apprCommentListObj[i][0])));
				innerBean.setApprComments(Utility.checkNull(String.valueOf(apprCommentListObj[i][1])));
				innerBean.setApprDate(Utility.checkNull(String.valueOf(apprCommentListObj[i][2])));
				innerBean.setApprStatus(Utility.checkNull(String.valueOf(apprCommentListObj[i][3])));
				approverList.add(innerBean);
			}
			trBean.setApproverCommentList(approverList);
		}
	}

}
