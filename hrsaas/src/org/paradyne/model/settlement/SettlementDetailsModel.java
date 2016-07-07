package org.paradyne.model.settlement;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.settlement.SettlementDetails;
import org.paradyne.lib.DateUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.leave.LeavePolicyModel;
import org.paradyne.model.payroll.NonIndustrialSalaryModel;
import org.paradyne.model.payroll.SalaryProcessModel;
import org.paradyne.model.payroll.incometax.CommonTaxCalculationModel;

/**
 * 
 * @author Reeba Joseph Date: 02-July-2009
 * 
 */

public class SettlementDetailsModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(SettlementDetailsModel.class);
	NumberFormat formatter = new DecimalFormat("#0.00");
	Vector combinedMonthList = new Vector();

	public void callSettlementList(SettlementDetails settleDetails,
			String status, HttpServletRequest request, String divAccess) {
		String listQuery = "";
		logger.info("Status            :" + status);

		if (status.equals("P")) {
			listQuery = " SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME NAME, HRMS_EMP_OFFC.EMP_ID,TO_CHAR(HRMS_RESIGN.RESIGN_DATE,'DD-MM-YYYY') RESIGN, "
					+ " TO_CHAR(SETTL_SETTLDT,'DD-MM-YYYY') SETTL,TO_CHAR(HRMS_RESIGN.RESIGN_SEPR_DATE,'DD-MM-YYYY') SEPAR, "
					+ " HRMS_RESIGN.RESIGN_CODE,HRMS_SETTL_HDR.SETTL_CODE,RESIGN_NOTICE_PERIOD,SETTL_LOCKFLAG,RESIGN_NOTICE_STATUS "
					+ " FROM HRMS_RESIGN "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_RESIGN.RESIGN_EMP = HRMS_EMP_OFFC.EMP_ID) "
					+ " LEFT JOIN HRMS_SETTL_HDR on(HRMS_SETTL_HDR.SETTL_RESGNO=HRMS_RESIGN.RESIGN_CODE AND HRMS_SETTL_HDR.SETTL_LOCKFLAG IS NULL) ";

			listQuery += divAccess
					+ "  AND RESIGN_WITHDRAWN = 'N' AND RESIGN_APPL_STATUS='Y' AND HRMS_RESIGN.RESIGN_CODE NOT IN(SELECT HRMS_SETTL_HDR.SETTL_RESGNO FROM HRMS_SETTL_HDR) ";
		}
		if (status.equals("I")) {
			listQuery = " SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME NAME, "
					+ " HRMS_EMP_OFFC.EMP_ID,TO_CHAR(HRMS_RESIGN.RESIGN_DATE,'DD-MM-YYYY') RESIGN, "
					+ " TO_CHAR(SETTL_SETTLDT,'DD-MM-YYYY') SETTL,TO_CHAR(HRMS_RESIGN.RESIGN_SEPR_DATE,'DD-MM-YYYY') SEPAR, "
					+ " HRMS_RESIGN.RESIGN_CODE,HRMS_SETTL_HDR.SETTL_CODE,SETTL_NOTICEDAYS,SETTL_LOCKFLAG,SETTL_NOTICEMTH "
					+ " FROM HRMS_SETTL_HDR "
					+ " LEFT JOIN HRMS_RESIGN on(HRMS_SETTL_HDR.SETTL_RESGNO=HRMS_RESIGN.RESIGN_CODE) "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_RESIGN.RESIGN_EMP = HRMS_EMP_OFFC.EMP_ID) "
					+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) ";
			listQuery += divAccess
					+ " AND HRMS_SETTL_HDR.SETTL_LOCKFLAG = 'N'   ORDER BY HRMS_RESIGN.RESIGN_DATE DESC";
		}
		if (status.equals("F")) {
			listQuery = " SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME NAME, "
					+ " HRMS_EMP_OFFC.EMP_ID,TO_CHAR(HRMS_RESIGN.RESIGN_DATE,'DD-MM-YYYY') RESIGN, "
					+ " TO_CHAR(SETTL_SETTLDT,'DD-MM-YYYY') SETTL,TO_CHAR(HRMS_RESIGN.RESIGN_SEPR_DATE,'DD-MM-YYYY') SEPAR, "
					+ " HRMS_RESIGN.RESIGN_CODE,HRMS_SETTL_HDR.SETTL_CODE,SETTL_NOTICEDAYS,SETTL_LOCKFLAG,SETTL_NOTICEMTH "
					+ " FROM HRMS_SETTL_HDR "
					+ " LEFT JOIN HRMS_RESIGN on(HRMS_SETTL_HDR.SETTL_RESGNO=HRMS_RESIGN.RESIGN_CODE) "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_RESIGN.RESIGN_EMP = HRMS_EMP_OFFC.EMP_ID) "
					+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) ";

			listQuery += divAccess
					+ " AND  HRMS_SETTL_HDR.SETTL_LOCKFLAG = 'Y' ORDER BY SETTL_SETTLDT DESC";
		}
System.out.println("=======listQuery================="+divAccess);
		Object[][] result = getSqlModel().getSingleResult(listQuery);
		if (result != null && result.length > 0)
			settleDetails.setModeLength("true");
		else
			settleDetails.setModeLength("false");
		String[] pageIndex = Utility.doPaging(settleDetails.getMyPage(),
				result.length, 20);
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
			settleDetails.setMyPage("1");
		ArrayList<Object> tableList = new ArrayList<Object>();
		if (result.length > 0) {
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {
				SettlementDetails bean1 = new SettlementDetails();
				bean1.setEmpTokenItt(checkNull(String.valueOf(result[i][0])));
				bean1.setEmpNameItt(checkNull(String.valueOf(result[i][1])));
				bean1.setEmpCodeItt(checkNull(String.valueOf(result[i][2])));
				if (status.equals("P") || status.equals("I")) {
					bean1.setResignDateItt(checkNull(String
							.valueOf(result[i][3])));
					bean1.setResignCodeItt(checkNull(String
							.valueOf(result[i][6])));
				} else {
					bean1.setResignDateItt(checkNull(String
							.valueOf(result[i][3])));
					bean1.setResignCodeItt(checkNull(String
							.valueOf(result[i][6])));
					bean1
							.setSettDateItt(checkNull(String
									.valueOf(result[i][4])));
					bean1
							.setSettCodeItt(checkNull(String
									.valueOf(result[i][7])));
				}
				bean1.setSeparDateItt(checkNull(String.valueOf(result[i][5])));
				bean1
						.setNoticePeriodItt(checkNull(String
								.valueOf(result[i][8])));
				// bean1.setNoticeStatusItt(checkNull(String
				// .valueOf(result[i][10])));
				bean1.setNoticeStatusItt("D");

				if (String.valueOf(result[i][9]).equals("N"))
					bean1.setStatusItt("I");
				else if (String.valueOf(result[i][9]).equals("Y"))
					bean1.setStatusItt("F");
				else
					bean1.setStatusItt("P");
				tableList.add(bean1);
			}
			settleDetails.setTableList(tableList);
			settleDetails.setTotalRecords(String.valueOf(result.length));
		} else {
			settleDetails.setNoData("true");// No data to display message shown
			settleDetails.setTableList(null);
			settleDetails.setTotalRecords(String.valueOf(result.length));
		}

	}

	/**
	 * @param request
	 * @param String
	 *            Resign code
	 * @return void for getting details of employee from employee office and
	 *         resignation details before saving record.
	 * 
	 */

	public String[] getResgDetails(SettlementDetails settleDetails,
			HttpServletRequest request) {

		String[] detailObj = new String[1];
		try {
			String query = " SELECT distinct EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,TO_CHAR(HRMS_RESIGN.RESIGN_DATE,'DD-MM-YYYY'),"
					+ " NVL(CENTER_NAME,' '),NVL(RANK_NAME,' '),"
					+ " TO_CHAR(HRMS_RESIGN.RESIGN_SEPR_DATE,'DD-MM-YYYY'),HRMS_RESIGN.RESIGN_CODE ,"
					+ " NVL(RESIGN_NOTICE_PERIOD,0),NVL(RESIGN_NOTICE_STATUS,' '),RESIGN_EMP,HRMS_SETTL_HDR.SETTL_CODE,TO_CHAR(HRMS_SETTL_HDR.SETTL_SEPRDT,'DD-MM-YYYY'), "
					+ " DECODE(NVL(TO_CHAR(HRMS_SETTL_HDR.SETTL_LOCKFLAG),'R'),'R','Not Processed','N','Processed','Locked')"
					+ " FROM HRMS_RESIGN"
					+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_RESIGN.RESIGN_EMP = HRMS_EMP_OFFC.EMP_ID) "
					+ " LEFT JOIN  HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
					+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
					+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
					+ " LEFT JOIN HRMS_SETTL_HDR on(HRMS_SETTL_HDR.SETTL_RESGNO=HRMS_RESIGN.RESIGN_CODE)"
					+ "  WHERE  HRMS_RESIGN.RESIGN_CODE= "
					+ settleDetails.getResignCode();

			Object[][] result = getSqlModel().getSingleResult(query);

			for (int i = 0; i < result.length; i++) {
				settleDetails.setEmpToken(checkNull(String
						.valueOf(result[i][0])));
				settleDetails.setEmpName(checkNull(String.valueOf(result[i][1])
						.trim()));

				settleDetails.setDesgn(checkNull(String.valueOf(result[i][4])
						.trim()));

				settleDetails.setBranch(checkNull(String.valueOf(result[i][3])
						.trim()));
				settleDetails.setSepDate(checkNull(String.valueOf(result[i][5])
						.trim()));
				settleDetails.setResignDate(checkNull(String.valueOf(
						result[i][2]).trim()));
				settleDetails.setResignCode(checkNull(String.valueOf(
						result[i][6]).trim()));
				settleDetails.setNoticePeriod(String.valueOf(result[i][7])
						.trim());
				settleDetails.setNoticeStatus(checkNull(String.valueOf(
						result[i][8]).trim()));

				settleDetails.setNoticePeriodStatus(checkNull(String.valueOf(
						result[i][8]).trim()));
				settleDetails.setEmpCode(checkNull(String.valueOf(result[i][9])
						.trim()));
				settleDetails.setSettCode(checkNull(String.valueOf(
						result[i][10]).trim()));
				settleDetails.setIsSettled(checkNull(String.valueOf(
						result[i][12]).trim()));

			}// end for loop
			// settleDetails.setLockFlag("");
			settleDetails.setModePayment("");
			settleDetails.setBank("");
			settleDetails.setBankMicrId("");
			settleDetails.setChequeno("");
			settleDetails.setChequeDate("");
			settleDetails.setPreparedby("");
			settleDetails.setPrepbyCode("");
			settleDetails.setPrepbyToken("");
			settleDetails.setAccCheck("");
			settleDetails.setAccChkCode("");
			settleDetails.setAccChkToken("");
			settleDetails.setCheckedby("");
			settleDetails.setChkbyCode("");
			settleDetails.setChkbyToken("");
			settleDetails.setHandedOver("");
			settleDetails.setHandOverCode("");
			settleDetails.setHandOverToken("");
			settleDetails.setPermSettle("");
			settleDetails.setProcessFlag("");
			settleDetails.setIsSettled("");
			settleDetails.setEligibleMth("");
			settleDetails.setComments("");
			settleDetails.setGratuity("");
			settleDetails.setReimburse("");
			settleDetails.setDeduct("");
			settleDetails.setSettleAmt("");
			settleDetails.setOhMonth("");
			settleDetails.setOhYear("");
			settleDetails.setCalDays("");
			settleDetails.setCalType("");
			settleDetails.setPayByMnth("");
			settleDetails.setPayByYr("");
			settleDetails.setCalPayByDays("");
			settleDetails.setCalPayByType("");
			settleDetails.setListCode("");
			settleDetails.setPayListCode("");
			settleDetails.setLeaveLength("");
			settleDetails.setCreditCode("");
			settleDetails.setCreditHead("");
			settleDetails.setDebitCode("");
			settleDetails.setDebitHead("");
			settleDetails.setLedgerCode("");
			settleDetails.setMonth("");
			settleDetails.setYear("");
			settleDetails.setMonth1("");
			settleDetails.setYear1("");
			settleDetails.setTypeCheck("");
			settleDetails.setDaysCheck("");
			settleDetails.setNetOnHold("");
			settleDetails.setLeaveId("");
			settleDetails.setLeaveName("");
			settleDetails.setClBal("");
			settleDetails.setTotal("");
			settleDetails.setTotalAmt("");
			settleDetails.setIsFlag("false");
			// settleDetails.setNextFlag(false);
			// settleDetails.setAfterNextFlag(false);
			settleDetails.setOnholdFlag(false);
			settleDetails.setSettDtlCode("");
			String sysQuery = " SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
			Object[][] sysObj = getSqlModel().getSingleResult(sysQuery);
			int k = 0;
			if (k < sysObj.length) {
				settleDetails.setSettDate(String.valueOf(sysObj[0][0]));
			}// end if

		} catch (Exception e) {
			e.printStackTrace();
		}
		return detailObj;
	}

	/**
	 * check pay by company/pay by employee
	 * 
	 * @param settleDetails
	 * @param noticePeriod
	 * @param separDate
	 * @param resgDate
	 * @return Integer value (-1 or 1)
	 */
	public int getPayByFlag(SettlementDetails settleDetails, String resgDate,
			String separDate, String noticePeriod) {
		/*
		 * getNoticeDate() for getting notice date according to resignation
		 * date,seperation date and notice period
		 */
		String noticeDate = new DateUtility()
				.getNoticeDate(resgDate, settleDetails.getNoticeStatus(),
						Integer.parseInt(noticePeriod));
		logger.info("noticeDate       :" + noticeDate);
		/*
		 * CheckDate()returns 0 or 1 if seperation date is less than notice date
		 * otherwise it returns -1
		 */
		int payByFlag = new Utility().checkDate(noticeDate, separDate);
		logger.info("payByFlag       :" + payByFlag);
		return payByFlag;
	}

	/**
	 * get days for pay by company and employee
	 * 
	 * @param settleDetails
	 * @param noticePeriod
	 * @param separDate
	 * @param resgDate
	 * @return ArrayList
	 */
	public ArrayList<Object> getPayByFields(SettlementDetails settleDetails,
			String resgDate, String separDate, String noticePeriod,
			String empCode) {
		String joinYrQuery = "SELECT TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') FROM HRMS_EMP_OFFC WHERE EMP_ID ="
				+ empCode;
		Object[][] joinYrObj = getSqlModel().getSingleResult(joinYrQuery);
		settleDetails.setJoinDate("" + joinYrObj[0][0]);

		int resignYear = Integer.parseInt(resgDate.substring(6, 10));
		int resignMonth = Integer.parseInt(resgDate.substring(3, 5));
		int seperateYear = Integer.parseInt(separDate.substring(6, 10));
		int seperateMonth = Integer.parseInt(separDate.substring(3, 5));
		int seperateDay = Integer.parseInt(separDate.substring(0, 2));
		String monthObj = "";

		if (seperateMonth < 10) {
			monthObj = 0 + String.valueOf(seperateMonth);
		} else
			monthObj = String.valueOf(seperateMonth);

		ArrayList<Object> list = new ArrayList<Object>();

		DateUtility c = new DateUtility();
		// logger.info("Resignation date :" + resgDate);
		// logger.info("Notice status :" + settleDetails.getNoticeStatus());
		// logger.info("Notice period :" + noticePeriod);
		String noticeDate = c.getNoticeDate(resgDate, settleDetails
				.getNoticeStatus(), Integer.parseInt(noticePeriod));
		// logger.info("Notice date :" + noticeDate);
		settleDetails.setNoticeDate(noticeDate);
		// logger.info("Separation date :" + settleDetails.getSepDate());
		String[][] rangeObj = null;
		try {
			rangeObj = c.getDatesBetween(separDate, noticeDate);
		} catch (Exception e) {
			// TODO: handle exception
		}
		/**
		 * If resign and separation month & year are same set separation
		 * month-year & separation days as pay by company and set remaining days
		 * (notice date - separation date) as pay by employee.
		 */
		if (resignYear == seperateYear && resignMonth == seperateMonth) {
			int payByDays = isSalayProcessed(seperateYear, seperateMonth,
					empCode);
			if (payByDays > 0) {
				// logger.info("IN co type... days..." + seperateDay);
				SettlementDetails bean = new SettlementDetails();
				bean.setPayFlag(true);
				bean.setPayByMnth(String.valueOf(seperateMonth));
				bean.setPayByYr(String.valueOf(seperateYear));

				bean.setPayMonth(String.valueOf(seperateYear) + monthObj);
				bean.setCalPayByDays(String.valueOf(seperateDay));
				bean.setMaxDays(String.valueOf(30));
				bean.setCalPayByType("CO");
				list.add(bean);
			}
			int compareDate = new Utility().checkDate(settleDetails
					.getSepDate(), noticeDate);
			// if separation date greater than notice date
			if (compareDate == 1) {
				// logger.info("separ date greater than notice date");
			} else {
				// logger.info("separ date less than/equal to notice date");
				for (int i = 0; i < rangeObj.length; i++) {

					String[] splitObj = rangeObj[i][1].split("-");
					String monthObj1 = "";
					if (Integer.parseInt(splitObj[1]) < 10)
						monthObj1 = 0 + splitObj[1];
					else
						monthObj1 = splitObj[1];

					SettlementDetails bean = new SettlementDetails();
					if (i == 0) {
						payByDays = isSalayProcessed(Integer
								.parseInt(splitObj[2]), Integer
								.parseInt(splitObj[1]), empCode);
						if (payByDays > 0) {
							/*
							 * int setDays = Integer.parseInt(rangeObj[i][2]) -
							 * Integer.parseInt(rangeObj[i][0].substring(0, 2));
							 */
							int setDays = Integer.parseInt(rangeObj[i][1]
									.substring(0, 2))
									- Integer.parseInt(rangeObj[i][0]
											.substring(0, 2));
							logger.info("in if i = 0 setDays......" + setDays);
							if (setDays != 0) {
								bean.setPayByMnth(String.valueOf(Integer
										.parseInt(splitObj[1])));
								bean.setPayByYr(splitObj[2]);
								bean.setPayMonth(String.valueOf(splitObj[2])
										+ monthObj1);
								bean.setCalPayByDays(String.valueOf(setDays));
								Calendar cal = Calendar.getInstance();
								cal.setTime(Utility.getDate("01-" + splitObj[1]
										+ "-" + splitObj[2]));
								bean.setMaxDays(String.valueOf(30));
								bean.setCalPayByType("EM");
								list.add(bean);
							}
						}

					} else {
						String year = rangeObj[i][1].substring(rangeObj[i][1]
								.length() - 4, rangeObj[i][1].length());
						payByDays = isSalayProcessed(Integer.parseInt(year),
								Integer.parseInt(splitObj[1]), empCode);
						if (payByDays > 0) {
							int setDays = Integer.parseInt(rangeObj[i][1]
									.substring(0, 2));
							logger.info("if i not 0 setDaysE......" + setDays);
							bean.setPayByMnth(String.valueOf(Integer
									.parseInt(splitObj[1])));
							bean.setPayByYr(year);
							bean.setPayMonth(String.valueOf(year) + monthObj1);
							bean.setCalPayByDays(String.valueOf(setDays));
							Calendar cal = Calendar.getInstance();
							cal.setTime(Utility.getDate("01-" + splitObj[1]
									+ "-" + year));
							bean.setMaxDays(String.valueOf(30));
							bean.setCalPayByType("EM");
							list.add(bean);
						}
					}

				}
			}
			settleDetails.setTypeList(list);

		} else {
			int prevMonth = 0;
			int prevYear = 0;
			int payByDays = 0;
			// to check if pay by company or employee
			int payByFlag = getPayByFlag(settleDetails, resgDate, separDate,
					noticePeriod);
			int new_seperateMonth = seperateMonth;
			int new_seperateYear = seperateYear;
			payByDays = seperateDay;
			SettlementDetails bean = new SettlementDetails();
			/**
			 * setting separation month-year and days as pay by company
			 */
			payByDays = isSalayProcessed(seperateYear, seperateMonth, empCode);
			if (payByDays > 0) {// not onHold
				bean.setPayFlag(true);
				bean.setPayByMnth(String.valueOf(seperateMonth));
				bean.setPayByYr(String.valueOf(seperateYear));
				bean.setPayMonth(String.valueOf(seperateYear) + monthObj);
				bean.setCalPayByDays(String.valueOf(seperateDay));
				Calendar cal = Calendar.getInstance();
				cal.setTime(Utility.getDate("01-" + seperateMonth + "-"
						+ seperateYear));
				bean.setMaxDays(String.valueOf(30));
				bean.setCalPayByType("CO");
				list.add(bean);
			}

			try {
				if (payByFlag == 1) {// pay by employee
					for (int i = 0; i < rangeObj.length; i++) {
						String[] splitObj = rangeObj[i][1].split("-");
						String monthObj2 = "";
						if (Integer.parseInt(splitObj[1]) < 10)
							monthObj2 = 0 + splitObj[1];
						else
							monthObj2 = splitObj[1];
						bean = new SettlementDetails();
						int setDays = 0;
						if (i == 0) {
							System.out.print("----if----"
									+ rangeObj[i][0].substring(0, 2));
							System.out.print("||" + splitObj[1]);
							System.out.println("||" + splitObj[2] + "||"
									+ rangeObj[i][2]);

							// setDays =
							// Integer.parseInt(rangeObj[i][2])-Integer.parseInt(rangeObj[i][0].substring(0,
							// 2));
							setDays = Integer.parseInt(rangeObj[i][1]
									.substring(0, 2))
									- Integer.parseInt(rangeObj[i][0]
											.substring(0, 2));
						} else {
							System.out.println("--else----"
									+ rangeObj[i][1].substring(0, 2)
									+ "||"
									+ splitObj[1]
									+ "||"
									+ rangeObj[i][1].substring(rangeObj[i][1]
											.length() - 4, rangeObj[i][1]
											.length()) + "||" + rangeObj[i][2]);

							setDays = Integer.parseInt(rangeObj[i][1]
									.substring(0, 2));
						}
						String year = rangeObj[i][1].substring(rangeObj[i][1]
								.length() - 4, rangeObj[i][1].length());
						bean.setPayByMnth(String.valueOf(Integer
								.parseInt(splitObj[1])));
						bean.setPayByYr(year);
						bean.setPayMonth(String.valueOf(year) + monthObj2);
						bean.setCalPayByDays(String.valueOf(setDays));
						Calendar cal = Calendar.getInstance();
						cal.setTime(Utility.getDate("01-" + splitObj[1] + "-"
								+ year));
						bean.setMaxDays(String.valueOf(30));
						bean.setCalPayByType("EM");
						list.add(bean);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			do {
				logger.info("in do-while");
				prevMonth = getPrevMonth(new_seperateMonth);
				prevYear = getPrevYear(new_seperateMonth, new_seperateYear);
				new_seperateMonth = prevMonth;
				new_seperateYear = prevYear;

				String monthObj3 = "";
				if (prevMonth < 10)
					monthObj3 = 0 + String.valueOf(prevMonth);
				else
					monthObj3 = String.valueOf(prevMonth);

				payByDays = isSalayProcessed(prevYear, prevMonth, empCode);
				if (payByDays > 0) {// not onHold
					bean = new SettlementDetails();
					/**
					 * setting previous months-year and days as pay by company
					 */
					bean.setPayFlag(true);
					bean.setPayByMnth(String.valueOf(prevMonth));
					bean.setPayByYr(String.valueOf(prevYear));
					bean.setPayMonth(String.valueOf(prevYear) + monthObj3);
					bean.setCalPayByDays(String.valueOf(payByDays));
					Calendar cal = Calendar.getInstance();
					cal.setTime(Utility.getDate("01-" + prevMonth + "-"
							+ prevYear));
					bean.setMaxDays(String.valueOf(30));
					bean.setCalPayByType("CO");
					list.add(bean);

				}

			} while (prevMonth != resignMonth || prevYear != resignYear);
			settleDetails.setTypeList(list);
		}
		try {
			list = sortList(list, settleDetails);
			settleDetails.setTypeList(list);
		} catch (Exception e) {
			logger.error("Error while sorting list :: ", e);
		}
		return list;
	}

	/**
	 * CHECK IF SALARAY PROCESSED (ONHOLD/PAID)
	 * 
	 * @param year
	 * @param month
	 * @param empCode
	 * @return Integer value ( 0 or 1)
	 */
	public int isSalayProcessed(int year, int month, String empCode) {
		int shortPay = 0;
		Object[][] onholdMthObj = null;
		String onholdMthQue = "select distinct HRMS_SALARY_"
				+ String.valueOf(year) + ".SAL_LEDGER_CODE "
				+ " from HRMS_SALARY_" + String.valueOf(year)
				+ " inner join hrms_salary_ledger on HRMS_SALARY_"
				+ String.valueOf(year)
				+ ".SAL_LEDGER_CODE =  hrms_salary_ledger.LEDGER_CODE"
				+ " where HRMS_SALARY_" + String.valueOf(year) + ".EMP_ID ="
				+ empCode + "   and LEDGER_MONTH=" + String.valueOf(month)
				+ " and LEDGER_year=" + String.valueOf(year);

		onholdMthObj = getSqlModel().getSingleResult(onholdMthQue);
		if (onholdMthObj != null && onholdMthObj.length > 0) {
			// logger.info("......." + onholdMthObj.length);
			return 0;
		} else {

			Calendar c = Calendar.getInstance();
			c.setTime(Utility.getDate("01-" + month + "-" + year));
			shortPay = c.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
			// logger.info("Short pay........." + shortPay);
			return shortPay;
		}

	}

	public ArrayList<Object> sortList(ArrayList<Object> list,
			SettlementDetails settleDetails) {
		// SORTING THE LIST OF PAY BY MONTHS
		String[][] finalObj = new String[list.size()][6];
		int objCount = 0;
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			SettlementDetails finalBean = (SettlementDetails) iterator.next();
			finalObj[objCount][0] = finalBean.getPayByMnth();// month
			finalObj[objCount][1] = finalBean.getPayByYr();// year
			finalObj[objCount][2] = finalBean.getPayMonth();// hidden
			// logger.info("YrMonthObj before
			// Sorting...."+finalObj[objCount][2]);
			finalObj[objCount][3] = finalBean.getCalPayByDays();// pay days
			finalObj[objCount][4] = finalBean.getMaxDays();// calc days
			finalObj[objCount][5] = finalBean.getCalPayByType();// type
			objCount++;

		}
		for (int i = 0; i < finalObj.length - 1; i++) {

			for (int j = 0; j < finalObj.length - 1 - i; j++) {
				int a = Integer.parseInt(String.valueOf(finalObj[j][2]));
				int b = Integer.parseInt(String.valueOf(finalObj[j + 1][2]));

				if (b < a) {

					String obj[][] = new String[1][6];
					obj[0][0] = finalObj[j + 1][0];
					obj[0][1] = finalObj[j + 1][1];
					obj[0][2] = finalObj[j + 1][2];
					obj[0][3] = finalObj[j + 1][3];
					obj[0][4] = finalObj[j + 1][4];
					obj[0][5] = finalObj[j + 1][5];

					finalObj[j + 1][0] = finalObj[j][0];
					finalObj[j + 1][1] = finalObj[j][1];
					finalObj[j + 1][2] = finalObj[j][2];
					finalObj[j + 1][3] = finalObj[j][3];
					finalObj[j + 1][4] = finalObj[j][4];
					finalObj[j + 1][5] = finalObj[j][5];

					finalObj[j][0] = obj[0][0];
					finalObj[j][1] = obj[0][1];
					finalObj[j][2] = obj[0][2];
					finalObj[j][3] = obj[0][3];
					finalObj[j][4] = obj[0][4];
					finalObj[j][5] = obj[0][5];

				}
			}

		}
		// LIST AFTER SORTING
		list = new ArrayList<Object>();
		for (int i = 0; i < finalObj.length; i++) {
			// logger.info("YrMonthObj after Sorting...."+finalObj[i][2]);
			SettlementDetails sortedBean = new SettlementDetails();
			sortedBean.setPayByMnth(finalObj[i][0]);// month
			sortedBean.setPayByYr(finalObj[i][1]);// year
			sortedBean.setPayMonth(finalObj[i][2]);// hidden
			sortedBean.setCalPayByDays(finalObj[i][3]);// pay days
			sortedBean.setMaxDays(finalObj[i][4]);// calc days
			sortedBean.setCalPayByType(finalObj[i][5]);// type

			list.add(sortedBean);
		}
		settleDetails.setTypeList(list);
		return list;
	}

	/**
	 * for retrieving onHold months and paid months
	 * 
	 * @param empCode
	 * @param noticePeriod
	 * @param separDate
	 * @param resgDate
	 * 
	 * @param String
	 *            Resign code
	 * @return void
	 */

	public int getOnholdMonths(SettlementDetails settleDetails,
			ArrayList<Object> list1, String resgDate, String separDate,
			String noticePeriod, String empCode) {
		ArrayList<Object> list = list1;
		try {

			// logger.info("============= in Onhold months ==============");
			String onholdmm = "";
			String joinYrQuery = "SELECT TO_CHAR(EMP_REGULAR_DATE,'YYYY') FROM HRMS_EMP_OFFC WHERE EMP_ID ="
					+ empCode;
			Object[][] joinYrObj = getSqlModel().getSingleResult(joinYrQuery);
			int joinYear = (Integer.parseInt(String.valueOf(joinYrObj[0][0])));
			// logger.info("============= joinYear ==" + joinYear);// 2008
			int yearSep = (Integer.parseInt(separDate.substring(6, 10)));
			// logger.info("============= sepYear ==" + yearSep);// 2009
			int yearResg = (Integer.parseInt(resgDate.substring(6, 10)));
			// logger.info("============= resgYear ==" + yearResg);// 2008
			int monthSep = (Integer.parseInt(separDate.substring(3, 5)));
			// logger.info("============= sepMonth ==" + monthSep);// 1
			int monthResg = (Integer.parseInt(resgDate.substring(3, 5)));
			// logger.info("============= reshMonth ==" + monthResg);// 11

			int yr;
			String mnthS = "", mnthR = "";
			String noMMYY = "There are no onhold months";
			String yrMonthObj = "", monthObj = "";
			if (monthSep < 10) {
				mnthS = 0 + String.valueOf(monthSep);
			} else
				mnthS = String.valueOf(monthSep);
			if (monthResg < 10) {
				mnthR = 0 + String.valueOf(monthResg);
			} else
				mnthR = String.valueOf(monthResg);
			String sepYrMonth = String.valueOf(yearSep) + mnthS;
			String resgYrMonth = String.valueOf(yearResg) + mnthR;
			// logger.info("============= sepYrMonth ==" + sepYrMonth);// 200901
			// logger.info("============= resgYrMonth ==" + resgYrMonth);//
			// 200811

			Object[][] onholdObj = null;
			for (yr = joinYear; yr <= yearSep; yr++) {
				// logger.info("============= Loop within ==" + yr);
				String onholdQue = "";
				try {
					onholdQue = "select distinct HRMS_SALARY_"
							+ yr
							+ ".SAL_LEDGER_CODE,"
							+ " LEDGER_MONTH,"
							+ " LEDGER_year,SAL_TOTAL_CREDIT,SAL_TOTAL_DEBIT,SAL_NET_SALARY ,LEDGER_MONTH MM, SAL_DAYS,SAL_ONHOLD  from HRMS_SALARY_"
							+ yr
							+ " inner join hrms_salary_ledger on HRMS_SALARY_"
							+ yr
							+ ".SAL_LEDGER_CODE =  hrms_salary_ledger.LEDGER_CODE"
							+ " where HRMS_SALARY_" + yr + ".EMP_ID ="
							+ empCode + "  and LEDGER_year<=" + yr
							+ " order by MM ";
					onholdObj = getSqlModel().getSingleResult(onholdQue);
				} catch (Exception e) {
					logger.error("Exception in onhold salary query :" + e);
				}
				if (onholdObj != null && onholdObj.length > 0) {
					for (int j = 0; j < onholdObj.length; j++) {
						SettlementDetails bean = new SettlementDetails();
						onholdmm = String.valueOf(onholdObj[j][1]) + "-"
								+ String.valueOf(onholdObj[j][2]);
						if (Integer.parseInt("" + onholdObj[j][1]) < 10) {
							monthObj = 0 + String.valueOf(onholdObj[j][1]);
						} else
							monthObj = String.valueOf(onholdObj[j][1]);
						yrMonthObj = String.valueOf(onholdObj[j][2]) + monthObj;
						// logger.info("============= yrMonthObj ==" +
						// yrMonthObj);
						bean.setOhMonth(String.valueOf(onholdObj[j][1]));
						bean.setOhYear(String.valueOf(onholdObj[j][2]));
						bean.setCalMonth(yrMonthObj);
						bean.setOhMaxDays("31");
						if (String.valueOf(onholdObj[j][8]).equals("Y")) {
							// SETTING ONHOLD MONTHS
							if (Integer.parseInt(yrMonthObj) <= Integer
									.parseInt(sepYrMonth)) {
								// logger.info("============= In OnHold ==");
								bean.setCalType("OH");
								bean
										.setCalDays(String
												.valueOf(onholdObj[j][7]));
								bean.setOnholdFlag(true);
								list.add(bean);
							}
						} else {
							// SETTING PAID MONTHS
							if (Integer.parseInt(yrMonthObj) <= Integer
									.parseInt(sepYrMonth)
									&& Integer.parseInt(yrMonthObj) >= Integer
											.parseInt(resgYrMonth)) {
								// logger.info("============= In Paid ==");
								bean.setCalType("PD");
								bean
										.setCalDays(String
												.valueOf(onholdObj[j][7]));
								bean.setOnholdFlag(true);
								list.add(bean);
							}
						}
					}
					settleDetails.setTypeList(list);
				} else {
					settleDetails.setOhMonth("");
					settleDetails.setOhYear("");
					settleDetails.setCalDays("");
					settleDetails.setCalType("");
					settleDetails.setOnholdFlag(true);
				}// end else
			}

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return list.size();
	}

	/**
	 * @param year
	 * @param month
	 * @param String
	 *            emp code
	 * @return Object This method returns all credits and debits of onhold
	 *         salary of employee
	 */

	public Vector<Object[][]> calOnholdSalData(SettlementDetails settleDetails,
			HttpServletRequest request) {
		Object[][] finalObj = null;
		Vector<Object[][]> month_year = new Vector<Object[][]>();
		ArrayList<Object[][]> credit_List = new ArrayList<Object[][]>();
		logger.info("Inside calOnholdSalData.....");
		int listSize = Integer.parseInt(settleDetails.getListCode());

		String[] month = request.getParameterValues("hdOhMnth");
		String[] year = request.getParameterValues("hdOhYear");
		String[] ohMaxDays = request.getParameterValues("hdOhMaxDays");
		String[] days = request.getParameterValues("hdcalDays");
		String[] type = request.getParameterValues("hdcalType");

		logger.info("List size..... " + listSize);
		for (int i = 0; i < listSize; i++) {
			logger.info("onhold days....... " + days[i]);
			if (!days[i].equals("0")) {
				if (type[i].equals("OH")) {
					try {
						String query = " select distinct HRMS_SALARY_"
								+ year[i]
								+ ".SAL_LEDGER_CODE, SAL_TOTAL_CREDIT,SAL_TOTAL_DEBIT,SAL_NET_SALARY,SAL_ONHOLD from HRMS_SALARY_"
								+ year[i]
								+ " INNER JOIN HRMS_SALARY_LEDGER ON HRMS_SALARY_"
								+ year[i]
								+ ".SAL_LEDGER_CODE =  hrms_salary_ledger.LEDGER_CODE"
								+ " WHERE HRMS_SALARY_" + year[i] + ".EMP_ID ="
								+ settleDetails.getEmpCode()
								+ " and LEDGER_year<=" + year[i]
								+ " AND LEDGER_MONTH=" + month[i];
						Object[][] data = getSqlModel().getSingleResult(query);
						settleDetails.setNetOnHold(String.valueOf(data[0][3]));
						settleDetails.setLedgerCode(String.valueOf(data[0][0]));
						settleDetails.setMonth(month[i]);
						settleDetails.setYear(year[i]);
						/*
						 * Query for retrieving credits according to onHold
						 * month..
						 */
						String creditQue = "SELECT DISTINCT CREDIT_NAME,NVL(HRMS_SAL_CREDITS_"
								+ year[i]
								+ ".SAL_AMOUNT,0),"
								+ " HRMS_CREDIT_HEAD.CREDIT_CODE||'#'||"+month[i]+"||'#'||"+year[i]+",HRMS_CREDIT_HEAD.CREDIT_CODE FROM HRMS_SALARY_"
								+ year[i]
								+ " INNER JOIN HRMS_SAL_CREDITS_"
								+ year[i]
								+ " ON(HRMS_SALARY_"
								+ year[i]
								+ ".SAL_LEDGER_CODE = HRMS_SAL_CREDITS_"
								+ year[i]
								+ ".SAL_LEDGER_CODE and"
								+ " HRMS_SALARY_"
								+ year[i]
								+ ".EMP_ID = HRMS_SAL_CREDITS_"
								+ year[i]
								+ ".EMP_ID)"
								+ " INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_SAL_CREDITS_"
								+ year[i]
								+ ".SAL_CREDIT_CODE)"
								+ " WHERE HRMS_SALARY_"
								+ year[i]
								+ ".EMP_ID ="
								+ settleDetails.getEmpCode()
								+ " AND HRMS_SALARY_"
								+ year[i]
								+ ".SAL_LEDGER_CODE ="
								+ settleDetails.getLedgerCode()
								+ " ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
						Object[][] creditObj = getSqlModel().getSingleResult(
								creditQue);
						if(creditObj !=null && creditObj.length>0){
							creditObj=Utility.removeColumns(creditObj, new int[]{3});
						}
						/*
						 * Query for retrieving debits according to onHold
						 * month..
						 */
						String debitQue = "SELECT DEBIT_NAME,NVL(HRMS_SAL_DEBITS_"
								+ year[i]
								+ ".SAL_AMOUNT,0),HRMS_DEBIT_HEAD.DEBIT_CODE"
								+ " FROM HRMS_SALARY_"
								+ year[i]
								+ " INNER JOIN HRMS_SAL_DEBITS_"
								+ year[i]
								+ " ON(HRMS_SALARY_"
								+ year[i]
								+ ".SAL_LEDGER_CODE = HRMS_SAL_DEBITS_"
								+ year[i]
								+ ".SAL_LEDGER_CODE and"
								+ " HRMS_SALARY_"
								+ year[i]
								+ ".EMP_ID = HRMS_SAL_DEBITS_"
								+ year[i]
								+ ".EMP_ID)"
								+ " INNER JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_SAL_DEBITS_"
								+ year[i]
								+ ".SAL_DEBIT_CODE)"
								+ " WHERE HRMS_SALARY_"
								+ year[i]
								+ ".EMP_ID ="
								+ settleDetails.getEmpCode()
								+ " AND HRMS_SALARY_"
								+ year[i]
								+ ".SAL_LEDGER_CODE="
								+ settleDetails.getLedgerCode()
								+ " order by HRMS_DEBIT_HEAD.DEBIT_PRIORITY,HRMS_DEBIT_HEAD.DEBIT_CODE";

						Object[][] debitObj = getSqlModel().getSingleResult(
								debitQue);
						int lengthOfObj = 0;
						if (creditObj.length > debitObj.length) {
							lengthOfObj = creditObj.length;
						} else
							lengthOfObj = debitObj.length;
						finalObj = new Object[lengthOfObj][];
						Object[][] mon_yr = new Object[1][8];
						mon_yr[0][0] = month[i];
						mon_yr[0][1] = year[i];
						mon_yr[0][2] = String.valueOf(data[0][1]);
						mon_yr[0][3] = String.valueOf(data[0][2]);
						mon_yr[0][4] = String.valueOf(data[0][3]);
						mon_yr[0][5] = days[i];
						mon_yr[0][6] = type[i];
						mon_yr[0][7] = String.valueOf(data[0][4]);

						if (creditObj.length > 0 && debitObj.length > 0) {
							finalObj = Utility.joinArrays(creditObj, debitObj,
									0, 0);
						} // end if
						else if (creditObj.length > 0 && debitObj.length == 0) {
							finalObj = Utility.joinArrays(creditObj,
									new Object[1][3], 0, 0);
						}// end else if
						else if (debitObj.length > 0 && creditObj.length == 0) {

							finalObj = Utility.joinArrays(new Object[1][3],
									debitObj, 0, 0);
						}// end else if
						else if (creditObj.length == 0 && debitObj.length == 0) {
							finalObj = null;
						}// end else if
						month_year.add(mon_yr);
						if (finalObj != null) {
							credit_List.add(finalObj);
						}// end if
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		/*
		 * Handles credit list in try catch
		 */
		try {

			if (credit_List.size() > 0) {
				request.setAttribute("credit_List", credit_List);
				request.setAttribute("month_year", month_year);
				settleDetails.setProcessFlag("true");
				settleDetails.setOnholdFlag(true);
			}// end if
			else {
				settleDetails.setProcessFlag("false");
				settleDetails.setOnholdFlag(false);
			}// end else
		} catch (Exception e) {
			e.printStackTrace();
		}
		return month_year;
	}

	/**
	 * This method calculates credits and debits of eligible days and short
	 * notice period days according to flag.
	 * 
	 * @param settleDetails
	 * @param request
	 * @param type
	 * @param month
	 * @param year
	 * @param days
	 * @param path
	 * @param maxDays
	 * @param eligibleDays
	 * @param year
	 * @param month
	 * @param type
	 * @return Object
	 */
	public ArrayList<Object[][]> calDaySalary(SettlementDetails settleDetails, HttpServletRequest request, String path, String[] type,
			String[] month, String[] year, String[] eligibleDays, String[] maxDays) {
		int listSize = Integer.parseInt(settleDetails.getPayListCode());
		logger.info("List size  : " + listSize);

		Object[][] rows = null;
		Vector<Object[][]> mon_List = new Vector<Object[][]>();
		ArrayList<Object[][]> rows_List = new ArrayList<Object[][]>();
		ArrayList<SettlementDetails> creditNames = new ArrayList<SettlementDetails>();
		ArrayList<SettlementDetails> debitNames = new ArrayList<SettlementDetails>();

		/*
		 * FOR GETTING CREDIT CODE AND CREDIT ABBR WHICH USED FOR DISPLAYING AS
		 * NAME OF CREDIT ON SCREEN
		 * 
		 */
		Object credit_header[][] = null;
		String selectCredit = "SELECT CREDIT_CODE,  CREDIT_NAME FROM HRMS_CREDIT_HEAD WHERE CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' ORDER BY CREDIT_CODE";
		credit_header = getSqlModel().getSingleResult(selectCredit);
		/*
		 * FOR GETTING DEBIT CODE AND DEBIT ABBR WHICH USED FOR DISPLAYING AS
		 * NAME OF DEBIT ON SCREEN
		 * 
		 */
		Object debit_header[][] = null;
		String selectDebit = "SELECT DEBIT_CODE,  DEBIT_NAME FROM HRMS_DEBIT_HEAD WHERE DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' ORDER BY DEBIT_PRIORITY,DEBIT_CODE ";
		debit_header = getSqlModel().getSingleResult(selectDebit);

		request.setAttribute("creditLength", credit_header);
		request.setAttribute("debitLength", debit_header);

		for (int c = 0; c < credit_header.length; c++) {
			/**
			 * WE ARE USING DYNAMICS HEADER SO FOR DISPLAYING CREDIT HEADER THIS
			 * LOOP IS USED
			 */
			SettlementDetails creditName = new SettlementDetails();
			creditName.setCreditCode(String.valueOf(credit_header[c][0]));
			creditName.setCreditHead(String.valueOf(credit_header[c][1]));
			creditNames.add(creditName);

		}// end for loop

		for (int d = 0; d < debit_header.length; d++) {
			/**
			 * FOR DISPLAYING DEBIT NAME DYNAMICALLY
			 */
			SettlementDetails debitName = new SettlementDetails();
			debitName.setDebitCode(String.valueOf(debit_header[d][1]));
			debitName.setDebitHead(String.valueOf(debit_header[d][1]));
			debitNames.add(debitName);

		}// end i for loop

		settleDetails.setCreditHeader(creditNames);
		settleDetails.setDebitHeader(debitNames);

		for (int i = 0; i < listSize; i++) {
			// String[] splitObj = monthYr[i].split("-");
			// month[i] = splitObj[0];
			// year[i] = splitObj[1];
			if (!eligibleDays[i].equals("0")) {
				try {
					// calculates for short period days
					Object[][] mm = new Object[1][5];
					mm[0][0] = month[i];
					logger.info("Recalculate month  : " + mm[0][0]);
					mm[0][1] = year[i];
					logger.info("Recalculate year  : " + mm[0][1]);
					mm[0][2] = eligibleDays[i];
					logger.info("Recalculate days  : " + mm[0][2]);
					mm[0][3] = type[i];
					logger.info("Recalculate type  : " + mm[0][3]);
					mm[0][4] = maxDays[i];
					logger.info("Recalculate maxdays  : " + mm[0][4]);
					mon_List.add(mm);

					Object[][] empId = getEmpId(settleDetails);
					int empIds = empId.length;
					rows = new Object[empIds][];
					// logger.info("Calculation days :"+maxDays[i]);
					for (int y = 0; y < empId.length; y++) {
						/*
						 * getRow METHOD RETURN A COMLETE ROW FOR A EMPLOYEE AND
						 * SET EACH ROW IN A 2 DIMENSIONSION ARRARY THAT ROWS
						 * LENNGTH IS EQUAL TO TOTAL NO OF EMPLPYEE
						 */

						Object[][] row = getRow(String.valueOf(empId[y][0]),
								String.valueOf(empId[y][1]), String
										.valueOf(empId[y][2]), String
										.valueOf(empId[y][5]), month[i], String
										.valueOf(year[i]), credit_header,
								debit_header, settleDetails, String
										.valueOf(empId[y][6]), String
										.valueOf(empId[y][3]), eligibleDays[i],
								type[i], path, maxDays[i], String
										.valueOf(empId[y][7]), String
										.valueOf(empId[y][8]));

						if (row[0].length > 0 && row[0] != null) {
							rows[y] = row[0];
						}// end if
					}// end for loop
					rows_List.add(rows);

				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
		}

		request.setAttribute("mon_List", mon_List);
		combinedMonthList.add(mon_List);
		// request.setAttribute("rows_List", rows_List);
		return rows_List;

	}

	/**
	 * @param String
	 *            emp code
	 * @return Object for getting employee id
	 */

	public Object[][] getEmpId(SettlementDetails settleDetails) {
		Object emp_id[][] = null;
		try {
			/*
			 * FOR SELECTING THE EMPLOYEE THOSE FULL FILL SORTING CRITERIA
			 * 
			 */
			String selectSalary = " SELECT HRMS_EMP_OFFC.EMP_ID ,EMP_TOKEN ,EMP_FNAME ||' '||EMP_MNAME ||' '||EMP_LNAME,EMP_CENTER,CENTER_LOCATION,NVL(CENTER_PTAX_STATE,15),EMP_TYPE, "
					+ " NVL(SAL_PTAX_FLAG,'Y'),NVL(SAL_EPF_FLAG,'Y') "
					+ " FROM  HRMS_EMP_OFFC "
					+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID= HRMS_EMP_OFFC.EMP_ID) "
					+ " left join hrms_center on (hrms_center.CENTER_ID= HRMS_EMP_OFFC.EMP_CENTER) "
					+ " left join HRMS_LOCATION on (HRMS_LOCATION.LOCATION_CODE = hrms_center.CENTER_LOCATION) "
					+ " WHERE HRMS_EMP_OFFC.EMP_ID="
					+ settleDetails.getEmpCode();
			emp_id = getSqlModel().getSingleResult(selectSalary);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp_id;
	}

	public Object[][] getRow(String emp_id, String emp_token, String emp_name,
			String location, String month, String year,
			Object creditLength[][], Object debitLength[][],
			SettlementDetails settleDetails, String typeId, String branchId,
			String days, String type, String path, String maxDays,
			String empPTAXFlag, String empPFFlag) {

		/*
		 * THIS IS ORIGINAL CREDIT AMOUNT ON WHICH MANUPILATION HAS TO DO---
		 * 
		 */
		Object[][] credit_amount = getCredit(emp_id, month, year,
				settleDetails, days, type, maxDays);
		/*
		 * THIS IS ORIGINAL DEBIT AMOUNT ON WHICH MANUPLILATION HAS TO DO
		 * 
		 */

		Object[][] debit_amount = getDebit(emp_id, month, year, credit_amount,
				settleDetails, location, branchId, typeId, days, type, path,
				empPTAXFlag, empPFFlag,maxDays);
		/*for (int i = 0; i < debit_amount.length; i++) {
			for (int j = 0; j < debit_amount[0].length; j++) {
				// logger.info("Debit amount [" + i + "][" + j + "]...."
				// + debit_amount[i][j]);
			}
		}*/

		Object[][] total_amount = null;

		float totalCredit = 0;
		float totalDebit = 0;
		float netPay = 0;
		float creditamt = 0;

		/*
		 * TOTAL NO OF VARIABLES THAT HAS BEEN USED IN FOR LOOP FOR SETTING
		 * CREDITS , TOTAL CREDIT , DEBITS , TOTAL DEBIT AND NET PAY
		 * 
		 */
		int total_coulum = creditLength.length + debitLength.length + 9;

		total_amount = new Object[1][total_coulum];
		// TO LIST EMP ID, EMP NAME, EMP TOKEN
		try {
			total_amount[0][0] = emp_id;
			total_amount[0][1] = emp_token;
			total_amount[0][2] = emp_name;
		} catch (Exception e) {

		}

		try {
			int k = 0;
			int c = 0;
			for (int j = 0; j < total_coulum - 8; j++) {

				if (j < creditLength.length) {
					/*
					 * TO DISPLAY INDIVIDUAL CREDITS
					 * 
					 */
					try {

						total_amount[0][j + 3] = "0";
						try {
							if (credit_amount[c][1] != null)
								/*
								 * FOR FILTERING NULL VALUES FROM DATA IF DATA
								 * IS NULL IT WILL TREATED AS O VALUES
								 */
								total_amount[0][j + 3] = Utility
										.twoDecimals(String
												.valueOf(credit_amount[c][1]));
							totalCredit = totalCredit
									+ Float.parseFloat(String
											.valueOf(total_amount[0][j + 3]));
						} catch (Exception e) {
							e.printStackTrace();
						}
						c++;
					} catch (Exception e) {

					}

				} else if (j == creditLength.length) {
					/*
					 * TO DISPALY TOTAL CREDIT WHEN ALL CREDIT PLACED ON THEIR
					 * POSITION THEN TOTAL CREDIT FIELD WILL BE FILLED //
					 * 
					 */

					try {
						total_amount[0][j + 3] = Utility.twoDecimals(String
								.valueOf(totalCredit));
					} catch (RuntimeException e) {
						e.printStackTrace();
					}
					creditamt = totalCredit;
					// logger.info("test creditamt :" + creditamt);
				} else if (j > creditLength.length) {

					total_amount[0][j + 3] = "0";
					try {
						if (debit_amount[k][1] != null)
							/*
							 * FOR FILTERING NULL VALUES FROM DATA IF DATA IS
							 * NULL IT WILL TREATED AS O VALUES
							 */
							total_amount[0][j + 3] = Utility.twoDecimals(String
									.valueOf(debit_amount[k][1]));

						totalDebit = totalDebit
								+ Float.parseFloat(String
										.valueOf(total_amount[0][j + 3]));
					} catch (Exception e) {
						e.printStackTrace();
					}
					k++;
				}

				netPay = totalCredit - totalDebit;

				/*
				 * CALCULATION OF NET PAY
				 * 
				 */

				try {
					total_amount[0][j + 4] = Utility.twoDecimals(String
							.valueOf(totalDebit));
					total_amount[0][j + 5] = Math.round(netPay);
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
				if (totalDebit > totalCredit) {
					total_amount[0][j + 4] = Utility.twoDecimals(String
							.valueOf(totalCredit));
					/*
					 * IF DEBIT IS GREATER THEN CREDIT THEN HIS NET PAY WILL
					 * ZERO
					 * 
					 */
					total_amount[0][j + 5] = Math.round(netPay);
				}
				total_amount[0][j + 6] = String.valueOf(days);
				total_amount[0][j + 7] = String.valueOf(type);

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return total_amount;
	}

	public Object[][] getCredit(String emp_id, String month, String year,
			SettlementDetails settlDetails, String days, String type,
			String maxDays) {
		Object[][] credit_amount = null;
		Object[][] credit_sal_amount = null;
		Object[] gross_credit = null;

		try {

			String selectCredits = "SELECT HRMS_CREDIT_HEAD.CREDIT_CODE, NVL(CREDIT_AMT,0),NVL(CREDIT_APPLICABLE_ESI,'N'),NVL(CREDIT_APPLICABLE_PTAX,'N')  FROM HRMS_CREDIT_HEAD LEFT JOIN HRMS_EMP_CREDIT ON (HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_EMP_CREDIT.CREDIT_CODE AND  EMP_ID='"
					+ emp_id
					+ "'  ) where HRMS_CREDIT_HEAD.CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' order BY HRMS_CREDIT_HEAD.CREDIT_CODE";
			credit_amount = getSqlModel().getSingleResult(selectCredits,
					new Object[0][0]);
		} catch (Exception e) {

		}
		// logger.info("calc days in credit calc :"+maxDays);
		int daysOfMonth = 0;
		if (maxDays.equals("31")) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(Utility.getDate(01 + "-" + month + "-" + year));
			logger.info("in actual day   : 01-" + month + "-" + year);
			daysOfMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
			logger.info("in actual day daysOfMonth  : " + daysOfMonth);
		} else {
			daysOfMonth = 30;
		}
		// logger.info("Days of month used for calc :"+daysOfMonth);

		if (credit_amount != null) {
			credit_sal_amount = new Object[credit_amount.length][4];
			gross_credit = new Object[credit_amount.length];
			// String[][] rangeObj = null;
			// String noticeDate = "";

			for (int i = 0; i < credit_amount.length; i++) {
				credit_sal_amount[i][0] = credit_amount[i][0];
				credit_sal_amount[i][1] = Math.round((Double.parseDouble(String
						.valueOf(credit_amount[i][1])) * Double
						.parseDouble(String.valueOf(days)))
						/ daysOfMonth);
				credit_sal_amount[i][2] = credit_amount[i][2];
				credit_sal_amount[i][3] = credit_amount[i][3];
				gross_credit[i] = credit_amount[i][1];
				settlDetails.setGrossCredit(gross_credit);

			}

		}
		return credit_sal_amount;

	}

	public Object[][] getDebit(String emp_id, String month, String year,
			Object[][] credit_amount, SettlementDetails settlDetails,
			String location, String branchId, String typeId, String days,
			String type, String path, String empPTAXFlag, String empPFFlag,String maxDays) {
		Object[][] total_debit_amount = null;
		SalaryProcessModel nonSalModel = new SalaryProcessModel();
		nonSalModel.initiate(context, session);
		nonSalModel.loadPayrollSetting();
		NonIndustrialSalaryModel nonInModel = new NonIndustrialSalaryModel();
		nonInModel.initiate(context, session);
		double esi_amount = 0, pf_amount = 0, result = 0;

		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(Utility.getDate(01 + "-" + month + "-" + year));
			int daysOfMonth = 0;
			if (maxDays.equals("31")) {
				logger.info("in actual day   : 01-" + month + "-" + year);
				daysOfMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
				logger.info("in actual day daysOfMonth  : " + daysOfMonth);
			} else {
				daysOfMonth = 30;
			}
			String selectDebits = "  SELECT HRMS_DEBIT_HEAD.DEBIT_CODE, CASE WHEN DEBIT_TYPE='SD' THEN ROUND(NVL(DEBIT_AMT,0)*"+days+"/"+daysOfMonth+") "
					+" ELSE NVL(DEBIT_AMT,0)  END AS NEW_AMT,NVL(DEBIT_ROUND,0)  FROM HRMS_DEBIT_HEAD "
					+" LEFT JOIN HRMS_EMP_DEBIT ON (HRMS_DEBIT_HEAD.DEBIT_CODE=HRMS_EMP_DEBIT.DEBIT_CODE AND  HRMS_EMP_DEBIT.EMP_ID='"
					+ emp_id
					+ "' ) where HRMS_DEBIT_HEAD.DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' order BY HRMS_DEBIT_HEAD.DEBIT_PRIORITY,HRMS_DEBIT_HEAD.DEBIT_CODE ";
			Object[][] debit_amount = getSqlModel().getSingleResult(
					selectDebits);

			Object[][] pf_data = nonSalModel.getPFData(path, month, year);// settlDetails.getPfBeanData();
			String incomeTaxCode = getIncomeTaxCode(month, year);
			if (empPFFlag.equals("Y")) { // if employee is applicable for PF
				if (nonSalModel.getEmpTypeBranchApplicabilityChk(typeId, branchId, 3, path)) {

					if (pf_data == null) {

					} else if (pf_data.length <= 0) {

					}

					else {
						/*
						 * else loop for calculating debit amount for eligible
						 * days
						 */
						result = Utility.expressionEvaluate(new Utility()
								.generateFormulaPay(credit_amount, String
										.valueOf(pf_data[0][1]), context,
										session));

						try {
							/*
							 * This method will check minimum amount condition
							 * is on or off 4 particular employee type 4 PF
							 * deduction if YES then check minimum amount is
							 * less than or equal to result(BASIC+DA) if MinAmt >=
							 * result then PF=0 else calculate PF amount as
							 * usual else calculate PF amount as usual
							 */
							if (nonSalModel.getEmpTypeMinAmtChkCondition(typeId, 4, path)) {

								if (!String.valueOf(pf_data[0][5]).trim()
										.equals("0")) {

									if (String.valueOf(pf_data[0][5]).trim()
											.equals("1")
											&& result == Double
													.parseDouble(String
															.valueOf(pf_data[0][4]))) {

										pf_amount = nonSalModel
												.getpfAmtWithRuleCheck(
														String
																.valueOf(pf_data[0][6]),
														result,
														String
																.valueOf(pf_data[0][7]),
														String
																.valueOf(pf_data[0][2]));

									} else if (String.valueOf(pf_data[0][5])
											.trim().equals("2")
											&& result < Double
													.parseDouble(String
															.valueOf(pf_data[0][4]))) {

										pf_amount = nonSalModel
												.getpfAmtWithRuleCheck(
														String
																.valueOf(pf_data[0][6]),
														result,
														String
																.valueOf(pf_data[0][7]),
														String
																.valueOf(pf_data[0][2]));

									} else if (String.valueOf(pf_data[0][5])
											.trim().equals("3")
											&& result > Double
													.parseDouble(String
															.valueOf(pf_data[0][4]))) {

										pf_amount = nonSalModel
												.getpfAmtWithRuleCheck(
														String
																.valueOf(pf_data[0][6]),
														result,
														String
																.valueOf(pf_data[0][7]),
														String
																.valueOf(pf_data[0][2]));

									} else if (String.valueOf(pf_data[0][5])
											.trim().equals("4")
											&& result <= Double
													.parseDouble(String
															.valueOf(pf_data[0][4]))) {

										pf_amount = nonSalModel
												.getpfAmtWithRuleCheck(
														String
																.valueOf(pf_data[0][6]),
														result,
														String
																.valueOf(pf_data[0][7]),
														String
																.valueOf(pf_data[0][2]));

									} else if (String.valueOf(pf_data[0][5])
											.trim().equals("5")
											&& result >= Double
													.parseDouble(String
															.valueOf(pf_data[0][4]))) {

										pf_amount = nonSalModel
												.getpfAmtWithRuleCheck(
														String
																.valueOf(pf_data[0][6]),
														result,
														String
																.valueOf(pf_data[0][7]),
														String
																.valueOf(pf_data[0][2]));

									}
								} else
									pf_amount = Math
											.round((result * Double
													.parseDouble(String
															.valueOf(pf_data[0][2]))) / 100);

							} else
								pf_amount = Math
										.round((result * Double
												.parseDouble(String
														.valueOf(pf_data[0][2]))) / 100);
						} catch (Exception e) {
							logger.error(e.getMessage());
							pf_amount = 0;
						}
					}
				} else { // if employee is not applicable for PF
					pf_amount = 0;
				}
			}
			double totalGross = 0;
			// Object[] grossCredit = credit_amount;
			for (int i = 0; i < credit_amount.length; i++) {
				// logger.info("credit_amount[i][1] :"+credit_amount[i][1]);
				totalGross += Double.parseDouble(String
						.valueOf(credit_amount[i][1]));
			}

			/*
			 * if (grossCredit != null || grossCredit.length > 0) { for (int i =
			 * 0; i < grossCredit.length; i++) { logger.info("grossCredit
			 * :"+grossCredit[i]); totalGross += Double.parseDouble(String
			 * .valueOf(grossCredit[i])); } }
			 */

			float totalCreditAmount = 0;
			float totalESICreditAmount = 0;
			double totalESICGross = 0;
			float totalPTAXCreditAmount = 0;
			Object[] grossCredit = settlDetails.getGrossCredit();
			try {
				if (credit_amount != null) {
					for (int i = 0; i < credit_amount.length; i++) {
						try {
							totalCreditAmount += Float.parseFloat(String
									.valueOf(credit_amount[i][1]));
							// logger.info("credit_amount=="+credit_amount[i].length);

							if (String.valueOf(credit_amount[i][2]).trim()
									.equals("Y")) {
								totalESICreditAmount += Double
										.parseDouble(String
												.valueOf(credit_amount[i][1]));
							}

							if (String.valueOf(credit_amount[i][3]).trim()
									.equals("Y")) {
								totalPTAXCreditAmount += Double
										.parseDouble(String
												.valueOf(credit_amount[i][1]));
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (grossCredit != null) {
				if (grossCredit.length > 0) {
					for (int i = 0; i < grossCredit.length; i++) {

						try {

							if (String.valueOf(credit_amount[i][2]).trim()
									.equals("Y")) {
								totalESICGross += Double.parseDouble(String
										.valueOf(grossCredit[i]));
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
						}

					}
				}
			}
			Object[][] esi_data = nonSalModel.getESIData(path, month, year);// settlDetails.getEsiBeanData();
			String comLedgerCode = nonInModel
					.prevLedger(month, year, esi_data);
			String previousEsic = "";
			if (nonSalModel.getEmpTypeBranchApplicabilityChk(typeId, branchId, 1, path)) {
				// logger.info("totalGross :"+totalGross);
				// logger.info("esi_data[0][4] :"+esi_data[0][4]);
				if (esi_data == null) {

				} else if (esi_data.length <= 0) {

				}

				else {
					// logger.info("esic gross---------"+totalESICGross);
					logger.info("totalESICreditAmount---------"
							+ totalESICreditAmount);
					logger.info("esi_data[0][4]---------" + esi_data[0][4]);
					try {
						if (totalESICGross <= Integer.parseInt(String
								.valueOf(esi_data[0][4]))) {
							// esiResult=Utility.expressionEvaluate(new
							// Utility().generateFormulaPay(credit_amount,String.valueOf(esi_data[0][1]),
							// context,session));

							esi_amount = Math.ceil(totalESICreditAmount
									* Double.parseDouble(String
											.valueOf(esi_data[0][2])) / 100);
						}
						// If ESI start month and end month is equal then
						// straight away esi will be calculated.
						else if (month.equals(String.valueOf(esi_data[0][5]))
								|| month.equals(String.valueOf(esi_data[0][6]))) {
							if (totalESICGross <= Integer.parseInt(String
									.valueOf(esi_data[0][4]))) {
								// esiResult=Utility.expressionEvaluate(new
								// Utility().generateFormulaPay(credit_amount,String.valueOf(esi_data[0][1]),
								// context,session));

								esi_amount = Math
										.ceil(totalESICreditAmount
												* Double
														.parseDouble(String
																.valueOf(esi_data[0][2]))
												/ 100);
							}
						} else if (totalESICGross >= Integer.parseInt(String
								.valueOf(esi_data[0][4]))) {
							/**
							 * if not, system will check whether esi deducted on
							 * the specified month or not this method returns
							 * status of esi whether to deduct or not. It it
							 * returns "NP" means salary has not been processed
							 * for specified esi cutoff months, then condition
							 * will remain same depend on gross esi will be
							 * deducted. If it returns "CE" means esi deducted
							 * on cut off months hence esi will be deducted
							 * irrespective of gorss
							 */
							if (comLedgerCode.equals("spilt")) {
								previousEsic = nonSalModel.getPreESICSpilt(
										month, year, emp_id, esi_data,
										comLedgerCode);
							} else
								previousEsic = nonSalModel.getPreESIC(month,
										year, emp_id, esi_data, comLedgerCode);
							if (previousEsic.equals("true")) {
								esi_amount = Math
										.ceil(totalESICreditAmount
												* Double
														.parseDouble(String
																.valueOf(esi_data[0][2]))
												/ 100);
							}

						}
					} catch (Exception e) {
						esi_amount = 0;
					}
				}
			}

			/**
			 * Calculate TotalCredit Amount
			 */

			Object[][] ptax_data = nonSalModel.getPtaxAmount(path, month, year,
					location, totalPTAXCreditAmount);// settlDetails.getPtaxLoc();
			for (int i = 0; i < ptax_data.length; i++) {
				for (int j = 0; j < ptax_data[0].length; j++) {
					// logger.info("ptax data......" + ptax_data[i][j]);
				}
			}
			double ptax = 0;

			if (nonSalModel.getEmpTypeBranchApplicabilityChk(typeId, branchId, 2, path)) {
				if (location.equals("") || location.equals("null")) {
					ptax = 0;
				}

				else if (empPTAXFlag.equals("N")) {
					ptax = 0;
				} else
					ptax = nonInModel.getEmpPtax(month, ptax_data);

			}

			/**
			 * CALCULATE LOSOFPAY ACORDING TO FORMULA--
			 * ((basic+da+dp)*absenceDays))/DaysMonth
			 */

			float totalDebit = 0;
			boolean flag = true;
			if (debit_amount != null) {
				total_debit_amount = new Object[debit_amount.length][2];
				for (int i = 0; i < debit_amount.length; i++) {
					if (type.equals("EM")) {
						// if short period days... debit amount is zero
						total_debit_amount[i][0] = debit_amount[i][0];
						total_debit_amount[i][1] = "0";
						// logger.info("in if EM type........"
						// + total_debit_amount[i][1]);
					} else {
						total_debit_amount[i][0] = debit_amount[i][0];
						total_debit_amount[i][1] = debit_amount[i][1];
						// logger.info("in else CO type........"
						// + total_debit_amount[i][1]);

						// * FOR PTAX ,ESI,PF OF PAY */
						if (ptax_data.length == 0) {

						} else if (String.valueOf(debit_amount[i][0]).trim()
								.equals(String.valueOf(ptax_data[0][7]).trim())) {
							total_debit_amount[i][1] = ptax;
							// logger.info("in else if ptax........"
							// + total_debit_amount[i][1]);
						}
						if (pf_data.length == 0) {

						} else if (String.valueOf(debit_amount[i][0]).trim()
								.equals(String.valueOf(pf_data[0][0]).trim())) {
							total_debit_amount[i][1] = pf_amount;
							// logger.info("in else if pf_amount........"
							// + total_debit_amount[i][1]);
						}
						if (esi_data.length == 0) {

						} else if (String.valueOf(debit_amount[i][0]).trim()
								.equals(String.valueOf(esi_data[0][0]).trim())) {
							total_debit_amount[i][1] = esi_amount;
							// logger.info("in else if esi_amount........"
							// + total_debit_amount[i][1]);
						}
						// set debit amount to Zero if its incometax debit
						if (incomeTaxCode.equals(String
								.valueOf(debit_amount[i][0]))) {
							total_debit_amount[i][1] = "0";
						}
					}
					try {
						totalDebit += Float.parseFloat(String
								.valueOf(total_debit_amount[i][1]));
					} catch (Exception e) {

					}
					total_debit_amount[i][1] = Utility.twoDecimals(roundCheck(Integer.parseInt(String.valueOf(debit_amount[i][2])),
							Double.parseDouble(String.valueOf(total_debit_amount[i][1]))));
					/** DEDUCT AMOUNT AS PER PROPRITY FIRST */
					if (totalDebit > totalCreditAmount) {
						if (flag) {
							try {
								float extraDebit = totalDebit
										- totalCreditAmount;
								total_debit_amount[i][1] = Float
										.parseFloat(String
												.valueOf(total_debit_amount[i][1]))
										- extraDebit;
							} catch (Exception e) {

							}
						} else {
							/**
							 * IF TOTAL DEBIT > TOTAL CREDIT THEN SET NEXT DEBIT
							 * AS 0
							 */
							total_debit_amount[i][1] = 0;
						}
						flag = false;
					}
				} // end of for-loop
			} // end 1st if-cond
		} catch (Exception e) {
			e.printStackTrace();
		}

		return total_debit_amount;

	}

	public String getLeavePolicyCode(String empId) {
		LeavePolicyModel model = new LeavePolicyModel();
		model.initiate(context, session);
		String leavePolicyCode = model.getLeavePolicy(empId);
		model.terminate();
		return leavePolicyCode;
	}

	public String[][] getLeaveRecord(SettlementDetails settleDetails) {
		String policyCode = getLeavePolicyCode(settleDetails.getEmpCode());
		if (policyCode.length() > 0) {

			String query = " SELECT  TO_CHAR(LEAVE_NAME) ,NVL(LEAVE_CLOSING_BALANCE,0),HRMS_LEAVE.LEAVE_ID,HRMS_LEAVE_POLICY_DTL.LEAVE_ENC_FORMULA, "
					+ " HRMS_LEAVE_POLICY_DTL.LEAVE_ENC_MAXLIMIT, NVL(HRMS_LEAVE_BALANCE.LEAVE_ONHOLD,0), NVL(LEAVE_CLOSING_BALANCE,0), "
					+ " HRMS_LEAVE_POLICY_DTL.LEAVE_ENC_MAXLIMIT_SEPARATION, (CASE WHEN (LEAST(NVL(LEAVE_CLOSING_BALANCE,0),HRMS_LEAVE_POLICY_DTL.LEAVE_ENC_MAXLIMIT_SEPARATION))=0 THEN NVL(LEAVE_CLOSING_BALANCE,0) ELSE LEAST(NVL(LEAVE_CLOSING_BALANCE,0),HRMS_LEAVE_POLICY_DTL.LEAVE_ENC_MAXLIMIT_SEPARATION) END) AS LEAVEDAYS "
					+ " FROM HRMS_LEAVE "
					+ " LEFT JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_BALANCE.LEAVE_CODE AND EMP_ID ="
					+ settleDetails.getEmpCode()
					+ ") "
					+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_LEAVE_BALANCE.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
					+ " INNER JOIN HRMS_LEAVE_POLICY_DTL ON(HRMS_LEAVE_POLICY_DTL.LEAVE_CODE=HRMS_LEAVE_BALANCE.LEAVE_CODE "
					+ " AND LEAVE_APPLICABLE='Y' AND LEAVE_ENC_FLAG='Y' AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE="
					+ policyCode + ") " + " ORDER BY LEAVE_ID";

			Object[][] data = getSqlModel().getSingleResult(query);
			String[][] total = new String[data.length][8];
			double total_encash = 0.0d;
			float totDays = 0.0f;
			Object[][] settlementLeaves = getSettlementEntitle(settleDetails
					.getSepDate(), policyCode);

			for (int i = 0; i < data.length; i++) {
				total[i][0] = String.valueOf(data[i][0]); // LEAVE NAME
				total[i][2] = String.valueOf(data[i][7]);// MAX ENCASH
				// LIMIT FOR SEP
				total[i][4] = String.valueOf(data[i][2]);// LEAVE CODE
				total[i][6] = String.valueOf(data[i][4]);// MAX LIMIT
				// POLICY
				double leaveTotEntitle = Double.parseDouble(String
						.valueOf(data[i][8]));
				if (settlementLeaves != null && settlementLeaves.length > 0) {
					for (int j = 0; j < settlementLeaves.length; j++) {
						// if leave codes match
						if (String.valueOf(data[i][2]).equals(
								String.valueOf(settlementLeaves[j][0]))) {
							leaveTotEntitle = Double.parseDouble(String
									.valueOf(data[i][8]))
									+ Double.parseDouble(String
											.valueOf(settlementLeaves[j][1]));
							// if greater than separation limit in policy, set
							// separation limit as leaves
							/*
							 * // updated by mangesh on behalf of reeba
							 */
							if (leaveTotEntitle > Double.parseDouble(String
									.valueOf(data[i][7]))
									&& Double.parseDouble(String
											.valueOf(data[i][7])) != 0.0) {
								leaveTotEntitle = Double.parseDouble(String
										.valueOf(data[i][7]));
							}
						}
					}
				}
				total[i][7] = String.valueOf(leaveTotEntitle);// AVAILABLE
				// BALANCE
				total[i][1] = String.valueOf(leaveTotEntitle);// CLOSING
				// BALANCE
				// TOTAL LEAVE DAYS
				totDays += Float.parseFloat(total[i][1]);

				try {
					double amount = 0;
					amount = Utility.expressionEvaluate(new Utility()
							.generateFormula(settleDetails.getEmpCode(), String
									.valueOf(data[i][3]), context, // encash
									// formula
									session));
					//total[i][5] = String.valueOf(Math.round(amount)); // 		
					 total[i][5] = String.valueOf(new Utility().twoDecimal( amount, 2));
					double totAmt = 0.0d;
					totAmt = Double.parseDouble(total[i][5])
							* Double.parseDouble(total[i][1]);
					total_encash += totAmt;
					total[i][3] = String.valueOf(new Utility().twoDecimal(
							totAmt, 2));
					logger.info("Leave total[i][3]	::" + total[i][3]);
				}// end of try
				catch (Exception e) {
					logger.error("Exception in getLeaveRecord	::" + e);
				}// end of catch
			}// end of for
			if (data != null && data.length > 0) {
				settleDetails.setIsFlag("true");
				settleDetails.setTotalAmt(Utility.twoDecimals(String
						.valueOf(total_encash)));
				settleDetails.setTotalLeaveDays(String.valueOf(totDays));
			}
			return total;
		} else {
			settleDetails.setIsFlag("false");
			settleDetails.setTotalAmt("0");
		}
		return new String[0][0];

	}// end of getLeaveRecord

	public String[][] setLeaveOnCalculateTax(SettlementDetails settleDetails,
			HttpServletRequest request) {
		logger.info("Is flag on calculate tax  : " + settleDetails.getIsFlag());
		if (settleDetails.getIsFlag().equals("true")) {
			String[] leaveId = request.getParameterValues("leaveId");
			String[] leaveName = request.getParameterValues("leaveName");
			String[] avlBal = request.getParameterValues("avlBal");
			String[] clBal = request.getParameterValues("clBal");
			String[] total = request.getParameterValues("total");
			String[] amtPerDay = request.getParameterValues("amtPerDay");
			String[] maxEncashAmt = request.getParameterValues("maxEncashAmt");
			String[] maxEncashLmt = request.getParameterValues("maxEncashLmt");
			if (leaveId != null) {
				String[][] values = new String[leaveId.length][8];
				double total_encash = 0.0d;
				float totDays = 0.0f;
				for (int i = 0; i < leaveId.length; i++) {
					values[i][0] = leaveName[i]; // leave name
					values[i][1] = clBal[i];// closing balance
					values[i][2] = maxEncashLmt[i];// max encash limit for sep
					values[i][3] = total[i];// each total amount
					values[i][4] = leaveId[i];// leave code
					values[i][5] = amtPerDay[i];// amount per day
					values[i][6] = maxEncashAmt[i];// max limit policy
					values[i][7] = avlBal[i];// available balance
					// TOTAL LEAVE DAYS
					totDays += Float.parseFloat(values[i][1]);
					try {
						double totAmt = 0.0d;
						totAmt = Double.parseDouble(values[i][5])
								* Double.parseDouble(values[i][1]);
						total_encash += totAmt;
						logger.info("Leave total[i][3]	::" + values[i][3]);
					}// end of try
					catch (Exception e) {
						logger.error("Exception in setLeaveOnCalculateTax	::"
								+ e);
					}// end of catch
				}
				settleDetails.setTotalAmt(Utility.twoDecimals(String
						.valueOf(total_encash)));
				settleDetails.setTotalLeaveDays(String.valueOf(totDays));
				return values;
			} else
				return new String[0][0];
		} else
			return new String[0][0];
	}// end of setLeaveOnCalculateTax

	public boolean saveHeader(SettlementDetails settleDetails) {
		boolean result = false;
		try {
			Object[][] saveObj;
			/*
			 * String delQuery = " DELETE FROM HRMS_SETTL_HDR WHERE SETTL_CODE =
			 * "+settleDetails.getSettCode();
			 * getSqlModel().singleExecute(delQuery);
			 */

			saveObj = new Object[1][17];
			saveObj[0][0] = settleDetails.getResignCode();
			saveObj[0][1] = settleDetails.getSettDate();
			saveObj[0][2] = settleDetails.getSepDate();
			saveObj[0][3] = settleDetails.getModePayment();
			if (settleDetails.getModePayment().equals("CH")) {
				saveObj[0][4] = settleDetails.getBankMicrId();
				saveObj[0][5] = settleDetails.getChequeno();
				saveObj[0][6] = settleDetails.getChequeDate();
			} else {
				saveObj[0][4] = "";
				saveObj[0][5] = 0;
				saveObj[0][6] = "";
			}
			saveObj[0][7] = settleDetails.getPrepbyCode();
			saveObj[0][8] = settleDetails.getChkbyCode();
			saveObj[0][9] = settleDetails.getAccChkCode();
			saveObj[0][10] = settleDetails.getHandOverCode();
			if (settleDetails.getPermSettle().equals("true")) {
				saveObj[0][11] = "Y";
			} else {
				saveObj[0][11] = "N";
			}
			saveObj[0][12] = settleDetails.getComments();
			saveObj[0][13] = settleDetails.getNoticePeriod();
			saveObj[0][14] = "D";
			// settleDetails.getNoticeStatus();
			if (settleDetails.getLockFlag().equals("Y"))
				saveObj[0][15] = "Y";
			else
				saveObj[0][15] = "N";
			saveObj[0][16] = settleDetails.getEmpCode();

			String insQuery = " INSERT INTO HRMS_SETTL_HDR(SETTL_CODE,SETTL_RESGNO,SETTL_SETTLDT,SETTL_SEPRDT, "
					+ " SETTL_PAYMODE,SETTL_BANKNM,SETTL_CHEQUENO,SETTL_CHEQUEDT,SETTL_PREPBY,SETTL_CHKBYHR, "
					+ " SETTL_CHKBYACC,SETTL_HANDOVER,SETTL_PERMSETTLEMENT,SETTL_COMMENT,SETTL_NOTICEDAYS,SETTL_NOTICEMTH,SETTL_LOCKFLAG,SETTL_ECODE) "
					+ " VALUES ((SELECT NVL(MAX(SETTL_CODE),0)+1 FROM HRMS_SETTL_HDR), "
					+ " ?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'),?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?,?,?,?) ";
			result = getSqlModel().singleExecute(insQuery, saveObj);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateHeader(SettlementDetails settleDetails) {
		boolean result = false;
		try {

			Object[][] upObj = new Object[1][6];
			upObj[0][0] = settleDetails.getResignCode();
			upObj[0][1] = settleDetails.getSepDate();
			upObj[0][2] = settleDetails.getNoticePeriod();
			upObj[0][3] = "D";
			// settleDetails.getNoticeStatus();
			upObj[0][4] = String.valueOf(settleDetails.getLockFlag().equals(
					"true") ? "Y" : "N");
			upObj[0][5] = settleDetails.getSettCode();

			String updateQuery = " UPDATE HRMS_SETTL_HDR SET SETTL_RESGNO=?,SETTL_SEPRDT=TO_DATE(?,'DD-MM-YYYY')  "
					+ "  ,SETTL_NOTICEDAYS=? ,SETTL_NOTICEMTH=?,SETTL_LOCKFLAG=? "
					+ " WHERE SETTL_CODE=?";
			result = getSqlModel().singleExecute(updateQuery, upObj);
			return result;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean updateHeaderFinal(SettlementDetails settleDetails) {
		boolean result = false;
		try {

			Object[][] upObj = new Object[1][17];
			upObj[0][0] = settleDetails.getResignCode();
			upObj[0][1] = settleDetails.getSettDate();
			upObj[0][2] = settleDetails.getSepDate();
			upObj[0][3] = settleDetails.getModePayment();

			if (settleDetails.getModePayment().equals("CH")) {
				upObj[0][4] = settleDetails.getBankMicrId();
				upObj[0][5] = settleDetails.getChequeno();
				upObj[0][6] = settleDetails.getChequeDate();
			} else {
				upObj[0][4] = "";
				upObj[0][5] = 0;
				upObj[0][6] = "";
			}
			upObj[0][7] = settleDetails.getPrepbyCode();
			upObj[0][8] = settleDetails.getChkbyCode();
			upObj[0][9] = settleDetails.getAccChkCode();
			upObj[0][10] = settleDetails.getHandOverCode();
			if (settleDetails.getPermSettle().equals("true")) {
				upObj[0][11] = "Y";
			} else {
				upObj[0][11] = "N";
			}
			upObj[0][12] = settleDetails.getComments();

			upObj[0][13] = settleDetails.getNoticePeriod();
			upObj[0][14] = "D";
			// settleDetails.getNoticeStatus();
			upObj[0][15] = String.valueOf(settleDetails.getLockFlag().equals(
					"true") ? "Y" : "N");
			upObj[0][16] = settleDetails.getSettCode();

			String updateQuery = "UPDATE HRMS_SETTL_HDR SET SETTL_RESGNO = ?,SETTL_SETTLDT = TO_DATE(?,'DD-MM-YYYY'),"
					+ " SETTL_SEPRDT = TO_DATE(?,'DD-MM-YYYY'),SETTL_PAYMODE =?,SETTL_BANKNM=?,SETTL_CHEQUENO=?,"
					+ " SETTL_CHEQUEDT = TO_DATE(?,'DD-MM-YYYY'),SETTL_PREPBY=?,SETTL_CHKBYHR=?,SETTL_CHKBYACC=?,"
					+ " SETTL_HANDOVER = ?,SETTL_PERMSETTLEMENT = ?,"
					+ " SETTL_COMMENT =? ,SETTL_NOTICEDAYS=?,SETTL_NOTICEMTH=?,SETTL_LOCKFLAG=? WHERE SETTL_CODE=?";
			result = getSqlModel().singleExecute(updateQuery, upObj);
			return result;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean saveSettle(SettlementDetails settleDetails, String netAmt,
			Object[] leaveId, Object[] clBal, Object[] total) {
		boolean result = false;
		try {
			String delQuery = " DELETE FROM HRMS_SETTL_HDR WHERE SETTL_CODE = "
					+ settleDetails.getSettCode();
			result = getSqlModel().singleExecute(delQuery);
			Object[][] saveObj;

			if (result) {
				saveObj = new Object[1][23];
				saveObj[0][0] = settleDetails.getResignCode();
				saveObj[0][1] = settleDetails.getSettDate();
				saveObj[0][2] = settleDetails.getSepDate();
				saveObj[0][3] = settleDetails.getModePayment();
				if (settleDetails.getModePayment().equals("CH")) {
					saveObj[0][4] = settleDetails.getBankMicrId();
					saveObj[0][5] = settleDetails.getChequeno();
					saveObj[0][6] = settleDetails.getChequeDate();
				} else {
					saveObj[0][4] = "";
					saveObj[0][5] = 0;
					saveObj[0][6] = "";
				}
				saveObj[0][7] = settleDetails.getPrepbyCode();
				saveObj[0][8] = settleDetails.getChkbyCode();
				saveObj[0][9] = settleDetails.getAccChkCode();
				saveObj[0][10] = settleDetails.getHandOverCode();
				if (settleDetails.getPermSettle().equals("true")) {
					saveObj[0][11] = "Y";
				} else {
					saveObj[0][11] = "N";
				}
				saveObj[0][12] = settleDetails.getGratuity();
				saveObj[0][13] = settleDetails.getReimburse();
				saveObj[0][14] = Utility.twoDecimals(settleDetails
						.getTotalAmt());
				saveObj[0][15] = settleDetails.getDeduct();
				saveObj[0][16] = netAmt;
				saveObj[0][17] = settleDetails.getComments();
				saveObj[0][18] = settleDetails.getNoticePeriod();
				saveObj[0][19] = "D";
				// settleDetails.getNoticeStatus();
				saveObj[0][20] = settleDetails.getTotalLeaveDays();
				if (settleDetails.getLockFlag().equals("Y"))
					saveObj[0][21] = "Y";
				else
					saveObj[0][21] = "N";
				saveObj[0][22] = settleDetails.getEmpCode();

				result = getSqlModel().singleExecute(getQuery(1), saveObj);

				if (result) {
					String codeQuery = "SELECT MAX(SETTL_CODE) FROM HRMS_SETTL_HDR";
					Object[][] codeObj = getSqlModel().getSingleResult(
							codeQuery);
					settleDetails.setSettCode(String.valueOf(codeObj[0][0]));
					if (leaveId != null) {
						for (int i = 0; i < leaveId.length; i++) {
							String dtlQuery = "INSERT INTO HRMS_SETTL_LEAVEENCASH (SETTL_CODE,SETTL_LEAVE_CODE, SETTL_ENCASH_DAYS, SETTL_ENCASH_AMOUNT)"
									+ " VALUES ("
									+ settleDetails.getSettCode()
									+ ", "
									+ leaveId[i]
									+ ","
									+ clBal[i]
									+ ","
									+ total[i] + ")";
							result = getSqlModel().singleExecute(dtlQuery);
						}
					}
					// if result is true then leave date and employee status in
					// employee office is updated...
					// updateEmpDetails(settleDetails);
				}
			}

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// Method for updating settlement header information ........

	public boolean updateSettle(SettlementDetails settleDetails, String netAmt,
			Object[] leaveId, Object[] clBal, Object[] total) {
		boolean result = false;

		try {

			Object[][] upObj = new Object[1][19];
			upObj[0][0] = settleDetails.getResignCode();
			upObj[0][1] = settleDetails.getSepDate();
			upObj[0][2] = settleDetails.getGratuity();
			upObj[0][3] = settleDetails.getReimburse();
			upObj[0][4] = settleDetails.getEncashAmt();
			upObj[0][5] = settleDetails.getDeduct();
			upObj[0][6] = netAmt;
			upObj[0][7] = settleDetails.getNoticePeriod();
			upObj[0][8] = "D";
			// settleDetails.getNoticePeriodStatus();
			upObj[0][9] = settleDetails.getTotalLeaveDays();
			if (settleDetails.getLockFlag().equals("Y"))
				upObj[0][10] = "Y";
			else
				upObj[0][10] = "N";
			logger.info("getTaxValue  : " + settleDetails.getTaxValue());

			upObj[0][11] = settleDetails.getTaxValue();
			upObj[0][12] = settleDetails.getEmpCode();
			if (Integer.parseInt(String.valueOf(settleDetails.getTaxValue())) > 0)
				upObj[0][13] = "Y";
			upObj[0][14] = settleDetails.getServiceTenure();
			upObj[0][15] = settleDetails.getGratuityAvgSalary();
			logger.info("upObj[0][14]===" + upObj[0][14]);
			logger.info("upObj[0][15]===" + upObj[0][15]);
			upObj[0][16] = settleDetails.getReimburseComments();
			upObj[0][17] = settleDetails.getDeductComments();
			upObj[0][18] = settleDetails.getSettCode();
			result = getSqlModel().singleExecute(getQuery(2), upObj);
			if (result) {
				String delQuery = "DELETE FROM HRMS_SETTL_LEAVEENCASH WHERE SETTL_CODE ="
						+ settleDetails.getSettCode();
				getSqlModel().singleExecute(delQuery);
				if (leaveId != null) {
					for (int i = 0; i < leaveId.length; i++) {
						String dtlQuery = "INSERT INTO HRMS_SETTL_LEAVEENCASH (SETTL_CODE,SETTL_LEAVE_CODE, SETTL_ENCASH_DAYS, SETTL_ENCASH_AMOUNT)"
								+ " VALUES ("
								+ settleDetails.getSettCode()
								+ ","
								+ leaveId[i]
								+ ","
								+ clBal[i]
								+ ","
								+ total[i] + ")";
						result = getSqlModel().singleExecute(dtlQuery);
					}
				}
				// updateEmpDetails(settleDetails);
			}
			return result;
		} catch (Exception e) {
			return false;
		}

	}

	// Method for updating leave date and employee status in employee office
	public void updateEmpDetails(SettlementDetails settleDetails) {

		try {

			String upsepDtQue = "UPDATE HRMS_EMP_OFFC SET EMP_LEAVE_DATE =TO_DATE('"
					+ settleDetails.getSepDate()
					+ "','DD-MM-YYYY') WHERE EMP_ID ="
					+ settleDetails.getEmpCode();
			getSqlModel().singleExecute(upsepDtQue);
			if (settleDetails.getPermSettle().equals("true")) {
				String upResignQue = "UPDATE HRMS_EMP_OFFC SET EMP_STATUS ='N' WHERE EMP_ID ="
						+ settleDetails.getEmpCode();
				getSqlModel().singleExecute(upResignQue);
			} else {
				String upServiceQue = "UPDATE HRMS_EMP_OFFC SET EMP_STATUS ='S' WHERE EMP_ID ="
						+ settleDetails.getEmpCode();
				getSqlModel().singleExecute(upServiceQue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean saveSettleDtl(SettlementDetails settleDetails,
			HttpServletRequest request) {
		boolean result = false;
		int listCode;
		try {
			listCode = Integer.parseInt(settleDetails.getListCode());
		} catch (Exception e) {
			listCode = 0;
		}
		int payListCode = Integer.parseInt(settleDetails.getPayListCode());
		String type[] = request.getParameterValues("calPayByType");
		String month[] = request.getParameterValues("payByMnth");
		String year[] = request.getParameterValues("payByYr");
		String eligibleDays[] = request.getParameterValues("calPayByDays");
		String[] maxDays = request.getParameterValues("maxDays");

		// logger.info("Months length...... "+month.length);

		try {
			String delQuery = " DELETE FROM HRMS_SETTL_DTL WHERE SETTL_CODE = "
					+ settleDetails.getSettCode();
			getSqlModel().singleExecute(delQuery);
			// int y = 0, i = 0;
			String codeQuery = " SELECT MAX(SETTL_CODE) FROM HRMS_SETTL_HDR";
			Object[][] settlCode = getSqlModel().getSingleResult(codeQuery);
			Object[][] saveObj = new Object[payListCode + listCode][6];

			if (payListCode > 0) {
				// logger.info("paylist code....update..... " + payListCode);
				for (int i = 0; i < payListCode; i++) {
					// PAY BY DAYS OBJECT
					if (!eligibleDays[i].equals("0")) {
						saveObj[i][1] = month[i];
						saveObj[i][2] = year[i];
						saveObj[i][3] = type[i];
						saveObj[i][4] = eligibleDays[i];
						saveObj[i][5] = maxDays[i];
						saveObj[i][0] = settlCode[0][0];
					}
				}
			}
			if (listCode > 0) {
				// logger.info("paylist code......... " + payListCode);
				String[] ohMonth = request.getParameterValues("hdOhMnth");
				String[] ohYear = request.getParameterValues("hdOhYear");
				String[] ohMaxDays = request.getParameterValues("hdOhMaxDays");
				String[] ohDays = request.getParameterValues("hdcalDays");
				String[] ohType = request.getParameterValues("hdcalType");

				// logger.info("Onhold Months length ...... " + ohMonth.length);
				// ONHOLD OBJECT
				for (int y = 0; y < listCode; y++) {
					if (!ohDays[y].equals("0")) {
						saveObj[payListCode][1] = ohMonth[y];
						saveObj[payListCode][2] = ohYear[y];
						saveObj[payListCode][3] = ohType[y];
						saveObj[payListCode][4] = ohDays[y];
						saveObj[payListCode][5] = ohMaxDays[y];
						saveObj[payListCode][0] = settlCode[0][0];
						payListCode++;
					}
				}
			}

			for (int i = 0; i < saveObj.length; i++) {
				/*
				 * logger.info(String.valueOf("saveObj :" + saveObj[i][0]));
				 * logger.info(String.valueOf("saveObj :" + saveObj[i][1]));
				 * logger.info(String.valueOf("saveObj :" + saveObj[i][2]));
				 * logger.info(String.valueOf("saveObj :" + saveObj[i][3]));
				 * logger.info(String.valueOf("saveObj :" + saveObj[i][4]));
				 */

				String query = " INSERT INTO HRMS_SETTL_DTL (SETTL_CODE, SETTL_MONTH, SETTL_YEAR, SETTL_TYPE, SETTL_DAYS, SETTL_CAL_DAYS) "
						+ " VALUES("
						+ saveObj[i][0]
						+ ","
						+ saveObj[i][1]
						+ ", "
						+ saveObj[i][2]
						+ ", '"
						+ saveObj[i][3]
						+ "', "
						+ saveObj[i][4] + ", " + saveObj[i][5] + ")";
				if (saveObj[i][0] != null) {
					result = getSqlModel().singleExecute(query);
				}
			}

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateSettleDtl(SettlementDetails settleDetails,
			HttpServletRequest request) {
		boolean result = false;
		String type[] = request.getParameterValues("calPayByType");
		String month[] = request.getParameterValues("payByMnth");
		String year[] = request.getParameterValues("payByYr");
		String eligibleDays[] = request.getParameterValues("calPayByDays");
		String maxDays[] = request.getParameterValues("maxDays");

		String[] ohMonth = request.getParameterValues("hdOhMnth");
		String[] ohYear = request.getParameterValues("hdOhYear");
		String[] ohMaxDays = request.getParameterValues("hdOhMaxDays");
		String[] ohDays = request.getParameterValues("hdcalDays");
		String[] ohType = request.getParameterValues("hdcalType");

		int payListCode = 0;
		if (request.getParameter("payByMnth") != null)
			payListCode = month.length;
		int listCode = 0;
		if (request.getParameter("hdOhMnth") != null)
			listCode = ohMonth.length;

		try {

			Object[][] updateObj = new Object[payListCode + listCode][6];
			if (payListCode > 0) {
				// logger.info("paylist code....update..... " + payListCode);
				for (int i = 0; i < payListCode; i++) {
					// PAY BY DAYS OBJECT
					updateObj[i][0] = month[i];
					updateObj[i][1] = year[i];
					updateObj[i][2] = type[i];
					updateObj[i][3] = eligibleDays[i];
					updateObj[i][4] = maxDays[i];
					updateObj[i][5] = settleDetails.getSettCode();
				}
			}
			if (listCode > 0) {
				// logger.info("list code....update..... " + listCode);
				// ONHOLD OBJECT
				for (int y = 0; y < listCode; y++) {
					updateObj[payListCode][0] = ohMonth[y];
					updateObj[payListCode][1] = ohYear[y];
					updateObj[payListCode][2] = ohType[y];
					updateObj[payListCode][3] = ohDays[y];
					updateObj[payListCode][4] = ohMaxDays[y];
					updateObj[payListCode][5] = settleDetails.getSettCode();
					payListCode++;
				}
			}

			String delQuery = " DELETE FROM HRMS_SETTL_DTL WHERE SETTL_CODE="
					+ settleDetails.getSettCode();
			result = getSqlModel().singleExecute(delQuery);
			if (result) {
				for (int i = 0; i < updateObj.length; i++) {
					String query = " INSERT INTO HRMS_SETTL_DTL (SETTL_CODE, SETTL_MONTH, SETTL_YEAR, SETTL_TYPE, SETTL_DAYS, SETTL_CAL_DAYS) "
							+ " VALUES("
							+ updateObj[i][5]
							+ ","
							+ updateObj[i][0]
							+ ", "
							+ updateObj[i][1]
							+ ", '"
							+ updateObj[i][2]
							+ "', "
							+ updateObj[i][3]
							+ ", "
							+ updateObj[i][4] + ")";

					result = getSqlModel().singleExecute(query);
				}
			}

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// GET CREDIT CODE AND CREDIT ABBR

	public Object[][] getCreditHeader() {
		Object credit_header[][] = null;
		try {
			String selectCredit = "SELECT CREDIT_CODE,  CREDIT_NAME FROM HRMS_CREDIT_HEAD WHERE CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' ORDER BY CREDIT_CODE";
			/*
			 * FOR GETTING CREDIT CODE AND CREDIT ABBR WHICH USED FOR DISPLAYING
			 * AS NAME OF CREDIT ON SCREEN
			 * 
			 */
			credit_header = getSqlModel().getSingleResult(selectCredit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return credit_header;

	}

	// GET DEBIT CODE AND DEBIT ABBR

	public Object[][] getDebitHeader() {
		Object debit_header[][] = null;
		try {
			String selectDebit = "SELECT DEBIT_CODE,  DEBIT_NAME FROM HRMS_DEBIT_HEAD WHERE DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' ORDER BY DEBIT_PRIORITY,DEBIT_CODE ";
			/*
			 * FOR GETTING DEBIT CODE AND DEBIT ABBR WHICH USED FOR DISPLAYING
			 * AS NAME OF DEBIT ON SCREEN
			 * 
			 */
			debit_header = getSqlModel().getSingleResult(selectDebit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return debit_header;

	}

	/**
	 * Method for saving settlement credits and debits of onHold months
	 * 
	 * @param settleDetails
	 * @param creditCode
	 * @param creditAmt
	 * @param debitCode
	 * @param debitAmt
	 * @param month
	 * @param year
	 * @return boolean
	 */
	public boolean saveSalOnhold(SettlementDetails settleDetails,
			Object[] creditCode, Object[] creditAmt, Object[] debitCode,
			Object[] debitAmt, Object[] month, Object[] year,
			Object[] onholdType) {

		boolean result = false, creResult = false, debResult = false;

		try {

			// String codeQuery = " SELECT max(SETTL_CODE) FROM HRMS_SETTL_HDR";
			// Object[][] settlCode = getSqlModel().getSingleResult(codeQuery);

			logger.info("settleDetails.getSettCode() in save onhold : "
					+ settleDetails.getSettCode());
			if (creditCode != null) {
				Object[][] saveCreObj = new Object[creditCode.length][4];
				int z = 0;
				int i = 0;
				for (int j = 0; j < month.length; j++) {
					if (String.valueOf(onholdType[j]).equals("OH")) {
						// logger.info("Month ... :" + month[j]);
						for (int k = 0; k < creditCode.length / month.length; k++) {
							String []creditMonthArray=String.valueOf(creditCode[i]).split("#");
							saveCreObj[z][0] = creditMonthArray[0];
							saveCreObj[z][1] = creditAmt[i];
							saveCreObj[z][2] = creditMonthArray[1];
							saveCreObj[z][3] = creditMonthArray[2];
							z++;
							i++;
						}
					}
				}
				/*for (int j = 0; j < saveCreObj.length; j++) {
					logger.info("creditCode[" + j + "]==" + saveCreObj[j][0]);
					logger.info("creditAmt[" + j + "]==" + saveCreObj[j][1]);
					logger.info("month[" + j + "]==" + saveCreObj[j][2]);
					logger.info("year[" + j + "]==" + saveCreObj[j][3]);
				}*/
				String saveCredits = "INSERT INTO HRMS_SETTL_CREDITS(SETTL_CODE,SETTL_CREDIT_CODE,SETTL_AMT,SETTL_MTH,SETTL_YEAR,SETTL_MTH_TYPE) "
						+ "VALUES("
						+ settleDetails.getSettCode()
						+ ",?,?,?,?,'OH')";
				creResult = getSqlModel()
						.singleExecute(saveCredits, saveCreObj);
			}
			if (debitCode != null) {
				Object[][] saveDbObj = new Object[debitCode.length][4];
				int i = 0;
				int z = 0;
				for (int j = 0; j < month.length; j++) {
					if (String.valueOf(onholdType[j]).equals("OH")) {
						for (int k = 0; k < debitCode.length / month.length; k++) {
							saveDbObj[z][0] = debitCode[i];
							saveDbObj[z][1] = debitAmt[i];
							saveDbObj[z][2] = month[j];
							saveDbObj[z][3] = year[j];
							z++;
							i++;
						}
					}
				}
				String saveDebits = "INSERT INTO HRMS_SETTL_DEBITS(SETTL_CODE,SETTL_DEBIT_CODE,SETTL_AMT,SETTL_MTH,SETTL_YEAR,SETTL_MTH_TYPE) "
						+ "VALUES("
						+ settleDetails.getSettCode()
						+ ",?,?,?,?,'OH')";
				debResult = getSqlModel().singleExecute(saveDebits, saveDbObj);
			}
			if (creResult && debResult)
				result = true;
			else
				result = false;

			return result;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	// Method for saving short period days..

	public boolean saveSalShort(SettlementDetails settleDetails,
			Object[][] dShort, Object[][] cShort, Object[][] shortRows,
			Object[] mmdays, Object[] yydays, Object[] monthType) {
		boolean result = false, creResult = false, debResult = false;
		try {
			// String codeQuery = " SELECT max(SETTL_CODE) FROM HRMS_SETTL_HDR";
			// Object[][] settlCode = getSqlModel().getSingleResult(codeQuery);
			logger.info("In saving credits and debits, sett code   : "
					+ settleDetails.getSettCode());
			Object CreditData[][] = new Object[mmdays.length * cShort.length][5];
			String saveCredits = "INSERT INTO HRMS_SETTL_CREDITS(SETTL_CODE,SETTL_CREDIT_CODE,SETTL_AMT,SETTL_MTH,SETTL_YEAR,SETTL_MTH_TYPE) "
					+ "VALUES(" + settleDetails.getSettCode() + ",?,?,?,?,?)";

			Object DebitData[][] = new Object[mmdays.length * dShort.length][5];
			String saveDebits = "INSERT INTO HRMS_SETTL_DEBITS(SETTL_CODE,SETTL_DEBIT_CODE,SETTL_AMT,SETTL_MTH,SETTL_YEAR,SETTL_MTH_TYPE) "
					+ "VALUES(" + settleDetails.getSettCode() + ",?,?,?,?,?)";

			int k = 0;
			int j = 0;

			for (int i = 0; i < mmdays.length; i++) {
				for (int m = 0, n = 0; m < cShort.length + dShort.length; m++) {
					if (m < cShort.length) {
						CreditData[j][0] = cShort[m][0]; // credit_code
						CreditData[j][1] = shortRows[i][m]; // amount
						CreditData[j][2] = mmdays[i];
						CreditData[j][3] = yydays[i];
						CreditData[j][4] = monthType[i];
						j++;

					} else {

						DebitData[k][0] = dShort[n][0];
						DebitData[k][1] = shortRows[i][m];
						DebitData[k][2] = mmdays[i];
						DebitData[k][3] = yydays[i];
						DebitData[k][4] = monthType[i];
						k++;
						n++;

					}

				}// this is end of c.length + d.length for loop
			}
			try {

				creResult = getSqlModel()
						.singleExecute(saveCredits, CreditData);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

			try {

				debResult = getSqlModel().singleExecute(saveDebits, DebitData);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			if (creResult && debResult)
				result = true;
			else
				result = false;

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/*
	 * Deletes the settlement details information of particular employee
	 * according to settlement code
	 */
	public void delCreDbData(SettlementDetails settleDetails) {
		Object[][] delObj = new Object[1][1];
		delObj[0][0] = settleDetails.getSettCode();

		String deleteCreditQuery = "DELETE FROM HRMS_SETTL_CREDITS"
				+ "  WHERE SETTL_CODE=" + delObj[0][0];
		getSqlModel().singleExecute(deleteCreditQuery);

		String deleteDebitQuery = "DELETE FROM HRMS_SETTL_DEBITS"
				+ " WHERE SETTL_CODE=" + delObj[0][0];
		getSqlModel().singleExecute(deleteDebitQuery);

	}

	// Method for updating salary credits of onhold months............

	public void updateSalOnhold(SettlementDetails settleDetails,
			Object[] creditCode, Object[] creditAmt, Object[] debitCode,
			Object[] debitAmt, Object[] month, Object[] year,
			Object[] onholdType) {

		try {

			if (creditCode != null) {
				Object[][] saveCreObj = new Object[creditCode.length][4];

				int z = 0;
				int i = 0;
				for (int j = 0; j < month.length; j++) {
					if (String.valueOf(onholdType[j]).equals("OH")) {
						// logger.info("Month value :" + month[j]);

						for (int k = 0; k < creditCode.length / month.length; k++) {

							saveCreObj[z][0] = creditCode[i];
							saveCreObj[z][1] = creditAmt[i];
							saveCreObj[z][2] = month[j];
							saveCreObj[z][3] = year[j];
							z++;
							i++;
						}
					}

				}
				String saveCredits = "INSERT INTO HRMS_SETTL_CREDITS(SETTL_CODE,SETTL_CREDIT_CODE,SETTL_AMT,SETTL_MTH,SETTL_YEAR,SETTL_MTH_TYPE) "
						+ "VALUES("
						+ settleDetails.getSettCode()
						+ ",?,?,?,?,'OH')";
				getSqlModel().singleExecute(saveCredits, saveCreObj);

			}

			if (debitCode != null) {
				Object[][] saveDbObj = new Object[debitCode.length][4];
				int z = 0;
				int i = 0;
				for (int j = 0; j < month.length; j++) {
					if (String.valueOf(onholdType[j]).equals("OH")) {
						for (int k = 0; k < debitCode.length / month.length; k++) {
							saveDbObj[z][0] = debitCode[i];
							saveDbObj[z][1] = debitAmt[i];
							saveDbObj[z][2] = month[j];
							saveDbObj[z][3] = year[j];
							z++;
							i++;
						}
					}
				}
				String saveDebits = "INSERT INTO HRMS_SETTL_DEBITS(SETTL_CODE,SETTL_DEBIT_CODE,SETTL_AMT,SETTL_MTH,SETTL_YEAR,SETTL_MTH_TYPE) "
						+ "VALUES("
						+ settleDetails.getSettCode()
						+ ",?,?,?,?,'OH')";
				getSqlModel().singleExecute(saveDebits, saveDbObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// updateSalGo(settleDetails,d,c,rows,mmdays,yydays);

	}

	// Method for updating short period days..

	public boolean updateSalShort(SettlementDetails settleDetails,
			Object[][] dShort, Object[][] cShort, Object[][] shortRows,
			Object[] mmdays, Object[] yydays, Object[] monthType) {
		try {
			boolean result = false, creResult = false, debResult = false;
			logger.info("In updating credits and debits, sett code   : "
					+ settleDetails.getSettCode());

			Object CreditData[][] = new Object[mmdays.length * cShort.length][5];
			String saveCredits = "INSERT INTO HRMS_SETTL_CREDITS(SETTL_CODE,SETTL_CREDIT_CODE,SETTL_AMT,SETTL_MTH,SETTL_YEAR,SETTL_MTH_TYPE) "
					+ "VALUES(" + settleDetails.getSettCode() + ",?,?,?,?,?)";

			Object DebitData[][] = new Object[mmdays.length * dShort.length][5];
			String saveDebits = "INSERT INTO HRMS_SETTL_DEBITS(SETTL_CODE,SETTL_DEBIT_CODE,SETTL_AMT,SETTL_MTH,SETTL_YEAR,SETTL_MTH_TYPE) "
					+ "VALUES(" + settleDetails.getSettCode() + ",?,?,?,?,?)";
			if (shortRows != null) {
				int k = 0;
				int j = 0;

				for (int i = 0; i < mmdays.length; i++) {
					for (int m = 0, n = 0; m < cShort.length + dShort.length; m++) {
						if (m < cShort.length) {
							CreditData[j][0] = cShort[m][0]; // credit_code
							CreditData[j][1] = shortRows[i][m]; // amount
							CreditData[j][2] = mmdays[i];
							CreditData[j][3] = yydays[i];
							CreditData[j][4] = monthType[i];
							j++;

						} else {

							DebitData[k][0] = dShort[n][0];
							DebitData[k][1] = shortRows[i][m];
							DebitData[k][2] = mmdays[i];
							DebitData[k][3] = yydays[i];
							DebitData[k][4] = monthType[i];
							k++;
							n++;

						}

					}// this is end of c.length + d.length for loop
				}
			}
			try {

				creResult = getSqlModel()
						.singleExecute(saveCredits, CreditData);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

			try {

				debResult = getSqlModel().singleExecute(saveDebits, DebitData);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			if (creResult && debResult)
				result = true;
			else
				result = false;

			return result;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public Object[][] retrieveSettleData(SettlementDetails settleDetails, HttpServletRequest request) {
		try {
			logger.info("########### IN RETRIEVE DETAILS ############");
			
			String settQue = "SELECT DISTINCT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,"
					+ " NVL(CENTER_NAME,' '), NVL(RANK_NAME,' '),TO_CHAR(HRMS_RESIGN.RESIGN_DATE,'DD-MM-YYYY'),"
					+ " NVL(TO_CHAR(SETTL_SETTLDT,'DD-MM-YYYY'),TO_CHAR(SYSDATE,'DD-MM-YYYY')),TO_CHAR(HRMS_RESIGN.RESIGN_SEPR_DATE,'DD-MM-YYYY'),NVL(SETTL_PAYMODE,' '),NVL(HRMS_BANK.BANK_NAME,' '),NVL(SETTL_CHEQUENO,' '),TO_CHAR(SETTL_CHEQUEDT,'DD-MM-YYYY'),"
					+ " E1.EMP_FNAME||' '||E1.EMP_MNAME||' '||E1.EMP_LNAME,"
					+ " E2.EMP_FNAME||' '||E2.EMP_MNAME||' '||E2.EMP_LNAME,"
					+ " E3.EMP_FNAME||' '||E3.EMP_MNAME||' '||E3.EMP_LNAME,"
					+ " E4.EMP_FNAME||' '||E4.EMP_MNAME||' '||E4.EMP_LNAME,"
					+ " NVL(SETTL_ELIGIBLEDAYS,'0'),NVL(SETTL_PERMSETTLEMENT,' '),NVL(SETTL_GRATUITY,'0'),NVL(SETTL_REIMBURSE,'0'),NVL(SETTL_LEAVE_ENCASH,'0'),NVL(SETTL_DEDUCTION,'0'),NVL(SETTL_NETAMT,' '),"
					+ " NVL(HRMS_RESIGN.RESIGN_CODE,'0'),NVL(SETTL_PREPBY,'0'),NVL(SETTL_CHKBYHR,'0'),NVL(SETTL_CHKBYACC,'0'),NVL(SETTL_HANDOVER,'0'),NVL(SETTL_COMMENT,' '),NVL(SETTL_SHORTPERIOD,'0'),"
					+ "  NVL(HRMS_RESIGN.RESIGN_NOTICE_PERIOD,'0'),HRMS_RESIGN.RESIGN_NOTICE_STATUS,NVL(SETTL_BANKNM,'0'),SETTL_SHORTPAYFLAG,SETTL_LOCKFLAG,NVL(SETTL_LEAVEDAYS,'0'),RESIGN_EMP"
					+ " ,TO_CHAR(HRMS_SETTL_HDR.SETTL_SEPRDT,'DD-MM-YYYY'), NVL(HRMS_SETTL_HDR.SETTL_NOTICEDAYS,'0'),DECODE(NVL(TO_CHAR(HRMS_SETTL_HDR.SETTL_LOCKFLAG),'R'),'R','Not Processed','N','Processed','Locked') "
					+ "  FROM HRMS_SETTL_HDR"
					+ " LEFT JOIN HRMS_RESIGN on(HRMS_SETTL_HDR.SETTL_RESGNO=HRMS_RESIGN.RESIGN_CODE)"
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_RESIGN.RESIGN_EMP = HRMS_EMP_OFFC.EMP_ID) "
					+ " INNER JOIN  HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
					+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
					+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
					+ " LEFT JOIN HRMS_EMP_OFFC E1 ON(E1.EMP_ID = HRMS_SETTL_HDR.SETTL_PREPBY) "
					+ " LEFT JOIN HRMS_TITLE T1 ON(T1.TITLE_CODE=E1.EMP_TITLE_CODE)"
					+ " LEFT JOIN HRMS_EMP_OFFC E2 ON(E2.EMP_ID = HRMS_SETTL_HDR.SETTL_CHKBYHR) "
					+ " LEFT JOIN HRMS_TITLE T2 ON(T2.TITLE_CODE=E2.EMP_TITLE_CODE)"
					+ " LEFT JOIN HRMS_EMP_OFFC E3 ON(E3.EMP_ID = HRMS_SETTL_HDR.SETTL_CHKBYACC) "
					+ " LEFT JOIN HRMS_TITLE T3 ON(T3.TITLE_CODE=E3.EMP_TITLE_CODE)"
					+ " LEFT JOIN HRMS_EMP_OFFC E4 ON(E4.EMP_ID = HRMS_SETTL_HDR.SETTL_HANDOVER) "
					+ " LEFT JOIN HRMS_TITLE T4 ON(T4.TITLE_CODE=E4.EMP_TITLE_CODE)"
					+ " LEFT JOIN HRMS_BANK  ON(HRMS_BANK.BANK_MICR_CODE=HRMS_SETTL_HDR.SETTL_BANKNM)"
					+ " WHERE HRMS_SETTL_HDR.SETTL_CODE="
					+ settleDetails.getSettCode();

			Object[][] settObj = getSqlModel().getSingleResult(settQue);
			
			for (int i = 0; i < settObj.length; i++) {
				settleDetails.setSettDate(checkNull(String.valueOf(settObj[i][5]).trim()));
				settleDetails.setModePayment(checkNull(String.valueOf(settObj[i][7]).trim()));
				if (String.valueOf(settObj[i][7]).equals("CH")) {
					settleDetails.setBank(checkNull(String.valueOf(settObj[i][8]).trim()));
					settleDetails.setChequeno(checkNull(String.valueOf(settObj[i][9]).trim()));
					settleDetails.setChequeDate(checkNull(String.valueOf(settObj[i][10]).trim()));
				} else {
					settleDetails.setBank(checkNull(String.valueOf(settObj[i][8]).trim()));
					settleDetails.setChequeno(checkNull(String.valueOf(settObj[i][9]).trim()));
					settleDetails.setChequeDate(checkNull(String.valueOf(settObj[i][10]).trim()));
				}
				settleDetails.setPreparedby(checkNull(String.valueOf(settObj[i][11]).trim()));
				settleDetails.setCheckedby(checkNull(String.valueOf(settObj[i][12]).trim()));
				settleDetails.setAccCheck(checkNull(String.valueOf(settObj[i][13]).trim()));
				settleDetails.setHandedOver(checkNull(String.valueOf(settObj[i][14]).trim()));
				settleDetails.setPermSettle(checkNull(String.valueOf(settObj[i][16]).equals("Y") ? "true" : "false"));
				settleDetails.setGratuity(checkNull(String.valueOf(settObj[i][17]).trim()));
				settleDetails.setReimburse(checkNull(String.valueOf(settObj[i][18]).trim()));
				settleDetails.setDeduct(checkNull(String.valueOf(settObj[i][20]).trim()));
				settleDetails.setSettleAmt(checkNull(String.valueOf(settObj[i][21]).trim()));
				settleDetails.setPrepbyCode(String.valueOf(settObj[i][23]));
				settleDetails.setChkbyCode(String.valueOf(settObj[i][24]));
				settleDetails.setAccChkCode(String.valueOf(settObj[i][25]));
				settleDetails.setHandOverCode(String.valueOf(settObj[i][26]));
				settleDetails.setComments(checkNull(String.valueOf(settObj[i][27]).trim()));
				settleDetails.setBankMicrId(String.valueOf(settObj[i][31]));
				settleDetails.setIsSettled(String.valueOf(settObj[i][38]));
			}
			return settObj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public void setDetailTable(SettlementDetails settleDetails,
			HttpServletRequest request) {
		try {
			ArrayList<Object> list = new ArrayList<Object>();
			int listCode;
			try {
				listCode = Integer.parseInt(request.getParameter("listCode"));
				// logger.info("listCode....." + listCode);
			} catch (Exception e) {
				listCode = 0;
			}
			int payListCode = Integer.parseInt(request
					.getParameter("payListCode"));
			// logger.info("payListCode....." + payListCode);
			String type[] = request.getParameterValues("calPayByType");
			String month[] = request.getParameterValues("payByMnth");
			String year[] = request.getParameterValues("payByYr");
			String eligibleDays[] = request.getParameterValues("calPayByDays");
			String ohType[] = request.getParameterValues("calType");
			String ohMonth[] = request.getParameterValues("ohMonth");
			String ohYear[] = request.getParameterValues("ohYear");
			String ohDays[] = request.getParameterValues("calDays");

			int y = 0, i = 0;
			if (payListCode > 0) {
				SettlementDetails bean = null;
				for (i = 0; i < payListCode; i++) {
					// PAY BY DAYS OBJECT
					bean = new SettlementDetails();
					bean.setPayByMnth(month[i]);
					bean.setPayByYr(year[i]);

					// String[] splitObj = monthYr[i].split("-");
					bean.setPayMonth(year[i] + month[i]);

					bean.setCalPayByType(type[i]);
					bean.setCalPayByDays(eligibleDays[i]);
					bean.setOnholdFlag(false);
					list.add(bean);
				}
				settleDetails.setTypeList(list);
			}
			if (listCode > 0) {
				SettlementDetails bean1 = null;
				// ONHOLD OBJECT
				for (y = 0; y < listCode; y++) {
					bean1 = new SettlementDetails();
					bean1.setOhMonth(ohMonth[y]);
					bean1.setOhYear(ohYear[y]);
					bean1.setCalMonth(ohYear[y] + ohMonth[y]);

					bean1.setCalType(ohType[y]);
					bean1.setCalDays(ohDays[y]);
					bean1.setOnholdFlag(true);
					list.add(bean1);

				}
				settleDetails.setOnholdFlag(true);
				settleDetails.setTypeList(list);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Object[][] retrieveSettleDetails(SettlementDetails settleDetails,
			HttpServletRequest request) {
		try {
			ArrayList<Object> list = new ArrayList<Object>();
			String query = "SELECT SETTL_CODE, SETTL_MONTH, SETTL_YEAR, SETTL_TYPE, SETTL_DAYS, SETTL_CAL_DAYS"
					+ " FROM HRMS_SETTL_DTL"
					+ " WHERE SETTL_CODE = "
					+ settleDetails.getSettCode()
					// + " group by SETTL_TYPE, SETTL_CODE, SETTL_MONTH,
					// SETTL_YEAR, SETTL_DAYS, SETTL_CAL_DAYS "
					+ " order by SETTL_TYPE, SETTL_MONTH";
			Object detailObj[][] = getSqlModel().getSingleResult(query);
			// set join date
			String joinYrQuery = "SELECT TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') FROM HRMS_EMP_OFFC WHERE EMP_ID ="
					+ settleDetails.getEmpCode();
			Object[][] joinYrObj = getSqlModel().getSingleResult(joinYrQuery);
			settleDetails.setJoinDate("" + joinYrObj[0][0]);
			// set notice date
			DateUtility c = new DateUtility();
			// logger.info("getResignDate......."+settleDetails.getResignDate());
			// logger.info("getNoticeStatus......."+settleDetails.getNoticeStatus());
			// logger.info("getNoticePeriod......."+settleDetails.getNoticePeriod());
			String noticeDate = c.getNoticeDate(settleDetails.getResignDate(),
					settleDetails.getNoticeStatus(), Integer
							.parseInt(settleDetails.getNoticePeriod()));
			// logger.info("Notice date :" + noticeDate);
			settleDetails.setNoticeDate(noticeDate);
			String month = "";
			int y = 0, z = 0;
			for (int i = 0; i < detailObj.length; i++) {
				SettlementDetails bean = new SettlementDetails();
				settleDetails.setSettCode(String.valueOf(detailObj[i][0]));
				if (String.valueOf(detailObj[i][3]).equals("CO")
						|| String.valueOf(detailObj[i][3]).trim().equals("EM")) {
					bean.setPayByMnth(String.valueOf(detailObj[i][1]));
					bean.setPayByYr(String.valueOf(detailObj[i][2]));
					if (Integer.parseInt(String.valueOf(detailObj[i][1])) < 10)
						month = 0 + String.valueOf(detailObj[i][1]);
					else
						month = String.valueOf(detailObj[i][1]);
					bean.setPayMonth(String.valueOf(detailObj[i][2]) + month);
					bean
							.setCalPayByType(String.valueOf(detailObj[i][3])
									.trim());
					bean.setCalPayByDays(String.valueOf(detailObj[i][4]));
					bean.setMaxDays(String.valueOf(detailObj[i][5]));
					bean.setOnholdFlag(false);
					z++;
					settleDetails.setPayListCode(String.valueOf(z));
					list.add(bean);
					try {
						list = sortList(list, settleDetails);
						settleDetails.setTypeList(list);
					} catch (Exception e) {
						logger.error("Error in sorting   :" + e);
						e.printStackTrace();
					}

				} else {
					bean.setOhMonth(String.valueOf(detailObj[i][1]));
					bean.setOhYear(String.valueOf(detailObj[i][2]));
					bean.setCalMonth(String.valueOf(detailObj[i][2])
							+ String.valueOf(detailObj[i][1]));

					bean.setCalType(String.valueOf(detailObj[i][3]).trim());
					bean.setCalDays(String.valueOf(detailObj[i][4]));
					bean.setOhMaxDays(String.valueOf(detailObj[i][5]));
					bean.setOnholdFlag(true);
					y++;
					settleDetails.setListCode(String.valueOf(y));
					settleDetails.setOnholdFlag(true);
					list.add(bean);
				}
				// list.add(bean);
			}
			settleDetails.setTypeList(list);

			return detailObj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String[][] getLeaveRecordSearch(SettlementDetails settleDetails) {
		String policyCode = getLeavePolicyCode(settleDetails.getEmpCode());
		if (policyCode.length() > 0) {
			/*
			 * String query = "SELECT HRMS_LEAVE.LEAVE_NAME, SETTL_ENCASH_DAYS,
			 * SETTL_LEAVE_CODE,
			 * SETTL_ENCASH_AMOUNT,HRMS_LEAVE_POLICY.LEAVE_ENCASH_FORMULA,HRMS_LEAVE_POLICY.LEAVE_ENCASH_MAX_LIMIT " + "
			 * ,NVL(LEAVE_CLOSING_BALANCE,0) FROM HRMS_SETTL_LEAVEENCASH" + "
			 * LEFT JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID
			 * =HRMS_SETTL_LEAVEENCASH.SETTL_LEAVE_CODE)" + "LEFT JOIN
			 * HRMS_LEAVE_BALANCE
			 * ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_BALANCE.LEAVE_CODE AND EMP_ID =" +
			 * settleDetails.getEmpCode() + ") " + " INNER JOIN
			 * HRMS_LEAVE_POLICY
			 * ON(HRMS_LEAVE_POLICY.LEAVE_CODE=HRMS_LEAVE.LEAVE_ID" + " AND
			 * LEAVE_APPLICABLE='Y' AND LEAVE_ENCASH_FLAG='Y'" + " AND
			 * HRMS_LEAVE_POLICY.LEAVE_POLICY_CODE=" + policyCode + ")" + "
			 * WHERE SETTL_RESIGN_CODE=" + settleDetails.getResignCode() + "
			 * ORDER BY SETTL_LEAVE_CODE";
			 */

			String query = " SELECT HRMS_LEAVE.LEAVE_NAME, SETTL_ENCASH_DAYS, SETTL_LEAVE_CODE, SETTL_ENCASH_AMOUNT,HRMS_LEAVE_POLICY_DTL.LEAVE_ENC_FORMULA,HRMS_LEAVE_POLICY_DTL.LEAVE_ENC_MAXLIMIT "
					+ " ,NVL(LEAVE_CLOSING_BALANCE,0), HRMS_LEAVE_POLICY_DTL.LEAVE_ENC_MAXLIMIT_SEPARATION "
					+ " ,(CASE WHEN (LEAST(NVL(LEAVE_CLOSING_BALANCE,0),HRMS_LEAVE_POLICY_DTL.LEAVE_ENC_MAXLIMIT_SEPARATION))=0 THEN NVL(LEAVE_CLOSING_BALANCE,0) ELSE LEAST(NVL(LEAVE_CLOSING_BALANCE,0),HRMS_LEAVE_POLICY_DTL.LEAVE_ENC_MAXLIMIT_SEPARATION) END) AS LEAVEDAYS "
					+ " , NVL(LEAVE_CLOSING_BALANCE-SETTL_ENCASH_DAYS,0)"
					+ " FROM HRMS_SETTL_LEAVEENCASH "
					+ " LEFT JOIN HRMS_LEAVE ON(HRMS_LEAVE.LEAVE_ID =HRMS_SETTL_LEAVEENCASH.SETTL_LEAVE_CODE) "
					+ " LEFT JOIN HRMS_LEAVE_BALANCE ON(HRMS_LEAVE.LEAVE_ID=HRMS_LEAVE_BALANCE.LEAVE_CODE AND EMP_ID ="
					+ settleDetails.getEmpCode()
					+ " ) "
					+ " INNER JOIN HRMS_LEAVE_POLICY_DTL ON(HRMS_LEAVE_POLICY_DTL.LEAVE_CODE=HRMS_LEAVE.LEAVE_ID "
					+ " AND LEAVE_APPLICABLE='Y' AND LEAVE_ENC_FLAG='Y' AND HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE="
					+ policyCode
					+ ") "
					+ " WHERE SETTL_CODE="
					+ settleDetails.getSettCode()
					+ " ORDER BY SETTL_LEAVE_CODE ";

			Object[][] data = getSqlModel().getSingleResult(query);

			String hdrQuery = "SELECT SETTL_LEAVE_ENCASH,SETTL_LEAVEDAYS FROM HRMS_SETTL_HDR WHERE SETTL_RESGNO="
					+ settleDetails.getResignCode();
			Object[][] hdrQueryData = getSqlModel().getSingleResult(hdrQuery);

			String[][] total = new String[data.length][8];
			double total_encash = 0.0d;
			for (int i = 0; i < data.length; i++) {
				total[i][0] = String.valueOf(data[i][0]); // leave name
				total[i][1] = String.valueOf(data[i][1]);// leave days
				total[i][2] = String.valueOf(data[i][7]);// max encash limit
				// on sep
				total[i][3] = String.valueOf(data[i][3]);// amount set to
				// zero
				total[i][4] = String.valueOf(data[i][2]);// leave id
				total[i][6] = String.valueOf(data[i][5]);// leave encash max
				// total[i][7] = String.valueOf(data[i][6]);// closing balance

				/*
				 * Object[][] settlementLeaves =
				 * getSettlementEntitle(settleDetails .getSepDate(),
				 * policyCode); double leaveTotEntitle =
				 * Double.parseDouble(String .valueOf(data[i][9])); if
				 * (settlementLeaves != null && settlementLeaves.length > 0) {
				 * for (int j = 0; j < settlementLeaves.length; j++) { // if
				 * leave codes match if (String.valueOf(data[i][2]).equals(
				 * String.valueOf(settlementLeaves[j][0]))) { leaveTotEntitle =
				 * Double.parseDouble(String .valueOf(data[i][9])) +
				 * Double.parseDouble(String .valueOf(settlementLeaves[j][1])); //
				 * if greater than separation limit in policy, set // separation
				 * limit as leaves if (leaveTotEntitle >
				 * Double.parseDouble(String .valueOf(data[i][7]))) {
				 * leaveTotEntitle = Double.parseDouble(String
				 * .valueOf(data[i][7])); } } } }
				 * 
				 * 
				 * logger.info("leaveTotEntitle......."+leaveTotEntitle);
				 * 
				 * logger.info("leaveTotEntitle......."+(leaveTotEntitle-Double.parseDouble(String.valueOf(data[i][1]))) );
				 * 
				 * total[i][7] = String.valueOf(new Utility()
				 * .twoDecimals(leaveTotEntitle-Double.parseDouble(String.valueOf(data[i][1]))));
				 * 
				 * 
				 * 
				 */
				total[i][7] = String.valueOf(data[i][8]);

				try {
					double totAmt = 0.0;
					
					double amount = 0;
					amount = Utility.expressionEvaluate(new Utility()
							.generateFormula(settleDetails.getEmpCode(), String
									.valueOf(data[i][4]), context, // encash
									// formula
									session));
					total[i][5] = String.valueOf(new Utility().twoDecimal(amount,2)); // 				
					
					if(!String.valueOf(data[i][3]).equals("")){
						totAmt = Double.parseDouble(String.valueOf(data[i][3]));
				        total_encash += totAmt;
					}else{
					
					totAmt = Double.parseDouble(total[i][5])
							* Double.parseDouble(total[i][1]);
					total_encash += totAmt;
				}
					//}
					// total[i][3] = String.valueOf(new Utility()
					// .twoDecimals(totAmt));

					// logger.info("Leave total[i][3] saved ::" + total[i][3]);
				}// end of try
				catch (Exception e) {
					logger.error("Exception in getLeaveRecord---------------"
							+ e);
				}// end of catch
			}
			if (data != null && data.length > 0) {
				settleDetails.setIsFlag("true");
			}
			settleDetails.setTotalAmt(Utility.twoDecimals(String
					.valueOf(total_encash)));
			settleDetails.setTotalLeaveDays(String.valueOf(hdrQueryData[0][1]));
			return total;
		}
		return new String[0][0];
	}

	public Vector<Object[][]> viewOnholdSalary(SettlementDetails settleDetails,
			HttpServletRequest request) {
		try {
			String procFlagQue = "SELECT DISTINCT SETTL_MTH_TYPE FROM HRMS_SETTL_DEBITS WHERE SETTL_MTH_TYPE = 'OH'";
			Object[][] procFlagObj = getSqlModel().getSingleResult(procFlagQue);

			Object[][] finalObj = null;
			Vector<Object[][]> month_year = new Vector<Object[][]>();
			ArrayList<Object[][]> credit_List = new ArrayList<Object[][]>();

			if (String.valueOf(procFlagObj[0][0]).equals("OH")) {
				String onholdQue = "SELECT DISTINCT SETTL_MONTH, SETTL_YEAR, SETTL_TYPE FROM HRMS_SETTL_DTL WHERE SETTL_TYPE = 'OH' and  SETTL_CODE ="
						+ settleDetails.getSettCode()
						+ " ORDER BY SETTL_MONTH DESC";

				Object[][] onholdObj = getSqlModel().getSingleResult(onholdQue);
				double credit_amt = 0.0d, debit_amt = 0.0d;
				try {

					if (onholdObj.length > 0) {
						for (int i = 0; i < onholdObj.length; i++) {

							String creditQue = "SELECT distinct HRMS_CREDIT_HEAD.CREDIT_NAME,NVL(SETTL_AMT,'0'),SETTL_CREDIT_CODE FROM HRMS_SETTL_CREDITS INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_SETTL_CREDITS.SETTL_CREDIT_CODE)"
									+ " WHERE SETTL_MTH ="
									+ onholdObj[i][0]
									+ " and SETTL_YEAR="
									+ onholdObj[i][1]
									+ " AND SETTL_CODE ="
									+ settleDetails.getSettCode()
									+ " AND SETTL_MTH_TYPE = 'OH' ORDER BY SETTL_CREDIT_CODE";

							Object[][] creditObj = getSqlModel()
									.getSingleResult(creditQue);

							String debitQue = "SELECT HRMS_DEBIT_HEAD.DEBIT_NAME,NVL(SETTL_AMT,'0'),SETTL_DEBIT_CODE FROM HRMS_SETTL_DEBITS "
									+ " INNER JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_SETTL_DEBITS.SETTL_DEBIT_CODE)"
									+ " WHERE SETTL_MTH ="
									+ onholdObj[i][0]
									+ " and  SETTL_YEAR="
									+ onholdObj[i][1]
									+ " AND SETTL_CODE ="
									+ settleDetails.getSettCode()
									+ " AND SETTL_MTH_TYPE = 'OH' ORDER BY HRMS_DEBIT_HEAD.DEBIT_PRIORITY,HRMS_DEBIT_HEAD.DEBIT_CODE";
							Object[][] debitObj = getSqlModel()
									.getSingleResult(debitQue);
							int lengthOfObj = 0;
							if (creditObj.length > debitObj.length) {
								lengthOfObj = creditObj.length;
							} else
								lengthOfObj = debitObj.length;
							finalObj = new Object[lengthOfObj][];
							Object[][] mon_yr = new Object[1][8];

							mon_yr[0][0] = String.valueOf(onholdObj[i][0]);
							mon_yr[0][1] = String.valueOf(onholdObj[i][1]);
							mon_yr[0][2] = "";
							mon_yr[0][3] = "";
							mon_yr[0][4] = "";
							mon_yr[0][5] = "";

							finalObj = Utility.joinArrays(creditObj, debitObj,
									0, 0);
							if (String.valueOf(onholdObj[i][2]).equals("OH")) {
								for (int j = 0; j < finalObj.length; j++) {
									// add credits
									if (!String.valueOf(finalObj[j][1]).equals(
											""))
										credit_amt += Double.parseDouble(String
												.valueOf(finalObj[j][1]));
									// subtract debits from credits
									if (!String.valueOf(finalObj[j][4]).equals(
											""))
										debit_amt += Double.parseDouble(String
												.valueOf(finalObj[j][4]));

								}
							} else {
								credit_amt = 0.0;
								debit_amt = 0.0;
							}
							// logger.info("total_amt....."
							// + (credit_amt - debit_amt));
							mon_yr[0][6] = String.valueOf(onholdObj[i][2]);

							mon_yr[0][7] = String
									.valueOf((credit_amt - debit_amt));

							// settleDetails.setNetOnHold(String.valueOf(total_amt));
							month_year.add(mon_yr);
							credit_List.add(finalObj);
							try {

								if (credit_List.size() > 0) {
									request.setAttribute("credit_List",
											credit_List);
									request.setAttribute("month_year",
											month_year);
									settleDetails.setProcessFlag("true");
									settleDetails.setOnholdFlag(true);
								} else
									settleDetails.setProcessFlag("false");
								settleDetails.setOnholdFlag(false);
							} catch (Exception e) {
								e.printStackTrace();
							}

						}

					}// end if

				} catch (Exception e) {
					logger.error("Error calculating credits/debits  :" + e);
					e.printStackTrace();
				}
			}
			return month_year;
		} catch (Exception e) {
			return null;
		}

	}

	// Calling saved sal records
	public ArrayList<Object[][]> savedShortSalary(
			SettlementDetails settleDetails, HttpServletRequest request) {
		try {
			Vector<Object[][]> mon_List = new Vector<Object[][]>();
			ArrayList<Object[][]> rows_List = new ArrayList<Object[][]>();
			ArrayList<SettlementDetails> creditNames = new ArrayList<SettlementDetails>();
			ArrayList<SettlementDetails> debitNames = new ArrayList<SettlementDetails>();
			Object[][] empId = getEmpId(settleDetails);

			String daysFlagQue = "SELECT DISTINCT SETTL_MTH_TYPE FROM HRMS_SETTL_DEBITS WHERE SETTL_CODE = "
					+ settleDetails.getSettCode();
			Object[][] daysFlagObj = getSqlModel().getSingleResult(daysFlagQue);
			if (daysFlagObj.length > 0) {
				if (String.valueOf(daysFlagObj[0][0]).equals("CO")
						|| String.valueOf(daysFlagObj[0][0]).equals("EM")) {
					/*
					 * FOR GETTING CREDIT CODE AND CREDIT ABBR WHICH USED FOR
					 * DISPLAYING AS NAME OF CREDIT ON SCREEN
					 * 
					 */
					Object credit_header[][] = null;
					String selectCredit = "SELECT CREDIT_CODE,  CREDIT_NAME FROM HRMS_CREDIT_HEAD WHERE CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' ORDER BY CREDIT_CODE";
					credit_header = getSqlModel().getSingleResult(selectCredit);
					/*
					 * FOR GETTING DEBIT CODE AND DEBIT ABBR WHICH USED FOR
					 * DISPLAYING AS NAME OF DEBIT ON SCREEN
					 * 
					 */
					Object debit_header[][] = null;
					String selectDebit = "SELECT DEBIT_CODE,  DEBIT_NAME FROM HRMS_DEBIT_HEAD WHERE DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' ORDER BY DEBIT_PRIORITY,DEBIT_CODE ";
					debit_header = getSqlModel().getSingleResult(selectDebit);

					request.setAttribute("creditLength", credit_header);
					request.setAttribute("debitLength", debit_header);

					for (int c = 0; c < credit_header.length; c++) {
						/**
						 * WE ARE USING DYNAMICS HEADER SO FOR DISPLAYING CREDIT
						 * HEADER THIS LOOP IS USED
						 */
						SettlementDetails creditName = new SettlementDetails();
						creditName.setCreditCode(String.valueOf(credit_header[c][0]));
						creditName.setCreditHead(String.valueOf(credit_header[c][1]));
						creditNames.add(creditName);

					}// end for loop

					for (int d = 0; d < debit_header.length; d++) {
						/**
						 * FOR DISPLAYING DEBIT NAME DYNAMICALLY
						 */
						SettlementDetails debitName = new SettlementDetails();
						debitName.setDebitCode(String.valueOf(debit_header[d][1]));
						debitName.setDebitHead(String.valueOf(debit_header[d][1]));
						debitNames.add(debitName);

					}// end i for loop

					settleDetails.setCreditHeader(creditNames);
					settleDetails.setDebitHeader(debitNames);

					String daysQuery = " SELECT SETTL_MONTH, SETTL_YEAR, SETTL_TYPE, SETTL_DAYS "
							+ " FROM HRMS_SETTL_DTL "
							+ " WHERE SETTL_TYPE IN ('CO','EM') and  SETTL_CODE = "
							+ settleDetails.getSettCode()
							+ " ORDER BY SETTL_MONTH";
					Object[][] daysObj = getSqlModel().getSingleResult(daysQuery);

					try {
						if (daysObj.length > 0) {
							for (int i = 0; i < daysObj.length; i++) {
								Object[][] mm = new Object[1][4];
								mm[0][0] = String.valueOf(daysObj[i][0]);
								mm[0][1] = String.valueOf(daysObj[i][1]);
								mm[0][2] = String.valueOf(daysObj[i][3]);
								mm[0][3] = String.valueOf(daysObj[i][2]);
								mon_List.add(mm);
								Object[][] rows = new Object[1][];

								for (int y = 0; y < empId.length; y++) {
									/*
									 * getRow METHOD RETURN A COMLETE ROW FOR A
									 * EMPLOYEE AND SET EACH ROW IN A 2
									 * DIMENSIONSION ARRARY THAT ROWS LENNGTH IS
									 * EQUAL TO TOTAL NO OF EMPLPYEE
									 */
									Object[][] row = getSalRow(String
											.valueOf(empId[y][0]), String
											.valueOf(empId[y][1]), String
											.valueOf(empId[y][2]), String
											.valueOf(daysObj[i][0]), String
											.valueOf(daysObj[i][1]),
											credit_header, debit_header,
											settleDetails, String
													.valueOf(daysObj[i][3]),
											"S", String.valueOf(daysObj[i][2]));

									if (row[0].length > 0 && row[0] != null) {
										rows[0] = row[0];
									}// end if
								}// end for loop
								for (int j = 0; j < rows.length; j++) {
									for (int j2 = 0; j2 < rows[0].length; j2++) {
										// logger.info("rows["+j+"]"+"["+j2+"]......."+rows[j][j2]);
									}
								}
								rows_List.add(rows);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						return null;
					}
				}
			}
			request.setAttribute("mon_List", mon_List);
			request.setAttribute("rows_List", rows_List);
			return rows_List;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Object[][]> viewShortSalary(
			SettlementDetails settleDetails, HttpServletRequest request,
			String[] cal_type, String[] cal_mon, String[] cal_year) {
		try {
			Vector<Object[][]> mon_List = new Vector<Object[][]>();
			ArrayList<Object[][]> rows_List = new ArrayList<Object[][]>();
			ArrayList<SettlementDetails> creditNames = new ArrayList<SettlementDetails>();
			ArrayList<SettlementDetails> debitNames = new ArrayList<SettlementDetails>();
			Object[][] empId = getEmpId(settleDetails);

			String daysFlagQue = "SELECT DISTINCT SETTL_MTH_TYPE FROM HRMS_SETTL_DEBITS WHERE SETTL_CODE = "
					+ settleDetails.getSettCode();
			Object[][] daysFlagObj = getSqlModel().getSingleResult(daysFlagQue);
			if (daysFlagObj.length > 0) {
				if (String.valueOf(daysFlagObj[0][0]).equals("CO")
						|| String.valueOf(daysFlagObj[0][0]).equals("EM")) {
					/*
					 * FOR GETTING CREDIT CODE AND CREDIT ABBR WHICH USED FOR
					 * DISPLAYING AS NAME OF CREDIT ON SCREEN
					 * 
					 */
					Object credit_header[][] = null;
					String selectCredit = "SELECT CREDIT_CODE,  CREDIT_NAME FROM HRMS_CREDIT_HEAD WHERE CREDIT_PERIODICITY='M' AND CREDIT_PAYFLAG='Y' ORDER BY CREDIT_CODE";
					credit_header = getSqlModel().getSingleResult(selectCredit);
					/*
					 * FOR GETTING DEBIT CODE AND DEBIT ABBR WHICH USED FOR
					 * DISPLAYING AS NAME OF DEBIT ON SCREEN
					 * 
					 */
					Object debit_header[][] = null;
					String selectDebit = "SELECT DEBIT_CODE,  DEBIT_NAME FROM HRMS_DEBIT_HEAD WHERE DEBIT_PERIODICITY='M' AND DEBIT_PAYFLAG='Y' ORDER BY DEBIT_PRIORITY,DEBIT_CODE ";
					debit_header = getSqlModel().getSingleResult(selectDebit);

					request.setAttribute("creditLength", credit_header);
					request.setAttribute("debitLength", debit_header);

					for (int c = 0; c < credit_header.length; c++) {
						/**
						 * WE ARE USING DYNAMICS HEADER SO FOR DISPLAYING CREDIT
						 * HEADER THIS LOOP IS USED
						 */
						SettlementDetails creditName = new SettlementDetails();
						creditName.setCreditCode(String
								.valueOf(credit_header[c][0]));
						creditName.setCreditHead(String
								.valueOf(credit_header[c][1]));
						creditNames.add(creditName);

					}// end for loop

					for (int d = 0; d < debit_header.length; d++) {
						/**
						 * FOR DISPLAYING DEBIT NAME DYNAMICALLY
						 */
						SettlementDetails debitName = new SettlementDetails();
						debitName.setDebitCode(String
								.valueOf(debit_header[d][1]));
						debitName.setDebitHead(String
								.valueOf(debit_header[d][1]));
						debitNames.add(debitName);

					}// end i for loop

					settleDetails.setCreditHeader(creditNames);
					settleDetails.setDebitHeader(debitNames);

					String daysQuery = " SELECT SETTL_MONTH, SETTL_YEAR, SETTL_TYPE, SETTL_DAYS, SETTL_CAL_DAYS "
							+ " FROM HRMS_SETTL_DTL "
							+ " WHERE SETTL_CODE = "
							+ settleDetails.getSettCode()
							+ " AND SETTL_MONTH IN("
							+ getStringWithComma(cal_mon, 0)
							+ ")"
							+ " AND SETTL_YEAR IN("
							+ getStringWithComma(cal_year, 0)
							+ ")"
							+ " AND SETTL_TYPE IN("
							+ getStringWithComma(cal_type, 1)
							+ ")"
							+ " ORDER BY SETTL_MONTH";
					Object[][] daysObj = getSqlModel().getSingleResult(
							daysQuery);

					try {
						if (daysObj.length > 0) {
							for (int i = 0; i < daysObj.length; i++) {
								Object[][] mm = new Object[1][5];
								mm[0][0] = String.valueOf(daysObj[i][0]);
								logger.info("calculate month  : " + mm[0][0]);
								mm[0][1] = String.valueOf(daysObj[i][1]);
								logger.info("calculate year  : " + mm[0][1]);
								mm[0][2] = String.valueOf(daysObj[i][3]);
								logger.info("calculate days  : " + mm[0][2]);
								mm[0][3] = String.valueOf(daysObj[i][2]);
								logger.info("calculate type  : " + mm[0][3]);
								mm[0][4] = String.valueOf(daysObj[i][4]);
								logger.info("calculate maxdays  : " + mm[0][4]);

								mon_List.add(mm);
								Object[][] rows = new Object[1][];

								for (int y = 0; y < empId.length; y++) {
									/*
									 * getRow METHOD RETURN A COMLETE ROW FOR A
									 * EMPLOYEE AND SET EACH ROW IN A 2
									 * DIMENSIONSION ARRARY THAT ROWS LENNGTH IS
									 * EQUAL TO TOTAL NO OF EMPLPYEE
									 */
									Object[][] row = getSalRow(String
											.valueOf(empId[y][0]), String
											.valueOf(empId[y][1]), String
											.valueOf(empId[y][2]), String
											.valueOf(daysObj[i][0]), String
											.valueOf(daysObj[i][1]),
											credit_header, debit_header,
											settleDetails, String
													.valueOf(daysObj[i][3]),
											"S", String.valueOf(daysObj[i][2]));

									if (row[0].length > 0 && row[0] != null) {
										rows[0] = row[0];
									}// end if
								}// end for loop
								for (int j = 0; j < rows.length; j++) {
									for (int j2 = 0; j2 < rows[0].length; j2++) {
										// logger.info("rows["+j+"]"+"["+j2+"]......."+rows[j][j2]);
									}
								}
								rows_List.add(rows);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						return null;
					}
				}
			}
			request.setAttribute("mon_List", mon_List);
			combinedMonthList.add(mon_List);

			return rows_List;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Object[][] getSalRow(String emp_id, String emp_token,
			String emp_name, String month, String year,
			Object creditLength[][], Object debitLength[][],
			SettlementDetails settleDetails, String sal_days,
			String MONTH_TYPE, String sal_type) {

		Object[][] debit_amount = null;
		Object[][] credit_amount = null;

		debit_amount = getSalDebit(emp_id, year, debitLength, settleDetails,
				sal_type, month);

		/**
		 * MONTH OF SALARY HAS BEEN PROCESSED AND WE ARE FATCHING DEBIT FROM
		 * HRMS_SAL_DEBITS TABLE
		 */

		credit_amount = getSalCredit(emp_id, year, creditLength, settleDetails,
				sal_type, month);

		/**
		 * MONTH OF SALARY HAS BEEN PROCESSED AND WE ARE FATCHING CREDIT FROM
		 * HRMS_SAL_CREDITS TABLE
		 */
		Object[][] total_amount = null;

		double totalCredit = 0;
		double totalDebit = 0;
		double netPay = 0;
		/*
		 * TOTAL NO OF COULUM THAT WILL BE IN A ROW DUE TO EXTRA COULUM OF
		 * EMP_ID , EMP_TOKEN , EMPLOYEE NAME , TOTAL CREDIT , TOTAL DEBIT AND
		 * NET PAY SO COULUM NO IS INCREASE BY 6
		 */
		int total_coulum = creditLength.length + debitLength.length + 9;

		total_amount = new Object[1][total_coulum];
		try {
			total_amount[0][0] = emp_id;
			total_amount[0][1] = emp_token;
			total_amount[0][2] = emp_name;
		} catch (Exception e) {

		}
		try {
			int k = 0;
			int c = 0;
			/*
			 * FOR LOOP IS DECREMENT BY 5 BECOZ WE ALREADY SET THE EMP_ID ,
			 * EMP_TOKEN AND EMP_NAME IN ROW AND AFTER THE LOOP COMPLETION THREE
			 * MORE FIELD HAS ADD LIKE TOTAL CREDIT , TOTAL DEBIT AND NET PAY
			 * 
			 */
			for (int j = 0; j < total_coulum - 8; j++) {

				if (j < creditLength.length) {
					/*
					 * THIS LOOP WILL RUN FOR THE NO OF CREDIT LENGTH
					 * 
					 */
					try {

						total_amount[0][j + 3] = "0";
						try {
							/*
							 * FILTER THE NULL AMOUNT IF IF CREDIT AMOUNT IS
							 * NULL IT WILL TREATED AS ZERO
							 */
							if (credit_amount[c][1] != null)
								total_amount[0][j + 3] = Utility
										.twoDecimals(String
												.valueOf((credit_amount[c][1])));
							/*
							 * ADD THE CREDIT INTO TOTAL CREDIT VARIABLE
							 * 
							 */
							totalCredit = totalCredit
									+ Double.parseDouble(String
											.valueOf(total_amount[0][j + 3]));
						} catch (Exception e) {

						}
						c++;
					} catch (Exception e) {
						System.out
								.println("Error in if  loop which is credit ");
					}
				} else if (j == creditLength.length) {
					/*
					 * THIS IS THE PALACE WHERE TOTAL CREDIT WILL TAKE PLACE
					 * AFTER ALL CREDIT
					 * 
					 */
					total_amount[0][j + 3] = Utility.twoDecimals(totalCredit);
				} else if (j > creditLength.length) {
					/*
					 * FOR INSERTING DEBIT BECOZ AFTER CREDIT DEBIT TAKE PLACE
					 * 
					 */
					total_amount[0][j + 3] = "0.00";
					try {
						if (debit_amount[k][1] == null) {
							{
								/*
								 * if debit amount is null set amount to zero
								 */
								debit_amount[k][1] = 0.00;
							}
						}

						total_amount[0][j + 3] = Utility.twoDecimals(String
								.valueOf(debit_amount[k][1]));

					} catch (Exception e) {

					}

					/*
					 * add debit amount into total debit
					 * 
					 */
					try {
						totalDebit = totalDebit
								+ Double.parseDouble(String
										.valueOf(debit_amount[k][1]));

					} catch (Exception e) {

					}
					k = k + 1;
				}
				netPay = totalCredit - totalDebit;
				total_amount[0][j + 4] = Utility.twoDecimals(String
						.valueOf(totalDebit));
				total_amount[0][j + 5] = Utility.twoDecimals(String
						.valueOf(netPay));
				if (totalDebit > totalCredit) {
					total_amount[0][j + 4] = Utility.twoDecimals(String
							.valueOf(totalCredit));
					total_amount[0][j + 5] = Utility.twoDecimals(String
							.valueOf(netPay));
				}
				total_amount[0][j + 6] = String.valueOf(sal_days);
				total_amount[0][j + 7] = String.valueOf(sal_type);

			}

		} catch (Exception e) {

		}
		return total_amount;

	}

	public Object[][] getSalCredit(String emp_id, String year,
			Object[][] creditHeader, SettlementDetails settleDetails,
			String MONTH_TYPE, String month) {
		Object[][] credit_amount = null;
		try {

			String selectCredits = "SELECT distinct HRMS_CREDIT_HEAD.CREDIT_NAME,SETTL_AMT,SETTL_CREDIT_CODE FROM HRMS_SETTL_CREDITS"
					+ " INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_SETTL_CREDITS.SETTL_CREDIT_CODE) "
					+ " WHERE SETTL_MTH_TYPE='"
					+ MONTH_TYPE
					+ "' AND SETTL_CODE="
					+ settleDetails.getSettCode()
					+ " AND HRMS_SETTL_CREDITS.SETTL_MTH="
					+ month
					+ " AND HRMS_SETTL_CREDITS.SETTL_YEAR= "
					+ year
					+ " ORDER BY SETTL_CREDIT_CODE";

			credit_amount = getSqlModel().getSingleResult(selectCredits,
					new Object[0][0]);

		} catch (Exception e) {

		}
		return credit_amount;

	}

	public Object[][] getSalDebit(String emp_id, String year,
			Object[][] debitHeader, SettlementDetails settleDetails,
			String MONTH_TYPE, String month) {
		Object[][] debit_sal_amount = null;

		try {
			// Added Periodicity

			String selectDebits = "SELECT HRMS_DEBIT_HEAD.DEBIT_NAME,SETTL_AMT,SETTL_DEBIT_CODE FROM HRMS_SETTL_DEBITS"
					+ " INNER JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_SETTL_DEBITS.SETTL_DEBIT_CODE)"
					+ " WHERE SETTL_MTH_TYPE='"
					+ MONTH_TYPE
					+ "' AND SETTL_CODE="
					+ settleDetails.getSettCode()
					+ " AND HRMS_SETTL_DEBITS.SETTL_MTH="
					+ month
					+ " AND HRMS_SETTL_DEBITS.SETTL_YEAR= "
					+ year
					+ " ORDER BY HRMS_DEBIT_HEAD.DEBIT_PRIORITY,HRMS_DEBIT_HEAD.DEBIT_CODE";

			debit_sal_amount = getSqlModel().getSingleResult(selectDebits,
					new Object[0][0]);

		} catch (Exception e) {
			debit_sal_amount = new Object[0][0];
		}

		return debit_sal_amount;

	}

	public boolean deleteSettlement(SettlementDetails settleDetails) {
		boolean creRes = false, debRes = false, loanRes = false;
		boolean dtlRes = false, leaveRes = false, result = false;
		Object[][] delObj = new Object[1][1];
		delObj[0][0] = settleDetails.getSettCode();

		try {
			String deleteCreditQuery = "DELETE FROM HRMS_SETTL_CREDITS"
					+ "  WHERE SETTL_CODE=" + delObj[0][0];
			String deleteDebitQuery = "DELETE FROM HRMS_SETTL_DEBITS"
					+ " WHERE SETTL_CODE=" + delObj[0][0];
			String deleteDetailQuery = "DELETE FROM HRMS_SETTL_DTL"
					+ " WHERE SETTL_CODE=" + delObj[0][0];
			String deleteLeaveEncash = " DELETE FROM HRMS_SETTL_LEAVEENCASH"
					+ " WHERE SETTL_CODE=" + delObj[0][0];
			String deleteLoan = "DELETE FROM HRMS_SETTL_LOAN "
					+ " WHERE SETTL_CODE=" + delObj[0][0];
			creRes = getSqlModel().singleExecute(deleteCreditQuery);
			debRes = getSqlModel().singleExecute(deleteDebitQuery);
			dtlRes = getSqlModel().singleExecute(deleteDetailQuery);
			leaveRes = getSqlModel().singleExecute(deleteLeaveEncash);
			loanRes = getSqlModel().singleExecute(deleteLoan);
			if (creRes && debRes && dtlRes && leaveRes && loanRes) {
				result = getSqlModel().singleExecute(getQuery(3), delObj);

			}
			calculateTaxThread(settleDetails);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * Locks the settlemnt of employee
	 */
	public boolean updateLock(SettlementDetails settleDetails) {
		boolean upLockObj = false;
		upLockObj = updateHeaderFinal(settleDetails);
		if (upLockObj) {
			String updateLock = "UPDATE HRMS_SETTL_HDR SET SETTL_LOCKFLAG='Y' WHERE SETTL_CODE="
					+ settleDetails.getSettCode();
			upLockObj = getSqlModel().singleExecute(updateLock);
			if (upLockObj) {
				logger.info("SETTL_PERMSETTLEMENT....."
						+ settleDetails.getPermSettle());
				updateEmpDetails(settleDetails);
			}
		}

		calculateTaxThread(settleDetails);
		return upLockObj;
	}

	/*
	 * Unlocks the settlement of employee
	 */
	public boolean updateUnLock(SettlementDetails settleDetails) {
		boolean upLockObj = false;
		String upLock = "";
		upLock = "UPDATE HRMS_SETTL_HDR SET SETTL_LOCKFLAG='N' WHERE SETTL_CODE="
				+ settleDetails.getSettCode();

		upLockObj = getSqlModel().singleExecute(upLock);
	/*	if (upLockObj) {
			String upResignQue = "UPDATE HRMS_EMP_OFFC SET EMP_STATUS ='S', EMP_LEAVE_DATE=NULL WHERE EMP_ID ="
					+ settleDetails.getEmpCode();
			getSqlModel().singleExecute(upResignQue);
		}*/
		calculateTaxThread(settleDetails);
		return upLockObj;
	}

	/**
	 * THIS METHOD IS USED FOR GETTING VALUE OF PREV MONTH
	 * 
	 * @param mon
	 * @return int
	 */
	public int getPrevMonth(int mon) {
		int m = 0;
		if (mon == 1) {
			m = 12;
		}// end of if
		else {
			m = mon - 1;
		}// end of else
		return m;
	}// end of getPrevMonth

	/**
	 * THIS METHOD IS USED FOR GETTING VALUE OF PREV YEAR
	 * 
	 * @param mon
	 * @param year
	 * @return int
	 */
	public int getPrevYear(int mon, int year) {
		int y = 0;
		if (mon == 1) {
			y = year - 1;
		} // end of if
		else {
			y = year;
		}// end of else
		return y;
	}// end of getPrevYear

	/**
	 * method to check the null value
	 * 
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	/**
	 * @param String
	 *            month
	 * @return int month
	 */
	public int getIntMonth(String month) {
		if (month.toUpperCase().equals("JANUARY"))
			return 1;
		else if (month.toUpperCase().equals("FEBRUARY"))
			return 2;
		else if (month.toUpperCase().equals("MARCH"))
			return 3;
		else if (month.toUpperCase().equals("APRIL"))
			return 4;
		else if (month.toUpperCase().equals("MAY"))
			return 5;
		else if (month.toUpperCase().equals("JUNE"))
			return 6;
		else if (month.toUpperCase().equals("JULY"))
			return 7;
		else if (month.toUpperCase().equals("AUGUST"))
			return 8;
		else if (month.toUpperCase().equals("SEPTEMBER"))
			return 9;
		else if (month.toUpperCase().equals("OCTOBER"))
			return 10;
		else if (month.toUpperCase().equals("NOVEMBER"))
			return 11;
		else if (month.toUpperCase().equals("DECEMBER"))
			return 12;
		else
			return 0;

	}

	/**
	 * @param String
	 *            month
	 * @return int month
	 */
	public String getStringMonth(int month) {
		if (month == 1)
			return "January";
		else if (month == 2)
			return "February";
		else if (month == 3)
			return "March";
		else if (month == 4)
			return "April";
		else if (month == 5)
			return "May";
		else if (month == 6)
			return "June";
		else if (month == 7)
			return "July";
		else if (month == 8)
			return "August";
		else if (month == 9)
			return "September";
		else if (month == 10)
			return "October";
		else if (month == 11)
			return "November";
		else if (month == 12)
			return "December";
		else
			return "";

	}

	public void calculateSettlementTax(SettlementDetails settleDetails) {
		/**
		 * Following code calculates the tax and updates tax process
		 */

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		String sepDate=settleDetails.getSepDate();
		int fromYear=0;
		int month=0;
		try {
			if (!sepDate.equals("")) {
				String dateArray[] = sepDate.split("-");
				fromYear = Integer.parseInt(dateArray[2]);
				month = Integer.parseInt(dateArray[1]);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (month <= 3)
			fromYear--;
		calculateTaxThread(settleDetails);
		try {
			int totalTax = 0;
			CommonTaxCalculationModel taxmodel = new CommonTaxCalculationModel();
			taxmodel.initiate(context, session);
			logger.info("I m calling tax calculation method");
			totalTax = taxmodel.totalTaxAmount(settleDetails.getEmpCode(),
					String.valueOf(fromYear), String.valueOf(fromYear + 1));
			taxmodel.terminate();
			if (totalTax < 0) {
				settleDetails.setTaxValue("0");
			} else
				settleDetails.setTaxValue("" + totalTax);
		} catch (Exception e) {
			logger
					.error("Exception in settlement  while calling calculateTax : "
							+ e);
			e.printStackTrace();
		} // end of catch

	}

	/**
	 * Loan amount calculation
	 */
	public String[][] getLoanAmount(SettlementDetails settleDetails) {
		Object[][] data = null;

		String[][] total = null;
		float loanAmt = 0.0f;
		float totalLoanAmt = 0.0f;
		try {
			String query = " SELECT A.LOAN_APPL_CODE,LOAN_SANCTION_AMOUNT ,(LOAN_SANCTION_AMOUNT-SUM(NVL(LOAN_INSTAL_AMT,0))) AS INSTALLMENT,LOAN_NAME AS LOAN_TYPE,TO_CHAR(LOAN_APPL_DATE,'DD-MM-YYYY'),LOAN_DEBIT_CODE "
					+ " FROM HRMS_LOAN_PROCESS "
					+ " LEFT JOIN HRMS_LOAN_APPLICATION A ON(A.LOAN_APPL_CODE=HRMS_LOAN_PROCESS.LOAN_APPL_CODE ) "
					+ " INNER JOIN HRMS_LOAN_INSTALMENT B ON(B.LOAN_APPL_CODE=A.LOAN_APPL_CODE AND "
					+ " LOAN_INSTALLMENT_IS_PAID='Y')"
					+ " left join HRMS_LOAN_MASTER C on(C.LOAN_CODE=A.LOAN_CODE)"
					+ " WHERE LOAN_PF_TYPE='Y' AND A.LOAN_EMP_ID="
					+ settleDetails.getEmpCode()
					+ " GROUP BY A.LOAN_APPL_CODE,LOAN_SANCTION_AMOUNT,LOAN_NAME,LOAN_APPL_DATE,LOAN_DEBIT_CODE ";
			data = getSqlModel().getSingleResult(query);
			total = new String[data.length][6];
			for (int i = 0; i < data.length; i++) {
				String prepayQuery = " select A.LOAN_APPL_CODE,SUM(NVL(LOAN_PREPAY_AMOUNT,0)) AS PREPAY from HRMS_LOAN_CLOSURE "
						+ " INNER JOIN HRMS_LOAN_APPLICATION A ON(A.LOAN_APPL_CODE=HRMS_LOAN_CLOSURE.LOAN_APPL_CODE) "
						+ " where LOAN_PF_TYPE='Y' and A.LOAN_APPL_CODE="
						+ String.valueOf(data[i][0])
						+ " and LOAN_EMP_ID="
						+ settleDetails.getEmpCode()
						+ " group by A.LOAN_APPL_CODE ";
				Object[][] prepayData = getSqlModel().getSingleResult(
						prepayQuery);

				String debitQuery = " SELECT SETTL_AMT,SETTL_MTH_TYPE FROM HRMS_SETTL_DEBITS "
						+ " WHERE SETTL_MTH_TYPE = 'CO' AND SETTL_CODE="
						+ settleDetails.getSettCode()
						+ " AND SETTL_DEBIT_CODE= "
						+ String.valueOf(data[i][5]);
				Object[][] debitObj = getSqlModel().getSingleResult(debitQuery);

				String prepayAmt = "";
				if (prepayData != null && prepayData.length > 0) {
					prepayAmt = "" + prepayData[0][1];
				} else {
					prepayAmt = "0";
				}

				String debitAmt = "";
				if (debitObj != null && debitObj.length > 0) {
					debitAmt = "" + debitObj[0][0];
				} else {
					debitAmt = "0";
				}
				// logger.info("data[i][2] :"+data[i][2]);
				// logger.info("prepayData[0][1] :"+prepayAmt);
				// logger.info("debitObj[0][0] :"+debitAmt);
				loanAmt = Float.parseFloat("" + data[i][2])
						- Float.parseFloat(prepayAmt)
						- Float.parseFloat(debitAmt);
				// logger.info("loanAmt :"+loanAmt);
				total[i][0] = String.valueOf(data[i][3]); // loan type
				total[i][1] = String.valueOf(data[i][1]);// loan sanction
				// amount
				total[i][2] = String.valueOf(new Utility().twoDecimal(loanAmt,
						2));// installment difference
				total[i][3] = String.valueOf(data[i][4]);// applied date
				total[i][4] = String.valueOf(data[i][5]);// debit code
				total[i][5] = String.valueOf(data[i][0]);// loan appl code
				// TOTAL LOAN AMOUNT
				totalLoanAmt += Float.parseFloat(total[i][2]);

			}// end of for
		} catch (Exception e) {
			logger.error("Error in getLoanAmt method  : " + e);
		}
		if (data != null && data.length > 0) {
			settleDetails.setLoanFlag("true");
			settleDetails.setTotalLoanAmt(String.valueOf(Math
					.round(totalLoanAmt)));
		}
		return total;

	}// end of getLoanAmount

	public boolean saveLoanDetails(SettlementDetails settleDetails,
			HttpServletRequest request) {
		boolean result = false;
		String[] applCode = request.getParameterValues("applCode");
		String[] sancAmt = request.getParameterValues("sancAmt");
		String[] loanAmt = request.getParameterValues("loanAmt");
		String delQuery = "DELETE FROM HRMS_SETTL_LOAN WHERE SETTL_CODE ="
				+ settleDetails.getSettCode();
		getSqlModel().singleExecute(delQuery);
		if (applCode != null) {
			for (int i = 0; i < applCode.length; i++) {
				String dtlQuery = "INSERT INTO HRMS_SETTL_LOAN (SETTL_CODE,LOAN_APPL_CODE, LOAN_SANCTION_AMOUNT, SETTL_LOAN_AMT)"
						+ " VALUES ("
						+ settleDetails.getSettCode()
						+ ","
						+ applCode[i]
						+ ","
						+ sancAmt[i]
						+ ","
						+ loanAmt[i]
						+ ")";
				result = getSqlModel().singleExecute(dtlQuery);
			}
		}

		return result;
	}

	public String[][] getSavedLoanAmount(SettlementDetails settleDetails) {
		String[][] total = null;
		Object[][] data = null;
		float totalLoanAmt = 0.0f;
		try {
			String query = " SELECT HRMS_SETTL_LOAN.LOAN_APPL_CODE, HRMS_SETTL_LOAN.LOAN_SANCTION_AMOUNT, HRMS_SETTL_LOAN.SETTL_LOAN_AMT, "
					+ " LOAN_NAME AS LOAN_TYPE, LOAN_DEBIT_CODE "
					+ " FROM HRMS_SETTL_LOAN "
					+ " LEFT JOIN HRMS_LOAN_APPLICATION A ON(A.LOAN_APPL_CODE=HRMS_SETTL_LOAN.LOAN_APPL_CODE ) "
					+ " LEFT JOIN HRMS_LOAN_MASTER C on(C.LOAN_CODE=A.LOAN_CODE) "
					+ " WHERE SETTL_CODE=" + settleDetails.getSettCode();
			data = getSqlModel().getSingleResult(query);
			total = new String[data.length][6];
			for (int i = 0; i < data.length; i++) {
				total[i][0] = String.valueOf(data[i][3]); // loan type
				total[i][1] = String.valueOf(data[i][1]);// loan sanction
				// amount
				total[i][2] = String.valueOf(data[i][2]);// loan amount
				total[i][3] = "";// applied date
				total[i][4] = String.valueOf(data[i][4]);// debit code
				total[i][5] = String.valueOf(data[i][0]);// loan appl code
				// TOTAL LOAN AMOUNT
				totalLoanAmt += Float.parseFloat(total[i][2]);
			}
		} catch (Exception e) {
			logger.error("Error in getLoanAmt method  : " + e);
		}
		if (data != null && data.length > 0) {
			settleDetails.setLoanFlag("true");
			settleDetails.setTotalLoanAmt(String.valueOf(Math
					.round(totalLoanAmt)));
		} else {
			settleDetails.setLoanFlag("false");
			settleDetails.setTotalLoanAmt("0");
		}
		return total;
	}

	public String[][] setLoanAmountOnCalculateTax(
			SettlementDetails settleDetails, HttpServletRequest request) {
		logger.info("Loan flag  : " + settleDetails.getLoanFlag());
		if (settleDetails.getLoanFlag().equals("true")) {
			String[] applCode = request.getParameterValues("applCode");
			String[] loanType = request.getParameterValues("loanType");
			String[] sancAmt = request.getParameterValues("sancAmt");
			String[] loanAmt = request.getParameterValues("loanAmt");
			String[] debitCode = request.getParameterValues("debitCode");
			if (applCode != null) {
				float totalLoanAmt = 0.0f;
				String[][] total = new String[applCode.length][6];
				for (int i = 0; i < applCode.length; i++) {
					total[i][0] = loanType[i]; // loan type
					total[i][1] = sancAmt[i];// loan sanction amount
					total[i][2] = loanAmt[i];// loan amount
					total[i][3] = "";// applied date
					total[i][4] = debitCode[i];// debit code
					total[i][5] = applCode[i];// loan appl code
					// TOTAL LOAN AMOUNT
					totalLoanAmt += Float.parseFloat(total[i][2]);
				}
				settleDetails.setTotalLoanAmt(String.valueOf(Math
						.round(totalLoanAmt)));
				return total;
			} else {
				settleDetails.setTotalLoanAmt("0");
				return new String[0][0];
			}
		} else {
			settleDetails.setTotalLoanAmt("0");
			return new String[0][0];
		}
	}

	public String getStringWithComma(String[] str, int num) {
		String strg = "";
		try {

			for (int i = 0; i < str.length; i++) {
				if (num == 0)
					strg += String.valueOf(str[i]);
				else
					strg += "'" + String.valueOf(str[i]) + "'";

				strg += ",";
			}
			strg = strg.substring(0, strg.length() - 1);

		} catch (RuntimeException e) {
			logger.error(e);
		} // end of try-catch block
		return strg;
	}

	public Vector<Object[][]> getCombinedMonthList(HttpServletRequest request) {
		Vector<Object[][]> month_Year = new Vector<Object[][]>();
		for (Iterator iterator = combinedMonthList.iterator(); iterator
				.hasNext();) {
			Vector combinedMonths = (Vector) iterator.next();
			for (Iterator iterator2 = combinedMonths.iterator(); iterator2
					.hasNext();) {
				Object[][] monthsObject = (Object[][]) iterator2.next();
				/*
				 * for (int i = 0; i < monthsObject.length; i++) { for (int j =
				 * 0; j < monthsObject[0].length; j++) { logger.info("Month year
				 * object ["+i+"]["+j+"] : "+monthsObject[i][j]); } }
				 */
				month_Year.add(monthsObject);
			}
		}
		request.setAttribute("mon_List", month_Year);
		return month_Year;
	}

	/**
	 * 
	 * @param separationDate
	 * @param policy_obj
	 * @return leaves to be entitle depends on onBeforeEntitle and afterEntitle
	 */
	public Object[][] getSettlementEntitle(String separationDate,
			String policyCode) {
		logger.info("separationDate  : " + separationDate);
		logger.info("policyCode  : " + policyCode);
		Object[][] settlementLeaveObj = null;

		String query = "SELECT  HRMS_LEAVE_POLICY_DTL.LEAVE_CODE,NVL(LEAVE_ENTITLE,0),NVL(LEAVE_MAX_ACCM_UPTO,0),LEAVE_CREDIT_INTERVAL, "
				+ " LEAVE_IS_CARRIED_FORWARD,  NVL(LEAVE_CARRY_FORWARD_MAXLIMIT,0),NVL(HRMS_LEAVE_POLICY_ENTITLE.LEAVE_POLICY_MONTH,0), "
				+ " NVL(LEAVE_SEPARATE_BEFORE_DAY,15), NVL(LEAVE_ENTITLE_SEPARATE_BEFORE,0), NVL(LEAVE_ENTITLE_SEPARATE_AFTER,0) "
				+ " FROM HRMS_LEAVE_POLICY_DTL "
				+ " INNER JOIN HRMS_LEAVE_POLICY_ENTITLE ON(HRMS_LEAVE_POLICY_ENTITLE.LEAVE_POLICY_CODE=HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE "
				+ " AND HRMS_LEAVE_POLICY_ENTITLE.LEAVE_CODE=HRMS_LEAVE_POLICY_DTL.LEAVE_CODE) "
				+ " WHERE HRMS_LEAVE_POLICY_DTL.LEAVE_POLICY_CODE="
				+ policyCode
				+ " AND LEAVE_CREDIT_TYPE='PO' AND LEAVE_APPLICABLE='Y' AND LEAVE_ENC_FLAG='Y' "
				+ " ORDER BY HRMS_LEAVE_POLICY_DTL.LEAVE_CODE,HRMS_LEAVE_POLICY_ENTITLE.LEAVE_POLICY_MONTH";
		Object[][] policy_obj = getSqlModel().getSingleResult(query);
		if (policy_obj != null && policy_obj.length > 0) {
			settlementLeaveObj = new Object[policy_obj.length / 12][2];
			int count = 0;

			int separationDay = Integer.parseInt(separationDate.substring(0, 2));
			int separationMonth = Integer.parseInt(separationDate.substring(3, 5));
			for (int i = 0; i < settlementLeaveObj.length; i++) {
				String leaveEntitle = "";
				String leaveCode = String.valueOf(policy_obj[count][0]);
				for (int j = count; j < count + 12; j++) {
					if (separationMonth == Integer.parseInt(String
							.valueOf(policy_obj[j][6]))) {
						if (separationDay <= Integer.parseInt(String
								.valueOf(policy_obj[j][7]))) {
							leaveEntitle = String.valueOf(policy_obj[j][8]);
						} else {
							leaveEntitle = String.valueOf(policy_obj[j][9]);
						}
					}
				}
				logger.info("leaveCode in method : " + leaveCode);
				logger.info("leaveEntitle in method : " + leaveEntitle);
				settlementLeaveObj[i][0] = leaveCode;
				settlementLeaveObj[i][1] = leaveEntitle;
				count += 12;

			}

		}

		return settlementLeaveObj;
	}

	public void calcGratuity(SettlementDetails settleDetails) {
		String gratuityConfQuery = "SELECT GRATUITY_APPLICABLE, NVL(GRATUITY_MIN_SERV_TENURE,0), GRATUITY_AVGSAL_CREDIT_COMP, GRATUITY_SERV_YRS_ROUNDEDTO, nvl(GRATUITY_FORMULA_FACTOR,1), GRATUITY_SAL_OPTION "
				+ " FROM HRMS_GRATUITY_CONFIG_HDR";
		Object gratuityConfObj[][] = getSqlModel().getSingleResult(
				gratuityConfQuery);

		String applEmpQuery = "SELECT HRMS_GRATUITY_CONFIG_DTL.EMP_DIVISION, HRMS_GRATUITY_CONFIG_DTL.EMP_CENTER, HRMS_GRATUITY_CONFIG_DTL.EMP_DEPT, HRMS_GRATUITY_CONFIG_DTL.EMP_RANK,HRMS_GRATUITY_CONFIG_DTL.EMP_GRADE, HRMS_GRATUITY_CONFIG_DTL.EMP_TYPE FROM HRMS_GRATUITY_CONFIG_DTL"
				+ " LEFT JOIN HRMS_EMP_OFFC ON(EMP_DIVISION=EMP_DIV) WHERE EMP_ID="
				+ settleDetails.getEmpCode()
				+ " ORDER BY "
				+ " (CASE WHEN EMP_DIVISION IS NULL THEN 1 ELSE 0 END )+ "
				+ " (CASE WHEN EMP_DEPT IS NULL THEN 1 ELSE 0 END ) +  "
				+ " (CASE WHEN EMP_CENTER  IS NULL THEN 1 ELSE 0 END )+  "
				+ " (CASE WHEN EMP_RANK IS NULL THEN 1 ELSE 0 END )+  "
				+ " (CASE WHEN EMP_TYPE IS NULL THEN 1 ELSE 0 END) DESC ";

		boolean gratuityApplicable = false;
		try {
			if (String.valueOf(gratuityConfObj[0][0]).equals("Y")) {
				Object[][] applFiltersObj = getSqlModel().getSingleResult(
						applEmpQuery);

				try {
					for (int i = 0; i < applFiltersObj.length; i++) {
						String query = " SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE 1=1 ";

						if (!(String.valueOf(applFiltersObj[i][5]).equals(""))
								&& !(String.valueOf(applFiltersObj[i][5]) == null)
								&& !String.valueOf(applFiltersObj[i][5])
										.equals("null")) {
							// if emp type not null
							query += " AND EMP_TYPE ="
									+ String.valueOf(applFiltersObj[i][5]);

						} // end if
						if (!(String.valueOf(applFiltersObj[i][2]).equals(""))
								&& !(String.valueOf(applFiltersObj[i][2]) == null)
								&& !String.valueOf(applFiltersObj[i][2])
										.equals("null")) {
							// if dept not null
							query += " AND EMP_DEPT ="
									+ String.valueOf(applFiltersObj[i][2]);
						} // end if
						if (!(String.valueOf(applFiltersObj[i][1]).equals(""))
								&& !(String.valueOf(applFiltersObj[i][1]) == null)
								&& !String.valueOf(applFiltersObj[i][1])
										.equals("null")) {
							// if branch not null
							query += " AND EMP_CENTER ="
									+ String.valueOf(applFiltersObj[i][1]);

						} // end if
						if (!(String.valueOf(applFiltersObj[i][3]).equals(""))
								&& !(String.valueOf(applFiltersObj[i][3]) == null)
								&& !String.valueOf(applFiltersObj[i][3])
										.equals("null")) {
							// if desg not null
							query += " AND EMP_RANK ="
									+ String.valueOf(applFiltersObj[i][3]);
						} // end if

						if (!(String.valueOf(applFiltersObj[i][4]).equals(""))
								&& !(String.valueOf(applFiltersObj[i][4]) == null)
								&& !String.valueOf(applFiltersObj[i][4])
										.equals("null")) {
							// if desg not null
							query += " AND EMP_CADRE ="
									+ String.valueOf(applFiltersObj[i][4]);
						} // end if

						query += " AND HRMS_EMP_OFFC.EMP_ID="
								+ settleDetails.getEmpCode();
						System.out.println("value of query---------" + query);
						Object obj[][] = getSqlModel().getSingleResult(query);
						if (obj != null && obj.length > 0) {
							gratuityApplicable = true;
							break;
						}
					}
					logger.info("gratuityApplicable===" + gratuityApplicable);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} catch (Exception e) {
			logger.error("exception in gratuity config " + e);
			gratuityApplicable = false;
		}
		double avgSal = 0;
		double serviceYears = 0;
		String serviceYearsQuery = "SELECT NVL((MONTHS_BETWEEN(TO_DATE('"
				+ settleDetails.getSepDate()
				+ "','DD-MM-YYYY')"
				+ ",NVL(HRMS_EMP_OFFC.EMP_GROUP_JOIN_DATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE))/12),0) FROM HRMS_EMP_OFFC WHERE EMP_ID="
				+ settleDetails.getEmpCode();

		try {
			Object[][] serviceYearsObj = getSqlModel().getSingleResult(
					serviceYearsQuery);
			serviceYears = Double.parseDouble(String
					.valueOf(serviceYearsObj[0][0]));
			if (String.valueOf(gratuityConfObj[0][3]).equals("N")) {
				serviceYears = Math.round(serviceYears);
			} else if (String.valueOf(gratuityConfObj[0][3]).equals("F")) {
				serviceYears = Math.floor(serviceYears);
			}
			if (String.valueOf(gratuityConfObj[0][3]).equals("C")) {
				serviceYears = Math.ceil(serviceYears);
			}
			if (String.valueOf(gratuityConfObj[0][3]).equals("A")) {
				serviceYears = Double.parseDouble(formatter
						.format(serviceYears));
			}
		} catch (Exception e) {
			logger.error("exception in service years " + e);
			serviceYears = Double
					.parseDouble(Utility.twoDecimals(serviceYears));
		}
		if (gratuityApplicable) {

			if (serviceYears >= Double.parseDouble(String
					.valueOf(gratuityConfObj[0][1]))) {

				avgSal = getAvegareSalary(gratuityConfObj, settleDetails);

				logger.info("service years===" + serviceYears);
				logger.info("min service years==="
						+ String.valueOf(gratuityConfObj[0][1]));

				logger.info("service years after round off===" + serviceYears
						+ " round off type==" + gratuityConfObj[0][3]);
			} else {
				settleDetails.setGratuity("0");

			}
		}

		settleDetails.setServiceTenure(String.valueOf(serviceYears));
		settleDetails.setGratuityAvgSalary(String.valueOf(avgSal));
		String gratuityFormula = "0";
		try {
			gratuityFormula = serviceYears + "*" + String.valueOf(avgSal) + "*"
					+ (String.valueOf(gratuityConfObj[0][4]));
		} catch (Exception e) {
			gratuityFormula = "0";
		}
		double gratuityAmt = Utility.expressionEvaluate(gratuityFormula);
		logger.info("gratuityAmt===" + gratuityAmt);
		settleDetails.setGratuity(formatter.format(gratuityAmt));
	}

	public double getAvegareSalary(Object gratuityConfObj[][],
			SettlementDetails bean) {
		double avgSal = 0.0;
		try {
			if (String.valueOf(gratuityConfObj[0][5]).equals("L")) {
				String empSalCreditQuery = "SELECT SUM(CREDIT_AMT) FROM HRMS_EMP_CREDIT WHERE CREDIT_CODE IN("
						+ String.valueOf(gratuityConfObj[0][2])
						+ ") AND EMP_ID=" + bean.getEmpCode();

				Object[][] empSalCreditObj = getSqlModel().getSingleResult(
						empSalCreditQuery);
				try {
					if (empSalCreditObj != null && empSalCreditObj.length > 0) {
						avgSal = Double.parseDouble(String
								.valueOf(empSalCreditObj[0][0]));
					}
				} catch (Exception e) {
					logger.error("exception in getAvegareSalary" + e);
				}

			} else {

				avgSal = getLastSalaryAverage(bean, String
						.valueOf(gratuityConfObj[0][2]));
			}
		} catch (Exception e) {
			logger.error("exception in getAvegareSalary" + e);
		}
		return avgSal;
	}

	public double getLastSalaryAverage(SettlementDetails bean,
			String creditCodes) {
		double avgSal = 0.0;
		int lastSalaryMonth = Integer
				.parseInt((bean.getSepDate().split("-"))[1]);
		int lastSalaryYear = Integer
				.parseInt((bean.getSepDate().split("-"))[2]);
		int totalMonth = 10;
		int month = lastSalaryMonth, year = lastSalaryYear;

		for (int i = 0; i < totalMonth; i++) {
			try {
				String salQuery = "SELECT NVL(SUM(SAL_AMOUNT),0) FROM HRMS_SAL_CREDITS_"
						+ year
						+ " WHERE SAL_CREDIT_CODE IN("
						+ creditCodes
						+ ")"
						+ " AND EMP_ID ="
						+ bean.getEmpCode()
						+ " AND SAL_LEDGER_CODE IN(SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER WHERE LEDGER_MONTH="
						+ month
						+ " AND LEDGER_YEAR= "
						+ year
						+ " AND LEDGER_DIVISION ="
						+ " (SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="
						+ bean.getEmpCode() + "))";

				Object salObj[][] = getSqlModel().getSingleResult(salQuery);
				avgSal += Double.parseDouble(String.valueOf(salObj[0][0]));
			} catch (Exception e) {
				logger.error("exception in getLastSalaryAverage" + e);
			}
			if (month == 1) {
				month = 12;
				year -= 1;
			} else {
				month -= 1;
			}
		}

		return avgSal / totalMonth;
	}

	public void calculateTaxThread(SettlementDetails settleDetails) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int fromYear = Integer.parseInt(String.valueOf(cal.get(Calendar.YEAR)));
		int month = Integer.parseInt(String.valueOf(cal.get(Calendar.MONTH)));
		if (month <= 2)
			fromYear--;
		try {
			CommonTaxCalculationModel taxmodel = new CommonTaxCalculationModel();
			taxmodel.initiate(context, session);
			logger.info("I m calling tax calculation method");
			Object empList[][] = new Object[1][1];
			empList[0][0] = settleDetails.getEmpCode();

			if (empList != null && empList.length > 0)
				taxmodel.calculateTaxThread(empList, String.valueOf(fromYear),
						String.valueOf(fromYear + 1));
			taxmodel.terminate();
		} catch (Exception e) {
			logger
					.error("Exception in settlement  while calling calculateTax : "
							+ e);
			e.printStackTrace();
		} // end of catch
	}

	public String getIncomeTaxCode(String month, String year) {
		Object[][] resultObj = null;
		String incomeTaxCode = "";
		try {
			String query = " SELECT TDS_DEBIT_CODE FROM HRMS_TAX_PARAMETER WHERE TO_CHAR(TDS_EFF_DATE,'dd-MON-yyyy') =  (select max(TDS_EFF_DATE) "
					+ " from HRMS_TAX_PARAMETER where to_char(TDS_EFF_DATE,'yyyy-mm')<='"
					+ year + "-" + month + "')";
			resultObj = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("Exception getting the incometax debit code ---------"
					+ e);
		}
		if (resultObj != null && resultObj.length > 0) {
			incomeTaxCode = String.valueOf(resultObj[0][0]);
		}
		return incomeTaxCode;
	}
	public double roundCheck(int ch,double amount){
		  try {
			 
				switch(ch){
				  case 0: 	return Double.parseDouble(Utility.twoDecimals(amount));
					  		
				  case 1: 	return Math.round(Double.parseDouble(Utility.twoDecimals(amount)));
					  		
				  case 2: 	return Math.floor(Double.parseDouble(Utility.twoDecimals(amount)));
					  		
				  case 3: 	return Math.ceil(Double.parseDouble(Utility.twoDecimals(amount)));
					  		
				  case 4: 	if(!(Math.round((amount))%10 == 0))
					  			return Double.parseDouble(Utility.twoDecimals((Math.round(amount) + (10 -(Math.round(amount)%10)))));
				  			else
				  				return Math.round(amount);
				  				
				  default : return amount;
				  }
		} catch (Exception e) {
			logger.error("Exception in getting roundCheck  ---------"+e);
			return Double.parseDouble(Utility.twoDecimals(amount));
		}
	  }

	public void fetchSettlementDetailsByEmpCode(SettlementDetails settleDetails, String status, String empCode) {
		try {
			String query = "";
			if (status.equals("P")) {
				query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME NAME, " 
					+ " NVL(RANK_NAME,' ') DESIGNATION, NVL(CENTER_NAME,' ') BRANCH, NVL(CADRE_NAME,' ') GRADE, "
					+ " NVL(TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY'),' ') DOJ, TO_CHAR(HRMS_RESIGN.RESIGN_DATE,'DD-MM-YYYY') RESIGN_DATE, " 
					+ " TO_CHAR(HRMS_RESIGN.RESIGN_SEPR_DATE,'DD-MM-YYYY') SEPAR_DATE, NVL(HRMS_RESIGN.RESIGN_NOTICE_PERIOD,0), " 
					+ " HRMS_SETTL_HDR.SETTL_CODE,NVL(HRMS_SETTL_HDR.SETTL_LOCKFLAG,' '), HRMS_RESIGN.RESIGN_CODE, HRMS_EMP_OFFC.EMP_ID "
					+ " FROM HRMS_EMP_OFFC "
					+ " LEFT JOIN  HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
					+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
					+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)"  
					+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
					+ " INNER JOIN HRMS_RESIGN ON(HRMS_EMP_OFFC.EMP_ID = HRMS_RESIGN.RESIGN_EMP)" 
					+ " LEFT JOIN HRMS_SETTL_HDR on(HRMS_RESIGN.RESIGN_CODE = HRMS_SETTL_HDR.SETTL_RESGNO)" 
					+ " WHERE HRMS_EMP_OFFC.EMP_ID ="+empCode;
			} else {
				query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME NAME, " 
					+ " NVL(RANK_NAME,' ') DESIGNATION, NVL(CENTER_NAME,' ') BRANCH, NVL(CADRE_NAME,' ') GRADE," 
					+ " NVL(TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY'),' ') DOJ, TO_CHAR(HRMS_RESIGN.RESIGN_DATE,'DD-MM-YYYY') RESIGN_DATE," 
					+ " TO_CHAR(HRMS_RESIGN.RESIGN_SEPR_DATE,'DD-MM-YYYY') SEPAR_DATE, NVL(HRMS_RESIGN.RESIGN_NOTICE_PERIOD,0), " 
					+ " HRMS_SETTL_HDR.SETTL_CODE, NVL(HRMS_SETTL_HDR.SETTL_LOCKFLAG,' '), HRMS_SETTL_HDR.SETTL_RESGNO, HRMS_EMP_OFFC.EMP_ID "
					+ " FROM HRMS_SETTL_HDR " 
					+ " LEFT JOIN HRMS_RESIGN on(HRMS_RESIGN.RESIGN_CODE = HRMS_SETTL_HDR.SETTL_RESGNO)" 
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_RESIGN.RESIGN_EMP)" 
					+ " LEFT JOIN  HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
					+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)" 
					+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)" 
					+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)" 
					+ " WHERE HRMS_RESIGN.RESIGN_EMP = "+empCode;
			}
			Object empObj[][] = getSqlModel().getSingleResult(query);
			settleDetails.setEmpToken(String.valueOf(empObj[0][0]));
			settleDetails.setEmpName(String.valueOf(empObj[0][1]));
			settleDetails.setDesgn(String.valueOf(empObj[0][2]));
			settleDetails.setBranch(String.valueOf(empObj[0][3]));
			settleDetails.setCadreName(String.valueOf(empObj[0][4]));
			settleDetails.setDateOfJoin(String.valueOf(empObj[0][5]));
			settleDetails.setResignDate(String.valueOf(empObj[0][6]));
			settleDetails.setSepDate(String.valueOf(empObj[0][7]));
			settleDetails.setNoticePeriod(String.valueOf(empObj[0][8]));
			settleDetails.setSettCode(String.valueOf(empObj[0][9]));
			settleDetails.setLockFlag(String.valueOf(empObj[0][10]));
			settleDetails.setResignCode(String.valueOf(empObj[0][11]));
			settleDetails.setEmpCode(String.valueOf(empObj[0][12]));
			settleDetails.setStatus(status);
			settleDetails.setNoticePeriodStatus("D");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}