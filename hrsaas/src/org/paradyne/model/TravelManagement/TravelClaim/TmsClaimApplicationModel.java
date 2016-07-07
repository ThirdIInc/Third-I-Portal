package org.paradyne.model.TravelManagement.TravelClaim;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.TravelManagement.TravelClaim.TmsClaimApplication;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;
import org.paradyne.model.TravelManagement.TravelProcess.TravelProcessModel;
import org.paradyne.model.common.ReportingModel;
import com.lowagie.text.Font;
import com.lowagie.text.Image;

public class TmsClaimApplicationModel extends ModelBase {
	org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TmsClaimApplicationModel.class);

	public TmsClaimApplicationModel() {
	}

	public void getBlockedApplications(TmsClaimApplication bean,
			HttpServletRequest request, String empId) {

		Object blckTrvlData[][] = getSqlModel().getSingleResult(getQuery(17),
				new Object[] { empId });
		// bean.setMyPageSchlTrvlList("1");
		ArrayList<TmsClaimApplication> blckTrvlList = new ArrayList<TmsClaimApplication>();
		if (blckTrvlData != null && blckTrvlData.length > 0) {
			for (int i = 0; i < blckTrvlData.length; i++) {

				TmsClaimApplication bean1 = new TmsClaimApplication();
				// logger.info("i="+i);
				bean1.setAppId(checkNull(String.valueOf(blckTrvlData[i][0])));
				bean1.setAppCode(checkNull(String.valueOf(blckTrvlData[i][1])));
				bean1.setAppFor(checkNull(String.valueOf(blckTrvlData[i][11])));
				bean1.setEmpName(checkNull(String.valueOf(blckTrvlData[i][5])));
				bean1.setTrvlReqName(checkNull(String
						.valueOf(blckTrvlData[i][8])));
				bean1.setAppEndDate(checkNull(String
						.valueOf(blckTrvlData[i][7])));// travel date
				bean1
						.setInitName(checkNull(String
								.valueOf(blckTrvlData[i][4])));
				bean1.setEmpId(checkNull(String.valueOf(blckTrvlData[i][9])));
				bean1.setInitId(checkNull(String.valueOf(blckTrvlData[i][3])));
				bean1.setTrvlId(checkNull(String.valueOf(blckTrvlData[i][2])));
				bean1.setClaimDueDays(checkNull(String
						.valueOf(blckTrvlData[i][10])));
				bean1.setApplnStatus(checkNull(String
						.valueOf(blckTrvlData[i][12])));

			/*	bean1.setMaximumClaimDueDays(checkNull(String
						.valueOf(blckTrvlData[i][13])));*/

				/*
				 * Object data[][] = getSqlModel().getSingleResult(getQuery(70),
				 * new Object[] { bean1.getAppId(), bean1.getAppCode() });
				 */
				blckTrvlList.add(bean1);
			}
			bean.setTravelType("T");
		}
		bean.setBlockedAppList(blckTrvlList);

	}

	public void getScheduledApplications(TmsClaimApplication bean,
			HttpServletRequest request, String empId) {
		logger.info("--empId--" + empId);
		Object schlTrvlData[][] = getSqlModel().getSingleResult(getQuery(1),
				new Object[] { empId });

		bean.setMyPageSchlTrvlList("1");

		ArrayList<TmsClaimApplication> schlTrvlList = new ArrayList<TmsClaimApplication>();
		if (schlTrvlData != null && schlTrvlData.length > 0) {
			for (int i = 0; i < schlTrvlData.length; i++) {

				TmsClaimApplication bean1 = new TmsClaimApplication();
				// logger.info("i="+i);
				bean1.setAppId(checkNull(String.valueOf(schlTrvlData[i][0])));
				bean1.setAppCode(checkNull(String.valueOf(schlTrvlData[i][1])));
				bean1.setAppFor(checkNull(String.valueOf(schlTrvlData[i][11])));
				bean1.setEmpName(checkNull(String.valueOf(schlTrvlData[i][5])));
				bean1.setTrvlReqName(checkNull(String
						.valueOf(schlTrvlData[i][8])));
				bean1.setAppEndDate(checkNull(String
						.valueOf(schlTrvlData[i][7])));// travel date
				bean1.setInitName(checkNull(String
								.valueOf(schlTrvlData[i][4])));
				bean1.setEmpId(checkNull(String.valueOf(schlTrvlData[i][9])));
				bean1.setInitId(checkNull(String.valueOf(schlTrvlData[i][3])));
				bean1.setTrvlId(checkNull(String.valueOf(schlTrvlData[i][2])));
				bean1.setClaimDueDays(checkNull(String
						.valueOf(schlTrvlData[i][10])));
				bean1.setApplnStatus(checkNull(String
						.valueOf(schlTrvlData[i][12])));
				 //source destination
				// bean1.setScheduledApplicationStatus(checkNull(String.valueOf(schlTrvlData[i][12])));
				/*
				 * Object data[][] = getSqlModel().getSingleResult(getQuery(70),
				 * new Object[] { bean1.getAppId(), bean1.getAppCode() });
				 */
				schlTrvlList.add(bean1);
			}
			bean.setTravelType("T");
		}
		bean.setScheduledAppList(schlTrvlList);
	}

	public void getEmployeeDtls(TmsClaimApplication claimApp, String empId) {
		Object empData[][] = getSqlModel().getSingleResult(getQuery(2),
				new Object[] { empId });
		// logger.info("--empId-- Ayyappa ---" + empId);
		if (empData != null && empData.length > 0) {
			claimApp.setEmployeeToken(checkNull(String.valueOf(empData[0][0])));
			claimApp.setEmployeeName(checkNull(String.valueOf(empData[0][1])));
			claimApp.setBranch(checkNull(String.valueOf(empData[0][2])));
			claimApp.setDept(checkNull(String.valueOf(empData[0][3])));
			claimApp.setDesg(checkNull(String.valueOf(empData[0][4])));
			claimApp.setGrad(checkNull(String.valueOf(empData[0][5])));
			claimApp.setEmployeeId(checkNull(String.valueOf(empData[0][6])));
			claimApp.setBranchId(checkNull(String.valueOf(empData[0][7])));
			claimApp.setDeptId(checkNull(String.valueOf(empData[0][8])));
			claimApp.setDesgId(checkNull(String.valueOf(empData[0][9])));
			claimApp.setGradId(checkNull(String.valueOf(empData[0][10])));
		}
	}

	/*
	 * public void addExpenseDtl(TmsClaimApplication claimApp,
	 * HttpServletRequest request, String[] srNo, String[] expenseDateIt,
	 * String[] expenseTypeIt, String[] expenseTypeIdIt, String[] particularsIt,
	 * String[] eligibleAmtIt, String[] expenseAmtIt, String[] proofIt, String[]
	 * proofRequiredIt, String[] expDtlId, String[] expItId) {
	 * 
	 * ArrayList<Object> list = new ArrayList<Object>();
	 * 
	 * if(expenseTypeIt!=null){ logger.info("--Inside if--"+srNo); for (int i =
	 * 0; i < expenseTypeIt.length; i++) { TmsClaimApplication bean = new
	 * TmsClaimApplication(); bean.setSrNo(srNo[i]);
	 * bean.setExpenseDateIt(expenseDateIt[i]);
	 * bean.setExpenseTypeIt(expenseTypeIt[i]);
	 * bean.setExpenseTypeIdIt(expenseTypeIdIt[i]);
	 * bean.setParticularsIt(particularsIt[i]);
	 * bean.setEligibleAmtIt(eligibleAmtIt[i]);
	 * bean.setExpenseAmtIt(expenseAmtIt[i]);
	 * bean.setProofIt(checkNull(proofIt[i]));
	 * bean.setProofRequiredIt(proofRequiredIt[i]); list.add(bean); } }
	 * 
	 * TmsClaimApplication bean1 = new TmsClaimApplication();
	 * bean1.setSrNo(String.valueOf(list.size() + 1));
	 * bean1.setExpenseDateIt(claimApp.getExpenseDate());
	 * bean1.setExpenseTypeIt(claimApp.getExpenseType());
	 * bean1.setExpenseTypeIdIt(claimApp.getExpenseTypeId());
	 * bean1.setParticularsIt(claimApp.getParticulars());
	 * bean1.setEligibleAmtIt(claimApp.getEligibleAmt());
	 * bean1.setExpenseAmtIt(claimApp.getExpenseAmt());
	 * bean1.setProofIt(claimApp.getUploadLocFileName());
	 * //bean1.setProofRequiredIt(claimApp.getProofRequired());
	 * bean1.setProofRequiredIt(claimApp.getProofRequiredIt());
	 * 
	 * list.add(bean1); logger.info(">>>>>>>>>>list size-------"+list.size());
	 * claimApp.setExpenseDtlList(list); }
	 */

	public void addExpenseDtl(TmsClaimApplication claimApp,
			HttpServletRequest request, String[] srNo, String[] expenseDateIt,
			String[] expenseTypeIt, String[] expenseTypeIdIt,
			String[] particularsIt, String[] expenseAmtIt, String[] proofIt,
			String[] proofRequiredIt, String expDtlId[], String expItId[],
			String eligibleAmtIt[], String proofName[], String ittproofName[],
			String[] itteratorProofNameForSave, String[] policyViolationTextIt, String[] currencyExpenseAmtItr) {
		try {
			ArrayList<Object> list = new ArrayList<Object>();

			if (expenseTypeIt != null) {
				for (int i = 0; i < expenseTypeIt.length; i++) {
					TmsClaimApplication bean = new TmsClaimApplication();
					bean.setSrNo(srNo[i]);
					bean.setExpDtlId(expDtlId[i]);
					bean.setExpItId(expItId[i]);
					bean.setExpenseDateIt(expenseDateIt[i]);
					bean.setExpenseTypeIt(expenseTypeIt[i]);
					bean.setExpenseTypeIdIt(expenseTypeIdIt[i]);
					bean.setParticularsIt(particularsIt[i]);
					bean.setEligibleAmtIt(Utility.twoDecimals(eligibleAmtIt[i]));
					bean.setExpenseAmtIt(Utility.twoDecimals(expenseAmtIt[i]));
					// bean.setProofIt(checkNull(proofIt[i]));
					bean.setProofRequiredIt(proofRequiredIt[i]);
					bean.setIttproofName(ittproofName[i]);
					bean.setItteratorProofNameForSave(itteratorProofNameForSave[i]);
					bean.setPolicyViolationTextIt(policyViolationTextIt[i]);
					bean.setCurrencyExpenseAmtItr(currencyExpenseAmtItr[i]);

					// logger.info("PolicyViolationTextIt :"+
					// bean.getPolicyViolationTextIt());

					if (itteratorProofNameForSave != null
							&& itteratorProofNameForSave.length > 0) {
						String[] innerproofName = itteratorProofNameForSave[i]
								.split(",");
						ArrayList<Object> innerlist = new ArrayList<Object>();
						for (int k = 0; k < innerproofName.length; k++) {
							TmsClaimApplication innerbean = new TmsClaimApplication();
							innerbean.setIttproofName(checkNull(innerproofName[k]));
							innerlist.add(innerbean);
						}
						bean.setIttUploadList(innerlist);
						claimApp.setTotalCurrencyExpense(currencyExpenseAmtItr[i]);
					}
					list.add(bean);
				}
			}
			TmsClaimApplication bean1 = new TmsClaimApplication();
			bean1.setSrNo(String.valueOf(list.size() + 1));
			bean1.setExpenseDateIt(checkNull(claimApp.getExpenseDate()));
			bean1.setExpenseTypeIt(checkNull(claimApp.getExpenseType()));
			bean1.setExpenseTypeIdIt(checkNull(claimApp.getExpenseTypeId()));
			bean1.setParticularsIt(checkNull(claimApp.getParticulars()));
			bean1.setPolicyViolationTextIt(checkNull(claimApp.getPolicyViolationText()));
			// logger.info("PolicyViolationText :" +
			// bean1.getPolicyViolationText());

			double totalAmountWithBillOrWithoutBill = 0.0d;
			if (claimApp.getAtActual().equals("At Actual")) {
				totalAmountWithBillOrWithoutBill = Double.parseDouble(claimApp.getExpenseAmt());
				bean1.setEligibleAmtIt(String.valueOf(totalAmountWithBillOrWithoutBill));
			} else {
				//if (claimApp.getProofRequired().equals("true")) {
				System.out.println("claimApp.getHiddenProofCnt() ::: "+claimApp.getHiddenProofCnt());
				if (Integer.parseInt(claimApp.getHiddenProofCnt())>0) {
					totalAmountWithBillOrWithoutBill = Double.parseDouble(claimApp.getAmountWithBill());
					bean1.setEligibleAmtIt(String.valueOf(totalAmountWithBillOrWithoutBill));
				} else {
					totalAmountWithBillOrWithoutBill = Double.parseDouble(claimApp.getAmountWithoutBill());
					bean1.setEligibleAmtIt(String.valueOf(totalAmountWithBillOrWithoutBill));
				}
			}
			bean1.setExpenseAmtIt(checkNull(Utility.twoDecimals(claimApp.getExpenseAmt())));
			bean1.setCurrencyExpenseAmtItr(claimApp.getCurrencyExpenseAmt());
			// bean1.setProofIt(checkNull(claimApp.getUploadLocFileName()));

			if (checkNull(claimApp.getProofRequired()).equals("Yes")) {
				bean1.setProofRequiredIt("Y");
			} else {
				bean1.setProofRequiredIt("N");
			}
			String proofNameValue = "";
			if (proofName != null && proofName.length > 0) {
				for (int i = 0; i < proofName.length; i++) {

					if (proofName.length - 1 == i) {
						proofNameValue += proofName[i];
					} else {
						proofNameValue += proofName[i] + ",";
					}
				}
			}
			String proofNameValueItt = proofNameValue;
			bean1.setItteratorProofNameForSave(proofNameValueItt);
			if (proofNameValueItt != null && proofNameValueItt.length() > 0) {
				String[] innerproofName = proofNameValueItt.split(",");
				ArrayList <Object> innerlist = new ArrayList<Object>();
				for (int k = 0; k < innerproofName.length; k++) {
					TmsClaimApplication innerbean = new TmsClaimApplication();
					innerbean.setIttproofName(checkNull(innerproofName[k]));
					innerlist.add(innerbean);
				}
				bean1.setIttUploadList(innerlist);
			}
			list.add(bean1);
			claimApp.setExpenseDtlList(list);
			/*
			if (claimApp.getExpenseDtlList().size() > 0) {

				claimApp.setExpTabLength("true");
			} else {
				claimApp.setExpTabLength("false");
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getReportingData(TmsClaimApplication bean) {
		ReportingModel model = new ReportingModel();
		model.initiate(context, session);
		Object rptObj[][] = model.generateEmpFlow(bean.getInitId(), "TYD", 1);// getReportingStructure(bean.getAppEmpId(),
		model.terminate();
	}

	public boolean save(TmsClaimApplication bean, String[] expenseDateIt,
			String[] expenseTypeIt, String[] expenseTypeIdIt,
			String[] particularsIt, String[] eligibleAmtIt,
			String[] expenseAmtIt, String[] proofIt, String[] proofRequiredIt,
			String buttonType, Object[][] rptObj,
			String[] itteratorProofNameForSave) {

		Object[][] add = null;
		Object[][] addObj = null;
		boolean result = false;
		Object maxCodeObj[][] = getSqlModel().getSingleResult(
				"SELECT NVL(MAX(EXP_APPID),0) FROM TMS_CLAIM_APPL");
		int maxCode = Integer.parseInt("" + maxCodeObj[0][0]);

		Object maxCodeObjDtl[][] = getSqlModel().getSingleResult(
				"SELECT NVL(MAX(EXP_DTLID),0) FROM TMS_EXP_DTL");
		int maxCodeDtl = Integer.parseInt("" + maxCodeObjDtl[0][0]);
		/*
		 * Object maxApplIdObj[][] = getSqlModel().getSingleResult( "SELECT
		 * NVL(MAX(APPL_ID),0) FROM TMS_APPLICATION"); int maxApplId =
		 * Integer.parseInt("" + maxApplIdObj[0][0]);
		 * 
		 * Object maxClaimApplIdObj[][] = getSqlModel().getSingleResult( "SELECT
		 * NVL(MAX(EXP_TRVL_APPID),0) FROM TMS_CLAIM_APPL"); int maxClaimApplId =
		 * Integer.parseInt("" + maxClaimApplIdObj[0][0]);
		 */
		/*
		 * if (maxApplId > maxClaimApplId) { maxId = maxApplId; } else if
		 * (maxClaimApplId > maxApplId) { maxId = maxClaimApplId; } else if
		 * (maxClaimApplId == maxApplId) { maxId = maxApplId; }
		 */
		// if (!bean.getBranchCode().equals("")) {
		add = new Object[1][20];
		add[0][0] = ++maxCode;
		add[0][1] = bean.getExpAppDateDraft();

		logger.info("==buttonType--" + buttonType);
		add[0][2] = buttonType;

		if (checkNull(bean.getAdvanceAmtTaken()).equals("")
				|| checkNull(bean.getAdvanceAmtTaken()).equals("null")) {
			add[0][3] = 0.00;
		} else {
			add[0][3] = checkNull(Utility
					.twoDecimals(bean.getAdvanceAmtTaken()));
		}
		add[0][4] = checkNull(bean.getEmployeeId());
		add[0][5] = checkNull(bean.getTrvlStartDate());
		add[0][6] = checkNull(bean.getTrvlEndDate());
		add[0][7] = checkNull(bean.getTravelPurposeId());
		add[0][8] = checkNull(bean.getTravelTypeId());
		add[0][9] = checkNull(bean.getComment());
		// add[0][10] = checkNull(bean.getTotExpAmount());
		// add[0][11] = checkNull(bean.getTotEligibleAmount());
		add[0][10] = checkNull(bean.getTotExpAmt());
		add[0][11] = checkNull(bean.getTotElgAmt());
		add[0][12] = ""; // appid
		add[0][13] = "1"; // appcode
		add[0][14] = checkNull(String.valueOf(rptObj[0][0]));// main approver
		add[0][15] = checkNull(String.valueOf(rptObj[0][3]));// Alternate
		// Approver
		add[0][16] = "N"; // App Type
		add[0][17] = checkNull(bean.getTrvlReqNameAddNew());
		add[0][18] = "TRVLCLM_" + maxCode + "_1";

		add[0][19] = checkNull(bean.getProjectId());
		add[0][20] = checkNull(bean.getCustomerId());

		result = getSqlModel().singleExecute(getQuery(3), add);

		if (result) {
			logger.info("--result---");
			String maxQuery = "SELECT MAX(EXP_APPID) FROM TMS_CLAIM_APPL";
			Object[][] data = getSqlModel().getSingleResult(maxQuery);
			bean.setClaimApplnCode(String.valueOf(data[0][0]));
			if (buttonType.equals("P")) {
				// save record in approval dtl table
				saveApprovalDtl(bean, rptObj);

			}
			int k = 0;
			addObj = new Object[expenseDateIt.length][9];
			if (expenseDateIt != null && expenseDateIt.length > 0) {
				logger.info("--expenseDateIt--len-" + expenseDateIt.length);
				for (int i = 0; i < expenseDateIt.length; i++) {

					addObj[k][0] = ++maxCodeDtl;
					logger.info("addObj[k][0]--" + addObj[k][0]);
					addObj[k][1] = checkNull(String.valueOf(data[0][0]));
					logger.info("addObj[k][1]--" + addObj[k][1]);
					addObj[k][2] = checkNull(String.valueOf(expenseDateIt[i]));
					logger.info("addObj[k][2]--" + addObj[k][2]);
					addObj[k][3] = checkNull(String.valueOf(expenseTypeIdIt[i]));
					logger.info("addObj[k][3]--" + addObj[k][3]);
					addObj[k][4] = checkNull(String.valueOf(eligibleAmtIt[i]));
					logger.info("addObj[k][4]--" + addObj[k][4]);
					addObj[k][5] = checkNull(String.valueOf(expenseAmtIt[i]));
					logger.info("addObj[k][5]--" + addObj[k][5]);
					addObj[k][6] = checkNull(String.valueOf(particularsIt[i]));
					logger.info("addObj[k][6]--" + addObj[k][6]);
					// addObj[k][7] = "N";
					addObj[k][7] = checkNull(String.valueOf(proofRequiredIt[i]));
					logger.info("addObj[k][7]--" + addObj[k][7]);
					addObj[k][8] = itteratorProofNameForSave[i];
					logger.info("hhghghg---" + addObj[k][8]);
					k++;
				}
				// String vsql="";
				result = getSqlModel().singleExecute(getQuery(4), addObj);
			}
		}
		// model.terminate();
		return result;
	}

	private void saveApprovalDtl_OLD(TmsClaimApplication bean, Object[][] rptObj) {
		Object[][] addObj = new Object[1][3];

		logger.info("Id------" + bean.getClaimApplnCode());
		logger.info(" rptObj[0][0]--------" + rptObj[0][0]);
		// logger.info("Id------"+bean.getClaimApplnCode());
		addObj[0][0] = bean.getClaimApplnCode();
		addObj[0][1] = rptObj[0][0];
		addObj[0][2] = "1";
		getSqlModel().singleExecute(getQuery(12), addObj);
	}

	private void saveApprovalDtl(TmsClaimApplication bean, Object[][] rptObj) {

	}

	public String checkNull(String result) {
		if (result == null || result.equals("null") || result.equals(" ")
				|| result.equals("")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}

	public void getDraftRecords(TmsClaimApplication claimApp,
			HttpServletRequest request, String stat, String empId) {
		Object draftData[][] = getSqlModel().getSingleResult(getQuery(5),
				new Object[] { empId });
		// Object draftData[][] = getSqlModel().getSingleResult(getQuery(5));

		String[] pageIndexDraft = org.paradyne.lib.Utility.doPaging(claimApp
				.getMyPageDraft(), draftData.length, 10);
		request.setAttribute("totalPageDraft", Integer.parseInt(String
				.valueOf(pageIndexDraft[2])));
		request.setAttribute("PageNoDraft", Integer.parseInt(String
				.valueOf(pageIndexDraft[3])));
		request.setAttribute("rowDraft", Integer.parseInt(String
				.valueOf(pageIndexDraft[0])));

		if (pageIndexDraft[4].equals("1"))
			claimApp.setMyPageDraft("1");

		ArrayList<TmsClaimApplication> draftList = new ArrayList<TmsClaimApplication>();
		if (draftData != null && draftData.length > 0) {
			for (int i = 0; i < draftData.length; i++) {
				TmsClaimApplication bean1 = new TmsClaimApplication();
				// logger.info("i="+i);

				bean1.setDraftExpAppId(checkNull(String
						.valueOf(draftData[i][0])));
				bean1
						.setTrvlIdDraft(checkNull(String
								.valueOf(draftData[i][1])));
				bean1
						.setEmpNameDraft(checkNull(String
								.valueOf(draftData[i][2])));
				bean1.setTrvlReqNameDraft(checkNull(String
						.valueOf(draftData[i][3])));
				bean1.setAppEndDateDraft(checkNull(String
						.valueOf(draftData[i][4])));
				bean1.setAppIdDraft(checkNull(String.valueOf(draftData[i][5])));
				bean1
						.setAppCodeDraft(checkNull(String
								.valueOf(draftData[i][6])));
				bean1.setAppStatusDraft(checkNull(String
						.valueOf(draftData[i][7])));
				bean1.setEmpIdDraft(checkNull(String.valueOf(draftData[i][8])));

				/*
				 * Object data[][] = getSqlModel().getSingleResult(getQuery(70),
				 * new Object[] { bean1.getAppId(), bean1.getAppCode() });
				 */

				draftList.add(bean1);
			}
		}

		claimApp.setDraftAppList(draftList);

	}

	public void getSubmitRecords(TmsClaimApplication claimApp,
			HttpServletRequest request, String stat, String empId) {
		Object submitedData[][] = getSqlModel().getSingleResult(getQuery(5),
				new Object[] { empId });
		// Object draftData[][] = getSqlModel().getSingleResult(getQuery(5));

		// String[] pageIndexSubmit = org.paradyne.lib.Utility.doPaging(claimApp
		// .getMyPageSubmit(), submitedData.length, 10);
		// request.setAttribute("totalPageSubmit", Integer.parseInt(String
		// .valueOf(pageIndexSubmit[2])));
		// request.setAttribute("PageNoSubmit", Integer.parseInt(String
		// .valueOf(pageIndexSubmit[3])));
		// request.setAttribute("rowSubmit", Integer.parseInt(String
		// .valueOf(pageIndexSubmit[0])));
		//
		// if (pageIndexSubmit[4].equals("1"))
		// claimApp.setMyPageDraft("1");

		ArrayList<TmsClaimApplication> submitList = new ArrayList<TmsClaimApplication>();
		if (submitedData != null && submitedData.length > 0) {
			for (int i = 0; i < submitedData.length; i++) {
				TmsClaimApplication bean1 = new TmsClaimApplication();
				// logger.info("i="+i);
				bean1.setSubmitExpAppId(checkNull(String
						.valueOf(submitedData[i][0])));
				bean1.setTrvlIdSubmit(checkNull(String
						.valueOf(submitedData[i][1])));
				bean1.setEmpNameSubmit(checkNull(String
						.valueOf(submitedData[i][2])));
				bean1.setTrvlReqNameSubmit(checkNull(String
						.valueOf(submitedData[i][3])));
				bean1.setAppEndDateSubmit(checkNull(String
						.valueOf(submitedData[i][4])));
				bean1.setAppIdSubmit(checkNull(String
						.valueOf(submitedData[i][5])));
				bean1.setAppCodeSubmit(checkNull(String
						.valueOf(submitedData[i][6])));
				bean1.setAppStatusSubmit(checkNull(String
						.valueOf(submitedData[i][7])));
				bean1.setEmpIdSubmit(checkNull(String
						.valueOf(submitedData[i][8])));
				
				if(String
						.valueOf(submitedData[i][10]).equals("_F")){
					bean1.setPendingStatus("Revoked");
				}
				else{
					bean1.setPendingStatus(checkNull(String
							.valueOf(submitedData[i][10])));
				}
				// bean1.setInitName("NA");

				/*
				 * Object data[][] = getSqlModel().getSingleResult(getQuery(70),
				 * new Object[] { bean1.getAppId(), bean1.getAppCode() });
				 */
				submitList.add(bean1);
			}
		}
		claimApp.setSubmitAppList(submitList);

	}

	public void trvlClaimDraftData(TmsClaimApplication claimApp,
			HttpServletRequest request) {
		try {
			draftEmpDtl(claimApp);
			draftExpenseDtl(claimApp);
			setParams4BookingDtls(claimApp, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setParams4BookingDtls(TmsClaimApplication claimApp,
			HttpServletRequest request) {

		try {
			String bParamsQuery = "SELECT EMP_TYPE.APPL_ID,EMP_TYPE.APPL_CODE,APPL_INITIATOR,APPL_EMP_CODE,TO_CHAR(APPL_DATE,'DD-MM-YYYY'),APPL_FOR_FLAG"
					+ "	FROM TMS_APPLICATION"
					+ "	INNER JOIN TMS_APP_EMPDTL EMP_TYPE ON(TMS_APPLICATION.APPL_ID=EMP_TYPE.APPL_ID)"
					+ "	WHERE EMP_TYPE.APPL_ID="
					+ claimApp.getApplnId()
					+ " AND EMP_TYPE.APPL_CODE="
					+ claimApp.getApplnCode()
					+ "	UNION"
					+ "	SELECT GUEST_TYPE.APPL_ID,GUEST_TYPE.APPL_CODE,APPL_INITIATOR,0,TO_CHAR(APPL_DATE,'DD-MM-YYYY'),APPL_FOR_FLAG"
					+ "	FROM TMS_APPLICATION"
					+ "	INNER JOIN TMS_APP_GUEST_DTL GUEST_TYPE ON(TMS_APPLICATION.APPL_ID=GUEST_TYPE.APPL_ID)"
					+ "	WHERE GUEST_TYPE.APPL_ID="
					+ claimApp.getApplnId()
					+ " AND GUEST_TYPE.APPL_CODE=" + claimApp.getApplnCode();

			Object[][] bParamsData = getSqlModel()
					.getSingleResult(bParamsQuery);
			if (bParamsData != null && bParamsData.length > 0) {
				claimApp.setBDtlAppId(checkNull(String
						.valueOf(bParamsData[0][0])));
				claimApp.setBDtlAppCode(checkNull(String
						.valueOf(bParamsData[0][1])));
				claimApp.setBDtlInitrId(checkNull(String
						.valueOf(bParamsData[0][2])));
				claimApp.setBDtlEmpId(checkNull(String
						.valueOf(bParamsData[0][3])));
				claimApp.setBDtlAppDate(checkNull(String
						.valueOf(bParamsData[0][4])));
				claimApp.setBDtlForFlag(checkNull(String
						.valueOf(bParamsData[0][5])));

			}
		} catch (RuntimeException e) {
			logger.error(e);
		}

	}

	private void draftExpenseDtl(TmsClaimApplication claimApp) {
		try {
			Object DraftExpData[][] = getSqlModel().getSingleResult(
					getQuery(7), new Object[] { claimApp.getClaimApplnCode() });
			ArrayList<TmsClaimApplication> list = new ArrayList<TmsClaimApplication>();
			if (DraftExpData != null && DraftExpData.length > 0) {
				for (int i = 0; i < DraftExpData.length; i++) {
					TmsClaimApplication bean1 = new TmsClaimApplication();
					bean1.setExpenseDateIt(checkNull(String
							.valueOf(DraftExpData[i][0])));
					bean1.setExpenseTypeIdIt(checkNull(String
							.valueOf(DraftExpData[i][1])));
					bean1.setEligibleAmtIt(checkNull(String.valueOf(Utility
							.twoDecimals(String.valueOf(DraftExpData[i][2])))));
					bean1.setExpenseAmtIt(checkNull(String.valueOf(Utility
							.twoDecimals(String.valueOf(DraftExpData[i][3])))));
					bean1.setParticularsIt(checkNull(String
							.valueOf(DraftExpData[i][4])));
					bean1.setExpenseTypeIt(checkNull(String
							.valueOf(DraftExpData[i][5])));
					bean1.setProofRequiredIt(checkNull(String
							.valueOf(DraftExpData[i][6])));
					bean1.setIttproofName(checkNull(String
							.valueOf(DraftExpData[i][7])));
					bean1.setExpDtlId(checkNull(String
							.valueOf(DraftExpData[i][8])));
					bean1.setExpItId(checkNull(String
							.valueOf(DraftExpData[i][9])));
					bean1.setPolicyViolationTextIt(checkNull(String.valueOf(DraftExpData[i][10])));
					bean1.setCurrencyExpenseAmtItr(checkNull(String.valueOf(DraftExpData[i][11])));
					if (claimApp.getApplnStatus().equals("P")) {
						bean1.setSubmitFlag("true");
					}
					String proofNameValue = String.valueOf(DraftExpData[i][7]);
					bean1.setItteratorProofNameForSave(proofNameValue);

					if (proofNameValue != null && proofNameValue.length() > 0) {
						String[] innerproofName = proofNameValue.split(",");
						ArrayList <Object> innerlist = new ArrayList <Object>();
						for (int k = 0; k < innerproofName.length; k++) {
							TmsClaimApplication innerbean = new TmsClaimApplication();
							innerbean.setIttproofName(checkNull(String
									.valueOf(innerproofName[k])));
							innerlist.add(innerbean);
						}
						bean1.setIttUploadList(innerlist);
					}
					list.add(bean1);
				}
			}
			claimApp.setExpenseDtlList(list);
			if (claimApp.getExpenseDtlList().size() > 0) {
				claimApp.setExpTabLength("true");
			} else {
				claimApp.setExpTabLength("false");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception Occurred============>" + e);
		}
	}

	/*
	 * private void draftExpenseDtl123(TmsClaimApplication claimApp) {
	 * logger.info("--claimApp.getClaimApplnCode()--" +
	 * claimApp.getClaimApplnCode()); Object DraftExpData[][] =
	 * getSqlModel().getSingleResult(getQuery(7), new Object[] {
	 * claimApp.getClaimApplnCode() }); ArrayList<TmsClaimApplication> list =
	 * new ArrayList<TmsClaimApplication>(); if (DraftExpData != null &&
	 * DraftExpData.length > 0) { for (int i = 0; i < DraftExpData.length; i++) {
	 * TmsClaimApplication bean1 = new TmsClaimApplication();
	 * bean1.setExpenseDateIt(checkNull(String .valueOf(DraftExpData[i][0])));
	 * bean1.setExpenseTypeIdIt(checkNull(String .valueOf(DraftExpData[i][1])));
	 * bean1.setEligibleAmtIt(checkNull(String .valueOf(DraftExpData[i][2])));
	 * bean1.setExpenseAmtIt(checkNull(String .valueOf(DraftExpData[i][3])));
	 * bean1.setParticularsIt(checkNull(String .valueOf(DraftExpData[i][4])));
	 * bean1.setExpenseTypeIt(checkNull(String .valueOf(DraftExpData[i][5]))); //
	 * bean1.setProofIt(checkNull(String.valueOf(DraftExpData[i][7])));
	 * 
	 * bean1 .setExpDtlId(checkNull(String .valueOf(DraftExpData[i][8])));
	 * bean1.setExpItId(checkNull(String.valueOf(DraftExpData[i][9])));
	 * list.add(bean1); } } claimApp.setExpenseDtlList(list); if
	 * (claimApp.getExpenseDtlList().size() > 0) {
	 * claimApp.setExpTabLength("true"); } else {
	 * claimApp.setExpTabLength("false"); } }
	 */

	public void draftEmpDtl(TmsClaimApplication claimApp) {
		Object appDraftData[][] = getSqlModel().getSingleResult(getQuery(6),
				new Object[] { claimApp.getClaimApplnCode() });
		if (appDraftData != null && appDraftData.length > 0) {
			claimApp.setClaimApplnCode(checkNull(String.valueOf(appDraftData[0][0])));
			claimApp.setEmployeeName(checkNull(String.valueOf(appDraftData[0][1])));
			claimApp.setExpAppDateDraft(checkNull(String.valueOf(appDraftData[0][2])));
			claimApp.setApplnStatus(checkNull(String.valueOf(appDraftData[0][3])));
			claimApp.setAdvanceAmtTaken(checkNull(String.valueOf(Utility.twoDecimals(String.valueOf(appDraftData[0][4])))));
			claimApp.setTrvlStartDate(checkNull(String.valueOf(appDraftData[0][6])));
			claimApp.setTrvlEndDate(checkNull(String.valueOf(appDraftData[0][7])));
			claimApp.setTravelPurposeId(checkNull(String.valueOf(appDraftData[0][8])));
			claimApp.setTravelPurpose(checkNull(String.valueOf(appDraftData[0][9])));
			claimApp.setTravelTypeId(checkNull(String.valueOf(appDraftData[0][10])));
			claimApp.setTravelType(checkNull(String.valueOf(appDraftData[0][11])));
			claimApp.setComment(checkNull(String.valueOf(appDraftData[0][12])));
			claimApp.setTotExpAmount(checkNull(String.valueOf(appDraftData[0][13])));
			claimApp.setTotEligibleAmount(checkNull(String.valueOf(appDraftData[0][14])));
			claimApp.setTmsExpType(checkNull(String.valueOf(appDraftData[0][17])));
			claimApp.setTrvlReqNameAddNew(checkNull(String.valueOf(appDraftData[0][19])));
			claimApp.setBranch(checkNull(String.valueOf(appDraftData[0][20])));
			claimApp.setBranchId(checkNull(String.valueOf(appDraftData[0][21])));
			claimApp.setDept(checkNull(String.valueOf(appDraftData[0][22])));
			claimApp.setDesg(checkNull(String.valueOf(appDraftData[0][23])));
			claimApp.setGradId(checkNull(String.valueOf(appDraftData[0][24])));
			claimApp.setGrad(checkNull(String.valueOf(appDraftData[0][25])));
			claimApp.setProjectId(checkNull(String.valueOf(appDraftData[0][26])));
			claimApp.setCustomerId(checkNull(String.valueOf(appDraftData[0][27])));
			claimApp.setProject(checkNull(String.valueOf(appDraftData[0][28])));
			claimApp.setCustomerName(checkNull(String.valueOf(appDraftData[0][29])));
			claimApp.setSourceDestination(checkNull(String.valueOf(appDraftData[0][30])));
			claimApp.setCurrencyEmployeeAdvance(checkNull(String.valueOf(appDraftData[0][31])));

			String query = " SELECT NVL(TOUR_REPORT_COMMENTS,''), NVL(TOUR_REPORT_PROOF,''), NVL(ACHIEVEMENT_COMMENTS,''), NVL(FOLLOWUP_COMMENTS,''), "
					+ " TO_CHAR(FOLLOWUP_DATE,'DD-MM-YYYY')"
					+ " FROM TMS_CLAIM_APPL "
					+ " WHERE EXP_APPID="
					+ claimApp.getClaimApplnCode();

			Object data[][] = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				claimApp.setTourComments(checkNull(String.valueOf(data[0][0])));
				claimApp.setTourReportFile(checkNull(String.valueOf(data[0][1])));
				claimApp.setAchievementComments(checkNull(String.valueOf(data[0][2])));
				claimApp.setFollowUpComments(checkNull(String.valueOf(data[0][3])));
				claimApp.setTargetDate(checkNull(String.valueOf(data[0][4])));
			}
		}
	}

	public boolean updateStatus(TmsClaimApplication claimApp) {
		boolean result = false;
		Object updateParam[][] = new Object[1][1];
		updateParam[0][0] = claimApp.getClaimApplnCode();
		result = getSqlModel().singleExecute(getQuery(8), updateParam);
		return result;
	}

	public boolean update(TmsClaimApplication claimApp, String[] expenseDateIt,
			String[] expenseTypeIt, String[] expenseTypeIdIt,
			String[] particularsIt, String[] eligibleAmtIt,
			String[] expenseAmtIt, String[] proofIt, String[] proofRequiredIt,
			String buttonType, Object[][] rptObj,
			String[] itteratorProofNameForSave, String[] policyViolationTextIt, String[] currencyExpenseAmtItr) {
		boolean result = false;
		try {
			Object maxCodeObjDtl[][] = getSqlModel().getSingleResult(
					"SELECT NVL(MAX(EXP_DTLID),0) FROM TMS_EXP_DTL");
			int maxCodeDtl = Integer.parseInt("" + maxCodeObjDtl[0][0]);
			Object deleteParam[][] = new Object[1][1];
			deleteParam[0][0] = claimApp.getClaimApplnCode();
			result = getSqlModel().singleExecute(getQuery(9), deleteParam);
			if (result) {
				if (buttonType.equals("P")) {
					// save record in approval dtl table
					saveApprovalDtl(claimApp, rptObj);
				}

				Object updateParam[][] = new Object[1][7];
				updateParam[0][0] = checkNull(claimApp.getComment());
				updateParam[0][1] = checkNull(claimApp.getTotExpAmt());
				updateParam[0][2] = checkNull(claimApp.getTotElgAmt());
				updateParam[0][3] = buttonType;
				updateParam[0][4] = checkNull(String.valueOf(rptObj[0][0]));
				updateParam[0][5] =checkNull(String.valueOf(rptObj[0][3]));
				updateParam[0][6] = checkNull(claimApp.getClaimApplnCode());
				result = getSqlModel().singleExecute(getQuery(10), updateParam);

				String query = " select EXP_APP_ADMIN_STATUS from TMS_CLAIM_APPL where EXP_APPID=" + claimApp.getClaimApplnCode();

				Object claimstatusObj[][] = getSqlModel().getSingleResult(query);

				if (claimstatusObj != null && claimstatusObj.length > 0) {
					if (String.valueOf(claimstatusObj[0][0]).equals("B")
							& claimApp.getApplnStatus().equals("A")) {
						String updateQuery = " update TMS_CLAIM_APPL  set EXP_APP_ADMIN_STATUS='P' where "
								+ " EXP_APPID=" + claimApp.getClaimApplnCode();
						getSqlModel().singleExecute(updateQuery);

					}
				}

				if (result) {
					int k = 0;
					Object[][] addObj = null;
					addObj = new Object[expenseDateIt.length][11];
					if (expenseDateIt != null && expenseDateIt.length > 0) {
						for (int i = 0; i < expenseDateIt.length; i++) {
							addObj[k][0] = ++maxCodeDtl;
							addObj[k][1] = claimApp.getClaimApplnCode();
							addObj[k][2] = String.valueOf(checkNull(expenseDateIt[i]));
							addObj[k][3] = String.valueOf(checkNull(expenseTypeIdIt[i]));
							addObj[k][4] = String.valueOf(checkNull((eligibleAmtIt[i])));
							addObj[k][5] = String.valueOf(checkNull((expenseAmtIt[i])));
							addObj[k][6] = String.valueOf(checkNull(particularsIt[i]));

							if (claimApp.getProofRequiredIt().equals("true")) {
								addObj[k][7] = "Y";
							} else {
								addObj[k][7] = "N";
							}
							addObj[k][8] = checkNull(String.valueOf(itteratorProofNameForSave[i])); 
							addObj[k][9] = String.valueOf(policyViolationTextIt[i]).equals("No") ? "N" : "Y";
							addObj[k][10] = checkNull(String.valueOf(currencyExpenseAmtItr[i]));
							k++;
						}
						result = getSqlModel().singleExecute(getQuery(4), addObj);
						draftExpenseDtl(claimApp);
					}
				}

			}

			/**
			 * For rating updates
			 */

			try {
				if (buttonType.equals("P"))
				{
					TravelProcessModel processMod = new TravelProcessModel();
					processMod.initiate(context, session);
					processMod.updateHotelRating(claimApp.getClaimApplnCode());
					processMod.terminate();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception occurred ============>" + e);

		}
		return result;
	}

	public void getReturnRecords(TmsClaimApplication claimApp,
			HttpServletRequest request, String stat, String empId) {
		Object returnData[][] = getSqlModel().getSingleResult(getQuery(5),
				new Object[] { empId });
		if (returnData != null && returnData.length > 0) {
			logger.info("==returnData.length==" + returnData.length);
		}

		String[] pageIndexReturn = org.paradyne.lib.Utility.doPaging(claimApp
				.getMyPageReturn(), returnData.length, 10);
		request.setAttribute("totalPageReturn", Integer.parseInt(String
				.valueOf(pageIndexReturn[2])));
		request.setAttribute("PageNoReturn", Integer.parseInt(String
				.valueOf(pageIndexReturn[3])));
		request.setAttribute("rowReturn", Integer.parseInt(String
				.valueOf(pageIndexReturn[0])));

		if (pageIndexReturn[4].equals("1"))
			claimApp.setMyPageReturn("1");

		ArrayList<TmsClaimApplication> returnList = new ArrayList<TmsClaimApplication>();
		if (returnData != null && returnData.length > 0) {
			for (int i = Integer.parseInt(pageIndexReturn[0]); i < Integer
					.parseInt(pageIndexReturn[1]); i++) {
				TmsClaimApplication bean1 = new TmsClaimApplication();
				// logger.info("i="+i);

				bean1.setReturnExpAppId(checkNull(String
						.valueOf(returnData[i][0])));
				bean1.setTrvlIdReturn(checkNull(String
						.valueOf(returnData[i][1])));
				bean1.setEmpNameReturn(checkNull(String
						.valueOf(returnData[i][2])));
				bean1.setTrvlReqNameReturn(checkNull(String
						.valueOf(returnData[i][3])));
				bean1.setAppEndDateReturn(checkNull(String
						.valueOf(returnData[i][4])));
				bean1
						.setAppIdReturn(checkNull(String
								.valueOf(returnData[i][5])));
				bean1.setAppCodeReturn(checkNull(String
						.valueOf(returnData[i][6])));
				bean1.setAppStatusReturn(checkNull(String
						.valueOf(returnData[i][7])));
				bean1
						.setEmpIdReturn(checkNull(String
								.valueOf(returnData[i][8])));

				/*
				 * Object data[][] = getSqlModel().getSingleResult(getQuery(70),
				 * new Object[] { bean1.getAppId(), bean1.getAppCode() });
				 */

				returnList.add(bean1);
			}
		}

		claimApp.setReturnAppList(returnList);

	}

	public void setPreviousExpenseDtl(TmsClaimApplication claimApp,
			HttpServletRequest request, String[] srNo, String[] expenseDateIt,
			String[] expenseTypeIt, String[] expenseTypeIdIt,
			String[] particularsIt, String[] eligibleAmtIt,
			String[] expenseAmtIt, String[] proofIt, String[] proofRequiredIt,
			String expDtlId[], String expItId[], String[] ittproofName,
			String[] itteratorProofNameForSave, String[] policyViolationTextIt, String[] currencyExpenseAmtItr) {

		ArrayList<Object> list = new ArrayList<Object>();

		if (expenseTypeIt != null) {
			for (int i = 0; i < expenseTypeIt.length; i++) {
				TmsClaimApplication bean = new TmsClaimApplication();
				bean.setSrNo(checkNull(srNo[i]));
				bean.setExpDtlId(checkNull(expDtlId[i]));
				bean.setExpItId(checkNull(expItId[i]));
				bean.setExpenseDateIt(checkNull(expenseDateIt[i]));
				bean.setExpenseTypeIt(checkNull(expenseTypeIt[i]));
				bean.setExpenseTypeIdIt(checkNull(expenseTypeIdIt[i]));
				bean.setParticularsIt(checkNull(particularsIt[i]));
				bean.setPolicyViolationTextIt(checkNull(String.valueOf(policyViolationTextIt[i])));
				bean.setEligibleAmtIt(checkNull(Utility.twoDecimals(eligibleAmtIt[i])));
				bean.setExpenseAmtIt(checkNull(Utility.twoDecimals(expenseAmtIt[i])));
				bean.setItteratorProofNameForSave(checkNull(String.valueOf(itteratorProofNameForSave[i])));
				bean.setCurrencyExpenseAmtItr(checkNull(String.valueOf(currencyExpenseAmtItr[i])));

				if (itteratorProofNameForSave != null
						&& itteratorProofNameForSave.length > 0) {
					String[] innerproofName = itteratorProofNameForSave[i].split(",");
					ArrayList <Object> innerlist = new ArrayList <Object>();
					for (int k = 0; k < innerproofName.length; k++) {
						TmsClaimApplication innerbean = new TmsClaimApplication();
						innerbean.setIttproofName(checkNull(String.valueOf(innerproofName[k])));
						innerlist.add(innerbean);
					}
					bean.setIttUploadList(innerlist);
				}
				list.add(bean);
			}
		}
		claimApp.setExpenseDtlList(list);
	}

	public void setExpRemDtls(TmsClaimApplication claimApp) {

		draftEmpDtl(claimApp);
	}

	public boolean removeExpDtls(TmsClaimApplication bean,
			HttpServletRequest request) {
		// JOURNEY DETAILS
		String expDtlId[] = request.getParameterValues("expDtlId");
		String expId[] = request.getParameterValues("expItId");
		String expenseDateIt[] = request.getParameterValues("expenseDateIt");
		String expenseTypeIt[] = request.getParameterValues("expenseTypeIt");
		String particularsIt[] = request.getParameterValues("particularsIt");
		String eligibleAmtIt[] = request.getParameterValues("eligibleAmtIt");
		String expenseAmtIt[] = request.getParameterValues("expenseAmtIt");
		String proofNameItt[] = request.getParameterValues("ittproofName");
		String proofRequiredIt[] = request.getParameterValues("proofRequiredIt");
		String expFlag[] = request.getParameterValues("expFlag");// CHECK
		String[] expenseTypeIdIt = request.getParameterValues("expenseTypeIdIt");
		String removeData = request.getParameter("removeData");
		String[] itteratorProofNameForSave = request.getParameterValues("itteratorProofNameForSave");
		String[] policyViolationTextIt = request.getParameterValues("policyViolationTextIt");
		String currencyExpenseAmtItr[] = request.getParameterValues("currencyExpenseAmtItr");
		if (expDtlId != null && expDtlId.length > 0) {
			ArrayList<TmsClaimApplication> list = new ArrayList<TmsClaimApplication>();
			for (int i = 0; i < expDtlId.length; i++) {
				TmsClaimApplication bean1 = new TmsClaimApplication();
				// TO BE DELETED
				if (expFlag[i].equals("Y")) {
					// STATIC RECORD
					if (expId[i].trim().equals("")) {
						continue;
					} else {// EXISTING RECORD
						// remove from the database
						// delExp(expDtlId[i], expId[i]);
						removeData += expDtlId[i] + "#" + expId[i];
					}
				} else {// TO BE ADDED IN LIST
					bean1.setExpItId(checkNull(expId[i]));
					bean1.setExpDtlId(checkNull(expDtlId[i]));
					bean1.setExpenseDateIt(checkNull(expenseDateIt[i]));
					bean1.setExpenseTypeIt(checkNull(expenseTypeIt[i]));
					bean1.setExpenseTypeIdIt(checkNull(expenseTypeIdIt[i]));
					bean1.setParticularsIt(checkNull(particularsIt[i]));
					bean1.setEligibleAmtIt(checkNull(new Utility().twoDecimals(eligibleAmtIt[i])));
					bean1.setExpenseAmtIt(checkNull(new Utility().twoDecimals(expenseAmtIt[i])));
					bean1.setProofRequiredIt(checkNull(proofRequiredIt[i]));
					bean1.setPolicyViolationTextIt(checkNull(policyViolationTextIt[i]));
					// setProof(proofName, bean1);
					bean1.setItteratorProofNameForSave(itteratorProofNameForSave[i]);
					bean1.setCurrencyExpenseAmtItr(currencyExpenseAmtItr[i]);
					bean.setTotalCurrencyExpense(currencyExpenseAmtItr[0]);
					if (itteratorProofNameForSave != null && itteratorProofNameForSave.length > 0) {
						String[] innerproofName = itteratorProofNameForSave[i].split(",");
						ArrayList <Object> innerlist = new ArrayList <Object> ();
						for (int k = 0; k < innerproofName.length; k++) {
							TmsClaimApplication innerbean = new TmsClaimApplication();
							innerbean.setIttproofName(checkNull(innerproofName[k]));
							innerlist.add(innerbean);
						}
						bean1.setIttUploadList(innerlist);
					}
					list.add(bean1);
				}
			}
			bean.setRemoveData(removeData);
			bean.setExpenseDtlList(list);
			if (bean.getExpenseDtlList().size() > 0) {
				bean.setExpTabLength("true");
			} else {
				bean.setExpTabLength("false");
			}
		}
		return true;
	}

	private void delExp(String expDtlId, String expClmId) {

		String delQuery = "DELETE FROM TMS_EXP_DTL WHERE EXP_DTLID="
				+ Integer.parseInt(expDtlId) + " AND EXP_APPID="
				+ Integer.parseInt(expClmId);

		getSqlModel().singleExecute(delQuery);

	}

	public void getApprovedList(TmsClaimApplication claimApp,
			HttpServletRequest request, String stat, String empId) {

		Object approveData[][] = getSqlModel().getSingleResult(getQuery(15),
				new Object[] { empId });
		// Object draftData[][] = getSqlModel().getSingleResult(getQuery(5));

		String[] pageIndexApprove = org.paradyne.lib.Utility.doPaging(claimApp
				.getMyPageApproved(), approveData.length, 10);
		request.setAttribute("totalPageApprove", Integer.parseInt(String
				.valueOf(pageIndexApprove[2])));
		request.setAttribute("PageNoApprove", Integer.parseInt(String
				.valueOf(pageIndexApprove[3])));
		request.setAttribute("rowApprove", Integer.parseInt(String
				.valueOf(pageIndexApprove[0])));

		if (pageIndexApprove[4].equals("1"))
			claimApp.setMyPageApproved("1");

		ArrayList<TmsClaimApplication> approveList = new ArrayList<TmsClaimApplication>();
		if (approveData != null && approveData.length > 0) {
			for (int i = Integer.parseInt(pageIndexApprove[0]); i < Integer
					.parseInt(pageIndexApprove[1]); i++) {
				TmsClaimApplication bean1 = new TmsClaimApplication();
				// logger.info("i="+i);

				bean1.setApproveExpAppId(checkNull(String
						.valueOf(approveData[i][0])));
				bean1.setTrvlIdApprove(checkNull(String
						.valueOf(approveData[i][1])));
				bean1.setEmpNameApprove(checkNull(String
						.valueOf(approveData[i][2])));
				bean1.setTrvlReqNameApprove(checkNull(String
						.valueOf(approveData[i][3])));
				bean1.setAppEndDateApprove(checkNull(String
						.valueOf(approveData[i][4])));
				bean1.setAppIdApprove(checkNull(String
						.valueOf(approveData[i][5])));
				bean1.setAppCodeApprove(checkNull(String
						.valueOf(approveData[i][6])));
				bean1.setStatus(checkNull(String.valueOf(approveData[i][7])));
				bean1.setEmpIdApprove(checkNull(String
						.valueOf(approveData[i][8])));

				/*
				 * Object data[][] = getSqlModel().getSingleResult(getQuery(70),
				 * new Object[] { bean1.getAppId(), bean1.getAppCode() });
				 */

				approveList.add(bean1);
			}
		}

		claimApp.setApproveAppList(approveList);

	}

	public void getClosedList(TmsClaimApplication claimApp,
			HttpServletRequest request, String stat, String empId) {
		Object closeData[][] = getSqlModel().getSingleResult(getQuery(5),
				new Object[] { empId });
		// Object draftData[][] = getSqlModel().getSingleResult(getQuery(5));

		String[] pageIndexClose = org.paradyne.lib.Utility.doPaging(claimApp
				.getMyPageClose(), closeData.length, 10);
		request.setAttribute("totalPageClose", Integer.parseInt(String
				.valueOf(pageIndexClose[2])));
		request.setAttribute("PageNoClose", Integer.parseInt(String
				.valueOf(pageIndexClose[3])));
		request.setAttribute("rowClose", Integer.parseInt(String
				.valueOf(pageIndexClose[0])));

		if (pageIndexClose[4].equals("1"))
			claimApp.setMyPageClose("1");

		ArrayList<TmsClaimApplication> CloseList = new ArrayList<TmsClaimApplication>();
		if (closeData != null && closeData.length > 0) {
			for (int i = Integer.parseInt(pageIndexClose[0]); i < Integer
					.parseInt(pageIndexClose[1]); i++) {
				TmsClaimApplication bean1 = new TmsClaimApplication();
				// logger.info("i="+i);

				bean1.setCloseExpAppId(checkNull(String
						.valueOf(closeData[i][0])));
				bean1
						.setTrvlIdClose(checkNull(String
								.valueOf(closeData[i][1])));
				bean1
						.setEmpNameClose(checkNull(String
								.valueOf(closeData[i][2])));
				bean1.setTrvlReqNameClose(checkNull(String
						.valueOf(closeData[i][3])));
				bean1.setAppEndDateClose(checkNull(String
						.valueOf(closeData[i][4])));
				bean1.setAppIdClose(checkNull(String.valueOf(closeData[i][5])));
				bean1
						.setAppCodeClose(checkNull(String
								.valueOf(closeData[i][6])));
				bean1.setAppStatusClose(checkNull(String
						.valueOf(closeData[i][7])));
				bean1.setEmpIdClose(checkNull(String.valueOf(closeData[i][8])));

				/*
				 * Object data[][] = getSqlModel().getSingleResult(getQuery(70),
				 * new Object[] { bean1.getAppId(), bean1.getAppCode() });
				 */

				CloseList.add(bean1);
			}
		}

		claimApp.setCloseAppList(CloseList);

	}

	public void getSchTrvlClaimDtl(TmsClaimApplication claimApp,
			String applnEmpId, String applnInitId) {
		Object empData[][] = null;

		if (claimApp.getApplnFor().equals("G")) {
			empData = getSqlModel().getSingleResult(
					getQuery(13),
					new Object[] { applnInitId, claimApp.getApplnId(),
							claimApp.getApplnCode() });
		} else {
			empData = getSqlModel().getSingleResult(
					getQuery(11),
					new Object[] { applnEmpId, claimApp.getApplnId(),
							claimApp.getApplnCode() });
		}
		if (empData != null && empData.length > 0) {
			claimApp.setEmployeeToken(checkNull(String.valueOf(empData[0][0])));
			claimApp.setEmployeeName(checkNull(String.valueOf(empData[0][1])));
			claimApp.setExpAppDateDraft(checkNull(String.valueOf(empData[0][2])));
			claimApp.setTrvlStartDate(checkNull(String.valueOf(empData[0][3])));
			claimApp.setTrvlEndDate(checkNull(String.valueOf(empData[0][4])));
			claimApp.setEmployeeId(checkNull(String.valueOf(empData[0][5])));
			claimApp.setTrvlReqNameAddNew(checkNull(String.valueOf(empData[0][6])));
			claimApp.setAdvanceAmtTaken(checkNull(String.valueOf(Utility
					.twoDecimals(String.valueOf(empData[0][7])))));
			claimApp.setTravelPurpose(checkNull(String.valueOf(empData[0][8])));
			claimApp.setTravelPurposeId(checkNull(String.valueOf(empData[0][9])));
			claimApp.setTravelTypeId(checkNull(String.valueOf(empData[0][10])));
			claimApp.setTravelType(checkNull(String.valueOf(empData[0][11])));
			claimApp.setBranch(checkNull(String.valueOf(empData[0][12])));
			claimApp.setDept(checkNull(String.valueOf(empData[0][13])));
			claimApp.setDesg(checkNull(String.valueOf(empData[0][14])));
			claimApp.setGrad(checkNull(String.valueOf(empData[0][15])));
			claimApp.setGradId(checkNull(String.valueOf(empData[0][16])));
			claimApp.setProjectId(checkNull(String.valueOf(empData[0][21])));
			claimApp.setCustomerId(checkNull(String.valueOf(empData[0][22])));
			claimApp.setProject(checkNull(String.valueOf(empData[0][23])));
			claimApp.setCustomerName(checkNull(String.valueOf(empData[0][24])));
			claimApp.setSourceDestination(checkNull(String.valueOf(empData[0][25])));
			claimApp.setCurrencyEmployeeAdvance(checkNull(String.valueOf(empData[0][26])));
		}
	}

	public boolean saveSch(TmsClaimApplication bean, String[] expenseDateIt,
			String[] expenseTypeIt, String[] expenseTypeIdIt,
			String[] particularsIt, String[] eligibleAmtIt,
			String[] expenseAmtIt, String[] proofIt, String[] proofRequiredIt,
			String buttonType, Object[][] rptObj,
			String[] itteratorProofNameForSave, String[] policyViolationTextIt, String[] currencyExpenseAmtItr) {

		boolean saveHdrresult = false;
		boolean saveDtlresult = false;
		boolean finalresult = false;
		try {
			// getReportingStructure(bean.getAppEmpId(),
			// bean.getDeptId(),
			// bean.getBranchId(),""
			// bean.getDesgId());

			Object[][] add = null;
			Object[][] addObj = null;
			Object maxCodeObj[][] = getSqlModel().getSingleResult(
					"SELECT NVL(MAX(EXP_APPID),0) FROM TMS_CLAIM_APPL");
			int maxCode = Integer.parseInt("" + maxCodeObj[0][0]);
			Object maxCodeObjDtl[][] = getSqlModel().getSingleResult(
					"SELECT NVL(MAX(EXP_DTLID),0) FROM TMS_EXP_DTL");
			int maxCodeDtl = Integer.parseInt("" + maxCodeObjDtl[0][0]);
			/*
			 * Object maxApplIdObj[][] = getSqlModel().getSingleResult( "SELECT
			 * NVL(MAX(APPL_ID),0) FROM TMS_APPLICATION"); int maxApplId =
			 * Integer.parseInt("" + maxApplIdObj[0][0]);
			 * 
			 * Object maxClaimApplIdObj[][] = getSqlModel().getSingleResult(
			 * "SELECT NVL(MAX(EXP_TRVL_APPID),0) FROM TMS_CLAIM_APPL"); int
			 * maxClaimApplId = Integer.parseInt("" + maxClaimApplIdObj[0][0]);
			 * int maxId = 0; if (maxApplId > maxClaimApplId) { maxId =
			 * maxApplId; } else if (maxClaimApplId > maxApplId) { maxId =
			 * maxClaimApplId; }
			 */
			// if (!bean.getBranchCode().equals("")) {
			
			//double actualExpenditure = 0.0;
			
			add = new Object[1][28];
			add[0][0] = ++maxCode;
			// add[0][1] = checkNull(bean.getAppDate());
			add[0][1] = getSysDate();
			
			add[0][2] = buttonType;
			// add[0][3] = checkNull(new
			// Utility().twoDecimals(bean.getAdvanceAmtTaken()));
			if (checkNull(bean.getAdvanceAmtTaken()).equals("")
					|| checkNull(bean.getAdvanceAmtTaken()).equals("null")) {
				add[0][3] = 0.00;
			} else {
				add[0][3] = checkNull(Utility.twoDecimals(bean.getAdvanceAmtTaken()));
			}
			add[0][4] = checkNull(bean.getEmployeeId());
			add[0][5] = checkNull(bean.getTrvlStartDate());
			add[0][6] = checkNull(bean.getTrvlEndDate());
			add[0][7] = checkNull(bean.getTravelPurposeId());
			add[0][8] = checkNull(bean.getTravelTypeId());
			add[0][9] = checkNull(bean.getComment());
			add[0][10] = checkNull(bean.getTotExpAmt());
			add[0][11] = checkNull(bean.getTotElgAmt());
			add[0][12] = bean.getApplnId(); // appid
			add[0][13] = checkNull(bean.getApplnCode());
			add[0][14] = checkNull(String.valueOf(rptObj[0][0]));// main approver
			add[0][15] = checkNull(String.valueOf(rptObj[0][3]));// Alternate approver
			add[0][16] = "T";// App Type
			add[0][17] = checkNull(bean.getTrvlReqNameAddNew());
			add[0][18] = "TRVL_" + bean.getApplnId() + "_" + bean.getApplnCode() + "";

			add[0][19] = checkNull(bean.getProjectId());
			add[0][20] = checkNull(bean.getCustomerId());
			add[0][21] = checkNull(bean.getTourComments());
			add[0][22] = checkNull(bean.getTourReportFile());
			add[0][23] = checkNull(bean.getAchievementComments());
			add[0][24] = checkNull(bean.getFollowUpComments());
			add[0][25] = checkNull(bean.getTargetDate());
			add[0][26] = checkNull(bean.getCurrencyEmployeeAdvance());
			add[0][27] = checkNull(bean.getTotalCurrencyExpense());

			saveHdrresult = getSqlModel().singleExecute(getQuery(3), add);
			if (saveHdrresult) {
				String maxQuery = "SELECT MAX(EXP_APPID) FROM TMS_CLAIM_APPL";
				Object[][] data = getSqlModel().getSingleResult(maxQuery);
				bean.setClaimApplnCode(String.valueOf(data[0][0]));
				if (buttonType.equals("P")) {
					// save record in approval dtl table
					saveApprovalDtl(bean, rptObj);
				}
				int k = 0;
				addObj = new Object[expenseDateIt.length][11];
				if (expenseDateIt != null && expenseDateIt.length > 0) {
					for (int i = 0; i < expenseDateIt.length; i++) {
						addObj[k][0] = ++maxCodeDtl;
						addObj[k][1] = data[0][0];
						addObj[k][2] = checkNull(String.valueOf(expenseDateIt[i]));
						addObj[k][3] = checkNull(String.valueOf(expenseTypeIdIt[i]));
						addObj[k][4] = checkNull(String.valueOf(eligibleAmtIt[i]));
						addObj[k][5] = checkNull(String.valueOf(expenseAmtIt[i]));
						addObj[k][6] = checkNull(String.valueOf(particularsIt[i]));

						if (bean.getProofRequiredIt().equals("true")) {
							addObj[k][7] = "Y";
						} else {
							addObj[k][7] = "N";
						}
						/**
						 * Error of proof available flag. Please rectify.
						 * 
						 */
						addObj[k][8] = checkNull(String.valueOf(itteratorProofNameForSave[i]));
						// checkNull(String.valueOf(proofIt[i]));
						addObj[k][9] = String.valueOf(policyViolationTextIt[i]).equals("No") ? "N" : "Y";
						addObj[k][10] = checkNull(String.valueOf(currencyExpenseAmtItr[i]));
						k++;
					}
					saveDtlresult = getSqlModel().singleExecute(getQuery(4), addObj);
					/**
					 * For rating updates
					 */
					try {
						if (buttonType.equals("P")) {
							TravelProcessModel processMod = new TravelProcessModel();
							processMod.initiate(context, session);
							processMod.updateHotelRating(bean.getClaimApplnCode());
							processMod.terminate();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (saveHdrresult & saveDtlresult) {
			finalresult = true;
		}

		return finalresult;

	}

	public boolean checkArrngdFlags(TmsClaimApplication claimApp) {
		String arrngmntQuery = "SELECT APPL_MANAGE_JOURNEY FROM TMS_APPLICATION WHERE APPL_ID="
				+ claimApp.getApplnId()
				+ " AND (APPL_MANAGE_JOURNEY ='C' OR APPL_MANAGE_JOURNEY IS NULL)"
				+ " AND (APPL_MANAGE_ACCOMODATION='C' OR  APPL_MANAGE_ACCOMODATION IS NULL  )"
				+ " AND (APPL_MANAGE_LOCAL_CONV ='C' OR APPL_MANAGE_LOCAL_CONV IS NULL) ";
		Object arrngmntData[][] = getSqlModel().getSingleResult(arrngmntQuery);
		if (arrngmntData != null && arrngmntData.length > 0)
			return true;
		else
			return false;
	}

	public boolean delExpDtls(String removeData) {
		if (removeData != null && !(removeData.equals("null"))
				&& !(removeData.equals(""))) {
			removeData = removeData.substring(0, removeData.length() - 1);
			String splitVals[] = removeData.split("#");
			// logger.info("No. of records to be delete --
			// "+(splitVals.length/2));
			for (int i = 0; i < splitVals.length; i++) {
				delExp(splitVals[i], splitVals[i++]);
			}
			// logger.info("Deleted--------------------");
		}
		return true;
	}

	/*
	 * public void setDropDownValueList(TmsTravelPolicy tp) {  try {
	 * 
	 * TreeMap map = new TreeMap();
	 * 
	 * String selectSql = " SELECT GRADE_ID,GRADE_CITIES FROM HRMS_CITY_GRADE
	 * ORDER BY GRADE_ID ";
	 * 
	 * Object dataObj[][] = getSqlModel().getSingleResult(selectSql);
	 * 
	 * if (dataObj != null && dataObj.length > 0) { for (int i = 0; i <
	 * dataObj.length; i++) { map.put(String.valueOf(dataObj[i][0]),
	 * String.valueOf(dataObj[i][1])); } } else { map.put("-1","--Select--"); }
	 * tp.setTmap(map); } catch (Exception e) { e.printStackTrace(); } }
	 */

	// Comma Separated city name, Split according to comma and Set into
	// drop-down list
	public void setDropDownValueList(TmsClaimApplication travelClaim) {
		try {

			TreeMap map = new TreeMap();

			String selectSql = " SELECT GRADE_ID,GRADE_CITIES FROM TMS_POLICY_EXPENSE_DTL "
					+ " INNER JOIN HRMS_CITY_GRADE ON (TMS_POLICY_EXPENSE_DTL.POLICY_CITYGRADE = HRMS_CITY_GRADE.GRADE_ID  ) "
					+ " INNER JOIN TMS_POLICY_GRADE_DTL ON (TMS_POLICY_GRADE_DTL.POLICY_ID = TMS_POLICY_EXPENSE_DTL.POLICY_ID ) "
					+ " WHERE TMS_POLICY_GRADE_DTL.POLICY_GRAD_ID = "
					+ travelClaim.getGradId()
					+ " AND POLICY_EXP_CAT_ID = "
					+ travelClaim.getExpenseTypeId();

			Object dataObj[][] = getSqlModel().getSingleResult(selectSql);
			if (dataObj != null && dataObj.length > 0) {
				int count = 0;
				// Loop for getting the count for number of cities included into
				// selected grade.
				for (int i = 0; i < dataObj.length; i++) {
					String[] cityname = String.valueOf(dataObj[i][1])
							.split(",");
					for (int j = 0; j < cityname.length; j++) {
						System.out.println("Count ===============> " + count);
						count++;
					}
				}
				Object newObj[][] = new Object[count][2];
				count = 0;
				for (int i = 0; i < dataObj.length; i++) {
					String[] cityname = String.valueOf(dataObj[i][1])
							.split(",");
					for (int j = 0; j < cityname.length; j++) {
						newObj[count][0] = cityname[j];
						System.out
								.println(" newObj[count][0] ======================> "
										+ newObj[count][0]);

						// newObj[count][1] = cityname[j];
						// System.out.println(" newObj[count][1]
						// ======================> "+newObj[count][1]);
						count++;
					}
				}
				if (newObj != null && newObj.length > 0) {
					travelClaim.setCityGradeFlag(true);

					for (int i = 0; i < newObj.length; i++) {
						map.put(String.valueOf(newObj[i][0]), String
								.valueOf(newObj[i][0]));
					}
				}

			}

			travelClaim.setTmap(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void displayIteratorValueForUploadProof(String[] srNo,
			String[] proofName, TmsClaimApplication travelClaim) {

		try {
			ArrayList<TmsClaimApplication> proofList = new ArrayList<TmsClaimApplication>();
			if (srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					TmsClaimApplication tmsclaimapp = new TmsClaimApplication();
					tmsclaimapp.setProofName(proofName[i]);
					// tmsclaimapp.setProofFileName(proofFileName[i]);
					proofList.add(tmsclaimapp);
				}
				travelClaim.setProofList(proofList);
			}
		} catch (Exception e) {
			logger
					.error("Exception in displayIteratorValueForUploadProof------"
							+ e);
		}

	}

	public void setProofList(String[] srNo, String[] proofName,
			TmsClaimApplication travelClaim) {
		try {
			TmsClaimApplication claimapp = new TmsClaimApplication();
			claimapp.setProofName(travelClaim.getUploadLocFileName());

			ArrayList<TmsClaimApplication> proofList = displayNewValueProofList(
					srNo, proofName, travelClaim);
			claimapp.setProofSrNo(String.valueOf(proofList.size() + 1));// sr no
			proofList.add(claimapp);
			travelClaim.setProofList(proofList);
		} catch (Exception e) {
			logger.error("Exception in setProofList------" + e);
		}

	}

	private ArrayList<TmsClaimApplication> displayNewValueProofList(
			String[] srNo, String[] proofName, TmsClaimApplication travelClaim) {
		ArrayList<TmsClaimApplication> proofList = null;
		try {
			proofList = new ArrayList<TmsClaimApplication>();
			if (srNo != null) {

				for (int i = 0; i < srNo.length; i++) {
					TmsClaimApplication claimApp = new TmsClaimApplication();
					claimApp.setProofName(proofName[i]);
					claimApp.setProofSrNo(srNo[i]);
					// claimApp.setProofFileName(proofFileName[i]);
					proofList.add(claimApp);
				}

			}
		} catch (Exception e) {
			logger.error("Exception in displayNewValueProofList------" + e);
		}
		return proofList;
	}

	public void removeUploadFile(String[] srNo, String[] proofName,
			TmsClaimApplication travelClaim) {
		try {
			ArrayList<Object> tableList = new ArrayList<Object>();
			if (srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					TmsClaimApplication bean = new TmsClaimApplication();
					bean.setProofSrNo(String.valueOf(i + 1));
					bean.setProofName(proofName[i]);
					// bean.setProofFileName(proofFileName[i]);
					tableList.add(bean);
				}
				tableList.remove(Integer.parseInt(travelClaim
						.getCheckRemoveUpload()) - 1);

			}

			travelClaim.setProofList(tableList);
		} catch (Exception e) {
			logger.error("Exception in removeUploadFile------" + e);
		}

	}

	public void setProof(String proofNameArr[], TmsClaimApplication bean) {
		try {

			String proofNameValue = "";

			if (proofNameArr != null && proofNameArr.length > 0) {
				for (int i = 0; i < proofNameArr.length; i++) {

					if (proofNameArr.length - 1 == i) {
						proofNameValue += proofNameArr[i];
					} else {
						proofNameValue += proofNameArr[i] + ",";
					}
				}

			}

			if (proofNameValue != null && proofNameValue.length() > 0) {
				String[] innerproofName = proofNameValue.split(",");
				ArrayList <Object> innerlist = new ArrayList <Object>();
				for (int k = 0; k < innerproofName.length; k++) {
					TmsClaimApplication innerbean = new TmsClaimApplication();
					innerbean.setIttproofName(checkNull(innerproofName[k]));
					innerlist.add(innerbean);

				}
				bean.setIttUploadList(innerlist);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getTravelPolicyCode(String gradeId, String appDate) {

		String policyCode = "";

		Object policyObj[][] = null;
		try {

			if (appDate.equals("")) {
				Date date = new Date();
				SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
				String sysDate = formater.format(date);
				appDate = sysDate;
			}
			System.out
					.println("Application Date in travel policy code()===========>"
							+ appDate);
			String query = "   SELECT TMS_TRAVEL_POLICY.POLICY_ID,POLICY_GRAD_ID,CADRE_NAME,POLICY_NAME,POLICY_ABBR,TO_CHAR(POLICY_EFFECTIVE_DATE,'DD-MM-YYYY'),POLICY_DESC,POLICY_STATUS , POLICY_ISADVANCE, MAX_DAYS_SETTLE_TRAVEL_CLAIM,  POLICY_PAY_MODE_CHEQUE,  POLICY_PAY_MODE_CASH, POLICY_PAY_MODE_TRANSFER, POLICY_PAY_MODE_IN_SALARY  FROM TMS_TRAVEL_POLICY "
					+ " INNER JOIN  TMS_POLICY_GRADE_DTL ON(TMS_POLICY_GRADE_DTL.POLICY_ID = TMS_TRAVEL_POLICY.POLICY_ID)"
					+ " INNER JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID = TMS_POLICY_GRADE_DTL.POLICY_GRAD_ID)"
					+ " WHERE POLICY_GRAD_ID="
					+ gradeId
					+ " AND  POLICY_STATUS='A' AND   TO_DATE('"
					+ appDate
					+ "' ,'DD-MM-YYYY')>=POLICY_EFFECTIVE_DATE"// AND
					+ " ORDER BY POLICY_EFFECTIVE_DATE DESC";

			policyObj = getSqlModel().getSingleResult(query);

			if (policyObj != null && policyObj.length > 0) {
				policyCode = String.valueOf(policyObj[0][0]);
			}

		} catch (Exception e) {
			policyCode = "";
		}

		return policyCode;
	}

	// Function for report
	public void getReport(TmsClaimApplication claimApp,
			HttpServletResponse response) {
		try {

			ReportDataSet rds = new ReportDataSet();
			rds.setReportType("Pdf");
			rds.setFileName("Travel Claim__ "
					+ claimApp.getEmployeeToken().trim());
			org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(
					rds);

			String title = "Travel Claim";
			String subTitle = "";

			String divisionName = "", divisionAddress = "";

			String divDetailsQury = "SELECT DIV_NAME,NVL(DIV_ABBR,''),nvl(DIV_ADDRESS1,''),nvl(DIV_ADDRESS2,''),nvl(DIV_ADDRESS3,''),"
					+ " NVL(DIV_TELEPHONE,''),NVL(DIV_WEBSITE,'')  FROM HRMS_DIVISION where DIV_ID=(SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="
					+ claimApp.getApplnEmpId() + ")";
			Object divisionDtl[][] = getSqlModel().getSingleResult(
					divDetailsQury);

			if (divisionDtl != null && divisionDtl.length > 0) {
				divisionName = String.valueOf(divisionDtl[0][0]);
				divisionAddress = String.valueOf(checkNull(""
						+ divisionDtl[0][2]))
						+ "\n"
						+ String.valueOf(checkNull("" + divisionDtl[0][3]))
						+ String.valueOf(checkNull("" + divisionDtl[0][4]));
				// +"\n"+String.valueOf(checkNull(""+divisionDtl[0][5]))+"
				// "+String.valueOf(checkNull(""+divisionDtl[0][6]));

			}

			Object[][] nameObj = new Object[1][1];
			boolean isLogo = false;
			try {
				String logoQuery = "select COMPANY_CODE,COMPANY_LOGO from HRMS_COMPANY";
				Object logoObj[][] = getSqlModel().getSingleResult(logoQuery);
				if (logoObj != null && logoObj.length > 0) {
					String filename = "";
					if (!String.valueOf(logoObj[0][1]).equals("")) {
						String clientUser = (String) session
								.getAttribute("session_pool");
						filename = String.valueOf(logoObj[0][1]);
						String filePath = context.getRealPath("/")
								+ "pages/Logo/"
								+ session.getAttribute("session_pool") + "/"
								+ filename;
						nameObj[0][0] = Image.getInstance(filePath);
						isLogo = true;

					} else
						nameObj[0][0] = "";

				} else {
					nameObj[0][0] = "";
				}
				// Image
				// im=Image.getInstance("C:/hrwork/dataFiles/sal_logo.jpg");
				// nameObj[0][1]=divisionName+"\n\n "+title+" "+subTitle;
			} catch (Exception eee) {
				logger.info("when assign the image...!" + eee);

				nameObj[0][0] = " ";
				// nameObj[0][1]=divisionName+"\n\n "+title+" "+subTitle;
			}

			TableDataSet logoData = new TableDataSet();
			logoData.setData(nameObj);
			logoData.setCellAlignment(new int[] { 0 });
			logoData.setCellWidth(new int[] { 100 });
			logoData.setBorder(false);
			// PdfPTable nameTable0 = rg.createTable(titleName);

			Object[][] titleObj = new Object[1][1];
			titleObj[0][0] = "" + divisionName;

			TableDataSet titleName = new TableDataSet();
			titleName.setData(titleObj);
			titleName.setCellAlignment(new int[] { 1 });
			titleName.setCellWidth(new int[] { 100 });
			titleName.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
					new Color(0, 0, 0));
			titleName.setBorder(false);

			Object[][] subtitleObj = new Object[1][1];
			subtitleObj[0][0] = "" + divisionAddress;

			TableDataSet subtitleName = new TableDataSet();
			subtitleName.setData(subtitleObj);
			subtitleName.setCellAlignment(new int[] { 1 });
			subtitleName.setCellWidth(new int[] { 100 });
			subtitleName.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD,
					new Color(0, 0, 0));
			subtitleName.setBorder(false);

			Object[][] subtitleObj2 = new Object[1][1];
			subtitleObj2[0][0] = "" + title + " " + subTitle;

			TableDataSet subtitleName2 = new TableDataSet();
			subtitleName2.setData(subtitleObj2);
			subtitleName2.setCellAlignment(new int[] { 1 });
			subtitleName2.setCellWidth(new int[] { 100 });
			subtitleName2.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,
					new Color(0, 0, 0));
			subtitleName2.setBorder(false);

			HashMap<String, Object> mapOne = rg.joinTableDataSet(titleName,
					subtitleName, false, 0);

			HashMap<String, Object> mapTwo = rg.joinTableDataSet(mapOne,
					subtitleName2, false, 0);
			HashMap<String, Object> mapThree = null;
			if (isLogo)
				mapThree = rg.joinTableDataSet(logoData, mapTwo, true, 30);
			else
				mapThree = rg.joinTableDataSet(logoData, mapTwo, true, 5);

			rg.addTableToDoc(mapThree);

			
				 
			String query = " SELECT EXP_APPID,NVL(TITLE_NAME||' '||applicant.EMP_FNAME||' '||applicant.EMP_MNAME||' '||applicant.EMP_LNAME,''), TO_CHAR(EXP_APP_DATE,'DD-MM-YYYY'), EXP_APP_STATUS, EXP_ADV_AMT, EXP_APP_EMPID,"
					+ " TO_CHAR(EXP_TRVL_START_DATE,'DD-MM-YYYY'), TO_CHAR(EXP_TRVL_END_DATE,'DD-MM-YYYY'), EXP_TRVL_PURPOSE,NVL(HRMS_TMS_PURPOSE.PURPOSE_NAME,''), EXP_TRVL_TYPE, NVL(HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_NAME,''),NVL(EXP_COMMENTS,''), EXP_TOT_EXPAMT, "
					+ " EXP_TOT_ELIGBLEAMT, EXP_TRVL_APPID, EXP_TRVL_APPCODE,  EXP_APP_TYPE, EXP_APPRVD_AMT, NVL(EXP_TRVL_REQNAME,'') ,"
					+ " NVL(HRMS_CENTER.CENTER_NAME,''),CENTER_ID,NVL(DEPT_NAME,''),  NVL(HRMS_RANK.RANK_NAME,''),CADRE_ID,NVL(CADRE_NAME,''),RANK_ID,DEPT_ID, NVL(TMS_TRAVEL_PROJECT.PROJECT_NAME,'') ,NVL(TMS_TRAVEL_CUSTOMER.TRAVEL_CUST_NAME,''), EXP_MAIN_APPRVR ,NVL(approver.EMP_FNAME||' '||approver.EMP_MNAME||' '||approver.EMP_LNAME,''),approverRank.RANK_NAME,EXP_APP_DATE,NVL(TMS_TRAVEL_PROJECT.PROJECT_NAME,'') ,NVL(TMS_TRAVEL_CUSTOMER.TRAVEL_CUST_NAME,'')   "
					+ " ,EXP_TRVL_ID , case when EXP_ADV_AMT=0 then 0 else EXP_TOT_EXPAMT-EXP_ADV_AMT end as recovery  FROM TMS_CLAIM_APPL "
					+ " INNER JOIN HRMS_EMP_OFFC applicant ON(applicant.EMP_ID=TMS_CLAIM_APPL.EXP_APP_EMPID) "
					+ " LEFT JOIN HRMS_TITLE ON (HRMS_TITLE.TITLE_CODE = applicant.EMP_TITLE_CODE)   "
					+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=applicant.EMP_CENTER) "
					+ " LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=applicant.EMP_DEPT) "
					+ " LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=applicant.EMP_RANK) "
					+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=applicant.EMP_CADRE) "
					+ " LEFT JOIN HRMS_TMS_LOCATION_TYPE ON(HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID=TMS_CLAIM_APPL.EXP_TRVL_TYPE)"
					+ " LEFT JOIN HRMS_TMS_PURPOSE ON(HRMS_TMS_PURPOSE.PURPOSE_ID=TMS_CLAIM_APPL.EXP_TRVL_PURPOSE)"
					+ " LEFT JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID=applicant.EMP_ID AND ADD_TYPE='L')"
					+ " LEFT JOIN TMS_TRAVEL_PROJECT ON(TMS_TRAVEL_PROJECT.PROJECT_ID=TMS_CLAIM_APPL.EXP_PROJECT_ID)"
					+ " LEFT JOIN TMS_TRAVEL_CUSTOMER ON(TMS_TRAVEL_CUSTOMER.TRAVEL_CUST_ID=TMS_CLAIM_APPL.EXP_CUSTOMER_ID)"
					+ " INNER JOIN HRMS_EMP_OFFC APPROVER ON(approver.EMP_ID=TMS_CLAIM_APPL.EXP_MAIN_APPRVR)"
					+ "  LEFT JOIN HRMS_RANK APPROVERRANK ON(approverRank.RANK_ID=approver.EMP_RANK) "
					+ " WHERE EXP_APPID= " + claimApp.getClaimApplnCode();
			Object[][] dataFormula = getSqlModel().getSingleResult(query);

			if (dataFormula != null && dataFormula.length > 0) {
				// Employee Information
				Object[][] employeeInformationObj = new Object[5][4];

				employeeInformationObj[0][0] = "Employee Name :";
				employeeInformationObj[0][1] = String
						.valueOf(dataFormula[0][1]);

				employeeInformationObj[0][2] = "Application Date :";
				employeeInformationObj[0][3] = String
						.valueOf(dataFormula[0][2]);

				employeeInformationObj[1][0] = "Branch Name :";
				employeeInformationObj[1][1] = String
						.valueOf(dataFormula[0][20]);
				employeeInformationObj[1][2] = "Travel Start Date :";
				employeeInformationObj[1][3] = String
						.valueOf(dataFormula[0][6]);

				employeeInformationObj[2][0] = "Department :";
				employeeInformationObj[2][1] = String
						.valueOf(dataFormula[0][22]);
				employeeInformationObj[2][2] = "Travel End Date :";
				employeeInformationObj[2][3] = String
						.valueOf(dataFormula[0][7]);

				employeeInformationObj[3][0] = "Designation :  ";
				employeeInformationObj[3][1] = String
						.valueOf(dataFormula[0][23]);
				employeeInformationObj[3][2] = "Reporting Head's Name :  ";
				employeeInformationObj[3][3] = String
						.valueOf(dataFormula[0][31]);

				employeeInformationObj[4][0] = "Grade :";
				employeeInformationObj[4][1] = String
						.valueOf(dataFormula[0][25]);
				employeeInformationObj[4][2] = "Reporting Head's Designation : ";
				employeeInformationObj[4][3] = String
						.valueOf(dataFormula[0][32]);

				Object[][] empInfoDet = new Object[1][1];
				empInfoDet[0][0] = "Employee Information";
				TableDataSet appraiseeDetTable = new TableDataSet();
				appraiseeDetTable.setData(empInfoDet);
				appraiseeDetTable.setCellAlignment(new int[] { 1 });
				appraiseeDetTable.setCellWidth(new int[] { 100 });
				appraiseeDetTable.setBlankRowsAbove(1);
				// appraiseeDetTable.setBlankRowsBelow(1);
				appraiseeDetTable.setBodyFontDetails(Font.HELVETICA, 10,
						Font.BOLD, new Color(0, 0, 0));
				appraiseeDetTable.setBodyBGColor(new Color(192, 192, 192));
				rg.addTableToDoc(appraiseeDetTable);

				TableDataSet employeeInformationTable = new TableDataSet();
				employeeInformationTable.setData(employeeInformationObj);
				employeeInformationTable.setCellAlignment(new int[] { 0, 0, 0,
						0 });
				employeeInformationTable.setCellWidth(new int[] { 25, 25, 25,
						25 });
				// employeeInformationTable.setBlankRowsAbove(1);
				employeeInformationTable.setBlankRowsBelow(1);
				employeeInformationTable.setBorder(true);
				rg.addTableToDoc(employeeInformationTable);

				// Travel Request Name
				Object[][] travelReqInformationObj = new Object[5][4];

				travelReqInformationObj[0][0] = "Travel Request Name :";
				travelReqInformationObj[0][1] = String
						.valueOf(dataFormula[0][19]);
				travelReqInformationObj[0][2] = "Travel Purpose :";
				travelReqInformationObj[0][3] = String
						.valueOf(dataFormula[0][9]);

				travelReqInformationObj[1][0] = "Travel Type :";
				travelReqInformationObj[1][1] = String
						.valueOf(dataFormula[0][11]);
				travelReqInformationObj[1][2] = "Comments :";
				travelReqInformationObj[1][3] = String
						.valueOf(dataFormula[0][12]);

				travelReqInformationObj[2][0] = "Customer Name :";
				travelReqInformationObj[2][1] = String
						.valueOf(dataFormula[0][35]);
				travelReqInformationObj[2][2] = "Project Name :";
				travelReqInformationObj[2][3] = String
						.valueOf(dataFormula[0][34]);

				travelReqInformationObj[3][0] = "Advance Amount taken :";
				travelReqInformationObj[3][1] = String
						.valueOf(dataFormula[0][4]);
				travelReqInformationObj[3][2] = "Travel Application ID:";
				travelReqInformationObj[3][3] = String
				.valueOf(dataFormula[0][36]);
				
				
				
				travelReqInformationObj[4][0] = "Recovery Amount:";
				travelReqInformationObj[4][1] = String
				.valueOf(dataFormula[0][37]);
				travelReqInformationObj[4][2] = "";
				travelReqInformationObj[4][3] = "";
				

				Object[][] travelRequestDet = new Object[1][1];
				travelRequestDet[0][0] = "Travel Request Details";
				TableDataSet appraiseeDetTable1 = new TableDataSet();
				appraiseeDetTable1.setData(travelRequestDet);
				appraiseeDetTable1.setCellAlignment(new int[] { 1 });
				appraiseeDetTable1.setCellWidth(new int[] { 100 });
				// appraiseeDetTable1.setBlankRowsAbove(1);
				// appraiseeDetTable1.setBlankRowsBelow(1);
				appraiseeDetTable1.setBodyFontDetails(Font.HELVETICA, 10,
						Font.BOLD, new Color(0, 0, 0));
				appraiseeDetTable1.setBodyBGColor(new Color(192, 192, 192));
				rg.addTableToDoc(appraiseeDetTable1);

				TableDataSet travelReqInformationTable = new TableDataSet();
				travelReqInformationTable.setData(travelReqInformationObj);
				travelReqInformationTable.setCellAlignment(new int[] { 0, 0, 0,
						0 });
				travelReqInformationTable.setCellWidth(new int[] { 25, 25, 25,
						25 });
				// travelReqInformationTable.setBlankRowsAbove(1);
				travelReqInformationTable.setBlankRowsBelow(1);
				travelReqInformationTable.setBorder(true);
				rg.addTableToDoc(travelReqInformationTable);

				// Expense Dtls

				String expenseQuery = "SELECT TO_CHAR(EXP_DTL_DATE,'DD-MM-YYYY') AS EXP_DATE, EXP_DTL_EXP_TYPE, EXP_DTL_EXP_ELIGBLEAMT||' '||EXP_DTL_CURRENCY, "
						+ "	EXP_DTL_EXP_AMT||' '||EXP_DTL_CURRENCY,	NVL(EXP_DTL_PARTICULARS,''),HRMS_TMS_EXP_CATEGORY.EXP_CATEGORY_NAME, "
						+ "	DECODE(TMS_EXP_DTL.EXP_DTL_PROOF,'Y','Yes','N','No','No'), NVL(EXP_DTL_PROOF,'') 	,EXP_DTLID,EXP_APPID   FROM TMS_EXP_DTL  "
						+ "	INNER JOIN  HRMS_TMS_EXP_CATEGORY ON(HRMS_TMS_EXP_CATEGORY.EXP_CATEGORY_ID=TMS_EXP_DTL.EXP_DTL_EXP_TYPE) "
						+ "	WHERE EXP_APPID= " + claimApp.getClaimApplnCode();

				Object[][] expDetail = getSqlModel().getSingleResult(
						expenseQuery);

				Object[][] objTabularData = new Object[expDetail.length][7];
				Object[][] objTabularData1 = new Object[1][7];
				String[] columns = new String[] { "Sr. No.", "Expense Date",
						"Expense Type", "Particulars", "Eligible Amount",
						"Expense Amount", "Attached Proof" };
				
				int[] bcellAlign = new int[] { 0, 0, 0, 0, 2, 2, 1 };
				int[] bcellWidth = new int[] { 10, 10, 20, 15, 10, 10, 25 };
				String Sumquery="SELECT SUM(EXP_DTL_EXP_AMT)||' '|| EXP_DTL_CURRENCY FROM TMS_EXP_DTL WHERE EXP_APPID= "+claimApp.getClaimApplnCode()+"GROUP BY EXP_DTL_CURRENCY";
				Object[][] SumqueryObj = getSqlModel().getSingleResult(
						Sumquery);
				if(SumqueryObj!=null && SumqueryObj.length>0){
					objTabularData1[0][0]="";
					objTabularData1[0][1]="";
					objTabularData1[0][2]="";
					objTabularData1[0][3]="";
					objTabularData1[0][4]="";
					objTabularData1[0][5]=String.valueOf(SumqueryObj[0][0]);
					objTabularData1[0][6]="";
					
				}
				int count = 1;

				if (expDetail != null && expDetail.length > 0) {
					for (int i = 0; i < expDetail.length; i++) {

						objTabularData[i][0] = count++;

						objTabularData[i][1] = String.valueOf(expDetail[i][0]);
						objTabularData[i][2] = String.valueOf(expDetail[i][5]);
						objTabularData[i][3] = String.valueOf(expDetail[i][4]);
						objTabularData[i][4] = String.valueOf(expDetail[i][2]);
						objTabularData[i][5] = String.valueOf(expDetail[i][3]);

						if (String.valueOf(expDetail[i][7]).equals("null")) {
							objTabularData[i][6] = "No";
						} else {
							objTabularData[i][6] = "Yes";
						}
					}
				}
				Object[][] finalObject= new Object[expDetail.length+1][7];
				finalObject = Utility.joinArrays(objTabularData, objTabularData1, 1, 0);
				
				Object[][] expenseDtlDet = new Object[1][1];
				expenseDtlDet[0][0] = "Expense Details ";
				TableDataSet expenseDtlDetTable = new TableDataSet();
				expenseDtlDetTable.setData(expenseDtlDet);
				expenseDtlDetTable.setCellAlignment(new int[] { 1 });
				expenseDtlDetTable.setCellWidth(new int[] { 100 });
				expenseDtlDetTable.setBodyFontDetails(Font.HELVETICA, 10,
						Font.BOLD, new Color(0, 0, 0));
				expenseDtlDetTable.setBodyBGColor(new Color(192, 192, 192));
				rg.addTableToDoc(expenseDtlDetTable);
				// expenseDtlDetTable.setBlankRowsAbove(1);
				// expenseDtlDetTable.setBlankRowsBelow(1);
				
				TableDataSet tdstable = new TableDataSet();
				tdstable.setHeader(columns);
				tdstable.setData(finalObject);
				tdstable.setCellAlignment(bcellAlign);
				tdstable.setCellWidth(bcellWidth);
				tdstable.setBorder(true);
				//tdstable.setColumnSum(new int[]{5});
				tdstable.setHeaderBGColor(new Color(225, 225, 225));
				rg.addTableToDoc(tdstable);
				
				/*TableDataSet tdstable1 = new TableDataSet();
				tdstable1.setHeader(columns1);
				tdstable1.setData(objTabularData1);
				tdstable1.setCellAlignment(bcellAlign);
				tdstable1.setCellWidth(bcellWidth);
				tdstable1.setBorder(true);
				//tdstable.setColumnSum(new int[]{5});
				//tdstable1.setHeaderBGColor(new Color(225, 225, 225));
				rg.addTableToDoc(tdstable1);*/
				// Approver Dtls

				String approverQuery = "select EXP_APPRVR_ID,NVL(C1.EMP_FNAME||' '||C1.EMP_LNAME,' ') AS APPROVER_NAME , TO_CHAR(EXP_APPRVL_DATE,'DD-MM-YYYY') AS APPROVERED_DATE, EXP_APPR_CMTS AS APPROVER_COMMENTS, "
									+ " DECODE(EXP_APPRVL_STATUS,'A','Approved','B','Send Back','R','Rejected')  AS APPROVER_STATUS"
									+ " from TMS_EXP_APPROVAL_DTL"
									+ " INNER JOIN HRMS_EMP_OFFC  C1 ON(C1.EMP_ID=TMS_EXP_APPROVAL_DTL.EXP_APPRVR_ID)"

									+ "	WHERE EXP_APPID= " + claimApp.getClaimApplnCode();

				Object[][] approverDetail = getSqlModel().getSingleResult(
						approverQuery);
				
				Object[][] objApproverTabularData = new Object[approverDetail.length][5];
				String[] columnsApp = new String[] { "Sr. No.", "Approver Name",
						"Approved Date", "Approver Comments" };
				int[] bcellAlignApp = new int[] { 0, 0, 0, 0 };
				int[] bcellWidthApp = new int[] { 10, 30, 20, 35 };

				int count1 = 1;
				
				Object[][] approverDtlDet = new Object[1][1];
				approverDtlDet[0][0] = "Approver Details ";
				TableDataSet approverDtlDetTable = new TableDataSet();
				approverDtlDetTable.setData(approverDtlDet);
				approverDtlDetTable.setCellAlignment(new int[] { 1 });
				approverDtlDetTable.setCellWidth(new int[] { 100 });
				approverDtlDetTable.setBodyFontDetails(Font.HELVETICA, 10,
						Font.BOLD, new Color(0, 0, 0));
				approverDtlDetTable.setBodyBGColor(new Color(192, 192, 192));
				rg.addTableToDoc(approverDtlDetTable);
				approverDtlDetTable.setBlankRowsAbove(1);
				//approverDtlDetTable.setBlankRowsBelow(1);
				
				if (approverDetail != null && approverDetail.length > 0) {
					for (int i = 0; i < approverDetail.length; i++) {

						objApproverTabularData[i][0] = count1++;

						objApproverTabularData[i][1] = String.valueOf(approverDetail[i][1]);
						objApproverTabularData[i][2] = String.valueOf(approverDetail[i][2]);
						objApproverTabularData[i][3] = String.valueOf(approverDetail[i][3]);
						
						
					}
				}else {
					TableDataSet noData = new TableDataSet();
					Object[][] noDataObj = new Object[1][1];
					noDataObj[0][0] = "No data to display ";
					
					noData.setData(noDataObj);
					noData.setCellAlignment(new int[] { 1 });
					noData.setCellWidth(new int[] { 100 });
					noData.setBodyFontDetails(Font.HELVETICA, 10,
							Font.NORMAL, new Color(0, 0, 0));
					noData.setBorder(true);
					rg.addTableToDoc(noData);
				}
				

				

				TableDataSet tdstableApp = new TableDataSet();
				tdstableApp.setHeader(columnsApp);
				tdstableApp.setData(objApproverTabularData);
				tdstableApp.setCellAlignment(bcellAlignApp);
				tdstableApp.setCellWidth(bcellWidthApp);
				tdstableApp.setBorder(true);
				tdstableApp.setHeaderBGColor(new Color(225, 225, 225));
				rg.addTableToDoc(tdstableApp);

				Object signatureObj[][] = new Object[2][4];

				signatureObj[0][0] = "Applicant Signature:";
				signatureObj[0][1] = "_______________________";
				signatureObj[0][2] = "";
				signatureObj[0][3] = "";
				if (dataFormula != null && dataFormula.length > 0) {
					signatureObj[1][0] = "";
					signatureObj[1][1] = "("
							+ String.valueOf(dataFormula[0][1]) + ")";
					signatureObj[1][2] = "";
			//		signatureObj[1][3] = "("
				//			+ String.valueOf(dataFormula[0][31]) + ")";
				}

				TableDataSet signDataSet = new TableDataSet();
				signDataSet.setData(signatureObj);
				signDataSet.setCellWidth(new int[] { 25, 25, 25, 25 });
				signDataSet.setCellAlignment(new int[] { 0, 0, 0, 0 });
				signDataSet.setBlankRowsAbove(3);
				signDataSet.setBlankRowsBelow(1);
				signDataSet.setBorder(false);
				signDataSet.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD,
						new Color(0, 0, 0));

				rg.addTableToDoc(signDataSet);
				Object[][] commentDtlsObj = new Object[2][4];

				commentDtlsObj[0][0] = "Finance Signature:";
				commentDtlsObj[0][1] = "_______________________";
				commentDtlsObj[0][2] = "";
				commentDtlsObj[0][3] = "";

				String adminAccountantQuery = "  SELECT  AUTH_ALT_MAIN_SCHL_ID, NVL(C1.EMP_FNAME||' '||C1.EMP_LNAME,' ') , "
						+ " AUTH_ACCOUNTENT ,NVL(C2.TITLE_NAME||' '||C2.EMP_FNAME||' '||C2.EMP_LNAME,' ')   FROM TMS_MANG_AUTH_HDR "
						+ "  INNER JOIN HRMS_EMP_OFFC  C1 ON(C1.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_MAIN_SCHL_ID)  " // AUTH_ALT_MAIN_SCHL_ID
						+ " INNER JOIN HRMS_EMP_OFFC  C2 ON(C2.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_ACCOUNTENT)  "
						+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_ACCOUNTENT)"
						+ " LEFT JOIN HRMS_TITLE C2 ON (C2.TITLE_CODE = C2.EMP_TITLE_CODE)"	
						+ " WHERE  AUTH_ALL_BRNCH='N' AND AUTH_STATUS='A' AND AUTH_BRNCH_ID="
						+ claimApp.getBranchId(); // branch Id
				Object[][] adminAccountantObj = getSqlModel().getSingleResult(
						adminAccountantQuery);
				
				adminAccountantObj=null;

				if (adminAccountantObj != null && adminAccountantObj.length > 0) {
					commentDtlsObj[1][0] = "";

					System.out.println("accountant------------->"
							+ String.valueOf(adminAccountantObj[0][1]));
					System.out.println("finance------------->"
							+ String.valueOf(adminAccountantObj[0][3]));

					commentDtlsObj[1][1] = "("
							+ String.valueOf(adminAccountantObj[0][3]) + ")";
					commentDtlsObj[1][2] = "";
				//	commentDtlsObj[1][3] = "("
					//		+ String.valueOf(adminAccountantObj[0][3]) + ")";
				}
				adminAccountantQuery = "  SELECT  AUTH_ALT_MAIN_SCHL_ID, NVL(C1.EMP_FNAME||' '||C1.EMP_LNAME,' ') , "
						+ " AUTH_ACCOUNTENT ,NVL(C2.TITLE_NAME||' '||C2.EMP_FNAME||' '||C2.EMP_LNAME,' ')   FROM TMS_MANG_AUTH_HDR "
						+ "  INNER JOIN HRMS_EMP_OFFC  C1 ON(C1.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_MAIN_SCHL_ID)  " // AUTH_ALT_MAIN_SCHL_ID
						+ " INNER JOIN HRMS_EMP_OFFC  C2 ON(C2.EMP_ID=TMS_MANG_AUTH_HDR.AUTH_ACCOUNTENT)  "
						+ " LEFT JOIN HRMS_TITLE C2 ON (C2.TITLE_CODE = C2.EMP_TITLE_CODE)"	
						+ " WHERE  AUTH_ALL_BRNCH='Y' AND AUTH_STATUS='A'";// branch
				// Id
				adminAccountantObj = getSqlModel().getSingleResult(
						adminAccountantQuery);
				if (adminAccountantObj != null && adminAccountantObj.length > 0) {
					commentDtlsObj[1][0] = "";
					commentDtlsObj[1][1] = "("
							+ String.valueOf(adminAccountantObj[0][3]) + ")";
					commentDtlsObj[1][2] = "";
				//	commentDtlsObj[1][3] = "("
					//		+ String.valueOf(adminAccountantObj[0][3]) + ")";
				}

				TableDataSet commentDtlInfoTable = new TableDataSet();
				commentDtlInfoTable.setData(commentDtlsObj);
				commentDtlInfoTable.setCellWidth(new int[] { 25, 25, 25, 25 });
				commentDtlInfoTable.setCellAlignment(new int[] { 0, 0, 0, 0 });
				commentDtlInfoTable.setBlankRowsAbove(2);
				commentDtlInfoTable.setBlankRowsBelow(1);
				commentDtlInfoTable.setBorder(false);
				commentDtlInfoTable.setBodyFontDetails(Font.HELVETICA, 8,
						Font.BOLD, new Color(0, 0, 0));
				rg.addTableToDoc(commentDtlInfoTable);

			}

			rg.process();
			rg.createReport(response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	} // End of Function for report

	public void setEligibleAmountAccordingToPolicy(TmsClaimApplication claimApp) {
		try {
			// String policyCode =
			// getTravelPolicyCode(claimApp.getGradId(),claimApp.getAppDate());
			String policyCode = getTravelPolicyCode(claimApp.getGradId(),
					claimApp.getTrvlStartDate());

			String query = " SELECT NVL(POLICY_EXP_ENT_AMT_WITHBILL,0), NVL(POLICY_EXP_ENT_AMT_WITHOUTBILL,0), POLICY_EXP_NO_LIMIT, NVL(GRADE_NAME,'NA') FROM TMS_POLICY_EXPENSE_DTL	"
					+ "	INNER JOIN TMS_POLICY_GRADE_DTL ON(TMS_POLICY_GRADE_DTL.POLICY_ID=TMS_POLICY_EXPENSE_DTL.POLICY_ID and POLICY_GRAD_ID="
					+ claimApp.getGradId()
					+ ")"
					+ " LEFT JOIN HRMS_CITY_GRADE ON(HRMS_CITY_GRADE.GRADE_ID=TMS_POLICY_EXPENSE_DTL.POLICY_CITYGRADE)"
					+ "	WHERE POLICY_EXP_CAT_ID= "
					+ claimApp.getExpenseTypeId()
					+ " AND TMS_POLICY_EXPENSE_DTL.POLICY_ID=" + policyCode;

			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				if (String.valueOf(data[0][2]).equals("Y")) {
					claimApp.setAtActual("At Actual");
					if ((String.valueOf(data[0][3]).equals("NA"))) {
						claimApp.setCityGrade("  With City Grade : NA ");
					} else {
						claimApp.setCityGrade("  With City Grade : "
								+ String.valueOf(data[0][3]));
					}
				} else {
					claimApp.setEligibleAmountFlag(true);
					
					claimApp
							.setAmountWithBill(String.valueOf(Double
									.parseDouble(String.valueOf(Utility.twoDecimals(String
													.valueOf(data[0][0]))))));
					claimApp
							.setAmountWithoutBill(String.valueOf(Double
									.parseDouble(String.valueOf(Utility
											.twoDecimals(String
													.valueOf(data[0][1]))))));
					if ((String.valueOf(data[0][3]).equals("NA"))) {
						claimApp.setCityGrade(" NA ");
					} else {
						claimApp.setCityGrade(String.valueOf(data[0][3]));
					}
				}
			} else {
				claimApp.setAmountWithBill("No Limit");
				claimApp.setAmountWithoutBill("No Limit");
			}
		} catch (Exception e) {
	     	e.printStackTrace();
		}
	}

	public void setItteratorValues(TmsClaimApplication claimApp,
			HttpServletRequest request, String[] srNo, String[] expenseDateIt,
			String[] expenseTypeIt, String[] expenseTypeIdIt,
			String[] particularsIt, String[] expenseAmtIt, String[] proofIt,
			String[] proofRequiredIt, String[] expDtlId, String[] expItId,
			String[] eligibleAmtIt, String[] proofName, String[] proofNameItt,
			String[] itteratorProofNameForSave, String[] policyViolationTextIt) {
		try {
			ArrayList<Object> list = new ArrayList<Object>();
			if (expenseTypeIt != null) {
				for (int i = 0; i < expenseTypeIt.length; i++) {
					TmsClaimApplication bean = new TmsClaimApplication();
					bean.setSrNo(srNo[i]);
					bean.setExpDtlId(expDtlId[i]);
					bean.setExpItId(expItId[i]);
					bean.setExpenseDateIt(expenseDateIt[i]);
					bean.setExpenseTypeIt(expenseTypeIt[i]);
					bean.setExpenseTypeIdIt(expenseTypeIdIt[i]);
					bean.setParticularsIt(particularsIt[i]);
					bean
							.setEligibleAmtIt(Utility
									.twoDecimals(eligibleAmtIt[i]));

					bean.setExpenseAmtIt(Utility.twoDecimals(expenseAmtIt[i]));
					// bean.setProofIt(checkNull(proofIt[i]));
					bean.setProofRequiredIt(proofRequiredIt[i]);
					bean
							.setItteratorProofNameForSave(itteratorProofNameForSave[i]);
					bean.setIttproofName(checkNull(proofNameItt[i]));
					bean.setPolicyViolationTextIt(policyViolationTextIt[i]);
					if (itteratorProofNameForSave != null
							&& itteratorProofNameForSave.length > 0) {
						String[] innerproofName = itteratorProofNameForSave[i]
								.split(",");
						ArrayList <Object> innerlist = new ArrayList <Object>();
						for (int k = 0; k < innerproofName.length; k++) {
							TmsClaimApplication innerbean = new TmsClaimApplication();
							innerbean.setIttproofName(checkNull(innerproofName[k]));
							innerlist.add(innerbean);
						}
						bean.setIttUploadList(innerlist);
					}
					list.add(bean);
				}
				claimApp.setExpenseDtlList(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updatePolicyViolatedStatus(TmsClaimApplication claimApp) {
		try {
			String updateQuery = " UPDATE  TMS_EXP_DTL SET IS_POLICY_VIOLATED='Y' WHERE EXP_APPID="
					+ claimApp.getApplnCode().trim();
			getSqlModel().singleExecute(updateQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean updateSendUnblockRequestStatus(TmsClaimApplication claimApp,
			String applicationid, String applicationCode) {
		boolean result = false;
		try {
			String updateQuery = " UPDATE TMS_APP_EMPDTL SET APPL_EMP_TRAVEL_APPLSTATUS='Z' WHERE APPL_ID= "
					+ applicationid + "AND APPL_CODE=" + applicationCode;
			result = getSqlModel().singleExecute(updateQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean setApproverComments(TmsClaimApplication travelbean,String travlAppId) {

		boolean result = false;
		try {
			String approverCommentQuery = " Select EMP_TOKEN,(EMP_FNAME||' '|| EMP_LNAME ||' '),to_CHAR(EXP_APPRVL_DATE,'DD-MM-YYYY') "
					+ " , DECODE(TRIM(EXP_APPRVL_STATUS),'A','Approved','R','Rejected','B','Sent Back') AS STATUS  "
					+ " ,NVL(EXP_APPR_CMTS,'') "
					+ " from TMS_EXP_APPROVAL_DTL "
					+ " INNER JOIN HRMS_EMP_OFFC ON (TMS_EXP_APPROVAL_DTL.EXP_APPRVR_ID= HRMS_EMP_OFFC.EMP_ID) "
					+ " WHERE EXP_APPID ="
					+ travelbean.getClaimApplnCode();

			Object approverCommentObj[][] = getSqlModel().getSingleResult(
					approverCommentQuery);
			if (approverCommentObj != null && approverCommentObj.length > 0) {
				ArrayList <Object> arrList = new ArrayList <Object> ();
				for (int i = 0; i < approverCommentObj.length; i++) {
					travelbean.setApproverListFlag("true");
					TmsClaimApplication bean = new TmsClaimApplication();
					bean.setPrevApproverID(checkNull(String
							.valueOf(approverCommentObj[i][0])));
					bean.setPrevApproverName(checkNull(String
							.valueOf(approverCommentObj[i][1])));
					bean.setPrevApproverDate(checkNull(String
							.valueOf(approverCommentObj[i][2])));
					bean.setPrevApproverStatus(checkNull(String
							.valueOf(approverCommentObj[i][3])));
					bean.setPrevApproverComment(checkNull(String
							.valueOf(approverCommentObj[i][4])));
					/*
					 * String srNo = i + 1 + getOrdinalFor(i + 1) + "-Approver";
					 * leaveBean.setAppSrNo(srNo);
					 */
					arrList.add(bean);
					result = true;
				}
				travelbean.setApproverCommentList(arrList);
			}
		} catch (Exception e) {
			logger.error("Exception in setApproverComments------" + e);
		}
		return result;
	}

	public String getRatingParameters(TmsClaimApplication travelbean,
			String claimId,String trvlAppId) {
		try {
			String travelRatingParameterQuery = " SELECT RATING_ID, RATING_NAME, RATING_VALUE, RATING_TYPE "
					+ " FROM TMS_RATING_PARAM "
					+ " LEFT JOIN TMS_RATING ON (TMS_RATING.RATING_PARAM = TMS_RATING_PARAM.RATING_ID AND CLAIM_ID= "
					+ claimId + ")"
					+" WHERE RATING_TYPE='desk'";
			Object[][] travelRatingParameterObj = getSqlModel()
					.getSingleResult(travelRatingParameterQuery);

			ArrayList<Object> travelRatingList = new ArrayList<Object>();
			if (travelRatingParameterObj != null
					&& travelRatingParameterObj.length > 0) {

				for (int i = 0; i < travelRatingParameterObj.length; i++) {
					TmsClaimApplication deskbean = new TmsClaimApplication();
					deskbean.setDeskRatingIdItt(String
							.valueOf(travelRatingParameterObj[i][0]));
					deskbean.setDeskRatingNameItt(String
							.valueOf(travelRatingParameterObj[i][1]));
					deskbean.setDeskRatingItt(String
							.valueOf(travelRatingParameterObj[i][2]));
					travelRatingList.add(deskbean);
				}
				travelbean.setTravelRatingParameterList(travelRatingList);
			}

			String hotelNameQuery = "SELECT DISTINCT LODGE_HOTEL_ID, HOTEL_NAME "
					+ " FROM TMS_BOOK_LODGE "
					+ " INNER JOIN HRMS_TRAVEL_HOTEL_MASTER ON (HRMS_TRAVEL_HOTEL_MASTER.HOTEL_ID=TMS_BOOK_LODGE.LODGE_HOTEL_ID) "
					+ " WHERE TRAVEL_APPL_ID=" + trvlAppId;

			Object[][] hotelNameObj = getSqlModel().getSingleResult(
					hotelNameQuery);

			ArrayList<Object> hotelList = new ArrayList<Object>();
			if (hotelNameObj != null && hotelNameObj.length > 0) {
				for (int i = 0; i < hotelNameObj.length; i++) {
					
					travelbean.setShowHotelRatingFlag(true);
					
					TmsClaimApplication hotelNamebean = new TmsClaimApplication();
					hotelNamebean.setHotelIdItt(String
							.valueOf(hotelNameObj[i][0]));
					hotelNamebean.setHotelNameItt(String
							.valueOf(hotelNameObj[i][1]));
				
					String hotelRatingParameterQuery = " SELECT RATING_ID, RATING_NAME, RATING_VALUE, RATING_TYPE "
						+ " FROM TMS_RATING_PARAM "
						+ " LEFT JOIN TMS_RATING ON (TMS_RATING.RATING_PARAM = TMS_RATING_PARAM.RATING_ID AND CLAIM_ID= "
						+ claimId 
						+ " AND HOTEL_ID="
						+ String.valueOf(hotelNameObj[i][0])+ ")"
						+ " WHERE RATING_TYPE='hotel'";
						
					Object[][] hotelRatingParameterObj = getSqlModel()
							.getSingleResult(hotelRatingParameterQuery);
					ArrayList<Object> hotelRatingList = new ArrayList<Object>();

					if (hotelRatingParameterObj != null
							&& hotelRatingParameterObj.length > 0) {

						for (int k = 0; k < hotelRatingParameterObj.length; k++) {
							TmsClaimApplication hotelbean = new TmsClaimApplication();
							hotelbean.setHotelRatingIdItt(String
									.valueOf(hotelRatingParameterObj[k][0]));
							hotelbean.setHotelRatingNameItt(String
									.valueOf(hotelRatingParameterObj[k][1]));
							hotelbean.setHotelRatingItt(String
									.valueOf(hotelRatingParameterObj[k][2]));
							hotelRatingList.add(hotelbean);
						}
						hotelNamebean
								.setHotelRatingParameterList(hotelRatingList);
					}
					hotelList.add(hotelNamebean);
				}
				travelbean.setHotelNameList(hotelList);
			}// outer if
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void saveRatings(TmsClaimApplication claimApp,
			HttpServletRequest request, String[] ratingId, String[] ratingName,
			String[] ratings, String[] hotelRatingId, String[] hotelRatingName,
			String[] hotelRatings, String[] hotelId) {
		try {
			if (!claimApp.getClaimApplnCode().equals("")) {
				String deleteDeskQuery = "DELETE FROM TMS_RATING WHERE CLAIM_ID="
						+ claimApp.getClaimApplnCode();
				getSqlModel().singleExecute(deleteDeskQuery);
			}
			if (ratingId != null && ratingId.length > 0) {
				for (int i = 0; i < ratings.length; i++) {
					Object updateTravelDeskRatings[][] = new Object[1][4];
					updateTravelDeskRatings[0][0] = claimApp
							.getClaimApplnCode();
					updateTravelDeskRatings[0][1] = ratingId[i];
					updateTravelDeskRatings[0][2] = ratings[i];
					updateTravelDeskRatings[0][3] = "";
                    if(updateTravelDeskRatings != null && updateTravelDeskRatings.length >0){
					String updateQuery = "INSERT INTO TMS_RATING(CLAIM_ID, RATING_PARAM, RATING_VALUE, HOTEL_ID) VALUES(?,?,?,?)";
					getSqlModel().singleExecute(updateQuery,
							updateTravelDeskRatings);
                    }
				}
			}

/*			if (!claimApp.getClaimApplnCode().equals("")) {
				String deleteHotelQuery = "DELETE FROM TMS_HOTEL_RATING WHERE CLAIM_ID="
						+ claimApp.getClaimApplnCode();
				getSqlModel().singleExecute(deleteHotelQuery);
			}*/

			if (hotelId != null && hotelId.length > 0) {
				int count = 0;
				for (int i = 0; i < hotelId.length; i++) {
					if (hotelRatings != null && hotelRatings.length > 0) {
						for (int j = 0; j < hotelRatings.length
								/ hotelId.length; j++) {

							Object updateHotelRatings[][] = new Object[1][4];
							updateHotelRatings[0][0] = claimApp
									.getClaimApplnCode();
							updateHotelRatings[0][1] = hotelRatingId[count];
							updateHotelRatings[0][2] = hotelRatings[count];
							updateHotelRatings[0][3] = hotelId[i];

							if(updateHotelRatings != null && updateHotelRatings.length > 0){
								String updateHotelQuery = "INSERT INTO TMS_RATING(CLAIM_ID, RATING_PARAM, RATING_VALUE, HOTEL_ID) VALUES(?,?,?,?)";
								getSqlModel().singleExecute(updateHotelQuery,
										updateHotelRatings);
								count++;
							}							
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveTourReportDetails(TmsClaimApplication claimApp,
			HttpServletRequest request, String[] followUpComments,
			String[] responsibleEmpIds, String[] responsibleEmpTokens,
			String[] responsibleEmpName, String[] targetDates) {
		
		try {
			
			String updateTourCommentQuery = "UPDATE TMS_CLAIM_APPL SET TOUR_REPORT_COMMENTS= '" 
			+claimApp.getTourComments()		
			+ "' WHERE EXP_APPID= "
			+claimApp.getClaimApplnCode();
			
			getSqlModel().singleExecute(updateTourCommentQuery);
			
			if (!claimApp.getClaimApplnCode().equals("")) {
				String deleteFollowUpQuery = "DELETE FROM TMS_TOUR_FOLLOWUP WHERE TOUR_APPL_ID="
						+ claimApp.getClaimApplnCode();
				getSqlModel().singleExecute(deleteFollowUpQuery);
			}
			
			String saveFollowUpActionsQuery = " INSERT INTO TMS_TOUR_FOLLOWUP (TOUR_APPL_ID, TOUR_RESPONSIBLE_PERSON, TOUR_FOLLOWUP_ACTION, TOUR_TARGET_DATE) VALUES (?,?,?,TO_DATE(?,'DD-MM-YYYY'))";
			
			if (responsibleEmpIds != null && responsibleEmpIds.length > 0) {
				for (int i = 0; i < responsibleEmpIds.length; i++) {
					Object insertFollowUp[][] = new Object[1][4];

					insertFollowUp[0][0] = claimApp.getClaimApplnCode();
					insertFollowUp[0][1] = responsibleEmpIds[i];
					insertFollowUp[0][2] = followUpComments[i];
					insertFollowUp[0][3] = targetDates[i];
					
					getSqlModel().singleExecute(saveFollowUpActionsQuery,
							insertFollowUp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getTourReportDetails(TmsClaimApplication travelbean,
			String claimId) {
		try {
			
			String tourCommentQuery = "SELECT NVL(TOUR_REPORT_COMMENTS,' ') FROM TMS_CLAIM_APPL WHERE EXP_APPID="
				+claimId;
			Object[][] tourCommentObj = getSqlModel().getSingleResult(tourCommentQuery);
			
			if(tourCommentObj !=null && tourCommentObj.length > 0){
				travelbean.setTourComments(String.valueOf(tourCommentObj[0][0]));
			}
			
			String followUpActionQuery = "SELECT EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, HRMS_EMP_OFFC.EMP_ID, TOUR_FOLLOWUP_ACTION, TO_CHAR(TOUR_TARGET_DATE,'DD-MM-YYYY') "
					+ " FROM TMS_TOUR_FOLLOWUP "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = TMS_TOUR_FOLLOWUP.TOUR_RESPONSIBLE_PERSON) "
					+ " WHERE TOUR_APPL_ID = " + claimId;
			Object[][] followUpActionObj = getSqlModel().getSingleResult(
					followUpActionQuery);

			ArrayList<Object> followUpList = new ArrayList<Object>();
			if (followUpActionObj != null && followUpActionObj.length > 0) {

				for (int i = 0; i < followUpActionObj.length; i++) {
					TmsClaimApplication followUpbean = new TmsClaimApplication();
					followUpbean.setResponsibleEmpTokenItt(String
							.valueOf(followUpActionObj[i][0]));
					followUpbean.setResponsibleEmpItt(String
							.valueOf(followUpActionObj[i][1]));
					followUpbean.setResponsibleEmpIdItt(String
							.valueOf(followUpActionObj[i][2]));
					followUpbean.setFollowUpCommentsItt(String
							.valueOf(followUpActionObj[i][3]));
					followUpbean.setTargetDateItt(String
							.valueOf(followUpActionObj[i][4]));

					followUpList.add(followUpbean);
				}
				travelbean.setFollowUpActionList(followUpList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getSysDate(){
		String claimApplDate="";
		try {
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			claimApplDate = formater.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return claimApplDate;
	}
	
	public void getDefaultPolicyCurrency(TmsClaimApplication travelClaimBean) {
		try {
			Object[][] travelAdvanceCurrency = getSqlModel().getSingleResult("SELECT NVL(APPL_CURRENCY,'') FROM TMS_APP_EMPDTL WHERE APPL_ID="+travelClaimBean.getApplnId()+"");
			if(travelAdvanceCurrency!=null && travelAdvanceCurrency.length>0)
			{
				travelClaimBean.setCurrencyExpenseAmt(checkNull(String.valueOf(travelAdvanceCurrency[0][0])));
				travelClaimBean.setDefaultCurrencyFlag("true");
				Object[][]  advQuery = getSqlModel().getSingleResult(" SELECT APPL_EMP_ADVANCE_AMT ,TMS_BOOK_CURRENCY FROM TMS_APP_EMPDTL inner JOIN TMS_BOOK_TRAVEL ON (TMS_APP_EMPDTL.APPL_ID=TMS_BOOK_TRAVEL.TRAVEL_APPL_ID) WHERE APPL_ID="+travelClaimBean.getApplnId()+"");
				if(advQuery!=null && advQuery.length>0){
					if(String.valueOf(advQuery[0][0]).equals("0")){
						travelClaimBean.setCurrencyExpenseAmt(checkNull(String.valueOf(advQuery[0][1])));
					}
					
				}
				
			}else
			{
				Object[][] travelBookingCurrency = getSqlModel().getSingleResult("SELECT   NVL(TMS_BOOK_TRAVEL.TMS_BOOK_CURRENCY,'')   FROM TMS_APPLICATION "+ 
																				"inner JOIN TMS_BOOK_LOC_CONV ON(TMS_APPLICATION.APPL_ID=TMS_BOOK_LOC_CONV.TRAVEL_APPL_ID) "+
																				"inner JOIN TMS_BOOK_TRAVEL ON (TMS_APPLICATION.APPL_ID=TMS_BOOK_TRAVEL.TRAVEL_APPL_ID) "+
																				"inner JOIN TMS_BOOK_LODGE ON (TMS_APPLICATION.APPL_ID=TMS_BOOK_LODGE.TRAVEL_APPL_ID) "+
																				"WHERE APPL_ID="+travelClaimBean.getApplnId()+"");
				if(travelBookingCurrency!=null && travelBookingCurrency.length>0)
				{
					travelClaimBean.setCurrencyExpenseAmt(checkNull(String.valueOf(travelBookingCurrency[0][0])));
					travelClaimBean.setDefaultCurrencyFlag("true");
				}else
				{
					Object[][] travelPolicyCurrency = getSqlModel().getSingleResult("SELECT POLICY_TRAVEL_CURRENCY FROM TMS_TRAVEL_POLICY " + 
							  " INNER JOIN  TMS_POLICY_GRADE_DTL ON(TMS_POLICY_GRADE_DTL.POLICY_ID = TMS_TRAVEL_POLICY.POLICY_ID) " +
							  " WHERE POLICY_GRAD_ID= " + travelClaimBean.getGradId() + " AND  POLICY_STATUS='A'");
					if (travelPolicyCurrency != null && travelPolicyCurrency.length > 0) {
						travelClaimBean.setCurrencyExpenseAmt(String.valueOf(travelPolicyCurrency[0][0]));
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
