/**
 * 
 */
package org.paradyne.model.reimbursement;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.reimbursement.ReimbDisbursement;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;
import org.paradyne.model.common.ApplCodeTemplateModel;

import com.lowagie.text.Font;
import com.opensymphony.xwork2.util.profiling.UtilTimerStack;

/**
 * @author aa0554
 * 
 */
public class ReimbDisbursementModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ReimbDisbursementModel.class);

	public void getDisbursementList(ReimbDisbursement bean,
			HttpServletRequest request, String status) {

		
		String query = "SELECT HRMS_REIMB_APPLICATION.REIMB_APPL_CODE,EMP_TOKEN||'-'||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(REIMB_APPL_DATE,'DD-MM-YYYY'),"
				+ " CREDIT_NAME,SUM(NVL(HRMS_REIMB_APPL_DTL.REIMB_APPROVED_AMOUNT,REIMB_CLAIM_AMOUNT)),HRMS_REIMB_APPLICATION.REIMB_CLAIM_ID,REIMB_DISB_ID,REIMB_APPL_STATUS ,HRMS_REIMB_APPLICATION.REIMB_EMP_ID FROM HRMS_REIMB_APPLICATION "
				+ " INNER JOIN HRMS_REIMB_APPL_DTL ON(HRMS_REIMB_APPL_DTL.REIMB_CLAIM_ID=HRMS_REIMB_APPLICATION.REIMB_CLAIM_ID)"
				+ " LEFT JOIN HRMS_REIMB_DISBURSEMENT ON ( HRMS_REIMB_DISBURSEMENT.REIMB_CLAIM_ID=HRMS_REIMB_APPLICATION.REIMB_CLAIM_ID)"
				+ " INNER JOIN HRMS_EMP_OFFC ON (EMP_ID=HRMS_REIMB_APPLICATION.REIMB_EMP_ID)"
				+ " INNER JOIN HRMS_CREDIT_HEAD ON(CREDIT_CODE=HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE)"
				+ " INNER JOIN HRMS_REIMB_CONFIG ON(HRMS_CREDIT_HEAD.CREDIT_CODE=HRMS_REIMB_CONFIG.REIMB_CREDIT_HEAD)"
				+ " WHERE REIMB_APPL_STATUS IN("
				+ status
				+ ") AND REIMB_ACCOUNTANT="
				+ bean.getUserEmpId();
		String filterQuery = getFilterString(bean);
		query += filterQuery;
		query += " GROUP BY "
				+ " HRMS_REIMB_APPLICATION.REIMB_APPL_CODE,EMP_TOKEN||'-'||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(REIMB_APPL_DATE,'DD-MM-YYYY'), "
				+ " CREDIT_NAME,HRMS_REIMB_APPLICATION.REIMB_CLAIM_ID,REIMB_DISB_ID,REIMB_APPL_STATUS ,HRMS_REIMB_APPLICATION.REIMB_EMP_ID ";
		
		Object[][] claimObj = getSqlModel().getSingleResult(query);
		if (claimObj != null && claimObj.length > 0) {
			String[] pageIndex = null;
			if (status.equals("'C'")) {
				pageIndex = Utility.doPaging(bean.getMyPageClosed(),
						claimObj.length, 20);
			} else {
				pageIndex = Utility.doPaging(bean.getMyPage(), claimObj.length,
						20);
				
			}
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			if (status.equals("'C'")) {
				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("pageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
			} else {
				request.setAttribute("totalPagePending", Integer
						.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNoPending", Integer.parseInt(String
						.valueOf(pageIndex[3])));
			}
			if (pageIndex[4].equals("1"))
				if (status.equals("'C'")) {
					bean.setMyPageClosed("1");
				} else {
					bean.setMyPage("1");
				}
			ArrayList tableList = new ArrayList();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {
				ReimbDisbursement beanList = new ReimbDisbursement();
				beanList.setApplRefNoList(Utility.checkNull(String
						.valueOf(claimObj[i][0])));
				beanList.setEmpNameList(Utility.checkNull(String
						.valueOf(claimObj[i][1])));
				beanList.setClaimDateList(Utility.checkNull(String
						.valueOf(claimObj[i][2])));
				beanList.setClaimAmtList(Utility.checkNull(String
						.valueOf(claimObj[i][4])));
				beanList.setApplIdList(Utility.checkNull(String
						.valueOf(claimObj[i][5])));
				beanList.setDisbIdList(Utility.checkNull(String
						.valueOf(claimObj[i][6])));
				beanList.setStatusList(Utility.checkNull(String
						.valueOf(claimObj[i][7])+"_"));
				beanList.setEmpIdList(Utility.checkNull(String
						.valueOf(claimObj[i][8])));
				
				tableList.add(beanList);
			}
			if (status.equals("'C'")) {
				bean.setClosedList(tableList);
				bean.setClosedLength("true");
				bean.setListType("closed");
				
			} else {
				bean.setDisburseList(tableList);
				bean.setPendingLength("true");
				bean.setListType("pending");
			}
		}

	}

	public void getClaimDetails(ReimbDisbursement bean,
			HttpServletRequest request) {
		logger.info("claim id==" + bean.getApplId());
		String empDetailsQuery = "SELECT REIMB_APPL_CODE,EMP_TOKEN||'-'||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,RANK_NAME,CENTER_NAME,CADRE_NAME,DIV_NAME,"
				+ " TO_CHAR(REIMB_APPL_DATE,'DD-MM-YYYY'),REIMB_CLAIM_PARTICULARS,"
				+ " CREDIT_NAME,REIMB_APPL_STATUS,REIMB_EMP_ID,HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE,SUM(NVL(HRMS_REIMB_APPL_DTL.REIMB_APPROVED_AMOUNT,REIMB_CLAIM_AMOUNT)) FROM HRMS_REIMB_APPLICATION"
				+ " INNER JOIN HRMS_REIMB_APPL_DTL ON (HRMS_REIMB_APPL_DTL.REIMB_CLAIM_ID=HRMS_REIMB_APPLICATION.REIMB_CLAIM_ID)"
				+ " INNER JOIN HRMS_EMP_OFFC ON (EMP_ID=REIMB_EMP_ID)"
				+ " INNER JOIN HRMS_DIVISION ON(EMP_DIV=DIV_ID)"
				+ " INNER JOIN HRMS_CENTER ON(EMP_CENTER=CENTER_ID)"
				+ " INNER JOIN HRMS_RANK ON(EMP_RANK=RANK_ID)"
				+ " LEFT JOIN HRMS_CADRE ON(CADRE_ID=EMP_CADRE)"
				+ " INNER JOIN HRMS_CREDIT_HEAD ON(CREDIT_CODE=HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE)"
				+ " WHERE HRMS_REIMB_APPLICATION.REIMB_CLAIM_ID="
				+ bean.getApplId()
				+ " GROUP BY REIMB_APPL_CODE,EMP_TOKEN||'-'||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,RANK_NAME,CENTER_NAME,CADRE_NAME,DIV_NAME,"
				+ " TO_CHAR(REIMB_APPL_DATE,'DD-MM-YYYY'),REIMB_CLAIM_PARTICULARS,"
				+ " CREDIT_NAME,REIMB_APPL_STATUS,REIMB_EMP_ID,HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE";
		Object[][] empDetailsObj = getSqlModel().getSingleResult(
				empDetailsQuery);

		bean.setApplRefNo(Utility
				.checkNull(String.valueOf(empDetailsObj[0][0])));
		bean.setEmpName(Utility.checkNull(String.valueOf(empDetailsObj[0][1])));
		bean.setDesignation(Utility.checkNull(String
				.valueOf(empDetailsObj[0][2])));
		bean.setBranch(Utility.checkNull(String.valueOf(empDetailsObj[0][3])));
		bean.setGrade(Utility.checkNull(String.valueOf(empDetailsObj[0][4])));
		bean
				.setDivision(Utility.checkNull(String
						.valueOf(empDetailsObj[0][5])));
		bean.setClaimDate(Utility
				.checkNull(String.valueOf(empDetailsObj[0][6])));
		bean.setClaimParticular(Utility.checkNull(String
				.valueOf(empDetailsObj[0][7])));
		bean.setReimbHeadList(Utility.checkNull(String
				.valueOf(empDetailsObj[0][8])));
		bean.setApplStatus(Utility.checkNull(String
				.valueOf(empDetailsObj[0][9])));
		bean.setEmpId(Utility.checkNull(String.valueOf(empDetailsObj[0][10])));
		bean.setReimbHead(Utility.checkNull(String
				.valueOf(empDetailsObj[0][11])));
		bean.setDisbAmount(Utility.checkNull(String
				.valueOf(empDetailsObj[0][12])));
		/*
		 * Display expense details
		 */
		String expenseQuery = "SELECT TO_CHAR(REIMB_EXPENSE_DATE,'DD-MM-YYYY'), NVL(REIMB_APPROVED_AMOUNT,REIMB_CLAIM_AMOUNT), REIMB_PROOF,REIMB_REFERENCE_ID "
				+ " FROM HRMS_REIMB_APPL_DTL WHERE REIMB_CLAIM_ID="
				+ bean.getApplId();
		Object[][] expenseObj = getSqlModel().getSingleResult(expenseQuery);
		double totalClaimAmt = 0;
		if (expenseObj != null && expenseObj.length > 0) {
			ArrayList expenseList = new ArrayList();
			for (int i = 0; i < expenseObj.length; i++) {
				ReimbDisbursement expenseBean = new ReimbDisbursement();
				expenseBean.setReimbHeadList(Utility.checkNull(String
						.valueOf(empDetailsObj[0][8])));
				expenseBean.setExpenseDate(Utility.checkNull(String
						.valueOf(expenseObj[i][0])));
				expenseBean.setClaimAmtHead(Utility.checkNull(String
						.valueOf(expenseObj[i][1])));
				expenseBean.setProofHidden(Utility.checkNull(String
						.valueOf(expenseObj[i][2])));
				expenseBean.setProofRefNoHidden(Utility.checkNull(String
						.valueOf(expenseObj[i][3])));
				totalClaimAmt = totalClaimAmt
						+ Double.parseDouble(String.valueOf(expenseObj[i][1]));
				if (!Utility.checkNull(String.valueOf(expenseObj[i][2]))
						.equals("")) {
					String[] proofs = String.valueOf(expenseObj[i][2]).split(
							",");
					String[] refNo = String.valueOf(expenseObj[i][3])
							.split(",");
					ArrayList proofList = new ArrayList();
					for (int j = 0; j < refNo.length; j++) {
						ReimbDisbursement proofBean = new ReimbDisbursement();
						proofBean.setProof(proofs[j]);
						proofBean.setProofRefNo(refNo[j]);
						proofList.add(proofBean);
					}
					expenseBean.setProofList(proofList);
				}
				expenseList.add(expenseBean);
			}
			bean.setExpenseList(expenseList);
			bean.setTotalClaimAmt(String.valueOf(totalClaimAmt));
		}

	}

	public String getFilterString(ReimbDisbursement bean) {

		String filterQuery = "";
		try {
			if (!bean.getApplIdSearch().equals("")) {
				filterQuery += " AND UPPER(HRMS_REIMB_APPLICATION.REIMB_APPL_CODE) LIKE UPPER('%"
						+ bean.getApplIdSearch() + "%')";
			}
			if (!bean.getEmpIdSearch().equals("")) {
				filterQuery += " AND HRMS_REIMB_APPLICATION.REIMB_EMP_ID= " + bean.getEmpIdSearch();
			}
			if (!bean.getReimbHeadSearch().equals("")) {
				filterQuery += " AND HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE="
						+ bean.getReimbHeadSearch();
			}
			if (!bean.getClaimDateSearch().equals("")) {
				filterQuery += " AND TO_CHAR(REIMB_APPL_DATE,'DD-MM-YYYY')='"
						+ bean.getClaimDateSearch() + "'";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return filterQuery;
	}

	public void getApproverComments(ReimbDisbursement bean) {
		String commentsQuery = "SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,NVL(REIMB_APPROVER_COMMENTS,' '),TO_CHAR(REIMB_APPROVAL_DATE,'DD-MM-YYYY HH12:MI PM')"
				+ " FROM HRMS_REIMB_APPL_PATH "
				+ " INNER JOIN HRMS_EMP_OFFC ON(EMP_ID=REIMB_APPROVER)"
				+ " WHERE REIMB_CLAIM_ID=" + bean.getApplId();
		Object[][] commentsObj = getSqlModel().getSingleResult(commentsQuery);

		if (commentsObj != null && commentsObj.length > 0) {
			ArrayList tableList = new ArrayList();
			for (int j = 0; j < commentsObj.length; j++) {
				ReimbDisbursement beanList = new ReimbDisbursement();
				beanList.setApproverNameList(Utility.checkNull(String
						.valueOf(commentsObj[j][0])));
				beanList.setApproverCommentsList(Utility.checkNull(String
						.valueOf(commentsObj[j][1])));
				beanList.setApproverDateList(Utility.checkNull(String
						.valueOf(commentsObj[j][2])));
				tableList.add(beanList);
			}
			bean.setApproverCommentList(tableList);
			bean.setApproverListFlag("true");
		} else {
			bean.setApproverListFlag("false");
		}

	}

	public void setPayModeList(ReimbDisbursement bean) {

		String payListQuery = "SELECT DISTINCT NVL(REIMB_PAYMODE_CASH,'N'), NVL(REIMB_PAYMODE_CHEQUE,'N'), NVL(REIMB_PAYMODE_TRANSFER,'N'), NVL(REIMB_PAYMODE_SALARY,'N'),REIMB_TRANSFER_ACCOUNT FROM HRMS_REIMB_APPL_DTL"
				+ " INNER JOIN HRMS_REIMB_CONFIG ON (HRMS_REIMB_APPL_DTL.REIMB_CREDIT_CODE=HRMS_REIMB_CONFIG.REIMB_CREDIT_HEAD AND REIMB_CLAIM_ID="
				+ bean.getApplId() + ")";
		Object[][] payListObj = getSqlModel().getSingleResult(payListQuery);
		HashMap<String, String> payModeHashmap = new HashMap<String, String>();
		if (payListObj != null && payListObj.length > 0) {
			if (String.valueOf(payListObj[0][0]).equals("Y")) {
				payModeHashmap.put("CS", "Cash");
			}
			if (String.valueOf(payListObj[0][1]).equals("Y")) {
				payModeHashmap.put("CH", "Cheque");
			}
			if (String.valueOf(payListObj[0][2]).equals("Y")) {
				payModeHashmap.put("TR", "Transfer");
			}
			if (String.valueOf(payListObj[0][3]).equals("Y")) {
				payModeHashmap.put("SL", "Salary");
			}
		}

		bean.setPayModeHashmap(payModeHashmap);

		String accNoQuery = "SELECT SAL_MICR_REGULAR,BANK_NAME,SAL_ACCNO_REGULAR FROM HRMS_SALARY_MISC "
				+ " LEFT JOIN HRMS_BANK ON (BANK_MICR_CODE=SAL_MICR_REGULAR)"
				+ " WHERE EMP_ID=" + bean.getEmpId();

		if (String.valueOf(payListObj[0][4]).equals("R")) {
			accNoQuery = "SELECT SAL_REIMBANK,BANK_NAME,SAL_REIMBMENT FROM HRMS_SALARY_MISC "
					+ " LEFT JOIN HRMS_BANK ON (BANK_MICR_CODE=SAL_REIMBANK)"
					+ " WHERE EMP_ID=" + bean.getEmpId();
		}

		Object[][] accNoObj = getSqlModel().getSingleResult(accNoQuery);
		if (accNoObj != null && accNoObj.length > 0) {
			bean.setBank(Utility.checkNull(String.valueOf(accNoObj[0][0])));
			bean.setBankName(Utility.checkNull(String.valueOf(accNoObj[0][1])));
			bean
					.setAccountNo(Utility.checkNull(String
							.valueOf(accNoObj[0][2])));
		}

	}

	public String save(ReimbDisbursement reimbDisb) {
		String maxIdQuery = " SELECT NVL(MAX(REIMB_DISB_ID),0)+1 FROM HRMS_REIMB_DISBURSEMENT ";
		Object maxIdData[][] = getSqlModel().getSingleResult(maxIdQuery);
		String maxId= String.valueOf(maxIdData[0][0]);
		String insertQuery = "INSERT INTO HRMS_REIMB_DISBURSEMENT ( REIMB_DISB_ID, REIMB_CLAIM_ID, REIMB_APPL_CODE, REIMB_EMP_ID,"
				+ " REIMB_DISBURSE_DATE, REIMB_DISBURSE_AMT, REIMB_DISB_PAYMODE, REIMB_CHEQUE_NO,"
				+ " REIMB_CHEQUE_DATE, REIMB_ACCOUNT_NO, REIMB_BANKID, REIMB_MONTH,"
				+ " REIMB_YEAR,REIMB_ACC_COMMENTS ) VALUES (?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?) ";
		Object[][] insertObj = new Object[1][14];
		boolean result=false;
		String returnMsg="";
		String paymode=reimbDisb.getPayMode();
		insertObj[0][0] = maxId;
		insertObj[0][1] = reimbDisb.getApplId();
		insertObj[0][2] = reimbDisb.getApplRefNo();
		insertObj[0][3] = reimbDisb.getEmpId();
		insertObj[0][4] = reimbDisb.getDisbDate();
		insertObj[0][5] = reimbDisb.getDisbAmount();
		insertObj[0][6] = paymode;
		insertObj[0][7] = "";
		insertObj[0][8] = "";
		insertObj[0][9] = "";
		insertObj[0][10] = "";
		insertObj[0][11] = "";
		insertObj[0][12] = "";
		insertObj[0][13] = reimbDisb.getAccountantComments();
		
		if(paymode.equals("CH")){
			insertObj[0][7] = reimbDisb.getChequeNo();
			insertObj[0][8] = reimbDisb.getChequeDate();
		}else if(paymode.equals("TR")){
			insertObj[0][9] = reimbDisb.getAccountNo();
			insertObj[0][10] = reimbDisb.getBank();
		}else if(paymode.equals("SL")){
			insertObj[0][11] = reimbDisb.getMonth();
			insertObj[0][12] = reimbDisb.getYear();
		}
		result=getSqlModel().singleExecute(insertQuery,insertObj);
		if(result){
			reimbDisb.setDisbursementCode(maxId);
			/*
			 * Generate Disbursement Ref ID
			 */
			ApplCodeTemplateModel model = new ApplCodeTemplateModel();
			model.initiate(context, session);
			String applnCode = model
					.generateApplicationCode(
							reimbDisb
									.getDisbursementCode(),
							"REIMBDISB", reimbDisb
									.getEmpId(),reimbDisb.getDisbDate());
			logger
					.info("final appln code in application=="
							+ applnCode);
			if(applnCode.equals("")){
				applnCode ="DISB_"+reimbDisb.getDisbursementCode();
			}
			reimbDisb.setDisbReferenceId(applnCode);
			result = getSqlModel()
					.singleExecute(
							"UPDATE  HRMS_REIMB_DISBURSEMENT SET REIMB_DISB_REF_ID='"
									+ applnCode
									+ "' WHERE REIMB_DISB_ID="
									+ reimbDisb
											.getDisbursementCode());
			model.terminate();
			/*
			 * 
			 */
			returnMsg=applnCode;
			
			double updateBalance=0;
			updateBalance=Double.parseDouble(reimbDisb.getTotalClaimAmt())-Double.parseDouble(reimbDisb.getDisbAmount());
			
			if(updateBalance<0){
				updateBalance=0;
			}
			/**
			 * Update onhold amount from HRMS_REIMB_BALANCE
			 */
			String updateOnholdQuery="UPDATE HRMS_REIMB_BALANCE SET REIMB_ONHOLD_AMT=REIMB_ONHOLD_AMT-?,REIMB_BALANCE_AMT=REIMB_BALANCE_AMT+? WHERE REIMB_CREDIT_CODE=? AND REIMB_EMP_ID=?";
			Object updateOnholdObj[][]=new Object[1][4];
			updateOnholdObj[0][0]= reimbDisb.getDisbAmount();	
			updateOnholdObj[0][1]= updateBalance;
			updateOnholdObj[0][2]= reimbDisb.getReimbHead();
			updateOnholdObj[0][3]= reimbDisb.getEmpId();
				getSqlModel().singleExecute(updateOnholdQuery,updateOnholdObj);
			
			/**
			 * end Update onhold amount
			 */
				/**
				 * UPDATE application status to closed
				 */
				String updateQuery = "  UPDATE HRMS_REIMB_APPLICATION  SET REIMB_APPL_STATUS='S' WHERE REIMB_CLAIM_ID= "+reimbDisb.getApplId();
				getSqlModel().singleExecute(updateQuery);
		}
		return returnMsg;
	}

	public void getDisbDetails(ReimbDisbursement bean,
			HttpServletRequest request) {

		logger.info("cliam id==" + bean.getApplId());
		logger.info("disb id==" + bean.getDisbursementCode());
		String disbDetailsQuery="SELECT REIMB_DISB_ID,REIMB_DISB_REF_ID, TO_CHAR(REIMB_DISBURSE_DATE,'DD-MM-YYYY'), REIMB_DISBURSE_AMT, REIMB_DISB_PAYMODE, REIMB_CHEQUE_NO, REIMB_CHEQUE_DATE, REIMB_ACCOUNT_NO, "
					+" REIMB_BANKID,BANK_NAME, REIMB_MONTH, REIMB_YEAR,NVL(REIMB_ACC_COMMENTS,'')"
					+" FROM HRMS_REIMB_DISBURSEMENT "
					+" LEFT JOIN HRMS_BANK ON (BANK_MICR_CODE=REIMB_BANKID)"
					+" WHERE REIMB_DISB_ID= "+bean.getDisbursementCode();
		
		
		Object [][]disbObj=getSqlModel().getSingleResult(disbDetailsQuery);
		if(disbObj !=null && disbObj.length>0){
			bean.setDisbursementCode(Utility.checkNull(String.valueOf(disbObj[0][0])));
			bean.setDisbReferenceId(Utility.checkNull(String.valueOf(disbObj[0][1])));
			bean.setDisbDate(Utility.checkNull(String.valueOf(disbObj[0][2])));
			bean.setDisbAmount(Utility.checkNull(String.valueOf(disbObj[0][3])));
			bean.setPayMode(Utility.checkNull(String.valueOf(disbObj[0][4])));
			bean.setChequeNo(Utility.checkNull(String.valueOf(disbObj[0][5])));
			bean.setChequeDate(Utility.checkNull(String.valueOf(disbObj[0][6])));
			bean.setAccountNo(Utility.checkNull(String.valueOf(disbObj[0][7])));
			bean.setBank(Utility.checkNull(String.valueOf(disbObj[0][8])));
			bean.setBankName(Utility.checkNull(String.valueOf(disbObj[0][9])));
			bean.setMonth(Utility.checkNull(String.valueOf(disbObj[0][10])));
			bean.setYear(Utility.checkNull(String.valueOf(disbObj[0][11])));
			bean.setAccountantComments(Utility.checkNull(String.valueOf(disbObj[0][12])));
		}

	
		
	}

	public boolean disburseClaim(String claimIds, ReimbDisbursement reimbDisb, HttpServletRequest request) {
		
		String[] ittReimDisbEmployeeID = request.getParameterValues("empIdList");
		
		String payModeQuery="SELECT HRMS_REIMB_DISBURSEMENT.REIMB_DISB_PAYMODE,HRMS_REIMB_DISBURSEMENT.REIMB_DISBURSE_AMT, " +
				"HRMS_REIMB_DISBURSEMENT.REIMB_MONTH, HRMS_REIMB_DISBURSEMENT.REIMB_YEAR," +
				"HRMS_REIMB_APPLICATION.REIMB_CREDIT_CODE,HRMS_REIMB_DISBURSEMENT.REIMB_DISB_ID,HRMS_REIMB_DISBURSEMENT.REIMB_CLAIM_ID " +
				"FROM HRMS_REIMB_DISBURSEMENT " +
				" INNER JOIN HRMS_REIMB_APPLICATION ON (HRMS_REIMB_APPLICATION.REIMB_APPL_CODE = HRMS_REIMB_DISBURSEMENT.REIMB_APPL_CODE) "+
				" WHERE HRMS_REIMB_DISBURSEMENT.REIMB_CLAIM_ID IN( "+claimIds+" ) AND REIMB_DISB_PAYMODE = 'SL' ";
		Object [][]payModeObj=getSqlModel().getSingleResult(payModeQuery);
		if(payModeObj !=null && payModeObj.length>0){
			String sPayInSalQuery="";
			if(payModeObj[0][0].equals("SL")){
				Object[][] payInSalData;
				try {
					payInSalData = new Object[payModeObj.length][11];
					for (int i = 0; i < payModeObj.length; i++) {
					sPayInSalQuery = " INSERT INTO HRMS_MISC_SALARY_UPLOAD (EMP_ID, MONTH, YEAR, UPLOAD_PAY_TYPE, " +
							"SALARY_CODE, SALARY_AMOUNT, UPLOAD_IS_OVERWRITE, APPL_CODE, APPL_TYPE, DISPLAY_FLAG, COMMENTS) "
							+ " VALUES (?,?,?, ?, ?, ?,?,?,?,? ,? ) ";
							payInSalData[i][0] = ittReimDisbEmployeeID[i] ; /* Emp Id */
							payInSalData[i][1] = payModeObj[i][2]; /* Month */
							payInSalData[i][2] = payModeObj[i][3]; /* Year */
							payInSalData[i][3] = "C"; /* Pay Type */
							payInSalData[i][4] = payModeObj[i][4]; /* Credit Code */
							payInSalData[i][5] =  payModeObj[i][1]; /* SALARY_AMOUNT */
							payInSalData[i][6] = "Y"; /* OverWrite */
							payInSalData[i][7] = payModeObj[i][6]; /* OT CALCULATION ID */
							payInSalData[i][8] = "REIMB"; /* Appl Type */
							payInSalData[i][9] = "Y"; /* Display Flag */
							payInSalData[i][10] = "Emp Claim Reimbursement"; /* Comment */
						}	
					
					getSqlModel().singleExecute(sPayInSalQuery, payInSalData);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		
		String updateQuery = "  UPDATE HRMS_REIMB_APPLICATION  SET REIMB_APPL_STATUS='C' WHERE REIMB_CLAIM_ID IN( "+claimIds+" )";
		return getSqlModel().singleExecute(updateQuery);
	
	}

	public void generateStatement(HttpServletResponse response) {
		String disbQuery="SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,DIV_NAME,REIMB_DISBURSE_AMT,TO_CHAR(REIMB_DISBURSE_DATE,'DD-MM-YYYY') "
					+" ,REIMB_DISB_REF_ID,DECODE(REIMB_DISB_PAYMODE,'SL','In Salary','CH','Cheque','TR','Transfer','CS','Cash'), REIMB_CHEQUE_NO, TO_CHAR(REIMB_CHEQUE_DATE,'DD-MM-YYYY'), "
					+" REIMB_ACCOUNT_NO, BANK_NAME, TO_CHAR(TO_DATE(REIMB_MONTH,'mm'),'Month'), REIMB_YEAR,"
					+" DECODE(EMP_STATUS,'S','Service','N','Resigned','R','Retired') FROM HRMS_REIMB_DISBURSEMENT"
					+" INNER JOIN HRMS_REIMB_APPLICATION ON (HRMS_REIMB_DISBURSEMENT.REIMB_CLAIM_ID=HRMS_REIMB_APPLICATION.REIMB_CLAIM_ID)"
					+" INNER JOIN HRMS_EMP_OFFC ON (EMP_ID=HRMS_REIMB_APPLICATION.REIMB_EMP_ID)"
					+" INNER JOIN HRMS_DIVISION ON(DIV_ID=EMP_DIV)"
					+" LEFT JOIN HRMS_BANK ON (BANK_MICR_CODE=REIMB_BANKID)"
					+" WHERE REIMB_APPL_STATUS='S' ORDER BY UPPER(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME)";
		
		
		
		String title = " Disbursement Statement ";
		ReportDataSet rds = new ReportDataSet();
		rds.setFileName(title);
		rds.setReportName(title);
		rds.setPageSize("landscape");
		rds.setReportType("Xls");
		org.paradyne.lib.ireport.ReportGenerator rg = new org.paradyne.lib.ireport.ReportGenerator(rds);
		
		
		
		//For Report heading
		TableDataSet repTitle = new TableDataSet();
		Object[][] repTitleObj = new Object[1][1];
		repTitleObj[0][0] = title;			
		repTitle.setData(repTitleObj);
		repTitle.setCellAlignment(new int[] { 1 });
		repTitle.setCellWidth(new int[] { 100 });
		repTitle.setBodyFontDetails(Font.HELVETICA, 10,Font.BOLD, new Color(0, 0, 0));
		repTitle.setBorder(false);
		repTitle.setBlankRowsBelow(1);
		rg.addTableToDoc(repTitle);			
			
		
		Object empData[][] = getSqlModel().getSingleResult(disbQuery);

		Object[][] objTotalData = new Object[1][14];
		objTotalData =Utility.checkNullObjArr(objTotalData, "");
		String[] columns = new String[] { "Employee Code" ,"Employee Name", "Division",
				 						   "Amount", "Date","Disb.ID","Paymode","Cheque No.","Cheque Date", "Account Number", 
				 						   "Bank Name", "Month","Year", "Employee Status"};
		int[] bcellAlign = new int[] { 0, 0, 0, 2, 1, 0, 0, 2, 0, 0, 1, 0 , 2, 0 };
		int[] bcellWidth = new int[] { 5, 11, 11, 9, 8, 8, 9, 9, 6, 6, 6, 7, 9,9 };
		double totalAmount=0.0d;			
		if(empData!=null && empData.length>0){
				for (int i = 0; i < empData.length; i++) {
					totalAmount += Double.parseDouble(Utility.checkNull(String.valueOf(empData[i][3])));
				}
				objTotalData[0][12]="Total Amount ";
				objTotalData[0][13]=totalAmount;	
				
				
				//For table data
				TableDataSet tdstable = new TableDataSet();
				tdstable.setBlankRowsAbove(1);
				tdstable.setHeader(columns);
				tdstable.setData(empData);
				tdstable.setCellAlignment(bcellAlign);
				tdstable.setCellWidth(bcellWidth);
				tdstable.setBorder(true);
				tdstable.setHeaderBGColor(new Color(225, 225, 225));
				tdstable.setBlankRowsBelow(1);
				rg.addTableToDoc(tdstable);
				
				//For total amount
				bcellAlign[13]=2;
				TableDataSet totaltable = new TableDataSet();					
				totaltable.setData(objTotalData);
				totaltable.setCellAlignment(bcellAlign);
				totaltable.setCellWidth(bcellWidth);
				totaltable.setBorder(false);					
				rg.addTableToDoc(totaltable);
			}
		
		
		else {					
			//If no data is available	
			TableDataSet noData = new TableDataSet();
			Object[][] noDataObj = new Object[1][1];
			noDataObj[0][0] = "No records available";
			
			noData.setData(noDataObj);
			noData.setCellAlignment(new int[] { 1 });
			noData.setCellWidth(new int[] { 100 });
			noData.setBodyFontDetails(Font.HELVETICA, 10,
					Font.BOLD, new Color(0, 0, 0));
			noData.setBorder(false);
			rg.addTableToDoc(noData);
		}
		
		rg.process();
		rg.createReport(response);
	
		
	}
	
	public Object[][] getEmployeeDetails(String claimId)
	{
		Object employeeObj[][]=null;
		String query="SELECT REIMB_EMP_ID FROM HRMS_REIMB_APPLICATION WHERE REIMB_CLAIM_ID ="+claimId;
				
		employeeObj=getSqlModel().getSingleResult(query);
		return employeeObj;
	}

	
}
