package org.paradyne.model.TravelManagement;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.TravelManagement.TravelExpensesDisbursement;
import org.paradyne.lib.ModelBase;

public class TravelExpensesDisbursementModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TravelExpensesDisbursementModel.class);

	/*
	 * This method is used for show the status wise data.
	 */

	public void callStatus(TravelExpensesDisbursement bean, String status,
			HttpServletRequest request) {

		// check the status here
		bean.setStat(status);
		logger.info("Status---------------" + bean.getStat());

		String query = "";

		// check status here

		if (bean.getStat().equals("P")) {
			logger.info("11111111111111111--pppppppppppppppp----");
			query = " SELECT EXP_APP_ID,EMP_FNAME ||'  '||EMP_MNAME  ||' '||  EMP_LNAME,TRAVEL_APP_REQUEST, TO_CHAR(EXP_APP_APPDATE,'DD-MM-YYYY')  ,nvl(EXP_APP_ADVANCE_AMT,0),nvl(EXP_APP_EXPAMT,0),EXP_APP_TRAVEL_APPID"
					+ " FROM HRMS_TMS_EXP_APP"
					+ " LEFT JOIN HRMS_TMS_TRAVEL_APP ON(HRMS_TMS_EXP_APP.EXP_APP_TRAVEL_APPID=HRMS_TMS_TRAVEL_APP.TRAVEL_APP_ID)"
					+ " LEFT  JOIN HRMS_EMP_OFFC ON(HRMS_TMS_EXP_APP.EXP_APP_EMPID=HRMS_EMP_OFFC.EMP_ID) "
					+ "  WHERE EXP_APP_ID NOT IN(SELECT EXP_DISB_EXPAPP_ID FROM HRMS_TMS_EXP_DISB )";
		}

		else {
			logger.info("11111111111111111--QQQQQQQQQQQQQQQQQQQQQQQQQQQQ----");
			query = " SELECT EXP_APP_ID,EMP_FNAME ||'  '||EMP_MNAME  ||' '||  EMP_LNAME,TRAVEL_APP_REQUEST, TO_CHAR(EXP_APP_APPDATE,'DD-MM-YYYY')  ,nvl(EXP_APP_ADVANCE_AMT,0),nvl(EXP_APP_EXPAMT,0),EXP_APP_TRAVEL_APPID"
					+ "	 FROM HRMS_TMS_EXP_APP "
					+ "	 LEFT JOIN HRMS_TMS_TRAVEL_APP ON(HRMS_TMS_EXP_APP.EXP_APP_TRAVEL_APPID=HRMS_TMS_TRAVEL_APP.TRAVEL_APP_ID)"
					+ "	 LEFT  JOIN HRMS_EMP_OFFC ON(HRMS_TMS_EXP_APP.EXP_APP_EMPID=HRMS_EMP_OFFC.EMP_ID)"
					+ "	 left join HRMS_TMS_EXP_DISB on(HRMS_TMS_EXP_APP.EXP_APP_ID=HRMS_TMS_EXP_DISB.EXP_DISB_EXPAPP_ID)"
					+ "	 where EXP_DISB_STATUS ='" + bean.getStat() + "'";

		}

		Object[][] result = getSqlModel().getSingleResult(query);

		doPaging(bean, result.length, new Object[][] {}, request);
		
		
		int fromTotRec = Integer.parseInt(bean.getFromTotRec());
		int toTotRec = Integer.parseInt(bean.getToTotRec());

		ArrayList travelList = new ArrayList();

		for (int i = fromTotRec; i < toTotRec; i++) {
			TravelExpensesDisbursement bean1 = new TravelExpensesDisbursement();

			bean1.setTrvlExpId(checkNull(String.valueOf(result[i][0])));
			bean1.setEmpName(checkNull(String.valueOf(result[i][1])));
			bean1.setTrvlReqName(checkNull(String.valueOf(result[i][2])));
			bean1.setExpAppDate(checkNull(String.valueOf(result[i][3])));
			bean1.setExpAdvAmt(checkNull(String.valueOf(result[i][4])));
			bean1.setExpExpnsAmt(checkNull(String.valueOf(result[i][5])));
			bean1.setExpAppId(checkNull(String.valueOf(result[i][6])));

			if (status.equals("P")) {
				bean1.setPenFlag(true);
			} else if (status.equals("B")) {
				bean1.setBalFlag(true);
			} else {
				bean1.setFullFlag(true);
			}

			travelList.add(bean1);
		} // end of for loop
	logger.info("ITERATOR SIZE________________"+travelList.size());
		logger.info("no data flag========"+bean.isNoData());
		
		bean.setTravelDtlList(travelList);
		
		if(travelList.size()==0)
		 { // bean.setNoData("true"); 
			bean.setNoData(true);
			logger.info("no data flag========"+bean.isNoData());
		 }
		else
		{
			bean.setNoData(false);
		}
		
		if (status.equals("P")) {
			bean.setPen("true");
		} else if (status.equals("B")) {
			bean.setBal("true");
		} else if (status.equals("F")) {
			bean.setFull("true");
		}
	
	} // end of callStatus

	public void doPaging(TravelExpensesDisbursement bean, int empLength,
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

	public void callDtl(TravelExpensesDisbursement bean, String travelExpId) {

		// get the data from data base for that expId

		if (bean.getNavStatus().equals("P")) {
			logger.info("I am in call Deatails");
			String dataQUery = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,"
					+ "	CENTER_NAME,DEPT_NAME,RANK_NAME,TO_CHAR(EXP_APP_APPDATE,'DD-MM-YYYY') ,EXP_APP_STATUS,NVL(CADRE_NAME,' '),NVL(EXP_APP_EXPAMT,0),NVL(EXP_APP_ADVANCE_AMT,0),DECODE(EXP_APP_PREF_PAYMODE,'C','Cash','S','Salary','Q','Check','T','Transfer'),EMP_ID "
					+ "   FROM HRMS_TMS_EXP_APP"
					+ "	LEFT JOIN HRMS_EMP_OFFC ON(HRMS_TMS_EXP_APP.EXP_APP_EMPID=HRMS_EMP_OFFC.EMP_ID)"
					+ "	LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
					+ "	LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT )"
					+ "	LEFT  JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)"
					+ "	LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK )"
					+ " WHERE EXP_APP_ID=" + bean.getExpDisbId() ;

			Object[][] data = getSqlModel().getSingleResult(dataQUery);

			if (data.length > 0 && data != null) {
				bean.setEmpToken(checkNull(String.valueOf(data[0][0])));
				bean.setEmployeeName(checkNull(String.valueOf(data[0][1])));
				bean.setBrnchName(checkNull(String.valueOf(data[0][2])));
				bean.setDeptName(checkNull(String.valueOf(data[0][3])));
				bean.setDesgName(checkNull(String.valueOf(data[0][4])));
				bean.setApplDate(checkNull(String.valueOf(data[0][5])));
				bean.setStatusFld(checkNull(String.valueOf(data[0][6])));
				bean.setGrdName(checkNull(String.valueOf(data[0][7])));
				bean.setApprExpAmt(checkNull(String.valueOf(data[0][8])));
				bean.setLessAdvAmt(checkNull(String.valueOf(data[0][9])));
				bean.setPrefModPay(checkNull(String.valueOf(data[0][10])));
				bean.setEmpId(checkNull(String.valueOf(data[0][11])));
			}
		}

		else if (bean.getNavStatus().equals("B")) {

			logger.info("I am in call Deatails");
			String dataQUery = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,"
					+ "	CENTER_NAME,DEPT_NAME,RANK_NAME,TO_CHAR(EXP_APP_APPDATE,'DD-MM-YYYY') ,EXP_APP_STATUS,NVL(CADRE_NAME,' '),NVL(EXP_APP_EXPAMT,0),NVL(EXP_APP_ADVANCE_AMT,0),DECODE(EXP_APP_PREF_PAYMODE,'C','Cash','S','Salary','Q','Check','T','Transfer'),EMP_ID "
					+ "   FROM HRMS_TMS_EXP_APP"
					+ "	LEFT JOIN HRMS_EMP_OFFC ON(HRMS_TMS_EXP_APP.EXP_APP_EMPID=HRMS_EMP_OFFC.EMP_ID)"
					+ "	LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
					+ "	LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT )"
					+ "	LEFT  JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)"
					+ "	LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK )"
			        + " WHERE EXP_APP_ID=" + bean.getExpDisbId() + "";
			Object[][] data = getSqlModel().getSingleResult(dataQUery);
			if (data.length > 0 && data != null) {
				bean.setEmpToken(checkNull(String.valueOf(data[0][0])));
				bean.setEmployeeName(checkNull(String.valueOf(data[0][1])));
				bean.setBrnchName(checkNull(String.valueOf(data[0][2])));
				bean.setDeptName(checkNull(String.valueOf(data[0][3])));
				bean.setDesgName(checkNull(String.valueOf(data[0][4])));
				bean.setApplDate(checkNull(String.valueOf(data[0][5])));
				bean.setStatusFld(checkNull(String.valueOf(data[0][6])));
				bean.setGrdName(checkNull(String.valueOf(data[0][7])));
				bean.setBalApprAmt(checkNull(String.valueOf(data[0][8])));
				bean.setBalLessAdvAmt(checkNull(String.valueOf(data[0][9])));
				bean.setBalPrefModPay(checkNull(String.valueOf(data[0][10])));
				bean.setEmpId(checkNull(String.valueOf(data[0][11])));

			}

			// setting Any other deductions ,Paid amount and previous balance 

			String bQuery = "SELECT EXP_DISB_OTHER_DEDUCT,EXP_DISB_PAID_RECORD,EXP_DISB_BAL_RECORD FROM HRMS_TMS_EXP_DISB "
					+ " WHERE EXP_DISB_EXPAPP_ID=" + bean.getExpDisbId();
			Object[][] bData = getSqlModel().getSingleResult(bQuery);

			if (bData.length > 0 && bData != null) {
				logger.info("rakkkkkkkkkkkkkkk");

				bean.setBalLessAnyAmt(checkNull(String.valueOf(bData[0][0])));

				bean.setBalPiadAmt(checkNull(String.valueOf(bData[0][1])));

				bean.setBalTotDisbAmt(checkNull(String.valueOf(bData[0][2])));

			}

		} else {

			logger.info("I am in call Deatails");
			String fullQuery = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,"
					+ "	CENTER_NAME,DEPT_NAME,RANK_NAME,TO_CHAR(EXP_APP_APPDATE,'DD-MM-YYYY') ,EXP_APP_STATUS,NVL(CADRE_NAME,' '),NVL(EXP_APP_EXPAMT,0),NVL(EXP_APP_ADVANCE_AMT,0),EMP_ID "
					+ "   FROM HRMS_TMS_EXP_APP"
					+ "	LEFT JOIN HRMS_EMP_OFFC ON(HRMS_TMS_EXP_APP.EXP_APP_EMPID=HRMS_EMP_OFFC.EMP_ID)"
					+ "	LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
					+ "	LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT )"
					+ "	LEFT  JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)"
					+ "	LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK )"
					+ " WHERE EXP_APP_ID=" + bean.getExpDisbId() + "";

			Object[][] fullData = getSqlModel().getSingleResult(fullQuery);

			if (fullData.length > 0 && fullData != null) {
				bean.setEmpToken(checkNull(String.valueOf(fullData[0][0])));
				bean.setEmployeeName(checkNull(String.valueOf(fullData[0][1])));
				bean.setBrnchName(checkNull(String.valueOf(fullData[0][2])));
				bean.setDeptName(checkNull(String.valueOf(fullData[0][3])));
				bean.setDesgName(checkNull(String.valueOf(fullData[0][4])));
				bean.setApplDate(checkNull(String.valueOf(fullData[0][5])));
				bean.setStatusFld(checkNull(String.valueOf(fullData[0][6])));
				bean.setGrdName(checkNull(String.valueOf(fullData[0][7])));

				bean
						.setFullAppExpAmt(checkNull(String
								.valueOf(fullData[0][8])));

				bean.setFullAdvAmt(checkNull(String.valueOf(fullData[0][9])));

				/*bean
						.setFullAnyDedAmt(checkNull(String
								.valueOf(fullData[0][10])));
				 */
				bean.setEmpId(checkNull(String.valueOf(fullData[0][10])));

			}

			// setting Any other deductions 
			String bQuery = "SELECT EXP_DISB_OTHER_DEDUCT FROM HRMS_TMS_EXP_DISB "
					+ " WHERE EXP_DISB_EXPAPP_ID=" + bean.getExpDisbId();
			Object[][] bData = getSqlModel().getSingleResult(bQuery);

			if (bData.length > 0 && bData != null) {
				logger.info("rakkkkkkkkkkkkkkk");

				bean.setFullAnyDedAmt(checkNull(String.valueOf(bData[0][0])));

			}

			//get  the data from HRMS_TMS_EXP_DISB_BAL table to show iterator

			String iteQuery = " SELECT NVL(DISB_BAL_PAID_BAL,0),TO_CHAR(DISB_BAL_PAYMENT_DATE,'DD-MM-YYYY'),DECODE(DISB_BAL_PAYMODE,'C','Cash','S','Salary','Q','Check','T','Transfer') FROM HRMS_TMS_EXP_DISB_BAL"

					+ "	WHERE DISB_BAL_EXP_DISB_ID="+bean.getExpDisbId() +"ORDER BY DISB_BAL_PAYMENT_DATE ";

			Object[][] result1 = getSqlModel().getSingleResult(iteQuery);

			ArrayList list = new ArrayList();

			for (int i = 0; i < result1.length; i++) {
				TravelExpensesDisbursement bean1 = new TravelExpensesDisbursement();
				bean1.setFullPaidAmt(String.valueOf(result1[i][0]));
				bean1.setFullPaidDate(String.valueOf(result1[i][1]));
				bean1.setFullPaidMode(String.valueOf(result1[i][2]));
				list.add(bean1);
			}
			bean.setTrvlList(list);
			
			logger.info("SIZE==========="+list.size());

		}

	}

	public boolean saveExpnsDtls(TravelExpensesDisbursement bean) {
		Object addObj[][] = new Object[1][19];

		// addObj[0][0] = checkNull(bean.getExpDisbId().trim());
		addObj[0][0] = checkNull(bean.getEmpId().trim());
		addObj[0][1] = checkNull(bean.getExpAppId().trim());
		addObj[0][2] = checkNull(bean.getApprExpAmt().trim());
		addObj[0][3] = checkNull(bean.getLessAdvAmt().trim());
		addObj[0][4] = checkNull(bean.getLessAnyAmt().trim());
		addObj[0][5] = checkNull(bean.getEntDisbAmt().trim());
		addObj[0][6] = checkNull(bean.getBalAmt().trim());
		addObj[0][7] = checkNull(bean.getPayDate().trim());
		addObj[0][8] = checkNull(bean.getModPayment().trim());
		addObj[0][9] = checkNull(bean.getBank().trim());
		addObj[0][10] = checkNull(bean.getCheckNo().trim());
		addObj[0][11] = checkNull(bean.getCheckDate().trim());
		addObj[0][12] = checkNull(bean.getExpMon().trim());
		addObj[0][13] = checkNull(bean.getExpYr().trim());
		addObj[0][14] = checkNull(bean.getComment().trim());

		if (Integer.parseInt(bean.getBalAmt()) == 0) {
			addObj[0][15] = "F";
		} else {
			addObj[0][15] = "B";
		}

		addObj[0][16] = checkNull(bean.getExpDisbId().trim());
		addObj[0][17] = checkNull(bean.getEntDisbAmt().trim());
		addObj[0][18] = checkNull(bean.getBalAmt().trim());

	
		
		
		//insert one record in bal also

		if (getSqlModel().singleExecute(getQuery(1), addObj)) {

			//get disbursId from table

			String disbQuery = "SELECT MAX(EXP_DISB_ID) FROM HRMS_TMS_EXP_DISB";
			Object[][] disbId = getSqlModel().getSingleResult(disbQuery);
			String disId = "";
			if (disbId.length > 0 && disbId != null) {
				disId = String.valueOf(disbId[0][0]);
			}

			Object balObj[][] = new Object[1][12];

			balObj[0][0] = checkNull(bean.getBalAmt().trim());//total balance
			balObj[0][1] = checkNull(bean.getEntDisbAmt().trim());//paid amount
			balObj[0][2] = checkNull(bean.getBalAmt().trim());//pending amount
			balObj[0][3] = checkNull(bean.getPayDate().trim());
			balObj[0][4] = checkNull(bean.getModPayment().trim());
			balObj[0][5] = checkNull(bean.getCheckNo().trim());
			balObj[0][6] = checkNull(bean.getCheckDate().trim());
			balObj[0][7] = checkNull(bean.getBank().trim());
			balObj[0][8] = checkNull(bean.getExpMon().trim());
			balObj[0][9] = checkNull(bean.getExpYr().trim());
			balObj[0][10] = checkNull(bean.getComment().trim());
			balObj[0][11] = disId;
			
			
			
			return getSqlModel().singleExecute(getQuery(6), balObj);

		} else {
			return false;
		}

	}

	
	
	public boolean saveBalDtls(TravelExpensesDisbursement bean) {
		logger.info("In saveBalDtls------------------------");

		//insert into balance table

		Object addObj[][] = new Object[1][11];
		addObj[0][0] = checkNull(bean.getBalEntDisbAmt().trim());
		addObj[0][1] = checkNull(bean.getBalanceAmt().trim());
		addObj[0][2] = checkNull(bean.getBalPayDate().trim());
		addObj[0][3] = checkNull(bean.getBalModPayment().trim());
		addObj[0][4] = checkNull(bean.getBalBank().trim());
		addObj[0][5] = checkNull(bean.getBalCheckNo().trim());
		addObj[0][6] = checkNull(bean.getBalCheckDate().trim());
		addObj[0][7] = checkNull(bean.getBalMon().trim());
		addObj[0][8] = checkNull(bean.getBalYr().trim());
		addObj[0][9] = checkNull(bean.getBalComment().trim());
		addObj[0][10] = checkNull(bean.getExpDisbId().trim());
		
		

		if (getSqlModel().singleExecute(getQuery(2), addObj)) {
			// insert HRMS_TMS_EXP_DISB after saving the record
			//update the disburse table

			logger.info("updatind bisb table================");
			Object addBalObj[][] = new Object[1][4];
			addBalObj[0][0] = checkNull(bean.getBalEntDisbAmt().trim());
			addBalObj[0][1] = checkNull(bean.getBalanceAmt().trim());
			if (Integer.parseInt(bean.getBalanceAmt()) == 0) {
				addBalObj[0][2] = "F";
			} else {
				addBalObj[0][2] = "B";
			}
			addBalObj[0][3] = checkNull(bean.getExpDisbId().trim());

			return getSqlModel().singleExecute(getQuery(5), addBalObj);

		} else {
			return false;
		}

	}

}
