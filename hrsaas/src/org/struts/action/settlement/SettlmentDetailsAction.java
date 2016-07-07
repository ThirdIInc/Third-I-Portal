package org.struts.action.settlement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import org.paradyne.bean.settlement.SettlementDetails;
import org.paradyne.lib.Utility;
import org.paradyne.model.settlement.SettlementDetailsModel;
import org.paradyne.model.settlement.SettlementDetailsReportModel;
import org.struts.lib.ParaActionSupport;

/**
 * 
 * @author Reeba Joseph Date: 02-July-2009
 * 
 */

public class SettlmentDetailsAction extends ParaActionSupport {

	SettlementDetails settleDetails;
	String poolDir = "";
	String fileName = "";

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(SettlmentDetailsAction.class);

	public void prepare_local() throws Exception {
		settleDetails = new SettlementDetails();
		settleDetails.setMenuCode(370);
	}

	public SettlementDetails getSettleDetails() {
		return settleDetails;
	}

	public void setSettleDetails(SettlementDetails settleDetails) {
		this.settleDetails = settleDetails;
	}

	public Object getModel() {
		return settleDetails;
	}

	@Override
	public String input() throws Exception {
		// getNavigationPanel(1);
		SettlementDetailsModel model = new SettlementDetailsModel();
		model.initiate(context, session);
		model.callSettlementList(settleDetails, "P", request,getprofileQuery(settleDetails));
		model.terminate();
		return SUCCESS;
	}

	public String callSettlementList() throws Exception {
		String status = "";
		String stat = "";
		try {
			status = request.getParameter("status");
			status = String.valueOf(status.charAt(0));
		}// end of try
		catch (Exception e) {
			logger.error("Exception in callSettlementList-------" + e);
		}// end of catch
		if (status == null || status.equals("")) {
			status = "P";
		}// end of if
		else if (status.equals("")) {
			status = "P";
		}// end of else if

		if (status.equals("P")) {
			settleDetails.setPending("true");
			stat = "Pending Settlement";
			getNavigationPanel(9);
		}// end of if
		else if (status.equals("I")) {
			settleDetails.setInProcess("true");
			stat = "In Process Settlement";
			getNavigationPanel(9);
		}// end of else if
		else if (status.equals("F")) {
			settleDetails.setFinalized("true");
			stat = "Finalized Settlement";
			getNavigationPanel(9);
		}// end of else if

		/*
		 * if (!(status.equals("P"))) { settleDetails.setApprflag("true"); }//
		 * end of if
		 */request.setAttribute("stat", stat);
		SettlementDetailsModel model = new SettlementDetailsModel();
		model.initiate(context, session);
		model.callSettlementList(settleDetails, status, request,getprofileQuery(settleDetails));
		model.terminate();
		return SUCCESS;
	}

	public String processEditView(){
		try {
			SettlementDetailsModel model = new SettlementDetailsModel();
			model.initiate(context, session);
			String status = "", empCode = "";
			status = request.getParameter("status");
			empCode = request.getParameter("empCode");
			
			// SETTING EMPLOYEE DETAILS LIKE DESIG, BRANCH, ..... NOTICE PERIOD
			model.fetchSettlementDetailsByEmpCode(settleDetails, status, empCode);
			
			if (status.equals("P")) {
				logger.info("############ In status P");
				settleDetails.setFinalFlag("false");
				settleDetails.setReCalFlag("false");
				getNavigationPanel(1);
				
				// GETTING PAY BY EMPLOYEE/COMPANY MONTHS.
				ArrayList<Object> list = model.getPayByFields(settleDetails, settleDetails.getResignDate(), settleDetails.getSepDate(), settleDetails.getNoticePeriod(), empCode);
				logger.info("List size........." + list.size());
				int listSize = list.size();
				settleDetails.setPayListCode(String.valueOf(listSize));
				// GETTING ONHOLD/PAID MONTHS. PASS PAY BY LIST
				int size = model.getOnholdMonths(settleDetails, list, settleDetails.getResignDate(), settleDetails.getSepDate(), settleDetails.getNoticePeriod(), empCode);
				logger.info("size......." + size);
				// SETTING PAY BY & ONHOLD LIST SIZE
				if (size > 0 && size != listSize) {
					if (size > listSize) {
						size = size - listSize;
					} else {
						size = listSize - size;
					}
					settleDetails.setOnholdFlag(true);
					settleDetails.setListCode(String.valueOf(size));
				} else {
					settleDetails.setPayListCode(String.valueOf(listSize));
					settleDetails.setOnholdFlag(false);
				}
			} else if (status.equals("I")) {
				logger.info(" ############## In status I");
				settleDetails.setFinalFlag("false");
				settleDetails.setReCalFlag("true");
				Object[][] settleData = model.retrieveSettleData(settleDetails, request);
				model.retrieveSettleDetails(settleDetails, request);
				String[][] note = new String[1][2];
				logger.info("separ date       :"+ String.valueOf(settleData[0][6]));
				logger.info("separ date settle       :"	+ String.valueOf(settleData[0][36]));
				if (!(String.valueOf(settleData[0][6]).trim().equals(String.valueOf(settleData[0][36]).trim()))) {
					note[0][0] = getMessage("seperation.date") + " has been changed under Resignation Details. "
							+ " \nPlease recalculate "	+ getMessage("settelment.amount");
					request.setAttribute("note", note[0][0]);
				} else {
					note[0][0] = "";
				}

				logger.info("notice :"+ String.valueOf(settleData[0][29]));
				logger.info("notice settle :"+ String.valueOf(settleData[0][37]));
				if (!(String.valueOf(settleData[0][29]).trim().equals(String.valueOf(settleData[0][37]).trim()))) {
					note[0][1] = getMessage("period") + " has been changed under Resignation Details. "
							+ " \nPlease recalculate "	+ getMessage("settelment.amount");
					request.setAttribute("note1", note[0][1]);
				} else {
					note[0][1] = "";
				}
				getNavigationPanel(7);
			} else {
				logger.info(" ############ In status F");
				settleDetails.setFinalFlag("true");
				settleDetails.setReCalFlag("false");
				// retrieving details table
				model.retrieveSettleDetails(settleDetails, request);
				settleDetails.setEnableAll("N");
				getNavigationPanel(5);
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "detailView";
	}
	public String processEditViewOld() throws Exception {
		
		// settleDetails.setAfterNextFlag(true);
		SettlementDetailsModel model = new SettlementDetailsModel();
		model.initiate(context, session);
		String status = "", resgDate = "", separDate = "", noticePeriod = "", empCode = "", resignCode = "", noticeStatus = "";
		
		try {
			status = request.getParameter("status");
			// status = String.valueOf(status.charAt(0));
			logger.info("status.........." + status);
			resgDate = request.getParameter("resignDate");
			separDate = request.getParameter("separDate");
			noticePeriod = request.getParameter("notice");
			empCode = request.getParameter("empCode");
			resignCode = request.getParameter("resignCode");
			noticeStatus = "D";
			// request.getParameter("noticeStatus");
			logger.info("noticeStatus.........." + noticeStatus);
		}// end of try
		catch (Exception e) {
			logger.error("Exception in callSettlementList-------" + e);
			e.printStackTrace();
		}// end of catch
		
		// SETTING EMPLOYEE DETAILS
		if(separDate.equals("") || separDate.equals(null)){
			separDate =resgDate;
		}
		settleDetails.setResignDate(resgDate);
		settleDetails.setSepDate(separDate);
		settleDetails.setNoticePeriod(noticePeriod);
		settleDetails.setResignCode(resignCode);
		settleDetails.setStatus(status);
		settleDetails.setNoticePeriodStatus(noticeStatus);
		logger.info("noticeStatus.........."+ settleDetails.getNoticePeriodStatus());
		String empQuery = "";
		if (status.equals("P")) {
			
			empQuery = " SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME NAME, HRMS_EMP_OFFC.EMP_ID,"
				+ " NVL(CENTER_NAME,' '),NVL(RANK_NAME,' '),HRMS_SETTL_HDR.SETTL_CODE,SETTL_LOCKFLAG,NVL(CADRE_NAME,''),  "
				+ " NVL(TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY'),' ') FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN  HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
				+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) "  //Anantha lakshmi
				+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
				+ " INNER JOIN HRMS_RESIGN ON(HRMS_EMP_OFFC.EMP_ID = HRMS_RESIGN.RESIGN_EMP) "
				+ " LEFT JOIN HRMS_SETTL_HDR on(HRMS_RESIGN.RESIGN_CODE = HRMS_SETTL_HDR.SETTL_RESGNO) "
				+ " WHERE HRMS_EMP_OFFC.EMP_ID = " + empCode;
		} else {
			
			empQuery = " SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME "
				+ " NAME, HRMS_EMP_OFFC.EMP_ID, NVL(CENTER_NAME,' '),NVL(RANK_NAME,' '), HRMS_SETTL_HDR.SETTL_CODE, SETTL_LOCKFLAG,NVL(CADRE_NAME,' '), "
				+ " NVL(TO_CHAR(HRMS_EMP_OFFC.EMP_REGULAR_DATE,'DD-MM-YYYY'),' ') FROM HRMS_SETTL_HDR  "
				+ " LEFT JOIN HRMS_RESIGN on(HRMS_RESIGN.RESIGN_CODE = HRMS_SETTL_HDR.SETTL_RESGNO) "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_RESIGN.RESIGN_EMP) "
				+ " LEFT JOIN  HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
				+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
				+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) "  //Anantha lakshmi
				+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
				+ " WHERE HRMS_RESIGN.RESIGN_EMP = " + empCode;
		}
		Object empObj[][] = model.getSqlModel().getSingleResult(empQuery);
		
		settleDetails.setEmpCode(String.valueOf(empObj[0][2]));
		settleDetails.setEmpName(String.valueOf(empObj[0][1]));
		settleDetails.setEmpToken(String.valueOf(empObj[0][0]));
		settleDetails.setBranch(String.valueOf(empObj[0][3]));
		settleDetails.setDesgn(String.valueOf(empObj[0][4]));
		settleDetails.setSettCode(String.valueOf(empObj[0][5]));
		if (String.valueOf(empObj[0][6]).equals("Y"))
			settleDetails.setLockFlag("Y");
		else if (String.valueOf(empObj[0][6]).equals("N"))
			settleDetails.setLockFlag("N");
		else
			settleDetails.setLockFlag("");
		
		settleDetails.setCadreName(checkNull(String.valueOf(empObj[0][7])));//Anantha lakshmi
		settleDetails.setDateOfJoin(String.valueOf(empObj[0][8]));
		if (status.equals("P")) {
			logger.info("In status P");
			settleDetails.setFinalFlag("false");
			settleDetails.setReCalFlag("false");
			getNavigationPanel(1);
			// GETTING PAY BY EMPLOYEE/COMPANY MONTHS.
			ArrayList<Object> list = model.getPayByFields(settleDetails,
					resgDate, separDate, noticePeriod, empCode);
			
			logger.info("List size........." + list.size());
			int listSize = list.size();
			settleDetails.setPayListCode(String.valueOf(listSize));
			// GETTING ONHOLD/PAID MONTHS. PASS PAY BY LIST
			int size = model.getOnholdMonths(settleDetails, list, resgDate,
					separDate, noticePeriod, empCode);
			logger.info("size......." + size);
			// SETTING PAY BY & ONHOLD LIST SIZE
			if (size > 0 && size != listSize) {
				if (size > listSize) {
					size = size - listSize;
					// logger.info("size1......." + size);
				} else {
					size = listSize - size;
					// logger.info("size1......." + size);
				}
				settleDetails.setOnholdFlag(true);
				settleDetails.setListCode(String.valueOf(size));
			} else {
				settleDetails.setPayListCode(String.valueOf(listSize));
				settleDetails.setOnholdFlag(false);
			}
		} else if (status.equals("I")) {
			logger.info("In status I");
			settleDetails.setFinalFlag("false");
			settleDetails.setReCalFlag("true");
			getNavigationPanel(7);
			Object[][] settleData = model.retrieveSettleData(settleDetails,
					request);
			
			model.retrieveSettleDetails(settleDetails, request);
			
			String[][] note = new String[1][2];
			logger
			.info("separ date       :"
					+ String.valueOf(settleData[0][6]));
			logger.info("separ date settle       :"
					+ String.valueOf(settleData[0][36]));
			if (!(String.valueOf(settleData[0][6]).trim().equals(String
					.valueOf(settleData[0][36]).trim()))) {
				note[0][0] = getMessage("seperation.date")
				+ " has been changed under Resignation Details. "
				+ " \nPlease recalculate "
				+ getMessage("settelment.amount");
				request.setAttribute("note", note[0][0]);
			} else
				note[0][0] = "";
			
			logger.info("notice       :" + String.valueOf(settleData[0][29]));
			logger.info("notice settle       :"
					+ String.valueOf(settleData[0][37]));
			if (!(String.valueOf(settleData[0][29]).trim().equals(String
					.valueOf(settleData[0][37]).trim()))) {
				note[0][1] = getMessage("period")
				+ " has been changed under Resignation Details. "
				+ " \nPlease recalculate "
				+ getMessage("settelment.amount");
				request.setAttribute("note1", note[0][1]);
			} else
				note[0][1] = "";
			
		} else {
			logger.info("In status F");
			settleDetails.setFinalFlag("true");
			settleDetails.setReCalFlag("false");
			getNavigationPanel(5);
			// retrieving details table
			model.retrieveSettleDetails(settleDetails, request);
			settleDetails.setEnableAll("N");
		}
		
		model.terminate();
		return "detailView";
	}

	public String saveAndNextFirst() throws Exception {
		// SAVE DETAILS & DISPLAY SALARY TABLES
		getNavigationPanel(2);
		boolean result = false;
		SettlementDetailsModel model = new SettlementDetailsModel();
		model.initiate(context, session);
		// SAVES HEADER IF NOT LOCKED
		if (!settleDetails.getLockFlag().equals("Y")) {
			if (settleDetails.getSettCode() == null
					|| settleDetails.getSettCode().equals("null")
					|| settleDetails.getSettCode().equals("")) {
				// SAVE SETTLEMENT HEADER ON SAVE & NEXT BUTTON
				result = model.saveHeader(settleDetails);
				String query = " SELECT MAX(SETTL_CODE) FROM HRMS_SETTL_HDR ";
				Object code[][] = model.getSqlModel().getSingleResult(query);
				settleDetails.setSettCode(String.valueOf(code[0][0]));
				if (result) {
					result = model.saveSettleDtl(settleDetails, request);
					if (result) {
						addActionMessage(getMessage("save"));
						// DISPLAY SALARY TABLES
						calculate();
					} else
						addActionMessage(getMessage("save.error"));
				}
			} else {
				// UPDATE SETTLEMENT HEADER ON SAVE & NEXT BUTTON
				result = model.updateHeader(settleDetails);
				if (result) {
					result = model.updateSettleDtl(settleDetails, request);
					if (result) {
						addActionMessage(getMessage("update"));
						// DISPLAY SALARY TABLES
						calculate();
					} else
						addActionMessage(getMessage("update.error"));
				}
			}
		}

		model.terminate();
		return "settlementFirst";
	}

	public String saveAndNextSecond() throws Exception {
		try {
			// SAVE SALARY TABLES
			SettlementDetailsModel model = new SettlementDetailsModel();
			model.initiate(context, session);
			// SETTING EMPLOYEE DETAILS LIKE DESIG, BRANCH, ..... NOTICE PERIOD
			model.fetchSettlementDetailsByEmpCode(settleDetails, "I", settleDetails.getEmpCode());
			
			logger.info("settlement code...before...."+ settleDetails.getSettCode());
			logger.info("settlement detail code...before...."+ settleDetails.getSettDtlCode());
			try {
				// SETTING CODE IF DATA PRESENT IN DTL TABLE/ CREDITS TABLE
				String dtlCodeQuery = " SELECT DISTINCT HRMS_SETTL_DTL.SETTL_CODE FROM HRMS_SETTL_DTL  "
						+ " INNER JOIN HRMS_SETTL_CREDITS ON (HRMS_SETTL_CREDITS.SETTL_CODE = HRMS_SETTL_DTL.SETTL_CODE ) "
						+ " WHERE HRMS_SETTL_DTL.SETTL_CODE = "
						+ settleDetails.getSettCode();
				Object dtlCode[][] = model.getSqlModel().getSingleResult(dtlCodeQuery);
				logger.info("settlement detail code length......"+ dtlCode.length);
				if (dtlCode != null && dtlCode.length > 0) {
					settleDetails.setSettDtlCode(String.valueOf(dtlCode[0][0]));
					logger.info("in if...");
				} else {
					settleDetails.setSettDtlCode("");
				}
			} catch (Exception e) {
				settleDetails.setSettDtlCode("");
				e.printStackTrace();
			}
			logger.info("settlement detail code......"+ settleDetails.getSettDtlCode());
			Object debitHead[][] = model.getDebitHeader();
			Object creditHead[][] = model.getCreditHeader();
			Object[] cc = request.getParameterValues("creditC");
			Object[] ca = request.getParameterValues("creditA");
			Object[] dc = request.getParameterValues("debitC");
			Object[] da = request.getParameterValues("debitA");
			Object[] mm = request.getParameterValues("month");
			Object[] yy = request.getParameterValues("year");
			Object[] onholdType = request.getParameterValues("onholdType");
			Object[] mmshort = request.getParameterValues("mm");
			Object[] yyshort = request.getParameterValues("yy");
			Object[] monthType = request.getParameterValues("monthType");
			Object[][] shortRows = null;
			if (mmshort != null && mmshort.length > 0) {
				shortRows = new Object[mmshort.length][debitHead.length	+ creditHead.length];
				for (int i = 0; i < mmshort.length; i++) {
					/**
					 * FOR GETTING CREDIT AND DEBIT FROM JSP
					 */
					shortRows[i] = request.getParameterValues(String.valueOf(i + 1));
				}// end j for loop
			}
			boolean result = false;
			String updateSalAmt = " UPDATE HRMS_SETTL_HDR SET SETTL_NETSAL_AMT = "+ settleDetails.getSalaryAmt()
					+ " WHERE SETTL_CODE = "+ settleDetails.getSettCode();
			model.getSqlModel().singleExecute(updateSalAmt);
			if (settleDetails.getSettDtlCode() == null || settleDetails.getSettDtlCode().equals("null") || (settleDetails.getSettDtlCode().equals(""))) {
				try {
					// INSERT ONHOLD CREDITS & DEBITS
					logger.info("In saving onhold salary details");
					result = model.saveSalOnhold(settleDetails, cc, ca, dc, da, mm, yy, onholdType);
				} catch (Exception e) {
					logger.error("Error in saving onhold salary details  : "
							+ e);
					e.printStackTrace();
				}
				try {
					// INSERT SHORT NOTICE CREDITS & DEBITS
					if (shortRows != null && shortRows.length > 0) {
						logger.info("In saving short salary details");
						result = model.saveSalShort(settleDetails, debitHead, creditHead, shortRows, mmshort, yyshort, monthType);
					}
				} catch (Exception e) {
					logger.error("Error in saving short salary details  : "+ e);
					e.printStackTrace();
				}
				addActionMessage(getMessage("save"));
			} else {
				model.delCreDbData(settleDetails);
				// UPDATE ONHOLD CREDITS & DEBITS
				String query = " SELECT SETTL_TYPE FROM HRMS_SETTL_DTL WHERE SETTL_CODE="+ settleDetails.getSettCode()
						+ " AND (SETTL_TYPE='OH' OR SETTL_TYPE = 'PD') ";
				Object code[][] = model.getSqlModel().getSingleResult(query);
				if (code != null && code.length > 0){
					settleDetails.setOnholdFlag(true);
				}
				if (settleDetails.isOnholdFlag() == true) {
					// UPDATES DATA OF ONHOLD MONTHS
					model.updateSalOnhold(settleDetails, cc, ca, dc, da, mm, yy, onholdType);
				}
				try {
					if (shortRows!= null && shortRows.length > 0) {
						// UPDATES DATA OF SHORT NOTICE PERIOD DAYS
						model.updateSalShort(settleDetails, debitHead, creditHead, shortRows, mmshort, yyshort, monthType);
					}
				} catch (Exception e) {
					logger.error("Error in updating short salary  :" + e);
					e.printStackTrace();
				}
				addActionMessage(getMessage("update"));
			}
			// SETS SETTL AMT,GRATUITY,REIMBURSEMENT, DEDUCTION AMOUNTS
			String amtQuery = " SELECT NVL(SETTL_NETAMT,0), NVL(SETTL_GRATUITY,0), NVL(SETTL_REIMBURSE,0), NVL(SETTL_DEDUCTION,0), NVL(SETTL_NETSAL_AMT,0), NVL(SETTL_TAX_AMT,0), "
					+ " NVL(SETTL_SERVICE_TENURE,0), NVL(SETTL_GRATUITY_AVG_SALARY,0),NVL(SETTL_REIMBURSE_COMMENT,' '), NVL(SETTL_DEDUCTION_COMMENT,' ') FROM HRMS_SETTL_HDR WHERE SETTL_CODE = "
					+ settleDetails.getSettCode();
			Object amtObj[][] = model.getSqlModel().getSingleResult(amtQuery);
			if (amtObj != null && amtObj.length > 0) {
				settleDetails.setSettleAmt(String.valueOf(amtObj[0][0]));
				settleDetails.setGratuity(String.valueOf(amtObj[0][1]));
				settleDetails.setReimburse(String.valueOf(amtObj[0][2]));
				settleDetails.setDeduct(String.valueOf(amtObj[0][3]));
				settleDetails.setSalaryAmt(String.valueOf(amtObj[0][4]));
				settleDetails.setTaxValue(String.valueOf(amtObj[0][5]));
				settleDetails.setServiceTenure(String.valueOf(amtObj[0][6]));
				settleDetails.setGratuityAvgSalary(String.valueOf(amtObj[0][7]));
				settleDetails.setReimburseComments(String.valueOf(amtObj[0][8]));
				settleDetails.setDeductComments(String.valueOf(amtObj[0][9]));
			} else {
				settleDetails.setSettleAmt("0");
				settleDetails.setGratuity("0");
				settleDetails.setReimburse("0");
				settleDetails.setDeduct("0");
				settleDetails.setSalaryAmt("0");
				settleDetails.setTaxValue("0");
				settleDetails.setServiceTenure("0");
				settleDetails.setGratuityAvgSalary("0");
				settleDetails.setReimburseComments("");
				settleDetails.setDeductComments("");
			}
			// SETS LEAVE RECORDS
			String query = "SELECT HRMS_SETTL_LEAVEENCASH.SETTL_CODE FROM HRMS_SETTL_LEAVEENCASH "
					+ " LEFT JOIN HRMS_SETTL_HDR ON (HRMS_SETTL_HDR.SETTL_CODE = HRMS_SETTL_LEAVEENCASH.SETTL_CODE) "
					+ " WHERE HRMS_SETTL_LEAVEENCASH.SETTL_CODE="+ settleDetails.getSettCode();
			Object[][] queryObj = model.getSqlModel().getSingleResult(query);
			double netEncashAmt = 0.0d;
			try {
				String[][] values = null;
				if (queryObj != null && queryObj.length > 0) {
					// SETS SAVED LEAVE RECORDS
					values = model.getLeaveRecordSearch(settleDetails);
				} else {
					values = model.getLeaveRecord(settleDetails);
				}
				if (values != null) {
					request.setAttribute("values", values);
					int length = values.length;
					settleDetails.setLeaveLength(String.valueOf(length));
					netEncashAmt = Double.parseDouble(settleDetails.getTotalAmt());
					logger.info("netEncashAmt......." + netEncashAmt);
				}
			} catch (Exception e) {
				logger.error("Error in setting leave encashment table  : " + e);
				netEncashAmt = 0.0;
				settleDetails.setEncashAmt("0");
				settleDetails.setTotalLeaveDays("0");
				e.printStackTrace();
			}
			// SETTING LOAN DETAILS
			double netLoanAmt = 0.00;
			try {
				String loanQuery = " SELECT DISTINCT HRMS_SETTL_LOAN.SETTL_CODE FROM HRMS_SETTL_LOAN "
						+ " LEFT JOIN HRMS_SETTL_HDR ON (HRMS_SETTL_HDR.SETTL_CODE = HRMS_SETTL_LOAN.SETTL_CODE) "
						+ " WHERE HRMS_SETTL_LOAN.SETTL_CODE="+ settleDetails.getSettCode();
				Object[][] loanObj = model.getSqlModel().getSingleResult(loanQuery);

				String[][] loanValues = null;
				if (loanObj != null && loanObj.length > 0) {
					// SETS SAVED LEAVE RECORDS
					loanValues = model.getSavedLoanAmount(settleDetails);
				} else {
					loanValues = model.getLoanAmount(settleDetails);
				}
				if (loanValues != null && loanValues.length > 0) {
					logger.info("in if.......");
					request.setAttribute("loanValues", loanValues);
					netLoanAmt = Double.parseDouble(settleDetails.getTotalLoanAmt());
					logger.info("netLoanAmt......." + netLoanAmt);
				}
			} catch (Exception e) {
				logger.error("Error in setting loan table  : " + e);
				netLoanAmt = 0.00;
				settleDetails.setLoanAmtCh("0");
				settleDetails.setTotalLoanAmt("0");
				//e.printStackTrace();
			}
			double salaryAmt = Double.parseDouble(settleDetails.getSalaryAmt());
			double settleAmt = 0.0d;
			double gratuity = Double.parseDouble(settleDetails.getGratuity());
			double reimburse = Double.parseDouble(settleDetails.getReimburse());
			double deduct = Double.parseDouble(settleDetails.getDeduct());
			double taxAmount = Double.parseDouble(settleDetails.getTaxValue());
			settleAmt = (netEncashAmt + salaryAmt + gratuity + reimburse)- (deduct + taxAmount + netLoanAmt);
			logger.info("netEncashAmt + salaryAmt + gratuity + reimburse      :"+ (netEncashAmt + salaryAmt + gratuity + reimburse)
							+ "\ndeduct + taxAmount + netLoanAmt :"	+ (deduct + taxAmount + netLoanAmt)
							+ "\nSettlement Amount      : " + settleAmt);
			settleDetails.setSettleAmt(String.valueOf(Math.round(settleAmt)));
			settleDetails.setSettlementFlag(true);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(3);
		return "settlementSecond";
	}

	public String saveAndNextThird() throws Exception {
		// SAVES LEAVE TABLE< GRATUITY ETC. & DISPLAY HEADER DETAILS
		if (String.valueOf(settleDetails.getStatus()).equals("P")) {
			getNavigationPanel(8);
		} else if (String.valueOf(settleDetails.getStatus()).equals("I")) {
			getNavigationPanel(4);
		}

		SettlementDetailsModel model = new SettlementDetailsModel();
		model.initiate(context, session);

		// SAVE LEAVE & GRATUITY ETC. RECORDS
		String netAmt = settleDetails.getSettleAmt();
		Object[] leaveId = request.getParameterValues("leaveId");
		Object[] clBal = request.getParameterValues("clBal");
		Object[] leaveTotal = request.getParameterValues("total");
		// boolean result =
		boolean result = model.updateSettle(settleDetails, netAmt, leaveId,
				clBal, leaveTotal);
		if (result) {
			result = model.saveLoanDetails(settleDetails, request);
			// DISPLAY RECORDS OF LAST PAGE IF SAVED
			addActionMessage(getMessage("save"));
			model.retrieveSettleData(settleDetails, request);
		} else {
			addActionMessage(getMessage("save.error"));
		}
		return "settlementThird";
	}

	public String previousFirst() throws Exception {
		// DISPLAY LIST
		callSettlementList();
		return SUCCESS;
	}

	public String previousSecond() throws Exception {
		// DISPLAY DETAIL TABLE
		SettlementDetailsModel model = new SettlementDetailsModel();
		model.initiate(context, session);
		model.retrieveSettleDetails(settleDetails, request);
		model.terminate();
		String status = settleDetails.getStatus();
		if (status.equals("F")) {
			getNavigationPanel(5);
			settleDetails.setEnableAll("N");
		} else {
			getNavigationPanel(7);
		}
		return "detailView";
	}

	public String previousThird() throws Exception {
		// DISPLAY SALARY TABLES
		SettlementDetailsModel model = new SettlementDetailsModel();
		model.initiate(context, session);
		// SETS SALARY RECORD OF SHORT NOTICE DAYS
		ArrayList<Object[][]> rowsEligible = model.savedShortSalary(
				settleDetails, request);
		for (Iterator<Object[][]> iterator = rowsEligible.iterator(); iterator
				.hasNext();) {
			Object[][] name = (Object[][]) iterator.next();
			int j = 0;
			for (int i = 0; i < name.length; i++) {
				for (j = 0; j < name[0].length; j++) {
					// logger.info("saved
					// name["+i+"]"+"["+j+"]......."+name[i][j]);
				}
			}
		}

		// SETS SALARY RECORD OF ONHOLD MONTHS
		try {
			Vector<Object[][]> onholdRows = model.viewOnholdSalary(
					settleDetails, request);
			if (onholdRows != null) {
				request.setAttribute("onholdRows", onholdRows);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// SETS TOTAL SALARY AMT, TOTAL ENCASH AMT
		String amtQuery = " SELECT NVL(SETTL_NETSAL_AMT,0),NVL(SETTL_LEAVE_ENCASH,0) "
				+ " FROM HRMS_SETTL_HDR WHERE SETTL_CODE = "
				+ settleDetails.getSettCode();
		Object amtObj[][] = model.getSqlModel().getSingleResult(amtQuery);
		settleDetails.setSalaryAmt(String.valueOf(amtObj[0][0]));
		settleDetails.setTotalAmt(Utility.twoDecimals(String
				.valueOf(amtObj[0][1])));
		model.terminate();
		String status = settleDetails.getStatus();
		if (status.equals("F")) {
			getNavigationPanel(5);
			settleDetails.setEnableAll("N");
		} else {
			getNavigationPanel(2);
		}
		return "settlementFirst";
	}

	public String previousFourth() throws Exception {
		try {
			// DISPLAY LEAVE TABLE
			SettlementDetailsModel model = new SettlementDetailsModel();
			model.initiate(context, session);
			model.fetchSettlementDetailsByEmpCode(settleDetails, settleDetails.getStatus(), settleDetails.getEmpCode());
			// SETS LEAVE AND GRATUITY DETAILS
			// SETS SETTL AMT,GRATUITY,REIMBURSEMENT, DEDUCTION AMOUNTS
			String amtQuery = " SELECT NVL(SETTL_NETAMT,0), NVL(SETTL_GRATUITY,0), NVL(SETTL_REIMBURSE,0), NVL(SETTL_DEDUCTION,0), "
					+ "NVL(SETTL_NETSAL_AMT,0),NVL(SETTL_LEAVE_ENCASH,0), NVL(SETTL_TAX_AMT,0), "
					+ " NVL(SETTL_SERVICE_TENURE,0), NVL(SETTL_GRATUITY_AVG_SALARY,0),NVL(SETTL_REIMBURSE_COMMENT,' '), NVL(SETTL_DEDUCTION_COMMENT,' ') FROM HRMS_SETTL_HDR WHERE SETTL_CODE = "
					+ settleDetails.getSettCode();
			Object amtObj[][] = model.getSqlModel().getSingleResult(amtQuery);
			if (amtObj != null && amtObj.length > 0) {
				settleDetails.setSettleAmt(String.valueOf(amtObj[0][0]));
				settleDetails.setGratuity(String.valueOf(amtObj[0][1]));
				settleDetails.setReimburse(String.valueOf(amtObj[0][2]));
				settleDetails.setDeduct(String.valueOf(amtObj[0][3]));
				settleDetails.setSalaryAmt(String.valueOf(amtObj[0][4]));
				settleDetails.setTotalAmt(Utility.twoDecimals(String
						.valueOf(amtObj[0][5])));
				settleDetails.setTaxValue(String.valueOf(amtObj[0][6]));
				settleDetails.setServiceTenure(String.valueOf(amtObj[0][7]));
				settleDetails
						.setGratuityAvgSalary(String.valueOf(amtObj[0][8]));
				settleDetails
						.setReimburseComments(String.valueOf(amtObj[0][9]));
				settleDetails.setDeductComments(String.valueOf(amtObj[0][10]));
			} else {
				settleDetails.setGratuity("0");
				settleDetails.setReimburse("0");
				settleDetails.setDeduct("0");
				settleDetails.setTaxValue("0");
				settleDetails.setSalaryAmt("0");
				settleDetails.setTotalAmt("0");
				settleDetails.setServiceTenure("0");
				settleDetails.setGratuityAvgSalary("0");
				settleDetails.setReimburseComments("");
				settleDetails.setDeductComments("");
			}
			// SETS SAVED LEAVE RECORDS
			String[][] values = model.getLeaveRecordSearch(settleDetails);
			if (values != null) {
				request.setAttribute("values", values);
				int length = values.length;
				// logger.info("length....."+length);
				settleDetails.setLeaveLength(String.valueOf(length));
			} else {
				settleDetails.setTotalAmt("0");
			}
			// SETS SAVED LOAN RECORDS
			String[][] loanValues = model.getSavedLoanAmount(settleDetails);
			if (values != null) {
				request.setAttribute("loanValues", loanValues);
			} else {
				settleDetails.setTotalLoanAmt("0");
			}
			String status = settleDetails.getStatus();
			if (status.equals("F")) {
				getNavigationPanel(5);
				settleDetails.setEnableAll("N");
			} else {
				getNavigationPanel(3);
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Enable all in previous fourth : "+ settleDetails.getEnableAll());
		settleDetails.setSettlementFlag(true);
		return "settlementSecond";
	}

	public String saveFirst() throws Exception {
		// SAVE DETAILS
		boolean result = false;
		getNavigationPanel(7);
		SettlementDetailsModel model = new SettlementDetailsModel();
		model.initiate(context, session);
		// SAVES HEADER IF NOT LOCKED
		if (!settleDetails.getLockFlag().equals("Y")) {
			if (settleDetails.getSettCode() == null
					|| settleDetails.getSettCode().equals("null")
					|| settleDetails.getSettCode().equals("")) {
				// SAVE SETTLEMENT HEADER ON SAVE & NEXT BUTTON
				result = model.saveHeader(settleDetails);
				String query = " SELECT MAX(SETTL_CODE) FROM HRMS_SETTL_HDR ";
				Object code[][] = model.getSqlModel().getSingleResult(query);
				settleDetails.setSettCode(String.valueOf(code[0][0]));
				if (result) {
					result = model.saveSettleDtl(settleDetails, request);
					if (result) {
						addActionMessage(getMessage("save"));
					} else
						addActionMessage(getMessage("save.error"));
				}
			} else {
				// UPDATE SETTLEMENT HEADER ON SAVE & NEXT BUTTON
				result = model.updateHeader(settleDetails);
				if (result) {
					result = model.updateSettleDtl(settleDetails, request);
					if (result) {
						addActionMessage(getMessage("update"));
					} else
						addActionMessage(getMessage("update.error"));
				}
			}
		}
		model.retrieveSettleDetails(settleDetails, request);
		model.terminate();
		return "detailView";
	}

	public String saveSecond() throws Exception {
		// SAVE SALARY DETAILS
		getNavigationPanel(2);
		SettlementDetailsModel model = new SettlementDetailsModel();
		model.initiate(context, session);

		logger.info("settlement code...before...."
				+ settleDetails.getSettCode());
		logger.info("settlement detail code...before...."
				+ settleDetails.getSettDtlCode());
		try {
			// SETTING CODE IF DATA PRESENT IN DTL TABLE/ CREDITS TABLE
			String dtlCodeQuery = " SELECT DISTINCT HRMS_SETTL_DTL.SETTL_CODE FROM HRMS_SETTL_DTL  "
					+ " INNER JOIN HRMS_SETTL_CREDITS ON (HRMS_SETTL_CREDITS.SETTL_CODE = HRMS_SETTL_DTL.SETTL_CODE ) "
					+ " WHERE HRMS_SETTL_DTL.SETTL_CODE = "
					+ settleDetails.getSettCode();
			Object dtlCode[][] = model.getSqlModel().getSingleResult(
					dtlCodeQuery);
			logger.info("settlement detail code length......" + dtlCode.length);
			if (dtlCode != null && dtlCode.length > 0) {
				settleDetails.setSettDtlCode(String.valueOf(dtlCode[0][0]));
				logger.info("in if...");
			} else {
				settleDetails.setSettDtlCode("");
			}
		} catch (Exception e) {
			settleDetails.setSettDtlCode("");
			e.printStackTrace();
		}
		logger.info("settlement detail code......"
				+ settleDetails.getSettDtlCode());

		Object debitHead[][] = model.getDebitHeader();
		Object creditHead[][] = model.getCreditHeader();
		Object[] cc = request.getParameterValues("creditC");
		Object[] ca = request.getParameterValues("creditA");
		Object[] dc = request.getParameterValues("debitC");
		Object[] da = request.getParameterValues("debitA");
		Object[] mm = request.getParameterValues("month");
		Object[] yy = request.getParameterValues("year");
		Object[] onholdType = request.getParameterValues("onholdType");
		Object[] mmshort = request.getParameterValues("mm");
		Object[] yyshort = request.getParameterValues("yy");
		Object[] monthType = request.getParameterValues("monthType");
		Object[][] shortRows = null;
		if (mmshort != null && mmshort.length > 0) {
			shortRows = new Object[mmshort.length][debitHead.length
					+ creditHead.length];
			for (int i = 0; i < mmshort.length; i++) {
				/**
				 * FOR GETTING CREDIT AND DEBIT FROM JSP
				 */
				shortRows[i] = request
						.getParameterValues(String.valueOf(i + 1));
			}// end j for loop
		}

		String updateSalAmt = " UPDATE HRMS_SETTL_HDR SET SETTL_NETSAL_AMT = "
				+ settleDetails.getSalaryAmt() + " WHERE SETTL_CODE = "
				+ settleDetails.getSettCode();
		model.getSqlModel().singleExecute(updateSalAmt);

		boolean result = false;
		if (settleDetails.getSettDtlCode() == null
				|| settleDetails.getSettDtlCode().equals("null")
				|| (settleDetails.getSettDtlCode().equals(""))) {
			try {
				// INSERT ONHOLD CREDITS & DEBITS
				// if (settleDetails.isOnholdFlag() == true) {
				logger.info("In saving onhold salary details");
				result = model.saveSalOnhold(settleDetails, cc, ca, dc, da, mm,
						yy, onholdType);
				// }
			} catch (Exception e) {
				logger.error("Error in saving onhold salary details  : " + e);
				e.printStackTrace();
			}
			try {
				// INSERT SHORT NOTICE CREDITS & DEBITS
				if (shortRows[0] != null && shortRows[0].length > 0) {
					logger.info("In saving short salary details");
					result = model.saveSalShort(settleDetails, debitHead,
							creditHead, shortRows, mmshort, yyshort, monthType);
				}
			} catch (Exception e) {
				logger.error("Error in saving short salary details  : " + e);
				e.printStackTrace();
			}
			addActionMessage(getMessage("save"));
		} else {
			model.delCreDbData(settleDetails);
			// UPDATE ONHOLD CREDITS & DEBITS
			String query = " SELECT SETTL_TYPE FROM HRMS_SETTL_DTL WHERE SETTL_CODE="
					+ settleDetails.getSettCode()
					+ " AND (SETTL_TYPE='OH' OR SETTL_TYPE = 'PD') ";
			Object code[][] = model.getSqlModel().getSingleResult(query);
			if (code != null && code.length > 0)
				settleDetails.setOnholdFlag(true);
			logger.info("onhold flag....." + settleDetails.isOnholdFlag());
			if (settleDetails.isOnholdFlag() == true) {
				// UPDATES DATA OF ONHOLD MONTHS
				model.updateSalOnhold(settleDetails, cc, ca, dc, da, mm, yy,
						onholdType);

			}
			try {
				if (shortRows[0] != null && shortRows[0].length > 0) {
					// UPDATES DATA OF SHORT NOTICE PERIOD DAYS
					model.updateSalShort(settleDetails, debitHead, creditHead,
							shortRows, mmshort, yyshort, monthType);
				}
			} catch (Exception e) {
				logger.error("Error in updating short salary  :" + e);
				e.printStackTrace();
			}
			addActionMessage(getMessage("update"));
		}
		// DISPLAY SALARY TABLES AFTER SAVE
		// SETS SALARY RECORD OF SHORT NOTICE DAYS
		ArrayList<Object[][]> rowsEligible = model.savedShortSalary(
				settleDetails, request);
		for (Iterator<Object[][]> iterator = rowsEligible.iterator(); iterator
				.hasNext();) {
			Object[][] name = (Object[][]) iterator.next();
			int j = 0;
			for (int i = 0; i < name.length; i++) {
				for (j = 0; j < name[0].length; j++) {
					// logger.info("saved
					// name["+i+"]"+"["+j+"]......."+name[i][j]);
				}
			}
		}

		// SETS SALARY RECORD OF ONHOLD MONTHS
		try {
			Vector<Object[][]> onholdRows = model.viewOnholdSalary(
					settleDetails, request);
			if (onholdRows != null) {
				request.setAttribute("onholdRows", onholdRows);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "settlementFirst";
	}

	public String saveThird() throws Exception {
		getNavigationPanel(3);
		// SAVE LEAVE DETAILS AND GRATUITY ETC.
		SettlementDetailsModel model = new SettlementDetailsModel();
		model.initiate(context, session);
		// SAVE LEAVE & GRATUITY ETC. RECORDS
		String netAmt = settleDetails.getSettleAmt();
		Object[] leaveId = request.getParameterValues("leaveId");
		Object[] clBal = request.getParameterValues("clBal");
		Object[] leaveTotal = request.getParameterValues("total");
		boolean result = model.updateSettle(settleDetails, netAmt, leaveId,
				clBal, leaveTotal);
		if (result) {
			String updateSalAmt = " UPDATE HRMS_SETTL_HDR SET SETTL_NETSAL_AMT = "
					+ settleDetails.getSalaryAmt()
					+ ",SETTL_TAX_AMT="
					+ settleDetails.getTaxValue()
					+ " WHERE SETTL_CODE = "
					+ settleDetails.getSettCode();
			result = model.getSqlModel().singleExecute(updateSalAmt);
			if (result) {
				result = model.saveLoanDetails(settleDetails, request);
				addActionMessage(getMessage("save"));
			} else {
				addActionMessage(getMessage("save.error"));
			}
		}

		// SETS SETTL AMT,GRATUITY,REIMBURSEMENT, DEDUCTION AMOUNTS
		String amtQuery = " SELECT NVL(SETTL_NETAMT,0), NVL(SETTL_GRATUITY,0), NVL(SETTL_REIMBURSE,0), "
				+ "NVL(SETTL_DEDUCTION,0), NVL(SETTL_NETSAL_AMT,0), NVL(SETTL_TAX_AMT,0),  "
				+ " NVL(SETTL_SERVICE_TENURE,0), NVL(SETTL_GRATUITY_AVG_SALARY,0) FROM HRMS_SETTL_HDR WHERE SETTL_CODE = "
				+ settleDetails.getSettCode();
		Object amtObj[][] = model.getSqlModel().getSingleResult(amtQuery);
		if (amtObj != null && amtObj.length > 0) {
			settleDetails.setSettleAmt(String.valueOf(amtObj[0][0]));
			settleDetails.setGratuity(String.valueOf(amtObj[0][1]));
			settleDetails.setReimburse(String.valueOf(amtObj[0][2]));
			settleDetails.setDeduct(String.valueOf(amtObj[0][3]));
			settleDetails.setSalaryAmt(String.valueOf(amtObj[0][4]));
			settleDetails.setTaxValue(String.valueOf(amtObj[0][5]));
			settleDetails.setServiceTenure(String.valueOf(amtObj[0][6]));
			settleDetails.setGratuityAvgSalary(String.valueOf(amtObj[0][7]));
		} else {
			settleDetails.setGratuity("0");
			settleDetails.setReimburse("0");
			settleDetails.setDeduct("0");
			settleDetails.setTaxValue("0");
			settleDetails.setSalaryAmt("0");
			settleDetails.setServiceTenure("0");
			settleDetails.setGratuityAvgSalary("0");
		}

		// SETS SAVED LEAVE RECORDS
		String[][] values = model.getLeaveRecordSearch(settleDetails);
		if (values != null) {
			request.setAttribute("values", values);
			int length = values.length;
			// logger.info("length....."+length);
			settleDetails.setLeaveLength(String.valueOf(length));
		} else {
			settleDetails.setTotalAmt("0");
		}

		// SETS SAVED LOAN RECORDS
		String[][] loanValues = model.getSavedLoanAmount(settleDetails);
		if (loanValues != null) {
			request.setAttribute("loanValues", loanValues);
		} else {
			settleDetails.setTotalLoanAmt("0");
		}
		model.terminate();
		return "settlementSecond";
	}

	public String saveFourth() throws Exception {
		// SAVE HEADER
		getNavigationPanel(4);
		SettlementDetailsModel model = new SettlementDetailsModel();
		model.initiate(context, session);
		model.updateHeaderFinal(settleDetails);
		addActionMessage(getMessage("save"));
		model.terminate();
		return "settlementThird";
	}

	public String saveAndPreviousFirst() throws Exception {
		// SAVES DETAILS AND DISPLAY LIST
		logger.info("######### IN SAVE PREVIOUS FIRST ############");
		SettlementDetailsModel model = new SettlementDetailsModel();
		model.initiate(context, session);
		boolean result = false;
		// SAVES HEADER IF NOT LOCKED
		if (!settleDetails.getLockFlag().equals("Y")) {
			if (settleDetails.getSettCode() == null
					|| settleDetails.getSettCode().equals("null")
					|| settleDetails.getSettCode().equals("")) {
				// SAVE SETTLEMENT HEADER ON SAVE & NEXT BUTTON
				result = model.saveHeader(settleDetails);
				String query = " SELECT MAX(SETTL_CODE) FROM HRMS_SETTL_HDR ";
				Object code[][] = model.getSqlModel().getSingleResult(query);
				
				settleDetails.setSettCode(String.valueOf(code[0][0]));
				if (result) {
					result = model.saveSettleDtl(settleDetails, request);
					if (result) {
						addActionMessage(getMessage("save"));
					} else
						addActionMessage(getMessage("save.error"));
				}
			} else {
				// UPDATE SETTLEMENT HEADER ON SAVE & NEXT BUTTON
				result = model.updateHeader(settleDetails);
				if (result) {
					result = model.updateSettleDtl(settleDetails, request);
					if (result) {
						addActionMessage(getMessage("update"));
					} else
						addActionMessage(getMessage("update.error"));
				}
			}
		}
		callSettlementList();
		model.terminate();
		return SUCCESS;
	}

	public String saveAndPreviousSecond() throws Exception {
		// SAVES SALARY TABLES AND DISPLAY DETAILS
		getNavigationPanel(1);
		SettlementDetailsModel model = new SettlementDetailsModel();
		model.initiate(context, session);

		logger.info("settlement code...before...."
				+ settleDetails.getSettCode());
		logger.info("settlement detail code...before...."
				+ settleDetails.getSettDtlCode());
		try {
			// SETTING CODE IF DATA PRESENT IN DTL TABLE/ CREDITS TABLE
			String dtlCodeQuery = " SELECT DISTINCT HRMS_SETTL_DTL.SETTL_CODE FROM HRMS_SETTL_DTL  "
					+ " INNER JOIN HRMS_SETTL_CREDITS ON (HRMS_SETTL_CREDITS.SETTL_CODE = HRMS_SETTL_DTL.SETTL_CODE ) "
					+ " WHERE HRMS_SETTL_DTL.SETTL_CODE = "
					+ settleDetails.getSettCode();
			Object dtlCode[][] = model.getSqlModel().getSingleResult(
					dtlCodeQuery);
			logger.info("settlement detail code length......" + dtlCode.length);
			if (dtlCode != null && dtlCode.length > 0) {
				settleDetails.setSettDtlCode(String.valueOf(dtlCode[0][0]));
				logger.info("in if...");
			} else {
				settleDetails.setSettDtlCode("");
			}
		} catch (Exception e) {
			settleDetails.setSettDtlCode("");
			e.printStackTrace();
		}
		logger.info("settlement detail code......"
				+ settleDetails.getSettDtlCode());

		Object debitHead[][] = model.getDebitHeader();
		Object creditHead[][] = model.getCreditHeader();
		Object[] cc = request.getParameterValues("creditC");
		Object[] ca = request.getParameterValues("creditA");
		Object[] dc = request.getParameterValues("debitC");
		Object[] da = request.getParameterValues("debitA");
		Object[] mm = request.getParameterValues("month");
		Object[] yy = request.getParameterValues("year");
		Object[] onholdType = request.getParameterValues("onholdType");
		Object[] mmshort = request.getParameterValues("mm");
		Object[] yyshort = request.getParameterValues("yy");
		Object[] monthType = request.getParameterValues("monthType");
		Object[][] shortRows = null;
		if (mmshort != null && mmshort.length > 0) {
			shortRows = new Object[mmshort.length][debitHead.length
					+ creditHead.length];
			for (int i = 0; i < mmshort.length; i++) {
				/**
				 * FOR GETTING CREDIT AND DEBIT FROM JSP
				 */
				shortRows[i] = request
						.getParameterValues(String.valueOf(i + 1));
			}// end j for loop
		}

		String updateSalAmt = " UPDATE HRMS_SETTL_HDR SET SETTL_NETSAL_AMT = "
				+ settleDetails.getSalaryAmt() + " WHERE SETTL_CODE = "
				+ settleDetails.getSettCode();
		model.getSqlModel().singleExecute(updateSalAmt);

		boolean result = false;
		if (settleDetails.getSettDtlCode() == null
				|| settleDetails.getSettDtlCode().equals("null")
				|| (settleDetails.getSettDtlCode().equals(""))) {
			try {
				// INSERT ONHOLD CREDITS & DEBITS
				// if (settleDetails.isOnholdFlag() == true) {
				logger.info("In saving onhold salary details");
				result = model.saveSalOnhold(settleDetails, cc, ca, dc, da, mm,
						yy, onholdType);
				// }
			} catch (Exception e) {
				logger.error("Error in saving onhold salary details  : " + e);
				e.printStackTrace();
			}
			try {
				// INSERT SHORT NOTICE CREDITS & DEBITS
				if (shortRows[0] != null && shortRows[0].length > 0) {
					logger.info("In saving short salary details");
					result = model.saveSalShort(settleDetails, debitHead,
							creditHead, shortRows, mmshort, yyshort, monthType);
				}
			} catch (Exception e) {
				logger.error("Error in saving short salary details  : " + e);
				e.printStackTrace();
			}
			addActionMessage(getMessage("save"));
		} else {
			model.delCreDbData(settleDetails);
			// UPDATE ONHOLD CREDITS & DEBITS
			String query = " SELECT SETTL_TYPE FROM HRMS_SETTL_DTL WHERE SETTL_CODE="
					+ settleDetails.getSettCode()
					+ " AND (SETTL_TYPE='OH' OR SETTL_TYPE = 'PD') ";
			Object code[][] = model.getSqlModel().getSingleResult(query);
			if (code != null && code.length > 0)
				settleDetails.setOnholdFlag(true);
			logger.info("onhold flag....." + settleDetails.isOnholdFlag());
			if (settleDetails.isOnholdFlag() == true) {
				// UPDATES DATA OF ONHOLD MONTHS
				model.updateSalOnhold(settleDetails, cc, ca, dc, da, mm, yy,
						onholdType);

			}
			try {
				if (shortRows[0] != null && shortRows[0].length > 0) {
					// UPDATES DATA OF SHORT NOTICE PERIOD DAYS
					model.updateSalShort(settleDetails, debitHead, creditHead,
							shortRows, mmshort, yyshort, monthType);
				}
			} catch (Exception e) {
				logger.error("Error in updating short salary  :" + e);
				e.printStackTrace();
			}
			addActionMessage(getMessage("update"));
		}
		// SETS DETAILS TABLE
		model.retrieveSettleDetails(settleDetails, request);

		model.terminate();
		return "detailView";
	}

	public String saveAndPreviousThird() throws Exception {
		// SAVES LEAVE DETAILS AND DISPLAY SALARY TABLES
		getNavigationPanel(2);
		// SAVE LEAVE DETAILS AND GRATUITY ETC.
		SettlementDetailsModel model = new SettlementDetailsModel();
		model.initiate(context, session);
		// SAVE LEAVE & GRATUITY ETC. RECORDS
		String netAmt = settleDetails.getSettleAmt();
		Object[] leaveId = request.getParameterValues("leaveId");
		Object[] clBal = request.getParameterValues("clBal");
		Object[] leaveTotal = request.getParameterValues("total");
		// boolean result =
		boolean result = model.updateSettle(settleDetails, netAmt, leaveId,
				clBal, leaveTotal);
		if (result) {
			String updateSalAmt = " UPDATE HRMS_SETTL_HDR SET SETTL_NETSAL_AMT = "
					+ settleDetails.getSalaryAmt()
					+ ",SETTL_TAX_AMT="
					+ settleDetails.getTaxValue()
					+ " WHERE SETTL_CODE = "
					+ settleDetails.getSettCode();
			result = model.getSqlModel().singleExecute(updateSalAmt);
			if (result) {
				addActionMessage(getMessage("save"));
				result = model.saveLoanDetails(settleDetails, request);
			} else {
				addActionMessage(getMessage("save.error"));
			}
		}

		// DISPLAY SALARY TABLES AFTER SAVE
		// SETS SALARY RECORD OF SHORT NOTICE DAYS
		ArrayList<Object[][]> rowsEligible = model.savedShortSalary(
				settleDetails, request);
		for (Iterator<Object[][]> iterator = rowsEligible.iterator(); iterator
				.hasNext();) {
			Object[][] name = (Object[][]) iterator.next();
			int j = 0;
			for (int i = 0; i < name.length; i++) {
				for (j = 0; j < name[0].length; j++) {
					// logger.info("saved
					// name["+i+"]"+"["+j+"]......."+name[i][j]);
				}
			}
		}

		// SETS SALARY RECORD OF ONHOLD MONTHS
		try {
			Vector<Object[][]> onholdRows = model.viewOnholdSalary(settleDetails, request);
			if (onholdRows != null) {
				request.setAttribute("onholdRows", onholdRows);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
		return "settlementFirst";
	}

	public String saveAndPreviousFourth() throws Exception {
		// SAVES HEADER AND DISPLAY LEAVE TABLE,GRATUITY ETC.
		getNavigationPanel(3);
		SettlementDetailsModel model = new SettlementDetailsModel();
		model.initiate(context, session);
		// SAVES HEADER
		model.updateHeaderFinal(settleDetails);
		addActionMessage(getMessage("save"));

		// SETS SETTL AMT,GRATUITY,REIMBURSEMENT, DEDUCTION AMOUNTS
		String amtQuery = " SELECT NVL(SETTL_NETAMT,0), NVL(SETTL_GRATUITY,0), NVL(SETTL_REIMBURSE,0), "
				+ "NVL(SETTL_DEDUCTION,0), NVL(SETTL_NETSAL_AMT,0), NVL(SETTL_TAX_AMT,0),  "
				+ " NVL(SETTL_SERVICE_TENURE,0), NVL(SETTL_GRATUITY_AVG_SALARY,0),NVL(SETTL_REIMBURSE_COMMENT,' '), NVL(SETTL_DEDUCTION_COMMENT,' ') FROM HRMS_SETTL_HDR WHERE SETTL_CODE = "
				+ settleDetails.getSettCode();
		Object amtObj[][] = model.getSqlModel().getSingleResult(amtQuery);
		if (amtObj != null && amtObj.length > 0) {
			settleDetails.setSettleAmt(String.valueOf(amtObj[0][0]));
			settleDetails.setGratuity(String.valueOf(amtObj[0][1]));
			settleDetails.setReimburse(String.valueOf(amtObj[0][2]));
			settleDetails.setDeduct(String.valueOf(amtObj[0][3]));
			settleDetails.setSalaryAmt(String.valueOf(amtObj[0][4]));
			settleDetails.setTaxValue(String.valueOf(amtObj[0][5]));
			settleDetails.setServiceTenure(String.valueOf(amtObj[0][6]));
			settleDetails.setGratuityAvgSalary(String.valueOf(amtObj[0][7]));
			settleDetails.setReimburseComments(String.valueOf(amtObj[0][8]));
			settleDetails.setDeductComments(String.valueOf(amtObj[0][9]));
		} else {
			settleDetails.setGratuity("0");
			settleDetails.setReimburse("0");
			settleDetails.setDeduct("0");
			settleDetails.setTaxValue("0");
			settleDetails.setSalaryAmt("0");
			settleDetails.setServiceTenure("0");
			settleDetails.setGratuityAvgSalary("0");
			settleDetails.setReimburseComments("");
			settleDetails.setDeductComments("");
		}
		// SETS SAVED LEAVE RECORDS
		String[][] values = model.getLeaveRecordSearch(settleDetails);
		if (values != null) {
			request.setAttribute("values", values);
			int length = values.length;
			// logger.info("length....."+length);
			settleDetails.setLeaveLength(String.valueOf(length));
		} else {
			settleDetails.setTotalAmt("0");
		}

		// SETS SAVED LOAN RECORDS
		String[][] loanValues = model.getSavedLoanAmount(settleDetails);
		if (values != null) {
			request.setAttribute("loanValues", loanValues);
		} else {
			settleDetails.setTotalLoanAmt("0");
		}
		model.terminate();
		return "settlementSecond";
	}

	public String nextFirst() throws Exception {
		// DISPLAYS SALARY TABLES
		// SETS SALARY RECORD OF SHORT NOTICE DAYS
		getNavigationPanel(5);
		SettlementDetailsModel model = new SettlementDetailsModel();
		model.initiate(context, session);
		ArrayList<Object[][]> rowsEligible = model.savedShortSalary(
				settleDetails, request);
		for (Iterator<Object[][]> iterator = rowsEligible.iterator(); iterator
				.hasNext();) {
			Object[][] name = (Object[][]) iterator.next();
			int j = 0;
			for (int i = 0; i < name.length; i++) {
				for (j = 0; j < name[0].length; j++) {
					// logger.info("saved
					// name["+i+"]"+"["+j+"]......."+name[i][j]);
				}
			}
		}

		// SETS SALARY RECORD OF ONHOLD MONTHS
		try {
			Vector<Object[][]> onholdRows = model.viewOnholdSalary(
					settleDetails, request);
			if (onholdRows != null) {
				request.setAttribute("onholdRows", onholdRows);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// SETS TOTAL SALARY AMOUNT
		String amtQuery = " SELECT NVL(SETTL_NETSAL_AMT,0)  "
				+ " FROM HRMS_SETTL_HDR WHERE SETTL_CODE = "
				+ settleDetails.getSettCode();
		Object amtObj[][] = model.getSqlModel().getSingleResult(amtQuery);
		if (amtObj != null && amtObj.length > 0) {
			settleDetails.setSalaryAmt(String.valueOf(amtObj[0][0]));
		} else {
			settleDetails.setSalaryAmt("0");
		}
		model.terminate();
		settleDetails.setEnableAll("N");
		return "settlementFirst";
	}

	public String nextSecond() throws Exception {
		try {
			// SETS LEAVE TABLE
			SettlementDetailsModel model = new SettlementDetailsModel();
			model.initiate(context, session);
			// SETS SETTL AMT,GRATUITY,REIMBURSEMENT, DEDUCTION AMOUNTS
			String amtQuery = " SELECT NVL(SETTL_NETAMT,0), NVL(SETTL_GRATUITY,0), NVL(SETTL_REIMBURSE,0), NVL(SETTL_DEDUCTION,0), NVL(SETTL_NETSAL_AMT,0),NVL(SETTL_TAX_AMT,0),  "
					+ " NVL(SETTL_SERVICE_TENURE,0), NVL(SETTL_GRATUITY_AVG_SALARY,0),NVL(SETTL_REIMBURSE_COMMENT,' '), NVL(SETTL_DEDUCTION_COMMENT,' ') FROM HRMS_SETTL_HDR WHERE SETTL_CODE = "
					+ settleDetails.getSettCode();
			Object amtObj[][] = model.getSqlModel().getSingleResult(amtQuery);
			if (amtObj != null && amtObj.length > 0) {
				settleDetails.setSettleAmt(String.valueOf(amtObj[0][0]));
				settleDetails.setGratuity(String.valueOf(amtObj[0][1]));
				settleDetails.setReimburse(String.valueOf(amtObj[0][2]));
				settleDetails.setDeduct(String.valueOf(amtObj[0][3]));
				settleDetails.setSalaryAmt(String.valueOf(amtObj[0][4]));
				settleDetails.setTaxValue(String.valueOf(amtObj[0][5]));
				settleDetails.setServiceTenure(String.valueOf(amtObj[0][6]));
				settleDetails
						.setGratuityAvgSalary(String.valueOf(amtObj[0][7]));
				settleDetails
						.setReimburseComments(String.valueOf(amtObj[0][8]));
				settleDetails.setDeductComments(String.valueOf(amtObj[0][9]));
			} else {
				settleDetails.setGratuity("0");
				settleDetails.setReimburse("0");
				settleDetails.setDeduct("0");
				settleDetails.setTaxValue("0");
				settleDetails.setSalaryAmt("0");
				settleDetails.setServiceTenure("0");
				settleDetails.setGratuityAvgSalary("0");
				settleDetails.setReimburseComments("");
				settleDetails.setDeductComments("");
			}
			// SETS SAVED LEAVE RECORDS
			String[][] values = model.getLeaveRecordSearch(settleDetails);
			if (values != null) {
				request.setAttribute("values", values);
				int length = values.length;
				// logger.info("length....."+length);
				settleDetails.setLeaveLength(String.valueOf(length));
			} else {
				settleDetails.setTotalAmt("0");
				settleDetails.setEncashAmt("0");
			}
			// SETS SAVED LOAN RECORDS
			String[][] loanValues = model.getSavedLoanAmount(settleDetails);
			if (values != null) {
				request.setAttribute("loanValues", loanValues);
			} else {
				settleDetails.setTotalLoanAmt("0");
				settleDetails.setLoanAmtCh("0");
			}
			model.terminate();
			logger.info("Enable all in previous fourth : "
					+ settleDetails.getEnableAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
		settleDetails.setSettlementFlag(true);
		getNavigationPanel(5);
		settleDetails.setEnableAll("N");
		return "settlementSecond";
	}

	public String nextThird() throws Exception {
		// SETS HEADER
		SettlementDetailsModel model = new SettlementDetailsModel();
		model.initiate(context, session);
		// DISPLAY RECORDS OF LAST PAGE IF SAVED
		model.retrieveSettleData(settleDetails, request);
		model.terminate();
		getNavigationPanel(6);
		settleDetails.setEnableAll("N");
		return "settlementThird";
	}

	public String addRow() {
		SettlementDetailsModel model = new SettlementDetailsModel();
		model.initiate(context, session);
		String status = request.getParameter("status");
		logger.info("status  : " + status);

		String[] month = request.getParameterValues("payByMnth");
		String[] year = request.getParameterValues("payByYr");
		String[] maxDays = request.getParameterValues("maxDays");
		String[] calPayByDays = request.getParameterValues("calPayByDays");
		String[] calPayByType = request.getParameterValues("calPayByType");

		String[] ohMonth = request.getParameterValues("hdOhMnth");
		String[] ohYear = request.getParameterValues("hdOhYear");
		String[] ohMaxDays = request.getParameterValues("hdOhMaxDays");
		String[] ohCalDays = request.getParameterValues("hdcalDays");
		String[] ohCalType = request.getParameterValues("hdcalType");

		int payListLength = 0;
		if (month != null)
			payListLength = month.length;
		logger.info("payListLength     :" + payListLength);
		int listLength = 0;
		if (ohMonth != null)
			listLength = ohMonth.length;
		logger.info("listLength     :" + listLength);

		ArrayList<Object> list = new ArrayList<Object>();
		for (int i = 0; i < payListLength; i++) {
			SettlementDetails bean = new SettlementDetails();
			bean.setPayByMnth(month[i]);
			bean.setPayByYr(year[i]);
			bean.setMaxDays(maxDays[i]);
			bean.setCalPayByDays(calPayByDays[i]);
			bean.setCalPayByType(calPayByType[i]);
			list.add(bean);
		}

		SettlementDetails bean = new SettlementDetails();
		bean.setPayByMnth("");
		bean.setPayByYr("");
		bean.setMaxDays("");
		bean.setCalPayByDays("0");
		bean.setCalPayByType("");
		list.add(bean);
		if (listLength > 0) {
			for (int j = 0; j < listLength; j++) {
				bean = new SettlementDetails();
				bean.setOhMonth(ohMonth[j]);
				bean.setOhYear(ohYear[j]);
				bean.setOhMaxDays(ohMaxDays[j]);
				bean.setCalDays(ohCalDays[j]);
				bean.setCalType(ohCalType[j]);
				bean.setOnholdFlag(true);
				list.add(bean);
			}
		}

		settleDetails.setPayListCode("" + (payListLength + 1));
		settleDetails.setTypeList(list);
		if (status.equals("P"))
			getNavigationPanel(1);
		else if (status.equals("I")) {
			getNavigationPanel(7);
		}
		return "detailView";
	}

	public String deleteRows() {
		String status = request.getParameter("status");
		logger.info("status  : " + status);

		String[] month = request.getParameterValues("payByMnth");
		String[] year = request.getParameterValues("payByYr");
		String[] maxDays = request.getParameterValues("maxDays");
		String[] calPayByDays = request.getParameterValues("calPayByDays");
		String[] calPayByType = request.getParameterValues("calPayByType");

		String[] ohMonth = request.getParameterValues("hdOhMnth");
		String[] ohYear = request.getParameterValues("hdOhYear");
		String[] ohMaxDays = request.getParameterValues("hdOhMaxDays");
		String[] ohCalDays = request.getParameterValues("hdcalDays");
		String[] ohCalType = request.getParameterValues("hdcalType");

		String[] checkValue = new String[month.length];
		int count = 0;
		for (int i = 0; i < month.length; i++) {
			checkValue[i] = request.getParameter("hdeleteSkill" + (i + 1));
			logger.info("checkValue value      ==  :" + checkValue[i]);
			if (checkValue[i].equals("Y"))
				count++;
		}

		int payListLength = 0;
		if (request.getParameter("payByMnth") != null)
			payListLength = month.length;
		int listLength = 0;
		if (request.getParameter("hdOhMnth") != null)
			listLength = ohMonth.length;

		ArrayList<Object> tableList = new ArrayList<Object>();

		try {
			for (int i = 0; i < payListLength; i++) {
				if (!checkValue[i].equals("Y")) {
					// if not checked
					SettlementDetails bean = new SettlementDetails();
					bean.setPayByMnth(month[i]);
					bean.setPayByYr(year[i]);
					bean.setMaxDays(maxDays[i]);
					bean.setCalPayByDays(calPayByDays[i]);
					bean.setCalPayByType(calPayByType[i]);
					tableList.add(bean);
				}
			}

			if (listLength > 0) {
				for (int j = 0; j < listLength; j++) {
					SettlementDetails bean = new SettlementDetails();
					bean.setOhMonth(ohMonth[j]);
					bean.setOhYear(ohYear[j]);
					bean.setOhMaxDays(ohMaxDays[j]);
					bean.setCalDays(ohCalDays[j]);
					bean.setCalType(ohCalType[j]);
					bean.setOnholdFlag(true);
					tableList.add(bean);
				}

			}
			settleDetails.setTypeList(tableList);
			settleDetails.setPayListCode("" + (payListLength - count));
			settleDetails.setListCode("" + listLength);

			if (String.valueOf(settleDetails.getListCode()).equals("0")
					&& String.valueOf(settleDetails.getPayListCode()).equals(
							"0")) {
				settleDetails.setNoDtlData("true");
			}
		} catch (Exception e) {
			logger.error("Error in deleting rows : ", e);
			e.printStackTrace();
		}
		if (status.equals("P"))
			getNavigationPanel(1);
		else if (status.equals("I")) {
			getNavigationPanel(7);
		}
		return "detailView";
	}

	/**
	 * @param String
	 *            settlement code
	 * @return String to lock the settlement of resigned employee
	 * 
	 */
	public String lock() {

		SettlementDetailsModel model = new SettlementDetailsModel();
		model.initiate(context, session);
		boolean res = false;
		res = model.updateLock(settleDetails);
		if (res) {
			addActionMessage("Settlement locked successfully.");
			String stat = "Finalized Settlement";
			request.setAttribute("stat", stat);
			// getSettlementDetails();
			model.callSettlementList(settleDetails, "F", request,getprofileQuery(settleDetails));
			settleDetails.setStatus("F");
			settleDetails.setLockFlag("Y");
			settleDetails.setFinalFlag("true");
			// settleDetails.setSaveAndNextFlag(false);

		} else {
			addActionMessage("Error while locking the record.");
		}
		settleDetails.setIsFlag("true");
		model.terminate();
		return SUCCESS;
	}

	/**
	 * @param String
	 *            settlement code
	 * @return String to unlock the settlement of resigned employee
	 * 
	 */
	public String unLock() {
		SettlementDetailsModel model = new SettlementDetailsModel();
		model.initiate(context, session);
		boolean res = false;
		res = model.updateUnLock(settleDetails);
		if (res) {
			// addActionMessage("Settlement unlocked successfully.");
			String stat = "Finalized Settlement";
			request.setAttribute("stat", stat);
			// getSettlementDetails();
			model.callSettlementList(settleDetails, "F", request,getprofileQuery(settleDetails));
			settleDetails.setStatus("I");
			settleDetails.setLockFlag("N");
			settleDetails.setFinalFlag("false");
			// settleDetails.setSaveAndNextFlag(true);
			settleDetails.setSettDtlCode("");
		} else {
			addActionMessage("Error while unlocking the record.");
		}
		/*
		 * if (settleDetails.isAfterNextFlag() == true) getNavigationPanel(6);
		 * else getNavigationPanel(4);
		 */
		// reset();
		settleDetails.setIsFlag("true");
		model.terminate();
		return SUCCESS;
	}

	/**
	 * @param String
	 *            settlement code
	 * @return String for deleting the settlement header information with salary
	 *         credits and debits & leave records.
	 * 
	 * 
	 */
	public String delete() throws Exception {
		getNavigationPanel(1);
		SettlementDetailsModel model = new SettlementDetailsModel();
		model.initiate(context, session);
		boolean result = model.deleteSettlement(settleDetails);
		model.terminate();
		if (result) {
			addActionMessage(getMessage("delete"));
			String stat = "In Process Settlement";
			request.setAttribute("stat", stat);
			// getSettlementDetails();
			model.callSettlementList(settleDetails, "I", request,getprofileQuery(settleDetails));
			settleDetails.setStatus("P");
		} else {
			addActionMessage("Error while deleting the record.");
		}
		return SUCCESS;
	}

	/**
	 * @param String
	 *            settlement code
	 * @return String Generates the report of settlement
	 * 
	 */
	public String report() {
		SettlementDetailsReportModel model = new SettlementDetailsReportModel();
		model.initiate(context, session);
		String settlCode = request.getParameter("settCode");
		logger.info("settlement code====" + request.getParameter("settCode"));
		if (settlCode != null && !(settlCode.equals("") && !settlCode.equals("null"))) {
			settleDetails.setSettCode(settlCode);
		}
		model.getReport(request, response, settleDetails);
		model.terminate();
		// getSettlementDetails();
		return null;
	}

	public String calculateTax() {
		getNavigationPanel(3);
		SettlementDetailsModel model = new SettlementDetailsModel();
		model.initiate(context, session);
		// CALCULATES TAX
		model.calculateSettlementTax(settleDetails);
		setLeaveAndLoanLists();
		// SETS SETTL AMT,GRATUITY,REIMBURSEMENT, DEDUCTION AMOUNTS

		/*
		 * String amtQuery = " SELECT NVL(SETTL_NETAMT,0),
		 * NVL(SETTL_GRATUITY,0), NVL(SETTL_REIMBURSE,0), NVL(SETTL_DEDUCTION,0) " + "
		 * FROM HRMS_SETTL_HDR WHERE SETTL_CODE = " +
		 * settleDetails.getSettCode(); Object amtObj[][] =
		 * model.getSqlModel().getSingleResult(amtQuery); if (amtObj != null &&
		 * amtObj.length > 0) {
		 * settleDetails.setSettleAmt(String.valueOf(amtObj[0][0]));
		 * settleDetails.setGratuity(String.valueOf(amtObj[0][1]));
		 * settleDetails.setReimburse(String.valueOf(amtObj[0][2]));
		 * settleDetails.setDeduct(String.valueOf(amtObj[0][3])); } else {
		 * settleDetails.setGratuity("0"); settleDetails.setReimburse("0");
		 * settleDetails.setDeduct("0"); }
		 */

		return "settlementSecond";
	}

	public String setLeaveAndLoanLists() {
		// SETS LEAVE RECORDS
		double netEncashAmt = 0.0d;
		double salaryAmt = 0.0d;
		try {
			salaryAmt = Double.parseDouble(request.getParameter("salaryAmt"));
		} catch (Exception e) {
			logger.error("Error in retrieving salaryAmt on calculateTax : "+ e);
			salaryAmt = 0.0;
		}
		double gratuity = 0.0d;
		try {
			gratuity = Double.parseDouble(settleDetails.getGratuity());
		} catch (Exception e) {
			logger.error("Error in retrieving gratuity on calculateTax : " + e);
			gratuity = 0.0;
		}
		double reimburse = 0.0d;
		try {
			reimburse = Double.parseDouble(request.getParameter("reimburse"));
		} catch (Exception e) {
			logger
					.error("Error in retrieving reimburse on calculateTax : "
							+ e);
			reimburse = 0.0;
		}
		double deduct = 0.0d;
		try {
			deduct = Double.parseDouble(request.getParameter("deduct"));
		} catch (Exception e) {
			logger.error("Error in retrieving deduct on calculateTax : " + e);
			deduct = 0.0;
		}
		double taxAmount = Double.parseDouble(settleDetails.getTaxValue());
		double settleAmt = 0.0d;

		SettlementDetailsModel model = new SettlementDetailsModel();
		model.initiate(context, session);
		try {
			String[][] values = null;
			values = model.setLeaveOnCalculateTax(settleDetails, request);
			if (values != null) {
				request.setAttribute("values", values);
				int length = values.length;
				settleDetails.setLeaveLength(String.valueOf(length));
				netEncashAmt = Double.parseDouble(Utility
						.twoDecimals(settleDetails.getTotalAmt()));
				logger.info("netEncashAmt......." + netEncashAmt);

			}// end if
		} catch (Exception e) {
			logger.error("Error in setting leave encashment table  : " + e);
			netEncashAmt = 0.0;
			settleDetails.setEncashAmt("0");
			settleDetails.setTotalLeaveDays("0");
			e.printStackTrace();
		}
		double netLoanAmt = 0.0d;
		// SETTING LOAN DETAILS
		try {
			String[][] loanValues = null;
			loanValues = model.setLoanAmountOnCalculateTax(settleDetails,
					request);
			if (loanValues != null) {
				logger.info("in if.......");
				request.setAttribute("loanValues", loanValues);
				netLoanAmt = Double
						.parseDouble(settleDetails.getTotalLoanAmt());
				logger.info("netLoanAmt......." + netLoanAmt);

			}// end if
		} catch (Exception e) {
			logger.error("Error in setting loan table  : " + e);
			e.printStackTrace();
			netLoanAmt = 0.0;
			settleDetails.setLoanAmtCh("0");
			settleDetails.setTotalLoanAmt("0");
			e.printStackTrace();
		}

		// logger.info("Salary Amount = :"+settleDetails.getSalaryAmt());

		settleAmt = (netEncashAmt + salaryAmt + gratuity + reimburse)
				- (deduct + taxAmount + netLoanAmt);
		logger.info("netEncashAmt + salaryAmt + gratuity + reimburse      :"
				+ (netEncashAmt + salaryAmt + gratuity + reimburse)
				+ "\ndeduct + taxAmount + netLoanAmt :"
				+ (deduct + taxAmount + netLoanAmt)
				+ "\nSettlement Amount      : " + settleAmt);
		settleDetails.setSettleAmt(String.valueOf(Math.round(settleAmt)));
		return null;
	}

	/**
	 * Settlement amount calculation - total leave encashed + salary eligible
	 * days (+/-) short notice pay
	 * 
	 * @return String
	 */
	public String calculate() throws Exception {

		String poolName = String.valueOf(session.getAttribute("session_pool"));
		if (!(poolName.equals("") || poolName == null || poolName.equals(null))) {
			poolName = "/" + poolName;
		}
		String path = getText("data_path") + "/datafiles/" + poolName
				+ "/xml/Payroll/";

		SettlementDetailsModel model = new SettlementDetailsModel();
		model.initiate(context, session);
		double netOnHoldAmt = 0.0d;
		double netEncashAmt = 0.0d;
		double netShortPay = 0.0d;
		double settlAmt = 0.0d;
		String type = "";

		try {
			// SETS DETAIL TABLE
			logger.info("Settl code in calculate...."
					+ settleDetails.getSettCode());
			logger.info("Settl dtl code in calculate before...."
					+ settleDetails.getSettDtlCode());
			try {
				// SETTING CODE IF DATA PRESENT IN DTL TABLE/ CREDITS TABLE
				String dtlCodeQuery = " SELECT DISTINCT HRMS_SETTL_DTL.SETTL_CODE FROM HRMS_SETTL_DTL  "
						+ " INNER JOIN HRMS_SETTL_CREDITS ON (HRMS_SETTL_CREDITS.SETTL_CODE = HRMS_SETTL_DTL.SETTL_CODE ) "
						+ " WHERE HRMS_SETTL_DTL.SETTL_CODE = "
						+ settleDetails.getSettCode();
				Object dtlCode[][] = model.getSqlModel().getSingleResult(
						dtlCodeQuery);
				logger.info("settlement detail code length......"
						+ dtlCode.length);
				if (dtlCode != null && dtlCode.length > 0) {
					settleDetails.setSettDtlCode(String.valueOf(dtlCode[0][0]));
				} else {
					settleDetails.setSettDtlCode("");
				}
			} catch (Exception e) {
				settleDetails.setSettDtlCode("");
				e.printStackTrace();
			}
			logger.info("Settl dtl code in calculate after...."
					+ settleDetails.getSettDtlCode());

			String calType[] = request.getParameterValues("calPayByType");
			String month[] = request.getParameterValues("payByMnth");
			String year[] = request.getParameterValues("payByYr");
			String eligibleDays[] = request.getParameterValues("calPayByDays");
			String[] maxDays = request.getParameterValues("maxDays");

			if (settleDetails.getSettDtlCode() == null
					|| settleDetails.getSettDtlCode().equals("null")
					|| settleDetails.getSettDtlCode().equals("")) {

				logger.info("In calculate===if");
				settleDetails.setReCalFlag("false");

				try {
					// CALCULATES THE CREDITS AND DEBITS OF ELIGIBLE DAYS
					// ACCORDING TO
					// FLAG.

					ArrayList<Object[][]> rowsEligible = model.calDaySalary(
							settleDetails, request, path, calType, month, year,
							eligibleDays, maxDays);
					request.setAttribute("rows_List", rowsEligible);
					for (Iterator<Object[][]> iterator = rowsEligible
							.iterator(); iterator.hasNext();) {
						Object[][] name = (Object[][]) iterator.next();
						int j = 0;
						for (int i = 0; i < name.length; i++) {
							for (j = 0; j < name[0].length; j++) {
								// logger.info("name["+i+"]"+"["+j+"]......."+name[i][j]);
							}
						}
						netShortPay = Double.parseDouble(String
								.valueOf(name[0][j - 4]));

						// logger.info("netShortPay...."+String.valueOf(name[0][j-2])+"..."+netShortPay);
						if (String.valueOf(name[0][j - 2]).equals("CO")) {
							settlAmt = settlAmt + netShortPay;
						} else {
							settlAmt = settlAmt - netShortPay;
						}
					}
				} catch (Exception e) {
					netShortPay = 0.0;
					settlAmt = 0.0;
					e.printStackTrace();
				}
				logger.info("Total settlement amount 1   :" + settlAmt);

				try {
					if (settleDetails.isOnholdFlag() == true) {
						// RETRIEVES THE CREDITS AND DEBITS OF ONHOLD MONTHS
						Vector<Object[][]> onholdRows = model.calOnholdSalData(
								settleDetails, request);
						if (onholdRows != null) {
							for (Iterator<Object[][]> iterator = onholdRows
									.iterator(); iterator.hasNext();) {
								Object[][] onHold = (Object[][]) iterator
										.next();
								int j = 0;
								for (int i = 0; i < onHold.length; i++) {
									for (j = 0; j < onHold[0].length; j++) {
										// logger.info("name["+i+"]"+"["+j+"]......."+onHold[i][j]);
									}
								}
								type = String.valueOf(onHold[0][j - 2]);
								// TOTAL ONHOLD AMOUNT (PAID AMTS EXEMPTED)
								if (type.equals("OH"))
									netOnHoldAmt += Double.parseDouble(String
											.valueOf(onHold[0][j - 4]));

							}
							request.setAttribute("onholdRows", onholdRows);
							logger.info("netOnHoldAmt......." + netOnHoldAmt);
						}// end if
					}
				} catch (Exception e) {
					e.printStackTrace();
					netOnHoldAmt = 0.0;
				}
				// CALCULATING TOTAL SETTLEMENT AMOUNT
				settlAmt = settlAmt + netOnHoldAmt + netEncashAmt;
				logger.info("Total settlement amount 2 if   :" + settlAmt);

				settleDetails
						.setSalaryAmt(String.valueOf(Math.round(settlAmt)));

			} else {
				logger.info("In calculate===else");
				settleDetails.setReCalFlag("true");
				Vector<Object> tot_Recalculate_Vect = new Vector<Object>();
				Vector<Object> tot_Calculated_Vect = new Vector<Object>();

				try {
					String[] checkValue = new String[month.length];

					int count = 0;

					for (int i = 0; i < month.length; i++) {
						checkValue[i] = request
								.getParameter("hrecal" + (i + 1));
						logger.info("checkValue value      ==  :"
								+ checkValue[i]);
						if (checkValue[i].equals("Y")) {
							count++;
							// FOR DETAILS TO BE RECALCULATED
							tot_Recalculate_Vect.add(calType[i]);
							tot_Recalculate_Vect.add(eligibleDays[i]);
							tot_Recalculate_Vect.add(month[i]);
							tot_Recalculate_Vect.add(year[i]);
							tot_Recalculate_Vect.add(maxDays[i]);
						} else {
							// FOR DETAILS PICKED FROM DATABASE (NO
							// RECALCULATION)
							tot_Calculated_Vect.add(calType[i]);
							tot_Calculated_Vect.add(month[i]);
							tot_Calculated_Vect.add(year[i]);
						}
					}

				} catch (Exception e) {
					logger.error("Error in Adding values to vector : " + e);
					e.printStackTrace();
				}
				try {
					// TO BE RECALCULATED
					String[] recal_type = new String[tot_Recalculate_Vect
							.size() / 5];
					String[] recal_days = new String[tot_Recalculate_Vect
							.size() / 5];
					String[] recal_mon = new String[tot_Recalculate_Vect.size() / 5];
					String[] recal_year = new String[tot_Recalculate_Vect
							.size() / 5];
					String[] recal_maxDays = new String[tot_Recalculate_Vect
							.size() / 5];
					int count_total = 0;
					int i;
					for (i = 0; i < tot_Recalculate_Vect.size() / 5; i++) {

						recal_type[i] = (String) tot_Recalculate_Vect
								.get(count_total++);
						recal_days[i] = (String) tot_Recalculate_Vect
								.get(count_total++);
						recal_mon[i] = (String) tot_Recalculate_Vect
								.get(count_total++);
						recal_year[i] = (String) tot_Recalculate_Vect
								.get(count_total++);
						recal_maxDays[i] = (String) tot_Recalculate_Vect
								.get(count_total++);

					}

					// PICKED FROM DATABASE...NO RECALCULATION
					String[] cal_type = new String[tot_Calculated_Vect.size() / 3];
					String[] cal_mon = new String[tot_Calculated_Vect.size() / 3];
					String[] cal_year = new String[tot_Calculated_Vect.size() / 3];
					int cal_count = 0;
					for (int j = 0; j < tot_Calculated_Vect.size() / 3; j++) {
						cal_type[j] = (String) tot_Calculated_Vect
								.get(cal_count++);
						cal_mon[j] = (String) tot_Calculated_Vect
								.get(cal_count++);
						cal_year[j] = (String) tot_Calculated_Vect
								.get(cal_count++);
					}

					// IF CHECKED RECALCULATE
					ArrayList<Object[][]> rowsRecalculated = new ArrayList<Object[][]>();
					if (tot_Recalculate_Vect.size() > 0) {
						settleDetails.setPayListCode(String.valueOf(i));
						rowsRecalculated = model.calDaySalary(settleDetails,
								request, path, recal_type, recal_mon,
								recal_year, recal_days, recal_maxDays);
						logger.info("rowsRecalculated size  :"
								+ rowsRecalculated.size());
					}

					// IF NOT CHECKED PICK FROM DATABASE
					ArrayList<Object[][]> rowsCalculated = new ArrayList<Object[][]>();
					if (tot_Calculated_Vect.size() > 0) {
						rowsCalculated = model.viewShortSalary(settleDetails,
								request, cal_type, cal_mon, cal_year);
						logger.info("rowsCalculated size  :"
								+ rowsCalculated.size());
					}

					if (rowsRecalculated != null && rowsCalculated == null) {
						logger
								.info("rowsRecalculated!=null && rowsCalculated==null");
						request.setAttribute("rows_List", rowsRecalculated);
					} else if (rowsRecalculated == null
							&& rowsCalculated != null) {
						logger
								.info("rowsRecalculated==null && rowsCalculated!=null");
						request.setAttribute("rows_List", rowsCalculated);
					} else if (rowsRecalculated != null
							&& rowsCalculated != null) {
						logger
								.info("rowsRecalculated!=null && rowsCalculated!=null");
						for (Iterator<Object[][]> iterator = rowsCalculated
								.iterator(); iterator.hasNext();) {
							Object[][] calObj = (Object[][]) iterator.next();
							rowsRecalculated.add(calObj);
						}
						for (Iterator<Object[][]> iterator = rowsRecalculated
								.iterator(); iterator.hasNext();) {
							Object[][] recalObj = (Object[][]) iterator.next();
							int k2 = 0;
							for (int k = 0; k < recalObj.length; k++) {
								for (k2 = 0; k2 < recalObj[0].length; k2++) {
									// logger.info("recalObj[" + k + "][" + k2
									// + "] : " + recalObj[k][k2]);
								}
							}
							netShortPay = Double.parseDouble(String
									.valueOf(recalObj[0][k2 - 4]));
							logger.info("netShortPay...."
									+ String.valueOf(recalObj[0][k2 - 2])
									+ "..." + netShortPay);
							if (String.valueOf(recalObj[0][k2 - 2])
									.equals("CO")) {
								settlAmt = settlAmt + netShortPay;
							} else {
								settlAmt = settlAmt - netShortPay;
							}
							logger.info("Salary Amount in recalculate  : "
									+ settlAmt);
						}
						request.setAttribute("rows_List", rowsRecalculated);
						model.getCombinedMonthList(request);
					}

				} catch (Exception e) {
					logger.error("Error in recalculate: " + e);
					e.printStackTrace();
				}

				// SETS SALARY RECORD OF ONHOLD MONTHS
				try {
					Vector<Object[][]> onholdRows = model.viewOnholdSalary(
							settleDetails, request);
					if (onholdRows != null) {
						request.setAttribute("onholdRows", onholdRows);

						for (Iterator<Object[][]> iterator = onholdRows
								.iterator(); iterator.hasNext();) {
							Object[][] onHold = (Object[][]) iterator.next();
							int j = 0;
							for (int i = 0; i < onHold.length; i++) {
								for (j = 0; j < onHold[0].length; j++) {
									logger.info("onHold[" + i + "]" + "[" + j
											+ "]......." + onHold[i][j]);
								}
							}
							type = String.valueOf(onHold[0][j - 2]);
							// TOTAL ONHOLD AMOUNT (PAID AMTS EXEMPTED)
							if (type.equals("OH"))
								netOnHoldAmt = Double.parseDouble(String
										.valueOf(onHold[0][j - 1]));

						}
						logger.info("netOnHoldAmt......." + netOnHoldAmt);
					}// end if
				} catch (Exception e) {
					e.printStackTrace();
				}

				// CALCULATING TOTAL SETTLEMENT AMOUNT
				settlAmt = settlAmt + netOnHoldAmt;
				logger.info("Total salary amount   :" + settlAmt);
				settleDetails
						.setSalaryAmt(String.valueOf(Math.round(settlAmt)));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
		return SUCCESS;
	}

	/**
	 * Method call on f9 action of prepared by employee
	 * 
	 * @return String
	 * 
	 */
	public String f9prepByaction() {

		String proBy = " SELECT EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME||'  '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME,' '),EMP_ID FROM HRMS_EMP_OFFC"
				+ " WHERE EMP_ID NOT IN ("
				+ settleDetails.getEmpCode()
				+ ")"
				+ " AND EMP_STATUS = 'S'" + " ORDER BY EMP_ID ";
		String[] headers = { getMessage("employee.id"), getMessage("employee") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "prepbyToken", "preparedby", "prepbyCode" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(proBy, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/**
	 * Method call on f9 action of checked by HR
	 * 
	 * @return String
	 * 
	 */
	public String f9chkByHRaction() {

		String proBy = " SELECT EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME||'  '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME,' '),EMP_ID FROM HRMS_EMP_OFFC"
				+ " WHERE EMP_ID NOT IN ( "
				+ settleDetails.getEmpCode()
				+ ")"
				+ " AND EMP_STATUS = 'S'" + " ORDER BY EMP_ID ";
		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "chkbyToken", "checkedby", "chkbyCode" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(proBy, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/**
	 * Method call on f9 action of checked by account
	 * 
	 * @return String
	 * 
	 */
	public String f9chkByAccaction() {

		String proBy = " SELECT EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME||'  '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME,' '),EMP_ID FROM HRMS_EMP_OFFC"
				+ " left JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) WHERE EMP_ID NOT IN ("
				+ settleDetails.getEmpCode()
				+ ")"
				+ " AND EMP_STATUS = 'S'"
				+ " ORDER BY EMP_ID ";
		String[] headers = { getMessage("employee.id"), getMessage("employee") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "accChkToken", "accCheck", "accChkCode" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(proBy, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/**
	 * Method call on f9 action of Handover by emp
	 * 
	 * @return String
	 * 
	 */
	public String f9handOveraction() {

		String proBy = " SELECT EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME||'  '||HRMS_EMP_OFFC.EMP_MNAME||'  '||HRMS_EMP_OFFC.EMP_LNAME,' '),EMP_ID FROM HRMS_EMP_OFFC"
				+ " left JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) WHERE EMP_ID NOT IN ("
				+ settleDetails.getEmpCode()
				+ ")"
				+ " AND EMP_STATUS = 'S'"
				+ " ORDER BY EMP_ID ";
		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "handOverToken", "handedOver", "handOverCode" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(proBy, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	/**
	 * Method call on f9 action for bank
	 * 
	 * @return String
	 * 
	 */
	public String f9bankaction() {

		String proBy = " SELECT BANK_MICR_CODE,NVL(BANK_NAME,' ') FROM HRMS_BANK"
				+ " ORDER BY BANK_MICR_CODE ";
		String[] headers = { getMessage("micr"), getMessage("bank") };
		String[] headerWidth = { "20", "80" };
		String[] fieldNames = { "bankMicrId", "bank" };
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(proBy, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String calcGratuity() {
		getNavigationPanel(3);
		SettlementDetailsModel model = new SettlementDetailsModel();
		model.initiate(context, session);
		model.calcGratuity(settleDetails);
		setLeaveAndLoanLists();
		model.terminate();
		return "settlementSecond";
	}
	
	public String f9Action() throws Exception {
		
		try {
			String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME NAME," 
				 + " DECODE(SETTL_LOCKFLAG,'Y','Finalized','N','In Process','Pending'),TO_CHAR(HRMS_RESIGN.RESIGN_DATE,'DD-MM-YYYY') RESIGN,  TO_CHAR(SETTL_SETTLDT,'DD-MM-YYYY') SETTL, "
				 + " TO_CHAR(HRMS_RESIGN.RESIGN_SEPR_DATE,'DD-MM-YYYY') SEPAR, " 
				 + " HRMS_RESIGN.RESIGN_CODE,HRMS_SETTL_HDR.SETTL_CODE,RESIGN_NOTICE_PERIOD,"
				 + " DECODE(SETTL_LOCKFLAG,'Y','F','N','I','P'),RESIGN_NOTICE_STATUS, HRMS_EMP_OFFC.EMP_ID "  
				 + " FROM HRMS_RESIGN " 
				 + " INNER JOIN HRMS_EMP_OFFC ON(HRMS_RESIGN.RESIGN_EMP = HRMS_EMP_OFFC.EMP_ID)"  
				 + " LEFT JOIN HRMS_SETTL_HDR on(HRMS_SETTL_HDR.SETTL_RESGNO=HRMS_RESIGN.RESIGN_CODE )" 
				 + " WHERE  RESIGN_WITHDRAWN = 'N' AND RESIGN_APPL_STATUS='Y'";
				
			if (settleDetails.getUserProfileDivision() != null && settleDetails.getUserProfileDivision().length() > 0)
				query += "AND EMP_DIV IN (" + settleDetails.getUserProfileDivision()
						+ ")";
			
			String[] headers = { "Emp Id", "Employee Name", "Status", "Resign Date" };
			String[] headerwidth = { "10", "30", "20", "10" };
			String fieldNames[] = { "empToken", "empName", "decodedStatus",
					"resignDate", "settDate", "sepDate", "resignCode",
					"settCode", "noticePeriod", "status", "noticePeriodStatus",
					"empCode" };
			int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
			String submitFlag = "true";
			String submitToMethod = "SettlmentDetails_viewDetails.action";
			setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
			return "f9page";
	}
	
	public String viewDetails(){
		
		try {
			String status = settleDetails.getStatus();
			String resgDate = settleDetails.getResignDate();
			String separDate = settleDetails.getSepDate();
			String noticePeriod = settleDetails.getNoticePeriod();
			String empCode = settleDetails.getEmpCode();
			String resignCode = settleDetails.getResignCode();
			String noticeStatus = settleDetails.getNoticeStatus();
			SettlementDetailsModel model = new SettlementDetailsModel();
			
			settleDetails.setResignDate(resgDate);
			settleDetails.setSepDate(separDate);
			settleDetails.setNoticePeriod(noticePeriod);
			settleDetails.setResignCode(resignCode);
			settleDetails.setStatus(status);
			settleDetails.setNoticePeriodStatus(noticeStatus);
			
			model.initiate(context, session);
			String empQuery = "";
			if (status.equals("P")) {

				empQuery = " SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME NAME, HRMS_EMP_OFFC.EMP_ID,"
						+ " NVL(CENTER_NAME,' '),NVL(RANK_NAME,' '),HRMS_SETTL_HDR.SETTL_CODE,SETTL_LOCKFLAG  "
						+ " FROM HRMS_EMP_OFFC "
						+ " LEFT JOIN  HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
						+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
						+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
						+ " INNER JOIN HRMS_RESIGN ON(HRMS_EMP_OFFC.EMP_ID = HRMS_RESIGN.RESIGN_EMP) "
						+ " LEFT JOIN HRMS_SETTL_HDR on(HRMS_RESIGN.RESIGN_CODE = HRMS_SETTL_HDR.SETTL_RESGNO) "
						+ " WHERE HRMS_EMP_OFFC.EMP_ID = " + empCode;
			} else {

				empQuery = " SELECT HRMS_EMP_OFFC.EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME "
						+ " NAME, HRMS_EMP_OFFC.EMP_ID, NVL(CENTER_NAME,' '),NVL(RANK_NAME,' '), HRMS_SETTL_HDR.SETTL_CODE, SETTL_LOCKFLAG "
						+ " FROM HRMS_SETTL_HDR  "
						+ " LEFT JOIN HRMS_RESIGN on(HRMS_RESIGN.RESIGN_CODE = HRMS_SETTL_HDR.SETTL_RESGNO) "
						+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_RESIGN.RESIGN_EMP) "
						+ " LEFT JOIN  HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
						+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
						+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
						+ " WHERE HRMS_RESIGN.RESIGN_EMP = " + empCode;
			}
			Object empObj[][] = model.getSqlModel().getSingleResult(empQuery);
			settleDetails.setEmpCode(String.valueOf(empObj[0][2]));
			settleDetails.setEmpName(String.valueOf(empObj[0][1]));
			settleDetails.setEmpToken(String.valueOf(empObj[0][0]));
			settleDetails.setBranch(String.valueOf(empObj[0][3]));
			settleDetails.setDesgn(String.valueOf(empObj[0][4]));
			settleDetails.setSettCode(String.valueOf(empObj[0][5]));
			if (String.valueOf(empObj[0][6]).equals("Y"))
				settleDetails.setLockFlag("Y");
			else if (String.valueOf(empObj[0][6]).equals("N"))
				settleDetails.setLockFlag("N");
			else
				settleDetails.setLockFlag("");
			if (status.equals("P")) {
				logger.info("In status P");
				settleDetails.setFinalFlag("false");
				settleDetails.setReCalFlag("false");
				getNavigationPanel(1);
				// GETTING PAY BY EMPLOYEE/COMPANY MONTHS.
				ArrayList<Object> list = model.getPayByFields(settleDetails,
						resgDate, separDate, noticePeriod, empCode);

				logger.info("List size........." + list.size());
				int listSize = list.size();
				settleDetails.setPayListCode(String.valueOf(listSize));
				// GETTING ONHOLD/PAID MONTHS. PASS PAY BY LIST
				int size = model.getOnholdMonths(settleDetails, list, resgDate,
						separDate, noticePeriod, empCode);
				logger.info("size......." + size);
				// SETTING PAY BY & ONHOLD LIST SIZE
				if (size > 0 && size != listSize) {
					if (size > listSize) {
						size = size - listSize;
						logger.info("size1......." + size);
					} else {
						size = listSize - size;
						logger.info("size1......." + size);
					}
					settleDetails.setOnholdFlag(true);
					settleDetails.setListCode(String.valueOf(size));
				} else {
					settleDetails.setPayListCode(String.valueOf(listSize));
					settleDetails.setOnholdFlag(false);
				}
			} else if (status.equals("I")) {
				logger.info("In status I");
				settleDetails.setFinalFlag("false");
				settleDetails.setReCalFlag("true");
				getNavigationPanel(7);
				Object[][] settleData = model.retrieveSettleData(settleDetails,
						request);

				model.retrieveSettleDetails(settleDetails, request);

				String[][] note = new String[1][2];
				logger.info("separ date       :"
						+ String.valueOf(settleData[0][6]));
				logger.info("separ date settle       :"
						+ String.valueOf(settleData[0][36]));
				if (!(String.valueOf(settleData[0][6]).trim().equals(String
						.valueOf(settleData[0][36]).trim()))) {
					note[0][0] = getMessage("seperation.date")
							+ " has been changed under Resignation Details. "
							+ " \nPlease recalculate "
							+ getMessage("settelment.amount");
					request.setAttribute("note", note[0][0]);
				} else
					note[0][0] = "";

				logger.info("notice       :"
						+ String.valueOf(settleData[0][29]));
				logger.info("notice settle       :"
						+ String.valueOf(settleData[0][37]));
				if (!(String.valueOf(settleData[0][29]).trim().equals(String
						.valueOf(settleData[0][37]).trim()))) {
					note[0][1] = getMessage("period")
							+ " has been changed under Resignation Details. "
							+ " \nPlease recalculate "
							+ getMessage("settelment.amount");
					request.setAttribute("note1", note[0][1]);
				} else
					note[0][1] = "";

			} else {
				logger.info("In status F");
				settleDetails.setFinalFlag("true");
				settleDetails.setReCalFlag("false");
				getNavigationPanel(5);
				// retrieving details table
				model.retrieveSettleDetails(settleDetails, request);
				settleDetails.setEnableAll("N");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "detailView";
	}
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull
}